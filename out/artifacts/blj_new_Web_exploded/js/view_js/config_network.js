function ifaceStatic(v){
    $('#ipv'+v+'addr')[0].disabled	= false;
    $('#ipv'+v+'gateway')[0].disabled = false;
    if(v==4){
        $('#ipv'+v+'netmask')[0].disabled = false;
    }
}
function ifaceDhcp(v){
    $('#ipv'+v+'addr')[0].disabled	= true;
    $('#ipv'+v+'gateway')[0].disabled	= true;
    if(v==4){
        $('#ipv'+v+'netmask')[0].disabled	= true;
        $('#Vipv'+v+'netmask').text("");
        $('#ipv'+v+'netmask').val("");
    }
    $('#Vipv'+v+'addr').text("");
    $('#ipv'+v+'addr').val("");
    $('#Vipv'+v+'gateway').text("");
    $('#ipv'+v+'gateway').val("");
}

//ipv4和ipv6的切换
function showMask(){
    $("#ipv4_mask").show();
    $("#VrouteDestIp").text("");
    $("#VnextAddress").text("");
    $("#VrouteNetmask").text("");
}
function hideMask(){
    $("#ipv4_mask").hide();
    $("#VrouteDestIp").text("");
    $("#VnextAddress").text("");
}
$(function () {
    $('.network_test_type').click(function () {
        $('#networkResult').html('');
        if ($(this).attr('id') == 'tcpport') {
            $('#portdiv').css('display', '');
        } else {
            $('#portdiv').css('display', 'none');
        }

    });
    $('#networktestbtn').click(function () {
        $("#Vnetworkport").text("");
        $("#Vnetworkip").text("");
        if ($('#ping')[0].checked) {
            cmd = 'lping';
            if(!checknetIp())return false;
            $("#Vnetworkport").text("");
            $("#Vnetworkip").text("");
        } else if ($('#route')[0].checked) {
            cmd = 'ltraceroute';
            if(!checknetIp())return false;
            $("#Vnetworkport").text("");
            $("#Vnetworkip").text("");
        } else if ($('#tcpport')[0].checked) {
            cmd = 'lportest';
            if(!checkNet())return false;
            $("#Vnetworkport").text("");
            $("#Vnetworkip").text("");
        } else {
            cmd = 'ltcpdump';
            if(!checknetIp())return false;
            $("#Vnetworkport").text("");
            $("#Vnetworkip").text("");
        }
        $('#networkResult').html('后台执行中...');
        $.ajax({
            url: "../../configNetwork/" + cmd,
            type: "POST",
            data: {
                ip: $('#networkip').val(),
                port: $('#networkport').val()
            },
            success: function (data) {
                if (data.success) {
                    $('#networkResult').html(data.msg.replace(/\n/g, '<br>'));
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg ? data.msg : "操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function () {
            }
        })
    });
    function checknetIp() {
        var flag=true;
        var nip=$("#networkip").val();
        var p2=/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
        if(nip==""){
            $("#Vnetworkip").text("请输入IPv4地址");
            flag=false;
        }else if(!p2.test(nip)){
            $("#Vnetworkip").text("请输入有效的IPv4地址");
            flag=false;
        }
        return flag;
    }
    function checkNet() {
        var nport=$("#networkport").val();
        var nip=$("#networkip").val();
        var flag=true;
        var p1=/^\d{1,65535}$/;
        var p2=/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
        if(nport==""){
            $("#Vnetworkport").text("请输入端口号");
            flag=false;
        }else if(nport<1||nport>65535||!p1.test(nport)){
            $("#Vnetworkport").text("请输入1-65535之间的数字");
            flag=false;
        }
        if(nip==""){
            $("#Vnetworkip").text("请输入IPv4地址");
            flag=false;
        }else if(!p2.test(nip)){
            $("#Vnetworkip").text("请输入有效的IPv4地址");
            flag=false;
        }
        return flag;
    }
    function checkIpv4() {
        var flag=true;
        var nip=$("#ipv4addr").val();
        var p2=/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
        if($("#ipv4dhcp")[0].checked){
            $("#Vipv4addr").text("");
        }
        else if(nip==""){
            $("#Vipv4addr").text("请输入IPv4地址");
            flag=false;
        }else if(!p2.test(nip)){
            $("#Vipv4addr").text("请输入有效的IPv4地址");
            flag=false;
        }
        return flag;
    }
    function checkIpv6() {
        var flag=true;
        var nip6=$("#ipv6addr").val();
        var p3=/^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\/\d{1,3}\s*$/;
        // var p3=/^(([\da-fA-F]{1,4}):){8}$/;
        if($("#ipv6dhcp")[0].checked || $("#ipv6enable")[0].checked){
            $("#Vipv6addr").text("");
        }else
        if(nip6==""){
            $("#Vipv6addr").text("请输入IPv6地址");
            flag=false;
        }else if(!p3.test(nip6)){
            $("#Vipv6addr").text("请输入有效的IPv6地址");
            flag=false;
        }
        return flag;
    }
    function checkIpv4netmask() {
        var flag=true;
        var nip=$("#ipv4netmask").val();
        var p2=/^((128|192)|2(24|4[08]|5[245]))(\.(0|(128|192)|2((24)|(4[08])|(5[245])))){3}$/;
        if($("#ipv4dhcp")[0].checked){
            $("#Vipv4addr").text("");
        }else if(nip==""){
            $("#Vipv4netmask").text("请输入子网掩码");
            flag=false;
        }else if(!p2.test(nip)){
            $("#Vipv4netmask").text("请输入有效的子网掩码");
            flag=false;
        }
        return flag;
    }
    //网关格式4
    function checkGate4(){
        var flag=true;
        var ipv4gateway=$("#ipv4gateway").val();
        var p2=/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
        if(ipv4gateway!=""&&!p2.test(ipv4gateway)){
            $("#Vipv4gateway").text("请输入有效的网关");
            flag=false;
        }
        return flag;
    }
    function checkGate6(){
        var flag=true;
        var ipv6gateway=$("#ipv6gateway").val();
        var p2=/^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/;
        if(ipv6gateway!=""&&!p2.test(ipv6gateway)){
            $("#Vipv6gateway").text("请输入有效的网关");
            flag=false;
        }
        return flag;
    }
    $("#ipv4gateway").blur(function(){
        checkGate4();
    })
    $("#ipv6gateway").blur(function(){
        checkGate6();
    })
    $("#ipv4gateway").focus(function () {
        $("#Vipv4gateway").text("");
    });
    $("#ipv6gateway").focus(function () {
        $("#Vipv6gateway").text("");
    });

    $("#ipv4addr").blur(function(){
        checkIpv4();
    })
    $("#ipv6addr").blur(function(){
        checkIpv6();
    })
    $("#ipv6addr").blur(function(){
        checkIpv6();
    })
    $("#ipv4netmask").blur(function(){
        checkIpv4netmask();
    })

    $("#ipv4addr").focus(function () {
        $("#Vipv4addr").text("");
    });
    $("#ipv6addr").focus(function () {
        $("#Vipv6addr").text("");
    });
    $("#ipv4netmask").focus(function () {
        $("#Vipv4netmask").text("");
    });
    $("#networkip").focus(function () {
        $("#Vnetworkip").text("");
    });
    $("#networkport").focus(function () {
        $("#Vnetworkport").text("");
    });
    $("#networkip").focus(function () {
        $("#Vnetworkip").text("");
    });
    var _interfaces = function (){
        $.ajax({
            url: "../../configRoute/listInterface",
            type: "POST",
            data: {
                id: $('#routeId').val(),
                device_type: $('#v4')[0].checked ? 0 : 1,
                destion_ip: $('#routeDestIp').val(),
                netmask: $('#routeNetmask').val(),
                prefix: $('#routePrefix').val(),
                nextaddress: $('#nextAddress').val(),
                device: $('#routeInterface').val(),
                desc: $('#routeDesc').val()
            },
            success: function (data) {
                if (data.success) {
                    $('#routeInterface')[0].options.length = 0;
                    for (var i = 0; i < data.data.length; i++) {
                        $('#routeInterface')[0].options[i] = new Option(data.data[i], data.data[i]);
                    }
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg ? data.msg : "操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function () {

            }
        })
    };
//删除路由
    $('#delRouteBtn').click(function () {
        $.ajax({
            url: "../../configRoute/delConfigRoute",
            type: "POST",
            data: {
                'ids[]': $('#delRouteId').val()
            },
            success: function (data) {
                if (data.success) {
                    loadAJAX('#routeList');
                    $("#modal-routedel").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg ? data.msg : "操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function () {
            }
        })
    });

    $('#editRouteBtn').click(function (){
        checkIPName();
        var flag = checkIp();
        if (flag == false) return false;
        $.ajax({
            url: "../../configRoute/editConfigRoute",
            type: "POST",
            data: {
                id: $('#routeId').val(),
                device_type: $('#v4')[0].checked ? 0 : 1,
                destion_ip: $('#routeDestIp').val(),
                netmask: $('#routeNetmask').val(),
                prefix: $('#routePrefix').val(),
                nextaddress: $('#nextAddress').val(),
                device: $('#routeInterface').val(),
                desc: $('#routeDesc').val()
            },
            success: function (data) {
                if (data.success) {
                    if ($('#routeId').val()=="") {
                        loadAJAX('#routeList');
                        $("#modal-editroute").modal("hide");
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text("新建成功!");
                        $("#modal-success").modal();
                    }else {
                        loadAJAX('#routeList');
                        $("#modal-editroute").modal("hide");
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text("编辑成功!");
                        $("#modal-success").modal();
                    }
                } else{
                    if ($('#routeId').val() == "") {
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text(data.msg ? data.msg : "新建失败!");
                        $("#modal-danger").modal();
                    }else {
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text(data.msg ? data.msg : "编辑失败!");
                        $("#modal-danger").modal();
                    }
                }
            },
            error: function () {
            }
        })
    });

    _network = function () {
        $('#interfaceList').DataTable({
            'paging': true,
            "iDisplayLength": 10,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'orderable': false,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            "destroy": true,
            "ajax": {
                "url": "../../configNetwork/listConfigNetwork",
                "data": function (d) {
                    for (var key in d) {
                        if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                }
            },
            "columns": [
                {"data": "device"},
                {"data": "name", render:function(data, type, row, meta){
                        return row.ipv4addr+"<br>"+row.ipv6addr;
                    }},
                {"data": "name", render:function(data, type, row, meta){
                        return row.ipv4netmask+"<br>"+row.ipv6netmask;
                    }},
                {"data": "name", render:function(data, type, row, meta){
                        return row.ipv4gateway+"<br>"+row.ipv6gateway;
                    }},
                {"data": "onboot", render:function(data, type, row, meta){
                        return data==1?'启用':'禁用';
                    }},
                {
                    "data": "id", "render": function (data, type, row, meta) {
                        return '<a class="newcss1" data-toggle="modal" data-row="' + meta.row + '" data-target="#modal-interface" style="line-height:2px;margin-right:20px;cursor:pointer;">编辑</a>'+
                            '<a class="newcss1" data-toggle="modal" data-row="' + meta.row + '" data-target="#modal-restart" style="line-height:2px;cursor:pointer;">重启</a>';
                    }
                },
            ]
        })
    };

    var _routeList = function () {
        $('#routeList').DataTable({
            'paging': true,
            "iDisplayLength": 10,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'orderable': false,
            'info': true,
            'autoWidth':false,
            "serverSide": true,
            "destroy": true,
            "ajax": {
                "url": "../../configRoute/listConfigRoute",
                "data": function (d) {
                    for (var key in d) {
                        if (key.indexOf("columns") == 0|| key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                }
            },
            "columns": [
                {"data": "destion_ip",
                    "render": function (data, type, row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="' + data + '">'
                            + data
                            + '</div>';

                    }},
                {
                    "data": "netmask", "render": function (data, type, row, meta) {
                        return row.device_type == 0 ? row.netmask : " ";
                    }
                },
                {"data": "nextaddress",
                    "render": function (data, type, row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="' + data + '">'
                            + data
                            + '</div>';

                    }
                },
                {
                    "data": "device_type", "render": function (data, type, row, meta) {
                        return data == 0 ? 'IPv4' : 'IPv6';
                    }
                },
                {"data": "device"},
                {
                    "data": "desc",
                    "render": function (data, type, row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="' + data + '">'
                            + data
                            + '</div>';

                    }
                },
                {
                    "data": "id", "render": function (data, type, row, meta) {
                        return '<a class="newcss1" data-toggle="modal" data-row="' + meta.row + '" id="editeA" data-target="#modal-editroute" style="line-height: 2px;cursor: pointer;">编辑</a>'
                            + '<a class="newcss2" data-toggle="modal" data-row="' + meta.row + '" data-target="#modal-routedel" style="margin-left:10px;line-height: 2px;cursor: pointer;">删除</a>'
                            ;
                    }
                },
            ],
            "fnDrawCallback": function (settings, json) {
                $('#routeList div').tooltip();
            }
        })
    };
    $('#saveInterface').click(function () {
        if(!checkIpv4()||!checkIpv4netmask()||!checkIpv6()){
            return ;
        }
        $.ajax({
            url: "../../configNetwork/editConfigNetwork",
            type: "POST",
            data: {
                device: $('#deviceName').val(),
                bootprotocol: $('#ipv4dhcp')[0].checked?'dhcp':'static',
                ipv4addr: $('#ipv4addr').val(),
                ipv4netmask: $('#ipv4netmask').val(),
                ipv4gateway: $('#ipv4gateway').val(),
                ipv6addr: $('#ipv6addr').val(),
                ipv6gateway: $('#ipv6gateway').val(),
                ipv6autoconf: $('#ipv6dhcp')[0].checked ? 1 : 0,
                ipv6enable: $('#ipv6enable')[0].checked ? 0 : 1
            },
            success: function (data) {
                if (data.success) {
                    loadAJAX('#interfaceList');
                    $("#modal-interface").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error: function (){

            }
        })
    });
    _network();
    _routeList();
    _interfaces();

    $('#modal-routedel').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#delRouteId').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].id);
    });

    $('#modal-editroute').on('show.bs.modal', function (event) {
        //子网掩码显示
        var button = $(event.relatedTarget); // Button that triggered the modal
        i = button.data('row');
        if (i != null) {
            $('#modal-editroute .modal-title').text('编辑路由规则');
            $('#v4')[0].checked = $('#routeList').DataTable().row('#' + i).nodes(i).data()[i].device_type == 0 ? true : false;
            $('#v6')[0].checked = $('#routeList').DataTable().row('#' + i).nodes(i).data()[i].device_type == 1 ? true : false;
            if($('#v4')[0].checked===true){
                $("#ipv4_mask").show();
                $('#routeNetmask').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].netmask);
            }else if($('#v6')[0].checked===true){
                $("#ipv4_mask").hide();
                $('#routeNetmask').val("");
            }
            $('#routeId').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].id);
            $('#routeDestIp').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].destion_ip);
            $('#prefix').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].prefix);
            $('#nextAddress').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].nextaddress);
            $('#routeInterface').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].device);
            $('#routeDesc').val($('#routeList').DataTable().row('#' + i).nodes(i).data()[i].desc);
            $('#VrouteDestIp').text('');
            $('#VrouteNetmask').text('');
            $('#VnextAddress').text('');
        } else {
            $('#modal-editroute .modal-title').text('新建路由规则');
            $('#v4')[0].checked = true;
            $("#ipv4_mask").show();
            $('#routeId').val('');
            $('#routeDestIp').val('');
            $('#routeNetmask').val('');
            $('#prefix').val('');
            $('#nextAddress').val('');
            $('#routeInterface').val('');
            $('#routeDesc').val('');
            $('#VrouteDestIp').text('');
            $('#VrouteNetmask').text('');
            $('#VnextAddress').text('');
        }
    });

    $('#modal-interface').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#ifaceName').text($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].device);
        $('#deviceName').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].device);
        if ($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].bootproto == 'static' || $('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].bootproto == 'none') {
            $('#ipv4static')[0].checked = true;
            ifaceStatic(4);
            $('#ipv4addr').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv4addr);
            $('#ipv4netmask').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv4netmask);
            $('#ipv4gateway').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv4gateway);
        } else {
            $('#ipv4dhcp')[0].checked = true;
            ifaceDhcp(4);
        }
        if ($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6enable == '0' && $('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6autoconf == 'no') {
            $('#ipv6enable')[0].checked = true;
            ifaceDhcp(6);
        }
        if(!$('#ipv6enable')[0].checked){
            if ($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6autoconf == 'yes') {
                ifaceDhcp(6);
                $('#ipv6addr').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6addr+$('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6netmask);
                $('#ipv6gateway').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6gateway);
            } else if ($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6autoconf == 'no') {
                $('#ipv6static')[0].checked = true;
                ifaceStatic(6);
                $('#ipv6addr').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6addr+'/'+$('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6netmask);
                $('#ipv6gateway').val($('#interfaceList').DataTable().row('#' + i).nodes(i).data()[i].ipv6gateway);
            }
        }
    });
});

//检查目的地址重名
function checkIPName(){
    $.ajax({
        url: "../../configRoute/checkIPName",
        type: "POST",
        data: {
            id:$('#routeId').val(),
            destion_ip:$('#routeDestIp').val()
        },
        success:function (data){
            if(!data.success){
                $('#VrouteDestIp').text('IP地址重复');
            }
        }
    });
}
//验证
var regexp = {
    ipv4:/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/,
    ipv6:/^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/,
    ip3:/^((128|192)|2(24|4[08]|5[245]))(\.(0|(128|192)|2((24)|(4[08])|(5[245])))){3}$/,
}
$('#routeDestIp').blur(function(){
    var destIp1=$("#routeDestIp").val();
    if($('#v4')[0].checked==true&&destIp1==""){
        $('#VrouteDestIp').text('请输入IPv4地址');
    }else if($('#v4')[0].checked==true&&!regexp.ipv4.test(destIp1)){
        $('#VrouteDestIp').text('请输入有效的IPv4地址');
    }else {
        checkIPName();
    }
    if($('#v6')[0].checked==true&&destIp1==""){
        $('#VrouteDestIp').text('请输入IPv6地址');
    }else if($('#v6')[0].checked==true&&!regexp.ipv6.test(destIp1)){
        //$('#VrouteDestIp').text('请输入有效的IPv6地址');
    }else {
        checkIPName();
    }
});
$('#routeDestIp').focus(function(){
    $('#VrouteDestIp').text('');
});
$("#routeNetmask").blur(function () {
    var destIP2=$("#routeNetmask").val();
    if($('#v4')[0].checked==true&&destIP2==""){
        $('#VrouteNetmask').text('请输入子网地址');
    }else if($('#v4')[0].checked==true&&!regexp.ip3.test(destIP2)){
        $('#VrouteNetmask').text('请输入有效的子网地址');
    }
});
$('#routeNetmask').focus(function(){
    $('#VrouteNetmask').text('');
})
$('#nextAddress').blur(function(){
    var destIp3=$("#nextAddress").val();
    if($('#v4')[0].checked === true){
        if(destIp3 === ""){
            $('#VnextAddress').text('请输入IPv4地址');
        }else if(!regexp.ipv4.test(destIp3)){
            $('#VnextAddress').text('请输入有效的IPv4地址');
        }else if(destIp3 === $("#routeDestIp").val()){
            $('#VnextAddress').text('不能和目的地址相同');
        }
    }
    if($('#v6')[0].checked === true){
        if(destIp3 === ""){
            $('#VnextAddress').text('请输入IPv6地址');
        }else if(!regexp.ipv6.test(destIp3)){
            $('#VnextAddress').text('请输入有效的IPv6地址');
        }else if(destIp3 === $("#routeDestIp").val()){
            $('#VnextAddress').text('不能和目的地址相同');
        }
    }
});
$('#nextAddress').focus(function(){
    $('#VnextAddress').text('');
});
//总验证
function checkIp(){
    var flag=true;
    var destIp1=$("#routeDestIp").val();
    var destIP2=$("#routeNetmask").val();
    var destIp3=$("#nextAddress").val();
    if($('#v4')[0].checked === true){
        if(destIp1==""){
            $('#VrouteDestIp').text('请输入IPv4地址');
            flag=false;
        }else if(!regexp.ipv4.test(destIp1)){
            $('#VrouteDestIp').text('请输入有效的IPv4地址');
            flag=false;
        }
    }
    if($('#v6')[0].checked === true){
        if(destIp1==""){
            $('#VrouteDestIp').text('请输入IPv6地址');
            flag=false;
        }else if(!regexp.ipv6.test(destIp1)){
            $('#VrouteDestIp').text('请输入有效的IPv6地址');
            flag=false;
        }
    }
    if($('#v4')[0].checked === true&&destIP2 === ""){
        $('#VrouteNetmask').text('请输入子网地址');
        flag=false;
    }else if($('#v4')[0].checked === true&&!regexp.ip3.test(destIP2)){
        $('#VrouteNetmask').text('请输入有效的子网地址');
        flag=false;
    }else if($('#v6')[0].checked === true){
        destIP2="ipv6";
        $('#VrouteNetmask').text('');
        flag=true;
    }
    if($('#v4')[0].checked === true){
        if(destIp3===""){
            $('#VnextAddress').text('请输入IPv4地址');
            flag=false;
        } else if(!regexp.ipv4.test(destIp3)){
            $('#VnextAddress').text('请输入有效的IPv4地址');
            flag=false;
        }else if(destIp1===destIp3){
            $('#VnextAddress').text('不能和目的地址相同');
            flag=false;
        }
    }
    if($('#v6')[0].checked === true){
        if(destIp3===""){
            $('#VnextAddress').text('请输入IPv6地址');
            flag=false;
        }else if(!regexp.ipv6.test(destIp3)){
            $('#VnextAddress').text('请输入有效的IPv6地址');
            flag=false;
        }else if(destIp1===destIp3){
            $('#VnextAddress').text('不能和目的地址相同');
            flag=false;
        }
    }
    return flag;
}