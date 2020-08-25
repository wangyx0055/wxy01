package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface ChangePasswordPolicyDeviceAccountDao {

	public boolean addChangePasswordPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("devices")List<Integer> devices);
	
	public boolean editChangePasswordPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("devices")List<Integer> devices);

	public boolean delChangePasswordPolicyDeviceAccount(List<String> ids);

	public List<Object> findAll(@Param("changePasswordPolicyDeviceAccount")ChangePasswordPolicyDeviceAccount changePasswordPolicyDevice, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public List<Deviceaccess> selectById(@Param("policy_id")Integer policy_id);

	public Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);

}
