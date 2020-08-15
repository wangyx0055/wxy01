package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CmdPolicyUser;
import com.longersec.blj.domain.DTO.Users;

public interface CmdPolicyUserDao {

	public boolean editCmdPolicyUser(@Param("policy_id")Integer policy_id,@Param("user")List<Integer> user);

	public boolean addCmdPolicyUser(@Param("policy_id")Integer policy_id,@Param("user")List<Integer> user);

	public boolean delCmdPolicyUser(List<String> ids);

	public List<Object> findAll(@Param("cmdPolicyUser")CmdPolicyUser cmdPolicyUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Users> selectById(@Param("policy_id")Integer policy_id);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);
}
