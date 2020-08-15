package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.Device;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.GroupDeviceAccountService;
import com.longersec.blj.service.GroupService;
import com.longersec.blj.service.UserGroupUserService;
import com.longersec.blj.utils.UpdateDepartmentCount;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.DeviceAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/deviceAccount")
public class DeviceAccountController {

	@Autowired
	private GroupDeviceAccountService groupDeviceAccountService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private DeviceAccountService deviceAccountService;

	@RequestMapping("/listDeviceAccount")
	@ResponseBody
	public JSONObject listDeviceAccount(DeviceAccount deviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceAccounts = new ArrayList<Object>();
		ArrayList<DeviceAccount> deviceAccounts = new ArrayList<DeviceAccount>();
		long total = 0;
		resultDeviceAccounts = (ArrayList<Object>)deviceAccountService.findAll(deviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceAccounts)) {
			deviceAccounts = (ArrayList<DeviceAccount>)resultDeviceAccounts.get(0);
			total = ((ArrayList<Long>) resultDeviceAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/listDeviceAccountNameIp")
	@ResponseBody
	public JSONObject listDeviceAccountNameIp(DeviceAccount deviceAccount,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Deviceaccess> nameIp = deviceAccountService.selectNameAndId();
		result.accumulate("success", true);
		result.accumulate("data", nameIp);
		return result;
	}

	@RequestMapping("/addDeviceAccount")
	@ResponseBody
	public JSONObject addDeviceAccount(DeviceAccount deviceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceAccountService.addDeviceAccount(deviceAccount);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editDeviceAccount")
	@ResponseBody
	public JSONObject editDeviceAccount(DeviceAccount deviceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceAccountService.editDeviceAccount(deviceAccount);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delDeviceAccount")
	@ResponseBody
	public JSONObject delDeviceAccount(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		boolean r = deviceAccountService.delDeviceAccount(_ids);
		UpdateDepartmentCount.AutoUpdateGroupDeviceCounts(groupService,groupDeviceAccountService);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/queryByDeviceId")
	@ResponseBody
	public JSONObject queryByDeviceId(@RequestParam(value = "device_id") Integer device_id,DeviceAccount deviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceAccounts = new ArrayList<Object>();
		ArrayList<DeviceAccount> deviceAccounts = new ArrayList<DeviceAccount>();
		long total = 0;
		resultDeviceAccounts = (ArrayList<Object>)deviceAccountService.queryByDeviceId(device_id,deviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceAccounts)) {
			deviceAccounts = (ArrayList<DeviceAccount>)resultDeviceAccounts.get(0);
			total = ((ArrayList<Long>) resultDeviceAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/editstatus")
	@ResponseBody
	public JSONObject editstatus(DeviceAccount deviceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);

		if(result.getBoolean("success")){
			try{
				Boolean d = deviceAccountService.editstatus(deviceAccount);
				result.put("success", true);
			}catch (Exception e){
				result.put("success", false);
			}

		}
		return result;
	}

	@RequestMapping("/checkaccountname")
	@ResponseBody
	public JSONObject checkaccountname(DeviceAccount deviceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);

		String username = deviceAccount.getUsername();
		int protocol_id = deviceAccount.getProtocol_id();
		int device_id = deviceAccount.getDevice_id();
		if (deviceAccount.getId()==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			DeviceAccount _deviceaccount = deviceAccountService.checkaccountname(username,protocol_id,device_id);
			if (_deviceaccount==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			DeviceAccount _deviceaccount = deviceAccountService.checkaccountname(username,protocol_id,device_id);
			if (_deviceaccount==null){
				result.put("success", true);
			}else {
				if (_deviceaccount.getId().equals(deviceAccount.getId())){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}
		return result;
	}

	@RequestMapping("/getUserDeviceAccount")
	@ResponseBody
	public JSONObject getUserDeviceAccount(DeviceAccount deviceAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceAccounts = new ArrayList<Object>();
		ArrayList<DeviceAccount> deviceAccounts = new ArrayList<DeviceAccount>();
		long total = 0;
		resultDeviceAccounts = (ArrayList<Object>)deviceAccountService.getUserDeviceAccount(deviceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceAccounts)) {
			deviceAccounts = (ArrayList<DeviceAccount>)resultDeviceAccounts.get(0);
			total = ((ArrayList<Long>) resultDeviceAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
}
