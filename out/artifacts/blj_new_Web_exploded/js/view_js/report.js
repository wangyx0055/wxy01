//Initialize Select2 Elements
$('.select2').select2()

//iCheck for checkbox and radio inputs
$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
    checkboxClass: 'icheckbox_minimal-blue',
    radioClass   : 'iradio_minimal-blue'
})
//Red color scheme for iCheck
$('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
    checkboxClass: 'icheckbox_minimal-red',
    radioClass   : 'iradio_minimal-red'
})
//Flat red color scheme for iCheck
$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
    checkboxClass: 'icheckbox_flat-green',
    radioClass   : 'iradio_flat-green'
})

//ajax
var _deviceAccountList = function(field,value){
    $('#device-account-table').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'         :'t<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : false,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"  : true,
        "destroy"     : true,
        "ajax": {
            "url":"../../deviceAccount/getUserDeviceAccount",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
            },
        },
        "columns": [
            { "data": "name" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "ip" },
            {"data": "device_username",},
            { "data": "username" },
            { "data": "realname" },
            { "data": "policyname" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }}
        ],
        "initComplete": function( settings, json ) {
            $('#device-account-table div').tooltip();
        }
    });
};

//ajax
var _apppubAccountList = function(field,value){
    $('#apppub-account-table').DataTable({
        'paging'      : true,
        "iDisplayLength": 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'         :'t<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : false,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"  : true,
        "destroy"     : true,
        "ajax": {
            "url":"../../apppubAccount/getUserApppubAccount",
            "data": function (d) {
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                //指定检索参数名称，后台参数名为extraSerach
                eval('d.'+field+'="'+value+'"');
            },
        },
        "columns": [
            { "data": "appserverip" },
            { "data": "apppubaccountname" },
            { "data": "appprogramname"},
			{ "data": "apppubusername"},
            { "data": "username" },
            { "data": "realname" },
            { "data": "policyname" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }}
        ],
        "initComplete": function( settings, json ) {
            $('#apppub-account-table div').tooltip();
        }
    });
};

$('#export').click(function(){
	$('#modal-default1 .r-type input').each(function(){
		if($(this)[0].checked){
			var url = '../../export/export'+UpperFirstLetter($(this).val())+'PriorityReport';
			document.getElementById('hide_'+$(this).val()).src=url;
		}
	})
})

$('.nav.nav-tabs li').each(function() {
    $(this).click(function() {
        if ($(this).children().attr('href') == '#F') {
            _deviceAccountList("day", "");
        } else {
            _apppubAccountList("day", "");
        }
    })
})

/* 调用user_group_list */
_deviceAccountList('searchAll','');
/* 根据条件搜索 */
$('#search').click(function(){
    _deviceAccountList($('#Distinguish').val(),$('#searchId').val());
})
$('#search1').click(function(){
    _apppubAccountList($('#Distinguish1').val(),$('#searchId1').val());
})