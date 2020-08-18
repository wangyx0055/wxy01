<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>审计授权 | teleprot</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <!-- Google Font -->
  <!-- Font Awesome -->
 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  <!-- Google Font -->
  <link rel="stylesheet">
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
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  
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
        <div class="col-md-9" style="width: 100%" >
          <div style="width:100%;height: 54px;background-color: white">
            <div style="float: left">
              <span style="margin-left: 16px" class="glyphicon glyphicon-list"></span>
            <span  style="margin-left: 4px;font-size: 17px;line-height: 54px">授权策略&nbsp;1</span>
            </div>
          </div>
         <div class="nav-tabs-custom" style="margin-top: 1px">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#activity" data-toggle="tab">授权对象</a></li>
              <li><a href="#timeline" data-toggle="tab">连接控制</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="activity">
                <!-- Post -->
                <div class="box-header" >
                  
                   <ul class="mr-nav-like" style="list-style: none;">
                       <li style="float: left;"><span style="font-size: 17px;;margin-left: -40px;">授权操作者（允许远程操作的用户）&nbsp;&nbsp;&nbsp;</span></li>
              <li>            
                   <div class="pull-right box-tools">
                    <div style="text-align: right;">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default" style="right;height: 30px;font-size: 12px;margin-left: 335px;background-color: #4cae4c;color: #fff">添加用户</button>                       
                       <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modal-primary" data-toggle="modal" data-target="#modal-primary"  style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12.5px">添加用户组</button>
                  </div>
                </div></ul>
                </div>
                <div class="box-body pad">
                  <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">类型</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作者
                       <input type="text" placeholder="搜索：用户名/用户组名"style="width:300px; border-radius: 5px;border: 1px solid #ccc"> 
                      </span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>用户</td>
                        <td>admin</td>
                      </tr>
                     <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>用户组</td>
                        <td>test</td>
                      </tr>
                    </tbody>
                  </table>
                    <br>
                    <form>
                    <input type="checkbox" class="禁用 解禁 删除"  onclick="sel('chk')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default8" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">删除</button> 
                    </form>                        
                </div>
                <hr style="border: 0;border-bottom:1px dashed #c4c4c4;width: 101.4%;margin-left: -8px;margin-top: 10px">
                <div class="box-header" >
                  
                   <ul class="mr-nav-like" style="list-style: none;">
                       <li style="float: left;"><span style="font-size: 17px;;margin-left: -40px;">被授权资产（允许被远程操作的主机或账号）&nbsp;&nbsp;&nbsp;</span></li>  
                   <div class="pull-right box-tools">
                    <div style="text-align: right;">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal"  data-toggle="modal" data-target="#modal-default1" style="right;height: 30px;font-size: 12px;margin-left: 335px;background-color: #4cae4c;color: #fff">添加账号</button>                       
                       <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modal-primary1" style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12.5px">添加账号组</button>
                       <button type="button" class="btn btn-default pull-left" data-dismiss="modal"  data-toggle="modal" data-target="#modal-default2" style="right;height: 30px;font-size: 12px;margin-left: 10px;background-color: #4cae4c;color: #fff">添加主机</button>                       
                       <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modal-primary2" style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12.5px">添加主机组</button>
                  </div>
                </div></ul>
                </div>
                <div class="box-body pad">
                  <table id="example3" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">类型</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">资产&nbsp;<input type="text" placeholder="搜索：账号"style="width:300px; border-radius: 5px;border: 1px solid #ccc"> 
                      </span>

                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk1"></td>
                        <td>账号</td>
                        <td>root@127.0.0.1</td>
                      </tr>
                    </tbody>
                  </table>
                    <br>
                    <form>
                    <input type="checkbox" class="禁用 解禁 删除"  onclick="sel('chk1')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default8" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">删除</button> 
                    </form>                        
                </div>
              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="timeline" style="height: 280px">
                
                <span style="float:left;margin-left:28px;margin-top: 15px ">RDP选项</span>
                  <input type="checkbox" style="float: left;margin-left: 30px;margin-top: 18px">
                  <span style="margin-left:8px;line-height: 50px;color: #459dee">允许剪切板</span><br>
                  <input type="checkbox" style="float: left;margin-left: 110px;margin-top: 5px">
                  <span style="margin-left:8px;line-height: 25px;color: #459dee">允许驱动器映射</span><br>
                  <input type="checkbox" style="float: left;margin-left: 110px;margin-top: 10px">
                  <span style="margin-left:8px;line-height: 38px;color: #459dee">允许驱动器映射</span>
                  <hr style="border: 0;border-bottom:1px dashed #c4c4c4;width: 101.4%;margin-left: -8px;margin-top: 10px">
                  <span style="float:left;margin-left:28px;margin-top: 2px ">SSH选项</span>
                  <input type="checkbox" style="float: left;margin-left: 30px;margin-top:6px">
                  <span style="margin-left:8px;line-height: 28px;color: #459dee">允许SSH</span><br>
                  <input type="checkbox" style="float: left;margin-left: 110px;margin-top: 8px">
                  <span style="margin-left:8px;line-height: 32px;color: #459dee">允许SFTP</span>
                  <hr style="border: 0;border-bottom:1px dashed #c4c4c4;width: 101.4%;margin-left: -8px;margin-top: 10px">
                   <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;margin-left: 110px;font-size: 12.5px;background-color: #5cb85c;color: #fff">保存设置</button> 
              </div>
            </div>
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <div class="modal fade" id="modal-default">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 830px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择用户</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>用户</option>
                    <option>角色</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body">            
                  <div class="box-body" style="margin-top: -10px">
                      <table id="example4" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">用户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">角色</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk8"></td>
                        <td>root</td>
                        <td>admin</td>
                        <td>正常</td>
                      </tr>
                     <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk8"></td>
                        <td>root</td>
                        <td>admin</td>
                        <td>正常</td>
                      </tr>
                    </tbody>
                  </table><br>
                      <form>
                    <input type="checkbox" onclick="sel('chk8')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为授权操作者</button>    
                      </form>
                      <div class="modal-footer" style="margin-top: 40px">
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 700px">关闭</button> 
                      </div>
                  </div>
              </div>
          <!-- /.modal-dialog -->
            </div>
           </div>
      </div>
      <div class="modal fade" id="modal-primary">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 500px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                  <h3 class="modal-title">选择用户组</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>用户组</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body"> 
                  <div class="box-body" style="margin-top: -10px">
                     <table id="example5" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" ></th>
                        <th><span style="font-size: 14px;font-weight: lighter">用户组
                      </span></th></tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk2"></td>
                        <td>root</td>
                      </tr>
                    </tbody>
                     </table><br>
                     <form>
                    <input type="checkbox" onclick="sel('chk2')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为授权操作者</button>    
                     </form>
                     <div class="modal-footer" style="margin-top: 20px">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 400px">关闭</button>                
                     </div> 
                  </div>
              </div>
          <!-- /.modal-dialog -->
            </div>
          </div>
      </div>
      <div class="modal fade" id="modal-default1">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 830px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择账号</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>账号</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body">
                  <div class="box-body" style="margin-top: -10px">
                      <table id="example6" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">账号 
                        </span></th>
                        <th>
                        <span style="font-size: 14px;font-weight: lighter">远程连接协议</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">认证方式</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                       </tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk3"></td>
                        <td>root</td>
                        <td>fdg</td>
                        <td>fhuf</td>
                        <td><div style="height: 23px;width:55px;background:#3c8dbc;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            连接中</div></td>
                      </tr>
                    </tbody>
                      </table><br>
                      <form>
                    <input type="checkbox" onclick="sel('chk3')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为被授权操作者</button>    
                      </form>
                      <div class="modal-footer" style="margin-top: 40px">
                
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 700px">关闭</button>
                      </div>
                  </div>
              </div>
          <!-- /.modal-dialog -->
            </div>
          </div>
      </div>
      <div class="modal fade" id="modal-default8">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">删除</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要删除全部吗？</p>
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
      <div class="modal fade" id="modal-primary1">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 500px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择账户组</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>账户组</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body">                 
                  <div class="box-body" style="margin-top: -10px">
                      <table id="example7" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">账号组
                      </span></th></tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk4"></td>
                        <td>root</td>
                      </tr>
                    </tbody>
                      </table><br>
                      <form>
                      <input type="checkbox" onclick="sel('chk4')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button"  class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为被授权操作者</button>    
                      </form>
                      <div class="modal-footer" style="margin-top: 20px">
                
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 400px">关闭</button>
                
                      </div>
            
                  </div>
              </div>
          <!-- /.modal-dialog -->
            </div>
          </div>
      </div>
      <div class="modal fade" id="modal-default2">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 830px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择主机</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>主机</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body">
                  <div class="box-body" style="margin-top: -10px">
                      <table id="example8" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">主机
                        </span></th>
                        <th>
                        <span style="font-size: 14px;font-weight: lighter">系统</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">资产编号</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                       </tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk5"></td>
                        <td>127.12.12</td>
                        <td></td>
                        <td></td>
                        <td><div style="height: 23px;width:55px;background:#d34242;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            禁用</div></td>
                      </tr>
                    </tbody>
                      </table><br>
                      <form>
                    <input type="checkbox" onclick="sel('chk5')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button"  class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为被授权操作者</button>    
                      </form>
                      <div class="modal-footer" style="margin-top: 40px">
                
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 700px">关闭</button>
                
                      </div>
            
                  </div>
             </div>
          <!-- /.modal-dialog -->
            </div>
          </div>
      </div>
      <div class="modal fade" id="modal-primary2">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 500px">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">选择主机组</h3>
              </div>
              <div class="input-group margin" style="margin-bottom: -4px">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: 15px">
                    <option>自动识别</option>
                    <option>主机组</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
              <div class="modal-body">
                  <div class="box-body" style="margin-top: -10px">
                      <table id="example9" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">主机组 
                      </span></th></tr>
                    </thead>
                    <tbody>
                       <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk6"></td>
                        <td>root</td>
                      </tr>
                    </tbody>
                      </table><br>
                      <form>
                    <input type="checkbox" onclick="sel('chk6')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">添加为被授权操作者</button>    
                      </form>
                      <div class="modal-footer" style="margin-top: 20px">
                
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 400px">关闭</button>
                
                      </div>
            
                  </div>
               </div>
            </div>
          </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
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
<script src="${pageContext.request.contextPath }/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/dist/js/demo.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../../js/common/common.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath }/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->

<script src="${pageContext.request.contextPath }/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="${pageContext.request.contextPath }/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath }/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath }/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="${pageContext.request.contextPath }/bower_components/moment/min/moment.min.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="${pageContext.request.contextPath }/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="${pageContext.request.contextPath }/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="${pageContext.request.contextPath }/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath }/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath }/plugins/iCheck/icheck.min.js"></script>

<script src="${pageContext.request.contextPath }/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->


<script>
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
    $('#example3').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
    $('#example4').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
    $('#example5').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
    $('#example6').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
   $('#example7').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
   $('#example8').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
   $('#example9').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
<script language="javascript">
function sel(r){
    o=document.getElementsByName(r)
    for(i=0;i<o.length;i++)
      o[i].checked=event.srcElement.checked
}

</script>
</body>
</html>
