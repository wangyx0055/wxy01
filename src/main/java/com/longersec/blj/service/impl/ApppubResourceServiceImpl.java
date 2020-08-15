package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubResourceDao;
import com.longersec.blj.domain.ApppubResource;
import com.longersec.blj.service.ApppubResourceService;


@Transactional
@Service
public class ApppubResourceServiceImpl implements ApppubResourceService{

	@Autowired
	private ApppubResourceDao ApppubResourceDao;

	@Override
	public boolean editApppubResource(ApppubResource apppubResource) {
		// TODO Auto-generated method stub
		return this.ApppubResourceDao.editApppubResource(apppubResource);
	}

	@Override
	public boolean addApppubResource(ApppubResource apppubResource) {
		// TODO Auto-generated method stub
		return this.ApppubResourceDao.addApppubResource(apppubResource);
	}

	@Override
	public boolean delApppubResource(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubResourceDao.delApppubResource(ids);
	}

	@Override
	public List<Object> findAll(ApppubResource apppubResource, int page_start, int page_length) {
		return ApppubResourceDao.findAll(apppubResource, page_start, page_length);
	}

}
