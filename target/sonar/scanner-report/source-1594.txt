package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigRadiusDao;
import com.longersec.blj.domain.ConfigRadius;
import com.longersec.blj.service.ConfigRadiusService;


@Transactional
@Service
public class ConfigRadiusServiceImpl implements ConfigRadiusService{

	@Autowired
	private ConfigRadiusDao ConfigRadiusDao;

	@Override
	public boolean editConfigRadius(ConfigRadius configRadius) {
		// TODO Auto-generated method stub
		return this.ConfigRadiusDao.editConfigRadius(configRadius);
	}

	@Override
	public boolean addConfigRadius(ConfigRadius configRadius) {
		// TODO Auto-generated method stub
		return this.ConfigRadiusDao.addConfigRadius(configRadius);
	}

	@Override
	public boolean delConfigRadius(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigRadiusDao.delConfigRadius(ids);
	}

	@Override
	public List<Object> findAll(ConfigRadius configRadius, int page_start, int page_length) {
		return ConfigRadiusDao.findAll(configRadius, page_start, page_length);
	}

}
