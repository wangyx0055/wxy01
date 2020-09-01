$(function () {
  /* ChartJS
   * -------
   * Here we will create a few charts using ChartJS
   */

  // -----------------------
  // - MONTHLY SALES CHART -
	// -----------------------
//许可信息
	function licenseInfo(){
		$.ajax({
			url:"../../systeminfo/licenseinfo",
			type:"POST",
			data:{
				'':(new Date()).getTime()
			},
			success:function(data){
				$('#lic_customename').text(data.name);
				$('#lic_versiontype').text(data.versiontyp);
				$('#lic_devicenumber').text(data.devices);
				$('#lic_expireddate').text(data.endtimestr);
			},
			error:function(){}
		})
	}
	licenseInfo();
//最近用户访问本周/月
function _visitByDay(data){
	let labels=[];
	 let values = [];
	 console.log(data);
	   for(var i=0; i<data.length; i++){
		labels[i] = parseInt(data[i].day);
	 	values[i] = data[i].ct;
	     }
	  option = {
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
			        left: '1%',
			        right: '3%',
			        bottom: '2%',
					top:'5%',
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
			            boundaryGap: true,
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
			            color:['rgba(20,148,247,0.67)'],
			            type: 'line',
			            stack: '总量',
			            data: values
			        }
			    ]
			};
	  var myChart = echarts.init(document.getElementById('salesChart'),'light');
	  myChart.setOption(option);
}
//累计用户访问量本周/月
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
        title: {
            text: '',
        },
		grid: {
			left: '0%',
			right: '2%',
			bottom: '0%',
			top:'12%',
			containLabel: true
		},
        xAxis: {
            data: names,
            axisLabel:{
                show:true,
                rotate: 30,
                color: 'rgba(0,0,0,0.65)',
				/*fontSize:'10',*/
				fontWeight:400
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
            color:'rgba(20,148,247,0.67)',
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
//资产最近访问本周/月
	function _visitAsset(data){
		let labels2=[];
		let values2 = [];
		console.log(data);
		for(var i=0; i<data.length; i++){
			labels2[i] = parseInt(data[i].day);
			values2[i] = data[i].ct;
		}
		option = {
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
				left: '1%',
				right: '3%',
				bottom: '2%',
				top:'5%',
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
					boundaryGap: true,
					data: labels2,
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
					color:['rgba(20,148,247,0.67)'],
					type: 'line',
					stack: '总量',
					data: values2
				}
			]
		};
		var myChart = echarts.init(document.getElementById('assetsChart'),'light');
		myChart.setOption(option);
	}
//资产累计访问本周/月
	function _TotalAsset(data){
		var names = [];
		var values = [];
		for(var i=0; i<data.length; i++){
			names[i]  = data[i]['name'];
			values[i] = data[i]['count'];
		}
		var myChart = echarts.init(document.getElementById('bar-asset-chart'),'light');
		// 指定图表的配置项和数据
		var option = {
			title: {
				text: '',
			},
			grid: {
				left: '0%',
				right: '2%',
				bottom: '0%',
				top:'12%',
				containLabel: true
			},
			xAxis: {
				data: names,
				axisLabel:{
					show:true,
					rotate: 30,
					color: 'rgba(0,0,0,0.65)',
					fontWeight:400
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
				color:'rgba(20,148,247,0.67)',
			}]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
//系统状态CPU
function  cpuSystem(data){
	var myChart = echarts.init(document.getElementById('cpuChart'));
	var option = {
			color:['rgba(20,148,247,0.67)','rgba(20,148,247,0.21)'],
	     	series : [ //系列列表
			{
				type: 'pie',   //类型 pie表示饼图
				center: ['50%', '50%'], //设置饼的原心坐标 不设置就会默认在中心的位置
				radius: ['75%', '100%'],  //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
				hoverAnimation: false, //鼠标移入变大
				avoidLabelOverlap: false,
				label: {formatter:data.cpu_userate*100+'%',
						position: 'center',
					    color:'black',
					    fontSize:'6px',
				},  //提示文字
				data: [data.cpu_used,data.cpu_total-data.cpu_used]
			}
        ],
};
	myChart.setOption(option)
}
//系统状态内存
	function  memorySystem(data){
		var myChart = echarts.init(document.getElementById('memoryChart'));
		var option = {
			color:['#448AFF','#AFE5FF'],
			series : [ //系列列表
				{
					type: 'pie',   //类型 pie表示饼图
					center: ['50%', '50%'], //设置饼的原心坐标 不设置就会默认在中心的位置
					radius: ['75%', '100%'],  //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
					hoverAnimation: false, //鼠标移入变大
					label: {
						    formatter: data.memory_used_human+'/'+data.memory_total_human,
							position: 'center',
							color:'black'
					},  //提示文字
					data: [parseInt(data.memory_used_human),parseInt(data.memory_total_human)-parseInt(data.memory_used_human)]
				}
			],
		};
		myChart.setOption(option)
	}
	
//系统状态硬盘
	function  diskSystem(data){
		var myChart = echarts.init(document.getElementById('diskChart'));
		var option = {
			color:['#448AFF','#AFE5FF'],
			series : [ //系列列表
				{
					type: 'pie',   //类型 pie表示饼图
					center: ['50%', '50%'], //设置饼的原心坐标 不设置就会默认在中心的位置
					radius: ['75%', '100%'],   //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
					hoverAnimation: false, //鼠标移入变大
					label: {
						    formatter: data.disk_used_human+'/'+data.disk_total_human,
							position: 'center',
							color:'black'
					},  //提示文字
					data: [parseInt(data.disk_used_human), parseInt(data.disk_total_human)-parseInt(data.disk_used_human)]
				}
			],
		};
		myChart.setOption(option)
	}
	

//系统状态流量
function  infoSystem(data){
		var myChart = echarts.init(document.getElementById('infoChart'));
		var option = {
			color:['#448AFF','#AFE5FF'],
			series : [ //系列列表
				{
					type: 'pie',   //类型 pie表示饼图
					center: ['50%', '50%'], //设置饼的原心坐标 不设置就会默认在中心的位置
					radius: ['75%', '100%'],   //饼图的半径,第一项是内半径,第二项是外半径,内半径为0就是真的饼,不是环形
					hoverAnimation: false, //鼠标移入变大
					label: {
					     	formatter: data.network_receive_human+'/'+data.network_send_human,
							position: 'center',
							color:'black'
					},  //提示文字
					data: [parseInt(data.network_receive_human), parseInt(data.network_send_human)]
				}
			],
		};
		myChart.setOption(option)
	}
	
//流量统计
$.ajax({
    url:"../../systeminfo/all",
    type:"POST",
    data:(new Date()).getTime(),
    success:function(data){
		cpuSystem(data);
		memorySystem(data);
		diskSystem(data);
		infoSystem(data);
    },
    error:function(){}
});

   /* var visit=$("#total_visit>button");
	visit[0].onclick=function() {
		this.setAttribute("class","week-time");
		document.getElementById('total-visit-month').setAttribute("class","month-time");
	};
	visit[1].onclick=function() {
		document.getElementById('total-visit-week').setAttribute("class","week-time-click");
		this.setAttribute("class","month-time-click");
	};*/
//数据回显
$.ajax({
    url:"../../systeminfo/sysdata",
    type:"POST",
    data:(new Date()).getTime(),
    success:function(data){
        $('#userct').html(data.userct);
        $('#devicect').html(data.devicect);
        $('#apppubct').html(data.apppubct);
        $('#alertct').html(data.alertct);

        /*devicesByType(data.devicesByType,data.devicect);*/
      /*  apppubByProgram(data.apppubByProgram,data.appprogramct);*/

        $('#text30increate').html(data.getTextTotal);
    	$('#text30increatepercent').html("<span style='color: black;font-weight: 500;font-size: 12px;'>近30天访问 </span>"+data.device30DayTextIncrease+"<span  style='color: black;font-weight: 500;font-size: 12px;'> 次</span>");
    	$('#graph30increate').html(data.getGraphTotal);
    	$('#graph30increatepercent').html("<span style='color: black;font-weight: 500;font-size: 12px;'>近30天访问 </span>"+data.device30DayGraphIncrease+"<span  style='color: black;font-weight: 500;font-size: 12px;'> 次</span>");
    	$('#apppub30increate').html(data.getApppubRecordTotal);
    	$('#apppub30increatepercent').html("<span style='color: black;font-weight: 500;font-size: 12px;'>近30天访问 </span>"+data.apppub30DayIncrease+"<span  style='color: black;font-weight: 500;font-size: 12px;'> 次</span>");
        $('#filetransfer30increate').html(0);
        $('#filetransfer30increatepercent').html("<span style='color: black;font-weight: 500;font-size: 12px;'>近30天访问 </span>"+0+"<span  style='color: black;font-weight: 500;font-size: 12px;'> 次</span>");
        //最近用户
        let user=$("#lated_user>button");
		_visitByDay(data.visitByDay);
		user[0].onclick=function() {
			_visitByDay(data.visitByDay);
		  this.setAttribute("class","week-time");
		  document.getElementById('lated_user_month').setAttribute("class","month-time");
		};
		user[1].onclick=function() {
			_visitByDay(data.visitByMonth);
			document.getElementById('lated_user_week').setAttribute("class","week-time-click");
			this.setAttribute("class","month-time-click");


		};
		//累计用户
		let totol_user=$("#total-user>button");
		_visitByUser(data.visitByUser);
		totol_user[0].onclick=function() {
			_visitByUser(data.visitByUser);
			this.setAttribute("class","week-time");
			document.getElementById('total-user-month').setAttribute("class","month-time");
		};
		totol_user[1].onclick=function() {
			_visitByUser(data.visitByUserMonth);
			document.getElementById('total-user-week').setAttribute("class","week-time-click");
			this.setAttribute("class","month-time-click");
		};
		//最近资产
		let asset=$("#lated_asset>button");
		_visitAsset(data.visitByDay);
		asset[0].onclick=function() {
			_visitAsset(data.visitByDay);
			console.log("oe");
			this.setAttribute("class","week-time");
			document.getElementById('lated_asset_month').setAttribute("class","month-time");
		};
		asset[1].onclick=function() {
			console.log("kf");
			_visitAsset(data.visitByMonth);
			document.getElementById('lated_asset_week').setAttribute("class","week-time-click");
			this.setAttribute("class","month-time-click");
		};
		//累计资产
		let total_asset=$("#total_asset>button");
		 _TotalAsset(data.devicesByType);
		 total_asset[0].onclick=function() {
			_TotalAsset(data.devicesByType);
			console.log("oe");
			this.setAttribute("class","week-time");
			document.getElementById('total_asset_month').setAttribute("class","month-time");
		};
		total_asset[1].onclick=function() {
			console.log("kf");
			_TotalAsset(data.devicesByType);
			document.getElementById('total_asset_week').setAttribute("class","week-time-click");
			this.setAttribute("class","month-time-click");
		};
    },
    error:function(){}
})
//实时在线用户
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
