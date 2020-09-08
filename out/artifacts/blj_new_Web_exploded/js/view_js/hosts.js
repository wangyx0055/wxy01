let topNode = {id:0,text:''};
$('#down').click(function(){
    window.location.href="../../file/downSsh?file_id="+$('#file_id').val()
});
var fileInput = function(file_id){
    $("#scriptfile-input").fileinput({
        uploadUrl: "../../file/uploadSsh",
        enableResumableUpload: false,
        resumableUploadOptions: {
        },
        uploadExtraData: {
            'uploadToken': new Date().getTime(), // for access control / security
        },
        layoutTemplates: {
            actionUpload: '',//就是让文件上传中的文件去除上传按钮
            actionDelete: '',//去除删除按钮
        },
        maxFileCount: 1,
        language: 'zh',
        allowedPreviewTypes: ['html','text'],    // allow only images
        showCancel: false,
        showPreview: false,
        showUpload: false,//是否显示上传按钮
        initialPreviewAsData: true,
        overwriteInitial: false,
        initialPreviewDownloadUrl:false,
        theme: 'fas',
        deleteUrl: "../../file/deleteSsh?file_id="+file_id
    }).on('filebatchselected', function () {
        $(this).fileinput("upload");
    }).on('fileuploaded', function(event, previewId, index, fileId) {
        console.log('File Uploaded', 'ID: ' + fileId + ', Thumb ID: ' + previewId);
        $('#file_id').val(arguments[1].response['file-id']);
        $("#Vupload_file").text("");
    }).on('fileuploaderror', function(event, data, msg) {
        console.log('File Upload Error', 'ID: ' + data.fileId + ', Thumb ID: ' + data.previewId);
    }).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
        console.log('File Batch Uploaded', preview, config, tags, extraData);
    }).on('filesuccessremove', function() {
        console.log('Uploaded thumbnail successfully removed');
    })
}
var fileInput1 = function(file_id){
    $("#scriptfile-input1").fileinput({
        uploadUrl: "../../file/uploadSsh",
        enableResumableUpload: false,
        resumableUploadOptions: {
        },
        uploadExtraData: {
            'uploadToken': new Date().getTime(), // for access control / security
        },
        layoutTemplates: {
            actionUpload: '',//就是让文件上传中的文件去除上传按钮
            actionDelete: '',//去除删除按钮
        },
        maxFileCount: 1,
        language: 'zh',
        allowedPreviewTypes: ['html','text'],    // allow only images
        showCancel: false,
        showPreview: false,
        showUpload: false,//是否显示上传按钮
        initialPreviewAsData: true,
        overwriteInitial: false,
        initialPreviewDownloadUrl:false,
        theme: 'fas',
        deleteUrl: "../../file/deleteSsh?file_id="+file_id
    }).on('filebatchselected', function () {
        $(this).fileinput("upload");
    }).on('fileuploaded', function(event, previewId, index, fileId) {
        console.log('File Uploaded', 'ID: ' + fileId + ', Thumb ID: ' + previewId);
        $('#file_id1').val(arguments[1].response['file-id']);
        $("#Vupload_file1").text("");
    }).on('fileuploaderror', function(event, data, msg) {
        console.log('File Upload Error', 'ID: ' + data.fileId + ', Thumb ID: ' + data.previewId);
    }).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
        console.log('File Batch Uploaded', preview, config, tags, extraData);
    }).on('filesuccessremove', function(event, id) {
        console.log('Uploaded thumbnail successfully removed');
    });
}
//默认端口
function showAGREE(v){
	var device_port=$('#modal-editdeviceaccount .modal-title').text();
    if(v==='2'){
        document.getElementById("div20").style.display="none";
        document.getElementById("edit_account_port").value="3389";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
    }
    else if(v==='1'){
        document.getElementById("div20").style.display="block";
        document.getElementById("edit_account_port").value="22";
        document.getElementById("xing2").style.color="white";
        document.getElementById("xing3").style.color="white";
    }
    else if(v==='3'){
        document.getElementById("div20").style.display="none";
        document.getElementById("edit_account_port").value="23";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
    }
    else if(v==='4') {
        document.getElementById("div20").style.display="none";
        document.getElementById("edit_account_port").value="5901";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
    }else if(v==='5'){
        document.getElementById("div20").style.display="none";
        document.getElementById("edit_account_port").value="21";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
    }else if(v==="6"){
        document.getElementById("div20").style.display="none";
        document.getElementById("edit_account_port").value="22";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
	}
}
function showBGREE(v){
    if(v==='2'){
        document.getElementById("div22").style.display="none";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
        document.getElementById("edit_device_port").value="3389";
    }
    else if(v==='1'){
        document.getElementById("div22").style.display="block";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "white";
        document.getElementById("xing6").style.color = "white";
        $('#edit_device_ssh_key').val('1');
        document.getElementById("edit_device_port").value="22";
    }
    else if(v==='3'){
        document.getElementById("div22").style.display="none";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
        document.getElementById("edit_device_port").value="23";
    }
    else if(v==='4') {
        document.getElementById("div22").style.display="none";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
        document.getElementById("edit_device_port").value="5901";
    }else if(v==='5'){
        document.getElementById("div22").style.display="none";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
        document.getElementById("edit_device_port").value="21";
    }else if(v==="6"){
        document.getElementById("div22").style.display="none";
        document.getElementById("div55").style.display="none";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
        document.getElementById("edit_device_port").value="22";
    }
}
function  showSsh(v){
    if(v==="0"){
        $('#div23').show();
        $("#key_name").text("");
        $("#Vupload_file").text("");
        $('.btn-file>.hidden-xs').text('选择');
    }else if(v==='1'){
        $('#div23').hide();
        $("#key_name").text("");
        $("#Vupload_file").text(" ");
    }
}
function editstatus(status,id) {
    $.ajax({
        url:"../../deviceAccount/editstatus",
        type:"POST",
        data:{
            id: id,
            status: status,
        },
        success:function(){
            loadAJAX('#deviceaccount');
        }
    })
}

function switchcase(o,id){
    if(o.innerText=="启用"){
        editstatus(0,id)
    }else{
        editstatus(1,id)
    }
}

//格式判断
//清空内容
function resetC(){
    $('#edit_device_name').val('');
    $("#Vdevicename").text('');
}
var regexp = {
    name:/^([A-Za-z]|[\u4e00-\u9fa5]|\-|\@|\_||\.|[0-9]){0,32}$/,
    length:/^\S{0,32}$/,
    length_des:/^\S{0,64}$/,
    port: /^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]{1}|6553[0-5])$/,
}
//失去焦点，移出输入框
$('#edit_device_name').blur(function(){
    //组名的验证规则
    //获取输入的名字
    var username = $('#edit_device_name').val();
    //判断是否符合规则
    if(username==""){
        $('#Vdevicename').text('请输入设备名');
    }else if (!regexp.name.test(username)){
        //不符合
        $('#Vdevicename').text('设备名格式不正确');
        if (!regexp.length.test(username)){
            $('#Vdevicename').text("最长32个字符")
        }
    }else {
        //进行重复判断
        checkusername();
    }
});

// 检查重名
function checkusername(){
    $.ajax({
        url:"../../device/checkname",
        type:"POST",
        data:{
            id:$('#edit_device_id').val(),
            name:$('#edit_device_name').val(),
        },
        success:function(data){
            if(!data.success){
                $('#Vdevicename').text('设备名已存在');
            }
        }
    })
}
// 获取焦点，重新输入
$('#edit_device_name').focus(function(){
    //将提示信息置空
    $('#Vdevicename').text('');
})


//失去焦点，移出输入框
$('#edit_device_ip').blur(function(){
    checkip();
    //用户名的验证规则
  /*  var FORMATE  = /^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/;*/
    var p2 = /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    //获取输入的名字
    var ip = $('#edit_device_ip').val();
    if(ip==""){
     $('#Vip').text('请输入IP地址或域名');
    }else if (p2.exec(ip)){             //判断是否符合规则
        $('#Vip').text('');
    }else if(p3.exec(ip)){
        $('#Vip').text('');
    }else{
        //不符合
            $('#Vip').text('请输入有效的IP地址或域名');
            if (!regexp.length.test(ip)){
                $('#Vip').text("最长32个字符")
            }
    }
});

// 检查重名
function checkip(){
    $.ajax({
        url:"../../device/checkip",
        type:"POST",
        data:{
            id:$('#edit_device_id').val(),
            ip:$('#edit_device_ip').val(),
        },
        success:function(data){
            if(!data.success){
                $('#Vip').text('IP地址或域名重复');
            }
        }
    });
}
// 获取焦点，重新输入
$('#edit_device_ip').focus(function(){
    //将提示信息置空
    $('#Vip').text('');
})

//清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("")
});
// 获取焦点，重新输入
$('#queryPsw').focus(function(){
    //将提示信息置空
    $('#PswRep').text('');
});

// 失去焦点
$('#_edit_device_super_account').blur(function(){
    if ($('#edit_device_login_method').val()=='11'){
        //用户名的验证规则
        var regexp = {
            name:/^([A-Za-z]|\-|\_|[0-9]){0,32}$/,
        };
        var name = $('#_edit_device_super_account').val();
        if(!regexp.name.test(name)){
            $('#_Vsuper_account').text('账号格式不正确');
        }
    }
});
// 获取焦点
$('#_edit_device_super_account').focus(function(){
    $('#_Vsuper_account').text('');
});

// 获取焦点
$('#_edit_device_password1').focus(function(){
    $('#_Vdevice_password1').text('');
});
// 获取焦点
$('#_edit_device_password').focus(function(){
    $('#_Vdevice_password').text('');
});
// 失去焦点
$('#_edit_device_password').blur(function(){
    if ($('#edit_device_login_method').val()=='11'){
        var pwd = $('#_edit_device_password').val();
        var pwd2 = $('#_edit_device_password1').val();
        if(pwd==''){
            $('#_Vdevice_password').text('请输入密码');
        }else if(pwd2!==''&&pwd==pwd2){
            $('#_Vdevice_password1').text('');
        }
    }
});

// 失去焦点
$('#_edit_device_password1').blur(function(){
        var pwd = $('#_edit_device_password1').val();
    if ($('#edit_device_login_method').val()=='11') {
        if (pwd == '') {
            $('#_Vdevice_password1').text('请输入确认密码');
        } else if (pwd != $('#_edit_device_password').val()) {
            $('#_Vdevice_password1').text('密码不一致');
        }
    }
});
// 获取焦点
$('#edit_device_password1').focus(function(){
    $('#Vdevice_password1').text('');
});
$('#edit_device_password').focus(function(){
    $('#Vdevice_password').text('');
});

// 失去焦点
$('#edit_device_super_account').blur(function(){
    if ($('#edit_device_login_method').val()=='11'){
        //用户名的验证规则
        var regexp = {
            name:/^([A-Za-z]|\-|\_|[0-9]){0,32}$/,
        };
        var name = $('#edit_device_super_account').val();
        if(!regexp.name.test(name)){
            if ($('#edit_device_super_account').val()!=""){
                $('#Vsuper_account').text('账号格式不正确');
            }

        }
    }
});
// 获取焦点
$('#_edit_device_super_account').focus(function(){
    $('#Vsuper_account').text('');
});

$('#edit_device_description').blur(function () {
    var regexp = {
        name:/^\S{0,128}$/,
    };
    if(!regexp.name.test($('#edit_device_description').val())){
        $('#Vedit_device_description').text("超过限制长度");
    }else {
        $('#Vedit_device_description').text("");
    }
});

// 获取焦点，重新输入
$('#edit_device_description').focus(function(){
    //将提示信息置空
    $('#Vedit_device_description').text('');
})
//监听系统类型选择框
$('#edit_device_device_type').change(function () {
    if ($('#edit_device_device_type').val()!=null){
        $('#Vdevice_type').text('');
    }
})
//监听设备组选择框
$('#edit_device_group').change(function () {
    if ($('#edit_device_group').val()!=null){
        $('#Vgroup').text('');
    }
});

// 失去焦点
$('#_edit_account_username').blur(function(){
    if ($('#edit_account_login_method').val()=='33'){
        //用户名的验证规则
        var regexp = {
            name:/^([A-Za-z]|\-|\_|[0-9]){1,32}$/,
        };
        var name = $('#_edit_account_username').val();
        if(name==""){
            $('#_Vaccount_username').text('请输入设备账号');
        }else if(!regexp.name.test(name)){
            $('#_Vaccount_username').text('设备账号格式不正确');
        }else {
            checkaccountname();
        }
    }
});
// 获取焦点
$('#_edit_account_username').focus(function(){
    $('#_Vaccount_username').text('');
});

// 失去焦点
$('#edit_account_username').blur(function(){
    if ($('#edit_account_login_method').val()=='44'){
        var regexp = {
            name:/^([A-Za-z]|\-|\_|[0-9]){1,32}$/,
        };
        var name = $('#edit_account_username').val();
        if(!regexp.name.test(name)){
            if ($('#edit_account_username').val()!=""){
                $('#Vaccount_username').text('设备账号格式不正确');
            }else {
                checkaccountname1()
            }

        }
    }
});
// 获取焦点
$('#edit_account_username').focus(function(){
    $('#_Vaccount_username').text('');
});

// 检查重名
function checkaccountname1(){
    $.ajax({
        url:"../../deviceAccount/checkaccountname",
        type:"POST",
        data:{
            id:$('#edit_account_id').val(),
            username:$('#edit_account_username').val(),
            protocol_id:$('#edit_account_protocol').val(),
            device_id:$('#_edit_device_id').val(),
        },
        success:function(data){
            if(!data.success){
                $('#Vaccount_username').text('设备账号已存在');
                return false;
            }else {
                $('#Vaccount_username').text('');
                return true;
            }
        }
    })
}
// 检查重名
function checkaccountname(){
    $.ajax({
        url:"../../deviceAccount/checkaccountname",
        type:"POST",
        data:{
            id:$('#edit_account_id').val(),
            username:$('#_edit_account_username').val(),
            protocol_id:$('#edit_account_protocol').val(),
            device_id:$('#_edit_device_id').val(),
        },
        success:function(data){
            if(!data.success){
                $('#_Vaccount_username').text('设备账号已存在');
                return false;
            }else {
                $('#_Vaccount_username').text('');
            }
        }
    })
}

// 获取焦点
$('#_edit_account_password1').focus(function(){
    $('#_Vaccount_password1').text('');
});

$('#_edit_account_password').focus(function(){
    $('#_Vaccount_password').text('');
});

// 失去焦点
$('#_edit_account_password').blur(function(){
    if ($('#edit_account_login_method').val()=='33') {
        if ($('#_edit_account_password').val() == "") {
            $('#_Vaccount_password').text('请输入密码');
        }else if($('#_edit_account_password1').val()!=""&&$('#_edit_account_password').val()==$('#_edit_account_password1').val()){
            $('#_Vaccount_password1').text('');
        }
    }
});

$('#_edit_account_password1').blur(function(){
        var pwd = $('#_edit_account_password1').val();
        if($('#_edit_account_password').val()!=""&&pwd==""){
            $('#_Vaccount_password1').text('请输入确认密码');
        }else if($('#_edit_account_password').val()!=""&&pwd!=$('#_edit_account_password').val()){
            $('#_Vaccount_password1').text('密码不一致');
        }

});
// 获取焦点
$('#edit_account_password1').focus(function(){
    $('#Vaccount_password1').text('');
});
//监听协议选择框的改变
$('#edit_account_protocol').change(function () {
    $('#Vedit_account_port').text('');
    if($('#edit_account_login_method').val()=="33"){
        checkaccountname();
    }else {
        checkaccountname1();
    }

});
//协议改变端口错误提示消失
  $("#edit_device_protocol").change(function () {
    $('#Vport').text('');
});

// 失去焦点
$('#edit_device_port').blur(function(){
    if ($('#edit_device_port').val()==''){
        $('#Vport').text('端口不能为空');
    }else if (!regexp.port.test($('#edit_device_port').val())){
        $('#Vport').text('端口不符合要求');
    }
});
$('#edit_account_port').blur(function () {
    if ($('#edit_account_port').val()==''){
        $('#Vedit_account_port').text('端口不能为空');
    }else if (!regexp.port.test($('#edit_account_port').val())){
        $('#Vedit_account_port').text('端口不符合要求');
    }
})
// 获取焦点
$('#edit_device_port').focus(function(){
    $('#Vport').text('');
});

// 获取焦点
$('#edit_account_port').focus(function(){
    $('#Vedit_account_port').text('');
});

$("#depart_name").focus(function () {
    $("#VdepartName").text("");
});

function clearText() {
    $('#Vaccount_username').text("");
    $('#_Vaccount_username').text('');
    $('#Vaccount_password1').text('');
    $('#_Vaccount_password1').text('');
    $('#Vaccount_password').text('');
    $('#_Vaccount_password').text('');
    $('#edit_account_password').val('');
    $('#_edit_account_password').val('');
    $('#edit_account_password1').val('');
    $('#_edit_account_password1').val('');
    $('#_edit_account_username').val('');
    $('#edit_account_username').val('');
    $('#edit_account_port').val('22');
    $('#Vedit_account_port').text('');
    $('#edit_account_login_method').val("33");
    $("#key_name").text("");
    $("#Vupload_file").text(" ");
    document.getElementById("xing1").style.color="red";
    document.getElementById("xing2").style.color="red";
    document.getElementById("xing3").style.color="red";
    document.getElementById("div20").style.display="block";
}

//ajax
var hostList = function(field,value){
    $('#devices').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'         :'t<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : true,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"	: true,
        "destroy": true,
        "ajax": {
            "url":"../../device/listDevice",
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
            { "data": "id" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" id="checkbox_on" name="chk[]" value='+data+'>';
                }},
            { "data": "name" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "ip" },
            {"data": "typename"},
            { "data": "depart_name",
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                        +data
                        + '</div>';

                }},
            { "data": "account_count"},
            { "data": "description" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "id", "render": function(data,type,row,meta){
                    return'<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-default4" class="newcss1" style="cursor:pointer;" onclick="deviceaccount('+row.id+')">设备账号</a>'+
                        '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-editdevice" class="newcss1" style="cursor:pointer;margin-left: 10px;" onclick="showbutton();resetC()" >编辑</a> '+
                        '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-deldevice" class="newcss2" style="cursor:pointer;margin-left: 10px;">删除</a>';
                }}
        ],
        "fnDrawCallback": function( settings, json ) {
            $('#devices div').tooltip();
        }
    });
};
/* 调用user_group_list */
hostList('searchAll','');
/* 根据条件搜索 */
$('#search').click(function(){
    hostList($('#Distinguish').val(),$('#searchId').val());
})

//新建设备第一步
$("#next").click(function(){
    var flag = checkdevice();
    if(flag===false)return flag;
    $('#edit_device_login_method').val('11');
    document.getElementById("xing4").style.color = "red";
    document.getElementById("xing5").style.color = "red";
    document.getElementById("xing6").style.color = "red";
    $('#_edit_device_super_account').val('');
    $('#_edit_device_password').val('');
    $('#_edit_device_password1').val('');
    $('#depart_name').val(topNode.text);
    $('#edit_device_protocol').val('2');
    $('#edit_device_port').val('22');
    $('#_Vsuper_account').text('');
    $('#_Vdevice_password').text('');
    $('#_Vdevice_password1').text('');
    $('#Vport').text('');
    $('#file_id').val(0);
    fileInput1('');
   //系统类型为windows时协议类型为rdp
    let _device_type = $('#edit_device_device_type option:selected').text().toUpperCase();
    if (_device_type==="WINDOWS"){
        $('#edit_device_protocol').val('2');
        $('#edit_device_port').val("3389");
        $('#div55').css("display",'none');
        $('#pass3').css("display","block");
        $('#div22').css("display",'none');
        $('#pass4').css("display","block");
    } else{
        $('#edit_device_protocol').val('1');
        $('#edit_device_port').val("22");
        $('#div55').css("display",'none');
        $('#div22').css("display",'block');
        $('#edit_device_ssh_key').val('1');
    }
});
function  showSSHKEY(v){
    if(v==="0"){
        $('#div55').css("display",'block');
        $("#Vupload_file1").text("");
        $('.btn-file>.hidden-xs').text('选择');
    }else if(v==='1'){
        $('#div55').css("display",'none');
        $("#Vupload_file1").text(" ");
    }
}
//第二部_editdeviceButton
$("#_editdeviceButton").off().click(function(){
    var url = "../../device/editDevice";
    var flag=checkdevice();
    if (flag == false) return false;
        $.ajax({
            url:url,
            type:"POST",
            data:{
                id:$('#edit_device_id').val(),
                name:$('#edit_device_name').val(),
                ip:$('#edit_device_ip').val(),
                device_type:$('#edit_device_device_type').find("option:selected").val(),
                department:$('#depart_id').val(),
                description:$('#edit_device_description').val(),
            },
            success:function(data){
                if(data.success){
                    $('#modal-editdevice').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("编辑成功!");
                    $("#modal-success").modal();
                    loadAJAX('#devices');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                    loadAJAX('#devices');
                }
            },
            error:function(){

            }
        })
});
  function checkdevice(){
        checkip();
        var flag;
        let p2 = /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
        let p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        if ($("#edit_device_name").val()==""){
        $('#Vdevicename').text('请输入设备名称');
          flag=false;
      }
      if ($("#edit_device_ip").val()==""){
          $('#Vip').text('请输入IP地址或域名');
          flag=false;
      }else if (p2.exec($("#edit_device_ip").val())){   //判断是否符合规则
          $('#Vip').text('');
          flag=true;
      }else if(p3.exec($("#edit_device_ip").val())){
          $('#Vip').text('');
          flag=true;
      }/*else{
          //不符合
          $('#Vip').text('请输入有效的IP地址或域名');
          flag=false;
  }*/
/*    if ($("#edit_device_port").val()==""){
        $('#Vport').text('请输入端口');
        flag=false;
    }*/
        if($('#depart_name').val() == "") {
            $('#VdepartName').text('请选择部门');
            flag=false;
       }
        if($('#edit_device_description').val()!=""){
            var regexp = {
                name:/^\S{0,128}$/,};
            if(!regexp.name.test($('#edit_device_description').val())){
                $('#Vedit_device_description').text("超过限制长度");
                flag=false;
            }else {
                $('#Vedit_device_description').text("");
            }
        }
    if ($("#Vdevicename").text()!==""||$("#Vip").text()!=="" || $('#VdepartName').text()!==""|| $('#Vedit_device_description').text()!==""){
        flag=false;
    }
    return flag;
}

//添加下一步
$('#editdeviceButton').off().click(function(){
    // var url = "../../device/editDevice";
    // if($('#edit_device_id').val()==""){
    //     url = "../../device/addDevice";
    // }
    var super_account ="";
    var password = "";
    var password1 = "";
    flag = true;
    if ($('#edit_device_port').val()==""){
        ('#Vport').text('请输入端口');
        flag=false;
    }
    if ($('#depart_name').val()==""|| $('#VdepartName').text()!="") {
        if ($('#depart_name').val() == "") {
            $('#VdepartName').text('请选择部门');
        }
        flag=false;
    }
    if ($('#edit_device_login_method').val()=='11'){
        if ($('#_edit_device_super_account').val()==""){
            $('#_Vsuper_account').text('请输入账号');
            flag=false;
        }
    }
    password = $('#_edit_device_password').val();
    password1 = $('#_edit_device_password1').val();
    account = $('#_edit_device_super_account').val();
    if ($('#edit_device_login_method').val()==='11' && $('#edit_device_protocol').val()!=='1') {
        if (password == "" || password1 == "") {
            if (password == "") {
                $('#_Vdevice_password').text('请输入密码');
            }
            if (password1 == "") {
                $('#_Vdevice_password1').text('请输入确认密码');
            }
            flag = false;
        }
    }
    if(password!="" && password1=="" && $('#edit_device_ssh_key').val()!=='1'){
            $('#_Vdevice_password1').text('请输入确认密码');
            flag=false;
    }else if(password !== ""&&password !== password1){
            $('#_Vdevice_password1').text('密码不一致');
            flag=false;
    }
    if (($('#_Vdevice_password1').text()!="" || $('#_Vdevice_password').text()!="" || $('#_Vsuper_account').text()!="" || $('#Vport').text()!="")&& $('#edit_device_protocol').val()!=='1'){
            flag=false;
    }
    if ( $('#edit_device_ssh_key').val()==='0' && $('#edit_sshkey1')==="") {
        flag=false;
    }
    var login_method = $('#edit_device_login_method').find("option:selected").val();
    if(login_method==11){
        login_method=0;
    }else {
        login_method=1;
    }
    if (flag){
        $.ajax({
            url:"../../device/addDevice",
            type:"POST",
            data:{
                id:$('#edit_device_id').val(),
                name:$('#edit_device_name').val(),
                ip:$('#edit_device_ip').val(),
                device_type:$('#edit_device_device_type').find("option:selected").val(),
                department:$('#depart_id').val(),
                groupid:$('#edit_device_group').val(),
                description:$('#edit_device_description').val(),
                login_method:login_method,
                account:account,
                password:password,
				is_super:$('#is_super')[0].checked?1:0,
                protocol_id:$('#edit_device_protocol').find("option:selected").val(),
                port:$('#edit_device_port').val(),
                ssh_key:$('#edit_device_ssh_key').find("option:selected").val(),
                file_id:$('#file_id1').val(),
                ssh_password:$('#edit_sshkey1').val(),
            },
            success:function(data){
                if(data.success){
                    $('#modal-primary1').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("新建成功!");
                    $("#modal-success").modal();
                    loadAJAX('#devices');
                }else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg?data.msg:"新建失败!");
                    $("#modal-danger").modal();
                    loadAJAX('#devices');
                }
            },
            error:function(){

            }
        })
    }
});

//删除
$('#modal-deldevice').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    i = button.data('row');
    $('#del_device_id').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].id);
});

$('#delButton').click(function(){
    $.ajax({
        url:"../../device/delDevice",
        type:"POST",
        data:{
            ids:new Array($('#del_device_id').val()),
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                $('#modal-deldevice').modal('hide');
                loadAJAX('#devices');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
        }
    })
});

$('#delAllButton').click(function(){
    var obj = document.getElementsByName('chk[]');
    console.log(obj);
    var ids= new Array();
    for (var i in obj){
        if(obj[i].checked)
         ids.push(obj[i].value);
    }
    if(ids.length==0){
        $("#modal-hint .modal-title").text('失败');
        $("#modal-hint .modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#devices');
        return false;
    }
    $.ajax({
        url:"../../device/delDevice",
        type:"POST",
        data:{
            ids:ids
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#devices');
                $('#modal-default7').modal('hide');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
        }
    })
});

$('#modal-default4').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);// Button that triggered the modal
    i = button.data('row');
    let title_name=($('#devices').DataTable().row('#' + i).nodes(i).data()[i].name);
    console.log(title_name);
    $('#modal-default4 .modal-title').text('设备账号'+" "+" "+"["+title_name+"]");
    $('#device_id').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].id);
});

$("#newDevice").click(function(){
    $("#_editdeviceButton").hide();
    $("#next").show();
    $('#modal-editdevice .modal-title').text('新建设备');
    $('#modal-primary1 .modal-title').text('新建账号');
    $('#edit_device_id').val('');
    $('#edit_device_name').val('');
    $('#depart_id').val(topNode.id );
    $('#depart_name').val(topNode.text);
    $('#edit_device_ip').val('');
    $('#edit_device_device_type').val('51');
    $('#edit_device_group').val('');
    $('#edit_device_description').val('');
    $('#Vname').text('');
    $('#Vlogin').text('');
    $('#Vgroup').text('');
    $('#Vdevice_type').text('');
    $('#Vip').text('');
    $('#VdepartName').text('');
    $('#edit_device_ip').attr("readOnly",false);
});
/*
$('#modal-editdevice').on('show.bs.modal', function (event) {

});
*/

$('#modal-editdevice').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    if($(button).attr('data-name')=='previous'){
        return ;
    }else if(button.data('row')!=undefined&&button.data('row')!=null){
        $('#modal-editdevice .modal-title').text('编辑设备');
        $('#modal-primary1 .modal-title').text('编辑账号');
        i = button.data('row');
        $('#edit_device_id').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_device_name').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#depart_name').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#depart_id').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].department);
        $('#edit_device_ip').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].ip);
        $('#edit_device_device_type').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].device_type);
        $('#edit_device_group').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].groupid);
        $('#edit_device_description').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].description);
        $('#edit_device_login_method').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].login_method);
        $('#edit_device_super_account').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].super_account);
        $('#edit_device_password1').val('');
        $('#edit_device_password').val('');
        $('#edit_device_protocol').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].protocol_id);
        $('#edit_device_port').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].port);
        $('#edit_device_ssh_key').val($('#devices').DataTable().row('#' + i).nodes(i).data()[i].ssh_key);
        $('#Vname').text('');
        $('#Vlogin').text('');
        $('#Vgroup').text('');
        $('#Vdevice_type').text('');
        $('#Vport').text('');
        $('#Vip').text('');
        $('#edit_device_ip').attr("readOnly",true);
    }else{
    }
});
//动态加载设备组
$.ajax({
    url:"../../group/listByType",
    type:"POST",
    data:{
        type:1,
    },
    success:function(data){
        if (data.success){
            for (var i = 0; i < data.data.length; i++){
                $("#edit_device_group").append("<option  value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
            }
        }
    }
})
//动态加载系统类型
$.ajax({
    url:"../../deviceType/listType",
    type:"POST",
    success:function(data){
        if (data.success){
            for (var i = 0; i < data.data.length; i++){
                $("#edit_device_device_type").append("<option  value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
            }
        }
    }
});
//动态加载协议类型
$.ajax({
    url:"../../protocol/listProtocol",
    type:"POST",
    success:function(data){
        if (data.success){
            for (var i = 0; i < data.data.length; i++){
                $("#edit_device_protocol").append("<option  value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
                $("#edit_account_protocol").append("<option  value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
            }
        }
    }
})
//下载模版
$('#downTemplate').click(function(){
    $('#downClick')[0].click();

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
                $('#downFile')[0].click();
                $('#modal-default3').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep").text(data.msg)
                }
                loadAJAX('#devices');
            }
        }
    })
});

function showbutton(){
    $("#_editdeviceButton").show();
    $("#next").hide();
}

//清空内容
function resetFileInput(){
    $('#Vfile').text('');
    $('#btn_file').val('');
    $("#filename").text('');
}
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
$("#upload").on("click", function (){
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
        url: "../../upload/device",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.success){
                $("#modal-default2").modal('hide');
                $("#modal-upload").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text(data.msg);
                $("#modal-success").modal();
            }else {
                $("#modal-upload").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('导入失败!');
                $("#modal-danger").modal();
            }
            loadAJAX('#devices');
            setTimeout(function () {
                if (data.errorInfo.length !== 0) {
                    $("#modal-uploadInfo").modal();
                    if (data.errorInfo.length !== 0) {
                        $("#modal-uploadInfo").modal();
                        for(let item of data.errorInfo) {
                            $('#uploadError').append(item+"<br/>");
                        }
                        $('#uploadError').append("----详细请看文档和日志");
                    }
                }
            },1500);
        }
    });
});

$("#upload_file").click(function () {
    $("#key_file").click();
    stopPropagation(event);
    $("#Vupload_file").text("");
    $("#key_file").change(function(){
        var file = $("#key_file").val();
        var fileName = getFileName(file);
        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }
        $("#key_name").html(fileName);
        $("#upload_file").val(fileName);
      console.log($("#upload_file").val());
    });
});
//设备账户
function deviceaccount(device_id){
    $('#_edit_device_id').val(device_id);
    $('#deviceaccount').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'         :'t<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : true,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"	: true,
        "destroy": true,
        "ajax": {
            "url": "../../deviceAccount/queryByDeviceId",
            "type": "POST",
            "data":{
                "device_id": device_id
            },
        },
        "columns": [
            { "data": "id" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk_[]" value='+data+'>';
                }},
            { "data": "username","render": function(data,type,row,meta){
                    if (data==""){
                        return "空用户";
                    }else {
                        return data;
                    }

                }
            },
            { "data": "protocol_id","render": function(data,type,row,meta){
                    if(data==1){
                        return "SSH";
                    }else if(data==2){
                        return "RDP";
                    }else if(data==3){
                        return "TELNET";
                    }else if(data==4){
                        return "VNC";
                    }else if(data==5){
                        return "FTP";
                    }else if(data==6){
                        return "SFTP";
                    }
                    return "";
                }
            },
            { "data": "port" },
            { "data": "status", "render": function(data,type,row,meta){
                    if(data==0){
                        return "已启用";
                    }else if(data==1){
                        return "已禁用";
                    }
                }
            },
            { "data": "id", "render": function(data,type,row,meta){
                    {
                        return'<a class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-editdeviceaccount" style="line-height: 2px;cursor: pointer" onclick="clearText()">编辑</a>'+
                            '<a class="newcss1" onclick="switchcase(this,'+row.id+')" data-row="'+meta.row+'" style="line-height: 2px;margin-left: 10px;cursor: pointer">' + (row.status == 0?'禁用':'启用')+'</a> '+
                            '<a href="../../Operator/testDeviceLogin?device_account_id='+data+'" target="_blank" class="newcss1" data-row="'+meta.row+'" style="line-height: 2px;margin-left: 10px;cursor: pointer">' + '登录测试'+'</a> '+
                            '<a class="newcss2" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-deldeviceaccount" style="line-height: 2px;margin-left: 10px;cursor: pointer">删除</a>';
                    }

                }}
        ]
    });
    $('#modal-editdeviceaccount').off().on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        if(button.data('row')!=undefined&&button.data('row')!=null){
            $('#modal-editdeviceaccount .modal-title').text('编辑账号');
            i = button.data('row');
            $('#edit_account_id').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].id);
            $('#edit_account_port').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].port);
            var login_value = $('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].login_method;
            if(login_value ==0){
                $('#edit_account_login_method').val("33");
                $('#_edit_account_username').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].username);
                $('#_edit_account_password').val('******');
                $('#_edit_account_password1').val('******');
                $('#_edit_account_password').attr("placeholder","\xa0\xa0\xa0********");
                $('#_edit_account_password1').attr("placeholder","\xa0\xa0\xa0********");
                document.getElementById("xing1").style.color="red";
                document.getElementById("xing2").style.color="red";
                document.getElementById("xing3").style.color="red";
            }else {
                document.getElementById("xing1").style.color="white";
                document.getElementById("xing2").style.color="white";
                document.getElementById("xing3").style.color="white";
                $('#edit_account_login_method').val("44");
                $('#_edit_account_username').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].username);
                $('#_edit_account_password').val('******');
                $('#_edit_account_password1').val('******');
                if ($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].username!=""){
                    $('#_edit_account_password').attr("placeholder","\xa0\xa0\xa0********");
                    $('#_edit_account_password1').attr("placeholder","\xa0\xa0\xa0********");
                }
            }
            $('#edit_account_protocol').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].protocol_id);
            if ($('#edit_account_protocol').val()==="1") {
                $('#div20').css("display",'block');
            }else {
                $('#div20').css("display",'none');
            }
            $('#edit_account_ssh_key').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].ssh_key);
            if( $('#edit_account_ssh_key').val()==='0'){
                $("#div23").show();
            }else {
                $("#div23").hide();
            }
            $('#file_id').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].file_id);
            fileInput('');
            $('#down').css("display",'block');
            $(".file-caption-name").val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].script_name);
            $('#edit_sshkey').attr("placeholder","\xa0\xa0\xa0********");
        }else{
            $('#edit_account_id').val('');
            $('#modal-editdeviceaccount .modal-title').text('新建账号');
            $('#edit_account_username').val('');
            $('#edit_account_password').val('');  
            $('#edit_account_password1').val('');
            $('#edit_account_protocol option:first').prop("selected",'selected');
            $('#edit_account_ssh_key option:last').prop("selected",'selected');
            $(".fileinput-remove-button").click();
            $('#down').css("display","none");
            $('#file_id').val(0);
            fileInput('');
            showAGREE($('#edit_account_protocol').val());
            $('#div23').css("display","none");
            $('#edit_account_ssh_key').val('1');
        }
    });
    //设备账号删除的回显
    $('#modal-deldeviceaccount').off().on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        i = button.data('row');
        $('#del_account_id').val($('#deviceaccount').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    //设备账号编辑操作
    $('#editDeviceAccountButton').off().on('click', function(){
        var url = "../../deviceAccount/editDeviceAccount";
        if($('#edit_account_id').val()==""){
            url = "../../deviceAccount/addDeviceAccount";
        }
        var flag = true;
        if ($('#edit_account_port').val()==""){
            $('#Vedit_account_port').text("端口不能为空");
            flag = false;
        }
        var password="";
        var _password="";
        var username="";
        password = $('#_edit_account_password').val();
        _password = $('#_edit_account_password1').val();
        username = $('#_edit_account_username').val();
        if ($('#edit_account_login_method').val()=='33' &&$('#edit_account_ssh_key').val()==='1'){
            if ($('#_edit_account_username').val()=="" ){
                $('#_Vaccount_username').text('请输入设备账号')
                flag = false;
            }
            if ($('#edit_account_id').val()==""){
                if ($('#_edit_account_password1').val()=="" || $('#_edit_account_password').val()==""){
                    if ($('#_edit_account_password').val()==""){
                        $('#_Vaccount_password').text('请输入密码');
                    }
                    if ($('#_edit_account_password1').val()==""){
                        $('#_Vaccount_password1').text('请输入确认密码');
                    }
                    flag = false;
                }
            }
            if ($('#_Vaccount_username').text()!="" || $('#_Vaccount_password').text()!="" || $('#_Vaccount_password1').text()!="" || $('#Vedit_account_port').text()!="" ){
                flag = false;
            }
        }
            var pwd3=$('#_edit_account_password1').val();
            if($('#_edit_account_password').val()!=""&&pwd3==""){
                $('#_Vaccount_password1').text('请输入确认密码');
            }else if($('#_edit_account_password').val()!=""&&pwd3!=$('#_edit_account_password').val()){
                $('#_Vaccount_password1').text('密码不一致');
            }

        var login_method = $('#edit_account_login_method').val();
        if (login_method==33){
            login_method=0;
        }else {
            login_method=1;
        }
        if($("#edit_account_ssh_key").val()==0 && $(".file-caption-name").val()===""){
            $("#Vupload_file").text("请上传文件");
        }
        if (($('#_Vaccount_username').text()!="" || $('#_Vaccount_password').text()!="" || $('#_Vaccount_password1').text()!=""||($("#edit_account_ssh_key").val()==0)&&$("#Vupload_file").text()!=""&&$('#edit_account_ssh_key').val()==='1')){
            flag = false;
        }
        if (flag){
            $.ajax({
                url:url,
                type:"POST",
                data:{
                    id:$('#edit_account_id').val(),
                    login_method:login_method,
                    username:username,
                    password:password,
                    querypassword:_password,
                    file_id:$('#file_id').val(),
					is_super:$('#is_super')[0].checked?1:0,
                    protocol_id:$('#edit_account_protocol').val(),
                    port:$('#edit_account_port').val(),
                    ssh_key:$('#edit_account_ssh_key').val(),
                    device_id:$('#_edit_device_id').val(),
                    ssh_password:$('#edit_sshkey').val(),
                },
                success:function(data){
                    if(data.success){
                        if ($('#edit_account_id').val()== "") {
                            $('#modal-editdeviceaccount').modal('hide');
                            $("#modal-success .modal-title").text('成功');
                            $("#modal-success .modal-body").text('新建成功!');
                            $("#modal-success").modal();
                            loadAJAX('#deviceaccount');
                            loadAJAX('#devices');
                        }else{
                            $('#modal-editdeviceaccount').modal('hide');
                            $("#modal-success .modal-title").text('成功');
                            $("#modal-success .modal-body").text('编辑成功!');
                            $("#modal-success").modal();
                            loadAJAX('#deviceaccount');
                        }
                    }
                    else{
                        if ($('#edit_account_id').val()== "") {
                            $("#modal-danger .modal-title").text('失败');
                            $("#modal-danger .modal-body").text('新建失败!');
                            $("#modal-danger").modal();
                            loadAJAX('#deviceaccount');
                        }else{
                            $("#modal-danger .modal-title").text('失败');
                            $("#modal-danger .modal-body").text('编辑失败!');
                            $("#modal-danger").modal();

                            loadAJAX('#deviceaccount');
                        }
                    }
                },
            })
        }
    });
    function clearAcount(){
        $('#edit_account_username').val();
        $('#_edit_account_password').val();
        $('#_edit_account_password1').val();
        $('#edit_account_login_method').val="33";
        $('#edit_account_protocol option:first').prop("selected",'selected');
        $('#_edit_account_password').attr("placeholder","");
        $('#_edit_account_password1').attr("placeholder","");
    }
    $('#clearText').click(function(){
        clearAcount()
    });

    $('#_delAllButton').off().on('click', function(){
        var obj = document.getElementsByName('chk_[]');
        console.log(obj);
        var ids= new Array();
        for (i in obj){
            if(obj[i].checked)
                ids.push(obj[i].value);
        }
        console.log(ids);
        if(ids.length==0){
            $("#modal-hint.modal-title").text('失败');
            $("#modal-hint .modal-body").text('请选择要删除的信息');
            $("#modal-hint").modal();
            loadAJAX('#deviceaccount');
            return false;
        }
        $.ajax({
            url:"../../deviceAccount/delDeviceAccount",
            type:"POST",
            data:{
                ids:ids
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    $("#modal-del2").modal('hide');
                    loadAJAX('#deviceaccount');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#deviceaccount');
                }
            },
            error:function(){
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        })
    });
    $('#delButton1').off().on('click', function(){
        $.ajax({
            url:"../../deviceAccount/delDeviceAccount",
            type:"POST",
            data:{
                ids: new Array($('#del_account_id').val()),
            },
            success:function(data){
                if(data.success){
                    $("#modal-deldeviceaccount").modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    loadAJAX('#deviceaccount');
                    loadAJAX('#devices');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#deviceaccount');
                }
            },
            error:function(){
            }
        })
    });
}

function show2(v){
    if(v==='33'){
        document.getElementById("xing1").style.color="red";
        document.getElementById("xing2").style.color="red";
        document.getElementById("xing3").style.color="red";
    }else if(v==='44'){
        document.getElementById("xing1").style.color="white";
        document.getElementById("xing2").style.color="white";
        document.getElementById("xing3").style.color="white";
        $("#_Vaccount_username").text("");
        $("#_Vaccount_password").text("");
        $("#_Vaccount_password1").text("");
    }
}
function show1(v){
    if(v==='11') {
        document.getElementById("xing4").style.color = "red";
        document.getElementById("xing5").style.color = "red";
        document.getElementById("xing6").style.color = "red";
    } else if (v==='13'){
        document.getElementById("xing4").style.color="white";
        document.getElementById("xing5").style.color="white";
        document.getElementById("xing6").style.color="white";
        $("#_Vsuper_account").text("");
        $("#_Vdevice_password").text("");
        $("#_Vdevice_password1").text("");
    }
}
//点击空白关闭tree控件
$('#modal-editdevice').click(function(event){
    $('#tree').css("display","none");
});
$('#depart_name').click(function (event) {
    stopPropagation(event);
    $('#tree').css("display","block");
});
function stopPropagation(e) {
    e = e || window.event;
    if(e.stopPropagation) { //W3C阻止冒泡方法
        e.stopPropagation();
    } else {
        e.cancelBubble = true; //IE阻止冒泡方法
    }
}
//获取部门列表
$(function () {
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
                $("#depart_name").click(function(){
                    var options = {
                        levels : 2,
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
            }
        },
    })
});
