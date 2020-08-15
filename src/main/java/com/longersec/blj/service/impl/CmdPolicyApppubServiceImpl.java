package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyApppubDao;
import com.longersec.blj.domain.CmdPolicyApppub;
import com.longersec.blj.service.CmdPolicyApppubService;


@Transactional
@Service
public class CmdPolicyApppubServiceImpl implements CmdPolicyApppubService{

	@Autowired
	private CmdPolicyApppubDao CmdPolicyApppubDao;

	@Override
	public boolean editCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub) {
		// TODO Auto-generated method stub
		return this.CmdPolicyApppubDao.editCmdPolicyApppub(cmdPolicyApppub);
	}

	@Override
	public boolean addCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub) {
		// TODO Auto-generated method stub
		return this.CmdPolicyApppubDao.addCmdPolicyApppub(cmdPolicyApppub);
	}

	@Override
	public boolean delCmdPolicyApppub(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CmdPolicyApppubDao.delCmdPolicyApppub(ids);
	}

	@Override
	public List<Object> findAll(CmdPolicyApppub cmdPolicyApppub, int page_start, int page_length) {
		return CmdPolicyApppubDao.findAll(cmdPolicyApppub, page_start, page_length);
	}

}
