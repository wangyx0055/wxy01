package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ResourceGroup;

public interface ResourceGroupDao {

	public boolean editResourceGroup(ResourceGroup resourceGroup);

	public boolean addResourceGroup(ResourceGroup resourceGroup);

	public boolean delResourceGroup(List<Integer> ids);

	public List<Object> findAll(@Param("resourceGroup")ResourceGroup resourceGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
