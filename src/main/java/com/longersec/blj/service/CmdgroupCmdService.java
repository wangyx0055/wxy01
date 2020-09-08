package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdgroupCmd;
import org.apache.ibatis.annotations.Param;

public interface CmdgroupCmdService {

	public boolean addCmdgroupCmd(CmdgroupCmd cmdgroupCmd);

	public boolean editCmdgroupCmd(CmdgroupCmd cmdgroupCmd);

	public boolean delCmdgroupCmd(List<Integer> ids);

	public boolean deletegroupcmd(Integer id);

	public List<Object> findAll(CmdgroupCmd cmdgroupCmd, int page_start, int page_length);

	public List<Object> queryCmdGroupCmdByGroupId (int group_id,String sname,Integer type, CmdgroupCmd cmdgroupCmd,int page_start,int page_length);

}

