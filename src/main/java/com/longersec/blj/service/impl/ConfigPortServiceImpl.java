package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigPortDao;
import com.longersec.blj.domain.ConfigPort;
import com.longersec.blj.service.ConfigPortService;


@Transactional
@Service
public class ConfigPortServiceImpl implements ConfigPortService{

	@Autowired
	private ConfigPortDao ConfigPortDao;

	@Override
	public boolean editConfigPort(ConfigPort configPort) {
		// TODO Auto-generated method stub
		return this.ConfigPortDao.editConfigPort(configPort);
	}

	@Override
	public boolean addConfigPort(ConfigPort configPort) {
		// TODO Auto-generated method stub
		return this.ConfigPortDao.addConfigPort(configPort);
	}

	@Override
	public boolean delConfigPort(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigPortDao.delConfigPort(ids);
	}

	@Override
	public List<Object> findAll(ConfigPort configPort, int page_start, int page_length) {
		return ConfigPortDao.findAll(configPort, page_start, page_length);
	}

}
