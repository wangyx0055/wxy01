package com.longersec.blj.staticFactory;

import com.csvreader.CsvWriter;
import com.longersec.blj.service.LoginLogService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author wxy
 * @description 导出csv格式
 * @data 2020/9/8
 */
@Transactional
@Service
public class ExportProtocolLoginlogHTML implements ExportProtocolLoginlog {
	@Override
	public File createTempFile_reportProtocolLoginlog(String interval, String start_date, String end_date, LoginLogService loginLogService, HttpServletResponse response)  throws IOException {
		File tempFile = File.createTempFile("vehicle", ".csv");
		CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
		// 写表头
		String[] headers = {"日期", "SSH", "RDP", "TELNET", "VNC", "应用发布", "前台", "合计"};
		csvWriter.writeRecord(headers);
		ArrayList<Map<String, String>> loginLogs = new ArrayList<>();
		ArrayList<Object> resultLoginLogs = (ArrayList<Object>) loginLogService.selectProtocolBydate(interval, start_date, end_date, 0, 10000);
		if (CollectionUtils.isNotEmpty(resultLoginLogs)) {
			loginLogs = (ArrayList<Map<String, String>>) resultLoginLogs.get(0);
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
}
