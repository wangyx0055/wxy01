package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ResourceHostAccountDao;
import com.longersec.blj.domain.ResourceHostAccount;
import com.longersec.blj.service.ResourceHostAccountService;


@Transactional
@Service
public class ResourceHostAccountServiceImpl implements ResourceHostAccountService{

	@Autowired
	private ResourceHostAccountDao ResourceHostAccountDao;

	@Override
	public boolean editResourceHostAccount(ResourceHostAccount resourceHostAccount) {
		// TODO Auto-generated method stub
		return this.ResourceHostAccountDao.editResourceHostAccount(resourceHostAccount);
	}

	@Override
	public boolean addResourceHostAccount(ResourceHostAccount resourceHostAccount) {
		// TODO Auto-generated method stub
		return this.ResourceHostAccountDao.addResourceHostAccount(resourceHostAccount);
	}

	@Override
	public boolean delResourceHostAccount(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ResourceHostAccountDao.delResourceHostAccount(ids);
	}

	@Override
	public List<Object> findAll(ResourceHostAccount resourceHostAccount, int page_start, int page_length) {
		return ResourceHostAccountDao.findAll(resourceHostAccount, page_start, page_length);
	}

}
