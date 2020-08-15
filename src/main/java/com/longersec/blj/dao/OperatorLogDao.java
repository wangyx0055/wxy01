package com.longersec.blj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.OperatorLog;

public interface OperatorLogDao {

	public boolean editOperatorLog(OperatorLog operatorLog);

	public boolean addOperatorLog(OperatorLog operatorLog);

	public boolean delOperatorLog(List<Integer> ids);

	public List<Object> findAll(@Param("operatorLog")OperatorLog operatorLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<OperatorLog> selectAll();
	
	public List<Map<String, Object>> selectByDay();
	
	public List<Map<String, Object>> selectByUser();
}
