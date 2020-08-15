package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CmdPolicyApppub;

public interface CmdPolicyApppubDao {

	public boolean editCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub);

	public boolean addCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub);

	public boolean delCmdPolicyApppub(List<String> ids);

	public List<Object> findAll(@Param("cmdPolicyApppub")CmdPolicyApppub cmdPolicyApppub, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
