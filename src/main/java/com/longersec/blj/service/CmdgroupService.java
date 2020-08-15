package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Cmdgroup;
import com.longersec.blj.domain.DTO.Cmd;

public interface CmdgroupService {

	public boolean addCmdgroup(Cmdgroup cmdgroup);

	public boolean editCmdgroup(Cmdgroup cmdgroup);

	public boolean delCmdgroup(List<Integer> ids);

	public List<Object> findAll(Cmdgroup cmdgroup, String sname,Integer type,int page_start, int page_length);

	public List<Cmd> selectNameAndId();

    Cmdgroup checkname(String name);
}

