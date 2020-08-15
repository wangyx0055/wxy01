package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AccessPolicyApppubDao;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.AccessPolicyApppubService;


@Transactional
@Service
public class AccessPolicyApppubServiceImpl implements AccessPolicyApppubService{

	@Autowired
	private AccessPolicyApppubDao AccessPolicyApppubDao;

	@Override
	public boolean editAccessPolicyApppub(Integer policy_id,List<Integer> app) {
		// TODO Auto-generated method stub
		return this.AccessPolicyApppubDao.editAccessPolicyApppub(policy_id,app);
	}

	@Override
	public boolean addAccessPolicyApppub(Integer policy_id,List<Integer> app) {
		// TODO Auto-generated method stub
		return this.AccessPolicyApppubDao.addAccessPolicyApppub(policy_id,app);
	}

	@Override
	public boolean delAccessPolicyApppub(List<String> ids) {
		// TODO Auto-generated method stub
		return this.AccessPolicyApppubDao.delAccessPolicyApppub(ids);
	}

	@Override
	public List<Object> findAll(AccessPolicyApppub accessPolicyApppub, int page_start, int page_length) {
		return AccessPolicyApppubDao.findAll(accessPolicyApppub, page_start, page_length);
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
