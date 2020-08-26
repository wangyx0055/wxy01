package com.longersec.blj.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 鐢ㄦ埛鎺у埗鍣�
 */
@Controller
public class IndexController {
	

    @RequestMapping("/index")
    public String index2() {
    	
        return "redirect:/view/index/index";
    }

    @RequestMapping("/view/index")
    public String index3() {
    	
        return "redirect:../view/index/index";
    }

    @RequestMapping("/view/index/index")
    public String index() {
        return "view/index";
    }

    @RequestMapping("/header")
    public String header() {
        return "view/common/header";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "view/common/menu";
    }

    @RequestMapping("/footer")
    public String footer() {
        return "view/common/footer";
    }

    @RequestMapping("/controlside")
    public String controlside() {
        return "view/common/controlside";
    }

    //审计管理
    @RequestMapping("/view/audit_manage/host_sessions")
    public String host_sessions(){
        return "view/audit_manage/host_sessions";
    }
    @RequestMapping("/view/audit_manage/apppub_sessions")
    public String apppub_sessions(){
        return "view/audit_manage/apppub_sessions";
    }
    @RequestMapping("/view/audit_manage/sessions_online")
    public String sessions_online(){
        return "view/audit_manage/sessions_online";
    }
    @RequestMapping("/view/audit_manage/logs_system")
    public String logs_system(){
        return "view/audit_manage/logs_system";
    }
    @RequestMapping("/view/audit_manage/log_manage")
    public String log_manage(){
        return "view/audit_manage/log_manage";
    }

    //用户管理
    @RequiresPermissions("user:user")
    @RequestMapping("/view/user/user")
    public String user(){
        return "view/user/user";
    }
    @RequiresPermissions("user:user_group")
    @RequestMapping("/view/user/user_group")
    public String user_group(){
        return "view/user/user_group";
    }
    @RequestMapping("/view/user/role_manage")
    public String role_manage(){
        return "view/user/role_manage";
    }

    //资产管理
    @RequestMapping("/view/asset_manage/apppub")
    public String apppub(){
        return "view/asset_manage/apppub";
    }
    @RequestMapping("/view/asset_manage/apppub_manage")
    public String appub_manage(){
        return "view/asset_manage/apppub_manage";
    }
    @RequestMapping("/view/asset_manage/device_group")
    public String device_group(){
        return "view/asset_manage/device_group";
    }
    @RequestMapping("/view/asset_manage/hosts")
    public String hosts(){
        return "view/asset_manage/hosts";
    };
    @RequestMapping("/view/asset_manage/system_Type")
    public String system_Type(){
        return "view/asset_manage/system_Type";
    }

    //策略管理
    @RequestMapping("/view/strategy_manage/access_control")
    public String access_control(){
        return "view/strategy_manage/access_control";
    }
    @RequestMapping("/view/strategy_manage/command_control")
    public String commoand_control(){
        return "view/strategy_manage/command_control";
    }

    //自动运维
    @RequestMapping("/view/auto_operation/operational_task")
    public String operational_task(){
        return "view/auto_operation/operational_task";
    }
    @RequestMapping("/view/auto_operation/rapid_operation")
    public String rapid_operation(){
        return "view/auto_operation/rapid_operation";
    }

    //统计分析
    @RequestMapping("/view/statistic_analysis/reports")
    public String reports(){
        return "view/statistic_analysis/reports";
    }
    @RequestMapping("/view/statistic_analysis/reports_login")
    public String reports_login(){
        return "view/statistic_analysis/reports_login";
    }
    @RequestMapping("/view/statistic_analysis/reports_sessions")
    public String reports_session(){
        return "view/statistic_analysis/reports_sessions";
    }

    //系统管理
    @RequestMapping("/view/system_manage/config_network")
    public String config_network(){
        return "view/system_manage/config_network";
    }
    @RequestMapping("/view/system_manage/config_system")
    public String config_system(){
        return "view/system_manage/config_system";
    }
    @RequestMapping("/view/system_manage/config_system_parameter")
    public String config_system_parameter(){
        return "view/system_manage/config_system_parameter";
    }

    //运维管理界面
    @RequestMapping("/view/operation_manage/app_operation")
    public String app_operation(){
        return "view/operation_manage/app_operation";
    }
    @RequestMapping("/view/operation_manage/host_operation")
    public String host_operation(){
        return "view/operation_manage/host_operation";
    }
    @RequestMapping("/view/operation_manage/self")
    public String self(){
        return "view/operation_manage/self";
    }

    //密码管理界面
    @RequestMapping("/view/password_manage/password_management")
    public String password_management(){
        return "view/password_manage/password_management";
    }
    @RequestMapping("/view/password_manage/password_modify_logs")
    public String password_modify_logs(){
        return "view/password_manage/password_modify_logs";
    }
    @RequestMapping("/view/password_manage/secret_change_strategy")
    public String secret_change_strategy(){
        return "view/password_manage/secret_change_strategy";
    }

    //消息中心
    @RequestMapping("/view/common/message")
    public String message(){ return "view/common/message"; }
    //部门
    @RequestMapping("/view/common/department")
    public String department(){
        return "view/common/department";
    }
    //部门
    @RequestMapping("/view/user/finger")
    public String finger(){
        return "view/user/finger";
    }
    //工单管理
    @RequestMapping("/view/work_manage/authorize")
    public String authorize(){
        return "/view/work_manage/authorize";
    }
    @RequestMapping("/view/work_manage/approve")
    public String approve(){ return "/view/work_manage/approve";}
}