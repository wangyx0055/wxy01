$(function() {
    $('#sslEnable').click(function () {
        document.getElementById("email_port").value = '465';
    });
    $('#sslDisable').click(function () {
        document.getElementById("email_port").value = '25';
    })

});
   //邮件配置
    $('#email_button').click(function() {
    	$("#outbound_send").css("overflow-y","scroll");
        var flag = CheckEmail();
        if (flag=== false) return false;
        $.ajax({
                url: "../../configEmail/editConfigEmail",
                type: "POST",
                data: {
                    id: $("#email_id").val(),
                    ssl: $('#email_ssl input[name="ssl"]:checked').val(),
                	ip: $('#email_ip').val(),
                    port: $('#email_port').val(),
                    username: $('#email_username').val(),
                    password: $('#email_password').val()
                },
                success: function(data) {
                    if (data.success) {
                        $("#email_g").modal("hide");
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text('编辑成功!');
                        $("#modal-success").modal();
                        $('#email_ip1').text($('#email_ip').val());
                        $('#email_ssl1').text($('#email_ssl input[name="ssl"]').get(0).checked == true?"无":"SSL");
                        $('#email_port1').text($('#email_port').val());
                        $('#email_username1').text($('#email_username').val());
                        $('#email_password1').text("******");
                        $('#VEmail_ip').text();
                        $('#VEmail_port').text();
                        $('#VEmail_username').text();
                        $('#VEmail_password').text();
                    } else {
                        /*if (data.msg) {
                            for (k in data.msg) {
                                $('#VEmail_' + k).html(data.msg[k]);
                            }
                        }*/
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text('编辑失败!');
                        $("#modal-danger").modal();
                    }
                },
                error: function() {}
            })
    });
 function CheckEmail(){
    //邮件正则
    flag = true;
    var  email_port2= /^[0-9]{1,65535}$/;
    var  email_ip1= /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;;
    var email_ip2=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    var email_username2=/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
     var eip=$('#email_ip').val();
     var ePort=$('#email_port').val();
     var eUsername=$('#email_username').val();
     if (eip== ""){
         $('#VEmail_ip').text("请输入IP地址或域名");
         flag= false;
     }else if(email_ip1.test(eip)){
         $('#VEmail_ip').text("");
         flag= true;
     }else if(email_ip2.test(eip)){
         $('#VEmail_ip').text("");
         flag= true;
     }else {
         $('#VEmail_ip').text("请输入有效的IP地址或域名");
         flag=false;
     }
    if (eUsername== "") {
        $('#VEmail_username').text("请输入邮箱地址");
        flag= false;
    }else if(!email_username2.test(eUsername)){
        $('#VEmail_username').text("邮箱地址格式不正确");
        flag= false;
    }
    if (ePort=="") {
         $('#VEmail_port').text("请输入端口");
         flag= false;
     } else if (!email_port2.test(ePort)||ePort<1||ePort>65535) {
         $('#VEmail_port').text("请输入1-65535之间的有效数字");
         flag= false;
     }
   if ($('#email_password').val() == "") {
        $('#VEmail_password').text("请输入密码或授权码");
        flag= false;
    }
    return flag;
}

 
     /*  */
$('#emailedit').click(function() {
	$("#outbound_send").css("overflow-y","scroll");
    $('#VEmail_ip').text('');
    $('#VEmail_port').text('');
    $('#VEmail_username').text('');
    $('#VEmail_password').text('');
  /*  $('#email_ssl input[name="ssl"]').get(0).checked = true;*/
  /*  $('#email_port').val("25");*/

    $.ajax({
        "url": "../../configEmail/listConfigEmail",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                if (data.data[0].ssl == 0){
                    $('#email_ssl input[name="ssl"]').get(0).checked = true;
                    $('#email_ssl1').text("无");
                    $('#email_port').val("25");
                } else if (data.data[0].ssl == 1){
                    $('#email_ssl input[name="ssl"]').get(1).checked = true;
                    $('#email_ssl1').text("SSL");
                    $('#email_port').val("465");
                }
                $('#email_ip').val(data.data[0].ip);
                $('#email_port').val(data.data[0].port);
                $('#email_username').val(data.data[0].username);
                $('#email_password').val('xxxxxx');
            } else {}
        },
        error: function() {}
    })

});


   //短信bainji
   $("#message_btn").click(function () {
	   $("#outbound_send").css("overflow-y","scroll");
       // $("#telephone").val("");
       $("#telephone2").val("");
       $("#type_message input[name=sex]")[0].checked=true;
       /*$("#selfDe1").hide();*/
       $("#Vaip_Id").text("");
       $("#Vsign").text("");
       $("#Vapi_Secret").text("");
       $("#Vexample_code").text("");
       $("#SMS_telephone2").text("");
       $("#SMS_telephone").text("");
   });
   //短信第二步
    $('#sms_button').click(function() {
    	  $("#outbound_send").css("overflow-y","auto");
/*        if (type_message == 0) {
           /!* http_headers = $('#sms_http_header1').val();
            urls = $('#sms_url1').val();
            characters = $('#sms_character1').val();
            parameters = $('#sms_parameter1').val();*!/
            telephone=$("#telephone".val());
        } else {
            telephone = $('#telephone2').val();
    /!*     aip_Id = $('#aip_Id').val();
            sign = $('#sign').val();
            api_Secret = $('#api_Secret').val();
            example_code = $('#example_code').val();*!/
        }*/
        var flag=chekmassage();
        if(flag==false) return false;
      /*  if($("#type_message input[name=sex]")[0].checked==true) {
            $("#massageType").text('内置');
           showIN();
        }else
         if($("#type_message input[name=sex]").checked==true){
            $("#massageType").text("阿里云");
        }*/
        $.ajax({
            url: "../../configSms/editConfigSms",
            type: "POST",
            data: {
                id: $('#sms_id').val(),
                type: $('#type_message input[name="sex"]').val(),
                access_key_id: $("#aip_Id").val(),
                access_key_secret:$("#api_Secret").val(),
                sign_name: $("#sign").val(),
                template_code: $("#example_code").val(),
                /*password:$('#sms_password').val(),*/
            /*    url: urls,*/
            },
            success: function(data) {
                if (data.success) {
                    console.log("succ");
                    $("#sms_g").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#massageType').text("阿里云");
                    $('#aip_Id1').text($("#aip_Id").val());
                    $('#api_Secret1').text($("#api_Secret").val());
                    $('#sign1').text($("#sign").val());
                    $('#example_code1').text($("#example_code").val());
                }else{
                    console.log("succ _erro");
                   if (data.msg) {
                        for (k in data.msg) {
                            $('#SMS_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {
                console.log("erro");
            }
        });
    });
  /*  function showIN() {
        $("#selfDe1").hide();
    }
    function showSel() {
        $("#selfDe1").show();
    }*/
   /* function inDe(){
        $("#in_de").show();
        $("#self_de").hide();
    }*/
 /*   function selfDe(){
        $("#in_de").hide();
        $("#self_de").show();
    }*/
    function chekmassage() {
        var flag=true;
        var aip_Id=$("#aip_Id").val();
        var sign=$("#sign").val();
        var api_Secret=$("#api_Secret").val();
        var example_code=$("#example_code").val();
        if(aip_Id==""){
            $("#Vaip_Id").text("请输入aip_Id");
            flag=false;
        }
        if(sign==""){
            $("#Vsign").text("请输入姓名");
            flag=false;
        }
        if(api_Secret==""){
            $("#Vapi_Secret").text("请输入api_Secret");
            flag=false;
        }
        if(example_code==""){
            $("#Vexample_code").text("请输入模板编码");
            flag=false;
        }
        return flag;
    }
    $("#aip_Id").blur(function () {
        var aip_Id=$("#aip_Id").val();
        if(aip_Id==""){
            $("#Vaip_Id").text("请输入aip_Id");
        }
    });
     $("#sign").blur(function () {
         var sign=$("#sign").val();
         if(sign==""){
             $("#Vsign").text("请输入姓名");
         }
     });
    $("#api_Secret").blur(function () {
        var api_Secret=$("#api_Secret").val();
        if(api_Secret==""){
            $("#Vapi_Secret").text("请输入api_Secret");
        }
    });
    $("#example_code").blur(function () {
        var example_code=$("#example_code").val();
        if(example_code==""){
            $("#Vexample_code").text("请输入模板编码");
        }
    });
    $("#aip_Id").focus(function (){
          $("#Vaip_Id").text("");

      });
    $("#sign").focus(function () {
        $("#Vsign").text("");
    });
    $("#api_Secret").focus(function () {
        $("#Vapi_Secret").text("");
    });
    $("#example_code").focus(function () {
        $("#Vexample_code").text("");
    });
    //color picker with addon
/*    var smstest = function() {
        var regexp=/^[1]([3-9])[0-9]{9}$/;
        if ($('#telephone').val() == "") {
            $('#SMS_telephone').html("请输入手机号码");
            return;
        }else if (!regexp.test($('#telephone').val())){
            $('#SMS_telephone').html("手机号格式不正确");
            return;
        }
        $.ajax({
            url: "../../configSms/smstest",
            type: "POST",
            data: {},
            success: function(data) {
                if (data.success) {
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('发送成功!');
                    $("#modal-success").modal();
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('发送失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {}
  })
 };*/
/*$('#telephone').focus(function () {
    $('#SMS_telephone').html("");
});*/
$('#telephone2').focus(function () {
    $('#SMS_telephone2').html("");
});
  //一短信测试
    $('#sms_test').click(function() {
        $('#SMS_telephone').html("");
        smstest();
    });
    //第二个短信测试
   $('#sms_test2').click(function() {
       var regexp=/^[1]([3-9])[0-9]{9}$/;
       if ($('#telephone2').val() == "") {
           $('#SMS_telephone2').html("请输入手机号码");
           return;
       }else if (!regexp.test($('#telephone2').val())){
           $('#SMS_telephone2').html("手机号格式不正确");
           return;
       }
       $.ajax({
           url: "../../configSms/smstest",
           type: "POST",
           data: {},
           success: function(data) {
               if (data.success) {
                   $("#modal-success .modal-title").text('成功');
                   $("#modal-success .modal-body").text('发送成功!');
                   $("#modal-success").modal();
               } else {
                   $("#modal-danger .modal-title").text('失败');
                   $("#modal-danger .modal-body").text('发送失败!');
                   $("#modal-danger").modal();
               }
           },
           error: function() {}
       })

   });
    // $('#sms_test1').click(function() {
    //     smstest();
    // });
    $('#snmp_button1').click(function() {
        var flag = CheckSnmp();
        if (flag == false) return false;
       if($('#snmp_status input[name="snmp1"]:checked').val() == 0){
           showSnmp1();
        }else if($('#snmp_status input[name="snmp1"]:checked').val() == 1){
           hideSnmp1();
        }

        $.ajax({
            url: "../../configSnmp/editConfigSnmp",
            type: "POST",
            data: {
                id: $('#snmp_id').val(),
                status: $('#snmp_status input[name="snmp1"]').val(),
                community: $('#snmp_community').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#snmp_button").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#snmp_status1').text($('#snmp_status input[name="snmp1"]:checked').val() == 0 ? '开启': '关闭');
                    $('#snmp_community1').text($('#snmp_community').val());
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#Snmp_' + k).html(data.msg[k]);
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
    function showSnmp() {
        $("#snmpch").show();
    }
    function hideSnmp() {
        $("#snmpch").hide();
    }
    function hideSnmp1() {
        $("#snmpch1").hide();
    }
    function showSnmp1() {
        $("#snmpch1").show();
    }
    $('#syslog_button1').click(function() {
        var flag = CheckSyslog();
        if (flag == false) return false;
        if($('#syslog_status input[name="syslog"]:checked').val() == 0){
            showSys1()
        }else if($('#syslog_status input[name="syslog"]:checked').val() == 1){
            hideSys1()
        }
        $.ajax({
            url: "../../configSyslog/editConfigSyslog",
            type: "POST",
            data: {
                id: $('#syslog_id').val(),
                ip: $('#syslog_ip').val(),
                port: $('#syslog_port').val(),
                status: $('#syslog_status input[name="syslog"]').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#syslog_button").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#syslog_status1').text($('#syslog_status input[name="syslog"]:checked').val() == 0 ? '开启': '关闭');
                    $('#syslog_ip1').text($('#syslog_ip').val());
                    $('#syslog_port1').text($('#syslog_port').val());
                } else {

                    if (data.msg) {
                        for (k in data.msg) {
                            $('#Syslog_' + k).html(data.msg[k]);
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
    function showSys(){
        $("#syslog").show();
    }
    function hideSys(){
        $("#syslog").hide();
    }
    function showSys1(){
        $("#syslog1").show();
    }
    function hideSys1(){
        $("#syslog1").hide();
    }
    //color picker with addon邮件
    $.ajax({
        "url": "../../configEmail/listConfigEmail",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                $('#email_ip1').text(data.data[0].ip);
                $('#email_ssl1').text(data.data[0].ssl == 0?"无":"SSL");
                $('#email_port1').text(data.data[0].port);
                $('#email_id').val(data.data[0].id);
                $('#email_username1').text(data.data[0].username);
                $('#email_password1').text("******");
            } else {}
        },
        error: function() {}
    });
//短信回显
        $.ajax({
            url: "../../configSms/listConfigSms",
            type: "POST",
            data: {
                start: 0,
                length: 1
            },
            success: function(data) {
                if (data.success) {
                    $('#sms_id').val(data.data[0].id);
                    $('#aip_Id').val(data.data[0].access_key_id);
                    $('#api_Secret').val(data.data[0].access_key_secret);
                    $('#sign').val(data.data[0].sign_name);
                    $('#example_code').val(data.data[0].template_code);
                    $('#aip_Id1').text(data.data[0].access_key_id);
                    $('#massageType').text(data.data[0].type==1?"阿里云":"");
                    $('#api_Secret1').text(data.data[0].access_key_secret);
                    $('#sign1').text(data.data[0].sign_name);
                    $('#example_code1').text(data.data[0].template_code);
                  /*  $('#example_code').val(data.data[0].http_header);
                    $('#sms_http_header1').val(data.data[0].http_header);
                    $('#sms_character1').val(data.data[0].character);
                    $('#sms_character').val(data.data[0].character);
                    $('#sms_parameter1').val(data.data[0].parameter);*/
                    $('#Vaip_Id').text("");
                    $('#Vapi_Secret').text("");
                    $('#Vsign').text("");
                    $('#Vexample_code').text("");
                    $('#SMS_telephone').text("");
                    $('#SMS_telephone2').text("");
                } else {}
            },
            error: function() {}
        })
    $.ajax({
        "url": "../../configSnmp/listConfigSnmp",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                /* var id = data.data[0].id; */
                $('#snmp_id').val(data.data[0].id);
                $('#snmp_community1').html(data.data[0].community);
                if (data.data[0].status == 0) {
                    $('#snmp_status input[name="snmp1"]').get(0).checked = true;
                    $('#snmp_status1').html("开启");
                } else {
                    $('#snmp_status input[name="snmp1"]').get(1).checked = true;
                    $('#snmp_status1').html("关闭");
                }
            }
        },
        error: function() {

}
    });
    $.ajax({
        "url": "../../configSyslog/listConfigSyslog",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                /* var id = data.data[0].id; */
                $('#syslog_id').val(data.data[0].id);
                $('#syslog_ip1').html(data.data[0].ip);
                $('#syslog_port1').html(data.data[0].port);
                if (data.data[0].status == 0) {
                    $('#syslog_status input[name="syslog"]').get(0).checked = true;
                    $('#syslog_status1').html("开启");
                } else {
                    $('#syslog_status input[name="syslog"]').get(1).checked = true;
                    $('#syslog_status1').html("关闭");
                }
            } else {}
        },
        error: function() {

}
    })

    $('#syslogeditbtn').click(function() {
        document.getElementById("syslog_id1").value = document.getElementById("syslog_id").value;
        $("#syslog_ip").val(document.getElementById("syslog_ip1").innerHTML);
        $("#syslog_port").val(document.getElementById("syslog_port1").innerHTML);
        $("#VSyslog_ip").text("");
        $("#VSyslog_port").text("");
    });

    $('#snmpeditbtn').click(function() {
        document.getElementById("snmp_id1").value = document.getElementById("snmp_id").value;
        $("#snmp_community").val(document.getElementById("snmp_community1").innerHTML);
        $("#VSnmp_community").text("");
    });


    function CheckSnmp() {
        var flag = true;
        var reg=/[\u4E00-\u9FFF|。]+/;
        var ad_community = $('#snmp_community').val();
        if (ad_community == "") {
            $('#VSnmp_community').html("请输入通讯字符串");
             flag = false;
        }else if(reg.test(ad_community)){
        	 $('#VSnmp_community').html("输入格式不正确");
        	 flag = false;
        }
        return flag;
    }
    function CheckSyslog(){
        var flag = true;
        var p2 = /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;;
        var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        var ad_syslog_ip = $('#syslog_ip').val();
        if (ad_syslog_ip==""){
         $('#VSyslog_ip').html("请输入IP地址或域名");
         flag = false;
       }else if(p2.test(ad_syslog_ip)){
            $('#VSyslog_ip').html("");
            flag = true;
        }else if(p3.test(ad_syslog_ip)){
            $('#VSyslog_ip').html("");
            flag = true;
        }else {
            $('#VSyslog_ip').html("请输入有效的IP地址或域名");
            flag = false;
        }
        var ad_syslog_port = $('#syslog_port').val();
        var p1= /^[0-9]{1,65535}$/;
        if (ad_syslog_port == 0) {
            $('#VSyslog_port').html("请输入端口");
            flag = false;
        } else if(ad_syslog_port<1||ad_syslog_port>65535||!p1.test(ad_syslog_port)){
            $('#VSyslog_port').html("请输入1-65535之间的数字");
            flag = false;
        }
        return flag;
    }
//邮件置空
    //获取焦点
  $('#email_ip').blur(function() {
       var email_ip=$('#email_ip').val();
       var p2 = /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;;
       var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        if (email_ip==""){
            $('#VEmail_ip').text('请输入IP地址或域名');
        }else if(p2.test(email_ip)){
            $('#VEmail_ip').text("");
        }else if(p3.test(email_ip)){
            $('#VEmail_ip').text("");
        }else {
            $('#VEmail_ip').text("请输入有效的IP地址或域名");
        }
    });

    $('#email_port').blur(function() {
        var email_port= $('#email_port').val();
        var  p1= /^[0-9]{1,65535}$/;
        if (email_port== "") {
            $('#VEmail_port').text('请输入端口');
        }else if(email_port>65535||email_port<1||!p1.test(email_port)){
            $('#VEmail_port').text('请输入1-65535之间的有效数字');
        }
    });

    $('#email_username').blur(function() {
        var p1=/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        var email_username= $('#email_username').val()
        if (email_username== "") {
            $('#VEmail_username').text('请输入邮箱地址');
        }else if(!p1.test(email_username)){
            $('#VEmail_username').text('邮箱地址格式不正确');
        }
    });

    $('#email_password').blur(function() {
        if ($('#email_password').val()== "") {
            $('#VEmail_password').text('请输入密码或授权码');
        }
    });
    $('#email_port').focus(function() {
        $('#VEmail_port').text('');
    });
    $('#email_username').focus(function() {
        $('#VEmail_username').text('');
    });
    $('#email_password').focus(function(){
            $('#VEmail_password').text('');
    });
    $('#email_ip').focus(function() {
        $('#VEmail_ip').text('');
    });

//SNMP Agent配置
$("#snmp_community").blur(function () {
    var reg=/[\u4E00-\u9FFF|。]+/;
    var scommunity=$("#snmp_community").val();
    if(scommunity==""){
        $("#VSnmp_community").text("请输入通讯字符串");
    }else if(reg.test(scommunity)){
        $("#VSnmp_community").text("输入格式不正确");
    }
});
$("#snmp_community").focus(function () {
    $("#VSnmp_community").text("");
});

//SYSLOG配置
$("#syslog_ip").blur(function () {
    var syslog_ip=$("#syslog_ip").val();
    var p2 = /^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    if(syslog_ip==""){
        $("#VSyslog_ip").text("请输入IP地址或域名");
    }else if(p2.test(syslog_ip)){
        $("#VSyslog_ip").text("");
    }else if(p3.test(syslog_ip)){
        $("#VSyslog_ip").text("");
    }else {
        $("#VSyslog_ip").text("请输入有效的IP地址或域名");
    }
});
$("#syslog_ip").focus(function () {
    $("#VSyslog_ip").text("");
});

$("#syslog_port").blur(function () {
    var syslog_port=$("#syslog_port").val();
    var  p1= /^[0-9]{1,65535}$/;
    if(syslog_port==""){
        $("#VSyslog_port").text("请输入端口");
    }else if(syslog_port<1||syslog_port>65535||!p1.test(syslog_port)){
        $("#VSyslog_port").text("请输入1-65535之间的数字");
    }
});
$("#syslog_port").focus(function () {
    $("#VSyslog_port").text("");
});

//发件测试
$("#send_email").click(function(){
	    $.ajax({
           url: "../../configRadius/connectTest",
           type: "POST",
           data: {},
           success: function(data) {
               if (data.success) {
                   $("#modal-success .modal-title").text('成功');
                   $("#modal-success .modal-body").text('发件成功!');
                   $("#modal-success").modal();
                  
               } else {
                   $("#modal-success .modal-title").text('失败');
                   $("#modal-success .modal-body").text('发件失败!');
                   $("#modal-danger").modal();
               }
           },
           error: function() {}
	    })
});


