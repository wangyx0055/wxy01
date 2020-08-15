package com.longersec.blj.config;

import com.longersec.blj.dao.MenuDao;
import com.longersec.blj.dao.RoleDao;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.SessionService;
import com.longersec.blj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleDao roleDao;
    

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权：doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        //获取当前登陆的对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();//拿到user对象
        List<String> resources = menuDao.findSources(user.getId());
        //System.out.println(resources);
        List<String> menuSources = new ArrayList<String>();

        for (String res:resources){
            String[] menus = res.split("-");
            menuSources.add(menus[0]);
        }
        for ( int i = 0 ; i < menuSources.size() - 1 ; i ++ ) {
            for ( int j = menuSources.size() - 1 ; j > i; j -- ) {
                if (menuSources.get(j).equals(menuSources.get(i))) {
                    menuSources.remove(j);
                }
            }
        }
        info.addStringPermissions(resources);
        info.addStringPermissions(menuSources);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证：doGetAuthorizationInfo");
        UsernamePasswordToken usertoken = (UsernamePasswordToken) authenticationToken;

        
        Subject current_user =  SecurityUtils.getSubject();
        Session session = current_user.getSession();
		User user  = (User) session.getAttribute("user");
		String rolename = roleDao.selectByid(user.getRole_id());
		if (rolename!=null){
		    user.setRolename(rolename);
        }
        if (user == null){
            return null;
        }
        session.setAttribute("loginUser",session.getAttribute("user"));

        return new SimpleAuthenticationInfo(user,usertoken.getPassword(),"");
    }
}
