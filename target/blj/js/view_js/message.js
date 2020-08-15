$(function () {
    var _message = function (field, value){
        $('#massagelist').DataTable({
            'paging': true,
            'lengthChange': true,
            "lengthMenu": [
                [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
            ],
            'dom': 't<"bottom"lifp<"clear">>',
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false,
            "serverSide": true,
            'destroy': true,
            "ajax": {
                "url": "../../systemMessage/listSystemMessage",
                "data": function (d) {
                    for(var key in d){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete d[key];
                        }
                    }
                    //指定检索参数名称，后台参数名为extraSerach
                    eval('d.' + field + '="' + value
                        + '"');
                }
            },
            "columns": [
                { "data": "id" ,"render": function(data,type,row,meta){
                        return '<input type="checkbox" name="chk[]" value='+data+'>';
                    }},
                {"data": "login_datetime"},
                {"data": "title"},
                {
                    "data": "level",
                    "render": function (data, type, row, mata) {
                        if (data == 3)
                            return '高';
                        else if(data == 2){
                        	return '中';
                        }
                        else
                            return '低';
                    }
                },
                {"data": "content",
                    "render" : function(data, type,
                            row, mata) {
		            return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:100px;height: 20px;" data-html="true" data-toggle="tooltip" title="' + data + '">'
		                + data
		                + '</div>';
		        }}

            ],
            "fnDrawCallback": function( settings, json ) {
                $('#massagelist div').tooltip();
                //点击已读消息
                let $tr=$("#massagelist>tbody tr");
                //jquery实现
                        $tr.on("click",function (event){
                        console.log($(this));
                        $(this).css({"font-weight":"500","color":"rgba(0, 0, 0, 0.85)",});
                /*        $.ajax({
                            url:"../../systemMessage/listSystemMessage?"+new Date().getTime(),
                            type:"POST",
                            data:{
                                start:0,
                                length:1
                            },
                            success:function(data){
                                $('.navbar-custom-menu .label-warning').text(data.recordsTotal-1);
                                $('.navbar-custom-menu .dropdown-menu .header').text(data.recordsTotal+"条通知");
                                for(var i=0; i<data.data.length; i++){
                                    $('.navbar-custom-menu .dropdown-menu .menu').remove("<li><a href=\"#\"><i class=\"fa fa-warning text-red\"></i> "+data.data[i].title+":"+data.data[i].content+"</a> </li>");
                                }
                            },
                            error:function(){}
                        })*/
                });
                //点击阅读全部消息
                $("#ad_del_button_").click(function () {
                    let $tr=$("#massagelist>tbody").find("tr");
                    $tr.css({"font-weight":"500","color":"rgba(0, 0, 0, 0.85)",});
                })
            }
        })
    };

    $("#sousuoanniu").click(function () {
    	_message($('#search').val(), $('#searchId').val());
    });
    _message();

    $('#massagelist div').tooltip();
});
//删除
$('#delAllButton').click(function(){
    var obj = document.getElementsByName('chk[]');
    /*  console.log(obj);*/
    var ids= [];
    for ( let i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    console.log(ids);
    if(ids.length==0){
        $("#modal-hint.modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#massagelist');
        return false;
    }
    $.ajax({
        url:"../../systemMessage/delSystemMessage",
        type:"POST",
        data:{
            ids:ids,
            type:0
        },
        success:function(data){
            if(data.success){
                $("#delAll").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功！');
                $("#modal-success").modal();
                loadAJAX('#massagelist');
                $.ajax({
                    url:"../../systemMessage/listSystemMessage?"+new Date().getTime(),
                    type:"POST",
                    data:{
                        start:0,
                        length:10
                    },
                    success:function(data){
                        $('.navbar-custom-menu .label-warning').text(data.recordsTotal);
                        $('.navbar-custom-menu .dropdown-menu .header').text(data.recordsTotal+"条通知");
                        for(var i=0; i<data.data.length; i++){
                            $('.navbar-custom-menu .dropdown-menu .menu').append("<li><a href=\"#\"><i class=\"fa fa-warning text-red\"></i> "+data.data[i].title+":"+data.data[i].content+"</a> </li>");
                        }
                    },
                    error:function(){}
                })
            }
            else{
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
