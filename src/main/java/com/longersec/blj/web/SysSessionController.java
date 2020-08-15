package com.longersec.blj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.SysSession;
import com.longersec.blj.domain.User;
import com.longersec.blj.utils.JSBtoAAtoB;
import com.longersec.blj.utils.SerializableUtils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/sysSession")
public class SysSessionController {
	@Autowired
	private SessionDAO sessionDAO;

	@RequestMapping("/listSysSession")
	@ResponseBody
	public JSONObject listSysSession(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSysSessions = new ArrayList<Object>();
		long total = 0;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		JSONArray jsonArray = new JSONArray();
		total = sessions.size();
		for(Session _session:sessions){
			if (_session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
				System.out.println("登录ip:"+_session.getHost());
				System.out.println("登录用户"+_session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
				System.out.println("最后操作日期:"+_session.getLastAccessTime());
				
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) _session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                User user = (User) principalCollection.getPrimaryPrincipal();
				Date loginDate = _session.getStartTimestamp();
				Date refreshDate = _session.getLastAccessTime();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", _session.getId());
				jsonObject.put("username", user.getUsername());
				jsonObject.put("realname", user.getRealname());
				jsonObject.put("rolename", user.getRolename());
				jsonObject.put("last_login_ip", _session.getHost());
				jsonObject.put("logintime", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(loginDate));
				jsonObject.put("refreshtime", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(refreshDate));
				jsonObject.put("self",0);
				if(_session.getId()==session.getId()) {
					jsonObject.put("self",1);
				}
				jsonArray.add(jsonObject);
            }
		}
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/delSysSession")
	@ResponseBody
	public JSONObject delSysSession(@RequestParam(value = "id") String id, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		ArrayList<String> _ids =  new ArrayList<String>();
		_ids.add(Base64Decoder.decodeStr(id));
		
		result.put("success", true);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		Session session = sessionDAO.readSession(id);
        session.setTimeout(0);
		return result;
	}
}
