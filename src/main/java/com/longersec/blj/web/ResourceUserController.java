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
import com.longersec.blj.domain.ResourceUser;
import com.longersec.blj.service.ResourceUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/resourceUser")
public class ResourceUserController {

	@Autowired
	private ResourceUserService resourceUserService;

	@RequestMapping("/listResourceUser")
	@ResponseBody
	public JSONObject listResourceUser(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultResourceUsers = new ArrayList<Object>();
		ArrayList<ResourceUser> resourceUsers = new ArrayList<ResourceUser>();
		ResourceUser resourceUser=null;
		long total = 0;
		resultResourceUsers = (ArrayList<Object>)resourceUserService.findAll(resourceUser, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultResourceUsers)) {
			resourceUsers = (ArrayList<ResourceUser>)resultResourceUsers.get(0);
			total = ((ArrayList<Long>) resultResourceUsers.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(resourceUsers);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addResourceUser")
	@ResponseBody
	public JSONObject addResourceUser(ResourceUser resourceUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceUserService.addResourceUser(resourceUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editResourceUser")
	@ResponseBody
	public JSONObject editResourceUser(ResourceUser resourceUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceUserService.editResourceUser(resourceUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delResourceUser")
	@ResponseBody
	public JSONObject delResourceUser(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = resourceUserService.delResourceUser(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
