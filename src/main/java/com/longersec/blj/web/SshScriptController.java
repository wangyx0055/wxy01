package com.longersec.blj.web;

import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.SshScript;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.SshScriptService;
import com.longersec.blj.utils.Operator_log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping(value = "/sshScript")
public class SshScriptController {
	@Autowired
	private SshScriptService sshScriptService;
	@Autowired
	private OperatorLogService operatorLogService;
	@RequestMapping("/listCrontabScript")
	@ResponseBody
	public JSONObject listSshScript(SshScript sshScript, HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSshScripts = new ArrayList<Object>();
		ArrayList<SshScript> sshScripts = new ArrayList<SshScript>();
		long total = 0;
		resultSshScripts = (ArrayList<Object>)sshScriptService.findAll(sshScript, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultSshScripts)) {
			sshScripts = (ArrayList<SshScript>)resultSshScripts.get(0);
			total = ((ArrayList<Long>) resultSshScripts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(sshScripts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSshScript")
	@ResponseBody
	public JSONObject addSshScript(SshScript sshScript, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		//操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("设备账号");
		operatorLog.setDetails("添加sshkey");
		operatorLog.setContent("添加");
		operatorLogService.addOperatorLog(operatorLog);
		Boolean r = sshScriptService.addSshScript(sshScript);
		if (r) {
			operatorLog.setResult("成功");
		}else {
			operatorLog.setResult("失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/editSshScript")
	@ResponseBody
	public JSONObject editSshScript(SshScript sshScript, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		//操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("设备账户");
		operatorLog.setDetails("编辑sshkey");
		operatorLog.setContent("编辑");
		//是否操作成功
		operatorLog.setResult("成功");
		Boolean r = sshScriptService.editSshScript(sshScript);
		if (r) {
			operatorLog.setResult("成功");
		}else {
			operatorLog.setResult("失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		result.put("success", r);
		return result;
	}

	@RequestMapping("/delSshScript")
	@ResponseBody
	public JSONObject delSshScript(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("设备账号");
		operatorLog.setDetails("删除sshkey");
		operatorLog.setContent("删除");
		Boolean r = sshScriptService.delSshScript(_ids);
		//是否操作成功
		if(r) {
			operatorLog.setResult("成功");
		}else {
			operatorLog.setResult("失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		result.put("success", r);
		return result;
	}
}
