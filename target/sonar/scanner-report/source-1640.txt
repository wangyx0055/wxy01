package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ResourceUserDao;
import com.longersec.blj.domain.ResourceUser;
import com.longersec.blj.service.ResourceUserService;


@Transactional
@Service
public class ResourceUserServiceImpl implements ResourceUserService{

	@Autowired
	private ResourceUserDao ResourceUserDao;

	@Override
	public boolean editResourceUser(ResourceUser resourceUser) {
		// TODO Auto-generated method stub
		return this.ResourceUserDao.editResourceUser(resourceUser);
	}

	@Override
	public boolean addResourceUser(ResourceUser resourceUser) {
		// TODO Auto-generated method stub
		return this.ResourceUserDao.addResourceUser(resourceUser);
	}

	@Override
	public boolean delResourceUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ResourceUserDao.delResourceUser(ids);
	}

	@Override
	public List<Object> findAll(ResourceUser resourceUser, int page_start, int page_length) {
		return ResourceUserDao.findAll(resourceUser, page_start, page_length);
	}

}
