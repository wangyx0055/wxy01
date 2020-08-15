package com.longersec.blj.web;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.longersec.blj.domain.ConfigSms;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.ConfigSmsService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.KeyUtil;

import net.sf.json.JSONObject;

import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/send")
@Controller
public class SendSmsController {
	@Autowired
	ConfigSmsService configSmsService;
	@Autowired
	UserService userService;

    

    @RequestMapping("/sendSms")
    @ResponseBody
    public JSONObject sendSms(@Param("username")String username,HttpServletRequest request, HttpSession session) throws ClientException {
    	ConfigSms configSms = new ConfigSms();
    	List<Object> confiList = configSmsService.findAll(configSms, 0, 1);
    	configSms = ((List<ConfigSms>)confiList.get(0)).get(0);
    	String accessKeyId = configSms.getAccess_key_id();
        String accessKeySecret = configSms.getAccess_key_secret();
        String SignName = configSms.getSign_name();
        String TemplateCode = configSms.getTemplate_code();
        String product = "Dysmsapi";
        // 产品域名,开发者无需替换
        String domain = "dysmsapi.aliyuncs.com";
        User user = userService.checkLogin(username);
        JSONObject result = new JSONObject();
        
        if(user.getMobile().equals("")) {
        	result.put("success",false);
            result.put("msg","未绑定手机号码，请联系管理员");
            return result;
        }
        String mobile = user.getMobile();
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",product,domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest smsrequest = new SendSmsRequest();
        // 必填:待发送手机号
        smsrequest.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        smsrequest.setSignName(SignName); // TODO 改这里
        // 必填:短信模板-可在短信控制台中找到
        smsrequest.setTemplateCode(TemplateCode);  // TODO 改这里
        String code =KeyUtil.getDigitalCode(4);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        smsrequest.setTemplateParam("{\"content\":\"" + code + "\"}");
        System.out.println("短信code:"+code);
        session.setAttribute("login_smscode", code);
        user.setSms_code(code);
        userService.editUser(user);
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(smsrequest);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
            result.put("success",true);
            result.put("msg","短信发送成功！");
        }else {
            System.out.println("短信发送失败！");
            result.put("success",false);
            result.put("msg","短信发送失败！");
        }
        return result;
    }
}
