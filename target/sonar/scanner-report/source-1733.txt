package com.longersec.blj.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.Device;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.CmdPolicy;
import com.longersec.blj.domain.CmdPolicyCmd;
import com.longersec.blj.domain.CmdgroupCmd;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.CmdPolicyCmdgroupService;
import com.longersec.blj.service.CmdPolicyDeviceAccountService;
import com.longersec.blj.service.CmdPolicyGroupService;
import com.longersec.blj.service.CmdPolicyService;
import com.longersec.blj.service.CmdPolicyUserService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/cmdPolicy")
public class CmdPolicyController {
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private CmdPolicyService cmdPolicyService;
	
	@Autowired
	private CmdPolicyUserService cmdPolicyUserService;
	
	@Autowired
	private CmdPolicyDeviceAccountService cmdPolicyDeviceAccountService;
	
	@Autowired
	private CmdPolicyCmdgroupService cmdPolicyCmdgroupService;

	@Autowired
	private CmdPolicyGroupService cmdPolicyGroupService;
	
	private static String id=null;
	/**
	 * @Description 命令集的搜索
	 * @param sname 输入框搜索的值
	 * @param type select的value值
	 * @param cot 执行动作的值
	 * @param stat 状态的值
	 * @author wxy
	 * @create 2020/3/31 15:43
	 **/
	@RequestMapping("/listCmdPolicy")
	@ResponseBody
	public JSONObject listCmdPolicy(CmdPolicy cmdPolicy,
									@RequestParam(value = "sname",required = false,defaultValue = "") String sname,
									@RequestParam(value = "type",required = false) Integer type,
									@RequestParam(value = "cot",required = false,defaultValue = "") String cot,
									@RequestParam(value = "stat",required = false,defaultValue = "") String stat,
									HttpServletRequest request, HttpSession session) {
		if (sname==null||sname.equals("")) { sname="";}
		if (stat==null||stat.equals("")) { stat="";}
		if (cot==null||cot.equals("")) { cot="";}
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultCmdPolicys = new ArrayList<Object>();
		ArrayList<CmdPolicy> cmdPolicys = new ArrayList<CmdPolicy>();
		long total = 0;
		resultCmdPolicys = (ArrayList<Object>)cmdPolicyService.findAll(cmdPolicy,sname,type,stat,cot,page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultCmdPolicys)) {
			cmdPolicys = (ArrayList<CmdPolicy>)resultCmdPolicys.get(0);
			total = ((ArrayList<Long>) resultCmdPolicys.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(cmdPolicys);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/editStatus")
	@ResponseBody
	public JSONObject editStatus(@RequestParam(value = "status")Integer status, @RequestParam(value = "id")Integer id){
		JSONObject result = new JSONObject();
		Boolean r = cmdPolicyService.editStatus(status,id);
		if (r){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping("/addCmdPolicy")
	@ResponseBody
	public JSONObject addCmdPolicy(@RequestParam(value="usergroup[]", required=false) Integer[] _usergroup,@RequestParam(value="devicegroup[]", required=false) Integer[] _devicegroup,@RequestParam(value="cmdgroup[]", required=false) Integer[] _cmdgroup,@RequestParam(value="device[]", required=false) Integer[] _device,@RequestParam(value="user[]", required=false) Integer[] _user, HttpServletRequest request, HttpSession session, CmdPolicy cmdPolicy) {
		List<Integer> user 			=  	_user==null?null:Arrays.asList(_user);
		List<Integer> device 		=  	_device==null?null:Arrays.asList(_device);	
		List<Integer> cmdgroup 		= 	_cmdgroup==null?null:Arrays.asList(_cmdgroup);
		List<Integer> usergroup 	= 	_usergroup==null?null:Arrays.asList(_usergroup);
		List<Integer> devicegroup 	= 	_devicegroup==null?null:Arrays.asList(_devicegroup);
		JSONObject result = new JSONObject();
		CmdPolicy _cmdPolicy = cmdPolicyService.checkName(cmdPolicy.getName());
		String name = cmdPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.put("success", false);
			result.put("name", "策略名不正确");
		}else {
			result.put("success", true);
			if (_cmdPolicy!=null){
				result.put("success",false);
				result.put("name","名称重复");
			}
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令控制策略");
		operatorLog.setDetails("添加命令控制策略["+cmdPolicy.getName()+"]");
		operatorLog.setContent("添加");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
				cmdPolicyService.addCmdPolicy(cmdPolicy);
			if(user!=null)
				cmdPolicyUserService.addCmdPolicyUser(cmdPolicy.getId(), user);
			if(device!=null)
				cmdPolicyDeviceAccountService.addCmdPolicyDeviceAccount(cmdPolicy.getId(), device);
			if(cmdgroup!=null)
				cmdPolicyCmdgroupService.addCmdPolicyCmdgroup(cmdPolicy.getId(), cmdgroup);
			if(usergroup!=null|| devicegroup!=null)
				cmdPolicyGroupService.addCmdPolicyGroup(cmdPolicy.getId(), usergroup,devicegroup);
			if(cmdPolicy.getCmd()!=null&&!cmdPolicy.getCmd().isEmpty()) {
				String[] cmds = cmdPolicy.getCmd().split("\n");
				for(int i=0; i<cmds.length; i++) {
					CmdPolicyCmd cmdPolicyCmd2 = new CmdPolicyCmd();
					cmdPolicyCmd2.setPolicy_id(cmdPolicy.getId());
					cmdPolicyCmd2.setCommand(cmds[i]);
					cmdPolicyService.addCmdPolicyCmd(cmdPolicyCmd2);
				}
			}
			result.put("success", cmdPolicy.getId()>0?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;
	}

	@RequestMapping("/editCmdPolicy")
	@ResponseBody
	public JSONObject editCmdPolicy(CmdPolicy cmdPolicy, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		CmdPolicy _cmdPolicy = cmdPolicyService.checkName(cmdPolicy.getName());
		String name = cmdPolicy.getName();
		String regex = "^([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]){1,64}$";
		if (!name.matches(regex)) {
			result.put("success", false);
			result.put("name", "策略名不正确");
		}else {
			result.put("success", true);

			if (_cmdPolicy!=null&& !_cmdPolicy.getId().equals(cmdPolicy.getId())){
				result.put("success",false);
				result.put("name","策略名称重复");
			}
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令控制策略");
		operatorLog.setDetails("编辑命令控制策略["+cmdPolicy.getName()+"]");
		operatorLog.setContent("编辑");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = cmdPolicyService.editCmdPolicy(cmdPolicy);
			result.put("success", r?true:false);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		System.out.println(cmdPolicy.getLongtime());
		return result;
	}

	//编辑关联命令
	@RequestMapping("/editCmdPolicyCmd")
	@ResponseBody
	public JSONObject editCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		cmdPolicyService.clearCmdPolicyCmd(cmdPolicyCmd);
		Boolean r = true;
		if(cmdPolicyCmd.getPolicy_id()>0) {
			String[] cmds = cmdPolicyCmd.getCommand().split("\n");
			for(int i=0; i<cmds.length; i++) {
				CmdPolicyCmd cmdPolicyCmd2 = new CmdPolicyCmd();
				cmdPolicyCmd2.setPolicy_id(cmdPolicyCmd.getPolicy_id());
				cmdPolicyCmd2.setCommand(cmds[i]);
				if(!cmdPolicyService.addCmdPolicyCmd(cmdPolicyCmd2)) r=false;
			}
		}
		//操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		CmdPolicy cmdPolicy = cmdPolicyService.getById(cmdPolicyCmd.getPolicy_id());
		operatorLog.setModule("命令控制策略");
		operatorLog.setDetails("编辑命令控制策略关联命令["+CmdPolicyCmd.getCommand()+"]");
		operatorLog.setContent("编辑");
		//是否操作成功
		if(r) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			result.put("success", true);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping("/delCmdPolicy")
	@ResponseBody
	public JSONObject delCmdPolicy(@RequestParam(value = "ids[]") String[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<String> _ids =  Arrays.asList(ids);
		result.put("success", true);
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}
		String policies = "";
		for (String _id : ids){
			CmdPolicy cmdPolicy = cmdPolicyService.getById(Integer.valueOf(_id));
			policies += cmdPolicy.getName()+",";
		}
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("命令控制策略");
		operatorLog.setDetails("删除命令控制策略["+policies.substring(0,policies.length()-1)+"]");
		operatorLog.setContent("删除");
		//是否操作成功
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
			Boolean r = cmdPolicyService.delCmdPolicy(_ids);
			result.put("success", r);
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
			CmdPolicy checkname = cmdPolicyService.checkName(name);
			if (checkname==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			CmdPolicy checkname = cmdPolicyService.checkName(name);
			if (checkname==null){
				result.put("success", true);
			}else {
				if (checkname.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}

		return result;
	}
}
