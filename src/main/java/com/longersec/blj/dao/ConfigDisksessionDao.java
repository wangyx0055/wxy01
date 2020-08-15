package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.ConfigDisksession;

public interface ConfigDisksessionDao {

	public boolean editConfigDisksession(ConfigDisksession configDisksession);

	public boolean addConfigDisksession(ConfigDisksession configDisksession);

	public boolean delConfigDisksession(List<Integer> ids);

	public List<Object> findAll(@Param("configDisksession")ConfigDisksession configDisksession, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigDisksession get();
}

