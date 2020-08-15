package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.DeviceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.CrontabScriptConfigDevice;
import com.longersec.blj.service.CrontabScriptConfigDeviceService;
import com.longersec.blj.service.DeviceAccountService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/crontabScriptConfigDevice")
public class CrontabScriptConfigDeviceController {

	@Autowired
	private CrontabScriptConfigDeviceService crontabScriptConfigDeviceService;
	@Autowired
	private DeviceAccountService deviceAccountService;

	@RequestMapping("/findCrontScriptConfigDevice")
	@ResponseBody
	public JSONObject findCrontScriptConfigDevice(@RequestParam("config_id")Integer config_id) {
		ArrayList<Deviceaccess> resultAccessPolicyDevice = new ArrayList<Deviceaccess>();
		ArrayList<Deviceaccess> resultDevice = new ArrayList<Deviceaccess>();
		resultAccessPolicyDevice = (ArrayList<Deviceaccess>) crontabScriptConfigDeviceService.selectById(config_id);
		resultDevice = (ArrayList<Deviceaccess>) deviceAccountService.selectNameAndId();
		JSONObject result = new JSONObject();
		resultDevice.removeAll(resultAccessPolicyDevice);
		JSONArray jsonArray_p_device = JSONArray.fromObject(resultAccessPolicyDevice);
		JSONArray jsonArray_device = JSONArray.fromObject(resultDevice);
		result.accumulate("success", true);
		result.accumulate("data_device", jsonArray_device);
		result.accumulate("data_p_device", jsonArray_p_device);
		return result;
	}
	
	@RequestMapping("/addCrontabScriptConfigDevice")
	@ResponseBody
	public JSONObject addAccessPolicyDevice(@RequestParam("config_id")Integer config_id,@RequestParam(value = "devices[]") Integer[] _devices, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> devices =  Arrays.asList(_devices);
		result.put("success", true);
		if(result.getBoolean("success")) {
			boolean r = crontabScriptConfigDeviceService.addCrontabScriptConfigDevice(config_id, devices);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editCrontabScriptConfigDevice")
	@ResponseBody
	public JSONObject editCrontabScriptConfigDevice(@RequestParam("config_id")Integer config_id,@RequestParam(value = "devices[]",required = false) Integer[] _devices, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		crontabScriptConfigDeviceService.deleteByid(config_id);
		if (_devices != null) {
			r = crontabScriptConfigDeviceService.editCrontabScriptConfigDevice(config_id, Arrays.asList(_devices));
		}
		result.put("success", r);
		return result;
	}
	
	@RequestMapping("/delCrontabScriptConfigDevice")
	@ResponseBody
	public JSONObject delCrontabScriptConfigDevice(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = crontabScriptConfigDeviceService.delCrontabScriptConfigDevice(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
