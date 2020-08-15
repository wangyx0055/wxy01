package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.User;

public interface AccessPolicyService {

	public Integer addAccessPolicy(AccessPolicy accessPolicy);

	public boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	public boolean editAccessPolicy(AccessPolicy accessPolicy);

	public boolean delAccessPolicy(List<String> ids);

	public List<Object> findAll(AccessPolicy accessPolicy, String sname , String stat , Integer type, int page_start, int page_length, List<Integer> depart_ids);
    
    public List<AccessPolicy> getUserPolicy(Integer userid, Integer groupid, Integer device_account_id, Integer apppub_account_id);
    
    public AccessPolicy getById(@Param("id")Integer id);

    public AccessPolicy checkname(String name);
}

