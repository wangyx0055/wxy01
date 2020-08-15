package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyUserDao;
import com.longersec.blj.domain.CmdPolicyUser;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.CmdPolicyUserService;


@Transactional
@Service
public class CmdPolicyUserServiceImpl implements CmdPolicyUserService{

	@Autowired
	private CmdPolicyUserDao CmdPolicyUserDao;

	@Override
	public boolean editCmdPolicyUser(Integer policy_id,List<Integer> user) {
		// TODO Auto-generated method stub
		return this.CmdPolicyUserDao.editCmdPolicyUser(policy_id,user);
	}

	@Override
	public boolean addCmdPolicyUser(Integer policy_id,List<Integer> user) {
		// TODO Auto-generated method stub
		return this.CmdPolicyUserDao.addCmdPolicyUser(policy_id,user);
	}

	@Override
	public boolean delCmdPolicyUser(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CmdPolicyUserDao.delCmdPolicyUser(ids);
	}

	@Override
	public List<Object> findAll(CmdPolicyUser cmdPolicyUser, int page_start, int page_length) {
		return CmdPolicyUserDao.findAll(cmdPolicyUser, page_start, page_length);
	}

	@Override
	public List<Users> selectById(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyUserDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyUserDao.deleteBypolicy_id(policy_id);
	}

}
