package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ResourceDao;
import com.longersec.blj.domain.Resource;
import com.longersec.blj.service.ResourceService;


@Transactional
@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao ResourceDao;

	@Override
	public boolean editResource(Resource resource) {
		// TODO Auto-generated method stub
		return this.ResourceDao.editResource(resource);
	}

	@Override
	public boolean addResource(Resource resource) {
		// TODO Auto-generated method stub
		return this.ResourceDao.addResource(resource);
	}

	@Override
	public boolean delResource(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ResourceDao.delResource(ids);
	}

	@Override
	public List<Object> findAll(Resource resource, int page_start, int page_length) {
		return ResourceDao.findAll(resource, page_start, page_length);
	}

}
