package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubResourceAccountDao;
import com.longersec.blj.domain.ApppubResourceAccount;
import com.longersec.blj.service.ApppubResourceAccountService;


@Transactional
@Service
public class ApppubResourceAccountServiceImpl implements ApppubResourceAccountService{

	@Autowired
	private ApppubResourceAccountDao ApppubResourceAccountDao;

	@Override
	public boolean editApppubResourceAccount(ApppubResourceAccount apppubResourceAccount) {
		// TODO Auto-generated method stub
		return this.ApppubResourceAccountDao.editApppubResourceAccount(apppubResourceAccount);
	}

	@Override
	public boolean addApppubResourceAccount(ApppubResourceAccount apppubResourceAccount) {
		// TODO Auto-generated method stub
		return this.ApppubResourceAccountDao.addApppubResourceAccount(apppubResourceAccount);
	}

	@Override
	public boolean delApppubResourceAccount(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubResourceAccountDao.delApppubResourceAccount(ids);
	}

	@Override
	public List<Object> findAll(ApppubResourceAccount apppubResourceAccount, int page_start, int page_length) {
		return ApppubResourceAccountDao.findAll(apppubResourceAccount, page_start, page_length);
	}

}
