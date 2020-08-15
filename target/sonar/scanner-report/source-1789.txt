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
import com.longersec.blj.domain.Record;
import com.longersec.blj.service.RecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/record")
public class RecordController {

	@Autowired
	private RecordService recordService;

	@RequestMapping("/listRecord")
	@ResponseBody
	public JSONObject listRecord(Record record,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultRecords = new ArrayList<Object>();
		ArrayList<Record> records = new ArrayList<Record>();
		long total = 0;
		resultRecords = (ArrayList<Object>)recordService.findAll(record, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultRecords)) {
			records = (ArrayList<Record>)resultRecords.get(0);
			total = ((ArrayList<Long>) resultRecords.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(records);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addRecord")
	@ResponseBody
	public JSONObject addRecord(Record record, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordService.addRecord(record);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editRecord")
	@ResponseBody
	public JSONObject editRecord(Record record, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordService.editRecord(record);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delRecord")
	@ResponseBody
	public JSONObject delRecord(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = recordService.delRecord(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
