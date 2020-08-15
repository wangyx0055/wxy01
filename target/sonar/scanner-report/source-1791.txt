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
import com.longersec.blj.domain.ResourceGroup;
import com.longersec.blj.service.ResourceGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/resourceGroup")
public class ResourceGroupController {

	@Autowired
	private ResourceGroupService resourceGroupService;

	@RequestMapping("/listResourceGroup")
	@ResponseBody
	public JSONObject listResourceGroup(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultResourceGroups = new ArrayList<Object>();
		ArrayList<ResourceGroup> resourceGroups = new ArrayList<ResourceGroup>();
		ResourceGroup resourceGroup=null;
		long total = 0;
		resultResourceGroups = (ArrayList<Object>)resourceGroupService.findAll(resourceGroup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultResourceGroups)) {
			resourceGroups = (ArrayList<ResourceGroup>)resultResourceGroups.get(0);
			total = ((ArrayList<Long>) resultResourceGroups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(resourceGroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addResourceGroup")
	@ResponseBody
	public JSONObject addResourceGroup(ResourceGroup resourceGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceGroupService.addResourceGroup(resourceGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editResourceGroup")
	@ResponseBody
	public JSONObject editResourceGroup(ResourceGroup resourceGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = resourceGroupService.editResourceGroup(resourceGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delResourceGroup")
	@ResponseBody
	public JSONObject delResourceGroup(List<Integer> ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = resourceGroupService.delResourceGroup(ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
