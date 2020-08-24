package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.User;

public interface AccessPolicyDao {

	public boolean editAccessPolicy(AccessPolicy accessPolicy);

	public boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	public Integer addAccessPolicy(AccessPolicy accessPolicy);

	public boolean delAccessPolicy(List<String> ids);

	public List<Object> findAll(@Param("accessPolicy")AccessPolicy accessPolicy, @Param("sname")String sname, @Param("stat")String stat,@Param("type")Integer type,@Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);
    
    public List<AccessPolicy> getUserPolicy(@Param("userid")Integer userid, @Param("device_account_id")Integer device_account_id, @Param("apppub_account_id")Integer apppub_account_id);
    
    public AccessPolicy getById(@Param("id")Integer id);

    public List<AccessPolicy> selectAll();

    public AccessPolicy checkname(@Param("name") String name);
}
