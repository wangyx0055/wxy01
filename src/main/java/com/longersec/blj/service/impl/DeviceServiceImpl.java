package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.dao.DeviceAccountDao;
import com.longersec.blj.dao.GroupDao;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DeviceDao;
import com.longersec.blj.domain.Device;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.ConfigPasswordEncryptKeyService;
import com.longersec.blj.service.DeviceService;
import com.longersec.blj.utils.Sm4Utils;


@Transactional
@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceDao DeviceDao;

	@Autowired
	private DeviceAccountDao deviceAccountDao;
	
	@Autowired
	private ConfigPasswordEncryptKeyService configPasswordEncryptKeyService;

	@Override
	public boolean editDevice(Device device) {
		return this.DeviceDao.editDevice(device);
	}

	@Override
	public Integer selectOldDepartment(int id) {
		return DeviceDao.selectOldDepartment(id);
	}

	@Override
	public boolean addDevice(Device device) {
		DeviceAccount deviceAccount = new DeviceAccount();
		device.setAccount_count(1);
		boolean a = DeviceDao.addDevice(device);
		deviceAccount.setDevice_id(device.getId());
		deviceAccount.setLogin_method(device.getLogin_method());
		deviceAccount.setUsername(device.getAccount());
		deviceAccount.setPassword(Sm4Utils.encryptEcb(configPasswordEncryptKeyService.getKey(), device.getPassword()));
		deviceAccount.setProtocol_id(device.getProtocol_id());
		deviceAccount.setPort(device.getPort());
		deviceAccount.setSsh_key(device.getSsh_key());
		deviceAccount.setFile_id(device.getFile_id());
		deviceAccount.setSsh_password(device.getSsh_password());
		boolean c = deviceAccountDao.addDeviceAccount(deviceAccount);
		if ( a && c){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delDevice(List<Integer> ids) {
		return this.DeviceDao.delDevice(ids);
	}

	@Override
	public boolean deleteUselessDevice(int department) {
		return DeviceDao.deleteUselessDevice(department);
	}

	@Override
	public List<Object> findAll(Device device, int page_start, int page_length,List<Integer> depart_ids) {
		return DeviceDao.findAll(device, page_start, page_length,depart_ids);
	}
	

	@Override
	public Device checkip(String ip) {
		return DeviceDao.checkip(ip);
	}
	
	@Override
	public Device getById(Integer id) {
		return DeviceDao.getById(id);
	}

	@Override
	public int total() {
		return DeviceDao.total();
	}

	@Override
	public List<Map<String, Integer>> totalByDeviceType() {
		return DeviceDao.totalByDeviceType();
	}

	@Override
	public Device checkname(String name) {
		return DeviceDao.checkname(name);
	}

	@Override
	public boolean insertMore(Device device) {
		return DeviceDao.insertMore(device);
	}

	@Override
	public boolean editDeviceList(ArrayList<Device> updatedevices) {
		return DeviceDao.editDeviceList(updatedevices);
	}
}
