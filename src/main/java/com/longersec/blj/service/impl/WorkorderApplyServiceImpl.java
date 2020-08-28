package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.WorkorderApplyDao;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.service.WorkorderApplyService;


@Service
@Transactional
public class WorkorderApplyServiceImpl implements WorkorderApplyService{

	@Autowired
	private WorkorderApplyDao WorkorderApplyDao;

	@Override
	public boolean editWorkorderApply(WorkorderApply workorderApply) {
		return this.WorkorderApplyDao.editWorkorderApply(workorderApply);
	}

	@Override
	public boolean addWorkorderApply(WorkorderApply workorderApply) {
		return this.WorkorderApplyDao.addWorkorderApply(workorderApply);
	}

	@Override
	public boolean delWorkorderApply(List<Integer> ids) {
		return this.WorkorderApplyDao.delWorkorderApply(ids);
	}

	@Override
	public List<Object> findAll(WorkorderApply workorderApply, int page_start, int page_length) {
		return WorkorderApplyDao.findAll(workorderApply, page_start, page_length);
	}

	@Override
	public int selectWorkDept() {
		return WorkorderApplyDao.selectWorkDept();
	}

	@Override
	public boolean updateResult(int result, int id,int dateline) {
		return WorkorderApplyDao.updateResult(result,id,dateline);
	}

	@Override
	public WorkorderApply getById(Integer id) {
		return WorkorderApplyDao.getById(id);
	}

	@Override
	public String selectCmd(int id) {
		return WorkorderApplyDao.selectCmd(id);
	}

	@Override
	public int selectDeadLine() {
		return WorkorderApplyDao.selectDeadLine();
	}

}
