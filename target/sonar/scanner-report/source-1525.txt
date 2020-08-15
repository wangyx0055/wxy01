package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigRoute;

public interface ConfigRouteService {

	public boolean addConfigRoute(ConfigRoute configRoute);

	public boolean editConfigRoute(ConfigRoute configRoute);

	public boolean delConfigRoute(List<Integer> ids);

	public List<Object> findAll(ConfigRoute configRoute, int page_start, int page_length);

	public ConfigRoute getById(Integer id);

	int checkip(Integer id,String destion_ip);
}

