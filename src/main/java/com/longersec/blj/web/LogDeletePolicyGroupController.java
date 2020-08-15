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
import com.longersec.blj.domain.LogDeletePolicyGroup;
import com.longersec.blj.service.LogDeletePolicyGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/logDeletePolicyGroup")
public class LogDeletePolicyGroupController {

	@Autowired
	private LogDeletePolicyGroupService logDeletePolicyGroupService;

	@RequestMapping("/listLogDeletePolicyGroup")
	@ResponseBody
	public JSONObject listLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLogDeletePolicyGroups = new ArrayList<Object>();
		ArrayList<LogDeletePolicyGroup> logDeletePolicyGroups = new ArrayList<LogDeletePolicyGroup>();
		long total = 0;
		resultLogDeletePolicyGroups = (ArrayList<Object>)logDeletePolicyGroupService.findAll(logDeletePolicyGroup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLogDeletePolicyGroups)) {
			logDeletePolicyGroups = (ArrayList<LogDeletePolicyGroup>)resultLogDeletePolicyGroups.get(0);
			total = ((ArrayList<Long>) resultLogDeletePolicyGroups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(logDeletePolicyGroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLogDeletePolicyGroup")
	@ResponseBody
	public JSONObject addLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyGroupService.addLogDeletePolicyGroup(logDeletePolicyGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLogDeletePolicyGroup")
	@ResponseBody
	public JSONObject editLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyGroupService.editLogDeletePolicyGroup(logDeletePolicyGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLogDeletePolicyGroup")
	@ResponseBody
	public JSONObject delLogDeletePolicyGroup(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyGroupService.delLogDeletePolicyGroup(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
