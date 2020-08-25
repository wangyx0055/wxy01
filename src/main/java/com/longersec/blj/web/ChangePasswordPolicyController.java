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

import com.alibaba.druid.stat.TableStat.Name;
import com.longersec.blj.domain.ChangePasswordPolicy;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.ChangePasswordPolicyDeviceAccountService;
import com.longersec.blj.service.ChangePasswordPolicyGroupService;
import com.longersec.blj.service.ChangePasswordPolicyService;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.SystemCommandUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/changePasswordPolicy")
public class ChangePasswordPolicyController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private ChangePasswordPolicyService changePasswordPolicyService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private ChangePasswordPolicyGroupService changePasswordPolicyGroupService;
	@Autowired
	private ChangePasswordPolicyDeviceAccountService changePasswordPolicyDeviceAccountService;

	@RequestMapping("/listChangePasswordPolicy")
	@ResponseBody
	public JSONObject listChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy,@RequestParam(value = "",required = false)String name,HttpServletRequest request, HttpSession session) {
		if (name==null) {
			name="";
		}
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultChangePasswordPolicys = new ArrayList<Object>();
		ArrayList<ChangePasswordPolicy> changePasswordPolicys = new ArrayList<ChangePasswordPolicy>();
		long total = 0;
		resultChangePasswordPolicys = (ArrayList<Object>)changePasswordPolicyService.findAll(changePasswordPolicy,name, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultChangePasswordPolicys)) {
			changePasswordPolicys = (ArrayList<ChangePasswordPolicy>)resultChangePasswordPolicys.get(0);
			total = ((ArrayList<Long>) resultChangePasswordPolicys.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(changePasswordPolicys);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addChangePasswordPolicy" )
	@ResponseBody
	public JSONObject addChangePasswordPolicy(@RequestParam(value = "devices[]", required=false) Integer[] _devices,@RequestParam(value = "devicegroup[]", required=false) Integer[] _devicegroup,ChangePasswordPolicy changePasswordPolicy, HttpServletRequest request, HttpSession session) {
		List<Integer> devices =  _devices==null?null:Arrays.asList(_devices);
		List<Integer> devicegroup =  _devicegroup==null?null:Arrays.asList(_devicegroup);
		JSONObject result = new JSONObject();
		String name = changePasswordPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("name", "策略名格式不正确");	
			result.accumulate("success", false);	
		}else {
			result.accumulate("success", true);	
			result.accumulate("name", "");	
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("改密策略");
		operatorLog.setDetails("增加改密策略");
		operatorLog.setContent("add");
		//是否操作成功
		if(result.getBoolean("success")) {
		operatorLog.setResult("成功");
		operatorLogService.addOperatorLog(operatorLog);
		Boolean r = changePasswordPolicyService.addChangePasswordPolicy(changePasswordPolicy);
		if(devices!=null) {
			changePasswordPolicyDeviceAccountService.addChangePasswordPolicyDeviceAccount(changePasswordPolicy.getId(),devices);
		}
		if(devicegroup!=null) {
			changePasswordPolicyGroupService.addChangePasswordPolicyGroup(changePasswordPolicy.getId(),devicegroup );
		}
		result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editChangePasswordPolicy")
	@ResponseBody
	public JSONObject editChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<ChangePasswordPolicy> list = changePasswordPolicyService.checkName();
		List<String> nameList = new ArrayList<String>();
		for (ChangePasswordPolicy names : list) {
			nameList.add(names.getName());
		}
		String name = changePasswordPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("name", "策略名格式不正确");	
			result.accumulate("success", false);	
		}else {
			result.accumulate("success", true);	
			result.accumulate("name", "");	
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("改密策略");
		operatorLog.setDetails("修改改密策略");
		operatorLog.setContent("edit");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = changePasswordPolicyService.editChangePasswordPolicy(changePasswordPolicy);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delChangePasswordPolicy")
	@ResponseBody
	public JSONObject delChangePasswordPolicy(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("改密策略");
		operatorLog.setDetails("删除改密策略");
		operatorLog.setContent("delete");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = changePasswordPolicyService.delChangePasswordPolicy(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/executeCommand")
	@ResponseBody
	public JSONObject executeCommand(@RequestParam(value = "id") Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			SystemCommandUtil.execCmd(configService.getByName("lsbljChangePasswordCommand").getValue()+" "+id);
		}
		return result;
	}
}
