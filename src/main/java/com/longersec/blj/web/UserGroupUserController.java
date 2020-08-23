package com.longersec.blj.web;

import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.GroupService;
import com.longersec.blj.service.UserGroupUserService;
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

@Controller
@RequestMapping(value = "/userGroupUser")
public class UserGroupUserController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserGroupUserService userGroupUserService;

	@Autowired
	private UserService userService;

	@RequestMapping("/findUserGroupUser")
	@ResponseBody
	public JSONObject findUserGroupUser(@RequestParam("group_id") Integer group_id) {
		ArrayList<Users> resultUserGroupUsers = new ArrayList<Users>();
		ArrayList<Users> resultUsers = new ArrayList<Users>();
		User users = (User) SecurityUtils.getSubject().getPrincipal();
		resultUserGroupUsers = (ArrayList<Users>) userGroupUserService.selectById(group_id);
		resultUsers = (ArrayList<Users>) userService.selectNameAndId(users.getDepartment());
		JSONObject result = new JSONObject();
		resultUsers.removeAll(resultUserGroupUsers);
		JSONArray jsonArray_p_users = JSONArray.fromObject(resultUserGroupUsers);
		JSONArray jsonArray_users = JSONArray.fromObject(resultUsers);
		result.accumulate("success", true);
		result.accumulate("data_users", jsonArray_users);
		result.accumulate("data_p_users", jsonArray_p_users);
		return result;
	}

	@RequestMapping("/addUserGroupUser")
	@ResponseBody
	public JSONObject addUserGroupUser(@RequestParam(value = "users[]") Integer[] _users, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> users =  Arrays.asList(_users);
		Integer policy_id = null;
		Boolean r = userGroupUserService.addUsergroupUser(policy_id,users);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editUserGroupUser")
	@ResponseBody
	public JSONObject editUserGroupUser(@RequestParam(value = "users[]",required = false) Integer[] _users,@RequestParam(value = "group_id") Integer group_id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		userGroupUserService.deleteByUserGroupUserId(group_id);
		boolean r = true;
		if (_users != null) {
			r = userGroupUserService.editUsergroupUser(group_id, Arrays.asList(_users));
		}
		int selectAccessPolicyUserCounts = userGroupUserService.selectUsergroupUserCounts(group_id);
		boolean groupCount = groupService.updateGroupCount(selectAccessPolicyUserCounts, group_id);
		result.put("success", r && groupCount);
		return result;
	}
}
