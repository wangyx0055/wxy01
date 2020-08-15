package com.longersec.blj.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.longersec.blj.dao.SessionDao;
import com.longersec.blj.service.SessionService;
import com.longersec.blj.utils.SerializableUtils;


/**
 * 机能概要:用于Session的保存
 */
@Transactional
public class SessionServiceImpl extends EnterpriseCacheSessionDAO implements SessionService {

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    protected HttpServletResponse response;
    
    @Autowired
    protected HttpServletRequest request;

    public void delete(Session session) {
        //删除session
        this.sessionDao.delete(session.getId().toString());
    }

    public void update(Session session) throws UnknownSessionException {
    	this.sessionDao.clearTimeouts();
    	
        //当是ValidatingSession 无效的情况下，直接退出
        if(session instanceof ValidatingSession && 
                !((ValidatingSession)session).isValid() ) {
            return ;
        }
        
        String userid = null;
        //检索到用户名
        if(session.getAttribute("userid")!=null) {
        	userid = session.getAttribute("userid").toString();
        	
        }
        	

        //序列化Session
        this.sessionDao.update(session.getId().toString(), SerializableUtils.serializ(session),userid);
    }

    @Override
    public Serializable doCreate(Session session) {
        //生成session的id
        Serializable sessionId = generateSessionId(session);
        //给Session设定id
        assignSessionId(session, sessionId);


        //插入session 到数据库
        this.sessionDao.insert(session.getId().toString(), SerializableUtils.serializ(session));

        return sessionId;
    }

    /**
     * 机能概要:通过userid来获取用户 Session
     * @param userid
     * @return
     */
    public List<Session> loadByUserId(String userid) {
        //获取session的字符串
        List<com.longersec.blj.domain.Session> dbSessions = this.sessionDao.loadByUserId(userid);

        //判断是否存在用户的情况
        if(dbSessions == null || dbSessions.size() == 0) {
            return null;
        }

        List<Session> result = new ArrayList<Session>();
        for(com.longersec.blj.domain.Session session:dbSessions) {
            //加载session数据
            String sessionStr = session.getSession();

            //将Session的数据串，转化为对象
            result.add(SerializableUtils.deserializ(sessionStr));
        }

        return result;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        //获取session的字符串
    	com.longersec.blj.domain.Session dbSession = this.sessionDao.load(sessionId.toString());
        if(dbSession == null) {
            return null;
        }

        //加载session数据
        String sessionStr = dbSession.getSession();
        return SerializableUtils.deserializ(sessionStr);
    }

	@Override
	public com.longersec.blj.domain.Session doReadSession(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

}