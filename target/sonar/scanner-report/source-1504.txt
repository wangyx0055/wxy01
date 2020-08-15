package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordPolicy;

public interface ChangePasswordPolicyService {

	public boolean addChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy);

	public boolean editChangePasswordPolicy(ChangePasswordPolicy changePasswordPolicy);

	public boolean delChangePasswordPolicy(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicy changePasswordPolicy, String name,int page_start, int page_length);

	 public List<ChangePasswordPolicy> checkName();
}

