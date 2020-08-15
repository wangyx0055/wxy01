package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.SystemUsageDao;
import com.longersec.blj.domain.SystemUsage;
import com.longersec.blj.service.SystemUsageService;


@Service
@Transactional
public class SystemUsageServiceImpl implements SystemUsageService{

	@Autowired
	private SystemUsageDao SystemUsageDao;

	@Override
	public boolean editSystemUsage(SystemUsage systemUsage) {
		// TODO Auto-generated method stub
		return this.SystemUsageDao.editSystemUsage(systemUsage);
	}

	@Override
	public boolean addSystemUsage(SystemUsage systemUsage) {
		// TODO Auto-generated method stub
		return this.SystemUsageDao.addSystemUsage(systemUsage);
	}

	@Override
	public boolean delSystemUsage(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.SystemUsageDao.delSystemUsage(ids);
	}

	@Override
	public List<SystemUsage> findAll(String interval,String start_date, String end_date, SystemUsage systemUsage) {
		return SystemUsageDao.findAll(systemUsage, interval, start_date, end_date);
	}

	@Override
	public SystemUsage getById(Integer id) {
		return SystemUsageDao.getById(id);
	}

}
