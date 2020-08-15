package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.DTO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AccessPolicyUserDao;
import com.longersec.blj.domain.AccessPolicyUser;
import com.longersec.blj.service.AccessPolicyUserService;


@Transactional
@Service
public class AccessPolicyUserServiceImpl implements AccessPolicyUserService{
	@Autowired
	private AccessPolicyUserDao AccessPolicyUserDao;

	@Override
	public boolean editAccessPolicyUser(Integer policy_id,List<Integer> users) {
		// TODO Auto-generated method stub
		return this.AccessPolicyUserDao.editAccessPolicyUser(policy_id,users);
	}

	@Override
	public boolean addAccessPolicyUser(Integer policy_id,List<Integer> users) {
		// TODO Auto-generated method stub

		return this.AccessPolicyUserDao.addAccessPolicyUser(policy_id,users);
	}

	@Override
	public boolean delAccessPolicyUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.AccessPolicyUserDao.delAccessPolicyUser(ids);
	}

	@Override
	public List<Object> findAll(AccessPolicyUser accessPolicyUser, int page_start, int page_length) {
		return AccessPolicyUserDao.findAll(accessPolicyUser, page_start, page_length);
	}
	
	@Override
	public List<Users> selectById(Integer policy_id) {
		return this.AccessPolicyUserDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		return this.AccessPolicyUserDao.deleteBypolicy_id(policy_id);
	}

	@Override
	public int selectAccessPolicyUserCounts(int policy_id) {
		return AccessPolicyUserDao.selectAccessPolicyUserCounts(policy_id);
	}
}
