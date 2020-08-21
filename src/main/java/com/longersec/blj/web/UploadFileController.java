package com.longersec.blj.web;

import com.longersec.blj.domain.SshScript;
import com.longersec.blj.service.SshScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.CrontabScript;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.CrontabScriptService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
public class UploadFileController {
	@Autowired
	ConfigService configService;
	@Autowired
	CrontabScriptService crontabScriptService;
    @Autowired
    private SshScriptService sshScriptService;
    @RequestMapping("/upload")
    public String fileload(MultipartFile file, HttpServletRequest request) {
        //获取文件名称
        String fileName = file.getOriginalFilename();
        
        Config config = configService.getByName("fileUploadPath");
        CrontabScript crontabScript = new CrontabScript();

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        String ymd = ft.format(dNow);
        fileName = ymd+"/"+fileName;
        //设置上传路径
        String path = config.getValue();
        if(path.charAt(path.length()-1)!='/') {
        	path = path + "/";
        }

        //文件上传
        File f = new File(path);

        //判断路径是否存在，不存在则创建
        if(!f.exists()){
            f.mkdirs();
        }
        //判断上传文件是否为空
        if(!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + fileName);
                InputStream in = file.getInputStream();
                int a = 0;
                if((a = in.read())!=-1){
                    fos.write(a);
                }
                in.close();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String address = path+fileName;
        System.out.println(address);

        return address;
    }
    
    @RequestMapping("/uploadScript")
    public JSONObject uploadScript(MultipartFile scriptfile, HttpServletRequest request) {
    	JSONObject result = new JSONObject();
    	Config config = configService.getByName("fileUploadPath");
        CrontabScript crontabScript = new CrontabScript();
    	
    	//获取文件名称
        String fileName = scriptfile.getOriginalFilename();

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        String ymd = ft.format(dNow);
        fileName = ymd+"/"+fileName;
        //设置上传路径
        String path = config.getValue();
        if(path.charAt(path.length()-1)!='/') {
        	path = path + "/";
        }
        //文件上传
        File f = new File(path);
        //判断路径是否存在，不存在则创建
        if(!f.exists()){
            f.mkdirs();
        }
        f = new File(path+ymd);
        if(!f.exists()){
            f.mkdirs();
        }
        //判断上传文件是否为空
        if(!scriptfile.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + fileName);
                InputStream in = scriptfile.getInputStream();
                int a = 0;
                while((a = in.read())!=-1){
                    fos.write(a);
                }
                in.close();
                fos.close();
                crontabScript.setFile_path(fileName);
                crontabScript.setName(scriptfile.getOriginalFilename());
                crontabScript.setUpload_time(new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date()));
                crontabScriptService.addCrontabScript(crontabScript);
            	result.put("success", true);
            	result.put("chunkIndex", request.getParameter("chunkIndex"));
                result.put("file-id", crontabScript.getId());
                
            }catch (Exception e){
            	result.put("success", false);
                e.printStackTrace();
            }
        }
        return result;
    }
    
    @RequestMapping("/deleteFile")
    public JSONObject deleteFile(HttpServletRequest request, Integer file_id) {
    	JSONObject result = new JSONObject();
    	Config config = configService.getByName("fileUploadPath");
    	CrontabScript crontabScript = crontabScriptService.getById(file_id);
		File file = new File(config.getValue()+"/"+crontabScript.getFile_path());

        //判断上传文件是否为空
        if(file.exists()){
            try {
            	file.delete();
            	result.put("success", true);
            }catch (Exception e){
            	result.put("success", false);
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    @RequestMapping("/down")
    public void filedown(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer file_id) throws Exception {
    	Config config = configService.getByName("fileUploadPath");
        CrontabScript crontabScript = crontabScriptService.getById(file_id);
		File file = new File(config.getValue()+"/"+crontabScript.getFile_path());
	    try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("content-disposition","attachment;fileName="+crontabScript.getName());
            InputStream stream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = stream.read(buff)) > 0) {
                out.write(buff,0,length);
            }
            stream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/uploadSsh")
    public JSONObject uploadSsh(MultipartFile scriptfile, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        Config config = configService.getByName("fileUploadPath");
        SshScript sshScript = new SshScript();
        //获取文件名称
        String fileName = scriptfile.getOriginalFilename();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMM");
        String ymd = ft.format(dNow);
        fileName = ymd+"/"+fileName;
        //设置上传路径
        String path = config.getValue();
        if(path.charAt(path.length()-1)!='/') {
            path = path + "/";
        }
        //文件上传
        File f = new File(path);
        //判断路径是否存在，不存在则创建
        if(!f.exists()){
            f.mkdirs();
        }
        f = new File(path+ymd);
        if(!f.exists()){
            f.mkdirs();
        }
        //判断上传文件是否为空
        if(!scriptfile.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + fileName);
                InputStream in = scriptfile.getInputStream();
                int a = 0;
                while((a = in.read())!=-1){
                    fos.write(a);
                }
                in.close();
                fos.close();
                sshScript.setFile_path(fileName);
                sshScript.setName(scriptfile.getOriginalFilename());
                sshScript.setUpload_time(new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date()));
                sshScriptService.addSshScript(sshScript);
                result.put("success", true);
                result.put("chunkIndex", request.getParameter("chunkIndex"));
                result.put("file-id", sshScript.getId());
            }catch (Exception e){
                result.put("success", false);
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping("/deleteSsh")
    public JSONObject deleteSsh(HttpServletRequest request, Integer file_id) {
        JSONObject result = new JSONObject();
        Config config = configService.getByName("fileUploadPath");
        SshScript sshScript = sshScriptService.getById(file_id);
        File file = new File(config.getValue()+"/"+sshScript.getFile_path());
        //判断上传文件是否为空
        if(file.exists()){
            try {
                file.delete();
                result.put("success", true);
            }catch (Exception e){
                result.put("success", false);
                e.printStackTrace();
            }
        }

        return result;
    }

    @RequestMapping("/downSsh")
    public void filedownSsh(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer file_id) throws Exception {
        Config config = configService.getByName("fileUploadPath");
        SshScript sshScript = sshScriptService.getById(file_id);
        File file = new File(config.getValue()+"/"+sshScript.getFile_path());
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.setHeader("content-disposition","attachment;fileName="+sshScript.getName());
            InputStream stream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = stream.read(buff)) > 0) {
                out.write(buff,0,length);
            }
            stream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}