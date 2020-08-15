package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.UserFavouriteDao;
import com.longersec.blj.domain.UserFavourite;
import com.longersec.blj.service.UserFavouriteService;


@Service
@Transactional
public class UserFavouriteServiceImpl implements UserFavouriteService{

	@Autowired
	private UserFavouriteDao userFavouriteDao;

	@Override
	public boolean editUserFavourite(UserFavourite userFavourite) {
		// TODO Auto-generated method stub
		return this.userFavouriteDao.editUserFavourite(userFavourite);
	}

	@Override
	public boolean addUserFavourite(UserFavourite userFavourite) {
		// TODO Auto-generated method stub
		return this.userFavouriteDao.addUserFavourite(userFavourite);
	}

	@Override
	public boolean delUserFavourite(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.userFavouriteDao.delUserFavourite(ids);
	}

	@Override
	public boolean delUserFavouriteByDeviceId(List<Integer> ids, Integer user_id) {
		// TODO Auto-generated method stub
		return this.userFavouriteDao.delUserFavouriteByDeviceId(ids, user_id);
	}

	@Override
	public List<Object> findAll(UserFavourite userFavourite, int page_start, int page_length) {
		return userFavouriteDao.findAll(userFavourite, page_start, page_length);
	}

	@Override
	public UserFavourite getById(Integer id) {
		// TODO Auto-generated method stub
		return userFavouriteDao.getById(id);
	}

}
