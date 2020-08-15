package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SmsLogDao;
import com.longersec.blj.domain.SmsLog;
import com.longersec.blj.service.SmsLogService;


@Service
@Transactional
public class SmsLogServiceImpl implements SmsLogService{

	@Autowired
	private SmsLogDao SmsLogDao;

	@Override
	public boolean editSmsLog(SmsLog smsLog) {
		// TODO Auto-generated method stub
		return this.SmsLogDao.editSmsLog(smsLog);
	}

	@Override
	public boolean addSmsLog(SmsLog smsLog) {
		// TODO Auto-generated method stub
		return this.SmsLogDao.addSmsLog(smsLog);
	}

	@Override
	public boolean delSmsLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.SmsLogDao.delSmsLog(ids);
	}

	@Override
	public List<Object> findAll(SmsLog smsLog, int page_start, int page_length) {
		return SmsLogDao.findAll(smsLog, page_start, page_length);
	}

	@Override
	public SmsLog getById(Integer id) {
		return SmsLogDao.getById(id);
	}

}
