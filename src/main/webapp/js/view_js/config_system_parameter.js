var charts = [];
function douploadLicense(formObj){
if($('#uploadLicenseInput').val()=="") {
    $("#modal-danger .modal-title").text('失败');
    $("#modal-danger .modal-body").text("操作失败!");
    $("#modal-danger").modal();
    return ;
}
$('#loadingModal').modal({backdrop: 'static', keyboard: false});
formObj.submit();
}
function dbrecover(formObj){
$('#modal-recoverupload').modal('hide');
if($('#recoverfileInput').val()=="") {
    $("#modal-danger .modal-title").text('失败');
    $("#modal-danger .modal-body").text("操作失败!");
    $("#modal-danger").modal();
    return ;
}
$('#loadingModal').modal({backdrop: 'static', keyboard: false});
formObj.submit();
}
function doupgrade(formObj){
    $('#modal-upgrade').modal('hide');
    if($('#upgradeInput').val()=="") {
        $("#modal-danger .modal-title").text('失败');
        $("#modal-danger .modal-body").text("操作失败!");
        $("#modal-danger").modal();
        return ;
    }
    $('#loadingModal').modal({backdrop: 'static', keyboard: false});
    formObj.submit();
}

$(function () {
    $('#upgradeButton').click(function(){
        $('#upgradeButton')[0].disabled = true;
        $('#upgradeInput').click();
    })
    $('#recoveruploadButton').click(function(){
        $('#recoveruploadButton')[0].disabled = true;
        $('#recoverfileInput').click();
    });
    $('#uploadLicenseBtn').click(function(){
        $('#uploadLicenseBtn')[0].disabled = true;
        $('#uploadLicenseInput').click();
    })
    $('#recoverButton').click(function(){
        $('#modal-recover').modal('hide');
        $('#loadingModal').modal({backdrop: 'static', keyboard: false});
        $.ajax({
            url:"../../configDbbackup/recover",
            type:"POST",
            data:{
                id:$("#backupid").val()
            },
            success:function(data){
                $('#loadingModal').modal('hide');
                if(data.success){
                    $('#loadingModal').modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg?data.msg:'操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    });
    $('#modal-upgrade').on('show.bs.modal', function (event) {
        $('#upgradeButton')[0].disabled = false;
    });
    $('#modal-recoverupload').on('show.bs.modal', function (event) {
        $('#recoveruploadButton')[0].disabled = false;
    });
    $('#modal-recover').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#backupid').val($('#backuplist').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    $('#modal-recover').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#backupid').val($('#backuplist').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    $('#modal-delbackup').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#backupid').val($('#backuplist').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    function executeCmd(cmd){
        $.ajax({
            url:"../../configDbbackup/"+cmd,
            type:"POST",
            data:{
                'ids[]':$("#backupid").val()
            },
            success:function(data){
                if(data.success){
                    $("#modal-"+cmd).modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功"+data.msg+"!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("操作失败"+data.msg+"!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    };
    $('#applyLicenseBtn').click(function(){
        $.ajax({
            url:"../../configDbbackup/applyLicense",
            type:"POST",
            data:{
            },
            success:function(data){
                if(data.success){
                    $("#modal-"+cmd).modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg?data.msg:"操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    })
    $('#backupLicenseBtn').click(function(){
        $.ajax({
            url:"../../configDbbackup/backupLicense",
            type:"POST",
            data:{
                'ids[]':$("#backupid").val()
            },
            success:function(data){
                if(data.success){
                    $("#modal-"+cmd).modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text(data.msg?data.msg:"操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    })

    $('#rebootButton').click(function(){
        executeCmd('reboot');
    });
    $('#shutdownButton').click(function(){
        executeCmd('shutdown');
    });
    $('#resetButton').click(function(){
        executeCmd('reset');
    });
    $('#delbackupButton').click(function(){
        $.ajax({
            url:"../../configDbbackup/delConfigDbbackup",
            type:"POST",
            data:{
                'ids[]':$("#backupid").val()
            },
            success:function(data){
                if(data.success){
                    loadAJAX('#backuplist');
                    $("#modal-delbackup").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    })
    $("#addbackupbtn").click(function(){
        $.ajax({
            url:"../../configDbbackup/addConfigDbbackup",
            type:"POST",
            data:{
                desc:$("#backupdesc").val()
            },
            success:function(data){
                if(data.success){
                    loadAJAX('#backuplist');
                    $("#modal-addbackup").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("操作成功!");
                    $("#modal-success").modal();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("操作失败!");
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    })
    var _backuplist = function(){
        $('#backuplist').DataTable({
            'paging'      : true,
            "iDisplayLength": 10,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom'         :'t<"bottom"lifp<"clear">>',
            'searching'   : false,
            'orderable'    : false,
            'info'        : true,
            'autoWidth'   : false,
            "serverSide"	: true,
            "destroy":true,
            "ajax" : {
                "url" : "../../configDbbackup/listConfigDbbackup",
                "data" : function(d) {
                    for ( var key in d) {
                        if (key.indexOf("columns") == 0
                            || key.indexOf("order") == 0
                            || key
                                .indexOf("search") == 0) { //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                }
            },
            "columns": [
                { "data": "backup_time"},
                { "data": "desc",
                    "render" : function(data, type,
                                        row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;max-width:400px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                            +data
                            + '</div>';

                    }},
                { "data": "filepath" ,"render":function(data,type,row,meta){
                        return data!=""?"正常":"备份中";
                    }},
                { "data": "filesize" },
                { "data": "id","render":function(data,type,row,meta){
                        return '<a class="newcss1" data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-recover" style="line-height: 2px;cursor:pointer;">还原</a>'
                            + '&nbsp;&nbsp;<a class="newcss1" href="../../configDbbackup/download?id='+data+'" target="hide" style="line-height: 2px;margin-left: 3px;cursor:pointer;">下载</a>'
                            + '&nbsp;&nbsp;<a class="newcss2" data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-delbackup" style="line-height: 2px;margin-left: 3px;cursor:pointer;">删除</a>';
                    }},
            ],
            "fnDrawCallback": function( settings, json ) {
                $('#backuplist div').tooltip();
            }
        })
    }
    _backuplist();
    
    

	
    
   function _cpuUsageChart(data,datetitle,interval){
  	  labels = [];
  	  values = [];
  	  for(var i=0; i<data.length; i++){
  		  labels[i] = data[i].get_datetime;
  		  values[i] = data[i].percent;
  	  }
  	  
  	  option = {
  			    title: {
  			        text: 'CPU使用率',
  			        subtext: '时间:'+datetitle+' 取值间隔:'+interval,
  			        left: 'center',
  			        textStyle: {
  			            fontWeight:'400',
  			            fontSize:'14',
  						color: 'rgba(0,0,0,0.65)',
  			        }
  			    },
  			    tooltip: {
  			        trigger: 'axis',
  			        axisPointer: {
  			            type: 'cross',
  			            label: {
  			                backgroundColor: '#6a7985'
  			            }
  			        }
  			    },
  			    grid: {
  			        left: '3%',
  			        right: '4%',
  			        bottom: '3%',
  			        containLabel: true
  			    },
  		        yAxis: {
  		            axisLabel:{
  		                color: 'rgba(0,0,0,0.65)',
  		            },
  		            axisLine:{
  		                lineStyle:{
  		                    color:' #cccccc',
  		                    symbol:'none'
  		                }
  		            },
  		        },
  			    xAxis: [
  			        {
  			            type: 'category',
  			            boundaryGap: false,
  			            data: labels,
  			            axisLabel:{
  			                show:true,
  			                color: 'rgba(0,0,0,0.65)',
  			            },
  			            axisLine:{
  			                lineStyle:{
  			                    color:'#cccccc',
  			                },
  			            }
  			        },
  		            
  			    ],
  			    series: [
  			        {
  			            name: '百分比',
  			            color:['#1494F7'],
  			            type: 'line',
  			            stack: '总量',
  			            areaStyle:{
  			                    normal:{
  			                        //右，下，左，上
  			                        color:new echarts.graphic.LinearGradient(0,0,0,1,[
  			                            {
  			                                offset:0,
  			                                color:'#1494F7'
  			                            },
  			                            {
  			                                offset:1,
  			                                color:'#fbfefb'
  			                            }
  			                        ],false)
  			                        
  			                    }
  			                },
  			            data: values
  			        }
  			    ]
  			};
  	  var myChart = echarts.init(document.getElementById('cpuUsageChart'),'light');
  	  myChart.setOption(option);
      charts.push(myChart)
  }
   
   function _memoryUsageChart(data,datetitle,interval){
	  	  labels = [];
	  	  values = [];
	  	  for(var i=0; i<data.length; i++){
	  		  labels[i] = data[i].get_datetime;
	  		  values[i] = data[i].percent;
	  	  }
	  	  
	  	  option = {
	  			    title: {
	  			        text: '内存使用率',
	  			        subtext: '时间:'+datetitle+' 取值间隔:'+interval,
	  			        left: 'center',
	  			        textStyle: {
	  			            fontWeight:'400',
	  			            fontSize:'14',
	  						color: 'rgba(0,0,0,0.65)',
	  			        }
	  			    },
	  			    tooltip: {
	  			        trigger: 'axis',
	  			        axisPointer: {
	  			            type: 'cross',
	  			            label: {
	  			                backgroundColor: '#6a7985'
	  			            }
	  			        }
	  			    },
	  			    grid: {
	  			        left: '3%',
	  			        right: '4%',
	  			        bottom: '3%',
	  			        containLabel: true
	  			    },
	  		        yAxis: {
	  		            axisLabel:{
	  		                color: 'rgba(0,0,0,0.65)',
	  		            },
	  		            axisLine:{
	  		                lineStyle:{
	  		                    color:' #cccccc',
	  		                    symbol:'none'
	  		                }
	  		            },
	  		        },
	  			    xAxis: [
	  			        {
	  			            type: 'category',
	  			            boundaryGap: false,
	  			            data: labels,
	  			            axisLabel:{
	  			                show:true,
	  			                color: 'rgba(0,0,0,0.65)',
	  			            },
	  			            axisLine:{
	  			                lineStyle:{
	  			                    color:'#cccccc',
	  			                },
	  			            }
	  			        },
	  		            
	  			    ],
	  			    series: [
	  			        {
	  			            name: '百分比',
	  			            color:['#1494F7'],
	  			            type: 'line',
	  			            stack: '总量',
	  			            areaStyle:{
	  			                    normal:{
	  			                        //右，下，左，上
	  			                        color:new echarts.graphic.LinearGradient(0,0,0,1,[
	  			                            {
	  			                                offset:0,
	  			                                color:'#1494F7'
	  			                            },
	  			                            {
	  			                                offset:1,
	  			                                color:'#fbfefb'
	  			                            }
	  			                        ],false)
	  			                        
	  			                    }
	  			                },
	  			            data: values
	  			        }
	  			    ]
	  			};
	  	  var myChart = echarts.init(document.getElementById('memoryUsageChart'),'light');
	  	  myChart.setOption(option);
	      charts.push(myChart)
	  }
   function _diskUsageChart(data,datetitle,interval){
	  	  labels = [];
	  	  values = [];
	  	  for(var i=0; i<data.length; i++){
	  		  labels[i] = data[i].get_datetime;
	  		  values[i] = data[i].percent;
	  	  }
	  	  
	  	  option = {
	  			    title: {
	  			        text: '磁盘使用率',
	  			        subtext: '时间:'+datetitle+' 取值间隔:'+interval,
	  			        left: 'center',
	  			        textStyle: {
	  			            fontWeight:'400',
	  			            fontSize:'14',
	  						color: 'rgba(0,0,0,0.65)',
	  			        }
	  			    },
	  			    tooltip: {
	  			        trigger: 'axis',
	  			        axisPointer: {
	  			            type: 'cross',
	  			            label: {
	  			                backgroundColor: '#6a7985'
	  			            }
	  			        }
	  			    },
	  			    grid: {
	  			        left: '3%',
	  			        right: '4%',
	  			        bottom: '3%',
	  			        containLabel: true
	  			    },
	  		        yAxis: {
	  		            axisLabel:{
	  		                color: 'rgba(0,0,0,0.65)',
	  		            },
	  		            axisLine:{
	  		                lineStyle:{
	  		                    color:' #cccccc',
	  		                    symbol:'none'
	  		                }
	  		            },
	  		        },
	  			    xAxis: [
	  			        {
	  			            type: 'category',
	  			            boundaryGap: false,
	  			            data: labels,
	  			            axisLabel:{
	  			                show:true,
	  			                color: 'rgba(0,0,0,0.65)',
	  			            },
	  			            axisLine:{
	  			                lineStyle:{
	  			                    color:'#cccccc',
	  			                },
	  			            }
	  			        },
	  		            
	  			    ],
	  			    series: [
	  			        {
	  			            name: '百分比',
	  			            color:['#1494F7'],
	  			            type: 'line',
	  			            stack: '总量',
	  			            areaStyle:{
	  			                    normal:{
	  			                        //右，下，左，上
	  			                        color:new echarts.graphic.LinearGradient(0,0,0,1,[
	  			                            {
	  			                                offset:0,
	  			                                color:'#1494F7'
	  			                            },
	  			                            {
	  			                                offset:1,
	  			                                color:'#fbfefb'
	  			                            }
	  			                        ],false)
	  			                        
	  			                    }
	  			                },
	  			            data: values
	  			        }
	  			    ]
	  			};
	  	  var myChart = echarts.init(document.getElementById('diskUsageChart'),'light');
	  	  myChart.setOption(option);
	      charts.push(myChart)
	  }
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
	    $('#cpuUsage-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment(),
	        endDate : moment(),
	        "opens"  : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_cpuUsage(start.format('YYYY-MM-DD'),end.format('YYYY-MM-DD'));
	    });
	    $('#memoryUsage-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment(),
	        endDate : moment(),
	        "opens"  : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_memoryUsage(start.format('YYYY-MM-DD'),end.format('YYYY-MM-DD'));
	    });
	    $('#diskUsage-date').daterangepicker({
	        'locale' : locale,
	        showDropdowns : true,
	        autoApply : false,
	        startDate : moment(),
	        endDate : moment(),
	        "opens"  : "right",
	        "ranges" : ranges
	    },function(start, end, label) {
	    	_diskUsage(start.format('YYYY-MM-DD'),end.format('YYYY-MM-DD'));
	    });
   
    var _cpuUsage = function(start, end){
    	if(start==null || start==""){
    		$_date = $('#cpuUsage-date').val() + "";
    		if($_date.indexOf(' - ')>0){
    			$_date = $_date.split(' - ');
    			start = $_date[0];
    			end = $_date[1];
    		}
    	}
	   var interval = $('#cpuUsageDate .mr-nav-like .newaddbtn').parent().attr('data-interval');
	   interval = interval?interval:5;
	   $.ajax({
		    url:"../../systemUsage/listSystemUsage",
		    type:"POST",
		    data:{
		    	'type':'cpu',
		    	'interval':interval,
		    	'start_date':start,
		    	'end_date':end,
		    	'':(new Date()).getTime()
		    	},
		    success:function(data){
		    	_cpuUsageChart(data.data,start==end?start:$('#cpuUsage-date').val(),interval==60?'1小时':interval+"分钟")
		    },
		    error:function(){}
		})
     }
   
     var _memoryUsage = function(start, end){
     	if(start==null || start==""){
	    	$_date = $('#memoryUsage-date').val() + "";
			if($_date.indexOf(' - ')>0){
	    		$_date = $_date.split(' - ');
	    		start = $_date[0];
	    		end = $_date[1];
			}
     	}
	   var interval = $('#memoryUsageDate .mr-nav-like .newaddbtn').parent().attr('data-interval');
	   interval = interval?interval:5;
		$.ajax({
		    url:"../../systemUsage/listSystemUsage",
		    type:"POST",
		    data:{
		    	'type':'memory',
		    	'interval':interval,
		    	'start_date':start,
		    	'end_date':end,
		    	'':(new Date()).getTime()
		    	},
		    success:function(data){
		    	_memoryUsageChart(data.data,start==end?start:$('#memoryUsage-date').val(),interval==60?'1小时':interval+"分钟")
		    },
		    error:function(){}
		})
     }
	 var _diskUsage = function(start, end){
	     if(start==null || start==""){
			$_date = $('#diskUsage-date').val() + "";
			if($_date.indexOf(' - ')>0){
				$_date = $_date.split(' - ');
				start = $_date[0];
				end = $_date[1];
			}
	     }
	   var interval = $('#diskUsageDate .mr-nav-like .newaddbtn').parent().attr('data-interval');
	   interval = interval?interval:5;
		$.ajax({
		    url:"../../systemUsage/listSystemUsage",
		    type:"POST",
		    data:{
		    	'type':'disk',
		    	'interval':interval,
		    	'start_date':start,
		    	'end_date':end,
		    	'':(new Date()).getTime()
		    	},
		    success:function(data){
		    	_diskUsageChart(data.data,start==end?start:$('#diskUsage-date').val(),interval==60?'1小时':interval+"分钟")
		    },
		    error:function(){}
		})
	}
	
	
	$('#cpuUsageDate .mr-nav-like li button').click(
	    function() {
	        $('#cpuUsageDate .mr-nav-like .newaddbtn').addClass(
	            'newaddbtn2').removeClass('newaddbtn')
	        $(this).addClass('newaddbtn').removeClass('newaddbtn2')
	        _cpuUsage();
	    });
	
	$('#memoryUsageDate .mr-nav-like li button').click(
	    function() {
	        $('#memoryUsageDate .mr-nav-like .newaddbtn').addClass(
	            'newaddbtn2').removeClass('newaddbtn')
	        $(this).addClass('newaddbtn').removeClass('newaddbtn2')
	        _memoryUsage();
	    });
	
	$('#diskUsageDate .mr-nav-like li button').click(
	    function() {
	        $('#diskUsageDate .mr-nav-like .newaddbtn').addClass(
	            'newaddbtn2').removeClass('newaddbtn')
	        $(this).addClass('newaddbtn').removeClass('newaddbtn2')
	        _diskUsage();
	    })
	 _cpuUsage();_memoryUsage();_diskUsage();
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
})