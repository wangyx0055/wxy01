package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordLogDao;
import com.longersec.blj.domain.ChangePasswordLog;
import com.longersec.blj.service.ChangePasswordLogService;


@Transactional
@Service
public class ChangePasswordLogServiceImpl implements ChangePasswordLogService{

	@Autowired
	private ChangePasswordLogDao ChangePasswordLogDao;

	@Override
	public boolean editChangePasswordLog(ChangePasswordLog changePasswordLog) {
		// TODO Auto-generated method stub
		return this.ChangePasswordLogDao.editChangePasswordLog(changePasswordLog);
	}

	@Override
	public boolean addChangePasswordLog(ChangePasswordLog changePasswordLog) {
		// TODO Auto-generated method stub
		return this.ChangePasswordLogDao.addChangePasswordLog(changePasswordLog);
	}

	@Override
	public boolean delChangePasswordLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordLogDao.delChangePasswordLog(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordLog changePasswordLog, int page_start, int page_length) {
		return ChangePasswordLogDao.findAll(changePasswordLog, page_start, page_length);
	}

}
