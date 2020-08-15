package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.service.ApppubAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubAccount")
public class ApppubAccountController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private ApppubAccountService apppubAccountService;

	@RequestMapping("/listApppubAccount")
	@ResponseBody
	public JSONObject listApppubAccount(@RequestParam(value = "sname",required = false,defaultValue = "") String sname,
										ApppubAccount apppubAccount,HttpServletRequest request,
										HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubAccounts = new ArrayList<Object>();
		ArrayList<ApppubAccount> apppubAccounts = new ArrayList<ApppubAccount>();
		long total = 0;
		int type=1;
		resultApppubAccounts = (ArrayList<Object>)apppubAccountService.findAll(sname,type,apppubAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubAccounts)) {
			apppubAccounts = (ArrayList<ApppubAccount>)resultApppubAccounts.get(0);
			total = ((ArrayList<Long>) resultApppubAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubAccount")
	@ResponseBody
	public JSONObject addApppubAccount(@Validated ApppubAccount apppubAccount, BindingResult errorResult,HttpServletRequest request, HttpSession session) {
		return this.editApppubAccount(apppubAccount,errorResult,request,session);
	}

	@RequestMapping("/editApppubAccount")
	@ResponseBody
	public JSONObject editApppubAccount(@Validated ApppubAccount apppubAccount, BindingResult errorResult, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		Boolean r = false;
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		//操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("应用发布");
		String isexitU = apppubAccountService.checkName(apppubAccount.getName());
		if (apppubAccount.getId() == null){
			operatorLog.setDetails("添加应用发布");
			operatorLog.setContent("添加");
			if(resultMap == null && isexitU == null){
				r = apppubAccountService.addApppubAccount(apppubAccount);
				if (r){
					operatorLog.setResult("成功");
					result.put("success", true);
				}else{
					operatorLog.setResult("失败");
					result.put("success", false);
				}
			}else{
				result.put("success", false);
			}
		}else{
			operatorLog.setDetails("编辑应用发布");
			operatorLog.setContent("编辑");
			if (resultMap == null && isexitU == null){
				r = apppubAccountService.editApppubAccount(apppubAccount);
				if (r){
					operatorLog.setResult("成功");
					result.put("success", true);
				}else{
					operatorLog.setResult("失败");
					result.put("success", false);
				}
			}else{
				result.put("success", false);
			}
		}
		operatorLog.setResult("");
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/delApppubAccount")
	@ResponseBody
	public JSONObject delApppubAccount(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.put("success", true);
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("应用");
		operatorLog.setDetails("删除应用发布");
		operatorLog.setContent("删除");
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		boolean r = apppubAccountService.delApppubAccount(_ids);
		if (r){
			operatorLog.setResult("成功");
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}
//	@RequestMapping("/queryByappserver")
//	@ResponseBody
//	public JSONObject queryByappserver(@RequestParam(value = "apppub_server_id") Integer apppub_server_id,
//											 @RequestParam(value = "sname",required = false,defaultValue = "") String sname,
//											 @RequestParam(value = "type",required = false) Integer type,
//											 ApppubAccount apppubAccount,HttpServletRequest request, HttpSession session) {
//		if (sname == null || "".equals(sname)) {
//			sname = "";
//		}
//		int page_start = Integer.parseInt(request.getParameter("start"));
//		int page_length = Integer.parseInt(request.getParameter("length"));
//		ArrayList<Object> resultApppubAccounts = new ArrayList<Object>();
//		ArrayList<ApppubAccount> apppubAccounts = new ArrayList<ApppubAccount>();
//		long total = 0;
//		resultApppubAccounts = (ArrayList<Object>)apppubAccountService.queryByappserver(apppub_server_id,sname,type,apppubAccount, page_start, page_length);
//		if(CollectionUtils.isNotEmpty(resultApppubAccounts)) {
//			apppubAccounts = (ArrayList<ApppubAccount>)resultApppubAccounts.get(0);
//			total = ((ArrayList<Long>) resultApppubAccounts.get(1)).get(0);
//		}
//		JSONArray jsonArray = JSONArray.fromObject(apppubAccounts);
//		JSONObject result = new JSONObject();
//		result.accumulate("success", true);
//		result.accumulate("recordsTotal", total);
//		result.accumulate("recordsFiltered", total);
//		result.accumulate("data", jsonArray);
//		return result;
//	}
	@RequestMapping("/checkName")
	@ResponseBody
	public JSONObject checkName(@RequestParam(value = "id",defaultValue = "") Integer id,@RequestParam(value = "name") String name) {
		JSONObject result = new JSONObject();
		if (id == null || "".equals(id)){
			id = 0;
		}
		String count = apppubAccountService.selectName(id,name);
		if (count != null){
			result.put("success", false);
		}else{
			result.put("success", true);
		}
		return result;
	}
	
	@RequestMapping("/getUserApppubAccount")
	@ResponseBody
	public JSONObject getUserApppubAccount(ApppubAccount apppubAccount,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubAccounts = new ArrayList<Object>();
		ArrayList<ApppubAccount> apppubAccounts = new ArrayList<ApppubAccount>();
		long total = 0;
		resultApppubAccounts = (ArrayList<Object>)apppubAccountService.getApppubAccount(apppubAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubAccounts)) {
			apppubAccounts = (ArrayList<ApppubAccount>)resultApppubAccounts.get(0);
			total = ((ArrayList<Long>) resultApppubAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
}
