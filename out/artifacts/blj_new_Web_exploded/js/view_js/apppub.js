window.onload=function(){
	 var url = window.location.href;
         $('.fa_asset').addClass('active');
         $('.asset_treeview').addClass('active menu-open');
     
}

$(function () {
    //获取当前显示文档的完整的URL(字符串)
    var sHref = window.location.href;
    //将字符串以？分割成数组
    var args = sHref.split('?');
    //判断数组的第一个元素是否==完整的url地址
    if (args[0] === sHref) {
        //如果==，说明没有可查询的参数，则返回一个空串，函数执行结束
        return "";
    }
    //将args的第二个元素(字符串)以 & 分割成数组
    var arr = args[1].split('&');
    //设置空对象
    var obj = {};
    for (var i = 0; i < arr.length; i++) {
        //将arr中的每个元素以 = 分割成数组
        var arg = arr[i].split('=');
        //将arg 的 第一个元素作为key值，第二个元素作为value值，存储在obj对象中
        obj[arg[0]] = arg[1];
    }
    let id = obj[arg[0]];
    var apppub_server_id = id;
//正则判断
let regexp = {
    name: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}$/,
    password: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}$/,
    username:  /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,32}$/,
    ip:  /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/,
    desc: /(^$)|(^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,64}$)/
};

//失去焦点，移出输入框
$('#edit_name').blur(function () {
    if($("#edit_name").val()==""){
        $('#judge_name').text("请输入应用名称");
    }else if (!regexp.name.test($("#edit_name").val())){
        $('#judge_name').text("请输入有效的应用名称");
    }else{
        checkName();
    }
});
    $('#depart_name').blur(function () {
    if($("#depart_name").val()==""){
        $('#VdepartName').text("请选择部门");
    }
    });
$('#edit_username').blur(function () {
  // if ($("#edit_username").val()!="") {
  //       $('#judge_username').text("请输入用户名");
  //   }else
    if(!regexp.username.test($("#edit_username").val())){
      $('#judge_password').text("请输入有效的用户名");
  }else {
        $('#judge_username').text("");
    }
});
$('#edit_password').blur(function (){
   if ($("#edit_password").val()===""){
        $('#judge_password').text("请输入密码");
    }else if(!regexp.password.test($("#edit_password").val())){
       $('#judge_password').text("请输入有效的密码");
   } else{
        $('#judge_password').text("");
    }
});
$('#edit_password1').blur(function (){
    if($('#edit_password1').val()==""){
        $('#judge_password1').text("请输入确认密码");
    }else if ($("#edit_password").val()!=""&&$("#edit_password").val()!=$("#edit_password1").val()){
        $('#judge_password1').text("输入的密码不一致");
    }else{
        $('#judge_password1').text("");
    }
});
$('#edit_desc').blur(function () {
    if ($("#edit_desc").val()!=""&&!regexp.desc.test($("#edit_desc").val())) {
        $('#judge_desc').text("超过限制长度");
    }else{
        $('#judge_desc').text("");
    }
});
// 获取焦点，重新输入
    $('#edit_name').focus(function () {
        $('#judge_name').text('');
    });
    $('#edit_username').focus(function () {
        $('#judge_username').text('');
    });
    $('#depart_name').focus(function () {
        $('#VdepartName').text('');
    });
    $('#edit_password').focus(function () {
        $('#judge_password').text('');
        $('#judge_password1').text('');
    });
    $('#edit_password1').focus(function () {
        $('#judge_password1').text('');
    });
    $('#edit_url').focus(function () {
        $('#judge_ip').text('');
    });
    $('#edit_desc').focus(function () {
        $('#judge_desc').text('');
    });

//检查重名
function checkName() {
    $.ajax({
        url: "../../apppubAccount/checkName",
        type: "POST",
        data: {
            id:$('#edit_id').val(),
            name: $('#edit_name').val()
        },
        success: function (data) {
            if (!data.success) {
                $('#judge_name').text('应用名称重复');
                return false;
            }
        }
    })
}
//发布应用的显示
function AutoSearch() {
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
            url:"../../apppubAccount/queryByappserver",
            data:{
                apppub_server_id:apppub_server_id,
                type:$('#Distinguish').val(),
                sname:$('#searchId').val().trim()
            },
        },
        "columns": [
            { "data": "id" ,"render": function(data){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "name" },
            { "data": "username" },
            { "data": "depart_name" },
            { "data": "appprogramname"},
          /*  { "data": "desc","render":function (data) {
                    return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
                }},*/
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
AutoSearch();
$('#search').click(function () {
    AutoSearch();
})
//新建和编辑应用的回显
$('#modal-editapp').on('show.bs.modal', function (event) {
    $('#judge_desc').text('');
    $('#judge_name').text('');
    $('#VdepartName').text('');
    $('#judge_username').text('');
    $('#judge_password').text('');
    $('#judge_password1').text('');
    $('#judge_url').text('');
    $('#judge_ip').text('');
    let button = $(event.relatedTarget);
    if(button.data('row')!=undefined&&button.data('row')!=null){
        $('.modal-title').text('编辑应用');
        let i = button.data('row');
        $('#edit_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_name').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#edit_username').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].username);
        $('#depart_name').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#depart_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].department);
        $('#edit_url').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].url);
        $('#edit_desc').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].desc);
        $('#edit_apppub').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].apppub_program_id);
        $('#edit_password').val("******");
        $('#edit_password1').val("******");
    }else{
        $('.modal-title').text('添加应用');
        $('#edit_id').val('');
        $('#edit_name').val('');
        $('#edit_username').val('');
        $('#edit_password').val("");
        $('#edit_password1').val("");
        $('#edit_url').val('');
        $('#edit_desc').val('');
        $('#edit_apppub').val('1');
        $('#depart_name').val('');
        $('#depart_id').val(1);
    }
});

//新建和编辑应用的操作
$('#editButton').click(function(){
    let url = '';
    if ($('#edit_id').val() !== ''){
        url = "../../apppubAccount/editApppubAccount";
    }else{
        url = "../../apppubAccount/addApppubAccount";
    }
    setTimeout(()=> {
        let flag = true;
        checkName();
        if($("#edit_name").val()==""){
            $('#judge_name').text("请输入应用名称");
            flag=false;
        }else if (!regexp.name.test($("#edit_name").val())){
            $('#judge_name').text("请输入有效的应用名称");
            flag=false;
        }
        if($('#judge_name').text()!=""){
            flag=false;
        }
        if($("#depart_name").val()==""){
            $('#VdepartName').text("请选择部门");
            flag=false;
        }
        if ($("#edit_password").val()!=""&&!regexp.password.test($("#edit_password").val())) {
            $('#judge_password').text("请输入有效的密码");
            flag=false;
        }
        if ($("#edit_password").val()!=""&&$("#edit_password").val() != $("#edit_password1").val()){
            $('#judge_password1').text("输入的密码不一致");
            flag=false;
        }
        if (!regexp.desc.test($("#edit_desc").val())) {
            $('#judge_desc').text("超过限制长度");
            flag=false;
        }
        if(flag) {
            $.ajax({
                url: url,
                type: "POST",
                data: {
                    id: $('#edit_id').val(),
                    name: $('#edit_name').val(),
                    username: $('#edit_username').val(),
                    department: $('#depart_id').val(),
                    password: $('#edit_password').val(),
                    apppub_program_id: $('#edit_apppub').val(),
                    apppub_server_id: apppub_server_id,
                    url: $('#edit_url').val(),
                    desc: $('#edit_desc').val()
                },
                success: function (data) {
                    if (data.success) {
                        if ($('#edit_id').val() == "") {
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
                        if ($('#edit_id').val() === "") {
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
//删除的回显
$('#modal-delapp').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#del_id').val($('#apppub').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//删除
$('#delButton1').click(function(){
    $.ajax({
        url:"../../apppubAccount/delApppubAccount",
        type:"POST",
        data:{
            ids: new Array($('#del_id').val()),
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
$('#delAllButton').click(function(){
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
});
//导出表格
$('#export').click(function(){
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#queryPsw').val(),
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
                    $("#PswRep").text(data.msg)
                }
                loadAJAX('#apppub');
            }
        },
        error:function(){

        }
    })
});
$('#delButton').click(function(){
    $.ajax({
        url:"../../apppubAccount/delApppubAccount",
        type:"POST",
        data:{
            ids: new Array($('#del_id').val())
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

        }
    })
});
function test(){
    alert(document.getElementById("test").value);
}

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
            	$('#edit_apppub').append('<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>');
            }
        }
    }
});
//获取部门列表
$.ajax({
    url:"../../department/findAll",
    type:"POST",
    contentType:'application/json',
    async: false,
    data: JSON.stringify({"helpId":"hello"}),//随便往后台传一个值
    success:function(result){
        if(result!=null){
            $("#depart_name").click(function() {
                var options = {
                    levels : 2,
                    data : result.data,
                    onNodeSelected : function(event, data) {
                        $("#depart_name").val(data.text);
                        $("#depart_id").val(data.id);
                        $("#tree").hide();//选中树节点后隐藏树
                    }
                };
                $('#tree').treeview(options);
            });
        }
    },
});
