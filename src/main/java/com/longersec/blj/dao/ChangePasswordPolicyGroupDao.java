package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyGroup;

public interface ChangePasswordPolicyGroupDao {

	public boolean editChangePasswordPolicyGroup(@Param("policy_id")Integer policy_id,@Param("groups")List<Integer> groups);
	
	public boolean editChangePasswordPolicyDeviceGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean addChangePasswordPolicyDeviceGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean delChangePasswordPolicyGroup(List<String> ids);

	public List<Object> findAll(@Param("changePasswordPolicyGroup")ChangePasswordPolicyGroup changePasswordPolicyGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<UserGroup> selectById(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	public List<DeviceGroup> selectBydIdDevice(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id, @Param("type") int type);
}
