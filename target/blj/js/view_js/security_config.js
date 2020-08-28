$(function() {
    //登陆锁定
    $('#lockeditbtn').click(function() {
        document.getElementById("user_id1").value = document.getElementById("user_id").value;
        $("#user_lock_type").val(document.getElementById("user_lock_type1").innerHTML);
        $("#user_lock_try_count").val(document.getElementById("user_lock_try_count1").innerHTML);
        $("#user_lock_time_length").val(document.getElementById("user_lock_time_length1").innerHTML);
        $("#VPWD_lock_try_count").text("");
        $("#VPWD_lock_time_length").text("");
    });
    //密码
    $('#pwdpolicyeditbtn').click(function() {
        document.getElementById("password_id1").value = document.getElementById("password_id").value;
        $("#safe_password_verify").val(document.getElementById("password_verify1").innerHTML);
        $("#password_verification_times").val(document.getElementById("password_verification_times1").innerHTML);
        $("#safe_password_newuser_forcechange").val(document.getElementById("password_newuser_forcechange1").innerHTML);
        $("#password_cycle").val(document.getElementById("password_cycle1").innerHTML);
        $("#fail_warn").val(document.getElementById("fail_warn1").innerHTML);
     /*   $("#new_user_default_password").val(document.getElementById("new_user_default_password1").innerHTML);*/
        /*$("#Vnew_user_default_password").text('');*/
        $("#VPWD_password_verification_times").text("");
        $("#VPWD_password_cycle").text("");
    });
    $('#webeditbtn').click(function() {
        document.getElementById("web_web_id1").value = document.getElementById("web_web_id").value;
        $("#web_timeout").val(document.getElementById("web_timeout1").innerHTML);
        $("#same_user").val(document.getElementById("same_user1").innerHTML);
        $("#VPWD_web_timeout").text("");
        $("#Vsame_user").text("");
    })
    $('#admin_login_ip1').click(function() {
        document.getElementById("admin_login_ip_id1").value = document.getElementById("admin_login_ip").value;
        $("#admin_login_ip").val(document.getElementById("address").innerHTML);
        $("#VPWD_admin_login_ip").text("");
    })
   /*  if (document.getElementById("password_verify1").innerHTML == "开启") {
           var password_verify = 0;
       } else if (document.getElementById("password_verify1").innerHTML == "关闭") {
           var password_verify = 1;
       }
       if (document.getElementById("password_newuser_forcechange1").innerHTML == "开启") {
           var password_newuser_forcechange = 0;
       } else if (document.getElementById("password_newuser_forcechange1").innerHTML == "关闭") {
           var password_newuser_forcechange = 1;
       }*/
    //web登陆确定
    $('#web_button').click(function() {
        var p1=/^\d{1,3600}$/;
        var p2=/^\d{0,100}$/;
        if($('#web_timeout').val()==""){
        	$('#VPWD_web_timeout' ).html("请输入时间");
        	return ;
        } else if ($('#web_timeout').val()<1||$('#web_timeout').val()>3600||!p1.test($('#web_timeout').val())){
            $('#VPWD_web_timeout').html("输入时间不在有效值范围");
            return ;
        }
        if($('#same_user').val()==""){
            $('#Vsame_user' ).html("请输入WEB在线会话数");
            return ;
        }else if(!p2.test($('#same_user').val())||$('#same_user').val()<0||$('#same_user').val()>100){
            $('#Vsame_user').html("输入会话数不在有效值范围");
            return ;
        }
        $.ajax({
            url: "../../configLogin/editConfigLogin",
            type: "POST",
            data: {
                id: $('#web_web_id').val(),
                web_timeout: $('#web_timeout').val(),
                same_user: $('#same_user').val(),
            },
            success: function(data) {
                if (data.success) {
                	$('#VPWD_web_timeout' ).html("");
                    $("#web").modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#web_timeout1").text($('#web_timeout').val());
                    $("#same_user1").text($('#same_user').val());
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPWD_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });
    $('#admin_ip_button').click(function(){
        var regExp =/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
        var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        var ip = $('#admin_login_ip').val();
        var id = $('#admin_login_ip_id').val();
        if (ip!=""&&regExp.test(ip)){
            $('#VPWD_admin_login_ip').html("");
        }else if(ip!=""&&p3.test(ip)){
            $('#VPWD_admin_login_ip').html("");
        } else if(ip!=""){
            $('#VPWD_admin_login_ip').html("请输入有效的IP地址或域名");
            return false;
        }
        $.ajax({
            url: "../../configLogin/editConfigLogin",
            type: "POST",
            data: {
                id: id,
                admin_login_ip: ip
            },
            success: function(data) {
                if (data.success) {
                	$('#VPWD_admin_login_ip').html("");
                    $('#modal1').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $('#admin_login_ip').val(ip);
                    $('#address').text(ip);
                    $("#modal-success").modal();

                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPWD_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });
    function checkLogin(){
        var flag=true;
       /* var p1=/\d{0,10080}/;
        var p2=/\d{0,999}/;*/
        if($('#user_lock_try_count').val()==""){
            $('#VPWD_lock_try_count' ).html("请输入尝试密码次数");
            return false;

        }else if($('#user_lock_try_count').val()>999||$('#user_lock_try_count').val()<0||isNaN($('#user_lock_try_count').val())){
            $('#VPWD_lock_try_count' ).html("输入次数不在有效值范围");
            return false;
        }
        if($('#user_lock_time_length').val()==""){
            $('#VPWD_lock_time_length' ).html("请输入锁定时长");
            return false;
        }else if($('#user_lock_time_length').val()>10080||$('#user_lock_time_length').val()<0||isNaN($('#user_lock_time_length').val())){
            $('#VPWD_lock_time_length' ).html("输入时长不在有效值范围");
            return false;
        }
        return flag;
    }
//登陆锁定确定ok
    $('#lock_button').click(function() {
        var flag = checkLogin();
        if (flag == false) return false;
        $.ajax({
            url: "../../configLogin/editConfigLogin",
            type: "POST",
            data: {
                id: $('#user_lock_id').val(),
                lock_type: $('#user_lock_type input[name="suoding"]:checked').val(),
                lock_try_count: $('#user_lock_try_count').val(),
                lock_time_length: $('#user_lock_time_length').val(),
            },
            success: function(data) {
                if (data.success) {
                	$('#VPWD_lock_try_count' ).html("");
                    $("#modal-primary").modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#user_lock_type1").text($('#user_lock_type input[name="suoding"]:checked').val() == 0 ? '用户': '来源ip');
                    $("#user_lock_try_count1").text($('#user_lock_try_count').val());
                    $("#user_lock_time_length1").text($('#user_lock_time_length').val());

                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPWD_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });

    //密码
    function chekPassword(){
        var flag=true;
        var p1=/^\S{0,32}$/;
        var newPassw=$('#new_user_default_password').val();
        if($('#password_verification_times').val()==""){
            $('#VPWD_password_verification_times' ).html("请输入密码校验次数");
            return false;
        }else if($('#password_verification_times').val()>30||$('#password_verification_times').val()<1){
            $('#VPWD_password_verification_times' ).html("输入次数不在有效值范围");
            return false;
        }
        if($('#password_cycle').val()==""){
            $('#VPWD_password_cycle' ).html("请输入密码修改周期");
            return false;
        }else if($('#password_cycle').val()<0||$('#password_cycle').val()>365){
            $('#VPWD_password_cycle' ).html("输入次数不在有效值范围");
            return false;
        }
       /* if(newPassw!==" "&&!p1.test(newPassw)){
            $('#Vnew_user_default_password').html("输入密码格式不正确");
            return false;
        }*/
        return flag
    }
    $('#password_button').click(function() {
        var flag=chekPassword();
        if (flag == false) return false;
        if ($('#strength').is(":checked")) {
            var password_verify = 1;
        } else {
            var password_verify = 0;
        }
        if ($('#force').is(":checked")) {
            var password_newuser_forcechange = 0;
        } else {
            var password_newuser_forcechange = 1;
        }
        $.ajax({
            url: "../../configLogin/editConfigLogin",
            type: "POST",
            data: {
                id: $('#password_id').val(),
                password_verify:password_verify,
                password_verification_times: $('#password_verification_times').val(),
                password_newuser_forcechange:password_newuser_forcechange,
                password_cycle: $('#password_cycle').val(),
               /* new_user_default_password: $('#new_user_default_password').val(),*/
             /*   web_timeout: document.getElementById("web_timeout1").innerHTML,*/
            },
            success: function(data) {
                if (data.success) {
                    $("#modal-default2").modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#password_verify1").text(password_verify== 0 ? '关闭': '开启');
                    $("#password_verification_times1").text($('#password_verification_times').val());
                    $("#password_newuser_forcechange1").text(password_newuser_forcechange== 0 ? '开启': '关闭');
                    $("#password_cycle1").text($('#password_cycle').val());
                    $("#fail_warn1").text($('#fail_warn').val());
                    /*$("#new_user_default_password1").text($('#new_user_default_password').val());*/
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPWD_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });
    //color picker with addon
    $.ajax({
        "url": "../../configLogin/listConfigLogin",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                $('#user_lock_id').val(data.data[0].id);
                $('#password_id').val(data.data[0].id);
                $('#web_web_id').val(data.data[0].id);
                $('#admin_login_ip_id').val(data.data[0].id);
                $('#admin_login_ip').val(data.data[0].admin_login_ip);
                $('#address').text(data.data[0].admin_login_ip);
                $('#user_lock_try_count1').html(data.data[0].lock_try_count);
                $('#user_lock_time_length1').html(data.data[0].lock_time_length);
                if (data.data[0].lock_type == 0) {
                    $('#user_lock_type input[name="suoding"]').get(0).checked = true;
                    $('#user_lock_type1').html("用户");
                } else {
                    $('#user_lock_type input[name="suoding"]').get(1).checked = true;
                    $('#user_lock_type1').html("来源IP");
                }

                if (data.data[0].password_verify == 1) {
                    $('#safe_password_verify input[name="strength"]').get(0).checked = true;
                    $('#password_verify1').html("开启");
                } else {
                    $('#safe_password_verify input[name="strength"]').get(0).checked = false;
                    $('#password_verify1').html("关闭");
                }

                $('#password_verification_times1').html(data.data[0].password_verification_times);
                if (data.data[0].password_newuser_forcechange == 0) {
                    $('#safe_password_newuser_forcechange input[name="force"]').get(0).checked = true;
                    $('#password_newuser_forcechange1').html("开启");
                } else {
                 $('#safe_password_newuser_forcechange input[name="force"]').get(0).checked = false;
                    $('#password_newuser_forcechange1').html("关闭");
                }
                $('#password_cycle1').html(data.data[0].password_cycle);
                $('#fail_warn').html(data.data[0].fail_warn);
                /*$('#new_user_default_password1').html(data.data[0].new_user_default_password);*/
                $('#web_timeout1').html(data.data[0].web_timeout);
                $('#same_user1').html(data.data[0].same_user);
            } else {
                $("#modal-danger.modal-title").text('失败');
                $("#modal-danger .modal-body").text('编辑失败!');
                $("#modal-danger").modal();
            }
        },
        error: function() {

}
    });
});

//单个判断
//登陆锁定
$('#user_lock_try_count').blur(function (){
    var user_count=$('#user_lock_try_count').val();
    if(user_count==""){
        $('#VPWD_lock_try_count' ).html("请输入尝试密码次数");
    }else if(user_count>999||user_count<0||isNaN(user_count)){
        $('#VPWD_lock_try_count' ).html("输入次数不在有效值范围");
    }
});
$("#user_lock_try_count").focus(function () {
        $('#VPWD_lock_try_count' ).html("");
});

$('#user_lock_time_length').blur(function (){
    var user_lenght=$('#user_lock_time_length').val();
    if(user_lenght==""){
        $('#VPWD_lock_time_length' ).html("请输入锁定时长");
    }else if(user_lenght>10080||user_lenght<0||isNaN(user_lenght)){
        $('#VPWD_lock_time_length' ).html("输入时长不在有效值范围");
    }
});
$("#user_lock_time_length").focus(function () {
       $('#VPWD_lock_time_length' ).html("");
});
//密码策略
$('#password_verification_times').blur(function (){
    var password_verification_times=$('#password_verification_times').val();
    var p2=/\d{1,30}/;
    if(password_verification_times==""){
        $('#VPWD_password_verification_times' ).html("请输入密码校验次数");
    }else if(password_verification_times>30||password_verification_times<1||!p2.test(password_verification_times)){
        $('#VPWD_password_verification_times' ).html("输入次数不在有效值范围");
    }
});
$("#password_verification_times").focus(function () {
    $('#VPWD_password_verification_times' ).html("");
});
$('#password_cycle').blur(function (){
    var password_cycle=$('#password_cycle').val();
    var p2=/^\d{0,365}$/;
    if(password_cycle==""){
        $('#VPWD_password_cycle' ).html("请输入密码修改周期");
    }else if(password_cycle>365||password_cycle<0||!p2.test(password_cycle)){
        $('#VPWD_password_cycle' ).html("输入次数不在有效值范围");
    }
});
$("#password_cycle").focus(function () {
    $('#VPWD_password_cycle' ).html("");
});
/*$("#new_user_default_password").focus(function () {
    $('#Vnew_user_default_password' ).html("");
});
$('#new_user_default_password').focus(function () {
    $("#Vnew_user_default_password").text('');
});*/
//web登录
$('#web_timeout').blur(function () {
    var p1=/^\d{1,3600}$/;
    if($('#web_timeout').val()==""){
        $('#VPWD_web_timeout' ).html("请输入时间");
    }else if($('#web_timeout').val()<1||$('#web_timeout').val()>3600||!p1.test($('#web_timeout').val())){
        $('#VPWD_web_timeout').html("输入时间不在有效值范围");
    }
});
$('#same_user').blur(function (){
    var same_user=$('#same_user').val();
    var p2=/^\d{0,100}$/;
    if(same_user==""){
        $('#Vsame_user' ).html("请输入在线会话数");
    }else if(same_user>100||same_user<0||!p2.test(same_user)){
        $('#Vsame_user' ).html("输入会话数不在有效值范围");
    }
});
$('#same_user').focus(function () {
    $('#Vsame_user').html("");
});
$('#web_timeout').focus(function () {
    $('#VPWD_web_timeout').html("");
});

//远程管理

$('#admin_login_ip').blur(function () {
    var ip = $('#admin_login_ip').val();
    var regExp =/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    if (ip!=""&&regExp.test(ip)){
        $('#VPWD_admin_login_ip').html("");
    }else if(ip!=""&&p3.test(ip)){
        $('#VPWD_admin_login_ip').html("");
    } else if(ip!=""){
        $('#VPWD_admin_login_ip').html("请输入有效的IP地址或域名");
    }
});
$('#admin_login_ip').focus(function () {
    $('#VPWD_admin_login_ip').html("");
});