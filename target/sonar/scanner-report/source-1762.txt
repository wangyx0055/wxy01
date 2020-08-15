package com.longersec.blj.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.Group;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.*;
import com.longersec.blj.utils.UpdateDepartmentCount;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.Index;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.Device;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.Validator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController {

	@Autowired
	private GroupDeviceAccountService groupDeviceAccountService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceAccountService deviceAccountService;
	@Autowired
	private AccessPolicyDeviceAccountService accessPolicyDeviceAccountService;
	@Autowired
	private DepartmentService departmentService;
	private String ip;

	@RequestMapping("/listDevice")
	@ResponseBody
	public JSONObject listDevice(Device device,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultDevices = new ArrayList<Object>();
		ArrayList<Device> devices = new ArrayList<Device>();
		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Integer> depart_ids = new ArrayList<>();
		if (p_user.getRole_id().equals(5)){
			//获取所在的部门
			int depart_id = p_user.getDepartment();
			depart_ids = departmentService.selectById(depart_id);
			depart_ids.add(p_user.getDepartment());
		}
		long total = 0;
		if (device.getSearchAll()!=null&&device.getSearchAll().equals("")) {
			device.setSearchAll(null);
		}
		resultDevices = (ArrayList<Object>)deviceService.findAll(device, page_start, page_length,depart_ids);
		if(CollectionUtils.isNotEmpty(resultDevices)) {
			devices = (ArrayList<Device>)resultDevices.get(0);
			total = ((ArrayList<Long>) resultDevices.get(1)).get(0);
		}
		for (Device device1:devices) {
			List<String> allParentName = departmentService.findAllParentName(device1.getDepartment());
			StringBuilder stringBuilder = new StringBuilder();
			for (Object strings : allParentName) {
				stringBuilder.append(strings).append("/");
			}
			device1.setTopName(stringBuilder.substring(0,stringBuilder.length()-1));
		}
		JSONArray jsonArray = JSONArray.fromObject(devices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addDevice")
	@ResponseBody
	public JSONObject addDevice(@Validated Device device,@RequestParam(value = "super_password1",required = false)String super_password1,BindingResult errorResult,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("设备列表");
		operatorLog.setContent("添加");
		operatorLog.setDetails("添加设备["+device.getName()+"]["+device.getIp()+"]");
		result.put("success",true);
		String ip = device.getIp();
		Device _device = deviceService.checkip(ip);
		Device _device1 = deviceService.checkname(device.getName());

		User user = (User) SecurityUtils.getSubject().getPrincipal();
		device.setCreate_time((int) System.currentTimeMillis());
		device.setCreator_id(user.getId());
		if (_device1!=null){
			result.put("success",false);
			result.put("isName","设备名称重复");
		}

		if (_device!=null){
			result.put("success",false);
			result.put("isIp","IP地址重复");
		}

		if (!super_password1.equals(device.getSuper_password())){
			result.put("success",false);
			result.put("errorPwd","密码不一致");
		}

		if (resultMap==null && result.getBoolean("success")) {
			Boolean r = deviceService.addDevice(device);
			if (r) {
				List<Object> b1 = UpdateDepartmentCount.deviceUpdateDepartmentCount(departmentService,device.getDepartment(),1);
				result.put("success",true);
				operatorLog.setResult("成功");
				operatorLogService.addOperatorLog(operatorLog);
			}
		}else {
			result.put("success", false);
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
			if (resultMap!=null){
				result.put("errorMessage", resultMap);
			}
		}
		return result;
	}

	@RequestMapping("/editDevice")
	@ResponseBody
	public JSONObject editDevice(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("ip")String ip,
								 @RequestParam("os_type")int os_type,@RequestParam("department")int department,@RequestParam("description")String description,HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		Device device = new Device();
		device.setId(id);
		device.setName(name);
		device.setDepartment(department);
		device.setOs_type(os_type);
		device.setIp(ip);
		device.setDescription(description);
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("设备列表");
		operatorLog.setContent("编辑");
		operatorLog.setDetails("编辑设备["+device.getName()+"]["+device.getIp()+"]");
		result.put("success",true);
		Device _device = deviceService.checkip(ip);

		if (_device!=null && !_device.getId().equals(device.getId())){
			result.put("success",false);
			result.put("isIp","IP地址重复");
		}
		if (result.getBoolean("success")) {
			int selectOldDepartment = deviceService.selectOldDepartment(device.getId());
			Boolean r = deviceService.editDevice(device);
			if (r) {
				List<Object> objects1 = UpdateDepartmentCount.deviceUpdateDepartmentCount(departmentService, selectOldDepartment, -1);
				List<Object> objects = UpdateDepartmentCount.deviceUpdateDepartmentCount(departmentService, device.getDepartment(), 1);
				result.put("success",true);
				operatorLog.setResult("成功");
				operatorLogService.addOperatorLog(operatorLog);
			}
		}else {
			result.put("success", false);
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
		}
		return result;

	}

	@RequestMapping("/delDevice")
	@ResponseBody
	public JSONObject delDevice(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		OperatorLog operatorLog = Operator_log.log(request, session);
		operatorLog.setModule("设备列表");
		operatorLog.setContent("删除");
		result.put("success", true);
		String ips = "";
		for (int item : ids){
			//查询旧的部门id
			int selectOldDepartment = deviceService.selectOldDepartment(item);
			//更新部门设备数量
			UpdateDepartmentCount.deviceUpdateDepartmentCount(departmentService,selectOldDepartment,-1);
			 Device _device = deviceService.getById(item);
			 ips += _device.getName()+"("+_device.getIp()+"),";
		}
		if(_ids.isEmpty()) {
			result.put("success", false);
			result.put("msg", "id不能为空");
		}

		operatorLog.setDetails("删除设备["+ips.substring(0,ips.length()-1)+"]");
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			Boolean r = deviceService.delDevice(_ids);
			Boolean b = deviceAccountService.delByDevice(_ids);
			operatorLogService.addOperatorLog(operatorLog);
			result.put("success", r);
			UpdateDepartmentCount.AutoUpdateGroupDeviceCounts(groupService,groupDeviceAccountService);
		}else {
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);   
		}
		return result;
	}

	@RequestMapping("/checkip")
	@ResponseBody
	public JSONObject checkip(@RequestParam(value = "ip") String ip,@RequestParam(value = "id" ,required = false)Integer id){
		JSONObject result = new JSONObject();
		result.put("success", true);
		if (id==null){
			result.put("success", false);
		}
		if (!result.getBoolean("success")){
			Device _device = deviceService.checkip(ip);
			if (_device==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			Device _device = deviceService.checkip(ip);
			if (_device==null){
				result.put("success", true);
			}else {
				if (_device.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
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
			Device _device = deviceService.checkname(name);
			if (_device==null){
				result.put("success", true);
			}else {
				result.put("success", false);
			}
		}else {
			Device _device = deviceService.checkname(name);
			if (_device==null){
				result.put("success", true);
			}else {
				if (_device.getId().equals(id)){
					result.put("success", true);
				}else {
					result.put("success", false);
				}
			}
		}
		return result;
	}

	public static Map<String, Object> checkDeviceExport(DeviceService deviceService, String name, String ip, int osType, String description,
	                                                    String account,String password,int protocolId, int port, int sshKey){
		Map<String, Object> errorMap = new HashMap<>(16);
		ArrayList<Integer> list = new ArrayList<Integer>(10);
		list.add(26);list.add(32);list.add(44);list.add(45);list.add(46);list.add(51);
		Device checkName = deviceService.checkname(name);
		if (checkName != null) {
			errorMap.put("info",name+":用户名已经存在");
			errorMap.put("success", false);
			return errorMap;
		}
		if(name == null) {
			errorMap.put("info",  ":设备名称不能为空");
			errorMap.put("success", false);
			return errorMap;
		}
		if(!list.contains(osType)) {
			errorMap.put("info",  osType+":系统类型不存在");
			errorMap.put("success", false);
			return errorMap;
		}
		Device checkip = deviceService.checkip(ip);
		if (checkip != null) {
			errorMap.put("info",checkip+":设备地址重复");
			errorMap.put("success", false);
			return errorMap;
		}
		if (ip == null) {
			errorMap.put("info",name+":设备地址不能为空");
			errorMap.put("success", false);
			return errorMap;
		}
		int num = 120;
		if (description.length() > num) {
			errorMap.put("info",description+":描述超长");
			errorMap.put("success", false);
			return errorMap;
		}
		if (port<1 || port >65535) {
			errorMap.put("info",port+":不在端口范围");
			errorMap.put("success", false);
			return errorMap;
		}
		if (protocolId<1 && protocolId >6) {
			errorMap.put("info",protocolId+":该协议不存在");
			errorMap.put("success", false);
			return errorMap;
		}
		if (sshKey<0 && sshKey >1) {
			errorMap.put("info",protocolId+":该sshKey不存在");
			errorMap.put("success", false);
			return errorMap;
		}
		if (!"".equals(account)) {
			if (password.length()<1) {
				errorMap.put("info",name+":账号密码格式不正确");
				errorMap.put("success", false);
				return errorMap;
			}
		}
		if (!"".equals(password)) {
			if (account.length()<1) {
				errorMap.put("info",name+":账号密码格式不正确");
				errorMap.put("success", false);
				return errorMap;
			}
		}
		errorMap.put("success", true);
		return errorMap;
	}
}
