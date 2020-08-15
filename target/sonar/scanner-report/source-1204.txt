package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyGroup;

public interface ChangePasswordPolicyGroupDao {

	public boolean editChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup);

	public boolean addChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup);

	public boolean delChangePasswordPolicyGroup(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicyGroup")ChangePasswordPolicyGroup changePasswordPolicyGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public boolean addChangePasswordPolicyGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

}
