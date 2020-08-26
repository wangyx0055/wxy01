package com.longersec.blj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.dao.WorkorderApplyDeviceAccountDao;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.WorkorderApplyDeviceAccountService;
import com.longersec.blj.utils.BljConstant;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.Operator_log;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.service.WorkorderApplyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/workorderApply")
public class WorkorderApplyController {

	@Autowired
	private WorkorderApplyService workorderApplyService;

	@Autowired
	private WorkorderApplyDeviceAccountDao workorderApplyDeviceAccountDao;
	
	@Autowired
	private OperatorLogService operatorLogService;

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
	public JSONObject addWorkorderApply(WorkorderApply workorderApply,@RequestParam(value = "devices",required = false)Integer[] device,
	                                    HttpServletRequest request, HttpSession session) {
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("添加");
		result = new JSONObject();
		//更新时间
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		workorderApply.setUpdate_time(dateFormat.format(System.currentTimeMillis()));
		//设置工单名称
		workorderApply.setName(KeyUtil.genUniqueKey());
		operatorLog.setDetails("添加授权工单["+workorderApply.getName()+"]");
		boolean r = workorderApplyService.addWorkorderApply(workorderApply);
		boolean d = true;
		if (device != null){
			d = workorderApplyDeviceAccountDao.addWorkorderApplyDeviceAccount(workorderApply.getId(),Arrays.asList(device));
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
		//更新时间
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		workorderApply.setUpdate_time(dateFormat.format(System.currentTimeMillis()));
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
		operatorLog.setDetails("删除授权工单的id为:["+ids+"]");
		operatorLog.setResult(r?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/updateResult")
	@ResponseBody
	public JSONObject updateResult(@RequestParam("id")int id, @RequestParam("result")int results,HttpServletRequest request, HttpSession session) {
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("授权工单");
		operatorLog.setContent("更新");
		operatorLog.setDetails("更新状态");
		result = new JSONObject();
		boolean r = workorderApplyService.updateResult(results,id);
		result.put(BljConstant.SUCCESS, r);
		operatorLog.setResult(r?"成功":"失败");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}
}
