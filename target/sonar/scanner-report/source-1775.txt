package com.longersec.blj.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.LiveSessions;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.LiveSessionsService;
import com.longersec.blj.utils.httpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/liveSessions")
public class LiveSessionsController {

	@Autowired
	private LiveSessionsService liveSessionsService;
	@Autowired
	private ConfigService configService;

	@RequestMapping("/listLiveSessions")
	@ResponseBody
	public JSONObject listLiveSessions(LiveSessions liveSessions,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLiveSessionss = new ArrayList<Object>();
		ArrayList<LiveSessions> liveSessionss = new ArrayList<LiveSessions>();
		long total = 0;
		resultLiveSessionss = (ArrayList<Object>)liveSessionsService.findAll(liveSessions, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLiveSessionss)) {
			liveSessionss = (ArrayList<LiveSessions>)resultLiveSessionss.get(0);
			total = ((ArrayList<Long>) resultLiveSessionss.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(liveSessionss);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLiveSessions")
	@ResponseBody
	public JSONObject addLiveSessions(LiveSessions liveSessions, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = liveSessionsService.addLiveSessions(liveSessions);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLiveSessions")
	@ResponseBody
	public JSONObject editLiveSessions(LiveSessions liveSessions, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = liveSessionsService.editLiveSessions(liveSessions);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLiveSessions")
	@ResponseBody
	public JSONObject delLiveSessions(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = liveSessionsService.delLiveSessions(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
    @RequestMapping("/updateKey")
    public void updateKey(HttpServletRequest request,HttpServletResponse response, HttpSession session, String key, String token, String uuid, Integer connectid) {
    	LiveSessions liveSessions = new LiveSessions();
    	liveSessions.setMonitorkey(key);
    	liveSessions.setRecord_id(connectid);
    	liveSessions.setAuthtoken(token);
    	liveSessions.setUuid(uuid);
    	liveSessionsService.updateKey(liveSessions);
		return ;
	}
    
    @RequestMapping("/Monitor")
    public void deviceMonitor(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer live_id) {
    	LiveSessions liveSessions = liveSessionsService.getById(live_id);
    	try {
    		if(liveSessions.getMonitorkey().isEmpty()) {
    			Config config = configService.getByName("connectDataSource");
    			String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
          		url += "/cLogin/api/session/tunnels/"+liveSessions.getUuid()+"/activeConnection/sharingCredentials/"+liveSessions.getConnectid()+"?token="+liveSessions.getAuthtoken();
    			JSONObject resJsonObject = com.longersec.blj.utils.httpClient.doGetStr(url);
    			liveSessions.setMonitorkey(resJsonObject.getJSONObject("values").getString("key"));
    			System.out.println(resJsonObject);
    		}
			response.sendRedirect("/cLogin/#/?key="+liveSessions.getMonitorkey());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}
    
    @RequestMapping("/Cut")
    @ResponseBody
    public JSONObject Cut(HttpServletRequest request,HttpSession session, Integer live_id) {
    	LiveSessions liveSessions = liveSessionsService.getById(live_id);
    	Config config = configService.getByName("connectDataSource");
    	JSONObject resultObj = new JSONObject();  
    	JSONObject jsonParam = new JSONObject();
        HttpClient httpClient = new DefaultHttpClient();  
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
         		url += "/cLogin/api/session/data/"+config.getValue()+"/activeConnections?token=abcd&userid="+session.getAttribute("userid");
        jsonParam.put("op","remove");
        jsonParam.put("path", "/"+liveSessions.getUuid());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonParam);
        HttpPatch httpPatch = new HttpPatch(url);  
        httpPatch.setHeader("Content-type", "application/json");  
        httpPatch.setHeader("Charset", HTTP.UTF_8);  
        httpPatch.setHeader("Accept", "application/json");  
        httpPatch.setHeader("Accept-Charset", HTTP.UTF_8);  
        try {  
            if (jsonParam != null){
                StringEntity entity = new StringEntity(jsonArray.toString()+"]",HTTP.UTF_8);  
                httpPatch.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPatch);  
            int StatusCode = response.getStatusLine().getStatusCode(); 
            if(StatusCode==204) {
            	resultObj.put("success", true);
            }else {
            	resultObj.put("success", false);
            }
        } catch (ParseException | JSONException | IOException   e) {  
            e.printStackTrace();  
        }
		return resultObj;
	}
}
