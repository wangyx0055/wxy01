package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.AccessPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import org.apache.ibatis.annotations.Param;

public interface AccessPolicyDeviceAccountDao {

	public boolean editAccessPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("devices")List<Integer> devices);

	public boolean addAccessPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("devices")List<Integer> devices);

	public boolean delAccessPolicyDeviceAccount(List<String> ids);

	public List<Object> findAll(@Param("accessPolicyDeviceAccount")AccessPolicyDeviceAccount accessPolicyDeviceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Deviceaccess> selectById(@Param("policy_id")Integer policy_id);

	Integer selectAccessPolicyDeviceCounts(@Param("policy_id") Integer policy_id);

	public Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);

	Integer selectBydeviceId(@Param("item") Integer item);
}
