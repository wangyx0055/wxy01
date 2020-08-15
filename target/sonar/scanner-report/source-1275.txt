package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.longersec.blj.domain.Device;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface DeviceAccountDao {

	public boolean editDeviceAccount(DeviceAccount deviceAccount);

	public boolean addDeviceAccount(DeviceAccount deviceAccount);

	public boolean delDeviceAccount(List<Integer> ids);

	public List<Object> findAll(@Param("deviceAccount")DeviceAccount deviceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Object> queryByDeviceId(@Param("device_id")int device_id,@Param("deviceAccount")DeviceAccount deviceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public Boolean delByDevice(List<Integer> ids);
    
    public List<Object> getDeviceAccountByPolicies(@Param("userid")Integer userid, @Param("policy_ids")ArrayList<Integer> policy_ids, @Param("where")HashMap<String, Object> where, @Param("page_start")int page_start, @Param("page_length")int page_length);
    
    public List<Object> getDeviceAccountByHistory(@Param("policy_ids")ArrayList<Integer> policy_ids, @Param("where")HashMap<String, Object> where, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<Object> getUserDeviceAccount( @Param("deviceAccount")DeviceAccount deviceRecord, @Param("page_start")int page_start, @Param("page_length")int page_length);
     
    public DeviceAccount getById(@Param("id")Integer id);
    
    public List<Deviceaccess> selectNameAndId();
    
    public int total();

    public Boolean editstatus(DeviceAccount deviceAccount);

    DeviceAccount checkaccountname(@Param("username") String username,@Param("protocol_id") int protocol_id,@Param("device_id") int device_id);

    public boolean insertMore(@Param("ArrayList") ArrayList<Device> devices);

    boolean editDeviceAccountList(@Param("ArrayList")ArrayList<Device> updatedevices);

    List<Integer> selectBydevice(@Param("item") int item);
}
