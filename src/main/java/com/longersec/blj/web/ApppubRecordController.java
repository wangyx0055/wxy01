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
import com.longersec.blj.domain.ApppubRecord;
import com.longersec.blj.service.ApppubRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppubRecord")
public class ApppubRecordController {

	@Autowired
	private ApppubRecordService apppubRecordService;

	@RequestMapping("/listApppubRecord")
	@ResponseBody
	public JSONObject listApppubRecord(ApppubRecord apppubRecord,HttpServletRequest request, HttpSession session) {
		
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubRecords = new ArrayList<Object>();
		ArrayList<ApppubRecord> apppubRecords = new ArrayList<ApppubRecord>();
		long total = 0;
		resultApppubRecords = (ArrayList<Object>)apppubRecordService.findAll(apppubRecord, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubRecords)) {
			apppubRecords = (ArrayList<ApppubRecord>)resultApppubRecords.get(0);
			total = ((ArrayList<Long>) resultApppubRecords.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubRecords);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubRecord")
	@ResponseBody
	public JSONObject addApppubRecord(ApppubRecord apppubRecord, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubRecordService.addApppubRecord(apppubRecord);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editApppubRecord")
	@ResponseBody
	public JSONObject editApppubRecord(ApppubRecord apppubRecord, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = apppubRecordService.editApppubRecord(apppubRecord);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delApppubRecord")
	@ResponseBody
	public JSONObject delApppubRecord(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubRecordService.delApppubRecord(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
