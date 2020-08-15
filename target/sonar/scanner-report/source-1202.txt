package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordPolicyDevice;

public interface ChangePasswordPolicyDeviceDao {

	public boolean editChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice);

	public boolean addChangePasswordPolicyDevice(ChangePasswordPolicyDevice changePasswordPolicyDevice);

	public boolean delChangePasswordPolicyDevice(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordPolicyDevice")ChangePasswordPolicyDevice changePasswordPolicyDevice, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public boolean addChangePasswordPolicyDevice(@Param("policy_id")Integer policy_id,@Param("devices")List<Integer> devices);

}
