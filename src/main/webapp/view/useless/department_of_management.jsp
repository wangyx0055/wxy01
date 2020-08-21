<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>部门</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- my css -->
    <link rel="stylesheet" href="../../bower_components/dist/css/main/main.css">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../bower_components/dist/css/AdminLTE.min.css">
    <!-- daterange picker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- Bootstrap Color Picker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../bower_components/dist/css/skins/_all-skins.min.css">
  <script src="../../js/html5shiv.min.js"></script>
  <script src="../../js/respond.min.js"></script>
  
  <link rel="stylesheet"
        href="../../bower_components/treeview/font.css">

</head>
<body class="hold-transition skin-blue sidebar-mini"  style="overflow-y: hidden;">
<div class="wrapper" >
  <header class="main-header">
  <jsp:include page="/index/header"></jsp:include>
    <!-- Logo -->
    <!-- Header Navbar: style can be found in header.less -->
  </header>
   <aside class="main-sidebar">
   <jsp:include page="/index/menu"></jsp:include>
   
    <!-- /.sidebar -->
  </aside>
  <div class="content-wrapper" >
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="box-shadow: none;border-top: none">
            <div class="box-header">
              <span style="float: left;margin-top: 9px;margin-left: 5px" class="glyphicon glyphicon-list"></span>
              <ul class="mr-nav-like" style="list-style: none;margin-top: 5px">
              <li style="float: left;"><div style="margin-top: 3px"><span style="font-size: 17px;;margin-left: -10px;">部门&nbsp;&nbsp;&nbsp;</span></div></li>
              </ul>
            </div>
            <hr style="border-top:solid 1px #e9e9e9 ;margin-top: 4px;margin-left: -10px;width: 101.6% ">
           <div class="box-body" style="margin-top: -22px">
               <div class="input-group margin">
                  <select class="form-control" style="width: 32%;height:30px;font-size: 12px;margin-left: -2.7%">
                    <option>部门名称</option>
                    <option>部门名称</option>
                  </select>
                  <input type="text" class="form-control" style="width: 40%;font-size: 12px;height: 30px">
                  <span class="input-group-btn" style="float: left;">
                    <button type="button" class="btn btn-default pull-left" style="height: 30px;font-size: 12px;">搜索</button>
                  </span>
                </div>
                    <div style="text-align: right;">
                      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-default2" style="right;height: 30px;font-size: 12px;margin-top: -5%">新建</button> 
                    </div>
                </div>
                <div style="margin-top: -18px">
              <table   class="table table-bordered table-hover" style="font-size: 12.5px">
                <thead>
                       <tr>
                        <th style="width: 40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></th>
                        <th style="width: 700px"><span style="font-size: 14px;font-weight: lighter">部门</span></th>
                        <th style="width: 50px"><span style="font-size: 14px;font-weight: lighter">用户数</span></th>
                        <th style="width: 50px"><span style="font-size: 14px;font-weight: lighter">主机数</span></th>
                       </tr>
                    </thead>
                    <tbody>
                      <tr id="Tbl_1" onclick="show(this.id)">
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td ><span>总部</span></td>
                        <td>2</td>
                        <td>1</td>
                      </tr>
                      <tr  id="Tbl_1_1" onclick="show(this.id)">
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td><span style="margin-left: 3%">销售部</span></td>
                        <td>2</td>
                        <td>1</td>
                      </tr>
                      <tr id="Tbl_1_1_2" onclick="show(this.id)">
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td><span style="margin-left: 5%">市场部</span></td>
                        <td>2</td>
                        <td>1</td>
                      </tr>
                      <tr id="Tbl_1_2" onclick="show(this.id)">
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"></td>
                        <td><span style="margin-left: 3%">技术部</span></td>
                        <td>2</td>
                        <td>1</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
        </div>
      </div> 
        <div class="modal fade" id="modal-default2">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #f7f7f7">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">新建部门</h3>
              </div>
              <div style="margin-left: 100px;margin-top: 10px">
                  <div style="margin-top: 10px">
                  <span style="font-size: 12px;color: #505050;font-weight: bold;">上级部门：</span>
                  <select name="lo" style="width: 210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;">
                    <option>总部:</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;销售部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市场部</option>
                    <option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术部</option>
                   
                  </select>
              </div>
              </div>
                <div style="margin-top: 20px;margin-left: 100px">
                  <p><span style="font-size: 12px;color: #505050;font-weight: bold">部门名称：</span>
                  <input type="text" style="margin-top: -10px;width:210px;height: 30px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <div style="margin-top: 25px;margin-left: 100px">
                  <p><span style="font-size: 12px;color: #505050">部门描述：</span>
                  <input type="text"style="margin-top: -10px;width:210px;height: 60px;border-radius: 3px;border: 1px solid #ccc;font-size: 12px;color: #ccc"></p>
                </div>
                <br><br><br>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" style="float: left;margin-left:420px;height: 30px;color: #fff;font-size: 12px">确定</button>
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="right;height: 30px;font-size: 12px;margin-left: 10px">取消</button>
                </div> 
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
    </section>
    <!-- /.content -->
  
  <footer class="main-footer">
    <jsp:include page="/index/footer"></jsp:include>
  </footer>
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../bower_components/plugins/jQueryUI/jquery.js"></script>
<script src="../../bower_components/plugins/jQueryUI/jquery-ui.min.js"></script>
<script src="../../js/common/common.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="../../pluginsi/sinput-mask/jquery.inputmask.js"></script>
<script src="../../pluginsi/sinput-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../../pluginsi/sinput-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="../../bower_components/moment/min/moment.min.js"></script>
<script src="../../bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="../../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="../../bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="../../pluginsi/stimepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="../../pluginsi/siCheck/icheck.min.js"></script>

<script src="../../bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath }/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>

<script src="${pageContext.request.contextPath }/dist/js/demo.js"></script>
<!-- FastClick -->
<script>  
function show(sid)
{
  var trs = document.getElementsByTagName('tr');
  for(var i=0; i<trs.length; i++){
    if(trs[i].id.substring(0,sid.length+1)==sid+'_'){
      if(trs[i].style.display=='none'){
        trs[i].style.display='table-row'
      }else{
        trs[i].style.display='none'
      }
    }
  }
}
</script>
</html>
</body>
</html>