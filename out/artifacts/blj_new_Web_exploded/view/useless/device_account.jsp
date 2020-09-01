<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>设备账号 | teleprot</title>
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
    <!-- Logo -->
    <!-- Header Navbar: style can be found in header.less -->
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
              <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">账号列表&nbsp;&nbsp;&nbsp;</span></div></li>
           
              <div class="pull-right box-tools">
                    <div style="text-align: right;"> 
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-primary"  style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12px">新建</button>
                  </div>
                </div> </ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
            <div class="box-body" style="margin-top: -22px">
              <div class="input-group margin">
                  <select class="form-control" style="width: 32%;height:30px;font-size: 12px;margin-left: -2.7%">
                    <option>自动识别</option>
                    <option>账号组</option>
                    <option>成员账号</option>
                  </select>
                  <input type="text" class="form-control" style="width: 40%;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span></div>
              <table id="example2" class="table table-hover" style="font-size: 12.5px">
                <thead>
                <tr>
                  <td style="width:30px"><input type="checkbox"></td>
                  <td><span style="font-size: 14px">账号组
                      </span></th>
                  <td><span style="font-size: 14px">成员数</span></td>
                  <td><span style="font-size: 14px">成员账号</span></td>
                  <td><span style="font-size: 14px">状态</span></td>
                  <td><span style="font-size: 14px">操作</span></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td><input type="checkbox"></td>
                  <td>group1</td>
                  <td>3</td>
                  <td>13123232</td>
                  <td><div style="height: 23px;width:55px;background:#e1e1e1;text-align: center;line-height: 25px;color:#aaa;border-radius: 4px">
                  离线</div></td>
                  <td><button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                    <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px "><span style="margin-left: -5px">删除</span></button>
               </td>
                </tr>
               <tr>
                  <td><input type="checkbox"></td>
                  <td>group1</td>
                  <td>2</td>
                  <td>13123232</td>  
                   <td><div style="height: 23px;width:55px;background:#e1e1e1;text-align: center;line-height: 25px;color:#aaa;border-radius: 4px">
                  离线</div></td>
                  <td><button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>
                    <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px "><span style="margin-left: -5px">删除</span></button>
                </td>
                </tr>
              </table>
             <form>
                    <input type="checkbox" class="禁用 解禁 删除" style="float: left;margin-left: 8px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">删除</button>   
                     
                 </form>
            </div>
          </div>
        </div>
      </div>
      <div class="modal fade" id="modal-primary">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">创建用户账号</h3>
              </div>
              <div class="modal-body">
                <div style="margin-left: 50px">
                  <span style="font-size: 12px;color: #505050;font-weight: bold;">角色：</span>
                  <select name="lo" style="width: 80px;height: 30px;border-radius: 3px;border: 1px solid #ccc;background-color: #DDDDDD">
                  <option>系统管理员</option>
                  <option>审计员</option>
                   </select>
                </div>
                <div style="margin-top: 20px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050;font-weight: bold">账号：</span>
                  <input type="text" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050">姓名：</span>
                  <input type="text" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 41px">
                  <p><span style="font-size: 13.5px;color: #505050">email：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;电子邮箱地址" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050">电话：</span>
                  <input type="text"  style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 56px">
                  <p><span style="font-size: 13px;color: #505050">QQ：</span>
                  <input type="text"  style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 20px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050">微信：</span>
                  <input type="text" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div> 
                <div style="margin-top: 24px;margin-left: 50px">
                  <span style="font-size: 12px;color: #505050">备注：
                  <input type="text" size="30px" style="text-indent:2px;margin-top: -10px;width: 450px;height: 50px;border-radius: 3px;border: 1px solid #ccc"></span>
                </div><br>
                <div>
                  <p style="margin-left: 20px;font-size: 13px">认证方式：<span style="color: #999;font-size: 14px">&nbsp;&nbsp;当前使用系统默认设置</span></p>
                  <input type="checkbox" checked="checked" style="margin-left: 95px" /><span style="color: #3374b0">使用系统默认设置</span>
                  <hr style="border: 0;border-bottom:1px dashed #c4c4c4;margin-left: 95px;margin-top: 5px;width: 450px"> 
                  <div style="margin-top: -15px">
                    <input type="checkbox" checked="checked" style="margin-left: 95px" disabled="disabled"/><span style="color: #747474;">用户名&nbsp;+&nbsp;密码&nbsp;+&nbsp;验证码</span><br>
                    <input type="checkbox" checked="checked" style="margin-left: 95px" disabled="disabled"/><span style="color: #747474;">用户名&nbsp;+&nbsp;密码&nbsp;+&nbsp;身份认证器动态密码</span>
                  </div><br>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button> 
              </div>    
            </div>
          </div>
            <!-- /.modal-content -->
      </div>
          <!-- /.modal-dialog -->
       <div class="modal fade" id="modal-default1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding:10px">编辑：</span>
              </div>
              <div class="modal-body">
                 <div style="margin-top: 10px;margin-left: 50px">
                  <p><span style="color:red;font-size:20px;vertical-align:-20%;margin-right:-2px;">*</span>
                  <span style="font-size: 12px;color: #505050">名称：</span>
                  <input type="text" style="margin-top: -10px;width:190px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px"><span style="color: #999;margin-left: 10px;font-size: 12px">最大20个字符或64个英文字符</span></p>
                 </div>
                 <div style="margin-top: 20px;margin-left: 60px">
                  <span style="font-size: 12px;color: #505050">描述：
                  <input type="text" size="30px" style="margin-top: -10px;width: 360px;height: 30px;border-radius: 3px;border: 1px solid #ccc"></span>
                 </div><br><br>  
                <div class="modal-footer">
                   <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                   <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12px">确定</button>
                 </div>
              </div>
            <!-- /.modal-content -->
            </div>
          <!-- /.modal-dialog -->
          </div>
        </div>
      <div class="modal fade" id="modal-default">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <span class="modal-title" style="font-size:16px;padding：10px">确认</span>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要删除指定设备分组吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 420px">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"  style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12px">确定</button>   
              </div>
            </div>
            <!-- /.modal-content -->
        </div>
          <!-- /.modal-dialog -->
      </div>   
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 <footer class="main-footer">
    <jsp:include page="/index/footer"></jsp:include>
  </footer>
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
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/dist/js/demo.js"></script>
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
