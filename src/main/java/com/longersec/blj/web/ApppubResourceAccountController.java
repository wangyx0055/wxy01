package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.ApppubResourceAccount;
import com.longersec.blj.service.ApppubResourceAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubResourceAccount")
public class ApppubResourceAccountController {

	@Autowired
	private ApppubResourceAccountService apppubResourceAccountService;

	@RequestMapping("/listApppubResourceAccount")
	@ResponseBody
	public JSONObject listApppubResourceAccount(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubResourceAccounts = new ArrayList<Object>();
		ArrayList<ApppubResourceAccount> apppubResourceAccounts = new ArrayList<ApppubResourceAccount>();
		ApppubResourceAccount apppubResourceAccount=null;
		long total = 0;
		resultApppubResourceAccounts = (ArrayList<Object>)apppubResourceAccountService.findAll(apppubResourceAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubResourceAccounts)) {
			apppubResourceAccounts = (ArrayList<ApppubResourceAccount>)resultApppubResourceAccounts.get(0);
			total = ((ArrayList<Long>) resultApppubResourceAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubResourceAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubResourceAccount")
	@ResponseBody
	public JSONObject addApppubResourceAccount(ApppubResourceAccount apppubResourceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceAccountService.addApppubResourceAccount(apppubResourceAccount);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editApppubResourceAccount")
	@ResponseBody
	public JSONObject editApppubResourceAccount(ApppubResourceAccount apppubResourceAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceAccountService.editApppubResourceAccount(apppubResourceAccount);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delApppubResourceAccount")
	@ResponseBody
	public JSONObject delApppubResourceAccount(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceAccountService.delApppubResourceAccount(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
