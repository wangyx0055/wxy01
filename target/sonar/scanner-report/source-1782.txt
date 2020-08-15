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
import com.longersec.blj.domain.LoginLog;
import com.longersec.blj.service.LoginLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/loginLog")
public class LoginLogController {

	@Autowired
	private LoginLogService loginLogService;

	@RequestMapping("/listLoginLog")
	@ResponseBody
	public JSONObject listLoginLog(LoginLog loginLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<LoginLog> loginLogs = new ArrayList<LoginLog>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)loginLogService.findAll(loginLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<LoginLog>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLoginLog")
	@ResponseBody
	public JSONObject addLoginLog(LoginLog loginLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = loginLogService.addLoginLog(loginLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLoginLog")
	@ResponseBody
	public JSONObject editLoginLog(LoginLog loginLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = loginLogService.editLoginLog(loginLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLoginLog")
	@ResponseBody
	public JSONObject delLoginLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = loginLogService.delLoginLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/protocolByDate")
	@ResponseBody
	public JSONObject protocolByDate(String interval,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)loginLogService.selectProtocolBydate(interval, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/loginUser")
	@ResponseBody
	public JSONObject loginUser(String interval,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)loginLogService.selectByUser(interval, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/loginReports")
	@ResponseBody
	public JSONObject loginReports(String interval,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)loginLogService.selectByInterval(interval, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
}
