package com.longersec.blj.web;

import com.longersec.blj.domain.*;
import com.longersec.blj.service.ApppubAccountService;
import com.longersec.blj.service.ConfigPasswordEncryptKeyService;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.ExportService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.Sm4Utils;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private DeviceAccountService deviceAccountService;
    
    @Autowired
    private ApppubAccountService apppubAccountService;
    
    @Autowired
    private OperatorLogService operatorLogService;
	
	@Autowired
	private ConfigPasswordEncryptKeyService configPasswordEncryptKeyService;


    @RequestMapping(value = "/operatorLog", method = RequestMethod.GET)
    public void operatorLog( HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_operatorLog();
            String filename = "operatorLog-"+formatter.format(t)+".csv";
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("操作日志");
			operatorLog.setDetails("导出操作日志:"+"operatorLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("操作日志");
			operatorLog.setDetails("导出操作日志:"+"operatorLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
        }
    }

    @RequestMapping(value = "/loginLog", method = RequestMethod.GET)
    public void loginLog( HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_loginLog();
            String filename = "loginLog-"+formatter.format(t)+".csv";
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("登录日志");
			operatorLog.setDetails("导出登录日志:"+"loginLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("登录日志");
			operatorLog.setDetails("导出登录日志:"+"loginLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
        }
    }

    @RequestMapping(value = "/AlertLog", method = RequestMethod.GET)
    public void AlertLog( HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_AlertLog();
            String filename = "AlertLog-"+formatter.format(t)+".csv";
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("告警日志");
			operatorLog.setDetails("导出告警日志:"+"AlertLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("告警日志");
			operatorLog.setDetails("导出告警日志:"+"AlertLog-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("失败");
			operatorLogService.addOperatorLog(operatorLog);
        }
    }

    @RequestMapping(value = "/exprotUser", method = RequestMethod.GET)
    public void exprotUser( HttpServletResponse response) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_user();
            String filename = "user-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exprotUserGroup", method = RequestMethod.GET)
    public void exprotUserGroup(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_group(0);
            String filename = "userGroup-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }
    @RequestMapping(value = "/exprotDeviceGroup", method = RequestMethod.GET)
    public void exprotDeviceGroup(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_group(1);
            String filename = "deviceGroup-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exprotDevice", method = RequestMethod.GET)
    public void exprotDevice(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_device();
            String filename = "device-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }
//部门
   @RequestMapping(value = "/exprotDepartment", method = RequestMethod.GET)
   public void exprotDepartment(HttpServletResponse response, HttpServletRequest request) throws IOException {
    Date t = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    try {
        //创建临时csv文件
        File tempFile = exportService.createTempFile_department();
        String filename = "department-"+formatter.format(t)+".csv";
        //输出csv流文件，提供给浏览器下载
        outCsvStream(response, tempFile,filename);
        //删除临时文件
        deleteFile(tempFile);
    } catch (IOException e) {
        System.out.println("导出失败");
    }
}
//renwu
@RequestMapping(value = "/exprotOperation", method = RequestMethod.GET)
public void exprotOperation(HttpServletResponse response, HttpServletRequest request) throws IOException {
    Date t = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    try {
        //创建临时csv文件
        File tempFile = exportService.createTempFile_operation();
        String filename = "exprotOperation-"+formatter.format(t)+".csv";
        //输出csv流文件，提供给浏览器下载
        outCsvStream(response, tempFile,filename);
        //删除临时文件
        deleteFile(tempFile);
    } catch (IOException e) {
        System.out.println("导出失败");
    }
}
    @RequestMapping(value = "/exprotAccessPolicy", method = RequestMethod.GET)
    public void exprotAccessPolicy(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_AccessPolicy();
            String filename = "AccessPolicy-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }


    @RequestMapping(value = "/exprotDeviceRecord", method = RequestMethod.GET)
    public void exprotDeviceRecord(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_DeviceRecord();
            String filename = "DeviceRecord-"+formatter.format(t)+".csv";
            OperatorLog operatorLog =Operator_log.log(request, session);
			operatorLog.setModule("主机审计");
			operatorLog.setDetails("导出主机审计:"+"DeviceRecord-"+formatter.format(t)+".csv");
			operatorLog.setContent("导出");
			operatorLog.setResult("成功");
			operatorLogService.addOperatorLog(operatorLog);
            
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }
//exprotApppubServer
@RequestMapping(value = "/exprotApppubServer", method = RequestMethod.GET)
public void exprotApppubServer(HttpServletResponse response, HttpServletRequest request) throws IOException {
    Date t = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    try {
        //创建临时csv文件
        File tempFile = exportService.createTempFile_AppubServe();
        String filename = "exprotApppubServer-"+formatter.format(t)+".csv";
        //输出csv流文件，提供给浏览器下载
        outCsvStream(response, tempFile,filename);
        //删除临时文件
        deleteFile(tempFile);
    } catch (IOException e) {
        System.out.println("导出失败");
    }
}
//exprotApppubApp
@RequestMapping(value = "/exprotApppubApp", method = RequestMethod.GET)
public void exprotApppubApp(HttpServletResponse response, HttpServletRequest request) throws IOException {
    Date t = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    try {
        //创建临时csv文件
        File tempFile = exportService.createTempFile_AppubApp();
        String filename = "exprotApppubApp-"+formatter.format(t)+".csv";
        //输出csv流文件，提供给浏览器下载
        outCsvStream(response, tempFile,filename);
        //删除临时文件
        deleteFile(tempFile);
    } catch (IOException e) {
        System.out.println("导出失败");
    }
}
    //exprotApppubProgram
    @RequestMapping(value = "/exprotApppubProgram", method = RequestMethod.GET)
    public void exprotApppubProgram(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_ApppubProgram();
            String filename = "exprotApppubProgram-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }
 //changePassword
    @RequestMapping(value = "/exprotPassword", method = RequestMethod.GET)
    public void exprotPassword(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_Password();
            String filename = "exprotPassword-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exprotPassword2", method = RequestMethod.GET)
    public void exprotPassword2(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_Password2();
            String filename = "exprotAppPassword-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportProtocolLoginlog", method = RequestMethod.GET)
    public void exportProtocolLoginlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportProtocolLoginlog(interval, start_date, end_date);
            String filename = "reportProtocolLoginlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportUserLoginlog", method = RequestMethod.GET)
    public void exportUserLoginlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportUserLoginlog(interval, start_date, end_date);
            String filename = "reportUserLoginlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportFailLoginlog", method = RequestMethod.GET)
    public void exportFailLoginlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportFailLoginlog(interval, start_date, end_date);
            String filename = "reportSuccessFailLoginlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportTimeRecordReportlog", method = RequestMethod.GET)
    public void exportTimeRecordReportlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportRecordlog(interval, start_date, end_date);
            String filename = "reportRecordlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportTimeLongDeviceRecordReportlog", method = RequestMethod.GET)
    public void exportTimeLongDeviceRecordReportlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportTimeLongDeviceRecordlog(interval, start_date, end_date);
            String filename = "reportTimeLongRecordlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportTimeLongUserRecordReportlog", method = RequestMethod.GET)
    public void exportTimeLongUserRecordReportlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportTimeLongUserRecordlog(interval, start_date, end_date);
            String filename = "reportTimeLongRecordlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportCommandRecordReportlog", method = RequestMethod.GET)
    public void exportCommandReportlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportCommandlog(interval, start_date, end_date);
            String filename = "reportCommandlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportAlertRecordReportlog", method = RequestMethod.GET)
    public void exportAlertReportlog(String interval,String start_date, String end_date, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportAlertlog(interval, start_date, end_date);
            String filename = "reportAlertlog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportDevicePriorityReport", method = RequestMethod.GET)
    public void exportDevicePriorityReport(DeviceAccount deviceAccount, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportSystemPriority(deviceAccount);
            String filename = "reportDevicePrioritylog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/exportApppubPriorityReport", method = RequestMethod.GET)
    public void exportApppubPriorityReport(ApppubAccount apppubAccount, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportApppubPriority(apppubAccount);
            String filename = "reportApppubPrioritylog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }
    //执行日志导出
    @RequestMapping(value = "/exportCrontabCommandLogReport", method = RequestMethod.GET)
    public void exportCrontabCommandLogReport(@RequestParam("id") Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportService.createTempFile_reportCrontabCommandLog(id);
            String filename = "reportCrontabCommandLog-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkPwd(@RequestParam(value = "password" ,required = false)String password, HttpServletResponse response, HttpSession session) throws IOException {
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("msg", "");
        if (password.equals("")){
            result.put("success", false);
            result.put("msg", "密码不能为空");
        }else {
            User current_u = (User) SecurityUtils.getSubject().getPrincipal();
            User _user = userService.checkLogin(current_u.getUsername());
            _user.setPassword(Sm4Utils.decryptEcb(configPasswordEncryptKeyService.getKey(), _user.getPassword()));
            if (password.equals(_user.getPassword())  && result.getBoolean("success")){
                result.put("success", true);
            }else {
                result.put("success", false);
                result.put("msg","密码错误");
            }
        }

        return result;

    }
  //密码查看1
    @RequestMapping(value = "/checkDevicePa",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkDevicePa(@RequestParam("username") String username, @RequestParam("password") String password, Integer device_account_id, Integer app_account_id, HttpServletResponse response, HttpSession session) throws IOException {
        JSONObject result = new JSONObject();
        result.put("success", true);
        if (password.equals("")){
            result.put("success", false);
            result.put("msg1", "请输入管理员密码");
        }
        if (username.equals("")){
            result.put("success", false);
            result.put("msg2", "请输入管理员ID");
        }

        if (result.getBoolean("success")){
            //User current_u = (User) SecurityUtils.getSubject().getPrincipal();
            User _user = userService.checkLogin(username);
            _user.setPassword(Sm4Utils.decryptEcb(configPasswordEncryptKeyService.getKey(), _user.getPassword()));
            if (password.equals(_user.getPassword())){
            	if(device_account_id!=null) {
            		DeviceAccount device_account = deviceAccountService.getById(device_account_id);
                	result.put("password", device_account.getPassword());
            	}
            	else if(app_account_id!=null) {
            		ApppubAccount apppub_account = apppubAccountService.getById(app_account_id);
                	result.put("password", apppub_account.getPassword());
            	}
                result.put("success", true);
            }else {
                result.put("success", false);
                result.put("msg1","管理员密码不正确");
            }
            if (!username.equals(_user.getUsername())){
                result.put("success", false);
                result.put("msg2", "管理员ID不正确");
            }else {
                result.put("success", true);
            }
        }else {
            result.put("success", false);
        }


        return result;
        }

    /**
    /**
     * 写入csv结束，写出流
     */
    public void outCsvStream(HttpServletResponse response,File tempFile ,String filename) throws IOException {
        java.io.OutputStream out = response.getOutputStream();
        byte[] b = new byte[10240];
        java.io.File fileLoad = new java.io.File(tempFile.getCanonicalPath());
        response.reset();
        response.setContentType("application/csv");
        response.setHeader("content-disposition", "attachment; filename="+filename);
        java.io.FileInputStream in = new java.io.FileInputStream(fileLoad);
        int n;
        //为了保证excel打开csv不出现中文乱码
        out.write(new   byte []{( byte ) 0xEF ,( byte ) 0xBB ,( byte ) 0xBF });
        while ((n = in.read(b)) != -1) {
            //每次写入out1024字节
            out.write(b, 0, n);
        }
        response.setContentType("text/csv; charset=\"utf-8\"");
        in.close();
        out.close();

    }


    /**
     * 删除单个文件
     *
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile( File file) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
