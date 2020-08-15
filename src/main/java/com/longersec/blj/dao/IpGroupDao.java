package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.IpGroup;

public interface IpGroupDao {

	public boolean editIpGroup(IpGroup ipGroup);

	public boolean addIpGroup(IpGroup ipGroup);

	public boolean delIpGroup(List<Integer> ids);

	public List<Object> findAll(@Param("ipGroup")IpGroup ipGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
