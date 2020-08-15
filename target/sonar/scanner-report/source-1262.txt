package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.DTO.Deviceaccess;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabScriptConfigDevice;

public interface CrontabScriptConfigDeviceDao {

	public boolean editCrontabScriptConfigDevice(@Param("config_id")Integer config_id,@Param("list")List<Integer> list);

	public boolean addCrontabScriptConfigDevice(@Param("config_id") Integer config_id,@Param("device")List<Integer> device);

	public boolean delCrontabScriptConfigDevice(List<Integer> ids);

	public List<Object> findAll(@Param("crontabScriptConfigDevice")CrontabScriptConfigDevice crontabScriptConfigDevice, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<Deviceaccess> selectById(@Param("config_id")Integer config_id);

    public Boolean deleteByid(@Param("config_id") Integer config_id);
}
