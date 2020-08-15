package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.RecordDao;
import com.longersec.blj.domain.Record;
import com.longersec.blj.service.RecordService;


@Transactional
@Service
public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordDao RecordDao;

	@Override
	public boolean editRecord(Record record) {
		// TODO Auto-generated method stub
		return this.RecordDao.editRecord(record);
	}

	@Override
	public boolean addRecord(Record record) {
		// TODO Auto-generated method stub
		return this.RecordDao.addRecord(record);
	}

	@Override
	public boolean delRecord(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.RecordDao.delRecord(ids);
	}

	@Override
	public List<Object> findAll(Record record, int page_start, int page_length) {
		return RecordDao.findAll(record, page_start, page_length);
	}

}
