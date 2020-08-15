package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigEmailDao;
import com.longersec.blj.domain.ConfigEmail;
import com.longersec.blj.service.ConfigEmailService;


@Transactional
@Service
public class ConfigEmailServiceImpl implements ConfigEmailService{

	@Autowired
	private ConfigEmailDao ConfigEmailDao;

	@Override
	public boolean editConfigEmail(ConfigEmail configEmail) {
		// TODO Auto-generated method stub
		return this.ConfigEmailDao.editConfigEmail(configEmail);
	}

	@Override
	public boolean addConfigEmail(ConfigEmail configEmail) {
		// TODO Auto-generated method stub
		return this.ConfigEmailDao.addConfigEmail(configEmail);
	}

	@Override
	public boolean delConfigEmail(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigEmailDao.delConfigEmail(ids);
	}

	@Override
	public List<Object> findAll(ConfigEmail configEmail, int page_start, int page_length) {
		return ConfigEmailDao.findAll(configEmail, page_start, page_length);
	}

}
