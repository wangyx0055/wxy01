package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.SmsLog;

public interface SmsLogService {

	public boolean addSmsLog(SmsLog smsLog);

	public boolean editSmsLog(SmsLog smsLog);

	public boolean delSmsLog(List<Integer> ids);

	public List<Object> findAll(SmsLog smsLog, int page_start, int page_length);

	public SmsLog getById(Integer id);

}

