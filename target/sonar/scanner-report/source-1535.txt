package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CrontabScriptLog;
import org.apache.ibatis.annotations.Param;

public interface CrontabScriptLogService {

	public boolean addCrontabScriptLog(CrontabScriptLog crontabScriptLog);

	public boolean editCrontabScriptLog(CrontabScriptLog crontabScriptLog);

	public boolean delCrontabScriptLog(List<String> ids);

	public List<Object> findAll(CrontabScriptLog crontabScriptLog, int page_start, int page_length);
}

