package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CmdPolicyCmdgroup;
import com.longersec.blj.domain.DTO.Cmd;

public interface CmdPolicyCmdgroupDao {

	public boolean editCmdPolicyCmdgroup(@Param("policy_id")Integer policy_id,@Param("cmdgroup") List<Integer> cmdgroup);

	public boolean addCmdPolicyCmdgroup(@Param("policy_id")Integer policy_id,@Param("cmdgroup") List<Integer> cmdgroup);

	public boolean delCmdPolicyCmdgroup(List<String> ids);

	public List<Object> findAll(@Param("CmdPolicyCmdgroup")CmdPolicyCmdgroup cmdPolicyCmdgroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<Cmd> selectById(@Param("policy_id")Integer policy_id);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);
}
