package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.SystemUsage;

public interface SystemUsageDao {

	public boolean editSystemUsage(SystemUsage systemUsage);

	public boolean addSystemUsage(SystemUsage systemUsage);

	public boolean delSystemUsage(List<Integer> ids);

	public List<SystemUsage> findAll(	@Param("systemUsage")SystemUsage systemUsage,
									@Param("interval")String interval, 
									@Param("start_date")String start_date,
									@Param("end_date")String end_date
								);	

	public SystemUsage getById(Integer id);

}
