package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LogDeletePolicyUserDao;
import com.longersec.blj.domain.LogDeletePolicyUser;
import com.longersec.blj.service.LogDeletePolicyUserService;


@Transactional
@Service
public class LogDeletePolicyUserServiceImpl implements LogDeletePolicyUserService{

	@Autowired
	private LogDeletePolicyUserDao LogDeletePolicyUserDao;

	@Override
	public boolean editLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyUserDao.editLogDeletePolicyUser(logDeletePolicyUser);
	}

	@Override
	public boolean addLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyUserDao.addLogDeletePolicyUser(logDeletePolicyUser);
	}

	@Override
	public boolean delLogDeletePolicyUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyUserDao.delLogDeletePolicyUser(ids);
	}

	@Override
	public List<Object> findAll(LogDeletePolicyUser logDeletePolicyUser, int page_start, int page_length) {
		return LogDeletePolicyUserDao.findAll(logDeletePolicyUser, page_start, page_length);
	}

}
