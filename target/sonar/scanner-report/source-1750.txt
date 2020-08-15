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
import com.longersec.blj.domain.ConfigSms;
import com.longersec.blj.service.ConfigSmsService;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configSms")
public class ConfigSmsController {

	@Autowired
	private ConfigSmsService configSmsService;

	@RequestMapping("/listConfigSms")
	@ResponseBody
	public JSONObject listConfigSms(ConfigSms configSms,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigSmss = new ArrayList<Object>();
		ArrayList<ConfigSms> configSmss = new ArrayList<ConfigSms>();
		long total = 0;
		resultConfigSmss = (ArrayList<Object>)configSmsService.findAll(configSms, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigSmss)) {
			configSmss = (ArrayList<ConfigSms>)resultConfigSmss.get(0);
			total = ((ArrayList<Long>) resultConfigSmss.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configSmss);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigSms")
	@ResponseBody
	public JSONObject addConfigSms(@Validated ConfigSms configSms,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configSmsService.addConfigSms(configSms);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigSms")
	@ResponseBody
	public JSONObject editConfigSms(@Validated ConfigSms configSms,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		boolean r = configSmsService.editConfigSms(configSms);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delConfigSms")
	@ResponseBody
	public JSONObject delConfigSms(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configSmsService.delConfigSms(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/smstest")
	@ResponseBody
	public JSONObject smstest( HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		return result;
	}
}
