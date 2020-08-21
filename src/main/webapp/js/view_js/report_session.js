var charts = [];
var export_start_time = moment().subtract(29, 'days')/1000;
var export_end_time = moment()/1000;
$(function() {
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

		var date_range = function(tab, start, end){
			var interval_days = (end - start)/86400;
			if(interval_days<=1){
				$('#'+tab+' .rs-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rs-time button[data-interval=hour]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'hour';
			}else if(interval_days>1&&interval_days<=7){
				$('#'+tab+' .' +
                    'time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rs-time button[data-interval=day]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'day';
			}else if(interval_days>7&&interval_days<=62){
				$('#'+tab+' .rs-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rs-time button[data-interval=week]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'week';
			}else if(interval_days>62){
				$('#'+tab+' .rs-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rs-time button[data-interval=month]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'month';
			}
		}
	    var ranges =  {
	        '今天': [moment(), moment()],
	        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	        '最近7天': [moment().subtract(6, 'days'), moment()],
	        '最近30天': [moment().subtract(29, 'days'), moment()],
	        '本月': [moment().startOf('month'), moment().endOf('month')],
	        '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	      }
	    //日期控件初始化
	    $('#A-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens"  : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_A($($('#A .rs-time .mr-nav-like .newaddbtn')).attr('data-interval'), start/1000, end/1000);
	    });
	    $('#B-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_B('', start/1000, end/1000);
	    });
	    $('#C-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_C($($('#C .rs-time .mr-nav-like .newaddbtn')).attr('data-interval'), start/1000, end/1000);
	    });
	    $('#D-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_D($($('#D .rs-time .mr-nav-like .newaddbtn')).attr('data-interval'), start/1000, end/1000);
	    });

		$('#export-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	export_start_time = start/1000;
			export_end_time = end/1000;
	    	var interval_days = (end/1000 - start/1000)/86400;
			if(interval_days<=1){
            	$('#modal-default1 .rs-show input[data-interval=hour]')[0].checked = true;
			}else if(interval_days>1&&interval_days<=7){
            	$('#modal-default1 .rs-show input[data-interval=day]')[0].checked = true;
			}else if(interval_days>7&&interval_days<=62){
            	$('#modal-default1 .rs-show input[data-interval=week]')[0].checked = true;
			}else if(interval_days>62){
            	$('#modal-default1 .rs-show input[data-interval=month]')[0].checked = true;
			}
	    });

	};
	
	$('#export').click(function(){
		$('#modal-default1 .rs-type input').each(function(){
			if($(this)[0].checked){
				var interval = $('#modal-default1 .rs-show input[checked=checked]').attr('data-interval');
				var url = '../../export/export'+UpperFirstLetter($(this).val())+'RecordReportlog?interval='+interval+'&start='+export_start_time+'&end='+export_end_time;
				document.getElementById('hide_'+$(this).val()).src=url;
			}
		})
	});
	
    var _AChart = function(_data) {
        var myChart = echarts.init(document
            .getElementById('A-chart'), 'light');
        var interval = new Array();
        var deviceips = new Array();
        var users = new Array();
        for (var i = 0; i < _data.length; i++) {
            interval[i] = _data[i].interval;
            deviceips[i] = _data[i].deviceips;
            users[i] = _data[i].users;
        }
        var title = '按'
            + $('#A .rs-time .mr-nav-like .newaddbtn').text()
            + '访问量';
        option = {
            title : {
                text : title,
                left : 'center',
                textStyle : {
                    fontWeight : '400',
                    fontSize : '14',
                    color : 'rgba(0,0,0,0.65)',
                }
            },
            tooltip : {
                trigger : 'axis',
                axisPointer : { // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend : {
                data : [ '设备数', '用户数', ],
                left : 'left'
            },
            grid : {
                left : '3%',
                right : '4%',
                bottom : '3%',
                containLabel : true
            },
            xAxis : [ {
                type : 'category',
                data : interval
            } ],
            yAxis : [ {
                type : 'value'
            } ],
            series : [ {
                name : '设备数',
                type : 'line',
                data : deviceips
            }, {
                name : '用户数',
                type : 'line',
                data : users
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }

    var _A = function(interval, start, end) {
    	if(start==null||start==""){
    		$_date = $('A-date').val() + "";
    		if($_date.indexOf(' - ')>0){
        		$_date = $_date.split(' - ');
        		start = $_date[0];
        		end = $_date[1];
    		}
    	}
        $('#A-table').DataTable(
            {
                'paging' : true,
                "iDisplayLength" : 10,
                'lengthChange' : true,
                "lengthMenu" : [ [ 10, 25, 50, 100 ],
                    [ "10条/页", "25条/页", "50条/页", "100条/页" ] ],
                'dom' : 't<"bottom"lifp<"clear">>',
                'searching' : false,
                'orderable' : false,
                'info' : true,
                'autoWidth' : false,
                "serverSide" : true,
                "destroy" : true,
                "ajax" : {
                    "url" : "../../deviceRecord/deviceRecordReports",
                    "data" : function(d) {
                        for ( var key in d) {
                            if (key.indexOf("columns") == 0
                                || key.indexOf("order") == 0
                                || key.indexOf("search") == 0) { //以columns开头的参数删除
                                delete d[key];
                            }
                        }
                        eval('d.interval="' + interval + '"');
                        eval('d.start_date="' + start + '"');
                        eval('d.end_date="' + end + '"');
                    }
                },
                "columns" : [
                    {"data" : "interval"},
                    {"data" : "users","render": function(data,type,row,meta){
                            return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-user" style="cursor:pointer;">'+data+'</a>';
                        }
                    },
                    {"data" : "deviceips",
                        "render": function(data,type,row,meta){
                            return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-device" style="cursor:pointer;">'+data+'</a>';
                        }},
                    {"data" : "device_ip"},
                    {"data" : "serverips",
                        "render": function(data,type,row,meta){
                            return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-app" style="cursor:pointer;">'+data+'</a>';
                        }},
                    {"data" : "server_ip",
                    }],
                "initComplete" : function(settings, json) {
                    $('#A div').tooltip();
                    _AChart(json.data);
                }
            })
    };

    var _BChart = function(_data) {
        var myChart = echarts.init(document.getElementById('B-chart'), 'light');
        var name = new Array();
        var total = new Array();
        var _name = $('#B .rs-time .newaddbtn').attr('data-interval');
        for (var i = 0; i < _data.length; i++) {
            if(_name=='device'){
            	name[i] = _data[i].device_name + '('+ _data[i].device_ip+')';
            }else{
            	name[i] = _data[i].username + '('+ _data[i].realname+')';
            }
            total[i] = _data[i].total;
        }
        var title = '按'
            + $('#B .rs-time .mr-nav-like .newaddbtn').text()
            + '时长(秒)';
        option = {
            title : {
                text : title,
                left : 'center',
                textStyle : {
                    fontWeight : '400',
                    fontSize : '14',
                    color : 'rgba(0,0,0,0.65)',
                }
            },
            tooltip : {
                trigger : 'axis',
                axisPointer : { // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend : {
                data : ['时长'],
                left : 'left'
            },
            grid : {
                left : '3%',
                right : '4%',
                bottom : '3%',
                containLabel : true
            },
            xAxis : [{
                type : 'category',
                data : name
            } ],
            yAxis : [{
                type : 'value'
            }],
            series : [{
                name : '时长',
                type : 'line',
                stack : 'type',
                data : total
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }
    
    var _B = function(name, start, end) {
    	if(start==null||start==""){
    		$_date = $('B-date').val() + "";
    		if($_date.indexOf(' - ')>0){
        		$_date = $_date.split(' - ');
        		start = $_date[0];
        		end = $_date[1];
    		}
    	}
    	//var name = $('#B .rs-time .newaddbtn').attr('data-interval');
    	if(name=="device"){
        	var columns =  [ {
                "data" : "interval"
            },  {
                "data" : "device_name"
            }, {
                "data" : "device_ip"
            }, {
                "data" : "protocol_name"
            },  {
                "data" : "total", "render": function(data,type,row,meta){
                    return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-session" style="cursor:pointer;">'+data+'</a>';
                }
            }];
    	}else{
        	var columns =  [  {
                "data" : "interval"
            }, {
                "data" : "username"
            }, {
                "data" : "realname"
            },  {
                "data" : "protocol_name"
            }, {
                "data" : "total", "render": function(data,type,row,meta){
                    return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-session" style="cursor:pointer;">'+data+'</a>';
                }
            }];
    	}
    	$('#B-table-user').addClass('hide');
    	$('#B-table-device').addClass('hide');
    	$('#B-table-'+name).removeClass('hide');
        $('#B-table-'+name).DataTable(
            {
                'paging' : true,
                "iDisplayLength" : 10,
                'lengthChange' : true,
                "lengthMenu" : [ [ 10, 25, 50, 100 ],
                    [ "10条/页", "25条/页", "50条/页", "100条/页" ] ],
                'dom' : 't<"bottom"lifp<"clear">>',
                'searching' : false,
                'orderable' : false,
                'info' : true,
                'autoWidth' : false,
                "serverSide" : true,
                "destroy" : true,
                "ajax" : {
                    "url" : "../../deviceRecord/deviceRecordTimeLongReports",
                    "data" : function(d) {
                        for ( var key in d) {
                            if (key.indexOf("columns") == 0
                                || key.indexOf("order") == 0
                                || key.indexOf("search") == 0) { //以columns开头的参数删除
                                delete d[key];
                            }
                        }
                        eval('d.name="' + name + '"');
                        eval('d.start_date="' + start + '"');
                        eval('d.end_date="' + end + '"');
                    }
                },
                "columns" :columns,
                "initComplete" : function(settings, json) {
                    $('#B div').tooltip();
                    _BChart(json.data);
                }
            })
    }

    var _CChart = function(_data) {
        var myChart = echarts.init(document
            .getElementById('C-chart'), 'light');
        var name = new Array();
        var total = new Array();
        for (var i = 0; i < _data.length; i++) {
        	name[i] = _data[i].command;
        	total[i] = _data[i].total;
        }
        var title = '按'
            + $('#C .rs-time .mr-nav-like .newaddbtn').text()
            + '访问量';
        option = {
            title : {
                text : title,
                left : 'center',
                textStyle : {
                    fontWeight : '400',
                    fontSize : '14',
                    color : 'rgba(0,0,0,0.65)',
                }
            },
            tooltip : {
                trigger : 'axis',
                axisPointer : { // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend : {
                data : [ '次数'],
                left : 'left'
            },
            grid : {
                left : '3%',
                right : '4%',
                bottom : '3%',
                containLabel : true
            },
            xAxis : [ {
                type : 'category',
                data : name
            } ],
            yAxis : [ {
                type : 'value'
            } ],
            series : [ {
                name : '次数',
                type : 'line',
                stack : 'type',
                data : total
            } ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }
    
    var _C = function(interval, start, end) {
        $('#C-table').DataTable(
            {
                'paging' : true,
                "iDisplayLength" : 10,
                'lengthChange' : true,
                "lengthMenu" : [ [ 10, 25, 50, 100 ],
                    [ "10条/页", "25条/页", "50条/页", "100条/页" ] ],
                'dom' : 't<"bottom"lifp<"clear">>',
                'searching' : false,
                'orderable' : false,
                'info' : true,
                'autoWidth' : false,
                "serverSide" : true,
                "destroy" : true,
                "ajax" : {
                    "url" : "../../deviceRecord/deviceCommandReports",
                    "data" : function(d) {
                        for ( var key in d) {
                            if (key.indexOf("columns") == 0
                                || key.indexOf("order") == 0
                                || key.indexOf("search") == 0) { //以columns开头的参数删除
                                delete d[key];
                            }
                        }
                        eval('d.interval="' + interval + '"');
                        eval('d.start_date="' + start + '"');
                        eval('d.end_date="' + end + '"');
                    }
                },
                "columns" : [ {
                    "data" : "interval"
                }, {
                    "data" : "command"
                }, {
                    "data" : "total", "render": function(data,type,row,meta){
            return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-command" style="cursor:pointer;">'+data+'</a>';
                 }
                }],
                "initComplete" : function(settings, json) {
                    $('#C div').tooltip();
                    _CChart(json.data);
                }
            })
    }
    

	
    var _DChart = function(_data) {
        var myChart = echarts.init(document
            .getElementById('D-chart'), 'light');
        var interval = new Array();
        var system = new Array();
        var ssh = new Array();
        for (var i = 0; i < _data.length; i++) {
            interval[i] = _data[i].interval;
            system[i] = _data[i].system;
            ssh[i] = _data[i].ssh;
        }
        var title = '按'
            + $('#D .rs-time .mr-nav-like .newaddbtn').text()
            + '访问量';
        option = {
            title : {
                text : title,
                left : 'center',
                textStyle : {
                    fontWeight : '400',
                    fontSize : '14',
                    color : 'rgba(0,0,0,0.65)',
                }
            },
            tooltip : {
                trigger : 'axis',
                axisPointer : { // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend : {
                data : [ '设备', '用户', ],
                left : 'left'
            },
            grid : {
                left : '3%',
                right : '4%',
                bottom : '3%',
                containLabel : true
            },
            xAxis : [ {
                type : 'category',
                data : interval
            } ],
            yAxis : [ {
                type : 'value'
            } ],
            series : [ {
                name : '系统',
                type : 'line',
                data : system
            }, {
                name : 'SSH',
                type : 'line',
                data : ssh
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }

    var _D = function(interval, start, end) {
    	if(start==null||start==""){
    		$_date = $('D-date').val() + "";
    		if($_date.indexOf(' - ')>0){
        		$_date = $_date.split(' - ');
        		start = $_date[0];
        		end = $_date[1];
    		}
    	}
        $('#D-table').DataTable(
            {
                'paging' : true,
                "iDisplayLength" : 10,
                'lengthChange' : true,
                "lengthMenu" : [ [ 10, 25, 50, 100 ],
                    [ "10条/页", "25条/页", "50条/页", "100条/页" ] ],
                'dom' : 't<"bottom"lifp<"clear">>',
                'searching' : false,
                'orderable' : false,
                'info' : true,
                'autoWidth' : false,
                "serverSide" : true,
                "destroy" : true,
                "ajax" : {
                    "url" : "../../deviceRecord/alertReports",
                    "data" : function(d) {
                        for ( var key in d) {
                            if (key.indexOf("columns") == 0
                                || key.indexOf("order") == 0
                                || key.indexOf("search") == 0) { //以columns开头的参数删除
                                delete d[key];
                            }
                        }
                        eval('d.interval="' + interval + '"');
                        eval('d.start_date="' + start + '"');
                        eval('d.end_date="' + end + '"');
                    }
                },
                "columns" : [ {
                    "data" : "interval"
                }, {
                    "data" : "high_level", "render": function(data,type,row,meta){
                        return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-Level" style="cursor:pointer;">'+data+'</a>';
                    }
                }, {
                    "data" : "middle_level", "render": function(data,type,row,meta){
                        return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-Level" style="cursor:pointer;">'+data+'</a>';
                    }
                }, {
                    "data" : "low_level", "render": function(data,type,row,meta){
                        return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-Level" style="cursor:pointer;">'+data+'</a>';
                    }
                }],
                "initComplete" : function(settings, json) {
                    $('#D div').tooltip();
                    _DChart(json.data);
                }
            })
    };

    $('#A .rs-time .mr-nav-like li button').click(
        function() {
            $('#A .rs-time .mr-nav-like .newaddbtn').addClass(
                'newaddbtn2').removeClass('newaddbtn')
            $(this).addClass('newaddbtn').removeClass('newaddbtn2').css(
                    'width', '50px');
            _A($(this).attr('data-interval'), "", "");
        });

    $('#B .rs-time .mr-nav-like li button').click(
        function() {
            $('#B .rs-time .mr-nav-like .newaddbtn').addClass(
                'newaddbtn2').removeClass('newaddbtn')
            $(this).addClass('newaddbtn').removeClass('newaddbtn2').css(
                    'width', '50px');
            _B($(this).attr('data-interval'), "", "");
        });
    
    $('#D .rs-time .mr-nav-like li button').click(
        function() {
            $('#D .rs-time .mr-nav-like .newaddbtn').addClass(
                'newaddbtn2').removeClass('newaddbtn')
            $(this).addClass('newaddbtn').removeClass('newaddbtn2').css(
                    'width', '50px');
            _D($(this).attr('data-interval'), "", "");
        })

    $('.nav.nav-tabs li').each(function() {
        $(this).click(function() {
            if ($(this).children().attr('href') == '#A') {
                _A("day", "", "");
            } else if ($(this).children().attr('href') == '#B') {
                _B("device", "", "");
            } else if ($(this).children().attr('href') == '#C') {
                _C("day", "", "");
            } else {
                _D("day", "", "");
            }
        })
    });
    
    _A("day", "", "");
    $(window).resize(function() {
        for (var i = 0; i < charts.length; i++) {
            charts[i].resize();
        }
    });
    $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
        for (var i = 0; i < charts.length; i++) {
            charts[i].resize();
        }
    });
	init();
});
//用户
$('#user_table').DataTable({
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
    },
    "columns": [
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
});

//设备


//应用
$('#app_table').DataTable({
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
    "destroy" : true,
    "ajax": {
        "url":"../../apppubRecord/listApppubRecord",
/*        "data": function (d) {
            for(var key in d){
                if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                    delete d[key];
                }
            }
            eval('d.' + field + '="' + value + '"');
            if(where!=null){
                for(k in where){
                    d[k]=where[k];
                }
            }
        }*/
    },
    "columns": [
        { "data": "name" },
        { "data": "program" },
        { "data": "apppub_account_id" },
        { "data": "client_ip" },
        { "data": "username" },
        { "data": "realname" },
    ],
    "fnDrawCallback": function( settings, json ) {
        $('#apppubsessions div').tooltip();
    }
});

//会话
//命令
$("#modal-command").on('show.bs.modal', function (event) {
     var button = $(event.relatedTarget);
     i = button.data('row');
     var title=$('#C-table').DataTable().row('#' + i).nodes(i).data()[i].command;
     console.log(title);
     $("#modal-command .modal-title").text("统计数据["+""+title+"]");
});
//告警