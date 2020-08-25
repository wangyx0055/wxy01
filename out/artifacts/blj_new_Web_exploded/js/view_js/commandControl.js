let listAddUser = null;
let listUserGroup = null;
let listDeviceAccount = null;
let listDeviceGroup = null;
let listCmdGroup = null;
const page_length = 200;
$('#modal-primary33').on('show.bs.modal', function (event){
	let page_start5 =0;
	let page_start6 =0;
	//关联显示用户
	$.ajax({
		url:"../../user/listUser",
		type:"POST",
		dataType:"json",
		data:{
			start:page_start5,
			length:page_length,
		},
		success:function (data){
			var data = data.data;
			for (let item of data) {
				$('#add_user').append( '<div><input value="' + item.id + '" type="checkbox"><span>' + item.username + "[" + item.realname + "]" + '</span></div>')
			}
			RelativeMethods('');//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面4
			listAddUser = $('#add_user').html();//保存显示出来的原本的数据
			//慢加载
			$('#add_user').on("scroll",function () {
				let viewH =$(this).height(),//可见高度
					contentH =$(this).get(0).scrollHeight,//内容高度
					scrollTop =$(this).scrollTop();//滚动高度
				if(contentH - viewH - scrollTop <= 60) {
					page_start5 += 500;
					$.ajax({
						url: "../../user/listUser",
						type: "POST",
						data: {
							start:page_start5,
							length:page_length,
						},
						success: function (data) {
							var data = data.data;
							for (let item of data) {
								$('#add_user').append( '<div><input value="' + item.id + '" type="checkbox"><span>' + item.username + "[" + item.realname + "]" + '</span></div>')
							}
							RelativeMethods('');//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面4
							listAddUser = $('#add_user').html();//保存显示出来的原本的数据
						},
					})
				}
			});
		},
		error:function(){}
	})
	//新建显示用户组
	$.ajax({
		url:"../../group/listGroup",
		type:"POST",
		dataType:"json",
		data:{
			type: 0,
			start: page_start6,
			length: page_length,
		},
		success:function (data) {
			var data = data.data;
			for (let item of data) {
				$('#add_group').append('<div><input value="' + item.id + '" type="checkbox"><span>' + item.name + '</span></div>');
			}
			RelativeMethods(1);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			listUserGroup = $('#add_group').html();//保存显示出来的原本的数据
			//慢加载
			$('#add_group').on("scroll", function () {
				let viewH = $(this).height(),//可见高度
					contentH = $(this).get(0).scrollHeight,//内容高度
					scrollTop = $(this).scrollTop();//滚动高度
				if (contentH - viewH - scrollTop <= 60) {
					page_start6 += 500;
					$.ajax({
						url: "../../group/listGroup",
						type: "POST",
						data: {
							"type": 0,
							start: page_start6,
							length: page_length,
						},
						success: function (data) {
							var data = data.data;
							for (let item of data) {
								$('#add_group').append('<div><input value="' + item.id + '" type="checkbox"><span>' + item.name + '</span></div>');
							}
							RelativeMethods(1);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
							listUserGroup = $('#add_group').html();//保存显示出来的原本的数据
						},
					})
				}
			});
		}
	})
});
$('#modal-primary55').on('show.bs.modal', function (event){
	let page_start7 =0;
	let page_start8 =0;
	//关联显示设备账户
	$.ajax({
		url:"../../deviceAccount/listDeviceAccountNameIp",
		type:"POST",
		dataType:"json",
		data:{
			start: page_start7,
			length: page_length
		},
		success:function (data){
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
		error:function(){}
	})
	//关联显示设备组
	$.ajax({
		url:"../../group/listGroup",
		type:"POST",
		dataType:"json",
		data:{
			type: 1,
			start: page_start8,
			length: page_length,
			name: $('#searchIdG').val(),
		},
		success:function (data){
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
		error:function(){}
	})
});
$('#modal-primary44').on('show.bs.modal', function (event){
	//关联显示命令集
	$.ajax({
		url:"../../cmdgroup/listCmdgroup",
		type:"POST",
		dataType:"json",
		data:{
			start:0,
			length:10000,
		},
		success:function (data){
			var data = data.data;
			for (let item of data) {
				$('#add_cmdgroup').append('<div><input value="'+item.id+'" type="checkbox"><span>'+item.name+'</span></div>');
			};
			RelativeMethods(4);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			listCmdGroup = $('#add_cmdgroup').html();//保存显示出来的原本的数据
		},
		error:function(){}
	})
});
$(function(){
//策略列表的显示
	function AutoSearch(){
		let status = null;
		let cot = null;
		if ($('#Distinguish').val()==1){
			if ($('#searchId').val().trim().match("启")){
				status=1;
				cot=10;//随机赋值 不然传到后台会搜全部
			}else if($('#searchId').val().trim().match("禁")){
				status=0;
				cot=10;//随机赋值 不然传到后台会搜全部
			}else if($('#searchId').val().trim().match("拒")){
				status=10;//随机赋值 不然传到后台会搜全部
				cot=2;
			}else if($('#searchId').val().trim().match("断")){
				cot=1;
				status=10;//随机赋值 不然传到后台会搜全部
			}else if($('#searchId').val().trim()==''){
				status='';
				cot='';
			}else{
				status=$('#searchId').val().trim();
				cot=$('#searchId').val().trim();
			}
		}
		if ($('#Distinguish').val()==5){
			if ($('#searchId').val().trim().match("启")){
				status=1;
			}else if($('#searchId').val().trim().match("禁")){
				status=0;
			}else if($('#searchId').val().trim()==''){
				status='';
			}else{
				status=$('#searchId').val().trim();
			}
		}
		if ($('#Distinguish').val()==4){
			if ($('#searchId').val().trim().match("断")){
				cot = 1
			}else if($('#searchId').val().trim().match("拒")){
				cot = 2;
			}else if($('#searchId').val().trim()==''){
				cot='';
			}else{
				cot=$('#searchId').val().trim();
			}
		}


 $('#example2').DataTable({
			'paging'      : true,
			"iDisplayLength": 10,
			'lengthChange': true,
			'dom'			:'t<"bottom"lifp<"clear">>',
			"lengthMenu": [
				[10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
			],
			'ordering'    : false,
			'info'        : true,
			'autoWidth'   : false,
			'searching'   : false,
			"serverSide"  : true,
			'destroy': true,
			"ajax": {
				url:"../../cmdPolicy/listCmdPolicy",
				data:{
					type:$('#Distinguish').val(),
					sname:$('#searchId').val().trim(),
					stat:status,
					cot:cot,
				}
			},
			"columns": [
				{ "data": "id" ,"render": function(data,type,row,meta){
						return '<input type="checkbox" name="chk[]" value='+data+'>';
					}},
				{ "data": "name" },
				{"data": "depart_name","render":function (data,type, row, meta) {
						return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+row.topName+'">'+data+'</div>'
					}},
				{"data": "desc","render":function (data) {
						return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+data+'">'+data+'</div>'
					}},
				{
					"data": "execute_action",
					"render":function(data,type,row,mata){
						if(data==1)
							return '断开连接';
						else if(data==2)
							return '拒绝执行';
						else if(data==3)
							return '动态授权';
						else
							return '允许执行';
					}
				},
				{"data":"status","render":function(data){
						if (data==0){
							return '<span>已禁用</span>';
						}else{
							return '<span>已启用</span>';
						}
					}},
				{ "data": "id", "render": function(data,type,row,meta){
						return '<a data-toggle="modal" data-target="#modal-default777" class="newcss1" data-row="'+meta.row+'" style="cursor:pointer">编辑</a>'+
							'<div class="dropdown btn-group" style="margin-left:20px;vertical-align: unset;" >'+
							'<a type="button" data-row="'+meta.row+'" data-toggle="dropdown" class="newcss1 dropdown-toggle" style="line-height: 1.5px;cursor:pointer">关联</a>'+
							'<a type="button" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="line-height: 1.5px;cursor:pointer">'+
							'<span class="caret" style="margin-bottom:3px;"></span>'+
							'<span class="sr-only">Toggle Dropdown</span>'+
							'</a>'+
							'<ul class="dropdown-menu" role="menu" style="font-size: 12px">'+
							'<li><a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary1">命令</a></li>'+
							' <li><a data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary2">命令集</a></li>'+
							'<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary3">用户</a></li>'+
							'<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary4">用户组</a></li>'+
							'<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary5">设备</a></li>'+
							'<li><a  data-toggle="modal" data-row="'+meta.row+'"  data-target="#modal-primary6">设备组</a></li>'+
							'</ul>'+
							'</div>'+
							'<a data-row="'+meta.row+'" class="newcss1" style="cursor:pointer;margin-left: 20px" onclick="switchcase(this,'+row.id+')">'+(row.status==0?'启用':'禁用')+'</a>'+
							'<a>'+
							'<a data-row="'+meta.row+'" data-toggle="modal" class="newcss2" data-target="#modal-default1" style="cursor:pointer;margin-left: 20px;" >删除</a>'+
							'</a>'
					}}
			],
			 "fnDrawCallback": function() {
			 //提示工具
			 $('#example2 div').tooltip()
		 }
		});
	}
	AutoSearch();
	$('#search').click(function () {
		AutoSearch();
	})
//命令集
	function AutoSearch1(){
		$('#example3').DataTable({
			'paging': true,
			"iDisplayLength": 10,
			'lengthChange': true,
			"lengthMenu": [
				[10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
			],
			'dom': 't<"bottom"lifp><"clear">',
			'searching': false,
			'ordering': false,
			'info': true,
			'autoWidth': false,
			'serverSide': true,
			'destroy': true,
			"ajax": {
				url:"../../cmdgroup/listCmdgroup",
				data:{
					type:$('#Distinguish1').val(),
					sname:$('#searchId1').val().trim(),
				}
			},
			"columns": [
				{ "data": "id" ,"render": function(data,type,row,meta){
						return '<input type="checkbox" name="chk1[]" value='+data+'>';
					}},
				{ "data": "name" },
				{"data": "depart_name","render":function (data,type, row, meta) {
						return '<div style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 150px;" data-html="true" data-placement="right" data-toggle="tooltip" title="'+row.topName1+'">'+data+'</div>'
					}},
				{ "data": "cmd", "render" : function(data, type, row, mata) {
						return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:500px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
						+data
						+ '</div>';
				}},
				{ "data": "id", "render": function(data,type,row,meta){
						return '<a data-row="'+meta.row+'" class="newcss1" data-toggle="modal" data-target="#modal-default99" style="cursor: pointer">编辑</a>'+
						'<a data-row="'+meta.row+'" data-toggle="modal" class="newcss2" data-target="#modal-default11" style="cursor:pointer;margin-left: 20px">删除</a>'
				}}
			],
			"fnDrawCallback": function( settings, json ) {
				$('#example3 div').tooltip();
			}
		})
	}
	AutoSearch1();
	$('#search1').click(function () {
		AutoSearch1();
	})
})
//新建
$('#modal-default').on('show.bs.modal', function (event) {
});

//删除
$('#modal-default1').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var i = button.data('row');
	$('#del_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
});

//编辑
$('#modal-default777').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
	var  i = button.data('row');
	$('#edit_id777').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#edit_name777').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].name);
	$('#edit_desc777').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].desc);
	var valid_long=$('#example2').DataTable().row('#' + i).nodes(i).data()[i].longtime;
	if(valid_long==0){
		$('#reservation1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].valid_datetime_start);
		$('#reservation3').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].valid_datetime_end);
		$("#edit_long777").prop("checked",false);
		$('#reservation1').prop('disabled',false);
		$('#reservation3').prop('disabled',false);
	}else{
		$('#reservation1').val("");
		$('#reservation3').val("");
		$('#reservation1').prop('disabled',true);
		$('#reservation3').prop('disabled',true);
		$("#edit_long777").prop("checked",true);
	}
	$('#edit_execute_action777').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].execute_action);
	$('#alertLevel1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].alert_level);
	let aM = $('#example2').DataTable().row('#' + i).nodes(i).data()[i].alert_methods;
	if (aM.substring(0,1)>0){
		$('#alertMessage1').prop("checked",true)
	}else{$('#alertMessage1').prop("checked",false)}
	if (aM.substring(1,2)>0){
		$('#alertEmail1').prop("checked",true)
	}else{$('#alertEmail1').prop("checked",false)}
	if(aM.substring(2,3)>0){
		$('#alertShort1').prop("checked",true)
	}else{$('#alertShort1').prop("checked",false)}
});

//命
$('#edit_cmdname').focus(function(){
	$("#Vedit_cmdname").text("");
})
$('#edit_cmdname').blur(function(){
	    var reg=/[\u4E00-\u9FFF|@#$%&。￥]+/;
		var edit_cmdname=$("#edit_cmdname").val();
		if(edit_cmdname!=""&&reg.test(edit_cmdname)){
			$("#Vedit_cmdname").text("输入格式不正确");
			return;
		}
});
//关联命令
$('#modal-primary1').on('show.bs.modal',function(event){
	$("#Vedit_cmdname").text("");
	var button = $(event.relatedTarget) // Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary1 .modal-title').text("关联命令["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary1_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#edit_cmdname').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].cmd);
})


$('#relevance-cmd').click(function(){
		var reg=/[\u4E00-\u9FFF|@#$%&。￥]+/;
		var edit_cmdname=$("#edit_cmdname").val();
		if(edit_cmdname!=""&&reg.test(edit_cmdname)){
			$("#Vedit_cmdname").text("输入格式不正确");
			return;
		}
	$.ajax({
		url:"../../cmdPolicy/editCmdPolicyCmd",
		type:"POST",
		data:{
			policy_id:$('#primary1_id').val(),
			command:$("#edit_cmdname").val(),
		},
		success:function(data){
			if(data.success){
				$('#modal-primary1').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){}
	})
})
//关联用户
let ac_edit_user_list = null;
let ac_edit_user1_list = null;
$('#modal-primary3').on('show.bs.modal',function(event){
	let page_start1 =0;
	var button = $(event.relatedTarget) // Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary3 .modal-title').text("关联用户["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary3_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url:"../../cmdPolicyUser/findCmdPolicyUserAndUser",
		type:"POST",
		data:{
			policy_id:$('#primary3_id').val(),
			page_start:page_start1,
			page_length:page_length
		},
		success:function(data){
			var arr = data.data_users;
			var arr1 = data.data_p_users;
			//show
			$('#edit_user').html('');
			$('#edit_user1').html('');
			for (let item of arr) {
				$('#edit_user').append( '<div><input value="' + item.user_id + '" type="checkbox"><span>' + item.username + "[" + item.realname + "]" + '</span></div>')
			}
			for (let item1 of arr1) {
				$('#edit_user1').append('<div><input value="' + item1.user_id + '" type="checkbox"><span>' + item1.username + "[" + item1.realname + "]" + '</span></div>')
			}
			RelativeMethods(5);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			ac_edit_user_list = $('#edit_user').html();
			ac_edit_user1_list = $('#edit_user1').html();
			//慢加载
			$('#edit_user').on("scroll",function () {
				let viewH =$(this).height(),//可见高度
					contentH =$(this).get(0).scrollHeight,//内容高度
					scrollTop =$(this).scrollTop();//滚动高度
				if(contentH - viewH - scrollTop <= 60) {
					page_start1 += 500;
					$.ajax({
						url:"../../cmdPolicyUser/findCmdPolicyUserAndUser",
						type: "POST",
						data: {
							policy_id:$('#primary3_id').val(),
							page_start:page_start1,
							page_length:page_length
						},
						success: function (data) {
							var arr = data.data_users;
							for (let item of arr) {
								$('#edit_user').append( '<div><input value="' + item.user_id + '" type="checkbox"><span>' + item.username + "[" + item.realname + "]" + '</span></div>')
							}
							RelativeMethods(5);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
							ac_edit_user_list = $('#edit_user').html();
						},
					})
				}
			});
		},
		error:function(){},
	})
})

//deviceAccount
let ac_edit_device_list = null;
let ac_edit_device1_list =null;
$('#modal-primary5').on('show.bs.modal', function (event) {
	let page_start2 =0;
	var button = $(event.relatedTarget);// Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary5 .modal-title').text("关联设备["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary5_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url:"../../cmdPolicyDevice/findCmdPolicyDeviceAndUser",
		type:"POST",
		data:{
			policy_id: $('#primary5_id').val(),
			page_start:page_start2,
			page_length:page_length
		},
		success:function(data) {
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
						url:"../../cmdPolicyDevice/findCmdPolicyDeviceAndUser",
						type: "POST",
						data: {
							policy_id: $('#primary5_id').val(),
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
		error:function(){},
	})
})

//cmdgrooup
let ac_edit_cmdgroup_list = null;
let ac_edit_cmdgroup1_list = null;
$('#modal-primary2').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget); // Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary2 .modal-title').text("关联命令集["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary2_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url:"../../cmdPolicyCmd/findCmdPolicyCmdAndUser",
		type:"POST",
		data:{
			policy_id:$('#primary2_id').val(),
		},
		success:function(data){
			var arr = data.data_cmd;
			var arr1 = data.data_p_cmd;
			//show
			$('#edit_cmdgroup').html('');
			$('#edit_cmdgroup1').html('');
			let len = arr.length;
			for(let i=0; i<len; i++){
				$('#edit_cmdgroup').html($('#edit_cmdgroup').html()+'<div><input value="'+arr[i].cmdgroup_id+'" type="checkbox"><span>'+arr[i].name+'</span></div>')
			}
			let len1 = arr1.length;
			for(let i=0; i<len1; i++){
				$('#edit_cmdgroup1').html($('#edit_cmdgroup1').html()+'<div><input value="'+arr1[i].cmdgroup_id+'" type="checkbox"><span>'+arr1[i].name+'</span></div>')
			}
			RelativeMethods(9);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			ac_edit_cmdgroup_list=$('#edit_cmdgroup').html();
			ac_edit_cmdgroup1_list=$('#edit_cmdgroup1').html();
		},
		error:function(){},
	})
});

//user-group
let ac_edit_usergroup_list = null;
let ac_edit_usergroup1_list = null;
$('#modal-primary4').on('show.bs.modal', function (event) {
	let page_start =0;
	var button = $(event.relatedTarget) // Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary4 .modal-title').text("关联用户组["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary4_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url:"../../cmdPolicyGroup/findCmdPolicyUserGroupAndUser",
		type:"POST",
		data:{
			policy_id:$('#primary4_id').val(),
			page_start:page_start,
			page_length:page_length
		},
		success:function(data){
			var arr = data.data_users;
			var arr1 = data.data_p_users;
			//show
			$('#edit_usergroup').html('');
			$('#edit_usergroup1').html('');
			for (let item of arr) {
				$('#edit_usergroup').append('<div><input value="' + item.group_id + '" type="checkbox"><span>' + item.group_name  + '</span></div>')
			}
			//已选择
			for (let item1 of arr1) {
				$('#edit_usergroup1').append('<div><input value="' + item1.group_id + '" type="checkbox"><span>' + item1.group_name + '</span></div>')
			}
			RelativeMethods(6);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
			ac_edit_usergroup_list = $('#edit_usergroup').html();
			ac_edit_usergroup1_list = $('#edit_usergroup1').html();
			//慢加载
			$('#edit_usergroup').on("scroll",function () {
				let viewH =$(this).height(),//可见高度
					contentH =$(this).get(0).scrollHeight,//内容高度
					scrollTop =$(this).scrollTop();//滚动高度
				if(contentH - viewH - scrollTop <= 60) {
					page_start += 500;
					$.ajax({
						url:"../../cmdPolicyGroup/findCmdPolicyUserGroupAndUser",
						type: "POST",
						data: {
							policy_id:$('#primary4_id').val(),
							page_start:page_start,
							page_length:page_length
						},
						success: function (data) {
							var arr = data.data_users;
							for (let item of arr) {
								$('#edit_usergroup').append('<div><input value="' + item.group_id + '" type="checkbox"><span>' + item.group_name + '</span></div>')
							}
							RelativeMethods(6);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
							ac_edit_usergroup_list = $('#edit_usergroup').html();
						},
					})
				}
			});
		},
	})
})

//device-group
let ac_edit_devicegroup_list = null;
let ac_edit_devicegroup1_list = null;
$('#modal-primary6').on('show.bs.modal', function (event) {
	let page_start3 =0;
	var button = $(event.relatedTarget);// Button that triggered the modal
	var i = button.data('row');
	$('#modal-primary6 .modal-title').text("关联设备组["+$('#example2').DataTable().row('#' + i).nodes(i).data()[i].name+"]");
	$('#primary6_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
	$.ajax({
		url:"../../cmdPolicyGroup/findCmdPolicyDeviceGroupAndUser",
		type:"POST",
		data:{
			policy_id:$('#primary6_id').val(),
			page_start:page_start3,
			page_length:page_length
		},
		success:function(data){
			var arr = data.data_dgroups;
			var arr1 = data.data_p_dgroups;
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
						url:"../../cmdPolicyGroup/findCmdPolicyDeviceGroupAndUser",
						type: "POST",
						data: {
							policy_id:$('#primary6_id').val(),
							page_start:page_start3,
							page_length:page_length
						},
						success: function (data) {
							var arr = data.data_dgroups;
							for(let item of arr){
								$('#edit_devicegroup').append('<div><input value="' + item.dgroup_id + '" type="checkbox"><span>' + item.dgroup_name + '</span></div>')
							}
							RelativeMethods(8);//封装的穿梭框函数代码在/bower_components/dist/js/common/relative.js里面
							ac_edit_devicegroup_list = $('#edit_devicegroup').html();
						},
					})
				}
			})
		},
		error:function(){},
	})
})
//编辑关联用户
$("#relevance-user").click(function(){
	var selecteduser = [];
	$("#edit_user1 input").each(function(){
		selecteduser.push(this.value);
	})
	$.ajax({
		url:"../../cmdPolicyUser/editCmdPolicyUser",
		type:"POST",
		data:{
			policy_id:$('#primary3_id').val(),
			user:selecteduser,
		},
		success:function(data){
			if(data.success){
				$('#modal-primary3').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){}
	})
})
//编辑用户组
$("#relevance-usergroup").click(function(){
	var selectedusergroup = [];
	$("#edit_usergroup1 input").each(function(){
		selectedusergroup.push(this.value);
	})
	$.ajax({
		url:"../../cmdPolicyGroup/editCmdPolicyUserGroup",
		type:"POST",
		data:{
			policy_id:$('#primary4_id').val(),
			usergroup:selectedusergroup,
		},
		success:function(data){
			if(data.success){
				$('#modal-primary4').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){}
	})
})
//编辑设备组
$("#relevance-devicegroup").click(function(){
	var selecteddevicegroup = [];
	$("#edit_devicegroup1 input").each(function(){
		selecteddevicegroup.push(this.value);
	})
	$.ajax({
		url:"../../cmdPolicyGroup/editCmdPolicyDeviceGroup",
		type:"POST",
		data:{
			policy_id: $('#primary6_id').val(),
			devicegroup:selecteddevicegroup,
		},
		success:function(data){
			if(data.success){
				$("#modal-primary6").modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){}
	})
})
//编辑命令集
$("#relevance-cmdgroup").click(function(){
	var selectedcmdgroup = [];
	$("#edit_cmdgroup1 input").each(function(){
		selectedcmdgroup.push(this.value);
	})
	$.ajax({
		url:"../../cmdPolicyCmd/editCmdPolicyCmd",
		type:"POST",
		data:{
			policy_id: $('#primary2_id').val(),
			cmdgroup:selectedcmdgroup,
		},
		success:function(data){
			if(data.success){
				$('#modal-primary2').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){}
	})
})
//编辑设备
$("#relevance-device").click(function(){
	
	var selecteddevice = [];
	$("#edit_device1 input").each(function(){
		selecteddevice.push(this.value);
	})
	$.ajax({
		url:"../../cmdPolicyDevice/editCmdPolicyDevice",
		type:"POST",
		data:{
			policy_id: $('#primary5_id').val(),
			device:selecteddevice,
		},
		success:function(data){
			if(data.success){	
				$('#modal-primary5').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){
		}
	})
})
$('#delAllButton1').click(function(){
	var obj = document.getElementsByName('chk[]');
	var ids= new Array();
	for (i in obj){
		if(obj[i].checked)
			ids.push(obj[i].value);
	}
	if(ids.length==0){
		$("#modal-hint .modal-title").text('失败');
		$("#modal-hint .modal-body").text('请选择要删除的信息');
		$("#modal-hint").modal();
		$('#example2').DataTable().ajax.reload();
		return false;
	}
	$.ajax({
		url:"../../cmdPolicy/delCmdPolicy",
		type:"POST",
		data:{
			ids:ids
		},
		success:function(data){
			if(data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');

			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){
			$("#modal-danger .modal-title").text('失败');
			$("#modal-danger .modal-body").text('操作失败!');
			$("#modal-danger").modal();
			loadAJAX('#example2');
		}
	})
});
//删除命令策略
$('#table-del').click(function(){
	$.ajax({
		url:"../../cmdPolicy/delCmdPolicy",
		type:"POST",
		data:{
			ids: new Array($('#del_id').val()),
		},
		success:function(data){
			if(data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
				loadAJAX('#example2');
			}
		},
		error:function(){

		}
	})
});

//新建命令
$('#add_cmd2').focus(function(){
	$("#Vadd_cmd2").text("");
})
$('#add_cmd2').blur(function(){
	    var reg=/[\u4E00-\u9FFF|@#$%&。￥]+/;
		var add_cmd2=$("#add_cmd2").val();
		if(add_cmd2!=""&&reg.test(add_cmd2)){
			$("#Vadd_cmd2").text("输入格式不正确");
		}
})
//新建策略
$('.add-cmdpolicy').click(function(){
	if($('#add_long2').prop('checked')){
		var add_long=1
		$('#reservation').val("")
		$('#reservation2').val("")
	}else{
		var add_long=0
	}
	//告警方式
	let [methods,a,b,c] = [0,0,0,0]
	if ($('#alertMessage').prop("checked") === true){
		a=100
	}
	if ($('#alertEmail').prop("checked") === true){
		b = 10
	}
	if ($('#alertShort').prop("checked") === true){
		c = 1
	}
	if ((methods = a+b+c) === 0){
		methods='000'
	}else{
		methods =(a+b+c).toString()
	}
	//user
	var selecteduser = [];
	$('#add_user1 input').each(function(){
		selecteduser.push(this.value);
	})
	//usergroup
	var selectedusergroup = [];
	$('#add_group1 input').each(function(){
		selectedusergroup.push(this.value)
	})
	//device
	var selecteddevice = [];
	$('#add_device1 input').each(function(){
		selecteddevice.push(this.value);
	})
	//devicegroup
	var selecteddevicegroup = [];
	$('#add_devicegroup1 input').each(function(){
		selecteddevicegroup.push(this.value)
	})
	//cmdgroup
	var selectedcmdgroup = [];
	$('#add_cmdgroup1 input').each(function(){
		selectedcmdgroup.push(this.value);
	})
	/*var reg=/[\u4E00-\u9FFF]+/;
	var add_cmd=$("#add_cmd2").val();
	if(add_cmd!=""&&reg.test(add_cmd)){
		$("#Vadd_cmd2").text("输入格式不正确");
		return;
	}*/
	$.ajax({
		url:"../../cmdPolicy/addCmdPolicy",
		type:"POST",
		data:{
			name:$('#add_name2').val(),
			execute_action:$('#add_execute_action2').val(),
			valid_datetime_start:$('#reservation').val(),
			valid_datetime_end: $('#reservation').val(),
			longtime:add_long,
			desc:$('#add_desc2').val(),
			user:selecteduser,
			usergroup:selectedusergroup,
			device:selecteddevice,
			devicegroup:selecteddevicegroup,
			cmdgroup:selectedcmdgroup,
			cmd:$('#add_cmd2').val(),
			alert_level:$('#alertLevel').val(),
			alert_methods:methods,
		},
		success:function(data){
			if(data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('新建成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				if (data.name=="" ){
					$("#modal-danger .modal-title").text('失败');
					$("#modal-danger .modal-body").text('新建失败!');
					$("#modal-danger").modal();
					loadAJAX('#example2');
				}else{
					$("#Vname").text(data.name);
				}

			}
		},
		error:function(){}
	})
});
//编辑命令控制策略
$('#editButton777').off().click(function(){
	checkeditname1();
	if ($('#Ename').text()!="" || $('#Veditdesc').text()!="" || $('#edit_name777').val()==""){
		if ( $('#edit_name777').val()==""){
			$('#Ename').text("请输入策略名称")
		}else if (!regexp.name.test($('#edit_name777').val())){
			$('#Ename').text("输入格式不正确");
		}
		return;
	}
	//告警方式
	let [methods1,a,b,c] = [0,0,0,0]
	if ($('#alertMessage1').prop("checked") === true){
		a=100
	}
	if ($('#alertEmail1').prop("checked") === true){
		b = 10
	}
	if ($('#alertShort1').prop("checked") === true){
		c = 1
	}
	if ((methods1 = a+b+c) === 0){
		methods1='000'
	}else{
		methods1 =(a+b+c).toString()
	}
	let add_long=1;
	//永久有效
	if($('#edit_long777').prop('checked')){
		$('#reservation1').val("");
		$('#reservation3').val("");
	}else{
		add_long=0;
	}
	$.ajax({
		url:"../../cmdPolicy/editCmdPolicy",
		type:"POST",
		data:{
			id:$('#edit_id777').val(),
			name:$('#edit_name777').val(),
			execute_action:$('#edit_execute_action777').val(),
			valid_datetime_start:$('#reservation1').val(),
			valid_datetime_end:$('#reservation3').val(),
			longtime:add_long,
			desc:$('#edit_desc777').val(),
			alert_level:$('#alertLevel1').val(),
			alert_methods:methods1
		},
		success:function(data){
			if(data.success){
				$('#modal-default777').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example2');
			}
			else{
				if (data.name=="" ){
					$("#modal-danger .modal-title").text('失败');
					$("#modal-danger .modal-body").text('编辑失败!');
					$("#modal-danger").modal();
					loadAJAX('#example2');
				}else{
					$("#Ename").text(data.name);
				}
			}
		},
		error:function(){}
	})
});


//命令集显示
$('#modal-default99').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
	var  i = button.data('row');
	$('#edit_id1').val($('#example3').DataTable().row('#' + i).nodes(i).data()[i].id);
	$('#edit_name1').val($('#example3').DataTable().row('#' + i).nodes(i).data()[i].name);
	$('#edit_cmd1').val($('#example3').DataTable().row('#' + i).nodes(i).data()[i].cmd);
	$('#Veditname').text("");
	$('#Vedit_cmd1').text("");
});
//新建
$('#modal-default3').on('show.bs.modal', function (event) {
});
//删除
$('#modal-default11').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget)
	var i = button.data('row');
	$('#del_id11').val($('#example3').DataTable().row('#' + i).nodes(i).data()[i].id);
});
$('#delAllButton2').click(function(){
	var obj = document.getElementsByName('chk1[]');
	var ids= new Array();
	for (i in obj){
		if(obj[i].checked)
			ids.push(obj[i].value);
	}
	if(ids.length==0){
		$("#modal-hint.modal-title").text('失败');
		$("#modal-hint.modal-body").text('请选择要删除的信息');
		$("#modal-hint").modal();
		$('#example3').DataTable().ajax.reload();
		return false;
	}
	$.ajax({
		url:"../../cmdgroup/delCmdgroup",
		type:"POST",
		data:{
			ids:ids
		},
		success:function(data){
			if(data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
				$('#example3').DataTable().ajax.reload();

			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
				loadAJAX('#example3');
			}
		},
		error:function(){
			$("#modal-danger .modal-title").text('失败');
			$("#modal-danger .modal-body").text('操作失败!');
			$("#modal-danger").modal();
			loadAJAX('#example3');
		}
	})
});
$('#delButton').click(function(){
	$.ajax({
		url:"../../cmdgroup/delCmdgroup",
		type:"POST",
		data:{
			ids: new Array($('#del_id11').val()),
		},
		success:function(data){
			if(data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('操作成功!');
				$("#modal-success").modal();
				loadAJAX('#example3');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('操作失败!');
				$("#modal-danger").modal();
				loadAJAX('#example3');
			}
		},
		error:function(){

		}
	})
});
$('#editButton1').click(function(){
		if ($('#edit_name1').val()==""){
			$('#Veditname').text('请输入命令集名称');
			return;
		}else if (!regexp.name.test($('#edit_name1').val())){
			$('#Veditname').text("输入格式不正确");
			return;
		}else{
			checkeditname();
		}
		if ($('#edit_cmd1').val()==""){
			$('#Vedit_cmd1').text('请输入命令/参数');
			return;
		}else if (regexp.length.test($('#edit_cmd1').val())){
			$('#Vedit_cmd1').text("输入格式不正确");
			return;
		}
	if ($('#Veditname').text()!="" || $('#Vedit_cmd1').text()!=""){
		return;
	}
	$.ajax({
		url:"../../cmdgroup/editCmdgroup",
		type:"POST",
		data:{
			id:$('#edit_id1').val(),
			name:$('#edit_name1').val(),
			cmd:$('#edit_cmd1').val(),
		},
		success:function(data){
			if(data.success){
				$('#modal-default99').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				loadAJAX('#example3');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败!');
				$("#modal-danger").modal();
			
			}
		},
		error:function(){

		}
	})
});
$('#addButton1').click(function(){
	if ($('#add_name').val()==""){
		$('#Vaddname').text("请输入命令集名称");
		return;
	}else if (!regexp.name.test($('#add_name').val())){
		$('#Vaddname').text("输入格式不正确");
		return;
	}else{
		checkname();
	}
	if ($('#add_cmd').val()==""){
		$('#Vaddcmd').text("请输入命令/参数");
		return;
	}else if (regexp.length.test($('#add_cmd').val())){
		$('#Vaddcmd').text("输入格式不正确");
		return;
	}
	if ($('#Vaddname').text()!="" || $('#Vaddcmd').text()!=""){
		return;
	}
	$.ajax({
		url:"../../cmdgroup/addCmdgroup",
		type:"POST",
		data:{
			/*  id:$('#add_id').val(),*/
			name:$('#add_name').val(),
			cmd:$('#add_cmd').val(),
		},
		success:function(data){
			if(data.success){
				$('#modal-default3').modal('hide');
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('新建成功!');
				$("#modal-success").modal();
				loadAJAX('#example3');
			}
			else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('新建失败!');
				$("#modal-danger").modal();
				loadAJAX('#example3');
			}
		},
		error:function(){

		}
	})
});
//导出
$('#export').click(function(){
	$.ajax({
		url:"../../export/checkPwd",
		type:"POST",
		data:{
			password: $('#queryPsw').val(),
		},
		success:function(data){
			if(data.success){
				window.open("../../export/exprotAccessPolicy");
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('导出成功!');
				$("#modal-success").modal();
				$('#modal-default4').modal('hide');
				loadAJAX('#example2');
			}
			else{
				if (data.msg==""){
					$("#modal-danger .modal-title").text('失败');
					$("#modal-danger .modal-body").text('导出失败!');
					$("#modal-danger").modal();
				}else {
					$("#PswRep").text(data.msg)
				}
				loadAJAX('#example2');
			}
		},
		error:function(){

		}
	})
})
$('#export1').click(function(){
	$.ajax({
		url:"../../export/checkPwd",
		type:"POST",
		data:{
			password: $('#queryPsw1').val(),
		},
		success:function(data){
			if(data.success){
				window.open("../../export/exprotAccessPolicy");
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('导出成功!');
				$("#modal-success").modal();
				$('#modal-default44').modal('hide');
				loadAJAX('#example3');
			}
			else{
				if (data.msg==""){
					$("#modal-danger .modal-title").text('失败');
					$("#modal-danger .modal-body").text('导出失败!');
					$("#modal-danger").modal();
				}else {
					$("#PswRep1").text(data.msg)
				}
				loadAJAX('#example3');
			}
		},
		error:function(){

		}
	})
})
//默认置空时间
$('#reservation').val("");
$('#reservation2').val("");
$('#reservation').prop('disabled',true);
$('#reservation2').prop('disabled',true);
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
//初始化显示当前时间
let date = new Date();
let day = date.getDate();
let month = date.getMonth()+1;
let year = date.getFullYear();
let minDate = year+"-"+month+"-"+day;
//动态获取截止日期的最小取值范围
function showEndTime(){
	$('#reservation2').daterangepicker(
		{
			'locale': locale,
			showDropdowns:true,
			autoApply:false,
			singleDatePicker:true,
			opens:"right",
			startDate:minDate,
			minDate: $('#reservation').val(),//动态获取截止日期的最小取值范围
		}
	);
}
function showEndTime1(){
	$('#reservation3').daterangepicker(
		{
			'locale': locale,
			showDropdowns:true,
			autoApply:false,
			singleDatePicker:true,
			opens:"right",
			startDate:minDate,
			minDate: $('#reservation1').val(),//动态获取截止日期的最小取值范围
		}
	);
}
//每次点击永久有效就判断 如果为空就显示时间
$('#add_long2').click(()=>{
	if ($('#add_long2').prop('checked')==true){
		$('#reservation').val("");
		$('#reservation2').val("");
		$('#reservation').prop('disabled',true);
		$('#reservation2').prop('disabled',true);
	}else{
		$('#reservation').prop('disabled',false);
		$('#reservation2').prop('disabled',false);
		$('#reservation').daterangepicker(
			{
				'locale': locale,
				showDropdowns:true,
				autoApply:false,
				singleDatePicker:true,
				opens:"left",
				startDate:minDate,
				minDate:minDate,
			}
		);
		$('#reservation2').daterangepicker(
			{
				'locale': locale,
				showDropdowns:true,
				autoApply:false,
				singleDatePicker:true,
				opens:"right",
				startDate:minDate,
				minDate: minDate,
			}
		);
	}
})
$('#edit_long777').click(()=>{
	if ($('#edit_long777').prop('checked')==true){
		$('#reservation1').val("");
		$('#reservation3').val("");
		$('#reservation1').prop('disabled',true);
		$('#reservation3').prop('disabled',true);
	}else{
		$('#reservation1').prop('disabled',false);
		$('#reservation3').prop('disabled',false);
		$('#reservation1').daterangepicker(
			{
				'locale': locale,
				showDropdowns:true,
				autoApply:false,
				singleDatePicker:true,
				opens:"left",
				startDate:minDate,
				minDate:minDate,
			}
		);
		$('#reservation3').daterangepicker(
			{
				'locale': locale,
				showDropdowns:true,
				autoApply:false,
				singleDatePicker:true,
				opens:"right",
				startDate:minDate,
				minDate: minDate,
			}
		);
	}
})
//每次选择改变起始日期就重新判断结束日期的可选范围
$('#reservation').blur(()=>{
	showEndTime();
})
$('#reservation1').blur(()=>{
	showEndTime1();
})

function change(v){
	if(v=='0'){
		$('.black').css('display','block');
		$('.white').css('display','none');
	}
	if(v=='1'){
		$('.white').css('display','block');
		$('.black').css('display','none');
	}
}
function editchange(v){
	if(v=='0'){
		$('.edit_black777').css('display','block');
		$('.edit_white777').css('display','none');
	}
	if(v=='1'){
		$('.edit_white777').css('display','block');
		$('.edit_black777').css('display','none');
	}
}

function editStatus(status,id) {
	$.ajax({
		url:"../../cmdPolicy/editStatus",
		type:"POST",
		data:{
			id: id,
			status: status,
		},
		success:function(){
			loadAJAX('#example2');
		}
	})
}

function switchcase(o,id){
	if(o.innerText === "启用"){
		o.innerText="禁用";
		editStatus(1,id);
	}else{
		o.innerText="启用";
		editStatus(0,id);
	}
}

$("#selectable").selectable({
	 filter: ".blue-background",
	 stop:function(event,ul){
		 if($(".ui-selected").hasClass("gray-background")){
				var a = document.getElementsByClassName("ui-selected");
				var g = $('.ui-selected.gray-background');
				var b = a.length-g.length;
				if(g.length==a.length||g.length<b){
					$(".ui-selected").removeClass("gray-background");
				}else{
					$(".ui-selected").addClass("gray-background");
				}
			 }else{
				 $(".ui-selected").addClass("gray-background");
			 }
	 }
});
$("#selectable1").selectable({
	 filter: ".blue-background",
	 stop:function(event,ul){
		 if($(".ui-selected").hasClass("gray-background")){
				var a = document.getElementsByClassName("ui-selected");
				var g = $('.ui-selected.gray-background');
				var b = a.length-g.length;
				if(g.length==a.length||g.length<b){
					$(".ui-selected").removeClass("gray-background");
				}else{
					$(".ui-selected").addClass("gray-background");
				}
			 }else{
				 $(".ui-selected").addClass("gray-background");
			 }
	 }
});

//判断
//正则表达式数据
var regexp = {
	name:/^(\w|[\u4e00-\u9fa5]|-|_){1,64}$/,
	length:/[\u4E00-\u9FFF|@#$%&。￥]+/,
	length_des:/^\r|\n|\w{0,64}$/,
};
//失去焦点，移出输入框
$('#add_name').blur(function(){
	var name = $('#add_name').val();
	if(name==""){
		$('#Vaddname').text("请输入命令集名称");
	}  //判断是否符合规则
	else  if (!regexp.name.test(name)){
		$('#Vaddname').text("输入格式不正确");
	}else{
		checkname();
	}
});
// 获取焦点，重新输入
$('#add_name').focus(function(){
	if ($('#Vaddname').text()!==""){
		$('#Vaddname').text('');
	}
})
$('#add_cmd').blur(function(){
	let name = $('#add_cmd').val();
	if(name==""){
		$('#Vaddcmd').text("请输入命令/参数")
	}
	//判断是否符合规则
	else if (regexp.length.test(name)){
		$('#Vaddcmd').text("输入格式不正确");
	}else{
		$('#Vaddcmd').text("");
	}
});
// 获取焦点，重新输入
$('#add_cmd').focus(function(){
	if ($('#Vaddcmd').text()!==""){
		$('#Vaddcmd').text('');
	}
});

//失去焦点，移出输入框
$('#edit_name1').blur(function(){
	var name = $('#edit_name1').val();
	//判断是否符合规则
	if(name==""){
		$('#Veditname').text("请输入命令集名称");
	}else if (!regexp.name.test(name)){
		$('#Veditname').text("输入格式不正确");
	}else{
		checkeditname();
	}
});
// 获取焦点，重新输入
$('#edit_name1').focus(function(){
		$('#Veditname').text('');
});
//失去焦点，移出输入框
$('#edit_cmd1').blur(function(){
	var name = $('#edit_cmd1').val();
	//判断是否符合规则
	if(name==""){
		$('#Vedit_cmd1').text("请输入命令/参数");
	}else if (regexp.length.test(name)){
		$('#Vedit_cmd1').text("输入格式不正确");
	}
});
// 获取焦点，重新输入
$('#edit_cmd1').focus(function(){
		$('#Vedit_cmd1').text('');
});

// 检查重名 命令集
function checkname(){
	$.ajax({
		url:"../../cmdgroup/checkname",
		type:"POST",
		data:{
			name:$('#add_name').val(),
		},
		success:function(data){
			if(!data.success){
				$('#Vaddname').text('命令集名称已存在');
			}
		}
	})
}
// 检查重名 命令集 编辑
function checkeditname(){
	$.ajax({
		url:"../../cmdgroup/checkname",
		type:"POST",
		data:{
			id:$('#edit_id1').val(),
			name:$('#edit_name1').val(),
		},
		success:function(data){
			if(!data.success){
				$('#Veditname').text('命令名称已存在');
			}
		}
	})
}
//失去焦点，移出输入框
$('#add_name2').blur(function(){
	var name = $('#add_name2').val();
	//判断是否符合规则
	if(name==""){
		$('#Vname').text("请输入策略名称");
	}else if (!regexp.name.test(name)){
		$('#Vname').text("输入格式不正确");
	}else{
		checkname1();
	}
});
// 获取焦点，重新输入
$('#add_name2').focus(function(){
	if ($('#Vname').text()!==""){
		$('#Vname').text('');
	}
});

// 检查重名
function checkname1(){
	$.ajax({
		url:"../../cmdPolicy/checkname",
		type:"POST",
		data:{
			name:$('#add_name2').val(),
		},
		success:function(data){
			if(!data.success){
				$('#Vname').text('策略名称已存在');
				return;
			}
		}
	})
}
//失去焦点，移出输入框
$('#edit_name777').blur(function(){
	var name = $('#edit_name777').val();
	//判断是否符合规则
	if(name==""){
		$('#Ename').text("请输入策略名称");
	}else if (!regexp.name.test(name)){
		$('#Ename').text("输入格式不正确");
	}else{
		checkeditname1();
	}
});
// 获取焦点，重新输入
$('#edit_name777').focus(function(){
	if ($('#Ename').text()!==""){
		$('#Ename').text('');
	}
})
// 检查重名
function checkeditname1(){
	$.ajax({
		url:"../../cmdPolicy/checkname",
		type:"POST",
		data:{
			id:$('#edit_id777').val(),
			name:$('#edit_name777').val(),
		},
		success:function(data){
			if(!data.success){
				$('#Ename').text('策略名称已存在');
			}
		}
	})
}
$('#add_desc2').blur(function(){

	var name = $('#add_desc2').val();
	//判断是否符合规则
	if (!regexp.length_des.test(name)){
		$('#Vadddesc').text("超过限制长度")
	}
});
// 获取焦点，重新输入
$('#add_desc2').focus(function(){
	if ($('#Vadddesc').text()!==""){
		$('#Vadddesc').text('');
	}
})

$('#edit_desc777').blur(function(){

	var name = $('#edit_desc777').val();
	//判断是否符合规则
	if (!regexp.length_des.test(name)){
		$('#Veditdesc').text("超过限制长度")
	}
});
// 获取焦点，重新输入
$('#edit_desc777').focus(function(){
	//将提示信息置空
	$('#Veditdesc').text('');
})
//清楚命令集新建的样式
$('#clearT').click(function () {
	$('#add_name').val('');
	$('#add_cmd').val('');
	$('#Vaddname').text('');
	$('#Vaddcmd').text('');
})
//清楚命令策略新建的样式
$('#clearN').click(function () {
	$('#Vadddesc').text('');
	$('#add_desc2').val('');
	$('#add_name2').val('');
	$('#Vname').text('');
	$('#alertLevel').val('1');
	$('#alertMessage').prop("checked",true);
	$('#alertEmail').prop("checked",false);
	$('#alertShort').prop("checked",false);
	$('#reservation').prop('disabled', true);
	$('#reservation2').prop('disabled', true);
	$('#reservation').val('');
	$('#reservation2').val('');
	$('#add_long2').prop("checked",true);
	$('#add_cmd2').val('');
	$('#Vadd_cmd2').text('');
	$('#add_cmdgroup').html('');
	$('#add_cmdgroup1').html('');
	$('#add_user1').html('');
	$('#add_user').html('');
	$('#add_group').html('');
	$('#add_group1').html('');
	$('#add_devicegroup').html('');
	$('#add_devicegroup1').html('');
	$('#add_device').html('');
	$('#add_device1').html('');
});
function checkNull(){
	checkname1()
	var v_b1 = true;
	var v_b2 = true;
	var reg=/^(\w|[\u4e00-\u9fa5]|-){1,64}$/;
	if ($("#add_name2").val()==""){
		$('#Vname').text('请输入策略名称');
		v_b1 = false;
	}else if(!reg.test($("#add_name2").val())){
		$('#Vname').text("输入格式不正确");
		v_b1 = false;
	}
	if ($('#Vname').text()!="" || $('#Vadddesc').text()!=""){
		v_b2 = false;
	}
	if (v_b1==true && v_b2 == true){
		$('#next').attr("data-target","#modal-primary33");
		$('#next').attr("data-dismiss","modal");
	}else {
		$('#next').removeAttr("data-target");
		$('#next').removeAttr("data-dismiss");
	}
}