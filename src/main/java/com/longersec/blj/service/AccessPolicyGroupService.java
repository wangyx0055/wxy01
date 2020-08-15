package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AccessPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;

public interface AccessPolicyGroupService {

	public boolean addAccessPolicyGroup(Integer policy_id,List<Integer> groups,List<Integer> devicegroup);

	public boolean editAccessPolicyGroup(Integer policy_id,List<Integer> groups);
	
	public boolean editAccessPolicyDeviceGroup(Integer policy_id,List<Integer> devicegroup);

	public boolean delAccessPolicyGroup(List<String> ids);

	public List<Object> findAll(AccessPolicyGroup accessPolicyGroup, int page_start, int page_length);

	public List<UserGroup> selectById(Integer policy_id);
	
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id);
	
	 Boolean deleteBypolicy_id(Integer policy_id,int type);
}

