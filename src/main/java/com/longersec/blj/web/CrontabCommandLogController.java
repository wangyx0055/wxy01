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
import com.longersec.blj.domain.CrontabCommandLog;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.CrontabCommandLogService;
import com.longersec.blj.utils.SystemCommandUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/crontabCommandLog")
public class CrontabCommandLogController {

	@Autowired
	private CrontabCommandLogService crontabCommandLogService;

	@RequestMapping("/listCrontabCommandLog")
	@ResponseBody
	public JSONObject listCrontabCommandLog(CrontabCommandLog crontabCommandLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCrontabCommandLogs = new ArrayList<Object>();
		ArrayList<CrontabCommandLog> crontabCommandLogs = new ArrayList<CrontabCommandLog>();
		long total = 0;
		resultCrontabCommandLogs = (ArrayList<Object>)crontabCommandLogService.findAll(crontabCommandLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCrontabCommandLogs)) {
			crontabCommandLogs = (ArrayList<CrontabCommandLog>)resultCrontabCommandLogs.get(0);
			total = ((ArrayList<Long>) resultCrontabCommandLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(crontabCommandLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCrontabCommandLog")
	@ResponseBody
	public JSONObject addCrontabCommandLog(CrontabCommandLog crontabCommandLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = crontabCommandLogService.addCrontabCommandLog(crontabCommandLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editCrontabCommandLog")
	@ResponseBody
	public JSONObject editCrontabCommandLog(CrontabCommandLog crontabCommandLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = crontabCommandLogService.editCrontabCommandLog(crontabCommandLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delCrontabCommandLog")
	@ResponseBody
	public JSONObject delCrontabCommandLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = crontabCommandLogService.delCrontabCommandLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
