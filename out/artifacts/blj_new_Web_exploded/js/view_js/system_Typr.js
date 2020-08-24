var systemlist = function(field,value){
    $('#System_list').DataTable({
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
            "url":"../../deviceType/listDeviceType",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
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
          /*  { "data": "param" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},*/
            { "data": "description" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},

            { "data": "id", "render": function(data,type,row,meta){
                    return '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#new_System" class="newcss1" style="cursor:pointer;" >编辑</a> '+
                        '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-delsys" class="newcss2" style="margin-left: 20px;cursor:pointer;">删除</a>';
                }}
        ],
        "fnDrawCallback": function( settings, json ) {
            $('#systemType div').tooltip();
        }
    });
}
/* 调用user_group_list */
systemlist('searchall','');
/* 根据条件搜索 */
$('#search').click(function(){
    systemlist($('#Distinguish').val(),$('#searchId').val());
})


//删除
$('#modal-delsys').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#del_id1').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].id);
});

$('#delButton1').click(function(){
    $.ajax({
        url:"../../deviceType/delDeviceType",
        type:"POST",
        data:{
            ids: new Array($('#del_id1').val()),
            type:1,
        },
        success:function(data){
            if(data.success){
                $('#modal-delsys').modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#System_list');
            }
            else{
                $('#modal-delsys').modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        },
        error:function(){

        }
    })
});
$('#delAllButton').off().click(function () {
    var obj = document.getElementsByName('chk[]');
    console.log(obj);
    var ids= new Array();
    for (i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    console.log(obj);
    if(ids.length==0){
        $("#modal-hint .modal-title").text('失败');
        $("#modal-hint .modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#System_list');
        return false;
    }
    $.ajax({
        url:"../../deviceType/delDeviceType",
        type:"POST",
        data:{
            ids:ids,
            type:1,
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#System_list');
                $("#delAll").modal("hide");
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#System_list');
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
            loadAJAX('#System_list');
        }
    })
});

$('#new_System').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    if($(button).attr('data-name')=='previous'){
        return ;
    }else if(button.data('row')!=undefined&&button.data('row')!=null){
        $('#new_System .modal-title').text('编辑系统类型');
        i = button.data('row');
        $('#edit_id').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#name').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#param').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].param);
        $('#os_type').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].os_type);
        $('#description').val($('#System_list').DataTable().row('#' + i).nodes(i).data()[i].description);
    }else{
        $('#new_System .modal-title').text('新建系统类型');
        $('#name').val('');
        $('#param').val('');
        $('#os_type').val('0');
        $('#description').val('');
        $('#edit_id').val('');
    }
    $("#Sys_name").text("");
    $("#Vparam").text("");
    $("#Vdescription").text("");
});

$('#edit_btn').off().click(function() {
    var url = "../../deviceType/editDeviceType";
    if ($('#edit_id').val() == "") {
        url = "../../deviceType/addDeviceType";
    }
    function checkType(){
    	checkSys_name();
        var flag=true;
        var p2=/^(\w|[\u4E00-\u9FA5]|-|@){1,32}$/;
        var p3=/^\S{0,128}$/;
        var Tname=$("#name").val();
        if(Tname==''){
            $("#Sys_name").text("请输入系统名称");
            flag=false;
        }else if(!p2.test(Tname)){
            $("#Sys_name").text("输入格式不正确");
            flag=false;
        }    
        if($("#param").val()!=""&&!p3.test($("#param").val())){
            $("#Vparam").text("超过限制长度");
            flag=false;
        }
        if($("#description").val()!=""&&!p3.test($("#description").val())){
            $("#Vdescription").text("超过限制长度");
            flag=false;
        }
        if ($('#Sys_name').text()!=""){
            flag=false;
        }
        return flag;
    }

    if (checkType()){
        $.ajax({
            url:url,
            type:"POST",
            data:{
                id:$('#edit_id').val(),
                name:$('#name').val(),
                param:$('#param').val(),
                os_type:$('#os_type').find("option:selected").val(),
                description:$('#description').val(),
            },
            success:function(data){
                if(data.success){
                    $('#new_System').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    if ($('#edit_id').val() == ""){
                        $("#modal-success .modal-body").text("新建成功!");
                    }else {
                        $("#modal-success .modal-body").text("编辑成功!");
                    }
                    $("#modal-success").modal();
                    loadAJAX('#System_list');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("新建失败!");
                    $("#modal-danger").modal();
                    loadAJAX('#System_list');
                }
            },
            error:function(){
            }
        })
    }

});

$("#name").blur(function () {
    var Tname=$("#name").val();
    var p2=/^(\w|[\u4E00-\u9FA5]|-|@){1,32}$/;
    if(Tname==''){
        $("#Sys_name").text("请输入系统名称");
    }else if(!p2.test(Tname)){
        $("#Sys_name").text("输入格式不正确");
    }else {
        checkSys_name()
    }
});

// 检查重名
function checkSys_name(){
    $.ajax({
        url:"../../deviceType/checkname",
        type:"POST",
        data:{
            id:$('#edit_id').val(),
            name:$("#name").val(),
        },
        success:function(data){
            if(!data.success){
                $("#Sys_name").text("系统名称重复");
               return;
            }
        }
    })
}

$("#name").focus(function () {
    $("#Sys_name").text("");
});
$("#param").focus(function () {
    $("#Vparam").text("");
});
$("#description").focus(function () {
    $("#Vdescription").text("");
});
$("#name").blur(function () {
    var p3=/^\S{0,64}$/;
    if($("#param").val()!=""&&!p3.test($("#param").val())){
        $("#Vparam").text("超过限制长度");
    }
});
$("#description").blur(function () {
    var p3=/^\S{0,128}$/;
    if($("#description").val()!=""&&!p3.test($("#description").val())){
        $("#Vdescription").text("超过限制长度");
    }
});
