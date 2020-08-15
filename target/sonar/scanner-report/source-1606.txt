package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabScriptDao;
import com.longersec.blj.domain.CrontabScript;
import com.longersec.blj.service.CrontabScriptService;


@Transactional
@Service
public class CrontabScriptServiceImpl implements CrontabScriptService{

	@Autowired
	private CrontabScriptDao CrontabScriptDao;

	@Override
	public boolean editCrontabScript(CrontabScript crontabScript) {
		// TODO Auto-generated method stub
		return this.CrontabScriptDao.editCrontabScript(crontabScript);
	}

	@Override
	public boolean addCrontabScript(CrontabScript crontabScript) {
		// TODO Auto-generated method stub
		return this.CrontabScriptDao.addCrontabScript(crontabScript);
	}

	@Override
	public boolean delCrontabScript(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CrontabScriptDao.delCrontabScript(ids);
	}

	@Override
	public List<Object> findAll(CrontabScript crontabScript, int page_start, int page_length) {
		return CrontabScriptDao.findAll(crontabScript, page_start, page_length);
	}

	@Override
	public CrontabScript getById(int id) {
		// TODO Auto-generated method stub
		return CrontabScriptDao.getById(id);
	}

}
