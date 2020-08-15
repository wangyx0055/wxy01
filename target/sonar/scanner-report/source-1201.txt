package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicy;

public interface ChangePasswordPolicyDao {

	public boolean editChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy);

	public boolean addChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy);

	public boolean delChangePasswordPolicy(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicy")ChangePasswordPolicy changePasswordPolicy,@Param("name")String name, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<ChangePasswordPolicy> checkName();
}
