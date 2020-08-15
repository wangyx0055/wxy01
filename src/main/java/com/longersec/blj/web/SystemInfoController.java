package com.longersec.blj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longersec.blj.domain.AlertLog;
import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.domain.ConfigNetwork;
import com.longersec.blj.domain.EmailLog;
import com.longersec.blj.domain.SmsLog;
import com.longersec.blj.domain.SystemMessage;
import com.longersec.blj.domain.SystemUsage;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.AlertLogService;
import com.longersec.blj.service.ApppubAccountService;
import com.longersec.blj.service.ApppubProgramService;
import com.longersec.blj.service.ApppubRecordService;
import com.longersec.blj.service.ApppubService;
import com.longersec.blj.service.ConfigAlertLevelService;
import com.longersec.blj.service.ConfigNetworkService;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.DeviceRecordService;
import com.longersec.blj.service.DeviceService;
import com.longersec.blj.service.EmailLogService;
import com.longersec.blj.service.LoginLogService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.SmsLogService;
import com.longersec.blj.service.SystemMessageService;
import com.longersec.blj.service.SystemUsageService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.SystemCommandUtil;
import com.longersec.blj.utils.SystemUsageUtil;

import cn.hutool.system.SystemUtil;
import net.sf.json.JSONObject;

/**
 * 
 */
@Controller
@RequestMapping(value = "/systeminfo")
public class SystemInfoController {

	@Autowired
	ConfigAlertLevelService configAlertLevelService;
	
	@Autowired
	ConfigNetworkService configNetworkService;
	@Autowired
	UserService userService;
	@Autowired
	DeviceService deviceService;
	@Autowired
	ApppubService apppubService;
	@Autowired
	AlertLogService alertLogService;
	@Autowired
	LoginLogService loginLogService;
	@Autowired
	ApppubAccountService apppubAccountService;
	@Autowired
	DeviceRecordService deviceRecordService;
	@Autowired
	ApppubRecordService apppubRecordService;
	@Autowired
	ApppubProgramService apppubProgramService;
	@Autowired
	SystemMessageService systemMessageService;
	@Autowired
	EmailLogService emailLogService;
	@Autowired
	SmsLogService smsLogService;
	@Autowired
	ConfigService configService;
	@Autowired
	SystemUsageService systemUsageService;
	
	@RequestMapping("/all")
	@ResponseBody
	public JSONObject all(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		SystemUsageUtil systemUsageUtil = new SystemUsageUtil();
		ConfigNetwork configNetwork = configNetworkService.get();
		if(configNetwork!=null)
			result = systemUsageUtil.getAllInfo(configNetwork.getName());
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/checkInfo")
	@ResponseBody
	public JSONObject checkInfo(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		SystemUsageUtil systemUsageUtil = new SystemUsageUtil();
		ConfigNetwork configNetwork = configNetworkService.get();
		String ethx = configNetwork.getName();
		if(configNetwork!=null)
			result = systemUsageUtil.getAllInfo(ethx);
		List<Object> rsList = configAlertLevelService.findAll(null, 0, 1);
		String ip = configNetworkService.getNameByInterface(ethx, "ipaddr"); 
		ConfigAlertLevel configAlertLevel = null;
			configAlertLevel = ((List<ConfigAlertLevel>)rsList.get(0)).get(0);
			User user = userService.checkLogin("admin");
			AlertLog alertLog = new AlertLog();
			SystemUsage systemUsage = new SystemUsage();
			systemUsage.setType("cpu");
			systemUsage.setGet_datetime(new SimpleDateFormat("yyy-MM-dd HH:mm:00").format(new Date()));
			systemUsage.setTotal(result.getLong("cpu_total"));
			systemUsage.setValue(result.getLong("cpu_used"));
			Double cpu = result.getDouble("cpu_userate")*100;
			systemUsage.setPercent(cpu.intValue());
			systemUsageService.addSystemUsage(systemUsage);
			systemUsage.setType("memory");
			systemUsage.setGet_datetime(new SimpleDateFormat("yyy-MM-dd HH:mm:00").format(new Date()));
			systemUsage.setTotal(result.getLong("memory_total"));
			systemUsage.setValue(result.getLong("memory_used"));
			Double memory = result.getDouble("memory_userate")*100;
			systemUsage.setPercent(memory.intValue());
			systemUsageService.addSystemUsage(systemUsage);
			systemUsage.setType("disk");
			systemUsage.setGet_datetime(new SimpleDateFormat("yyy-MM-dd HH:mm:00").format(new Date()));
			systemUsage.setTotal(result.getLong("disk_total"));
			systemUsage.setValue(result.getLong("disk_used"));
			Double disk = result.getDouble("disk_userate")*100;
			systemUsage.setPercent(disk.intValue());
			systemUsageService.addSystemUsage(systemUsage);
			
			if(result.getDouble("cpu_userate")*100>=configAlertLevel.getCpu_max()) {
				if(configAlertLevel.getCpu_max_msg().charAt(0)=='1') {//system messages
					SystemMessage systemMessage = new SystemMessage();
					systemMessage.setTitle("CPU超过阈值");
					systemMessage.setContent("CPU:"+(int) (result.getDouble("cpu_userate")*100)+"%,阈值:"+configAlertLevel.getCpu_max()+"%");
					systemMessage.setLevel(configAlertLevel.getCpu_max_level());
					systemMessageService.addSystemMessage(systemMessage);

					alertLog.setCommand("CPU:"+(int) (result.getDouble("cpu_userate")*100)+"%,阈值:"+configAlertLevel.getCpu_max()+"%");
					alertLog.setDangerlevel(configAlertLevel.getCpu_max_level());
					alertLog.setOperate_datetime(Long.toString((new Date().getTime()/1000)));
					alertLog.setUsername("System");
					alertLog.setRealname("系统");
					alertLog.setProtocol("system");
					alertLog.setDevice_account("system");
					alertLog.setSource_ip(ip);
					alertLog.setDevice_ip(ip);
					alertLogService.addAlertLog(alertLog);
				}
				if(configAlertLevel.getCpu_max_msg().charAt(1)=='1') {//email
					EmailLog emailLog = new EmailLog();
					emailLog.setAlert_id(alertLog.getId());
					emailLog.setTo_title("CPUsage Alarm");
					emailLog.setTo_contents("CPU超过阈值,设备地址:"+ip+",CPU:"+(int) (result.getDouble("cpu_userate")*100)+"%,阈值:"+configAlertLevel.getCpu_max()+"%");
					emailLog.setTo_users(user.getEmail());
					emailLogService.addEmailLog(emailLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendEmail").getValue()+" "+emailLog.getId());
				}
				if(configAlertLevel.getCpu_max_msg().charAt(2)=='1') {//sms
					SmsLog smsLog = new SmsLog();
					smsLog.setAlert_id(alertLog.getId());
					smsLog.setContents("CPU超过阈值,设备地址:"+ip+"CPU:"+(int) (result.getDouble("cpu_userate")*100)+"%,阈值:"+configAlertLevel.getCpu_max()+"%");
					smsLog.setMobile(user.getMobile());
					smsLogService.addSmsLog(smsLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendSms").getValue()+" "+smsLog.getId());
				}
			}
			if(result.getDouble("memory_userate")*100>=configAlertLevel.getMemory_max()) {
				if(configAlertLevel.getMemory_max_msg().charAt(0)=='1') {//system messages
					SystemMessage systemMessage = new SystemMessage();
					systemMessage.setTitle("内存超过阈值");
					systemMessage.setContent("内存:"+(int) (result.getDouble("memory_userate")*100)+"%,阈值:"+configAlertLevel.getMemory_max()+"%");
					systemMessage.setLevel(configAlertLevel.getCpu_max_level());
					systemMessageService.addSystemMessage(systemMessage);

					alertLog.setCommand("内存:"+(int) (result.getDouble("memory_userate")*100)+"%,阈值:"+configAlertLevel.getMemory_max()+"%");
					alertLog.setDangerlevel(configAlertLevel.getCpu_max_level());
					alertLog.setOperate_datetime(Long.toString((new Date().getTime()/1000)));
					alertLog.setUsername("System");
					alertLog.setRealname("系统");
					alertLog.setProtocol("system");
					alertLog.setDevice_account("system");
					alertLog.setSource_ip(ip);
					alertLog.setDevice_ip(ip);
					alertLogService.addAlertLog(alertLog);
				}
				if(configAlertLevel.getMemory_max_msg().charAt(1)=='1') {//email
					EmailLog emailLog = new EmailLog();
					emailLog.setAlert_id(alertLog.getId());
					emailLog.setTo_title("MemoryUsage Alarm");
					emailLog.setTo_contents("内存超过阈值,设备地址:"+ip+"内存:"+(int) (result.getDouble("memory_userate")*100)+"%,阈值:"+configAlertLevel.getMemory_max()+"%");
					emailLog.setTo_users(user.getEmail());
					emailLogService.addEmailLog(emailLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendEmail").getValue()+" "+emailLog.getId());
				}
				if(configAlertLevel.getMemory_max_msg().charAt(2)=='1') {//sms
					SmsLog smsLog = new SmsLog();
					smsLog.setAlert_id(alertLog.getId());
					smsLog.setContents("内存超过阈值,设备地址:"+ip+"内存:"+(int) (result.getDouble("memory_userate")*100)+"%,阈值:"+configAlertLevel.getMemory_max()+"%");
					smsLog.setMobile(user.getMobile());
					smsLogService.addSmsLog(smsLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendSms").getValue()+" "+smsLog.getId());
				}
			}
			if(result.getDouble("disk_userate")*100>=configAlertLevel.getDisk_max()) {
				if(configAlertLevel.getDisk_max_msg().charAt(0)=='1') {//system messages
					SystemMessage systemMessage = new SystemMessage();
					systemMessage.setTitle("磁盘超过阈值");
					systemMessage.setContent("磁盘:"+(int) (result.getDouble("disk_userate")*100)+"%,阈值:"+configAlertLevel.getDisk_max()+"%");
					systemMessage.setLevel(configAlertLevel.getCpu_max_level());
					systemMessageService.addSystemMessage(systemMessage);

					alertLog.setCommand("磁盘:"+(int) (result.getDouble("disk_userate")*100)+"%,阈值:"+configAlertLevel.getDisk_max()+"%");
					alertLog.setDangerlevel(configAlertLevel.getCpu_max_level());
					alertLog.setOperate_datetime(Long.toString((new Date().getTime()/1000)));
					alertLog.setUsername("System");
					alertLog.setRealname("系统");
					alertLog.setProtocol("system");
					alertLog.setDevice_account("system");
					alertLog.setSource_ip(ip);
					alertLog.setDevice_ip(ip);
					alertLogService.addAlertLog(alertLog);
				}
				if(configAlertLevel.getDisk_max_msg().charAt(1)=='1') {//email
					EmailLog emailLog = new EmailLog();
					emailLog.setAlert_id(alertLog.getId());
					emailLog.setTo_title("DiskUsage Alarm");
					emailLog.setTo_contents("磁盘超过阈值,设备地址:"+ip+"磁盘:"+(int) (result.getDouble("disk_userate")*100)+"%,阈值:"+configAlertLevel.getDisk_max()+"%");
					emailLog.setTo_users(user.getEmail());
					emailLogService.addEmailLog(emailLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendEmail").getValue()+" "+emailLog.getId());
				}
				if(configAlertLevel.getDisk_max_msg().charAt(2)=='1') {//sms
					SmsLog smsLog = new SmsLog();
					smsLog.setAlert_id(alertLog.getId());
					smsLog.setContents("磁盘超过阈值,设备地址:"+ip+"磁盘:"+(int) (result.getDouble("disk_userate")*100)+"%,阈值:"+configAlertLevel.getDisk_max()+"%");
					smsLog.setMobile(user.getMobile());
					smsLogService.addSmsLog(smsLog);
					
					SystemCommandUtil.execCmd(configService.getByName("lsbljSendSms").getValue()+" "+smsLog.getId());
				}
			}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/sysdata")
	@ResponseBody
	public JSONObject sysdata(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("userct",userService.total());
		result.put("devicect", deviceService.total());
		result.put("apppubct", apppubService.total());
		result.put("alertct", alertLogService.total());
		result.put("appprogramct", apppubProgramService.total());
		
		result.put("visitByDay", loginLogService.selectLast7Day());
		result.put("visitByUser", loginLogService.selectByUser());
		
		result.put("devicesByType", deviceService.totalByDeviceType());
		result.put("apppubByProgram", apppubAccountService.totalByProgram());
		
		result.put("device30DayTextIncrease", deviceRecordService.get30DayTextIncrease());
		result.put("device30DayGraphIncrease", deviceRecordService.get30DayGraphIncrease());
		result.put("apppub30DayIncrease", apppubRecordService.get30DayIncrease());
		result.put("getTextTotal", deviceRecordService.getTextTotal());
		result.put("getGraphTotal", deviceRecordService.getGraphTotal());
		result.put("getApppubRecordTotal", apppubRecordService.getApppubRecordTotal());
		
		return result;
	}
}
