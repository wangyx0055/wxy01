if(window.location.href.indexOf('toprogram')>0){
    $('#program').click();
}
//应用服务器的正则
let regexp = {
    name: /^\S{1,32}$/,
    port: /^[0-9]{1,65535}$/,
    ip: /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/,
    ip_yu:/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/,
    desc: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!./#$%*+,=_?$]){0,64}$/
}
// 获取焦点，重新输入
$('#edit_ser_name').focus(function () {
    $('#judge_name').text('');
});
$('#edit_ser_ip').focus(function () {
    $('#judge_ip').text('');
});
$('#edit_ser_port').focus(function () {
    $('#judge_port').text('');
});
$('#depart_name').focus(function () {
    $('#VdepartName').text('');
});
$('#edit_ser_name').focus(function () {
    $('#judge_name').text("");
});
$('#edit_ser_desc').focus(function () {
    $('#judge_desc').text('');
});
// //解构赋值,用来判断确定的disabled的属性
// let [r_name,r_ip,r_port,r_desc,r_all] = [true,true,true,true,true,];
//失去焦点，移出输入框
$('#edit_ser_name').blur(function () {
    if($("#edit_ser_name").val()==""){
        $('#judge_name').text("请输入服务器名称");
    } else if (!regexp.name.test($("#edit_ser_name").val())) {
        $('#judge_name').text("请输入正确的服务器名称");
    }else {
        checkSerName();
    }
});
$('#edit_ser_ip').blur(function () {
    if($("#edit_ser_ip").val()==""){
        $('#judge_ip').text("请输入服务器地址");
    }else if (regexp.ip.test($("#edit_ser_ip").val())) {
        $('#judge_ip').text("");
    }else if(regexp.ip_yu.test($("#edit_ser_ip").val())){
        $('#judge_ip').text("");
    }else {
        $('#judge_ip').text("请输入正确的服务器地址");
    }
    checkappIP();
});
$('#edit_ser_port').blur(function () {
    if($("#edit_ser_port").val()==""){
        $('#judge_port').text("请输入端口");
    }else if ($("#edit_ser_port").val()<1||$("#edit_ser_port").val()>65535||!regexp.port.test($("#edit_ser_port").val())) {
        $('#judge_port').text("请输入1-65535之间的有效数字");
    }
});
$('#edit_ser_desc').blur(function () {
    if ( $("#edit_ser_desc").val() !== ""&&!regexp.desc.test($("#edit_ser_desc").val())) {
        $('#judge_desc').text("超过限制长度");
    }
});

//检查服务器名称重名
function checkSerName(){
    $.ajax({
        url: "../../apppubServer/checkName",
        type: "POST",
        data: {
            id:$('#edit_ser_id').val(),
            name: $('#edit_ser_name').val()
        },
        success: function (data){
            if(!data.success){
                $('#judge_name').text('服务器名称重复');
            }
        }
    });
}

//检查服务器地址重名
function checkappIP(){
    $.ajax({
        url: "../../apppubServer/checkIp",
        type: "POST",
        data: {
            id:$('#edit_ser_id').val(),
            ip: $('#edit_ser_ip').val()
        },
        success: function (data){
            if(!data.success){
                $('#judge_ip').text('服务器地址重复');
            }
        }
    });
}
//服务器提交总验证
function checkApp(){
    var flag=true;
    var ser_name=/^\S{1,32}$/;
    var ser_ip= /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var ser_ip2=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    var ser_port= /^[0-9]{1,65535}$/;
    var ser_desc=/^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!./#$%*+,=_?$]){0,64}$/;
    if($("#edit_ser_name").val()==""){
        $('#judge_name').text("请输入服务器名称");
        flag=false;
    }else if (!ser_name.test($("#edit_ser_name").val())){
        $('#judge_name').text("请输入正确的服务器名称");
        flag=false;
    }
    if($("#edit_ser_ip").val()==""){
        $('#judge_ip').text("请输入服务器地址");
        flag=false;
    }else if(ser_ip.test($("#edit_ser_ip").val())) {
        $('#judge_ip').text("");
        flag=true;
    }else if(ser_ip2.test($("#edit_ser_ip").val())) {
        $('#judge_ip').text("");
        flag=true;
    }else if ($('#judge_name').text()!=""){
        $('#judge_ip').text("请输入正确的服务器地址");
        flag=false;
    }
    if($("#depart_name").val()==""){
        $('#VdepartName').text("请输入部门");
        flag=false;
    }
    if($("#edit_ser_port").val()==""){
        $('#judge_port').text("请输入端口");
        flag=false;
    } else if ($("#edit_ser_port").val()<1||$("#edit_ser_port").val()>65535||!ser_port.test($("#edit_ser_port").val())){
        $('#judge_port').text("请输入1-65535之间的有效数字");
        flag=false;
    }
    if($('#judge_ip').text()!=""){
        flag=false;
    }
    if ( $("#edit_ser_desc").val() !== ""&&!ser_desc.test($("#edit_ser_desc").val())) {
        $('#judge_desc').text("超过限制长度");
        flag=false;
    }
  return flag;
}
//根据条件搜索,默认自动识别
$(function () {
    //应用服务器的搜索和显示
    function AutoSearch(){
        $('#appserver').DataTable({
            'paging': true,
            "iDisplayLength": 10,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'ordering': false,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            "destroy":true,
            "ajax": {
                url: "../../apppubServer/listApppubServer",
                data:{
                    type:$('#Distinguish').val(),
                    sname:$('#searchId').val().trim(),
                }
            },
            "columns": [
                {
                    "data": "id", "render": function (data, type, row, meta) {
                        return '<input type="checkbox" name="chk[]" value=' + data + '>';
                    }
                },
                {"data": "name"},
                {"data": "ip"},
                {"data": "depart_name", "render" : function(data, type, row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';
                    }},
                {"data": "port","render":function (data) {
                        return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
                }},
                {
                    "data": "id", "render": function (data, type, row, meta) {
                        return '<a data-toggle="modal" data-row="' + meta.row + '" data-target="#modal-editserver" class="newcss1" style="margin-left: 20px;cursor:pointer;">编辑</a>' +
                            '<a data-toggle="modal" data-row="' + meta.row + '" data-target="#modal-delserver" class="newcss2" style="margin-left: 20px;cursor:pointer;">删除</a>';
                    }
                }
            ],
            "fnDrawCallback": function() {
                //全选删除的全选和反全选
                let os=document.getElementsByName('chk[]');
                let allSelects = document.getElementsByTagName('form')[0].getElementsByTagName('input')[0];
                for (let i = 0; i <os.length ; i++) {
                    os[i].addEventListener("click",function () {
                        let count = 0;
                        for (let j = 0; j <os.length ; j++) {
                            if (os[j].checked==true){
                                count++
                            }
                        }
                        allSelects.checked=(count == os.length)
                    })
                }
                //提示工具
                $('#appserver div').tooltip()
            }
        });
    }
    AutoSearch();
    $('#search').click(function () {
        AutoSearch();
    });

    //发布应用的显示
    function AutoSearch1() {
        $('#apppub').DataTable({
            'paging'      : true,
            "iDisplayLength": 10,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],

            'dom'         :'t<"bottom"lifp<"clear">>',
            'searching'   : false,
            'ordering'    : false,
            'info'        : true,
            'autoWidth'   : false,
            "serverSide"	: true,
            'destroy' :true,
            "ajax": {
                url:"../../apppubAccount/listApppubAccount",
                data:{
                    type:$('#Distinguish1').val(),
                    sname:$('#searchId1').val().trim()
                },
            },
            "columns": [
                { "data": "id" ,"render": function(data){
                        return '<input type="checkbox" name="chk[]" value='+data+'>';
                    }},
                { "data": "appservername" },
                { "data": "depart_name" ,
                    "render" : function(data, type,row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';
                    }},
                { "data": "name" },
                { "data": "appprogramname"},
                { "data": "username" },
                { "data": "url" },
                { "data": "desc","render":function (data) {
                          return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
                      }},
                { "data": "id", "render": function(data,type,row,meta){
                        return '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-editapp" class="newcss1" style="cursor:pointer">编辑</a> '+
                            '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-delapp" class="newcss2" style="cursor:pointer;margin-left: 20px">删除</a> ';
                    }}
            ],
            "fnDrawCallback": function() {
                //全选删除的全选和反全选
                let os=document.getElementsByName('chk[]');
                let allSelects = document.getElementsByTagName('form')[0].getElementsByTagName('input')[0];
                for (let i = 0; i <os.length ; i++) {
                    os[i].addEventListener("click",function () {
                        let count = 0;
                        for (let j = 0; j <os.length ; j++) {
                            if (os[j].checked==true){
                                count++
                            }
                        }
                        allSelects.checked=(count == os.length)
                    })
                }
                //提示工具
                $('#apppub div').tooltip()
            }
        });
    }
    AutoSearch1();
    $('#search1').click(function () {
        AutoSearch1();
    })
})
//应用服务器的添加和编辑显示
$('#modal-editserver').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    if(button.data('row')!=undefined&&button.data('row')!=null){
        $('.modal-title').text('编辑应用服务器');
        let i = button.data('row');
        console.log($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].port);
        $('#edit_ser_id').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_ser_name').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#depart_name').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#depart_id').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].department);
        $('#edit_ser_ip').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].ip);
        $('#edit_ser_port').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].port);
        $('#edit_ser_desc').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].desc);
        $('#judge_name').text('');
        $('#judge_ip').text('');
        $("#judge_port").text('');
        $('#judge_desc').text('');
        $('#VdepartName').text('');
    }else{
        $('.modal-title').text('添加应用服务器');
        $('#edit_ser_id').val('');
        $('#edit_ser_name').val('');
        $('#edit_ser_ip').val('');
        $("#edit_ser_port").val('3389');
        $('#edit_ser_desc').val('');
        $('#judge_name').text('');
        $('#judge_ip').text('');
        $("#judge_port").text('');
        $("#VdepartName").text('');
        $('#judge_desc').text('');
        $('#depart_name').val(topNode.text);
        $('#depart_id').val(topNode.id);
    }
});

$('#downTemplate').click(function(){
    $('#downClick')[0].click();

});


//上传点击事件
function openFile() {
  $('#Vfile').text();
  $('#btn_file').click();

  $('#btn_file').change(function(){
      var file = $("#btn_file").val();
      var fileName = getFileName(file);
      function getFileName(o){
          var pos=o.lastIndexOf("\\");
          return o.substring(pos+1);
      }
      $("#filename").html(fileName);
  })

}

$("#upload").on("click", function () {
  var s = $('#btn_file')[0].files[0];
  if(!s){
      $('#Vfile').text('请上传文件');
      return;
  }

  var formData = new FormData();
  formData.append("file_data", s);
  formData.append("type", 0);
  $("#modal-upload .modal-title").text('状态');
  $("#modal-upload .modal-body").text('正在导入...');
  $("#modal-upload").modal();
  $.ajax({
      url: "../../upload/apppubserver",
      type: 'POST',
      cache: false,
      data: formData,
      processData: false,
      contentType: false,
      textEncoding: 'GBK',
      success: function (data) {
          if (data.success){
              $("#modal-default2").modal('hide');
              $("#modal-upload").modal('hide');
              $("#modal-success .modal-title").text('成功');
              $("#modal-success .modal-body").text('导入成功!');
              $("#modal-success").modal();
              loadAJAX('#appserver');

          }else {
              $("#modal-upload").modal('hide');
              $("#modal-danger .modal-title").text('失败');
              $("#modal-danger .modal-body").text('导入失败!');
              $("#modal-danger").modal();
          }
      },
      error: function () {

      }
  });
});

//应用服务器的添加和编辑操作
$('#editButton1').click(function(){
    var flag=checkApp();
    if(!flag)return false;
    var  url = "../../apppubServer/addApppubServer";
    if ($('#edit_ser_id').val() !== ''){
        url = "../../apppubServer/editApppubServer";
    }
    $.ajax({
        url:url,
        type:"POST",
        data:{
            id:$('#edit_ser_id').val(),
            name:$('#edit_ser_name').val(),
            ip:$('#edit_ser_ip').val(),
            department:$('#depart_id').val(),
            port:$('#edit_ser_port').val(),
            desc:$('#edit_ser_desc').val(),
            type:0
        },
        success:function(data){
            if(data.success) {
                if ($('#edit_ser_id').val()== "") {
                    $('#modal-editserver').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                } else {
                    $('#modal-editserver').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                }
                loadAJAX('#appserver');
            }
            else{
                if ($('#edit_ser_id').val() === "") {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('新建失败!');
                    $("#modal-danger").modal();
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
                loadAJAX('#appserver');
            }
        },
        error:function(){
            $("#modal-danger .modal-body").text('失败!');
        }
    })
});
//删除
$('#modal-delserver').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#del_ser_id').val($('#appserver').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//全选删除
$('#delAllButton1').click(function(){
    let obj = document.getElementsByName('chk[]');
    let ids= new Array();
    for (let i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    console.log(ids);
    if(ids.length==0){
        $("#modal-hint.modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#appserver');
        return false;
    }
    $.ajax({
        url:"../../apppubServer/delApppubServer",
        type:"POST",
        data:{
            ids:ids
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#appserver');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#appserver');
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
            loadAJAX('#appserver');
        }
    })
});
$('#delButton1').click(function(){
    $.ajax({
        url:"../../apppubServer/delApppubServer",
        type:"POST",
        data:{
            ids: new Array($('#del_ser_id').val()),
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#appserver');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#appserver');
            }
        },
        error:function(){

        }
    })
});

/*第二部分 应用发布*/
//正则判断
let regexp_1 = {
    name: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}$/,
    password: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}$/,
    username:  /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}$/,
    ip:  /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/,
    desc: /(^$)|(^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,64}$)/
};

// 获取焦点，重新输入
$('#apppub_depart_name').focus(function () {
    $('#apppub_VdepartName').text('');
});
$('#edit_app_name').focus(function () {
    $('#judge_app_name').text('');
});
$('#edit_username').focus(function () {
    $('#judge_username').text('');
});
$('#edit_password').focus(function () {
    $('#judge_password').text('');
});
$('#edit_password1').focus(function () {
    $('#judge_password1').text('');
});
$('#edit_url').focus(function () {
    $('#judge_url').text('');
});
$('#apppub_edit_desc').focus(function () {
    $('#apppub_judge_desc').text('');
});

//失去焦点，移出输入框
// $('#apppub_depart_name').blur(function () {
//     if($("#apppub_depart_name").val()==""){
//         $('#apppub_VdepartName').text("请选择部门");
//     }
// });

$('#edit_app_name').blur(function () {
    if($("#edit_app_name").val()==""){
        $('#judge_app_name').text("请输入应用名称");
    }else if (!regexp_1.name.test($("#edit_app_name").val())){
        $('#judge_app_name').text("请输入有效的应用名称");
    }else{
        checkName();
    }
});
$('#edit_username').blur(function () {
    if(!regexp_1.username.test($("#edit_username").val())){
        $('#judge_username').text("请输入有效的用户名");
    }else {
        $('#judge_username').text("");
    }
});
$('#edit_password').blur(function (){
    if(!regexp_1.password.test($("#edit_password").val())){
        $('#judge_password').text("请输入有效的密码");
    } else{
        $('#judge_password').text("");
    }
});
$('#edit_password1').blur(function (){
    if($('#edit_password1').val()==""&&$("#edit_password").val()!=""){
        $('#judge_password1').text("请输入确认密码");
    }else if ($("#edit_password").val()!=""&&$("#edit_password").val()!=$("#edit_password1").val()){
        $('#judge_password1').text("输入的密码不一致");
    }else{
        $('#judge_password1').text("");
    }
});
$('#edit_url').blur(function () {
    if ($("#edit_url").val()!=""&&!regexp_1.desc.test($("#edit_url").val())) {
        $('#judge_url').text("超过限制长度");
    }else{
        $('#judge_url').text("");
    }
});
$('#apppub_edit_desc').blur(function () {
    if ($("#apppub_edit_desc").val()!=""&&!regexp_1.desc.test($("#apppub_edit_desc").val())) {
        $('#apppub_judge_desc').text("超过限制长度");
    }else{
        $('#apppub_judge_desc').text("");
    }
});

//检查重名
function checkName() {
    $.ajax({
        url: "../../apppubAccount/checkName",
        type: "POST",
        data: {
            id:$('#apppub_edit_id').val(),
            name: $('#edit_app_name').val()
        },
        success: function (data) {
            if (!data.success) {
                $('#judge_app_name').text('应用名称重复');
                return false;
            }
        }
    })
}

//应用发布提交总验证
function checkApppub(){
    var flag=true;
    if($("#edit_app_name").val()==""){
        $('#judge_app_name').text("请输入应用名称");
        flag=false;
    }else if (!regexp_1.name.test($("#edit_app_name").val())){
        $('#judge_app_name').text("请输入有效的应用名称");
        flag=false;
    }
    if ($('#judge_app_name').text()!=""){
        flag=false;
    }
    if(!regexp_1.username.test($("#edit_username").val())){
        $('#judge_username').text("请输入有效的用户名");
        flag=false;
    }
    if(!regexp_1.password.test($("#edit_password").val())){
        $('#judge_password').text("请输入有效的密码");
        flag=false;
    }
    if($('#edit_password1').val()==""&&$("#edit_password").val()!=""){
        $('#judge_password1').text("请输入确认密码");
        flag=false;
    }else if ($("#edit_password").val()!=""&&$("#edit_password").val()!=$("#edit_password1").val()){
        $('#judge_password1').text("输入的密码不一致");
        flag=false;
    }
    if ($("#edit_url").val()!=""&&!regexp_1.desc.test($("#edit_url").val())) {
        $('#judge_url').text("超过限制长度");
        flag=false;
    }
    if ($("#apppub_edit_desc").val()!=""&&!regexp_1.desc.test($("#apppub_edit_desc").val())) {
        $('#apppub_judge_desc').text("超过限制长度");
        flag=false;
    }
    return flag;
};

//新建和编辑应用发布的操作
$('#editButton').click(function(){
    let url = '';
    if ($('#apppub_edit_id').val() !== ''){
        url = "../../apppubAccount/editApppubAccount";
    }else{
        url = "../../apppubAccount/addApppubAccount";
    }
    setTimeout(()=> {
        let flag = checkApppub();
        if(flag) {
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    id: $('#apppub_edit_id').val(),
                    name: $('#edit_app_name').val(),
                    username: $('#edit_username').val(),
                    department: $('#apppub_depart_id').val(),
                    password: $('#edit_password').val(),
                    apppub_program_id: $('#edit_app').val(),
                    apppub_server_id: $('#apppub_edit_ser_name').val(),
                    url: $('#edit_url').val(),
                    desc: $('#apppub_edit_desc').val()
                },
                success: function (data) {
                    if (data.success) {
                        if ($('#apppub_edit_id').val() == "") {
                            $('#modal-editapp').modal('hide');
                            $("#modal-success .modal-title").text('成功');
                            $("#modal-success .modal-body").text('新建成功!');
                            $("#modal-success").modal();
                        } else {
                            $('#modal-editapp').modal('hide')
                            $("#modal-success .modal-title").text('成功');
                            $("#modal-success .modal-body").text('编辑成功!');
                            $("#modal-success").modal();
                        }
                        loadAJAX('#apppub');
                    } else {
                        if ($('#apppub_edit_id').val() === "") {
                            $("#modal-danger .modal-title").text('失败');
                            $("#modal-danger .modal-body").text('新建失败!');
                            $("#modal-danger").modal();
                        } else {
                            $("#modal-danger .modal-title").text('失败');
                            $("#modal-danger .modal-body").text('编辑失败!');
                            $("#modal-danger").modal();
                        }
                        loadAJAX('#apppub');
                    }
                }

            }, 100)
        }
    })
});
//新建和编辑应用的回显
$('#modal-editapp').on('show.bs.modal', function (event) {
    $('#apppub_VdepartName').text('');
    $('#judge_app_name').text('');
    $('#judge_username').text('');
    $('#judge_password').text('');
    $('#judge_password1').text('');
    $('#judge_url').text('');
    $('#apppub_judge_desc').text('');
    let button = $(event.relatedTarget);
    if(button.data('row')!=undefined&&button.data('row')!=null){
        $('.modal-title').text('编辑应用发布');
        let i = button.data('row');
        $('#apppub_edit_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#apppub_edit_ser_name').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].apppub_server_id);
        $('#apppub_depart_name').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#apppub_depart_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].department);
        $('#edit_app_name').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#edit_username').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].username);
        $('#edit_url').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].url);
        $('#apppub_edit_desc').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].desc);
        $('#edit_app').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].apppub_program_id);
        $('#edit_password').val("******");
        $('#edit_password1').val("******");
    }else{
        $('.modal-title').text('新增应用发布');
        $('#apppub_edit_id').val('');
        $('#apppub_edit_ser_name option:first').prop("selected");
        $('#apppub_depart_name').val('');
        $('#apppub_depart_id').val('');
        $('#edit_app option:first').prop("selected");
        $('#edit_app_name').val('');
        $('#edit_username').val('');
        $('#edit_password').val("");
        $('#edit_password1').val("");
        $('#edit_url').val('');
        $('#apppub_edit_desc').val('');
    }
});

//删除的回显
$('#modal-delapp').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#del_apppub_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//删除
$('#delButton2').click(function(){
    $.ajax({
        url:"../../apppubAccount/delApppubAccount",
        type:"POST",
        data:{
            ids: new Array($('#del_apppub_id').val()),
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#apppub');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#apppub');
            }
        }
    })
});
//全选删除
$('#delAllButton2').click(function(){
    let obj = document.getElementsByName('chk[]');
    let ids= new Array();
    for (let i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    if(ids.length==0){
        $("#modal-hint.modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#apppub');
        return false;
    }
    $.ajax({
        url:"../../apppubAccount/delApppubAccount",
        type:"POST",
        data:{
            ids:ids
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#apppub');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#apppub');
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
            loadAJAX('#apppub');
        }
    })
});

//导出应用服务器表格
$('#export').click(function(){
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#queryPsw').val(),
        },
        success:function(data){
            if(data.success){
                window.open("../../export/exprotApppubServer");
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('导出成功!');
                $("#modal-success").modal();
                $('#modal-default2').modal('hide');
                loadAJAX('#appserver');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep").text(data.msg)
                }
                loadAJAX('#appserver');
            }
        },
        error:function(){

        }
    })
})
//导出应用发布表格
$('#export1').click(function(){
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#queryPsw1').val(),
        },
        success:function(data){
            if(data.success){
                window.open("../../export/exprotApppubApp");
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('导出成功!');
                $("#modal-success").modal();
                $('#modal-dao').modal('hide');
                loadAJAX('#apppub');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep1").text(data.msg)
                }
                loadAJAX('#apppub');
            }
        },
        error:function(){

        }
    })
});

//获取服务器列表
$.ajax({
    url:"../../apppubServer/listApppubServer",
    type:"POST",
    data:{
        start:0,
        length:100,
        type:1,
    },
    success:function(data){
        if (data.success){
            for (var i = 0; i < data.data.length; i++){
                $('#apppub_edit_ser_name').append('<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>');
            }
        }
    }
});

//获取应用程序列表
$.ajax({
    url:"../../apppubProgram/listApppubProgram",
    type:"POST",
    data:{
        start:0,
        length:100,
        type:1,
    },
    success:function(data){
        if (data.success){
            for (var i = 0; i < data.data.length; i++){
                $('#edit_app').append('<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>');
            }
        }
    }
});

//点击空白关闭tree控件
$('#modal-editserver').click(function(event){
    $('#tree').css("display","none");
});
$('#modal-editapp').click(function(event){
    $('#apppub_tree').css("display","none");
});
$('#depart_name').click(function (event) {
    stopPropagation(event);
    $('#tree').css("display","block");
});
$('#apppub_depart_name').click(function (event) {
    stopPropagation(event);
    $('#apppub_tree').css("display","block");
});
function stopPropagation(e) {
    e = e || window.event;
    if(e.stopPropagation) { //W3C阻止冒泡方法
        e.stopPropagation();
    } else {
        e.cancelBubble = true; //IE阻止冒泡方法
    }
}

//设置默认父节点
let topNode = {id:0,text:''};
//获取部门列表
$.ajax({
    url:"../../department/findAll",
    type:"POST",
    contentType:'application/json',
    async: false,
    data: JSON.stringify({"helpId":"hello"}),//随便往后台传一个值
    success:function(result){
        if(result!=null){
            topNode.id = result.data[0].id;
            topNode.text = result.data[0].text;
            $("#depart_name").click(function() {
                var options = {
                    levels : 1,
                    data : result.data,
                    collapseIcon:"treegrid-expander treegrid-expander-expanded",
                    expandIcon:'treegrid-expander treegrid-expander-collapsed',
                    onNodeSelected : function(event, data) {
                        $("#depart_name").val(data.text);
                        $("#depart_id").val(data.id);
                        $("#tree").hide();//选中树节点后隐藏树
                    }
                };
                $('#tree').treeview(options);
                $('#tree').click(function (event) {
                    stopPropagation(event);
                });
            });
            $("#apppub_depart_name").click(function() {
                var options = {
                    levels : 1,
                    data : result.data,
                    collapseIcon:"treegrid-expander treegrid-expander-expanded",
                    expandIcon:'treegrid-expander treegrid-expander-collapsed',
                    onNodeSelected : function(event, data) {
                        $("#apppub_depart_name").val(data.text);
                        $("#apppub_depart_id").val(data.id);
                        $("#apppub_tree").hide();//选中树节点后隐藏树
                    }
                };
                $('#apppub_tree').treeview(options);
                $('#apppub_tree').click(function (event) {
                    stopPropagation(event);
                });
            });
        }
    },
});
