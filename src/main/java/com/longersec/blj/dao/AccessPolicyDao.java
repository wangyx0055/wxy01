package com.longersec.blj.dao;

import com.longersec.blj.domain.AccessPolicy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccessPolicyDao {

	boolean editAccessPolicy(AccessPolicy accessPolicy);

	boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	Integer addAccessPolicy(AccessPolicy accessPolicy);

	boolean delAccessPolicy(List<String> ids);

	List<Object> findAll(@Param("accessPolicy")AccessPolicy accessPolicy, @Param("sname")String sname, @Param("stat")String stat,@Param("type")Integer type,@Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	List<AccessPolicy> getUserPolicy(@Param("userid")Integer userid, @Param("device_account_id")Integer device_account_id, @Param("apppub_account_id")Integer apppub_account_id);
    
    AccessPolicy getById(@Param("id")Integer id);

    List<AccessPolicy> selectAll();

    AccessPolicy checkname(@Param("name") String name);
}
