package com.longersec.blj.service.impl;

import com.longersec.blj.dao.AccessPolicyApppubDao;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.service.AccessPolicyApppubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class AccessPolicyApppubServiceImpl implements AccessPolicyApppubService{

	@Autowired
	private AccessPolicyApppubDao AccessPolicyApppubDao;

	@Override
	public Boolean editAccessPolicyApppub(Integer policy_id,List<Integer> app) {
		return this.AccessPolicyApppubDao.editAccessPolicyApppub(policy_id,app);
	}

	@Override
	public Boolean addAccessPolicyApppub(Integer policy_id,List<Integer> app) {
		return this.AccessPolicyApppubDao.addAccessPolicyApppub(policy_id,app);
	}

	@Override
	public List<App> selectById(Integer policy_id) {
		return this.AccessPolicyApppubDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		return this.AccessPolicyApppubDao.deleteBypolicy_id(policy_id);
	}

}
