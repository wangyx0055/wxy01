package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigDao;
import com.longersec.blj.domain.Config;
import com.longersec.blj.service.ConfigService;


@Transactional
@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired
	private ConfigDao ConfigDao;

	@Override
	public boolean editConfig(Config config) {
		// TODO Auto-generated method stub
		return this.ConfigDao.editConfig(config);
	}

	@Override
	public boolean addConfig(Config config) {
		// TODO Auto-generated method stub
		return this.ConfigDao.addConfig(config);
	}

	@Override
	public boolean delConfig(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigDao.delConfig(ids);
	}

	@Override
	public List<Object> findAll(Config config, int page_start, int page_length) {
		return ConfigDao.findAll(config, page_start, page_length);
	}

	@Override
	public Config getByName(String name) {
		// TODO Auto-generated method stub
		return ConfigDao.getByName(name);
	}

	@Override
	public Integer query(String sql) {
		// TODO Auto-generated method stub
		return ConfigDao.query(sql);
	}

}
