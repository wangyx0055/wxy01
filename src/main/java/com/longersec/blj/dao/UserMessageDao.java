package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.UserMessage;

public interface UserMessageDao {

	public boolean editUserMessage(UserMessage userMessage);

	public boolean addUserMessage(UserMessage userMessage);

	public boolean delUserMessage(List<Integer> ids);

	public List<Object> findAll(@Param("userMessage")UserMessage userMessage, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public UserMessage getById(Integer id);

}
