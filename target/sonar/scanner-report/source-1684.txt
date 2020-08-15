package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.UserFavourite;

public interface UserFavouriteService {

	public boolean addUserFavourite(UserFavourite userFavourite);

	public boolean editUserFavourite(UserFavourite userFavourite);
	
	public boolean delUserFavourite(List<Integer> ids);
	
	public boolean delUserFavouriteByDeviceId(List<Integer> ids, Integer user_id);

	public List<Object> findAll(UserFavourite userFavourite, int page_start, int page_length);

	public UserFavourite getById(Integer id);

}

