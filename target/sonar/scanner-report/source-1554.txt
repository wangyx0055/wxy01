package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AccessPolicyGroupDao;
import com.longersec.blj.domain.AccessPolicyGroup;
import com.longersec.blj.service.AccessPolicyGroupService;


@Transactional
@Service
public class AccessPolicyGroupServiceImpl implements AccessPolicyGroupService{

	@Autowired
	private AccessPolicyGroupDao AccessPolicyGroupDao;



	@Override
	public boolean addAccessPolicyGroup(Integer policy_id,List<Integer> groups,List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		Boolean flag,flag1;
		flag = AccessPolicyGroupDao.addAccessPolicyGroup(policy_id,groups);
		flag1 = AccessPolicyGroupDao.addAccessPolicyDeviceGroup(policy_id,devicegroup);
		if (flag.equals(flag1)){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public boolean delAccessPolicyGroup(List<String> ids) {
		// TODO Auto-generated method stub
		return this.AccessPolicyGroupDao.delAccessPolicyGroup(ids);
	}

	@Override
	public List<Object> findAll(AccessPolicyGroup accessPolicyGroup, int page_start, int page_length) {
		return AccessPolicyGroupDao.findAll(accessPolicyGroup, page_start, page_length);
	}

	@Override
	public List<UserGroup> selectById(Integer policy_id) {
		int type = 0;
		return AccessPolicyGroupDao.selectById(policy_id,type);
	}

	@Override
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id) {
		int type = 1; 
		return AccessPolicyGroupDao.selectBydIdDevice(policy_id,type);
	}


	@Override
	public Boolean deleteBypolicy_id(Integer policy_id, int type) {
		// TODO Auto-generated method stub
		return this.AccessPolicyGroupDao.deleteBypolicy_id(policy_id,type);
	}

	@Override
	public boolean editAccessPolicyGroup(Integer policy_id, List<Integer> groups) {
		// TODO Auto-generated method stub
		return AccessPolicyGroupDao.editAccessPolicyGroup(policy_id,groups);
	}

	@Override
	public boolean editAccessPolicyDeviceGroup(Integer policy_id, List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		return AccessPolicyGroupDao.editAccessPolicyDeviceGroup(policy_id,devicegroup);
	}
}
