//局部刷新表格
function loadAJAX(id){
	$('#checkAll').prop("checked",false);
	$(id).DataTable().ajax.reload();
}
//可以拖拽模态框
$(document).on("shown.bs.modal", ".modal", function(){
	$(this).draggable({
		handle: ".modal-header",   // 只能点击头部拖动
		cursor: 'move',
	});
});
$('.modal').on('show.bs.modal',function () {
	$('.modal').css("left",0);
	$('.modal').css("top",0);
	//模态框默认第一个激活active
	$('.modal .nav-tabs-custom li').removeClass('active');
	$('.modal .nav-tabs-custom li:first-child').addClass('active');
});
$('.modal').css('background','rgba(0,0,0,0)');
//返回状态模态框自动消失
$('#modal-success').on('show.bs.modal', function (){
	setTimeout(function () {
		$('#modal-success').modal("hide");
	}, 1500);
});
$('#modal-danger').on('show.bs.modal', function (){
	setTimeout(function () {
		$('#modal-danger').modal("hide");
	}, 1500);
});
$('#modal-hint').on('show.bs.modal', function (){
	setTimeout(function () {
		$('#modal-hint').modal("hide");
	}, 1500);
});

//点击空白处和ESC模态框不关闭
$('.modal-hides').modal({"backdrop": "static", "keyboard": false, "show":false,});

//全选删除
function sel(r){
	let o=document.getElementsByName(r);
	let obj  = event.srcElement ? event.srcElement : event.target;
	for(let i=0;i<o.length;i++)
		o[i].checked=obj.checked;
}