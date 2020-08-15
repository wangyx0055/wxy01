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
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.OperatorLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/operatorLog")
public class OperatorLogController {

	@Autowired
	private OperatorLogService operatorLogService;

	@RequestMapping("/listOperatorLog")
	@ResponseBody
	public JSONObject listOperatorLog(OperatorLog operatorLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultOperatorLogs = new ArrayList<Object>();
		ArrayList<OperatorLog> operatorLogs = new ArrayList<OperatorLog>();
		long total = 0;
		resultOperatorLogs = (ArrayList<Object>)operatorLogService.findAll(operatorLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultOperatorLogs)) {
			operatorLogs = (ArrayList<OperatorLog>)resultOperatorLogs.get(0);
			total = ((ArrayList<Long>) resultOperatorLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(operatorLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addOperatorLog")
	@ResponseBody
	public JSONObject addOperatorLog(OperatorLog operatorLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = operatorLogService.addOperatorLog(operatorLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editOperatorLog")
	@ResponseBody
	public JSONObject editOperatorLog(OperatorLog operatorLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = operatorLogService.editOperatorLog(operatorLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delOperatorLog")
	@ResponseBody
	public JSONObject delOperatorLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = operatorLogService.delOperatorLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
