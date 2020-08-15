function changeCss1(){
    document.getElementById("a1").style.color= " #12BA1D";
    document.getElementById("a2").style.color= "black";
    document.getElementById("a3").style.color= "black";
};
function changeCss2(){
    document.getElementById("a1").style.color= "black";
    document.getElementById("a2").style.color= "#12BA1D";
    document.getElementById("a3").style.color= "black";
};
function changeCss3(){
    document.getElementById("a1").style.color= "black";
    document.getElementById("a2").style.color= "black";
    document.getElementById("a3").style.color= "#12BA1D";
};
function show1(){
    document.getElementById("menu1").innerHTML= "全部";
}
function show2(){
    document.getElementById("menu1").innerHTML= "键盘输入";
}
function show3(){
    document.getElementById("menu1").innerHTML= "剪切板复制";
}
function show4(){
    document.getElementById("menu1").innerHTML= "剪切板粘贴";
}
function show5(){
    document.getElementById("menu2").innerHTML= "全部";
}
function show6(){
    document.getElementById("menu2").innerHTML= "上传文件(夹)";
}
function show7(){
    document.getElementById("menu2").innerHTML= "下载文件";
}
function show8(){
    document.getElementById("menu2").innerHTML= "重命名文件(夹)";
}
function show9(){
    document.getElementById("menu2").innerHTML= "删除文件(夹)";
}
function show10(){
    document.getElementById("menu2").innerHTML= "创建文件夹";
}
function goTo(a){
    $('#simpleSearch').css('display', 'none');
    $('#moreSearch').css('display', 'block');
    $('#export-div').css('margin-top','10px')
};
function back() {
    $('#simpleSearch').css('display', 'block');
    $('#moreSearch').css('display', 'none');
    $('#export-div').css('margin-top','-40px')
}
//ajax
var title = "";
var current_record_id = 0;
var i=0;
function _protocol(data) {

    if (data == 1) {
        return "SSH";
    } else if (data == 2) {
        return "RDP";
    } else if (data == 3) {
        return "TELNET";
    } else if (data == 4) {
        return "VNC";
    }
}

$(function() {

    $('#modal-default')
        .on(
            'show.bs.modal',
            function(event) {
                var button = $(event.relatedTarget) // Button that triggered the modal
                i = button.data('row');
                var auth_type = $('#hostsessions').DataTable()
                    .row('#' + i).nodes(i).data()[i].auth_type;
                var auth_type_new = showC_T(auth_type);
                $('#device_name2')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].device_name);
                $('#protocol')
                    .html(
                        _protocol($('#hostsessions')
                            .DataTable().row(
                                '#' + i).nodes(
                                i).data()[i].protocol_id));
                $('#device_ip2')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].device_ip);
                $('#device_username2')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].device_username);
                $('#start')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].start
                        + '---'
                        + $('#hostsessions')
                            .DataTable()
                            .row('#' + i)
                            .nodes(i)
                            .data()[i].end);
                $('#time_length')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].time_length);
                $('#log_file_size')
                    .html(
                    		renderSize($('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].video_file_size));
                $('#user')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].user);
                $('#client_ip2')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].client_ip);
                $('#client_mac')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].client_mac);
                $('#_start')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].start
                        + '---'
                        + $('#hostsessions')
                            .DataTable()
                            .row('#' + i)
                            .nodes(i)
                            .data()[i].end);
                $('#user')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].username);
                $('#length')
                    .html(
                        $('#hostsessions').DataTable()
                            .row('#' + i).nodes(i)
                            .data()[i].time_length);
                $('#auth_type').html(auth_type_new);

            });
    var _hostsessions = function(field, value, where) {
        $('#hostsessions')
            .DataTable(
                {
                    'paging' : true,
                    'lengthChange' : true,
                    "lengthMenu" : [
                        [ 10, 25, 50, 100 ],
                        [ "10条/页", "25条/页", "50条/页",
                            "100条/页" ] ],
                    'dom' : 't<"bottom"lifp<"clear">>',
                    'searching' : false,
                    'ordering' : true,
                    'info' : true,
                    'autoWidth' : false,
                    "serverSide" : true,
                    "destroy" : true,
                    "ajax" : {
                        "url" : "../../deviceRecord/listDeviceRecord",
                        "data" : function(d) {
                            for ( var key in d) {
                            	if(key=='start'){
                            		d['_start'] = d[key]
                            		delete d[key];
                            	}
                                if (key.indexOf("columns") == 0
                                    || key.indexOf("order") == 0
                                    || key.indexOf("search") == 0) { //以columns开头的参数删除
                                    delete d[key];
                                }
                            }
                            eval('d.' + field + '="' + value + '"');
                            if(where!=null){
                            	for(k in where){
                            		d[k]=where[k];
                            	}
                            }
                        }
                    },
                    "columns" : [
                        {
                            "data" : "id"
                        },
                        {
                            "data" : "device_name"
                        },
                        {
                            "data" : "device_ip"
                        },
                        {
                            "data" : "protocol_id",
                            "render" : function(data, type, row, meta) {
                            	return get_protocol_name(data);
                            }
                        },
                        {
                            "data" : "device_username"
                        },
                        {
                            "data" : "client_ip"
                        },
                        {
                            "data" : "username"
                        },
                        {
                            "data" : "realname"
                        },
                        {
                            "data" : "start",
                            "render" : function(data, type, row, mata) {
                                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="开始:'+row.start+"<br>结束:"+row.end+'">'
                                    + row.start
                                    + "-"
                                    + row.end
                                    + '</div>';

                            }
                        },
                        {
                            "data" : "id",
                            "render" : function(data, type,
                                                row, meta) {
                                var str = '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-default" style="cursor:pointer">详情</a>';
                                if(row.protocol_id==5||row.protocol_id==6){
                                	str += '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-record-command" style="margin-left:20px;cursor:pointer">命令</a>'; 
                                }else{
                                	str += '<a href="../../Operator/Replay?recordid=' + data + '" target="_blank" class="newcss1" style="margin-left:20px;cursor:pointer">回放</a>'; 
                                }
                                str += '<a class="newcss1" href="../../Operator/download?recordid='
                                    + data
                                    + '" target="hide" style="margin-left:20px;cursor:pointer">下载</a>';
                                return str;
                            }
                        } ],
                    "fnDrawCallback": function( settings, json, where ) {
                        $('#hostsessions div').tooltip();
                    }
                });

    }
    var recordcommand = function(record_id){
    	$('#record-command').DataTable({
            'paging'      : true,
            "iDisplayLength": 10,
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
            "destroy": true,
            "ajax": {
                "url":"../../recordCommand/listRecordCommand",
                "data": function (d) {
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                    //指定检索参数名称，后台参数名为extraSerach
                    eval('d.is_apppub=0')
                    eval('d.record_id="'+record_id+'"');
                },
            },
            "columns": [
                { "data": "_time" },
                { "data": "command" ,
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:300px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';

                    }},{
			        	"data":"cmddanger_id" , "render":function(data,type,row,mata){
			        		return '无'
			        	}
			        },
                { "data": "filepath"  ,
                    "render" : function(data, type,
			                            row, mata) {
                    	if(data=='1')
                    		return '<a href="../../recordCommand/download?id=' + row.id + '" target="hide" class="newcss1" style="margin-left:20px;cursor:pointer">下载</a>'; 
                    	return '';
			
			        }}
            ],
            "fnDrawCallback": function( settings, json ) {
                $('#record-command div').tooltip();
            }
        });
    }
    $('#modal-record-command').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        recordcommand($('#hostsessions').DataTable().row('#' + i).nodes(i).data()[i].id);
    });

    $("#sousuoId").click(function() {
        _hostsessions($('#Distinguish').val(), $('#searchId').val());
    });
    $("#moreBtn").click(function(){
    	var where = {};
    	if($('#device_name').val()!=""){
    		where['device_name'] = $('#device_name').val();
    	}
    	if($('#device_ip').val()!=""){
    		where['device_ip'] = $('#device_ip').val();
    	}
    	if($('#protocol').val()!=""){
    		where['protocol'] = $('#protocol').val();
    	}
    	if($('#device_username').val()!=""){
    		where['device_username'] = $('#device_username').val();
    	}
    	if($('#client_ip').val()!=""){
    		where['client_ip'] = $('#client_ip').val();
    	}
    	if($('#username').val()!=""){
    		where['username'] = $('#username').val();
    	}
    	if($('#realname').val()!=""){
    		where['realname'] = $('#realname').val();
    	}
    	if($('#command').val()!=""){
    		where['command'] = $('#command').val();
    	}
    	if($('#startend').val()!=""){
    		where['start'] = $('#startend').val().split(' - ')[0]+" 00:00:00";
    		where['end'] = $('#startend').val().split(' - ')[1]+" 23:59:59";
    	}
    	_hostsessions(null,null, where);
    });
    _hostsessions();

})
$('#_export').click(function() {
    $('#downFile')[0].click();

})

function showC_T(auth_type) {
    if (auth_type == 0)
        return '密码';
    else if (auth_type == 1)
        return '生物指纹';
    else if (auth_type == 2)
        return '密码+动态口令';
    else if (auth_type == 3)
        return '短信';
    else if (auth_type == 4)
        return 'AD域';
    else if (auth_type == 5)
        return 'RADIUS';
    else if (auth_type == 6)
        return 'LDAP';
    else
        return 'email';

};

/*
$("#clear").click (function () {
    $('#host_name').val('');
    $('#host_address').val('');
    $('#protocol').val('');
    $('#host_account').val('');
    $('#come_address').val('');
    $('#repair_user').val('');
    $('#user_name').val('');
    $('#operation_command').val('');
});*/
function init() {
	
    //定义locale汉化插件
	var locale = {
    	direction: $('#rtl').is(':checked') ? 'rtl' : 'ltr',
        "format" : 'YYYY-MM-DD',
        "applyLabel" : "确定",
        "cancelLabel" : "取消",
        "fromLabel" : "起始时间",
        "toLabel" : "结束时间'",
        "weekLabel" : "W",
        "daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
        "monthNames" : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月",
            "八月", "九月", "十月", "十一月", "十二月" ],
        "firstDay": 1,
        "customRangeLabel": '自定义',
    };
    var ranges =  {
        '今天': [moment(), moment()],
        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        '最近7天': [moment().subtract(6, 'days'), moment()],
        '最近30天': [moment().subtract(29, 'days'), moment()],
        '本月': [moment().startOf('month'), moment().endOf('month')],
        '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
      }
    //日期控件初始化
    $('#startend').daterangepicker({
        'locale' : locale,
        showDropdowns : true,
        autoApply : false,
        startDate : moment().subtract(29, 'days'),
        endDate : moment(),
        "opens"  : "right",
        "ranges" : ranges
    });
};
$(document).ready(function() {
    init();
})