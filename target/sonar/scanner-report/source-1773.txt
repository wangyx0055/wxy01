package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.IpGroup;
import com.longersec.blj.service.IpGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/ipGroup")
public class IpGroupController {

	@Autowired
	private IpGroupService ipGroupService;

	@RequestMapping("/listIpGroup")
	@ResponseBody
	public JSONObject listIpGroup(IpGroup ipGroup,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultIpGroups = new ArrayList<Object>();
		ArrayList<IpGroup> ipGroups = new ArrayList<IpGroup>();
		long total = 0;
		resultIpGroups = (ArrayList<Object>)ipGroupService.findAll(ipGroup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultIpGroups)) {
			ipGroups = (ArrayList<IpGroup>)resultIpGroups.get(0);
			total = ((ArrayList<Long>) resultIpGroups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(ipGroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addIpGroup")
	@ResponseBody
	public JSONObject addIpGroup(IpGroup ipGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = ipGroupService.addIpGroup(ipGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editIpGroup")
	@ResponseBody
	public JSONObject editIpGroup(IpGroup ipGroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = ipGroupService.editIpGroup(ipGroup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delIpGroup")
	@ResponseBody
	public JSONObject delIpGroup(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = ipGroupService.delIpGroup(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
