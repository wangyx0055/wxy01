package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface CmdPolicyDeviceAccountService {

	public boolean addCmdPolicyDeviceAccount(Integer policy_id,List<Integer> device);

	public boolean editCmdPolicyDeviceAccount(Integer policy_id,List<Integer> device);

	public boolean delCmdPolicyDeviceAccount(List<String> ids);

	public List<Object> findAll(CmdPolicyDeviceAccount cmdPolicyDeviceAccount, int page_start, int page_length);

    public List<Deviceaccess> selectById(Integer policy_id);
	
	Boolean deleteBypolicy_id(Integer policy_id);
}

