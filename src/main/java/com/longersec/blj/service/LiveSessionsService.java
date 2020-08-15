package com.longersec.blj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.LiveSessions;

public interface LiveSessionsService {

	public boolean addLiveSessions(LiveSessions liveSessions);

	public boolean editLiveSessions(LiveSessions liveSessions);

	public boolean delLiveSessions(List<Integer> ids);

	public boolean updateKey(LiveSessions liveSessions);
	
	public List<Object> findAll(LiveSessions liveSessions, int page_start, int page_length);
	
	public LiveSessions getById(Integer live_id);

}

