package com.longersec.blj.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.domain.Config;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.utils.SystemCommandUtil;
import com.longersec.blj.utils.SystemUsageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController {

	@Autowired
	private ConfigService configService;

	@RequestMapping("/listConfig")
	@ResponseBody
	public JSONObject listConfig(Config config,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigs = new ArrayList<Object>();
		ArrayList<Config> configs = new ArrayList<Config>();
		long total = 0;
		resultConfigs = (ArrayList<Object>)configService.findAll(config, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigs)) {
			configs = (ArrayList<Config>)resultConfigs.get(0);
			total = ((ArrayList<Long>) resultConfigs.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configs);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfig")
	@ResponseBody
	public JSONObject addConfig(Config config, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configService.addConfig(config);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfig")
	@ResponseBody
	public JSONObject editConfig(Config config, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configService.editConfig(config);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfig")
	@ResponseBody
	public JSONObject delConfig(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = configService.delConfig(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/keepalived")
	@ResponseBody
	public JSONObject keepalived(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", false);
		result.put("master", 0);
		Config keepaliveConfig = configService.getByName("keepAliveConfigFile");
		try {
			FileInputStream inputStream = new FileInputStream(keepaliveConfig.getValue());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String str = null;
			Boolean found_virtual_address = false;
			while((str = bufferedReader.readLine()) != null)
			{
				
				str = str.trim();
				if(found_virtual_address) {
					result.put("virtual_ipaddress", str.trim());
					found_virtual_address = false;
				}else if(str.toLowerCase().indexOf("state")>=0) {
					if(str.substring(str.lastIndexOf(' ')+1).toLowerCase().equals("master")) {
						result.put("master", 1);
					}
				}else if(str.toLowerCase().indexOf("interface")>=0) {
					result.put("interface", str.substring(str.lastIndexOf(' ')+1));
				}else if(str.toLowerCase().indexOf("mcast_src_ip")>=0) {
					result.put("mcast_src_ip", str.substring(str.lastIndexOf(' ')+1));
				}else if(str.toLowerCase().indexOf("virtual_ipaddress")>=0) {
					found_virtual_address=true;
					
				}
			}
			String[] resultString = SystemCommandUtil.execCmd("status keepalived").split("\n"); 
			for (String string : resultString) {
				if(string.toLowerCase().indexOf("active")>=0&&string.toLowerCase().indexOf("running")>=0) {
					result.put("running", 1);
					break;
				}
			}
			if(!result.isEmpty()) {
				result.put("success", true);
			}
			//close
			inputStream.close();
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/configKpalive")
	@ResponseBody
	public JSONObject configKpalive(HttpServletRequest request, HttpSession session) {
		JSONObject config = new JSONObject();
		if(request.getParameter("master")!=null) {
			config.put("master",request.getParameter("master"));
		}
		if(request.getParameter("key")!=null) {
			config.put("key",request.getParameter("key"));
		}
		if(request.getParameter("virtual_ipaddress")!=null) {
			config.put("virtual_ipaddress",request.getParameter("virtual_ipaddress"));
		}
		if(request.getParameter("mcast_src_ip")!=null) {
			config.put("mcast_src_ip",request.getParameter("mcast_src_ip"));
		}
		if(request.getParameter("interface")!=null) {
			config.put("interface",request.getParameter("interface"));
		}
		Config keepaliveConfig = configService.getByName("keepAliveConfigFile");
		Config mariadbConfig = configService.getByName("mariadbConfigFile");
		Config interfacePath = configService.getByName("interfacePath");
		try {

			FileInputStream inputStream = new FileInputStream(interfacePath.getValue()+"/ifcfg-"+config.getString("interface"));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String str = null;
			String localIpString = "";
			while((str = bufferedReader.readLine()) != null)
			{
				if(str.toLowerCase().indexOf("ipaddr")>=0) {
					localIpString = str.substring(str.lastIndexOf('=')+1);
					break;
				}
			}
			
			inputStream = new FileInputStream(keepaliveConfig.getValue());
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			Boolean found_virtual_address = false;
			String kconentString = "";
			while((str = bufferedReader.readLine()) != null)
			{
				if(found_virtual_address) {
					kconentString += "\t"+config.getString("virtual_ipaddress")+"\n";
					found_virtual_address = false;
				}else if(str.toLowerCase().indexOf("state")>=0) {
					if(config.getInt("master")==1) {
						kconentString += "\tstate MASTER\n";
					}else {
						kconentString += "\tstate BACKUP\n";
					}
				}else if(str.toLowerCase().indexOf("interface")>=0) {
					kconentString += "\tinterface "+config.getString("interface")+"\n";
				}else if(str.toLowerCase().indexOf("mcast_src_ip")>=0) {
					kconentString += "\tmcast_src_ip "+localIpString+"\n";
				}else if(str.toLowerCase().indexOf("virtual_server")>=0) {
					kconentString += "virtual_server "+config.getString("virtual_ipaddress")+" 443 {\n";
				}else if(str.toLowerCase().indexOf("real_server")>=0) {
					kconentString += "\treal_server "+localIpString+" 443 {\n";
				}else if(str.toLowerCase().indexOf("virtual_ipaddress")>=0) {
					found_virtual_address=true;
					kconentString += str+"\n";
				}else {
					kconentString += str+"\n";
				}
			}
			inputStream.close();
			bufferedReader.close();
			File file =new File(keepaliveConfig.getValue());
			//File file = new File("/tmp/k");
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(kconentString);
			bw.close();
			
	        kconentString = "";
			inputStream = new FileInputStream(mariadbConfig.getValue());
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while((str = bufferedReader.readLine()) != null)
			{
				if(str.toLowerCase().indexOf("server-id")>=0) {
					if(config.getInt("master")==1) {
						kconentString += "server-id = 1\n";
					}else {
						kconentString += "server-id = 2\n";
					}
				}else {
					kconentString += str+"\n";
				}
			}
			SystemCommandUtil.execCmd("systemctl restart mariadb"); 
			SystemCommandUtil.execCmd("stop slave"); 
			SystemCommandUtil.execCmd("reset master"); 
			SystemCommandUtil.execCmd("reset slave"); 
			SystemCommandUtil.execCmd("firewall-cmd --permanent --add-port=3306/tcp"); 
			//close
			inputStream.close();
			bufferedReader.close();

			File fileM =new File(mariadbConfig.getValue());
			//File fileM =new File("/tmp/m");
			FileWriter fileWriterM = new FileWriter(fileM.getAbsoluteFile());
			BufferedWriter bwM = new BufferedWriter(fileWriterM);
			bwM.write(kconentString);
			bwM.close();
	        configService.query("grant  replication slave  on   *.* to 'lsblj'@'"+config.getString("mcast_src_ip")+"'   identified  by  '"+config.getString("key")+"'");
	        configService.query("flush privileges");
	        configService.query("change master to master_host='"+config.getString("virtual_ipaddress")+"',master_user='lsblj',master_password='"+config.getString("key")+"',master_log_file='mysql-bin.000001',master_log_pos=1521");
	        configService.query("start slave");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		return jsonObject;
	}
}
