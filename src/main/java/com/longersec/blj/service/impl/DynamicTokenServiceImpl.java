package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DynamicTokenDao;
import com.longersec.blj.domain.DynamicToken;
import com.longersec.blj.service.DynamicTokenService;


@Transactional
@Service
public class DynamicTokenServiceImpl implements DynamicTokenService{

	@Autowired
	private DynamicTokenDao DynamicTokenDao;

	@Override
	public boolean editDynamicToken(DynamicToken dynamicToken) {
		// TODO Auto-generated method stub
		return this.DynamicTokenDao.editDynamicToken(dynamicToken);
	}

	@Override
	public boolean addDynamicToken(DynamicToken dynamicToken) {
		// TODO Auto-generated method stub
		return this.DynamicTokenDao.addDynamicToken(dynamicToken);
	}

	@Override
	public boolean delDynamicToken(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.DynamicTokenDao.delDynamicToken(ids);
	}

	@Override
	public List<Object> findAll(DynamicToken dynamicToken, int page_start, int page_length) {
		return DynamicTokenDao.findAll(dynamicToken, page_start, page_length);
	}

}
