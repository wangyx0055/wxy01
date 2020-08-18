package com.longersec.blj.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.longersec.blj.domain.*;
import com.longersec.blj.utils.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.longersec.blj.service.ApppubProgramService;
import com.longersec.blj.service.ApppubServerService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author wxy
 */
@Controller
@RequestMapping(value = "/apppubProgram")
public class ApppubProgramController {

	@Autowired
	private ApppubProgramService apppubProgramService;
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private ApppubServerService apppubServerservice;
	
	@RequestMapping("/listApppubProgram")
	@ResponseBody
	public JSONObject listApppubProgram(ApppubProgram apppubProgram,
										@RequestParam(value = "sname",required = false,defaultValue = "") String sname,
										@RequestParam(value = "type",required = false) Integer type,
										HttpServletRequest request, HttpSession session) {
		if (sname == null || "".equals(sname)) {
			sname = "";
		}
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubPrograms = new ArrayList<Object>();
		ArrayList<ApppubProgram> apppubPrograms = new ArrayList<ApppubProgram>();
		long total = 0;
		resultApppubPrograms = (ArrayList<Object>)apppubProgramService.findAll(apppubProgram,sname,type, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubPrograms)) {
			apppubPrograms = (ArrayList<ApppubProgram>)resultApppubPrograms.get(0);
			total = ((ArrayList<Long>) resultApppubPrograms.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubPrograms);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addApppubProgram")
	@ResponseBody
	public JSONObject addApppubProgram(@RequestParam(value = "file", required = false) MultipartFile file, @Validated ApppubProgram apppubProgram,BindingResult errorResult, HttpServletRequest request, HttpSession session) throws IOException {
		return this.editApppubProgram(file,apppubProgram,errorResult,request,session);
	}

	@RequestMapping("/editApppubProgram")
	@ResponseBody
	public JSONObject editApppubProgram(@RequestParam(value = "file", required = false)MultipartFile file, @Validated ApppubProgram apppubProgram, BindingResult errorResult, HttpServletRequest request, HttpSession session) throws IOException {
		//使用UUID给图片重命名，并去掉四个“-”
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		if(file!=null) {
			//获取文件的扩展名
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			// 获取文件存放地址
			String filePath = request.getSession().getServletContext().getRealPath("/uploadFile/");
			File f = new File(filePath);
			if (!f.exists()) {
				f.mkdirs();// 不存在路径则进行创建
			}
			name = name+ "." + ext;
			//以绝对路径保存重名命后的图片
			file.transferTo(new File(filePath+name ));
			apppubProgram.setIcon(name);
		}else {
			apppubProgram.setIcon(apppubProgram.getIcon());
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		Boolean r = false;
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("应用程序");
		Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
		if (apppubProgram.getId() == null){
			operatorLog.setDetails("增加应用程序");
			operatorLog.setContent("添加");
			if(resultMap == null){
				r = apppubProgramService.addApppubProgram(apppubProgram);
				if (r){
					operatorLog.setResult("成功");
					result.put("success", true);
				}else{
					operatorLog.setResult("失败");
					result.put("success", false);
				}
			}else{
				result.put("success", false);
			}
		}else{
			operatorLog.setDetails("编辑应用程序");
			operatorLog.setContent("编辑");
			if(resultMap == null){
				r = apppubProgramService.editApppubProgram(apppubProgram);
				if (r){
					operatorLog.setResult("成功");
					result.put("success", true);
				}else{
					operatorLog.setResult("失败");
					result.put("success", false);
				}
			}else{
				result.put("success", false);
			}
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}
	
	
	 @RequestMapping("checkAppName")
		@ResponseBody
		public JSONObject checkAppName(@RequestParam(value = "id",required = false)Integer id,@RequestParam("name")String name){
			JSONObject result = new JSONObject();
			result.put("success", true);
			if (id==null){
				result.put("success", false);
			}
			if (!result.getBoolean("success")){
			ApppubProgram isName = apppubProgramService.checkAppName(name);
			if (isName==null){
				result.put("success",true);
			}else {
				result.put("success",false);
			}
			}else {
		    ApppubProgram isName = apppubProgramService.checkAppName(name);
				if (isName==null){
					result.put("success", true);
				}else {
					if (isName.getId().equals(id)){
						result.put("success", true);
					}else {
						result.put("success", false);
					}
				}
			}

			return result;
		}

	@RequestMapping("/delApppubProgram")
	@ResponseBody
	public JSONObject delApppubProgram(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
        //操作日志
		OperatorLog operatorLog =Operator_log.log(request, session);
		operatorLog.setModule("应用程序");
		operatorLog.setDetails("删除应用程序");
		operatorLog.setContent("删除");
		//是否操作成功
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			operatorLog.setResult("成功");
			Boolean r = apppubProgramService.delApppubProgram(_ids);
			result.accumulate("success", r);
		}else {
			operatorLog.setResult("失败");
		}
		operatorLogService.addOperatorLog(operatorLog);
		return result;
	}

	@RequestMapping("/queryApppubProgramById")
	@ResponseBody
	public JSONObject queryApppubProgramById(@RequestParam(value = "apppub_server_id") Integer apppub_server_id,ApppubProgram apppubProgram,
											 HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultApppubPrograms = new ArrayList<Object>();
		ArrayList<ApppubProgram> apppubPrograms  = new ArrayList<ApppubProgram>();
		long total = 0;
		resultApppubPrograms = (ArrayList<Object>)apppubProgramService.queryApppubProgramById(apppub_server_id,apppubProgram,page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultApppubPrograms)) {
			apppubPrograms = (ArrayList<ApppubProgram>)resultApppubPrograms.get(0);
			total = ((ArrayList<Long>) resultApppubPrograms.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(apppubPrograms);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/fetchApps")
	@ResponseBody
	public JSONObject fetchApps(ApppubServer apppubServer, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		apppubServer = apppubServerservice.getById(apppubServer.getId());
		System.out.println("http://"+apppubServer.getIp()+":20616/getallapps.php");
		String res = com.longersec.blj.utils.httpClient.doGetResStr("http://"+apppubServer.getIp()+":20616/getallapps.php");
		System.out.println(res);
		try {
			byte[] _res = res.getBytes("UTF-8");
			for(int i=0; i<_res.length; i++) {
				if(_res[i]+256==0xEF||_res[i]+256==0xBB||_res[i]+256==0xBF) {
					_res[i]=' ';
				}
			}
			res = new String(_res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(res);
		JSONArray apps = JSONArray.fromObject(res.substring(res.indexOf("[{")));

		System.out.println(apps);
		for(int i=0; i<apps.size(); i++) {
			JSONObject app = apps.getJSONObject(i);
			ApppubProgram apppubProgram = new ApppubProgram();
			if(apppubProgramService.checkAppName(app.getString("name"))==null) {
				apppubProgram.setApppub_server_id(apppubServer.getId());
				apppubProgram.setName(app.getString("name"));
				apppubProgram.setPath(app.getString("alias"));
				apppubProgramService.addApppubProgram(apppubProgram);
			}
		}
		System.out.println(apps);
		return result;
	}

}
