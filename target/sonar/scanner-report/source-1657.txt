package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.IpGroup;

public interface IpGroupService {

	public boolean addIpGroup(IpGroup ipGroup);

	public boolean editIpGroup(IpGroup ipGroup);

	public boolean delIpGroup(List<Integer> ids);

	public List<Object> findAll(IpGroup ipGroup, int page_start, int page_length);

}

