package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicy;
import com.longersec.blj.domain.CmdPolicyCmd;

import org.apache.ibatis.annotations.Param;

public interface CmdPolicyService {

	public boolean addCmdPolicy(CmdPolicy cmdPolicy);

	public boolean editCmdPolicy(CmdPolicy cmdPolicy);

	public boolean editStatus(@Param("status")Integer status, @Param("id")Integer id);

	public boolean addCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	public boolean clearCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	public boolean delCmdPolicy(List<String> ids);

	public List<Object> findAll(CmdPolicy cmdPolicy,String sname ,Integer type ,String stat ,String cot ,int page_start, int page_length, List<Integer> depart_ids);

    public CmdPolicy checkName(String name);
    
    public List<String> getCommandsByDeviceAndUser(Integer device_account_id, Integer user_id);
    
    public CmdPolicy getById(Integer id);
}

