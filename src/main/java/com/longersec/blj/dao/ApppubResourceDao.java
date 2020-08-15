package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubResource;

public interface ApppubResourceDao {

	public boolean editApppubResource(ApppubResource apppubResource);

	public boolean addApppubResource(ApppubResource apppubResource);

	public boolean delApppubResource(List<Integer> ids);

	public List<Object> findAll(@Param("apppubResource")ApppubResource apppubResource, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
