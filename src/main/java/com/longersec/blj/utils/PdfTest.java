package com.longersec.blj.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wxy
 * @description 导出pdf
 * @data 2020/9/7
 */
public class PdfTest {
	public static void main(String[] args) throws IOException
	{
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
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
			//PDF输出内容
			document.add(table);
			document.close();
			writer.close();
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
