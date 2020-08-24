package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Apppub;
import com.longersec.blj.domain.DTO.App;

public interface ApppubDao {
    public boolean editApppub(Apppub apppub);

	public boolean addApppub(Apppub apppub);

	public boolean delApppub(List<Integer> ids);

	public List<Object> findAll(@Param("apppub")Apppub apppub, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<App> selectNameAndId(@Param("id")Integer id, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public int total();
	
}
