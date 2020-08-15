package com.longersec.blj.service;

import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.OperatorLog;

public interface OperatorLogService {

	public boolean addOperatorLog(OperatorLog operatorLog);

	public boolean editOperatorLog(OperatorLog operatorLog);

	public boolean delOperatorLog(List<Integer> ids);

	public List<Object> findAll(OperatorLog operatorLog, int page_start, int page_length);
	
	public List<Map<String, Object>> selectByDay();
	
	public List<Map<String, Object>> selectByUser();

}

