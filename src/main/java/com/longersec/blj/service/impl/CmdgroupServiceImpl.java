package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CmdgroupDao;
import com.longersec.blj.domain.Cmdgroup;
import com.longersec.blj.domain.DTO.Cmd;
import com.longersec.blj.service.CmdgroupService;


@Transactional
@Service
public class CmdgroupServiceImpl implements CmdgroupService{

	@Autowired
	private CmdgroupDao CmdgroupDao;

	@Override
	public boolean editCmdgroup(Cmdgroup cmdgroup) {
		// TODO Auto-generated method stub
		return this.CmdgroupDao.editCmdgroup(cmdgroup);
	}

	@Override
	public boolean addCmdgroup(Cmdgroup cmdgroup) {
		// TODO Auto-generated method stub
		return this.CmdgroupDao.addCmdgroup(cmdgroup);
	}

	@Override
	public boolean delCmdgroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CmdgroupDao.delCmdgroup(ids);
	}

	@Override
	public List<Object> findAll(Cmdgroup cmdgroup, String sname,Integer type,int page_start, int page_length, List<Integer> depart_ids) {
		return CmdgroupDao.findAll(cmdgroup, sname,type,page_start, page_length,depart_ids);
	}

	@Override
	public List<Cmd> selectNameAndId() {
		// TODO Auto-generated method stub
		return this.CmdgroupDao.selectNameAndId();
	}

	@Override
	public Cmdgroup checkname(String name) {
		return CmdgroupDao.checkname(name);
	}
}
