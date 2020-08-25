package com.longersec.blj.web;

import com.longersec.blj.domain.ConfigWorkorder;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.ConfigWorkorderService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.BljConstant;
import com.longersec.blj.utils.Operator_log;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping(value = "/configWorkorder")
public class ConfigWorkorderController {

	@Autowired
	private OperatorLogService operatorLogService;

	@Autowired
	private ConfigWorkorderService configWorkorderService;

	JSONObject result = null;

	@RequestMapping("/listConfigWorkorder")
	@ResponseBody
	public JSONObject listConfigWorkorder() {
		ConfigWorkorder configWorkorderServiceById = configWorkorderService.getById(1);
		result = new JSONObject();
		result.put("data",configWorkorderServiceById);
		return result;
	}

	@RequestMapping("/editConfigWorkorder")
	@ResponseBody
	public JSONObject editConfigWorkorder(ConfigWorkorder configWorkorder,
	                                      @RequestParam("type")int type,
	                                      HttpServletRequest request, HttpSession session) {
		result = new JSONObject();
		configWorkorder.setId(1);
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		configWorkorder.setUpdate_time(dateFormat.format(System.currentTimeMillis()));
		//操作日志
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setContent("编辑");
		operatorLog.setModule("系统配置-工单配置");
		operatorLog.setDetails(type==1?"编辑审批流程":"编辑基本配置");
		if (configWorkorder.getDead_hours() != null && (configWorkorder.getDead_hours()>72 ||configWorkorder.getDead_hours()<0)) {
			result.put(BljConstant.SUCCESS,false);
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			return result;
		}
		boolean r = configWorkorderService.editConfigWorkorder(configWorkorder);
		operatorLog.setResult(r?"成功":"失败");
		result.put(BljConstant.SUCCESS,r);
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}
}
