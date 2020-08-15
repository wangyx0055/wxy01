package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.dao.DeviceDao;
import com.longersec.blj.domain.Device;
import com.longersec.blj.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DeviceTypeDao;
import com.longersec.blj.domain.DeviceType;
import com.longersec.blj.service.DeviceTypeService;


@Transactional
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService{

	@Autowired
	private DeviceTypeDao DeviceTypeDao;
	@Autowired
	private DeviceDao deviceDao;

	@Override
	public boolean editDeviceType(DeviceType deviceType) {
		// TODO Auto-generated method stub
		return this.DeviceTypeDao.editDeviceType(deviceType);
	}

	@Override
	public boolean addDeviceType(DeviceType deviceType) {
		// TODO Auto-generated method stub
		return this.DeviceTypeDao.addDeviceType(deviceType);
	}

	@Override
	public boolean delDeviceType(List<Integer> ids) {
		// TODO Auto-generated method stub
		for (Integer item : ids){
			Device device = deviceDao.checkSys_type(item);
			if (device!=null){
				ids.remove(item);
			}
		}
		return ids.size() != 0 && this.DeviceTypeDao.delDeviceType(ids);
	}

	@Override
	public List<Object> findAll(DeviceType deviceType, int page_start, int page_length) {
		return DeviceTypeDao.findAll(deviceType, page_start, page_length);
	}

	@Override
	public ArrayList<DeviceType> listType() {
		return DeviceTypeDao.listType();
	}

	@Override
	public DeviceType checkname(String name) {
		return DeviceTypeDao.checkname(name);
	}

	@Override
	public int checOsType(String name) {
		return DeviceTypeDao.checOsType(name);
	}
}
