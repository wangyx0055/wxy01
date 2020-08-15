package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.CmdPolicyApppub;
import com.longersec.blj.service.CmdPolicyApppubService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdPolicyApppub")
public class CmdPolicyApppubController {

	@Autowired
	private CmdPolicyApppubService cmdPolicyApppubService;

	@RequestMapping("/listCmdPolicyApppub")
	@ResponseBody
	public JSONObject listCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdPolicyApppubs = new ArrayList<Object>();
		ArrayList<CmdPolicyApppub> cmdPolicyApppubs = new ArrayList<CmdPolicyApppub>();
		long total = 0;
		resultCmdPolicyApppubs = (ArrayList<Object>)cmdPolicyApppubService.findAll(cmdPolicyApppub, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCmdPolicyApppubs)) {
			cmdPolicyApppubs = (ArrayList<CmdPolicyApppub>)resultCmdPolicyApppubs.get(0);
			total = ((ArrayList<Long>) resultCmdPolicyApppubs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(cmdPolicyApppubs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCmdPolicyApppub")
	@ResponseBody
	public JSONObject addCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = cmdPolicyApppubService.addCmdPolicyApppub(cmdPolicyApppub);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editCmdPolicyApppub")
	@ResponseBody
	public JSONObject editCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = cmdPolicyApppubService.editCmdPolicyApppub(cmdPolicyApppub);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delCmdPolicyApppub")
	@ResponseBody
	public JSONObject delCmdPolicyApppub(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = cmdPolicyApppubService.delCmdPolicyApppub(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
