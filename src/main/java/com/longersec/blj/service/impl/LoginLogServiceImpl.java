package com.longersec.blj.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.LoginLogDao;
import com.longersec.blj.domain.LoginLog;
import com.longersec.blj.service.LoginLogService;


@Transactional
@Service
public class LoginLogServiceImpl implements LoginLogService{

	@Autowired
	private LoginLogDao LoginLogDao;

	@Override
	public boolean editLoginLog(LoginLog loginLog) {
		// TODO Auto-generated method stub
		return this.LoginLogDao.editLoginLog(loginLog);
	}

	@Override
	public boolean addLoginLog(LoginLog loginLog) {
		// TODO Auto-generated method stub
		return this.LoginLogDao.addLoginLog(loginLog);
	}

	@Override
	public boolean delLoginLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.LoginLogDao.delLoginLog(ids);
	}

	@Override
	public List<Object> findAll1(LoginLog loginLog,int page_start,String time_format,String time, int page_length) {
		return LoginLogDao.findAll1(loginLog,page_start,time_format, time, page_length);
	}

	@Override
	public List<Object> findAll(LoginLog loginLog,int page_start, int page_length) {
		return LoginLogDao.findAll(loginLog,page_start, page_length);
	}

	@Override
	public List<Map<String, Object>> selectLast7Day() {
		// TODO Auto-generated method stub
		return LoginLogDao.selectLast7Day();
	}

	@Override
	public List<Map<String, Object>> selectByUser() {
		// TODO Auto-generated method stub
		return LoginLogDao.selectByUser();
	}
	
	@Override
	public List<Object> selectByInterval(String interval, String start_date, String end_date, int page_start, int page_length){
		if(interval.equals("hour")) {
			return LoginLogDao.selectByHour(start_date, end_date, page_start, page_length);
		}else if(interval.equals("week")) {
			return LoginLogDao.selectByDay(start_date, end_date, page_start, page_length);
		}else if(interval.equals("month")) {
			return LoginLogDao.selectByMonth(start_date, end_date, page_start, page_length);
		}else {
			return LoginLogDao.selectByDay(start_date, end_date, page_start, page_length);
		}
		
	}
	
	@Override
	public List<Object> selectByUser(String interval, String start_date, String end_date, int page_start, int page_length){
		// TODO Auto-generated method stub
		
		return LoginLogDao.selectProtocolByUser(start_date, end_date, page_start, page_length);
	}
	
	@Override
	public List<Object> selectProtocolBydate(String interval, String start_date, String end_date, int page_start, int page_length){
		// TODO Auto-generated method stub
		if(interval.equals("hour")) {
			return LoginLogDao.selectProtocolByHour(start_date, end_date, page_start, page_length);
		}else if(interval.equals("week")) {
			return LoginLogDao.selectProtocolByWeek(start_date, end_date, page_start, page_length);
		}else if(interval.equals("month")) {
			return LoginLogDao.selectProtocolByMonth(start_date, end_date, page_start, page_length);
		}else {
			return LoginLogDao.selectProtocolByDay(start_date, end_date, page_start, page_length);
		}
		
	}

}
