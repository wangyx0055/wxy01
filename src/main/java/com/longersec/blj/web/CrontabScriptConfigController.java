package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.DeviceType;
import com.longersec.blj.domain.OperatorLog;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.CrontabScriptConfig;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.CrontabScriptConfigDeviceService;
import com.longersec.blj.service.CrontabScriptConfigGroupService;
import com.longersec.blj.service.CrontabScriptConfigService;
import com.longersec.blj.service.CrontabScriptConfigUserService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.SystemCommandUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/crontabScriptConfig")
public class CrontabScriptConfigController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private CrontabScriptConfigService crontabScriptConfigService;
	@Autowired
	private CrontabScriptConfigDeviceService crontabScriptConfigDeviceService;
	@Autowired
	private CrontabScriptConfigGroupService crontabScriptConfigGroupService;
	
	@RequestMapping("/listCrontabScriptConfig")
	@ResponseBody
	public JSONObject listCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCrontabScriptConfigs = new ArrayList<Object>();
		ArrayList<CrontabScriptConfig> crontabScriptConfigs = new ArrayList<CrontabScriptConfig>();
		long total = 0;
		resultCrontabScriptConfigs = (ArrayList<Object>)crontabScriptConfigService.findAll(crontabScriptConfig, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCrontabScriptConfigs)) {
			crontabScriptConfigs = (ArrayList<CrontabScriptConfig>)resultCrontabScriptConfigs.get(0);
			total = ((ArrayList<Long>) resultCrontabScriptConfigs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(crontabScriptConfigs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	/*
	 * @RequestMapping("/addCrontabScriptConfig")
	 * 
	 * @ResponseBody public JSONObject addCrontabScriptConfig(CrontabScriptConfig
	 * crontabScriptConfig, HttpServletRequest request, HttpSession session) {
	 * JSONObject result = new JSONObject(); result.accumulate("success", true);
	 * if(result.getBoolean("success")) { Boolean r =
	 * crontabScriptConfigService.addCrontabScriptConfig(crontabScriptConfig);
	 * result.accumulate("success", r?true:false); } return result; }
	 */
	@RequestMapping("/addCrontabScriptConfig")
	@ResponseBody
	public JSONObject addCrontabScriptConfig(@RequestParam(value="devicegroup[]", required=false) Integer[] _devicegroup,@RequestParam(value="device[]", required=false) Integer[] _device, HttpServletRequest request, HttpSession session, CrontabScriptConfig crontabScriptConfig) {
		List<Integer> device 		=  	_device==null?null:Arrays.asList(_device);		
		List<Integer> devicegroup 	= 	_devicegroup==null?null:Arrays.asList(_devicegroup);	
		JSONObject result = new JSONObject();
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("执行任务");
		operatorLog.setDetails("添加执行任务["+crontabScriptConfig.getName()+"]");
		operatorLog.setContent("添加");	
		String name = crontabScriptConfig.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}$";
		if (!name.matches(regex)) {
			result.accumulate("success", false);
			result.accumulate("name", "请输入正确的任务名称");
		}else {
			result.accumulate("success", true);
		}
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");			
			crontabScriptConfigService.addCrontabScriptConfig(crontabScriptConfig);
			if(device!=null) {
			crontabScriptConfigDeviceService.addCrontabScriptConfigDevice(crontabScriptConfig.getId(), device);
			}
			if(devicegroup!=null) {
			crontabScriptConfigGroupService.addCrontabScriptConfigDeviceGroup(crontabScriptConfig.getId(),devicegroup);
			}
			/* result.put("success", crontabScriptConfig.getId()>0?true:false); */
			result.put("success", true);
			operatorLogService.addOperatorLog(operatorLog);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	
	@RequestMapping("/editCrontabScriptConfig")
	@ResponseBody
	public JSONObject editCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = crontabScriptConfigService.editCrontabScriptConfig(crontabScriptConfig);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delCrontabScriptConfig")
	@ResponseBody
	public JSONObject delCrontabScriptConfig(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = crontabScriptConfigService.delCrontabScriptConfig(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	

	@RequestMapping("/checkName")
	@ResponseBody
	public JSONObject checkName(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "name") String name) {
		JSONObject result = new JSONObject();
	/*	if (id == null || "".equals(id)){
			id = 0;
		}
		String count = crontabScriptConfigService.selectName(id,name);
		if (count != null){
			result.put("success", false);
		}else{
			result.put("success", true);
		}
		return result;*/
		result.put("success", true);
		if (id==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			CrontabScriptConfig _config = crontabScriptConfigService.checkname(name);
			if (_config==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			CrontabScriptConfig _config = crontabScriptConfigService.checkname(name);
			if (_config==null){
				result.put("success", true);
			}else {
				if (_config.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}

		return result;
	}

	@RequestMapping("/executeCommand")
	@ResponseBody
	public JSONObject executeCommand(@RequestParam(value = "id") Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			SystemCommandUtil.execCmd(configService.getByName("lsbljCrontabCommand").getValue()+" "+id);
		}
		return result;
	}
}
