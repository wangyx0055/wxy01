package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.GroupDeviceAccountService;
import com.longersec.blj.service.GroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/groupDeviceAccount")
public class GroupDeviceAccountController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private GroupDeviceAccountService groupDeviceAccountService;

	@Autowired
	private DeviceAccountService deviceAccountService;

	@RequestMapping("/findGroupDeviceAccount")
	@ResponseBody
	public JSONObject findGroupDeviceAccount(@RequestParam("group_id") Integer group_id,
	                                         @RequestParam(value = "page_start",required = false)Integer page_start,
	                                         @RequestParam(value ="page_length",required = false)Integer page_length) {
		ArrayList<Deviceaccess> resultGroupDeviceAccount = new ArrayList<Deviceaccess>();
		ArrayList<Deviceaccess> resultDeviceAccount = new ArrayList<Deviceaccess>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultGroupDeviceAccount = (ArrayList<Deviceaccess>) groupDeviceAccountService.selectById(group_id);
		resultDeviceAccount = (ArrayList<Deviceaccess>) deviceAccountService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultDeviceAccount.removeAll(resultGroupDeviceAccount);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultGroupDeviceAccount);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDeviceAccount);

		result.accumulate("success", true);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}
	
	@RequestMapping("/addGroupDeviceAccount")
	@ResponseBody
	public JSONObject addGroupDeviceAccount(@RequestParam(value = "devices[]") Integer[] _devices, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> devices =  Arrays.asList(_devices);
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Integer group_id = null;
			Boolean r = groupDeviceAccountService.addDeviceAccountUser(group_id, devices);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/editGroupDeviceAccount")
	@ResponseBody
	public JSONObject editGroupDeviceAccount(@RequestParam(value = "devices[]",required = false) Integer[] _devices,@RequestParam(value = "group_id") Integer group_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Boolean r = true;
		groupDeviceAccountService.deleteByDeviceAccount_id(group_id);
		if (_devices != null) {
			r = groupDeviceAccountService.editDeviceAccountUser(group_id, Arrays.asList(_devices));
		}
		int selectAccessPolicyDeviceCounts = groupDeviceAccountService.selectDeviceAccountUserDeviceCounts(group_id);
		boolean updateGroupCount = groupService.updateGroupCount(selectAccessPolicyDeviceCounts, group_id);
		result.put("success",r && updateGroupCount);
		return result;
	}

}
