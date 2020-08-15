package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AlertLogDao;
import com.longersec.blj.domain.AlertLog;
import com.longersec.blj.service.AlertLogService;


@Transactional
@Service
public class AlertLogServiceImpl implements AlertLogService{

	@Autowired
	private AlertLogDao AlertLogDao;

	@Override
	public boolean editAlertLog(AlertLog alertLog) {
		// TODO Auto-generated method stub
		return this.AlertLogDao.editAlertLog(alertLog);
	}

	@Override
	public boolean addAlertLog(AlertLog alertLog) {
		// TODO Auto-generated method stub
		return this.AlertLogDao.addAlertLog(alertLog);
	}

	@Override
	public boolean delAlertLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.AlertLogDao.delAlertLog(ids);
	}

	@Override
	public List<Object> findAll(AlertLog alertLog, int page_start, int page_length) {
		return AlertLogDao.findAll(alertLog, page_start, page_length);
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return AlertLogDao.total();
	}

}
