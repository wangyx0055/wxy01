package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CrontabScriptConfigGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.Group;

public interface CrontabScriptConfigGroupService {

	public boolean addCrontabScriptConfigGroup(Integer config_id,List<Integer> groups);

	public boolean addCrontabScriptConfigDeviceGroup(Integer config_id,List<Integer> devicegroup);

	public boolean editCrontabScriptConfigGroup(Integer config_id,List<Integer> ugroup);

	public boolean editCrontabScriptConfigDeviceGroup(Integer config_id,List<Integer> dgroup);

	public List<UserGroup> selectById(Integer config_id);

	public List<DeviceGroup> selectBydIdDevice(Integer config_id);

	public Boolean deleteById(Integer config_id, int type);
}

