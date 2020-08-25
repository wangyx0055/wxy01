package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ChangePasswordPolicyGroupDao;
import com.longersec.blj.domain.ChangePasswordPolicyGroup;
import com.longersec.blj.service.ChangePasswordPolicyGroupService;


@Transactional
@Service
public class ChangePasswordPolicyGroupServiceImpl implements ChangePasswordPolicyGroupService{

	@Autowired
	private ChangePasswordPolicyGroupDao ChangePasswordPolicyGroupDao;



	@Override
	public boolean addChangePasswordPolicyGroup(Integer policy_id,List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		Boolean flag,flag1;
		flag1 = ChangePasswordPolicyGroupDao.addChangePasswordPolicyDeviceGroup(policy_id,devicegroup);
		if (flag1){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public boolean delChangePasswordPolicyGroup(List<String> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyGroupDao.delChangePasswordPolicyGroup(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyGroup changePasswordPolicyGroup, int page_start, int page_length) {
		return ChangePasswordPolicyGroupDao.findAll(changePasswordPolicyGroup, page_start, page_length);
	}

	@Override
	public List<UserGroup> selectById(Integer policy_id) {
		int type = 0;
		return ChangePasswordPolicyGroupDao.selectById(policy_id,type);
	}

	@Override
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id) {
		int type = 1; 
		return ChangePasswordPolicyGroupDao.selectBydIdDevice(policy_id,type);
	}


	@Override
	public Boolean deleteBypolicy_id(Integer policy_id, int type) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyGroupDao.deleteBypolicy_id(policy_id,type);
	}

	@Override
	public boolean editChangePasswordPolicyGroup(Integer policy_id, List<Integer> groups) {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyGroupDao.editChangePasswordPolicyGroup(policy_id,groups);
	}

	@Override
	public boolean editChangePasswordPolicyDeviceGroup(Integer policy_id, List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyGroupDao.editChangePasswordPolicyDeviceGroup(policy_id,devicegroup);
	}
}
