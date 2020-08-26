package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.User;

public interface AccessPolicyService {

	Integer addAccessPolicy(AccessPolicy accessPolicy);

	boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	boolean editAccessPolicy(AccessPolicy accessPolicy);

	boolean delAccessPolicy(List<String> ids);

	List<Object> findAll(AccessPolicy accessPolicy, String sname , String stat , Integer type, int page_start, int page_length, List<Integer> depart_ids);
    
	List<AccessPolicy> getUserPolicy(Integer userid, Integer device_account_id, Integer apppub_account_id);
    
	AccessPolicy getById(@Param("id")Integer id);

	AccessPolicy checkname(String name);
}

