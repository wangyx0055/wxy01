package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabScriptConfigGroupDao;
import com.longersec.blj.domain.CrontabScriptConfigGroup;
import com.longersec.blj.service.CrontabScriptConfigGroupService;


@Transactional
@Service
public class CrontabScriptConfigGroupServiceImpl implements CrontabScriptConfigGroupService{

	@Autowired
	private CrontabScriptConfigGroupDao CrontabScriptConfigGroupDao;


	@Override
	public boolean addCrontabScriptConfigGroup(Integer config_id, List<Integer> groups) {
		return CrontabScriptConfigGroupDao.addCrontabScriptConfigGroup(config_id,groups);
	}

	@Override
	public boolean addCrontabScriptConfigDeviceGroup(Integer config_id, List<Integer> devicegroup) {
		return CrontabScriptConfigGroupDao.addCrontabScriptConfigDeviceGroup(config_id,devicegroup);
	}

	@Override
	public boolean editCrontabScriptConfigGroup(Integer config_id, List<Integer> ugroup) {
		return CrontabScriptConfigGroupDao.editCrontabScriptConfigGroup(config_id,ugroup);
	}

	@Override
	public boolean editCrontabScriptConfigDeviceGroup(Integer config_id, List<Integer> dgroup) {
		return CrontabScriptConfigGroupDao.editCrontabScriptConfigDeviceGroup(config_id,dgroup);
	}

	@Override
	public List<UserGroup> selectById(Integer config_id) {
		return CrontabScriptConfigGroupDao.selectById(config_id);
	}

	@Override
	public List<DeviceGroup> selectBydIdDevice(Integer config_id) {
		return CrontabScriptConfigGroupDao.selectBydIdDevice(config_id);
	}


	@Override
	public Boolean deleteById(Integer config_id, int type) {
		return CrontabScriptConfigGroupDao.deleteById(config_id,type);
	}
}
