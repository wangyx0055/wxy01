package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.ApppubServer;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.domain.ApppubProgram;

public interface ApppubProgramDao {
	public List<ApppubProgram> selectAll();
	public boolean editApppubProgram(ApppubProgram apppubProgram);

	public boolean addApppubProgram(ApppubProgram apppubProgram);

	public boolean delApppubProgram(List<Integer> ids);

	public List<Object> findAll(@Param("apppubProgram")ApppubProgram apppubProgram,@Param("sname")String sname,@Param("type")Integer type, @Param("page_start")int page_start, @Param("page_length")int page_length);

    
    public int total();
    public ApppubProgram getById(@Param("id") Integer id);
    
	public ApppubProgram checkAppName(String name);
}
