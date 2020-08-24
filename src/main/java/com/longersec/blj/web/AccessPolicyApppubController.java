package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.service.AccessPolicyApppubService;
import com.longersec.blj.service.ApppubService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/accessPolicyApppub")
public class AccessPolicyApppubController {

	@Autowired
	private AccessPolicyApppubService accessPolicyApppubService;

	@Autowired
	private ApppubService apppubService;
	
	@RequestMapping("/listAccessPolicyApppub")
	@ResponseBody
	public JSONObject listAccessPolicyApppub(AccessPolicyApppub accessPolicyApppub,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultAccessPolicyApppubs = new ArrayList<Object>();
		ArrayList<AccessPolicyApppub> accessPolicyApppubs = new ArrayList<AccessPolicyApppub>();
		long total = 0;
		resultAccessPolicyApppubs = (ArrayList<Object>)accessPolicyApppubService.findAll(accessPolicyApppub, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultAccessPolicyApppubs)) {
			accessPolicyApppubs = (ArrayList<AccessPolicyApppub>)resultAccessPolicyApppubs.get(0);
			total = ((ArrayList<Long>) resultAccessPolicyApppubs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(accessPolicyApppubs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addAccessPolicyApppub")
	@ResponseBody
	public JSONObject addAccessPolicyApppub(@RequestParam(value = "app[]") Integer[] _app, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> app =  Arrays.asList(_app);
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Integer policy_id = null;
			Boolean r = accessPolicyApppubService.addAccessPolicyApppub(policy_id,app);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editAccessPolicyApppub")
	@ResponseBody
	public JSONObject editAccessPolicyApppub(@RequestParam(value = "app[]") Integer[] _app,@RequestParam(value = "policy_id") Integer policy_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Boolean r = true;
		accessPolicyApppubService.deleteBypolicy_id(policy_id);
		if (_app != null) {
			r = accessPolicyApppubService.editAccessPolicyApppub(policy_id, Arrays.asList(_app));
		}
		result.accumulate("success", r);
		return result;
	}

	@RequestMapping("/delAccessPolicyApppub")
	@ResponseBody
	public JSONObject delAccessPolicyApppub(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = accessPolicyApppubService.delAccessPolicyApppub(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/findAccessPolicyAppAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyAppAndUser(@RequestParam("policy_id") Integer policy_id,
	                                             @RequestParam(value = "page_start",required = false)int page_start,
	                                             @RequestParam(value ="page_length",required = false)int page_length) {
	
		ArrayList<App> resultAccessPolicyApp = new ArrayList<App>();
		ArrayList<App> resultApp = new ArrayList<App>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultAccessPolicyApp = (ArrayList<App>) accessPolicyApppubService.selectById(policy_id);
		resultApp = (ArrayList<App>) apppubService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultApp.removeAll(resultAccessPolicyApp);
		JSONArray jsonArray_p_app = JSONArray.fromObject(resultAccessPolicyApp);
		JSONArray jsonArray_app = JSONArray.fromObject(resultApp);
	
		result.accumulate("success", true);
		result.accumulate("data_app", jsonArray_app);
		result.accumulate("data_p_app", jsonArray_p_app);
		return result;
	}
}
