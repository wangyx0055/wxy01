package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.WorkorderAuditLog;

public interface WorkorderAuditLogDao {

	public boolean editWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean addWorkorderAuditLog(WorkorderAuditLog workorderAuditLog);

	public boolean delWorkorderAuditLog(List<Integer> ids);
	
	public boolean clearWorkorderAuditLog(@Param("workorder_apply_id")Integer workorder_apply_id);

	public List<Object> findAll(@Param("workorderAuditLog")WorkorderAuditLog workorderAuditLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Object> listAuditPeople(@Param("workorderAuditLog")WorkorderAuditLog workorderAuditLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public WorkorderAuditLog getById(Integer id);
	
	public boolean createWorkorderAuditLog(Integer id);
	
	public List<Object> listWorkorderApply(@Param("workorderAuditLog")WorkorderAuditLog workorderAuditLog, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public WorkorderAuditLog getApprovingApply(@Param("workorderAuditLog")WorkorderAuditLog workorderAuditLog);
	
	public List<Object> listWorkorderApplyDeviceAccount(@Param("workorderAuditLog")WorkorderAuditLog workorderAuditLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public Integer getNoAudit(int workorder_apply_id);
}
