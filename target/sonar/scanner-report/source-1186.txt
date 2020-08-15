package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubResourceGroup;

public interface ApppubResourceGroupDao {

	public boolean editApppubResourceGroup(ApppubResourceGroup apppubResourceGroup);

	public boolean addApppubResourceGroup(ApppubResourceGroup apppubResourceGroup);

	public boolean delApppubResourceGroup(List<Integer> ids);

	public List<Object> findAll(@Param("apppubResourceGroup")ApppubResourceGroup apppubResourceGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
