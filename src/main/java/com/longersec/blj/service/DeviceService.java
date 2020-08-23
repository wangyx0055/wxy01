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

	public boolean addDevice(Device device);

	public boolean editDevice(Device device);

	public boolean delDevice(List<Integer> ids);

	boolean deleteUselessDevice(int department);

	public List<Object> findAll(Device device, int page_start, int page_length,List<Integer> depart_ids);

	public Device checkip(String ip);
	
	public Device getById(Integer id);
	
	public int total();
	
	public List<Map<String, Integer>> totalByDeviceType();

    Device checkname(String name);

    boolean insertMore(Device device);

    boolean editDeviceList(ArrayList<Device> updatedevices);

	ArrayList<Device> findDeviceGroup(int i);
}

