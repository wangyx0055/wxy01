package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;

public interface CmdPolicyGroupService {

	public boolean addCmdPolicyGroup(Integer policy_id,List<Integer> usergroup,List<Integer> devicegroup);
	
	public boolean editCmdPolicyUserGroup(Integer policy_id,List<Integer> usergroup);
	
	public boolean editCmdPolicyDeviceGroup(Integer policy_id,List<Integer> devicegroup);

	public boolean delCmdPolicyGroup(List<String> ids);

	public List<Object> findAll(CmdPolicyGroup cmdPolicyGroup, int page_start, int page_length);

	public List<UserGroup> selectByIdUser(Integer policy_id);
	
	public List<DeviceGroup> selectBydIdDevice(Integer policy_id);
	
	Boolean deleteBypolicy_id(Integer policy_id,int type);
}

