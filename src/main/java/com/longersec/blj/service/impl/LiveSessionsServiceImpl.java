package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LiveSessionsDao;
import com.longersec.blj.domain.LiveSessions;
import com.longersec.blj.service.LiveSessionsService;


@Transactional
@Service
public class LiveSessionsServiceImpl implements LiveSessionsService{

	@Autowired
	private LiveSessionsDao LiveSessionsDao;

	@Override
	public boolean editLiveSessions(LiveSessions liveSessions) {
		// TODO Auto-generated method stub
		return this.LiveSessionsDao.editLiveSessions(liveSessions);
	}

	@Override
	public boolean addLiveSessions(LiveSessions liveSessions) {
		// TODO Auto-generated method stub
		return this.LiveSessionsDao.addLiveSessions(liveSessions);
	}

	@Override
	public boolean delLiveSessions(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LiveSessionsDao.delLiveSessions(ids);
	}
	
	@Override
	public boolean updateKey(LiveSessions liveSessions) {
		return this.LiveSessionsDao.updateKey(liveSessions);
	}

	@Override
	public List<Object> findAll(LiveSessions liveSessions, int page_start, int page_length) {
		return LiveSessionsDao.findAll(liveSessions, page_start, page_length);
	}

	@Override
	public LiveSessions getById(Integer live_id) {
		// TODO Auto-generated method stub
		return LiveSessionsDao.getById(live_id);
	}

}
