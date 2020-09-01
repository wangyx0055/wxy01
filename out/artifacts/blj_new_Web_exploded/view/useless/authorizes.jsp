
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>授权管理</title>
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
              <ul class="mr-nav-like" style="list-style: none;text-align: center;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;margin-left: -10px">授权管理&nbsp;&nbsp;&nbsp;</span></div></li>
              </ul>
                <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 50px;margin-left: -10px;width: 101.6% ">
            </div>
            <!-- <div class="one" style="float: right;">杨两份</div> -->

            <!-- /.box-header -->
            <div class="box-body" style="margin-top: -33px">
               <div class="input-group margin">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: -2px">
                    <option>自动识别</option>
                    <option>授权策略</option>
                    <option>顺序</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                  <i class="glyphicon glyphicon-search"> <span class="glyphicon glyphicon-search"></span></i>
                  <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
                 <div style="float: right;margin-left: 50px;margin-top: -40px">
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-primary" style="float: left;margin-left:10px;height: 30px;color: #fff;font-size: 12px;">新建</button>   
                 </div>
              <div class="box-body pad">
                  <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                    <thead>
                       <tr>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">顺序</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">授权策略</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">状态</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>1</td>
                        <td><a href="${pageContext.request.contextPath }/shouquan/shouquan">1</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            正常</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>2</td>
                        <td><a href="${pageContext.request.contextPath }/shouquan/shouquan">1</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            正常</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>3</td>
                        <td><a href="${pageContext.request.contextPath }/shouquan/shouquan">1</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            正常</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>4</td>
                        <td><a href="${pageContext.request.contextPath }/shouquan/shouquan">1</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            正常</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>5</td>
                        <td><a href="${pageContext.request.contextPath }/shouquan/shouquan">1</td>
                        <td><div style="height: 23px;width:55px;background:#337ab7;text-align: center;line-height: 25px;color:#fff;border-radius: 4px">
                            正常</div></td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default1" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button></td>
                      </tr>
                    </tbody>
                  </table>
                    <br>
                    <form>
                    <input type="checkbox" class="禁用 解禁 删除" onclick="sel('chk')" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">禁用</button>
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">解禁</button>
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default8" style="right;height: 30px;margin-left: 5px;font-size: 12.5px">删除</button> 
                    </form>                        
                </div>
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
                <h3 class="modal-title">创建授权策略</h3>
              </div>
              <div class="modal-body">
                 <div style="margin-top: 10px;margin-left: 26px">
                  <p><span style="font-size: 12px;color: #505050;font-weight: bold">策略名称：</span>
                  <input type="losss" placeholder="&nbsp;&nbsp;&nbsp;" style="margin-top: -10px;width:460px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
              </div>
              
              <div style="margin-top: 20px;margin-left: 26px">
                  <span style="font-size: 12px;color: #505050">简要描述：
                  <input type="text" size="30px" style="margin-top: -10px;width: 460px;height: 30px;border-radius: 3px;border: 1px solid #ccc"></span>
              </div><br><br>
              
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                </div>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>

      <div class="modal fade" id="modal-default1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">编辑授权策略：</h3>
              </div>
              <div class="modal-body">
                 <div style="margin-top: 10px;margin-left: 26px">
                  <p><span style="font-size: 12px;color: #505050;font-weight: bold">策略名称：</span>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;text" style="margin-top: -10px;width:460px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
              </div>
              
              <div style="margin-top: 20px;margin-left: 26px">
                  <span style="font-size: 12px;color: #505050">简要描述：
                  <input type="text" size="30px" style="margin-top: -10px;width: 460px;height: 30px;border-radius: 3px;border: 1px solid #ccc"></span>
              </div><br><br>
              
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px">确定</button>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
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
                  <p>您确定要删除全部的授权策略吗？</p>
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
<script>
  function test(){
    alert(document.getElementById("test").value);
  }

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
