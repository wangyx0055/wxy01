package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.dao.RoleMenuDao;
import com.longersec.blj.domain.DTO.RoleMenuDTO;
import com.longersec.blj.domain.Menu;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.RoleMenuService;
import com.longersec.blj.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.Role;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.RoleService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/listRole")
	@ResponseBody
	public JSONObject listRole(Role role, Model model) {
		List<RoleMenuDTO> resultRoles = new ArrayList<RoleMenuDTO>();
		long total = 0;
		resultRoles = roleService.findAll();
		model.addAttribute("resultRoles",resultRoles);
		JSONArray jsonArray = JSONArray.fromObject(resultRoles);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public JSONObject addRole(@RequestParam(value = "list[]",required = false) Integer[] list,@RequestParam(value = "name") String name,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		Role role = new Role();
		role.setName(name);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("添加角色["+role.getName()+"]");
		operatorLog.setContent("添加");
		Role isExist = roleService.checkname(name);
		//是否操作成功/Users
		if(result.getBoolean("success") && isExist==null) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = roleService.addRole(role,list);
			result.put("success", r?true:false);
		}  else {
			result.put("success", false);
			operatorLog.setResult("失败");
			result.put("msg","角色名重复");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editRole")
	@ResponseBody
	public JSONObject editRole(@RequestParam(value = "id") Integer id,@RequestParam(value = "name") String name, @RequestParam(value = "menuid[]") Integer[] menuid, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Role role = new Role();
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("编辑角色["+name+"]");
		operatorLog.setContent("编辑");
		Role isExist = roleService.checkname(name);
		result.put("success", true);
		if (isExist==null){
			result.put("success", true);
		}else {
			if (!isExist.getId().equals(id)){
				result.put("msg","角色名重复");
				result.put("success", false);
			}
		}
		if(result.getBoolean("success")) {
			role.setName(name);
			role.setId(id);
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);

			Boolean r = roleService.editRole(role);
			Boolean d = roleMenuService.delRoleMenu(id);
			Boolean m = roleMenuDao.addRoleMenu(id,menuid);
			if (r==true && d==true && m==true){
				result.put("success",true);
			}else {
				result.put("success",false);
			}
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delRole")
	@ResponseBody
	public JSONObject delRole(@RequestParam(value = "id") Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("删除角色["+roleService.selectByid(id)+"]");
		operatorLog.setContent("删除");
		//检查删除的角色是否绑定了用户
		User user = userService.checkUser(id);
		if (user!=null){
			result.put("success", false);
		}
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = roleService.delRole(id);
			Boolean d = roleMenuService.delRoleMenu(id);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public JSONObject findAll(){

		ArrayList<Role> roles = roleService.list();

		JSONArray jsonArray = JSONArray.fromObject(roles);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("data", jsonArray);
		return result;

	}

	@RequestMapping("checkname")
	@ResponseBody
	public JSONObject checkname(@RequestParam("name")String name){
		JSONObject result = new JSONObject();

		Role checkname = roleService.checkname(name);
		if (checkname!=null){
			result.put("success", false);
		}else {
			result.put("success", true);
		}
		return result;

	}
}
