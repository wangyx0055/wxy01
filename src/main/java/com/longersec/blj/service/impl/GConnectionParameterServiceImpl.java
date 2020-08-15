package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.GConnectionParameterDao;
import com.longersec.blj.domain.GConnectionParameter;
import com.longersec.blj.service.GConnectionParameterService;


@Transactional
@Service
public class GConnectionParameterServiceImpl implements GConnectionParameterService{

	@Autowired
	private GConnectionParameterDao GConnectionParameterDao;

	@Override
	public boolean editGConnectionParameter(GConnectionParameter gConnectionParameter) {
		// TODO Auto-generated method stub
		return this.GConnectionParameterDao.editGConnectionParameter(gConnectionParameter);
	}

	@Override
	public boolean addGConnectionParameter(GConnectionParameter gConnectionParameter) {
		// TODO Auto-generated method stub
		return this.GConnectionParameterDao.addGConnectionParameter(gConnectionParameter);
	}

	@Override
	public boolean delGConnectionParameter(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.GConnectionParameterDao.delGConnectionParameter(ids);
	}

	@Override
	public List<Object> findAll(GConnectionParameter gConnectionParameter, int page_start, int page_length) {
		return GConnectionParameterDao.findAll(gConnectionParameter, page_start, page_length);
	}

}
