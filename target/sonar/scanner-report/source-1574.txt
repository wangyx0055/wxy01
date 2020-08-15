package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyDao;
import com.longersec.blj.domain.ChangePasswordPolicy;
import com.longersec.blj.service.ChangePasswordPolicyService;


@Transactional
@Service
public class ChangePasswordPolicyServiceImpl implements ChangePasswordPolicyService{

	@Autowired
	private ChangePasswordPolicyDao ChangePasswordPolicyDao;

	@Override
	public boolean editChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDao.editChangePasswordPolicy(changePasswordPolicy);
	}

	@Override
	public boolean addChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy) {
		// TODO Auto-generated method stub
		/*
		 * int type = changePasswordPolicy.getType(); if (type==0){
		 * changePasswordPolicy.setEnd_datetime("");
		 * changePasswordPolicy.setExec_datetime("");
		 * 
		 * }if (type==1){ changePasswordPolicy.setEnd_datetime(""); }
		 */
		return this.ChangePasswordPolicyDao.addChangePasswordPolicy(changePasswordPolicy);
	}

	@Override
	public boolean delChangePasswordPolicy(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDao.delChangePasswordPolicy(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicy changePasswordPolicy,String name, int page_start, int page_length) {
		return ChangePasswordPolicyDao.findAll(changePasswordPolicy, name,page_start, page_length);
	}

	@Override
	public List<ChangePasswordPolicy> checkName() {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyDao.checkName();
	}

}
