function init() {
	//定义locale汉化插件
	let locale = {
		format: "YYYY-MM-DD HH:mm:ss",
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
	let minDate = year + "-" + month + "-" + day +date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	//日期控件初始化
	$('#effect_time').daterangepicker({
		locale: locale,
		autoApply: false,
		timePicker: true, //显示时间
		timePicker24Hour: true, //时间制
		showDropdowns: true, //年月份下拉框
		timePickerSeconds:true,
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
		timePickerSeconds:true,
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
				timePickerSeconds:true,
				singleDatePicker: true,
				startDate: minDate,
				minDate: $('#effect_time').val(),//动态获取截止日期的最小取值范围
			}
		);
	});

	//日期控件初始化
	$('#effect_time1').daterangepicker({
		locale: locale,
		autoApply: false,
		timePicker: true, //显示时间
		timePicker24Hour: true, //时间制
		timePickerSeconds:true,
		showDropdowns: true, //年月份下拉框
		singleDatePicker: true,
		startDate: minDate,
		minDate: minDate
	});
	$('#fail_time1').daterangepicker({
		locale: locale,
		autoApply: false,
		singleDatePicker: true,
		timePicker: true, //显示时间
		timePicker24Hour: true, //时间制
		timePickerSeconds:true,
		startDate: minDate,
		minDate: $('#effect_time1').val(),//动态获取截止日期的最小取值范围
	});
	$('#effect_time1').blur(() => {
		$('#fail_time').daterangepicker(
			{
				locale: locale,
				autoApply: false,
				timePicker: true, //显示时间
				timePicker24Hour: true, //时间制
				timePickerSeconds:true,
				singleDatePicker: true,
				startDate: minDate,
				minDate: $('#effect_time1').val(),//动态获取截止日期的最小取值范围
			}
		);
	});
}
$(document).ready(function() {
	init();
});
//关联设备每次加载多少
const page_length = 500;
//mysql datetime去掉最后的.0
function time_Format(dateTime){
	let i = dateTime.indexOf(".");
 	if (i === -1) {
 		return dateTime;
    }
	return dateTime.substring(0,i);
}
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
			{"data": "type","render":function(data){
				return 	data===0?"访问工单":"命令工单";
			}},
			{"data": "update_time","render":function (data) {
				return time_Format(data);
			}},
			{"data": "result","render":function (data) {
					return  '<div>' +status(data)+ '</div>';
			}},
			{"data": "description","render":function (data) {
					return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 200px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
			}},
			{
				"data": "id", "render": function (data, type, row, meta) {
					return (row.type === 0 ?('<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal-primary1" style="cursor:pointer;vertical-align: bottom;">编辑</a>' +'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-primary8" style="margin-left: 20px;cursor:pointer;">关联设备</a>'):'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-primary2" style="cursor:pointer;">查看命令</a>') +
						'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-withdraw"  style="margin-left: 20px;cursor:pointer;">撤回</a>' +
						'<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1"  data-target="#modal_default8" style="margin-left: 20px;cursor:pointer;">提交</a>' +
						(row.result !== 0?'':'<a  data-toggle="modal"  data-row="'+meta.row+'"  class="newcss2" data-target="#modal_default" style="margin-left: 20px;cursor:pointer">删除</a>')
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
	$('#add_device').html('');
	let selecteddevice = [];
	$('#add_device1 input').each(function () {
		selecteddevice.push(this.value);
	});
	if (selecteddevice.length ===  0){
		$('#warn_info').text("请关联至少一个设备");
		$("#modal-default3").modal();
		return;
	}
	$.ajax({
		url: "../../workorderApply/addWorkorderApply",
		type: "POST",
		data: {
			devices:selecteddevice,
			start:$('#effect_time').val(),
			end:$('#fail_time').val(),
			upload:$('#upload').prop("checked")===true?1:0,
			download:$('#download').prop("checked")===true?1:0,
			filemanage:$('#filemanage').prop("checked")===true?1:0,
			up_clipboard:$('#filemanage').prop("checked")===true?1:0,
			down_clipboard:$('#down_clipboard').prop("checked")===true?1:0,
			watermark:$('#watermark').prop("checked")===true?1:0,
			description:$('#description').val(),
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('添加成功!');
				$("#modal-success").modal();
				$('#modal-primary5').modal('hide');
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('添加失败!');
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
	$('#effect_time1').val(time_Format($('#example2').DataTable().row('#' + i).nodes(i).data()[i].start));
	$('#name').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].name);
	$('#fail_time1').val(time_Format($('#example2').DataTable().row('#' + i).nodes(i).data()[i].end));
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
	url: "../../workorderApply/editWorkorderApply",
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
			$('#modal-primary1').modal('hide');
		} else {
			$("#modal-danger .modal-title").text('失败');
			$("#modal-danger .modal-body").text('编辑失败!');
			$("#modal-danger").modal();
		}
		loadAJAX('#example2');
	}
})
});
let listDeviceAccount = null;
//新建设备的回显
$('#modal-primary5').on('show.bs.modal',function () {
	let page_start7 = 0;
	//设备账号
	$.ajax({
		url: "../../deviceAccount/listDeviceAccountNameIp",
		type: "POST",
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
$('#modal-withdraw').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#withdraw_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#withdraw_status').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].result);
});
$('#withdraw').click(function () {
	if ($('#withdraw_status').val() > 1) {
		$('#warn_info').text("此工单不能撤回");
		$("#modal-default3").modal();
		return;
	}
	$.ajax({
		url: "../../workorderApply/updateResult",
		type: "POST",
		data: {
			id:$('#withdraw_id').val(),
			result:0,
			status:$('#withdraw_status').val()
		},
		success: function (data) {
			if (data.success) {
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('撤回成功!');
				$("#modal-success").modal();
			} else {
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('撤回失败!');
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
$('#modal_default8').on('show.bs.modal', function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#submit_work').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#submit_status').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].result);
});
$('#submitWork').click(function () {
	if ($('#submit_status').val() !== '0') {
		$('#warn_info').text("您已经提交了这个工单");
		$("#modal-default3").modal();
		return;
	}
	$.ajax({
		url: "../../workorderApply/updateResult",
		type: "POST",
		data: {
			id:$('#submit_work').val(),
			result:1,
			status:$('#submit_status').val()
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
//回显关联命令
$('#modal-primary2').on('show.bs.modal',function (event) {
	let button = $(event.relatedTarget);
	let i = button.data('row');
	$('#primary2_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id)
	$.ajax({
		url:"../../workorderApply/listCmd",
		type:"POST",
		data:{
			id:$('#primary2_id').val(),
		},
		success:function (data) {
			$('#cmd').html(data.cmd===""?"暂无命令":data.cmd);
		}
	});
});