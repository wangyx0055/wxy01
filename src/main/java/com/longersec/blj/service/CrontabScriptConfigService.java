package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CrontabScriptConfig;

public interface CrontabScriptConfigService {

	public boolean addCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig);

	public boolean editCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig);

	public boolean delCrontabScriptConfig(List<Integer> ids);

	public List<Object> findAll(CrontabScriptConfig crontabScriptConfig, int page_start, int page_length,List<Integer> depart_ids);

	public String selectName(Integer id,String name);

    CrontabScriptConfig checkname(String name);
}

