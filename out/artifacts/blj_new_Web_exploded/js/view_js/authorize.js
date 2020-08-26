function init() {
	//定义locale汉化插件
	let locale = {
		format: "YYYY-MM-DD HH:mm",
		"applyLabel": "确定",
		"cancelLabel": "取消",
		"fromLabel": "起始时间",
		"toLabel": "结束时间'",
		"weekLabel": "W",
		"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
		"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		"firstDay": 1,
	};
	let date = new Date();
	let day = date.getDate();
	let month = date.getMonth() + 1;
	let year = date.getFullYear();
	let minDate = year + "-" + month + "-" + day;
	//日期控件初始化
	$('#effect_time').daterangepicker({
		locale: locale,
		autoApply: false,
		timePicker: true, //显示时间
		timePicker24Hour: true, //时间制
		showDropdowns: true, //年月份下拉框
		singleDatePicker: true,
		startDate: minDate,
		minDate: minDate
	});
	$('#fail_time').daterangepicker({
		locale: locale,
		autoApply: false,
		singleDatePicker: true,
		timePicker: true, //显示时间
		timePicker24Hour: true, //时间制
		startDate: minDate,
		minDate: $('#effect_time').val(),//动态获取截止日期的最小取值范围
	});
	$('#effect_time').blur(() => {
		$('#fail_time').daterangepicker(
			{
				locale: locale,
				autoApply: false,
				timePicker: true, //显示时间
				timePicker24Hour: true, //时间制
				singleDatePicker: true,
				startDate: minDate,
				minDate: $('#effect_time').val(),//动态获取截止日期的最小取值范围
			}
		);
	});
}
$(document).ready(function() {
	init();
});
const page_length = 500;
//根据条件查询
var _scripts_table= function (field,value){
	$('#example2').DataTable({
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
			"url": "../../workorderApply/listWorkorderApply",
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
			{"data": "update_time","render": function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'

			}},
			{"data": "result","render":function (data) {
					return  '<div>' +(data===0?"等待申请":(data===1?"审批中":"审批通过")) + '</div>';
			}},
			{"data": "description","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
			{
				"data": "id", "render": function (data, type, row, meta) {
					return '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal-primary1" style="cursor:pointer;vertical-align: bottom;">编辑</a>' +
						'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-primary8" style="margin-left: 20px;cursor:pointer;">关联设备</a>' +
						'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-withdraw"  style="margin-left: 20px;cursor:pointer;">撤回</a>' +
						'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal_default8" style="margin-left: 20px;cursor:pointer;">提交</a>' +
						'<a  data-toggle="modal"  data-row="'+meta.row+'"  class="newcss2" data-target="#modal_default" style="margin-left: 20px;cursor:pointer">删除</a>'
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
			$('#example2 div').tooltip()
		}
	});
};
_scripts_table('searchAll','');
$('#search').click(function () {
	_scripts_table($('#Distinguish').val(), $('#searchId').val());
});

//新建授权工单
$('#addWork').click(function () {
	let selectedDevice = [];
	$('#add_device1 input').each(function () {
		selectedDevice.push(this.value);
	});
	$.ajax({
		url: "../../workorderApply/addWorkorderApply",
		type: "POST",
		data: {
			start:$('#effect_time').val(),
			end:$('#fail_time').val(),
			upload:$('#upload').prop("checked")===true?1:0,
			download:$('#download').prop("checked")===true?1:0,
			filemanage:$('#filemanage').prop("checked")===true?1:0,
			up_clipboard:$('#filemanage').prop("checked")===true?1:0,
			down_clipboard:$('#down_clipboard').prop("checked")===true?1:0,
			watermark:$('#watermark').prop("checked")===true?1:0,
			description:$('#description').val(),
			devices: selectedDevice
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('新建成功!');
				$("#modal-success").modal();
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('新建失败!');
				$("#modal-danger").modal();
			}
			loadAJAX('#example2');
		}
	})
});
//编辑的回显
$('#modal-primary1').on('show.bs.modal',function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#authorize_id1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#effect_time1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].start);
	$('#fail_time1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].end);
	$('#upload1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].upload===0?false:true);
	$('#download1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].download===0?false:true);
	$('#filemanage1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].filemanage===0?false:true);
	$('#up_clipboard1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].up_clipboard===0?false:true);
	$('#down_clipboard1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].down_clipboard===0?false:true);
	$('#watermark1').prop("checked",$('#example2').DataTable().row('#' + i).nodes(i).data()[i].watermark===0?false:true);
	$('#description1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].description)
});
//编辑授权工单
$('#authorButton1').click(function () {
$.ajax({
	url: "../../workorderApply/addWorkorderApply",
	type: "POST",
	data: {
		id:$('#authorize_id1').val(),
		start:$('#effect_time1').val(),
		end:$('#fail_time1').val(),
		upload:$('#upload1').prop("checked")===true?1:0,
		download:$('#download1').prop("checked")===true?1:0,
		filemanage:$('#filemanage1').prop("checked")===true?1:0,
		up_clipboard:$('#up_clipboard1').prop("checked")===true?1:0,
		down_clipboard:$('#down_clipboard1').prop("checked")===true?1:0,
		watermark:$('#watermark1').prop("checked")===true?1:0,
		description:$('#description1').val(),
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
		loadAJAX('#example2');
	}
})
});

//新建设备的回显
$('#modal-primary5').on('show.bs.modal',function () {
	let page_start7 = 0;
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
			var data = data.data;
			for (let item of data) {
				$('#add_device').append('<div><input value="' + item.device_account_id + '" type="checkbox"><span>' + item.device_name + "[" + item.username + "]" + "[" + item.protocol_name + "]" + '</span></div>');
			}
			RelativeMethods(2);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			listDeviceAccount = $('#add_device').html();//保存显示出来的原本的数据
			//慢加载
			$('#add_device').on("scroll", function () {
				let viewH = $(this).height(),//可见高度
					contentH = $(this).get(0).scrollHeight,//内容高度
					scrollTop = $(this).scrollTop();//滚动高度
				if (contentH - viewH - scrollTop <= 60) {
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
							for (let item of data) {
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
	});
});
//关联设备的回显
let ac_edit_device_list = null;
let ac_edit_device1_list = null;
$('#modal-primary8').on('show.bs.modal', function (event) {
	let page_start2 =0;
	var button = $(event.relatedTarget) // Button that triggered the modal
	var i = button.data('row');
	$("#modal-primary8 .modal-title").text("关联设备["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#modal8_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url: "../../workorderApplyDeviceAccount/listWorkorderApplyDeviceAccount",
		type: "POST",
		data: {
			workorder_apply_id: $('#modal8_id').val(),
			page_start:page_start2,
			page_length:page_length
		},
		success: function (data) {
			var arr = data.data_device;
			var arr1 = data.data_p_device;
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
						url: "../../workorderApplyDeviceAccount/listWorkorderApplyDeviceAccount",
						type: "POST",
						data: {
							workorder_apply_id: $('#modal8_id').val(),
							page_start:page_start2,
							page_length:page_length
						},
						success: function (data) {
							var arr = data.data_device;
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
//关联设备的编辑
$('#relevance-device').click(function () {
	var selecteddevice = [];
	$("#edit_device1 input").each(function () {
		selecteddevice.push(this.value);
	});
	$.ajax({
		url: "../../workorderApplyDeviceAccount/addWorkorderApplyDeviceAccount",
		type: "POST",
		data: {
			workorder_apply_id: $('#modal8_id').val(),
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
			loadAJAX('#example2');
		},
		error: function () {
		}
	})
});

//回显删除
$('#modal_default').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#del_work').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
});
//授权工单的删除
$('#delWork').click(function () {
	$.ajax({
		url: "../../workorderApply/delWorkorderApply",
		type: "POST",
		data: {
			ids: new Array($('#del_work').val())
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
			loadAJAX('#example2');
		},
		error: function () {
			loadAJAX('#example2');
		},
	})
});
//授权工单的全选删除
$('#delAllWork').click(function () {
	let obj = document.getElementsByName('chk[]');
	let ids = new Array();
	for (let i in obj) {
		if (obj[i].checked)
			ids.push(obj[i].value);
	}
	if (ids.length === 0) {
		$("#modal-hint .modal-title").text('失败');
		$("#modal-hint.modal-body").text('请选择要删除的信息');
		$("#modal-hint").modal();
		loadAJAX('#example2');
		return false;
	}
	$.ajax({
		url: "../../workorderApply/delWorkorderApply",
		type: "POST",
		data: {
			ids: ids
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
			loadAJAX('#example2');
		},
		error: function () {
			$("#modal-danger .modal-title").text('失败');
			$("#modal-danger .modal-body").text('操作失败!');
			$("#modal-danger").modal();
			loadAJAX('#example2');
		}
	})
});


//授权工单的撤回
$('#modal_default').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#submit_work').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
});
$('#withdraw').click(function () {
	$.ajax({
		url: "../../workorderApply/delWorkorderApply",
		type: "POST",
		data: {
			result:0
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
			loadAJAX('#example2');
		},
		error: function () {
			loadAJAX('#example2');
		},
	})
});
//授权工单的提交
$('#modal-withdraw').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#withdraw_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
});
$('#submitWork').click(function () {
	$.ajax({
		url: "../../workorderApply/delWorkorderApply",
		type: "POST",
		data: {
			result:1
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('提交成功!');
				$("#modal-success").modal();
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('提交失败!');
				$("#modal-danger").modal();
			}
			loadAJAX('#example2');
		},
		error: function () {
			loadAJAX('#example2');
		},
	})
});
//授权工单的回显清除样式
$('#newT').click(function () {
	init();
	$('#upload').prop("checked",false);
	$('#download').prop("checked",false);
	$('#filemanage').prop("checked",false);
	$('#up_clipboard').prop("checked",false);
	$('#down_clipboard').prop("checked",false);
	$('#watermark').prop("checked",false);
	$('#description').val('');
	$('#add_device').html('');
	$('#add_device1').html('');
});