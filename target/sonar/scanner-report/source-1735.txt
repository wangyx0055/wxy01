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
import com.longersec.blj.domain.CmdPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.service.CmdPolicyGroupService;
import com.longersec.blj.service.GroupService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdPolicyGroup")
public class CmdPolicyGroupController {

	@Autowired
	private CmdPolicyGroupService cmdPolicyGroupService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/findCmdPolicyUserGroupAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyUserGroupAndUser(@RequestParam("policy_id")Integer policy_id) {
		ArrayList<UserGroup> resultCmdPolicyGroups = new ArrayList<UserGroup>();
		ArrayList<UserGroup> resultGroups = new ArrayList<UserGroup>();
		resultCmdPolicyGroups = (ArrayList<UserGroup>) cmdPolicyGroupService.selectByIdUser(policy_id);
		resultGroups = (ArrayList<UserGroup>)groupService.selectNameAndId();
		JSONObject result = new JSONObject();
		resultGroups.removeAll(resultCmdPolicyGroups);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultCmdPolicyGroups);
		JSONArray jsonArray_users = JSONArray.fromObject(resultGroups);

		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}
	
	@RequestMapping("/findCmdPolicyDeviceGroupAndUser")
	@ResponseBody
	public JSONObject findCmdPolicyDeviceGroupAndUser(@RequestParam("policy_id")Integer policy_id) {
		ArrayList<DeviceGroup> resultCmdPolicyDeviceGroups = new ArrayList<DeviceGroup>();
		ArrayList<DeviceGroup> resultDeviceGroups = new ArrayList<DeviceGroup>();
		resultCmdPolicyDeviceGroups = (ArrayList<DeviceGroup>) cmdPolicyGroupService.selectBydIdDevice(policy_id);
		resultDeviceGroups = (ArrayList<DeviceGroup>)groupService.selectNameAnddId();
		JSONObject result = new JSONObject();
		resultDeviceGroups.removeAll(resultCmdPolicyDeviceGroups);
		JSONArray jsonArray_p_dgroups = JSONArray.fromObject(resultCmdPolicyDeviceGroups);
		JSONArray jsonArray_dgroups = JSONArray.fromObject(resultDeviceGroups);

		result.accumulate("success", true);
		result.accumulate("data_dgroups", jsonArray_dgroups);
		result.accumulate("data_p_dgroups", jsonArray_p_dgroups);
		return result;
	}
	
	@RequestMapping("/editCmdPolicyUserGroup")
	@ResponseBody
	public JSONObject editCmdPolicyUserGroup(@RequestParam(value = "policy_id") Integer policy_id,@RequestParam(value = "usergroup[]",required = false) Integer[] usergroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		cmdPolicyGroupService.deleteBypolicy_id(policy_id,0);
		if (usergroup != null) {
			r = cmdPolicyGroupService.editCmdPolicyUserGroup(policy_id, Arrays.asList(usergroup));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editCmdPolicyDeviceGroup")
	@ResponseBody
	public JSONObject editCmdPolicyGroup(@RequestParam(value = "policy_id") Integer policy_id,@RequestParam(value = "devicegroup[]",required = false) Integer[] devicegroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		cmdPolicyGroupService.deleteBypolicy_id(policy_id,1);
		if (devicegroup != null) {
			r = cmdPolicyGroupService.editCmdPolicyDeviceGroup(policy_id, Arrays.asList(devicegroup));
		}
		result.put("success", r);
		return result;
	}

}
