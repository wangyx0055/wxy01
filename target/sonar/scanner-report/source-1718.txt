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
import com.longersec.blj.domain.ApppubResourceGroup;
import com.longersec.blj.service.ApppubResourceGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubResourceGroup")
public class ApppubResourceGroupController {

	@Autowired
	private ApppubResourceGroupService apppubResourceGroupService;

	@RequestMapping("/listApppubResourceGroup")
	@ResponseBody
	public JSONObject listApppubResourceGroup(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubResourceGroups = new ArrayList<Object>();
		ArrayList<ApppubResourceGroup> apppubResourceGroups = new ArrayList<ApppubResourceGroup>();
		ApppubResourceGroup apppubResourceGroup=null;
		long total = 0;
		resultApppubResourceGroups = (ArrayList<Object>)apppubResourceGroupService.findAll(apppubResourceGroup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubResourceGroups)) {
			apppubResourceGroups = (ArrayList<ApppubResourceGroup>)resultApppubResourceGroups.get(0);
			total = ((ArrayList<Long>) resultApppubResourceGroups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubResourceGroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubResourceGroup")
	@ResponseBody
	public JSONObject addApppubResourceGroup(ApppubResourceGroup apppubResourceGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceGroupService.addApppubResourceGroup(apppubResourceGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editApppubResourceGroup")
	@ResponseBody
	public JSONObject editApppubResourceGroup(ApppubResourceGroup apppubResourceGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceGroupService.editApppubResourceGroup(apppubResourceGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delApppubResourceGroup")
	@ResponseBody
	public JSONObject delApppubResourceGroup(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceGroupService.delApppubResourceGroup(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
