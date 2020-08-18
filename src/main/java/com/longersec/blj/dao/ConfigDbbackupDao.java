package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigDbbackup;
import com.longersec.blj.domain.DeviceRecord;

public interface ConfigDbbackupDao {

	public boolean editConfigDbbackup(ConfigDbbackup configDbbackup);

	public boolean addConfigDbbackup(ConfigDbbackup configDbbackup);

	public boolean delConfigDbbackup(List<Integer> ids);

	public List<Object> findAll(@Param("configDbbackup")ConfigDbbackup configDbbackup, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigDbbackup getById(Integer id);
	
	public void DBReset();
}
