package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SessionAutodeleteDao;
import com.longersec.blj.domain.SessionAutodelete;
import com.longersec.blj.service.SessionAutodeleteService;


@Transactional
@Service
public class SessionAutodeleteServiceImpl implements SessionAutodeleteService{

	@Autowired
	private SessionAutodeleteDao SessionAutodeleteDao;

	@Override
	public boolean editSessionAutodelete(SessionAutodelete sessionAutodelete) {
		// TODO Auto-generated method stub
		return this.SessionAutodeleteDao.editSessionAutodelete(sessionAutodelete);
	}

	@Override
	public boolean addSessionAutodelete(SessionAutodelete sessionAutodelete) {
		// TODO Auto-generated method stub
		return this.SessionAutodeleteDao.addSessionAutodelete(sessionAutodelete);
	}

	@Override
	public boolean delSessionAutodelete(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.SessionAutodeleteDao.delSessionAutodelete(ids);
	}

	@Override
	public List<Object> findAll(SessionAutodelete sessionAutodelete, int page_start, int page_length) {
		return SessionAutodeleteDao.findAll(sessionAutodelete, page_start, page_length);
	}

}
