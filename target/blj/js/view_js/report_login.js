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

	    var ranges =  {
	        '今天': [moment(), moment()],
	        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	        '最近7天': [moment().subtract(6, 'days'), moment()],
	        '最近30天': [moment().subtract(29, 'days'), moment()],
	        '本月': [moment().startOf('month'), moment().endOf('month')],
	        '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	      }

		var date_range = function(tab, start, end){
			var interval_days = (end - start)/86400;
			if(interval_days<=1){
				$('#'+tab+' .rl-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rl-time button[data-interval=hour]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'hour';
			}else if(interval_days>1&&interval_days<=7){
				$('#'+tab+' .rl-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rl-time button[data-interval=day]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'day';
			}else if(interval_days>7&&interval_days<=62){
				$('#'+tab+' .rl-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rl-time button[data-interval=week]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'week';
			}else if(interval_days>62){
				$('#'+tab+' .rl-time .mr-nav-like .newaddbtn').addClass('newaddbtn2').removeClass('newaddbtn');
            	$('#'+tab+' .rl-time button[data-interval=month]').addClass('newaddbtn').removeClass('newaddbtn2')
				return 'month';
			}
		}
	    //日期控件初始化
	    $('#date_dateVisit').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens"  : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_dateVisit(date_range('A', start/1000, end/1000), start/1000, end/1000);
	    });
	    $('#date_userVisit').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_userVisit('', start/1000, end/1000);
	    });
	    $('#date_dateSuccessFail').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment().subtract(29, 'days'),
	        endDate : moment(),
	        "opens" : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_dateSuccessFail(date_range('C', start/1000, end/1000), start/1000, end/1000);
	    });

	    $('#start_end_date4').daterangepicker({
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
            	$('#modal-default1 .rl-show input[data-interval=hour]')[0].checked = true;
			}else if(interval_days>1&&interval_days<=7){
            	$('#modal-default1 .rl-show input[data-interval=day]')[0].checked = true;
			}else if(interval_days>7&&interval_days<=62){
            	$('#modal-default1 .rl-show input[data-interval=week]')[0].checked = true;
			}else if(interval_days>62){
            	$('#modal-default1 .rl-show input[data-interval=month]')[0].checked = true;
			}
	    });

	};
	
	$('#export').click(function(){
		$('#modal-default1 .rl-type input').each(function(){
			if($(this)[0].checked){
				var interval = $('#modal-default1 .rl-show input[checked=checked]').attr('data-interval');
				var url = '../../export/export'+UpperFirstLetter($(this).val())+'Loginlog?interval='+interval+'&start='+export_start_time+'&end='+export_end_time;
				document.getElementById('hide_'+$(this).val()).src=url;
			}
		})
	})
	
    var _dateVisitChart = function(_data) {
        var myChart = echarts.init(document.getElementById('dateVisit-chart'), 'light');
        var interval = new Array();
        var web = new Array();
        var total = new Array();
        var ssh = new Array();
        var telnet = new Array();
        var rdp = new Array();
        var vnc = new Array();
        var ftp = new Array();
        var sftp = new Array();
        var apppub = new Array();
        for (var i = 0; i < _data.length; i++) {
            interval[i] = _data[i].interval;
            total[i] = _data[i].total;
            ssh[i] = _data[i].ssh;
            telnet[i] = _data[i].telnet;
            rdp[i] = _data[i].rdp;
            vnc[i] = _data[i].vnc;
            ftp[i] = _data[i].ftp;
            sftp[i] = _data[i].sftp;
            apppub[i] = _data[i].apppub;
            web[i] = _data[i].web;
        }
        var title = '按'
            + $('#A .rl-time .mr-nav-like .newaddbtn').text()
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
                data : [ '总数', '前台', 'SSH', 'RDP', 'VNC', 'TELNET', 'FTP', 'SFTP','应用发布' ],
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
                name : 'SSH',
                type : 'bar',
                stack : 'type',
                data : ssh
            }, {
                name : 'RDP',
                type : 'bar',
                stack : 'type',
                data : rdp
            }, {
                name : 'TELNET',
                type : 'bar',
                stack : 'type',
                data : telnet
            }, {
                name : 'VNC',
                type : 'bar',
                stack : 'type',
                data : vnc
            }, {
                name : 'FTP',
                type : 'bar',
                stack : 'type',
                data : ftp
            }, {
                name : 'SFTP',
                type : 'bar',
                stack : 'type',
                data : sftp
            }, {
                name : '应用发布',
                type : 'bar',
                stack : 'type',
                data : apppub
            }, {
                name : '前台',
                type : 'bar',
                stack : 'web',
                data : web
            }, {
                name : '总数',
                type : 'bar',
                data : total
            } ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }

    var _dateVisit = function(interval, start, end) {
    	if(start==null||start==""){
    		$_date = $('dateVisit_date').val() + "";
    		if($_date.indexOf(' - ')>0){
        		$_date = $_date.split(' - ');
        		start = $_date[0];
        		end = $_date[1];
    		}
    	}
        $('#dateVisit').DataTable(
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
                    "url" : "../../loginLog/protocolByDate",
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
                {"data" : "ssh"},
                {"data" : "telnet"},
                {"data" : "rdp",},
                {"data" : "vnc"},
                {"data" : "ftp"},
                {"data" : "sftp"},
                {"data" : "web"},
                {"data" : "apppub"},
                {"data" : "total",
                    "render": function(data,type,row,meta){
                        return '<a  class="newcss1" data-row="'+meta.row+'" data-toggle="modal" data-target="#modal-default4" style="cursor:pointer;">'+data+'</a>';
                    }}],
                "initComplete" : function(settings, json) {
                    $('#dateVisit div').tooltip();
                    _dateVisitChart(json.data);
                }
            })
    }

    var _userVisitChart = function(_data) {
        var myChart = echarts.init(document
            .getElementById('userVisit-chart'), 'light');
        var username = new Array();
        var web = new Array();
        var total = new Array();
        var ssh = new Array();
        var telnet = new Array();
        var rdp = new Array();
        var vnc = new Array();
        var apppub = new Array();
        for (var i = 0; i < _data.length; i++) {
            username[i] = _data[i].username;
            total[i] = _data[i].total;
            ssh[i] = _data[i].ssh;
            telnet[i] = _data[i].telnet;
            rdp[i] = _data[i].rdp;
            vnc[i] = _data[i].vnc;
            apppub[i] = _data[i].apppub;
            web[i] = _data[i].web;
        }
        var title = '按'
            + $('#B .rl-time .mr-nav-like .newaddbtn').text()
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
                data : [ '总数', '前台', 'SSH', 'TELNET','RDP', 'VNC','应用发布' ],
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
                data : username
            } ],
            yAxis : [ {
                type : 'value'
            } ],
            series : [ {
                name : 'SSH',
                type : 'bar',
                stack : 'type',
                data : ssh
            }, {
                name : 'RDP',
                type : 'bar',
                stack : 'type',
                data : rdp
            }, {
                name : 'TELNET',
                type : 'bar',
                stack : 'type',
                data : telnet
            }, {
                name : 'VNC',
                type : 'bar',
                stack : 'type',
                data : vnc
            }, {
                name : '应用发布',
                type : 'bar',
                stack : 'type',
                data : apppub
            }, {
                name : '前台',
                type : 'bar',
                stack : 'type',
                data : web
            }, {
                name : '总数',
                type : 'bar',
                data : total
            } ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        charts.push(myChart)
    }

    var _userVisit = function(interval, start, end) {
        $('#userVisit').DataTable(
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
                    "url" : "../../loginLog/loginUser",
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
                    "data" : "username"
                }, {
                    "data" : "realname"
                }, {
                    "data" : "ssh"
                }, {
                    "data" : "rdp"
                }, {
                    "data" : "telnet"
                }, {
                    "data" : "vnc"
                }, {
                    "data" : "apppub"
                }, {
                    "data" : "web"
                }, {
                    "data" : "total"
                } ],
                "initComplete" : function(settings, json) {
                    $('#userVisit div').tooltip();
                    _userVisitChart(json.data);
                }
            })
    }
    function _dateSuccessFailChart(_data) {

        var data = new Array();
        var interval = new Array();
        var users = new Array();
        var total = new Array();
        var successes = new Array();
        var fails = new Array();
        var sourceips = new Array();
        for (var i = 0; i < _data.length; i++) {
            interval[i] = _data[i].interval;
            users[i] = _data[i].users;
            total[i] = _data[i].total;
            successes[i] = _data[i].successes;
            fails[i] = _data[i].fails;
            sourceips[i] = _data[i].sourceips;
        }
        var title = '按'
            + $('#C .rl-time .mr-nav-like .newaddbtn').text()
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
                trigger : 'axis'
            },
            legend : {
                data : [ '总访问', '成功', '失败', '来源IP数', '用户数' ],
                left : 'left'
            },
            grid : {
                left : '3%',
                right : '4%',
                bottom : '3%',
                containLabel : true
            },
            toolbox : {
                feature : {
                    saveAsImage : {}
                }
            },
            xAxis : {
                type : 'category',
                boundaryGap : false,
                data : interval
            },
            yAxis : {
                type : 'value'
            },
            series : [ {
                name : '用户数',
                type : 'line',
                //stack : '总量',
                data : users
            }, {
                name : '总访问',
                type : 'line',
                //stack : '总量',
                data : total
            }, {
                name : '成功',
                type : 'line',
                //stack : '总量',
                data : successes
            }, {
                name : '失败',
                type : 'line',
                //stack : '总量',
                data : fails
            }, {
                name : '来源IP数',
                type : 'line',
                //stack : '总量',
                data : sourceips
            } ]
        };

        var myChart = echarts.init(document
            .getElementById('dateSuccessFail-chart'), 'light');
        myChart.setOption(option);
        charts.push(myChart)
    }
    var _dateSuccessFail = function(interval, start, end) {
        $('#dateSuccessFail').DataTable(
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
                    "url" : "../../loginLog/loginReports",
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
                    "data" : "users"
                }, {
                    "data" : "total"
                }, {
                    "data" : "successes"
                }, {
                    "data" : "fails"
                }, {
                    "data" : "sourceips"
                } ],
                "initComplete" : function(settings, json) {
                    $('#dateSuccessFail div').tooltip();
                    _dateSuccessFailChart(json.data);
                }
            })
    }

    function _userDateChart(_data) {

        var data = new Array();
        data[0] = [ 'interval', 'username', 'realname', 'total', 'ssh',
            'rdp', 'telnet', 'vnc', 'apppub', 'web' ];
        for (var i = 0; i < _data.length; i++) {
            data[i + 1] = [ _data[i].interval, _data[i].username,
                _data[i].realname, _data[i].total, _data[i].ssh,
                _data[i].rdp, _data[i].telnet, _data[i].vnc, _data[i].apppub,
                _data[i].web ]
        }

        option = {
            grid3D : {},
            tooltip : {},
            xAxis3D : {
                type : 'category',
                axisLabel : {
                    show : true,
                    rotate : 30,
                    align : 'left',
                    color : 'rgba(0,0,0,0.65)',
                },
            },
            yAxis3D : {
                type : 'category'
            },
            zAxis3D : {
                type : 'category'
            },
            visualMap : {
                max : 1e8,
                dimension : 'total'
            },
            dataset : {
                dimensions : [ 'interval', 'username', 'realname', 'total', 'ssh', 'rdp', 'telnet', 'vnc','apppub', 'web' ],
                source : data
            },
            series : [ {
                type : 'bar3D',
                // symbolSize: symbolSize,
                shading : 'lambert',
                encode : {
                    x : 'interval',
                    y : 'username',
                    z : 'total'
                }
            } ]
        };

        var myChart = echarts.init(document
            .getElementById('userDate-chart'), 'light');
        myChart.setOption(option);
        charts.push(myChart)
    }
    var _userDate = function(interval, start, end) {
        $('#userDate').DataTable(
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
                    "url" : "../../loginLog/protocolByDate",
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
                    "data" : "username"
                }, {
                    "data" : "realname"
                }, {
                    "data" : "total"
                }, {
                    "data" : "ssh"
                }, {
                    "data" : "rdp"
                }, {
                    "data" : "telnet"
                }, {
                    "data" : "vnc"
                }, {
                    "data" : "apppub"
                }, {
                    "data" : "web"
                } ],
                "initComplete" : function(settings, json) {
                    $('#userDate div').tooltip();
                    _userDateChart(json.data);
                }
            })
    }

   

    $('.nav.nav-tabs li').each(function() {
        $(this).click(function() {
            if ($(this).children().attr('href') == '#B') {
                _userVisit("day", "", "");
            } else if ($(this).children().attr('href') == '#C') {
                _dateSuccessFail("day", "", "");
            } else if ($(this).children().attr('href') == '#D') {
                _userDate("day", "", "");
            } else {
                _dateVisit("day", "", "");
            }
        })
    })
    _dateVisit("day", "", "");
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


    $('#login_log').DataTable({
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
            "url": "../../loginLog/listLoginLog",
           /* "data": function (d) {
                for (var key in d) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.' + field + '="' + value
                    + '"');
            }*/
        },
        "columns": [
            {"data": "login_datetime",
                /*"render": function (data, type, row, mata) {
                   if(){

                   }
                }*/
            },
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
        "fnDrawCallback": function (settings, json) {
            $('#loginlog div').tooltip();
        }
    });

/*$("#modal-default4").on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    i = button.data('row');
    $('#login_id').val($('#dateVisit').DataTable().row('#' + i).nodes(i).data()[i].interval);
    console.log($('#login_id').val());
    console.log($('#login_log').DataTable().row('#' + i).nodes(i).data()[i].login_datetime);
    var same_time=$('#login_log').DataTable().row('#' + i).nodes(i).data()[i].login_datetime;
    var time = getTime(same_time);
    function getTime(o) {

    }
});*/
