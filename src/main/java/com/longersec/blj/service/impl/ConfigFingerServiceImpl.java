package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigFingerDao;
import com.longersec.blj.domain.ConfigFinger;
import com.longersec.blj.service.ConfigFingerService;


@Transactional
@Service
public class ConfigFingerServiceImpl implements ConfigFingerService{

	@Autowired
	private ConfigFingerDao ConfigFingerDao;

	@Override
	public boolean editConfigFinger(ConfigFinger configFinger) {
		// TODO Auto-generated method stub
		return this.ConfigFingerDao.editConfigFinger(configFinger);
	}

	@Override
	public boolean addConfigFinger(ConfigFinger configFinger) {
		// TODO Auto-generated method stub
		return this.ConfigFingerDao.addConfigFinger(configFinger);
	}

	@Override
	public boolean delConfigFinger(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigFingerDao.delConfigFinger(ids);
	}

	@Override
	public List<Object> findAll(ConfigFinger configFinger, int page_start, int page_length) {
		return ConfigFingerDao.findAll(configFinger, page_start, page_length);
	}

	@Override
	public ConfigFinger getById(Integer id) {
		// TODO Auto-generated method stub
		return ConfigFingerDao.getById(id);
	}

}
