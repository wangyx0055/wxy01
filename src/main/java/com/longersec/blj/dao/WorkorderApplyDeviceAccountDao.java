package com.longersec.blj.dao;

import com.longersec.blj.domain.DTO.Deviceaccess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkorderApplyDeviceAccountDao {

	boolean addWorkorderApplyDeviceAccount(@Param("workorder_apply_id")Integer workorder_apply_id,@Param("devices")List<Integer> devices);

	List<Deviceaccess> selectById(@Param("workorder_apply_id")Integer workorder_apply_id);

	boolean deleteByWorkorder_id(@Param("workorder_apply_id")int workorder_apply_id);
}
