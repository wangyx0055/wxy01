var _deviceList = function(field, value){
	$('#deviceList').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'			: 't<"bottom"lifp<"clear">>',
        'searching'   : false,
        'orderable'    : false,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"  : true,
        "ajax": {
            "url":"../../deviceAccount/listDeviceAccount",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
            },
        },
        "columns": [
            { "data": "id"},
            { "data": "ip" },
            { "data": "device_name" },
            { "data": "groupname" },
            { "data": "typename" },
            { "data": "protocolname" },
            { "data": "username" },
            { "data": "id", "render": function(data,type,row,meta){
                    return  '<A data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal-default3" style="margin-right:10px;cursor:pointer;">查看密钥</A>'+
                    '<A data-toggle="modal" data-row="'+meta.row+'"  class="newcss1" data-target="#modal-default1" style="cursor:pointer;">下载密码</A>';
                }}
        ]
    });
	
}
_deviceList('search','')

var _apppubList = function(field, value){
    $('#apppubList').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'			: 't<"bottom"lifp<"clear">>',
        'searching'   : false,
        'orderable'    : false,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"  : true,
       "ajax": {
            "url":"../../apppubAccount/listApppubAccount",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
            },
        },
        "columns": [
            { "data": "id" },
            { "data": "name" },
            { "data": "appservername" },
            { "data": "username" },
            { "data": "appprogramname" },
            { "data": "id", "render": function(data,type,row,meta){
                    return  '<A data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-default6" style="margin-right:10px;cursor:pointer;">查看密钥</A>'+
                    '<A data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal-default5" style="cursor:pointer;">下载密码</A>';
                }}
        ]
    });
}
_apppubList('search','')

$('#downpassList').DataTable({
    'paging'      : true,
    "iDisplayLength": 10,
    'lengthChange': true,
    "lengthMenu": [
        [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
    ],
    'dom'			: 't<"bottom"lifp<"clear">>',
    'searching'   : false,
    'ordering'    : true,
    'info'        : true,
    'autoWidth'   : false
});
$('#checkList').DataTable({
    'paging'      : true,
    "iDisplayLength": 10,
    'lengthChange': true,
    "lengthMenu": [
        [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
    ],
    'dom'			: 't<"bottom"lifp<"clear">>',
    'searching'   : false,
    'ordering'    : true,
    'info'        : true,
    'autoWidth'   : false
});
$('#modal-default1').on('show.bs.modal', function (event){
    $('#admin_pass').val("");
    $("#Vadmin_pass").text("");
});
$('#admin_pass').focus(function () {
    $("#Vadmin_pass").text("");
});
$('#modal-default5').on('show.bs.modal', function (event){
    $('#admin_pass2').val("");
    $("#Vadmin_pass2").text("");
});
$('#admin_pass2').focus(function () {
    $("#Vadmin_pass2").text("");
});
$('#login').click(function(){
 /*   if(!checkPa())return false;*/
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#admin_pass').val(),
        },
        success:function(data){
            if(data.success){
                $('#downFile')[0].click();
                $('#modal-default1').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('下载失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#deviceList');
                }else {
                    $("#Vadmin_pass").text(data.msg);
                }
            }
        },
        error:function(){}
    })
    });
$('#login2').click(function(){
    /*   if(!checkPa())return false;*/
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#admin_pass2').val(),
        },
        success:function(data){
            if(data.success){
                $('#downFile3')[0].click();
                $('#modal-default5').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('下载失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#apppubList');
                }else {
                    $("#Vadmin_pass2").text(data.msg);
                }
            }
        },
        error:function(){}
    })
});
//daochu1
$('#pull_out').click(function(){
    $('#downFile1')[0].click();
});
//daochu2
$('#pull_out2').click(function(){
    $('#downFile4')[0].click();
});

//daying
$("#print_pa").click(function () {
    alert("未发现可驱动的密码打印机");
});
$("#print_pa2").click(function () {
    alert("未发现可驱动的密码打印机");
});
//查看秘钥
$('#modal-default3').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);// Button that triggered the modal
    i = button.data('row');
    $('#device_account_id').val($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].id)
    $('#ip2').html($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].ip);
    $('#name2').html($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].name);
    $('#account_count2').html($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].account_count);
    $('#protocal2').html($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].protocolname);
    $('#os_type2').html($('#deviceList').DataTable().row('#' + i).nodes(i).data()[i].typename);
    $("#Vadmin_id").text("");
    $("#Vmanage_password").text("");
    $("#admin_id").val("");
    $("#manage_password").val("");
});


$("#check_pa").click(function (){
    var flag=true;
    if (flag){
        $.ajax({
            url:"../../export/checkDevicePa",
            type:"POST",
            data:{
                username: $('#admin_id').val(),
                password: $('#manage_password').val(),
                device_account_id: $('#device_account_id').val()
            },
            success:function(data){
                if(data.success){
                    $('#modal-default4').modal("show");
                    $('#modal-default3').modal("hide");
                    $('#now_pa').text(data.password);
                }
                else{
                    $("#Vadmin_id").text(data.msg2);
                    $("#Vmanage_password").text(data.msg1);
                }
            },
            error:function(){}
        })
    }
});
$("#admin_id").focus(function () {
    $("#Vadmin_id").text("");
});

$("#manage_password").focus(function () {
    $("#Vmanage_password").text("");
});
//查看秘钥2
$('#modal-default6').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);// Button that triggered the modal
    i = button.data('row');
    $('#appIp').html($('#apppubList').DataTable().row('#' + i).nodes(i).data()[i].id);
    $('#app_name').html($('#apppubList').DataTable().row('#' + i).nodes(i).data()[i].name);
    $('#app_count2').html($('#apppubList').DataTable().row('#' + i).nodes(i).data()[i].desc);
  /*  $('#original_app').html($('#apppubList').DataTable().row('#' + i).nodes(i).data()[i].protocolname);*/
    $("#Vmanage_password2").text("");
    $("#Vadmin_id2").text("");
    $("#admin_id2").val("");
    $("#manage_password2").val("");
});

$("#check_pa2").click(function (){
    var flag=true;
    if (flag){
        $.ajax({
            url:"../../export/checkDevicePa",
            type:"POST",
            data:{
                username: $('#admin_id2').val(),
                password: $('#manage_password2').val(),
                app_account_id:$('#app_account_id').val()
            },
            success:function(data){
                if(data.success){
                    $('#modal-default7').modal("show");
                    $('#modal-default6').modal("hide");
                }
                else{
                    $("#Vadmin_id2").text(data.msg2);
                    $("#Vmanage_password2").text(data.msg1);
                }
            },
            error:function(){}
        })
    }
});
$("#admin_id2").focus(function () {
    $("#Vadmin_id2").text("");
});

$("#manage_password2").focus(function () {
    $("#Vmanage_password2").text("");
});
