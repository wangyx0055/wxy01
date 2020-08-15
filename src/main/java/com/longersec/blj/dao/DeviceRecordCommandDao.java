package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.DeviceRecordCommand;

public interface DeviceRecordCommandDao {

	public boolean editDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand);

	public boolean addDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand);

	public boolean delDeviceRecordCommand(List<Integer> ids);

	public List<Object> findAll(@Param("deviceRecordCommand")DeviceRecordCommand deviceRecordCommand, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public DeviceRecordCommand getById(Integer id);

}
