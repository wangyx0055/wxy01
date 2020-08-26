package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.WorkorderApply;

public interface WorkorderApplyDao {

	boolean editWorkorderApply(WorkorderApply workorderApply);

	boolean addWorkorderApply(WorkorderApply workorderApply);

	boolean delWorkorderApply(List<Integer> ids);

	List<Object> findAll(@Param("workorderApply")WorkorderApply workorderApply, @Param("page_start")int page_start, @Param("page_length")int page_length);

	/**查询工单作用部门范围 **/
	int selectWorkDept();
}
