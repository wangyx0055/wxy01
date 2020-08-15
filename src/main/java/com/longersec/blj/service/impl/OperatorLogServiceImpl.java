package com.longersec.blj.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.OperatorLogDao;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.OperatorLogService;


@Transactional
@Service
public class OperatorLogServiceImpl implements OperatorLogService{

	@Autowired
	private OperatorLogDao OperatorLogDao;

	@Override
	public boolean editOperatorLog(OperatorLog operatorLog) {
		// TODO Auto-generated method stub
		return this.OperatorLogDao.editOperatorLog(operatorLog);
	}

	@Override
	public boolean addOperatorLog(OperatorLog operatorLog) {
		// TODO Auto-generated method stub
		return this.OperatorLogDao.addOperatorLog(operatorLog);
	}

	@Override
	public boolean delOperatorLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.OperatorLogDao.delOperatorLog(ids);
	}

	@Override
	public List<Object> findAll(OperatorLog operatorLog, int page_start, int page_length) {
		return OperatorLogDao.findAll(operatorLog, page_start, page_length);
	}

	@Override
	public List<Map<String, Object>> selectByDay() {
		// TODO Auto-generated method stub
		return OperatorLogDao.selectByDay();
	}

	@Override
	public List<Map<String, Object>> selectByUser() {
		// TODO Auto-generated method stub
		return OperatorLogDao.selectByUser();
	}

}
