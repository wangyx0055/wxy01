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
import com.longersec.blj.domain.LogDeletePolicyDevice;
import com.longersec.blj.service.LogDeletePolicyDeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/logDeletePolicyDevice")
public class LogDeletePolicyDeviceController {

	@Autowired
	private LogDeletePolicyDeviceService logDeletePolicyDeviceService;

	@RequestMapping("/listLogDeletePolicyDevice")
	@ResponseBody
	public JSONObject listLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLogDeletePolicyDevices = new ArrayList<Object>();
		ArrayList<LogDeletePolicyDevice> logDeletePolicyDevices = new ArrayList<LogDeletePolicyDevice>();
		long total = 0;
		resultLogDeletePolicyDevices = (ArrayList<Object>)logDeletePolicyDeviceService.findAll(logDeletePolicyDevice, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLogDeletePolicyDevices)) {
			logDeletePolicyDevices = (ArrayList<LogDeletePolicyDevice>)resultLogDeletePolicyDevices.get(0);
			total = ((ArrayList<Long>) resultLogDeletePolicyDevices.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(logDeletePolicyDevices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLogDeletePolicyDevice")
	@ResponseBody
	public JSONObject addLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyDeviceService.addLogDeletePolicyDevice(logDeletePolicyDevice);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLogDeletePolicyDevice")
	@ResponseBody
	public JSONObject editLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyDeviceService.editLogDeletePolicyDevice(logDeletePolicyDevice);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLogDeletePolicyDevice")
	@ResponseBody
	public JSONObject delLogDeletePolicyDevice(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyDeviceService.delLogDeletePolicyDevice(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
