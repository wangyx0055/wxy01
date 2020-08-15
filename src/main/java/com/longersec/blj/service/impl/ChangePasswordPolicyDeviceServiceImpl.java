package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyDeviceDao;
import com.longersec.blj.domain.ChangePasswordPolicyDevice;
import com.longersec.blj.service.ChangePasswordPolicyDeviceService;


@Transactional
@Service
public class ChangePasswordPolicyDeviceServiceImpl implements ChangePasswordPolicyDeviceService{

	@Autowired
	private ChangePasswordPolicyDeviceDao ChangePasswordPolicyDeviceDao;

	@Override
	public boolean editChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceDao.editChangePasswordPolicyDevice(changePasswordPolicyDevice);
	}

	@Override
	public boolean addChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceDao.addChangePasswordPolicyDevice(changePasswordPolicyDevice);
	}

	@Override
	public boolean delChangePasswordPolicyDevice(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceDao.delChangePasswordPolicyDevice(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyDevice changePasswordPolicyDevice, int page_start, int page_length) {
		return ChangePasswordPolicyDeviceDao.findAll(changePasswordPolicyDevice, page_start, page_length);
	}

	@Override
	public boolean addChangePasswordPolicyDevice(Integer policy_id, List<Integer> devices) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyDeviceDao.addChangePasswordPolicyDevice(policy_id,devices);
	}

}
