package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubResourceUserDao;
import com.longersec.blj.domain.ApppubResourceUser;
import com.longersec.blj.service.ApppubResourceUserService;


@Transactional
@Service
public class ApppubResourceUserServiceImpl implements ApppubResourceUserService{

	@Autowired
	private ApppubResourceUserDao ApppubResourceUserDao;

	@Override
	public boolean editApppubResourceUser(ApppubResourceUser apppubResourceUser) {
		// TODO Auto-generated method stub
		return this.ApppubResourceUserDao.editApppubResourceUser(apppubResourceUser);
	}

	@Override
	public boolean addApppubResourceUser(ApppubResourceUser apppubResourceUser) {
		// TODO Auto-generated method stub
		return this.ApppubResourceUserDao.addApppubResourceUser(apppubResourceUser);
	}

	@Override
	public boolean delApppubResourceUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubResourceUserDao.delApppubResourceUser(ids);
	}

	@Override
	public List<Object> findAll(ApppubResourceUser apppubResourceUser, int page_start, int page_length) {
		return ApppubResourceUserDao.findAll(apppubResourceUser, page_start, page_length);
	}

}
