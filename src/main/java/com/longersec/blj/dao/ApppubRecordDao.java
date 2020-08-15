package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubRecord;
import com.longersec.blj.domain.DeviceRecord;

public interface ApppubRecordDao {

	public boolean editApppubRecord(ApppubRecord apppubRecord);

	public boolean addApppubRecord(ApppubRecord apppubRecord);

	public boolean delApppubRecord(List<Integer> ids);

	public List<Object> findAll(@Param("apppubRecord")ApppubRecord apppubRecord, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<ApppubRecord> getEarlyRecord();

    public List<ApppubRecord> selectAll();

    public Integer get30DayIncrease();
    
    public Integer getApppubRecordTotal();
	
	public ApppubRecord getById(Integer id);
    
}
