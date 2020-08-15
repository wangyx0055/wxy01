package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigDbbackupDao;
import com.longersec.blj.domain.ConfigDbbackup;
import com.longersec.blj.service.ConfigDbbackupService;


@Transactional
@Service
public class ConfigDbbackupServiceImpl implements ConfigDbbackupService{

	@Autowired
	private ConfigDbbackupDao ConfigDbbackupDao;

	@Override
	public boolean editConfigDbbackup(ConfigDbbackup configDbbackup) {
		// TODO Auto-generated method stub
		return this.ConfigDbbackupDao.editConfigDbbackup(configDbbackup);
	}

	@Override
	public boolean addConfigDbbackup(ConfigDbbackup configDbbackup) {
		// TODO Auto-generated method stub
		return this.ConfigDbbackupDao.addConfigDbbackup(configDbbackup);
	}

	@Override
	public boolean delConfigDbbackup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigDbbackupDao.delConfigDbbackup(ids);
	}

	@Override
	public List<Object> findAll(ConfigDbbackup configDbbackup, int page_start, int page_length) {
		return ConfigDbbackupDao.findAll(configDbbackup, page_start, page_length);
	}

	@Override
	public ConfigDbbackup getById(Integer id) {
		// TODO Auto-generated method stub
		return ConfigDbbackupDao.getById(id);
	}

}
