<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>历史会话 | teleprot</title>
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
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">历史会话&nbsp;&nbsp;&nbsp;</span></div></li>
              <li><button  class="btn btn-default pull-left" data-dismiss="modal" onclick="location.reload();" style="right;height: 30px;margin-left: 5px;font-size: 12.5px" ><i class="fa fa-fw fa-repeat"></i>&nbsp;刷新列表</button></li>
            
             </ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
            <!-- <div class="one" style="float: right;">杨两份</div> -->

            <!-- /.box-header -->
            <div class="box-body" style="margin-top: -22px">
               <div class="input-group margin">
                  <select class="form-control" style="width: 32%;height:30px;font-size: 12px;margin-left: -2.7%">
                    <option>资源名称</option>
                    <option>主机地址</option>
                    <option>应用地址</option>
                    <option>资源账户</option>
                    <option>用户</option>
                    <option>来源IP</option>
                  </select>
                  <input type="text" class="form-control" style="width: 40%;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                  <button type="button"  onClick="show()" value="显示" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;margin-left:55px ">高级搜索</button>
                </div>
                 <div class="pull-right box-tools">
                    <div style="text-align: right;margin-top: -60%"> 
                      <button type="button" class="btn btn-default pull-left" style="right;height: 30px;font-size: 12px;margin-right: 2px"><i class="fa fa-fw fa-arrow-circle-down"></i>&nbsp;导出</button>

                  </div>
                </div>
                <div id="div1" style="display:none;font-size: 13px">
                  <div>
                  <span style="margin-left: 20px">资源名称:</span>
                  <span style="margin-left: 145px">主机地址:</span>
                  <span style="margin-left: 145px">应用地址:</span>
                  <span style="margin-left: 145px">资源账户:</span>
                  <span style="margin-left: 145px">用户:</span>
                  </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源名称" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入主机地址" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入应用地址" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源账户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入用户登录名" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                 </div>
                 <div style="margin-top: 8px">
                 <span style="margin-left: 20px">来源IP:</span>
                  <span style="margin-left: 160px">起始时间:</span>
                  <span style="margin-left: 145px">截止时间:</span>
                  <span style="margin-left: 145px">会话时长范围:</span>
                  <span style="margin-left: 120px">操作指令:</span>
                 </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入来源IP" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入起始时间" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入截止时间" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入会话时长范围" style="margin-top: 4px;width: 88px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入会话时长范围" style="margin-top: 4px;width: 88px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 5px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入操作指定" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                 </div>
                 <div style="margin-top: 8px">
                 <span style="margin-left: 20px">双人授权:</span>
                  <span style="margin-left: 145px">双人授权用户:</span>
                  <span style="margin-left: 120px">会话协同:</span>
                  <span style="margin-left: 145px">会话协同用户:</span>
                 </div>
                  <div>
                    <select name="lo" style="width: 180px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 13px;margin-left: 20px"> 
                  <option   style="font-size: 13px" selected>&nbsp;&nbsp;是</option>
                  <option style="font-size: 13px">&nbsp;&nbsp;否</option>
                   </select>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入双人授权用户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <select name="lo" style="width: 180px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 13px;margin-left: 20px"> 
                  <option   style="font-size: 13px" selected>&nbsp;&nbsp;是</option>
                  <option style="font-size: 13px">&nbsp;&nbsp;否</option>
                   </select>
                   <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入会话协同用户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
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
                        <th><span style="font-size: 14px;font-weight: lighter">资源名称</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">类型</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">主机/应用地址</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">资源账户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">用户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">来源IP</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">起止时间</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">会话时长</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">结束状态</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td>RDP</td>
                        <td>123.35.24</td>
                        <td>adcadv</td>
                        <td>lvcs</td>
                        <td>61.148.245</td>
                        <td>2019-1-2 20:00</td>
                        <td>00:01:39</td>
                        <td>正常结束</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">详情</span></button>
                        <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">播放</span></button>
                      <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">下载</span></button></td>
                      </tr>
                     <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td>RDP</td>
                        <td>123.35.24</td>
                        <td>adcadv</td>
                        <td>lvcs</td>
                        <td>61.148.245</td>
                        <td>2019-1-2 20:00</td>
                        <td>00:01:39</td>
                        <td>正常结束</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">详情</span></button>
                        <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">播放</span></button>
                      <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">下载</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td>losss</td>
                        <td>RDP</td>
                        <td>123.35.24</td>
                        <td>adcadv</td>
                        <td>lvcs</td>
                        <td>61.148.245</td>
                        <td>2019-1-2 20:00</td>
                        <td>00:01:39</td>
                        <td>正常结束</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">详情</span></button>
                        <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">播放</span></button>
                      <button type="button" class="btn btn-default pull-left" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">下载</span></button></td>
                      </tr>
                    </tbody>
                  </table>
              </div>
            </div>
        </div>
      </div>
    <div class="modal fade" id="modal-default">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">编辑执行用户</h3>
          </div>
          <div class="nav-tabs-custom" style="margin-top: 1px">
              <ul class="nav nav-tabs">
                 <li class="active"><a href="#A" data-toggle="tab">资源会话信息</a></li>
                 <li><a href="#B" data-toggle="tab">系统会话信息</a></li>
              </ul>
            <div class="tab-content">
               <div class="active tab-pane" id="A" style="height: 100%">
                  <div class="modal-body" style="height: 380px">
                      <div style="margin-left: 80px;margin-top: 10px">
                        <span style="font-size: 14px;color: #505050;">资源名称：losss</span>
                      </div>
                      <div style="margin-top: 20px;margin-left: 108px">
                        <span style="font-size: 14px;color: #505050">类型：RDP</span>
                      </div>
                      <div style="margin-left: 80px;margin-top: 20px">
                        <span style="font-size: 14px;color: #505050;">主机地址：123.35.24</span>
                      </div>
                      <div style="margin-top: 20px;margin-left: 80px">
                        <span style="font-size: 14px;color: #505050;">资源账户：adcadv</span>
                      </div>
                      <div style="margin-top: 20px;margin-left: 80px">
                        <span style="font-size: 14px;color: #505050;">起止时间：2019-11-08 16:00:05~2019-11-08 16:00:55</span>
                      </div>
                      <div style="margin-top: 20px;margin-left: 80px">
                        <span style="font-size: 14px;color: #505050;">会话时长：00:00:50</span>
                      </div>
                      <div style="margin-top: 20px;margin-left: 80px">
                        <span style="font-size: 14px;color: #505050;">会话大小：2.0KB
                        </span>
                      </div>
                     <br>
                      <div class="modal-footer">
                       <button type="button" class="btn btn-primary" style="float: left;margin-left:410px;height: 30px;color: #fff;font-size: 12px">确定</button>
                       <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                      </div>   
                   </div>
                 </div>
               <div class="tab-pane" id="B" style="height: 100%">
                   <div class="modal-body" style="height: 400px">
                     <div style="margin-left: 104px;margin-top: 10px">
                <span style="font-size: 14px;color: #505050;">用户：lvcs</span>
                 </div>
                 <div style="margin-top: 20px;margin-left: 92px">
                  <span style="font-size: 14px;color: #505050">来源IP：61.148.245</span>
              </div>
                 <div style="margin-left: 78px;margin-top: 20px">
                    <span style="font-size: 14px;color: #505050;">来源MAC：-</span>
                 </div>
              <div style="margin-top: 20px;margin-left: 76px">
                  <span style="font-size: 14px;color: #505050;">起止时间：从2019-11-08 15:43:24开始，会话未结束</span>
              </div>
              <div style="margin-top: 20px;margin-left: 76px">
                  <span style="font-size: 14px;color: #505050;">会话时长：81:19:36</span>
              </div>
              <div style="margin-top: 20px;margin-left: 76px">
                  <span style="font-size: 14px;color: #505050;">认证类型：本地</span>
              </div>
              <div style="margin-top: 20px;margin-left: 62px">
                  <span style="font-size: 14px;color: #505050;">多因子认证：-
                  </span>
              </div>
              <div style="margin-top: 20px;margin-left: 76px">
                  <span style="font-size: 14px;color: #505050;">登录方式：Web页面
              </div>
                 <br>
                   <div class="modal-footer">
                     <button type="button" class="btn btn-primary" style="float: left;margin-left:410px;height: 30px;color: #fff;font-size: 12px">确定</button>
                     <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
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
<script type="text/javascript">
function show()
{var value = document.getElementById("div1").style.display;
if(value=="none")
{document.getElementById("div1").style.display="block";
}
else
document.getElementById("div1").style.display="none";
}
</script>
</body>
</html>
