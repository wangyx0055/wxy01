package com.longersec.blj.utils;

/**
 * @author wxy
 * @description 常量
 * @data 2020/8/25
 */
public class BljConstant {
	//返回给前台成功失败值
	public static final String SUCCESS = "success";
	
	public static String protocol(int protocol_id) {
		if(protocol_id==1) {
			return "SSH";
		}else if(protocol_id==2) {
			return "RDP";
		}else if(protocol_id==3) {
			return "TELNET";
		}else if(protocol_id==4) {
			return "VNC";
		}else if(protocol_id==5) {
			return "FTP";
		}else if(protocol_id==6) {
			return "SFTP";
		}else {
			return "UNKONWN";
		}
	}
}
