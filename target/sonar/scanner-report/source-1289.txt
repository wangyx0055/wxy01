package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.GConnection;

public interface GConnectionDao {

	public boolean editGConnection(GConnection gConnection);

	public boolean addGConnection(GConnection gConnection);

	public boolean delGConnection(List<Integer> ids);

	public List<Object> findAll(@Param("gConnection")GConnection gConnection, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
