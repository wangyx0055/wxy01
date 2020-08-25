package com.longersec.blj.web;

import com.longersec.blj.dao.RoleMenuDao;
import com.longersec.blj.domain.DTO.RoleMenuDTO;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.Role;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.RoleMenuService;
import com.longersec.blj.service.RoleService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.BljConstant;
import com.longersec.blj.utils.Operator_log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

	JSONObject result = null;
	
	@RequestMapping("/listRole")
	@ResponseBody
	public JSONObject listRole(Model model) {
		List<RoleMenuDTO> resultRoles = roleService.findAll();
		model.addAttribute("resultRoles",resultRoles);
		JSONArray jsonArray = JSONArray.fromObject(resultRoles);
		result = new JSONObject();
		result.put(BljConstant.SUCCESS, true);
		result.put("data", jsonArray);
		return result;
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public JSONObject addRole(@RequestParam(value = "list[]",required = false) Integer[] list,@RequestParam(value = "name") String name,HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
		Role role = new Role();
		role.setName(name);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("添加角色["+role.getName()+"]");
		operatorLog.setContent("添加");
		Role isExist = roleService.checkname(name);
		//是否操作成功/Users
		if(isExist == null) {
			operatorLog.setResult("成功");
			Boolean r = roleService.addRole(role,list);
			result.put(BljConstant.SUCCESS, r);
		} else {
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setResult("失败");
			result.put("msg","角色名重复");
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/editRole")
	@ResponseBody
	public JSONObject editRole(@RequestParam(value = "id") Integer id,@RequestParam(value = "name") String name, @RequestParam(value = "menuid[]") Integer[] menuid, HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
		Role role = new Role();
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("编辑角色["+name+"]");
		operatorLog.setContent("编辑");
		Role isExist = roleService.checkname(name);
		if (isExist == null){
			role.setName(name);
			role.setId(id);
			Boolean r = roleService.editRole(role);
			Boolean d = roleMenuService.delRoleMenu(id);
			Boolean m = roleMenuDao.addRoleMenu(id,menuid);
			if (r && d && m){
				result.put(BljConstant.SUCCESS,true);
				operatorLog.setResult("成功");
			}else {
				result.put(BljConstant.SUCCESS,false);
				operatorLog.setResult("失败");
			}
		}else {
			if (!isExist.getId().equals(id)){
				result.put("msg","角色名重复");
				result.put(BljConstant.SUCCESS, false);
				operatorLog.setResult("失败");
			}
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/delRole")
	@ResponseBody
	public JSONObject delRole(@RequestParam(value = "id") Integer id, HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("角色");
		operatorLog.setDetails("删除角色["+roleService.selectByid(id)+"]");
		operatorLog.setContent("删除");
		//检查删除的角色是否绑定了用户
		User user = userService.checkUser(id);
		if (user!=null){
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setResult("失败");
		} else {
			Boolean r = roleService.delRole(id);
			Boolean d = roleMenuService.delRoleMenu(id);
			result.accumulate(BljConstant.SUCCESS, r && d);
			operatorLog.setResult(r&&d ? "成功":"失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public JSONObject findAll(){
		ArrayList<Role> roles = roleService.list();
		JSONArray jsonArray = JSONArray.fromObject(roles);
		result = new JSONObject();
		result.put(BljConstant.SUCCESS, true);
		result.put("data", jsonArray);
		return result;

	}

	@RequestMapping("checkname")
	@ResponseBody
	public JSONObject checkname(@RequestParam("name")String name){
		result = new JSONObject();
		Role checkname = roleService.checkname(name);
		result.put(BljConstant.SUCCESS, checkname == null);
		return result;

	}
}
