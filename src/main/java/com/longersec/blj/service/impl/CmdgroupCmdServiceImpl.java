package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdgroupCmdDao;
import com.longersec.blj.domain.CmdgroupCmd;
import com.longersec.blj.service.CmdgroupCmdService;


@Transactional
@Service
public class CmdgroupCmdServiceImpl implements CmdgroupCmdService{

	@Autowired
	private CmdgroupCmdDao CmdgroupCmdDao;

	@Override
	public boolean editCmdgroupCmd(CmdgroupCmd cmdgroupCmd) {
		// TODO Auto-generated method stub
		return this.CmdgroupCmdDao.editCmdgroupCmd(cmdgroupCmd);
	}

	@Override
	public boolean addCmdgroupCmd(CmdgroupCmd cmdgroupCmd) {
		// TODO Auto-generated method stub
		return this.CmdgroupCmdDao.addCmdgroupCmd(cmdgroupCmd);
	}

	@Override
	public boolean delCmdgroupCmd(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CmdgroupCmdDao.delCmdgroupCmd(ids);
	}

	@Override
	public boolean deletegroupcmd(Integer id) {
		return CmdgroupCmdDao.deletegroupcmd(id);
	}

	@Override
	public List<Object> findAll(CmdgroupCmd cmdgroupCmd, int page_start, int page_length) {
		return CmdgroupCmdDao.findAll(cmdgroupCmd, page_start, page_length);
	}

	@Override
	public List<Object> queryCmdGroupCmdByGroupId(int group_id, String sname, Integer type, CmdgroupCmd cmdgroupCmd, int page_start, int page_length) {
		return CmdgroupCmdDao.queryCmdGroupCmdByGroupId(group_id, sname,type,cmdgroupCmd,page_start,page_length);
	}


}
