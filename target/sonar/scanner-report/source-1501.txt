package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordPolicyApppub;

public interface ChangePasswordPolicyApppubService {

	public boolean addChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub);

	public boolean editChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub);

	public boolean delChangePasswordPolicyApppub(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicyApppub changePasswordPolicyApppub, int page_start, int page_length);

}

