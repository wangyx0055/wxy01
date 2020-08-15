package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LogDeletePolicyGroupDao;
import com.longersec.blj.domain.LogDeletePolicyGroup;
import com.longersec.blj.service.LogDeletePolicyGroupService;


@Transactional
@Service
public class LogDeletePolicyGroupServiceImpl implements LogDeletePolicyGroupService{

	@Autowired
	private LogDeletePolicyGroupDao LogDeletePolicyGroupDao;

	@Override
	public boolean editLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyGroupDao.editLogDeletePolicyGroup(logDeletePolicyGroup);
	}

	@Override
	public boolean addLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyGroupDao.addLogDeletePolicyGroup(logDeletePolicyGroup);
	}

	@Override
	public boolean delLogDeletePolicyGroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyGroupDao.delLogDeletePolicyGroup(ids);
	}

	@Override
	public List<Object> findAll(LogDeletePolicyGroup logDeletePolicyGroup, int page_start, int page_length) {
		return LogDeletePolicyGroupDao.findAll(logDeletePolicyGroup, page_start, page_length);
	}

}
