package com.longersec.blj.service;


import com.longersec.blj.domain.UserPasswordLog;

public interface UserPasswordLogService {
	public void addUserPasswordLog(UserPasswordLog userPasswordLog);
	public int findUserPassordLastCertainTimes(String password, int times, int user_id);
}
