package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubDao;
import com.longersec.blj.domain.Apppub;
import com.longersec.blj.domain.DTO.App;
import com.longersec.blj.service.ApppubService;


@Transactional
@Service
public class ApppubServiceImpl implements ApppubService{

	@Autowired
	private ApppubDao ApppubDao;

	@Override
	public boolean editApppub(Apppub apppub) {
		// TODO Auto-generated method stub
		return this.ApppubDao.editApppub(apppub);
	}

	@Override
	public boolean addApppub(Apppub apppub) {
		// TODO Auto-generated method stub
		return this.ApppubDao.addApppub(apppub);
	}

	@Override
	public boolean delApppub(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubDao.delApppub(ids);
	}

	@Override
	public List<Object> findAll(Apppub apppub, int page_start, int page_length) {
		return ApppubDao.findAll(apppub, page_start, page_length);
	}

	@Override
	public List<App> selectNameAndId(Integer id,int page_start, int page_length) {
		return ApppubDao.selectNameAndId(id,page_start,page_length);
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return ApppubDao.total();
	}

}
