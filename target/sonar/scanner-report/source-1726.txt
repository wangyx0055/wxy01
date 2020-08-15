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
import com.longersec.blj.domain.ChangePasswordPolicyDevice;
import com.longersec.blj.service.ChangePasswordPolicyDeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/changePasswordPolicyDevice")
public class ChangePasswordPolicyDeviceController {

	@Autowired
	private ChangePasswordPolicyDeviceService changePasswordPolicyDeviceService;

	@RequestMapping("/listChangePasswordPolicyDevice")
	@ResponseBody
	public JSONObject listChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultChangePasswordPolicyDevices = new ArrayList<Object>();
		ArrayList<ChangePasswordPolicyDevice> changePasswordPolicyDevices = new ArrayList<ChangePasswordPolicyDevice>();
		long total = 0;
		resultChangePasswordPolicyDevices = (ArrayList<Object>)changePasswordPolicyDeviceService.findAll(changePasswordPolicyDevice, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultChangePasswordPolicyDevices)) {
			changePasswordPolicyDevices = (ArrayList<ChangePasswordPolicyDevice>)resultChangePasswordPolicyDevices.get(0);
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

	@RequestMapping("/addChangePasswordPolicyDevice")
	@ResponseBody
	public JSONObject addChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyDeviceService.addChangePasswordPolicyDevice(changePasswordPolicyDevice);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editChangePasswordPolicyDevice")
	@ResponseBody
	public JSONObject editChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyDeviceService.editChangePasswordPolicyDevice(changePasswordPolicyDevice);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delChangePasswordPolicyDevice")
	@ResponseBody
	public JSONObject delChangePasswordPolicyDevice(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = changePasswordPolicyDeviceService.delChangePasswordPolicyDevice(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
