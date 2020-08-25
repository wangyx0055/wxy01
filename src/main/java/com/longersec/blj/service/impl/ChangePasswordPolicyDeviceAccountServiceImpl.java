package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyDeviceAccountDao;
import com.longersec.blj.domain.ChangePasswordPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.ChangePasswordPolicyDeviceAccountService;


@Transactional
@Service
public class ChangePasswordPolicyDeviceAccountServiceImpl implements ChangePasswordPolicyDeviceAccountService{

	@Autowired
	private ChangePasswordPolicyDeviceAccountDao ChangePasswordPolicyDeviceAccountDao;

	@Override
	public boolean editChangePasswordPolicyDeviceAccount(Integer policy_id,List<Integer> devices) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceAccountDao.editChangePasswordPolicyDeviceAccount(policy_id, devices);
	}

	@Override
	public boolean addChangePasswordPolicyDeviceAccount(Integer policy_id,List<Integer> devices) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceAccountDao.addChangePasswordPolicyDeviceAccount(policy_id, devices);
	}

	@Override
	public boolean delChangePasswordPolicyDeviceAccount(List<String> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceAccountDao.delChangePasswordPolicyDeviceAccount(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyDeviceAccount changePasswordPolicyDeviceAccount, int page_start, int page_length) {
		return ChangePasswordPolicyDeviceAccountDao.findAll(changePasswordPolicyDeviceAccount, page_start, page_length);
	}

	@Override
	public List<Deviceaccess> selectById(Integer policy_id) {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyDeviceAccountDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyDeviceAccountDao.deleteBypolicy_id(policy_id);
	}

}
