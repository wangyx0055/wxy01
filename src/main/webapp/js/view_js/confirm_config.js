$(function() {
    $('#ldapadtable').DataTable({
        'paging': true,
        /*是否允许翻页 */
        'lengthChange': false,
         Pagination:false,
        'searching': false,
        "bPaginate": false,
        'dom': 't<"bottom"lifp<"clear">>',
        /*是否允许检索  */
        'ordering': false,
        "bInfo" : false, //显示表格条数信息
        'autoWidth': false,
        "serverSide": true,
        "bJQueryUI" : false,//
        /* 服务器端处理方式 */
        "ajax": {
            "url": "../../configLdapAd/listConfigLdapAd",
            "data": function(d) {
                for (var key in d) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                        delete d[key];
                    }
                }
            }
        },
        "columns": [
        /*  { "data": "id" ,"render": function(data,type,row,meta){
                        return '<input type="checkbox" name="chk[]" value='+data+'>';
                    }},*/
        {
            "data": "ip"
        },
        {
            "data": "basedn"
        },
        {
            "data": "domain"
        },
        {
            "data": "administrator"
        },
        {
            "data": "id",
            "render": function(data, type, row, meta) {
                return '<a   data-toggle="modal" data-row="' + meta.row + '" data-target="#ad-edit-group2" class="newcss1" style="cursor: pointer;margin-left:20px;">编辑</a>' +
                /*  '<button type="button" class="btn btn-default pull-left" data-toggle="modal" data-row="'+meta.row+'" data-target="#ad_edit_group" style="right;width:40px;height: 22px;line-height: 2px;margin-left: 15px;font-size: 12px"><span style="margin-left: -5px">编辑</span></button>'+ */
                '<a  onclick="asyncUser(\'' + data + '\');return false;" class="newcss1" style="cursor: pointer;margin-left:20px;">同步账号</a>' + '<a     data-toggle="modal" data-row="' + meta.row + '" class="newcss2" data-target="#modal-delldapad"  style="cursor: pointer;margin-left:20px;">删除</a>';

            }
        }]
    });

    /* 管理 */
    $('#ad-edit-group2').on('show.bs.modal',
    function(event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        i = button.data('row');
        if (i == undefined){
            $('#ad-edit-group2 .modal-title').text('新建LDAP/AD认证配置');
        } else {
            $('#ad-edit-group2 .modal-title').text('编辑LDAP/AD认证配置');
            $('#ad_edit_id').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].id);
            $('#ad_edit_ip1').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].ip);
            $('#ad_edit_port').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].port);
            $('#ad_edit_basedn').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].basedn);
            $('#ad_edit_domain').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].domain);
            $('#ad_edit_type').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].type); //
            $('#ad_edit_status').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].status);
            $('#ad_edit_username').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].username);
            $('#ad_edit_administrator').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].administrator);
            $('#ad_edit_password').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].password);
            $('#ad_edit_filter_department').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].filter_department);
            $('#ad_edit_filter_username').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].filter_username);
            $('#ad_edit_filter_loginname').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].filter_loginname);
            $('#ad_edit_realname').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].realname);
            $('#ad_edit_mobile').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].mobile);
            $('#ad_edit_async').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].async);
            $('#ad_edit_cover').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].cover);
            $('#ad_edit_email').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].email);
            $('#ad_edit_department_id').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].department_id);
            $("#Vip").text("");
            $("#Vport").text("");
            $("#Vadministrator").text("");
            $("#Vpassword").text("");
            $("#Vdomain").text("");
            $("#Vbasedn").text("");
        }
    });
    /* 删除 */
    $('#modal-delldapad').on('show.bs.modal',
    function(event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#delid').val($('#ldapadtable').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    /* 第二部确定 */
  function editAD(){
        var url = "../../configLdapAd/editConfigLdapAd";
        if ($('#ad_edit_id').val() == "") {
            url = "../../configLdapAd/addConfigLdapAd"
        }
        var flag = CheckValIsNull();
        if (flag == false) return false;
        $.ajax({
            url: url,
            type: "POST",
            data: {
                id: $('#ad_edit_id').val(),
                ip: $('#ad_edit_ip1').val(),
                /* type:$('#ad_edit_type').val(), */
                type: $('#ad_edit_type input[name="typerid1"]').val(),
                /* ssl:$('#ad_edit_ssl').val(), */
                ssl: $('#ad_edit_ssl input[name="sslrid1"]').val(),
                port: $('#ad_edit_port').val(),
                status: $('#ad_edit_status').val(),
                username: $('#ad_edit_username').val(),
                realname: $('#ad_edit_realname').val(),
                domain: $('#ad_edit_domain').val(),
                basedn: $('#ad_edit_basedn').val(),
                administrator: $('#ad_edit_administrator').val(),
                password: $('#ad_edit_password').val(),
                filter_department: $('#ad_edit_filter_department').val(),
                filter_username: $('#ad_edit_filter_username').val(),
                filter_loginname: $('#ad_edit_filter_loginname').val(),
                email: $('#ad_edit_email').val(),
                mobile: $('#ad_edit_mobile').val(),
                async: $('#ad_edit_async').val(),
                //department_id:$('#ad_edit_department_id').val(),
                cover: $('#ad_edit_cover').val(),
                add_department: $('#add_edit_ad_department').val(),
            },
            success: function(data) {
                if (data.success) {
                    if ($('#ad_edit_id').val()==""){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                    $("#ad_edit_p").modal("hide");
                    $("#ad-edit-group2").modal("hide");
                }else {
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#ad_edit_p").modal("hide");
                    $("#ad-edit-group2").modal("hide");
                    }
                    reload();
                }else {
                   /* if (data.msg) {
                        for (k in data.msg) {
                            $('#V' + k).html(data.msg[k]);
                        }
                    }*/
                    if ($('#ad_edit_id').val()==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("新建失败!");
                    $("#modal-danger").modal();
                }else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                    }
                }
            },
            error: function() {

}
        })
    };
  $("#ad_edit_button").click(function () {
      editAD();
  });
  $("#ad_edit_button1").click(function () {
        editAD();
   });
//AD删除
    $('#ad_del_button').click(function() {
        $.ajax({
            url: "../../configLdapAd/delConfigLdapAd",
            type: "POST",
            data: {
                ids: new Array($('#delid').val())
            },
            success: function(data) {
                if (data.success) {
                    reload();
                    $("#modal-delldapad").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function() {

}
        })
    });
});
//RADIUS认证配置
$.ajax({
    "url": "../../configRadius/listConfigRadius",
    data: {
        start: 0,
        length: 1
    },
    success: function(data) {
        if (data.success) {
            $('#radius_edit_id').val(data.data[0].id);
            $('#radius_ip1').html(data.data[0].ip);
            $('#radius_timeout1').html(data.data[0].timeout);
            if (data.data[0].status == 0) {
                $('#radius_status input[name="radiusone"]').get(0).checked = true;
                $('#radius_status1').html("开启");

            } else {
                $('#radius_status input[name="radiusone"]').get(1).checked = true;
                $('#radius_status1').html("关闭");
            }
            $('#radius_port1').html(data.data[0].port);
            if (data.data[0].protocol == 0) {
                $('#radius_protocol input[name="radiusone1"]').get(0).checked = true;
                $('#radius_protocol1').html("PAP");
            } else {
                $('#radius_protocol input[name="radiusone1"]').get(1).checked = true;
                $('#radius_protocol1').html("CHAP");
            }
            $('#radius_secret_key').val(data.data[0].secret_key);
        } else {
          /*  $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text("操作失败!");
            $("#modal-danger").modal();*/
        }
    },
    error: function() {

    }
});
//RADIUS认证配置1
$(function() {
    $('#radius_button1').click(function(){
        var flag = CheckRadius();
        if (flag == false) return false;
        if($('#radius_status input[name="radiusone"]:checked').val() == 0){
            showRadius1();
        }else if($('#radius_status input[name="radiusone"]:checked').val() == 1){
            hideRadius1();
        }
        $.ajax({
            url: "../../configRadius/editConfigRadius",
            type: "POST",
            data: {
                id: $("#radius_edit_id").val(),
                ip: $('#radius_ip').val(),
                status: $('#radius_status input[name="radiusone"]').val(),
                port: $('#radius_port').val(),
                protocol: $('#radius_protocol input[name="radiusone1"]').val(),
                secret_key: $('#radius_secret_key').val(),
                timeout: $('#radius_timeout').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#radius_button").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#radius_ip1').text($('#radius_ip').val());
                    $('#radius_status1').text($('#radius_status input[name="radiusone"]:checked').val() == 0 ? '开启': '关闭');
                    $('#radius_port1').text($('#radius_port').val());
                    $('#radius_protocol1').text($('#radius_protocol input[name="radiusone1"]:checked').val() == 0 ? 'PAP': 'CHAP');
                    $('#radius_timeout1').text($('#radius_timeout').val());
                    $("#VRadius_ip").text("");
                    $("#VRadius_port").text("");
                    $("#VRadius_secret_key").text("");
                    $("#VRadius_timeout").text("");
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#Radius_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function() {}
        })
    });
//测试
/*    $('#connectTest').click(function() {
        $.ajax({
            url: "../../configRadius/connectTest",
            type: "POST",
            data: {},
            success: function(data) {
                if (data.success) {
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                } else {
                    $("#modal-success .modal-title").text('失败');
                    $("#modal-success .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error: function() {}
        })
    });*/
});
   function hideRadius(){
        $("#radiusid").hide();
    };
  function showRadius(){
        $("#radiusid").show();
    };
/*if($('#radius_status input[name="radiusone"]:checked').val() == 1){
    $("#radiusid").hide();
}else if($('#radius_status input[name="radiusone"]:checked').val() == 0){
    $("#radiusid").show();
}*/
  function showRadius1(){
        $("#radiusid1").show();
    }
    function hideRadius1(){
        $("#radiusid1").hide();
    }
//生物认证配置
$(function() {
    $('#biont_button').click(function() {
        var flag = CheckRecv();
        if (flag === false) return false;
        $.ajax({
            url: "../../configFinger/editConfigFinger",
            type: "POST",
            data: {
                id: $('#biont_id').val(),
                url: $('#biont_url').val(),
				status:$('#finger_status input[name="fingerstatus"]').val()
            },
            success: function(data) {
                if (data.success) {
                    $("#biont_g").modal("hide");
                    $('#finger_status').text($('#finger_status input[name="fingerstatus"]').val() == 1 ? '开启': '关闭');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $('#biont_url1').text($('#biont_url').val());
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#Finger_' + k).html(data.msg[k]);
                        }
                    }
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function() {}
        })
    });
    //color
})

$.ajax({
    url: "../../configFinger/listConfigFinger",
    type: "POST",
    data: {
        start: 0,
        length: 1
    },
    success: function(data) {
        if (data.success) {
            $('#biont_id').val(data.data[0].id);
            $('#biont_url1').html(data.data[0].url);
			if (data.data[0].status == 1) {
                $('#finger_status input[name="fingerstatus"]').get(0).checked = true;
                $('#finger_status').html("开启");

            } else {
                $('#finger_status input[name="fingerstatus"]').get(1).checked = true;
                $('#finger_status').html("关闭");
            }
        } else {
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text("编辑失败!");
            $("#modal-danger").modal();
        }
    },
    error: function() {

}
});

function asyncUser(id) {
    $("#modal-upload .modal-title").text('状态');
    $("#modal-upload .modal-body").text('正在同步...');
    $("#modal-upload").modal();
    $.ajax({
        url: "../../configLdapAd/asyncUser",
        type: "POST",
        data: {
            ids: id
        },
        success: function(data) {
            if (data.success) {
                reload();
                $("#modal-upload").modal("hide");
                $("#modal-delldapad").modal("hide");
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('同步成功!');
                $("#modal-success").modal();
            } else {
                if(data.error.length!=0){
                    $("#modal-upload").modal("hide");
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.error);
                    $("#modal-danger").modal();
                }else{
                    $("#modal-upload").modal("hide");
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("同步失败!");
                    $("#modal-danger").modal();
                }
            }
        },
        error: function() {
        }
    })
}
function reload() {
    $('#ldapadtable').DataTable().ajax.reload();
}
/*function loser() {
    $('#ldapadtable').DataTable().ajax.reload();
}*/

function shownumber(){
    document.getElementById("ad_edit_id1").value = document.getElementById("ad_edit_id").value;
    $('#ad_edit_ssl input[name="sslrid1"]').get(0).checked = true;
    $('#sslrid').click(function () {
        document.getElementById("ad_edit_port").value = '389';
    });
    $('#sslrid2').click(function () {
        document.getElementById("ad_edit_port").value = '636';
    })
    document.getElementById("ad_edit_port").value = '389';
    $('#ad_edit_id').val();
    $('#ad_edit_ip1').val('');
    $('#ad_edit_basedn').val('');
    $('#ad_edit_async option:first').attr("selected","selected");
    $('#ad_edit_domain').val('');
    $('#ad_edit_type').val('');
    $('#ad_edit_status').val('');
    $('#ad_edit_username').val('');
    $('#ad_edit_administrator').val('');
    $('#ad_edit_password').val('');
    $('#ad_edit_filter_department').val('');
    $('#ad_edit_filter_username').val('');
    $('#ad_edit_filter_loginname').val('');
    $('#ad_edit_realname').val('');
    $('#ad_edit_mobile').val('');
    $('#ad_edit_cover').val('');
    $('#ad_edit_email').val('');
    $('#ad_edit_department_id').val('');
    $("#Vip").text("");
    $("#Vport").text("");
    $("#Vadministrator").text("");
    $("#Vpassword").text("");
    $("#Vdomain").text("");
    $("#Vbasedn").text("");
    /* $("#ad_edit_ip1").val(document.getElementById("ad_edit_ip").innerHTML);

      $("#ad_edit_domain").val(document.getElementById("ad_edit_domain1").innerHTML);
      $("#ad_edit_port").val(document.getElementById("ad_edit_port1").innerHTML);*/

}

function shownumber4() {
    document.getElementById("biont_id1").value = document.getElementById("biont_id").value;
    $("#biont_url").val(document.getElementById("biont_url1").innerHTML);
    $("#Finger_url").text("");
}

function shownumber5() {
    document.getElementById("radius_edit_id1").value = document.getElementById("radius_edit_id").value;
    $("#radius_ip").val(document.getElementById("radius_ip1").innerHTML);
    $("#radius_port").val(document.getElementById("radius_port1").innerHTML);
    $("#radius_timeout").val(document.getElementById("radius_timeout1").innerHTML);
    $("#VRadius_ip").text("");
    $("#VRadius_port").text("");
    $("#VRadius_secret_key").text("");
    $("#VRadius_timeout").text("");

}

function CheckRadius(){
    var flag = true;
/*    var p1 = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/g;*/
    var p2 = /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    var ad_radius_ip = $('#radius_ip').val();
    var p0= /^[0-9]{1,65535}$/;
   /* var p4=/^[0-9]{5,30}$/;*/
    if(ad_radius_ip==""){
        flag = false;
        $('#VRadius_ip').html("请输入IP地址或域名");
    }else if (p3.test(ad_radius_ip)){
        flag = true;
        $('#VRadius_ip').html("");
    }else if(p2.test(ad_radius_ip)){
        $('#VRadius_ip').html("");
        flag = true;
    }else {
        $('#VRadius_ip').html("请输入有效的IP地址或域名");
        flag=false;
    }
    var ad_radius_port = $('#radius_port').val();
    if (ad_radius_port== "") {
        $('#VRadius_port').html("请输入端口");
        flag = false;
    }else if(ad_radius_port<1||ad_radius_port>65535||!p0.test(ad_radius_port)){
        $('#VRadius_port').html("请输入1-65535之间的有效数字");
        flag = false;
    }
    var ad_radius_secret_key = $('#radius_secret_key').val();
    if (ad_radius_secret_key == "") {
        flag = false;
        $('#VRadius_secret_key').html("请输入共享密钥");
    }
    var ad_radius_timeout = $('#radius_timeout').val();
    if (ad_radius_timeout=="") {
        flag = false;
        $('#VRadius_timeout').html("请输入时间");
    }else if(ad_radius_timeout>30||ad_radius_timeout<5||isNaN(ad_radius_timeout)){
        $('#VRadius_timeout').html("时间不在有效值范围");
        flag = false;
    }
    return flag;
}
//生物
function CheckRecv(){
    var flag =true;
    var ad_url=$("#biont_url").val();
/*    var p2 = /^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$/;*/
      var p2 = /^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
      var p3=/^(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?$/;
    if(ad_url==""){
        $('#Finger_url').text("请输入地址或域名");
        flag = false;
    }else if (p2.test(ad_url)) {
        $('#Finger_url').text("");
        flag = true;
    }else if(p3.test(ad_url)){
        $('#Finger_url').text("");
        flag = true;
    }else {
        $('#Finger_url').text("请输入正确的地址或域名");
        flag=false;
    }
    return flag;
}
$('#biont_url').focus(function () {
    $('#Finger_url').text("");
});

//下一步
function CheckValIsNull() {
    var flag =true;
    var p2 =/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    var p1=/^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$/;
    var add_add_ip = $('#ad_edit_ip1').val();
    var port2= /^[0-9]{1,65535}$/;
     if(add_add_ip==""){
        $('#Vip').text("请输入IP地址或域名");
        flag = false;
    }else if (p2.test(add_add_ip)) {
        $('#Vip').text("");
        flag = true;
    }else if(p3.test(add_add_ip)){
         $('#Vip').text("");
         flag = true;
     }else {
         $('#Vip').text("请输入有效的IP地址或域名");
         flag=false
     }

    var add_ad_port = $('#ad_edit_port').val();
    if (add_ad_port=="") {
        $('#Vport').text("请输入端口");
        flag = false;
    }else if(add_ad_port<1||add_ad_port>65535||!port2.test(add_ad_port)){
        $('#Vport').text("请输入1~65535之间的数字");
        flag = false;
    }
    var add_ad_adminstrator = $('#ad_edit_administrator').val();
    if (add_ad_adminstrator == 0) {
        $('#Vadministrator').text("请输入管理员DN");
        flag = false;
    }
    var add_ad_password = $('#ad_edit_password').val();
    if (add_ad_password == "") {
        $('#Vpassword').text("请输入密码");
        flag = false;
    }
    var add_ad_domain = $('#ad_edit_domain').val();
     if(add_ad_domain==""){
        $('#Vdomain').text("请输入域名");
        flag = false;
    } else if (!p1.test(add_ad_domain)){
        $('#Vdomain').text("请输入有效的域名");
        flag = false;
    }
    var add_ad_basedn = $('#ad_edit_basedn').val();
    if (add_ad_basedn == "") {
        $('#Vbasedn').text("请输入目录DN");
        flag = false;
    }
   var ad_edit_filter_department=$("#ad_edit_filter_department").val();
   var ad_edit_filter_username=$("#ad_edit_filter_username").val();
   var ad_edit_filter_loginname=$("#ad_edit_filter_loginname").val();
   var ad_edit_email=$("#ad_edit_email").val();
   var ad_edit_mobile=$("#ad_edit_mobile").val();
    var pa=/^\S{0,32}$/;
    var pl=/^\S{0,1024}$/;
    var pe=/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var pm=/^[1][0-9]{10}$/;
    if(ad_edit_filter_department!==""&&!pa.test(ad_edit_filter_department)){
        $('#Vad_edit_filter_department').text("输入格式不正确");
        flag = false;
    }
    if(ad_edit_filter_username!==""&&!pa.test(ad_edit_filter_username)){
        $('#Vad_edit_filter_username').text("用户名格式不正确");
        flag = false;
    }
    if(ad_edit_email!==""&&!pe.test(ad_edit_email)){
        $('#Vad_edit_email').text("邮箱格式不正确");
        flag = false;
    }
    if(ad_edit_filter_loginname!==""&&!pl.test(ad_edit_filter_loginname)){
        $('#Vad_edit_filter_loginname').text("登录名格式不正确");
        flag = false;
    }
    if(ad_edit_mobile!==""&&!pm.test(ad_edit_mobile)){
        $('#Vad_edit_mobile').text("手机号格式不正确");
        flag = false;
    }

/*    if (flag) {
        $("#ad-edit-group2").modal("hide");
        $("#ad_edit_p").modal("show");
    }*/
    return flag;
}
//单个判断LDAP/AD认证配置

$('#ad_edit_ip1').blur(function () {
    var add_add_ip = $('#ad_edit_ip1').val();
    var p2=/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
   if(add_add_ip==""){
        $('#Vip').text("请输入IP地址或域名");
    }else if(p2.test(add_add_ip)) {
        $('#Vip').text("");
    }else if(p3.test(add_add_ip)){
       $('#Vip').text("");
   }else {
       $('#Vip').text("请输入有效的IP地址或域名");
   }
});
$('#ad_edit_ip1').focus(function () {
    $('#Vip').text("");
});

$('#ad_edit_port').blur(function () {
    var add_ad_port = $('#ad_edit_port').val();
    var port= /^[0-9]{1,65535}$/;
    if (add_ad_port== "") {
        $('#Vport').html("请输入端口");
    }else if(add_ad_port<1||add_ad_port>65535||!port.test(add_ad_port)){
        $('#Vport').html("请输入1-65535之间的有效数字");
    }
});
$('#ad_edit_port').focus(function () {
    $('#Vport').text("");
});
$('#ad_edit_administrator').blur(function () {
    var ad_edit_administrator = $('#ad_edit_administrator').val();
    if (ad_edit_administrator == "") {
        flag = false;
        $('#Vpassword').text("请输入管理员DN");
    }
});
$('#ad_edit_administrator').focus(function () {
    $('#Vadministrator').text("");
});

$('#ad_edit_password').blur(function () {
    var add_ad_password = $('#ad_edit_password').val();
    if (add_ad_password == "") {
        flag = false;
        $('#Vpassword').text("请输入密码");
    }
});
$('#ad_edit_password').focus(function () {
    $('#Vpassword').text("");
});

$("#ad_edit_domain").blur(function () {
    var add_ad_domain = $('#ad_edit_domain').val();
    var p2=/^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$/;
    if(add_ad_domain==""){
        $('#Vdomain').text("请输入域名");
    }else if (!p2.test(add_ad_domain)){
        $('#Vdomain').text("请输入有效的域名");
    }
});
$('#ad_edit_domain').focus(function () {
    $('#Vdomain').text("");
});

$("#ad_edit_basedn").blur(function (){
      var add_ad_basedn = $('#ad_edit_basedn').val();
      if (add_ad_basedn == "") {
          $('#Vbasedn').text("请输入目录DN");
      }
  })
$('#ad_edit_basedn').focus(function () {
    $('#Vbasedn').text("");
});

$("#ad_edit_filter_department").blur(function () {
    var ad_edit_filter_department=$("#ad_edit_filter_department").val();
    var pa=/^\S{0,32}$/;
    if(ad_edit_filter_department!==""&&!pa.test(ad_edit_filter_department)){
        $('#Vad_edit_filter_department').text("输入格式不正确");
    }
});
$("#ad_edit_filter_department").focus(function () {
    $('#Vad_edit_filter_department').text("");
});

$("#ad_edit_filter_username").blur(function () {
    var ad_edit_filter_username=$("#ad_edit_filter_username").val();
    var pa=/^\S{0,32}$/;
    if(ad_edit_filter_username!==""&&!pa.test(ad_edit_filter_username)){
        $('#Vad_edit_filter_username').text("用户名格式不正确");
    }
});
$("#ad_edit_filter_username").focus(function () {
    $('#Vad_edit_filter_username').text("");
});

$("#ad_edit_filter_loginname").blur(function () {
    var ad_edit_filter_loginname=$("#ad_edit_filter_loginname").val();
    var pl=/^\S{0,1024}$/;
    if(ad_edit_filter_loginname!==""&&!pl.test(ad_edit_filter_loginname)){
        $('#Vad_edit_filter_loginname').text("登录名格式不正确");
    }
});
$("#ad_edit_filter_loginname").focus(function () {
    $('#Vad_edit_filter_loginname').text("");
});

$("#ad_edit_email").blur(function () {
    var ad_edit_email=$("#ad_edit_email").val();
    var pe=/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    if(ad_edit_email!==""&&!pe.test(ad_edit_email)){
        $('#Vad_edit_email').text("邮箱格式不正确");
    }
});
$("#ad_edit_email").focus(function () {
    $('#Vad_edit_email').text("");
});
$("#ad_edit_mobile").blur(function () {
    var pm=/^[1][0-9]{10}$/;
    var ad_edit_mobile=$("#ad_edit_mobile").val();
    if(ad_edit_mobile!==""&&!pm.test(ad_edit_mobile)){
        $('#Vad_edit_mobile').text("手机号格式不正确");
    }
});
$("#ad_edit_mobile").focus(function () {
    $('#Vad_edit_mobile').text("");
});
//Radius单个
$("#radius_ip").blur(function (){
    var radius_ip = $('#radius_ip').val();
    var p2=/^(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
    var p3=/^(http(s)?:\/\/)?(www\.)?[a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+(:\d+)*(\/\w+\.\w+)*$/;
    if (radius_ip == "") {
        $('#VRadius_ip').text("请输入IP地址或域名");
    }else if (p3.test(radius_ip)) {
        $('#VRadius_ip').html("");
    }else if(p2.test(radius_ip)){
        $('#VRadius_ip').html("");
    }else {
        $('#VRadius_ip').html("请输入有效的IP地址或域名");
    }
});
$('#radius_ip').focus(function () {
    $('#VRadius_ip').text("");
});

$("#radius_port").blur(function (){
    var radius_port = $('#radius_port').val();
    var  radius_test= /^[0-9]{1,65535}$/;
    if (radius_port == "") {
        $('#VRadius_port').text("请输入端口");
    }else if(radius_port<1||radius_port>65535||!radius_test.test(radius_port)){
        $('#VRadius_port').html("请输入1-65535之间的有效数字");
    }
})
$('#radius_port').focus(function () {
    $('#VRadius_port').text("");
})

$("#radius_secret_key").blur(function (){
    var radius_secret_key = $('#radius_secret_key').val();
    if (radius_secret_key == "") {
        $('#VRadius_secret_key').text(" 请输入共享密钥");
    }
})
$('#radius_secret_key').focus(function () {
    $('#VRadius_secret_key').text("");
})
$("#radius_timeout").blur(function (){
    var radius_timeout = $('#radius_timeout').val();
   /* var p1=/^\d{5,30}$/;*/
    if (radius_timeout == "") {
        $('#VRadius_timeout').text("请输入时间");
    }else if(radius_timeout<5||radius_timeout>30||isNaN(radius_timeout)){
        $('#VRadius_timeout').text("时间不在有效值范围");
    }
})
$('#radius_timeout').focus(function () {
    $('#VRadius_timeout').text("");
})