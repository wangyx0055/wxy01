package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.ChangePasswordPolicyGroup;

public interface ChangePasswordPolicyGroupService {

	public boolean addChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup);

	public boolean editChangePasswordPolicyGroup(ChangePasswordPolicyGroup changePasswordPolicyGroup);

	public boolean delChangePasswordPolicyGroup(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicyGroup changePasswordPolicyGroup, int page_start, int page_length);

	public boolean addChangePasswordPolicyGroup(Integer policy_id,List<Integer> devicegroup);

}

