<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>访问授权</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- my css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/access_authorize/access_authorize.css">
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
  <link rel="stylesheet" href="../../bower_components/treeview/font.css">

<link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/AdminLTE.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/select2/dist/css/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  
  <link rel="stylesheet"
        href="../../bower_components/treeview/font.css">
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
  <link rel="stylesheet" href="../../bower_components/treeview/font.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/AdminLTE.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/select2/dist/css/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/dist/css/skins/_all-skins.min.css">
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  
  <link rel="stylesheet"href="../../bower_components/treeview/font.css">
<style>
  .one{
    float: right;
    height: 20px;
    width: 250px;
    background-color: #d3d3d3;
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
              <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">访问授权工单&nbsp;&nbsp;&nbsp;</span></div></li>
              <li><button  class="btn btn-default pull-left" data-dismiss="modal" onclick="location.reload();" style="height: 30px;margin-left: 5px;font-size: 12.5px" ><i class="fa fa-fw fa-repeat"></i>&nbsp;刷新列表</button></li>
            
              <div class="pull-right box-tools">
                    <div style="text-align: right;">

                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-default2" style="height: 30px;font-size: 12px">
                        <i class="fa fa-fw fa-plus-circle"></i>&nbsp;新建</button> 
                  </div>
                </div></ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
            <div class="box-body" style="margin-top: -22px">
                <div class="input-group margin">
                  <select class="form-control" style="width:100px;height:30px;font-size: 12px;margin-left: -2px">
                    <option>工单号</option>
                    <option>资源账户</option>
                    <option>工单备注</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                  <button type="button"  onClick="show()" value="显示" class="btn btn-default pull-left search-top" style="height: 30px;font-size: 12px;margin-left:55px ">高级搜索</button>
                </div>
                <div id="div1" style="display:none;font-size: 13px">
                  <div>
                  <span style="margin-left: 20px">工单号:</span>
                  <span style="margin-left: 160px">资源账户:</span>
                  <span style="margin-left: 145px">工单备注:</span>
                  <span style="margin-left: 145px">资源名称:</span>
                  <span style="margin-left: 145px">起始申请时间:</span>
                  </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入工单号" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源账户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入工单备注" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源名称" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  </div>
                  <div style="margin-top: 8px">
                  <span style="margin-left: 20px">截止申请日期:</span>
                  <span style="margin-left: 120px">起始运维时间:</span>
                  <span style="margin-left: 120px">截止运维时间:</span>
                  <span style="margin-left: 120px">审批用户:</span>
                  <span style="margin-left: 145px">起始审批时间:</span>
                  </div>
                  <div>
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入截止申请日期" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="data" placeholder="&nbsp;&nbsp;&nbsp;请输入起始运维时间" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入截止运维时间" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入审批用户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  </div>
                  <div style="margin-top: 8px">
                  <span style="margin-left: 20px">截止审批时间:</span>
                  <span style="margin-left: 120px">审批结果:</span>
                  </div>
                  <div>
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入截止申请日期" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <select name="lo" style="width: 180px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 13px;margin-left: 20px"> 
                  <option   style="font-size: 13px" selected>&nbsp;&nbsp;批准</option>
                  <option style="font-size: 13px">&nbsp;&nbsp;驳回</option>
                    <option style="font-size: 13px">&nbsp;&nbsp;撤销</option>
                    </select>
                  </div>
                  <div style="float: right;margin-top: 10px">
                  <button  class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;margin-left: 2px;font-size: 12.5px" >重置</button>
                  <button type="button" class="btn btn-primary" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 10px">搜索</button>
                </div>
                </div>
              <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                <thead>
                        <tr>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">工单号</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">申请时间</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">资源账户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">工单备注</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                        </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            连接中</div></td>
                        <td>2019-1-2 12:23</td>
                        <td>213.3232.232</td>
                        <td>已授权</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">管理</span></button>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">删除</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            连接中</div></td>
                        <td>2019-1-2 12:23</td>
                        <td>213.3232.232</td>
                        <td>已授权</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">管理</span></button>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">删除</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            连接中</div></td>
                        <td>2019-1-2 12:23</td>
                        <td>213.3232.232</td>
                        <td>已授权</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">管理</span></button>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">删除</span></button></td>
                      </tr>
                    </tbody>
                  </table>
              </div>
            </div>
        </div>
      </div>
      <!-- manage -->
        <div class="modal fade" id="modal-default">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">losss</h3>
              </div>
              <div class="modal-body">
                  <div style="margin-left: 80px;margin-top: 13px">
                    <span style="font-size: 14px;color: #505050;">工单名称：losss</span>
                  </div>
                  <div style="margin-left: 107px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">状态：已启用</span>
                  </div>
                  <div style="margin-top: 20px;margin-left: 79px">
                  <span style="font-size: 14px;color: #505050;">执行动作：动态授权</span>
                  </div>
                  <div style="margin-top: 20px;margin-left: 93px">
                  <span style="font-size: 14px;color: #505050;">有效期：-</span>
                  </div>
                  <div style="margin-left: 93px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">创建者：ds</span>
                  </div>
                  <div style="margin-left: 80px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">创建时间：2019-08-12 23:38:17</span>
                  </div>
                  <div style="margin-left: 94px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">修改者：bengbuxueyuan</span>
                  </div>
                  <div style="margin-left: 80px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">修改时间：2019-10-25 22:56:21</span>
                  </div>
                </div>
                  <br>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" style="height: 30px;color: #fff;font-size: 12px;"  data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="height: 30px;font-size: 12px;">取消</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- delete -->
        <div class="modal fade" id="modal-default1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" >删除</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要删除命令授权工单“11”吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- first -->
        <div class="modal fade" id="modal-default2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">新建访问授权工单</h3>
              </div>
              <div class="modal-body">
                <div style="margin-left: 60px;margin-top: 20px;font-size: 12px">
                  <span>运维时间：</span>
                    <div class="input-group" style="margin-right: 230px;float:right;margin-top: -8px">
                      <div class="input-group-addon" style="height: 34px;width: 20%">
                        <i class="fa fa-calendar" style="margin-left: -4px;margin-right: 4px;margin-top: 4px"></i>
                      </div>
                      <input type="text" class="form-control pull-right" id="reservation" style="width:100%;float: right;margin-left: -10px">
                    </div>
                </div>
                <div style="margin-left: 60px;font-size: 12px;margin-top: 30px">
                  <span>文件传输:</span> 
                  <input type="checkbox" style="margin-left:10px" checked="checked"/>上传
                  <input type="checkbox" style="margin-left:30px" checked="checked"/>下载
                </div>
                <div style="margin-left: 60px;font-size: 12px;margin-top: 14px">
                  <span>更多选项:</span> 
                  <input type="checkbox" style="margin-left:10px" checked="checked"/>文件管理
                  <input type="checkbox" style="margin-left:30px" checked="checked"/>上行剪切板
                  <input type="checkbox" style="margin-left:30px" checked="checked"/>下行剪切板
                  <input type="checkbox" style="margin-left:30px"/>显示水印
                </div>
                <span style="font-size: 12px;margin-left: 60px">工单备注:</span>
                  <input type="text" size="30px" style="margin-top: 15px;width: 300px;height: 60px;border-radius: 3px;border: 1px solid #ccc;margin-left: 10px">
                  <p style="font-size: 12px;margin-left: 122px;color: #a4abb5;margin-top: 0px">每行输入一个IP地址或地址段，支持子网掩码，例如：<br>192.168.1.10-192.168.1.100或192.168.1.10/24</p>
                  <div class="modal-footer">
                <button type="button" class="btn btn-primary" style="height: 30px;color: #fff;font-size: 12px" data-toggle="modal" data-target="#modal-default3" data-dismiss="modal">下一步</button>
                <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
                    
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- two -->
        <div class="modal fade" id="modal-default3">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header" style="background-color: #f7f7f7">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                      <h3 class="modal-title">新建访问授权工单</h3>
                </div>
                <div class="nav-tabs-custom" style="margin-top: 1px">
                  <div class="tab-content">
                      <div class="active tab-pane" style="height: 100%">
                        <div class="modal-body" style="height: 400px">
                            <div style="float: left;margin-left: 10px;margin-top: 10px">
                        <div style="width: 190px;height: 38px;border: 1px solid #d1dbe5;margin-left: 40px;font-size: 12px;line-height: 30px"><span style="margin-left: 10px;">可选择的资源账户</span>
                        </div>
                        <div style="width: 190px;height: 300px;border: 1px solid #d1dbe5;margin-left: 40px;font-size: 12px;line-height: 30px"><input type="text" class="form-control" style="width: 65%;font-size: 12px;height: 30px;margin-left: 15px">
                              <span class="input-group-btn" style="float: right">
                                <button type="button" class="btn btn-default pull-left" style="height: 30px;width:50px;font-size: 12px;margin-top:-30px;margin-left: -60px">搜索</button>
                              </span>
                              <select name="select-left" id="select-left" size="getLeftSize()" multiple>
                                <option value="" >张三</option>
                                <option value="" >李四</option>
                                <option value="" >王五</option>
                                <option value="" >1</option>
                                <option value="" >2</option>
                                <option value="" >3</option>
                                <option value="" >4</option>
                                <option value="" >5</option>
                                <option value="" >6</option>
                                <option value="" >7</option>
                                <option value="" >8</option>
                                <option value="" >9</option>
                              </select>
                        </div>
                            </div>
                              <!-- add and romove  -->
                   <div class="add-remove">
                    <button class="btn btn-default " id="remove" onclick="moveBack(document.getElementById('select-left'),document.getElementById('select-right'))" ><span class="glyphicon glyphicon-chevron-left"></span></button>
                    <button class="btn btn-default " id="add" onclick="moveTo(document.getElementById('select-left'),document.getElementById('select-right'))"><span class="glyphicon glyphicon-chevron-right"></span></button>  
                  </div>
                  <!-- selectall -->
                  <div class="select-left-all">
                    <input type="checkbox" id="left-checked">
                    <span>全选</span>
                  </div>
                  <div class="select-right-all" >
                   <input type="checkbox" id="right-checked">
                   <span>全选</span>
                  </div>
                            <div style="float: right;margin-right:30px;margin-top: 10px">
                        <div style="width: 190px;height: 38px;border: 1px solid #d1dbe5;margin-left: 40px;font-size: 12px;line-height: 30px"><span style="margin-left: 10px;">已选择的资源账户</span>
                        </div>
                        <div style="width: 190px;height: 300px;border: 1px solid #d1dbe5;margin-left: 40px;font-size: 12px;line-height: 30px">
                            <input type="text" class="form-control" style="width: 65%;font-size: 12px;height: 30px;margin-left: 15px">
                            <span class="input-group-btn" style="float: right">
                                <button type="button" class="btn btn-default pull-left" style="height: 30px;width:50px;font-size: 12px;margin-top:-30px;margin-left: -60px">搜索</button>
                            </span>
                            <!-- dataright -->
                           <select name="select-right" id="select-right" size="getRightSize()" multiple>
                          </select>
                        </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-primary" style="height: 30px;color: #fff;font-size: 12px"  data-toggle="modal" data-target="#modal-default2" data-dismiss="modal">上一步</button>
                          <button type="button" class="btn btn-primary" style="height: 30px;color: #fff;font-size: 12px"  data-dismiss="modal">确定</button>
                          <button type="button" class="btn btn-default pull-right" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                        </div>   
                      </div>
                  </div>
      
                </div>
              </div>
            </div>            
                  <!-- /.modal-content -->
        </div>
    </section>
      
          <!-- /.content -->
        </div>
        <footer class="main-footer">
         <jsp:include page="/index/footer"></jsp:include>
        </footer>
      
      </div>

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath }/bower_components/jquery/dist/jquery.min.js"></script>
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
</script>
<!-- my js1 -->
<script src="${pageContext.request.contextPath }/dist/js/aceess_authorize/access_authorize1.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../../js/common/common.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath }/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/dist/js/demo.js"></script>

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
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath }/plugins/iCheck/icheck.min.js"></script>

<script src="${pageContext.request.contextPath }/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()
    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, locale: { format: 'MM/DD/YYYY hh:mm A' }})
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>
<script type="text/javascript">
function show()
{var value = document.getElementById("div1").style.display;
if(value=="none")
{document.getElementById("div1").style.display="block";
 document.getElementsByClassName("search-top")[0].innerHTML="普通搜索";
}
else{
document.getElementById("div1").style.display="none";
document.getElementsByClassName("search-top")[0].innerHTML="高级搜索";
}
}
</script>
</body>
</html>
