package com.longersec.blj.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.longersec.blj.domain.AccessPolicy;
import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.domain.ApppubProgram;
import com.longersec.blj.domain.ApppubRecord;
import com.longersec.blj.domain.ApppubServer;
import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.ConfigDisksession;
import com.longersec.blj.domain.Device;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.GConnection;
import com.longersec.blj.domain.GConnectionParameter;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.AccessPolicyService;
import com.longersec.blj.service.ApppubAccountService;
import com.longersec.blj.service.ApppubProgramService;
import com.longersec.blj.service.ApppubRecordService;
import com.longersec.blj.service.ApppubServerService;
import com.longersec.blj.service.CmdPolicyService;
import com.longersec.blj.service.ConfigDisksessionService;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.DeviceRecordService;
import com.longersec.blj.service.DeviceService;
import com.longersec.blj.service.GConnectionParameterService;
import com.longersec.blj.service.GConnectionService;
import com.longersec.blj.service.ProtocolService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.AdOperate;
import com.longersec.blj.utils.JSBtoAAtoB;
import com.longersec.blj.utils.SystemCommandUtil;
import com.longersec.blj.utils.SystemUsageUtil;
import com.longersec.blj.utils.httpClient;
import com.sun.tools.javac.code.Attribute.Array;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 */
@Controller
@RequestMapping(value = "/Operator")
public class OperatorController {
	@Autowired
	private ConfigService configService;
	@Autowired
	private DeviceAccountService deviceAccountService;
	@Autowired
	private ApppubAccountService apppubAccountService;
	@Autowired
	private ProtocolService protocolService;
	@Autowired
	private GConnectionService gConnectionService;
	@Autowired
	private DeviceRecordService deviceRecordService;
	@Autowired
	private ApppubRecordService apppubRecordService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private GConnectionParameterService gConnectionParameterService;
	@Autowired
	private AccessPolicyService accessPolicyService;
	@Autowired
	private ConfigDisksessionService configDisksessionService;
	@Autowired
	private CmdPolicyService cmdPolicyService;
	@Autowired
	private ApppubServerService apppubServerService;
	@Autowired
	private ApppubProgramService apppubProgramService;
	@Autowired
	private UserService userService;

    @RequestMapping("/deviceList")
    @ResponseBody
    public JSONObject deviceList(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
    	int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> results = new ArrayList<Object>();
		ArrayList<Map<String, Object>> Devices = new ArrayList<Map<String, Object>>();
		long total = 0;
		HashMap<String, Object> where = new HashMap<String, Object>();
		String searchall = request.getParameter("searchAll");
		String device_name = request.getParameter("device_name");
		String device_ip = request.getParameter("device_ip");
		String username = request.getParameter("username");
		String protocol = request.getParameter("protocol");
		String data = request.getParameter("data");
		if(searchall!=null&&!searchall.equals("")) {
			where.put("searchall", searchall);
		}
		if(device_name!=null&&!device_name.equals("")) {
			where.put("device_name", device_name);
		}
		if(device_ip!=null&&!device_ip.equals("")) {
			where.put("device_ip", device_ip);
		}
		if(username!=null&&!username.equals("")) {
			where.put("username", username);
		}
		if(protocol!=null&&!protocol.equals("")) {
			where.put("protocol", protocol);
		}
		if(data!=null&&!data.equals("")) {
			if(data.equals("graph")) {
				where.put("protocol_id", configService.getByName("graphLoginType").getValue().split(","));
			}else if(data.equals("text")) {
				where.put("protocol_id", configService.getByName("textLoginType").getValue().split(","));
			}else if(data.equals("favorite")) {
				where.put("favorite", 1);
			}else if(data.equals("history")) {
				where.put("history", 1);
			}
			
		}
		User user = (User) session.getAttribute("loginUser");
		ArrayList<Integer> policyArrayList = (ArrayList<Integer>) session.getAttribute("userAllowedPolicies");
		if(policyArrayList!=null&&policyArrayList.size()>0) {
			results = (ArrayList<Object>) deviceAccountService.getDeviceAccountByPolicies(user.getId(), policyArrayList, where, page_start, page_length);
	    	if(CollectionUtils.isNotEmpty(results)) {
	    		Devices = (ArrayList<Map<String, Object>>)results.get(0);
				total = ((ArrayList<Long>) results.get(1)).get(0);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(Devices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
    	return result;
    }
    
    @RequestMapping("/appList")
    @ResponseBody
    public JSONObject appList(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
    	int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> results = new ArrayList<Object>();
		ArrayList<Map<String, Object>> Devices = new ArrayList<Map<String, Object>>();
		long total = 0;
		HashMap<String, Object> where = new HashMap<String, Object>();
		String searchall = request.getParameter("searchAll");
		String name = request.getParameter("name");
		String apppubprogram = request.getParameter("apppubprogram");
		String username = request.getParameter("username");
		String data = request.getParameter("data");
		if(searchall!=null&&!searchall.equals("")) {
			where.put("searchall", searchall);
		}
		if(name!=null&&!name.equals("")) {
			where.put("name", name);
		}
		if(apppubprogram!=null&&!apppubprogram.equals("")) {
			where.put("apppubprogram", apppubprogram);
		}
		if(username!=null&&!username.equals("")) {
			where.put("username", username);
		}
		if(data!=null&&!data.equals("")) {
			if(data.equals("graph")) {
				where.put("protocol_id", configService.getByName("graphLoginType").getValue().split(","));
			}else if(data.equals("text")) {
				where.put("protocol_id", configService.getByName("textLoginType").getValue().split(","));
			}else if(data.equals("favorite")) {
				where.put("favorite", 1);
			}else if(data.equals("history")) {
				where.put("history", 1);
			}
			
		}
		User user = (User) session.getAttribute("loginUser");
		ArrayList<Integer> policyArrayList = (ArrayList<Integer>) session.getAttribute("userAllowedPolicies");
		if(policyArrayList!=null&&policyArrayList.size()>0) {
			results = (ArrayList<Object>) apppubAccountService.getApppubAccountByPolicies(user.getId(), policyArrayList, where, page_start, page_length);
	    	if(CollectionUtils.isNotEmpty(results)) {
	    		Devices = (ArrayList<Map<String, Object>>)results.get(0);
				total = ((ArrayList<Long>) results.get(1)).get(0);
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(Devices);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
    	return result;
    }
    
    @RequestMapping("/deviceLogin")
    public void deviceLogin(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer device_account_id, Integer apppub_account_id) {
    	JSONObject  result = new JSONObject();
    	result.put("success","false");
    	GConnection gConnection = new GConnection();
		User user = (User) session.getAttribute("loginUser");
		ConfigDisksession configDisksession = configDisksessionService.get();
		Config config = new Config();
		config = configService.getByName("recordpath");//寻找授权策略
    	ArrayList<AccessPolicy> accessPolicies = new ArrayList<AccessPolicy>();
    	accessPolicies = (ArrayList<AccessPolicy>) accessPolicyService.getUserPolicy(user.getId(), user.getGroupid(), device_account_id, apppub_account_id);
		
    	if(device_account_id!=null) {
    		//设备账号
        	DeviceAccount deviceAccount = deviceAccountService.getById(device_account_id);
        	//获取设备信息
			Device device = deviceService.getById(deviceAccount.getDevice_id());
        	Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("YYYYMMddhhmmssS");
            String timeString = ft.format(dNow);
        	String logfileString = config.getValue()+"/text/"+device.getIp()+"-"+deviceAccount.getUsername()+"-"+timeString;
        	String videofileString = config.getValue()+"/videos/"+device.getIp()+"-"+deviceAccount.getUsername()+"-"+timeString;

    		//int diskUsage = new Double(systemUsageUtil.getDiskUsage()*100).intValue();
        	int diskUsage = 0;
        	if(diskUsage>configDisksession.getDisk_max_persent()) {
        		if(configDisksession.getDisk_write_type()==0) {
        			logfileString = "";
        			videofileString = "";
        		}else {
        			ArrayList<DeviceRecord> records = new ArrayList<DeviceRecord>();
        			records = (ArrayList<DeviceRecord>)deviceRecordService.getEarlyRecord();
        			for (DeviceRecord deviceRecord : records) {
        				File file = new File(deviceRecord.getLog_file());
        				File file2 = new File(deviceRecord.getVideo_file());
        				if(file.exists()&&file2.exists()) {
        					file.delete();
        					file2.delete();
        					DeviceRecord upDeviceRecord = new DeviceRecord();
        					deviceRecord.setLog_file("");
        					deviceRecord.setLog_file_size(0);
        					deviceRecord.setVideo_file("");
        					deviceRecord.setVideo_file_size(0);
        					deviceRecordService.editDeviceRecord(upDeviceRecord);
        					break;
        				}
					}
        		}
        	}
            
        	DeviceRecord deviceRecord = new DeviceRecord();
        	deviceRecord.setClient_ip(httpClient.getRemortIP(request));
        	deviceRecord.setDevice_ip(device.getIp());
        	deviceRecord.setDevice_name(device.getName());
        	deviceRecord.setDevice_username(deviceAccount.getUsername());
        	deviceRecord.setProtocol_id(deviceAccount.getProtocol_id());
        	deviceRecord.setPort(deviceAccount.getPort());
        	deviceRecord.setStart(Integer.toString((int)(System.currentTimeMillis()/1000)));
        	deviceRecord.setEnd(Integer.toString((int)(System.currentTimeMillis()/1000)));
        	deviceRecord.setUser_id(user.getId());
        	deviceRecord.setUsername(user.getUsername());
        	deviceRecord.setRealname(user.getRealname());
        	deviceRecord.setAuth_type(session.getAttribute("login_type").toString());
        	
        	gConnection.setConnection_name(deviceAccount.getId()+"-"+deviceAccount.getDevice_id()+"-"+deviceAccount.getUsername());
        	if(!(deviceAccount.getProtocol_id()==5||deviceAccount.getProtocol_id()==6)) {
            	deviceRecord.setLog_file(logfileString);
            	deviceRecord.setVideo_file(videofileString);
            	gConnection.setConnection_name(deviceAccount.getId().toString());
        	}
        	deviceRecordService.addDeviceRecord(deviceRecord);

    		gConnection.setProtocol(protocolService.getById(deviceAccount.getProtocol_id()).toLowerCase());
    		gConnectionService.addGConnection(gConnection);
    		int gconnection_id = gConnection.getConnection_id();
    		this.addGConnectionParameters(gconnection_id, deviceRecord, deviceAccount, accessPolicies.get(0), session);
    		
    		try {
    			String hashString = new sun.misc.BASE64Encoder().encode(new String(gconnection_id+"\0c\0"+configService.getByName("connectDataSource").getValue()+"\0u"+user.getId()+"\0"+deviceRecord.getDevice_username()+"@"+deviceRecord.getDevice_name()+"("+deviceRecord.getDevice_ip()+")"+"\0"+request.getContextPath()).getBytes());
    			if(deviceAccount.getProtocol_id()==5||deviceAccount.getProtocol_id()==6) {
    				response.setContentType("multipart/form-data");
    				response.setCharacterEncoding("UTF-8");
    				response.setContentType("text/json");
    				ServletOutputStream out;
    				out = response.getOutputStream();
    				JSONObject jsonObject = new JSONObject();
    				jsonObject.put("success", true);
    				jsonObject.put("ip", request.getServerName());
    				if(deviceAccount.getProtocol_id()==5) {
    					jsonObject.put("port", configService.getByName("ftpPort").getValue());
    				}else {
    					jsonObject.put("port", configService.getByName("sftpPort").getValue());
    				}
    				
    				jsonObject.put("username", gconnection_id);
    				jsonObject.put("password", gconnection_id);
					out.write(jsonObject.toString().getBytes());
					out.flush();
					out.close();
					return ;
        		} else {
        			response.sendRedirect("/cLogin/#/client/"+hashString);
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else if(apppub_account_id != null) {
    		//设备账号
    		
        	ApppubAccount apppubAccount = apppubAccountService.getById(apppub_account_id);
        	ApppubProgram apppubProgram = apppubProgramService.getById(apppubAccount.getApppub_program_id());
        	ApppubServer apppubServer = apppubServerService.getById(apppubAccount.getApppub_server_id());
        	
        	/*//"ldaps://WIN-NH8NPMU1DK2.lsblj.cn:636"
    		AdOperate adOperate = new AdOperate("ldaps://"+apppubServer.getName()+":"+apppubServer.getAdport(), apppubServer.getAccount()+"@lsblj.cn", apppubServer.getPassword(), "DC=lsblj,DC=cn");
    		user = userService.getUserByID(user.getId().toString());
			adOperate.addUser(user.getUsername(), user.getPassword());
    		*/
        	user = userService.getUserByID(user.getId().toString());
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("dn", "cn="+user.getUsername()+",OU=apppubusers,DC=lsblj,DC=cn");
        	jsonObject.put("password", user.getPassword());
        	jsonObject.put("upn", user.getUsername()+"@lsblj.cn");
        	jsonObject.put("cn", user.getUsername());
        	jsonObject.put("group", "CN=apppub,DC=lsblj,DC=cn");
        	String resultString = com.longersec.blj.utils.httpClient.doPost("http://"+apppubServer.getIp()+":20616/aduser.php", jsonObject, "UTF-8");
        	
        	Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("YYYYMMddhhmmssS");
            String timeString = ft.format(dNow);
        	String logfileString = config.getValue()+"/text/apppub-"+apppub_account_id+"-"+timeString;
        	String videofileString = config.getValue()+"/videos/apppub-"+apppub_account_id+"-"+timeString;

    		//int diskUsage = new Double(systemUsageUtil.getDiskUsage()*100).intValue();
        	int diskUsage = 0;
        	if(diskUsage>configDisksession.getDisk_max_persent()) {
        		if(configDisksession.getDisk_write_type()==0) {
        			logfileString = "";
        			videofileString = "";
        		}else {
        			ArrayList<ApppubRecord> records = new ArrayList<ApppubRecord>();
        			records = (ArrayList<ApppubRecord>)apppubRecordService.getEarlyRecord();
        			for (ApppubRecord apppubRecord : records) {
        				File file = new File(apppubRecord.getLog_file());
        				File file2 = new File(apppubRecord.getVideo_file());
        				if(file.exists()&&file2.exists()) {
        					file.delete();
        					file2.delete();
        					ApppubRecord upApppubRecord = new ApppubRecord();
        					upApppubRecord.setLog_file("");
        					upApppubRecord.setLog_file_size(0);
        					upApppubRecord.setVideo_file("");
        					upApppubRecord.setVideofile_size(0);
        					apppubRecordService.editApppubRecord(upApppubRecord);
        					break;
        				}
					}
        		}
        	}
            
        	ApppubRecord apppubRecord = new ApppubRecord();
        	apppubRecord.setName(apppubAccount.getName());
        	apppubRecord.setClient_ip(httpClient.getRemortIP(request));
        	apppubRecord.setServer_ip(apppubServer.getIp());
        	apppubRecord.setApppub_username(apppubAccount.getUsername());
        	apppubRecord.setPort(apppubServer.getPort());
        	apppubRecord.setStart(Integer.toString((int)(System.currentTimeMillis()/1000)));
        	apppubRecord.setEnd(Integer.toString((int)(System.currentTimeMillis()/1000)));
        	apppubRecord.setUser_id(user.getId());
        	apppubRecord.setUsername(user.getUsername());
        	apppubRecord.setRealname(user.getRealname());
        	apppubRecord.setVideo_file(videofileString);
        	apppubRecord.setProgram(apppubProgram.getName());
        	apppubRecord.setProgrampath(apppubProgram.getPath());
        	apppubRecordService.addApppubRecord(apppubRecord);
       
    		gConnection.setConnection_name(apppubRecord.getId()+"-"+apppubAccount.getId()+"-"+apppubAccount.getUsername());
    		gConnection.setProtocol("rdp");
    		gConnectionService.addGConnection(gConnection);
    		int gconnection_id = gConnection.getConnection_id();
    		this.addGConnectionApppubParameters(gconnection_id, apppubRecord, apppubAccount, accessPolicies.get(0), session);
    		String hashString = new sun.misc.BASE64Encoder().encode(new String(gconnection_id+"\0c\0"+configService.getByName("connectDataSource").getValue()+"\0u"+user.getId()+"\0"+apppubRecord.getApppub_username()+"@"+apppubRecord.getServer_ip()+"("+apppubRecord.getServer_ip()+")"+"\0"+request.getContextPath()).getBytes());

    		try {
				response.sendRedirect("/cLogin/#/client/"+hashString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return ;
	}
    
    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) {
		DeviceRecord devciRecord = deviceRecordService.getById(recordid);
		this.downloadVideo(request, response, session, devciRecord.getVideo_file());
    }
    @RequestMapping("/downloadApp")
    public void downloadApp(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) {
		ApppubRecord apppubRecord = apppubRecordService.getById(recordid);
		this.downloadVideo(request, response, session, apppubRecord.getVideo_file());
    }
    
    private void downloadText(HttpServletRequest request,HttpServletResponse response, HttpSession session, String filename) {
    	if(filename!=null) {
    		response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("content-disposition","attachment;fileName="+filename.substring(filename.lastIndexOf('/')+1)+".txt");
			ServletOutputStream out;
			// 3.通过response获取ServletOutputStream对象(out)
			try {
				out = response.getOutputStream();
				out.write(SystemCommandUtil.execCmd("cat " + filename).getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return ;
	}
    
    private void downloadVideo(HttpServletRequest request,HttpServletResponse response, HttpSession session, String filename) {
    	if(filename!=null) {
    		response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("content-disposition","attachment;fileName="+filename.substring(filename.lastIndexOf('/')+1)+".m4v");
			// 3.通过response获取ServletOutputStream对象(out)
			try {
				File file = new File(filename+".m4v");
	            InputStream stream = new FileInputStream(file);
	            ServletOutputStream out = response.getOutputStream();
	            byte buff[] = new byte[1024];
	            int length = 0;
	            while ((length = stream.read(buff)) > 0) {
	                out.write(buff,0,length);
	            }
	            stream.close();
	            out.close();
	            out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return ;
    }
    
    @RequestMapping("/getKeys")
    @ResponseBody
    public JSONArray getKeys(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) {
		DeviceRecord devciRecord = deviceRecordService.getById(recordid);
		JSONArray resultArray = new JSONArray();
    	if(devciRecord!=null) {
			// 3.通过response获取ServletOutputStream对象(out)
			try {
				FileInputStream inputStream = new FileInputStream(devciRecord.getVideo_file()+".txt");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String str = null;
				while((str = bufferedReader.readLine()) != null)
				{
					resultArray.add(str);
				}
				//close
				inputStream.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return resultArray;
    }
    
    @RequestMapping("/Replay")
    public String Replay(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid, @RequestParam(value="isapppub", defaultValue="0")int isapppub) throws Exception {
    	if(isapppub>0) {
    		ApppubRecord apppubRecord = apppubRecordService.getById(recordid);
        	model.addAttribute("record", apppubRecord);
        	model.addAttribute("isapppub", 1);
    	}else {
        	DeviceRecord devciRecord = deviceRecordService.getById(recordid);
        	model.addAttribute("record", devciRecord);
        	model.addAttribute("isapppub", 0);
		}
    	model.addAttribute("recordid", recordid);
    	return "view/audit_manage/replay";
    }
    
    @RequestMapping("/deviceReplay")
    public void deviceReplay(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) throws Exception {
        DeviceRecord devciRecord = deviceRecordService.getById(recordid);
		File file = new File(devciRecord.getVideo_file());
	    try {
            InputStream stream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte buff[] = new byte[1024];
            int length = 0;
            while ((length = stream.read(buff)) > 0) {
                out.write(buff,0,length);
            }
            stream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }
    
    @RequestMapping("/appReplay")
    public void appReplay(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) throws Exception {

        ApppubRecord apppubRecord = apppubRecordService.getById(recordid);
		File file = new File(apppubRecord.getVideo_file());
	    try {
            InputStream stream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte buff[] = new byte[1024];
            int length = 0;
            while ((length = stream.read(buff)) > 0) {
                out.write(buff,0,length);
            }
            stream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }
    
    //添加连接参数
    private void addGConnectionParameters(int gconnection_id, DeviceRecord deviceRecord, DeviceAccount deviceAccount, AccessPolicy accessPolicy, HttpSession session) {
    	User user = (User) session.getAttribute("loginUser");
    	Config config = new Config();
		this.addGConnParameter(gconnection_id, "record-id", deviceRecord.getId().toString());
		//this.addGConnParameter(gconnection_id, "font-name", "DroidSansMono");
    	switch (deviceRecord.getProtocol_id()) {
		case 1:
			List<String> commands = cmdPolicyService.getCommandsByDeviceAndUser(deviceAccount.getId(), user.getId(), user.getGroupid());
			this.addGConnParameter(gconnection_id, "policy-commands", String.join("\n", commands)+"\n");
			break;
		case 2:
			this.addGConnParameter(gconnection_id, "color-depth", "24");
			this.addGConnParameter(gconnection_id, "console", "true");
			this.addGConnParameter(gconnection_id, "ignore-cert", "true");
			this.addGConnParameter(gconnection_id, "create-drive-path", "true");
			this.addGConnParameter(gconnection_id, "enable-drive", "true");
			this.addGConnParameter(gconnection_id, "drive-name", user.getUsername());
			this.addGConnParameter(gconnection_id, "drive-path", configService.getByName("userdriver").getValue()+"/"+user.getUsername());
			this.addGConnParameter(gconnection_id, "create-drive-path", "true");
			
			break;
		case 5:
		case 6:
			return;
		default:
			break;
		}
    	
		this.addGConnParameter(gconnection_id, "hostname", deviceRecord.getDevice_ip());
		this.addGConnParameter(gconnection_id, "port", deviceRecord.getPort().toString());
		this.addGConnParameter(gconnection_id, "username", deviceRecord.getDevice_username());
		this.addGConnParameter(gconnection_id, "password", deviceAccount.getPassword());
		
		String logfileString = deviceRecord.getLog_file();
		String videofileString = deviceRecord.getVideo_file();
		
		this.addGConnParameter(gconnection_id, "recording-include-keys", "true");
		this.addGConnParameter(gconnection_id, "create-recording-path", "true");
		this.addGConnParameter(gconnection_id, "recording-path", videofileString.substring(0,videofileString.lastIndexOf('/')));
		this.addGConnParameter(gconnection_id, "recording-name", videofileString.substring(videofileString.lastIndexOf('/')+1));
		//this.addGConnParameter(gconnection_id, "typescript-path", logfileString.substring(0,logfileString.lastIndexOf('/')));
		//this.addGConnParameter(gconnection_id, "typescript-name", logfileString.substring(logfileString.lastIndexOf('/')+1));
		//this.addGConnParameter(gconnection_id, "create-typescript-path", "true");
    	return;
    }
  //添加连接参数
    private void addGConnectionApppubParameters(int gconnection_id, ApppubRecord apppubRecord, ApppubAccount apppubAccount, AccessPolicy accessPolicy, HttpSession session) {
    	User user = (User) session.getAttribute("loginUser");
    	user = userService.getUserByID(user.getId().toString());
    	Config config = new Config();
		this.addGConnParameter(gconnection_id, "hostname", apppubRecord.getServer_ip());
		this.addGConnParameter(gconnection_id, "port", apppubRecord.getPort().toString());
		this.addGConnParameter(gconnection_id, "username", user.getUsername());
		this.addGConnParameter(gconnection_id, "password", user.getPassword());
		this.addGConnParameter(gconnection_id, "domain", "lsblj.cn");
		this.addGConnParameter(gconnection_id, "record-id", apppubRecord.getId().toString());
		
		String logfileString = apppubRecord.getLog_file();
		String videofileString = apppubRecord.getVideo_file();
		
		this.addGConnParameter(gconnection_id, "recording-include-keys", "true");
		this.addGConnParameter(gconnection_id, "create-recording-path", "true");
		this.addGConnParameter(gconnection_id, "recording-path", videofileString.substring(0,videofileString.lastIndexOf('/')));
		this.addGConnParameter(gconnection_id, "recording-name", videofileString.substring(videofileString.lastIndexOf('/')+1));
		this.addGConnParameter(gconnection_id, "color-depth", "24");
		//this.addGConnParameter(gconnection_id, "console", "true");
		this.addGConnParameter(gconnection_id, "ignore-cert", "true");
		this.addGConnParameter(gconnection_id, "remote-app", "||"+apppubRecord.getProgrampath());
		this.addGConnParameter(gconnection_id, "remote-app-args", apppubAccount.getUrl());
    	return;
    }
    
    private void addGConnParameter(int gconnection_id, String name, String value) {
    	GConnectionParameter gConnectionParameter = new GConnectionParameter();
    	gConnectionParameter.setConnection_id(gconnection_id);
    	gConnectionParameter.setParameter_name(name);
    	gConnectionParameter.setParameter_value(value);
    	gConnectionParameterService.addGConnectionParameter(gConnectionParameter);
    }
    
    @RequestMapping("/scriptReplay")
    public void scriptReplay(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer recordid) {
    	JSONObject  result = new JSONObject();
    	result.put("success","false");
    	GConnection gConnection = new GConnection();
    	if(recordid!=null) {
    		User user = (User) session.getAttribute("loginUser");
    		//设备账号
        	//获取设备信息
    		DeviceRecord devciRecord = deviceRecordService.getById(recordid);
        	
    		gConnection.setConnection_name(recordid.toString());
    		gConnection.setProtocol(protocolService.getById(devciRecord.getProtocol_id()));
    		gConnectionService.addGConnection(gConnection);
    		int gconnection_id = gConnection.getConnection_id();
    		this.addGConnParameter(gconnection_id, "hostname", configService.getByName("baoleijireplayaddress").getValue());
    		this.addGConnParameter(gconnection_id, "port", configService.getByName("baoleijisshport").getValue());
    		this.addGConnParameter(gconnection_id, "username", configService.getByName("baoleijireplayusername").getValue());
    		this.addGConnParameter(gconnection_id, "password", configService.getByName("baoleijireplaypassword").getValue());
    		this.addGConnParameter(gconnection_id, "record-id", "0");
    		this.addGConnParameter(gconnection_id, "command", "scriptreplay "+devciRecord.getLog_file()+".timing "+devciRecord.getLog_file()+"  && exit");
		
    		String hashString = JSBtoAAtoB.btoa(gconnection_id+"\0c\0"+configService.getByName("connectDataSource").getValue()+"\0u"+user.getId());
    		
    		try {
				response.sendRedirect("/cLogin/#/client/"+hashString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return ;
	}
    
    
}
