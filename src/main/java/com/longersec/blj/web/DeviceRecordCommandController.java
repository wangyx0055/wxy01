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
import com.longersec.blj.domain.DeviceRecordCommand;
import com.longersec.blj.service.DeviceRecordCommandService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/deviceRecordCommand")
public class DeviceRecordCommandController {

	@Autowired
	private DeviceRecordCommandService deviceRecordCommandService;

	@RequestMapping("/listDeviceRecordCommand")
	@ResponseBody
	public JSONObject listDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceRecordCommands = new ArrayList<Object>();
		ArrayList<DeviceRecordCommand> deviceRecordCommands = new ArrayList<DeviceRecordCommand>();
		long total = 0;
		resultDeviceRecordCommands = (ArrayList<Object>)deviceRecordCommandService.findAll(deviceRecordCommand, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceRecordCommands)) {
			deviceRecordCommands = (ArrayList<DeviceRecordCommand>)resultDeviceRecordCommands.get(0);
			total = ((ArrayList<Long>) resultDeviceRecordCommands.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceRecordCommands);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addDeviceRecordCommand")
	@ResponseBody
	public JSONObject addDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceRecordCommandService.addDeviceRecordCommand(deviceRecordCommand);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editDeviceRecordCommand")
	@ResponseBody
	public JSONObject editDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceRecordCommandService.editDeviceRecordCommand(deviceRecordCommand);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delDeviceRecordCommand")
	@ResponseBody
	public JSONObject delDeviceRecordCommand(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = deviceRecordCommandService.delDeviceRecordCommand(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
