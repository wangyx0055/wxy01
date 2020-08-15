package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigAlertLevel;

public interface ConfigAlertLevelDao {

	public boolean editConfigAlertLevel(ConfigAlertLevel configAlertLevel);

	public boolean addConfigAlertLevel(ConfigAlertLevel configAlertLevel);

	public boolean delConfigAlertLevel(List<Integer> ids);

	public List<Object> findAll(@Param("configAlertLevel")ConfigAlertLevel configAlertLevel, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigAlertLevel getById(Integer id);

}
