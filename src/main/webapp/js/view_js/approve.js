
//关联设备每次加载多少
const page_length = 500;
var workorder_apply_index = -1;

//工单状态
function status(result){
	switch (result) {
		case 0:return '未提交';
		case 1:return '已提交';
		case 2:return '审批中';
		case 3:return '审批拒绝';
		case 10:return '审批通过';
	}
}

//根据条件查询
var _scripts_table= function (field,value){
	$('#apply_table').DataTable({
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
			"url": "../../workorderAuditLog/listWorkorderApply",
			"data":function (d) {
				for(var key in d){
					if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
						delete d[key];
					}
				}
				eval('d.'+field+'="'+value+'"');
			}
		},
		"columns": [
			{"data": "id", "render": function (data) {
				return '<input type="checkbox" name="chk[]" value=' + data + '>';
			}},
			{"data": "name"},
			{"data": "type","render": function (data) {
					return data==0?'授权工单':'命令工单';

			}},
			{"data": "update_time","render": function (data) {
					return stripMilSeconds(data);
			}},
			{"data": "result","render":function (data) {
					return  '<div>' +status(data)+ '</div>';
			}},
			{"data": "apply_username","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
			{"data": "apply_realname","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
			{
				"data": "id", "render": function (data, type, row, meta) {
					return '<a class="newcss1" data-row="'+meta.row+'" data-target="#work_approve" data-toggle="modal" style="margin-right: 15px;cursor: pointer">查看</a>'
							+(row.result>2 || row.isAudit!=0?'':'<a class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal_default8" style="margin-right: 15px;cursor: pointer">批准</a>'
							+'<a class="newcss1" data-row="'+meta.row+'" data-target="#modal_default2" data-toggle="modal" style="margin-right: 15px;cursor: pointer">驳回</a>');
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
						if (os[j].checked===true){
							count++
						}
					}
					allSelects.checked=(count === os.length)
				})
			}
			//提示工具
			$('#apply_table div').tooltip()
		}
	});
};
_scripts_table('searchAll','');
$('#search').click(function () {
	_scripts_table($('#Distinguish').val(), $('#searchId').val());
});

var _device_table= function (apply_id){
	$('#device_table').DataTable({
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
			"url": "../../workorderAuditLog/listWorkorderApplyDeviceAccount",
			"data":function (d) {
				for(var key in d){
					if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
						delete d[key];
					}
				}
				eval('d.workorder_apply_id="'+apply_id+'"');
			}
		},
		"columns": [
			{"data": "username", "render":function(data, type,row, meta){
				return data==''?'空用户':data;
			}},
			{"data": "device_name"},
			{"data": "ip"},
            { "data": "depart_name" , "render" : function(data, type,row, meta) {
                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                    +data
                    + '</div>';
            }},
			{"data": "port"},
			{"data": "protocol_id", "render": function (data) {
					return _protocol(data);
			}},
			{"data": "id", "render": function (data, type,row, meta) {
					return '<a class="newcss1" data-row="'+meta.row+'" data-target="#work_log" data-toggle="modal" data-dismiss="modal" style="margin-right: 15px;cursor: pointer">审批详情</a>';
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
						if (os[j].checked===true){
							count++
						}
					}
					allSelects.checked=(count === os.length)
				})
			}
			//提示工具
			$('#device_table div').tooltip()
		}
	});
};

var _approve_people_table= function (apply_id){
	$('#approve_people').DataTable({
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
			"url": "../../workorderAuditLog/listAuditPeople",
			"data":function (d) {
				for(var key in d){
					if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
						delete d[key];
					}
				}
				eval('d.workorder_apply_id="'+apply_id+'"');
			}
		},
		"columns": [
			{"data": "step", render: function (data, type, row, meta) {
		        return meta.row + 1 + meta.settings._iDisplayStart; //切换分页序号 也自动叠加
		    }},
			{"data": "audit_username"},
			{"data": "audit_realname"},
            { "data": "depart_name" , "render" : function(data, type,row, mata) {
                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                    +data
                    + '</div>';
            }},
			{"data": "audit_datetime","render": function (data) {
					return stripMilSeconds(data);
			}},
			{"data": "audit_result","render": function (data) {
					return _audit_result(data);
			}},
			{"data": "audit_comment","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
		],
		"fnDrawCallback": function() {
			//全选删除的全选和反全选
			let os=document.getElementsByName('chk[]');
			let allSelects = document.getElementsByTagName('form')[0].getElementsByTagName('input')[0];
			for (let i = 0; i <os.length ; i++) {
				os[i].addEventListener("click",function () {
					let count = 0;
					for (let j = 0; j <os.length ; j++) {
						if (os[j].checked===true){
							count++
						}
					}
					allSelects.checked=(count === os.length)
				})
			}
			//提示工具
			$('#approve_people div').tooltip()
		}
	});
};

var _device_people_table= function (apply_id, device_account_id){
	$('#device_approve_people').DataTable({
		'paging': false,
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
			"url": "../../workorderAuditLog/listWorkorderAuditLog",
			"data":function (d) {
				for(var key in d){
					if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
						delete d[key];
					}
				}
				eval('d.workorder_apply_id="'+apply_id+'"');
				eval('d.device_account_id="'+device_account_id+'"');
			}
		},
		"columns": [
			{"data": "step", render: function (data, type, row, meta) {
		        return meta.row + 1 + meta.settings._iDisplayStart; //切换分页序号 也自动叠加
		    }},
			{"data": "audit_username"},
			{"data": "audit_realname"},
            { "data": "depart_name" , "render" : function(data, type,row, mata) {
                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                    +data
                    + '</div>';
            }},
			{"data": "audit_datetime","render": function (data) {
					return stripMilSeconds(data);
			}},
			{"data": "audit_result","render": function (data) {
					return _audit_result(data);
			}},
			{"data": "audit_comment","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
		],
		"fnDrawCallback": function() {
			//全选删除的全选和反全选
			let os=document.getElementsByName('chk[]');
			let allSelects = document.getElementsByTagName('form')[0].getElementsByTagName('input')[0];
			for (let i = 0; i <os.length ; i++) {
				os[i].addEventListener("click",function () {
					let count = 0;
					for (let j = 0; j <os.length ; j++) {
						if (os[j].checked===true){
							count++
						}
					}
					allSelects.checked=(count === os.length)
				})
			}
			//提示工具
			$('#approve_people div').tooltip()
		}
	});
};

function return_to_log(){
	$('#work_log').modal('hide');
	$('#work_approve').modal('show');
}
//编辑的回显
$('#work_approve').on('show.bs.modal',function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	if(i!=undefined){
		workorder_apply_index = i;
	}else if(i==undefined&&workorder_apply_index>-1){
		$('.nav.nav-tabs li:eq(1) a').tab('show');
		return;
	}
	_device_table($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].id);
	_approve_people_table($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#name').text($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].name);
	$('#type').text($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].type==0?'授权工单':'命令工单');
	$('#result').text(status($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].result));
	$('#username').text($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].apply_username);
	$('#realname').text($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].apply_realname);
	$('#update_time').text(stripMilSeconds($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].update_time));
	$('#start_end_time').text(stripMilSeconds($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].start)+"~"+stripMilSeconds($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].end));
	$('#description').text($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].description);
	var file = [];
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].upload==1?'文件上传':'');
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].download==1?'文件下载':'');
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].filemanage==1?'文件管理':'');
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].up_clipboard==1?'上行剪切板':'');
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].down_clipboard==1?'下行剪切板':'');
	file[file.length] = ($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].watermark==1?'显示水印	':'');
	$('#functions').text(file.filter(function (s) {return s && s.trim()}));
	
});

$('#work_log').on('show.bs.modal',function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	_device_people_table($('#device_table').DataTable().row('#' + i).nodes(i).data()[i].workorder_apply_id,$('#device_table').DataTable().row('#' + i).nodes(i).data()[i].device_account_id)
});

//回显删除
$('#modal_default').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#del_work').val($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//
$('#approveBtn').click(function () {
	$.ajax({
		url: "../../workorderAuditLog/editWorkorderAuditLog",
		type: "POST",
		data: {
			workorder_apply_id: $('#approve_apply_id').val(),
			audit_comment: $('#approve_audit_comment').val(),
			audit_result:2
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
			}
			loadAJAX('#apply_table');
		},
		error: function () {
			loadAJAX('#apply_table');
		},
	})
});

//授权工单的删除
$('#rejectBtn').click(function () {
	$.ajax({
		url: "../../workorderAuditLog/editWorkorderAuditLog",
		type: "POST",
		data: {
			workorder_apply_id: $('#reject_apply_id').val(),
			audit_comment: $('#reject_audit_comment').val(),
			audit_result:1
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
			}
			loadAJAX('#apply_table');
		},
		error: function () {
			loadAJAX('#apply_table');
		},
	})
});
//授权工单的提交
$('#modal_default8').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#approve_apply_id').val($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].id);
});
$('#modal_default2').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#reject_apply_id').val($('#apply_table').DataTable().row('#' + i).nodes(i).data()[i].id);
});
