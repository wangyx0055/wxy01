$(function () {
	var _scriptLog = function(field,value){
		$('#scriptLog').DataTable({
	        'paging'      : true,
	        'lengthChange': true,
	        "iDisplayLength": 10,
	        "lengthMenu": [
	            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
	        ],
	        'dom':'t<"bottom"lifp<"clear">>',
	        'searching'   : false,
	        'ordering'    : true,
	        'info'        : true,
	        'autoWidth'   : false,
	        "serverSide"	: true,
	        'destroy'     :true,
	        "ajax": {
	            "url":"../../crontabCommandLog/listCrontabCommandLog",
	            "data":function (d) {
	                for(var key in d){
                        if (field === "searchAll" || field === "exec_status"){
                            if ($('#searchId').val().match("成功")){
                                value=1;
                                field="exec_status";
                            }else if($('#searchId').val().match("失败")){
                                value=0;
                                field="exec_status";
                            }else if($('#searchId').val()===''){
                                value='';
                            }else{
                                if(field === "searchAll"){
                                    value=$('#searchId').val();
                                }else if(field === "exec_status"){
                                    value=3;
                                }
                            }
                        }
                        if (field === "searchAll" || field === "config_method"|| field === "exec_status"){
                            if ($('#searchId').val().match("手")||$('#searchId').val().match("动")||$('#searchId').val().match("手动")||$('#searchId').val().match("手动执行")){
                                value=0;
                                field="config_method";
                            }else if($('#searchId').val().match("时")||$('#searchId').val().match("定时")||$('#searchId').val().match("定时执行")){
                                value=1;
                                field="config_method";
                            } else if($('#searchId').val().match("期")||$('#searchId').val().match("定期")||$('#searchId').val().match("定期执行")){
                                value=2;
                                field="config_method";
                            }else if($('#searchId').val()===''){
                                value='';
                            }else{
                                if(field === "searchAll"){
                                    value=$('#searchId').val();
                                }else if(field === "config_method"){
                                    value=3;
                                }
                            }
                        }
	                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
	                        delete d[key];
	                    }
	                }
	                eval('d.'+field+'="'+value+'"');
	            }
	        },
	        "columns": [
	            { "data": "config_name" },
                // { "data": "depart_name" , "render" : function(data, type,row, mata) {
                //         return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                //             +data
                //             + '</div>';
                //     }},
	            { "data": "command" },
                { "data": "config_method","render":function (data, type, row, meta) {
                        /*	return data==0?'手动执行':(data==1?'定时执行,时间:'+row.exec_datetime:'定期执行,周期:'+exec_cycle(data));*/
                        if(data === 0){
                            config_method='手动执行';
                        }else if(data === 1){
                            config_method='定时执行'
                        }else{
                            config_method='定期执行'
                        }
                        return  '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+config_method+'">'
                            +config_method
                            + '</div>';
                    }},
                {
                    "data" : "exec_datetime",
                    "render" : function(data, type, row, mata) {
                        return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="开始:'+row.exec_datetime+"<br>结束:"+row.end_datetime+'">'
                            + row.exec_datetime
                            + "-"
                            + row.end_datetime
                            + '</div>';

                    }
                },
                {"data": "exec_status", "render":function (data, type, row, meta){
                    if(data === 1){
                        exec_status='成功';
                    }else{
                        exec_status='失败';
                    }
                    return  '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+exec_status+'">'
                        +exec_status
                        + '</div>';
                }},
	            { "data": "id", "render": function(data,type,row,meta){
	                    return  '<a  data-toggle="modal" data-row="'+meta.row+'" class="newcss1" data-target="#modal-default" style="cursor:pointer">查看</a>'+
                                '<a href="../../export/exportCrontabCommandLogReport?id=' + data + '" class="newcss1" style="margin-left:20px;cursor:pointer">导出</a>'

	                }}
	        ],
	        "fnDrawCallback": function( settings, json ) {
	            $('#scriptLog div').tooltip();
	        }
	    })
	}
	// _scriptLog("search","");
    // $("#search").click(function(){
    // 	_scriptLog("search","");
    // })
    _scriptLog('searchAll','');
    $('#search').click(function () {
        _scriptLog($('#Distinguish').val(), $('#searchId').val());
    });
    $('#modal-default').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget); // Button that triggered the modal
        const i = button.data('row');
        $('#config_name').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].config_name);
        $('#device_name').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].device_name);
        $('#device_ip').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].device_ip);
        $('#device_port').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].device_port);
        $('#device_username').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].device_username);
        $('#command').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].command);
        $('#exec_datetime').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime);
        $('#end_datetime').text($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].end_datetime);
        $('#result').html($('#scriptLog').DataTable().row('#' + i).nodes(i).data()[i].result.replace(/\n/g, "<br />"));
    });

})
$(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, locale: { format: 'MM/DD/YYYY hh:mm A' }})
    //Date range as a button
    $('#daterange-btn').daterangepicker(
        {
            ranges   : {
                'Today'       : [moment(), moment()],
                'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month'  : [moment().startOf('month'), moment().endOf('month')],
                'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            startDate: moment().subtract(29, 'days'),
            endDate  : moment()
        },
        function (start, end) {
            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
        }
    )

    //Date picker
    $('#datepicker').datepicker({
        autoclose: true
    });

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass   : 'iradio_minimal-blue'
    });
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

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
        showInputs: false
    })
})
function show()
{var value = document.getElementById("div1").style.display;
    if(value=="none")
    {document.getElementById("div1").style.display="block";
    }
    else
        document.getElementById("div1").style.display="none";
}
function init() {
    //定义locale汉化插件
    var locale = {
        "format": 'YYYY-MM-DD',
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "起始时间",
        "toLabel": "结束时间'",
        "weekLabel": "W",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        "firstDay": 1,

    };
    //初始化显示当前时间

    //日期控件初始化
    $('#reservation').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            startDate: moment(),
            endDate: moment(),
            "opens":"left",
        }
    );

};
$(document).ready(function() {
    init();
});