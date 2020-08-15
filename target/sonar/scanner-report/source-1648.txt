package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SyslogDao;
import com.longersec.blj.domain.Syslog;
import com.longersec.blj.service.SyslogService;


@Transactional
@Service
public class SyslogServiceImpl implements SyslogService{

	@Autowired
	private SyslogDao SyslogDao;

	@Override
	public boolean editSyslog(Syslog syslog) {
		// TODO Auto-generated method stub
		return this.SyslogDao.editSyslog(syslog);
	}

	@Override
	public boolean addSyslog(Syslog syslog) {
		// TODO Auto-generated method stub
		return this.SyslogDao.addSyslog(syslog);
	}

	@Override
	public boolean delSyslog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.SyslogDao.delSyslog(ids);
	}

	@Override
	public List<Object> findAll(Syslog syslog, int page_start, int page_length) {
		return SyslogDao.findAll(syslog, page_start, page_length);
	}

}
