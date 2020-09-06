package com.longersec.blj.web;

import com.longersec.blj.dao.RoleMenuDao;
import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.AlertLog;
import com.longersec.blj.domain.AppLoginkey;
import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.ConfigFinger;
import com.longersec.blj.domain.ConfigLogin;
import com.longersec.blj.domain.LoginLog;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.AccessPolicyService;
import com.longersec.blj.service.AlertLogService;
import com.longersec.blj.service.AppLoginkeyService;
import com.longersec.blj.service.ConfigFingerService;
import com.longersec.blj.service.ConfigLoginService;
import com.longersec.blj.service.ConfigPasswordEncryptKeyService;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.DepartmentService;
import com.longersec.blj.service.LoginLogService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.service.UtilService;
import com.longersec.blj.utils.GoogleAuthenticatorUtil;
import com.longersec.blj.utils.KeyUtil;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.QRCodeUtils;
import com.longersec.blj.utils.Sm4Utils;
import com.longersec.blj.utils.httpClient;
import com.sun.tools.javac.resources.javac;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	private ConfigService configService;
    @Autowired
    private UserService userService;
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private AlertLogService alertLogService;
	@Autowired
	private ConfigLoginService configLoginService;
	@Autowired
	private AccessPolicyService accessPolicyService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private AppLoginkeyService appLoginkeyService;
	@Autowired
	private ConfigFingerService configFingerService;
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ConfigPasswordEncryptKeyService configPasswordEncryptKeyService;
	
	
    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestParam("username") String username,@RequestParam(value="password", required=false) String password,  Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response){
    	String smscode = request.getParameter("smscode");
    	String dpassword = request.getParameter("dpassword");
    	String fingerpassword = request.getParameter("fingerpassword");
    	String dynamiccode = request.getParameter("dynamiccode");
    	String login_type = request.getParameter("login_type");
    	if(request.getParameter("logintoken")==null) {
    		try {
    			response.setContentType("multipart/form-data");
    			response.setCharacterEncoding("UTF-8");
    			response.setContentType("text/html");
				response.getWriter().print("请在登录页面刷新");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return "";
    	}
    	
    	
    	String logintoken = KeyUtil.genUniqueKey();
    	session.setAttribute("logintoken", logintoken);
    	model.addAttribute("logintoken", logintoken);
    	username = username.trim();
    	if(username.equals("")) {
            model.addAttribute("msg","请输入用户名");
            return "/login";
    	}
    	User user= userService.checkLogin(username);
    	if(user==null) {
            model.addAttribute("msg","用户不存在");
            return "/login";
    	}
    	if(user.getDynamic_auth()==1) {
    		if(dpassword==null|| dpassword.equals("")) {
                model.addAttribute("msg","请输入动态密码");
                return "/login";
        	}
    	}else if(user.getSms_auth()==1) {
    		if(smscode==null|| smscode.equals("")) {
                model.addAttribute("msg","请输入短信验证码");
                return "/login";
        	}
    	}else if(user.getFinger_auth()==1) {
    		if(user.getLocal_auth()==1&&(fingerpassword==null|| fingerpassword.equals("")) ){
                model.addAttribute("msg","请输入用户密码");
                return "/login";
        	}
    	}else {
    		if(password==null|| password.equals("")) {
                model.addAttribute("msg","请输入密码");
                return "/login";
        	}
    	}
    	LoginLog loginlog =new LoginLog();
    	ConfigLogin configLogin = new ConfigLogin();
    	ArrayList<Object> resultConfigLogins;
    	ArrayList<ConfigLogin> configLogins;
    	
    	if(user.getValid_long()!=1) {
        	int nowtime = (int)(System.currentTimeMillis()/1000);
    		String[] symd = user.getValid_datetime_start().split("-");
    		String[] eymd = user.getValid_datetime_end().split("-");
    		
    		if(nowtime<(new Date(Integer.parseInt(symd[0])-1900,Integer.parseInt(symd[1])-1,Integer.parseInt(symd[2])).getTime())/1000
				|| nowtime>(new Date(Integer.parseInt(eymd[0])-1900,Integer.parseInt(eymd[1])-1,Integer.parseInt(eymd[2])+1).getTime())/1000
					){
				 model.addAttribute("msg","不在登录日期范围");
			        return "/login";
			}
    	}
    	
    	loginlog.setSource_ip(httpClient.getRemortIP(request)+":"+httpClient.getRemotePort(request));
    	loginlog.setDepartment(user.getDepartment());
    	loginlog.setUsername(user.getUsername());
    	loginlog.setUser_id(user.getId());
    	loginlog.setRealname(user.getRealname());
    	loginlog.setProtocol("web");
    	loginlog.setLogin_datetime(Long.toString(System.currentTimeMillis()/1000));
    	if(login_type!=null) {
    		switch (Integer.parseInt(login_type)) {
			case 0:
				loginlog.setLogin_type("静态密码");
				break;
			case 1:
				loginlog.setLogin_type("动态口令");
				break;
			case 2:
				loginlog.setLogin_type("密码+指纹");
				break;
			case 3:
				loginlog.setLogin_type("指纹认证");
				break;
			case 4:
				loginlog.setLogin_type("短信认证");
				break;
			default:
				break;
			}
    	}
  		
  		resultConfigLogins = (ArrayList<Object>)configLoginService.findAll(configLogin, 0, 1);
    	if(CollectionUtils.isNotEmpty(resultConfigLogins)) {
    		configLogins = (ArrayList<ConfigLogin>)resultConfigLogins.get(0);
    		configLogin = configLogins.get(0);
		}
    	
        if(username.equals("admin")&&!configLogin.getAdmin_login_ip().isEmpty()&&!httpClient.getRemortIP(request).equals(configLogin.getAdmin_login_ip())) {
        	model.addAttribute("msg","远程地址不符合设置");
        	loginlog.setDetails("远程地址不符合设置");
    		loginlog.setStatus(0);
    		loginlog.setResult("失败");
    		loginLogService.addLoginLog(loginlog);
            return "/login";
        }
        
        if(configLogin.getLock_type()==0) {
        	if(user.getFail_count()>=configLogin.getLock_try_count()&&(int)(System.currentTimeMillis()/1000)-user.getLock_time()>configLogin.getLock_time_length()*60) {
        		user.setStatus(1);
        		user.setFail_count(0);
        	}
        }else {
        	if((int)(System.currentTimeMillis()/1000)-configLogin.getLock_time()>configLogin.getLock_time_length()*60) {
        		configLogin.setFail_count(0);
        		configLogin.setLock_ip("");
        		configLogin.setLock_time(0);
        		configLoginService.editConfigLogin(configLogin);
        	}else if(httpClient.getRemortIP(request).equals(configLogin.getLock_ip())) {
        		loginlog.setDetails("IP被锁定");
        		loginlog.setStatus(0);
        		loginlog.setResult("失败");
        		loginLogService.addLoginLog(loginlog);
                model.addAttribute("msg","IP被锁定");
                return "/login";
        	}
        }
    	
    	if(user.getStatus()==0) {
        	loginlog.setDetails("账号被锁定");
    		loginlog.setStatus(0);
    		loginlog.setResult("失败");
    		loginLogService.addLoginLog(loginlog);
            model.addAttribute("msg","账号被锁定");
            return "/login";
    	}
    	
    	if(departmentService.getById(user.getDepartment())==null) {
        	loginlog.setDetails("部门不存在");
    		loginlog.setStatus(0);
    		loginlog.setResult("失败");
    		loginLogService.addLoginLog(loginlog);
            model.addAttribute("msg","部门不存在");
            return "/login";
    	}
    	int total_online = 0;
    	
        Subject subject = SecurityUtils.getSubject();
        //操作日志
        
		try {
			String db_password = Sm4Utils.decryptEcb(configPasswordEncryptKeyService.getKey(),user.getPassword());
			System.out.println(db_password);
			user.setPassword(null);
			boolean result = false;
			if(user.getDynamic_auth()==1) {
				result = dpassword.equals(db_password)&&this.dynamicAuthCheck(user.getId().toString(), dynamiccode);
				if(!result) {
					this.setLoginFail(configLogin, user, model, "动态密码", loginlog, request);
		    		model.addAttribute("dynamic","1");
		    		if(user.getGoogle_auth_token_used()==0) {
		                model.addAttribute("msg","请扫码重置令牌");
		    		}
		            return "/login";
				}
			}else if(user.getSms_auth()==1) {
				result = smscode.equals(user.getSms_code());
				if(!result) {
					this.setLoginFail(configLogin, user, model, "验证码", loginlog, request);
		    		model.addAttribute("sms_auth","1");
		            return "/login";
				}
			}else if(user.getFinger_auth()==1) {
				if(user.getLocal_auth()==1) {
					result = fingerpassword.equals(db_password);
					if(!result) {
						this.setLoginFail(configLogin, user, model, "静态密码", loginlog, request);
			    		model.addAttribute("finger_auth","1");
			            return "/login";
					}
				}
				ConfigFinger configFinger = configFingerService.getById(1);
				String url = configFinger.getUrl()+"/IService";
				String s = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.biolink.biokee.com/\">\n" + 
								"		   <soapenv:Header/>\n" + 
								"		   <soapenv:Body>\n" + 
								"		      <ws:verify>\n" + 
								"				 <arg0>"+configFinger.getEndpoint()+"</arg0>\r\n" + 
								"				 <arg1>"+configFinger.getPwd()+"</arg1>\r\n" + 
								"		         <arg2>"+user.getUsername()+"</arg2>\n" + 
								"		         <arg3>"+request.getParameter("fpdata")+"</arg3>\n" + 
								"		      </ws:verify>\n" + 
								"		   </soapenv:Body>\n" + 
								"		</soapenv:Envelope>";
				String _result = httpClient.httpPostRaw(url, s, null, "UTF-8");
				System.out.println(_result);
				Pattern pattern = Pattern.compile("<return>(.*?)</return>");
				Matcher matcher = pattern.matcher(_result );
				if (!matcher.find()||!matcher.group(1).toString().equals("C100")) {
					result = false;
					this.setLoginFail(configLogin, user, model, "指纹比对", loginlog, request);
		    		model.addAttribute("finger_auth","1");
		            return "/login";
				}
				result = true;
			}else{
				result = password.equals(db_password);
			}
			if(result) {
				Collection<Session> sessions = sessionDAO.getActiveSessions();
				for(Session _session:sessions){
					if (_session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
		                continue;
		            }
					SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) _session
		                    .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		            User useronline = (User) principalCollection.getPrimaryPrincipal();
		            if(useronline.getUsername().equals(user.getUsername())) {
		            	total_online++;
		            }
				}
				if(total_online>=configLogin.getSame_user()) {
					loginlog.setDetails("超过相同用户在线数");
		    		loginlog.setStatus(0);
		    		loginlog.setResult("失败");
		    		loginLogService.addLoginLog(loginlog);
		            model.addAttribute("msg","超过相同用户在线数");
		            
		            AlertLog alertLog = new AlertLog();
		            alertLog.setDangerlevel(0);
		            alertLog.setProtocol("web");
		            alertLog.setUsername(user.getUsername());
		            alertLog.setRealname(user.getRealname());
		            alertLog.setPolicy("登录策略");
		            alertLog.setSource_ip(httpClient.getRemortIP(request)+":"+httpClient.getRemotePort(request));
		            alertLog.setOperate_datetime(Integer.toString((int)(System.currentTimeMillis()/1000)));
		            alertLog.setAlert_times("0");
		            alertLog.setCommand("超过在线人数");
		            alertLogService.addAlertLog(alertLog);
		            return "/login";
				}
				
				if(user.getDynamic_auth()==1) {
					user.setGoogle_auth_token_used(1);
				}
				if(configLogin.getWeb_timeout()>0) {
			        SecurityUtils.getSubject().getSession().setTimeout(configLogin.getWeb_timeout()*60*1000);
				}
				if( (int)(System.currentTimeMillis()/1000) - user.getLast_change_password() > configLogin.getPassword_cycle()*24*3600
						|| user.getLast_change_password()<=0
						) {
					session.setAttribute("forceChangePassword", "true");
				}
				user.setLast_login_ip(httpClient.getRemortIP(request)+":"+httpClient.getRemotePort(request));
				user.setSms_code("");
				session.setAttribute("user", user);
				session.setAttribute("login_smscode", null);
				session.setAttribute("userid", Integer.toString(user.getId()));
				session.setAttribute("logintime", (int)(System.currentTimeMillis()/1000));
				session.setAttribute("login_type", loginlog.getLogin_type());
				//封装用户的登陆数据
		        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				subject.login(token);
				subject.isPermitted("user");
				user.setFail_count(0);
				userService.editUser(user);
	    		loginlog.setStatus(1);
	    		loginlog.setResult("成功");
	    		loginlog.setDetails("登录成功");
	    		loginlog.setProtocol("web");
	    		loginLogService.addLoginLog(loginlog);
				int role_id = user.getRole_id();
				List<String> resources = roleMenuDao.findByIdAse(role_id);
				if (resources.get(0).equals("index")){
					return "redirect:index/";
				}else {
					String res = resources.get(0);
					String[] index = res.split("-");
					return "redirect:view/"+index[0]+"/"+index[1]+"";
				}

			}else {
				this.setLoginFail(configLogin, user, model, "密码", loginlog, request);
				return "/login";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "/login";
    }
    
    public void setLoginFail(ConfigLogin configLogin, User user, Model model, String msg, LoginLog loginlog, HttpServletRequest request) {
		user.setFail_count(user.getFail_count()+1);
		if(configLogin.getLock_type()==0) {
        	if(user.getFail_count()>=configLogin.getLock_try_count()) {
        		user.setStatus(0);
        		user.setLock_time((int)(System.currentTimeMillis()/1000));
        	}
        	model.addAttribute("msg",user.getFail_count()>=configLogin.getLock_try_count()?msg+"次数达到出错限制，请等"+(((int)(System.currentTimeMillis()/1000)-user.getLock_time())/60+1)+"分钟再尝试":msg+"错误，还可以尝试"+(configLogin.getLock_try_count()-user.getFail_count())+"次");
        	
		}else {
			if(user.getFail_count()>=configLogin.getLock_try_count()) {
	    		configLogin.setFail_count(user.getFail_count());
	    		configLogin.setLock_ip(httpClient.getRemortIP(request));
        		configLogin.setLock_time((int)(System.currentTimeMillis()/1000));
        	}
    		configLoginService.editConfigLogin(configLogin);
    		model.addAttribute("msg",user.getFail_count()>=configLogin.getLock_try_count()?msg+"次数达到出错限制，请等"+(((int)(System.currentTimeMillis()/1000)-configLogin.getLock_time())/60+1)+"分钟再尝试":msg+"错误，还可以尝试"+(configLogin.getLock_try_count()-user.getFail_count())+"次");
		}
    	userService.editUser(user);
		loginlog.setStatus(0);
		loginlog.setResult("失败");
		loginlog.setDetails(msg+"错误");
		loginLogService.addLoginLog(loginlog);
    }
    
    @RequestMapping(value="/apiLogin",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String apiLogin(String username, String password, Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
    	JSONObject _result = new JSONObject();
    	_result.put("success", false); 
    	if(request.getParameter("loginkey")!=null&&!request.getParameter("loginkey").equals("")) {
    		AppLoginkey appLoginkey = new AppLoginkey();
    		appLoginkey.setLoginkey(request.getParameter("loginkey"));
    		List<Object> appLoginkeys = (List<Object>) appLoginkeyService.findAll(appLoginkey, 0, 1);
    		if(((ArrayList<Long>) appLoginkeys.get(1)).get(0)>0) {
    			appLoginkey = (AppLoginkey) ((List<AppLoginkey>)appLoginkeys.get(0)).get(0);
    			User user = userService.getUserByID(appLoginkey.getUserid().toString());
    			ConfigLogin configLogin = new ConfigLogin();
    			ArrayList<Object> resultConfigLogins = new ArrayList<Object>();
    	    	ArrayList<ConfigLogin> configLogins = new ArrayList<ConfigLogin>();
    	  		
    	  		resultConfigLogins = (ArrayList<Object>)configLoginService.findAll(configLogin, 0, 1);
    	    	if(CollectionUtils.isNotEmpty(resultConfigLogins)) {
    	    		configLogins = (ArrayList<ConfigLogin>)resultConfigLogins.get(0);
    	    		configLogin = configLogins.get(0);
    			}
    	    	
    			Subject subject = SecurityUtils.getSubject();
    			if(configLogin.getWeb_timeout()>0) {
			        SecurityUtils.getSubject().getSession().setTimeout(configLogin.getWeb_timeout()*60*1000);
				}
				if( (int)(System.currentTimeMillis()/1000) - user.getLast_change_password() > configLogin.getPassword_cycle()*24*3600
						|| user.getLast_change_password()<=0
						) {
					session.setAttribute("forceChangePassword", "true");
				}
				session.setAttribute("user", user);
				session.setAttribute("userid", Integer.toString(user.getId()));
				session.setAttribute("logintime", (int)(System.currentTimeMillis()/1000));
				//封装用户的登陆数据
		        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
				subject.login(token);
				subject.isPermitted("user");
				int role_id = user.getRole_id();
				List<String> resources = roleMenuDao.findByIdAse(role_id);
				ArrayList<Integer> ids = new ArrayList<>();
				ids.add(appLoginkey.getId());
				//appLoginkeyService.delAppLoginkey(ids);
				try {
					if (resources.get(0).equals("index")){
						response.sendRedirect("index/");
					}else {
						String res = resources.get(0);
						String[] index = res.split("-");
						response.sendRedirect("view/"+index[0]+"/"+index[1]+"");
						return "";
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else {
    			try {
					response.sendRedirect("./error.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    	}else if(username!=null&&password!=null) {
        	System.out.println("apiLogin :"+username+' '+password+ ' '+(request.getParameter("dpassword")!=null?request.getParameter("dpassword"):"no dynamic code"));
        	String result = this.checkLogin(username, password, model, request, session, response);
        	System.out.println("apiLogin result:"+result);
        	if(!result.contains("login")) {
        		String key = KeyUtil.genUniqueKey();
        		User user = userService.checkLogin(username);
        		AppLoginkey appLoginkey = new AppLoginkey();
        		Long _time = (new java.util.Date().getTime())/1000;
        		appLoginkey.setLoginkey(key);
        		appLoginkey.setUserid(user.getId());
        		appLoginkey.setLogintime(_time.intValue());
        		appLoginkeyService.addAppLoginkey(appLoginkey);
        		_result.put("loginkey", key);
        		_result.put("success", true);
        	}
    	}
		_result.put("msg", model.asMap().get("msg"));
    	return _result.toString();
	}
    
    @RequestMapping({"/","/login.html","login"})
	public String login( Model model,HttpSession session){
    	String logintoken = KeyUtil.genUniqueKey();
    	session.setAttribute("logintoken", logintoken);
    	model.addAttribute("logintoken", logintoken);
    	return "/login";
	}

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未授权，无法访问此页面";
    }

    @RequestMapping("/loginout")
    public String loginout(){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:login";
    }
    
    @RequestMapping("/userLoginType")
    @ResponseBody
    public JSONObject userLoginType(User user) {
    	 user = userService.checkLogin(user.getUsername());
    	 JSONObject result = new JSONObject();
    	 result.put("success", false);
    	 if(user!=null) {
    		 if(user.getDynamic_auth()==1) {
        		 result.put("dynamic_auth", 1);
        		 result.put("auth_type", "dynamic_auth");
            	 result.put("success", true);
    		 }else if(user.getSms_auth()==1) {
        		 result.put("sms_auth", 1);
        		 result.put("auth_type", "sms_auth");
        		 result.put("mobile", user.getMobile().substring(7));
            	 result.put("success", true);
    		 }else if(user.getFinger_auth()==1) {
    			 if(user.getLocal_auth()==1) {
    				 result.put("local_finger_auth", 1);
    				 result.put("auth_type", "local_finger_auth");
    			 }else {
    				 result.put("finger_auth", 1);
            		 result.put("auth_type", "finger_auth");
    			 }
            	 result.put("success", true);
    		 }else {
        		 result.put("local_auth", 1);
        		 result.put("auth_type", "local_auth");
            	 result.put("success", true);
    		 }
    	 }else {
    		 result.put("msg", "用户不存在");
    	 }
		 return result;
	}
    
    @RequestMapping("/qrcodeInfo")
    @ResponseBody
    public JSONObject qrcodeInfo(User user, HttpServletRequest request, HttpSession session) {
    	 user = userService.checkLogin(user.getUsername());
    	 JSONObject result = new JSONObject();
    	 result.put("success", false);
    	 if(user==null||!user.getPassword().equals(request.getParameter("password"))) {
    		 result.put("msg", "用户名或密码不正确");
    		 return result;
    	 }
    	 if(user.getDynamic_auth()==1) {
    		 String base64Content = "";
    		 String googleAuthKen = "";
    		 if(user.getGoogle_auth_token_used()==0) {

    	         	int width = 100;
    	         	int height = 100;
    	             Config config = new Config();
    	             config.setName("company");
    	             ArrayList<Object> configs = new ArrayList<Object>();
    	             configs = (ArrayList<Object>) configService.findAll(config, 0, 1);
    	             config = (Config) ((ArrayList<Config>)configs.get(0)).get(0);
    	             
    	             String content = GoogleAuthenticatorUtil.createGoogleAuthQRCodeData(user.getGoogle_auth_key(), user.getRealname(), config.getValue());
    	             base64Content = QRCodeUtils.toBase64ImageData(content);
    	             googleAuthKen = user.getGoogle_auth_key();
    	             
    	    		 session.setAttribute("tmp_dynamic_auth_qrcode_image_userid",user.getId());
    		 }
    		 result.put("dynamic_auth", 1);
    		 result.put("showQrcode", user.getGoogle_auth_token_used());
    		 result.put("qrcodeImgBase64Data", base64Content);
    		 result.put("googleAuthKey", googleAuthKen);
        	 result.put("success", true);
    	 }
		 return result;
	}

    @RequestMapping("/qrcode")
    public void qrcode( HttpSession session, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        String userid = "";
        if(session.getAttribute("tmp_dynamic_auth_qrcode_image_userid")!=null)
        	userid = session.getAttribute("tmp_dynamic_auth_qrcode_image_userid").toString();
        if(userid.isEmpty()) {
        	return ;
        }
        try {
        	int width = 100;
        	int height = 100;
            outputStream = response.getOutputStream();
            Config config = new Config();
            config.setName("company");
            ArrayList<Object> configs = new ArrayList<Object>();
            User user = userService.getUserByID(userid);
            configs = (ArrayList<Object>) configService.findAll(config, 0, 1);
            config = (Config) ((ArrayList<Config>)configs.get(0)).get(0);
            
            String content = GoogleAuthenticatorUtil.createGoogleAuthQRCodeData(user.getGoogle_auth_key(), user.getRealname(), config.getValue());
            QRCodeUtils.writeToStream(content, outputStream, width, height);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            session.setAttribute("tmp_dynamic_auth_qrcode_image_userid",null);
        }
    }
    
    private boolean dynamicAuthCheck(String userid, String code) {
    	User user = userService.getUserByID(userid);
	    return GoogleAuthenticatorUtil.verify(user.getGoogle_auth_key(), code);
    } 
}
