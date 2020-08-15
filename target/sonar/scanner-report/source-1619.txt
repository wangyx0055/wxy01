package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.GConnectionDao;
import com.longersec.blj.domain.GConnection;
import com.longersec.blj.service.GConnectionService;


@Transactional
@Service
public class GConnectionServiceImpl implements GConnectionService{

	@Autowired
	private GConnectionDao GConnectionDao;

	@Override
	public boolean editGConnection(GConnection gConnection) {
		// TODO Auto-generated method stub
		return this.GConnectionDao.editGConnection(gConnection);
	}

	@Override
	public boolean addGConnection(GConnection gConnection) {
		// TODO Auto-generated method stub
		return this.GConnectionDao.addGConnection(gConnection);
	}

	@Override
	public boolean delGConnection(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.GConnectionDao.delGConnection(ids);
	}

	@Override
	public List<Object> findAll(GConnection gConnection, int page_start, int page_length) {
		return GConnectionDao.findAll(gConnection, page_start, page_length);
	}

}
