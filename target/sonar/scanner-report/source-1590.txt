package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigLoginDao;
import com.longersec.blj.domain.ConfigLogin;
import com.longersec.blj.service.ConfigLoginService;


@Transactional
@Service
public class ConfigLoginServiceImpl implements ConfigLoginService{

	@Autowired
	private ConfigLoginDao ConfigLoginDao;

	@Override
	public boolean editConfigLogin(ConfigLogin configLogin) {
		// TODO Auto-generated method stub
		return this.ConfigLoginDao.editConfigLogin(configLogin);
	}

	@Override
	public boolean addConfigLogin(ConfigLogin configLogin) {
		// TODO Auto-generated method stub
		return this.ConfigLoginDao.addConfigLogin(configLogin);
	}

	@Override
	public boolean delConfigLogin(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigLoginDao.delConfigLogin(ids);
	}

	@Override
	public List<Object> findAll(ConfigLogin configLogin, int page_start, int page_length) {
		return ConfigLoginDao.findAll(configLogin, page_start, page_length);
	}

}
