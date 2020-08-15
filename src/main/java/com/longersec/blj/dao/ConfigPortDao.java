package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigPort;

public interface ConfigPortDao {

	public boolean editConfigPort(ConfigPort configPort);

	public boolean addConfigPort(ConfigPort configPort);

	public boolean delConfigPort(List<Integer> ids);

	public List<Object> findAll(@Param("configPort")ConfigPort configPort, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
