package com.longersec.blj.staticFactory;

import com.longersec.blj.service.LoginLogService;

import javax.servlet.http.HttpServletResponse;

public interface ExportProtocolLoginlog {
	Object createTempFile_reportProtocolLoginlog(String interval, String start_date, String end_date, LoginLogService loginLogService, HttpServletResponse response) throws Exception;
}
