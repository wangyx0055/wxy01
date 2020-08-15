package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.LogDeletePolicyDevice;

public interface LogDeletePolicyDeviceService {

	public boolean addLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice);

	public boolean editLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice);

	public boolean delLogDeletePolicyDevice(List<Integer> ids);

	public List<Object> findAll(LogDeletePolicyDevice logDeletePolicyDevice, int page_start, int page_length);

}

