package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.SessionHanddelete;

public interface SessionHanddeleteDao {

	public boolean editSessionHanddelete(SessionHanddelete sessionHanddelete);

	public boolean addSessionHanddelete(SessionHanddelete sessionHanddelete);

	public boolean delSessionHanddelete(List<Integer> ids);

	public List<Object> findAll(@Param("sessionHanddelete")SessionHanddelete sessionHanddelete, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
