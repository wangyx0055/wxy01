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
import com.longersec.blj.domain.Syslog;
import com.longersec.blj.service.SyslogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/syslog")
public class SyslogController {

	@Autowired
	private SyslogService syslogService;

	@RequestMapping("/listSyslog")
	@ResponseBody
	public JSONObject listSyslog(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSyslogs = new ArrayList<Object>();
		ArrayList<Syslog> syslogs = new ArrayList<Syslog>();
		Syslog syslog=null;
		long total = 0;
		resultSyslogs = (ArrayList<Object>)syslogService.findAll(syslog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultSyslogs)) {
			syslogs = (ArrayList<Syslog>)resultSyslogs.get(0);
			total = ((ArrayList<Long>) resultSyslogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(syslogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSyslog")
	@ResponseBody
	public JSONObject addSyslog(Syslog syslog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = syslogService.addSyslog(syslog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editSyslog")
	@ResponseBody
	public JSONObject editSyslog(Syslog syslog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = syslogService.editSyslog(syslog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delSyslog")
	@ResponseBody
	public JSONObject delSyslog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = syslogService.delSyslog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
