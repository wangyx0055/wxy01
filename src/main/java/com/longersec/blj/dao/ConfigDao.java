package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Config;

public interface ConfigDao {

	public boolean editConfig(Config config);

	public boolean addConfig(Config config);

	public boolean delConfig(List<Integer> ids);

	public List<Object> findAll(@Param("config")Config config, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public Config getByName(@Param("name")String name);
	
	public Integer query(@Param("sql")String sql);

}
