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
import com.longersec.blj.domain.LogDeletePolicy;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.LogDeletePolicyService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/logDeletePolicy")
public class LogDeletePolicyController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private LogDeletePolicyService logDeletePolicyService;

	@RequestMapping("/listLogDeletePolicy")
	@ResponseBody
	public JSONObject listLogDeletePolicy(LogDeletePolicy logDeletePolicy,@RequestParam(value = "",required = false)String name,HttpServletRequest request, HttpSession session) {
		if (name==null) {
			name="";
		}
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLogDeletePolicys = new ArrayList<Object>();
		ArrayList<LogDeletePolicy> logDeletePolicys = new ArrayList<LogDeletePolicy>();
		long total = 0;
		resultLogDeletePolicys = (ArrayList<Object>)logDeletePolicyService.findAll(logDeletePolicy, name,page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLogDeletePolicys)) {
			logDeletePolicys = (ArrayList<LogDeletePolicy>)resultLogDeletePolicys.get(0);
			total = ((ArrayList<Long>) resultLogDeletePolicys.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(logDeletePolicys);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLogDeletePolicy")
	@ResponseBody
	public JSONObject addLogDeletePolicy(LogDeletePolicy logDeletePolicy, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("日志");
		operatorLog.setDetails("增加日志");
		operatorLog.setContent("add");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = logDeletePolicyService.addLogDeletePolicy(logDeletePolicy);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editLogDeletePolicy")
	@ResponseBody
	public JSONObject editLogDeletePolicy(LogDeletePolicy logDeletePolicy, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("日志");
		operatorLog.setDetails("编辑日志");
		operatorLog.setContent("edit");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = logDeletePolicyService.editLogDeletePolicy(logDeletePolicy);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delLogDeletePolicy")
	@ResponseBody
	public JSONObject delLogDeletePolicy(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.put("success", true);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("日志");
		operatorLog.setDetails("删除日志");
		operatorLog.setContent("delete");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = logDeletePolicyService.delLogDeletePolicy(_ids);
			result.put("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
}
