package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.DynamicToken;

public interface DynamicTokenDao {

	public boolean editDynamicToken(DynamicToken dynamicToken);

	public boolean addDynamicToken(DynamicToken dynamicToken);

	public boolean delDynamicToken(List<Integer> ids);

	public List<Object> findAll(@Param("dynamicToken")DynamicToken dynamicToken, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
