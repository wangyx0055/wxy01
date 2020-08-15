package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.SmsLog;

public interface SmsLogDao {

	public boolean editSmsLog(SmsLog smsLog);

	public boolean addSmsLog(SmsLog smsLog);

	public boolean delSmsLog(List<Integer> ids);

	public List<Object> findAll(@Param("smsLog")SmsLog smsLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public SmsLog getById(Integer id);

}
