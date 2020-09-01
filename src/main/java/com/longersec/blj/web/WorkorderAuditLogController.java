package com.longersec.blj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.ConfigWorkorder;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.domain.WorkorderAuditLog;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.ConfigWorkorderService;
import com.longersec.blj.service.DepartmentService;
import com.longersec.blj.service.WorkorderApplyService;
import com.longersec.blj.service.WorkorderAuditLogService;
import com.sun.tools.javac.resources.javac;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/workorderAuditLog")
public class WorkorderAuditLogController {

	@Autowired
	private WorkorderAuditLogService workorderAuditLogService;
	@Autowired
	private WorkorderApplyService workorderApplyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ConfigWorkorderService configWorkorderService;

	@RequestMapping("/listWorkorderAuditLog")
	@ResponseBody
	public JSONObject listWorkorderAuditLog(WorkorderAuditLog workorderAuditLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		if(page_length<0) {
			page_length = 100;
		}
		ArrayList<Object> resultWorkorderAuditLogs = new ArrayList<Object>();
		ArrayList<WorkorderAuditLog> workorderAuditLogs = new ArrayList<WorkorderAuditLog>();
		long total = 0;
		User user = (User) session.getAttribute("loginUser");
		if(user.getRole_id()==5) {
			workorderAuditLog.setDepartment(user.getDepartment());
		}
		resultWorkorderAuditLogs = (ArrayList<Object>)workorderAuditLogService.findAll(workorderAuditLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultWorkorderAuditLogs)) {
			workorderAuditLogs = (ArrayList<WorkorderAuditLog>)resultWorkorderAuditLogs.get(0);
			total = ((ArrayList<Long>) resultWorkorderAuditLogs.get(1)).get(0);
			for (WorkorderAuditLog _workorderAuditLog:workorderAuditLogs) {
				if (_workorderAuditLog.getDepartment() != 0) {
					List<String> allParentName = departmentService.findAllParentName(_workorderAuditLog.getDepartment());
					StringBuilder stringBuilder = new StringBuilder();
					for (Object strings : allParentName) {
						stringBuilder.append(strings).append("/");
					}
					_workorderAuditLog.setTopName(stringBuilder.substring(0,stringBuilder.length()>1?stringBuilder.length()-1:stringBuilder.length()));
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(workorderAuditLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/listAuditPeople")
	@ResponseBody
	public JSONObject listAuditPeople(WorkorderAuditLog workorderAuditLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultWorkorderAuditLogs = new ArrayList<Object>();
		ArrayList<WorkorderAuditLog> workorderAuditLogs = new ArrayList<WorkorderAuditLog>();
		long total = 0;
		User user = (User) session.getAttribute("loginUser");
		if(user.getRole_id()==5) {
			workorderAuditLog.setDepartment(user.getDepartment());
		}
		resultWorkorderAuditLogs = (ArrayList<Object>)workorderAuditLogService.listAuditPeople(workorderAuditLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultWorkorderAuditLogs)) {
			workorderAuditLogs = (ArrayList<WorkorderAuditLog>)resultWorkorderAuditLogs.get(0);
			total = ((ArrayList<Long>) resultWorkorderAuditLogs.get(1)).get(0);
			for (WorkorderAuditLog _workorderAuditLog:workorderAuditLogs) {
				if (_workorderAuditLog.getDepartment() != 0) {
					List<String> allParentName = departmentService.findAllParentName(_workorderAuditLog.getDepartment());
					StringBuilder stringBuilder = new StringBuilder();
					for (Object strings : allParentName) {
						stringBuilder.append(strings).append("/");
					}
					_workorderAuditLog.setTopName(stringBuilder.substring(0,stringBuilder.length()>1?stringBuilder.length()-1:stringBuilder.length()));
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(workorderAuditLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addWorkorderAuditLog")
	@ResponseBody
	public JSONObject addWorkorderAuditLog(WorkorderAuditLog workorderAuditLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = workorderAuditLogService.addWorkorderAuditLog(workorderAuditLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editWorkorderAuditLog")
	@ResponseBody
	public JSONObject editWorkorderAuditLog(WorkorderAuditLog workorderAuditLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		User user = (User) session.getAttribute("loginUser");
		if(user.getRole_id()==5) {
			workorderAuditLog.setDepartment(user.getDepartment());
		}
		WorkorderAuditLog _worWorkorderAuditLog = workorderAuditLogService.getApprovingApply(workorderAuditLog);
		if(_worWorkorderAuditLog==null&&user.getRole_id()!=2) {
			result.put("success", false);
		}
		if(result.getBoolean("success")) {
			//workorderAuditLog.setId(_worWorkorderAuditLog.getId());
			workorderAuditLog.setAudit_datetime(new SimpleDateFormat ("YYYY-MM-dd hh:mm:ss").format(new Date()));
			workorderAuditLog.setAudit_user_id(user.getId());
			workorderAuditLog.setAudit_username(user.getUsername());
			workorderAuditLog.setAudit_realname(user.getRealname());
			workorderAuditLog.setDepartment(user.getDepartment());
			if(user.getRole_id()==2) {
				workorderAuditLog.setDepartment(0);
			}
			Boolean r = workorderAuditLogService.editWorkorderAuditLog(workorderAuditLog);
			if(r) {
				ConfigWorkorder configWorkorder = configWorkorderService.getById(1);
				
				WorkorderApply workorderApply = new WorkorderApply();
				workorderApply.setId(workorderAuditLog.getWorkorder_apply_id());
				workorderApply.setResult(2);
				if(user.getRole_id()==2 || workorderAuditLog.getAudit_result()==1 || configWorkorder.getMode()==0 || configWorkorder.getMode()==1&&workorderAuditLogService.getNoAudit(workorderAuditLog.getWorkorder_apply_id())!=null) {
					
					if(workorderAuditLog.getAudit_result()==2) {
						workorderApply.setResult(10);
					}else {
						workorderApply.setResult(3);
					}
					workorderApply.setReason(workorderAuditLog.getAudit_comment());
				}
				workorderApplyService.editWorkorderApply(workorderApply);
			}
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delWorkorderAuditLog")
	@ResponseBody
	public JSONObject delWorkorderAuditLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = workorderAuditLogService.delWorkorderAuditLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/listWorkorderApply")
	@ResponseBody
	public JSONObject listWorkorderApply(WorkorderAuditLog workorderAuditLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultWorkorderAuditLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> workorderAuditLogs = new ArrayList<Map<String, String>>();
		long total = 0;
		User user = (User) session.getAttribute("loginUser");
		if(user.getRole_id()==5) {
			workorderAuditLog.setDepartment(user.getDepartment());
		}else {
			workorderAuditLog.setDepartment(0);
		}
		resultWorkorderAuditLogs = (ArrayList<Object>)workorderAuditLogService.listWorkorderApply(workorderAuditLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultWorkorderAuditLogs)) {
			workorderAuditLogs = (ArrayList<Map<String, String>>)resultWorkorderAuditLogs.get(0);
			total = ((ArrayList<Long>) resultWorkorderAuditLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(workorderAuditLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
	@RequestMapping("/listWorkorderApplyDeviceAccount")
	@ResponseBody
	public JSONObject listWorkorderApplyDeviceAccount(WorkorderAuditLog workorderAuditLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultWorkorderAuditLogs = new ArrayList<Object>();
		ArrayList<Deviceaccess> workorderAuditLogs = new ArrayList<Deviceaccess>();
		long total = 0;
		User user = (User) session.getAttribute("loginUser");
		if(user.getRole_id()==5) {
			workorderAuditLog.setDepartment(user.getDepartment());
		}
		resultWorkorderAuditLogs = (ArrayList<Object>)workorderAuditLogService.listWorkorderApplyDeviceAccount(workorderAuditLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultWorkorderAuditLogs)) {
			workorderAuditLogs = (ArrayList<Deviceaccess>)resultWorkorderAuditLogs.get(0);
			total = ((ArrayList<Long>) resultWorkorderAuditLogs.get(1)).get(0);
			for (Deviceaccess _workorderAuditLog:workorderAuditLogs) {
				if (_workorderAuditLog.getDepartment() != 0) {
					List<String> allParentName = departmentService.findAllParentName(_workorderAuditLog.getDepartment());
					StringBuilder stringBuilder = new StringBuilder();
					for (Object strings : allParentName) {
						stringBuilder.append(strings).append("/");
					}
					_workorderAuditLog.setTopName(stringBuilder.substring(0,stringBuilder.length()>1?stringBuilder.length()-1:stringBuilder.length()));
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(workorderAuditLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
	
}
