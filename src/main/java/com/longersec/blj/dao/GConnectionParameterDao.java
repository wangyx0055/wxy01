package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.GConnectionParameter;

public interface GConnectionParameterDao {

	public boolean editGConnectionParameter(GConnectionParameter gConnectionParameter);

	public boolean addGConnectionParameter(GConnectionParameter gConnectionParameter);

	public boolean delGConnectionParameter(List<Integer> ids);

	public List<Object> findAll(@Param("gConnectionParameter")GConnectionParameter gConnectionParameter, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
