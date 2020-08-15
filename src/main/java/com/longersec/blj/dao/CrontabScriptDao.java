package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabScript;

public interface CrontabScriptDao {

	public boolean editCrontabScript(CrontabScript crontabScript);

	public boolean addCrontabScript(CrontabScript crontabScript);

	public boolean delCrontabScript(List<Integer> ids);

	public List<Object> findAll(@Param("crontabScript")CrontabScript crontabScript, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public CrontabScript getById(@Param("id")Integer id);

}
