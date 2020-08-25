package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;

public interface CmdPolicyGroupService {

	Boolean addCmdPolicyGroup(Integer policy_id,List<Integer> usergroup,List<Integer> devicegroup);
	
	Boolean editCmdPolicyUserGroup(Integer policy_id,List<Integer> usergroup);
	
	Boolean editCmdPolicyDeviceGroup(Integer policy_id,List<Integer> devicegroup);

	List<Object> findAll(CmdPolicyGroup cmdPolicyGroup, int page_start, int page_length);

	List<UserGroup> selectByIdUser(Integer policy_id);
	
	List<DeviceGroup> selectBydIdDevice(Integer policy_id);
	
	Boolean deleteBypolicy_id(Integer policy_id,int type);
}

