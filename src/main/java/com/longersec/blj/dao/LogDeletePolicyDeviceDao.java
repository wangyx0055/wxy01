package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LogDeletePolicyDevice;

public interface LogDeletePolicyDeviceDao {

	public boolean editLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice);

	public boolean addLogDeletePolicyDevice(LogDeletePolicyDevice logDeletePolicyDevice);

	public boolean delLogDeletePolicyDevice(List<Integer> ids);

	public List<Object> findAll(@Param("logDeletePolicyDevice")LogDeletePolicyDevice logDeletePolicyDevice, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
