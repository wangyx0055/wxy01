package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AccessPolicyApppub;
import com.longersec.blj.domain.DTO.App;

public interface AccessPolicyApppubDao {

	Boolean editAccessPolicyApppub(@Param("policy_id")Integer policy_id,@Param("app")List<Integer> app);

	Boolean addAccessPolicyApppub(@Param("policy_id")Integer policy_id,@Param("app")List<Integer> app);

	List<App> selectById(@Param("policy_id")Integer policy_id);

    Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);
}
