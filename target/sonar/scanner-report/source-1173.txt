package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.AppLoginkey;

public interface AppLoginkeyDao {

	public boolean editAppLoginkey(AppLoginkey appLoginkey);

	public boolean addAppLoginkey(AppLoginkey appLoginkey);

	public boolean delAppLoginkey(List<Integer> ids);

	public List<Object> findAll(@Param("appLoginkey")AppLoginkey appLoginkey, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public AppLoginkey getById(Integer id);

}
