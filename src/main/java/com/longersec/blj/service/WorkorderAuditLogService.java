package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.WorkorderAuditLog;

public interface WorkorderAuditLogService {

	public boolean addWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean editWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean delWorkorderAuditLog(List<Integer> ids);

	public List<Object> findAll(WorkorderAuditLog workorderAuditLog, int page_start, int page_length);

	public WorkorderAuditLog getById(Integer id);
	
	public boolean createWorkorderAuditLog(Integer id); 

}

