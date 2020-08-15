package com.longersec.blj.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.User;

/**
 * @author wxy
 */
public class Operator_log {
	public static OperatorLog log(HttpServletRequest request, HttpSession session) {
		//获取主机ip
		InetAddress ipAddress = null;
		String localip = null;
		try {
		    ipAddress =InetAddress.getLocalHost();
		    localip = ipAddress.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		//获取session中的user对象
		User users = (User)session.getAttribute("user");
		OperatorLog operatorLog = new OperatorLog();
		//赋值
		operatorLog.setSource_ip(localip);
		operatorLog.setDepartment(users.getDepartment());
		operatorLog.setUsername(users.getUsername());
		operatorLog.setUser_id(users.getId());
		operatorLog.setStatus(users.getStatus());
		operatorLog.setDepartment_id(5);
		//当前时间的时间戳
		int totalMilliSeconds = (int) (System.currentTimeMillis()/1000);
		operatorLog.setOperate_datetime(String.valueOf(totalMilliSeconds));
		operatorLog.setRealname(users.getRealname());

		return operatorLog;
	}
}
