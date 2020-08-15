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
import com.longersec.blj.domain.SmsLog;
import com.longersec.blj.service.SmsLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/smsLog")
public class SmsLogController {

	@Autowired
	private SmsLogService smsLogService;

	@RequestMapping("/listSmsLog")
	@ResponseBody
	public JSONObject listSmsLog(SmsLog smsLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSmsLogs = new ArrayList<Object>();
		ArrayList<SmsLog> smsLogs = new ArrayList<SmsLog>();
		long total = 0;
		resultSmsLogs = (ArrayList<Object>)smsLogService.findAll(smsLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultSmsLogs)) {
			smsLogs = (ArrayList<SmsLog>)resultSmsLogs.get(0);
			total = ((ArrayList<Long>) resultSmsLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(smsLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSmsLog")
	@ResponseBody
	public JSONObject addSmsLog(SmsLog smsLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = smsLogService.addSmsLog(smsLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editSmsLog")
	@ResponseBody
	public JSONObject editSmsLog(SmsLog smsLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = smsLogService.editSmsLog(smsLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delSmsLog")
	@ResponseBody
	public JSONObject delSmsLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = smsLogService.delSmsLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
