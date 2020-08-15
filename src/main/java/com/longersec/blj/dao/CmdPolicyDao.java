package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.CmdPolicy;
import com.longersec.blj.domain.CmdPolicyCmd;

public interface CmdPolicyDao {

	public boolean editCmdPolicy(CmdPolicy cmdPolicy);

	public boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	public boolean addCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	public boolean clearCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	public boolean addCmdPolicy(CmdPolicy cmdPolicy);

	public boolean delCmdPolicy(List<String> ids);

	public List<Object> findAll(@Param("cmdPolicy")CmdPolicy cmdPolicy,@Param("sname")String sname, @Param("type")Integer type, @Param("stat")String stat,@Param("cot")String cot, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public CmdPolicy checkName(@Param("name") String name);
    
    public List<String> getCommandsByDeviceAndUser(@Param("device_account_id") Integer device_account_id, @Param("user_id")Integer user_id, @Param("group_id")Integer group_id);

    public CmdPolicy getById(@Param("id")Integer id);
}
