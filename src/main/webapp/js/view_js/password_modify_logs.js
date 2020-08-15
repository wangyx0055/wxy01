$(function () {
    $('#changePasswordLog').DataTable({
        'paging'      : true,
        'iDisplayLength': 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'			: 't<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : true,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"	: true,
        'ajax': {
            "url":"../../changePasswordLog/listChangePasswordLog"
        },
        'columns': [
            { "data": "device_ip" },
            { "data": "device_name" },
            { "data": "device_username" },
            { "data": "exec_datetime" },
            { "data": "policy_name" },
            { "data": "update_success_flag" ,
                "render":function(data,type,row,meta){
                    if(data==0)
                        return "失败";
                    else
                        return "成功";}},
            { "data": "id", "render": function(data,type,row,meta){
                    return '<A data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary2" class="newcss2" style="margin-left: -5px;cursor: pointer">删除</A>';
                }}
        ]
    });
    $('#modal-primary2').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#del_id').val($('#changePasswordLog').DataTable().row('#' + i).nodes(i).data()[i].id);
    });

    $('#delButton').click(function(){
        $.ajax({
            url:"../../changePasswordLog/delChangePasswordLog",
            type:"POST",
            data:{
                ids: new Array($('#del_id').val())
            },
            success:function(data){
                if(data.success){
                    $("#modal-primary2").modal('hide');
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    loadAJAX('#changePasswordLog');
                } else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error:function(){

            }
        })
    });
    $('#changePasswordStatistics').DataTable({
        'paging'      : true,
        'searching'   : false,
        'ordering'    : true,
        'iDisplayLength': 10,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'			: 't<"bottom"lifp<"clear">>',
        'lengthChange': true,
        'info'        : true,
        'autoWidth'   : false,
        "serverSide"	: true,
        'ajax': {
            "url":"../../changePasswordLog/listChangePasswordLog"
        },
        'columns': [
            { "data": "device_ip" },
            { "data": "device_name" },
            { "data": "device_username" },
            { "data": "exec_datetime" },
            { "data": "end_datetime" },
            { "data": "change_pa" },
            { "data": "update_success_flag" ,
                "render":function(data,type,row,meta){
                    if(data==0)
                        return "失败";
                    else
                        return "成功";}},
        ]
    });
});
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
    $('#reservation1').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        }
    );
    $('#reservation2').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        }
    );
    $('#reservation3').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        }
    );
    $('#reservation4').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",

        }
    );
};
$(document).ready(function() {
    init();
});