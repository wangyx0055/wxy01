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
import com.longersec.blj.domain.ConfigLdapAd;
import com.longersec.blj.service.ConfigLdapAdService;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configLdapAd")
public class ConfigLdapAdController {

	@Autowired
	private ConfigLdapAdService configLdapAdService;

	@RequestMapping("/listConfigLdapAd")
	@ResponseBody
	public JSONObject listConfigLdapAd(ConfigLdapAd configLdapAd,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigLdapAds = new ArrayList<Object>();
		ArrayList<ConfigLdapAd> configLdapAds = new ArrayList<ConfigLdapAd>();
		long total = 0;
		resultConfigLdapAds = (ArrayList<Object>)configLdapAdService.findAll(configLdapAd, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigLdapAds)) {
			configLdapAds = (ArrayList<ConfigLdapAd>)resultConfigLdapAds.get(0);
			total = ((ArrayList<Long>) resultConfigLdapAds.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configLdapAds);
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		result.put("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigLdapAd")
	@ResponseBody
	public JSONObject addConfigLdapAd(@Validated ConfigLdapAd configLdapAd,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.put("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configLdapAdService.addConfigLdapAd(configLdapAd);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigLdapAd")
	@ResponseBody
	public JSONObject editConfigLdapAd(@Validated ConfigLdapAd configLdapAd,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.put("success", true);
		if(result.getBoolean("success")) {
			if(configLdapAd.getPassword().equals("xxxxxx")) {
				configLdapAd.setPassword(null);
			}
			Boolean r = configLdapAdService.editConfigLdapAd(configLdapAd);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigLdapAd")
	@ResponseBody
	public JSONObject delConfigLdapAd(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		List<Integer> _ids =  Arrays.asList(ids);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configLdapAdService.delConfigLdapAd(_ids);
			result.put("success", r);
		}
		return result;
	}

	@RequestMapping("/asyncUser")
	@ResponseBody
	public JSONObject asyncUser(Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		return result;
	}
}
