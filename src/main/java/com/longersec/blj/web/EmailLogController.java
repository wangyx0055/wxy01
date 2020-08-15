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
import com.longersec.blj.domain.EmailLog;
import com.longersec.blj.service.EmailLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/emailLog")
public class EmailLogController {

	@Autowired
	private EmailLogService emailLogService;

	@RequestMapping("/listEmailLog")
	@ResponseBody
	public JSONObject listEmailLog(EmailLog emailLog,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultEmailLogs = new ArrayList<Object>();
		ArrayList<EmailLog> emailLogs = new ArrayList<EmailLog>();
		long total = 0;
		resultEmailLogs = (ArrayList<Object>)emailLogService.findAll(emailLog, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultEmailLogs)) {
			emailLogs = (ArrayList<EmailLog>)resultEmailLogs.get(0);
			total = ((ArrayList<Long>) resultEmailLogs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(emailLogs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addEmailLog")
	@ResponseBody
	public JSONObject addEmailLog(EmailLog emailLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = emailLogService.addEmailLog(emailLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editEmailLog")
	@ResponseBody
	public JSONObject editEmailLog(EmailLog emailLog, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = emailLogService.editEmailLog(emailLog);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delEmailLog")
	@ResponseBody
	public JSONObject delEmailLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = emailLogService.delEmailLog(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
