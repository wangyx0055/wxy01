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
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.service.DeviceRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/deviceRecord")
public class DeviceRecordController {

	@Autowired
	private DeviceRecordService deviceRecordService;

	@RequestMapping("/listDeviceRecord")
	@ResponseBody
	public JSONObject listDeviceRecord(DeviceRecord deviceRecord,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("_start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceRecords = new ArrayList<Object>();
		ArrayList<DeviceRecord> deviceRecords = new ArrayList<DeviceRecord>();
		long total = 0;
		resultDeviceRecords = (ArrayList<Object>)deviceRecordService.findAll(deviceRecord, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceRecords)) {
			deviceRecords = (ArrayList<DeviceRecord>)resultDeviceRecords.get(0);
			total = ((ArrayList<Long>) resultDeviceRecords.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceRecords);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addDeviceRecord")
	@ResponseBody
	public JSONObject addDeviceRecord(DeviceRecord deviceRecord, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			int r = deviceRecordService.addDeviceRecord(deviceRecord);
			result.accumulate("success", r>0?true:false);
		}
		return result;
	}

	@RequestMapping("/editDeviceRecord")
	@ResponseBody
	public JSONObject editDeviceRecord(DeviceRecord deviceRecord, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceRecordService.editDeviceRecord(deviceRecord);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delDeviceRecord")
	@ResponseBody
	public JSONObject delDeviceRecord(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = deviceRecordService.delDeviceRecord(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/deviceRecordReports")
	@ResponseBody
	public JSONObject deviceRecordReports(String interval,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectTimeByInterval(interval, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/deviceRecordTimeLongReports")
	@ResponseBody
	public JSONObject deviceRecordTimeLongReports(String name,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectTimeByLong(name, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	


	@RequestMapping("/deviceCommandReports")
	@ResponseBody
	public JSONObject deviceCommandReports(String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectCommandReport(start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/alertReports")
	@ResponseBody
	public JSONObject alertReports(String interval,String start_date, String end_date, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Object> loginLogs = new ArrayList<Object>();
		long total = 0;
		resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectAlertByInterval(interval, start_date, end_date, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Object>)resultLoginLogs.get(0);
			total = ((ArrayList<Long>) resultLoginLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(loginLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
}
