package com.longersec.blj.web;

import com.longersec.blj.dao.ProtocolDao;
import com.longersec.blj.dao.RoleDao;
import com.longersec.blj.domain.*;
import com.longersec.blj.license.License;
import com.longersec.blj.service.*;
import com.longersec.blj.utils.EncodeUtils;
import com.longersec.blj.utils.MultipartFileToFile;
import com.longersec.blj.utils.Operator_log;
import com.longersec.blj.utils.UpdateDepartmentCount;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ProtocolDao protocolDao;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceAccountService deviceAccountService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ApppubServerService apppubserverService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private OperatorLogService operatorLogService;
    @Autowired
    private ApppubAccountService apppubAccountService;
    @Autowired
    private ApppubProgramService apppubProgramService;

    @RequestMapping("/group")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam("file_data")MultipartFile fileUp, HttpServletRequest request, HttpSession session, @RequestParam("type") Integer type, @RequestParam("udepartment") Integer udepartment) throws Exception {
        JSONObject result = new JSONObject();
        ArrayList<String> errorInfo = new ArrayList<String>(10);
        result.put("success", true);
        if(fileUp==null || fileUp.isEmpty()){
            result.put("errorInfo", "文件不能为空");
            result.put("success", false);
            return result;
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule(type==0?"用户分组":"设备分组");
        operatorLog.setContent("导入");
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            List<String> list = importCsv(file);
            int length =0;
            for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1," "," ");
                Group group = new Group();
                group.setName(temp[0]);
                group.setType(type);
                group.setDesc(temp[1]);
                group.setDepartment(udepartment);
                if ("".equals(group.getName())) {
                    result.put("errorInfo", group.getName()+"组名称不能为空");
                    operatorLog.setDetails(type==0?group.getName()+"组名称不能为空":group.getName()+"组名称不能为空");
                    operatorLog.setResult("失败");
                    operatorLogService.addOperatorLog(operatorLog);
                    continue;
                }
                if (group.getDesc().length()>128) {
                    result.put("errorInfo", group.getName()+"描述超过128字符");
                    operatorLog.setDetails(type==0?group.getName()+"用户组描述超过128字符":group.getName()+"设备组描述超过128字符");
                    operatorLog.setResult("失败");
                    operatorLogService.addOperatorLog(operatorLog);
                    continue;
                }
                Group checkname = groupService.checkname(group);
                boolean b;
                if (checkname != null) {
                    group.setId(checkname.getId());
                    b = groupService.editGroupList(group);
                    operatorLog.setDetails(type==0?"更新用户组["+group.getName()+"]":"更新设备组["+group.getName()+"]");
                } else {
                    b = groupService.insertMore(group);
                    operatorLog.setDetails(type==0?"导入用户组["+group.getName()+"]":"导入设备组["+group.getName()+"]");
                }
                if(b) {
                    length++;
                    operatorLog.setResult("成功");
                } else{
                    operatorLog.setResult("失败");
                }
                operatorLogService.addOperatorLog(operatorLog);
            }
            //判断是导入成功
            if (list.size() - length == 1) {
                result.put("msg", "导入成功");
            } else if(length > 0) {
                result.put("msg", "部分成功");
            } else {
                result.put("success", false);
            }
        }catch (Exception e){
            result.put("success", false);
        }
        result.put("errorInfo", errorInfo);
        return result;
    }


    @RequestMapping("/user")
    @ResponseBody
    public JSONObject fileUploaduser(@RequestParam("file_data")MultipartFile fileUp, HttpServletRequest request, HttpSession session) throws Exception{
        JSONObject result = new JSONObject();
        ArrayList<String> errorInfo = new ArrayList<String>(10);
        result.put("success", true);
        if (fileUp == null || fileUp.isEmpty()) {
            result.put("errorInfo", "文件不能为空");
            result.put("success", false);
            return result;
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("用户列表");
        operatorLog.setContent("导入");
        try {
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            List<String> list = importCsv(file);
            int length = 0;
            for (int i = 1; i < list.size(); i++) {
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1, " ");
                User user = new User();
                //检查角色
                Role role = roleDao.checkname(temp[3]);
                if(role == null) {
                    errorInfo.add(temp[0]+":角色不存在");
                    operatorLog.setDetails("导入用户["+temp[0]+"]");
                    operatorLog.setResult("失败");
                } else {
                    String parent_id = "";
                    String parent = temp[2];
                    if (Pattern.matches("^.+\\{\\{\\d+}}$", parent)) {
                        String[] split =  temp[2].split("\\{\\{");
                        parent = split[0];
                        parent_id = split[1].split("}}")[0];
                    }
                    //检查部门
                    Map<String, Object> checkDepartmentExport = DepartmentController.checkDepartmentExport(departmentService, parent, parent_id);
                    Map<String, Object> checkUserExport = UserController.checkUserExport(userService, temp[0], temp[1], temp[4], temp[5], temp[6], temp[7], temp[8]);
                    //检查用户
                    if (checkDepartmentExport.get("success").equals(true) && checkUserExport.get("success").equals(true)) {
                        if ("".equals(parent_id)) {
                            Department department1 = departmentService.selectByname(parent);
                            user.setDepartment(department1.getId());
                        } else {
                            user.setDepartment(Integer.parseInt(parent_id));
                        }
                        user.setRole_id(role.getId());
                        user.setUsername(temp[0]);
                        user.setRealname(temp[1]);
                        user.setPassword(temp[4]);
                        user.setEmail(temp[5]);
                        user.setQq(temp[6]);
                        user.setWechat(temp[7]);
                        user.setMobile(temp[8]);
                        User p_user = (User) SecurityUtils.getSubject().getPrincipal();
                        user.setCreator_id(p_user.getId());
                        User isexitU = userService.checkLogin(user.getUsername());
                        boolean b;
                        if (isexitU != null) {
                            user.setId(isexitU.getId());
                            b = userService.editUserList(user);
                            operatorLog.setDetails("更新用户["+user.getUsername()+"]");
                        } else {
                            b = userService.insertMore(user);
                            operatorLog.setDetails("导入用户["+user.getUsername()+"]");
                        }
                        if (b) {
                            //计算成功数量
                            length++;
                            operatorLog.setResult("成功");
                        } else{
                            operatorLog.setResult("失败");
                        }
                    } else if(!checkDepartmentExport.get("success").equals(true)){
                        errorInfo.add(checkDepartmentExport.get("info") + "");
                        operatorLog.setDetails(checkDepartmentExport.get("info") + "");
                    } else if(!checkUserExport.get("success").equals(true)) {
                        errorInfo.add(checkUserExport.get("info") + "");
                        operatorLog.setDetails(checkUserExport.get("info") + "");
                    }
                }
                operatorLogService.addOperatorLog(operatorLog);
            }
            //判断是导入成功
            if (list.size() - length == 1) {
                result.put("msg", "导入成功");
            } else if(length > 0) {
                result.put("msg", "部分成功");
            } else {
                result.put("success", false);
            }
            }catch(Exception e){
            	e.printStackTrace();
                result.put("success", false);
            }
        UpdateDepartmentCount.AutoUpdateDepartmentUserCounts(departmentService);
        result.put("errorInfo", errorInfo);
        return result;
    }

    @RequestMapping("/department")
    @ResponseBody
    public JSONObject fileUploadDepartment(@RequestParam("file_data")MultipartFile fileUp, HttpServletRequest request, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();
        ArrayList<String> errorInfo = new ArrayList<String>(10);
        result.put("success", true);
        if(fileUp==null || fileUp.isEmpty()){
            result.put("errorInfo", "文件不能为空");
            result.put("success", false);
            return result;
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("部门管理");
        operatorLog.setContent("导入");
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            List<String> list = importCsv(file);
            ArrayList<Department> listDepartment  = new ArrayList<>();
            for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1," "," ");
                Department department = new Department();
                department.setName(temp[0]);
                department.setDescription(temp[1]);
                if(temp1.length == 2){
                    department.setParent_name("");
                } else {
                    department.setParent_name(temp[2]);
                }
                listDepartment.add(department);
            }
            //表格条数
            int length = listDepartment.size();
            for (int i = 0; i < listDepartment.size(); i++) {
                Department department1 = listDepartment.get(i);
                operatorLog.setDetails("导入部门["+department1.getName()+"],上级部门为:["+department1.getParent_name()+"]");
                Map<String,Object> checkExport = DepartmentController.checkExport(departmentService,department1.getName(), department1.getDescription(), department1.getParent_name());
                if (checkExport.get("success").equals(true)){
                    if ("".equals(department1.getParent_name())){
                        department1.setParent_id(1);
                    }else {
                        Department department2 = departmentService.selectByname(department1.getParent_name());
                        department1.setParent_id(department2.getId());
                    }
                    User p_user = (User) SecurityUtils.getSubject().getPrincipal();
                    department1.setCreate_id(p_user.getId());
                    department1.setCreate_time((int) System.currentTimeMillis());
                    departmentService.insertMore(department1);
                    operatorLog.setResult("成功");
                } else {
                    length--;
                    operatorLog.setResult("失败");
                    errorInfo.add(checkExport.get("info")+"");
                }
                operatorLogService.addOperatorLog(operatorLog);
            }
            if (list.size()-length==1 && length>0){
                result.put("msg", "导入成功");
            }else if(length>0 && list.size()-length>1){
                result.put("msg", "部分成功");
            }else {
                result.put("success", false);
            }
        }catch (Exception e){
            result.put("success", false);
        }
        result.put("errorInfo",errorInfo);
        return result;
    }

    @RequestMapping("/device")
    @ResponseBody
    public JSONObject fileUploaddevice(@RequestParam("file_data")MultipartFile fileUp, HttpServletRequest request, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();
        ArrayList<String> errorInfo = new ArrayList<String>(10);
        result.put("success", true);
        if(fileUp==null || fileUp.isEmpty()){
            result.put("errorInfo", "文件不能为空");
            result.put("success", false);
            return result;
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("设备列表");
        operatorLog.setContent("导入");
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            List<String> list = importCsv(file);
            int length = 0;

    		License l = new License();
        	boolean hasLicense = l.LicenseCheckUuid("");
        	long licensecount = l.LicenseGetDevices();
        	
            for (int i = 1; i < list.size(); i++) {
            	long total = deviceService.total();
        		if(!hasLicense&&total>=3000) {
        			result.put("success",false);
        			result.put("errorInfo","设备数超过限制");
        			return result;
        		}else if(hasLicense&&total>=licensecount) {
        			result.put("success",false);
        			result.put("errorInfo","设备数超过许可限制");
                    return result;
        		}
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1, " ", " ");
                Device device = new Device();
                String parent_id = "";
                String parent = temp[3];
                if (Pattern.matches("^.+\\{\\{\\d+}}$", parent)) {
                    String[] split =  temp[3].split("\\{\\{");
                    parent = split[0];
                    parent_id = split[1].split("}}")[0];
                }
                Map<String, Object> checkDeviceExport = DeviceController.checkDeviceExport(deviceService,deviceTypeService, protocolDao,temp[0], temp[1], temp[2], temp[4], temp[5], temp[6], temp[7], Integer.parseInt(temp[8]), Integer.parseInt(temp[9]));
                Map<String, Object> checkDepartmentExport = DepartmentController.checkDepartmentExport(departmentService, parent, parent_id);
                if (checkDeviceExport.get("success").equals(true) && checkDepartmentExport.get("success").equals(true)) {
                    if ("".equals(parent_id)) {
                        Department department1 = departmentService.selectByname(parent);
                        device.setDepartment(department1.getId());
                    } else {
                        device.setDepartment(Integer.parseInt(parent_id));
                    }
                    Protocol byName = protocolDao.getByName(temp[7]);
                    device.setName(temp[0]);
                    device.setIp(temp[1]);
                    device.setOs_type((Integer) checkDeviceExport.get("ostype"));
                    device.setDescription(temp[4]);
                    if ("".equals(temp[5]) || "".equals(temp[6])) {
                        device.setLogin_method(1);
                        device.setSuper_account(temp[5]);
                        device.setSuper_password(temp[6]);
                    } else {
                        device.setLogin_method(0);
                        device.setSuper_account(temp[5]);
                        device.setSuper_password(temp[6]);
                    }
                    device.setProtocol_id(byName.getId());
                    device.setPort(Integer.parseInt(temp[8]));
                    device.setSsh_key(Integer.parseInt(temp[9]));
                    User p_user = (User) SecurityUtils.getSubject().getPrincipal();
                    device.setCreator_id(p_user.getId());
                    DeviceAccount deviceAccount = new DeviceAccount();
                    deviceAccount.setUsername(device.getSuper_account());
                    deviceAccount.setPassword(device.getSuper_password());
                    deviceAccount.setPort(device.getPort());
                    deviceAccount.setProtocol_id(device.getProtocol_id());
                    deviceAccount.setSsh_key(device.getSsh_key());
                    deviceAccount.setLogin_method(device.getLogin_method());
                    boolean b;
                    //检查设备重名
                    Device checkName = deviceService.checkname(device.getName());
                    if (checkName != null) {
                        //更新设备
                        device.setId(checkName.getId());
                        deviceService.editDevice(device);
                        //检查设备账号重名
                        DeviceAccount deviceAccount1 = deviceAccountService.checkaccountById(device.getId(),device.getSuper_account());
                        if (deviceAccount1 != null) {
                            deviceAccount.setDevice_id(deviceAccount1.getDevice_id());
                            deviceAccount.setId(deviceAccount1.getId());
                            b = deviceAccountService.editDeviceAccount(deviceAccount);
                            operatorLog.setDetails("更新设备["+device.getName()+"]和更新设备账号["+device.getSuper_account()+"]");
                            if (b) {
                                length++;
                                operatorLog.setResult("成功");
                            } else{
                                operatorLog.setResult("失败");
                            }
                        } else {
                            deviceAccount.setDevice_id(checkName.getId());
                            b = deviceAccountService.addDeviceAccount(deviceAccount);
                            operatorLog.setDetails("更新设备["+device.getName()+"]和新建设备账号["+device.getSuper_account()+"]");
                            if (b) {
                                length++;
                                operatorLog.setResult("成功");
                            } else{
                                operatorLog.setResult("失败");
                            }
                        }
                    }else {
                        b = deviceService.addDevice(device);
                        operatorLog.setDetails("导入设备["+device.getName()+"]和设备账号["+device.getSuper_account()+"]");
                        if (b) {
                            length++;
                            operatorLog.setResult("成功");
                        } else{
                            operatorLog.setResult("失败");
                        }
                    }
                } else if(!checkDepartmentExport.get("success").equals(true)){
                    errorInfo.add(checkDepartmentExport.get("info") + "");
                    operatorLog.setDetails(checkDepartmentExport.get("info")+"");
                } else if(!checkDeviceExport.get("success").equals(true)) {
                    errorInfo.add(checkDeviceExport.get("info") + "");
                    operatorLog.setDetails(checkDeviceExport.get("info")+"");
                }
                operatorLogService.addOperatorLog(operatorLog);
            }
            //判断是导入成功
            if (list.size() - length == 1) {
                result.put("msg", "导入成功");
            } else if(length > 0) {
                result.put("msg", "部分成功");
            } else {
                result.put("success", false);
            }
           // UpdateDepartmentCount.AutoUpdateDepartmentDeviceCounts(departmentService);
        }catch (Exception e){
            result.put("success", false);
        }
        result.put("errorInfo",errorInfo);
        return result;
    }

    @RequestMapping("/apppubserver")
    @ResponseBody
    public JSONObject fileUploadApppubserver(@RequestParam("file_data")MultipartFile fileUp, @RequestParam("type") Integer type) throws Exception {
        JSONObject result = new JSONObject();
        result.put("success", true);
        if(fileUp==null || fileUp.isEmpty()){
            result.put("success", false);
        }
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            System.out.println(file);
            List<String> list = importCsv(file);
            ArrayList<ApppubServer> listApppubservers  = new ArrayList<>();
            ArrayList<ApppubServer> UpdatelistApppubservers = new ArrayList<>();
            for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1," "," ");
                ApppubServer apppubServer = new ApppubServer();
                ApppubServer _apppubServer = apppubserverService.checkip(apppubServer.getIp(),0);      //判断重名
                apppubServer.setName(temp[0]);   //部门名
                apppubServer.setIp(temp[1]);
                apppubServer.setPort(Integer.valueOf(temp[2]));
                apppubServer.setAccount(temp[3]);
                apppubServer.setPassword(temp[4]);
                apppubServer.setDesc(temp[5]);   //描述
                if (_apppubServer==null){
                	listApppubservers.add(apppubServer);
                }else{
                	apppubServer.setId(_apppubServer.getId());
                	UpdatelistApppubservers.add(apppubServer);
                }
            }
            if (result.getBoolean("success")){
                boolean b = true;
                boolean u = true;
                if (listApppubservers.size()!=0|| UpdatelistApppubservers.size()!=0){
                    if (listApppubservers.size()!=0){
                        b = apppubserverService.insertMore(listApppubservers);
                    }else if (UpdatelistApppubservers.size()!=0){
                        u = apppubserverService.editApppubServerList(UpdatelistApppubservers);
                    }
                    if (b && u){
                        result.put("success", true);
                    }else {
                        result.put("success", false);
                    }
                    int length = listApppubservers.size()+UpdatelistApppubservers.size();  //表格条数
                    if (list.size()-length==1){
                        result.put("msg", "导入成功");
                    }else {
                        result.put("msg", "部分成功");
                    }
                }else {
                    result.put("success", false);
                }
            }
        }catch (Exception e){
            result.put("success", false);
        }
        return result;
    }


    @RequestMapping("/apppubAccount")
    @ResponseBody
    public JSONObject fileUploadApppubAccount(@RequestParam("file_data")MultipartFile fileUp, @RequestParam("type") Integer type,
                                              HttpServletRequest request, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();
        ArrayList<String> errorInfo = new ArrayList<String>(10);
        result.put("success", true);
        int t = type;
        if(fileUp==null || fileUp.isEmpty()){
            result.put("success", false);
            result.put("errorInfo", "文件不能为空");
            return result;
        }
        //操作日志
        OperatorLog operatorLog = Operator_log.log(request, session);
        operatorLog.setModule("发布应用");
        operatorLog.setContent("导入");
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            System.out.println(file);
            List<String> list = importCsv(file);
            ArrayList<ApppubAccount> listApppubAccount   = new ArrayList<>();
            ArrayList<ApppubAccount> UpdatelistApppubAccount  = new ArrayList<>();
            int length = 0;
            for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",",-1);
                String[] temp = insert(temp1," "," ");
                ApppubAccount apppubAccount = new ApppubAccount();

                String parent_id = "";
                String parent = temp[1];
                if (Pattern.matches("^.+\\{\\{\\d+}}$", parent)) {
                    String[] split =  temp[1].split("\\{\\{");
                    parent = split[0];
                    parent_id = split[1].split("}}")[0];
                }
                //检查部门
                Map<String, Object> checkDepartmentExport = DepartmentController.checkDepartmentExport(departmentService, parent, parent_id);
                if(checkDepartmentExport.get("success").equals(true)){
                    if("".equals(parent_id)){
                        Department department = departmentService.selectByname(parent);
                        apppubAccount.setDepartment(department.getId());
                    }else {
                        apppubAccount.setDepartment(Integer.parseInt(parent_id));
                    }
                    ApppubServer apppubServer = apppubserverService.getApppubServerByName(temp[0]);
                    ApppubAccount _apppubAccount = apppubAccountService.getApppubAccountByName(temp[2]);
                    ApppubProgram apppubProgram = apppubProgramService.getApppubProgramByName(temp[3]);

                    if(apppubServer == null ){
                        errorInfo.add(temp[0]+"---"+"服务器名称不存在/");
                        continue;
                    }
                    if(apppubProgram == null ){
                        errorInfo.add(temp[2]+"---"+"应用程序不存在/");
                        continue;
                    }
                    if(_apppubAccount !=null){
                        apppubAccount.setId(_apppubAccount.getId());
                        apppubAccount.setApppub_server_id(apppubServer.getId());
                        apppubAccount.setApppub_program_id(apppubProgram.getId());
                        apppubAccount.setUsername(temp[4]);
                        apppubAccount.setPassword(temp[5]);
                        apppubAccount.setUrl(temp[6]);
                        apppubAccount.setDesc(temp[7]);
                        boolean a = apppubAccountService.editApppubAccount(apppubAccount);
                        operatorLog.setDetails("更新发布应用["+apppubAccount.getName()+"]");
                        if (a) {
                            //计算成功数量
                            length++;
                            operatorLog.setResult("成功");
                        } else{
                            operatorLog.setResult("失败");
                        }
                    }else {
                        apppubAccount.setApppub_server_id(apppubServer.getId());
                        apppubAccount.setName(temp[2]);
                        apppubAccount.setApppub_program_id(apppubProgram.getId());
                        apppubAccount.setUsername(temp[4]);
                        apppubAccount.setPassword(temp[5]);
                        apppubAccount.setUrl(temp[6]);
                        apppubAccount.setDesc(temp[7]);
                        boolean b = apppubAccountService.insertMore(apppubAccount);
                        operatorLog.setDetails("导入发布应用["+apppubAccount.getName()+"]");
                        if (b) {
                            //计算成功数量
                            length++;
                            operatorLog.setResult("成功");
                        } else{
                            operatorLog.setResult("失败");
                        }
                    }
                }else if(!checkDepartmentExport.get("success").equals(true)){
                    errorInfo.add(checkDepartmentExport.get("info") + "");
                    operatorLog.setDetails(checkDepartmentExport.get("info") + "");
                }
                operatorLogService.addOperatorLog(operatorLog);
            }
            //判断是导入成功
            if (list.size() - length == 1) {
                result.put("msg", "导入成功!");
            } else if(length > 0) {
                result.put("msg", "部分成功!");
            } else {
                result.put("success", false);
            }
        }catch (Exception e){
            result.put("success", false);
        }
        result.put("errorInfo", errorInfo);
        return result;
    }



    // 往字符串数组追加新数据
    private static String[] insert(String[] arr, String... str) {
        int size = arr.length; // 获取原数组长度
        int newSize = size + str.length; // 原数组长度加上追加的数据的总长度

        // 新建临时字符串数组
        String[] tmp = new String[newSize];
        // 先遍历将原来的字符串数组数据添加到临时字符串数组
        System.arraycopy(arr, 0, tmp, 0, size);
        // 在末尾添加上需要追加的数据
        if (newSize - size >= 0) {
            System.arraycopy(str, 0, tmp, size, newSize - size);
        }
        return tmp; // 返回拼接完成的字符串数组
    }

    public static List<String> importCsv(File file) throws IOException {
        InputStream ins= new FileInputStream(file);
        List<String> data = new ArrayList<String>();
        BufferedReader br = null;
        InputStreamReader isr = null;
        byte[] b = new byte[3];
        ins.read(b);
        ins.close();
        try {
	        String encodeType = EncodeUtils.getEncode(file.getAbsolutePath(), true);
	        if ("UTF-8".equals(encodeType)){
		        isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
	        }else{
		        isr = new InputStreamReader(new FileInputStream(file),"GBK");
	        }
            br = new BufferedReader(isr);
            String line = "";
            while((line = br.readLine()) != null){
                data.add(line);
            }
        } catch (Exception e) {
            System.out.println("CSVUtils.importCsv error:"+e);
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("CSVUtils.importCsv close BufferedReader error:"+e);
            }
        }
        return data;
    }
}
