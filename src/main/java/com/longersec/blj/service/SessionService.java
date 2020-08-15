package com.longersec.blj.service;

import java.io.Serializable;
import java.util.List;
import org.apache.shiro.session.Session;

public interface SessionService {

	public void delete(Session session);
	
	public void update(Session session);
	
	public Serializable doCreate(Session session);
	
	public List<Session> loadByUserId(String userid);
	
	public com.longersec.blj.domain.Session doReadSession(String sessionId);

}
