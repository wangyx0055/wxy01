package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigDbbackup;
import com.longersec.blj.domain.DeviceRecord;

public interface ConfigDbbackupService {

	public boolean addConfigDbbackup(ConfigDbbackup configDbbackup);

	public boolean editConfigDbbackup(ConfigDbbackup configDbbackup);

	public boolean delConfigDbbackup(List<Integer> ids);

	public List<Object> findAll(ConfigDbbackup configDbbackup, int page_start, int page_length);
	
	public ConfigDbbackup getById(Integer id);
	
	public void DBReset();

}

