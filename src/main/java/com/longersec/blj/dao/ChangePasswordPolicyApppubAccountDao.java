package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyApppubAccount;

public interface ChangePasswordPolicyApppubAccountDao {

	public boolean editChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount);

	public boolean addChangePasswordPolicyApppubAccount(ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount);

	public boolean delChangePasswordPolicyApppubAccount(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicyApppubAccount")ChangePasswordPolicyApppubAccount changePasswordPolicyApppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
