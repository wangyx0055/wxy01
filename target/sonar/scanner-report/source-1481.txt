package com.longersec.blj.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.longersec.blj.domain.ConfigLogin;
import com.longersec.blj.service.ConfigLoginService;
import com.longersec.blj.service.ConfigService;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
 
/**
 * 课程拦截器,当会员过期或未激活时自动跳转到激活页面
 * 
 * @version 1.0.0
 * @date 2018 -10-19
 */
public class resourceCheckFilter extends AccessControlFilter {
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private ConfigLoginService configLoginService;
 
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String uri = httpServletRequest.getRequestURI();
		int seconds = ((ConfigLogin)((ArrayList<Object>) (configLoginService.findAll(null, 0, 1).get(0))).get(0)).getWeb_timeout()*60*1000;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for(Session _session:sessions){
			if (_session.getLastAccessTime().getTime() + seconds < new Date().getTime()) {
				System.out.println("session expired:"+_session.getLastAccessTime().getTime() +","+ seconds+":"+new Date().getTime());
				_session.setTimeout(0);
            } 
		}

		if(( uri.indexOf("/editSelf") <= 0 &&uri.indexOf("self.html") <= 0 )
				&& httpServletRequest.getSession().getAttribute("forceChangePassword")!=null
				&&httpServletRequest.getSession().getAttribute("forceChangePassword").equals("true")
			) {
        	System.out.println("修改密码");
        	try {
        		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/view/operation_manage/self.html");
        	 	//response.sendRedirect(request.getContextPath()+"/view/operation_manage/self.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return true;
 
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}