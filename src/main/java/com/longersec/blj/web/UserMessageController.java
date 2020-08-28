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
import com.longersec.blj.domain.UserMessage;
import com.longersec.blj.service.UserMessageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/userMessage")
public class UserMessageController {

	@Autowired
	private UserMessageService userMessageService;

	@RequestMapping("/listUserMessage")
	@ResponseBody
	public JSONObject listUserMessage(UserMessage userMessage,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultUserMessages = new ArrayList<Object>();
		ArrayList<UserMessage> userMessages = new ArrayList<UserMessage>();
		long total = 0;
		resultUserMessages = (ArrayList<Object>)userMessageService.findAll(userMessage, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultUserMessages)) {
			userMessages = (ArrayList<UserMessage>)resultUserMessages.get(0);
			total = ((ArrayList<Long>) resultUserMessages.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(userMessages);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addUserMessage")
	@ResponseBody
	public JSONObject addUserMessage(UserMessage userMessage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = userMessageService.addUserMessage(userMessage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editUserMessage")
	@ResponseBody
	public JSONObject editUserMessage(UserMessage userMessage, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = userMessageService.editUserMessage(userMessage);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delUserMessage")
	@ResponseBody
	public JSONObject delUserMessage(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = userMessageService.delUserMessage(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
