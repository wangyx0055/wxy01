package com.longersec.blj.service.impl;

import com.csvreader.CsvWriter;
import com.longersec.blj.dao.*;
import com.longersec.blj.domain.*;
import com.longersec.blj.service.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ExportServiceImpl implements ExportService {
    @Autowired
    private OperatorLogDao operatorLogDao;
    @Autowired
    private LoginLogDao loginLogDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private DeviceTypeDao deviceTypeDao;
    @Autowired
    private AccessPolicyDao accessPolicyDao;
    @Autowired
    private DeviceRecordDao deviceRecordDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ApppubServerDao ApppubServerDao;
    @Autowired
    private ApppubProgramDao ApppubProgramDao;
    @Autowired
    private CrontabScriptConfigDao CrontabScriptConfigDao;
    @Autowired
    private ApppubAccountDao ApppubAccountDao;
    @Autowired
	private LoginLogService loginLogService;
    @Autowired
	private DeviceRecordService deviceRecordService;
    @Autowired
	private DeviceAccountService deviceAccountService;
    @Autowired
	private ApppubAccountService apppubAccountService;
    @Autowired
    private CrontabCommandLogService crontabCommandLogService;

    @Override
    public File createTempFile_operatorLog() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"时间","来源ip","用户名","姓名","部门","日志内容","状态","结果","模块","备注"};
        csvWriter.writeRecord(headers);
        ArrayList<OperatorLog> datas = (ArrayList<OperatorLog>) operatorLogDao.selectAll();
        for (OperatorLog data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getOperate_datetime());
            csvWriter.write(data.getSource_ip());
            csvWriter.write(data.getUsername());
            csvWriter.write(data.getRealname());
            csvWriter.write(data.getDepartment().toString());
            csvWriter.write(data.getContent());
            csvWriter.write(data.getStatus().toString());
            csvWriter.write(data.getResult());
            csvWriter.write(data.getModule());
            csvWriter.write(data.getDetails());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }
    //部门
    @Override
    public File createTempFile_department() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","部门名称","用户数","主机数","描述"};
        csvWriter.writeRecord(headers);
        ArrayList<Department> datas = (ArrayList<Department>) departmentDao.selectAll();
        for (Department data : datas) {
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getCount().toString());
            csvWriter.write(data.getDevice_count().toString());
            csvWriter.write(data.getDescription());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_AppubServe() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","服务器名称","服务器地址","端口","账号","密码","描述"};
        csvWriter.writeRecord(headers);
        ArrayList<ApppubServer> datas = (ArrayList<ApppubServer>) ApppubServerDao.selectAll();
        for (ApppubServer data : datas) {
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getIp());
            csvWriter.write(data.getPort().toString());
            csvWriter.write(data.getAccount());
            csvWriter.write("");
            csvWriter.write(data.getDesc());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }
    //ApppubAccount
    @Override
    public File createTempFile_AppubApp() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","服务器名称","部门","应用名称","应用程序","用户","访问参数","描述"};
        csvWriter.writeRecord(headers);
        ArrayList<ApppubAccount> datas = (ArrayList<ApppubAccount>) ApppubAccountDao.selectAll();
        for (ApppubAccount data : datas) {
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getAppservername());
            csvWriter.write(data.getDepart_name());
            csvWriter.write(data.getName());
            csvWriter.write(data.getAppprogramname());
            csvWriter.write(data.getUsername());
            csvWriter.write(data.getUrl());
            csvWriter.write(data.getDesc());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_ApppubProgram() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","程序名称","程序路径","程序参数","描述"};
        csvWriter.writeRecord(headers);
        ArrayList<ApppubProgram> datas = (ArrayList<ApppubProgram>) ApppubProgramDao.selectAll();
        for (ApppubProgram data : datas) {
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getPath());
            csvWriter.write(data.getParameter());
            csvWriter.write(data.getDesc());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_operation() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","任务名称","命令/脚本","描述","执行方式"};
        csvWriter.writeRecord(headers);
        ArrayList<CrontabScriptConfig> datas = (ArrayList<CrontabScriptConfig>) CrontabScriptConfigDao.selectAll();
        for (CrontabScriptConfig data : datas) {
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getCommand());
            csvWriter.write(data.getDescription());
            csvWriter.write(data.getExec_method()==0?"手动执行":(data.getExec_method()==1?"定时执行":"周期执行"));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_loginLog() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"时间", "来源ip","用户名","姓名","部门","设备地址","系统账号","登陆方式","状态","备注"};
        csvWriter.writeRecord(headers);
        ArrayList<LoginLog> datas = (ArrayList<LoginLog>) loginLogDao.selectAll();
        for (LoginLog data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getLogin_datetime());
            csvWriter.write(data.getSource_ip());
            csvWriter.write(data.getUsername());
            csvWriter.write(data.getRealname());
            csvWriter.write(data.getDepartment().toString());
            csvWriter.write(data.getDevice_ip());
            csvWriter.write(data.getOs());
            csvWriter.write(data.getProtocol());
            csvWriter.write(data.getStatus().toString());
            csvWriter.write(data.getDetails());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_AlertLog() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"时间", "事件","内容","告警等级","告警方式","结果"};
        csvWriter.writeRecord(headers);
        //这里如果数据不是String类型，请进行转换
        csvWriter.write("2020-03-18 18:15:49.0");
        csvWriter.write("告警事件");
        csvWriter.write("告警内容");
        csvWriter.write("A");
        csvWriter.write("方式");
        csvWriter.write("告警结果");
        csvWriter.endRecord();
        csvWriter.write("2020-03-18 18:15:49.0");
        csvWriter.write("告警事件");
        csvWriter.write("告警内容");
        csvWriter.write("B");
        csvWriter.write("方式");
        csvWriter.write("告警结果");
        csvWriter.endRecord();
        csvWriter.write("2020-03-18 18:15:49.0");
        csvWriter.write("告警事件");
        csvWriter.write("告警内容");
        csvWriter.write("C");
        csvWriter.write("方式");
        csvWriter.write("告警结果");
        csvWriter.endRecord();

        csvWriter.close();
        return tempFile;
    }
//passsword
   @Override
        public File createTempFile_Password2() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID", "应用服务器","应用名称","程序名称"};
        csvWriter.writeRecord(headers);
        ArrayList<ApppubServer> datas = (ArrayList<ApppubServer>) ApppubServerDao.selectAll();
        for (ApppubServer data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getIp());
            csvWriter.write(data.getName());
          /*  csvWriter.write(data.getPort().toString());*/
            csvWriter.write(data.getDesc());
            csvWriter.write(data.getAccount());
            /*    csvWriter.write(data.getAccount());*/
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_Password() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID", "设备地址","设备名称","设备分组","系统类型","登录协议","设备账号"};
        csvWriter.writeRecord(headers);
        ArrayList<Device> datas = (ArrayList<Device>) deviceDao.selectAll();
        for (Device data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getIp());
            csvWriter.write(data.getName());
            csvWriter.write(data.getGroupname());
            csvWriter.write(data.getOs_type().toString());
            csvWriter.write(data.getProtocolname());
            csvWriter.write(data.getAccount_count().toString());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }


    @Override
    public File createTempFile_user() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID", "用户名","姓名","部门","角色名","状态","邮箱","QQ","微信","手机号码"};
        csvWriter.writeRecord(headers);
        ArrayList<User> datas = (ArrayList<User>) userDao.selectAll();
        for (User data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getUsername());
            csvWriter.write(data.getRealname());
         /*   csvWriter.write(data.getGroupname());*/
            csvWriter.write(data.getDepart_name());
            csvWriter.write(data.getRolename());
            csvWriter.write(data.getStatus()==1?"已禁用":"已启用");
            csvWriter.write(data.getEmail());
            csvWriter.write(data.getQq());
            csvWriter.write(data.getWechat());
            csvWriter.write(data.getMobile());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_group(int type) throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID","组名","部门","描述","成员数"};
        csvWriter.writeRecord(headers);
        ArrayList<Group> datas = (ArrayList<Group>) groupDao.listByType(type);
        for (Group data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getDepart_name());
            csvWriter.write(data.getDesc());
            csvWriter.write(data.getCount().toString());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_device() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID", "设备名","IP","系统类型","部门","描述","账号数"};
        csvWriter.writeRecord(headers);
        ArrayList<Device> datas = (ArrayList<Device>) deviceDao.selectAll();
        for (Device data : datas) {
            //这里如果数据不是String类型，请进行转换
            String checknameById = deviceTypeDao.checknameById(data.getOs_type());
            String departmentDaoName = departmentDao.findName(data.getDepartment());
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getIp());
            csvWriter.write(checknameById);
            csvWriter.write(departmentDaoName);
            csvWriter.write(data.getDescription());
            csvWriter.write(data.getAccount_count().toString());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_AccessPolicy() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = {"ID", "策略名称","有效开始时间","永久有效","有效截止时间","允许的ip","状态","描述"};
        csvWriter.writeRecord(headers);
        ArrayList<AccessPolicy> datas = (ArrayList<AccessPolicy>) accessPolicyDao.selectAll();
        for (AccessPolicy data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getId().toString());
            csvWriter.write(data.getName());
            csvWriter.write(data.getValid_datetime_start());
            csvWriter.write(data.getValid_long().toString());
            csvWriter.write(data.getValid_datetime_end());
            csvWriter.write(data.getAllow_ip());
            csvWriter.write(data.getStatus().toString());
            csvWriter.write(data.getDesc());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_DeviceRecord() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "开始时间","结束时间","设备名称","设备地址","协议","设备账号","来源ip","运维用户","姓名","状态"};
        csvWriter.writeRecord(headers);
        ArrayList<DeviceRecord> datas = (ArrayList<DeviceRecord>) deviceRecordDao.selectAll();
        for (DeviceRecord data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getStart());
            csvWriter.write(data.getEnd());
            csvWriter.write(data.getDevice_name());
            csvWriter.write(data.getDevice_ip());
            csvWriter.write(data.getProtocol_name());
            csvWriter.write(data.getDevice_username());
            csvWriter.write(data.getClient_ip());
            csvWriter.write(data.getUsername());
            csvWriter.write(data.getRealname());
            csvWriter.write(data.getStatus().toString());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }
    
	@Override
	public File createTempFile_reportProtocolLoginlog(String interval,String start_date, String end_date) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "日期","SSH","RDP","TELNET","VNC","应用发布","前台","合计"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)loginLogService.selectProtocolBydate(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("interval"));
            csvWriter.write(String.valueOf(data.get("ssh")));
            csvWriter.write(String.valueOf(data.get("rdp")));
            csvWriter.write(String.valueOf(data.get("telnet")));
            csvWriter.write(String.valueOf(data.get("vnc")));
            csvWriter.write(String.valueOf(data.get("apppub")));
            csvWriter.write(String.valueOf(data.get("web")));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
    
	@Override
	public File createTempFile_reportUserLoginlog(String interval,String start_date, String end_date) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "用户名","真实姓名","SSH","RDP","TELNET","VNC","应用发布","前台","合计"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)loginLogService.selectByUser(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("username"));
            csvWriter.write(data.get("realname"));
            csvWriter.write(String.valueOf(data.get("ssh")));
            csvWriter.write(String.valueOf(data.get("rdp")));
            csvWriter.write(String.valueOf(data.get("telnet")));
            csvWriter.write(String.valueOf(data.get("vnc")));
            csvWriter.write(String.valueOf(data.get("apppub")));
            csvWriter.write(String.valueOf(data.get("web")));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
    
	@Override
	public File createTempFile_reportFailLoginlog(String interval,String start_date, String end_date) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "时间","用户数","总次数","成功次数","失败次数","来源IP数"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)loginLogService.selectByInterval(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("interval"));
            csvWriter.write(String.valueOf(data.get("users")));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.write(String.valueOf(data.get("successes")));
            csvWriter.write(String.valueOf(data.get("fails")));
            csvWriter.write(String.valueOf(data.get("sourceips")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	
	@Override
	public File createTempFile_reportRecordlog(String interval, String start_date, String end_date) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "时间","设备数","用户数"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectTimeByInterval(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("interval"));
            csvWriter.write(String.valueOf(data.get("deviceips")));
            csvWriter.write(String.valueOf(data.get("users")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	
	@Override
	public File createTempFile_reportTimeLongDeviceRecordlog(String interval, String start_date, String end_date)
			throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "设备名称","设备地址","时长（秒）"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectTimeByLong(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("device_name"));
            csvWriter.write(data.get("device_ip"));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}

	@Override
	public File createTempFile_reportTimeLongUserRecordlog(String interval, String start_date, String end_date)
			throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "用户名","真实姓名","时长（秒）"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectTimeByLong(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("username"));
            csvWriter.write(data.get("realname"));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	
	@Override
	public File createTempFile_reportCommandlog(String interval, String start_date, String end_date)
			throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "时间","用户数","总次数","成功次数","失败次数","来源IP数"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectCommandReport(start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("interval"));
            csvWriter.write(String.valueOf(data.get("users")));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.write(String.valueOf(data.get("successes")));
            csvWriter.write(String.valueOf(data.get("fails")));
            csvWriter.write(String.valueOf(data.get("sourceips")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	
	@Override
	public File createTempFile_reportAlertlog(String interval, String start_date, String end_date) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "时间","用户数","总次数","成功次数","失败次数","来源IP数"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceRecordService.selectAlertByInterval(interval, start_date, end_date, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("interval"));
            csvWriter.write(String.valueOf(data.get("users")));
            csvWriter.write(String.valueOf(data.get("total")));
            csvWriter.write(String.valueOf(data.get("successes")));
            csvWriter.write(String.valueOf(data.get("fails")));
            csvWriter.write(String.valueOf(data.get("sourceips")));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	
	@Override
	public File createTempFile_reportSystemPriority(DeviceAccount deviceAccount) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "设备名称","设备地址","设备账号","运维用户","真实姓名","策略名称"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)deviceAccountService.getUserDeviceAccount(deviceAccount, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("name"));
            csvWriter.write(data.get("ip"));
            csvWriter.write(data.get("device_username"));
            csvWriter.write(data.get("username"));
            csvWriter.write(data.get("realname"));
            csvWriter.write(data.get("policyname"));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}
	@Override
	public File createTempFile_reportApppubPriority(ApppubAccount apppubAccount) throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "应用发布服务器","应用名称","应用程序","应用账号","运维用户","真实姓名","策略名称"};
        csvWriter.writeRecord(headers);
		ArrayList<Object> resultLoginLogs = new ArrayList<Object>();
		ArrayList<Map<String, String>> loginLogs = new ArrayList<Map<String, String>>();
        resultLoginLogs = (ArrayList<Object>)apppubAccountService.getApppubAccount(apppubAccount, 0, 10000);
		if(CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>)resultLoginLogs.get(0);
		}
        for (Map<String, String> data : loginLogs) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.get("appserverip"));
            csvWriter.write(data.get("apppubaccountname"));
            csvWriter.write(data.get("appprogramname"));
            csvWriter.write(data.get("apppubusername"));
            csvWriter.write(data.get("username"));
            csvWriter.write(data.get("realname"));
            csvWriter.write(data.get("policyname"));
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
	}

    @Override
    public File createTempFile_reportCrontabCommandLog(Integer id) throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
        // 写表头
        String[] headers = { "设备地址","设备端口","设备用户","命令/脚本","开始时间","结束时间","执行结果"};
        csvWriter.writeRecord(headers);
        ArrayList<CrontabCommandLog> datas = crontabCommandLogService.getCrontabCommandLog(id);
        for (CrontabCommandLog data : datas) {
            //这里如果数据不是String类型，请进行转换
            csvWriter.write(data.getDevice_ip());
            csvWriter.write(data.getDevice_port().toString());
            csvWriter.write(data.getDevice_username());
            csvWriter.write(data.getCommand());
            csvWriter.write(data.getExec_datetime());
            csvWriter.write(data.getEnd_datetime());
            csvWriter.write(data.getResult());
            csvWriter.endRecord();
        }
        csvWriter.close();
        return tempFile;
    }


}
