package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.domain.DTO.Users;

public interface AccessPolicyApppubService {

	Boolean addAccessPolicyApppub(Integer policy_id,List<Integer> app);

	Boolean editAccessPolicyApppub(Integer policy_id, List<Integer> app);

	List<App> selectById(Integer policy_id);

    Boolean deleteBypolicy_id(Integer policy_id);

}

