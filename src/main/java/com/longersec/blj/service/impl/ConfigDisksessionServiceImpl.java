package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigDisksessionDao;
import com.longersec.blj.domain.ConfigDisksession;
import com.longersec.blj.service.ConfigDisksessionService;


@Transactional
@Service
public class ConfigDisksessionServiceImpl implements ConfigDisksessionService{

	@Autowired
	private ConfigDisksessionDao configDisksessionDao;

	@Override
	public boolean editConfigDisksession(ConfigDisksession configDisksession) {
		// TODO Auto-generated method stub
		return this.configDisksessionDao.editConfigDisksession(configDisksession);
	}

	@Override
	public boolean addConfigDisksession(ConfigDisksession configDisksession) {
		// TODO Auto-generated method stub
		return this.configDisksessionDao.addConfigDisksession(configDisksession);
	}

	@Override
	public boolean delConfigDisksession(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.configDisksessionDao.delConfigDisksession(ids);
	}

	@Override
	public List<Object> findAll(ConfigDisksession configDisksession, int page_start, int page_length) {
		return configDisksessionDao.findAll(configDisksession, page_start, page_length);
	}
	
	@Override
	public ConfigDisksession get() {
		// TODO Auto-generated method stub
		return configDisksessionDao.get();
	}

}
