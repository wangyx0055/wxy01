package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.AccessPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AccessPolicyDeviceAccountDao;
import com.longersec.blj.service.AccessPolicyDeviceAccountService;


@Transactional
@Service
public class AccessPolicyDeviceAccountServiceImpl implements AccessPolicyDeviceAccountService{
	@Autowired
	private AccessPolicyDeviceAccountDao accessPolicyDeviceAccountDao;
	
	@Override
	public boolean addAccessPolicyDevice(Integer policy_id, List<Integer> devices) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.addAccessPolicyDeviceAccount(policy_id, devices);
	}

	@Override
	public boolean delAccessPolicyDevice(List<String> ids) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.delAccessPolicyDeviceAccount(ids);
	}

	@Override
	public List<Object> findAll(AccessPolicyDeviceAccount accessPolicyDeviceAccount, int page_start,
			int page_length) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.findAll(accessPolicyDeviceAccount, page_start, page_length);
	}

	@Override
	public List<Deviceaccess> selectById(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.selectById(policy_id);
	}

	@Override
	public Boolean editAccessPolicyDeviceAccount(Integer policy_id, List<Integer> devices) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.editAccessPolicyDeviceAccount(policy_id, devices);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.accessPolicyDeviceAccountDao.deleteBypolicy_id(policy_id);
	}

	@Override
	public int selectAccessPolicyDeviceCounts(int policy_id) {
		return accessPolicyDeviceAccountDao.selectAccessPolicyDeviceCounts(policy_id);
	}

	@Override
	public int selectBydeviceId(int item) {
		return accessPolicyDeviceAccountDao.selectBydeviceId(item);
	}
}
