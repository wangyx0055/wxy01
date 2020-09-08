package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.*;
import com.longersec.blj.service.DepartmentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.service.CmdgroupCmdService;
import com.longersec.blj.service.CmdgroupService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdgroup")
public class CmdgroupController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private CmdgroupService cmdgroupService;
	@Autowired
	private CmdgroupCmdService cmdgroupCmdService;
	@Autowired
	private DepartmentService departmentService;

	/**
	 * @Description 命令集的搜索
	 * @param sname 输入框搜索的值
	 * @param type select的value值
	 * @author wxy
	 * @create 2020/3/31 15:43
	 **/
	@RequestMapping("/listCmdgroup")
	@ResponseBody
	public JSONObject listCmdgroup(Cmdgroup cmdgroup,
								   @RequestParam(value = "sname",required = false,defaultValue = "") String sname,
								   @RequestParam(value = "type",required = false)Integer type,HttpServletRequest request,
								   HttpSession session) {
		if (sname==null||sname.equals("")){ sname="";}
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdgroups = new ArrayList<Object>();
		ArrayList<Cmdgroup> cmdgroups = new ArrayList<Cmdgroup>();
		long total = 0;
		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Integer> depart_ids = new ArrayList<>();
		if (p_user.getRole_id().equals(5)){
			//获取所在的部门
			int depart_id = p_user.getDepartment();
			depart_ids = departmentService.selectById(depart_id);
			depart_ids.add(p_user.getDepartment());
		}
		resultCmdgroups = (ArrayList<Object>)cmdgroupService.findAll(cmdgroup, sname,type,page_start, page_length,depart_ids);
		if(CollectionUtils.isNotEmpty(resultCmdgroups)) {
			cmdgroups = (ArrayList<Cmdgroup>)resultCmdgroups.get(0);
			total = ((ArrayList<Long>) resultCmdgroups.get(1)).get(0);
		}

		for (Cmdgroup cmdgroup1 : cmdgroups) {
			if(cmdgroup1.getDepartment()!=0) {
				List<String> allParentName = departmentService.findAllParentName(cmdgroup1.getDepartment());
				StringBuilder stringBuilder = new StringBuilder();
				for (Object strings : allParentName) {
					stringBuilder.append(strings).append("/");
				}
				cmdgroup1.setTopName1(stringBuilder.substring(0, stringBuilder.length() - 1));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(cmdgroups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addCmdgroup")
	@ResponseBody
	public JSONObject addCmdgroup(Cmdgroup cmdgroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		String name = cmdgroup.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|\\_|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("success", false);
			result.accumulate("name", "命令集名不正确");
		}else {
			result.accumulate("success", true);
		}
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		cmdgroup.setDepartment(user.getDepartment());
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令集控制策略");
		operatorLog.setDetails("添加命令集控制策略");
		operatorLog.setContent("添加");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = cmdgroupService.addCmdgroup(cmdgroup);
//			if(cmdgroup.getId()>0) {
//				String[] cmds = cmdgroup.getCmd().split("\n");
//				for(int i=0; i<cmds.length; i++) {
//					CmdgroupCmd cmdgroupCmd = new CmdgroupCmd();
//					cmdgroupCmd.setGroup_id(cmdgroup.getId());
//					cmdgroupCmd.setCommand(cmds[i]);
//					cmdgroupCmdService.addCmdgroupCmd(cmdgroupCmd);
//				}
//			}
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editCmdgroup")
	@ResponseBody
	public JSONObject editCmdgroup(Cmdgroup cmdgroup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		String name = cmdgroup.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.accumulate("success", false);
			result.accumulate("name", "命令集名不正确");
		}else {
			result.accumulate("success", true);
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令集控制策略");
		operatorLog.setDetails("编辑命令集控制策略");
		operatorLog.setContent("编辑");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			cmdgroupCmdService.deletegroupcmd(cmdgroup.getId());
			Boolean r = cmdgroupService.editCmdgroup(cmdgroup);
			if(r&&cmdgroup.getId()>0) {
				String[] cmds = cmdgroup.getCmd().split("\n");
				for(int i=0; i<cmds.length; i++) {
					CmdgroupCmd cmdgroupCmd =new CmdgroupCmd();
					cmdgroupCmd.setGroup_id(cmdgroup.getId());
					cmdgroupCmd.setCommand(cmds[i]);
					cmdgroupCmdService.addCmdgroupCmd(cmdgroupCmd);
				}
			}
			result.accumulate("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/delCmdgroup")
	@ResponseBody
	public JSONObject delCmdgroup(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令集控制策略");
		operatorLog.setDetails("删除命令集控制策略");
		operatorLog.setContent("删除");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = cmdgroupService.delCmdgroup(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/checkname")
	@ResponseBody
	public JSONObject checkname(@RequestParam(value = "name") String name,@RequestParam(value = "id" ,required = false)Integer id){
		JSONObject result = new JSONObject();

		result.put("success", true);
		if (id==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			Cmdgroup cmdgroup = cmdgroupService.checkname(name);
			if (cmdgroup==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			Cmdgroup cmdgroup = cmdgroupService.checkname(name);
			if (cmdgroup==null){
				result.put("success", true);
			}else {
				if (cmdgroup.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}

		return result;
	}
}
