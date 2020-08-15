package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabCommandLogDao;
import com.longersec.blj.domain.CrontabCommandLog;
import com.longersec.blj.service.CrontabCommandLogService;


@Service
@Transactional
public class CrontabCommandLogServiceImpl implements CrontabCommandLogService{

	@Autowired
	private CrontabCommandLogDao CrontabCommandLogDao;

	@Override
	public boolean editCrontabCommandLog(CrontabCommandLog crontabCommandLog) {
		// TODO Auto-generated method stub
		return this.CrontabCommandLogDao.editCrontabCommandLog(crontabCommandLog);
	}

	@Override
	public boolean addCrontabCommandLog(CrontabCommandLog crontabCommandLog) {
		// TODO Auto-generated method stub
		return this.CrontabCommandLogDao.addCrontabCommandLog(crontabCommandLog);
	}

	@Override
	public boolean delCrontabCommandLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CrontabCommandLogDao.delCrontabCommandLog(ids);
	}

	@Override
	public List<Object> findAll(CrontabCommandLog crontabCommandLog, int page_start, int page_length) {
		return CrontabCommandLogDao.findAll(crontabCommandLog, page_start, page_length);
	}

	@Override
	public CrontabCommandLog getById(Integer id) {
		return CrontabCommandLogDao.getById(id);
	}

	@Override
	public ArrayList<CrontabCommandLog> getCrontabCommandLog(Integer id) {
		return CrontabCommandLogDao.getCrontabCommandLog(id);
	}

}
