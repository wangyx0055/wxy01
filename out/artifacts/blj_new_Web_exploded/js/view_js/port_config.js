$(function() {

    $('#operateporteditbtn').click(function() {
        document.getElementById("operate_id1").value = document.getElementById("operate_id").value;
        $("#operate_ssh_sftp").val(document.getElementById("operate_ssh_sftp1").innerHTML);
      /*  $("#operate_rdp").val(document.getElementById("operate_rdp1").innerHTML);*/
        $("#operate_ftp").val(document.getElementById("operate_ftp1").innerHTML);
        $("#VPort_ssh_sftp").text("");
        $("#VPort_rdp").text("");
        $("#VPort_ftp").text("");
    });
    $('#webporteditbtn').click(function() {
        document.getElementById("web_id1").value = document.getElementById("web_id").value;
        $("#web_web").val(document.getElementById("web_web1").innerHTML);
        $("#VPort_web_port").text("");
    });
    $('#sshporteditbtn').click(function() {
        document.getElementById("ssh_id1").value = document.getElementById("ssh_id").value;
        $("#ssh_control_port").val(document.getElementById("ssh_control_port1").innerHTML);
        $("#VPort_control_port").text("");
    });
    function CheckDevOps() {
        var flag = true;
        var p2=/^\d{1,65535}$/;
        var ad_operate_ssh_sftp = $('#operate_ssh_sftp').val();
        if(ad_operate_ssh_sftp==""){
            $('#VPort_ssh_sftp').html("请输入端口号");
            flag=false;
        }else if (ad_operate_ssh_sftp > 65535 || ad_operate_ssh_sftp < 1||!p2.test(ad_operate_ssh_sftp)) {
            $('#VPort_ssh_sftp').html("请输入1-65535之间的数字");
            flag = false;
        }
        /*var ad_operate_rdp = $('#operate_rdp').val();
        if(ad_operate_rdp==""){
            $('#VPort_rdp').html("请输入端口号");
            flag = false;
        } else if (ad_operate_rdp > 65535 || ad_operate_rdp <1||!p2.test(ad_operate_rdp)) {
            $('#VPort_rdp').html("请输入1-65535之间的数字");
            flag = false;
        }*/
        var ad_operate_ftp = $('#operate_ftp').val();
        if(ad_operate_ftp == ""){
            $('#VPort_ftp').html("请输入端口号");
            flag = false;
        }else if (ad_operate_ftp > 65535 || ad_operate_ftp <1||!p2.test(ad_operate_ftp)) {
            $('#VPort_ftp').html("请输入1-65535之间的数字");
            flag = false;
        }
        return flag;
    }
    function CheckWc() {
        var flag = true;
        var p1=/^\d{1,65535}$/;
        var ad_web_web = $('#web_web').val();
        if(ad_web_web ==""){
            $('#VPort_web_port').html("请输入端口号");
        }else if (ad_web_web > 65535 || ad_web_web <1||!p1.test(ad_web_web)) {
            $('#VPort_web_port').html("请输入1-65535之间的数字");
            flag = false;
        }
        return flag;
    }
    function CheckSsh() {
        var flag = true;
        var p1=/^\d{1,65535}$/;
        var ad_ssh_control_port = $('#ssh_control_port').val();
        if(ad_ssh_control_port==""){
            $('#VPort_control_port').html("请输入端口号");
            flag = false;
        }else if (ad_ssh_control_port > 65535 || ad_ssh_control_port<1||!p1.test(ad_ssh_control_port)) {
            $('#VPort_control_port').html("请输入1-65535之间的数字");
            flag = false;
        }
        return flag;
    }

    $('#oprate_button1').click(function() {
        var flag = CheckDevOps();
        if (flag == false) return false;
        $.ajax({
            url: "../../configPort/editConfigPort",
            type: "POST",
            data: {
                id: $('#operate_id').val(),
                ssh_sftp: $('#operate_ssh_sftp').val(),
                /*rdp: $('#operate_rdp').val(),*/
                ftp: $('#operate_ftp').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#oprate_button").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#operate_ssh_sftp1").text($('#operate_ssh_sftp').val());
                    $("#operate_rdp1").text($('#operate_rdp').val());
                    $("#operate_ftp1").text($('#operate_ftp').val());
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPort_' + k).html(data.msg[k]);
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
    $('#web_edit_button').click(function() {
        var flag = CheckWc();
        if (flag == false) return false;
        $.ajax({
            url: "../../configPort/editConfigPort",
            type: "POST",
            data: {
                id: $('#web_id').val(),
                web: $('#web_web').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#003").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#web_web1").text($('#web_web').val());
                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPort_' + k).html(data.msg[k]);
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

    $('#ssh_edit_button').click(function() {
        var flag = CheckSsh();
        if (flag == false) return false;
        $.ajax({
            url: "../../configPort/editConfigPort",
            type: "POST",
            data: {
                id: $('#ssh_id').val(),
                control_port: $('#ssh_control_port').val(),
            },
            success: function(data) {
                if (data.success) {
                    $("#005").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    $("#ssh_control_port1").text($('#ssh_control_port').val());

                } else {
                    if (data.msg) {
                        for (k in data.msg) {
                            $('#VPort_' + k).html(data.msg[k]);
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

    $.ajax({
        "url": "../../configPort/listConfigPort",
        data: {
            start: 0,
            length: 1
        },
        success: function(data) {
            if (data.success) {
                /* var id = data.data[0].id; */
                $('#operate_id').val(data.data[0].id);
                $('#web_id').val(data.data[0].id);
                $('#ssh_id').val(data.data[0].id);
                $('#operate_ssh_sftp1').html(data.data[0].ssh_sftp);
                $('#operate_rdp1').html(data.data[0].rdp);
                $('#operate_ftp1').html(data.data[0].ftp);
                $('#web_web1').html(data.data[0].web);
                $('#ssh_control_port1').html(data.data[0].control_port);
            }
        },
        error: function() {

}
    });
});
//单个
$("#operate_ssh_sftp").blur(function () {
    var operate_ssh_sftp=$("#operate_ssh_sftp").val();
    var p1=/^\d{1,65535}$/;
    if(operate_ssh_sftp==""){
        $('#VPort_ssh_sftp').html("请输入端口号");
    } else if (operate_ssh_sftp > 65535 || operate_ssh_sftp < 1||!p1.test(operate_ssh_sftp)) {
        $('#VPort_ssh_sftp').html("请输入1-65535之间的数字");
    }
});
$("#operate_ssh_sftp").focus(function () {
    $('#VPort_ssh_sftp').html("");
});

/*
$("#operate_rdp").blur(function () {
    var operate_rdp = $('#operate_rdp').val();
    var p1=/^\d{1,65535}$/;
    if(operate_rdp==""){
        $('#VPort_rdp').html("请输入端口号");
    } else if (operate_rdp > 65535 || operate_rdp <1||!p1.test(operate_rdp)) {
        $('#VPort_rdp').html("请输入1-65535之间的数字");
    }
});
$("#operate_rdp").focus(function () {
    $('#VPort_rdp').html("");
});
*/

$("#operate_ftp").blur(function () {
    var ad_operate_ftp = $('#operate_ftp').val();
    var p1=/^\d{1,65535}$/;
    if(ad_operate_ftp == ""){
        $('#VPort_ftp').html("请输入端口号");
    }else if (ad_operate_ftp > 65535 || ad_operate_ftp <1||!p1.test(ad_operate_ftp)) {
        $('#VPort_ftp').html("请输入1-65535之间的数字");
    }
});
$("#operate_ftp").focus(function () {
    $('#VPort_ftp').html("");
});

//2
$("#web_web").blur(function () {
    var web_web = $('#web_web').val();
    var p1=/^\d{1,65535}$/;
    if(web_web == ""){
        $('#VPort_web_port').html("请输入端口号");
    }else if (web_web > 65535 || web_web <1||!p1.test(web_web)) {
        $('#VPort_web_port').html("请输入1-65535之间的数字");
    }
});
$("#web_web").focus(function () {
    $('#VPort_web_port').html("");
});
//3
$("#ssh_control_port").blur(function () {
    var ssh_control_port = $('#ssh_control_port').val();
    var p1=/^\d{1,65535}$/;
    if(ssh_control_port == ""){
        $('#VPort_control_port').html("请输入端口号");
    }else if (ssh_control_port > 65535 || ssh_control_port <1||!p1.test(ssh_control_port)) {
        $('#VPort_control_port').html("请输入1-65535之间的数字");
    }
});
$("#ssh_control_port").focus(function () {
    $('#VPort_control_port').html("");
});