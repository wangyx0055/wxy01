package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.LiveSessions;

public interface LiveSessionsDao {

	public boolean editLiveSessions(LiveSessions liveSessions);

	public boolean addLiveSessions(LiveSessions liveSessions);

	public boolean delLiveSessions(List<Integer> ids);
	
	public boolean updateKey(LiveSessions liveSessions);

	public List<Object> findAll(@Param("liveSessions")LiveSessions liveSessions, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public LiveSessions getById(@Param("live_id")Integer live_id);
}
