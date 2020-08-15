package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyDeviceAccountDao;
import com.longersec.blj.domain.CmdPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.CmdPolicyDeviceAccountService;


@Transactional
@Service
public class CmdPolicyDeviceAccountServiceImpl implements CmdPolicyDeviceAccountService{

	@Autowired
	private CmdPolicyDeviceAccountDao CmdPolicyDeviceAccountDao;

	@Override
	public boolean editCmdPolicyDeviceAccount(Integer policy_id,List<Integer> device) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDeviceAccountDao.editCmdPolicyDeviceAccount(policy_id,device);
	}

	@Override
	public boolean addCmdPolicyDeviceAccount(Integer policy_id,List<Integer> device) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDeviceAccountDao.addCmdPolicyDeviceAccount(policy_id,device);
	}

	@Override
	public boolean delCmdPolicyDeviceAccount(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDeviceAccountDao.delCmdPolicyDeviceAccount(ids);
	}

	@Override
	public List<Object> findAll(CmdPolicyDeviceAccount cmdPolicyDeviceAccount, int page_start, int page_length) {
		return CmdPolicyDeviceAccountDao.findAll(cmdPolicyDeviceAccount, page_start, page_length);
	}
	
	@Override
	public List<Deviceaccess> selectById(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDeviceAccountDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDeviceAccountDao.deleteBypolicy_id(policy_id);
	}

}
