package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyCmdgroup;
import com.longersec.blj.domain.DTO.Cmd;

public interface CmdPolicyCmdgroupService {

	public boolean addCmdPolicyCmdgroup(Integer policy_id,List<Integer> cmdgroup);

	public boolean editCmdPolicyCmdgroup(Integer policy_id,List<Integer> cmdgroup);

	public boolean delCmdPolicyCmdgroup(List<String> ids);

	public List<Object> findAll(CmdPolicyCmdgroup cmdPolicyCmdgroup, int page_start, int page_length);

    public List<Cmd> selectById(Integer policy_id);
	
	Boolean deleteBypolicy_id(Integer policy_id);
}

