package com.longersec.blj.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.longersec.blj.dao.SessionDao;
import com.longersec.blj.utils.SerializableUtils;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;


/**
 * 机能概要:用于Session的保存
 */
@Transactional
public class SessionValidateServiceImpl extends EnterpriseCacheSessionDAO {

    @Autowired
    private SessionDao sessionDao;
    
    private long sessionValidationInterval;
}