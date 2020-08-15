package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.EmailLogDao;
import com.longersec.blj.domain.EmailLog;
import com.longersec.blj.service.EmailLogService;


@Service
@Transactional
public class EmailLogServiceImpl implements EmailLogService{

	@Autowired
	private EmailLogDao EmailLogDao;

	@Override
	public boolean editEmailLog(EmailLog emailLog) {
		// TODO Auto-generated method stub
		return this.EmailLogDao.editEmailLog(emailLog);
	}

	@Override
	public boolean addEmailLog(EmailLog emailLog) {
		// TODO Auto-generated method stub
		return this.EmailLogDao.addEmailLog(emailLog);
	}

	@Override
	public boolean delEmailLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.EmailLogDao.delEmailLog(ids);
	}

	@Override
	public List<Object> findAll(EmailLog emailLog, int page_start, int page_length) {
		return EmailLogDao.findAll(emailLog, page_start, page_length);
	}

	@Override
	public EmailLog getById(Integer id) {
		return EmailLogDao.getById(id);
	}

}
