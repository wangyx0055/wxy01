package com.longersec.blj.service.impl;

import java.util.List;

import com.longersec.blj.domain.DTO.Deviceaccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.CrontabScriptConfigDeviceDao;
import com.longersec.blj.domain.CrontabScriptConfigDevice;
import com.longersec.blj.service.CrontabScriptConfigDeviceService;


@Transactional
@Service
public class CrontabScriptConfigDeviceServiceImpl implements CrontabScriptConfigDeviceService{

	@Autowired
	private CrontabScriptConfigDeviceDao CrontabScriptConfigDeviceDao;



	@Override
	public List<Deviceaccess> selectById(Integer config_id) {
		return CrontabScriptConfigDeviceDao.selectById(config_id);
	}

	@Override
	public boolean addCrontabScriptConfigDevice(Integer config_id,List<Integer> device) {
		// TODO Auto-generated method stub
		return this.CrontabScriptConfigDeviceDao.addCrontabScriptConfigDevice(config_id,device);
	}

	@Override
	public boolean delCrontabScriptConfigDevice(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.CrontabScriptConfigDeviceDao.delCrontabScriptConfigDevice(ids);
	}

	@Override
	public List<Object> findAll(CrontabScriptConfigDevice crontabScriptConfigDevice, int page_start, int page_length) {
		return CrontabScriptConfigDeviceDao.findAll(crontabScriptConfigDevice, page_start, page_length);
	}

	@Override
	public boolean editCrontabScriptConfigDevice(Integer config_id, List<Integer> list) {
		return CrontabScriptConfigDeviceDao.editCrontabScriptConfigDevice(config_id,list);
	}

	@Override
	public Boolean deleteByid(Integer config_id) {
		return CrontabScriptConfigDeviceDao.deleteByid(config_id);
	}
}
