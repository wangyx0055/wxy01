package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.CrontabScriptConfigUserService;
import com.longersec.blj.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
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


/**
 * 
 */
@Controller
@RequestMapping(value = "/crontabScriptUser")
public class CrontabScriptUserController {

	@Autowired
	private CrontabScriptConfigUserService crontabScriptConfigUserService;

	@Autowired
	private UserService userService;

	@RequestMapping("/findCrontabScriptConfigUser")
	@ResponseBody
	public JSONObject findAccessPolicyUserAndUser(@RequestParam("config_id") Integer config_id,
	                                              @RequestParam(value = "page_start",required = false)int page_start,
	                                              @RequestParam(value ="page_length",required = false)int page_length) {
		ArrayList<Users> resultCrontabScriptConfigUser = new ArrayList<Users>();
		ArrayList<Users> resultUsers = new ArrayList<Users>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultCrontabScriptConfigUser = (ArrayList<Users>) crontabScriptConfigUserService.selectById(config_id);
		resultUsers = (ArrayList<Users>) userService.selectNameAndId(users.getDepartment(),page_start,page_length);
		JSONObject result = new JSONObject();
		resultUsers.removeAll(resultCrontabScriptConfigUser);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultCrontabScriptConfigUser);
		JSONArray jsonArray_users = JSONArray.fromObject(resultUsers);

		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}

	@RequestMapping("/editAccessPolicyUser")
	@ResponseBody
	public JSONObject editAccessPolicyUser(@RequestParam(value = "users[]",required = false) Integer[] _users,@RequestParam(value = "config_id") Integer config_id) {
		JSONObject result = new JSONObject();
		List<Integer> users = _users==null?Arrays.asList(0):Arrays.asList(_users);
		result.put("success", true);
		crontabScriptConfigUserService.deleteByid(config_id);
		boolean r = crontabScriptConfigUserService.editCrontabScriptconfigUse(config_id,users);
		result.put("success", r);
		return result;
	}
}
