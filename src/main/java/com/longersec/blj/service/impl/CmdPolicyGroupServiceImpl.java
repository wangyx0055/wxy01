package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyGroupDao;
import com.longersec.blj.domain.CmdPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.service.CmdPolicyGroupService;


@Transactional
@Service
public class CmdPolicyGroupServiceImpl implements CmdPolicyGroupService{

	@Autowired
	private CmdPolicyGroupDao CmdPolicyGroupDao;

	@Override
	public List<Object> findAll(CmdPolicyGroup cmdPolicyGroup, int page_start, int page_length) {
		return CmdPolicyGroupDao.findAll(cmdPolicyGroup, page_start, page_length);
	}

	@Override
	public Boolean addCmdPolicyGroup(Integer policy_id,List<Integer> usergroup,List<Integer> devicegroup) {
		Boolean flag,flag1;
		flag = CmdPolicyGroupDao.addCmdPolicyUserGroup(policy_id,usergroup);
		flag1 = CmdPolicyGroupDao.addCmdPolicyDeviceGroup(policy_id,devicegroup);
		return flag.equals(flag1);
	}

	@Override
	public Boolean editCmdPolicyUserGroup(Integer policy_id, List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		return this.CmdPolicyGroupDao.editCmdPolicyUserGroup(policy_id, devicegroup);
	}
	
	@Override
	public Boolean editCmdPolicyDeviceGroup(Integer policy_id, List<Integer> devicegroup) {
		// TODO Auto-generated method stub
		return this.CmdPolicyGroupDao.editCmdPolicyDeviceGroup(policy_id, devicegroup);
	}

	@Override
	public List<UserGroup> selectByIdUser(Integer policy_id) {
		// TODO Auto-generated method stub
		int type = 0;
		return this.CmdPolicyGroupDao.selectByIdUser(policy_id, type);
	}

	@Override
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id) {
		// TODO Auto-generated method stub
		int type = 1;
		return this.CmdPolicyGroupDao.selectBydIdDevice(policy_id, type);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id, int type) {
		// TODO Auto-generated method stub
		return this.CmdPolicyGroupDao.deleteBypolicy_id(policy_id,type);
	}
}
