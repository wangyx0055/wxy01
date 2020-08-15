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
import com.longersec.blj.domain.CrontabScript;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.CrontabScriptService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/crontabScript")
public class
CrontabScriptController {

	@Autowired
	private CrontabScriptService crontabScriptService;
	@Autowired
	private OperatorLogService operatorLogService;
	@RequestMapping("/listCrontabScript")
	@ResponseBody
	public JSONObject listCrontabScript(CrontabScript crontabScript,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCrontabScripts = new ArrayList<Object>();
		ArrayList<CrontabScript> crontabScripts = new ArrayList<CrontabScript>();
		long total = 0;
		resultCrontabScripts = (ArrayList<Object>)crontabScriptService.findAll(crontabScript, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCrontabScripts)) {
			crontabScripts = (ArrayList<CrontabScript>)resultCrontabScripts.get(0);
			total = ((ArrayList<Long>) resultCrontabScripts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(crontabScripts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCrontabScript")
	@ResponseBody
	public JSONObject addCrontabScript(CrontabScript crontabScript, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行任务");
		operatorLog.setDetails("增加执行任务");
		operatorLog.setContent("add");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = crontabScriptService.addCrontabScript(crontabScript);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editCrontabScript")
	@ResponseBody
	public JSONObject editCrontabScript(CrontabScript crontabScript, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行任务");
		operatorLog.setDetails("编辑执行任务");
		operatorLog.setContent("edit");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = crontabScriptService.editCrontabScript(crontabScript);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delCrontabScript")
	@ResponseBody
	public JSONObject delCrontabScript(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行任务");
		operatorLog.setDetails("删除执行任务");
		operatorLog.setContent("delete");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = crontabScriptService.delCrontabScript(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
}
