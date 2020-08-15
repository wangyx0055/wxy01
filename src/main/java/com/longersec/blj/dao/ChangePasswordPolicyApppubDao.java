package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyApppub;

public interface ChangePasswordPolicyApppubDao {

	public boolean editChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub);

	public boolean addChangePasswordPolicyApppub(ChangePasswordPolicyApppub changePasswordPolicyApppub);

	public boolean delChangePasswordPolicyApppub(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicyApppub")ChangePasswordPolicyApppub changePasswordPolicyApppub, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
