package com.longersec.blj.staticFactory;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.longersec.blj.service.LoginLogService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author wxy
 * @description 导出csv格式
 * @data 2020/9/8
 */
@Transactional
@Service
public class ExportProtocolLoginlogPDF implements ExportProtocolLoginlog {
	@Override
	public Object createTempFile_reportProtocolLoginlog(String interval, String start_date, String end_date, LoginLogService loginLogService, HttpServletResponse response)  throws IOException {
		Document document = new Document();
		try {
			Date t = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String filename = "reportProtocolLoginlog-"+formatter.format(t)+".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			//设置宋体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			//设置字大小  颜色
			Font font = new Font(bfChinese, 16, Font.NORMAL, BaseColor.BLACK);
			//创建pdf表格
			PdfPTable table = new PdfPTable(8);
			String[] tableName=new String[]{"日期","SSH","RDP","TELNET","VNC","应用发布","前台","合计"};
			for (String s : tableName) {
				Paragraph element = new Paragraph(s, font);
				PdfPCell cell = new PdfPCell(element);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			ArrayList<Map<String, String>> loginLogs = new ArrayList<>();
			ArrayList<Object> resultLoginLogs = (ArrayList<Object>) loginLogService.selectProtocolBydate(interval, start_date, end_date, 0, 10000);
			if (CollectionUtils.isNotEmpty(resultLoginLogs)) {
				loginLogs = (ArrayList<Map<String, String>>) resultLoginLogs.get(0);
			}
			for (Map<String, String> data : loginLogs) {
				Paragraph element = new Paragraph(data.get("interval"), font);
				PdfPCell cell = new PdfPCell(element);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				Paragraph element2 = new Paragraph(String.valueOf(data.get("ssh")), font);
				PdfPCell cell2 = new PdfPCell(element2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);
				Paragraph element3 = new Paragraph(String.valueOf(data.get("rdp")), font);
				PdfPCell cell3 = new PdfPCell(element3);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
				Paragraph element4 = new Paragraph(String.valueOf(data.get("telnet")), font);
				PdfPCell cell4 = new PdfPCell(element4);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell4);
				Paragraph element5 = new Paragraph(String.valueOf(data.get("vnc")), font);
				PdfPCell cell5 = new PdfPCell(element5);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell5);
				Paragraph element6 = new Paragraph(String.valueOf(data.get("apppub")), font);
				PdfPCell cell6 = new PdfPCell(element6);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell6);
				Paragraph element7 = new Paragraph(String.valueOf(data.get("web")), font);
				PdfPCell cell7 = new PdfPCell(element7);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell7);
				Paragraph element8 = new Paragraph(String.valueOf(data.get("total")), font);
				PdfPCell cell8 = new PdfPCell(element8);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell8);
			}
			//写入文件
			document.add(table);
			document.close();
			writer.close();
			//交给浏览器下载
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/pdf");
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
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
