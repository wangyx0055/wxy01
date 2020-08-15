package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LogDeletePolicyGroup;

public interface LogDeletePolicyGroupDao {

	public boolean editLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup);

	public boolean addLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup);

	public boolean delLogDeletePolicyGroup(List<Integer> ids);

	public List<Object> findAll(@Param("logDeletePolicyGroup")LogDeletePolicyGroup logDeletePolicyGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
