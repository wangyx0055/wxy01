package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigRouteDao;
import com.longersec.blj.domain.ConfigRoute;
import com.longersec.blj.service.ConfigRouteService;


@Transactional
@Service
public class ConfigRouteServiceImpl implements ConfigRouteService{

	@Autowired
	private ConfigRouteDao ConfigRouteDao;

	@Override
	public boolean editConfigRoute(ConfigRoute configRoute) {
		// TODO Auto-generated method stub
		return this.ConfigRouteDao.editConfigRoute(configRoute);
	}

	@Override
	public boolean addConfigRoute(ConfigRoute configRoute) {
		// TODO Auto-generated method stub
		return this.ConfigRouteDao.addConfigRoute(configRoute);
	}

	@Override
	public boolean delConfigRoute(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigRouteDao.delConfigRoute(ids);
	}

	@Override
	public List<Object> findAll(ConfigRoute configRoute, int page_start, int page_length) {
		return ConfigRouteDao.findAll(configRoute, page_start, page_length);
	}

	@Override
	public ConfigRoute getById(Integer id) {
		return ConfigRouteDao.getById(id);
	}

	@Override
	public int checkip(Integer id,String destion_ip) {
		return  ConfigRouteDao.checkip(id,destion_ip);
	}

}
