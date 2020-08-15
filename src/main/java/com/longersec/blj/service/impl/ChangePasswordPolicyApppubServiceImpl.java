package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyApppubDao;
import com.longersec.blj.domain.ChangePasswordPolicyApppub;
import com.longersec.blj.service.ChangePasswordPolicyApppubService;


@Transactional
@Service
public class ChangePasswordPolicyApppubServiceImpl implements ChangePasswordPolicyApppubService{

	@Autowired
	private ChangePasswordPolicyApppubDao ChangePasswordPolicyApppubDao;

	@Override
	public boolean editChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubDao.editChangePasswordPolicyApppub(changePasswordPolicyApppub);
	}

	@Override
	public boolean addChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubDao.addChangePasswordPolicyApppub(changePasswordPolicyApppub);
	}

	@Override
	public boolean delChangePasswordPolicyApppub(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubDao.delChangePasswordPolicyApppub(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyApppub changePasswordPolicyApppub, int page_start, int page_length) {
		return ChangePasswordPolicyApppubDao.findAll(changePasswordPolicyApppub, page_start, page_length);
	}

}
