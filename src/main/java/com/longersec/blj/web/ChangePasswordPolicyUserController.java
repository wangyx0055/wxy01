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
import com.longersec.blj.domain.ChangePasswordPolicyUser;
import com.longersec.blj.service.ChangePasswordPolicyUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/changePasswordPolicyUser")
public class ChangePasswordPolicyUserController {

	@Autowired
	private ChangePasswordPolicyUserService changePasswordPolicyUserService;

	@RequestMapping("/listChangePasswordPolicyUser")
	@ResponseBody
	public JSONObject listChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultChangePasswordPolicyUsers = new ArrayList<Object>();
		ArrayList<ChangePasswordPolicyUser> changePasswordPolicyUsers = new ArrayList<ChangePasswordPolicyUser>();
		long total = 0;
		resultChangePasswordPolicyUsers = (ArrayList<Object>)changePasswordPolicyUserService.findAll(changePasswordPolicyUser, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultChangePasswordPolicyUsers)) {
			changePasswordPolicyUsers = (ArrayList<ChangePasswordPolicyUser>)resultChangePasswordPolicyUsers.get(0);
			total = ((ArrayList<Long>) resultChangePasswordPolicyUsers.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(changePasswordPolicyUsers);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addChangePasswordPolicyUser")
	@ResponseBody
	public JSONObject addChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyUserService.addChangePasswordPolicyUser(changePasswordPolicyUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editChangePasswordPolicyUser")
	@ResponseBody
	public JSONObject editChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyUserService.editChangePasswordPolicyUser(changePasswordPolicyUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delChangePasswordPolicyUser")
	@ResponseBody
	public JSONObject delChangePasswordPolicyUser(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyUserService.delChangePasswordPolicyUser(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
