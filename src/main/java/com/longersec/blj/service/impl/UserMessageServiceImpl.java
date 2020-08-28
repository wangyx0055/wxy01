package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.UserMessageDao;
import com.longersec.blj.domain.UserMessage;
import com.longersec.blj.service.UserMessageService;


@Service
@Transactional
public class UserMessageServiceImpl implements UserMessageService{

	@Autowired
	private UserMessageDao UserMessageDao;

	@Override
	public boolean editUserMessage(UserMessage userMessage) {
		// TODO Auto-generated method stub
		return this.UserMessageDao.editUserMessage(userMessage);
	}

	@Override
	public boolean addUserMessage(UserMessage userMessage) {
		// TODO Auto-generated method stub
		return this.UserMessageDao.addUserMessage(userMessage);
	}

	@Override
	public boolean delUserMessage(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.UserMessageDao.delUserMessage(ids);
	}

	@Override
	public List<Object> findAll(UserMessage userMessage, int page_start, int page_length) {
		return UserMessageDao.findAll(userMessage, page_start, page_length);
	}

	@Override
	public UserMessage getById(Integer id) {
		return UserMessageDao.getById(id);
	}

}
