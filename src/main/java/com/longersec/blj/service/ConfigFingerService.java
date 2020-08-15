package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigFinger;
import com.longersec.blj.domain.Device;

public interface ConfigFingerService {

	public boolean addConfigFinger(ConfigFinger configFinger);

	public boolean editConfigFinger(ConfigFinger configFinger);

	public boolean delConfigFinger(List<Integer> ids);

	public List<Object> findAll(ConfigFinger configFinger, int page_start, int page_length);
	
	public ConfigFinger getById(Integer id);

}

