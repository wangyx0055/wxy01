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
import com.longersec.blj.domain.ChangePasswordPolicyGroup;
import com.longersec.blj.service.ChangePasswordPolicyGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/changePasswordPolicyGroup")
public class ChangePasswordPolicyGroupController {

	@Autowired
	private ChangePasswordPolicyGroupService changePasswordPolicyGroupService;

	@RequestMapping("/listChangePasswordPolicyGroup")
	@ResponseBody
	public JSONObject listChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultChangePasswordPolicyGroups = new ArrayList<Object>();
		ArrayList<ChangePasswordPolicyGroup> changePasswordPolicyGroups = new ArrayList<ChangePasswordPolicyGroup>();
		long total = 0;
		resultChangePasswordPolicyGroups = (ArrayList<Object>)changePasswordPolicyGroupService.findAll(changePasswordPolicyGroup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultChangePasswordPolicyGroups)) {
			changePasswordPolicyGroups = (ArrayList<ChangePasswordPolicyGroup>)resultChangePasswordPolicyGroups.get(0);
			total = ((ArrayList<Long>) resultChangePasswordPolicyGroups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(changePasswordPolicyGroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addChangePasswordPolicyGroup")
	@ResponseBody
	public JSONObject addChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyGroupService.addChangePasswordPolicyGroup(changePasswordPolicyGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editChangePasswordPolicyGroup")
	@ResponseBody
	public JSONObject editChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyGroupService.editChangePasswordPolicyGroup(changePasswordPolicyGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delChangePasswordPolicyGroup")
	@ResponseBody
	public JSONObject delChangePasswordPolicyGroup(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyGroupService.delChangePasswordPolicyGroup(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
