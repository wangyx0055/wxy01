package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;

public interface AccessPolicyApppubDao {

	public boolean editAccessPolicyApppub(@Param("policy_id")Integer policy_id,@Param("app")List<Integer> app);

	public boolean addAccessPolicyApppub(@Param("policy_id")Integer policy_id,@Param("app")List<Integer> app);

	public boolean delAccessPolicyApppub(List<String> ids);

	public List<Object> findAll(@Param("accessPolicyApppub")AccessPolicyApppub accessPolicyApppub, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<App> selectById(@Param("policy_id")Integer policy_id);

    Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);
}
