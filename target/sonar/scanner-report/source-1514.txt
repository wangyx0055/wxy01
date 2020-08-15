package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigAlertLevel;

public interface ConfigAlertLevelService {

	public boolean addConfigAlertLevel(ConfigAlertLevel configAlertLevel);

	public boolean editConfigAlertLevel(ConfigAlertLevel configAlertLevel);

	public boolean delConfigAlertLevel(List<Integer> ids);

	public List<Object> findAll(ConfigAlertLevel configAlertLevel, int page_start, int page_length);

	public ConfigAlertLevel getById(Integer id);

}

