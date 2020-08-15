package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AlertLog;

public interface AlertLogDao {

	public boolean editAlertLog(AlertLog alertLog);

	public boolean addAlertLog(AlertLog alertLog);

	public boolean delAlertLog(List<Integer> ids);

	public List<Object> findAll(@Param("alertLog")AlertLog alertLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

    List<AlertLog> selectAll();
    
    public int total();
}
