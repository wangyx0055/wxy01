package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.AccessPolicyApppubService;
import com.longersec.blj.service.ApppubService;
import com.longersec.blj.utils.BljConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/accessPolicyApppub")
public class AccessPolicyApppubController {

	@Autowired
	private AccessPolicyApppubService accessPolicyApppubService;

	@Autowired
	private ApppubService apppubService;

	JSONObject result = null;

	@RequestMapping("/editAccessPolicyApppub")
	@ResponseBody
	public JSONObject editAccessPolicyApppub(@RequestParam(value = "app[]",required = false) Integer[] _app,@RequestParam(value = "policy_id") Integer policy_id) {
		result = new JSONObject();
		Boolean r = true;
		accessPolicyApppubService.deleteBypolicy_id(policy_id);
		if (_app != null) {
			r = accessPolicyApppubService.editAccessPolicyApppub(policy_id, Arrays.asList(_app));
		}
		result.put(BljConstant.SUCCESS, r);
		return result;
	}

	@RequestMapping("/findAccessPolicyAppAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyAppAndUser(@RequestParam("policy_id") Integer policy_id,
	                                             @RequestParam(value = "page_start",required = false)int page_start,
	                                             @RequestParam(value ="page_length",required = false)int page_length) {
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		ArrayList<App> resultAccessPolicyApp = (ArrayList<App>) accessPolicyApppubService.selectById(policy_id);
		ArrayList<App> resultApp = (ArrayList<App>) apppubService.selectNameAndId(users.getDepartment(),page_start,page_length);
		result = new JSONObject();
		resultApp.removeAll(resultAccessPolicyApp);
		JSONArray jsonArray_p_app = JSONArray.fromObject(resultAccessPolicyApp);
		JSONArray jsonArray_app = JSONArray.fromObject(resultApp);
	
		result.put(BljConstant.SUCCESS, true);
		result.put("data_app", jsonArray_app);
		result.put("data_p_app", jsonArray_p_app);
		return result;
	}
}
