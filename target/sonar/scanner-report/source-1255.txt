package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigSnmp;

public interface ConfigSnmpDao {

	public boolean editConfigSnmp(ConfigSnmp configSnmp);

	public boolean addConfigSnmp(ConfigSnmp configSnmp);

	public boolean delConfigSnmp(List<Integer> ids);

	public List<Object> findAll(@Param("configSnmp")ConfigSnmp configSnmp, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
