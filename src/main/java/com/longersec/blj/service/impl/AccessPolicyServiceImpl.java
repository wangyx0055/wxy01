package com.longersec.blj.service.impl;

import com.longersec.blj.dao.AccessPolicyDao;
import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.service.AccessPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class AccessPolicyServiceImpl implements AccessPolicyService{

	@Autowired
	private AccessPolicyDao AccessPolicyDao;

	@Override
	public boolean editAccessPolicy(AccessPolicy accessPolicy) {
		// TODO Auto-generated method stub
		return this.AccessPolicyDao.editAccessPolicy(accessPolicy);
	}

	@Override
	public Integer addAccessPolicy(AccessPolicy accessPolicy) {
		// TODO Auto-generated method stub
		return this.AccessPolicyDao.addAccessPolicy(accessPolicy);
	}

	@Override
	public boolean editStatus(Integer status, Integer id) {
		return AccessPolicyDao.editStatus(status,id);
	}

	@Override
	public boolean delAccessPolicy(List<String> ids) {
		return this.AccessPolicyDao.delAccessPolicy(ids);
	}

	@Override
	public List<Object> findAll(AccessPolicy accessPolicy, String sname ,String stat ,Integer type,int page_start, int page_length,List<Integer> depart_ids) {
		return AccessPolicyDao.findAll(accessPolicy, sname,stat,type,page_start, page_length,depart_ids);
	}

	@Override
	public List<AccessPolicy> getUserPolicy(Integer userid, Integer device_account_id, Integer apppub_account_id) {
		// TODO Auto-generated method stub
		return AccessPolicyDao.getUserPolicy(userid, device_account_id, apppub_account_id);
	}

	@Override
	public AccessPolicy getById(Integer id) {
		// TODO Auto-generated method stub
		return AccessPolicyDao.getById(id);
	}

	@Override
	public AccessPolicy checkname(String name) {
		return AccessPolicyDao.checkname(name);
	}
}
