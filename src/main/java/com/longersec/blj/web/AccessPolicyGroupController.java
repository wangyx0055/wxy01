package com.longersec.blj.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.GroupService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.AccessPolicyGroup;
import com.longersec.blj.service.AccessPolicyGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/accessPolicyGroup")
public class AccessPolicyGroupController {

	@Autowired
	private AccessPolicyGroupService accessPolicyGroupService;
	
	@Autowired
	private GroupService groupService;

	@RequestMapping("/editAccessPolicyGroup")
	@ResponseBody
	public JSONObject editAccessPolicyGroup(@RequestParam(value = "groups[]",required = false) Integer[] groups,@RequestParam(value = "policy_id") Integer policy_id) {
		JSONObject result = new JSONObject();
		int type = 0;
		boolean r = true;
		accessPolicyGroupService.deleteBypolicy_id(policy_id,type);
		if(groups != null) {
			r = accessPolicyGroupService.editAccessPolicyGroup(policy_id, Arrays.asList(groups));
		}
		result.put("success", r);
		return result;
	}
	
	@RequestMapping("/editAccessPolicyDeviceGroup")
	@ResponseBody
	public JSONObject editAccessPolicyDeviceGroup(@RequestParam(value = "devicegroup[]",required = false) Integer[] devicegroup,@RequestParam(value = "policy_id") Integer policy_id,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		int type = 1;
		boolean r = true;
		accessPolicyGroupService.deleteBypolicy_id(policy_id,type);
		if(devicegroup != null) {
			r = accessPolicyGroupService.editAccessPolicyDeviceGroup(policy_id, Arrays.asList(devicegroup));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/findAccessPolicyGroupAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyGroupAndUser(@RequestParam("policy_id")Integer policy_id) {
		ArrayList<UserGroup> resultAccessPolicyGroups = new ArrayList<UserGroup>();
		ArrayList<UserGroup> resultGroups = new ArrayList<UserGroup>();
		resultAccessPolicyGroups = (ArrayList<UserGroup>) accessPolicyGroupService.selectById(policy_id);
		resultGroups = (ArrayList<UserGroup>)groupService.selectNameAndId();
		JSONObject result = new JSONObject();
		resultGroups.removeAll(resultAccessPolicyGroups);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultAccessPolicyGroups);
		JSONArray jsonArray_users = JSONArray.fromObject(resultGroups);

		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}
	
	@RequestMapping("/findAccessPolicyDeviceGroupAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyDeviceGroupAndUser(@RequestParam("policy_id")Integer policy_id) {
		ArrayList<DeviceGroup> resultAccessPolicyDeviceGroups = new ArrayList<DeviceGroup>();
		ArrayList<DeviceGroup> resultDeviceGroups = new ArrayList<DeviceGroup>();
		resultAccessPolicyDeviceGroups = (ArrayList<DeviceGroup>) accessPolicyGroupService.selectBydIdDevice(policy_id);
		resultDeviceGroups = (ArrayList<DeviceGroup>)groupService.selectNameAnddId();
		JSONObject result = new JSONObject();
		resultDeviceGroups.removeAll(resultAccessPolicyDeviceGroups);
		JSONArray jsonArray_p_dgroups = JSONArray.fromObject(resultAccessPolicyDeviceGroups);
		JSONArray jsonArray_dgroups = JSONArray.fromObject(resultDeviceGroups);
		
		result.accumulate("success", true);
		result.accumulate("data_dgroups", jsonArray_dgroups);
		result.accumulate("data_p_dgroups", jsonArray_p_dgroups);
		return result;
	}
}
