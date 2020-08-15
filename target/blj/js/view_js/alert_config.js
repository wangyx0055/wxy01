function editInfo(){
        $.ajax({
            url:"../../configAlertLevel/editConfigAlertLevel",
            type:"POST",
            data:{
                'id': $('#config_alert_id').val(),
                //阈值
                'cpu_max': $('#cpu_max').val(),
                'memory_max': $('#memory_max').val(),
                'disk_max': $('#disk_max').val(),
                'swap_max': $('#swap_max').val(),
                //告警方式
                'cpu_max_msg': ($('#cpu_max_msg_1')[0].checked?'1':'0')+($('#cpu_max_msg_2')[0].checked?'1':'0')+($('#cpu_max_msg_3')[0].checked?'1':'0'),
                'disk_max_msg': ($('#disk_max_msg_1')[0].checked?'1':'0')+($('#disk_max_msg_2')[0].checked?'1':'0')+($('#disk_max_msg_3')[0].checked?'1':'0'),
                'swap_max_msg': ($('#swap_max_msg_1')[0].checked?'1':'0')+($('#swap_max_msg_2')[0].checked?'1':'0')+($('#swap_max_msg_3')[0].checked?'1':'0'),
                'memory_max_msg': ($('#memory_max_msg_1')[0].checked?'1':'0')+($('#memory_max_msg_2')[0].checked?'1':'0')+($('#memory_max_msg_3')[0].checked?'1':'0'),
                //告警等级
                'cpu_max_level': $('#cpu_max_level').val(),
                'disk_max_level': $('#disk_max_level').val(),
                'swap_max_level': $('#swap_max_level').val(),
                'memory_max_level': $('#memory_max_level').val(),
                //访问阈值
                'web_max': $('#web_max').val(),
                'ssh_max': $('#ssh_max').val(),
                'rdp_max': $('#rdp_max').val(),
                'telnet_max': $('#telnet_max').val(),
                'vnc_max': $('#vnc_max').val(),
                'app_max': $('#app_max').val(),
                //访问告警方式
                'web_max_msg': ($('#web_max_msg_1')[0].checked?'1':'0')+($('#web_max_msg_2')[0].checked?'1':'0')+($('#web_max_msg_3')[0].checked?'1':'0'),
                'ssh_max_msg': ($('#ssh_max_msg_1')[0].checked?'1':'0')+($('#ssh_max_msg_2')[0].checked?'1':'0')+($('#ssh_max_msg_3')[0].checked?'1':'0'),
                'rdp_max_msg': ($('#rdp_max_msg_1')[0].checked?'1':'0')+($('#rdp_max_msg_2')[0].checked?'1':'0')+($('#rdp_max_msg_3')[0].checked?'1':'0'),
                'telnet_max_msg': ($('#telnet_max_msg_1')[0].checked?'1':'0')+($('#telnet_max_msg_2')[0].checked?'1':'0')+($('#telnet_max_msg_3')[0].checked?'1':'0'),
                'vnc_max_msg': ($('#vnc_max_msg_1')[0].checked?'1':'0')+($('#vnc_max_msg_2')[0].checked?'1':'0')+($('#vnc_max_msg_3')[0].checked?'1':'0'),
                'app_max_msg': ($('#app_max_msg_1')[0].checked?'1':'0')+($('#app_max_msg_2')[0].checked?'1':'0')+($('#app_max_msg_3')[0].checked?'1':'0'),
                //访问告警等级
                'web_max_level': $('#web_max_level').val(),
                'ssh_max_level': $('#ssh_max_level').val(),
                'rdp_max_level': $('#rdp_max_level').val(),
                'telnet_max_level': $('#telnet_max_level').val(),
                'vnc_max_level': $('#vnc_max_level').val(),
                'app_max_level': $('#app_max_level').val(),
            },
            success:function(data){
                if(data.success){
                    $('#06').modal('hide');
                    $('#07').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();

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
    }
   
//回显
        $.ajax({
            url:"../../configAlertLevel/listConfigAlertLevel",
            type:"POST",
            data:{
                start:0,
                length:1,
            },
            success:function(data){
            	$('#config_alert_id').val(data.data[0].id);
                $('#cpu_maxA').text(data.data[0].cpu_max);
                $('#disk_maxA').text(data.data[0].disk_max);
                $('#swap_maxA').text(data.data[0].swap_max);
                $('#memory_maxA').text(data.data[0].memory_max);
                $('#cpu_max_msg_A').text((data.data[0].cpu_max_msg[0]==1?'消息':'' )+" "+" "+(data.data[0].cpu_max_msg[1]==1?'邮件':'')+" "+" "+(data.data[0].cpu_max_msg[2]==1?'短信':''));
                $('#disk_max_msg_A').text((data.data[0].disk_max_msg[0]==1?'消息':'' )+" "+" "+(data.data[0].disk_max_msg[1]==1?'邮件':'')+" "+" "+(data.data[0].disk_max_msg[2]==1?'短信':''));
                $('#swap_max_msg_A').text((data.data[0].swap_max_msg[0]==1?'消息':'' )+" "+" "+(data.data[0].swap_max_msg[1]==1?'邮件':'')+" "+" "+(data.data[0].swap_max_msg[2]==1?'短信':''));
                $('#memory_max_msg_A').text((data.data[0].memory_max_msg[0]==1?'消息':'' )+" "+" "+(data.data[0].memory_max_msg[1]==1?'邮件':'')+" "+" "+(data.data[0].memory_max_msg[2]==1?'短信':''));
                $('#cpu_max_msg_1')[0].checked = data.data[0].cpu_max_msg[0]==1?true:false;
                $('#cpu_max_msg_2')[0].checked = data.data[0].cpu_max_msg[1]==1?true:false;
                $('#cpu_max_msg_3')[0].checked = data.data[0].cpu_max_msg[2]==1?true:false;
                $('#disk_max_msg_1')[0].checked = data.data[0].disk_max_msg[0]==1?true:false;
                $('#disk_max_msg_2')[0].checked = data.data[0].disk_max_msg[1]==1?true:false;
                $('#disk_max_msg_3')[0].checked = data.data[0].disk_max_msg[2]==1?true:false;
                $('#swap_max_msg_1')[0].checked = data.data[0].swap_max_msg[0]==1?true:false;
                $('#swap_max_msg_2')[0].checked = data.data[0].swap_max_msg[1]==1?true:false;
                $('#swap_max_msg_3')[0].checked = data.data[0].swap_max_msg[2]==1?true:false;
                $('#memory_max_msg_1')[0].checked = data.data[0].memory_max_msg[0]==1?true:false;
                $('#memory_max_msg_2')[0].checked = data.data[0].memory_max_msg[1]==1?true:false;
                $('#memory_max_msg_3')[0].checked = data.data[0].memory_max_msg[2]==1?true:false;
                $('#cpu_max_level').val(data.data[0].cpu_max_level);
                $('#disk_max_level').val(data.data[0].disk_max_level);
                $('#swap_max_level').val(data.data[0].swap_max_level);
                $('#memory_max_level').val(data.data[0].memory_max_level);
                  if(data.data[0].cpu_max_level==1){
                      $('#cpu_max_levelA').text("低");
                  }else if(data.data[0].cpu_max_level==2){
                      $('#cpu_max_levelA').text("中");
                  }else {
                      $('#cpu_max_levelA').text("高");
                  }
               /* $('#cpu_max_levelA').text(data.data[0].cpu_max_level==1?'低':''||data.data[0].cpu_max_level==2?'中':''||data.data[0].cpu_max_level==3?'高':'');*/
                $('#disk_max_levelA').text(data.data[0].disk_max_level==1?'低':''||data.data[0].disk_max_level==2?'中':''||data.data[0].disk_max_level==3?'高':'');
                $('#swap_max_levelA').text(data.data[0].swap_max_level==1?'低':''||data.data[0].swap_max_level==2?'中':''||data.data[0].swap_max_level==3?'高':'');
                $('#memory_max_levelA').text(data.data[0].memory_max_level==1?'低':''||data.data[0].memory_max_level==2?'中':''||data.data[0].memory_max_level==3?'高':'');

                $('#web_max').val(data.data[0].web_max);
                $('#ssh_max').val(data.data[0].ssh_max);
                $('#rdp_max').val(data.data[0].rdp_max);
                $('#app_max').val(data.data[0].app_max);
                $('#telnet_max').val(data.data[0].telnet_max);
                $('#vnc_max').val(data.data[0].vnc_max);
                $('#web_max_msg_1')[0].checked = data.data[0].web_max_msg[0]==1?true:false;
                $('#web_max_msg_2')[0].checked = data.data[0].web_max_msg[1]==1?true:false;
                $('#web_max_msg_3')[0].checked = data.data[0].web_max_msg[2]==1?true:false;
                $('#ssh_max_msg_1')[0].checked = data.data[0].ssh_max_msg[0]==1?true:false;
                $('#ssh_max_msg_2')[0].checked = data.data[0].ssh_max_msg[1]==1?true:false;
                $('#ssh_max_msg_3')[0].checked = data.data[0].ssh_max_msg[2]==1?true:false;
                $('#rdp_max_msg_1')[0].checked = data.data[0].rdp_max_msg[0]==1?true:false;
                $('#rdp_max_msg_2')[0].checked = data.data[0].rdp_max_msg[1]==1?true:false;
                $('#rdp_max_msg_3')[0].checked = data.data[0].rdp_max_msg[2]==1?true:false;
                $('#app_max_msg_1')[0].checked = data.data[0].app_max_msg[0]==1?true:false;
                $('#app_max_msg_2')[0].checked = data.data[0].app_max_msg[1]==1?true:false;
                $('#app_max_msg_3')[0].checked = data.data[0].app_max_msg[2]==1?true:false;
                $('#telnet_max_msg_1')[0].checked = data.data[0].telnet_max_msg[0]==1?true:false;
                $('#telnet_max_msg_2')[0].checked = data.data[0].telnet_max_msg[1]==1?true:false;
                $('#telnet_max_msg_3')[0].checked = data.data[0].telnet_max_msg[2]==1?true:false;
                $('#vnc_max_msg_1')[0].checked = data.data[0].vnc_max_msg[0]==1?true:false;
                $('#vnc_max_msg_2')[0].checked = data.data[0].vnc_max_msg[1]==1?true:false;
                $('#vnc_max_msg_3')[0].checked = data.data[0].vnc_max_msg[2]==1?true:false;
                $('#web_max_level').val(data.data[0].web_max_level);
                $('#ssh_max_level').val(data.data[0].ssh_max_level);
                $('#rdp_max_level').val(data.data[0].rdp_max_level);
                $('#app_max_level').val(data.data[0].app_max_level);
                $('#telnet_max_level').val(data.data[0].telnet_max_level);
                $('#vnc_max_level').val(data.data[0].vnc_max_level);
                $('#web_maxA').text(data.data[0].web_max);
                $('#ssh_maxA').text(data.data[0].ssh_max);
                $('#rdp_maxA').text(data.data[0].rdp_max);
                $('#app_maxA').text(data.data[0].app_max);
                $('#telnet_maxA').text(data.data[0].telnet_max);
                $('#vnc_maxA').text(data.data[0].vnc_max);
                $('#web_max_msg_A').text((data.data[0].web_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].web_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].web_max_msg[2] == 1 ? '短信' : ''));
                $('#ssh_max_msg_A').text((data.data[0].ssh_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].ssh_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].ssh_max_msg[2] == 1 ? '短信' : ''));
                $('#rdp_max_msg_A').text((data.data[0].rdp_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].rdp_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].rdp_max_msg[2] == 1 ? '短信' : ''));
                $('#app_max_msg_A').text((data.data[0].app_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].app_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].app_max_msg[2] == 1 ? '短信' : ''));
                $('#telnet_max_msg_A').text((data.data[0].telnet_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].telnet_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].telnet_max_msg[2] == 1 ? '短信' : ''));
                $('#vnc_max_msg_A').text((data.data[0].vnc_max_msg[0] == 1 ? '消息' : '') + " " + " " + (data.data[0].vnc_max_msg[1] == 1 ? '邮件' : '') + " " + " " + (data.data[0].vnc_max_msg[2] == 1 ? '短信' : ''));
                $('#web_max_levelA').text(data.data[0].web_max_level==1?'低':''||data.data[0].web_max_level==2?'中':''||data.data[0].web_max_level==3?'高':'');
                $('#ssh_max_levelA').text(data.data[0].ssh_max_level==1?'低':''||data.data[0].ssh_max_level==2?'中':''||data.data[0].ssh_max_level==3?'高':'');
                $('#rdp_max_levelA').text(data.data[0].rdp_max_level==1?'低':''||data.data[0].rdp_max_level==2?'中':''||data.data[0].rdp_max_level==3?'高':'');
                $('#app_max_levelA').text(data.data[0].app_max_level==1?'低':''||data.data[0].app_max_level==2?'中':''||data.data[0].app_max_level==3?'高':'');
                $('#telnet_max_levelA').text(data.data[0].telnet_max_level==1?'低':''||data.data[0].telnet_max_level==2?'中':''||data.data[0].telnet_max_level==3?'高':'');
                $('#vnc_max_levelA').text(data.data[0].vnc_max_level==1?'低':''||data.data[0].vnc_max_level==2?'中':''||data.data[0].vnc_max_level==3?'高':'');


            }
        });
//系统编辑
  function showInfo(){
        $("#cpu_max").val($('#cpu_maxA').text());
        $("#memory_max").val($('#memory_maxA').text());
        $("#disk_max").val($('#disk_maxA').text());
        $("#swap_max").val($('#swap_maxA').text());
        $("#Vcpu_max").text("");
        $("#Vmemory_max").text("");
        $("#Vdisk_max").text("");
        $("#Vswap_max").text("");
       /* $('#cpu_max_msg_1')[0].checked=true;
        $('#memory_max_msg_1')[0].checked=true;
        $('#disk_max_msg_1')[0].checked=true;
        $('#swap_max_msg_1')[0].checked=true;*/
        /*$('#cpu_max_level').val( $('#cpu_max_levelA').text());
        $('#disk_max_level').val($('#disk_max_levelA').text());
        $('#swap_max_level').val($('#swap_max_level').text());
        $('#memory_max_level').val( $('#memory_max_level').text());*/
    }

/*    //访问量
    $.ajax({
        url:"../../configAlertLevel/listConfigAlertLevel",
        type:"POST",
        data:{
            start:0,
            length:1,
        },
        success:function(data) {

        }
    });*/
        function showInfo2(){
            $("#web_max").val($('#web_maxA').text());
            $("#ssh_max").val($('#ssh_maxA').text());
            $("#rdp_max").val($('#rdp_maxA').text());
            $("#vnc_max").val($('#vnc_maxA').text());
            $("#app_max").val($('#app_maxA').text());
            $("#telnet_max").val($('#telnet_maxA').text());
            $("#Vweb_max").text("");
            $("#Vssh_max").text("");
            $("#Vrdp_max").text("");
            $("#Vvnc_max").text("");
            $("#Vapp_max").text("");
            $("#Vtelnet_max").text("");
        }
//系统确定
    $('#alertSysBtn').click(function(){
    	var flag=checkSysbtn();
    	if(flag==false) return false;
        editInfo();
    	$("#cpu_maxA").text($("#cpu_max").val());
        $("#cpu_max_msg_A").text(($('#cpu_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#cpu_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#cpu_max_msg_3')[0].checked?'短信':''));
        $("#cpu_max_levelA").text($("#cpu_max_level option:checked" ).text());
        $("#memory_maxA").text($("#memory_max").val());
        $("#memory_max_msg_A").text(($('#memory_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#memory_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#memory_max_msg_3')[0].checked?'短信':''));
        $("#memory_max_levelA").text($("#memory_max_level option:checked" ).text());
        $("#disk_maxA").text($("#disk_max").val());
        $("#disk_max_msg_A").text(($('#disk_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#disk_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#disk_max_msg_3')[0].checked?'短信':''));
        $("#disk_max_levelA").text($("#disk_max_level option:checked" ).text());
        $("#swap_maxA").text($("#swap_max").val());
        $("#swap_max_msg_A").text(($('#swap_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#swap_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#swap_max_msg_3')[0].checked?'短信':''));
        $("#swap_max_levelA").text($("#swap_max_level option:checked" ).text());
    });
    
    $('#alertSessionBtn').click(function(){
        var flag=checkSessionBtn();
        if(flag==false) return false;
    	editInfo();
        $('#web_maxA').text($("#web_max").val());
        $("#web_max_msg_A").text(($('#web_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#web_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#web_max_msg_3')[0].checked?'短信':''));
        $("#web_max_levelA").text($("#web_max_level option:checked" ).text());
        $('#ssh_maxA').text($("#ssh_max").val());
        $("#ssh_max_msg_A").text(($('#ssh_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#ssh_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#ssh_max_msg_3')[0].checked?'短信':''));
        $("#ssh_max_levelA").text($("#ssh_max_level option:checked" ).text());
        $('#rdp_maxA').text($("#rdp_max").val());
        $("#rdp_max_msg_A").text(($('#rdp_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#rdp_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#rdp_max_msg_3')[0].checked?'短信':''));
        $("#rdp_max_levelA").text($("#rdp_max_level option:checked" ).text());
        $('#vnc_maxA').text($("#vnc_max").val());
        $("#vnc_max_msg_A").text(($('#vnc_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#vnc_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#vnc_max_msg_3')[0].checked?'短信':''));
        $("#vnc_max_levelA").text($("#vnc_max_level option:checked" ).text());
        $('#app_maxA').text($("#app_max").val());
        $("#app_max_msg_A").text(($('#app_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#app_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#app_max_msg_3')[0].checked?'短信':''));
        $("#app_max_levelA").text($("#app_max_level option:checked" ).text());
        $('#telnet_maxA').text($("#telnet_max").val());
        $("#telnet_max_msg_A").text(($('#telnet_max_msg_1')[0].checked?'消息':'')+" "+" "+($('#telnet_max_msg_2')[0].checked?'邮件':'')+" "+" "+($('#telnet_max_msg_3')[0].checked?'短信':''));
        $("#telnet_max_levelA").text($("#telnet_max_level option:checked" ).text());
    });
//系统判断
function checkSysbtn() {
    var p1=/^\d{0,100}$/;
    var flag=true;
    var cpuA=$("#cpu_max").val();
    var memoryA=$("#memory_max").val();
    var diskA=$("#disk_max").val();
    var swapA=$("#swap_max").val();
    if(cpuA==""){
        $("#Vcpu_max").text("请输入阈值");
        flag=false;
    }else if(cpuA<0||cpuA>100||!p1.test(cpuA)){
        $("#Vcpu_max").text("有效值为0~100");
        flag=false;
    }
    if(memoryA==""){
        $("#Vmemory_max").text("请输入阈值");
        flag=false;
    }else if(memoryA<0||memoryA>100||!p1.test(memoryA)){
        $("#Vmemory_max").text("有效值为0~100");
        flag=false;
    }
    if(diskA==""){
        $("#Vdisk_max").text("请输入阈值");
        flag=false;
    }else if(diskA<0||diskA>100||!p1.test(diskA)){
        $("#Vdisk_max").text("有效值为0~100");
        flag=false;
    }
    if(swapA==""){
        $("#Vswap_max").text("请输入阈值");
        flag=false;
    }else if(swapA<0||swapA>100||!p1.test(swapA)){
        $("#Vswap_max").text("有效值为0~100");
        flag=false;
    }
    return flag;
}
$("#cpu_max").focus(function () {
    $("#Vcpu_max").text("");
});
$("#memory_max").focus(function () {
    $("#Vmemory_max").text("");
});
$("#disk_max").focus(function () {
    $("#Vdisk_max").text("");
});
$("#swap_max").focus(function () {
    $("#Vswap_max").text("");
});

//访问判断
function checkSessionBtn() {
    var p1=/^\d{0,999}$/;
    var flag=true;
    var webA=$("#web_max").val();
    var sshA=$("#ssh_max").val();
    var rdpA=$("#rdp_max").val();
    var vncA=$("#vnc_max").val();
    var appA=$("#app_max").val();
    var telnetA=$("#telnet_max").val();
    if(webA==""){
        $("#Vweb_max").text("请输入阈值");
        flag=false;
    }else if(webA<0||webA>999||!p1.test(webA)){
        $("#Vweb_max").text("有效值为0~999");
        flag=false;
    }
    if(sshA==""){
        $("#Vssh_max").text("请输入阈值");
        flag=false;
    }else if(sshA<0||sshA>999||!p1.test(sshA)){
        $("#Vssh_max").text("有效值为0~999");
        flag=false;
    }
    if(rdpA==""){
        $("#Vrdp_max").text("请输入阈值");
        flag=false;
    }else if(rdpA<0||rdpA>999||!p1.test(rdpA)){
        $("#Vrdp_max").text("有效值为0~999");
        flag=false;
    }
    if(vncA==""){
        $("#Vvnc_max").text("请输入阈值");
        flag=false;
    }else if(vncA<0||vncA>999||!p1.test(vncA)){
        $("#Vvnc_max").text("有效值为0~999");
        flag=false;
    }
    if(appA==""){
        $("#Vapp_max").text("请输入阈值");
        flag=false;
    }else if(appA<0||appA>999||!p1.test(appA)){
        $("#Vapp_max").text("有效值为0~999");
        flag=false;
    }
    if(telnetA==""){
        $("#Vtelnet_max").text("请输入阈值");
        flag=false;
    }else if(telnetA<0||telnetA>999||!p1.test(telnetA)){
        $("#Vtelnet_max").text("有效值为0~999");
        flag=false;
    }
      return flag;
}
    $("#web_max").focus(function () {
        $("#Vweb_max").text("");
    });
    $("#ssh_max").focus(function () {
        $("#Vssh_max").text("");
    });
    $("#rdp_max").focus(function () {
        $("#Vrdp_max").text("");
    });
    $("#vnc_max").focus(function () {
        $("#Vvnc_max").text("");
    });
    $("#app_max").focus(function () {
        $("#Vapp_max").text("");
    });
    $("#telnet_max").focus(function () {
        $("#Vtelnet_max").text("");
   });