var _userlist = function(search,searchvalue,data,datavalue){
    //alert(search+':'+searchvalue+','+data+':'+datavalue);
    $('#devices').DataTable({
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
        "ajax": {
            "url":"../../Operator/deviceList",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+search+'="'+searchvalue+'"');
                eval('d.'+data+'="'+datavalue+'"');
            }
        },
        "columns": [
            { "data": "deviceid" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "name"},
            { "data": "ip" },
            { "data": "protocol"},
            { "data": "username" },
            { "data": "ip","render":function(data,type,row,meta){
                    return '<img src="../../bower_components/dist/img/link.png" style="width: 25px; height: 25px;margin-left: 15px;" onclick="connectTest(\''+row.ip+'\','+row.port+')">';
                }},
            { "data": "id","render": function(data,type,row,meta){
            		if(row.protocol_id==5||row.protocol_id==6){
            			return '<a href="#" id="login_'+data+'" onclick="ftplogin('+data+')"><img src="../../bower_components/dist/img/KHCFDC.png" style="width: 20px; height: 20px"></a>';
            		}else
            			return '<a href="../../Operator/deviceLogin?device_account_id='+data+'" id="login_'+data+'" target="_blank"><img src="../../bower_components/dist/img/KHCFDC.png" style="width: 20px; height: 20px"></a>';//<img src="../../bower_components/dist/img/link.png" style="width: 20px; height: 20px; margin-left: 10px">';
                }},
            { "data": "id", "render": function(data,type,row,meta){
            		if(row.favorite_id>0)
            			return '<img src="../../bower_components/dist/img/like.svg" data-row="'+meta.row+'" style="width: 21px; height: 20px;cursor: pointer;" onclick="like(this,'+row.id+')">';
            		else
            			return '<img src="../../bower_components/dist/img/like1.svg" data-row="'+meta.row+'" style="width: 21px; height: 20px;cursor: pointer;" onclick="like(this,'+row.id+')">';
                }}
        ]
    });
}
$('.nav.nav-tabs li').each(function(){
    $(this).click(function(){
        _userlist($('#Distinguish').val(),$('#searchId').val(),'data',$(this).attr('data-item'));
    })
})
_userlist('searchall','','data','all');
$('#search').click(function(){
    //alert($('.nav.nav-tabs li.active').attr('data-item'));
    _userlist($('#Distinguish').val(),$('#searchId').val(),'data', $('.nav.nav-tabs li.active').attr('data-item'));
});
function showfinger(v){
    if(v=='b'){
        document.getElementById("div1").style.display="block";
    }else{
        document.getElementById("div1").style.display="none";
    }
}
function like(o,id){
	if(o.getAttribute("src")=="../../bower_components/dist/img/like1.svg"){
    	$.ajax({
    	    url:"../../userFavourite/addUserFavourite",
    	    type:"POST",
    	    data:{
    	    	device_id: id
    	    },
    	    success:function(data){
    	        if(data.success){
    	            o.setAttribute("src","../../bower_components/dist/img/like.svg");
    	        }
    	    }
    	})
    }else{
    	$.ajax({
    	    url:"../../userFavourite/delUserFavouriteByDeviceId",
    	    type:"POST",
    	    data:{
    	    	'ids[]': [id]
    	    },
    	    success:function(data){
    	        if(data.success){
    	            o.setAttribute("src","../../bower_components/dist/img/like1.svg");
    	        }
    	    }
    	})
    }
}
function ftplogin(id)
{
	$.ajax({
	    url:"../../Operator/deviceLogin?device_account_id="+id,
	    type:"POST",
	    data:{
	    },
	    success:function(data){
	        if(data.success){
	        	$('#ftp_ip').text(data.ip);
	        	$('#ftp_port').text(data.port);
	        	$('#ftp_username').text(data.username);
	        	$('#ftp_password').text(data.password);
	            $('#modal-ftp-login').modal();
	        }
	    }
	})
}
function connectTest(ip,port){
	$.ajax({
	    url:"../../configNetwork/lportest",
	    type:"POST",
	    data:{
			ip:ip,
			port:port
	    },
	    success:function(data){
	        if(data.msg.indexOf('open')){
				$("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('连接成功!');
                $("#modal-success").modal();
			}else{
				$("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text(data.msg?data.msg:'连接失败!');
                $("#modal-danger").modal();
			}
	    }
	})
}