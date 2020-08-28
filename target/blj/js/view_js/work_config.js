//开启关闭按钮
document.getElementById("innerbtn").onclick = function() {
	var btn = document.getElementById("inner");
	if (btn.className === "inner-on") {
		btn.style.left = 27 + "px";
		btn.className = "inner-off";
		this.style.backgroundColor="#1494F7";
	}else{
		btn.style.left = 2+"px";
		btn.className = "inner-on";
		this.style.backgroundColor="#8080808c";
	}
};
//回显页面数据
function listAll(){
	$.ajax({
		url:"../../configWorkorder/listConfigWorkorder",
		type:"POST",
		success:function (data) {
			data.data.range
			$('#range_page').text(data.data.range===0?"本部门":(data.data.range===1?"本部门及以下部门":"全部"));
			$('#fail_time').text(data.data.dead_hours);
			$('#level_page').text(data.data.level);
			$('#fail_strategy').text(data.data.outdate_action === 1?"允许":"拒绝");
			$('#page_mode').text(data.data.mode===1?"会议审批":"多人审批");
			$('#page_author_type').text(data.data.endaudit===1?"启用":"禁用");
		}
	})
}
listAll();
//回显基本配置
$('#basic_mode1').click(function(){
	$.ajax({
		url:"../../configWorkorder/listConfigWorkorder",
		type:"POST",
		success:function (data) {
			document.getElementsByName("mode_range")[data.data.range].checked="checked";
			$('#Expired').val(data.data.dead_hours);
			$('#outdate_action').val(data.data.outdate_action);
		}
	})
});
//正则判断
$('#Expired').blur(function () {
	if ($('#Expired').val()<73 && $('#Expired').val()>=0){
		$("#Vtime").text('');
	}else{
		$("#Vtime").text("请填写正确的时间");
	}
});
$('#Expired').focus(function () {
	$("#Vtime").text('');
});
//编辑基本配置
$('#mode_sure').click(function () {
	if ($('#Expired').val()<73 && $('#Expired').val()>=0){
		$("#Vtime").text("请填写正确的时间");
		return;
	}
	let mode_range = document.getElementsByName("mode_range");
	let range = 0;
	for (let i = 0; i <mode_range.length ; i++) {
		if (mode_range[i].checked) {
			range = i;
		}
	}
	$.ajax({
		url:"../../configWorkorder/editConfigWorkorder",
		type:"POST",
		data:{
			type:0,
			range:range,
			dead_hours:$('#Expired').val(),
			outdate_action:$('#outdate_action').val()
		},
		success:function (data) {
			if (data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				$('#edit_mode').modal('hide')
			}else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败');
				$("#modal-danger").modal();
			}
			listAll();
		}
	})
});

//回显审批流程
$('#editProcess').click(function(){
	$.ajax({
		url:"../../configWorkorder/listConfigWorkorder",
		type:"POST",
		success:function (data) {
			$('#level').val(data.data.level);
			document.getElementsByName("author_type")[data.data.mode].checked="checked";
			let btn = document.getElementById("inner");
			if(data.data.endaudit === 1){
				btn.style.left = 27 + "px";
				btn.className = "inner-off";
				document.getElementById("innerbtn").style.backgroundColor="#1494F7";
			}else{
				btn.style.left = 2+"px";
				btn.className = "inner-on";
				document.getElementById("innerbtn").style.backgroundColor="#8080808c";
			}
		}
	})
});
//编辑审批流程
$('#process_sure').click(function () {
	let author_type = document.getElementsByName("author_type");
	let mode = 0;
	for (let i = 0; i <author_type.length ; i++) {
		if (author_type[i].checked) {
			mode = i;
		}
	}
	let endaudit = 0;
	if (document.getElementById("inner").className==="inner-off"){
		endaudit = 1;
	}
	$.ajax({
		url:"../../configWorkorder/editConfigWorkorder",
		type:"POST",
		data:{
			type:1,
			level:$('#level').val(),
			mode:mode,
			endaudit:endaudit
		},
		success:function (data) {
			if (data.success){
				$("#modal-success .modal-title").text('成功');
				$("#modal-success .modal-body").text('编辑成功!');
				$("#modal-success").modal();
				$('#process_mode').modal('hide');
			}else{
				$("#modal-danger .modal-title").text('失败');
				$("#modal-danger .modal-body").text('编辑失败');
				$("#modal-danger").modal();
			}
			listAll();

		}
	})
});