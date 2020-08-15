package com.longersec.blj.service;

import java.util.List;

import com.longersec.blj.domain.ApppubServer;
import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.domain.ConfigNetwork;

public interface ConfigNetworkService {

	public boolean addConfigNetwork(ConfigNetwork configNetwork);

	public boolean editConfigNetwork(ConfigNetwork configNetwork);

	public boolean delConfigNetwork(List<Integer> ids);

	public List<Object> findAll(ConfigNetwork configNetwork, int page_start, int page_length);

	public ConfigNetwork get();
	
	public String getNameByInterface(String ethx, String name);
}

