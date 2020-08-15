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
import com.longersec.blj.domain.AlertLog;
import com.longersec.blj.service.AlertLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/alertLog")
public class AlertLogController {

	@Autowired
	private AlertLogService alertLogService;

	@RequestMapping("/listAlertLog")
	@ResponseBody
	public JSONObject listAlertLog(AlertLog alertLog,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultAlertLogs = new ArrayList<Object>();
		ArrayList<AlertLog> alertLogs = new ArrayList<AlertLog>();
		long total = 0;
		resultAlertLogs = (ArrayList<Object>)alertLogService.findAll(alertLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultAlertLogs)) {
			alertLogs = (ArrayList<AlertLog>)resultAlertLogs.get(0);
			total = ((ArrayList<Long>) resultAlertLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(alertLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addAlertLog")
	@ResponseBody
	public JSONObject addAlertLog(AlertLog alertLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = alertLogService.addAlertLog(alertLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editAlertLog")
	@ResponseBody
	public JSONObject editAlertLog(AlertLog alertLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = alertLogService.editAlertLog(alertLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delAlertLog")
	@ResponseBody
	public JSONObject delAlertLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = alertLogService.delAlertLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
