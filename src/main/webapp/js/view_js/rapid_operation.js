//关联一系列操作
//新建显示关联信息
let listAddUser = null;
let listUserGroup = null;
let listDeviceAccount = null;
let listDeviceGroup = null;
const page_length = 500;
function listAll() {
    let page_start7 =0;
    let page_start8 =0;
    //设备账号
    $.ajax({
        url: "../../deviceAccount/listDeviceAccountNameIp",
        type: "POST",
        async: true,
        dataType: "json",
        data: {
            start: page_start7,
            length: page_length
        },
        success: function (data) {
            $('#add_device').html('');
            $('#add_device1').html('');
            var data = data.data;
            for(let item of data) {
                $('#add_device').append('<div><input value="' + item.device_account_id + '" type="checkbox"><span>' + item.device_name + "[" + item.username + "]" + "[" + item.protocol_name + "]" + '</span></div>');
            }
            RelativeMethods(2);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            listDeviceAccount = $('#add_device').html();//保存显示出来的原本的数据
            //慢加载
            $('#add_device').on("scroll",function () {
                let viewH =$(this).height(),//可见高度
                    contentH =$(this).get(0).scrollHeight,//内容高度
                    scrollTop =$(this).scrollTop();//滚动高度
                if(contentH - viewH - scrollTop <= 60) {
                    page_start7 += 500;
                    $.ajax({
                        url: "../../deviceAccount/listDeviceAccountNameIp",
                        type: "POST",
                        data: {
                            start: page_start7,
                            length: page_length
                        },
                        success: function (data) {
                            var data = data.data;
                            for(let item of data) {
                                $('#add_device').append('<div><input value="' + item.device_account_id + '" type="checkbox"><span>' + item.device_name + "[" + item.username + "]" + "[" + item.protocol_name + "]" + '</span></div>');
                            }
                            RelativeMethods(2);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
                            listDeviceAccount = $('#add_device').html();//保存显示出来的原本的数据
                        },
                    })
                }
            })
        },
        error: function () {
        }
    })
    //设备组
    $.ajax({
        url: "../../group/listGroup",
        type: "POST",
        async: true,
        dataType: "json",
        data: {
            type: 1,
            start: page_start8,
            length: page_length,
            name: $('#searchIdG').val(),
        },
        success: function (data) {
            $('#add_devicegroup').html('');
            $('#add_devicegroup1').html('');
            var data = data.data;
            for(let item of data){
                $('#add_devicegroup').append('<div><input value="' + item.id + '" type="checkbox"><span>' +item.name + '</span></div>');
            }
            RelativeMethods(3);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            listDeviceGroup = $('#add_devicegroup').html();//保存显示出来的原本的数据
            //慢加载
            $('#add_devicegroup').on("scroll",function () {
                let viewH =$(this).height(),//可见高度
                    contentH =$(this).get(0).scrollHeight,//内容高度
                    scrollTop =$(this).scrollTop();//滚动高度
                if(contentH - viewH - scrollTop <= 60) {
                    page_start8 += 500;
                    $.ajax({
                        url: "../../group/listGroup",
                        type: "POST",
                        data: {
                            type: 1,
                            start: page_start8,
                            length: page_length,
                            name: $('#searchIdG').val(),
                        },
                        success: function (data) {
                            var data = data.data;
                            for(let item of data){
                                $('#add_devicegroup').append('<div><input value="' + item.id + '" type="checkbox"><span>' +item.name + '</span></div>');
                            }
                            RelativeMethods(3);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
                            listDeviceGroup = $('#add_devicegroup').html();//保存显示出来的原本的数据
                        },
                    })
                }
            })
        },
        error: function () {
        }
    })
}
//deviceAccount
let ac_edit_device_list = null;
let ac_edit_device1_list = null;
$('#modal-primary3').on('show.bs.modal', function (event) {
    let page_start2 =0;
    let button = $(event.relatedTarget) // Button that triggered the modal
    let i = button.data('row');
    $('#modal8_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].id);
    $.ajax({
        url:"../../crontabScriptConfigDevice/findCrontScriptConfigDevice",
        type: "POST",
        data: {
            config_id: $('#modal8_id').val(),
            page_start:page_start2,
            page_length:page_length
        },
        success: function (data) {
            let arr = data.data_device;
            let arr1 = data.data_p_device;
            //show
            $('#edit_device').html('');
            $('#edit_device1').html('');
            for (let item of arr) {
                $('#edit_device').append('<div><input value="' + item.device_account_id + '" type="checkbox"><span>' + item.device_name + "[" + item.username + "]" + "[" + item.protocol_name + "]" + '</span></div>')
            }
            //已选择数据
            for(let item1 of arr1){
                $('#edit_device1').append('<div><input value="' + item1.device_account_id + '" type="checkbox" ><span>' + item1.device_name + "[" + item1.username + "]" + "[" + item1.protocol_name + "]" + '</span></div>')
            }
            RelativeMethods(7);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            ac_edit_device_list = $('#edit_device').html();
            ac_edit_device1_list = $('#edit_device1').html();
            //慢加载
            $('#edit_device').on("scroll",function () {
                let viewH =$(this).height(),//可见高度
                    contentH =$(this).get(0).scrollHeight,//内容高度
                    scrollTop =$(this).scrollTop();//滚动高度
                if(contentH - viewH - scrollTop <= 60) {
                    page_start2 += 500;
                    $.ajax({
                        url:"../../crontabScriptConfigDevice/findCrontScriptConfigDevice",
                        type: "POST",
                        data: {
                            config_id: $('#modal8_id').val(),
                            page_start:page_start2,
                            page_length:page_length
                        },
                        success: function (data) {
                            let arr = data.data_device;
                            for (let item of arr) {
                                $('#edit_device').append('<div><input value="' + item.device_account_id + '" type="checkbox"><span>' + item.device_name + "[" + item.username + "]" + "[" + item.protocol_name + "]" + '</span></div>')
                            }
                            RelativeMethods(7);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
                            ac_edit_device_list = $('#edit_device').html();
                        },
                    })
                }
            })
        },
        error: function () {
        },
    })
});

//device-group
let ac_edit_devicegroup_list = null;
let ac_edit_devicegroup1_list = null;
$('#modal-primary4').on('show.bs.modal', function (event) {
    let page_start3 =0;
    let button = $(event.relatedTarget);
    let i = button.data('row');
    $("#modal-primary4 .modal-title").text("关联设备组["+$('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
    $('#modal9_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].id);
    $.ajax({
        url:"../../crontabScriptConfigGroup/findCrontabScriptconfigDeviceGroup",
        type: "POST",
        data: {
            config_id: $('#modal9_id').val(),
            page_start:page_start3,
            page_length:page_length
        },
        success: function (data) {
            let arr = data.data_device_group;
            let arr1 = data.data_p_device_group;
            //show
            $('#edit_devicegroup').html('');
            $('#edit_devicegroup1').html('');
            for(let item of arr){
                $('#edit_devicegroup').append('<div><input value="' + item.dgroup_id + '" type="checkbox"><span>' + item.dgroup_name + '</span></div>')
            }
            for(let item1 of arr1){
                $('#edit_devicegroup1').append('<div><input value="' + item1.dgroup_id + '" type="checkbox"><span>' + item1.dgroup_name + '</span></div>')
            }
            RelativeMethods(8);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
            ac_edit_devicegroup_list = $('#edit_devicegroup').html();
            ac_edit_devicegroup1_list = $('#edit_devicegroup1').html();
            //慢加载
            $('#edit_devicegroup').on("scroll",function () {
                let viewH =$(this).height(),//可见高度
                    contentH =$(this).get(0).scrollHeight,//内容高度
                    scrollTop =$(this).scrollTop();//滚动高度
                if(contentH - viewH - scrollTop <= 60) {
                    page_start3 += 500;
                    $.ajax({
                        url:"../../crontabScriptConfigGroup/findCrontabScriptconfigDeviceGroup",
                        type: "POST",
                        data: {
                            config_id: $('#modal9_id').val(),
                            page_start:page_start3,
                            page_length:page_length
                        },
                        success: function (data) {
                            let arr = data.data_device_group;
                            for(let item of arr){
                                $('#edit_devicegroup').append('<div><input value="' + item.dgroup_id + '" type="checkbox"><span>' + item.dgroup_name + '</span></div>')
                            }
                            RelativeMethods(8);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
                            ac_edit_devicegroup_list = $('#edit_devicegroup').html();
                        },
                    })
                }
            })
        }
    })
});
//编辑关联信息
$("#relevance-user").click(function () {
    let selecteduser = [];
    $("#edit_user1 input").each(function () {
        selecteduser.push(this.value);
    })
    $.ajax({
        url:"../../crontabScriptUser/editAccessPolicyUser",
        type: "POST",
        data: {
            config_id: $('#modal6_id').val(),
            users: selecteduser,
        },
        success: function (data) {
            if (data.success) {
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#scripts_table');
            } else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
                loadAJAX('#scripts_table');
            }
        },
        error: function () {
        }
    })
});
//device
$("#relevance-device").click(function () {
    let selecteddevice = [];
    $("#edit_device1 input").each(function () {
        selecteddevice.push(this.value);
    });
    $.ajax({
        url: "../../crontabScriptConfigDevice/editCrontabScriptConfigDevice",
        type: "POST",
        data: {
            config_id: $('#modal8_id').val(),
            devices: selecteddevice,
        },
        success: function (data) {
            if (data.success) {
            
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
            } else {
           
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
            }
            loadAJAX('#scripts_table');
        },
        error: function () {
        	
        }
    })
});
//usergroup
$("#relevance-usergroup").click(function () {
    let selectedgroup = [];
    $("#edit_usergroup1 input").each(function () {
        selectedgroup.push(this.value);
    });
    $.ajax({
        url: "../../crontabScriptConfigGroup/editCrontabScriptConfigUserGroup",
        type: "POST",
        data: {
            config_id: $('#modal7_id').val(),
            groups: selectedgroup,
        },
        success: function (data) {
            if (data.success) {
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#scripts_table');
            } else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
                loadAJAX('#scripts_table');
            }
        },
        error: function () {
        }
    })
});
//devicegroup
$("#relevance-device-group").click(function () {
	console.log("hohhh");
    let selecteddevicegroup = [];
    $("#edit_devicegroup1 input").each(function () {
        selecteddevicegroup.push(this.value);
    });
    $.ajax({
        url: "../../crontabScriptConfigGroup/editCrontabScriptConfigDeviceGroup",
        type: "POST",
        data: {
            config_id: $('#modal9_id').val(),
            devicegroup: selecteddevicegroup,
        },
        success: function (data) {
            if (data.success) {
            	console.log("hoo")
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                loadAJAX('#scripts_table');
            } else {
             	console.log("aoo")
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
                loadAJAX('#scripts_table');
            }
        },
        error: function () {
        	console.log("fail")
        }
    })
});
function exec_cycle(value){
	switch(value){
		case 1:
			return '每分钟';
			break;
		case 2:
			return '每小时';
			break;
		case 1:
			return '每天';
			break;
		case 2:
			return '每周';
			break;
		case 2:
			return '每月';
			break;
		default:
			return '未知';
			break;
			
	}
}
//根据条件查询
var _scripts_table= function (field,value){
    $('#scripts_table').DataTable({
        'paging': true,
        'lengthChange': true,
        "iDisplayLength": 10,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom': 't<"bottom"lifp<"clear">>',
        'searching': false,
        'ordering': true,
        'info': true,
        'autoWidth': false,
        "serverSide": true,
        'destroy': true,
        "ajax": {
            "url": "../../crontabScriptConfig/listCrontabScriptConfig",
            "data":function (d) {
                if (field === "searchAll" || field === "exec_method"){
                    if ($('#searchId').val().match("手")||$('#searchId').val().match("动")||$('#searchId').val().match("手动")||$('#searchId').val().match("手动执行")){
                        value=0;
                        field="exec_method";
                    }else if($('#searchId').val().match("时")||$('#searchId').val().match("定时")||$('#searchId').val().match("定时执行")){
                        value=1;
                        field="exec_method";
                    } else if($('#searchId').val().match("期")||$('#searchId').val().match("定期")||$('#searchId').val().match("定期执行")){
                        value=2;
                        field="exec_method";
                    }else if($('#searchId').val()===''){
                        value='';
                    }else{
                        if(field === "searchAll"){
                            value=$('#searchId').val();
                        }else if(field === "exec_method"){
                            value=3;
                        }
                    }
                }
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                eval('d.'+field+'="'+value+'"');
            }
        },
        "columns": [
            {
                "data": "id", "render": function (data) {
                    return '<input type="checkbox" name="chk[]" value=' + data + '>';
                }
            },
            {"data": "name"},
            { "data": "depart_name" , "render" : function(data, type,row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                        +data
                        + '</div>';
                }},
            {"data": "command","render": function (data,type,row,meta) {
                    return  '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">' +data + '</div>';
                }},
            {"data": "description","render":function (data) {
                    return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
                }},
            {"data": "status","render":function (data) {
            	// return data==0?'否':'是';
                    if(data==0){
                        status='否';
                    }else{
                        status='是';
                    }
                    return  '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+status+'">'
                        +status
                        + '</div>';
            }},
            {"data": "exec_method","render":function (data, type, row, meta) {
            /*	return data==0?'手动执行':(data==1?'定时执行,时间:'+row.exec_datetime:'定期执行,周期:'+exec_cycle(data));*/
            	if(data==0){
            		exec_method='手动执行';
            	}else if(data==1){
            		exec_method='定时执行,时间:'+row.exec_datetime;
            	}else{
            		exec_method='定期执行,周期:'+exec_cycle(data);
            	}
            	return  '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+exec_method+'">'
                    +exec_method
                    + '</div>';
            }},
            {
                "data": "id", "render": function (data, type, row, meta) {
                    return '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal-primary8" style="cursor:pointer;vertical-align: bottom;">编辑</a>' +
		                    '<div class="btn-group" style="margin-left:20px">' +
		                    '<a class="newcss1 dropdown-toggle" data-toggle="dropdown" style="line-height: 1.5px;cursor:pointer">关联</a>' +
		                    '<a dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="line-height: 1.5px;cursor:pointer">' +
		                    '<span style="margin-bottom:3px;font-size:14px;" class="caret"></span>' +
		                    '<span class="sr-only">Toggle Dropdown</span>' +
		                    ' </a>' +
		                    ' <ul class="dropdown-menu" role="menu" style="font-size: 12px">' +
		                    '  <li><a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary3">执行设备</a></li>' +
		                    ' <li><a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary4">执行设备组</a></li>' +
		                    '</ul> ' +
                        '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-execmd" style="margin-left: 20px;cursor:pointer;">立即执行</a>' +
                        '<a  data-toggle="modal"  data-row="'+meta.row+'"  class="newcss2" data-target="#modal-default1" style="margin-left: 20px;cursor:pointer">删除</a>'
                }
            }
        ],
        "fnDrawCallback": function() {
            //提示工具
            $('#scripts_table div').tooltip()
        }
    });
}
_scripts_table('searchAll','');
$('#search').click(function () {
    _scripts_table($('#Distinguish').val(), $('#searchId').val());
});
//正则表达式
let regexp = {
    name: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}$/,
    desc: /(^$)|(^([A-Za-z]|[\u4e00-\u9fa5]|\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,128}$)/
};

//获取焦点，重新输入
$('#edit_name').focus(function () {
    $('#judge_name').text('');
});
$('#edit_desc').focus(function () {
    $('#judge_desc').text('');
});
//失去焦点
$('#edit_name').blur(function () {
    if($("#edit_name").val()==""){
        $('#judge_name').text("请输入任务名称");
    }else if (!regexp.name.test($("#edit_name").val())) {
        $('#judge_name').text("请输入正确的任务名称");
    }else{
        checkName()
    }
});
$('#edit_desc').blur(function () {
    if (!regexp.desc.test($("#edit_desc").val())) {
        $('#judge_desc').text("超过限制长度");
    }else{
        $('#judge_desc').text("");
    }
});
//检查重名
function checkName() {
    $.ajax({
        url: "../../crontabScriptConfig/checkName",
        type: "POST",
        data: {
            id:$('#edit_id').val(),
            name: $('#edit_name').val()
        },
        success: function (data) {
            if (!data.success) {
                $('#judge_name').text('任务名称重复');
                return false;
            }else {
                $('#judge_name').text('');
            }
        }
    })
}

//下一步
$('#editButton').click(function () {
    var flag=true;
    if($("#edit_name").val()==""){
        $('#judge_name').text("请输入任务名称");
        flag=false;
    }else if (!regexp.name.test($("#edit_name").val())) {
        $('#judge_name').text("请输入正确的任务名称");
        flag = false;
    }else {
        checkName();
    }
    if($("#edit_desc").val()!=""&&!regexp.desc.test($("#edit_desc").val())){
        $('#judge_desc').text("超过限制长度");
        flag=false;
    }
    if($('#judge_name').text()!=""){
        flag = false;
    }
    return flag;
});

//新建去除以前样式
$('#newadd').click(function () {
    $('#edit_name').val('');
    $('#edit_desc').val('');
    $('#typeName option:first').prop('selected',"selected");
    $('#edit_method option:first').prop('selected',"selected");
    $('#edit_su').prop("checked",false);
    $("#div11").css("display","none");
    $('#reservation').val();
    $('#reservation1').val();
    $('#edit_cycle').val("minute");
    document.getElementById("div7").style.display = "none";
    listAll();
    document.getElementById("div8").style.display = "none";
});
//添加编辑的回显
$('#modal-primary8').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    if(button.data('row')!=undefined&&button.data('row')!=null){
        $('.modal-title').text('编辑执行任务');
        $('#editButton').css("display","none");
        $('#editButton1').css("display","inline-block");
        $('#judge_name').text('');
        $('#judge_desc').text('');
        let i = button.data('row');
        $('#edit_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_name').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#command').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].command);
        $('#edit_method').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].exec_method);
        $('#edit_cycle').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].exec_cycle);
        if ($('#edit_method').val() == 0) {
            document.getElementById("div7").style.display = "none";
            document.getElementById("div8").style.display = "none";
        }
        if ($('#edit_method').val() == 1) {
            document.getElementById("div7").style.display = "block";
            document.getElementById("div8").style.display = "none";
            $('#reservation').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
        }
        if ($('#edit_method').val() == 2) {
            document.getElementById("div7").style.display = "block";
            document.getElementById("div8").style.display = "block";
            $('#reservation').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
            $('#reservation1').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
        }
        $('#typeName').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].file_id);
        $('#file_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].file_id);
        showSSHKEY1($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].file_id);
        fileInput($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].file_id);
        $('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].su==0?$('#edit_su').prop("checked",false):$('#edit_su').prop("checked",true);
        $('#edit_desc').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].description);
        $('#down').css("display",'block');
        $(".file-caption-name").val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].script_name);
    }else{
        $('.modal-title').text('添加执行任务');
        $('#editButton1').css("display","none");
        $('#editButton').css("display","inline-block");
        $('#edit_id').val('');
        $('#judge_name').text('');
        $('#div10').css("display","block");
        $('#down').css("display","none");
        $('#command').val('');
        $('#typeName').val(0);
        $('#judge_desc').text('');
        $(".fileinput-remove-button").click();
        fileInput('');
    }
});

//添加
$('.add-policy').click(function(){
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
    //执行时间
    let [startTime,endTime] = ['',''];
    if ($('#edit_method').val()=="定时执行"){
        [startTime,endTime] = [$('#reservation').val(),''];
    }else if($('#edit_method').val()=="周期执行"){
        [startTime,endTime] = [$('#reservation1').val(),$('#reservation2').val()];
    }else{
        [startTime,endTime] = ['',''];
    }
    $.ajax({
        url:"../../crontabScriptConfig/addCrontabScriptConfig",
        type:"POST",
        data:{
        	name:$('#edit_name').val(),
            exec_method:$('#edit_method').val(),
            file_id:$('#file_id').val(),
            su:$('#edit_su').prop("checked")==true?1:0,
            exec_datetime:$('#reservation').val(),
            end_datetime:$('#reservation1').val(),
            exec_cycle:$('#edit_cycle').val(),
            command:$('#command').val(),
            description:$('#edit_desc').val(),
            devices:selecteddevice,
            devicegroup:selecteddevicegroup,
        },
        success:function(data){
            if(data.success){
            	console.log("hhh")
            	$("#modal-default3").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('新建成功!');
                $("#modal-success").modal();
                loadAJAX('#scripts_table');
            }
            else{
            	console.log("kkk")
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('新建失败!');
                $("#modal-danger").modal();
                loadAJAX('#scripts_table');
            }
        },
        error:function(){
        	console.log("aaa")
        }
    })
});
//编辑
$('#editButton1').click(function(){
    checkName();
    function checkOperation(){
        var flag=true;
        if($("#edit_name").val()==""){
            $('#judge_name').text("请输入任务名称");
            flag=false;
        }else if (!regexp.name.test($("#edit_name").val())) {
            $('#judge_name').text("请输入正确的任务名称");
            flag = false;
        }
        if($("#edit_desc").val()!=""&&!regexp.desc.test($("#edit_desc").val())){
            $('#judge_desc').text("超过限制长度");
            flag=false;
        }
        if($('#judge_name').text()!=""){
            flag = false;
        }
        return flag;
    };
    //执行时间
    let [startTime1,endTime1] = ['',''];
    if ($('#edit_method').val()=="定时执行"){
        [startTime1,endTime1] = [$('#reservation').val(),''];
    }else if($('#edit_method').val()=="周期执行"){
        [startTime1,endTime1] = [$('#reservation1').val(),$('#reservation2').val()];
    }else{
        [startTime1,endTime1] = ['',''];
    }
    if(checkOperation()){
        $.ajax({
            url: "../../crontabScriptConfig/editCrontabScriptConfig",
            type: "POST",
            data: {
                id: $('#edit_id').val(),
                name: $('#edit_name').val(),
                exec_method: $('#edit_method').val(),
                file_id: $('#file_id').val(),
                su: $('#edit_su').prop("checked") == true ? 1 : 0,
                exec_datetime: $('#reservation').val(),
                end_datetime: $('#reservation1').val(),
                exec_cycle: $('#edit_cycle').val(),
                command: $('#command').val(),
                description: $('#edit_desc').val()
            },
            success: function (data) {
                if (data.success) {
                    $('#modal-primary8').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    loadAJAX('#scripts_table');
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function () {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
                loadAJAX('#scripts_table');
            }
        })
    }
});
//删除的回显
$('#modal-default1').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let i = button.data('row');
    $('#del_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].id);
});


$('#modal-execmd').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    $('#config_id').val($('#scripts_table').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//单个删除
$('#delButton').click(function(){
    $.ajax({
        url:"../../crontabScriptConfig/delCrontabScriptConfig",
        type:"POST",
        data:{
            ids: new Array($('#del_id').val())
        },
        success:function(data){
            if(data.success){
            	$("#modal-default1").modal('hide');
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
            loadAJAX('#scripts_table');
        }
    })
});
//全选删除
$('#delAllbutton').click(function(){
    let obj = document.getElementsByName('chk[]');
    let ids = new Array();
    for (let i in obj) {
        if (obj[i].checked)
            ids.push(obj[i].value);
    }
    if (ids.length == 0) {
        $("#modal-hint .modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        return false;
    }
    $.ajax({
        url:"../../crontabScriptConfig/delCrontabScriptConfig",
        type:"POST",
        data:{
            ids: ids
        },
        success:function(data){
            if(data.success){
            	$('#modal-default4').modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                loadAJAX('#scripts_table');
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
            loadAJAX('#scripts_table');
        }
    })
});
//执行方式
function showSSHKEY(v) {
    if (v === '0' || v === 0) {
        document.getElementById("div7").style.display = "none";
    }else {
        document.getElementById("div7").style.display = "block";
    }
}
function showSSHKEY1(v){
    if (v === 0 || v === '0') {
        $('#div11').css("display","none");
        $('#div10').css("display","block");
        $('#typeName').val(0);
    }else{
        $('#div11').css("display","block");
        $('#div10').css("display","none");
        $('#typeName').val(1);
    }
}
//时间选择器
function init() {
    //定义locale汉化插件
    var locale = {
        "format": 'YYYY-MM-DD HH:MM:SS',
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1

    };
    //日期控件初始化
    $('#reservation').daterangepicker(
        {
            'locale': locale,
            showDropdowns: true,
            timePicker: true, //显示时间
            timePicker24Hour: true, //时间制
            timePickerSeconds: true, //时间显示到秒
            autoApply: false,
            singleDatePicker: true,
            opens: "center",
        }
    );
    $('#reservation1').daterangepicker(
        {
            'locale': locale,
            showDropdowns: true,
            timePicker: true, //显示时间
            timePicker24Hour: true, //时间制
            timePickerSeconds: true, //时间显示到秒
            autoApply: false,
            singleDatePicker: true,
            opens: "center",
        }
    );
};
$('#down').click(function(){
    window.location.href="../../file/down?file_id="+$('#file_id').val()
});
var fileInput = function(file_id){
    $("#scriptfile-input").fileinput({
        uploadUrl: "../../file/uploadScript",
        enableResumableUpload: false,
        resumableUploadOptions: {
        },
        uploadExtraData: {
            'uploadToken': new Date().getTime(), // for access control / security
        },
        maxFileCount: 1,
        language: 'zh',
        allowedPreviewTypes: ['html','text'],    // allow only images
        showCancel: true,
        showPreview: false,
        initialPreviewAsData: true,
        overwriteInitial: false,
        initialPreviewDownloadUrl:false,
        theme: 'fas',
        deleteUrl: "../../file/deleteFile?file_id="+file_id
    }).on('fileuploaded', function(event, previewId, index, fileId) {
        console.log('File Uploaded', 'ID: ' + fileId + ', Thumb ID: ' + previewId);
        $('#file_id').val(arguments[1].response['file-id']);
    }).on('fileuploaderror', function(event, data, msg) {
        console.log('File Upload Error', 'ID: ' + data.fileId + ', Thumb ID: ' + data.previewId);
    }).on('filebatchuploadcomplete', function(event, preview, config, tags, extraData) {
        console.log('File Batch Uploaded', preview, config, tags, extraData);
    }).on('filesuccessremove', function(event, id) {
        console.log('Uploaded thumbnail successfully removed');
    });
}
$(document).ready(function () {
    init();
});

$('#execCmdBtn').click(function(){
	$.ajax({
	    url:"../../crontabScriptConfig/executeCommand",
	    type:"POST",
	    data:{
	        id: $('#config_id').val()
	    },
	    success:function(data){
	        if(data.success){
	        	$('#modal-execmd').modal('hide');
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
	        loadAJAX('#scripts_table');
	    }
	})
});
//导出清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("");
});
$('#queryPsw').focus(function () {
    $('#PswRep').text("");
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
                $('#modal-output').modal('hide');
                // $("#modal-success .modal-title").text('');
                $("#modal-success .modal-body").text('导出!');
                // $("#modal-success").modal();
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#scripts_table');
                }else {
                    $("#PswRep").text(data.msg)
                }
            }
        },
        error:function(){
        }
    })
});

//下载模版
$('#downTemplate').click(function(){
    $('#downClick')[0].click();
});

$("#upload").off().on("click", function () {
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
        url: "../../upload/opration",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.success){
                $("#modal-upload").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('导入成功!');
                $("#modal-success").modal();
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

//导入清空内容
function resetFileInput(){
    $('#Vfile').text('');
    $('#btn_file').val('');
    $("#filename").text('');
}
function openFile() {
    $('#Vfile').text('');
    $("#filename").text('');
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
