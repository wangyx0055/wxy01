package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LogDeletePolicy;

public interface LogDeletePolicyDao {

	public boolean editLogDeletePolicy(LogDeletePolicy logDeletePolicy);

	public boolean addLogDeletePolicy(LogDeletePolicy logDeletePolicy);

	public boolean delLogDeletePolicy(List<Integer> ids);

	public List<Object> findAll(@Param("logDeletePolicy")LogDeletePolicy logDeletePolicy, @Param("name")String name,@Param("page_start")int page_start, @Param("page_length")int page_length);

}
