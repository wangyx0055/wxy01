package com.longersec.blj.service.impl;

import com.longersec.blj.dao.ConfigWorkorderDao;
import com.longersec.blj.domain.ConfigWorkorder;
import com.longersec.blj.service.ConfigWorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ConfigWorkorderServiceImpl implements ConfigWorkorderService{

	@Autowired
	private ConfigWorkorderDao ConfigWorkorderDao;

	@Override
	public boolean editConfigWorkorder(ConfigWorkorder configWorkorder) {
		return this.ConfigWorkorderDao.editConfigWorkorder(configWorkorder);
	}

	@Override
	public ConfigWorkorder getById(Integer id) {
		return ConfigWorkorderDao.getById(id);
	}

}
