package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.WorkorderAuditLogDao;
import com.longersec.blj.domain.ConfigWorkorder;
import com.longersec.blj.domain.Department;
import com.longersec.blj.domain.User;
import com.longersec.blj.domain.WorkorderApply;
import com.longersec.blj.domain.WorkorderAuditLog;
import com.longersec.blj.domain.DTO.Deviceaccess;
import com.longersec.blj.service.ConfigWorkorderService;
import com.longersec.blj.service.DepartmentService;
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
	@Autowired
	private DepartmentService departmentService;

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
	public List<Object> listAuditPeople(WorkorderAuditLog workorderAuditLog, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.listAuditPeople(workorderAuditLog, page_start, page_length);
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
		ArrayList<Deviceaccess>  devices = (ArrayList<Deviceaccess>) workorderApplyDeviceAccountService.selectById(id);
		WorkorderAuditLogDao.clearWorkorderAuditLog(id);
		for (Deviceaccess deviceaccess : devices) {
			int i=1;
			Integer department_id = deviceaccess.getDepartment();
			if(department_id>1) {

				for(; i<=configWorkorder.getLevel(); i++) {//找上级部门管理员
					User user = userService.selectByDepartment(department_id);
					if(user!=null) {
						
					}
					WorkorderAuditLog workorderAuditLog = new WorkorderAuditLog();
					workorderAuditLog.setWorkorder_apply_id(id);
					workorderAuditLog.setDevice_account_id(deviceaccess.getDevice_account_id());
					workorderAuditLog.setDepartment(department_id);
					workorderAuditLog.setStep(i);
					WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
					Department d = departmentService.selectParentId(department_id);
					if(d==null) {
						break;
					}
					department_id = d.getParent_id();
				}
				
				if(configWorkorder.getEndaudit()==1) {
					WorkorderAuditLog workorderAuditLog = new WorkorderAuditLog();
					workorderAuditLog.setWorkorder_apply_id(id);
					workorderAuditLog.setDevice_account_id(deviceaccess.getDevice_account_id());
					workorderAuditLog.setDepartment(0);
					workorderAuditLog.setStep(i+1);
					WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
				}
			}else if(department_id==1){
				WorkorderAuditLog workorderAuditLog = new WorkorderAuditLog();
				workorderAuditLog.setWorkorder_apply_id(id);
				workorderAuditLog.setDevice_account_id(deviceaccess.getDevice_account_id());
				workorderAuditLog.setDepartment(0);
				workorderAuditLog.setStep(i);
				WorkorderAuditLogDao.addWorkorderAuditLog(workorderAuditLog);
			}
		}
		return result;
	}

	@Override
	public List<Object> listWorkorderApply(WorkorderAuditLog workorderAuditLog, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.listWorkorderApply(workorderAuditLog, page_start, page_length);
	}

	@Override
	public WorkorderAuditLog getApprovingApply(WorkorderAuditLog workorderAuditLog) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.getApprovingApply(workorderAuditLog);
	}

	@Override
	public List<Object> listWorkorderApplyDeviceAccount(WorkorderAuditLog workorderAuditLog, int page_start,
			int page_length) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.listWorkorderApplyDeviceAccount(workorderAuditLog, page_start, page_length);
	}

	@Override
	public Integer getNoAudit(int workorder_apply_id) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.getNoAudit(workorder_apply_id);
	}

	@Override
	public boolean clearWorkorderAuditLog(int workorder_apply_id) {
		// TODO Auto-generated method stub
		return WorkorderAuditLogDao.clearWorkorderAuditLog(workorder_apply_id);
	}

}
