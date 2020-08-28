package com.longersec.blj.service;

import com.longersec.blj.domain.DTO.Deviceaccess;
import net.sf.json.JSONObject;

import java.util.List;

public interface WorkorderApplyDeviceAccountService {

	JSONObject addWorkorderApplyDeviceAccount(Integer[] devices, Integer workorder_apply_id);

	List<Deviceaccess> selectById(Integer workorder_apply_id);

	boolean deleteByWorkorder_id(int workorder_apply_id);
}

