package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyUser;

public interface ChangePasswordPolicyUserDao {

	public boolean editChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser);

	public boolean addChangePasswordPolicyUser(ChangePasswordPolicyUser changePasswordPolicyUser);

	public boolean delChangePasswordPolicyUser(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicyUser")ChangePasswordPolicyUser changePasswordPolicyUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
