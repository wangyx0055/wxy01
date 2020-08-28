<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>成员列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- daterangepicker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
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
   <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="../../bower_components/treeview/font.css">
<style>
label{
display: inline-block;
padding: 3px 6px;
text-align: right;
width: 150px;
vertical-align: top;
}
  .one{
    float: right;
    height: 20px;
    width: 250px;
    background-color: #d3d3d3;
  }
  .input1{
   margin:0;padding: 0;vertical-align: text-top;margin-bottom: -4px 
  }
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <header class="main-header">
    <jsp:include page="/index/header"></jsp:include>
    <!-- Logo -->
    <!-- Header Navbar: style can be found in header.less -->
  </header>
   <aside class="main-sidebar">
   <jsp:include page="/index/menu"></jsp:include>
    <!-- /.sidebar -->
  </aside>
  <!-- Left side column. contains the logo and sidebar 
  <div class="content-wrapper">
    <!- Content Header (Page header) -->
    <div class="content-wrapper">
   <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="box-shadow: none;border-top: none">
            <div class="box-header">
            <span style="float: left;margin-top: 9px;margin-left: 5px" class="glyphicon glyphicon-list"></span>
              <span style="float: left;margin-top: 9px;margin-left: 5px"></span>
  
              <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">test成员列表&nbsp;&nbsp;&nbsp;</span></div></li>
              <div class="pull-right box-tools">
                    <div style="text-align: right;">
                       <form style="background-color: #FFFFFF">
                             
                    </form>
                  </div>
                </div></ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -1px;width: 100% ">
            <!-- <div class="one" style="float: right;">杨两份</div> -->
            <!-- /.box-header -->
             <div class="box-body" style="margin-top: -33px">
            <div class="input-group margin">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: -2.7px">
                    <option>自动搜索</option>
                    <option>成员用户</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                   <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                  </div>
                  <div style="float: right;margin-right: 10px;margin-top: -40px">
                  <button type="button" class="btn btn-primary pull-left"  data-toggle="modal" data-target="#modal-primary" style="float: right;height: 30px;color: #fff;font-size: 12.5px;margin-left:0px">添加</button>  
                  <button type="button" class="btn btn-default pull-left"  data-toggle="modal"  data-target="#modal-primary0"  data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 10px">导入</button>
                  <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal"  data-target="#modal-default10"  style="height: 30px;font-size: 12px;margin-left: 10px">导出</button>
                </div> 
             
            <div class="box-body" style="margin-top: -20px">
              <table id="example2" class="table table-hover" style="font-size: 12.5px">
                <thead>
                <tr>
                  <td style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                  <td><span style="font-size: 14px;font-weight: lighter">用户</span></td>
                  <td><span style="font-size: 14px;font-weight: lighter">部门</span></td>
                  <td><span style="font-size: 14px;font-weight: lighter">类型</td> 
                  <td><span style="font-size: 14px;font-weight: lighter">角色</td>
                  <td><span style="font-size: 14px;font-weight: lighter">状态</td>
                  <td><span style="font-size: 14px;font-weight: lighter">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                  <td><p data-toggle="modal" data-target="#modal-default3" style="color: blue">admin</p></td>
                  <td>销售部</td>
                   <td>****</td>
                  <td>系统管理员</td>
                  <td><div style="height: 23px;width:55px;background:#e1e1e1;text-align: center;line-height: 25px;color:#aaa;border-radius: 4px">
                                            离线</div></td>
                  <td><button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-primary20" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                             <button id="toggle" type="button" onClick="show1()" class="btn btn-default pull-left"style="width:60px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span class="search-top" style="margin-left: -3px;text-align: center;">禁用</span></button>
                                   <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default4" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">删除</span></button>
                        </td>
                </tr>
              </table>
              <form>
                    <input type="checkbox"  onclick="sel('chk')"  style="float: left;margin-left: 28px;margin-top: 9px">
                    <button type="button" class="btn btn-default pull-left" data-toggle="modal"  data-target="#modal-default4" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">删除</button>
                 </form>
            </div>
          </div>
        </div>
      </div>
      <!-- 编辑用户 -->
         <div class="modal " id="modal-primary20">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px">编辑用户</span>
              </div>
              <div class="modal-body">
                <div style="margin-left: 91px">
                <span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050">角色：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size:12px">
                    <option>运维用户</option>
                    <option>系统管理员</option>
                    <option>审计员</option>
                    <option>密码员</option>
                    
                  </select>
                </div>
                <div style="margin-left: 91px;margin-top: 10px">
                <span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050">部门：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;">
                    <option>总部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销售部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市场部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术部</option> 
                  </select>
              </div>
                <div style="margin-top: 20px;margin-left: 79px">
                  <p>
                  <span style="color:red;font-size:20px;vertical-align:-20%;margin-right:1px;">*</span><span style="font-size: 12px;color: #505050">用户名：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入用户登录名" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 88px">
                  <span><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:0px;">*</span>
                  <span style="font-size: 12px;color: #505050">姓名：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;用户的真实姓名" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                 </div>
                <div style="margin-top: 20px;margin-left: 88px">
                  <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:0px;">*</span>
                  <span style="font-size: 12px;color: #505050">密码：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入用户密码" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 65px">
                  <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:0px;">*</span>
                  <span style="font-size: 12px;color: #505050">确认密码：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请确认密码" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 91px">
                  <p><span style="font-size: 13.5px;color: #505050">email：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;电子邮箱地址" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-left: 75px">
                  <span style="font-size: 12px;color: #505050">认证方式：</span>
                  <input type="checkbox" class="input1" checked="checked"  ><span style="font-size:13px">&nbsp;密码</span>
                  <input type="checkbox" class="input1" id="abShow" onClick="toggleDiv2(this)" style="margin-left: 25px"><span for="abShow" style="font-size:13px;">&nbsp;生物指纹</span>
                  <img data-dismiss="modal" data-toggle="modal" data-target="#modal-default15" src="${pageContext.request.contextPath }/dist/img/finger.png" id="div6" style="position:absolute;width:15px;height: 20px;display:none;vertical-align:middle">
                  <input type="checkbox" class="input1"  id="abdShow" onClick="toggleDiv3(this)" style="margin-left:25px"><span for="abdShow" style="font-size:13px;">&nbsp;密码+动态口令
                  <img src="${pageContext.request.contextPath }/dist/img/arrowhead.png" onClick="show3()" style="width:10px;height: 15px"></span>                
                  <div id="div7" style="display:none;margin-right: 30px;margin-top:-5px;float:right">
                    <select name="lo" style="width: 120px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;">
                      <option>1234582266622525</option>
                      <option>1252145225525522</option>
                      <option>1352665452255525</option>
                      <option>1355255654525552</option>
                    </select>
                  </div><br>
                  <div id="div8" style="display:none">
                    <input type="checkbox" class="input1"  style="margin-left: 62px"><span style="font-size:13px">&nbsp;短信</span>
                    <input type="checkbox" class="input1"  style="margin-left: 26px"><span style="font-size:13px">&nbsp;AD域</span>
                    <input type="checkbox" class="input1"  style="margin-left: 50px"><span style="font-size:13px">&nbsp;RADIUS</span><br>
                    <input type="checkbox" class="input1"  style="margin-left: 62px"><span style="font-size:13px">&nbsp;LADP</span>
                    <input type="checkbox" class="input"  style="margin-left: 23.5px"><span style="font-size:13px">&nbsp;email</span>
                    </div>
                   <br>
                  <div class="modal-footer">
                  <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 300px">取消</button>
                   <button type="button" id="addButton" class="btn btn-primary"  data-dismiss="modal" style="float: left;margin-left:15px;height: 30px;color: #fff;font-size: 12px">确定</button>
                    <button type="button" class="btn btn-primary"  data-dismiss="modal"  data-toggle="modal"  data-target="#modal-primary4"  style="float: left;margin-left:430px;height: 30px;color: #fff;font-size: 12px;margin-top:-30px">更多</button>
                </div>     
                </div>
            </div>
          </div>
         </div>
         </div>
 <!-- 删除 -->
     <!--   <div class="modal" id="modal-primary2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding:10px;">操作确认</span>
              </div>
                <div class="hry" style="height: 80px;width: 572px ;margin-left:15px;background-color:#f2dede;line-height: 26px;color:#a94442;margin-top: 18px;border-color: #ebccd1">
                <p><span style="font-size: 12px;font-weight: bold">&nbsp;&nbsp;注意：删除操作不可恢复！！</span><br>
               &nbsp;&nbsp;删除成员将同时删除与之相关的成员账号，并将成员和成员账号从所在分组中移除，同时删<br>&nbsp;&nbsp;除所有相关授权！</p>
                </div>
                <div style="margin-top: 15px;font-size: 12px;margin-left: 15px">
                  <p>&nbsp;&nbsp;如果您希望临时禁止登录指定成员，可将其“禁用”！</p>
                  <p>&nbsp;&nbsp;您确定要移除选定的成员吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
                
              </div>
            </div>
            /.modal-content
          </div>
          /.modal-dialog
        </div> -->
     <!--   admin -->
        <div class="modal" id="modal-default3">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding:10px;">admin</span>
              </div>
              <div class="modal-body">
              <div style="margin-left: 60px;margin-top: 13px">
                <span style="font-size: 12px;color: #505050;"用户名：admin</span>
                 </div>
                 <div style="margin-top: 13px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050">姓名：admin</span>
              </div>
              <div style="margin-top: 13px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050">部门：销售部</span>
              </div>
                 <div style="margin-left: 74px;margin-top: 13px">
                    <span style="font-size: 12px;color: #505050;">邮箱：netmwd@qq.com</span>
                 </div>
              <div style="margin-top: 20px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050;">电话：
                  <div style="height: 23px;width:55px;background:#e5e5e5;text-align: center;line-height: 25px;border-radius: 4px;margin-left: 9%;margin-top: -5%">未设置</div></span>
              </div>
              <div style="margin-top: 20px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050;">QQ：
                  <div style="height: 23px;width:55px;background:#e5e5e5;text-align: center;line-height: 25px;border-radius: 4px;margin-left: 9%;margin-top: -5%">未设置</div></span>
              </div>
              <div style="margin-top: 20px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050;">微信：
                  <div style="height: 23px;width:55px;background:#e5e5e5;text-align: center;line-height: 25px;border-radius: 4px;margin-left: 9%;margin-top: -5%">未设置</div></span>
              </div>
              <div style="margin-top: 20px;margin-left: 74px">
                  <span style="font-size: 12px;color: #505050;">描述：
                  <div style="height: 23px;width:55px;background:#e5e5e5;text-align: center;line-height: 25px;border-radius: 4px;margin-left: 9%;margin-top: -5%">未设置</div></span>
              </div><br>
                <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:430px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
      </div>
  <!-- 导入 -->
     <div class="modal " id="modal-primary0">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                  <span class="modal-title" style="font-size:16px">导入</span>
              </div>
              <div style="margin-left: 100px;margin-top: 25px;font-size: 12px">
                <span>下载模板:
                <button type="button" class="btn btn-primary pull-right" style="margin-right:350px;height: 30px;color: #fff;font-size: 12px;margin-top: -5px">点击下载</button></span>
              </div><br>
              <div style="margin-left: 100px;margin-top: 25px;font-size: 12px">
                <span>上传文件:
                <button type="button" class="btn btn-primary pull-right" style="margin-right:350px;height: 30px;color: #fff;font-size: 12px;margin-top: -5px">点击上传</button></span>
              </div><br>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
        </div>
          <!-- /.modal-dialog -->
        </div>
    <!-- 导出 -->
           <div class="modal " id="modal-default10">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px">导出</span>
              </div>
              <div class="modal-body">
                 <div style="margin-top: 10px;margin-left: 80px">
                  <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:1px;">*</span>
                  <span style="font-size: 12px;color: #505050">用户密码：</span>
                  <input type="password" placeholder="&nbsp;&nbsp;&nbsp;请输入当前管理员用户密码" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                 </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
                
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
         </div>
          
      </div>
       <!--  选择用户 -->
      <div class="modal" id="modal-primary">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 830px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding:10px;">选择用户</span>
              </div>
              <div class="modal-body">
                 
                  <div class="box-body" style="margin-top: -10px">
                  <table id="example2" class="table table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <td style="width: 40px">&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td><span style="font-size: 14px;font-weight: lighter">用户 </span></td>
                        <td><span style="font-size: 14px;font-weight: lighter">部门 </span></td>
                        <td><span style="font-size: 14px;font-weight: lighter">类型</span></td>
                        <td><span style="font-size: 14px;font-weight: lighter">角色</span></td>
                        <td><span style="font-size: 14px;font-weight: lighter">状态</span></td>
                        <td><span style="font-size: 14px;font-weight: lighter">操作</span></td>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>admin</td>
                        <td>销售部</td>
                        <td>*****</td>
                        <td>系统管理员</td>
                        <td><div style="height: 23px;width:55px;background:#e1e1e1;text-align: center;line-height: 25px;color:#aaa;border-radius: 4px">
                                            离线</div></td>
                        <td><button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-primary20" style="right;width:40px;height: 22px;line-height: 2px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                                  <button id="toggle1" type="button" onClick="show7()" class="btn btn-default pull-left"style="width:60px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span class="search-top1" style="margin-left: -3px;text-align: center;">禁用</span></button>
                                   <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default4" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">删除</span></button>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table><br>
                <form>
                    <input type="checkbox"  onclick="sel('chk')"  style="float: left;margin-left: 15px;margin-top: 5px；">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;margin-bottom: 12px;font-size: 12.5px">添加为组成员</button>    
                 </form>
                 <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:640px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
               </div>
            
          </div>
            
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
      </div>
    </div>
    <!-- 指纹 -->
       <div class="modal " id="modal-default15">
          <div class="modal-dialog">
            <div class="modal-content" style="height: 500px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px">录入指纹</span>
              </div>
               <div style="height: 350px">
               </div>        
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"   data-dismiss="modal" data-toggle="modal"  data-target="#modal-adduser" style="right;height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" id="addButton" class="btn btn-primary"  data-dismiss="modal" data-toggle="modal"  data-target="#modal-adduser"  style="float: left;margin-left:15px;height: 30px;color: #fff;font-size: 12px">确定</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
       <div class="modal " id="modal-default10">
          <div class="modal-dialog">
            <div class="modal-content" style="height: 500px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px">录入指纹</span>
              </div>
               <div style="height: 350px">
               </div>        
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-toggle="modal"  data-target="#modal-primary1"  data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" id="addButton" class="btn btn-primary"  data-toggle="modal"  data-target="#modal-primary1"  data-dismiss="modal" style="float: left;margin-left:15px;height: 30px;color: #fff;font-size: 12px">确定</button>               
              </div> 
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
    <!-- 更多 -->
     <div class="modal " id="modal-primary4">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px">编辑用户</span>
              </div>
              <div class="modal-body">
                <div style="margin-top: 20px;margin-left: 100px">
                  <p><span style="font-size: 12px;color: #505050">电话：</span>
                  <input type="text"  style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 106px">
                  <p><span style="font-size: 13px;color: #505050">QQ：</span>
                  <input type="text"  style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 100px">
                  <p><span style="font-size: 12px;color: #505050">微信：</span>
                  <input type="text" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"></p>
                </div> 
                <div style="margin-top: 20px;margin-left: 100px">
                  <p>
                   <span style="font-size: 12px;color: #505050;position:absolute;">备注:</span>
                    <textarea align="left" style="border-radius: 3px;margin-left:38px; border: 1px solid #ccc;width: 210px;height: 80px;resize:none"></textarea></p>
                 
                 </div><br>
                <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 320px">取消</button>
                   <button type="button" id="addButton" class="btn btn-primary"  data-dismiss="modal" style="float: left;margin-left:15px;height: 30px;color: #fff;font-size: 12px">确定</button>
                    <button type="button" class="btn btn-primary"  data-dismiss="modal"  data-toggle="modal"  data-target="#modal-primary20" style="float: left;margin-left:450px;height: 30px;color: #fff;font-size: 12px;margin-top:-30px">上一步</button>
                </div>     
                </div>
              </div>
            </div>
          </div>
    <!-- 删除 -->
     <div class="modal" id="modal-default4">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding:10px;">删除</span>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要删除指定的成员吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"  data-dismiss="modal" style="float: left;margin-left:460px;height: 30px;font-size: 12px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px;color: #fff;">确定</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
    </section>
    </div>
    <footer class="main-footer">
    <jsp:include page="/index/footer"></jsp:include>
  </footer>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 
  <!-- Control Sidebar -->
  
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
<!-- page script -->
<script>
  $('.main-header').load('header.html');
    $('.main-sidebar').load('left.html');
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
  function showfinger(v){
    if(v=='b'){
       document.getElementById("div2").style.display="block";
    }else{
      document.getElementById("div2").style.display="none";
    }
  }
</script>
<script language="javascript">
function sel(r){
    o=document.getElementsByName(r)
    for(i=0;i<o.length;i++)
      o[i].checked=event.srcElement.checked
}
</script>
<script type="text/javascript">
  var div6 = document.getElementById("div6");
  var div7= document.getElementById("div7");
function toggleDiv2(ab){
  if(ab.checked){
	  document.getElementById("div6").style.display="inline-block";
  }else{
	  document.getElementById("div6").style.display="none";
  }
}
function toggleDiv3(abd){
	  if(abd.checked){
		  document.getElementById("div7").style.display="inline-block";
	  }else{
		  document.getElementById("div7").style.display="none";
	  }
	}
</script>
<script type="text/javascript">
    function show1(){
    $(".search-top").text("启用");
    $("#toggle").attr("onClick","show2()");
    }
    function show2(){
   	$(".search-top").text("禁用");
    $("#toggle").attr("onClick","show1()");
    }
</script>
<script type="text/javascript">
    function show7(){
    $(".search-top1").text("启用");
    $("#toggle1").attr("onClick","show8()");
    }
    function show8(){
   	$(".search-top1").text("禁用");
    $("#toggle1").attr("onClick","show7()");
    }
</script>
<!-- <script type="text/javascript">
 function show1()
{var value = document.getElementById("div2").style.display;
if(value=="none")
{document.getElementById("div2").style.display="block";
 document.getElementsByClassName("search-top")[0].innerHTML="启用";
}
else{
document.getElementById("div2").style.display="none";
document.getElementsByClassName("search-top")[0].innerHTML="禁用";
}
}
 function show2()
 {var value = document.getElementById("div2").style.display;
 if(value=="none")
 {document.getElementById("div2").style.display="block";
  document.getElementsByClassName("search-top1")[0].innerHTML="启用";
 }
 else{
 document.getElementById("div2").style.display="none";
 document.getElementsByClassName("search-top1")[0].innerHTML="禁用";
 }
 }
</script> 
 -->
<script type="text/javascript">
function show3()
{var value = document.getElementById("div8").style.display;
if(value=="none")
{document.getElementById("div8").style.display="block";
}
else{
document.getElementById("div8").style.display="none";
}
}
</script>
<script type="text/javascript">
function show()
{var value = document.getElementById("div").style.display;
if(value=="none")
{document.getElementById("div").style.display="block";
}
else{
document.getElementById("div").style.display="none";
}
}
</script>
</body>
</html>
