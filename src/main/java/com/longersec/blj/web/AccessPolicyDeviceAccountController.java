package com.longersec.blj.web;

import com.longersec.blj.domain.AccessPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.AccessPolicyDeviceAccountService;
import com.longersec.blj.service.DeviceAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping(value = "/accessPolicyDeviceAccount")
public class AccessPolicyDeviceAccountController {

	@Autowired
	private AccessPolicyDeviceAccountService accessPolicyDeviceAccountService;

	@Autowired
	private DeviceAccountService DeviceAccountService;
	
	@RequestMapping("/listAccessPolicyDeviceAccount")
	@ResponseBody
	public JSONObject listAccessPolicyDevice(AccessPolicyDeviceAccount accessPolicyDeviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultAccessPolicyDevices = new ArrayList<Object>();
		ArrayList<AccessPolicyDeviceAccount> accessPolicyDevices = new ArrayList<AccessPolicyDeviceAccount>();
		long total = 0;
		resultAccessPolicyDevices = (ArrayList<Object>)accessPolicyDeviceAccountService.findAll(accessPolicyDeviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultAccessPolicyDevices)) {
			accessPolicyDevices = (ArrayList<AccessPolicyDeviceAccount>)resultAccessPolicyDevices.get(0);
			total = ((ArrayList<Long>) resultAccessPolicyDevices.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(accessPolicyDevices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/findAccessPolicyDeviceAccountAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyDeviceAccountAndUser(@RequestParam("policy_id") Integer policy_id,
	                                                       @RequestParam(value = "page_start",required = false)int page_start,
	                                                       @RequestParam(value = "page_length",required = false)int page_length) {
		ArrayList<Deviceaccess> resultAccessPolicyDevice = new ArrayList<Deviceaccess>();
		ArrayList<Deviceaccess> resultDevice = new ArrayList<Deviceaccess>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultAccessPolicyDevice = (ArrayList<Deviceaccess>) accessPolicyDeviceAccountService.selectById(policy_id);
		resultDevice = (ArrayList<Deviceaccess>) DeviceAccountService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultDevice.removeAll(resultAccessPolicyDevice);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultAccessPolicyDevice);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDevice);

		result.accumulate("success", true);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}
	
	@RequestMapping("/addAccessPolicyDeviceAccount")
	@ResponseBody
	public JSONObject addAccessPolicyDevice(@RequestParam(value = "devices[]") Integer[] _devices, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> devices =  Arrays.asList(_devices);
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Integer policy_id = null;
			Boolean r = accessPolicyDeviceAccountService.addAccessPolicyDevice(policy_id, devices);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editAccessPolicyDeviceAccount")
	@ResponseBody
	public JSONObject editAccessPolicyDeviceAccount(@RequestParam(value = "devices[]",required = false) Integer[] _devices,@RequestParam(value = "policy_id") Integer policy_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		accessPolicyDeviceAccountService.deleteBypolicy_id(policy_id);
		if (_devices != null) {
			r = accessPolicyDeviceAccountService.editAccessPolicyDeviceAccount(policy_id, Arrays.asList(_devices));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delAccessPolicyDeviceAccount")
	@ResponseBody
	public JSONObject delAccessPolicyDevice(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = accessPolicyDeviceAccountService.delAccessPolicyDevice(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
