package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.GConnectionHistory;

public interface GConnectionHistoryDao {

	public boolean editGConnectionHistory(GConnectionHistory gConnectionHistory);

	public boolean addGConnectionHistory(GConnectionHistory gConnectionHistory);

	public boolean delGConnectionHistory(List<Integer> ids);

	public List<Object> findAll(@Param("gConnectionHistory")GConnectionHistory gConnectionHistory, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
