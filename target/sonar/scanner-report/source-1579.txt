package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdPolicyCmdgroupDao;
import com.longersec.blj.domain.CmdPolicyCmdgroup;
import com.longersec.blj.domain.DTO.Cmd;
import com.longersec.blj.service.CmdPolicyCmdgroupService;


@Transactional
@Service
public class CmdPolicyCmdgroupServiceImpl implements CmdPolicyCmdgroupService{

	@Autowired
	private CmdPolicyCmdgroupDao CmdPolicyCmdgroupDao;

	@Override
	public boolean editCmdPolicyCmdgroup(Integer policy_id,List<Integer> cmdgroup) {
		// TODO Auto-generated method stub
		return this.CmdPolicyCmdgroupDao.editCmdPolicyCmdgroup(policy_id,cmdgroup);
	}

	@Override
	public boolean addCmdPolicyCmdgroup(Integer policy_id,List<Integer> cmdgroup) {
		// TODO Auto-generated method stub
		return this.CmdPolicyCmdgroupDao.addCmdPolicyCmdgroup(policy_id,cmdgroup);
	}

	@Override
	public boolean delCmdPolicyCmdgroup(List<String> ids) {
		// TODO Auto-generated method stub
		return this.CmdPolicyCmdgroupDao.delCmdPolicyCmdgroup(ids);
	}

	@Override
	public List<Object> findAll(CmdPolicyCmdgroup CmdPolicyCmdgroup, int page_start, int page_length) {
		return CmdPolicyCmdgroupDao.findAll(CmdPolicyCmdgroup, page_start, page_length);
	}
	
	@Override
	public List<Cmd> selectById(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyCmdgroupDao.selectById(policy_id);
	}

	@Override
	public Boolean deleteBypolicy_id(Integer policy_id) {
		// TODO Auto-generated method stub
		return this.CmdPolicyCmdgroupDao.deleteBypolicy_id(policy_id);
	}

}
