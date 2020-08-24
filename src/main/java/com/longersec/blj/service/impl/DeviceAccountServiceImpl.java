package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.longersec.blj.dao.DeviceDao;
import com.longersec.blj.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DeviceAccountDao;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.DeviceAccountService;


@Transactional
@Service
public class DeviceAccountServiceImpl implements DeviceAccountService{

	@Autowired
	private DeviceAccountDao DeviceAccountDao;
	@Autowired
	private DeviceDao deviceDao;

	@Override
	public boolean editDeviceAccount(DeviceAccount deviceAccount) {
		// TODO Auto-generated method stub
		return this.DeviceAccountDao.editDeviceAccount(deviceAccount);
	}

	@Override
	public boolean addDeviceAccount(DeviceAccount deviceAccount) {
		int device_id = deviceAccount.getDevice_id();
		boolean d = deviceDao.updateAccounts(device_id,1);
		boolean a = this.DeviceAccountDao.addDeviceAccount(deviceAccount);
		if (d && a){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delDeviceAccount(List<Integer> ids) {
		// TODO Auto-generated method stub
		int counts = ids.size();
		int id = ids.get(0);
		DeviceAccount deviceAccount = DeviceAccountDao.getById(id);
		int device_id = deviceAccount.getDevice_id();
		boolean d = deviceDao.updateAccounts(device_id,-counts);
		boolean a = this.DeviceAccountDao.delDeviceAccount(ids);
		if (d==true && a==true){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Object> findAll(DeviceAccount deviceAccount, int page_start, int page_length) {
		return DeviceAccountDao.findAll(deviceAccount, page_start, page_length);
	}

	@Override
	public List<Object> queryByDeviceId(Integer device_id, DeviceAccount deviceAccount, int page_start, int page_length) {
		return DeviceAccountDao.queryByDeviceId(device_id,deviceAccount, page_start, page_length);
	}

	@Override
	public Boolean delByDevice(List<Integer> ids) {
		return DeviceAccountDao.delByDevice(ids);
	}
	
	@Override
	public List<Object> getDeviceAccountByPolicies(Integer userid, ArrayList<Integer> policy_ids, HashMap<String, Object> where, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return DeviceAccountDao.getDeviceAccountByPolicies(userid, policy_ids, where, page_start, page_length);
	}
	
	@Override
	public List<Object> getUserDeviceAccount(DeviceAccount deviceAccount, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return DeviceAccountDao.getUserDeviceAccount( deviceAccount, page_start, page_length);
	}

	@Override
	public DeviceAccount getById(Integer device_account_id) {
		// TODO Auto-generated method stub
		return DeviceAccountDao.getById(device_account_id);
	}

	@Override
	public List<Deviceaccess> selectNameAndId(Integer id,int page_start, int page_length) {
		// TODO Auto-generated method stub
		return DeviceAccountDao.selectNameAndId(id,page_start,page_length);
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return DeviceAccountDao.total();
	}

	@Override
	public Boolean editstatus(DeviceAccount deviceAccount) {
		return DeviceAccountDao.editstatus(deviceAccount);
	}

	@Override
	public DeviceAccount checkaccountname(String username,int protocol_id,int device_id) {
		return DeviceAccountDao.checkaccountname(username,protocol_id,device_id);
	}

	@Override
	public DeviceAccount checkaccountById(int device_id, String username) {
		return DeviceAccountDao.checkaccountById(device_id,username);
	}

	@Override
	public boolean insertMore(ArrayList<Device> devices) {
		return DeviceAccountDao.insertMore(devices);
	}

	@Override
	public boolean editDeviceAccountList(ArrayList<Device> updatedevices) {
		return DeviceAccountDao.editDeviceAccountList(updatedevices);
	}

	@Override
	public List<Integer> selectBydevice(int item) {
		return DeviceAccountDao.selectBydevice(item);
	}
}
