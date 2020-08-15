package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.AccessPolicyUserService;
import com.longersec.blj.service.GroupService;
import com.longersec.blj.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/accessPolicyUser")
public class AccessPolicyUserController {

	@Autowired
	private AccessPolicyUserService accessPolicyUserService;

	@Autowired
	private UserService userService;

	@RequestMapping("/findAccessPolicyUserAndUser")
	@ResponseBody
	public JSONObject findAccessPolicyUserAndUser(@RequestParam("pid") Integer pid) {
		ArrayList<Users> resultAccessPolicyUsers = new ArrayList<Users>();
		ArrayList<Users> resultUsers = new ArrayList<Users>();
		resultAccessPolicyUsers = (ArrayList<Users>) accessPolicyUserService.selectById(pid);
		resultUsers = (ArrayList<Users>) userService.selectNameAndId();
		JSONObject result = new JSONObject();
		resultUsers.removeAll(resultAccessPolicyUsers);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultAccessPolicyUsers);
		JSONArray jsonArray_users = JSONArray.fromObject(resultUsers);

		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}

	@RequestMapping("/addAccessPolicyUser")
	@ResponseBody
	public JSONObject addAccessPolicyUser(@RequestParam(value = "users[]") Integer[] _users, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> users =  Arrays.asList(_users);
		Integer policy_id = null;
		boolean r = accessPolicyUserService.addAccessPolicyUser(policy_id,users);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editAccessPolicyUser")
	@ResponseBody
	public JSONObject editAccessPolicyUser(@RequestParam(value = "users[]",required = false) Integer[] _users,@RequestParam(value = "policy_id") Integer policy_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		boolean r = true;
		accessPolicyUserService.deleteBypolicy_id(policy_id);
		if (_users != null) {
			r = accessPolicyUserService.editAccessPolicyUser(policy_id, Arrays.asList(_users));
		}
		result.put("success", r);
		return result;
	}
}
