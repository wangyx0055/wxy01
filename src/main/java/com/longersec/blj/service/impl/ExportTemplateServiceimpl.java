package com.longersec.blj.service.impl;

import com.csvreader.CsvWriter;
import com.longersec.blj.domain.*;
import com.longersec.blj.service.ExportTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Transactional
@Service
class ExportTemplateServiceImpl implements ExportTemplateService {
    @Override
    public File createTempFile_user() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"用户名(必填)","姓名（必填）","部门名称（如果部门名称重复请在名称后加{{id}} 例如:测试部{{380}}）","角色名（必填）","密码（必填）","邮箱","QQ","微信","手机号码"};
        csvWriter.writeRecord(headers);
        User data = new User();
        data.setUsername("test");
        data.setRealname("测试");
        data.setDepart_name("测试部");
        data.setRolename("运维用户");
        data.setPassword("wwwtesttest");
        data.setEmail("2357191256@qq.com");
        data.setQq("2357191256");
        data.setWechat("2357191256");
        data.setMobile("18955007261");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getUsername());
        csvWriter.write(data.getRealname());
        csvWriter.write(data.getDepart_name());
        csvWriter.write(data.getRolename());
        csvWriter.write(data.getPassword());
        csvWriter.write(data.getEmail());
        csvWriter.write(data.getQq());
        csvWriter.write(data.getWechat());
        csvWriter.write(data.getMobile());
        csvWriter.endRecord();

        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_group() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"组名(必填)","描述"};
        csvWriter.writeRecord(headers);
        Group data = new Group();
        data.setName("test");
        data.setDesc("测试");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getName());
        csvWriter.write(data.getDesc());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_department() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"部门名称(必填)","描述","上级部门(不填默认为根部门)"};
        csvWriter.writeRecord(headers);
        Department data = new Department();
        data.setName("测试部");
        data.setDescription("测试部");
        data.setParent_name("");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getName());
        csvWriter.write(data.getDescription());
        csvWriter.write(data.getParent_name());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_operation() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"任务名称(必填)","命令/脚本","执行方式","描述"};
        csvWriter.writeRecord(headers);
        CrontabScriptConfig data = new CrontabScriptConfig();
        data.setName("renwu");
        data.setCommand("pwd");
        data.setExec_method(1);
        data.setDescription("描述");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getName());
        csvWriter.write(data.getCommand());
        csvWriter.write(data.getExec_method().toString());
        csvWriter.write(data.getDescription());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_device() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"设备名(必填)","设备地址（必填）","系统类型（Linux Windows....)（必填）","部门名称（如果部门名称重复请在名称后加{{id}} 例如:测试部{{380}}）",
                "描述","账号","密码","协议类型(SSH[1] RDP[2] TELNET[3] VNC[4] FTP[5] SFTP[6])（必填）","端口(必填)","SSHKey(0:是 1:否)"};
        csvWriter.writeRecord(headers);
        Device data = new Device();
        data.setName("test");
        data.setIp("0.0.0.0");
        data.setTypename("Linux");
        data.setDepart_name("华中大区");
        data.setDescription("描述");
        data.setAccount("test");
        data.setPassword("testtest");
        data.setProtocolname("SSH");
        data.setPort(22);
        data.setSsh_key(1);
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getName());
        csvWriter.write(data.getIp());
        csvWriter.write(data.getTypename());
        csvWriter.write(data.getDepart_name());
        csvWriter.write(data.getDescription());
        csvWriter.write(data.getAccount());
        csvWriter.write(data.getPassword());
        csvWriter.write(data.getProtocolname());
        csvWriter.write(data.getPort().toString());
        csvWriter.write(data.getSsh_key().toString());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_apppubserver() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"服务器名称(必填)","服务器地址（必填）","部门名称（如果部门名称重复请在名称后加{{id}} 例如:测试部{{380}}）","端口（必填）","描述"};
        csvWriter.writeRecord(headers);
        ApppubServer data = new ApppubServer();
        data.setName("test");
        data.setIp("0.0.0.0");
        data.setDepart_name("测试部");
        data.setPort(3389);
        data.setDesc("描述");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getName());
        csvWriter.write(data.getIp());
        csvWriter.write(data.getDepart_name());
        csvWriter.write(data.getPort().toString());
        csvWriter.write(data.getDesc());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }

    @Override
    public File createTempFile_apppubAccount() throws IOException {
        File tempFile = File.createTempFile("vehicle", ".csv");
        CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
        // 写表头
        String[] headers = {"服务器名称(必填)","部门名称（如果部门名称重复请在名称后加{{id}} 例如:测试部{{380}}）（选填）","应用名称（必填）","应用程序（必填）","用户名","密码","访问参数","描述"};
        csvWriter.writeRecord(headers);
        ApppubAccount data = new ApppubAccount();
        data.setAppservername("test");
        data.setDepart_name("测试部");
        data.setName("test");
        data.setAppprogramname("Git");
        data.setUsername("administrator");
        data.setPassword("1@3");
        data.setUrl("198.162.1.1");
        data.setDesc("描述");
        //这里如果数据不是String类型，请进行转换
        csvWriter.write(data.getAppservername());
        csvWriter.write(data.getDepart_name());
        csvWriter.write(data.getName());
        csvWriter.write(data.getAppprogramname());
        csvWriter.write(data.getUsername());
        csvWriter.write(data.getPassword());
        csvWriter.write(data.getUrl());
        csvWriter.write(data.getDesc());
        csvWriter.endRecord();
        csvWriter.close();
        return tempFile;
    }
}
