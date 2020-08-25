package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.Device;
import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Param;

public interface DeviceService {

	Integer selectOldDepartment(int id);

	boolean addDevice(Device device);

	boolean editDevice(Device device);

	boolean delDevice(List<Integer> ids);

	boolean deleteUselessDevice(int department);

	List<Object> findAll(Device device, int page_start, int page_length,List<Integer> depart_ids);

	Device checkip(String ip);
	
	Device getById(Integer id);
	
	int total();
	
	List<Map<String, Integer>> totalByDeviceType();

    Device checkname(String name);

    boolean insertMore(Device device);

    boolean editDeviceList(ArrayList<Device> updatedevices);
}

