$(function () {
  /* ChartJS
   * -------
   * Here we will create a few charts using ChartJS
   */

  // -----------------------
  // - MONTHLY SALES CHART -
	// -----------------------
function _visitByDay(data){
	  labels = [];
	  values = [];
	  for(var i=0; i<data.length; i++){
		  labels[i] = data[i].day+'日';
		  values[i] = data[i].ct;
	  }
	  
	  option = {
			    title: {
			        text: '最近一周访问量',
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
			            name: '访问次数',
			            color:['#1494F7'],
			            type: 'line',
			            stack: '总量',
			            areaStyle:{
			                    normal:{
			                        //右，下，左，上
			                        color:new echarts.graphic.LinearGradient(0,0,0,1,[
			                            {
			                                offset:0,
			                                color:'#4FABF7'
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
	  var myChart = echarts.init(document.getElementById('salesChart'),'light');
	  myChart.setOption(option);
}

  

function _visitByUser(data){
	var names = [];
	var values = [];
	for(var i=0; i<data.length; i++){
		names[i]  = data[i]['realname'];
		values[i] = data[i]['ct'];
	}
    var myChart = echarts.init(document.getElementById('bar-chart'),'light');
    // 指定图表的配置项和数据
    var option = {
        legend: {
            data:['登录次数'],
            x:'80%',
            icon:'circle'
        },
        title: {
            text: '',
        },
		grid: {
			left: '3%',
			right: '10%',
			bottom: '3%',
			containLabel: true
		},
        xAxis: {
            data: names,
            axisLabel:{
                show:true,
                rotate: 30,
                color: 'rgba(0,0,0,0.65)',
            },
            axisLine:{
                lineStyle:{
                    color:'#cccccc',
					width:'1',
                },
            },
        },
        yAxis: {
            axisLabel:{
                color: 'rgba(0,0,0,0.65)',
            },
            axisLine:{
                lineStyle:{
                    color:'#cccccc',

                }
            },
            splitLine: {
                show: true,
                interval: 'auto',
                lineStyle: {
                    color: '#d9d9d9',
                }
            },
        },
        axisTick: {           //坐标轴刻线
            show: true
        },
        series: [{
            name: '登录次数',
            type: 'bar',
            data: values,
            barWidth:'10px',
            color:'#1494F7',
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


var pieOptions     = {
  // Boolean - Whether we should show a stroke on each segment
  segmentShowStroke    : true,
  // String - The colour of each segment stroke
  segmentStrokeColor   : '#fff',
  // Number - The width of each segment stroke
  segmentStrokeWidth   : 2,
  // Number - The percentage of the chart that we cut out of the middle
  percentageInnerCutout: 70, // This is 0 for Pie charts
  // Number - Amount of animation steps
  animationSteps       : 100,
  // String - Animation easing effect
  animationEasing      : 'easeOutBounce',
  // Boolean - Whether we animate the rotation of the Doughnut
  animateRotate        : true,
  // Boolean - Whether we animate scaling the Doughnut from the centre
  animateScale         : false,
  // Boolean - whether to make the chart responsive to window resizing
  responsive           : true,
  // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
  maintainAspectRatio  : false,
  // String - A legend template
  legendTemplate       : '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<segments.length; i++){%><li><span style=\'background-color:<%=segments[i].fillColor%>\'></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>',
  // String - A tooltip template
  tooltipTemplate      : '<%=value %> <%=label%> users'
};

function devicesByType(data,total){
	var pieChartCanvas = $('#pieChart').get(0).getContext('2d');
	var pieChart = new Chart(pieChartCanvas);
	var PieColor = ['#FF3F5E','#0094FF','#1494F7','#FFD835'];
	var PieData = [];
	for(var i=0; i<data.length; i++){
		PieData[i] = {	
						value    : data[i].count,
			            color    : PieColor[i],
			            highlight: PieColor[i],
			            label    : data[i].name 
			         }
		$('#devicesByTypeName').append('<li><i class="fa fa-circle" style="color: '+PieColor[i]+';margin-right: 7px;"></i> '+data[i].name+'</li>');
		$('#devicesByTypeValue').append('<li><a href="#" style="color: '+PieColor[i]+';">'+data[i].name+'<span class="pull-right" style="color: rgba(0,0,0,0.65);font-size: 14px;">'+data[i].count+'</span></a></li>');			
	}
	pieChart.Doughnut(PieData, pieOptions);
}

function apppubByProgram(data,total){
	var PieDataColor = ['#00CFB2','#5571F5','#3194F3','#1494F7']
	var pieChartCanvas = $('#pieChart2').get(0).getContext('2d');
	var pieChart       = new Chart(pieChartCanvas);
	var PieData = [];
	for(var i=0; i<data.length; i++){
		PieData[i] = {	
						value    : data[i].count,
			            color    : PieDataColor[i],
			            highlight: PieDataColor[i],
			            label    : data[i].name 
			         }
		$('#apppubByTypeName').append('<li><i class="fa fa-circle" style="color: '+PieDataColor[i]+';margin-right: 7px;"></i> '+data[i].name+'</li>');
		$('#apppubByTypeValue').append('<li><a href="#" style="color: '+PieDataColor[i]+';">'+data[i].name+'<span class="pull-right" style="color: rgba(0,0,0,0.65);font-size: 14px;">'+data[i].count+'</span></a></li>');			
	}
	pieChart.Doughnut(PieData, pieOptions);
}

$.ajax({
    url:"../../systeminfo/all",
    type:"POST",
    data:(new Date()).getTime(),
    success:function(data){
        $('#cpuinfo .progress-number').html('<b>'+data.cpu_used+'</b>/'+data.cpu_total);
        $('#cpuinfo .progress-bar').css('width', data.cpu_userate*100+'%');
        $('#memoryinfo .progress-number').html('<b>'+data.memory_used_human+'</b>/'+data.memory_total_human);
        $('#memoryinfo .progress-bar').css('width', data.memory_userate*100+'%');
        $('#diskinfo .progress-number').html('<b>'+data.disk_used_human+'</b>/'+data.disk_total_human);
        $('#diskinfo .progress-bar').css('width', data.disk_userate*100+'%');
        $('#networkinfo .progress-number').html('<b>'+data.network_receive_human+'</b>/'+data.network_send_human);
        $('#networkinfo .progress-bar').css('width', data.disk_userate*100+'%');
    },
    error:function(){}
})

$.ajax({
    url:"../../systeminfo/sysdata",
    type:"POST",
    data:(new Date()).getTime(),
    success:function(data){
        
        $('#userct').html(data.userct);
        $('#devicect').html(data.devicect);
        $('#apppubct').html(data.apppubct);
        $('#alertct').html(data.alertct);
        
        devicesByType(data.devicesByType,data.devicect);
        apppubByProgram(data.apppubByProgram,data.appprogramct);

        $('#text30increate').html(data.getTextTotal);
    	$('#text30increatepercent').html(data.device30DayTextIncrease);
    	$('#graph30increate').html(data.getGraphTotal);
    	$('#graph30increatepercent').html(data.device30DayGraphIncrease);
    	$('#apppub30increate').html(data.getApppubRecordTotal);
    	$('#apppub30increatepercent').html(data.apppub30DayIncrease);
        $('#filetransfer30increate').html(0);
        _visitByDay(data.visitByDay);
        _visitByUser(data.visitByUser);
    },
    error:function(){}
})

function webonline(){
	$.ajax({
	    url:"../../sysSession/listSysSession",
	    type:"POST",
	    data:{
	    	'start': 0,
	    	'length': 4,
	    	'':(new Date()).getTime()
	    },
	    success:function(data){
	    	data = data.data;
	    	console.log(data)
	    	for(var i=0; i<data.length; i++){
	    		$('#onlineuser'+(i+1)+'name').html(data[i].username);
	    		$('#onlineuser'+(i+1)+'time').html(data[i].logintime);
	    		$('#onlineuser'+(i+1)+'info').html(data[i].realname+' '+data[i].last_login_ip);
	    		$('#onlineuser'+(i+1)).css('display','');
				console.log(data[i].rolename)
	    		if (data[i].rolename=="系统管理员"){
					console.log("2")
					$('#onlineuser'+(i+1)+'image').attr("src","../../bower_components/dist/img/admin.png");
				}else {
					console.log("!")
					$('#onlineuser'+(i+1)+'image').attr("src","../../bower_components/dist/img/operation_user.png");
				}

	    	}
	    },
	    error:function(){}
	})
}
webonline();
var cutOnline=function (url){
	 $.ajax({
        url:url,
        type:"GET",
        success:function(data){
       	 if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('断开成功');
                $("#modal-success").modal();
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('断开失败');
                $("#modal-danger").modal();
            }
        }
    })
}
var _devicesonline = function (){

    $('#deviceonline').DataTable({
      'paging'      : false,
      'lengthChange': false,
        'dom'         :'t<"bottom"lifp<"clear">>',
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false,
      "serverSide"	: true,
      'sEmptyTable' : '暂无实时会话',
        'destroy'     :true,
      "ajax": {
    	  "url":"../../liveSessions/listLiveSessions",
          "data": function (d) {
        	  for(var key in d){
                   if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                       delete d[key];
                   }
               }
        	  d.start = 0;
        	  d.length = 10;
          }
      },
      "columns": [    	 
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
              		 '<a style="margin-left:20px;cursor:pointer" href="javascript:void(0)" onclick="cutOnline(\'../../liveSessions/Cut?live_id='+data+'\');return false;" class="newcss1">断开</a>';
          }}
      ]    
    });
}
_devicesonline();
});
