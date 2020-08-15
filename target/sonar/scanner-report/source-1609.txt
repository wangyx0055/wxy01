package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DeviceRecordCommandDao;
import com.longersec.blj.domain.DeviceRecordCommand;
import com.longersec.blj.service.DeviceRecordCommandService;


@Service
@Transactional
public class DeviceRecordCommandServiceImpl implements DeviceRecordCommandService{

	@Autowired
	private DeviceRecordCommandDao DeviceRecordCommandDao;

	@Override
	public boolean editDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand) {
		// TODO Auto-generated method stub
		return this.DeviceRecordCommandDao.editDeviceRecordCommand(deviceRecordCommand);
	}

	@Override
	public boolean addDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand) {
		// TODO Auto-generated method stub
		return this.DeviceRecordCommandDao.addDeviceRecordCommand(deviceRecordCommand);
	}

	@Override
	public boolean delDeviceRecordCommand(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.DeviceRecordCommandDao.delDeviceRecordCommand(ids);
	}

	@Override
	public List<Object> findAll(DeviceRecordCommand deviceRecordCommand, int page_start, int page_length) {
		return DeviceRecordCommandDao.findAll(deviceRecordCommand, page_start, page_length);
	}

	@Override
	public DeviceRecordCommand getById(Integer id) {
		return DeviceRecordCommandDao.getById(id);
	}

}
