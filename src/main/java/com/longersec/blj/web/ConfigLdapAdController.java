package com.longersec.blj.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.Department;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.DepartmentService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.UpdateDepartmentCount;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.ConfigLdapAd;
import com.longersec.blj.service.ConfigLdapAdService;
import com.longersec.blj.utils.Validator;
import com.longersec.blj.utils.AdOperate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configLdapAd")
public class ConfigLdapAdController {

	@Autowired
	private ConfigLdapAdService configLdapAdService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private OperatorLogService operatorLogService;

	@RequestMapping("/listConfigLdapAd")
	@ResponseBody
	public JSONObject listConfigLdapAd(ConfigLdapAd configLdapAd,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigLdapAds = new ArrayList<Object>();
		ArrayList<ConfigLdapAd> configLdapAds = new ArrayList<ConfigLdapAd>();
		long total = 0;
		resultConfigLdapAds = (ArrayList<Object>)configLdapAdService.findAll(configLdapAd, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigLdapAds)) {
			configLdapAds = (ArrayList<ConfigLdapAd>)resultConfigLdapAds.get(0);
			total = ((ArrayList<Long>) resultConfigLdapAds.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configLdapAds);
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		result.put("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigLdapAd")
	@ResponseBody
	public JSONObject addConfigLdapAd(@Validated ConfigLdapAd configLdapAd,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.put("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configLdapAdService.addConfigLdapAd(configLdapAd);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigLdapAd")
	@ResponseBody
	public JSONObject editConfigLdapAd(@Validated ConfigLdapAd configLdapAd,BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if(resultMap!=null) {
			result.put("msg", resultMap);
			result.put("success",false);
			return result;
		}
		result.put("success", true);
		if(result.getBoolean("success")) {
			if(configLdapAd.getPassword().equals("xxxxxx")) {
				configLdapAd.setPassword(null);
			}
			Boolean r = configLdapAdService.editConfigLdapAd(configLdapAd);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigLdapAd")
	@ResponseBody
	public JSONObject delConfigLdapAd(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		List<Integer> _ids =  Arrays.asList(ids);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configLdapAdService.delConfigLdapAd(_ids);
			result.put("success", r);
		}
		return result;
	}

	@RequestMapping("/asyncUser")
	@ResponseBody
	public JSONObject asyncUser(@RequestParam("ids") Integer ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		//获取当前登录用户对象
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		ConfigLdapAd configLdapAd = configLdapAdService.getConfigLdapById(ids);
		AdOperate adOperate = new AdOperate();
		//检测连接是否通过
		Boolean r = AdOperate.checkConnect(configLdapAd);
		if(!r) {
			result.put("success",false);
			result.put("error","认证失败!");
			return result;
		}
			//拿到AD域查询结果集
			ArrayList<User> userArrayList = adOperate.searchUser(configLdapAd);

			//部门过滤判断
			boolean isAddDepartment = true;

			//检测是否返回有结果集
			if(userArrayList.size()!=0){

			for(int i=0;i<userArrayList.size();i++) {
				//将获取的DN传到AdOperate中dnOperate方法进行处理，获得处理结果
				List<String> departmentList = adOperate.dnOperate(configLdapAd, userArrayList.get(i).getLdap_dn());
				//默认初始父ID为总部的ID
				int parentId = 1;
				for (int j = 0; j < departmentList.size(); j++) {
					//部门过滤
					isAddDepartment = departmentList.get(j).equals(configLdapAd.getFilter_department());
					if(isAddDepartment){
						break;
					}
					//查找Name和父ID的结果集
					Department department = departmentService.selectByNameAndParentId(departmentList.get(j), parentId);
					//没有的话创建部门
					if (department == null) {
						Department department1 = new Department();
						department1.setName(departmentList.get(j));
						department1.setParent_id(parentId);
						department1.setCreate_id(user.getId());
						department1.setCreate_time((int) System.currentTimeMillis());
						department1.setDescription("LDAP/AD域同步");
						//添加日志
						OperatorLog operatorLog = Operator_log.log(request, session);
						operatorLog.setModule("认证配置");
						operatorLog.setDetails("增加部门"+"["+departmentList.get(j)+"],"+"来自LDAP/AD域同步");
						operatorLog.setContent("添加");
						boolean addDepartment =departmentService.addDepartment(department1);
						//根据返回值判断
						if(addDepartment){
							operatorLog.setResult("成功");
							result.put("success",true);
						} else {
							operatorLog.setResult("失败");
							result.put("success",false);
						}
						operatorLogService.addOperatorLog(operatorLog);
						//拿到创建后的部门ID作为下一个部门的父级部门ID
						Department department2 = departmentService.selectByNameAndParentId(departmentList.get(j), parentId);
						parentId = department2.getId();
					}else {
						//如果已有部门,拿到此部门ID作为搜索下级部门的父ID
						parentId = department.getId();
					}
				}
				if(isAddDepartment){
					continue;
				}
				//判断是否有同名用户，有只进行更新操作，更新字段：Ldap_dn,Department
				User isEdit = userService.checkADUsername(userArrayList.get(i).getUsername());
				if (isEdit != null) {
					User user1 = new User();
					user1.setId(isEdit.getId());
					user1.setLdap_dn(userArrayList.get(i).getLdap_dn());
					//部门处理完毕，获得最后的部门ID作为用户的department
					user1.setDepartment(parentId);
					//添加日志
					OperatorLog operatorLog = Operator_log.log(request, session);
					operatorLog.setModule("认证配置");
					operatorLog.setDetails("编辑用户"+"["+isEdit.getUsername()+"],"+"来自LDAP/AD域同步");
					operatorLog.setContent("编辑");
					boolean editUser = userService.editUser(user1);
					if(editUser){
						operatorLog.setResult("成功");
						//更新部门人数，原有的部门人数减 1 ,新增加的部门人数加 1
						UpdateDepartmentCount.userUpdateDepartmentCount(departmentService,isEdit.getDepartment(),-1);
						UpdateDepartmentCount.userUpdateDepartmentCount(departmentService,parentId,1);
						result.put("success",true);
					} else {
						operatorLog.setResult("失败");
						result.put("success",false);
					}
					operatorLogService.addOperatorLog(operatorLog);
				} else {
					//部门处理完毕，获得最后的部门ID作为用户的department
					userArrayList.get(i).setDepartment(parentId);
					//添加日志
					OperatorLog operatorLog = Operator_log.log(request, session);
					operatorLog.setModule("认证配置");
					operatorLog.setDetails("添加用户"+"["+userArrayList.get(i).getUsername()+"],"+"来自LDAP/AD域同步");
					operatorLog.setContent("添加");
					boolean addUser = userService.addUser(userArrayList.get(i));
					if(addUser){
						operatorLog.setResult("成功");
						//更新部门人数
						UpdateDepartmentCount.userUpdateDepartmentCount(departmentService,parentId,1);
						result.put("success",true);
					} else {
						operatorLog.setResult("失败");
						result.put("success",false);
					}
					operatorLogService.addOperatorLog(operatorLog);
				}
			}
			OperatorLog operatorLog = Operator_log.log(request, session);
			operatorLog.setModule("认证配置");
			operatorLog.setDetails("LDAP/AD域同步成功");
			operatorLog.setContent("同步");
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			result.put("success", true);
		}else{
			result.put("success", false);
			result.put("error","同步失败!");
			OperatorLog operatorLog = Operator_log.log(request, session);
			operatorLog.setModule("认证配置");
			operatorLog.setDetails("LDAP/AD域同步失败");
			operatorLog.setContent("同步");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
}
