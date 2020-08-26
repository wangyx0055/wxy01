package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.WorkorderApply;
import org.apache.ibatis.annotations.Param;

public interface WorkorderApplyService {

	boolean addWorkorderApply(WorkorderApply workorderApply);

	boolean editWorkorderApply(WorkorderApply workorderApply);

	boolean delWorkorderApply(List<Integer> ids);

	List<Object> findAll(WorkorderApply workorderApply, int page_start, int page_length);

	/**查询工单作用部门范围 **/
	int selectWorkDept();

	/**更新状态 **/
	boolean updateResult(int result,int id);
}

