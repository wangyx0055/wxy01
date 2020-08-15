package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.Group;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.DeviceType;
import com.longersec.blj.service.DeviceTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/deviceType")
public class DeviceTypeController {

	@Autowired
	private DeviceTypeService deviceTypeService;

	@RequestMapping("/listDeviceType")
	@ResponseBody
	public JSONObject listDeviceType(DeviceType deviceType,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDeviceTypes = new ArrayList<Object>();
		ArrayList<DeviceType> deviceTypes = new ArrayList<DeviceType>();
		long total = 0;
		resultDeviceTypes = (ArrayList<Object>)deviceTypeService.findAll(deviceType, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultDeviceTypes)) {
			deviceTypes = (ArrayList<DeviceType>)resultDeviceTypes.get(0);
			total = ((ArrayList<Long>) resultDeviceTypes.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(deviceTypes);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	@RequestMapping("/listType")
	@ResponseBody
	public JSONObject listType(){
		ArrayList<DeviceType> deviceTypes = new ArrayList<DeviceType>();
		deviceTypes = deviceTypeService.listType();
		JSONArray jsonArray = JSONArray.fromObject(deviceTypes);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addDeviceType")
	@ResponseBody
	public JSONObject addDeviceType(DeviceType deviceType, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceTypeService.addDeviceType(deviceType);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editDeviceType")
	@ResponseBody
	public JSONObject editDeviceType(DeviceType deviceType, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = deviceTypeService.editDeviceType(deviceType);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delDeviceType")
	@ResponseBody
	public JSONObject delDeviceType(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = deviceTypeService.delDeviceType(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/checkname")
	@ResponseBody
	public JSONObject checkname(@RequestParam(value = "id",required = false)Integer id,@RequestParam("name")String name) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		if (id==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			DeviceType deviceType1 = deviceTypeService.checkname(name);
			if (deviceType1==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			DeviceType deviceType1 = deviceTypeService.checkname(name);
			if (deviceType1==null){
				result.put("success", true);
			}else {
				if (deviceType1.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}

		return result;
	}

}
