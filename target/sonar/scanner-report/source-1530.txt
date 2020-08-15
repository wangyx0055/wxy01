package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import com.longersec.blj.domain.CrontabCommandLog;

public interface CrontabCommandLogService {

	public boolean addCrontabCommandLog(CrontabCommandLog crontabCommandLog);

	public boolean editCrontabCommandLog(CrontabCommandLog crontabCommandLog);

	public boolean delCrontabCommandLog(List<Integer> ids);

	public List<Object> findAll(CrontabCommandLog crontabCommandLog, int page_start, int page_length);

	public CrontabCommandLog getById(Integer id);

	public ArrayList<CrontabCommandLog> getCrontabCommandLog(Integer id);

}

