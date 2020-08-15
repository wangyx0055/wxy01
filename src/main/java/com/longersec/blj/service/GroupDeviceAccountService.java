package com.longersec.blj.service;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.GroupDeviceAccount;

import java.util.List;

/**
 * @author wxy
 * @description
 * @data 2020/8/10
 */
public interface GroupDeviceAccountService {
	public boolean addDeviceAccountUser(Integer group_id, List<Integer> devices);

	public boolean editDeviceAccountUser(Integer group_id,List<Integer> devices);

	public boolean delDeviceAccountUser(List<String> ids);

	public List<Object> findAll(GroupDeviceAccount groupDeviceAccount, int page_start, int page_length);

	public List<Deviceaccess> selectById(Integer group_id);

	int selectDeviceAccountUserDeviceCounts(int group_id);

	public Boolean deleteByDeviceAccount_id(Integer group_id);

	int selectBydeviceId(int item);
}
