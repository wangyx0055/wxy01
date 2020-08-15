package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LogDeletePolicyUser;

public interface LogDeletePolicyUserDao {

	public boolean editLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser);

	public boolean addLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser);

	public boolean delLogDeletePolicyUser(List<Integer> ids);

	public List<Object> findAll(@Param("logDeletePolicyUser")LogDeletePolicyUser logDeletePolicyUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
