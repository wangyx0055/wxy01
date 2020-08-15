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

import com.longersec.blj.domain.CmdPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.CmdPolicyDeviceAccountService;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.DeviceService;
import com.longersec.blj.service.impl.DeviceAccountServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdPolicyDevice")
public class CmdPolicyDeviceController {

	@Autowired
	private CmdPolicyDeviceAccountService cmdPolicyDeviceAccountService;
	
	@Autowired
	private DeviceAccountService deviceAccountService;

	@RequestMapping("/listCmdPolicyDevice")
	@ResponseBody
	public JSONObject listCmdPolicyDevice(CmdPolicyDeviceAccount cmdPolicyDeviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdPolicyDevices = new ArrayList<Object>();
		ArrayList<CmdPolicyDeviceAccount> cmdPolicyDevices = new ArrayList<CmdPolicyDeviceAccount>();
		long total = 0;
		resultCmdPolicyDevices = (ArrayList<Object>)cmdPolicyDeviceAccountService.findAll(cmdPolicyDeviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCmdPolicyDevices)) {
			cmdPolicyDevices = (ArrayList<CmdPolicyDeviceAccount>)resultCmdPolicyDevices.get(0);
			total = ((ArrayList<Long>) resultCmdPolicyDevices.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(cmdPolicyDevices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/findCmdPolicyDeviceAndUser")
	@ResponseBody
	public JSONObject findCmdPolicyDeviceAndUser(@RequestParam("policy_id") Integer policy_id) {
		ArrayList<Deviceaccess> resultCmdPolicyDevice = new ArrayList<Deviceaccess>();
		ArrayList<Deviceaccess> resultDevice = new ArrayList<Deviceaccess>();
		resultCmdPolicyDevice = (ArrayList<Deviceaccess>) cmdPolicyDeviceAccountService.selectById(policy_id);
		resultDevice = (ArrayList<Deviceaccess>) deviceAccountService.selectNameAndId();
		JSONObject result = new JSONObject();
		resultDevice.removeAll(resultCmdPolicyDevice);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultCmdPolicyDevice);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDevice);

		result.accumulate("success", true);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}

	@RequestMapping("/addCmdPolicyDevice")
	@ResponseBody
	public JSONObject addCmdPolicyDevice(@RequestParam(value="policy_id") Integer policy_id,@RequestParam(value="device[]",required = false) Integer[] _device, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		List<Integer> device =_device==null?Arrays.asList(0):Arrays.asList(_device);
		if(result.getBoolean("success")) {
			Boolean r = cmdPolicyDeviceAccountService.addCmdPolicyDeviceAccount(policy_id,device);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editCmdPolicyDevice")
	@ResponseBody
	public JSONObject editCmdPolicyDevice(@RequestParam(value="device[]",required = false) Integer[] _device,@RequestParam(value="policy_id") Integer policy_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		cmdPolicyDeviceAccountService.deleteBypolicy_id(policy_id);
		if (_device != null) {
			r = cmdPolicyDeviceAccountService.editCmdPolicyDeviceAccount(policy_id, Arrays.asList(_device));
		}
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delCmdPolicyDevice")
	@ResponseBody
	public JSONObject delCmdPolicyDevice(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = cmdPolicyDeviceAccountService.delCmdPolicyDeviceAccount(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
