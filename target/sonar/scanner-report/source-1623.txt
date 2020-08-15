package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.IpGroupDao;
import com.longersec.blj.domain.IpGroup;
import com.longersec.blj.service.IpGroupService;


@Transactional
@Service
public class IpGroupServiceImpl implements IpGroupService{

	@Autowired
	private IpGroupDao IpGroupDao;

	@Override
	public boolean editIpGroup(IpGroup ipGroup) {
		// TODO Auto-generated method stub
		return this.IpGroupDao.editIpGroup(ipGroup);
	}

	@Override
	public boolean addIpGroup(IpGroup ipGroup) {
		// TODO Auto-generated method stub
		return this.IpGroupDao.addIpGroup(ipGroup);
	}

	@Override
	public boolean delIpGroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.IpGroupDao.delIpGroup(ids);
	}

	@Override
	public List<Object> findAll(IpGroup ipGroup, int page_start, int page_length) {
		return IpGroupDao.findAll(ipGroup, page_start, page_length);
	}

}
