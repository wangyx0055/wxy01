package com.longersec.blj.web;


import com.longersec.blj.service.ExportTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/downTemplate")
public class ExportTemplateController {
    @Autowired
    private ExportTemplateService exportTemplateService;

    @RequestMapping("/user")
    public void downUser( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_user();
            String filename = "userT-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/group")
    public void downGroup( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_group();
            String filename = "groupT-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/department")
    public void downDepartment( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_department();
            String filename = "departmentT-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/device")
    public void downdevice( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_device();
            String filename = "deviceT-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/operation")
    public void downOperation( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_operation();
            String filename = "downOperation-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/apppubserver")
    public void apppubserver( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_apppubserver();
            String filename = "downApppubServer-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

    @RequestMapping("/apppubAccount")
    public void apppubAccount( HttpServletResponse response) throws IOException{
        Date t = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            //创建临时csv文件
            File tempFile = exportTemplateService.createTempFile_apppubAccount();
            String filename = "downApppubAccount-"+formatter.format(t)+".csv";
            //输出csv流文件，提供给浏览器下载
            outCsvStream(response, tempFile,filename);
            //删除临时文件
            deleteFile(tempFile);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }




    /**
     * 写入csv结束，写出流
     */
    public void outCsvStream(HttpServletResponse response, File tempFile , String filename) throws IOException {
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
        in.close();
        out.close();
        response.setContentType("text/csv; charset=\"utf-8\"");
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
