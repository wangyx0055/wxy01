$(function() {
    $('#extendList').DataTable({
        'paging': true,
        /*是否允许翻页 */
        'lengthChange': false,
        Pagination:false,
        'searching': false,
        "bPaginate": false,
        'dom': 't<"bottom"lifp<"clear">>',
        /*是否允许检索  */
        'ordering': false,
        "bInfo" : false, //显示表格条数信息
        'autoWidth': false,
        "serverSide": true,
        "bJQueryUI" : false,//
        /* 服务器端处理方式 */
        "ajax": {
            "url": "../../configLog/listConfigLog",
            "data": function(d) {
                for (var key in d) {
                    if (key.indexOf("columns") == 0 || key.indexOf("order") == 0 || key.indexOf("search") == 0) { //以columns开头的参数删除
                        delete d[key];
                    }
                }
            }
        },
        "columns": [
            /*  { "data": "id" ,"render": function(data,type,row,meta){
                            return '<input type="checkbox" name="chk[]" value='+data+'>';
                        }},*/
            {
                "data": "name"
            },
            {
                "data": "log_manage"
            },
            {
                "data": "sort"
            },
            {
                "data": "id",
                "render": function(data, type, row, meta) {
                    return '<a   data-toggle="modal" data-row="' + meta.row + '" data-target="#edit_log" class="newcss1" style="cursor: pointer;">编辑</a>' +
                        '<a     data-toggle="modal" data-row="' + meta.row + '" class="newcss2" data-target="#modal-delLog"  style="cursor: pointer;margin-left:20px;">删除</a>';

                }
            }],
        "fnDrawCallback": function(settings) {
            $('#log_link').html('');
            const data = settings.json.data;
            data.sort(function (a,b) {
                if(a.sort === b.sort){//如果sort相同，按照age的降序
                    return b.name - a.name
                }else{
                    return a.sort - b.sort
                }
            });
            for(let item of data) {
                $('#log_link').append( '<a href="'+item.log_manage+'"  target="_blank" style="font-size: 14px;color:#0c6cbfc9;font-weight: 700;margin-right: 5px;" >'+item.name+'</a>');
            };
        }
    });
$("#edit_log").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        if(button.data('row')!=undefined&&button.data('row')!=null){
            $('#edit_log .modal-title').text('编辑链接');
            i = button.data('row');
            $('#log_id').val($('#extendList').DataTable().row('#' + i).nodes(i).data()[i].id);
            $('#log_name').val($('#extendList').DataTable().row('#' + i).nodes(i).data()[i].name);
            $('#log_manage').val($('#extendList').DataTable().row('#' + i).nodes(i).data()[i].log_manage);
            $('#log_sort').val($('#extendList').DataTable().row('#' + i).nodes(i).data()[i].sort);
        }else{
            $('#log_id').val('');
            $('#edit_log .modal-title').text('新建链接');
            $('#log_name').val("");
            $('#log_manage').val("");
            $('#log_sort').val("");
        }
        $("#Vlog_name").text("");
        $("#Vlog_manage").text("");
        $("#Vlog_sort").text("");
    });

$('#log_sure').click(function() {
    /*if(!link_name()||!link_url()||!link_sort()){
        return;
    }*/
    $.ajax({
        url: "../../configLog/addConfigLog",
        type: "POST",
        data: {
            id:$('#log_id').val(),
            name:$('#log_name').val(),
            log_manage: $('#log_manage').val(),
            sort: $('#log_sort').val(),
        },
        success: function(data) {
            if(data.success === "error") {
                if (typeof(data.errorMsg) === "undefined") {
                    $('#Vlog_name').text((typeof(data.errorMap.DuplicateName) === "undefined" ? '' : data.errorMap.DuplicateName));
                    $('#Vlog_manage').text('');
                    $('#Vlog_sort').text('');
                } else {
                    $('#Vlog_name').text(typeof(data.errorMap.DuplicateName) === "undefined" ? (typeof(data.errorMsg.name) === "undefined" ? '': data.errorMsg.name): data.errorMap.DuplicateName);
                    $('#Vlog_manage').text(typeof(data.errorMsg.log_manage) === "undefined" ? '': data.errorMsg.log_manage);
                    $('#Vlog_sort').text(typeof(data.errorMsg.sort) === "undefined" ? '' : data.errorMsg.sort);
                }
            }else if(data.success === "false") {
                $("#modal-danger .modal-title").text('失败');
                if ($('#log_id').val() === "") {
                    $("#modal-danger .modal-body").text("新建失败!");
                    $("#modal-danger").modal();
                }else {
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                }
                loadAJAX("#extendList");
            }else {
                $("#modal-success .modal-title").text('成功');
                if ($('#log_id').val() === ""){
                    $("#modal-success .modal-body").text("新建成功!");
                }else {
                    $("#modal-success .modal-body").text("编辑成功!");
                }
                $("#modal-success").modal();
                $('#edit_log').modal('hide');
                loadAJAX("#extendList");
            }
        },
        error: function() {
        }
    })
});

//删除
    $("#modal-delLog").on('show.bs.modal', function (event){
        let button = $(event.relatedTarget);
        let i = button.data('row');
        $('#del_log').val($('#extendList').DataTable().row('#' + i).nodes(i).data()[i].id);
    });
    $("#delLog").click(function (){
        console.log($('#del_log').val());
        $.ajax({
            url:"../../configLog/delConfigLog",
            type:"POST",
            data:{
                ids:new Array($('#del_log').val()),
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    $('#modal-delLog').modal('hide');
                    loadAJAX('#extendList');
                }else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                }
            },
            error:function(){
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        })
    });
});
function link_name(){
    var link_lenght=/^.{1,10}$/;
    if($("#log_name").val() === ""){
        $("#Vlog_name").text("请输入链接名称");
    }else if(!link_lenght.test($("#log_name").val())){
        $("#Vlog_name").text("输入超出限制长度");
    } else {
        $.ajax({
            url: "../../configLog/checkname",
            type: "POST",
            data: {
                id:$('#log_id').val(),
                name:$('#log_name').val(),
            },
            success: function (data) {
                if (data.success) {
                    $("#Vlog_name").text("");
                } else {
                    $("#Vlog_name").text("链接名称重复");
                }
            }
        })
    }
}
$("#log_name").blur(function () {
    link_name();
});
function link_url(){
    var url=/^(http(s)?:\/\/).{1,128}$/;
    var link_url=/^.{1,128}$/;
    if($("#log_manage").val() === ""){
        $("#Vlog_manage").text("请输入链接地址");
    }else if(!url.test($("#log_manage").val())){
        $("#Vlog_manage").text("请输入有效的链接地址");
    }else if(!link_url.test($("#log_manage").val())){
        $("#Vlog_manage").text("输入超出限制长度");
    }
}
$("#log_manage").blur(function () {
    link_url();
});
function link_sort(){
    let log_sort=$("#log_sort").val();
    let sort=/^[1-9]$/;
    if (log_sort.length ===0 ) {
        $("#Vlog_sort").text("请输入排序");
    } else if(log_sort !== "" && !sort.test(log_sort)&&(log_sort<1||log_sort>10)){
        $("#Vlog_sort").text("请输入1-10之间的数字");
    }/*else{
        $.ajax({
            url: "../../configLog/checksort",
            type: "POST",
            data: {
                id: $('#log_id').val(),
                sort: $('#log_sort').val(),
            },
            success: function (data) {
                if (data.success) {
                    $("#Vlog_sort").text("");
                } else {
                    $("#Vlog_sort").text("输入排序重复");
                }
                /!* $("#Vlog_sort").text("输入排序重复");*!/
            }
        })
    }*/
}
$("#log_sort").blur(function(){
    link_sort();
});
$("#log_manage").focus(function(){
		 $("#Vlog_manage").text("");
});
$("#log_name").focus(function(){
    $("#Vlog_name").text("");
});
$("#log_sort").focus(function(){
    $("#Vlog_sort").text("");
});
