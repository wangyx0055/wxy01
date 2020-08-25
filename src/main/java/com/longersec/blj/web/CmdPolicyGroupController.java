package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.CmdPolicyGroupService;
import com.longersec.blj.service.GroupService;
import com.longersec.blj.utils.BljConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/cmdPolicyGroup")
public class CmdPolicyGroupController {

	@Autowired
	private CmdPolicyGroupService cmdPolicyGroupService;
	
	@Autowired
	private GroupService groupService;

	JSONObject result = null;
	
	@RequestMapping("/findCmdPolicyUserGroupAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyUserGroupAndUser(@RequestParam("policy_id")Integer policy_id,
	                                                   @RequestParam(value = "page_start",required = false)int page_start,
	                                                   @RequestParam(value ="page_length",required = false)int page_length) {
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		ArrayList<UserGroup> resultCmdPolicyGroups = (ArrayList<UserGroup>) cmdPolicyGroupService.selectByIdUser(policy_id);
		ArrayList<UserGroup> resultGroups = (ArrayList<UserGroup>)groupService.selectNameAndId(users.getDepartment(),page_start,page_length);
		result = new JSONObject();
		resultGroups.removeAll(resultCmdPolicyGroups);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultCmdPolicyGroups);
		JSONArray jsonArray_users = JSONArray.fromObject(resultGroups);

		result.put(BljConstant.SUCCESS, true);
		result.put("data_users", jsonArray_users);
		result.put("data_p_users", jsonArray_p_users);
		return result;
	}
	
	@RequestMapping("/findCmdPolicyDeviceGroupAndUser")
	@ResponseBody
	public JSONObject findCmdPolicyDeviceGroupAndUser(@RequestParam("policy_id")Integer policy_id,
	                                                  @RequestParam(value = "page_start",required = false)int page_start,
	                                                  @RequestParam(value ="page_length",required = false)int page_length) {
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		ArrayList<DeviceGroup> resultCmdPolicyDeviceGroups = (ArrayList<DeviceGroup>) cmdPolicyGroupService.selectBydIdDevice(policy_id);
		ArrayList<DeviceGroup> resultDeviceGroups = (ArrayList<DeviceGroup>)groupService.selectNameAnddId(users.getDepartment(),page_start,page_length);
		result = new JSONObject();
		resultDeviceGroups.removeAll(resultCmdPolicyDeviceGroups);
		JSONArray jsonArray_p_dgroups = JSONArray.fromObject(resultCmdPolicyDeviceGroups);
		JSONArray jsonArray_dgroups = JSONArray.fromObject(resultDeviceGroups);

		result.put(BljConstant.SUCCESS, true);
		result.put("data_dgroups", jsonArray_dgroups);
		result.put("data_p_dgroups", jsonArray_p_dgroups);
		return result;
	}
	
	@RequestMapping("/editCmdPolicyUserGroup")
	@ResponseBody
	public JSONObject editCmdPolicyUserGroup(@RequestParam(value = "policy_id") Integer policy_id,@RequestParam(value = "usergroup[]",required = false) Integer[] usergroup) {
		JSONObject result = new JSONObject();
		Boolean r = true;
		cmdPolicyGroupService.deleteBypolicy_id(policy_id,0);
		if (usergroup != null) {
			r = cmdPolicyGroupService.editCmdPolicyUserGroup(policy_id, Arrays.asList(usergroup));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editCmdPolicyDeviceGroup")
	@ResponseBody
	public JSONObject editCmdPolicyGroup(@RequestParam(value = "policy_id") Integer policy_id,@RequestParam(value = "devicegroup[]",required = false) Integer[] devicegroup) {
		JSONObject result = new JSONObject();
		Boolean r = true;
		cmdPolicyGroupService.deleteBypolicy_id(policy_id,1);
		if (devicegroup != null) {
			r = cmdPolicyGroupService.editCmdPolicyDeviceGroup(policy_id, Arrays.asList(devicegroup));
		}
		result.put("success", r);
		return result;
	}
}
