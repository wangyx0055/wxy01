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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.ConfigSyslog;
import com.longersec.blj.service.ConfigSyslogService;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.SystemCommandUtil;
import com.longersec.blj.utils.SystemUsageUtil;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configSyslog")
public class ConfigSyslogController {

	@Autowired
	private ConfigSyslogService configSyslogService;


	@RequestMapping("/listConfigSyslog")
	@ResponseBody
	public JSONObject listConfigSyslog(ConfigSyslog configSyslog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigSyslogs = new ArrayList<Object>();
		ArrayList<ConfigSyslog> configSyslogs = new ArrayList<ConfigSyslog>();
		long total = 0;
		resultConfigSyslogs = (ArrayList<Object>)configSyslogService.findAll(configSyslog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigSyslogs)) {
			configSyslogs = (ArrayList<ConfigSyslog>)resultConfigSyslogs.get(0);
			total = ((ArrayList<Long>) resultConfigSyslogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configSyslogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigSyslog")
	@ResponseBody
	public JSONObject addConfigSyslog(@Validated ConfigSyslog configSyslog,BindingResult errorResult, HttpServletRequest request, HttpSession session) {

		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configSyslogService.addConfigSyslog(configSyslog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigSyslog")
	@ResponseBody
	public JSONObject editConfigSyslog(@Validated ConfigSyslog configSyslog,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configSyslogService.editConfigSyslog(configSyslog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigSyslog")
	@ResponseBody
	public JSONObject delConfigSyslog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configSyslogService.delConfigSyslog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/updateTimeServer")
	@ResponseBody
	public JSONObject updateTimeServer(String server, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		SystemCommandUtil.execCmd("ntpdate "+server);
		return result;
	}
	
	@RequestMapping("/setDatetime")
	@ResponseBody
	public JSONObject setDatetime(String sdatetime, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		String res = SystemCommandUtil.execCmd("date -s \""+sdatetime+"\"");
		System.out.println(res);
		res = SystemCommandUtil.execCmd("clock -w");
		System.out.println(res);
		return result;
	}
}
