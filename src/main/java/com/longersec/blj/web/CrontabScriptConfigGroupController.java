package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
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
import com.longersec.blj.domain.CrontabScriptConfigGroup;
import com.longersec.blj.service.CrontabScriptConfigGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/crontabScriptConfigGroup")
public class CrontabScriptConfigGroupController {

	@Autowired
	private CrontabScriptConfigGroupService crontabScriptConfigGroupService;
	@Autowired
	private GroupService groupService;

	@RequestMapping("/findCrontabScriptconfigUserGroup")
	@ResponseBody
	public JSONObject findAccessPolicyGroupAndUser(@RequestParam("config_id")Integer config_id,
	                                               @RequestParam(value = "page_start",required = false)int page_start,
	                                               @RequestParam(value ="page_length",required = false)int page_length) {
		JSONObject result = new JSONObject();
		ArrayList<UserGroup> resultCrontabScriptconfigGroups = new ArrayList<UserGroup>();
		ArrayList<UserGroup> resultGroups = new ArrayList<UserGroup>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultCrontabScriptconfigGroups = (ArrayList<UserGroup>) crontabScriptConfigGroupService.selectById(config_id);
		resultGroups = (ArrayList<UserGroup>)groupService.selectNameAndId(users.getDepartment(),page_start,page_length);
		resultGroups.removeAll(resultCrontabScriptconfigGroups);
		JSONArray jsonArray_p_user_group = JSONArray.fromObject(resultCrontabScriptconfigGroups);
		JSONArray jsonArray_user_group = JSONArray.fromObject(resultGroups);
		result.accumulate("success", true);
		result.accumulate("data_user_group", jsonArray_user_group);
		result.accumulate("data_p_user_group", jsonArray_p_user_group);
		return result;
	}

	@RequestMapping("/findCrontabScriptconfigDeviceGroup")
	@ResponseBody
	public JSONObject findCrontabScriptconfigDeviceGroup(@RequestParam("config_id")Integer config_id,
	                                                     @RequestParam(value = "page_start",required = false)int page_start,
	                                                     @RequestParam(value ="page_length",required = false)int page_length) {
		JSONObject result = new JSONObject();
		ArrayList<DeviceGroup> resultCrontabScriptconfigDeviceGroups = new ArrayList<DeviceGroup>();
		ArrayList<DeviceGroup> resultDeviceGroups = new ArrayList<DeviceGroup>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultCrontabScriptconfigDeviceGroups = (ArrayList<DeviceGroup>) crontabScriptConfigGroupService.selectBydIdDevice(config_id);
		resultDeviceGroups = (ArrayList<DeviceGroup>)groupService.selectNameAnddId(users.getDepartment(),page_start,page_length);
		resultDeviceGroups.removeAll(resultCrontabScriptconfigDeviceGroups);
		JSONArray jsonArray_p_device_group = JSONArray.fromObject(resultCrontabScriptconfigDeviceGroups);
		JSONArray jsonArray_device_group = JSONArray.fromObject(resultDeviceGroups);
		result.accumulate("success", true);
		result.accumulate("data_device_group", jsonArray_device_group);
		result.accumulate("data_p_device_group", jsonArray_p_device_group);
		return result;
	}

	@RequestMapping("/editCrontabScriptConfigUserGroup")
	@ResponseBody
	public JSONObject editCrontabScriptConfigGroup(@RequestParam(value = "config_id") Integer config_id,@RequestParam(value = "groups[]") Integer[] groups) {
		JSONObject result = new JSONObject();
		List<Integer> ugroup = groups==null?Arrays.asList(0):Arrays.asList(groups);
		int type = 0;
		crontabScriptConfigGroupService.deleteById(config_id,type);
		Boolean r = crontabScriptConfigGroupService.editCrontabScriptConfigGroup(config_id,ugroup);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editCrontabScriptConfigDeviceGroup")
	@ResponseBody
	public JSONObject editCrontabScriptConfigDeviceGroup(@RequestParam(value = "config_id") Integer config_id,@RequestParam(value = "devicegroup[]",required = false) Integer[] devicegroup,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> dgroup = devicegroup==null?Arrays.asList(0):Arrays.asList(devicegroup);
		int type = 1;
		crontabScriptConfigGroupService.deleteById(config_id,type);
		Boolean r = crontabScriptConfigGroupService.editCrontabScriptConfigDeviceGroup(config_id,dgroup);
		result.put("success", r);
		return result;
	}
}
