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
import com.longersec.blj.domain.IpGroupIp;
import com.longersec.blj.service.IpGroupIpService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/ipGroupIp")
public class IpGroupIpController {

	@Autowired
	private IpGroupIpService ipGroupIpService;

	@RequestMapping("/listIpGroupIp")
	@ResponseBody
	public JSONObject listIpGroupIp(IpGroupIp ipGroupIp,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultIpGroupIps = new ArrayList<Object>();
		ArrayList<IpGroupIp> ipGroupIps = new ArrayList<IpGroupIp>();
		long total = 0;
		resultIpGroupIps = (ArrayList<Object>)ipGroupIpService.findAll(ipGroupIp, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultIpGroupIps)) {
			ipGroupIps = (ArrayList<IpGroupIp>)resultIpGroupIps.get(0);
			total = ((ArrayList<Long>) resultIpGroupIps.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(ipGroupIps);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addIpGroupIp")
	@ResponseBody
	public JSONObject addIpGroupIp(IpGroupIp ipGroupIp, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = ipGroupIpService.addIpGroupIp(ipGroupIp);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editIpGroupIp")
	@ResponseBody
	public JSONObject editIpGroupIp(IpGroupIp ipGroupIp, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = ipGroupIpService.editIpGroupIp(ipGroupIp);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delIpGroupIp")
	@ResponseBody
	public JSONObject delIpGroupIp(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = ipGroupIpService.delIpGroupIp(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
