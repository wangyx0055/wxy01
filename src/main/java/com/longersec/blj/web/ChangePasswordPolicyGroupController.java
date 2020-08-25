package com.longersec.blj.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.GroupService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
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
	
	@Autowired
	private GroupService groupService;

	@RequestMapping("/editChangePasswordPolicyDeviceGroup")
	@ResponseBody
	public JSONObject editChangePasswordPolicyDeviceGroup(@RequestParam(value = "devicegroup[]",required = false) Integer[] devicegroup,@RequestParam(value = "policy_id") Integer policy_id,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		int type = 1;
		boolean r = true;
		changePasswordPolicyGroupService.deleteBypolicy_id(policy_id,type);
		if(devicegroup != null) {
			r = changePasswordPolicyGroupService.editChangePasswordPolicyDeviceGroup(policy_id, Arrays.asList(devicegroup));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/findChangePasswordPolicyGroupAndUser")
	@ResponseBody
	public JSONObject findChangePasswordPolicyGroupAndUser(@RequestParam("policy_id")Integer policy_id,
	                                               @RequestParam(value = "page_start",required = false)int page_start,
	                                               @RequestParam(value ="page_length",required = false)int page_length) {
		ArrayList<UserGroup> resultChangePasswordPolicyGroups = new ArrayList<UserGroup>();
		ArrayList<UserGroup> resultGroups = new ArrayList<UserGroup>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultChangePasswordPolicyGroups = (ArrayList<UserGroup>) changePasswordPolicyGroupService.selectById(policy_id);
		resultGroups = (ArrayList<UserGroup>)groupService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultGroups.removeAll(resultChangePasswordPolicyGroups);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultChangePasswordPolicyGroups);
		JSONArray jsonArray_users = JSONArray.fromObject(resultGroups);

		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}
	
	@RequestMapping("/findChangePasswordPolicyDeviceGroupAndUser")
	@ResponseBody
	public JSONObject findChangePasswordPolicyDeviceGroupAndUser(@RequestParam("policy_id")Integer policy_id,
	                                                     @RequestParam(value = "page_start",required = false)int page_start,
	                                                     @RequestParam(value ="page_length",required = false)int page_length) {
		ArrayList<DeviceGroup> resultChangePasswordPolicyDeviceGroups = new ArrayList<DeviceGroup>();
		ArrayList<DeviceGroup> resultDeviceGroups = new ArrayList<DeviceGroup>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultChangePasswordPolicyDeviceGroups = (ArrayList<DeviceGroup>) changePasswordPolicyGroupService.selectBydIdDevice(policy_id);
		resultDeviceGroups = (ArrayList<DeviceGroup>)groupService.selectNameAnddId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultDeviceGroups.removeAll(resultChangePasswordPolicyDeviceGroups);
		JSONArray jsonArray_p_dgroups = JSONArray.fromObject(resultChangePasswordPolicyDeviceGroups);
		JSONArray jsonArray_dgroups = JSONArray.fromObject(resultDeviceGroups);
		
		result.accumulate("success", true);
		result.accumulate("data_dgroups", jsonArray_dgroups);
		result.accumulate("data_p_dgroups", jsonArray_p_dgroups);
		return result;
	}
}
