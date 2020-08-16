package com.longersec.blj.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.longersec.blj.domain.Config;
import com.longersec.blj.domain.ConfigDbbackup;
import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.service.ConfigDbbackupService;
import com.longersec.blj.service.ConfigService;
import com.longersec.blj.utils.SystemCommandUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/configDbbackup")
public class ConfigDbbackupController {

	@Autowired
	private ConfigDbbackupService configDbbackupService;
	@Autowired
	private ConfigService configService;

	@RequestMapping("/listConfigDbbackup")
	@ResponseBody
	public JSONObject listConfigDbbackup(ConfigDbbackup configDbbackup,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultConfigDbbackups = new ArrayList<Object>();
		ArrayList<ConfigDbbackup> configDbbackups = new ArrayList<ConfigDbbackup>();
		long total = 0;
		resultConfigDbbackups = (ArrayList<Object>)configDbbackupService.findAll(configDbbackup, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultConfigDbbackups)) {
			configDbbackups = (ArrayList<ConfigDbbackup>)resultConfigDbbackups.get(0);
			total = ((ArrayList<Long>) resultConfigDbbackups.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(configDbbackups);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addConfigDbbackup")
	@ResponseBody
	public JSONObject addConfigDbbackup(ConfigDbbackup configDbbackup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		if(result.getBoolean("success")) {
			Config config = configService.getByName("dbbackuppath");
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("YYYYMMddhhmmssS");
            String filepath = config.getValue()+"/lsblj-"+ft.format(dNow)+".sql";
			SystemCommandUtil.execCmd("/opt/lsblj/mariadb/bin/mysqldump -ulsblj -plsblj lsblj > " + filepath);
			File dbFile = new File(filepath);
			configDbbackup.setFilepath(filepath);
			configDbbackup.setFilesize(dbFile.length());
			Boolean r = configDbbackupService.addConfigDbbackup(configDbbackup);
			result.put("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editConfigDbbackup")
	@ResponseBody
	public JSONObject editConfigDbbackup(ConfigDbbackup configDbbackup, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = configDbbackupService.editConfigDbbackup(configDbbackup);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delConfigDbbackup")
	@ResponseBody
	public JSONObject delConfigDbbackup(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			for(int i=0; i<_ids.size(); i++) {
				ConfigDbbackup configDbbackup = configDbbackupService.getById(ids[i]);
				SystemCommandUtil.execCmd("rm -f "+configDbbackup.getFilepath());
			}
			Boolean r = configDbbackupService.delConfigDbbackup(_ids);
			result.accumulate("success", r);
		}
		return result;
	}

	@RequestMapping("/reboot")
	@ResponseBody
	public JSONObject reboot(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		String msgString = SystemCommandUtil.execCmd("sudo /sbin/init 6 ");
		result.put("msg", msgString);
		return result;
	}

	@RequestMapping("/shutdown")
	@ResponseBody
	public JSONObject shutdown(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		String msgString = SystemCommandUtil.execCmd("sudo /sbin/init 0 ");
		result.put("msg", msgString);
		return result;
	}

	@RequestMapping("/reset")
	@ResponseBody
	public JSONObject reset(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		String msgString = SystemCommandUtil.execCmd("/opt/lsblj/mariadb/bin/mysql -ulsblj -plsblj lsblj < /opt/lsblj/dbbackup/lsblj.sql");
		result.put("msg", msgString);
		return result;
	}

	@RequestMapping("/backupLicense")
	@ResponseBody
	public JSONObject backupLicense(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", false);
		result.accumulate("msg", "未找到许可文件");
		return result;
	}

	@RequestMapping("/applyLicense")
	@ResponseBody
	public JSONObject applyLicense(HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", false);
		result.accumulate("msg", "未找到许可文件");
		return result;
	}

	@RequestMapping("/uploadLicense")
	@ResponseBody
	public void uploadLicense(@RequestParam MultipartFile licenseFile, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ServletOutputStream out;
		String filenameString = "/tmp/"+(new Date()).getTime();
		try {
			out = response.getOutputStream();
			File destFile = new File(filenameString);
			if(licenseFile.getOriginalFilename().indexOf('.')<=0||!licenseFile.getOriginalFilename().substring(licenseFile.getOriginalFilename().indexOf('.')).equals(".lic")) {
				out.write("<script>window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			FileUtils.copyInputStreamToFile(licenseFile.getInputStream(), destFile);
			if(!destFile.exists()) {
				out.write("<script>window.parent.$('#uploadLicenseBtn')[0].disabled=false;window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"复制文件出错!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			SystemCommandUtil.execCmd("/opt/lsblj/mariadb/bin/mysql -ulsblj -plsblj lsblj < "+filenameString);
			out.write("<script>window.parent.$('window.parent.$('#uploadLicenseBtn')[0].disabled=false;#loadingModal').modal('hide');window.parent.$(\"#modal-success .modal-title\").text('成功');window.parent.$(\"#modal-success .modal-body\").text(\"操作成功!\");window.parent.$(\"#modal-success\").modal();</script>".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@RequestMapping("/recoverFile")
	@ResponseBody
	public void recoverFile(@RequestParam MultipartFile recoverfile, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ServletOutputStream out;
		String filenameString = "/tmp/"+(new Date()).getTime();
		try {
			out = response.getOutputStream();
			File destFile = new File(filenameString);
			if(recoverfile.getOriginalFilename().indexOf('.')<=0||!recoverfile.getOriginalFilename().substring(recoverfile.getOriginalFilename().indexOf('.')).equals(".sql")) {
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			FileUtils.copyInputStreamToFile(recoverfile.getInputStream(), destFile);
			if(!destFile.exists()) {
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"复制文件出错!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			SystemCommandUtil.execCmd("/opt/lsblj/mariadb/bin/mysql -ulsblj -plsblj lsblj < "+filenameString);
			out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-success .modal-title\").text('成功');window.parent.$(\"#modal-success .modal-body\").text(\"操作成功!\");window.parent.$(\"#modal-success\").modal();</script>".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@RequestMapping("/upgrade")
	@ResponseBody
	public void upgrade(@RequestParam MultipartFile upgradefile, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ServletOutputStream out;
		String filenameString = "/tmp/"+(new Date()).getTime();
		try {
			out = response.getOutputStream();
			File destFile = new File(filenameString);
			if(upgradefile.getOriginalFilename().indexOf('.')<=0||!upgradefile.getOriginalFilename().substring(upgradefile.getOriginalFilename().indexOf('.')).equals(".upgrade.zip")) {
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			FileUtils.copyInputStreamToFile(upgradefile.getInputStream(), destFile);
			if(!destFile.exists()) {
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"复制文件出错!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			SystemCommandUtil.execCmd("unzip -o "+destFile+" -d /tmp");
			File unzipfile = new File("/tmp/lsblj.war");
			if(!unzipfile.exists()) {
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不正确!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			SystemCommandUtil.execCmd("mv /opt/lsblj/tomcat/webapps/ROOT.war /opt/lsblj/tomcat/webapps/ROOT.war.bak");
			destFile = new File("/opt/lsblj/tomcat/webapps/ROOT.war");
			FileUtils.copyInputStreamToFile(upgradefile.getInputStream(), destFile);
			if(!destFile.exists()) {
				SystemCommandUtil.execCmd("mv /opt/lsblj/tomcat/webapps/ROOT.war.bak /opt/lsblj/tomcat/webapps/ROOT.war");
				out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-danger .modal-title\").text('升级失败');window.parent.$(\"#modal-danger .modal-body\").text(\"复制文件出错!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
			}
			out.write("<script>window.parent.$('#loadingModal').modal('hide');window.parent.$(\"#modal-success .modal-title\").text('成功');window.parent.$(\"#modal-success .modal-body\").text(\"操作成功!\");window.parent.$(\"#modal-success\").modal();</script>".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@RequestMapping("/recover")
	@ResponseBody
	public JSONObject recover(ConfigDbbackup configDbbackup, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		result.put("success", true);
		configDbbackup = configDbbackupService.getById(configDbbackup.getId());
		String filenameString = configDbbackup.getFilepath();
		try {
			File destFile = new File(filenameString);
			if(filenameString.isEmpty()||!destFile.exists()) {
				result.put("success", false);
				result.put("msg", "还原文件不存在");
				return result;
			}
			SystemCommandUtil.execCmd("/opt/lsblj/mariadb/bin/mysql -ulsblj -plsblj lsblj < "+filenameString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "还原失败");
		} 
		return result;
	}
	
	@RequestMapping("/download")
    public void download(ConfigDbbackup configDbbackup,HttpServletRequest request,HttpServletResponse response) {
		configDbbackup = configDbbackupService.getById(configDbbackup.getId());
		String filenameString = configDbbackup.getFilepath();
			
		try {
            ServletOutputStream out = response.getOutputStream();

        	if(filenameString==null||filenameString.equals("")) {
	    		out.write("<script>window.parent.$(\"#modal-danger .modal-title\").text('失败');window.parent.$(\"#modal-danger .modal-body\").text(\"文件不存在!\");window.parent.$(\"#modal-danger\").modal();</script>".getBytes());
				out.flush();
				out.close();
				return ;
        	}
    		response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.setHeader("content-disposition","attachment;fileName="+filenameString.substring(filenameString.lastIndexOf('/')+1));
			InputStream stream = new FileInputStream(filenameString);
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
	
}
