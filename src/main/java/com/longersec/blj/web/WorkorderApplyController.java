package com.longersec.blj.web;

import com.longersec.blj.dao.WorkorderApplyDeviceAccountDao;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.WorkorderApplyService;
import com.longersec.blj.service.WorkorderAuditLogService;
import com.longersec.blj.utils.BljConstant;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.Operator_log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/workorderApply")
public class WorkorderApplyController {

	@Autowired
	private WorkorderApplyService workorderApplyService;

	@Autowired
	private WorkorderApplyDeviceAccountDao workorderApplyDeviceAccountDao;
	
	@Autowired
	private OperatorLogService operatorLogService;
	
	@Autowired
	private WorkorderAuditLogService workorderAuditLogService;

	JSONObject result = null;
	@RequestMapping("/listWorkorderApply")
	@ResponseBody
	public JSONObject listWorkorderApply(WorkorderApply workorderApply,HttpServletRequest request) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<WorkorderApply> workorderApplys = new ArrayList<>();
		long total = 0;
		if (workorderApply.getSearchAll().equals("") || workorderApply.getSearchAll()==null){
			workorderApply.setSearchAll(null);
		}
		//获取当前系统用户
		User principal = (User)SecurityUtils.getSubject().getPrincipal();
		workorderApply.setApply_user_id(principal.getId());
		ArrayList<Object> resultWorkorderApplys = (ArrayList<Object>)workorderApplyService.findAll(workorderApply, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultWorkorderApplys)) {
			workorderApplys = (ArrayList<WorkorderApply>)resultWorkorderApplys.get(0);
			total = ((ArrayList<Long>) resultWorkorderApplys.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(workorderApplys);
		result = new JSONObject();
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		result.put("data", jsonArray);
		return result;
	}

	@RequestMapping("/addWorkorderApply")
	@ResponseBody
	public JSONObject addWorkorderApply(WorkorderApply workorderApply,@RequestParam(value = "devices[]", required=false) Integer[] _devices,
	                                    HttpServletRequest request, HttpSession session) {
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("添加");
		result = new JSONObject();
		if (_devices == null) {
			operatorLog.setDetails("添加授权工单没有关联设备");
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		}
		//设置工单名称
		workorderApply.setName(KeyUtil.workName());
		//获取当前系统用户
		User principal = (User)SecurityUtils.getSubject().getPrincipal();
		workorderApply.setApply_user_id(principal.getId());
		operatorLog.setDetails("添加授权工单["+workorderApply.getName()+"]");
		boolean r = workorderApplyService.addWorkorderApply(workorderApply);
		boolean d = workorderApplyDeviceAccountDao.addWorkorderApplyDeviceAccount(workorderApply.getId(),Arrays.asList(_devices));
		if(r&&d) {
			workorderAuditLogService.createWorkorderAuditLog(workorderApply.getId());
		}
		result.put(BljConstant.SUCCESS, r && d);
		operatorLog.setResult(r&& d?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/editWorkorderApply")
	@ResponseBody
	public JSONObject editWorkorderApply(WorkorderApply workorderApply, HttpServletRequest request, HttpSession session) {
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("编辑");
		operatorLog.setDetails("编辑授权工单["+workorderApply.getName()+"]");
		result = new JSONObject();
		boolean r = workorderApplyService.editWorkorderApply(workorderApply);
		result.accumulate(BljConstant.SUCCESS, r);
		operatorLog.setResult(r?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/delWorkorderApply")
	@ResponseBody
	public JSONObject delWorkorderApply(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("删除");
		if(_ids.isEmpty()) {
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setDetails("删除授权工单");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		}
		Boolean r = workorderApplyService.delWorkorderApply(_ids);
		result.put(BljConstant.SUCCESS, r);
		operatorLog.setDetails("删除授权工单的id为:["+_ids+"]");
		operatorLog.setResult(r?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/updateResult")
	@ResponseBody
	public JSONObject updateResult(@RequestParam("id")int id, @RequestParam("result")int results,@RequestParam("status")int status,HttpServletRequest request, HttpSession session) {
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("更新");
		result = new JSONObject();
		if (results == 0 &&  status > 1){
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setResult("失败");
			operatorLog.setDetails("撤回失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		}
		int selectDeadLine =-1;
		if (results == 1 && status != 0){
			result.put(BljConstant.SUCCESS, false);
			operatorLog.setResult("失败");
			operatorLog.setDetails("提交失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		} else {
			selectDeadLine = workorderApplyService.selectDeadLine();
		}
		operatorLog.setDetails("更新状态");
		boolean r = workorderApplyService.updateResult(results,id,selectDeadLine);
		result.put(BljConstant.SUCCESS, r);
		operatorLog.setResult(r?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/listCmd")
	@ResponseBody
	public JSONObject listCmd(@RequestParam("id")int id){
		result = new JSONObject();
		String cmd = workorderApplyService.selectCmd(id);
		result.put("cmd",cmd);
		result.put(BljConstant.SUCCESS,true);
		return result;
	}
}
