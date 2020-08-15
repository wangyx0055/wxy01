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
import com.longersec.blj.domain.ConfigRadius;
import com.longersec.blj.service.ConfigRadiusService;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configRadius")
public class ConfigRadiusController {

	@Autowired
	private ConfigRadiusService configRadiusService;

	@RequestMapping("/listConfigRadius")
	@ResponseBody
	public JSONObject listConfigRadius(ConfigRadius configRadius,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigRadiuss = new ArrayList<Object>();
		ArrayList<ConfigRadius> configRadiuss = new ArrayList<ConfigRadius>();
		long total = 0;
		resultConfigRadiuss = (ArrayList<Object>)configRadiusService.findAll(configRadius, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigRadiuss)) {
			configRadiuss = (ArrayList<ConfigRadius>)resultConfigRadiuss.get(0);
			total = ((ArrayList<Long>) resultConfigRadiuss.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configRadiuss);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigRadius")
	@ResponseBody
	public JSONObject addConfigRadius(@Validated ConfigRadius configRadius,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configRadiusService.addConfigRadius(configRadius);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigRadius")
	@ResponseBody
	public JSONObject editConfigRadius(@Validated ConfigRadius configRadius,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configRadiusService.editConfigRadius(configRadius);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigRadius")
	@ResponseBody
	public JSONObject delConfigRadius(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configRadiusService.delConfigRadius(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/connectTest")
	@ResponseBody
	public JSONObject connectTest(Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		return result;
	}
}
