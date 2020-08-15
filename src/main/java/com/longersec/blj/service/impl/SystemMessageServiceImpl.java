package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SystemMessageDao;
import com.longersec.blj.domain.SystemMessage;
import com.longersec.blj.service.SystemMessageService;


@Service
@Transactional
public class SystemMessageServiceImpl implements SystemMessageService{

	@Autowired
	private SystemMessageDao SystemMessageDao;

	@Override
	public boolean editSystemMessage(SystemMessage systemMessage) {
		// TODO Auto-generated method stub
		return this.SystemMessageDao.editSystemMessage(systemMessage);
	}

	@Override
	public boolean addSystemMessage(SystemMessage systemMessage) {
		// TODO Auto-generated method stub
		return this.SystemMessageDao.addSystemMessage(systemMessage);
	}

	@Override
	public boolean delSystemMessage(List<Integer> ids) {
		// TODO Auto-generated method stub
		return SystemMessageDao.delSystemMessage(ids);
	}

	@Override
	public List<Object> findAll(SystemMessage systemMessage, int page_start, int page_length) {
		return SystemMessageDao.findAll(systemMessage, page_start, page_length);
	}

	@Override
	public SystemMessage getById(Integer id) {
		return SystemMessageDao.getById(id);
	}

}
