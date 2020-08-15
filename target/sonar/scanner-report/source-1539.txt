package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.DeviceRecordCommand;

public interface DeviceRecordCommandService {

	public boolean addDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand);

	public boolean editDeviceRecordCommand(DeviceRecordCommand deviceRecordCommand);

	public boolean delDeviceRecordCommand(List<Integer> ids);

	public List<Object> findAll(DeviceRecordCommand deviceRecordCommand, int page_start, int page_length);

	public DeviceRecordCommand getById(Integer id);

}

