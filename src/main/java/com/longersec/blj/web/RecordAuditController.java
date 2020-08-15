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
import com.longersec.blj.domain.RecordAudit;
import com.longersec.blj.service.RecordAuditService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/recordAudit")
public class RecordAuditController {

	@Autowired
	private RecordAuditService recordAuditService;

	@RequestMapping("/listRecordAudit")
	@ResponseBody
	public JSONObject listRecordAudit(RecordAudit recordAudit,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultRecordAudits = new ArrayList<Object>();
		ArrayList<RecordAudit> recordAudits = new ArrayList<RecordAudit>();
		long total = 0;
		resultRecordAudits = (ArrayList<Object>)recordAuditService.findAll(recordAudit, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultRecordAudits)) {
			recordAudits = (ArrayList<RecordAudit>)resultRecordAudits.get(0);
			total = ((ArrayList<Long>) resultRecordAudits.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(recordAudits);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addRecordAudit")
	@ResponseBody
	public JSONObject addRecordAudit(RecordAudit recordAudit, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordAuditService.addRecordAudit(recordAudit);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editRecordAudit")
	@ResponseBody
	public JSONObject editRecordAudit(RecordAudit recordAudit, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordAuditService.editRecordAudit(recordAudit);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delRecordAudit")
	@ResponseBody
	public JSONObject delRecordAudit(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = recordAuditService.delRecordAudit(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
