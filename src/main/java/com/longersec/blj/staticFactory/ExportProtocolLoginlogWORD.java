package com.longersec.blj.staticFactory;

import com.longersec.blj.service.LoginLogService;
import com.longersec.blj.web.ExportController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author wxy
 * @description 导出word格式
 * @data 2020/9/8
 */
@Service
public class ExportProtocolLoginlogWORD implements ExportProtocolLoginlog {
	@Override
	public Object createTempFile_reportProtocolLoginlog(String interval, String start_date, String end_date, LoginLogService loginLogService, HttpServletResponse response)  throws Exception {
		//Blank Document
		XWPFDocument document= new XWPFDocument();
		//create table
		XWPFTable table = document.createTable();
		//create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("日期");
		tableRowOne.addNewTableCell().setText("SSH");
		tableRowOne.addNewTableCell().setText("RDP");
		tableRowOne.addNewTableCell().setText("TELNET");
		tableRowOne.addNewTableCell().setText("VNC");
		tableRowOne.addNewTableCell().setText("应用发布");
		tableRowOne.addNewTableCell().setText("前台");
		tableRowOne.addNewTableCell().setText("合计");
		ArrayList<Map<String, String>> loginLogs = new ArrayList<>();
		ArrayList<Object> resultLoginLogs = (ArrayList<Object>) loginLogService.selectProtocolBydate(interval, start_date, end_date, 0, 10000);
		if (CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>) resultLoginLogs.get(0);
		}
		for (Map<String, String> data : loginLogs) {
			XWPFTableRow tableRowTwo = table.createRow();
			tableRowTwo.getCell(0).setText(data.get("interval"));
			tableRowTwo.getCell(1).setText(String.valueOf(data.get("ssh")));
			tableRowTwo.getCell(2).setText(String.valueOf(data.get("rdp")));
			tableRowTwo.getCell(3).setText(String.valueOf(data.get("telnet")));
			tableRowTwo.getCell(4).setText(String.valueOf(data.get("vnc")));
			tableRowTwo.getCell(5).setText(String.valueOf(data.get("apppub")));
			tableRowTwo.getCell(6).setText(String.valueOf(data.get("web")));
			tableRowTwo.getCell(7).setText(String.valueOf(data.get("total")));
		}
		Date t = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = "reportProtocolLoginlog-"+formatter.format(t)+".docx";
		//写入文件
		BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(filename));
		document.write(out1);
		out1.close();
		//交给浏览器下载
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msword");
		response.setHeader("content-disposition","attachment;fileName="+filename);
		InputStream stream = new FileInputStream(filename);
		BufferedInputStream bufferedReader = new BufferedInputStream(stream);
		ServletOutputStream out = response.getOutputStream();
		byte[] buff = new byte[2048];
		int length = 0;
		while ((length = bufferedReader.read(buff)) > 0) {
			out.write(buff,0,length);
			out.flush();
		}
		stream.close();
		bufferedReader.close();
		out.close();
		//删除临时文件
		ExportController.deleteFile(new File(filename));
		return null;
	}
}
