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
import com.longersec.blj.domain.LogDeletePolicyApppub;
import com.longersec.blj.service.LogDeletePolicyApppubService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/logDeletePolicyApppub")
public class LogDeletePolicyApppubController {

	@Autowired
	private LogDeletePolicyApppubService logDeletePolicyApppubService;

	@RequestMapping("/listLogDeletePolicyApppub")
	@ResponseBody
	public JSONObject listLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultLogDeletePolicyApppubs = new ArrayList<Object>();
		ArrayList<LogDeletePolicyApppub> logDeletePolicyApppubs = new ArrayList<LogDeletePolicyApppub>();
		long total = 0;
		resultLogDeletePolicyApppubs = (ArrayList<Object>)logDeletePolicyApppubService.findAll(logDeletePolicyApppub, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultLogDeletePolicyApppubs)) {
			logDeletePolicyApppubs = (ArrayList<LogDeletePolicyApppub>)resultLogDeletePolicyApppubs.get(0);
			total = ((ArrayList<Long>) resultLogDeletePolicyApppubs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(logDeletePolicyApppubs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addLogDeletePolicyApppub")
	@ResponseBody
	public JSONObject addLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyApppubService.addLogDeletePolicyApppub(logDeletePolicyApppub);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editLogDeletePolicyApppub")
	@ResponseBody
	public JSONObject editLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyApppubService.editLogDeletePolicyApppub(logDeletePolicyApppub);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delLogDeletePolicyApppub")
	@ResponseBody
	public JSONObject delLogDeletePolicyApppub(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = logDeletePolicyApppubService.delLogDeletePolicyApppub(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
