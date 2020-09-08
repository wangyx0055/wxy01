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

	/**更新状态 **/
	boolean updateResult(@Param("result")int result,@Param("id")int id,@Param("dateline")int dateline);

	/**工单提交截止日期 **/
	int selectDeadLine();
	
	WorkorderApply getById(@Param("id")Integer id);

	/** 命令工单的命令**/
	String selectCmd(@Param("id")int id);
	
	Integer getDeviceIdByConnectId(@Param("gconnectid")int gconnectid);
	
	Integer getRecordIdByConnectId(@Param("gconnectid")int gconnectid);
}
