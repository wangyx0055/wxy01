package com.longersec.blj.web;

import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.ApppubAccountService;
import com.longersec.blj.service.DepartmentService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.BljConstant;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.Validator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "/apppubAccount")
public class ApppubAccountController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private ApppubAccountService apppubAccountService;
	@Autowired
	private DepartmentService departmentService;

	JSONObject result = null;
	@RequestMapping("/listApppubAccount")
	@ResponseBody
	public JSONObject listApppubAccount(@RequestParam(value = "sname",required = false,defaultValue = "") String sname,
										ApppubAccount apppubAccount,HttpServletRequest request) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<ApppubAccount> apppubAccounts = new ArrayList<>();
		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Integer> depart_ids = new ArrayList<>();
		if (p_user.getRole_id().equals(5)){
			//获取所在的部门
			int depart_id = p_user.getDepartment();
			depart_ids = departmentService.selectById(depart_id);
			depart_ids.add(p_user.getDepartment());
		}
		long total = 0;
		int type=1;
		ArrayList<Object> resultApppubAccounts = (ArrayList<Object>)apppubAccountService.findAll(sname,type,apppubAccount, page_start, page_length,depart_ids);
		if(CollectionUtils.isNotEmpty(resultApppubAccounts)) {
			apppubAccounts = (ArrayList<ApppubAccount>)resultApppubAccounts.get(0);
			total = ((ArrayList<Long>) resultApppubAccounts.get(1)).get(0);
		}
		for (ApppubAccount apppubAccount1 : apppubAccounts) {
			if(apppubAccount1.getDepartment()!=0) {
				List<String> allParentName = departmentService.findAllParentName(apppubAccount1.getDepartment());
				StringBuilder stringBuilder = new StringBuilder();
				for (Object strings : allParentName) {
					stringBuilder.append(strings).append("/");
				}
				apppubAccount1.setTopName(stringBuilder.substring(0, stringBuilder.length() - 1));
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubAccounts);
		result = new JSONObject();
		result.put(BljConstant.SUCCESS, true);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		result.put("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubAccount")
	@ResponseBody
	public JSONObject addApppubAccount(@Validated ApppubAccount apppubAccount, BindingResult errorResult,
									   @RequestParam(value = "id",required = false)Integer id,
									   HttpServletRequest request, HttpSession session) {
		return this.editApppubAccount(apppubAccount,errorResult,id,request,session);
	}

	@RequestMapping("/editApppubAccount")
	@ResponseBody
	public JSONObject editApppubAccount(@Validated ApppubAccount apppubAccount, BindingResult errorResult,
										@RequestParam(value = "id")Integer id,
										HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
		boolean r;
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		//操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("发布应用");
		//检查重名的常量
		int checksum;
		//检查重名
		if(id == null){
			checksum = apppubAccountService.checkName(0,apppubAccount.getName());
		}else {
			checksum = apppubAccountService.checkName(id,apppubAccount.getName());
		}

		if (apppubAccount.getId() == null){
			operatorLog.setDetails("添加发布应用["+apppubAccount.getName()+"]");
			operatorLog.setContent("添加");
			if(resultMap == null && checksum ==0){
				r = apppubAccountService.addApppubAccount(apppubAccount);
				operatorLog.setResult(r?"成功":"失败");
				result.put(BljConstant.SUCCESS, r);
			}else{
				operatorLog.setResult("失败");
				result.put(BljConstant.SUCCESS, false);
			}
		}else{
			operatorLog.setDetails("编辑发布应用["+apppubAccount.getName()+"]");
			operatorLog.setContent("编辑");
			if (resultMap == null && checksum ==0){
				r = apppubAccountService.editApppubAccount(apppubAccount);
				operatorLog.setResult(r?"成功":"失败");
				result.put(BljConstant.SUCCESS, r);
			}else{
				operatorLog.setResult("失败");
				result.put(BljConstant.SUCCESS, false);
			}
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/delApppubAccount")
	@ResponseBody
	public JSONObject delApppubAccount(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("发布应用");
		operatorLog.setDetails("删除发布应用");
		operatorLog.setContent("删除");
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		}
		boolean r = apppubAccountService.delApppubAccount(_ids);
		operatorLog.setResult(r?"成功":"失败");
		result.put(BljConstant.SUCCESS, r);
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/checkName")
	@ResponseBody
	public JSONObject checkName(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "name") String name) {
		result = new JSONObject();
		int count = apppubAccountService.checkName(id,name);
		result.put(BljConstant.SUCCESS, count==0);
		return result;
	}
	
	@RequestMapping("/getUserApppubAccount")
	@ResponseBody
	public JSONObject getUserApppubAccount(ApppubAccount apppubAccount,HttpServletRequest request) {
		int pageStart = Integer.parseInt(request.getParameter("start"));
		int pageLength = Integer.parseInt(request.getParameter("length"));
		ArrayList<ApppubAccount> apppubAccounts = new ArrayList<>();
		long total = 0;
		ArrayList<Object> resultApppubAccounts = (ArrayList<Object>)apppubAccountService.getApppubAccount(apppubAccount, pageStart, pageLength);
		if(CollectionUtils.isNotEmpty(resultApppubAccounts)) {
			apppubAccounts = (ArrayList<ApppubAccount>)resultApppubAccounts.get(0);
			total = ((ArrayList<Long>) resultApppubAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubAccounts);
		result = new JSONObject();
		result.put(BljConstant.SUCCESS, true);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		result.put("data", jsonArray);
		return result;
	}
}
