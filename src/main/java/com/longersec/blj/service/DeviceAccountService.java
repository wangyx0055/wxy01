package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.longersec.blj.domain.Device;
import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface DeviceAccountService {

	public boolean addDeviceAccount(DeviceAccount deviceAccount);

	public boolean editDeviceAccount(DeviceAccount deviceAccount);

	public boolean delDeviceAccount(List<Integer> ids);

	public List<Object> findAll(DeviceAccount deviceAccount, int page_start, int page_length);

	public List<Object> queryByDeviceId(Integer device_id, DeviceAccount deviceAccount, int page_start, int page_length);

    public Boolean delByDevice(List<Integer> ids);
    
    public List<Object> getDeviceAccountByPolicies(Integer userid, ArrayList<Integer> policy_ids, HashMap<String, Object> where, int page_start, int page_length);
    
    public List<Object> getUserDeviceAccount(DeviceAccount deviceAccount, int page_start, int page_length);

	public DeviceAccount getById(Integer device_account_id);
    
    public List<Deviceaccess> selectNameAndId();
    
    public int total();

	public Boolean editstatus(DeviceAccount deviceAccount);

    public DeviceAccount checkaccountname(String username,int protocol_id,int device_id);

	DeviceAccount checkaccountById(int device_id, String username);

    public boolean insertMore(ArrayList<Device> devices);

    boolean editDeviceAccountList(ArrayList<Device> updatedevices);

    List<Integer> selectBydevice(int item);
}

