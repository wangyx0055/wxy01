package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigSmsDao;
import com.longersec.blj.domain.ConfigSms;
import com.longersec.blj.service.ConfigSmsService;


@Transactional
@Service
public class ConfigSmsServiceImpl implements ConfigSmsService{

	@Autowired
	private ConfigSmsDao ConfigSmsDao;

	@Override
	public boolean editConfigSms(ConfigSms configSms) {
		// TODO Auto-generated method stub
		return this.ConfigSmsDao.editConfigSms(configSms);
	}

	@Override
	public boolean addConfigSms(ConfigSms configSms) {
		// TODO Auto-generated method stub
		return this.ConfigSmsDao.addConfigSms(configSms);
	}

	@Override
	public boolean delConfigSms(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigSmsDao.delConfigSms(ids);
	}

	@Override
	public List<Object> findAll(ConfigSms configSms, int page_start, int page_length) {
		return ConfigSmsDao.findAll(configSms, page_start, page_length);
	}

}
