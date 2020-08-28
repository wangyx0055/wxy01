<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>设备列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/Ionicons/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  <!-- Google Font -->
  <link rel="stylesheet"
        href="../../bower_components/treeview/font.css">
<style>
  .one{
    float: right;
    height: 20px;
    width: 250px;
    background-color: #d3d3d3;
  }
  
label{
display: inline-block;
padding: 3px 6px;
text-align: right;
width: 150px;
vertical-align: top;
}
.input{
   margin:0;padding: 0;
   vertical-align: -3px;
   margin-bottom: -2px; 
  }

</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
  
<div class="wrapper">
<header class="main-header">
   <jsp:include page="/index/header"></jsp:include>
  </header>
   <aside class="main-sidebar">
   <jsp:include page="/index/menu"></jsp:include>
    <!-- /.sidebar -->
  </aside>
  
  <!-- Left side column. contains the logo and sidebar -->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->

   <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="box-shadow: none;border-top: none">
            <div class="box-header">
              <span style="float: left;margin-top: 9px;margin-left: 5px" class="glyphicon glyphicon-list"></span>
              <ul class="mr-nav-like" style="list-style: none;text-align: center;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;margin-left: -10px">group1列表&nbsp;&nbsp;&nbsp;</span></div></li>
              </ul>
                <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 50px;margin-left: -10px;width: 101.6% ">
            </div>
            <!-- <div class="one" style="float: right;">杨两份</div> -->

            <!-- /.box-header -->
            <div class="box-body" style="margin-top: -33px">
               <div class="input-group margin">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: -2px">
                    <option>自动识别</option>
                    <option>设备名称</option>
                    <option>设备地址</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
                 <div style="float: right;margin-left: 50px;margin-top: -40px">
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-default1" style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12px;">添加</button>   
                 </div>
              <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                <thead>
                <tr>
                  <td style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                  <th><span style="font-size: 14px;font-weight: lighter">设备名称</span></th>
                  <th><span style="font-size: 14px;font-weight: lighter">设备地址</span></th>
                  <!-- <th><span style="font-size: 14px;font-weight: lighter">端口</span></th>
                  <th><span style="font-size: 14px;font-weight: lighter">协议</span></th> -->
                  <th><span style="font-size: 14px;font-weight: lighter">系统类型</span></th>
                  <th><span style="font-size: 14px;font-weight: lighter">部门</span></th>
                  <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                </tr>
                </thead>
                <tbody>
                 <tr>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                  <td>Linux测试1</td>
                  <td>123.45.6.7</td>
                  <td>Linux</td>
                  <td>总部</td>
                  <td><button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default2" style="right;height: 25px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                  <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default3" style="right;height: 25px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">移除</span></button>
                        </td>
                </tr>
              </tbody>
              </table>
              <form>
                    <input type="checkbox" class="禁用 解禁 删除" onclick="sel('chk')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default4" style="right;height: 30px;margin-left: 10px;font-size: 13px">移除</button>     
                 </form>
            </div>
          </div>
        </div>
      </div>
      <div class="modal" id="modal-default1">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 830px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择设备</h3>
              </div>
              <div class="modal-body">
                 
                  <div class="box-body" style="margin-top: -10px">
                  <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;<input type="checkbox" name="chk"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">设备名称</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">设备地址 </span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">设备类型</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">部门</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>Linux测试1</td>
                        <td>123.45.6.7</td>
                        <td>Linux</td>
                        <td>总部</td>
                        <td><button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-primary1" data-dismiss="modal" style="right;width:65px;height: 25px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">设备账号</span></button>
                      <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-primary2" data-dismiss="modal" style="right;width:40px;height: 25px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                      <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-primary3" data-dismiss="modal" style="right;width:40px;height: 25px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">删除</span></button>
                        </td>
                      </tr>
                    </tbody>
                  </table><br>
                <form>
                    <input type="checkbox"  onclick="sel('chk')"  style="float: left;margin-left: 15px;margin-top: 9px">
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-dismiss="modal" data-target="#modal-primary4" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加</button>    
                 </form>
                 <div class="modal-footer" style="margin-top: 40px">
                
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 700px">关闭</button>
                
              </div>
            
          </div>
            
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
      </div>
    </div>
   <div class="modal " id="modal-primary1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">设备账号</span>
              </div>
              <div class="modal-body">
                 <div class="box-header">
                   <span style="float: left;margin-top: 9px;margin-left: 5px" class="glyphicon glyphicon-list"></span>
                   <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
                      <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">账号列表&nbsp;&nbsp;&nbsp;</span></div></li>
                      <li style="float: right;"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-primary2" style="margin-left:10px;height: 30px;color: #fff;font-size: 12.5px">添加账号</button></li>
                    </ul>
                 </div>
                 <div class="box-body" style="margin-top: -10px">
                  <table id="example3" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th><input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">账号</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">协议</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">端口</span></th>
                        <!-- <th><span style="font-size: 14px;font-weight: lighter">认证方式</span></th> -->
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                       </tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td><input type="checkbox"></td>
                        <td>root</td>
                        <td>SSH</td>
                            <td>22</td>
                        <td>
                            <div style="height: 23px;width:40px;background:#e5e9f2;text-align: center;line-height: 25px;color:#black;border-radius: 4px">
                            离线</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal"  data-target="#modal-primary12" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px;background-color:#337ab7;color: #fff"><span style="margin-left: -5px">编辑</span></button>
                      <button id="toggle" type="button" onClick="show1()" class="btn btn-default pull-left " style="margin-left:7px;position:relative;width:40px;height: 22px;line-height: 2px;font-size: 12px;background-color: #f0ad4e;color: #fff"><span class="search-top" style="position:absolute;left:7px;">禁用</span></button>
                      <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-primary13" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px;background-color: #d9534f;color: #fff"><span style="margin-left: -5px">删除</span></button></td>
                      </tr>
                    </tbody>
                  </table><br>
                 <form>
                    <input type="checkbox" class="禁用 解禁 删除" style="float: left;margin-left: 9px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-primary14" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">删除</button>    
                 </form>
                 <div class="modal-footer" style="margin-top: 40px">
                   <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 480px">完成</button></div> 
                 </div>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <div class="modal" id="modal-primary11">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">添加账号</span>
              </div>
              <div class="modal-body">
              <div style="margin-left: 50px;margin-top: 20px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;font-size:20px">*</span>
                  <span style="font-size: 12px;color: #505050">登录方式：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px">
                  <option>自动登录</option>
                  <option>手动登录</option>
                   </select>
                 </div>
                <div style="margin-top: 25px;margin-left: 60px">
                <p style="margin-bottom:1px;">
                  <span style="font-size: 12px;color: #505050;">设备账号：
                  <input type="text" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span></p>
                  <input type="checkbox" class="input" style="margin-left:65px;margin-bottom:-2px;"/>&nbsp;<span style="font-size: 12px">特权账号</span>
                 </div>       
                 <div style="margin-top: 15px;margin-left: 85px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-top: 25px;margin-left: 60px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">确认密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-left: 50px;margin-top: 25px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">协议类型：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showAGREE(this.value);">
                  <option value="RDP1">RDP</option>
                  <option value="SSH1">SSH</option>
                  <option value="TELNET1">TELNET</option>
                  <option value="FTP1">FTP</option>
                  <option value="SFTP1">SFTP</option>
                   </select>
                 </div>
                 <div id="div12" style="margin-top: 25px;margin-left: 75px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="3389" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div13" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="22" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div14" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="23" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div15" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="21" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div4" style="display:none;">
                 <div style="margin-left: 66px;margin-top: 20px">
                  <span style="font-size: 12px;color: #505050">SSH Key：</span>
                  <select name="lo" style="width: 145px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showSSHKEY(this.value);"> 
                  <option value="y1">是</option>                  
                  <option value="n1" selected="selected">否</option>
                   </select>
                   <div id="div3" style="display:none;margin-top:-30px">
                  <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-right: 238px">上传</button>
                 </div><br>
                 </div><br>
                 </div><br>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
      </div>
      <div class="modal" id="modal-primary12">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">编辑:Linux测试1</span>
              </div>
              <div class="modal-body">
              <div style="margin-left: 50px;margin-top: 20px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;font-size:20px">*</span>
                  <span style="font-size: 12px;color: #505050">登录方式：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px">
                  <option>自动登录</option>
                  <option>手动登录</option>
                   </select>
                 </div>
                <div style="margin-top: 25px;margin-left: 60px">
                <p style="margin-bottom:-1px;">
                  <span class="input" style="font-size: 12px;color: #505050;">设备账号：
                  <input type="text" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span></p>
                  <input type="checkbox" class="input" style="margin-left:65px;margin-bottom:-2px;"/>&nbsp;<span style="font-size: 12px">特权账号</span>
                 </div> 
                 <div style="margin-top: 15px;margin-left: 85px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-top: 25px;margin-left: 60px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">确认密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-left: 50px;margin-top: 25px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">协议类型：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showAGREE(this.value);">
                  <option value="RDP2">RDP</option>
                  <option value="SSH2">SSH</option>
                  <option value="TELNET2">TELNTE</option>
                  <option value="FTP2">FTP</option>
                  <option value="SFTP2">SFTP</option>
                   </select>
                 </div>
                  <div id="div16" style="margin-top: 25px;margin-left: 75px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="3389" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div17" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="22" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div18" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="23" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div19" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="21" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div6" style="display:none;">
                 <div style="margin-left: 66px;margin-top: 20px">
                  <span style="font-size: 12px;color: #505050">SSH Key：</span>
                  <select name="lo" style="width: 145px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showSSHKEY(this.value);"> 
                  <option value="y2">是</option>                  
                  <option value="n2" selected="selected">否</option>
                   </select>
                   <div id="div5" style="display:none;margin-top:-30px">
                  <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-right: 238px">上传</button>
                 </div><br>
                 </div><br>
                 </div><br>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
      </div>
      <div class="modal" id="modal-primary13">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">确认</span>
              </div>
                <div style="margin-top: 20px;margin-left: 170px">
                  <p>您确定要删除指定的账号吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
   
        <div class="modal" id="modal-primary14">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">确认</span>
              </div>
                <div style="margin-top: 20px;margin-left: 170px">
                  <p>您确定要删除指定的账号吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
          <div class="modal" id="modal-primary2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">编辑设备</span>
              </div>
              <div class="modal-body">
                <div style="margin-top: 20px;margin-left: 50px">
                <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">设备名称：</span>
                  <input type="text" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;"></p>
                 </div>
                 <p style="font-size: 12px;margin-left: 124px;color: #a4abb5;margin-top: -10px">长度为1-128个汉字或字符</p>
                 <!-- <div style="margin-left: 50px;margin-top: 20px">
                  <span style="font-size: 12px;color: #505050;font-weight: bold;">协议类型：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;">
                  <option value="kongbai"></option>
                  <option>RDP</option>
                  <option>SSH</option>
                  <option>TELNTE</option>
                  <option>FTP</option>
                  <option>SFTP</option>
                   </select>
                 </div> -->
                 <div style="margin-top: 15px;margin-left: 50px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">设备地址：</span>
                  <input type="text" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;"></p>
                 </div>
                 <p style="font-size: 12px;margin-left: 124px;color: #a4abb5;margin-top: -10px">请输入有效的IP地址或域名</p>
                 <!-- <div style="margin-top: 25px;margin-left: 75px">
                  <span style="font-size: 12px;color: #505050;font-weight: bold">端口：
                  <input type="text" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <p style="font-size: 12px;margin-left: 114px;color: #a4abb5;margin-top: 0px">请输入1-65535之间的有效数字</p> -->
                 <div style="margin-left: 50px;margin-top: 15px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">系统类型：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:13px">
                  <option value="kongbai"></option>
                  <option>Linux</option>                  
                  <option>Windows</option>
                  <option>Cisco</option>
                  <option>HuaWei</option>
                  <option>H3C</option>
                  <option>Ruijie</option>
                  <option>Sugon</option>
                  <option>AIX</option>
                  <option>HP-UX</option>
                   </select>
                 </div>
                 <!-- <div style="margin-left: 50px;font-size: 12px;margin-top: 20px">
                  <span>更多选项:</span> 
                 <input type="checkbox" style="margin-left:10px" checked="checked"/>文件类型
                  <input type="checkbox" style="margin-left:30px" checked="checked"/>剪切板
                  <input type="checkbox" style="margin-left:30px" checked="checked"/>X11转发
                </div> -->
                <div style="margin-left: 50px;margin-top: 20px">
                <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">所属部门：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px">
                    <option>总部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销售部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市场部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术部</option>
                   </select>
                 </div>
                 <div style="margin-top: 20px;margin-left: -30px">
                  <p>
                    <label for="messageContent"><span style="font-size: 12px;color: #505050;font-weight: lighter">描述:</span></label>
                    <textarea align="left" style="border-radius: 3px;border: 1px solid #ccc;width: 210px;height: 80px;resize:none"></textarea>
                    </p>
                 </div>
                 <p style="font-size: 12px;margin-left: 124px;color: #a4abb5;margin-top: -10px">描述最长128个汉字或字符</p>
                 <br>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
      </div>
      <div class="modal" id="modal-primary3">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">操作确认：</h3>
              </div>
                <div class="hry" style="height: 80px;width: 572px ;margin-left:15px;background-color:#f2dede;line-height: 26px;color:#a94442;margin-top: 18px;border-color: #ebccd1">
                <p><span style="font-size: 15px;font-weight: bold">&nbsp;&nbsp;注意：删除操作不可恢复！！</span><br>
               &nbsp;&nbsp;删除设备将同时删除与之相关的账号，并将设备和账号从所在分组中删除，同时删除所有<br>&nbsp;&nbsp;相关授权！</p>
                </div>
                <div style="margin-top: 15px;margin-left: 15px">
                  <p>您确定要删除选定的1个设备吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <div class="modal" id="modal-primary4">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">确认</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定将该设备添加为组设备吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
         <div class="modal" id="modal-default2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7" >
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">编辑</span>
              </div>
              <div class="modal-body">
              <div style="margin-left: 50px;margin-top: 20px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;font-size:20px">*</span>
                  <span style="font-size: 12px;color: #505050">登录方式：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px">
                  <option>自动登录</option>
                  <option>手动登录</option>
                   </select>
                 </div>
                <div style="margin-top: 25px;margin-left: 60px">
                <p style="margin-bottom:-1px;">
                  <span class="input" style="font-size: 12px;color: #505050;">设备账号：
                  <input type="text" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span></p>
                  <input type="checkbox" class="input" style="margin-left:65px;margin-bottom:-2px;"/>&nbsp;<span style="font-size: 12px">特权账号</span>
                 </div> 
                 <div style="margin-top: 15px;margin-left: 85px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-top: 25px;margin-left: 60px">
                 <p>
                  <span style="font-size: 12px;color: #505050;">确认密码：
                  <input type="password" size="30px" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div style="margin-left: 50px;margin-top: 25px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">协议类型：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showAGREE(this.value);">
                  <option value="RDP2">RDP</option>
                  <option value="SSH2">SSH</option>
                  <option value="TELNET2">TELNTE</option>
                  <option value="FTP2">FTP</option>
                  <option value="SFTP2">SFTP</option>
                   </select>
                 </div>
                  <div id="div16" style="margin-top: 25px;margin-left: 75px">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="3389" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div17" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="22" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div18" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="23" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div19" style="margin-top: 25px;margin-left: 75px;display:none">
                 <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050;">端口：
                  <input type="text" value="21" size="30px" style="margin-top: -10px;width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;"></span>
                 </div>
                 <div id="div6" style="display:none;">
                 <div style="margin-left: 66px;margin-top: 20px">
                  <span style="font-size: 12px;color: #505050">SSH Key：</span>
                  <select name="lo" style="width: 145px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px" onchange="showSSHKEY(this.value);"> 
                  <option value="y2">是</option>                  
                  <option value="n2" selected="selected">否</option>
                   </select>
                   <div id="div5" style="display:none;margin-top:-30px">
                  <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-right: 238px">上传</button>
                 </div><br>
                 </div><br>
                 </div><br>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style=";height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary pull-left" data-dismiss="modal" style="margin-left:15px;height: 30px;color: #fff;font-size: 12px;">确定</button>
                </div>
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
      </div>
      <div class="modal" id="modal-default3">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">确认</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要移除指定的一个设备吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <div class="modal" id="modal-default4">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">确认</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要移除指定的设备吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
       
    </section>
  <!-- /.content-wrapper -->
 </div>
  <!-- Control Sidebar -->
  <footer class="main-footer">
    <jsp:include page="/index/footer"></jsp:include>
  </footer>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath }/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../../js/common/common.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath }/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath }/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath }/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/dist/js/demo.js"></script>
<script src="${pageContext.request.contextPath }/dist/js/port.js"></script>
<!-- page script -->
<script>
  $(function () {
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
</body>
</html>