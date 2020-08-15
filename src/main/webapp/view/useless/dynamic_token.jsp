<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>动态令牌</title>
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
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<style>
  .one{
    float: right;
    height: 20px;
    width: 250px;
    background-color: #d3d3d3;
  }
  </style>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
  
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
          <div class="box" style="box-shadow: none;border-top: none;">
            <div class="box-header">
              <span style="float: left;margin-top: 9px;margin-left: 5px" class="glyphicon glyphicon-list"></span>
              <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">动态令牌&nbsp;&nbsp;&nbsp;</span></div></li>           </ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
            <!-- <div class="one" style="float: right;">杨两份</div> -->

            <!-- /.box-header -->
            <div class="box-body" style="margin-top: -23px">
              <div class="input-group margin">
                  <select class="form-control" style="width: 100px;height:30px;font-size: 12px;margin-left: -2.7px">
                    <option>令牌标识</option>
                    <option>关联用户</option>
                    <option>签发用户</option>
                  </select>
                  <input type="text" class="form-control" style="width: 130px;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                  <button type="button"  onClick="show()" value="显示"  class="btn btn-default pull-left  search-top" style="height: 30px;font-size: 12px;margin-left:55px">高级搜索</button>
                </div>
                <div style="float: right;margin-left: 50px;margin-top: -40px">
                    <div style="text-align: right;">
                      <button type="button" class="btn btn-default pull-left" data-toggle="modal"  data-target="#modal-default"  data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-right: 10px">导入</button> 
                     <button type="button" class="btn btn-default pull-left" data-toggle="modal"  data-target="#modal-default1"  data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-right: 10px">签发</button> 
                     <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="height: 30px;font-size: 12px">导出</button> 
                   </div>
                </div>
              <div id="div2" style="display:none;font-size: 13px">
                  <div>
                  <span style="margin-left: 20px">令牌标识:</span>
                  <span style="margin-left: 145px">关联用户:</span>
                  <span style="margin-left: 145px">签发用户:</span>
                  <span style="margin-left: 145px">起始签发时间:</span>
                  <span style="margin-left: 120px">截止签发时间:</span>
                  </div>
                  <div>
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入令牌标识" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入关联用户址" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="text" placeholder="&nbsp;&nbsp;&nbsp;请输入签发用户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入资源账户" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                  <input type="date" placeholder="&nbsp;&nbsp;&nbsp;请输入用户登录名" style="margin-top: 4px;width: 180px;height: 28px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc;margin-left: 20px ">
                 </div>
                </div>
              <table id="example2" class="table table-bordered table-hover" style="font-size: 12.5px">
                <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></th>
                        <th><span style="font-size: 14px;font-weight: lighter">令牌标识</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">秘钥</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">关联用户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">签发用户</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">签发时间</span></th>
                        <th><span style="font-size: 14px;font-weight: lighter">操作</span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>losss</td>
                        <td>losss</td>
                        <td>346523254</td>
                        <td>losss</td>
                        <td>20:42</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left"  data-toggle="modal" data-target="#modal-default2" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">吊销</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>losss</td>
                        <td>losss</td>
                        <td>346523254</td>
                        <td>losss</td>
                        <td>20:42</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default2" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">吊销</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>losss</td>
                        <td>losss</td>
                        <td>346523254</td>
                        <td>losss</td>
                        <td>20:42</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default2" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">吊销</span></button></td>
                      </tr>
                      <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk"></td>
                        <td>losss</td>
                        <td>losss</td>
                        <td>346523254</td>
                        <td>losss</td>
                        <td>20:42</td>
                        <td>
                        <button type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#modal-default2" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 10px;font-size: 12px"><span style="margin-left: -5px">吊销</span></button></td>
                      </tr>
                    </tbody>
                  </table>
                  <form>
                    <input type="checkbox"  onclick="sel('chk')" class="吊销" style="float: left;margin-left: 28px;margin-top: 9px">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal" data-toggle="modal" data-target="#modal-default4" style="right;height: 30px;margin-left: 10px;font-size: 12.5px">吊销</button>   
                     
                 </form>
              </div>
            </div>
        </div>
      </div>
  <div class="modal fade" id="modal-default">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                  <h3 class="modal-title">导入</h3>
              </div>
              <div style="margin-left: 100px;margin-top: 25px;font-size: 14px">
                <span>下载模板:
                <button type="button" class="btn btn-primary pull-right" style="margin-right:350px;height: 30px;color: #fff;font-size: 12px;margin-top: -5px">点击下载</button></span>
              </div><br>
              <div style="margin-left: 100px;margin-top: 25px;font-size: 14px">
                <span>上传文件:
                <button type="button" class="btn btn-primary pull-right" style="margin-right:350px;height: 30px;color: #fff;font-size: 12px;margin-top: -5px">点击上传</button></span>
              </div><br>
                <div class="modal-footer">
                   <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px">确定</button>
                   <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
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
                <h3 class="modal-title">签发令牌标识</h3>
              </div>
              <div class="modal-body">
                 <div style="margin-top: 10px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050;font-weight: bold">令牌标识：</span>
                  <input type="text" style="margin-top: -10px;width:190px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"><span style="color: #999;margin-left: 10px;font-size: 14px">长度为1-128个汉字或字符</span></p>
                 </div>
              
                 <div style="margin-top: 20px;margin-left:74px">
                   <span style="font-size: 12px;color: #505050">秘钥：</span>
                   <input type="text" size="30px" style="margin-top: -10px;width: 190px;height: 30px;border-radius: 3px;border: 1px solid #ccc">
                 </div>
                 <div style="margin-top: 20px;margin-left: 50px">
                  <p><span style="font-size: 12px;color: #505050">关联用户：</span>
                      <select class="form-control" style="width: 190px;height:30px;font-size: 12px;margin-left: 63px;margin-top: -25px">
                         <option>sdada</option>
                         <option>沙发床</option>
                         <option>发</option>
                         <option>仍无法</option>
                      </select></p>
                 </div><br>  
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
        <div class="modal fade" id="modal-default2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">吊销</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要吊销动态令牌“losss”吗？</p>
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
        <div class="modal fade" id="modal-default4">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">吊销</h3>
              </div>
                <div style="margin-top: 15px;margin-left: 170px">
                  <p>您确定要吊销全部的动态令牌吗？</p>
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
function show()
{var value = document.getElementById("div2").style.display;
if(value=="none")
{document.getElementById("div2").style.display="block";
 document.getElementsByClassName("search-top")[0].innerHTML="普通搜索";
}
else{
document.getElementById("div2").style.display="none";
document.getElementsByClassName("search-top")[0].innerHTML="高级搜索";
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
</body>
</html>
