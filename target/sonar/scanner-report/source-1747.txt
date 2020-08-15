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
import com.longersec.blj.domain.ConfigPort;
import com.longersec.blj.service.ConfigPortService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configPort")
public class ConfigPortController {

	@Autowired
	private ConfigPortService configPortService;

	@RequestMapping("/listConfigPort")
	@ResponseBody
	public JSONObject listConfigPort(ConfigPort configPort,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigPorts = new ArrayList<Object>();
		ArrayList<ConfigPort> configPorts = new ArrayList<ConfigPort>();
		long total = 0;
		resultConfigPorts = (ArrayList<Object>)configPortService.findAll(configPort, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigPorts)) {
			configPorts = (ArrayList<ConfigPort>)resultConfigPorts.get(0);
			total = ((ArrayList<Long>) resultConfigPorts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configPorts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigPort")
	@ResponseBody
	public JSONObject addConfigPort(ConfigPort configPort, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configPortService.addConfigPort(configPort);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigPort")
	@ResponseBody
	public JSONObject editConfigPort(ConfigPort configPort, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configPortService.editConfigPort(configPort);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigPort")
	@ResponseBody
	public JSONObject delConfigPort(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configPortService.delConfigPort(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
