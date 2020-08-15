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
import com.longersec.blj.domain.SystemMessage;
import com.longersec.blj.service.SystemMessageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/systemMessage")
public class SystemMessageController {
	@Autowired
	private SystemMessageService systemMessageService;

	@RequestMapping("/listSystemMessage")
	@ResponseBody
	public JSONObject listSystemMessage(SystemMessage systemMessage,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultSystemMessages = new ArrayList<Object>();
		ArrayList<SystemMessage> systemMessages = new ArrayList<SystemMessage>();
		long total = 0;
		resultSystemMessages = (ArrayList<Object>)systemMessageService.findAll(systemMessage, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultSystemMessages)) {
			systemMessages = (ArrayList<SystemMessage>)resultSystemMessages.get(0);
			total = ((ArrayList<Long>) resultSystemMessages.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(systemMessages);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addSystemMessage")
	@ResponseBody
	public JSONObject addSystemMessage(SystemMessage systemMessage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = systemMessageService.addSystemMessage(systemMessage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editSystemMessage")
	@ResponseBody
	public JSONObject editSystemMessage(SystemMessage systemMessage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = systemMessageService.editSystemMessage(systemMessage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delSystemMessage")
	@ResponseBody
	public JSONObject delSystemMessage(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.put("success", true);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = systemMessageService.delSystemMessage(_ids);
			result.put("success", r);
		}
		return result;
	}
}
