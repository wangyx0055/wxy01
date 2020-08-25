package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.longersec.blj.domain.ChangePasswordPolicyDeviceAccount;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.ChangePasswordPolicyDeviceAccountService;
import com.longersec.blj.service.DeviceAccountService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/changePasswordPolicyDeviceAccount")
public class ChangePasswordPolicyDeviceAccountController {

	@Autowired
	private ChangePasswordPolicyDeviceAccountService changePasswordPolicyDeviceAccountService;
	@Autowired
	private DeviceAccountService DeviceAccountService;

	@RequestMapping("/listChangePasswordPolicyDeviceAccount")
	@ResponseBody
	public JSONObject listChangePasswordPolicyDevice(ChangePasswordPolicyDeviceAccount changePasswordPolicyDeviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultChangePasswordPolicyDevices = new ArrayList<Object>();
		ArrayList<ChangePasswordPolicyDeviceAccount> changePasswordPolicyDevices = new ArrayList<ChangePasswordPolicyDeviceAccount>();
		long total = 0;
		resultChangePasswordPolicyDevices = (ArrayList<Object>)changePasswordPolicyDeviceAccountService.findAll(changePasswordPolicyDeviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultChangePasswordPolicyDevices)) {
			changePasswordPolicyDevices = (ArrayList<ChangePasswordPolicyDeviceAccount>)resultChangePasswordPolicyDevices.get(0);
			total = ((ArrayList<Long>) resultChangePasswordPolicyDevices.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(changePasswordPolicyDevices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/findChangePasswordPolicyDeviceAccountAndUser")
	@ResponseBody
	public JSONObject findChangePasswordPolicyDeviceAccountAndUser(@RequestParam("policy_id") Integer policy_id,
	                                                       @RequestParam(value = "page_start",required = false)int page_start,
	                                                       @RequestParam(value = "page_length",required = false)int page_length) {
		ArrayList<Deviceaccess> resultChangePasswordPolicyDevice = new ArrayList<Deviceaccess>();
		ArrayList<Deviceaccess> resultDevice = new ArrayList<Deviceaccess>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultChangePasswordPolicyDevice = (ArrayList<Deviceaccess>) changePasswordPolicyDeviceAccountService.selectById(policy_id);
		resultDevice = (ArrayList<Deviceaccess>) DeviceAccountService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultDevice.removeAll(resultChangePasswordPolicyDevice);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultChangePasswordPolicyDevice);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDevice);

		result.accumulate("success", true);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}
	
	@RequestMapping("/addChangePasswordPolicyDeviceAccount")
	@ResponseBody
	public JSONObject addChangePasswordPolicyDevice(@RequestParam(value = "devices[]") Integer[] _devices, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> devices =  Arrays.asList(_devices);
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Integer policy_id = null;
			Boolean r = changePasswordPolicyDeviceAccountService.addChangePasswordPolicyDeviceAccount(policy_id, devices);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editChangePasswordPolicyDeviceAccount")
	@ResponseBody
	public JSONObject editChangePasswordPolicyDeviceAccount(@RequestParam(value = "devices[]",required = false) Integer[] _devices,@RequestParam(value = "policy_id") Integer policy_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		changePasswordPolicyDeviceAccountService.deleteBypolicy_id(policy_id);
		if (_devices != null) {
			r = changePasswordPolicyDeviceAccountService.editChangePasswordPolicyDeviceAccount(policy_id, Arrays.asList(_devices));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delChangePasswordPolicyDeviceAccount")
	@ResponseBody
	public JSONObject delChangePasswordPolicyDevice(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyDeviceAccountService.delChangePasswordPolicyDeviceAccount(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
