package com.longersec.blj.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserPasswordLogDao {
	public void addUserPasswordLog(@Param("password")String password, @Param("lsblj_user_id")String lsblj_user_id);
	public Map<String, Object> findUserPassordLastCertainTimes(@Param("password")String password, @Param("times")int times, @Param("user_id")int user_id);
}