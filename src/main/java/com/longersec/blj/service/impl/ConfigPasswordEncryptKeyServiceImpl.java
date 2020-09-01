package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigPasswordEncryptKeyDao;
import com.longersec.blj.domain.ConfigPasswordEncryptKey;
import com.longersec.blj.service.ConfigPasswordEncryptKeyService;


@Service
@Transactional
public class ConfigPasswordEncryptKeyServiceImpl implements ConfigPasswordEncryptKeyService{

	@Autowired
	private ConfigPasswordEncryptKeyDao ConfigPasswordEncryptKeyDao;

	@Override
	public boolean editConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey) {
		// TODO Auto-generated method stub
		return this.ConfigPasswordEncryptKeyDao.editConfigPasswordEncryptKey(configPasswordEncryptKey);
	}

	@Override
	public boolean addConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey) {
		// TODO Auto-generated method stub
		return this.ConfigPasswordEncryptKeyDao.addConfigPasswordEncryptKey(configPasswordEncryptKey);
	}

	@Override
	public boolean delConfigPasswordEncryptKey(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigPasswordEncryptKeyDao.delConfigPasswordEncryptKey(ids);
	}

	@Override
	public List<Object> findAll(ConfigPasswordEncryptKey configPasswordEncryptKey, int page_start, int page_length) {
		return ConfigPasswordEncryptKeyDao.findAll(configPasswordEncryptKey, page_start, page_length);
	}

	@Override
	public ConfigPasswordEncryptKey getById(Integer id) {
		return ConfigPasswordEncryptKeyDao.getById(id);
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return ConfigPasswordEncryptKeyDao.getKey();
	}

}
