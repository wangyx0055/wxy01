package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ApppubRecord;
import com.longersec.blj.domain.DeviceRecord;

public interface ApppubRecordService {

	public boolean addApppubRecord(ApppubRecord apppubRecord);

	public boolean editApppubRecord(ApppubRecord apppubRecord);

	public boolean delApppubRecord(List<Integer> ids);

	public List<Object> findAll(ApppubRecord apppubRecord, int page_start, int page_length);

	public List<ApppubRecord> getEarlyRecord();
	
	public Integer get30DayIncrease();
    
    public Integer getApppubRecordTotal();
	
	public ApppubRecord getById(Integer id);

}

