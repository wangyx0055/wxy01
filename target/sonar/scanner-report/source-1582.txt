package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyDao;
import com.longersec.blj.domain.CmdPolicy;
import com.longersec.blj.domain.CmdPolicyCmd;
import com.longersec.blj.service.CmdPolicyService;


@Transactional
@Service
public class CmdPolicyServiceImpl implements CmdPolicyService{

	@Autowired
	private CmdPolicyDao CmdPolicyDao;

	@Override
	public boolean editCmdPolicy(CmdPolicy cmdPolicy) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDao.editCmdPolicy(cmdPolicy);
	}

	@Override
	public boolean editStatus(Integer status, Integer id) {
		return CmdPolicyDao.editStatus(status,id);
	}

	@Override
	public boolean addCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd) {
		return CmdPolicyDao.addCmdPolicyCmd(cmdPolicyCmd);
	}

	@Override
	public boolean clearCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd) {
		return CmdPolicyDao.clearCmdPolicyCmd(cmdPolicyCmd);
	}

	@Override
	public boolean addCmdPolicy(CmdPolicy cmdPolicy) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDao.addCmdPolicy(cmdPolicy);
	}

	@Override
	public boolean delCmdPolicy(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CmdPolicyDao.delCmdPolicy(ids);
	}

	@Override
	public List<Object> findAll(CmdPolicy cmdPolicy,String sname ,Integer type ,String stat,String cot ,int page_start, int page_length) {
		return CmdPolicyDao.findAll(cmdPolicy,sname,type,stat,cot,page_start, page_length);
	}

	@Override
	public CmdPolicy checkName(String name) {
		return CmdPolicyDao.checkName(name);
	}
	
	@Override
	public List<String> getCommandsByDeviceAndUser(Integer device_account_id, Integer user_id, Integer group_id){
		return CmdPolicyDao.getCommandsByDeviceAndUser(device_account_id, user_id, group_id);
	}

	@Override
	public CmdPolicy getById(Integer id) {
		// TODO Auto-generated method stub
		return CmdPolicyDao.getById(id);
	}

}
