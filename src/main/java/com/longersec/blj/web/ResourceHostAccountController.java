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
import com.longersec.blj.domain.ResourceHostAccount;
import com.longersec.blj.service.ResourceHostAccountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/resourceHostAccount")
public class ResourceHostAccountController {

	@Autowired
	private ResourceHostAccountService resourceHostAccountService;

	@RequestMapping("/listResourceHostAccount")
	@ResponseBody
	public JSONObject listResourceHostAccount(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultResourceHostAccounts = new ArrayList<Object>();
		ArrayList<ResourceHostAccount> resourceHostAccounts = new ArrayList<ResourceHostAccount>();
		ResourceHostAccount resourceHostAccount=null;
		long total = 0;
		resultResourceHostAccounts = (ArrayList<Object>)resourceHostAccountService.findAll(resourceHostAccount, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultResourceHostAccounts)) {
			resourceHostAccounts = (ArrayList<ResourceHostAccount>)resultResourceHostAccounts.get(0);
			total = ((ArrayList<Long>) resultResourceHostAccounts.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(resourceHostAccounts);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addResourceHostAccount")
	@ResponseBody
	public JSONObject addResourceHostAccount(ResourceHostAccount resourceHostAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceHostAccountService.addResourceHostAccount(resourceHostAccount);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editResourceHostAccount")
	@ResponseBody
	public JSONObject editResourceHostAccount(ResourceHostAccount resourceHostAccount, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceHostAccountService.editResourceHostAccount(resourceHostAccount);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delResourceHostAccount")
	@ResponseBody
	public JSONObject delResourceHostAccount(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = resourceHostAccountService.delResourceHostAccount(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
