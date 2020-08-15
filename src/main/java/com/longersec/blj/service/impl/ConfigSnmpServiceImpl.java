package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigSnmpDao;
import com.longersec.blj.domain.ConfigSnmp;
import com.longersec.blj.service.ConfigSnmpService;


@Transactional
@Service
public class ConfigSnmpServiceImpl implements ConfigSnmpService{

	@Autowired
	private ConfigSnmpDao ConfigSnmpDao;

	@Override
	public boolean editConfigSnmp(ConfigSnmp configSnmp) {
		// TODO Auto-generated method stub
		return this.ConfigSnmpDao.editConfigSnmp(configSnmp);
	}

	@Override
	public boolean addConfigSnmp(ConfigSnmp configSnmp) {
		// TODO Auto-generated method stub
		return this.ConfigSnmpDao.addConfigSnmp(configSnmp);
	}

	@Override
	public boolean delConfigSnmp(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigSnmpDao.delConfigSnmp(ids);
	}

	@Override
	public List<Object> findAll(ConfigSnmp configSnmp, int page_start, int page_length) {
		return ConfigSnmpDao.findAll(configSnmp, page_start, page_length);
	}

}
