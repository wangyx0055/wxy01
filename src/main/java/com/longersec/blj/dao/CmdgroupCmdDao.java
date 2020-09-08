package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CmdgroupCmd;

public interface CmdgroupCmdDao {

	public boolean editCmdgroupCmd(CmdgroupCmd cmdgroupCmd);

	public boolean addCmdgroupCmd(CmdgroupCmd cmdgroupCmd);

	public boolean delCmdgroupCmd(List<Integer> ids);

	public boolean deletegroupcmd(Integer id);

	public List<Object> findAll(@Param("cmdgroupCmd")CmdgroupCmd cmdgroupCmd, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Object> queryCmdGroupCmdByGroupId (@Param("group_id") int group_id,@Param("sname")String sname,@Param("type")Integer type,@Param("cmdgroupCmd") CmdgroupCmd cmdgroupCmd,@Param("page_start")int page_start, @Param("page_length")int page_length);
}
