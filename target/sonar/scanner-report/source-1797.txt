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
import com.longersec.blj.domain.SessionAutodelete;
import com.longersec.blj.service.SessionAutodeleteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/sessionAutodelete")
public class SessionAutodeleteController {

	@Autowired
	private SessionAutodeleteService sessionAutodeleteService;

	@RequestMapping("/listSessionAutodelete")
	@ResponseBody
	public JSONObject listSessionAutodelete(SessionAutodelete sessionAutodelete,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSessionAutodeletes = new ArrayList<Object>();
		ArrayList<SessionAutodelete> sessionAutodeletes = new ArrayList<SessionAutodelete>();
		long total = 0;
		resultSessionAutodeletes = (ArrayList<Object>)sessionAutodeleteService.findAll(sessionAutodelete, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultSessionAutodeletes)) {
			sessionAutodeletes = (ArrayList<SessionAutodelete>)resultSessionAutodeletes.get(0);
			total = ((ArrayList<Long>) resultSessionAutodeletes.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(sessionAutodeletes);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSessionAutodelete")
	@ResponseBody
	public JSONObject addSessionAutodelete(SessionAutodelete sessionAutodelete, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = sessionAutodeleteService.addSessionAutodelete(sessionAutodelete);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editSessionAutodelete")
	@ResponseBody
	public JSONObject editSessionAutodelete(SessionAutodelete sessionAutodelete, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = sessionAutodeleteService.editSessionAutodelete(sessionAutodelete);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delSessionAutodelete")
	@ResponseBody
	public JSONObject delSessionAutodelete(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = sessionAutodeleteService.delSessionAutodelete(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
