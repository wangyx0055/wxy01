package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.domain.DTO.Users;

public interface AccessPolicyApppubService {

	public boolean addAccessPolicyApppub(Integer policy_id,List<Integer> app);

	public boolean editAccessPolicyApppub(Integer policy_id, List<Integer> app);

	public boolean delAccessPolicyApppub(List<String> ids);

	public List<Object> findAll(AccessPolicyApppub accessPolicyApppub, int page_start, int page_length);

	public List<App> selectById(Integer policy_id);

    Boolean deleteBypolicy_id(Integer policy_id);

}

