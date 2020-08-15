package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigRoute;

public interface ConfigRouteDao {

	public boolean editConfigRoute(ConfigRoute configRoute);

	public boolean addConfigRoute(ConfigRoute configRoute);

	public boolean delConfigRoute(List<Integer> ids);

	public List<Object> findAll(@Param("configRoute")ConfigRoute configRoute, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigRoute getById(Integer id);

    int checkip(@Param("id") Integer id,@Param("destion_ip") String destion_ip);
}
