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
import com.longersec.blj.domain.ConfigLogin;
import com.longersec.blj.service.ConfigLoginService;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configLogin")
public class ConfigLoginController {

	@Autowired
	private ConfigLoginService configLoginService;

	@RequestMapping("/listConfigLogin")
	@ResponseBody
	public JSONObject listConfigLogin(ConfigLogin configLogin,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigLogins = new ArrayList<Object>();
		ArrayList<ConfigLogin> configLogins = new ArrayList<ConfigLogin>();
		long total = 0;
		resultConfigLogins = (ArrayList<Object>)configLoginService.findAll(configLogin, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigLogins)) {
			configLogins = (ArrayList<ConfigLogin>)resultConfigLogins.get(0);
			total = ((ArrayList<Long>) resultConfigLogins.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configLogins);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigLogin")
	@ResponseBody
	public JSONObject addConfigLogin(@Validated ConfigLogin configLogin,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configLoginService.addConfigLogin(configLogin);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigLogin")
	@ResponseBody
	public JSONObject editConfigLogin(@Validated ConfigLogin configLogin,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.put("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configLoginService.editConfigLogin(configLogin);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigLogin")
	@ResponseBody
	public JSONObject delConfigLogin(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configLoginService.delConfigLogin(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
