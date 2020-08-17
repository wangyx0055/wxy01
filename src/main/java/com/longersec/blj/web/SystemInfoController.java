package com.longersec.blj.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.longersec.blj.domain.AlertLog;
import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.domain.ConfigNetwork;
import com.longersec.blj.domain.EmailLog;
import com.longersec.blj.domain.SmsLog;
import com.longersec.blj.domain.SystemMessage;
import com.longersec.blj.domain.SystemUsage;
import com.longersec.blj.domain.User;
import com.longersec.blj.license.License;
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
import com.longersec.blj.license.License;

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
	
	@RequestMapping("/version")
	@ResponseBody
	public String version() {
		String version = "";
		try {
			InputStream stream = new FileInputStream(this.getClass().getResource("/config.properties").getPath());
			Properties pro = new Properties();
			pro.load(stream);
			version = pro.getProperty("blj.version");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return version;
	}
	
	@RequestMapping("/applyLicense")
	public void applyLicense(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		String uuid = "";
		License l = new License();
        uuid = l.LicenseGetUuid();
        System.out.println(uuid);
        response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("content-disposition","attachment;fileName=license.req");
		try {
			ServletOutputStream out = response.getOutputStream();
			out.write(uuid.getBytes(),0,uuid.length());
	        out.close();
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/uploadLicense")
	@ResponseBody
	public void uploadLicense(@RequestParam MultipartFile licenseFile, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ServletOutputStream out;
		Config config = configService.getByName("licensepath");
		String filenameString = config.getValue()+"/license";
		try {
			out = response.getOutputStream();
			File destFile = new File(filenameString);
			if(!licenseFile.getOriginalFilename().equals("license")) {
				out.write("<script>window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			FileUtils.copyFile(new File(filenameString), new File("/tmp/license"));
			FileUtils.copyInputStreamToFile(licenseFile.getInputStream(), destFile);
			if(!destFile.exists()) {
				FileUtils.copyFile(new File("/tmp/license"), new File(filenameString));
				out.write("<script>window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"复制文件出错!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			License l = new License();
			if(!l.LicenseCheckUuid("")) {
				FileUtils.copyFile(new File("/tmp/license"), new File(filenameString));
				out.write("<script>window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"License文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			out.write("<script>window.parent.licenseInfo();window.parent.$('#modal-updateLicense').modal('hide');window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-success .modal-title\").text('成功');window.parent.$(\"#modal-success .modal-body\").text(\"操作成功!\");window.parent.$(\"#modal-success\").modal();</script>".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@RequestMapping("/licenseinfo")
	@ResponseBody
	public JSONObject licenseinfo() {
		JSONObject result = new JSONObject();
		License l = new License();
		if(l.LicenseCheckUuid("")) {
			result.put("versiontyp", "已授权");
			result.put("productid", l.LicenseGetUuid());
			result.put("name", l.LicenseGetName());
			result.put("devices", l.LicenseGetDevices());
			result.put("endtimestr", l.LicenseGetTimestampToHuman().substring(0,l.LicenseGetTimestampToHuman().indexOf(" ")));
			result.put("endtime", l.LicenseGetEndTimestamp());
		}else {
			result.put("versiontyp", "未授权");
			result.put("productid", "");
			result.put("name", "");
			result.put("devices", 3);
			result.put("endtimestr", "");
			result.put("endtime", "");
		}
        System.out.println(l.LicenseCheckUuid(""));
        System.out.println(l.LicenseGetName());
        System.out.println(l.LicenseGetDevices());
        System.out.println(l.LicenseGetEndTimestr());
        System.out.println(l.LicenseGetEndTimestamp());
        
		return result;
	}
}
