package com.longersec.blj.staticFactory;

import com.longersec.blj.service.LoginLogService;
import com.longersec.blj.web.ExportController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wxy
 * @description 工厂
 * @data 2020/9/8
 */
public class ExportProtocolFactory {
	public static boolean excute(String interval,String start, String end, String file_type, LoginLogService loginLogService,HttpServletResponse response) throws Exception {
		if(file_type == null){
			return false;
		}
		Date t = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		if ("csv".equals(file_type)){
			ExportProtocolLoginlogCSV exportProtocolLoginlogCSV = new ExportProtocolLoginlogCSV();
			File tempFile_reportProtocolLoginlog = exportProtocolLoginlogCSV.createTempFile_reportProtocolLoginlog(interval, start, end, loginLogService,response);
			String filename = "reportProtocolLoginlog-"+formatter.format(t)+".csv";
			//输出csv流文件，提供给浏览器下载
			ExportController.outCsvStream(response, tempFile_reportProtocolLoginlog,filename);
			//删除临时文件
			ExportController.deleteFile(tempFile_reportProtocolLoginlog);
		} else if ("word".equals(file_type)){
			ExportProtocolLoginlogWORD exportProtocolLoginlogWORD = new ExportProtocolLoginlogWORD();
			exportProtocolLoginlogWORD.createTempFile_reportProtocolLoginlog(interval, start, end, loginLogService,response);
		} else if ("pdf".equals(file_type)){
			ExportProtocolLoginlogPDF exportProtocolLoginlogPDF = new ExportProtocolLoginlogPDF();
			exportProtocolLoginlogPDF.createTempFile_reportProtocolLoginlog(interval, start, end, loginLogService,response);
		}
		return false;
	}
}
