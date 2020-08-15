package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.SystemMessage;

public interface SystemMessageService {

	public boolean addSystemMessage(SystemMessage systemMessage);

	public boolean editSystemMessage(SystemMessage systemMessage);

	public boolean delSystemMessage(List<Integer> ids);

	public List<Object> findAll(SystemMessage systemMessage, int page_start, int page_length);

	public SystemMessage getById(Integer id);

}

