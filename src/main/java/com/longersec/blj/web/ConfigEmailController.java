package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.longersec.blj.domain.ConfigEmail;
import com.longersec.blj.service.ConfigEmailService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configEmail")
public class ConfigEmailController {

	@Autowired
	private ConfigEmailService configEmailService;

	@RequestMapping("/listConfigEmail")
	@ResponseBody
	public JSONObject listConfigEmail(ConfigEmail configEmail,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigEmails = new ArrayList<Object>();
		ArrayList<ConfigEmail> configEmails = new ArrayList<ConfigEmail>();
		long total = 0;
		resultConfigEmails = (ArrayList<Object>)configEmailService.findAll(configEmail, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigEmails)) {
			configEmails = (ArrayList<ConfigEmail>)resultConfigEmails.get(0);
			total = ((ArrayList<Long>) resultConfigEmails.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configEmails);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigEmail")
	@ResponseBody
	public JSONObject addConfigEmail(@Validated ConfigEmail configEmail,BindingResult errorResult,  HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configEmailService.addConfigEmail(configEmail);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigEmail")
	@ResponseBody
	public JSONObject editConfigEmail(@Validated ConfigEmail configEmail,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		Map<String,Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			return result;
		}
		if(configEmail.getPassword().equals("xxxxxx")) {
			configEmail.setPassword(null);
		}
		Boolean r = configEmailService.editConfigEmail(configEmail);
		if (r){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping("/delConfigEmail")
	@ResponseBody
	public JSONObject delConfigEmail(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configEmailService.delConfigEmail(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
