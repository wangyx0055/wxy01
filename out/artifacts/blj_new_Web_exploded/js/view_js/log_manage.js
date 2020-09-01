$(function () {
    $('#example2').DataTable({
        'paging'      : true,
        'iDisplayLength': 10,
        'lengthChange': true,
        "lengthMenu": [
            [10, 25, 50, 100], ["10条/页", "25条/页", "50条/页", "100条/页"]
        ],
        'dom'			: 't<"bottom"lifp<"clear">>',
        'searching'   : false,
        'ordering'    : true,
        'info'        : true,      /*是否允许显示情报  */
        'autoWidth'   : false,
        "serverSide"	: true,
        'ajax': {
            "url":"../../logDeletePolicy/listLogDeletePolicy"
        },
        'columns': [
            { "data": "id" ,"render": function(data,type,row,meta){
                    return '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "name" },
            { "data": "status" },
            {
                "data": "type",
                "render":function(data,type,row,mata){
                    if(data==0||data==4)
                        return '手动执行';
                    else if(data==1||data==5)
                        return '定时执行';
                    else
                        return '周期执行';}
            },
            {
                "data": "deletetype",
                "render":function(data,type,row,mata){
                    if(data==1)
                        return '主机审计';
                    else if(data==2)
                        return '应用审计';
                    else if(data==3)
                        return '系统日志';
                    else if(data==12||data==21)
                        return '主机审计  应用审计';
                    else if(data==13||data==31)
                        return '主机审计 系统日志';
                    else if(data==23||data==32)
                        return '应用审计 系统日志';
                    else
                        return '主机审计  应用审计 系统日志';
                }
            },
            { "data": "id", "render": function(data,type,row,meta){
                    return '<A data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-default14" class="newcss1" style="cursor:pointer;">编辑</A>'+
                        '<A data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-primary" class="newcss1" style="margin-left: 5px;cursor:pointer;">立即执行</A>'+
                        '<A data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-delgroup" class="newcss2" style="margin-left: 5px;cursor:pointer;">删除</A>';


                }},
        ]/* 获取列数据 */
    });
    $('#modal-addgroup').on('show.bs.modal', function (event) {

    });
    //del look
    $('#modal-delgroup').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var i = button.data('row');
        $('#del_id').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
    });

    $('#modal-default14').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        i = button.data('row');
        $('#edit_id').html($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_id1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].id);
        $('#edit_status').html($('#example2').DataTable().row('#' + i).nodes(i).data()[i].status);
        $('#edit_name').html($('#example2').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#edit_name1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].name);
        $('#edit_type1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].type);
        var v_1=($('#example2').DataTable().row('#' + i).nodes(i).data()[i].type);
        if(v_1==1){
            document.getElementById("div4").style.display="block";
            document.getElementById("div3").style.display="none";
        }
        else if(v_1==2){
            document.getElementById("div3").style.display="block";
            document.getElementById("div4").style.display="none";
        }
        else{
            document.getElementById("div3").style.display="none";
            document.getElementById("div4").style.display="none";
        };
        var deletetype_2 =($('#example2').DataTable().row('#' + i).nodes(i).data()[i].deletetype);
        if(deletetype_2==1){
            document.getElementById("checkbox1").checked=true;
            document.getElementById("checkbox2").checked=false;
            document.getElementById("checkbox3").checked=false;
        }
        else if(deletetype_2==2){
            document.getElementById("checkbox1").checked=false;
            document.getElementById("checkbox2").checked=true;
            document.getElementById("checkbox3").checked=false;
        }
        else if(deletetype_2==3){
            document.getElementById("checkbox1").checked=false;
            document.getElementById("checkbox2").checked=false;
            document.getElementById("checkbox3").checked=true;
        }
        else if(deletetype_2==12||deletetype_2==21){
            document.getElementById("checkbox1").checked=true;
            document.getElementById("checkbox2").checked=true;
            document.getElementById("checkbox3").checked=false;
        }
        else if(deletetype_2==13||deletetype_2==31){
            document.getElementById("checkbox1").checked=true;
            document.getElementById("checkbox2").checked=false;
            document.getElementById("checkbox3").checked=true;
        }
        else if(deletetype_2==23||deletetype_2==32){
            document.getElementById("checkbox1").checked=false;
            document.getElementById("checkbox2").checked=true;
            document.getElementById("checkbox3").checked=true;
        }
        else {
            document.getElementById("checkbox1").checked=true;
            document.getElementById("checkbox2").checked=true;
            document.getElementById("checkbox3").checked=true;
        };
        var type_1 = ($('#example2').DataTable().row('#' + i).nodes(i).data()[i].type);
        if(type_1==0){
            $('#edit_type').html("手动执行");
        }
        else if(type_1==1){
            $('#edit_type').html("定时执行");
        }
        else{
            $('#edit_type').html("周期执行");
        };

        var deletetype_1 =($('#example2').DataTable().row('#' + i).nodes(i).data()[i].deletetype);
        if(deletetype_1==1){
            $('#edit_deletetype').html("主机审计");
        }
        else if(deletetype_1==2){
            $('#edit_deletetype').html("应用审计");
        }
        else if(deletetype_1==3){
            $('#edit_deletetype').html("系统日志");
        }
        else if(deletetype_1==12||deletetype_1==21){
            $('#edit_deletetype').html("主机审计 应用审计");
        }
        else if(deletetype_1==13||deletetype_1==31){
            $('#edit_deletetype').html("主机审计  系统日志");
        }
        else if(deletetype_1==23||deletetype_1==32){
            $('#edit_deletetype').html("应用审计  系统日志");
        }
        else {$('#edit_deletetype').html("主机审计 应用审计 系统日志");
        };
        $('#reservation').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime); /*定时执行  */
        $('#reservation1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].exec_datetime); /*周期执行  */
        $('#internal1').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].internal);
        $('#reservation2').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].end_datetime);
        $('#reservation3').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].session_start_datetime);
        $('#reservation4').val($('#example2').DataTable().row('#' + i).nodes(i).data()[i].session_end_datetime);
    });

    $('#addButton').click(function(){
        var today1=$('#add_type').val();
        var exec_datetime12;
        if(today1==1){
            exec_datetime12=$('#reservation21').val();
            end_datetime12="";
            internal12=0;
        }
        else if(today1==2){
            exec_datetime12=$('#reservation24').val();
            end_datetime12=$('#reservation25').val();
            internal12=$('#internal2').val()
        }
        else{
            exec_datetime12="";
            end_datetime12="";
            internal12=0;
        };
        var checked111=document.getElementById("checkbox4").checked;
        var checked222=document.getElementById("checkbox5").checked;
        var checked333=document.getElementById("checkbox6").checked;
        var checked10;
        var checked20;
        var checked30;
        var checked112233;
        if(checked111==true){
            checked10=$('#checkbox4').val()
        }
        else{
            checked10=""
        };
        if(checked222==true){
            checked20=$('#checkbox5').val()
        }
        else{
            checked20=""
        };
        if(checked333==true){
            checked30=$('#checkbox6').val()
        }
        else{
            checked30=""
        };
        checked112233=checked10+checked20+checked30
        $.ajax({
            url:"../../logDeletePolicy/addLogDeletePolicy",
            type:"POST",
            data:{
                name:$('#add_name').val(),
                type: $("#add_type").val(), /* 执行方式 */
                exec_datetime:exec_datetime12, /*执行时间  */
                internal:internal12,  /*周期  */
                end_datetime:end_datetime12, /*结束时间  */
                deletetype:checked112233,/* 删除类别 */
                session_start_datetime:$('#reservation22').val(),  /*开始时间 */
                session_end_datetime:$('#reservation23').val() /*结束时间  */

            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                    loadAJAX('#example2');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('新建失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#example2');
                }
            },
            error:function(){

            }
        })
    });
    $('#editButton').click(function(){
        var today=$('#edit_type1').val();
        var exec_datetime11;
        if(today==1){
            exec_datetime11=$('#reservation').val();
        }
        else if(today==2){
            exec_datetime11=$('#reservation1').val();
        }
        else{
            exec_datetime11="";
        };
        var checked11=document.getElementById("checkbox1").checked;
        var checked22=document.getElementById("checkbox2").checked;
        var checked33=document.getElementById("checkbox3").checked;
        var checked1;
        var checked2;
        var checked3;
        var checked123;
        if(checked11==true){
            checked1=$('#checkbox1').val()
        }
        else{
            checked1=""
        };
        if(checked22==true){
            checked2=$('#checkbox2').val()
        }
        else{
            checked2=""
        };
        if(checked33==true){
            checked3=$('#checkbox3').val()
        }
        else{
            checked3=""
        };
        checked123=checked1+checked2+checked3
        $.ajax({
            url:"../../logDeletePolicy/editLogDeletePolicy",
            type:"POST",
            data:{
                id:$('#edit_id1').val(),
                name:$('#edit_name1').val(),
                type:$('#edit_type1').val(),
                /*  internal:$('#internal1').val(), */  /*周期  */
                end_datetime:$('#reservation2').val(), /*结束时间  */
                deletetype:checked123,/* 删除类别 */
                session_start_datetime:$('#reservation3').val(),
                session_end_datetime:$('#reservation4').val(),  /*结束时间  */
                exec_datetime:exec_datetime11, /*执行时间  */
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('编辑成功!');
                    $("#modal-success").modal();
                    loadAJAX('#example2');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('编辑失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#example2');
                }
            },
            error:function(){

            }
        })
    });


    $('#delAllButton').click(function(){
        var obj = document.getElementsByName('chk[]');
        console.log(obj);
        var ids= new Array();
        for (i in obj){
            if(obj[i].checked)
                ids.push(obj[i].value);
        }
        console.log(ids);
        if(ids.length==0){
            $("#modal-hint.modal-title").text('失败');
            $("#modal-hint .modal-body").text('请选择要删除的信息');
            $("#modal-hint").modal();
            loadAJAX('#example2');
            return false;
        }
        $.ajax({
            url:"../../logDeletePolicy/delLogDeletePolicy",
            type:"POST",
            data:{
                ids:ids
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    loadAJAX('#example2');
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#example2');
                }
            },
            error:function(){
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#example2');
            }
        })
    });

    $('#delButton').click(function(){
        $.ajax({
            url:"../../logDeletePolicy/delLogDeletePolicy",
            type:"POST",          /*从服务器请求数据  */
            data:{
                ids: new Array($('#del_id').val())
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');

                    $('#example2').DataTable().ajax.reload()

                    loadAJAX('#example2');

                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                    loadAJAX('#example2');
                }
            },
            error:function(){

            }
        })
    });

    // 查询用户
    $.ajax({
        url:"../../user/listUser",
        type:"POST",
        async : true ,
        dataType: "json",
        data:{
            type:0,
            start:0,
            length:10000
        },
        success:function(data){
            var data = data.data;
            for(var i=0; i<data.length; i++){
                if(data[i].username.length>0)
                    $('#userlist').html($('#userlist').html()+'<div><input value="'+data[i].id+'" type="checkbox"><span>'+data[i].username+'</span></div>');
            }
            new DataRelative(document.getElementsByClassName("data-left5")[0],document.getElementsByClassName("data-right5")[0],document.getElementById("left-checked5"),document.getElementById("right-checked5"),document.getElementById("add5"),document.getElementById('remove5'),document.getElementsByClassName("data-right5")[0].getElementsByTagName("input"),document.getElementsByClassName("data-left5")[0].getElementsByTagName("input"));
        },
        error:function(){

        }
    });
    // 查询用户组
    $.ajax({
        url:"../../group/listGroup",
        type:"POST",
        async : true ,
        dataType: "json",
        data:{
            type: 0,
            start: 0,
            length: 10000,
            name:$('#searchIdU').val(),
        },
        success:function(data){
            var data = data.data;
            for(var i=0; i<data.length; i++){
                if(data[i].name.length>0)
                    $('#usergroupList').html($('#usergroupList').html()+'<div><input value="'+data[i].id+'" type="checkbox"><span>'+data[i].name+'</span></div>');
            }
            new DataRelative(document.getElementsByClassName("data-left6")[0],document.getElementsByClassName("data-right6")[0],document.getElementById("left-checked6"),document.getElementById("right-checked6"),document.getElementById("add6"),document.getElementById('remove6'),document.getElementsByClassName("data-right6")[0].getElementsByTagName("input"),document.getElementsByClassName("data-left6")[0].getElementsByTagName("input"));
        },
        error:function(){

        }
    });
    //设备
    $.ajax({
        url:"../../device/listDevice",
        type:"POST",
        async : true ,
        dataType: "json",
        data:{
            start:0,
            length:10000
        },
        success:function(data){
            var data = data.data;
            for(var i=0; i<data.length; i++){
                if(data[i].name.length>0)
                    $('#deviceList').html($('#deviceList').html()+'<div><input value="'+data[i].id+'" type="checkbox"><span>'+data[i].name+'</span></div>');
            }
            new DataRelative(document.getElementsByClassName("data-left7")[0],document.getElementsByClassName("data-right7")[0],document.getElementById("left-checked7"),document.getElementById("right-checked7"),document.getElementById("add7"),document.getElementById('remove7'),document.getElementsByClassName("data-right7")[0].getElementsByTagName("input"),document.getElementsByClassName("data-left7")[0].getElementsByTagName("input"));
        },
        error:function(){

        }
    });
    //设备组
    $.ajax({
        url:"../../group/listGroup",
        type:"POST",
        async : true ,
        dataType: "json",
        data:{
            type: 1,
            start: 0,
            length: 10000,
            name:$('#searchIdG').val(),
        },
        success:function(data){
            var data = data.data;
            for(var i=0; i<data.length; i++){
                if(data[i].name.length>0)
                    $('#devicegroupList').html($('#devicegroupList').html()+'<div><input value="'+data[i].id+'" type="checkbox"><span>'+data[i].name+'</span></div>');
            }
            new DataRelative(document.getElementsByClassName("data-left8")[0],document.getElementsByClassName("data-right8")[0],document.getElementById("left-checked8"),document.getElementById("right-checked8"),document.getElementById("add8"),document.getElementById('remove8'),document.getElementsByClassName("data-right2")[0].getElementsByTagName("input"),document.getElementsByClassName("data-left8")[0].getElementsByTagName("input"));
        },
        error:function(){

        }
    });
    $.ajax({
        url:"../../apppubServer/listApppubServer",
        type:"POST",
        async : true ,
        dataType: "json",
        data:{
            start:0,
            length:10000
        },
        success:function(data){
            var data = data.data;
            for(var i=0; i<data.length; i++){
                if(data[i].name.length>0)
                    $('#appList').html($('#appList').html()+'<div><input value="'+data[i].id+'" type="checkbox"><span>'+data[i].name+'</span></div>');
            }
            new DataRelative(document.getElementsByClassName("data-left4")[0],document.getElementsByClassName("data-right4")[0],document.getElementById("left-checked4"),document.getElementById("right-checked4"),document.getElementById("add4"),document.getElementById('remove4'),document.getElementsByClassName("data-right4")[0].getElementsByTagName("input"),document.getElementsByClassName("data-left4")[0].getElementsByTagName("input"));
        },
        error:function(){

        }

    });

});
function showfinger1(v){
    if(v=='1'){
        document.getElementById("div4").style.display="block";
    }else{
        document.getElementById("div4").style.display="none";
    }
    if(v=='2'){
        document.getElementById("div3").style.display="block";
    }else{
        document.getElementById("div3").style.display="none";
    }
}
function showfinger2(v){
    if(v=='1'){
        document.getElementById("div14").style.display="block";
    }else{
        document.getElementById("div14").style.display="none";
    }
    if(v=='2'){
        document.getElementById("div13").style.display="block";
    }else{
        document.getElementById("div13").style.display="none";
    }
    /* if(v=='6'){
        document.getElementById("div19").style.display="block";
     }else{
       document.getElementById("div19").style.display="none";
     } */
}
function showfinger(v){
    if(v=='d'){
        document.getElementById("div5").style.display="block";
    }else{
        document.getElementById("div5").style.display="none";
    }
    if(v=='e'){
        document.getElementById("div6").style.display="block";
    }else{
        document.getElementById("div6").style.display="none";
    }
}

function init() {
    //定义locale汉化插件
    var locale = {
        "format": 'YYYY-MM-DD   HH:MM:SS',
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
            singleDatePicker:true,
            timePicker:true,
            opens:"center",

        }
    );
    $('#reservation21').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,
            opens:"center",

        }
    );

    $('#reservation1').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,
            opens:"center",

        }
    );
    $('#reservation24').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,
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
            timePicker:true,

        }
    );
    $('#reservation25').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            opens:"center",
            timePicker:true,

        }
    );
    $('#reservation3').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,
            opens:"left",

        }
    );
    $('#reservation22').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,
            opens:"left",

        }
    );
    $('#reservation4').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,

            opens:"right",

        }
    );
    $('#reservation23').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,

            opens:"right",

        }
    );
    $('#reservation5').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,

            opens:"center",

        }
    );
    $('#reservation20').daterangepicker(
        {
            'locale': locale,
            showDropdowns:true,
            autoApply:false,
            singleDatePicker:true,
            timePicker:true,

            opens:"center",

        }
    );

};
$(document).ready(function() {
    init();
})