package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Cmdgroup;
import com.longersec.blj.domain.DTO.Cmd;

public interface CmdgroupDao {

	public boolean editCmdgroup(Cmdgroup cmdgroup);

	public boolean addCmdgroup(Cmdgroup cmdgroup);

	public boolean delCmdgroup(List<Integer> ids);

	public List<Object> findAll(@Param("cmdgroup")Cmdgroup cmdgroup,@Param("sname")String sname,@Param("type")Integer type, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Cmd> selectNameAndId();

    public Cmdgroup checkname(String name);
}
