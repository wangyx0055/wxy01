package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigSyslogDao;
import com.longersec.blj.domain.ConfigSyslog;
import com.longersec.blj.service.ConfigSyslogService;


@Transactional
@Service
public class ConfigSyslogServiceImpl implements ConfigSyslogService{

	@Autowired
	private ConfigSyslogDao ConfigSyslogDao;

	@Override
	public boolean editConfigSyslog(ConfigSyslog configSyslog) {
		// TODO Auto-generated method stub
		return this.ConfigSyslogDao.editConfigSyslog(configSyslog);
	}

	@Override
	public boolean addConfigSyslog(ConfigSyslog configSyslog) {
		// TODO Auto-generated method stub
		return this.ConfigSyslogDao.addConfigSyslog(configSyslog);
	}

	@Override
	public boolean delConfigSyslog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigSyslogDao.delConfigSyslog(ids);
	}

	@Override
	public List<Object> findAll(ConfigSyslog configSyslog, int page_start, int page_length) {
		return ConfigSyslogDao.findAll(configSyslog, page_start, page_length);
	}

}
