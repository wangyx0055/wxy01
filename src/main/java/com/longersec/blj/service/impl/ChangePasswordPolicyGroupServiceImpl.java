package com.longersec.blj.service.impl;

import java.util.List;
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
	public boolean editChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyGroupDao.editChangePasswordPolicyGroup(changePasswordPolicyGroup);
	}

	@Override
	public boolean addChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyGroupDao.addChangePasswordPolicyGroup(changePasswordPolicyGroup);
	}

	@Override
	public boolean delChangePasswordPolicyGroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ChangePasswordPolicyGroupDao.delChangePasswordPolicyGroup(ids);
	}

	@Override
	public List<Object> findAll(ChangePasswordPolicyGroup changePasswordPolicyGroup, int page_start, int page_length) {
		return ChangePasswordPolicyGroupDao.findAll(changePasswordPolicyGroup, page_start, page_length);
	}

	@Override
	public boolean addChangePasswordPolicyGroup(Integer policy_id, List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		return ChangePasswordPolicyGroupDao.addChangePasswordPolicyGroup(policy_id,devicegroup);
	}

}
