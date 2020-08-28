package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.WorkorderApplyDeviceAccount;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.WorkorderApplyDeviceAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/workorderApplyDeviceAccount")
public class WorkorderApplyDeviceAccountController {

	@Autowired
	private WorkorderApplyDeviceAccountService workorderApplyDeviceAccountService;

	@Autowired
	private DeviceAccountService deviceAccountService;

	JSONObject result =null;

	@RequestMapping("/listWorkorderApplyDeviceAccount")
	@ResponseBody
	public JSONObject listWorkorderApplyDeviceAccount(@RequestParam("workorder_apply_id") Integer workorder_apply_id,
	                                                  @RequestParam(value = "page_start",required = false)Integer page_start,
	                                                  @RequestParam(value ="page_length",required = false)Integer page_length) {
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		ArrayList<Deviceaccess> resultWorkorderApplyDeviceAccount = (ArrayList<Deviceaccess>) workorderApplyDeviceAccountService.selectById(workorder_apply_id);
		ArrayList<Deviceaccess> resultDeviceAccount = (ArrayList<Deviceaccess>) deviceAccountService.selectNameAndId(users.getDepartment(),page_start,page_length);
		result = new JSONObject();
		resultDeviceAccount.removeAll(resultWorkorderApplyDeviceAccount);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultWorkorderApplyDeviceAccount);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDeviceAccount);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}

	@RequestMapping("/addWorkorderApplyDeviceAccount")
	@ResponseBody
	public JSONObject addWorkorderApplyDeviceAccount(@RequestParam(value = "devices[]",required = false) Integer[] _devices,@RequestParam(value = "workorder_apply_id") Integer workorder_apply_id) {
		return workorderApplyDeviceAccountService.addWorkorderApplyDeviceAccount(_devices,workorder_apply_id);
	}

}
