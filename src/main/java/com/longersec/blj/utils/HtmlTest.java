package com.longersec.blj.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author wxy
 * @description
 * @data 2020/9/7
 */
public class HtmlTest {
	public static void main(String[] args) {
		//打开文件
		PrintStream printStream = null;
		//用于存储html字符串
		StringBuilder stringHtml = new StringBuilder();
		try{
			 printStream = new PrintStream(new FileOutputStream("test.html"));
		}catch(
				FileNotFoundException e){
			e.printStackTrace();
		}
		//输入HTML文件内容
		stringHtml.append("<html><head>");
		stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		stringHtml.append("<title>测试报告文档</title>");
		stringHtml.append("</head>");
		stringHtml.append("<body>");
		stringHtml.append("<div>hello</div>");
		stringHtml.append("</body></html>");
		try{
			//将HTML文件内容写入文件中
			assert printStream != null;
			printStream.println(stringHtml.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
