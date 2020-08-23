var userList = function(field,value,type,value1){
    $('#device_group').DataTable({
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
        'destroy'   :true,
        "ajax": {
            "url":"../../group/listGroup",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
                eval('d.'+type+'="'+1+'"');
            }
        },
        "columns": [
            { "data": "id" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "name" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "depart_name" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+row.topName1+'">'
                        +data
                        + '</div>';

                }},
            { "data": "desc" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "count" },
            { "data": "id", "render": function(data,type,row,meta){
                    return '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-editgroup" class="newcss1" style="cursor:pointer;" onclick="resetC()">编辑</a> '+
                        '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary8" class="newcss1" style="margin-left: 20px;cursor:pointer;">关联设备</a> '+
                        '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-delgroup" class="newcss2" style="margin-left: 20px;cursor:pointer;">删除</a>';
                }}
        ],
        "fnDrawCallback": function( settings, json ) {
            $('#device_group div').tooltip();
        }
    });
};
/* 调用user_group_list */
userList('searchAll','','type',1);
/* 根据条件搜索 */
$('#search').click(function(){
    userList($('#Distinguish').val(),$('#searchId').val(),'type',1);
});

//新建和编辑设备组
$('#modal-editgroup').on('show.bs.modal', function (event) {
    $("#ValiName").text('');
    var button = $(event.relatedTarget) // Button that triggered the modal
    if(button.data('row')!=undefined&&button.data('row')!=null){
        i = button.data('row');
        let title_name=($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].name);
        $("#modal-editgroup .modal-title").text("编辑设备组"+""+""+"["+title_name+"]");
        console.log(title_name)
        $('#id').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_name').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#depart_name').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#edit_desc').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].desc);
    }else{
        $("#modal-editgroup .modal-title").text("新建设备组");
        $('#id').val('');
        $('#edit_name').val('');
        $('#edit_desc').val('');
        $('#depart_name').val('');
        $('#departName').text('');
    }
});

$('#modal-delgroup').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#del_id').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].id);
});

$('#editButton').click(function(){
    function blurStart() {
        flag = true;
        if ($('#edit_name').val()==""|| $('#Vname').text()!="" || $('#Vedit_desc').text()!=""){
            if ($('#edit_name').val()==""){
                $('#Vname').text('请输入组名');
            }
            flag = false;
        }
        // if ($('#depart_name').val()==''|| $('#VdepartName').val()){
        //     if ($('#depart_name').val()==''){
        //         $('#VdepartName').text('请选择部门');
        //     }
        //     flag = false;
        // }
        return flag;

    }
    if (blurStart()){
        $.ajax({
            url:"../../group/editGroup",
            type:"POST",
            data:{
                id:$('#id').val(),
                name:$('#edit_name').val(),
                department:$('#depart_id').val(),
                desc:$('#edit_desc').val(),
                type:1,
            },
            success:function(data){
                if(data.success){
                    if ($('#id').val()== "") {
                        $('#modal-editgroup').modal('hide');
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text('新建成功!');
                        $("#modal-success").modal();
                        loadAJAX('#device_group');
                    }else{
                        $('#modal-editgroup').modal('hide');
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text('编辑成功!');
                        $("#modal-success").modal();
                        loadAJAX('#device_group');
                    }
                }
                else{
                    if ($('#id').val()== "") {
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text('新建失败!');
                        $("#modal-danger").modal();
                        loadAJAX('#device_group');
                    } else {
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text('编辑失败!');
                        $("#modal-danger").modal();
                        loadAJAX('#device_group');
                    }

                }
            },
            error:function(){}
        })
    }

});

$('#delAllButton').click(function(){
    var obj = document.getElementsByName('chk[]');
    console.log(obj);
    var ids= new Array();
    for (i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    console.log(ids);
    if(ids.length==0){
        $("#modal-hint.modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        return false;
    }
    $.ajax({
        url:"../../group/delGroup",
        type:"POST",
        data:{
            ids:ids,
            type:1,
        },
        success:function(data){
            if(data.success){
                $("#modal-delgroup2").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#device_group');
            }
            else{
                $("#modal-delgroup2").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text(data.msg?data.msg:'操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#device_group');
            }
        },
        error:function(){
            $("#modal-delgroup2").modal('hide');
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
            loadAJAX('#device_group');
        }
    })
});
//删除设备组
$('#delButton').click(function(){
    $.ajax({
        url:"../../group/delGroup",
        type:"POST",
        data:{
            ids: new Array($('#del_id').val()),
            type:1,
        },
        success:function(data){
            if(data.success){
                $("#modal-delgroup").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#device_group');
            }
            else{
                $("#modal-delgroup").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text(data.msg?data.msg:'操作失败!');
                $("#modal-danger").modal();
            }
        },
        error:function(){}
    })
});
//下载模版
$('#downTemplate').click(function(){
    $('#downClick')[0].click();

});

$("#upload").off().on("click", function () {

    var s = $('#btn_file')[0].files[0];
    var udepartment = $('#depart_id').val();
    if (!s){
        $('#Vfile').text('请上传文件');
        return;
    }
    var formData = new FormData();
    formData.append("file_data", s);
    formData.append("type", 1);
    formData.append("udepartment", udepartment);
    $("#modal-upload .modal-title").text('状态');
    $("#modal-upload .modal-body").text('正在导入...');
    $("#modal-upload").modal();
    $.ajax({
        url: "../../upload/group",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.success){
                $("#modal-upload").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text(data.msg);
                $("#modal-success").modal();
                $('#modal-default').modal('hide');
            }else {
                $("#modal-upload").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('导入失败!');
                $("#modal-danger").modal();
            }
            loadAJAX('#device_group');
            setTimeout(function () {
                if (data.errorInfo.length !== 0) {
                    $("#modal-uploadInfo").modal();
                    $('#uploadError').text(data.errorInfo+"----详细请看文档");
                }
            },1500)
        }
    });
});

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
                $('#modal-default1').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep").text(data.msg)
                }
                loadAJAX('#device_group');
            }
        },
        error:function(){

        }
    })
})
//上传点击事件
function openFile() {
    $('#Vfile').text('');
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

//格式判断
//清空内容
function resetFileInput(){
    $('#Vfile').text('');
    $('#btn_file').val('');
    $("#filename").text('');
}
//清空内容
function resetC(){
    $('#edit_name').val('');
    $('#edit_desc').val('');
    $("#Vname").text('');
    $("#Vedit_desc").text('');
}
//失去焦点，移出输入框
$('#edit_name').blur(function(){
    //组名的验证规则
    var regexp = {
        name:/^([A-Za-z0-9]|[\u4e00-\u9fa5]|\@|\_){1,32}$/,
    }
    //获取输入的名字
    var username = $('#edit_name').val();
    //判断是否符合规则
    if (!regexp.name.test(username)){
        //不符合
        $('#Vname').text('组名格式不正确');
    }else {
        //进行重复判断
        checkusername();
    }
});

// 检查重名
function checkusername(){

    $.ajax({
        url:"../../group/checkname",
        type:"POST",
        data:{
            id:$('#id').val(),
            name:$('#edit_name').val(),
            type:1,
        },
        success:function(data){
            if(!data.success){
                $('#Vname').text('组名已存在');
            }
        }
    })
}
// 获取焦点，重新输入
$('#edit_name').focus(function(){
    //将提示信息置空
    $('#Vname').text('');
})

$('#edit_desc').blur(function () {
    var regexp = {
        name:/^\S{0,64}$/,
    };
    if(!regexp.name.test($('#edit_desc').val())){
        $('#Vedit_desc').text("超过限制长度");
    }else {
        $('#Vedit_desc').text("");
    }
})

// 获取焦点，重新输入
$('#edit_desc').focus(function(){
    //将提示信息置空
    $('#Vedit_desc').text('');
})

// 获取焦点，重新输入
$('#queryPsw').focus(function(){
    //将提示信息置空
    $('#PswRep').text('');
})
//清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("")
});
$('#depart_name').focus(function () {
    $('#VdepartName').text('');
});
//device
let ac_edit_device_list = null;
let ac_edit_device1_list = null;
$('#modal-primary8').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var i = button.data('row');
    $('#modal8_id').val($('#device_group').DataTable().row('#' + i).nodes(i).data()[i].id);
    $.ajax({
        url: "../../groupDeviceAccount/findGroupDeviceAccount",
        type: "POST",
        data: {
            group_id: $('#modal8_id').val(),
        },
        success: function (data) {
            var arr = data.data_device;
            var arr1 = data.data_p_device;
            //show
            $('#edit_device').html('');
            $('#edit_device1').html('');
            let len = arr.length;
            for (let i = 0; i < len; i++) {
                arr[i].protocol_id =_protocol(arr[i].protocol_id);
                $('#edit_device').append('<div><input value="' + arr[i].device_account_id + '" type="checkbox"><span>' + arr[i].device_name + "[" + arr[i].username + "]" + "[" + arr[i].protocol_id + "]" + '</span></div>')
            }
            let len1 = arr1.length;
            for (let i = 0; i < len1; i++) {
                arr1[i].protocol_id =_protocol(arr1[i].protocol_id);
                $('#edit_device1').append('<div><input value="' + arr1[i].device_account_id + '" type="checkbox" ><span>' + arr1[i].device_name + "[" + arr1[i].username + "]" + "[" + arr1[i].protocol_id + "]" + '</span></div>')
            }
            RelativeMethods(7);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            ac_edit_device_list = $('#edit_device').html();
            ac_edit_device1_list = $('#edit_device1').html();
        },
        error: function () {
        },
    })
});
$("#relevance-device").click(function () {
//devicegroup
let selecteddevicegroup = [];
$('#edit_device1 input').each(function () {
    selecteddevicegroup.push(this.value)
})
});
//device
$("#relevance-device").click(function () {
    var selecteddevice = [];
    $("#edit_device1 input").each(function () {
        selecteddevice.push(this.value);
    })
    $.ajax({
        url: "../../groupDeviceAccount/editGroupDeviceAccount",
        type: "POST",
        data: {
            group_id: $('#modal8_id').val(),
            devices: selecteddevice,
        },
        success: function (data) {
            if (data.success) {
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#device_group');
            } else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
                loadAJAX('#device_group');
            }
        },
        error: function () {
        }
    })
});