package com.longersec.blj.service.impl;

import com.longersec.blj.dao.GroupDeviceAccountDao;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.GroupDeviceAccount;
import com.longersec.blj.service.GroupDeviceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class GroupDeviceAccountServiceImpl implements GroupDeviceAccountService {
	@Autowired
	private GroupDeviceAccountDao groupDeviceAccountDao;
	
	@Override
	public boolean addDeviceAccountUser(Integer group_id, List<Integer> devices) {
		return this.groupDeviceAccountDao.addDeviceAccountUser(group_id, devices);
	}

	@Override
	public boolean delDeviceAccountUser(List<String> ids) {
		return this.groupDeviceAccountDao.delDeviceAccountUser(ids);
	}

	@Override
	public List<Object> findAll(GroupDeviceAccount groupDeviceAccount, int page_start, int page_length) {
		return this.groupDeviceAccountDao.findAll(groupDeviceAccount, page_start, page_length);
	}

	@Override
	public List<Deviceaccess> selectById(Integer group_id) {
		return this.groupDeviceAccountDao.selectById(group_id);
	}

	@Override
	public boolean editDeviceAccountUser(Integer group_id, List<Integer> devices) {
		return this.groupDeviceAccountDao.editDeviceAccountUser(group_id, devices);
	}

	@Override
	public Boolean deleteByDeviceAccount_id(Integer group_id) {
		return this.groupDeviceAccountDao.deleteByDeviceAccount_id(group_id);
	}

	@Override
	public int selectDeviceAccountUserDeviceCounts(int group_id) {
		return groupDeviceAccountDao.selectDeviceAccountUserDeviceCounts(group_id);
	}

	@Override
	public int selectBydeviceId(int item) {
		return groupDeviceAccountDao.selectBydeviceId(item);
	}
}
