package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.SessionAutodelete;

public interface SessionAutodeleteDao {

	public boolean editSessionAutodelete(SessionAutodelete sessionAutodelete);

	public boolean addSessionAutodelete(SessionAutodelete sessionAutodelete);

	public boolean delSessionAutodelete(List<Integer> ids);

	public List<Object> findAll(@Param("sessionAutodelete")SessionAutodelete sessionAutodelete, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
