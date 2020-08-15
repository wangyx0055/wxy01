package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CrontabScript;

public interface CrontabScriptService {

	public boolean addCrontabScript(CrontabScript crontabScript);

	public boolean editCrontabScript(CrontabScript crontabScript);

	public boolean delCrontabScript(List<Integer> ids);

	public List<Object> findAll(CrontabScript crontabScript, int page_start, int page_length);
	
	public CrontabScript getById(int id);

}

