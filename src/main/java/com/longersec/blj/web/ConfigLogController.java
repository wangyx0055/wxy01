package com.longersec.blj.web;

import com.longersec.blj.domain.ConfigLog;
import com.longersec.blj.domain.OperatorLog;
import com.longersec.blj.service.ConfigLogService;
import com.longersec.blj.service.OperatorLogService;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.Validator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/configLog")
public class ConfigLogController{

    @Autowired
    private OperatorLogService operatorLogService;

    @Autowired
    private ConfigLogService configLogService;

    @RequestMapping("/listConfigLog")
    @ResponseBody
    public JSONObject listConfigLog(ConfigLog configLog,
                                    HttpServletRequest request, HttpSession session) {
        int page_start = Integer.parseInt(request.getParameter("start"));
        int page_length = Integer.parseInt(request.getParameter("length"));
        ArrayList<Object> resultConfigLogs = new ArrayList<Object>();
        ArrayList<ConfigLog> configLogs = new ArrayList<ConfigLog>();
        long total = 0;
        int selectCount = configLogService.selectCount();
        resultConfigLogs = (ArrayList<Object>)configLogService.findAll(configLog, 0, selectCount);
        if(CollectionUtils.isNotEmpty(resultConfigLogs)) {
            configLogs = (ArrayList<ConfigLog>)resultConfigLogs.get(0);
            total = ((ArrayList<Long>) resultConfigLogs.get(1)).get(0);
        }
        JSONArray jsonArray = JSONArray.fromObject(configLogs);
        JSONObject result = new JSONObject();
        result.accumulate("success", true);
        result.accumulate("recordsTotal", total);
        result.accumulate("recordsFiltered", total);
        result.accumulate("data", jsonArray);
        return result;
    }

    @RequestMapping("/addConfigLog")
    @ResponseBody
    public JSONObject addConfigLog(@Validated ConfigLog configLog, BindingResult errorResult,
                                              @RequestParam(value = "id",required = false) Integer id,
                                              @RequestParam(value = "name") String name,
                                              @RequestParam(value = "log_manage") String log_manage,
                                              HttpServletRequest request, HttpSession session) {
        JSONObject result = new JSONObject();
        //错误处理
        Map<String, Object> errorMap = new HashMap<>(16);
        Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
        int checksum;
        //检查重名
        if (id == null) {
            checksum = configLogService.checkname(0, name);
        }else {
            checksum = configLogService.checkname(id, name);
        }
        if (checksum != 0) {
            errorMap.put("DuplicateName","链接名称重复");
        }
        //判断
        if(resultMap != null || !errorMap.isEmpty()) {
            result.put("errorMsg", resultMap);
            result.put("errorMap", errorMap);
            result.put("success","error");
            return result;
        }
        boolean r;
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("系统配置-扩展配置");
        if (id == null) {
            r = configLogService.addConfigLog(configLog);
            operatorLog.setDetails("添加链接"+"["+name+"]");
            operatorLog.setContent("添加");
        } else {
            r = configLogService.editConfigLog(configLog);
            operatorLog.setDetails("编辑链接"+"["+name+"]");
            operatorLog.setContent("编辑");
        }
        if (r) {
            operatorLog.setResult("成功");
        } else {
            operatorLog.setResult("失败");
        }
        operatorLogService.addOperatorLog(operatorLog);
        result.put("success", r);
        return result;
    }

    @RequestMapping("/editConfigLog")
    @ResponseBody
    public JSONObject editConfigLog(@Validated ConfigLog configLog,BindingResult errorResult,
                                    @RequestParam(value = "id",required = false) Integer id,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "log_manage") String log_manage,
                                         HttpServletRequest request, HttpSession session) {
        return addConfigLog(configLog,errorResult,id,name,log_manage,request,session);
    }

    @RequestMapping("/delConfigLog")
    @ResponseBody
    public JSONObject delConfigLog(@RequestParam(value = "ids[]") Integer[] ids, HttpServletRequest request, HttpSession session) {
        JSONObject result = new JSONObject();
        List<Integer> _ids =  Arrays.asList(ids);
        if(_ids.isEmpty()) {
            result.accumulate("success", false);
            result.accumulate("msg", "id不能为空");
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("系统配置-扩展配置");
        operatorLog.setDetails("删除链接"+"["+ids+"]");
        operatorLog.setContent("删除");
        Boolean r = configLogService.delConfigLog(_ids);
        result.put("success", r);
        if (r) {
            operatorLog.setResult("成功");
        } else {
            operatorLog.setResult("失败");
        }
        operatorLogService.addOperatorLog(operatorLog);
        return result;
    }

    @RequestMapping("/checkname")
    @ResponseBody
    public JSONObject checkname(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "name") String name) {
        JSONObject result = new JSONObject();
        int checksum;
        if (id == null ) {
            checksum = configLogService.checkname(0,name);
        } else {
            checksum = configLogService.checkname(id,name);
        }
        result.put("success", checksum == 0);
        return result;
    }
    @RequestMapping("/checksort")
    @ResponseBody
    public JSONObject checksort(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "sort") Integer sort) {
        JSONObject result = new JSONObject();
        int checksum;
        if (id == null ) {
            checksum = configLogService.checksort(0,sort);
        } else {
            checksum = configLogService.checksort(id, sort);
        }
        result.put("success", checksum == 0);
        return result;
    }
}

