package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.DTO.Users;

public interface ChangePasswordPolicyGroupService {

	public boolean addChangePasswordPolicyGroup(Integer policy_id,List<Integer> devicegroup);

	public boolean editChangePasswordPolicyGroup(Integer policy_id,List<Integer> devicegroup);
	
	public boolean editChangePasswordPolicyDeviceGroup(Integer policy_id,List<Integer> devicegroup);

	public boolean delChangePasswordPolicyGroup(List<String> ids);

	public List<Object> findAll(ChangePasswordPolicyGroup changePasswordPolicyGroup, int page_start, int page_length);

	public List<UserGroup> selectById(Integer policy_id);
	
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id);
	
	 Boolean deleteBypolicy_id(Integer policy_id,int type);
}

