package com.longersec.blj.dao;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CrontabScriptConfigGroup;

public interface CrontabScriptConfigGroupDao {

	public boolean editCrontabScriptConfigGroup(@Param("config_id")Integer config_id,@Param("ugroup")List<Integer> ugroup);

	public boolean editCrontabScriptConfigDeviceGroup(@Param("config_id")Integer config_id,@Param("dgroup")List<Integer> dgroup);

	public boolean addCrontabScriptConfigGroup(@Param("config_id")Integer config_id,@Param("groups")List<Integer> groups);

	public boolean addCrontabScriptConfigDeviceGroup(@Param("config_id")Integer config_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean delCrontabScriptConfigGroup(List<Integer> ids);

	public List<Object> findAll(@Param("crontabScriptConfigGroup")CrontabScriptConfigGroup crontabScriptConfigGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<UserGroup> selectById(@Param("config_id")Integer config_id);

	public List<DeviceGroup> selectBydIdDevice(@Param("config_id")Integer config_id);

    public Boolean deleteById(@Param("config_id") Integer config_id, @Param("type") int type);
}
