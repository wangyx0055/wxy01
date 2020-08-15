package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubRecordDao;
import com.longersec.blj.domain.ApppubRecord;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.service.ApppubRecordService;


@Transactional
@Service
public class ApppubRecordServiceImpl implements ApppubRecordService{

	@Autowired
	private ApppubRecordDao ApppubRecordDao;

	@Override
	public boolean editApppubRecord(ApppubRecord apppubRecord) {
		// TODO Auto-generated method stub
		return this.ApppubRecordDao.editApppubRecord(apppubRecord);
	}

	@Override
	public boolean addApppubRecord(ApppubRecord apppubRecord) {
		// TODO Auto-generated method stub
		return this.ApppubRecordDao.addApppubRecord(apppubRecord);
	}

	@Override
	public boolean delApppubRecord(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubRecordDao.delApppubRecord(ids);
	}

	@Override
	public List<Object> findAll(ApppubRecord apppubRecord, int page_start, int page_length) {
		return ApppubRecordDao.findAll(apppubRecord, page_start, page_length);
	}

	@Override
	public Integer get30DayIncrease() {
		// TODO Auto-generated method stub
		return ApppubRecordDao.get30DayIncrease();
	}

	@Override
	public Integer getApppubRecordTotal() {
		// TODO Auto-generated method stub
		return ApppubRecordDao.getApppubRecordTotal();
	}

	@Override
	public List<ApppubRecord> getEarlyRecord() {
		// TODO Auto-generated method stub
		return ApppubRecordDao.getEarlyRecord();
	}

	@Override
	public ApppubRecord getById(Integer id) {
		// TODO Auto-generated method stub
		return ApppubRecordDao.getById(id);
	}


}
