package com.longersec.blj.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.Session;


/**
 * 机能概要:
 */
public interface SessionDao {

    /**
     * 机能概要:插入session
     * 
     * @param session
     */
    public int insert(@Param("id") String id,@Param("session") String session);

    /**
     * 机能概要:删除session
     * 
     * @param session
     * @return
     */
    public int delete(String  sessionid);

    /**
     * 
     * 机能概要:删除session
     * 
     * @param session
     * @return
     */
    public int update(@Param("id") String id,@Param("session") String session,@Param("userid") String userid);

    /**
     * 机能概要:通过sessionid来获取session数据
     * 
     * @param sessionid
     * @return
     */
    public Session load(String sessionid);

    /**
     * 机能概要:根据用户ID获取sesssion
     * @param username
     * @return
     */
    public List<Session> loadByUserId(@Param("userid") String userid);
    
    /**
     * 机能概要:根据用户ID获取sesssion
     * @param username
     * @return
     */
    public int clearTimeouts();
  
    
    
}