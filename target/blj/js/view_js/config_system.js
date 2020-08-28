
function getServerDate() {
	var d = $.ajax({type:'HEAD',async: false}).getResponseHeader("Date")
    let time =  new Date(d);
    let yy = time.getFullYear();
    let mo = time.getMonth()+1;
    let dd = time.getDate();
    let hh = time.getHours();
    let mm = time.getMinutes();
    let ss = time.getSeconds();
    mm = extra(mm);
    ss = extra(ss);
    document.getElementById("navbar_time").value=yy+"-"+mo+"-"+dd+" "+hh+":"+mm+":"+ss;
}
getServerDate();
function extra(x) {
    if(x < 10){
        return "0" + x;
    }else{
        return x;
    }
}
function init() {
    //定义locale汉化插件
    var locale = {
        format: "HH:mm",
        "applyLabel": "确定",
        "cancelLabel": "取消",
        startView:0,
        minView: 0,
        minuteStep:5
    };
    //初始化显示当前时间

    //日期控件初始化
    $('#session_backup_time').daterangepicker(
        {
            'locale': locale,
            autoApply: false,
            timePicker: true, //显示时间
            timePicker24Hour: true, //时间制
            autoclose: false,
            singleDatePicker: true,
        }
    );
    $('#save-time').daterangepicker(
        {
            'locale': locale,
            showDropdowns: false,
            autoApply: false,
            singleDatePicker: true,
            opens: "center",

        }
    );
    $('#save-time1').daterangepicker(
        {
            'locale': locale,
            showDropdowns: true,
            autoApply: false,
            timePicker:true,
            singleDatePicker: true,
            opens: "center",

        }
    );
};
$(document).ready(function() {
    init();
});
//ajax
$('#updateServer').click(function(){
	 $("#modal-upload .modal-title").text('状态');
	    $("#modal-upload .modal-body").text('正在同步中...');
	    $("#modal-upload").modal();
    $.ajax({
        url:"../../configSyslog/updateTimeServer",
        type:"POST",
        data:{
            server:$('#timeserver').val()
        },
        success:function(data){
            if(data.success){
                $("#modal-upload").modal('hide');
                $("#modal-success.modal-title").text('成功');
                $("#modal-success.modal-body").text('编辑成功!');
                $("#modal-success").modal();
            }
            else{
                $("#modal-danger.modal-title").text('失败');
                $("#modal-danger.modal-body").text("编辑失败!");
                $("#modal-danger").modal();
            }
        },
        error:function(){}
    })
});

$('#setDatetime').click(function(){
    $.ajax({
        url:"../../configSyslog/setDatetime",
        type:"POST",
        data:{
            sdatetime:$('#navbar_time').val()
        },
        success:function(data) {
            if(data.success){
                $("#modal-upload").modal('hide');
                $("#modal-success.modal-title").text('成功');
                $("#modal-success.modal-body").text('操作成功!');
                $("#modal-success").modal();
            }
            else{
                $("#modal-danger.modal-title").text('失败');
                $("#modal-danger.modal-body").text("操作失败!");
                $("#modal-danger").modal();
            }
        },
        error:function(){}
    })
});
//判断
