package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.User;
import com.longersec.blj.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/accessPolicy")
public class AccessPolicyController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private AccessPolicyService accessPolicyService;
	@Autowired
	private AccessPolicyUserService accessPolicyUserService;
	@Autowired
	private AccessPolicyGroupService accessPolicyGroupService;
	@Autowired
	private AccessPolicyDeviceAccountService accessPolicyDeviceAccountService;
	@Autowired
	private AccessPolicyApppubService accessPolicyApppubService;
	@Autowired
	private DepartmentService departmentService;
	private static String id=null;

	/**
	 * @Description 修改搜索
	 * @param stat 代表状态
	 * @param sname 输入框的值
	 * @author wxy
	 * @create 2020/3/31 13:40
	 **/
	@RequestMapping("/listAccessPolicy")
	@ResponseBody
	public JSONObject listAccessPolicy(AccessPolicy accessPolicy,
									   @RequestParam(value = "sname",defaultValue = "",required = false)String sname,
									   @RequestParam(value = "stat",defaultValue = "",required = false)String stat,
									   @RequestParam(value = "type",required = false)Integer type,
									   HttpServletRequest request,
									   HttpSession session) {
		if (sname==null||sname.equals("")) {
			sname="";
		}
		if (stat==null||stat.equals("")) {
			stat="";
		}

		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Integer> depart_ids = new ArrayList<>();
		if (p_user.getRole_id().equals(5)){

			//获取所在的部门
			int depart_id = p_user.getDepartment();
			depart_ids = departmentService.selectById(depart_id);
			depart_ids.add(p_user.getDepartment());
		}

		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultAccessPolicys = new ArrayList<Object>();
		ArrayList<AccessPolicy> accessPolicys = new ArrayList<AccessPolicy>();
		long total = 0;
		resultAccessPolicys = (ArrayList<Object>)accessPolicyService.findAll(accessPolicy,sname,stat,type,page_start, page_length,depart_ids);
		if(CollectionUtils.isNotEmpty(resultAccessPolicys)) {
			accessPolicys = (ArrayList<AccessPolicy>)resultAccessPolicys.get(0);
			total = ((ArrayList<Long>) resultAccessPolicys.get(1)).get(0);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(accessPolicys);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addAccessPolicy")
	@ResponseBody
	public JSONObject addAccessPolicy(@RequestParam(value = "users[]",required=false) Integer[] _users,@RequestParam(value = "groups[]", required=false) Integer[] _groups,@RequestParam(value = "devices[]", required=false) Integer[] _devices,@RequestParam(value = "devicegroup[]", required=false) Integer[] _devicegroup,@RequestParam(value = "app[]", required=false) Integer[] _app,AccessPolicy accessPolicy, HttpServletRequest request, HttpSession session) {
		List<Integer> users = _users==null?null:Arrays.asList(_users);
		List<Integer> groups =  _groups==null?null:Arrays.asList(_groups);
		List<Integer> devices =  _devices==null?null:Arrays.asList(_devices);
		List<Integer> devicegroup =  _devicegroup==null?null:Arrays.asList(_devicegroup);
		List<Integer> app =  _app==null?null:Arrays.asList(_app);
		//获取当前登录用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		accessPolicy.setDepartment(user.getDepartment());

		JSONObject result = new JSONObject();
		String name = accessPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("success", false);
			result.accumulate("name", "策略名不正确");
		}else {
			result.accumulate("success", true);
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("访问控制策略");
		operatorLog.setDetails("添加访问控制策略["+accessPolicy.getName()+"]");
		operatorLog.setContent("添加");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			    accessPolicyService.addAccessPolicy(accessPolicy);
			if(users!=null) {
				accessPolicyUserService.addAccessPolicyUser(accessPolicy.getId(), users);
			}
			if(groups!=null&&devicegroup!=null) {
				accessPolicyGroupService.addAccessPolicyGroup(accessPolicy.getId(), groups,devicegroup);
			}
			if(devices!=null) {
				accessPolicyDeviceAccountService.addAccessPolicyDevice(accessPolicy.getId(), devices);
			}
			if(app!=null) {
				accessPolicyApppubService.addAccessPolicyApppub(accessPolicy.getId(), app);
			}
			result.accumulate("success", accessPolicy.getId()>0?true:false);
			operatorLogService.addOperatorLog(operatorLog);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editStatus")
	@ResponseBody
	public JSONObject editStatus(@RequestParam(value = "status")Integer status, @RequestParam(value = "id")Integer id){
		JSONObject result = new JSONObject();
		Boolean r = accessPolicyService.editStatus(status,id);
		if (r){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping("/editAccessPolicy")
	@ResponseBody
	public JSONObject editAccessPolicy(AccessPolicy accessPolicy, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		String name = accessPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("success", false);
			result.accumulate("name", "策略名不正确");
		}else {
			result.accumulate("success", true);
		}
		
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("访问控制策略");
		operatorLog.setDetails("编辑访问控制策略["+accessPolicy.getName()+"]");
		operatorLog.setContent("编辑");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = accessPolicyService.editAccessPolicy(accessPolicy);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
	@RequestMapping("/delAccessPolicy")
	@ResponseBody
	public JSONObject delAccessPolicy(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		String policies = "";
		for (String _id : ids){
			AccessPolicy accessPolicy =accessPolicyService.getById(Integer.valueOf(_id));
			policies += accessPolicy.getName()+",";
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("访问控制策略");
		operatorLog.setDetails("删除访问控制策略["+policies.substring(0,policies.length()-1)+"]");
		operatorLog.setContent("删除");
		//是否操作成功
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = accessPolicyService.delAccessPolicy(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
	@RequestMapping("/checkname")
	@ResponseBody
	public JSONObject checkname(@RequestParam(value = "name") String name,@RequestParam(value = "id" ,required = false)Integer id){
		JSONObject result = new JSONObject();

		result.put("success", true);
		if (id==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			AccessPolicy accessPolicy  = accessPolicyService.checkname(name);
			if (accessPolicy==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			AccessPolicy accessPolicy  = accessPolicyService.checkname(name);
			if (accessPolicy==null){
				result.put("success", true);
			}else {
				if (accessPolicy.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}

		return result;
	}

}
