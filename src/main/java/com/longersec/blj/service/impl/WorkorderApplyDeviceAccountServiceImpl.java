package com.longersec.blj.service.impl;

import com.longersec.blj.dao.WorkorderApplyDeviceAccountDao;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.WorkorderApplyDeviceAccountService;
import com.longersec.blj.utils.BljConstant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class WorkorderApplyDeviceAccountServiceImpl implements WorkorderApplyDeviceAccountService{

	@Autowired
	private WorkorderApplyDeviceAccountDao WorkorderApplyDeviceAccountDao;

	@Override
	public JSONObject addWorkorderApplyDeviceAccount(Integer[] devices,Integer workorder_apply_id) {
		JSONObject result = new JSONObject();
		WorkorderApplyDeviceAccountDao.deleteByWorkorder_id(workorder_apply_id);
		boolean r = true;
		if (devices != null) {
			r = WorkorderApplyDeviceAccountDao.addWorkorderApplyDeviceAccount(workorder_apply_id, Arrays.asList(devices));
		}
		result.put(BljConstant.SUCCESS,r);
		return result;
	}


	@Override
	public List<Deviceaccess> selectById(Integer workorder_apply_id) {
		return WorkorderApplyDeviceAccountDao.selectById(workorder_apply_id);
	}

	@Override
	public boolean deleteByWorkorder_id(int workorder_apply_id) {
		return WorkorderApplyDeviceAccountDao.deleteByWorkorder_id(workorder_apply_id);
	}
}
