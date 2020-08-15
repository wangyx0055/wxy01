package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyUser;
import com.longersec.blj.domain.DTO.Users;

public interface CmdPolicyUserService {

	public boolean addCmdPolicyUser(Integer policy_id,List<Integer> user);

	public boolean editCmdPolicyUser(Integer policy_id,List<Integer> user);

	public boolean delCmdPolicyUser(List<String> ids);

	public List<Object> findAll(CmdPolicyUser cmdPolicyUser, int page_start, int page_length);

	public List<Users> selectById(Integer policy_id);
	
	Boolean deleteBypolicy_id(Integer policy_id);
		
}

