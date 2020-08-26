package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import com.longersec.blj.domain.DeviceType;

public interface DeviceTypeService {

	public boolean addDeviceType(DeviceType deviceType);

	public boolean editDeviceType(DeviceType deviceType);

	public boolean delDeviceType(List<Integer> ids);

	public List<Object> findAll(DeviceType deviceType, int page_start, int page_length);

    public ArrayList<DeviceType> listType();

    DeviceType checkname(String name);

	int checDeviceType(String name);
}

