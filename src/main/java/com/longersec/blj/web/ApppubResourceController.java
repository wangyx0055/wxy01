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
import com.longersec.blj.domain.ApppubResource;
import com.longersec.blj.service.ApppubResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubResource")
public class ApppubResourceController {

	@Autowired
	private ApppubResourceService apppubResourceService;

	@RequestMapping("/listApppubResource")
	@ResponseBody
	public JSONObject listApppubResource(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubResources = new ArrayList<Object>();
		ArrayList<ApppubResource> apppubResources = new ArrayList<ApppubResource>();
		ApppubResource apppubResource=null;
		long total = 0;
		resultApppubResources = (ArrayList<Object>)apppubResourceService.findAll(apppubResource, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubResources)) {
			apppubResources = (ArrayList<ApppubResource>)resultApppubResources.get(0);
			total = ((ArrayList<Long>) resultApppubResources.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubResources);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubResource")
	@ResponseBody
	public JSONObject addApppubResource(ApppubResource apppubResource, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceService.addApppubResource(apppubResource);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editApppubResource")
	@ResponseBody
	public JSONObject editApppubResource(ApppubResource apppubResource, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceService.editApppubResource(apppubResource);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delApppubResource")
	@ResponseBody
	public JSONObject delApppubResource(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubResourceService.delApppubResource(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
