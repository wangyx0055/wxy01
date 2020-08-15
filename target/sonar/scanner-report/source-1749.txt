package com.longersec.blj.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.ConfigNetwork;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.ConfigRoute;
import com.longersec.blj.service.ConfigRouteService;
import com.longersec.blj.service.ConfigService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configRoute")
public class ConfigRouteController {

	@Autowired
	private ConfigRouteService configRouteService;
	@Autowired
	private ConfigService configService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/listConfigRoute")
	@ResponseBody
	public JSONObject listConfigRoute(ConfigRoute configRoute,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigRoutes = new ArrayList<Object>();
		ArrayList<ConfigRoute> configRoutes = new ArrayList<ConfigRoute>();
		long total = 0;
		resultConfigRoutes = (ArrayList<Object>)configRouteService.findAll(configRoute, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigRoutes)) {
			configRoutes = (ArrayList<ConfigRoute>)resultConfigRoutes.get(0);
			total = ((ArrayList<Long>) resultConfigRoutes.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configRoutes);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigRoute")
	@ResponseBody
	public JSONObject addConfigRoute(ConfigRoute configRoute, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configRouteService.addConfigRoute(configRoute);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigRoute")
	@ResponseBody
	public JSONObject editConfigRoute(ConfigRoute configRoute, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		if(configRoute.getId()==null||configRoute.getId()==0) {
			return this.addConfigRoute(configRoute, request, session);
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configRouteService.editConfigRoute(configRoute);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigRoute")
	@ResponseBody
	public JSONObject delConfigRoute(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configRouteService.delConfigRoute(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/listInterface")
	@ResponseBody
	public JSONObject listInterface(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		File dir = new File(configService.getByName("interfacePath").getValue());
		File[] listFiles = dir.listFiles();
		JSONArray interfaces = new JSONArray();
		for(File f: listFiles){
			if(f.getName().startsWith("ifcfg-"))
				interfaces.add(f.getName().substring(6));
		}
		result.put("data", interfaces);
		return result;
	}

	@RequestMapping("/checkIPName")
	@ResponseBody
	public JSONObject checkIPName(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "destion_ip") String destion_ip) {
		JSONObject result = new JSONObject();
		int idNUll = 0;
		if (id == null) {id = idNUll;}
		int _ip = configRouteService.checkip(id, destion_ip);
		result.put("success", _ip == 0);
		return result;
	}

}
