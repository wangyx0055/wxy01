package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.GConnectionHistoryDao;
import com.longersec.blj.domain.GConnectionHistory;
import com.longersec.blj.service.GConnectionHistoryService;


@Transactional
@Service
public class GConnectionHistoryServiceImpl implements GConnectionHistoryService{

	@Autowired
	private GConnectionHistoryDao GConnectionHistoryDao;

	@Override
	public boolean editGConnectionHistory(GConnectionHistory gConnectionHistory) {
		// TODO Auto-generated method stub
		return this.GConnectionHistoryDao.editGConnectionHistory(gConnectionHistory);
	}

	@Override
	public boolean addGConnectionHistory(GConnectionHistory gConnectionHistory) {
		// TODO Auto-generated method stub
		return this.GConnectionHistoryDao.addGConnectionHistory(gConnectionHistory);
	}

	@Override
	public boolean delGConnectionHistory(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.GConnectionHistoryDao.delGConnectionHistory(ids);
	}

	@Override
	public List<Object> findAll(GConnectionHistory gConnectionHistory, int page_start, int page_length) {
		return GConnectionHistoryDao.findAll(gConnectionHistory, page_start, page_length);
	}

}
