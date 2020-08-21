package com.longersec.blj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LoginLog;

public interface LoginLogDao {

	public boolean editLoginLog(LoginLog loginLog);

	public boolean addLoginLog(LoginLog loginLog);

	public boolean delLoginLog(List<Integer> ids);

	public List<Object> findAll(@Param("loginLog")LoginLog loginLog, @Param("page_start")int page_start,
	                            @Param("page_length")int page_length);

	public List<Object> findAll1(@Param("loginLog")LoginLog loginLog, @Param("page_start")int page_start,
	                            @Param("time_format")String time_format,  @Param("time")String time,
	                            @Param("page_length")int page_length);
    List<LoginLog> selectAll();
	
	public List<Map<String, Object>> selectLast7Day();
	
	public List<Map<String, Object>> selectByUser();
	
	public List<Object> selectByHour (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectByDay  (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectByWeek (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectByMonth(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Object> selectProtocolByUser (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectProtocolByHour (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectProtocolByDay  (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectProtocolByWeek (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectProtocolByMonth(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
}
