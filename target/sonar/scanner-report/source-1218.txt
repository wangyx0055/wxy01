package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.CmdPolicyDeviceAccount;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface CmdPolicyDeviceAccountDao {

	public boolean editCmdPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("device") List<Integer> device);

	public boolean addCmdPolicyDeviceAccount(@Param("policy_id")Integer policy_id,@Param("device") List<Integer> device);

	public boolean delCmdPolicyDeviceAccount(List<String> ids);

	public List<Object> findAll(@Param("cmdPolicyDeviceAccount")CmdPolicyDeviceAccount cmdPolicyDeviceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<Deviceaccess> selectById(@Param("policy_id")Integer policy_id);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id);
}
