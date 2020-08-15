package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyUserDao;
import com.longersec.blj.domain.ChangePasswordPolicyUser;
import com.longersec.blj.service.ChangePasswordPolicyUserService;


@Transactional
@Service
public class ChangePasswordPolicyUserServiceImpl implements ChangePasswordPolicyUserService{

	@Autowired
	private ChangePasswordPolicyUserDao ChangePasswordPolicyUserDao;

	@Override
	public boolean editChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyUserDao.editChangePasswordPolicyUser(changePasswordPolicyUser);
	}

	@Override
	public boolean addChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyUserDao.addChangePasswordPolicyUser(changePasswordPolicyUser);
	}

	@Override
	public boolean delChangePasswordPolicyUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyUserDao.delChangePasswordPolicyUser(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyUser changePasswordPolicyUser, int page_start, int page_length) {
		return ChangePasswordPolicyUserDao.findAll(changePasswordPolicyUser, page_start, page_length);
	}

}
