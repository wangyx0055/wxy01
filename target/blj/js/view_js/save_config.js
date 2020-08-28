  function CheckSave() {
        var flag = true;
        var p0=/^\d{1,65535}$/;
        /*var p1 = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/g;*/
        var p1=/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
        var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        var path=/^(http(s)?:\/\/)?(www\.)?([\/a-zA-Z0-9][-a-zA-Z]{0,62})+(\.[a-zA-Z0-9][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
        var _session_backup_time = $('#session_backup_time').val();
        if (_session_backup_time=="") {
            $('#VSave_session_backup_time').html("请输入备份时间");
            flag = false;
        } else {
            $('#VSave_session_backup_time').html("");
        }
        var _session_backup_host = $('#session_backup_host').val();
        if (_session_backup_host=="") {
            $('#VSave_session_backup_host').html("请输入IP地址或域名");
            flag = false;
        } else if(p1.test(_session_backup_host)){
            $('#VSave_session_backup_host').html("");
            flag = true;
        }else if(p3.test(_session_backup_host)){
            $('#VSave_session_backup_host').html("");
            flag = true;
        }else {
            $('#VSave_session_backup_host').html("请输入有效的IP地址或域名");
            flag=false;
        }
        var _session_backup_port = $('#session_backup_port').val();
        if (_session_backup_port=="") {
            $('#VSave_session_backup_port').html("请输入备份端口");
            flag = false;
        } else if(!p0.test(_session_backup_port)||_session_backup_port<1||_session_backup_port>65535){
            $('#VSave_session_backup_port').html("请输入1-65535之间的数字");
            flag = false;
        }
        var reg=/[\u4E00-\u9FFF|。]+/;
        var _session_backup_username = $('#session_backup_username').val();
        if (_session_backup_username=="") {
            $('#VSave_session_backup_username').html("请输入备份账号");
            flag = false;
        }else if(reg.test(_session_backup_username)){
        	  $('#VSave_session_backup_username').html("输入格式不正确");
              flag = false;
        }else {
            $('#VSave_session_backup_username').html("");
        }
        var _session_backup_password = $('#session_backup_password').val();
        if (_session_backup_password=="") {
            $('#VSave_session_backup_password').html("请输入备份用户密码");
            flag = false;
        } /*else if(!p4.test(_session_backup_password)){
            $('#VSave_session_backup_password').html("密码格式不正确");
            flag = false;
        }*/
        var _session_backup_path = $('#session_backup_path').val();
        if (_session_backup_path=="") {
            $('#VSave_session_backup_path').html("请输入备份路径");
            flag = false;
        }else if(!path.test(_session_backup_path)){
            $('#VSave_session_backup_path').html("请输入有效的备份路径");
            flag = false;
        }
        return flag;
    }
//置空与传值
  $("#session_edit").click(function () {
      $('#VSave_session_backup_time').text("");
      $('#VSave_session_backup_host').text("");
      $('#VSave_session_backup_port').text("");
      $('#VSave_session_backup_username').text("");
      $('#VSave_session_backup_password').text("");
      $('#VSave_session_backup_path').text("");
      $('#session_backup_time').val($("#session_backup_time_show").text());
      $('#session_backup_enable_yes').css("checked","checked");
     /* $('#session_backup_type').val($("#session_backup_type_show").text());*/
      $('#session_backup_host').val($("#session_backup_host_show").text());
      $('#session_backup_port').val($("#session_backup_port_show").text());
      $('#session_backup_username').val($("#session_backup_username_show").text());
      $('#session_backup_path').val($("#session_backup_path_show").text());
      // $('#session_backup_host').val($(#session_backup_iscompress_show).text());
  });
//单个判断
  $('#session_backup_time').blur(function () {
      var _session_backup_time = $('#session_backup_time').val();
      if (_session_backup_time=="") {
          $('#VSave_session_backup_time').html("请输入备份时间");
      }
  });
  $('#session_backup_time').focus(function () {
      $('#VSave_session_backup_time').html("");
  });
  $('#session_backup_host').blur(function () {
      var _session_backup_host = $('#session_backup_host').val();
      var p1 = /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
      var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
      if (_session_backup_host=="") {
          $('#VSave_session_backup_host').html("请输入IP地址或域名");
      } else if(p1.test(_session_backup_host)){
          $('#VSave_session_backup_host').html("");
      }else if(p3.test(_session_backup_host)){
          $('#VSave_session_backup_host').html("");
      }else {
          $('#VSave_session_backup_host').html("请输入有效的IP地址或域名");
      }
  });
  $('#session_backup_host').focus(function () {
      $('#VSave_session_backup_host').html("");
  });
  $('#session_backup_port').blur(function () {
      var _session_backup_port = $('#session_backup_port').val();
      var p0=/^\d{1,65535}$/;
      if (_session_backup_port=="") {
          $('#VSave_session_backup_port').html("请输入备份端口");
      } else if(!p0.test(_session_backup_port)||_session_backup_port<1||_session_backup_port>65535){
          $('#VSave_session_backup_port').html("请输入1-65535之间的数字");
      }
  });
  $('#session_backup_port').focus(function () {
      $('#VSave_session_backup_port').html("");
  });
  $('#session_backup_username').blur(function () {
	  var reg=/[\u4E00-\u9FFF|。]+/;
      var _session_backup_username = $('#session_backup_username').val();
      if (_session_backup_username=="") {
          $('#VSave_session_backup_username').html("请输入备份账号");
      }else if(reg.test(_session_backup_username)){
    	  $('#VSave_session_backup_username').html("输入格式不正确");
      }
  });
  $('#session_backup_username').focus(function () {
      $('#VSave_session_backup_username').html("");
  });
  $('#session_backup_password').blur(function () {
      var _session_backup_password = $('#session_backup_password').val();
      if (_session_backup_password=="") {
          $('#VSave_session_backup_password').html("请输入备份用户密码");
      }
  });
  $('#session_backup_password').focus(function () {
      $('#VSave_session_backup_password').html("");
  });
  $('#session_backup_path').blur(function () {
      var _session_backup_path = $('#session_backup_path').val();
      var path=/^(http(s)?:\/\/)?(www\.)?([\/a-zA-Z0-9][-a-zA-Z]{0,62})+(\.[a-zA-Z0-9][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
      if (_session_backup_path=="") {
          $('#VSave_session_backup_path').html("请输入备份路径");
      }else if(!path.test(_session_backup_path)){
          $('#VSave_session_backup_path').html("请输入有效的备份路径");
      }
  });
  $('#session_backup_path').focus(function () {
      $('#VSave_session_backup_path').html("");
  });

  //存储
    $('#save_config_disk').click(function() {
        if($('#disk_config_enable_yes')[0].checked==true){
            $('#disk_config_enable_show').text("启用");
            showdisk1();
        }else if($('#disk_config_enable_no')[0].checked==true){
            $('#disk_config_enable_show').text("未启用");
            hidedisk1();
        }
        if($('#disk_write_type').val() == 0){
            $('#disk_write_type_show').text("停止录像审计");
        }else {
            $('#disk_write_type_show').text("覆盖最早文件");
        }
        $.ajax({
            url: "../../configDisksession/editConfigDisksession",
            type: "POST",
            data: {
                id: $('#disk_session_id').val(),
                disk_config_enable: $('#disk_config_enable input[name="sex2"]').val(),
                disk_max_persent: $('#disk_max_persent').find("option:selected").val(),
                disk_write_type: $('#disk_write_type').val(),
                type:1,
            },
            success: function(data) {
                if (data.success) {
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#save1").modal("hide");
                    $('#disk_config_enable_show').text($('#disk_config_enable_yes')[0].checked==true ? '启用': '未启用');
                    $('#disk_max_persent_show').text($('#disk_max_persent').val()+"%");
                    $('#disk_write_type_show').text($('#disk_write_type').val() == 0 ? '停止录像审计': '覆盖最早文件');
                } else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {}
        })
    });
 /*   $("#saveEdit").click(function () {
       /!* $('#session_backup_enable_yes')[0].checked = true;*!/
        $('#disk_max_persent').val( $('#disk_max_persent_show').text());
    });*/
    function showdisk(){
       $("#div3").show();
    }
   function hidedisk(){
      $("#div3").hide();
   }
  function showdisk1(){
      $("#div31").show();
  }
  function hidedisk1(){
      $("#div31").hide();
  }
    //color picker with addon
    $('#save_backup_session').click(function() {
    	var flag=CheckSave();
    	if(flag==false) return false;
    	if($('#session_backup_enable_yes')[0].checked==true){
    	    showsession1();
        }else {
    	    hidesession1();
        }
        $.ajax({
            url: "../../configDisksession/editConfigDisksession",
            type: "POST",
            data: {
                id: $('#backup_session_id').val(),
                session_backup_enable: $('#session_backup_enable_yes')[0].checked ? 1 : 0,
                session_backup_time: $('#session_backup_time').val(),
                session_backup_type: $('#session_backup_type').val(),
                session_backup_host: $('#session_backup_host').val(),
                session_backup_port: $('#session_backup_port').val(),
                session_backup_username: $('#session_backup_username').val(),
                session_backup_password: $('#session_backup_password').val(),
                session_backup_path: $('#session_backup_path').val(),
                session_backup_iscompress: $('#session_backup_iscompress_yes')[0].checked ? 1 : 0,
                type:0,
            },
            success: function(data) {
                if (data.success) {
                    $("#save2").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#session_backup_enable_show').text($('#session_backup_enable_yes')[0].checked ? '启用': '未启用');
                   /* $('#disk_max_persent_show').text($('#disk_max_persent').val() + '%');*/
                    $('#session_backup_type_show').text($('#session_backup_type').val() == 0 ? 'FTP': 'SFTP');
                    $("#session_backup_time_show").text($('#session_backup_time').val());
                    $("#session_backup_host_show").text($('#session_backup_host').val());
                    $("#session_backup_port_show").text($('#session_backup_port').val());
                    $("#session_backup_username_show").text($('#session_backup_username').val());
                    $("#session_backup_path_show").text($('#session_backup_path').val());
                    $('#session_backup_iscompress_show').text($('#session_backup_iscompress_yes')[0].checked ? '是': '否');

                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });
    function showsession(){
      $("#session").show();
  }
  function  hidesession(){
      $("#session").hide();
  }
  function showsession1(){
      $("#session1").show();
  }
  function  hidesession1(){
      $("#session1").hide();
  }
$.ajax({
    "url": "../../configDisksession/listConfigDisksession",
    data: {
        start: 0,
        length: 1
    },
    success: function(data) {
        if (data.success) {
            $('#disk_session_id').val(data.data[0].id);
           /* $('#disk_config_enable_yes')[0].checked = data.data[0].disk_config_enable == 1 ? true: false;
            $('#disk_config_enable_no')[0].checked = data.data[0].disk_config_enable == 0 ? true: false;*/
            if(data.data[0].disk_config_enable == 1){
                $('#disk_config_enable_yes')[0].checked=true;
            }else if(data.data[0].disk_config_enable == 0){
                $('#disk_config_enable_no')[0].checked=true;
            }
             $('#disk_max_persent').val(data.data[0].disk_max_persent);
            $('#disk_write_type').val(data.data[0].disk_write_type);
            $('#backup_session_id').val(data.data[0].id);
           /* $('#session_backup_enable_yes')[0].checked = data.data[0].session_backup_enable == 1 ? true: false;
            $('#session_backup_enable_no')[0].checked = data.data[0].session_backup_enable == 0 ? true: false;*/
            $('#session_backup_time').val(data.data[0].session_backup_time);
            $('#session_backup_type').val(data.data[0].session_backup_type);
            $('#session_backup_host').val(data.data[0].session_backup_host);
            $('#session_backup_port').val(data.data[0].session_backup_port);
            $('#session_backup_username').val(data.data[0].session_backup_username);
            $('#session_backup_path').val(data.data[0].session_backup_path);
            $('#session_backup_iscompress_yes')[0].checked = data.data[0].session_backup_iscompress == 1 ? true: false;
            $('#session_backup_iscompress_no')[0].checked = data.data[0].session_backup_iscompress == 0 ? true: false;
            $('#disk_config_enable_show').text(data.data[0].disk_config_enable == 1 ? '启用': '未启用');
            $('#disk_max_persent_show').text(data.data[0].disk_max_persent + '%');
            $('#disk_write_type_show').text(data.data[0].disk_write_type == 0 ? '停止录像审计': '覆盖最早文件');
            $('#session_backup_enable_show').text(data.data[0].session_backup_enable == 1 ? '启用': '未启用');
            $('#session_backup_type_show').text(data.data[0].session_backup_type == 0 ? 'FTP': 'SFTP');
            $('#session_backup_time_show').text(data.data[0].session_backup_time);
            $('#session_backup_host_show').text(data.data[0].session_backup_host);
            $('#session_backup_port_show').text(data.data[0].session_backup_port);
            $('#session_backup_username_show').text(data.data[0].session_backup_username);
            $('#session_backup_path_show').text(data.data[0].session_backup_path);
            $('#session_backup_iscompress_show').text(data.data[0].session_backup_iscompress == 1 ? '是': '否');
        } else {}
    },
    error: function() {

}
});
