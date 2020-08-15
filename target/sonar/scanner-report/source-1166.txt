package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AccessPolicyGroup;

public interface AccessPolicyGroupDao {

	public boolean editAccessPolicyGroup(@Param("policy_id")Integer policy_id,@Param("groups")List<Integer> groups);
	
	public boolean editAccessPolicyDeviceGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean addAccessPolicyGroup(@Param("policy_id")Integer policy_id,@Param("groups")List<Integer> groups);

	public boolean addAccessPolicyDeviceGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean delAccessPolicyGroup(List<String> ids);

	public List<Object> findAll(@Param("accessPolicyGroup")AccessPolicyGroup accessPolicyGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<UserGroup> selectById(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	public List<DeviceGroup> selectBydIdDevice(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id, @Param("type") int type);
}
