package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.Deviceaccess;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Device;

public interface DeviceDao {

	Integer selectOldDepartment(@Param("id") int id);

	public boolean editDevice(Device device);

	public boolean addDevice(Device device);

	public boolean delDevice(List<Integer> ids);

	boolean deleteUselessDevice(@Param("department")int department);

	public List<Object> findAll(@Param("device")Device device, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids")List<Integer> depart_ids);

	public List<Deviceaccess> selectNameAndId();
	
	public Device checkip(@Param("ip") String ip);

	public Device getById(@Param("id")Integer id);

    public List<Device> selectAll();

    public boolean updateAccounts(@Param("device_id") int device_id,@Param("counts") int counts);
    
    public int total();
    
    public List<Map<String, Integer>> totalByDeviceType();
    
	public int getCounts(int group_id);

    Device checkname(String name);

    boolean insertMore(Device devices);

    boolean editDeviceList(@Param("ArrayList")ArrayList<Device> updatedevices);

    Device checkSys_type(Integer item);

    ArrayList<Device> findDeviceGroup(int i);
}
