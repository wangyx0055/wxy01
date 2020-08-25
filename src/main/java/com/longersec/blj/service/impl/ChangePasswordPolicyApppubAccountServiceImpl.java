package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyApppubAccountDao;
import com.longersec.blj.domain.ChangePasswordPolicyApppubAccount;
import com.longersec.blj.service.ChangePasswordPolicyApppubAccountService;


@Transactional
@Service
public class ChangePasswordPolicyApppubAccountServiceImpl implements ChangePasswordPolicyApppubAccountService{

	@Autowired
	private ChangePasswordPolicyApppubAccountDao ChangePasswordPolicyApppubAccountDao;

	@Override
	public boolean editChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubAccountDao.editChangePasswordPolicyApppubAccount(changePasswordPolicyApppubAccount);
	}

	@Override
	public boolean addChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubAccountDao.addChangePasswordPolicyApppubAccount(changePasswordPolicyApppubAccount);
	}

	@Override
	public boolean delChangePasswordPolicyApppubAccount(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyApppubAccountDao.delChangePasswordPolicyApppubAccount(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount, int page_start, int page_length) {
		return ChangePasswordPolicyApppubAccountDao.findAll(changePasswordPolicyApppubAccount, page_start, page_length);
	}

}
