package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.ApppubProgram;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabScriptConfig;

public interface CrontabScriptConfigDao {

	public boolean editCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig);

	public boolean addCrontabScriptConfig(CrontabScriptConfig crontabScriptConfig);

	boolean delCrontabScriptConfig(List<Integer> ids);

	public List<Object> findAll(@Param("crontabScriptConfig")CrontabScriptConfig crontabScriptConfig, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	public String selectName(@Param("id") Integer id,@Param("name") String name);

	public List<CrontabScriptConfig> selectAll();

    CrontabScriptConfig checkname(String name);
}
