package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigLdapAdDao;
import com.longersec.blj.domain.ConfigLdapAd;
import com.longersec.blj.service.ConfigLdapAdService;


@Transactional
@Service
public class ConfigLdapAdServiceImpl implements ConfigLdapAdService{

	@Autowired
	private ConfigLdapAdDao ConfigLdapAdDao;

	@Override
	public boolean editConfigLdapAd(ConfigLdapAd configLdapAd) {
		// TODO Auto-generated method stub
		return this.ConfigLdapAdDao.editConfigLdapAd(configLdapAd);
	}

	@Override
	public boolean addConfigLdapAd(ConfigLdapAd configLdapAd) {
		// TODO Auto-generated method stub
		return this.ConfigLdapAdDao.addConfigLdapAd(configLdapAd);
	}

	@Override
	public boolean delConfigLdapAd(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigLdapAdDao.delConfigLdapAd(ids);
	}

	@Override
	public List<Object> findAll(ConfigLdapAd configLdapAd, int page_start, int page_length) {
		return ConfigLdapAdDao.findAll(configLdapAd, page_start, page_length);
	}

	@Override
	public ConfigLdapAd getConfigLdapById(int id) {
		return ConfigLdapAdDao.getConfigLdapById(id);
	}

}
