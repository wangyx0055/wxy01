package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabScriptLog;

public interface CrontabScriptLogDao {

	public boolean editCrontabScriptLog(CrontabScriptLog crontabScriptLog);

	public boolean addCrontabScriptLog(CrontabScriptLog crontabScriptLog);

	public boolean delCrontabScriptLog(List<String> ids);

	public List<Object> findAll(@Param("crontabScriptLog")CrontabScriptLog crontabScriptLog, @Param("page_start")int page_start, @Param("page_length")int page_length);


}
