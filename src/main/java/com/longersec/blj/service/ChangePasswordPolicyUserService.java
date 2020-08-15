package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordPolicyUser;

public interface ChangePasswordPolicyUserService {

	public boolean addChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser);

	public boolean editChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser);

	public boolean delChangePasswordPolicyUser(List<Integer> ids);

	public List<Object> findAll(ChangePasswordPolicyUser changePasswordPolicyUser, int page_start, int page_length);

}

