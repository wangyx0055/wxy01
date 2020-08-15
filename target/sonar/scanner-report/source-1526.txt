package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Config;

public interface ConfigService {

	public boolean addConfig(Config config);

	public boolean editConfig(Config config);

	public boolean delConfig(List<Integer> ids);

	public List<Object> findAll(Config config, int page_start, int page_length);
	
	public Config getByName(String name);
	
	public Integer query(String sql);

}

