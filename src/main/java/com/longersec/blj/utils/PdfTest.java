package com.longersec.blj.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

			//准备Person类
			Person person = new  Person();
			person.setName("娃哈哈");
			person.setAge(99);
			person.setAdress("杭州西湖区文一路");

			//准备personList
			List<Person> personList = Arrays.asList(
					new Person("西湖一",18,"杭州西湖区123号东方通信大厦66楼1666号室"),
					new Person("滨江二",26,"杭州滨江区456号辉煌大厦99楼2999号室"),
					new Person("萧山三",66,"杭州萧山区789号新玛特国际大厦888楼38888号室")

			);

			//PDF输出内容
			document.add(new Paragraph(person.toString(), font));
			//继续输出
			document.add(new Paragraph(personList.toString(),font));
			document.close();

			writer.close();
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} finally {
		}
	}
}
