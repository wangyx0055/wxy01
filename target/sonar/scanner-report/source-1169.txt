package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AccessPolicyUser;

public interface AccessPolicyUserDao {

	public boolean editAccessPolicyUser(@Param("policy_id")Integer policy_id,@Param("users")List<Integer> users);

	public boolean addAccessPolicyUser(@Param("policy_id")Integer policy_id,@Param("users")List<Integer> users);

	public boolean delAccessPolicyUser(List<Integer> ids);

	public List<Object> findAll(@Param("accessPolicyUser")AccessPolicyUser accessPolicyUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Users> selectById(@Param("policy_id")Integer policy_id);

    Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);

    /** 用户分组关联用户成员数 **/
    int selectAccessPolicyUserCounts(@Param("policy_id") int policy_id);
}
