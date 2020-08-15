package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabScriptLogDao;
import com.longersec.blj.domain.CrontabScriptLog;
import com.longersec.blj.service.CrontabScriptLogService;


@Transactional
@Service
public class CrontabScriptLogServiceImpl implements CrontabScriptLogService{

	@Autowired
	private CrontabScriptLogDao CrontabScriptLogDao;

	@Override
	public boolean editCrontabScriptLog(CrontabScriptLog crontabScriptLog) {
		// TODO Auto-generated method stub
		return this.CrontabScriptLogDao.editCrontabScriptLog(crontabScriptLog);
	}

	@Override
	public boolean addCrontabScriptLog(CrontabScriptLog crontabScriptLog) {
		// TODO Auto-generated method stub
		return this.CrontabScriptLogDao.addCrontabScriptLog(crontabScriptLog);
	}

	@Override
	public boolean delCrontabScriptLog(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CrontabScriptLogDao.delCrontabScriptLog(ids);
	}

	@Override
	public List<Object> findAll(CrontabScriptLog crontabScriptLog, int page_start, int page_length) {
		return CrontabScriptLogDao.findAll(crontabScriptLog,page_start, page_length);
	}

}
