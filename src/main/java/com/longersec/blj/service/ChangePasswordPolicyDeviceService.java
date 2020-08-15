package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.ChangePasswordPolicyDevice;

public interface ChangePasswordPolicyDeviceService {

	public boolean addChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice);

	public boolean editChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice);

	public boolean delChangePasswordPolicyDevice(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicyDevice changePasswordPolicyDevice, int page_start, int page_length);

	public boolean addChangePasswordPolicyDevice(Integer policy_id,List<Integer> devices);

}

