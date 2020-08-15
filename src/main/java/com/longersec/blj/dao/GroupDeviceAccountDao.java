package com.longersec.blj.dao;

import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.domain.GroupDeviceAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupDeviceAccountDao {
	boolean addDeviceAccountUser(@Param("group_id")Integer group_id,@Param("devices")List<Integer> devices);

	boolean editDeviceAccountUser(@Param("group_id")Integer group_id,@Param("devices")List<Integer> devices);

	boolean delDeviceAccountUser(List<String> ids);

	List<Object> findAll(@Param("accessPolicyDeviceAccount") GroupDeviceAccount groupDeviceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

	List<Deviceaccess> selectById(@Param("group_id")Integer group_id);

	int selectDeviceAccountUserDeviceCounts(@Param("group_id") int group_id);

	Boolean deleteByDeviceAccount_id(@Param("group_id")Integer group_id);

	int selectBydeviceId(@Param("item") int item);
}