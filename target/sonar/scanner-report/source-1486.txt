package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AccessPolicyUser;
import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Param;

public interface AccessPolicyUserService {

	public boolean addAccessPolicyUser(Integer policy_id,List<Integer> users);

	public boolean editAccessPolicyUser(Integer policy_id,List<Integer> users);

	public boolean delAccessPolicyUser(List<Integer> ids);

	public List<Object> findAll(AccessPolicyUser accessPolicyUser, int page_start, int page_length);

	public List<Users> selectById(Integer policy_id);

    Boolean deleteBypolicy_id(Integer policy_id);

	int selectAccessPolicyUserCounts(int policy_id);
}

