package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.WorkorderAuditLogDao;
import com.longersec.blj.domain.ConfigWorkorder;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.domain.WorkorderAuditLog;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.ConfigWorkorderService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.service.WorkorderApplyDeviceAccountService;
import com.longersec.blj.service.WorkorderApplyService;
import com.longersec.blj.service.WorkorderAuditLogService;import oshi.hardware.platform.mac.MacCentralProcessor;


@Service
@Transactional
public class WorkorderAuditLogServiceImpl implements WorkorderAuditLogService{

	@Autowired
	private WorkorderAuditLogDao WorkorderAuditLogDao;
	@Autowired
	private ConfigWorkorderService configWorkorderService;
	@Autowired
	private WorkorderApplyService workorderApplyService;
	@Autowired
	private WorkorderApplyDeviceAccountService workorderApplyDeviceAccountService;
	@Autowired
	private UserService userService;

	@Override
	public boolean editWorkorderAuditLog(WorkorderAuditLog workorderAuditLog) {
		// TODO Auto-generated method stub
		return this.WorkorderAuditLogDao.editWorkorderAuditLog(workorderAuditLog);
	}

	@Override
	public boolean addWorkorderAuditLog(WorkorderAuditLog workorderAuditLog) {
		// TODO Auto-generated method stub
		return this.WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
	}

	@Override
	public boolean delWorkorderAuditLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.WorkorderAuditLogDao.delWorkorderAuditLog(ids);
	}

	@Override
	public List<Object> findAll(WorkorderAuditLog workorderAuditLog, int page_start, int page_length) {
		return WorkorderAuditLogDao.findAll(workorderAuditLog, page_start, page_length);
	}

	@Override
	public WorkorderAuditLog getById(Integer id) {
		return WorkorderAuditLogDao.getById(id);
	}

	@Override
	public boolean createWorkorderAuditLog(Integer id) {
		// TODO Auto-generated method stub
		boolean result = false;
		ConfigWorkorder configWorkorder = configWorkorderService.getById(1);
		WorkorderApply workorderApply = workorderApplyService.getById(id);
		ArrayList<Deviceaccess>  devices = (ArrayList<Deviceaccess>) workorderApplyDeviceAccountService.selectById(id);
		for (Deviceaccess deviceaccess : devices) {
			int i=1;
			int department_id = deviceaccess.getDepartment();
			for(; i<=configWorkorder.getLevel(); i++) {
				if(deviceaccess.getDepartment()==null) {//未找到设备所属部门
					break;
				}
				User user = userService.selectByDepartment(department_id);
				WorkorderAuditLog workorderAuditLog = new WorkorderAuditLog();
				workorderAuditLog.setDepartment(department_id);
				workorderAuditLog.setStep(i);
				WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
			}
			if(configWorkorder.getEndaudit()==1) {
				WorkorderAuditLog workorderAuditLog = new WorkorderAuditLog();
				workorderAuditLog.setDepartment(0);
				workorderAuditLog.setStep(i);
				WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
			}
		}
		return result;
	}

}
