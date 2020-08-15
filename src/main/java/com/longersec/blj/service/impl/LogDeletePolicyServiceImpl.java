package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LogDeletePolicyDao;
import com.longersec.blj.domain.LogDeletePolicy;
import com.longersec.blj.service.LogDeletePolicyService;


@Transactional
@Service
public class LogDeletePolicyServiceImpl implements LogDeletePolicyService{

	@Autowired
	private LogDeletePolicyDao LogDeletePolicyDao;

	@Override
	public boolean editLogDeletePolicy(LogDeletePolicy logDeletePolicy) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDao.editLogDeletePolicy(logDeletePolicy);
	}

	@Override
	public boolean addLogDeletePolicy(LogDeletePolicy logDeletePolicy) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDao.addLogDeletePolicy(logDeletePolicy);
	}

	@Override
	public boolean delLogDeletePolicy(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDao.delLogDeletePolicy(ids);
	}

	@Override
	public List<Object> findAll(LogDeletePolicy logDeletePolicy,String name, int page_start, int page_length) {
		return LogDeletePolicyDao.findAll(logDeletePolicy,name, page_start, page_length);
	}

}
