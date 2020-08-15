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
import com.longersec.blj.domain.Apppub;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.ApppubService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/apppub")
public class ApppubController {

	@Autowired
	private ApppubService apppubService;

	@Autowired
	private OperatorLogService operatorLogService;

	@RequestMapping("/listApppub")
	@ResponseBody
	public JSONObject listApppub(HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubs = new ArrayList<Object>();
		ArrayList<Apppub> apppubs = new ArrayList<Apppub>();
		Apppub apppub=null;
		long total = 0;
		resultApppubs = (ArrayList<Object>)apppubService.findAll(apppub, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubs)) {
			apppubs = (ArrayList<Apppub>)resultApppubs.get(0);
			total = ((ArrayList<Long>) resultApppubs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppub")
	@ResponseBody
	public JSONObject addApppub(Apppub apppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("应用");
		operatorLog.setDetails("增加应用");
		operatorLog.setContent("添加");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = apppubService.addApppub(apppub);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editApppub")
	@ResponseBody
	public JSONObject editApppub(Apppub apppub, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("应用");
		operatorLog.setDetails("编辑应用");
		operatorLog.setContent("编辑");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = apppubService.editApppub(apppub);
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delApppub")
	@ResponseBody
	public JSONObject delApppub(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("删除");
		operatorLog.setDetails("删除应用");
		operatorLog.setContent("删除");
		//是否操作成功
		if(_ids.isEmpty()) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = apppubService.delApppub(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}
}
