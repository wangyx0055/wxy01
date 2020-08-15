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
import com.longersec.blj.domain.ApppubResourceUser;
import com.longersec.blj.service.ApppubResourceUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubResourceUser")
public class ApppubResourceUserController {

	@Autowired
	private ApppubResourceUserService apppubResourceUserService;

	@RequestMapping("/listApppubResourceUser")
	@ResponseBody
	public JSONObject listApppubResourceUser(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubResourceUsers = new ArrayList<Object>();
		ArrayList<ApppubResourceUser> apppubResourceUsers = new ArrayList<ApppubResourceUser>();
		ApppubResourceUser apppubResourceUser=null;
		long total = 0;
		resultApppubResourceUsers = (ArrayList<Object>)apppubResourceUserService.findAll(apppubResourceUser, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubResourceUsers)) {
			apppubResourceUsers = (ArrayList<ApppubResourceUser>)resultApppubResourceUsers.get(0);
			total = ((ArrayList<Long>) resultApppubResourceUsers.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubResourceUsers);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubResourceUser")
	@ResponseBody
	public JSONObject addApppubResourceUser(ApppubResourceUser apppubResourceUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceUserService.addApppubResourceUser(apppubResourceUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editApppubResourceUser")
	@ResponseBody
	public JSONObject editApppubResourceUser(ApppubResourceUser apppubResourceUser, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceUserService.editApppubResourceUser(apppubResourceUser);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delApppubResourceUser")
	@ResponseBody
	public JSONObject delApppubResourceUser(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceUserService.delApppubResourceUser(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
