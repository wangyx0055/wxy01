package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ResourceGroupDao;
import com.longersec.blj.domain.ResourceGroup;
import com.longersec.blj.service.ResourceGroupService;


@Transactional
@Service
public class ResourceGroupServiceImpl implements ResourceGroupService{

	@Autowired
	private ResourceGroupDao ResourceGroupDao;

	@Override
	public boolean editResourceGroup(ResourceGroup resourceGroup) {
		// TODO Auto-generated method stub
		return this.ResourceGroupDao.editResourceGroup(resourceGroup);
	}

	@Override
	public boolean addResourceGroup(ResourceGroup resourceGroup) {
		// TODO Auto-generated method stub
		return this.ResourceGroupDao.addResourceGroup(resourceGroup);
	}

	@Override
	public boolean delResourceGroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ResourceGroupDao.delResourceGroup(ids);
	}

	@Override
	public List<Object> findAll(ResourceGroup resourceGroup, int page_start, int page_length) {
		return ResourceGroupDao.findAll(resourceGroup, page_start, page_length);
	}

}
