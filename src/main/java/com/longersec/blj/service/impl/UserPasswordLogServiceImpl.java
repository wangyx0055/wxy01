package com.longersec.blj.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longersec.blj.dao.UserPasswordLogDao;
import com.longersec.blj.domain.UserPasswordLog;
import com.longersec.blj.service.UserPasswordLogService;

@Transactional
@Service
public class UserPasswordLogServiceImpl implements UserPasswordLogService {
	
	@Autowired
	private UserPasswordLogDao userPasswordLogDao;
	
	@Override
	public void addUserPasswordLog(UserPasswordLog userPasswordLog) {
		userPasswordLogDao.addUserPasswordLog(userPasswordLog.getPassword(), Integer.toString(userPasswordLog.getLsblj_user_id()));
	}
	
	@Override
	public int findUserPassordLastCertainTimes(String password, int times, int user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = (HashMap<String, Object>) userPasswordLogDao.findUserPassordLastCertainTimes(password, times, user_id);
		if(map.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(map.get("times").toString());
	}
}
