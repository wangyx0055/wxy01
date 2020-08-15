package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface DeviceRecordDao {

	public boolean editDeviceRecord(DeviceRecord deviceRecord);

	public int addDeviceRecord(DeviceRecord deviceRecord);

	public boolean delDeviceRecord(List<Integer> ids);

	public List<Object> findAll(@Param("deviceRecord")DeviceRecord deviceRecord, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<DeviceRecord> getEarlyRecord();
	
	public DeviceRecord getById(Integer id);

    public List<DeviceRecord> selectAll();

    public Integer getTextTotal();
    
    public Integer getGraphTotal();
    
    public Integer get30DayTextIncrease();
    
    public Integer get30DayGraphIncrease();
    
    public List<Object> selectTimeByHour (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectTimeByDay  (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectTimeByWeek (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectTimeByMonth(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
    
	public List<Object> selectTimeLongByDevice(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectTimeLongByUser(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectCommandReport(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectAlertByHour (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectAlertByDay (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectAlertByWeek (@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Object> selectAlertByMonth(@Param("start_date")String start_date, @Param("end_date")String end_date, @Param("page_start")int page_start, @Param("page_length")int page_length);
    
}
