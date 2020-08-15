package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.RecordAuditDao;
import com.longersec.blj.domain.RecordAudit;
import com.longersec.blj.service.RecordAuditService;


@Transactional
@Service
public class RecordAuditServiceImpl implements RecordAuditService{

	@Autowired
	private RecordAuditDao RecordAuditDao;

	@Override
	public boolean editRecordAudit(RecordAudit recordAudit) {
		// TODO Auto-generated method stub
		return this.RecordAuditDao.editRecordAudit(recordAudit);
	}

	@Override
	public boolean addRecordAudit(RecordAudit recordAudit) {
		// TODO Auto-generated method stub
		return this.RecordAuditDao.addRecordAudit(recordAudit);
	}

	@Override
	public boolean delRecordAudit(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.RecordAuditDao.delRecordAudit(ids);
	}

	@Override
	public List<Object> findAll(RecordAudit recordAudit, int page_start, int page_length) {
		return RecordAuditDao.findAll(recordAudit, page_start, page_length);
	}

}
