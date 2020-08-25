package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.ChangePasswordPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface ChangePasswordPolicyDeviceAccountService {

	public boolean addChangePasswordPolicyDeviceAccount(Integer policy_id,List<Integer> devices);

	public boolean editChangePasswordPolicyDeviceAccount(Integer policy_id,List<Integer> devices);

	public boolean delChangePasswordPolicyDeviceAccount(List<String> ids);

	public List<Object> findAll(ChangePasswordPolicyDeviceAccount changePasswordPolicyDeviceAccount, int page_start, int page_length);
	
	Boolean deleteBypolicy_id(Integer policy_id);

	List<Deviceaccess> selectById(Integer policy_id);

}

