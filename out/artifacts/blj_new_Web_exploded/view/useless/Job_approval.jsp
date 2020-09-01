<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>工单审批 | teleprot</title>
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
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">命令授权工单&nbsp;&nbsp;&nbsp;</span></div></li>
              <li style="float: left;"><button  class="btn btn-default pull-left" data-dismiss="modal" onclick="location.reload();" style="right;height: 30px;margin-left: 5px;font-size: 12.5px" ><i class="fa fa-fw fa-repeat"></i>&nbsp;刷新列表</button></li>
              </ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
            <!-- <div class="one" style="float: right;">杨两份</div> -->

            <!-- /.box-header -->
            <div class="box-body" style="margin-top: -22px">
               <div class="input-group margin">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: -2px">
                    <option>工单号</option>
                    <option>申请内容</option>
                    <option>创建者</option>
                    <option>待审批</option>
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
                  <span style="margin-left: 160px">申请内容:</span>
                  <span style="margin-left: 145px">创建者:</span>
                  <span style="margin-left: 154px">待审批:</span>
                  <span style="margin-left: 160px">资源账户:</span>
                  </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入工单号" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入申请内容" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入创建者" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入待审批" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源账户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                 </div>
                 <div style="margin-top: 8px">
                  <span style="margin-left: 20px">资源名称:</span>
                  <span style="margin-left: 146px">起始申请时间:</span>
                  <span style="margin-left: 118px">截止申请日期:</span>
                 </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入资源名称" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
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
                        <th><span style="font-size: 14px;font-weight: lighter">执行命令</span></th>
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
                        <td>动态授权</td>
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
                        <td>动态授权</td>
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
                        <td>动态授权</td>
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
                <button type="button" class="btn btn-primary" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px;margin-top: -10px" data-toggle="modal" data-target="#modal" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 480px;margin-top: -30px">取消</button>
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
                <h3 class="modal-title">删除</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要删除命令授权工单“11”吗？</p>
                </div><br>          
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" style="float: left;margin-left:460px;height: 30px;color: #fff;font-size: 12px" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
    </section>

    <!-- /.content -->
  </div>
  <footer class="main-footer">
    <jsp:include page="/index/footer"></jsp:include>
  </footer>
  <div class="control-sidebar-bg"></div>
</div>

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
