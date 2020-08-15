package com.longersec.blj.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.longersec.blj.domain.DeviceRecord;
import com.longersec.blj.domain.RecordCommand;
import com.longersec.blj.service.RecordCommandService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 */
@Controller
@RequestMapping(value = "/recordCommand")
public class RecordCommandController {

	@Autowired
	private RecordCommandService recordCommandService;

	@RequestMapping("/listRecordCommand")
	@ResponseBody
	public JSONObject listRecordCommand(RecordCommand recordCommand,HttpServletRequest request, HttpSession session) {
		int page_start = Integer.parseInt(request.getParameter("start"));
		int page_length = Integer.parseInt(request.getParameter("length"));
		ArrayList<Object> resultRecordCommands = new ArrayList<Object>();
		ArrayList<RecordCommand> recordCommands = new ArrayList<RecordCommand>();
		long total = 0;
		resultRecordCommands = (ArrayList<Object>)recordCommandService.findAll(recordCommand, page_start, page_length);
		if(CollectionUtils.isNotEmpty(resultRecordCommands)) {
			recordCommands = (ArrayList<RecordCommand>)resultRecordCommands.get(0);
			total = ((ArrayList<Long>) resultRecordCommands.get(1)).get(0);
		}
		JSONArray jsonArray = JSONArray.fromObject(recordCommands);
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		result.accumulate("recordsTotal", total);
		result.accumulate("recordsFiltered", total);
		result.accumulate("data", jsonArray);
		return result;
	}

	@RequestMapping("/addRecordCommand")
	@ResponseBody
	public JSONObject addRecordCommand(RecordCommand recordCommand, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordCommandService.addRecordCommand(recordCommand);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/editRecordCommand")
	@ResponseBody
	public JSONObject editRecordCommand(RecordCommand recordCommand, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		result.accumulate("success", true);
		if(result.getBoolean("success")) {
			Boolean r = recordCommandService.editRecordCommand(recordCommand);
			result.accumulate("success", r?true:false);
		}
		return result;
	}

	@RequestMapping("/delRecordCommand")
	@ResponseBody
	public JSONObject delRecordCommand(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
		JSONObject result = new JSONObject();
		List<Integer> _ids =  Arrays.asList(ids);
		result.accumulate("success", true);
		if(_ids.isEmpty()) {
			result.accumulate("success", false);
			result.accumulate("msg", "id不能为空");
		}
		if(result.getBoolean("success")) {
			Boolean r = recordCommandService.delRecordCommand(_ids);
			result.accumulate("success", r);
		}
		return result;
	}
	
	@RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response, HttpSession session, Integer id) throws Exception {
		RecordCommand recordCommand = recordCommandService.getById(id);
		File file = new File(recordCommand.getFilepath());
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-disposition","attachment;fileName="+file.getName());
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
}
