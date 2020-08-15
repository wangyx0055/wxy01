package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.RecordCommandDao;
import com.longersec.blj.domain.RecordCommand;
import com.longersec.blj.service.RecordCommandService;


@Service
@Transactional
public class RecordCommandServiceImpl implements RecordCommandService{

	@Autowired
	private RecordCommandDao RecordCommandDao;

	@Override
	public boolean editRecordCommand(RecordCommand recordCommand) {
		// TODO Auto-generated method stub
		return this.RecordCommandDao.editRecordCommand(recordCommand);
	}

	@Override
	public boolean addRecordCommand(RecordCommand recordCommand) {
		// TODO Auto-generated method stub
		return this.RecordCommandDao.addRecordCommand(recordCommand);
	}

	@Override
	public boolean delRecordCommand(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.RecordCommandDao.delRecordCommand(ids);
	}

	@Override
	public List<Object> findAll(RecordCommand recordCommand, int page_start, int page_length) {
		return RecordCommandDao.findAll(recordCommand, page_start, page_length);
	}

	@Override
	public RecordCommand getById(Integer id) {
		return RecordCommandDao.getById(id);
	}

}
