package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Resource;

public interface ResourceDao {

	public boolean editResource(Resource resource);

	public boolean addResource(Resource resource);

	public boolean delResource(List<Integer> ids);

	public List<Object> findAll(@Param("resource")Resource resource, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
