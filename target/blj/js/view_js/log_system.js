function operatorLog() {
    $('#downFile')[0].click();
}
function loginLog() {
    $('#downFile1')[0].click();
}
function AlertLog() {
    $('#downFile2')[0].click();
}
$(function () {
	
    var _loginlog = function (field, value){
        $('#loginlog').DataTable({
            'paging': true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            'destroy': true,
            "ajax": {
                "url": "../../loginLog/listLoginLog",
                "data": function (d) {
                    if (field === "searchAll" || field === "status"){
                        if ($('#loginSearch').val().match("成")||$('#loginSearch').val().match("功")||$('#loginSearch').val().match("成功")){
                            value=1;
                            field="status";
                        }else if($('#loginSearch').val().match("失")||$('#loginSearch').val().match("败")||$('#loginSearch').val().match("失败")){
                            value=0;
                            field="status";
                        }else if($('#loginSearch').val()===''){
                            value='';
                        }else{
                            if(field === "searchAll"){
                                value=$('#loginSearch').val();
                            }else if(field === "status"){
                                value=3;
                            }
                        }
                    }
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                    //指定检索参数名称，后台参数名为extraSerach
                    eval('d.' + field + '="' + value
                        + '"');
                }
            },
            "columns": [
                {"data": "login_datetime",
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="' + data + '">'
                            + data
                            + '</div>';
                    }},
                {"data": "source_ip"},
                {"data": "username"},
                {"data": "realname"},
                {"data": "protocol"},
                {
                "data": "status",
                    "render": function (data, type, row, mata) {
                        if (data == 0)
                            return '失败';
                        else
                            return '成功';
                    }
                },
                {"data": "details"},

            ],
            "fnDrawCallback": function( settings, json ) {
                $('#loginlog div').tooltip();
            }
        })
    };

    $("#sousuoanniu").click(function () {
        _loginlog($('#loginOption').val(), $('#loginSearch').val());
    });

    var _systemlog = function(field, value){
        $('#systemlog').DataTable({
            'paging': true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            "destroy" : true,
            "ajax": {
                "url": "../../operatorLog/listOperatorLog",
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
                {"data": "operate_datetime",
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';}},
                {"data": "source_ip"},
                {"data": "username"},
                {"data": "realname"},
                {"data": "module"},
                {"data": "content"},
                {"data": "details",
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';

                    }},
                {"data": "result"},
            ],
            "fnDrawCallback": function( settings, json ) {
                $('#systemlog div').tooltip();
            }
        });
    };
    $("#sousuoanniusystem").click(function () {
        _systemlog($('#systemOption').val(), $('#systemSearch').val());
    });


    var _alertslog = function(field, value){
        $('#alertstable').DataTable({
            'paging': true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            "destroy" : true,
            "ajax": {
                "url": "../../alertLog/listAlertLog",
                "data": function (d) {
                    if (field === "searchAll" || field === "dangerlevel"){
                        if ($('#alertsSearch').val().match("高")){
                             value=3;
                             field="dangerlevel";
                        }else if($('#alertsSearch').val().match("中")){
                             value=2;
                             field="dangerlevel";
                        }else if($('#alertsSearch').val().match("低")){
                             value=1;
                             field="dangerlevel";
                        }else if($('#alertsSearch').val()===''){
                            value='';
                        }else{
                           if(field === "searchAll"){
                                value=$('#alertsSearch').val();
                            }else if(field === "dangerlevel"){
                                value=4;
                            }
                        }
                    }
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
                {"data": "operate_datetime",
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';
                }},
                {"data": "source_ip"},
                {"data": "username"},
                {"data": "device_ip"},
                {"data": "device_account"},
                {"data": "protocol"},
                {"data": "command"},
                {"data": "policy",
                    "render" : function(data, type,
			                            row, mata) {
			            return row.policy_id==0?'系统配置':data;
			    }},
                {"data": "dangerlevel",
                	"render": function (data, type, row, mata) {
                        if (data == 3)
                            return '高';
                        else if(data == 2)
                            return '中';
                        else
                        	return '低';
                    }
                },
                {"data": "isemail",
                	"render": function (data, type, row, mata) {
                        if (data == 1)
                            return '已发送邮件';
                        else
                            return '未通知';
                    }
                },
            ],
            "fnDrawCallback": function( settings, json ) {
                $('#alertstable div').tooltip();
            }
        });
    }
    $("#sousuoalerts").click(function () {
    	_alertslog($('#alertsOption').val(), $('#alertsSearch').val());
    });
    $('.nav.nav-tabs li').each(function(){
        $(this).click(function(){
            if($(this).children().attr('href')=='#timeline'){
                _systemlog();
            }else if($(this).children().attr('href')=='#alertslog'){
            	_alertslog();
            }else{
                _loginlog();
            }
        })
    })
    _loginlog();
	if(window.location.toString().indexOf('#alertslog')>0){
		$('.nav.nav-tabs li:eq(2) a').tab('show');
		_alertslog();
	}
})