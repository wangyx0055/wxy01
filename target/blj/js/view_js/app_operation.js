var _userlist = function(search,searchvalue,data,datavalue){
    //alert(search+':'+searchvalue+','+data+':'+datavalue);
    $('#apppub').DataTable({
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
            "url":"../../Operator/appList",
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
            { "data": "id" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "appservername"},
            { "data": "name"},
            { "data": "appprogramname" },
            { "data": "username" },
            { "data": "id","render": function(data,type,row,meta){
                    return '<a href="../../Operator/deviceLogin?apppub_account_id='+data+'" id="login_'+data+'" target="_blank"><img src="../../bower_components/dist/img/KHCFDC.png" style="width: 20px; height: 20px"></a>';//<img src="../../bower_components/dist/img/link.png" style="width: 20px; height: 20px; margin-left: 10px">';
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
    	    	device_id: id,
    	    	type: 1
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
    	    	'ids[]': [id],
    	    	type:1
    	    },
    	    success:function(data){
    	        if(data.success){
    	            o.setAttribute("src","../../bower_components/dist/img/like1.svg");
    	        }
    	    }
    	})
    }
}