$(function () {
var _policyList = function(field, value){
    $('#policyList').DataTable({
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
        "serverSide"	: true,
        "ajax": {
            "url":"../../changePasswordPolicy/listChangePasswordPolicy",
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
            { "data": "id" ,
            	 "render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }
            },
            { "data": "name" },
         /*   { "data": "status" },*/
            { "data": "exec_method",
                "render": function(data,type,row,meta){
                    if(data==0){
                        return "手动执行";
                    };
                    if(data==1){
                        return "定时执行";
                    };
                    if(data==2){
                        return "周期执行";
                    };
                }
            },
            { "data": "changetype",
            	"render":function(data,type,row,meta){
            		if(data==0){
                		return "生成不同密码";
                	}else if(data==1){
                		return "生成相同密码";
                	}else{
                		return "指定相同密码";
                	}	
            }
           },
            { "data": "id",
            	"render": function(data,type,row,meta){
                    return '<A  data-toggle="modal"  data-row="'+meta.row+'" id="edit_strategy" data-target="#modal-editpolicy" class="newcss1" style="margin-left: 5px;cursor:pointer;">编辑</A>'+
                        '<A data-toggle="modal"  data-row="'+meta.row+'" data-target="#modal-execmd" class="newcss1" style="margin-left: 25px;cursor:pointer;">立即执行</A>'+
                        '<div class="btn-group" style="margin-left:20px">'+
                        '<a class="newcss1" data-toggle="dropdown" style="line-height: 1.5px;cursor:pointer;">关联</a>'+
                        '<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="height: 22px;line-height: 1.5px;color:#12BA1D;cursor:pointer;">'+
                        '<span style="margin-bottom:3px;font-size:12.5px;" class="caret"></span>'+
                        '<span class="sr-only">Toggle Dropdown</span>'+
                        '</a>'+
                        '<ul class="dropdown-menu" role="menu" style="font-size: 12px">'+
                        '<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary1">设备</a></li>'+
                        '<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary2">设备组</a></li>'+
                        '</ul>'+
                        '</div>'+
                        '<A  data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-delpolicy" class="newcss2" style="margin-left: 25px;cursor:pointer;">删除</A></div>';
                }
            }
        ]
    });
}

_policyList('search', '')
    //按条件搜索
    $("#search").click(function(){
    	_policyList('search', '')
    });
//上一步
    $('#modal-addpolicy').on('show.bs.modal', function (event) {
    });
//编辑策略
    $('#modal-editpolicy').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#edit_id').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_name').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#edit_status').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].status);
        $('#edit_changetype').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].changetype);
        $('#edit_allow_root').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].allow_root);
        $('#edit_allow_change_root').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].allow_change_root);
        $('#edit_type1').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].exec_method);
        var v_1=($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].exec_method);
        if(v_1==1){
            document.getElementById("div4").style.display="block";
            document.getElementById("div3").style.display="none";
            $('#edit_exec_datetime1').val("");
            $('#edit_internal').val("");
            $('#edit_end_datetime').val("");
            $('#edit_exec_datetime').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
       }else if(v_1==2){
            document.getElementById("div3").style.display="block";
            document.getElementById("div4").style.display="none";
            $('#edit_internal').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].internal);
            $('#edit_exec_datetime1').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
            $('#edit_end_datetime').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].end_datetime);
            $('#edit_exec_datetime').val("");
        }
        else{
            document.getElementById("div3").style.display="none";
            document.getElementById("div4").style.display="none";
            $('#edit_exec_datetime1').val("");
            $('#edit_internal').val("");
            $('#edit_end_datetime').val("");
            $('#edit_exec_datetime').val("");
        };
       /* var type_1 = ($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].exec_method);
        if(type_1==0){
            $('#edit_type').html("手动执行");
        }
        else if(type_1==1){
            $('#edit_type').html("定时执行");
        }
        else{
            $('#edit_type').html("周期执行");
        };*/
    });
    //单个删除
    $('#modal-delpolicy').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#del_id').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    
    $('#modal-execmd').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#policy_id').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].id);
    });

   $('#addButton1').click(function(){
        if($("#add_allow_root").is(':checked')){
            var add_allow_root = 1;
        }else{
            add_allow_root = 0;
        }
        if($("#add_allow_change_root").is(':checked')){
            var add_allow_change_root = 1;
        }else{
            add_allow_change_root = 0;
        }
        //device
        let selecteddevice = [];
        $('#add_device1 input').each(function () {
            selecteddevice.push(this.value);
        });
        //devicegroup
        let selecteddevicegroup = [];
        $('#add_devicegroup1 input').each(function () {
            selecteddevicegroup.push(this.value)
        });
        $.ajax({
            url:"../../changePasswordPolicy/addChangePasswordPolicy",
            type:"POST",
            data:{
            	name:$('#add_name').val(),
                 /*   status:'在线',*/
                exec_method:$('#add_type').find("option:selected").val(),
                changetype:$('#add_changetype').find("option:selected").val(),
                exec_datetime:$('#add_exec_datetime').val()||$('#add_exec_datetime1').val(),
                internal:$('#add_internal').val(),
                end_datetime:$('#add_end_datetime').val(),
                allow_root:add_allow_root,
                allow_change_root:add_allow_change_root,
                devices: selecteddevice,
                devicegroup: selecteddevicegroup,
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                    loadAJAX('#policyList');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('新建失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#policyList');
                }
            },
            error:function(){

            }
        })
   });
    $('#addButton').click(function(){
    	console.log($('#add_type').find("option:selected").val());
        if($("#add_allow_root").is(':checked')){
            var add_allow_root = 1;
        }else{
                add_allow_root = 0;
        }
        if($("#add_allow_change_root").is(':checked')){
            var add_allow_change_root = 1;
        }else{
                add_allow_change_root = 0;
        }
        //device
        let selecteddevice = [];
        $('#add_device1 input').each(function () {
            selecteddevice.push(this.value);
        })
        //devicegroup
        let selecteddevicegroup = [];
        $('#add_devicegroup1 input').each(function () {
            selecteddevicegroup.push(this.value)
        })
        $.ajax({
            url:"../../changePasswordPolicy/addChangePasswordPolicy",
            type:"POST",
            data:{
                name:$('#add_name').val(),
             /*   status:'在线',*/
                exec_method:$('#add_type').find("option:selected").val(),
                changetype:$('#add_changetype').find("option:selected").val(),
                exec_datetime:$('#add_exec_datetime').val()||$('#add_exec_datetime1').val(),
                internal:$('#add_internal').val(),
                end_datetime:$('#add_end_datetime').val(),
                allow_root:add_allow_root,
                allow_change_root:add_allow_change_root,
                devices: selecteddevice,
                devicegroup: selecteddevicegroup,
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                    loadAJAX('#policyList');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('新建失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#policyList');
                }
            },
            error:function(){

            }
        })
    });
    $('#editButton').click(function(){
        if(!check_edit()) return false;
        if($("#edit_allow_root").is(':checked')){
            var edit_allow_root = 1;
        }else{
            edit_allow_root = 0;
        }
        if($("#edit_allow_change_root").is(':checked')){
            var edit_allow_change_root = 1;
        }else{
            edit_allow_change_root = 0;
        }
        //device
        let selecteddevice = [];
        $('#add_device1 input').each(function () {
            selecteddevice.push(this.value);
        });
        //devicegroup
        let selecteddevicegroup = [];
        $('#add_devicegroup1 input').each(function () {
            selecteddevicegroup.push(this.value)
        });
        $.ajax({
            url:"../../changePasswordPolicy/editChangePasswordPolicy",
            type:"POST",
            data:{
                id:$('#edit_id').val(),
                name:$('#edit_name').val(),
               /* status:'在线',*/
                exec_method:$('#edit_type1').find("option:selected").val(),
                changetype:$('#edit_changetype').val(),
                exec_datetime:$('#add_exec_datetime').val()||$('#add_exec_datetime1').val(),
                internal:$('#edit_internal').val(),
                end_datetime:$('#edit_end_datetime').val(),
                allow_root:edit_allow_root,
                allow_change_root:edit_allow_change_root,
                devices: selecteddevice,
                devicegroup: selecteddevicegroup,
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    loadAJAX('#policyList');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#policyList');
                }
            },
            error:function(){

            }
        })
    });
    
 //删除
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
            loadAJAX('#policyList');
            return false;
        }
        $.ajax({
            url:"../../changePasswordPolicy/delChangePasswordPolicy",
            type:"POST",
            data:{
                ids:ids
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    $('#policyList').DataTable().ajax.reload();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error:function(){
            }
        })
    });

    $('#delButton').click(function(){
        $.ajax({
            url:"../../changePasswordPolicy/delChangePasswordPolicy",
            type:"POST",
            data:{
                ids:new Array($('#del_id').val())
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    $('#policyList').DataTable().ajax.reload();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error:function(){}
        })
    })
});
//关联设备(组)
let listDeviceAccount = null;
let listDeviceGroup = null;
function listAll() {
    //设备
    $.ajax({
        url: "../../deviceAccount/listDeviceAccountNameIp",
        type: "POST",
        async: true,
        dataType: "json",
        data: {
            start: 0,
            length: 10000
        },
        success: function (data) {
            $('#add_device').html('');
            $('#add_device1').html('');
            var data = data.data;
            for (var i = 0; i < data.length; i++) {
                if (data[i].username.length > 0)
                    if (data[i].protocol_id == 1) {
                        data[i].protocol_id = "ssh";
                    } else if (data[i].protocol_id == 2) {
                        data[i].protocol_id = "rdp";
                    } else if (data[i].protocol_id == 3) {
                        data[i].protocol_id = "telnet";
                    } else {
                        data[i].protocol_id = "vnc";
                    }
                $('#add_device').html($('#add_device').html() + '<div><input value="' + data[i].device_account_id + '" type="checkbox"><span>' + data[i].device_name + "[" + data[i].username + "]" + "[" + data[i].protocol_id + "]" + '</span></div>');
            }
            RelativeMethods(2);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            listDeviceAccount = $('#add_device').html();//保存显示出来的原本的数据
        },
        error: function () {
        }
    });
    //设备组
    $.ajax({
        url: "../../group/listGroup",
        type: "POST",
        async: true,
        dataType: "json",
        data: {
            type: 1,
            start: 0,
            length: 10000,
            // name: $('#searchIdG').val(),
        },
        success: function (data) {
            $('#add_devicegroup').html('');
            $('#add_devicegroup1').html('');
            var data = data.data;
            for (var i = 0; i < data.length; i++) {
                if (data[i].name.length > 0)
                    $('#add_devicegroup').html($('#add_devicegroup').html() + '<div><input value="' + data[i].id + '" type="checkbox"><span>' + data[i].name + '</span></div>');
            }
            RelativeMethods(3);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            listDeviceGroup = $('#add_devicegroup').html();//保存显示出来的原本的数据
        },
        error: function () {
        }
    })
}
listAll();
//关联编辑
//deviceAccount
let ac_edit_device_list = null;
let ac_edit_device1_list = null;
$('#modal-primary1').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var i = button.data('row');
    $('#modal1_id').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].id);
    $.ajax({
        url: "../../accessPolicyDeviceAccount/findAccessPolicyDeviceAccountAndUser",
        type: "POST",
        data: {
            policy_id: $('#modal1_id').val(),
        },
        success: function (data) {
            var arr = data.data_device;
            var arr1 = data.data_p_device;
            //show
            $('#edit_device').html('');
            $('#edit_device1').html('');
            for (var i = 0; i < arr.length; i++) {
                $('#edit_device').html($('#edit_device').html() + '<div><input value="' + arr[i].device_account_id + '" type="checkbox"><span>' + arr[i].device_name + "[" + arr[i].username + "]" + "[" + arr[i].protocol_name + "]" + '</span></div>')
            }
            for (var i = 0; i < arr1.length; i++) {
                $('#edit_device1').html($('#edit_device1').html() + '<div><input value="' + arr1[i].device_account_id + '" type="checkbox" ><span>' + arr1[i].device_name + "[" + arr1[i].username + "]" + "[" + arr1[i].protocol_name + "]" + '</span></div>')
            }
            RelativeMethods(7);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            ac_edit_device_list = $('#edit_device').html();
            ac_edit_device1_list = $('#edit_device1').html();
        },
        error: function () {
        },
    })
});
//device-group
let ac_edit_devicegroup_list = null;
let ac_edit_devicegroup1_list = null;
$('#modal-primary2').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var i = button.data('row');
    $('#modal2_id').val($('#policyList').DataTable().row('#' + i).nodes(i).data()[i].id);
    $.ajax({
        url:  "../../accessPolicyGroup/findAccessPolicyDeviceGroupAndUser",
        type: "POST",
        data: {
            policy_id: $('#modal2_id').val(),
        },
        success: function (data) {
            var arr = data.data_dgroups;
            var arr1 = data.data_p_dgroups;
            //show
            $('#edit_devicegroup').html('');
            $('#edit_devicegroup1').html('');
            for (var i = 0; i < arr.length; i++) {
                $('#edit_devicegroup').html($('#edit_devicegroup').html() + '<div><input value="' + arr[i].dgroup_id + '" type="checkbox"><span>' + arr[i].dgroup_name + '</span></div>')
            }
            for (var i = 0; i < arr1.length; i++) {
                $('#edit_devicegroup1').html($('#edit_devicegroup1').html() + '<div><input value="' + arr1[i].dgroup_id + '" type="checkbox"><span>' + arr1[i].dgroup_name + '</span></div>')
            }
            RelativeMethods(1);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            ac_edit_devicegroup_list = $('#edit_devicegroup').html();
            ac_edit_devicegroup1_list = $('#edit_devicegroup1').html();
        },
        error: function () {
        },
    })
});
//device
$("#relevance-device").click(function () {
    var selecteddevice = [];
    $("#edit_device1 input").each(function () {
        selecteddevice.push(this.value);
    });
    $.ajax({
        url: "../../accessPolicyDeviceAccount/editAccessPolicyDeviceAccount",
        type: "POST",
        data: {
            policy_id: $('#modal1_id').val(),
            devices: selecteddevice,
        },
        success: function (data) {
            if (data.success) {
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#policyList');
            } else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
            }
        },
        error: function () {
        }
    })
});
//devicegroup
$("#relevance-device-group").click(function () {
    var selecteddevicegroup = [];
    $("#edit_devicegroup1 input").each(function () {
        selecteddevicegroup.push(this.value);
    })
    $.ajax({
        url: "../../accessPolicyGroup/editAccessPolicyDeviceGroup",
        type: "POST",
        data: {
            policy_id: $('#modal2_id').val(),
            devicegroup: selecteddevicegroup,
        },
        success: function (data) {
            if (data.success) {
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#policyList');
            } else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
            }
        },
        error: function () {
        }
    })
});

/* function show(){
	document.getElementById("edit_id1").value = document.getElementById("edit_id").value;
	$("#edit_name1").val(document.getElementById("edit_name").innerHTML);
	$("#edit_status1").val(document.getElementById("edit_status").innerHTML);
	$("#edit_type1").val(document.getElementById("edit_type").innerHTML);
	$("#edit_changetype1").val(document.getElementById("edit_changetype").innerHTML);
} */
function showfinger1(v){
    if(v=='1'){
        document.getElementById("div4").style.display="block";
    }else{
        document.getElementById("div4").style.display="none";
    }
    if(v=='2'){
        document.getElementById("div3").style.display="block";
    }else{
        document.getElementById("div3").style.display="none";
    }
}
function showfinger(v){
    if(v=='1'){
        document.getElementById("div5").style.display="block";
    }else{
        document.getElementById("div5").style.display="none";
    }
    if(v=='2'){
        document.getElementById("div6").style.display="block";
    }else{
        document.getElementById("div6").style.display="none";
    }
}
//初始化显示当前时间
let date = new Date();
let day = date.getDate();
let month = date.getMonth()+1;
let year = date.getFullYear();
let minDate = year+"-"+month+"-"+day;
function init() {
    //定义locale汉化插件
    var locale = {
        "format": 'YYYY-MM-DD',
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1,

    };
    //日期控件初始化
    $('#add_exec_datetime').daterangepicker({
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        });
    //kaishi
        $('#add_exec_datetime1').daterangepicker({
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",
            minDate: minDate,
        });
  function show1(){
    $('#add_end_datetime').daterangepicker({
        'locale': locale,
        showDropdowns: true,
        autoApply: false,
        singleDatePicker: true,
        opens: "center",
        drops: "up",
        startDate:minDate,
        minDate: $('#add_exec_datetime1').val(),
    });
}
     $('#add_end_datetime').focus(function () {
        show1();
    });
     
     
    //时间判断
    $('#edit_exec_datetime').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        }
    );

    $('#edit_exec_datetime1').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",
            minDate: minDate,
        }
    );

 function show2(){
    $('#edit_end_datetime').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",
            drops: "up",
            startDate:minDate,
            minDate: $('#edit_exec_datetime1').val(),
        }
    );
 };
    $('#edit_end_datetime').focus(function () {
        show2();
    });
}
$(document).ready(function() {
    init();
});
//新建策略
$("#new_strategy").click(function () {
	document.getElementById("div5").style.display="none";
	document.getElementById("div6").style.display="none";
    $("#add_name").val("");
    $("#add_exec_datetime").val("");
    $("#add_exec_datetime1").val("");
    $("#add_internal").val("");
    $("#add_type").val("0");
    $("#add_changetype").val("0");
    $("#add_end_datetime").val("");
    $("#Vadd_name").text("");
    $("#Vadd_exec_datetime").text("");
    $("#Vadd_exec_datetime1").text("");
    $("#Vadd_internal").text("");
    $("#Vadd_end_datetime").text("");
});
function check_step(){
    var flag=true;
    var add_name= $("#add_name").val();
    var p1=/^([\u4E00-\u9FA5]|[A-Za-z0-9]|-){1,64}$/;
    var p2=/^\d{1,365}$/;
    if($('#add_type').find("option:selected").val()==0){
        if(add_name==""){
            $("#Vadd_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(add_name)){
            $("#Vadd_name").text("输入策略名称格式不正确");
            flag=false;
        }
    }else if($('#add_type').find("option:selected").val()==1){
        if(add_name==""){
            $("#Vadd_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(add_name)){
            $("#Vadd_name").text("输入策略名称格式不正确");
            flag=false;
        }
        var add_exec_datetime=$("#add_exec_datetime").val();
        if(add_exec_datetime==""){
            $("#Vadd_exec_datetime").text("请输入执行时间");
            flag=false;
        }
    }else if($('#add_type').find("option:selected").val()==2){
        if(add_name==""){
            $("#Vadd_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(add_name)){
            $("#Vadd_name").text("输入策略名称格式不正确");
            flag=false;
        }
        var add_exec_datetime1=$("#add_exec_datetime1").val();
        if(add_exec_datetime1==""){
            $("#Vadd_exec_datetime1").text("请输入执行时间");
            flag=false;
        }
        var add_internal=$("#add_internal").val();
        if(add_internal==""){
            $("#Vadd_internal").text("请输入执行周期");
            flag=false;
        }else if(!p2.test(add_internal)||add_internal<1||add_internal>365){
            $("#Vadd_internal").text("请输入1到365之间的数字");
            flag=false;
        }
        var add_end_datetime=$("#add_end_datetime").val();
        if(add_end_datetime==""){
            $("#Vadd_end_datetime").text("请输入结束时间");
            flag=false;
        }
    }
    return flag;
}
$("#next_step").click(function () {
    var flag=check_step();
    if(flag==true){
       $("#modal-addpolicy").modal('hide');
       $("#modal-default1").modal('show');
   }
});
$("#add_name").blur(function () {
    var add_name= $("#add_name").val();
    var p1=/^([\u4E00-\u9FA5]|[A-Za-z0-9]|-){1,64}$/;
    if(add_name==""){
        $("#Vadd_name").text("请输入策略名称");
    }else if(!p1.test(add_name)){
        $("#Vadd_name").text("输入策略名称格式不正确");
    }
});
$("#add_name").focus(function () {
    $("#Vadd_name").text("");
});
$("#add_exec_datetime").blur(function () {
    var add_exec_datetime=$("#add_exec_datetime").val();
    if(add_exec_datetime==""){
      $("#Vadd_exec_datetime").text("请输入执行时间");
    }
});
$("#add_exec_datetime").focus(function () {
    $("#Vadd_exec_datetime").text("");
});
$("#add_exec_datetime1").blur(function () {
    var add_exec_datetime1=$("#add_exec_datetime1").val();
    if(add_exec_datetime1==""){
     $("#Vadd_exec_datetime1").text("请输入执行时间");
    }
});
$("#add_exec_datetime1").focus(function () {
    $("#Vadd_exec_datetime1").text("");
});
$("#add_internal").blur(function () {
    var add_internal=$("#add_internal").val();
    var p1=/^\d{1,365}$/;
    if(add_internal==""){
     $("#Vadd_internal").text("请输入执行周期");
    }else if(!p1.test(add_internal)||add_internal<1||add_internal>365){
     $("#Vadd_internal").text("请输入1到365之间的数字");
    }
});
$("#add_internal").focus(function () {
    $("#Vadd_internal").text("");
});
$("#add_end_datetime").blur(function () {
    var add_end_datetime=$("#add_end_datetime").val();
    if(add_end_datetime==""){
      $("#Vadd_end_datetime").text("请输入结束时间");
    }
});
$("#add_end_datetime").focus(function () {
    $("#Vadd_end_datetime").text("");
});
$("#edit_strategy").click(function () {
    $("#Vedit_name").text("");
    $("#Vedit_exec_datetime").text("");
    $("#Vedit_exec_datetime1").text("");
    $("#Vedit_internal").text("");
    $("#Vedit_end_datetime").text("");
});
function check_edit(){
    var flag=true;
    var edit_name= $("#edit_name").val();
    var p1=/^([\u4E00-\u9FA5]|[A-Za-z0-9]|-){1,64}$/;
    var p2=/^\d{1,365}$/;
    if($('#edit_type1').find("option:selected").val()==0){
        if(edit_name==""){
            $("#Vedit_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(edit_name)){
            $("#Vedit_name").text("输入策略名称格式不正确");
            flag=false;
        }
    }else if($('#edit_type1').find("option:selected").val()==1){
        if(edit_name==""){
            $("#Vedit_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(edit_name)){
            $("#Vedit_name").text("输入策略名称格式不正确");
            flag=false;
        }
        var edit_exec_datetime=$("#edit_exec_datetime").val();
        if(edit_exec_datetime==""){
            $("#Vedit_exec_datetime").text("请输入执行时间");
            flag=false;
        }
    }else if($('#edit_type1').find("option:selected").val()==2){
        if(edit_name==""){
            $("#Vedit_name").text("请输入策略名称");
            flag=false;
        }else if(!p1.test(edit_name)){
            $("#Vedit_name").text("输入策略名称格式不正确");
            flag=false;
        }
        var edit_exec_datetime1=$("#edit_exec_datetime1").val();
        if(edit_exec_datetime1==""){
            $("#Vedit_exec_datetime1").text("请输入执行时间");
            flag=false;
        }
        var edit_internal=$("#edit_internal").val();
        if(edit_internal==""){
            $("#Vedit_internal").text("请输入执行周期");
            flag=false;
        }else if(!p2.test(edit_internal)||edit_internal<1||edit_internal>365){
            $("#Vedit_internal").text("请输入1到365之间的数字");
            flag=false;
        }
        var edit_end_datetime=$("#edit_end_datetime").val();
        if(edit_end_datetime==""){
            $("#Vedit_end_datetime").text("请输入结束时间");
            flag=false;
        }
    }
    return flag;
}
$("#edit_name").focus(function () {
    $("#Vedit_name").text("");
});
$("#edit_exec_datetime").focus(function () {
    $("#Vedit_exec_datetime").text("");
});
$("#edit_exec_datetime1").focus(function () {
    $("#Vedit_exec_datetime1").text("");
});
$("#edit_internal").focus(function () {
    $("#Vedit_internal").text("");
});
$("#edit_end_datetime").focus(function () {
    $("#Vedit_end_datetime").text("");
});

//执行
$('#execCmdBtn').click(function(){
	$.ajax({
	    url:"../../changePasswordPolicy/executeCommand",
	    type:"POST",
	    data:{
	        id: $('#policy_id').val()
	    },
	    success:function(data){
	        if(data.success){
	            $("#modal-success .modal-title").text('成功');
	            $("#modal-success .modal-body").text('操作成功!');
	            $("#modal-success").modal();
	        }
	        else{
	            $("#modal-danger .modal-title").text('失败');
	            $("#modal-danger .modal-body").text('操作失败!');
	            $("#modal-danger").modal();
	        }
	        loadAJAX('#scripts_table');
	    },
	    error:function(){
	    }
	})
})