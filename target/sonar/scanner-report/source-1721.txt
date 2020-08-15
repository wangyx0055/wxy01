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
import com.longersec.blj.domain.BackupConfig;
import com.longersec.blj.service.BackupConfigService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/backupConfig")
public class BackupConfigController {

	@Autowired
	private BackupConfigService backupConfigService;

	@RequestMapping("/listBackupConfig")
	@ResponseBody
	public JSONObject listBackupConfig(BackupConfig backupConfig,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultBackupConfigs = new ArrayList<Object>();
		ArrayList<BackupConfig> backupConfigs = new ArrayList<BackupConfig>();
		long total = 0;
		resultBackupConfigs = (ArrayList<Object>)backupConfigService.findAll(backupConfig, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultBackupConfigs)) {
			backupConfigs = (ArrayList<BackupConfig>)resultBackupConfigs.get(0);
			total = ((ArrayList<Long>) resultBackupConfigs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(backupConfigs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addBackupConfig")
	@ResponseBody
	public JSONObject addBackupConfig(BackupConfig backupConfig, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = backupConfigService.addBackupConfig(backupConfig);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editBackupConfig")
	@ResponseBody
	public JSONObject editBackupConfig(BackupConfig backupConfig, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = backupConfigService.editBackupConfig(backupConfig);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delBackupConfig")
	@ResponseBody
	public JSONObject delBackupConfig(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = backupConfigService.delBackupConfig(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
}
