
//组名的验证规则
var regexp = {
    username:/^([A-Za-z]|\-|\_|[0-9]){0,32}$/,
    name:/^([A-Za-z]|[\u4e00-\u9fa5]|\-|\@|\_|[0-9]){0,32}$/,
    length:/^\S{0,32}$/,
    length_des:/^\S{0,64}$/,
}

// 失去焦点
$('#email').blur(function(){
    var FORMATE = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var email = $('#email').val();
    if(email!=''){
        if (!FORMATE.test(email)){
            $('#Vemail').text('邮箱格式不正确');
        }
    }

});
// 获取焦点
$('#email').focus(function(){
    $('#Vemail').text('');
})

// 失去焦点
$('#mobile').blur(function(){
    var FORMATE = /^[1][0-9]{10}$/;
    var mobile = $('#mobile').val();
    if(mobile!=''){
        if (!FORMATE.test(mobile)){
            $('#Vmobile').text('手机号码格式不正确');
        }
    }

});
// 获取焦点
$('#mobile').focus(function(){
    $('#Vmobile').text('');
})

// 失去焦点
$('#qq').blur(function(){
    var FORMATE = /^[1-9][0-9]{4,14}$/
    var qq = $('#qq').val();
    if(qq!=''){
        if (!FORMATE.test(qq)){
            $('#Vqq').text('qq号格式不正确');
        }
    }

});
// 获取焦点
$('#qq').focus(function(){
    $('#Vqq').text('');
});

//姓名
$('#username').blur(function(){


    var username = $('#username').val();
    if (!regexp.name.test(username)){
        $('#Vusername').text('姓名格式不正确');
        if (!regexp.length.test(username)){
            $('#Vusername').text("最长32个字符")
        }
    }
});
// 获取焦点
$('#username').focus(function(){
    $('#Vusername').text('');
})




// 失去焦点
$('#new_password').blur(function(){
    var FORMATE = new RegExp("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,32}$");
    var passwd = $('#new_password').val();
    $.ajax({
        url:"../../user/verify",
        type:"POST",
        success:function(data){
            verify = data.verify;
            if (passwd!=""){
                if (!FORMATE.test(passwd) && verify==1){
                    $('#Vnew_password').text('密码格式不正确');
                }
            }

        },
    })

});
// 获取焦点
$('#new_password').focus(function(){
    $('#Vnew_password').text('');
})

// 失去焦点
$('#confirm_password').blur(function(){
    var passwd = $('#new_password').val();
    var _passwd = $('#confirm_password').val();
    if (passwd!=_passwd){
        $('#Vconfirm_password').text('密码不一致');
    }
});
// 获取焦点
$('#confirm_password').focus(function(){
    $('#Vconfirm_password').text('');
})
// 获取焦点
$('#present_password').focus(function(){
    $('#Vpassword').text('');
})

$('#modifypassword').click(function(){

    $('#new_password').blur();
    $('#confirm_password').blur();
    function check(){
        var flag = true;
        var password = $('#present_password').val();
        var new_password = $('#new_password').val();
        var confirm_password = $('#confirm_password').val();
        var Vpassword = $('#Vpassword').text();
        var Vnew_password = $('#Vnew_password').text();
        var Vconfirm_password = $('#Vconfirm_password').text();

        if (password=="" || new_password=="" || confirm_password=="" ){
            if (password==""){
                $('#Vpassword').text("原密码不能为空");
            }
            if (new_password==""){
                $('#Vnew_password').text("新密码不能为空");
            }
            if (confirm_password==""){
                $('#Vconfirm_password').text("确认密码不能为空");
            }
            flag =  false;
        }
        if (Vnew_password!="" || Vconfirm_password!="" || Vpassword!=""){
            flag = false;
        }
        return flag;
    }

    if (check()){
        $.ajax({
            url:"../../user/editSelf",
            type:"POST",
            data:{
                present_password:$('#present_password').val(),
                new_password:	$('#new_password').val(),
                confirm_password:$('#confirm_password').val()
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#present_password').val('');
                    $('#new_password').val('');
                    $('#confirm_password').val('');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                    $('#Vpassword').text(data.old_password);
                    $('#Vnew_password').text(data.S_password);
                }
            },
            error:function(){

            }
        })
    }

});

$.ajax({
    url:"../../user/verify",
    type:"POST",
    success:function(data){
        if(data.verify==1){
            var  html ="";
            html="<div style=\"min-width: 110px;float: left;color: #a4abb5;font-weight: 300;line-height: 1.4;letter-spacing: .6px;font-size: 12px;text-align: left;margin-left: 190px;\n" +
                "    margin-top: 10px;\"><span>密码长度为8-32个字符，需包含<br>大小写字母、数字和特殊字符</span></div><br><br>";
            $('#showRule').html(html);
            $('#_showRule').attr("style","margin-top: 55px;");
        }else {
            $('#showRule').html('');
            $('#_showRule').attr("style","margin-top: 0px;");
        }
    }
})

$("#editName").click(function(){
    if ($('#username').val()==""){
        $('#Vusername').text("姓名不能为空")
        return;
    }
    function check(){
        var username = $('#Vusername').text();
        var qq = $('#Vqq').text();
        var mobile = $('#Vmobile').text();
        var email = $('#Vemail').text();
        if (username!="" || qq!="" || email!="" || mobile!=""){
            return false;
        }else {
            return true;
        }

    }


    if (check()){
        $.ajax({
            url:"../../user/editName",
            type:"POST",
            data:{
                realname:$("#username").val(),
                email:$("#email").val(),
                mobile:$("#mobile").val(),
                qq:$("#qq").val(),
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("编辑成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    }
})
$('#click').click(function () {
    var username = $("#_username").text();
    $.ajax({
        url:"../../user/listSelf",
        type:"POST",
        data:{
            username:$("#_username").text(),
        },
        success:function(data){
            if(data.success){
                $('#username').val(data.realname)
                $('#qq').val(data.qq)
                $('#mobile').val(data.mobile)
                $('#email').val(data.email)
            }
            else{

            }
        },
        error:function(){

        }
    })
})
