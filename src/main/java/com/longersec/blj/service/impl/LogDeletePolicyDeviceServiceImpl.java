package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LogDeletePolicyDeviceDao;
import com.longersec.blj.domain.LogDeletePolicyDevice;
import com.longersec.blj.service.LogDeletePolicyDeviceService;


@Transactional
@Service
public class LogDeletePolicyDeviceServiceImpl implements LogDeletePolicyDeviceService{

	@Autowired
	private LogDeletePolicyDeviceDao LogDeletePolicyDeviceDao;

	@Override
	public boolean editLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDeviceDao.editLogDeletePolicyDevice(logDeletePolicyDevice);
	}

	@Override
	public boolean addLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDeviceDao.addLogDeletePolicyDevice(logDeletePolicyDevice);
	}

	@Override
	public boolean delLogDeletePolicyDevice(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LogDeletePolicyDeviceDao.delLogDeletePolicyDevice(ids);
	}

	@Override
	public List<Object> findAll(LogDeletePolicyDevice logDeletePolicyDevice, int page_start, int page_length) {
		return LogDeletePolicyDeviceDao.findAll(logDeletePolicyDevice, page_start, page_length);
	}

}
