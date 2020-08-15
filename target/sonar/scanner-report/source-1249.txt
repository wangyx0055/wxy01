package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigRadius;

public interface ConfigRadiusDao {

	public boolean editConfigRadius(ConfigRadius configRadius);

	public boolean addConfigRadius(ConfigRadius configRadius);

	public boolean delConfigRadius(List<Integer> ids);

	public List<Object> findAll(@Param("configRadius")ConfigRadius configRadius, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
