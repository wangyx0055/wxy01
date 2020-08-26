package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.CmdPolicy;
import com.longersec.blj.domain.CmdPolicyCmd;

public interface CmdPolicyDao {

	boolean editCmdPolicy(CmdPolicy cmdPolicy);

	boolean editStatus(@Param("status")Integer status,@Param("id")Integer id);

	boolean addCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	boolean clearCmdPolicyCmd(CmdPolicyCmd cmdPolicyCmd);

	boolean addCmdPolicy(CmdPolicy cmdPolicy);

	boolean delCmdPolicy(List<String> ids);

	List<Object> findAll(@Param("cmdPolicy")CmdPolicy cmdPolicy,@Param("sname")String sname, @Param("type")Integer type, @Param("stat")String stat,@Param("cot")String cot, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	CmdPolicy checkName(@Param("name") String name);
    
	List<String> getCommandsByDeviceAndUser(@Param("device_account_id") Integer device_account_id, @Param("user_id")Integer user_id);

	CmdPolicy getById(@Param("id")Integer id);
}
