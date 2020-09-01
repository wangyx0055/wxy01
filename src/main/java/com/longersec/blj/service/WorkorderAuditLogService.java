package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.WorkorderAuditLog;

public interface WorkorderAuditLogService {

	public boolean addWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean editWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean delWorkorderAuditLog(List<Integer> ids);

	public boolean clearWorkorderAuditLog(int workorder_apply_id);

	public List<Object> findAll(WorkorderAuditLog workorderAuditLog, int page_start, int page_length);

	public List<Object> listAuditPeople(WorkorderAuditLog workorderAuditLog, int page_start, int page_length);

	public WorkorderAuditLog getById(Integer id);
	
	public boolean createWorkorderAuditLog(Integer id); 
	
	public List<Object> listWorkorderApply(WorkorderAuditLog workorderAuditLog, int page_start, int page_length);
	
	public WorkorderAuditLog getApprovingApply(WorkorderAuditLog workorderAuditLog);
	
	public List<Object> listWorkorderApplyDeviceAccount(WorkorderAuditLog workorderAuditLog, int page_start, int page_length);
	
	public Integer getNoAudit(int workorder_apply_id);
	
	
	

}

