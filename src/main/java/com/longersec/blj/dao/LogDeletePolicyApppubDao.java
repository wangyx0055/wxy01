package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LogDeletePolicyApppub;

public interface LogDeletePolicyApppubDao {

	public boolean editLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub);

	public boolean addLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub);

	public boolean delLogDeletePolicyApppub(List<Integer> ids);

	public List<Object> findAll(@Param("logDeletePolicyApppub")LogDeletePolicyApppub logDeletePolicyApppub, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
