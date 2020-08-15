package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.LogDeletePolicyUser;
import com.longersec.blj.service.LogDeletePolicyUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/logDeletePolicyUser")
public class LogDeletePolicyUserController {

	@Autowired
	private LogDeletePolicyUserService logDeletePolicyUserService;

	@RequestMapping("/listLogDeletePolicyUser")
	@ResponseBody
	public JSONObject listLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLogDeletePolicyUsers = new ArrayList<Object>();
		ArrayList<LogDeletePolicyUser> logDeletePolicyUsers = new ArrayList<LogDeletePolicyUser>();
		long total = 0;
		resultLogDeletePolicyUsers = (ArrayList<Object>)logDeletePolicyUserService.findAll(logDeletePolicyUser, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLogDeletePolicyUsers)) {
			logDeletePolicyUsers = (ArrayList<LogDeletePolicyUser>)resultLogDeletePolicyUsers.get(0);
			total = ((ArrayList<Long>) resultLogDeletePolicyUsers.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(logDeletePolicyUsers);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLogDeletePolicyUser")
	@ResponseBody
	public JSONObject addLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyUserService.addLogDeletePolicyUser(logDeletePolicyUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLogDeletePolicyUser")
	@ResponseBody
	public JSONObject editLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyUserService.editLogDeletePolicyUser(logDeletePolicyUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLogDeletePolicyUser")
	@ResponseBody
	public JSONObject delLogDeletePolicyUser(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyUserService.delLogDeletePolicyUser(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
