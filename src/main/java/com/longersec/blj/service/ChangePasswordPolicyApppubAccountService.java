package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordPolicyApppubAccount;

public interface ChangePasswordPolicyApppubAccountService {

	public boolean addChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount);

	public boolean editChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount);

	public boolean delChangePasswordPolicyApppubAccount(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount, int page_start, int page_length);

}

