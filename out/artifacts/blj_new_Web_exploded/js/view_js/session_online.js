var cutOnline=function (url, table){
    $.ajax({
        url:url,
        type:"GET",
        success:function(data){
            if(data.success){
            	loadAJAX("#"+table);
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('断开成功!');
                $("#modal-success").modal();
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('断开失败!');
                $("#modal-danger").modal();
            }
        }
    })
}
$(function () {
    var _devicesonline = function (field, value){

        $('#devicesonline').DataTable({
            'paging'      : true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom'         :'t<"bottom"lifp<"clear">>',
            'searching'   : false,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false,
            "serverSide"	: true,
            'destroy'     :true,
            "ajax": {
                "url":"../../liveSessions/listLiveSessions",
                "data": function (d) {
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                    eval('d.' + field + '="' + value
                        + '"');
                }
            },
            "columns": [
                { "data": "id" },
                { "data": "device_name" },
                { "data": "device_ip" },
                { "data": "protocol" },
                { "data": "device_user" },
                { "data": "client_ip" },
                { "data": "username" },
                { "data": "realname"},
                { "data": "start" },
                { "data": "id", "render": function(data,type,row,meta){
                        return '<a style="cursor:pointer" href="../../liveSessions/Monitor?live_id='+data+'" target="_blank" class="newcss1">监控</a>'+
                            '&nbsp;&nbsp;<a style="cursor:pointer" href="javascript:void(0)" onclick="cutOnline(\'../../liveSessions/Cut?live_id='+data+'\',\'devicesonline\');return false;" class="newcss1">断开</a>';
                    }}
            ]
        });
    }
    _devicesonline();
    $("#sousuoanniu1").click(function(){
        _devicesonline($('#DistinguishDevice').val(), $('#searchIdDevice').val());
    })

    var _apponline = function(){
        $('#apponline').DataTable({
            'paging'      : true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom'         :'t<"bottom"lifp<"clear">>',
            'searching'   : false,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false,
            "serverSide"	: true,
            'destroy'     :true,
            "ajax": {
                "url":"../../liveSessions/listLiveSessions",
                "data": function (d) {
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                }
            },
            "columns": [
                { "data": "id" },
                { "data": "app_name" },
                { "data": "program" },
                { "data": "app_account" },
                { "data": "client_ip" },
                { "data": "username" },
                { "data": "realname"},
                { "data": "start" },
                { "data": "id", "render": function(data,type,row,meta){
                        return '<a style="cursor:pointer" class="newcss1">监控</a>'+
                            '<a style="margin-left:20px;cursor:pointer" class="newcss1">断开</a>';
                    }}
            ]
        });

    }
    $("#sousuoanniu2").click(function(){
        _apponline()
    })
    //web在线用户
    var _webonline = function(field, value){
        $('#webonline').DataTable({
            'paging'      : true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom'         :'t<"bottom"lifp<"clear">>',
            'searching'   : false,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false,
            "serverSide"	: true,
            'destroy'     :true,
            "ajax": {
                "url":"../../sysSession/listSysSession",
                "data": function (d) {
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                    eval('d.' + field + '="' + value
                        + '"');
                }
            },
            "columns": [
                { "data": "username" },
                { "data": "realname" },
                { "data": "logintime" },
                { "data": "last_login_ip" },
                { "data": "refreshtime" },
                { "data": "id", "render": function(data,type,row,meta){
                		if(row.self==0)
                			return '<a href="javascript:void(0)" onclick="cutOnline(\'../../sysSession/delSysSession?id='+data+'\',\'webonline\');return false;" style="cursor:pointer" class="newcss1">断开</a>';
                		return '';
                    }}
            ]
        });
    }
    $("#sousuoanniu3").click(function(){
        _webonline($('#webonline').val(), $('#searchIdweb').val());
    });

    $('.nav.nav-tabs li').each(function(){
        $(this).click(function(){
            if($(this).children().attr('href')=='#timeline'){
                _apponline();
            }else if($(this).children().attr('href')=='#A'){
                _webonline();
            }else{
                _devicesonline();
            }
        })
    });
    if(window.location.toString().indexOf('#web')>0){
        $('.nav.nav-tabs li:eq(1) a').tab('show');
        _webonline();
    }
})