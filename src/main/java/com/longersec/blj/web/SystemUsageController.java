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
import com.longersec.blj.domain.SystemUsage;
import com.longersec.blj.service.SystemUsageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/systemUsage")
public class SystemUsageController {

	@Autowired
	private SystemUsageService systemUsageService;

	@RequestMapping("/listSystemUsage")
	@ResponseBody
	public JSONObject listSystemUsage(String interval,String start_date, String end_date,SystemUsage systemUsage,HttpServletRequest request, HttpSession session) {
		
		ArrayList<SystemUsage> systemUsages = new ArrayList<SystemUsage>();
		long total = 0;
		systemUsages = (ArrayList<SystemUsage>)systemUsageService.findAll(interval, start_date + " 00:00:00", end_date + " 23:59:59", systemUsage);
		JSONArray jsonArray = JSONArray.fromObject(systemUsages);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSystemUsage")
	@ResponseBody
	public JSONObject addSystemUsage(SystemUsage systemUsage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = systemUsageService.addSystemUsage(systemUsage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editSystemUsage")
	@ResponseBody
	public JSONObject editSystemUsage(SystemUsage systemUsage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = systemUsageService.editSystemUsage(systemUsage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delSystemUsage")
	@ResponseBody
	public JSONObject delSystemUsage(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = systemUsageService.delSystemUsage(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
