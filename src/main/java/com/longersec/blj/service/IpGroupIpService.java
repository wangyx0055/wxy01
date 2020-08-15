package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.IpGroupIp;

public interface IpGroupIpService {

	public boolean addIpGroupIp(IpGroupIp ipGroupIp);

	public boolean editIpGroupIp(IpGroupIp ipGroupIp);

	public boolean delIpGroupIp(List<Integer> ids);

	public List<Object> findAll(IpGroupIp ipGroupIp, int page_start, int page_length);

}

