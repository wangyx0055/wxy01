package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.UserMessage;

public interface UserMessageService {

	public boolean addUserMessage(UserMessage userMessage);

	public boolean editUserMessage(UserMessage userMessage);

	public boolean delUserMessage(List<Integer> ids);

	public List<Object> findAll(UserMessage userMessage, int page_start, int page_length);

	public UserMessage getById(Integer id);

}

