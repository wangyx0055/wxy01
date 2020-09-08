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
import com.longersec.blj.domain.CmdgroupCmd;
import com.longersec.blj.service.CmdgroupCmdService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdgroupCmd")
public class CmdgroupCmdController {

	@Autowired
	private CmdgroupCmdService cmdgroupCmdService;

	@RequestMapping("/listCmdgroupCmd")
	@ResponseBody
	public JSONObject listCmdgroupCmd(CmdgroupCmd cmdgroupCmd,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdgroupCmds = new ArrayList<Object>();
		ArrayList<CmdgroupCmd> cmdgroupCmds = new ArrayList<CmdgroupCmd>();
		long total = 0;
		resultCmdgroupCmds = (ArrayList<Object>)cmdgroupCmdService.findAll(cmdgroupCmd, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCmdgroupCmds)) {
			cmdgroupCmds = (ArrayList<CmdgroupCmd>)resultCmdgroupCmds.get(0);
			total = ((ArrayList<Long>) resultCmdgroupCmds.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(cmdgroupCmds);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCmdgroupCmd")
	@ResponseBody
	public JSONObject addCmdgroupCmd(CmdgroupCmd cmdgroupCmd, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Boolean r = cmdgroupCmdService.addCmdgroupCmd(cmdgroupCmd);
		result.accumulate("success", r);
		return result;
	}

	@RequestMapping("/editCmdgroupCmd")
	@ResponseBody
	public JSONObject editCmdgroupCmd(CmdgroupCmd cmdgroupCmd, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Boolean r = cmdgroupCmdService.editCmdgroupCmd(cmdgroupCmd);
		result.accumulate("success", r);
		return result;
	}

	@RequestMapping("/delCmdgroupCmd")
	@ResponseBody
	public JSONObject delCmdgroupCmd(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = cmdgroupCmdService.delCmdgroupCmd(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/queryCmdGroupCmdByGroupId")
	@ResponseBody
	public JSONObject queryCmdGroupCmdByGroupId(@RequestParam(value = "group_id") Integer group_id,@RequestParam(value = "type") Integer type,
												@RequestParam(value = "sname") String sname, CmdgroupCmd cmdgroupCmd,
											 HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdgroupCmds = new ArrayList<Object>();
		ArrayList<CmdgroupCmd> cmdgroupCmds  = new ArrayList<CmdgroupCmd>();
		long total = 0;
		resultCmdgroupCmds = (ArrayList<Object>)cmdgroupCmdService.queryCmdGroupCmdByGroupId(group_id,sname,type,cmdgroupCmd,page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCmdgroupCmds)) {
			cmdgroupCmds = (ArrayList<CmdgroupCmd>)resultCmdgroupCmds.get(0);
			total = ((ArrayList<Long>) resultCmdgroupCmds.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(cmdgroupCmds);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}
}
