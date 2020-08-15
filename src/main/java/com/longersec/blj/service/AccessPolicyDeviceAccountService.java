package com.longersec.blj.service;

import java.util.List;


import com.longersec.blj.dao.AccessPolicyDeviceAccountDao;
import com.longersec.blj.domain.AccessPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;
import org.apache.ibatis.annotations.Param;

public interface AccessPolicyDeviceAccountService {

	public boolean addAccessPolicyDevice(Integer policy_id,List<Integer> devices);

	public boolean delAccessPolicyDevice(List<String> ids);

	public List<Object> findAll(AccessPolicyDeviceAccount accessPolicyDeviceAccount, int page_start, int page_length);

	public List<Deviceaccess> selectById(Integer policy_id);

	Boolean editAccessPolicyDeviceAccount(Integer policy_id, List<Integer> devices);

	Boolean deleteBypolicy_id(Integer policy_id);

	int selectAccessPolicyDeviceCounts(int policy_id);

	int selectBydeviceId(int item);
}

