package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.service.ConfigPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabScriptConfigDao;
import com.longersec.blj.domain.CrontabScriptConfig;
import com.longersec.blj.service.CrontabScriptConfigService;


@Transactional
@Service
public class CrontabScriptConfigServiceImpl implements CrontabScriptConfigService{

	@Autowired
	private CrontabScriptConfigDao CrontabScriptConfigDao;

	@Override
	public boolean editCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig) {
		// TODO Auto-generated method stub
		return this.CrontabScriptConfigDao.editCrontabScriptConfig(crontabScriptConfig);
	}

	@Override
	public boolean addCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig) {
		// TODO Auto-generated method stub
		return this.CrontabScriptConfigDao.addCrontabScriptConfig(crontabScriptConfig);
	}

	@Override
	public boolean delCrontabScriptConfig(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CrontabScriptConfigDao.delCrontabScriptConfig(ids);
	}

	@Override
	public List<Object> findAll(CrontabScriptConfig crontabScriptConfig, int page_start, int page_length,List<Integer> depart_ids) {
		return CrontabScriptConfigDao.findAll(crontabScriptConfig, page_start, page_length,depart_ids);
	}

	@Override
	public String selectName(Integer id, String name) {
		return CrontabScriptConfigDao.selectName(id,name);
	}

	@Override
	public CrontabScriptConfig checkname(String name) {
		return CrontabScriptConfigDao.checkname(name);
	}

}
