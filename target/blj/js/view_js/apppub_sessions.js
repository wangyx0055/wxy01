function showA_T(auth_type) {
    if(auth_type==0)
        return '密码';
    else if(auth_type==1)
        return'生物指纹';
    else if(auth_type==2)
        return'密码+动态口令';
    else if(auth_type==3)
        return'短信';
    else if(auth_type==4)
        return'AD域';
    else if(auth_type==5)
        return'RADIUS';
    else if(auth_type==6)
        return'LDAP';
    else
        return'email';
}

//ajax
var _apppubsessions = function(field, value, where) {
	$('#apppubsessions').DataTable({
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
	        "data": function (d) {
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
	        }
	    },
	    "columns": [
	        { "data": "id" },
	        { "data": "name" },
	        { "data": "program" },
	        { "data": "apppub_account_id" },
	        { "data": "client_ip" },
	        { "data": "username" },
	        { "data": "realname" },
	        { "data": "start","render":function(data,type,row,mata){
	                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" title="'+row.start+"-"+row.end+'">'+row.start+"-"+row.end+'</div>';
	            }},
	        { "data": "status","render":function(data,type,row,mata){
	                if(data==1)
	                    return '已结束';
	                else
	                    return'未结束';
	            }},
	        { "data": "id", "render": function(data,type,row,meta){
		        	var str = '<a data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-default" style="cursor:pointer">详情</a>';
	                str += '<a href="../../Operator/Replay?isapppub=1&recordid=' + data + '" target="_blank" class="newcss1" style="margin-left:20px;cursor:pointer">回放</a>';
	                str += '<a class="newcss1" href="../../Operator/downloadApp?recordid='
	                    + data
	                    + '" target="hide" style="margin-left:20px;cursor:pointer">下载</a>';
	                return str;
	            }}
	    ],
        "fnDrawCallback": function( settings, json ) {
            $('#apppubsessions div').tooltip();
        }
	});
};

$("#sousuoId").click(function() {
	_apppubsessions($('#Distinguish').val(), $('#searchId').val());
});
_apppubsessions();

$('#modal-default').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    i = button.data('row');
    var auth_type=$('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].auth_type;
    var auth_type_new=showA_T(auth_type);
    $('#name').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].name);
    $('#program').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].program);
    $('#access_parameter').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].access_parameter);
    $('#apppub_account_id').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].apppub_account_id);
    $('#start').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].start +"-"+$('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].end);
    $('#time_length').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].time_length);
    $('#log_file_size').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].log_file_size);
    $('#username').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].username);
    $('#client_ip').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].client_ip);
    $('#client_mac').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].client_mac);
    $('#end').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].start +"-" +$('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].end);
    $('#length').html($('#apppubsessions').DataTable().row('#' + i).nodes(i).data()[i].time_length);
    $('#auth_type').html(auth_type_new);
});


//清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("")
})
$('#export').click(function(){
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#queryPsw').val(),
        },
        success:function(data){
            if(data.success){
                window.open("../../export/exprotApppubRecord");
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('导出成功!');
                $("#modal-success").modal();
                $('#modal-default1').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep").text(data.msg)
                }
            }
        },
        error:function(){

        }
    })
})