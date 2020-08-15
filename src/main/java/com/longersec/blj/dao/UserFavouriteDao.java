package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.UserFavourite;

public interface UserFavouriteDao {

	public boolean editUserFavourite(UserFavourite userFavouriteDevice);

	public boolean addUserFavourite(UserFavourite userFavouriteDevice);
	
	public boolean delUserFavourite(List<Integer> ids);
	
	public boolean delUserFavouriteByDeviceId( @Param("ids")List<Integer> ids, @Param("user_id") Integer user_id);

	public List<Object> findAll(@Param("userFavouriteDevice")UserFavourite userFavouriteDevice, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public UserFavourite getById(Integer id);

}
