package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.ApppubServer;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.domain.ConfigNetwork;

public interface ConfigNetworkDao {

	public boolean editConfigNetwork(ConfigNetwork configNetwork);

	public boolean addConfigNetwork(ConfigNetwork configNetwork);

	public boolean delConfigNetwork(List<Integer> ids);

	public List<Object> findAll(@Param("configNetwork")ConfigNetwork configNetwork, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigNetwork get();

}
