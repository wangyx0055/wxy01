package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.IpGroupIpDao;
import com.longersec.blj.domain.IpGroupIp;
import com.longersec.blj.service.IpGroupIpService;


@Transactional
@Service
public class IpGroupIpServiceImpl implements IpGroupIpService{

	@Autowired
	private IpGroupIpDao IpGroupIpDao;

	@Override
	public boolean editIpGroupIp(IpGroupIp ipGroupIp) {
		// TODO Auto-generated method stub
		return this.IpGroupIpDao.editIpGroupIp(ipGroupIp);
	}

	@Override
	public boolean addIpGroupIp(IpGroupIp ipGroupIp) {
		// TODO Auto-generated method stub
		return this.IpGroupIpDao.addIpGroupIp(ipGroupIp);
	}

	@Override
	public boolean delIpGroupIp(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.IpGroupIpDao.delIpGroupIp(ids);
	}

	@Override
	public List<Object> findAll(IpGroupIp ipGroupIp, int page_start, int page_length) {
		return IpGroupIpDao.findAll(ipGroupIp, page_start, page_length);
	}

}
