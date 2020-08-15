package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.AppLoginkeyDao;
import com.longersec.blj.domain.AppLoginkey;
import com.longersec.blj.service.AppLoginkeyService;


@Service
@Transactional
public class AppLoginkeyServiceImpl implements AppLoginkeyService{

	@Autowired
	private AppLoginkeyDao AppLoginkeyDao;

	@Override
	public boolean editAppLoginkey(AppLoginkey appLoginkey) {
		// TODO Auto-generated method stub
		return this.AppLoginkeyDao.editAppLoginkey(appLoginkey);
	}

	@Override
	public boolean addAppLoginkey(AppLoginkey appLoginkey) {
		// TODO Auto-generated method stub
		return this.AppLoginkeyDao.addAppLoginkey(appLoginkey);
	}

	@Override
	public boolean delAppLoginkey(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.AppLoginkeyDao.delAppLoginkey(ids);
	}

	@Override
	public List<Object> findAll(AppLoginkey appLoginkey, int page_start, int page_length) {
		return AppLoginkeyDao.findAll(appLoginkey, page_start, page_length);
	}

	@Override
	public AppLoginkey getById(Integer id) {
		return AppLoginkeyDao.getById(id);
	}

}
