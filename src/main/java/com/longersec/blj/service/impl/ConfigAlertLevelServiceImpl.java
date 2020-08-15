package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigAlertLevelDao;
import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.service.ConfigAlertLevelService;


@Service
@Transactional
public class ConfigAlertLevelServiceImpl implements ConfigAlertLevelService{

	@Autowired
	private ConfigAlertLevelDao ConfigAlertLevelDao;

	@Override
	public boolean editConfigAlertLevel(ConfigAlertLevel configAlertLevel) {
		// TODO Auto-generated method stub
		return this.ConfigAlertLevelDao.editConfigAlertLevel(configAlertLevel);
	}

	@Override
	public boolean addConfigAlertLevel(ConfigAlertLevel configAlertLevel) {
		// TODO Auto-generated method stub
		return this.ConfigAlertLevelDao.addConfigAlertLevel(configAlertLevel);
	}

	@Override
	public boolean delConfigAlertLevel(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigAlertLevelDao.delConfigAlertLevel(ids);
	}

	@Override
	public List<Object> findAll(ConfigAlertLevel configAlertLevel, int page_start, int page_length) {
		return ConfigAlertLevelDao.findAll(configAlertLevel, page_start, page_length);
	}

	@Override
	public ConfigAlertLevel getById(Integer id) {
		return ConfigAlertLevelDao.getById(id);
	}

}
