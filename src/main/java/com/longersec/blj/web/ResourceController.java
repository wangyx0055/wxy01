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
import com.longersec.blj.domain.Resource;
import com.longersec.blj.service.ResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/listResource")
	@ResponseBody
	public JSONObject listResource(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultResources = new ArrayList<Object>();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Resource resource=null;
		long total = 0;
		resultResources = (ArrayList<Object>)resourceService.findAll(resource, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultResources)) {
			resources = (ArrayList<Resource>)resultResources.get(0);
			total = ((ArrayList<Long>) resultResources.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(resources);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addResource")
	@ResponseBody
	public JSONObject addResource(Resource resource, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceService.addResource(resource);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editResource")
	@ResponseBody
	public JSONObject editResource(Resource resource, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceService.editResource(resource);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delResource")
	@ResponseBody
	public JSONObject delResource(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = resourceService.delResource(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
