package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.SystemMessage;

public interface SystemMessageDao {

	public boolean editSystemMessage(SystemMessage systemMessage);

	public boolean addSystemMessage(SystemMessage systemMessage);

	public boolean delSystemMessage(List<Integer> ids);

	public List<Object> findAll(@Param("systemMessage")SystemMessage systemMessage, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public SystemMessage getById(Integer id);

}
