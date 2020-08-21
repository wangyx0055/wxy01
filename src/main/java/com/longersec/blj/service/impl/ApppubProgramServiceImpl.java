package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubProgramDao;
import com.longersec.blj.domain.ApppubProgram;
import com.longersec.blj.service.ApppubProgramService;


@Transactional
@Service
public class ApppubProgramServiceImpl implements ApppubProgramService{

	@Autowired
	private ApppubProgramDao ApppubProgramDao;

	@Override
	public boolean editApppubProgram(ApppubProgram apppubProgram) {
		// TODO Auto-generated method stub
		return this.ApppubProgramDao.editApppubProgram(apppubProgram);
	}

	@Override
	public boolean addApppubProgram(ApppubProgram apppubProgram) {
		// TODO Auto-generated method stub
		return this.ApppubProgramDao.addApppubProgram(apppubProgram);
	}

	@Override
	public boolean delApppubProgram(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubProgramDao.delApppubProgram(ids);
	}

	@Override
	public List<Object> findAll(ApppubProgram apppubProgram,String sname,Integer type, int page_start, int page_length) {
		return ApppubProgramDao.findAll(apppubProgram,sname,type, page_start, page_length);
	}

	@Override
	public List<Object> queryApppubProgramById(Integer apppub_server_id, ApppubProgram apppubProgram, int page_start, int page_length) {
		return ApppubProgramDao.queryApppubProgramById(apppub_server_id,apppubProgram,page_start,page_length);
	}

//	@Override
//	public List<Object> queryApppubProgramById(Integer apppub_server_id) {
//		return ApppubProgramDao.queryApppubProgramById(apppub_server_id);
//	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return ApppubProgramDao.total();
	}

	@Override
	public ApppubProgram getById(Integer id) {
		// TODO Auto-generated method stub
		return ApppubProgramDao.getById(id);
	}

	@Override
	public ApppubProgram checkAppName(String name) {
		// TODO Auto-generated method stub
		return ApppubProgramDao.checkAppName(name);
	}

	@Override
	public ApppubProgram getApppubProgramByName(String name) {
		return ApppubProgramDao.getApppubProgramByName(name);
	}

}
