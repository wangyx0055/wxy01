package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SessionHanddeleteDao;
import com.longersec.blj.domain.SessionHanddelete;
import com.longersec.blj.service.SessionHanddeleteService;


@Transactional
@Service
public class SessionHanddeleteServiceImpl implements SessionHanddeleteService{

	@Autowired
	private SessionHanddeleteDao SessionHanddeleteDao;

	@Override
	public boolean editSessionHanddelete(SessionHanddelete sessionHanddelete) {
		// TODO Auto-generated method stub
		return this.SessionHanddeleteDao.editSessionHanddelete(sessionHanddelete);
	}

	@Override
	public boolean addSessionHanddelete(SessionHanddelete sessionHanddelete) {
		// TODO Auto-generated method stub
		return this.SessionHanddeleteDao.addSessionHanddelete(sessionHanddelete);
	}

	@Override
	public boolean delSessionHanddelete(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.SessionHanddeleteDao.delSessionHanddelete(ids);
	}

	@Override
	public List<Object> findAll(SessionHanddelete sessionHanddelete, int page_start, int page_length) {
		return SessionHanddeleteDao.findAll(sessionHanddelete, page_start, page_length);
	}

}
