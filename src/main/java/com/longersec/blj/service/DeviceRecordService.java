package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface DeviceRecordService {

	public int addDeviceRecord(DeviceRecord deviceRecord);

	public boolean editDeviceRecord(DeviceRecord deviceRecord);

	public boolean delDeviceRecord(List<Integer> ids);

	public List<Object> findAll(DeviceRecord deviceRecord, int page_start, int page_length);

	public List<DeviceRecord> getEarlyRecord();
	
	public DeviceRecord getById(Integer id);
	
	public Integer getTextTotal();
    
    public Integer getGraphTotal();
    
    public Integer get30DayTextIncrease();
    
    public Integer get30DayGraphIncrease();
    
    public List<Object> selectTimeByInterval(String interval,String start_date, String end_date, int page_start, int page_length);
    
    public List<Object> selectTimeByLong(String name,String start_date, String end_date, int page_start, int page_length);
    
    public List<Object> selectCommandReport(String start_date, String end_date, int page_start, int page_length);
    
    public List<Object> selectAlertByInterval(String interval,String start_date, String end_date, int page_start, int page_length);
}

