package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.IpGroupIp;

public interface IpGroupIpDao {

	public boolean editIpGroupIp(IpGroupIp ipGroupIp);

	public boolean addIpGroupIp(IpGroupIp ipGroupIp);

	public boolean delIpGroupIp(List<Integer> ids);

	public List<Object> findAll(@Param("ipGroupIp")IpGroupIp ipGroupIp, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
