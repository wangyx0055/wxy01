package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabCommandLog;

public interface CrontabCommandLogDao {

	public boolean editCrontabCommandLog(CrontabCommandLog crontabCommandLog);

	public boolean addCrontabCommandLog(CrontabCommandLog crontabCommandLog);

	public boolean delCrontabCommandLog(List<Integer> ids);

	public List<Object> findAll(@Param("crontabCommandLog")CrontabCommandLog crontabCommandLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public CrontabCommandLog getById(Integer id);

	public ArrayList<CrontabCommandLog> getCrontabCommandLog(@Param("id") Integer id);
}
