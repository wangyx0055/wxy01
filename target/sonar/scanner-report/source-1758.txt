package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.service.CrontabScriptConfigDeviceService;
import com.longersec.blj.service.CrontabScriptConfigGroupService;
import com.longersec.blj.service.CrontabScriptConfigUserService;

import com.longersec.blj.utils.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.CrontabScriptLog;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.CrontabScriptLogService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author wxy
 */
@Controller
@RequestMapping(value = "/crontabScriptLog")
public class CrontabScriptLogController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private CrontabScriptLogService crontabScriptLogService;
	@Autowired
	private CrontabScriptConfigDeviceService crontabScriptConfigDeviceService;
	@Autowired
	private CrontabScriptConfigGroupService crontabScriptConfigGroupService;
	@Autowired
	private CrontabScriptConfigUserService crontabScriptConfigUserService;

	@RequestMapping("/listCrontabScriptLog")
	@ResponseBody
	public JSONObject listCrontabScriptLog(CrontabScriptLog crontabScriptLog,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCrontabScriptLogs = new ArrayList<Object>();
		ArrayList<CrontabScriptLog> crontabScriptLogs = new ArrayList<CrontabScriptLog>();
		long total = 0;
		resultCrontabScriptLogs = (ArrayList<Object>)crontabScriptLogService.findAll(crontabScriptLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCrontabScriptLogs)) {
			crontabScriptLogs = (ArrayList<CrontabScriptLog>)resultCrontabScriptLogs.get(0);
			total = ((ArrayList<Long>) resultCrontabScriptLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(crontabScriptLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCrontabScriptLog")
	@ResponseBody
	public JSONObject addCrontabScriptLog(@Validated CrontabScriptLog crontabScriptLog, BindingResult errorResult,@RequestParam(value = "users[]",required = false) Integer[] _users,@RequestParam(value = "groups[]",required = false) Integer[] _groups,@RequestParam(value = "devices[]",required = false) Integer[] _devices,@RequestParam(value = "devicegroup[]",required = false) Integer[] _devicegroup, HttpServletRequest request, HttpSession session) {
		List<Integer> users =  _users==null?null:Arrays.asList(_users);
		List<Integer> groups =  _groups==null?null:Arrays.asList(_groups);
		List<Integer> devices =  _devices==null?null:Arrays.asList(_devices);
		List<Integer> devicegroup =  _devicegroup==null?null:Arrays.asList(_devicegroup);
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		JSONObject result = new JSONObject();
		result.put("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行日志");
		operatorLog.setDetails("增加执行日志");
		operatorLog.setContent("add");
		if (resultMap == null){
			Boolean r = crontabScriptLogService.addCrontabScriptLog(crontabScriptLog);
			if (r){
				operatorLog.setResult("成功");
				if (devices != null){
					crontabScriptConfigDeviceService.addCrontabScriptConfigDevice(crontabScriptLog.getId(),devices);
				}
				if (users != null){
					crontabScriptConfigUserService.addCrontabScriptConfigUser(crontabScriptLog.getId(),users);
				}
				if (groups != null){
					crontabScriptConfigGroupService.addCrontabScriptConfigGroup(crontabScriptLog.getId(),groups);
				}
				if (devicegroup != null){
					crontabScriptConfigGroupService.addCrontabScriptConfigDeviceGroup(crontabScriptLog.getId(),devicegroup);
				}
			}else{
				operatorLog.setResult("失败");
			}
			result.put("success", r?true:false);
		}else{
			result.put("success",false);
			operatorLog.setResult("失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/editCrontabScriptLog")
	@ResponseBody
	public JSONObject editCrontabScriptLog(@Validated CrontabScriptLog crontabScriptLog, BindingResult errorResult,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		result.put("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行日志");
		operatorLog.setDetails("编辑执行日志");
		operatorLog.setContent("edit");
		if (resultMap == null){
			//是否操作成功
			Boolean r = crontabScriptLogService.editCrontabScriptLog(crontabScriptLog);
			if (r){
				operatorLog.setResult("成功");
			}else{
				operatorLog.setResult("失败");
			}
			result.put("success", r?true:false);
		}else{
			result.put("success",false);
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/delCrontabScriptLog")
	@ResponseBody
	public JSONObject delCrontabScriptLog(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.accumulate("msg", "id不能为空");
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行日志");
		operatorLog.setDetails("删除执行日志");
		operatorLog.setContent("delete");
		//是否操作成功
		Boolean r = crontabScriptLogService.delCrontabScriptLog(_ids);
		if (r){
			operatorLog.setResult("成功");
		}else{
			operatorLog.setResult("失败");
		}
		result.put("success", r?true:false);
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}
}
