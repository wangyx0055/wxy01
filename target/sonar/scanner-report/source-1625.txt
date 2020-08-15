package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LogDeletePolicyApppubDao;
import com.longersec.blj.domain.LogDeletePolicyApppub;
import com.longersec.blj.service.LogDeletePolicyApppubService;


@Transactional
@Service
public class LogDeletePolicyApppubServiceImpl implements LogDeletePolicyApppubService{

	@Autowired
	private LogDeletePolicyApppubDao LogDeletePolicyApppubDao;

	@Override
	public boolean editLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyApppubDao.editLogDeletePolicyApppub(logDeletePolicyApppub);
	}

	@Override
	public boolean addLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyApppubDao.addLogDeletePolicyApppub(logDeletePolicyApppub);
	}

	@Override
	public boolean delLogDeletePolicyApppub(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyApppubDao.delLogDeletePolicyApppub(ids);
	}

	@Override
	public List<Object> findAll(LogDeletePolicyApppub logDeletePolicyApppub, int page_start, int page_length) {
		return LogDeletePolicyApppubDao.findAll(logDeletePolicyApppub, page_start, page_length);
	}

}
