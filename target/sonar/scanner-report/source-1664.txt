package com.longersec.blj.service;

import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.LoginLog;

public interface LoginLogService {

	public boolean addLoginLog(LoginLog loginLog);

	public boolean editLoginLog(LoginLog loginLog);

	public boolean delLoginLog(List<Integer> ids);

	public List<Object> findAll(LoginLog loginLog, int page_start, int page_length);
	
	public List<Map<String, Object>> selectLast7Day();
	
	public List<Map<String, Object>> selectByUser();
	
	public List<Object> selectByInterval(String interval,String start_date, String end_date, int page_start, int page_length);

	public List<Object> selectByUser(String interval,String start_date, String end_date, int page_start, int page_length);
	
	public List<Object> selectProtocolBydate(String interval,String start_date, String end_date, int page_start, int page_length);
}

