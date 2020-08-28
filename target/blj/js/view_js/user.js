//录入生物指纹
function methods(o){
    if(($('#id').val()=="")){
        if (o == 2 || o == 3){
            $('#div6').css('display', 'inline-block');
            $('#div4').css('display', 'none');
        } else
           {
            $('#div6').css('display', 'none');
            $('#div4').css('display', 'none');
        }
    }else{
        if (o == 2 || o == 3) {
            $('#div6').css('display', 'inline-block');
            $('#div4').css('display', 'none');
        } else if (o == 1) {
            $('#div4').css('display', 'inline-block');
            $('#div6').css('display', 'none');
        } else {
            $('#div6').css('display', 'none');
            $('#div4').css('display', 'none');
        }
        }
    if(o == 4){
        $("#tel_sign").css("color","red");
    }else{
        $("#tel_sign").css("color","white");
    }
}
function show1(o,id){
    if(o.innerText=="启用"){
        o.innerText="禁用";
        editstatus(1,id)
    }else{
        editstatus(0,id)
        o.innerText="启用"
    }
}

//时间初始化管理
let locale = {
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
let date = new Date();
let day = date.getDate();
let month = date.getMonth() + 1;
let year = date.getFullYear();
let minDate = year + "-" + month + "-" + day;
//动态获取截止日期的最小取值范围
function showEndTime() {
    $('#reservation2').daterangepicker(
        {
            'locale': locale,
            showDropdowns: true,
            autoApply: false,
            singleDatePicker: true,
            opens: "right",
            drops:"up",
            startDate: minDate,
            minDate: $('#reservation2').val(),//动态获取截止日期的最小取值范围
        }
    );
}
// function showEndTime1() {
//     $('#reservation2').daterangepicker(
//         {
//             'locale': locale,
//             showDropdowns: true,
//             autoApply: false,
//             singleDatePicker: true,
//             opens: "right",
//             startDate: minDate,
//             minDate: $('#reservation2').val(),//动态获取截止日期的最小取值范围
//         }
//     );
// }
//每次点击永久有效就判断 如果为空就显示时间
$('#add_long').click(() => {
    if ($('#add_long').prop('checked') == true) {
        $('#reservation').val('');
        $('#reservation2').val('');
        $('#reservation').prop('disabled', true);
        $('#reservation2').prop('disabled', true);
    } else {
        $('#reservation').prop('disabled', false);
        $('#reservation2').prop('disabled', false);
        $('#reservation').daterangepicker(
            {
                'locale': locale,
                showDropdowns: true,
                autoApply: false,
                singleDatePicker: true,
                opens: "left",
                drops:"up",
                startDate: minDate,
                minDate: minDate,
            }
        );
        $('#reservation2').daterangepicker(
            {
                'locale': locale,
                showDropdowns: true,
                autoApply: false,
                singleDatePicker: true,
                opens: "right",
                drops:"up",
                startDate: minDate,
                minDate: minDate,
            }
        );
    }
})
//每次选择改变起始日期就重新判断结束日期的可选范围
$('#reservation').blur(() => {
    showEndTime();
})


//导入清空内容
function resetFileInput(){
    $('#Vfile').text('');
    $('#btn_file').val('');
    $("#filename").text('');
}
function openFile() {
    $('#Vfile').text('');
    $("#filename").text('');
    $('#btn_file').click();
    $('#btn_file').change(function(){
        var file = $("#btn_file").val();
        var fileName = getFileName(file);
        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }
        $("#filename").html(fileName);
    })
}

//组名的验证规则
var regexp = {
    username:/^([A-Za-z]|\-|\_|\(|\)|[0-9]){0,32}$/,
    name:/^([A-Za-z]|[\u4e00-\u9fa5]|\-|\@|\_|[0-9]){0,32}$/,
    length:/^\S{0,32}$/,
    length_des:/^\S{0,128}$/,
}
//用户名
//失去焦点，移出输入框
$('#username').blur(function(){
    //组名的验证规则
    var username = $('#username').val();
    //判断是否符合规则
    if (!regexp.username.test(username)){
        //不符合
        $('#Vusername').text('用户名格式不正确');
        if (!regexp.length.test(username)){
            $('#Vusername').text("最长32个字符")
        }
    }else {
        //如果符合规则且输入框的内容不为空
        if ($('#id').val()==null || $('#id').val()==''){
            //进行重复判断
            checkusername();
        }
    }
});
// 获取焦点，重新输入
$('#username').focus(function(){
    $('#Vusername').text('');
})

//监听用户组组选择框
$('#groupid').change(function () {
    if ($('#groupid').val()!=null){
        $('#Vgroup').text('');
    }
});

// 检查重名
function checkusername(){
    $.ajax({
        url:"../../user/checkname",
        type:"POST",
        data:{
            username:$('#username').val(),
        },
        success:function(data){
            if(!data.success){
                $('#Vusername').text('用户名已存在');
            }
        }
    })
}

//姓名
$('#realname').blur(function(){
    var realname = $('#realname').val();
    if (!regexp.name.test(realname)){
        $('#Vrealname').text('姓名格式不正确');
        if (!regexp.length.test(realname)){
            $('#Vrealname').text("最长32个字符")
        }
    }
});
// 获取焦点
$('#realname').focus(function(){
    $('#Vrealname').text('');
})


// 失去焦点
$('#password').blur(function(){
    var FORMATE = new RegExp("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,32}$");
    var passwd = $('#password').val();
    if($("#query_passwd").val()!==""&&passwd==$("#query_passwd").val()){
        $("#V_pwd").text("");
    }
    $.ajax({
        url:"../../user/verify",
        type:"POST",
        success:function(data){
            verify = data.verify;
          /*  console.log(verify)*/
            if (!FORMATE.test(passwd) && verify==1){
                $('#Vpwd').text('密码格式不正确');
            }
        },
    })

});
// 获取焦点
$('#password').focus(function(){
    $('#Vpwd').text('');
})

// 失去焦点
$('#query_passwd').blur(function(){
    var passwd = $('#password').val();
    var _passwd = $('#query_passwd').val();
    if(passwd!=""&&_passwd==""){
        $('#V_pwd').text('请输入确认密码');
    }else if (passwd!=""&&passwd!=_passwd){
        $('#V_pwd').text('密码不一致');
    }
});
// 获取焦点
$('#query_passwd').focus(function(){
    $('#V_pwd').text('');
})

// 失去焦点
$('#email').blur(function(){
    var FORMATE = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var email = $('#email').val();
    if(email!=''){
        if (!FORMATE.test(email)){
            $('#Vemail').text('邮箱格式不正确');
        }
    }

});
// 获取焦点
$('#email').focus(function(){
    $('#Vemail').text('');
})

// 失去焦点
$('#mobile').blur(function(){
    var FORMATE = /^[1][0-9][0-9]{9}$/;
    var mobile = $('#mobile').val();
    if($("#u-methods").val()==4&&mobile==""){
        $('#Vmobile').text('请输入手机号');
    }else if(mobile!=''&&!FORMATE.test(mobile)){
        $('#Vmobile').text('手机号码格式不正确');
    }else{
        $('#Vmobile').text('');
    }
});
// 获取焦点
$('#mobile').focus(function(){
    $('#Vmobile').text('');
})

// 失去焦点
$('#qq').blur(function(){
    var FORMATE = /^[1-9][0-9]{4,14}$/
    var qq = $('#qq').val();
    if(qq!=''){
        if (!FORMATE.test(qq)){
            $('#Vqq').text('qq号格式不正确');
        }
    }

});
// 获取焦点
$('#qq').focus(function(){
    $('#Vqq').text('');
});

// 失去焦点
$('#wechat').blur(function(){
    var FORMATE = /([a-zA-Z\d_]{5,})?/;
    var wechat = $('#wechat').val();
    if(wechat!=''){
        if (!FORMATE.test(wechat)){
            $('#Vwechat').text('微信号格式不正确');
        }
    }
});
// 获取焦点
$('#wechat').focus(function(){
    $('#Vwechat').text('');
});
// 失去焦点
$('#description').blur(function(){
    var description = $('#description').val();
    if(description!=''){
        if (!regexp.length_des.test(description)){
            $('#Vdescription').text('超过限制长度');
        }
    }
});
// 获取焦点
$('#description').focus(function(){
    $('#Vdescription').text('');
});


function showMore() {
    if($('#id').val()==""){
        $('#modal-primary4 .modal-title').text('新建用户');
    }else {
        $('#modal-primary4 .modal-title').text('编辑用户');
    }

}

var _userlist = function(field,value){
    $('#userlist').DataTable({
        'paging' : true,
        "aLengthMenu":[
            [10, 20, 50, 100],['10条/页', '25条/页', '50条/页', '100条/页']
        ],
        "iDisplayLength": 10,
        'lengthChange': true,
        'dom'	: 't<"bottom"lifp<"clear">>S',
        'searching'   : false,
        'ordering'  : true,
        'info'   : true,
        'autoWidth'   : false,
        "serverSide"	: true,
        "destroy":true,
        "ajax": {
            "url":"../../user/listUser",
            "data": function (d) {
                if (field === "searchall" || field === "status"){
                    if ($('#searchId').val().match("启")||$('#searchId').val().match("启用")||$('#searchId').val().match("已启用")){
                        value=1;
                        field="status";
                    }else if($('#searchId').val().match("禁")||$('#searchId').val().match("禁用")||$('#searchId').val().match("已禁用")){
                        value=0;
                        field="status";
                    }else if($('#searchId').val()===''){
                        value='';
                    }else{
                        if(field === "searchall"){
                            value=$('#searchId').val();
                        }else if(field === "status"){
                            value=3;
                        }
                    }
                }
                for(var key in d){
                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                        delete d[key];
                    }
                }
                eval('d.'+field+'="'+value+'"');
            }
        },
        "columns": [
            { "data": "id" ,"render": function(data,type,row,meta){
                    return (row.id === 1|| row.id === 2|| row.id === 3) ?'':
                        '<input type="checkbox" name="chk[]" value='+data+'>';
                }},
            { "data": "username",
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';
                }},
            { "data": "realname" ,
                "render" : function(data, type,
                                    row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';

                }},
            { "data": "depart_name" , "render" : function(data, type,row, mata) {
                return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+row.topName+'">'
                    +data
                    + '</div>';
            }},
            { "data": "rolename"},
            { "data": "status", "render": function(data,type,row,meta){
                    if (data==0){
                        return '<span>已禁用</span>';
                    }else{
                        return '<span>已启用</span>';
                    }
                }},
            { "data": "id", "render": function(data,type,row,meta){
            		return '<a data-toggle="modal" data-row="'+meta.row+'" data-target="#edituser" class="newcss1" style="cursor:pointer;" >编辑</a> '+
                        ((row.id === 1) ? '' :
                        '<a id="showEnable"  onClick="show1(this,'+row.id+')" class="search-top newcss1" style="margin-left: 10px;text-align: center;cursor:pointer;" >'+(row.status === 0 ? '启用':'禁用')+'</a>')+
                        ((row.id === 1 || row.id === 2 || row.id === 3) ? '' :
                		'<a  data-toggle="modal" data-row="'+meta.row+'" data-target="#modal-deluser" class="newcss2" style="margin-left: 10px;cursor:pointer;">删除</a>');
                }}
        ],
        "fnDrawCallback": function( settings, json ) {
            $('#userlist div').tooltip();
        }
    });
}
_userlist('searchall','');
$('#search').click(function(){
    _userlist($('#Distinguish').val(),$('#searchId').val());
});

$('#edituser').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget);
      i = button.data('row');
      if(button.data('row')!=undefined&&button.data('row')!=null){
         $('#username').attr("readOnly","true");
        $('#edituser .modal-title').text('编辑用户');
        var edit_id = $('#userlist').DataTable().row('#' + i).nodes(i).data()[i].id;
        $('#id').val(edit_id);
        console.log( $('#id').val())
        var role_id = $('#userlist').DataTable().row('#' + i).nodes(i).data()[i].role_id;
        if (edit_id == 1 || edit_id ==2 ||edit_id==3){
            $('#role_id').attr("disabled",true);
            $('#depart_name').attr("disabled",true);
            $('#depart_name').css("background-color","white");
        }else{
            $('#role_id').attr("disabled",false);
            $('#depart_name').attr("disabled",false);
        }
        $('#username').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].username);
        $('#depart_name').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].depart_name);
        $('#depart_id').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].department);
        $('#type').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].type);
        $('#groupid').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].groupid);
        $('#role_id').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].role_id);
        $('#status').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].status);
        $('#realname').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].realname);
        $('#email').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].email);
        $('#div6').attr('data-user', edit_id);
        var local_auth=$('#userlist').DataTable().row('#' + i).nodes(i).data()[i].local_auth;
        var finger_auth=$('#userlist').DataTable().row('#' + i).nodes(i).data()[i].finger_auth;
        var dynamic_auth=$('#userlist').DataTable().row('#' + i).nodes(i).data()[i].dynamic_auth;
        var sms_auth=$('#userlist').DataTable().row('#' + i).nodes(i).data()[i].sms_auth;
          var valid_long = $('#userlist').DataTable().row('#' + i).nodes(i).data()[i].valid_long;
          if (valid_long == 0) {
              $('#reservation').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].valid_datetime_start);
              $('#reservation2').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].valid_datetime_end);
              $('#reservation').prop('disabled', false);
              $('#reservation2').prop('disabled', false);
              $("#add_long").prop("checked", false);
          } else {
              $('#reservation').val("");
              $('#reservation2').val("");
              $('#reservation').prop('disabled', true);
              $('#reservation2').prop('disabled', true);
              $("#add_long").prop("checked", true);
          }
        var methods = 0;
        if(dynamic_auth==1){
            methods = 1;
            $('#div6').css('display','none');
            $('#div4').css('display','inline-block');
        }else if(finger_auth ==1){
			if(local_auth==1){
				methods = 2;
			}else{
				methods = 3;
			}
            $('#div6').css('display','inline-block');
            $('#div4').css('display','none');
            
        }else if(sms_auth ==1){
            methods = 4;
            $('#div6').css('display','none');
            $('#div4').css('display','none');
        }else {
            methods = 0;
            $('#div6').css('display','none');
            $('#div4').css('display','none');
        }
        $('#u-methods').val(methods);
        if( $('#u-methods').val()==4){
            $("#tel_sign").css("color","red");
        }else {
            $("#tel_sign").css("color","white");
        }
        $('#radius_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].radius_auth==1?true:false);
        $('#ldap_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].ldap_auth==1?true:false);
        $('#ad_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].ad_auth==1?true:false);
        $('#sms_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].sms_auth==1?true:false);
        $('#weixin_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].weixin_auth==1?true:false);
        $('#email_auth')[0].checked=($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].email_auth==1?true:false);
        $('#mobile').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].mobile);
        $('#qq').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].qq);
        $('#wechat').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].wechat);
      /*  $('#password').val('');
        $('#query_passwd').val('');*/
        $('#password').attr("placeholder","\xa0\xa0\xa0********");
        $('#query_passwd').attr("placeholder","\xa0\xa0\xa0********");
        $('#V_pwd').text('');
        $('#Vpwd').text('');
        $('#Vemail').text('');
        $('#Vqq').text("");
        $('#Vwechat').text("");
        $('#Vrealname').text("");
        $('#Vusername').text("");
        $('#VdepartName').text("");
        $('#Vmobile').text("");
        $('#Vpwd').text("");
        $('#Vgroup').text("");
    }else{

    }
});

$('#newT').click(function () {
    clearT();
});
function clearT(){
    $('#edituser .modal-title').text('新建用户');
    $('#id').val('');
    $('#role_id option:first').prop("selected","selected");
    $('#username').val('');
    $('#depart_id').val(topNode.id );
    $('#depart_name').val(topNode.text);
    $("#tel_sign").css("color","white");
    $('#groupid').val('');
    $('#type').val('');
    $('#status').val('');
    $('#realname').val('');
    $('#password').val('');
    $('#query_passwd').val('');
    $('#email').val('');
    $('#radius_auth')[0].checked=false;
    $('#ldap_auth')[0].checked=false;
    $('#ad_auth')[0].checked=false;
    $('#sms_auth')[0].checked=false;
    $('#weixin_auth')[0].checked=false;
    $('#email_auth')[0].checked=false;
    $('#mobile').val('');
    $('#qq').val('');
    $('#wechat').val('');
    $('#description').val('');
    $('#Vdescription').text('');
    $('#Vemail').text('');
    $('#Vgroup').text('');
    $('#Vqq').text("");
    $('#Vwechat').text("");
    $('#Vrealname').text("");
    $('#Vusername').text("");
    $('#VdepartName').text('');
    $('#Vmobile').text("");
    $('#Vpwd').text("");
    $('#V_pwd').text("");
    $('#username').removeAttr("readOnly");
    $('#role_id').attr("disabled",false);
    $('#div6').css('display','none');
    $('#u-methods').val(0);
    $('#password').attr("placeholder","\xa0\xa0\xa0请输入用户密码")
    $('#query_passwd').attr("placeholder","\xa0\xa0\xa0请确认密码")
    $('#div4').css('display','none');
    $('#reservation').prop('disabled', true);
    $('#reservation2').prop('disabled', true);
    $('#reservation').val('');
    $('#reservation2').val('');
    $("#add_long").prop("checked", true);

}

$('#dynamicAllButton').click(function(){
    $.ajax({
        url:"../../user/resetDynamicToken",
        type:"POST",
        data:{
            id: $('#dynamic_id').val()
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                $("#modal-dynamicAll").modal('hide');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
        },
        error:function(){}
    })
});


// $('#modal-dynamicAll').on('show.bs.modal', function (event) {
//     var button = $(event.relatedTarget) // Button that triggered the modal
//     i = button.data('row');
//     $('#dynamic_id').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].id);
// });

function resetL(){
    $('#dynamic_id').val($('#id').val());
}
function editUser(){
    var url = "../../user/editUser";
    if($('#id').val()==""){
        url = "../../user/addUser";
    }
    var local_auth=0;
    var finger_auth=0;
    var dynamic_auth=0;
    var sms_auth = 0;
    var value = $('#u-methods').val();
    if (value==0){
        local_auth=1;
    }else if (value==1){
        local_auth = 1;
        dynamic_auth=1;
    }else if (value==4){
        sms_auth=1;
    }else if (value==2){
        local_auth = 1;
        finger_auth=1;
    }else if (value==3){
        finger_auth=1;
    }
    if ($('#add_long').prop('checked')) {
        var add_long = 1
        $('#reservation').val("")
        $('#reservation2').val("")
    } else {
        var add_long = 0
    }
    function blurStart() {
        var b = true;
        var regx = new RegExp("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,32}$");
        var FORMATE = /^[1][0-9][0-9]{9}$/;
        if ($('#username').val()==""){
                $('#Vusername').text('用户名不能为空');
                b=false;
            }
        if ( $('#realname').val()=="" || $('#Vrealname').text()!=""){
            if ($('#realname').val()==""){
                $('#Vrealname').text('姓名不能为空');
            }
            b=false;
           }
        if ($('#depart_name').val()=="") {
            $('#VdepartName').text('请选择部门');
            b = false;
        }
        if ($('#password').val()===""&&$('#id').val()==="") {
            $('#Vpwd').text('请输入密码');
            b = false;
        }
        if ($('#query_passwd').val()===""&&$('#id').val()===""){
            $('#V_pwd').text('请输入确认密码');
            b=false;
        }else if($('#password').val()!==""&&$('#query_passwd').val()!== $('#password').val()){
            $('#V_pwd').text('密码不一致');
            b=false;
           }
             if ($('#Vemail').text()!=""){
            b=false;
         }
        if($("#u-methods").val()==4&&$("#mobile").val()==""){
            $('#Vmobile').text('请输入手机号码');
            b=false;
        }else if($("#mobile").val()!=""&&!FORMATE.test($("#mobile").val())){
            $('#Vmobile').text('手机号码格式不正确');
            b=false;
        }
        return b;
    }
    if (blurStart()){
        $.ajax({
            url:url,
            type:"POST",
            data:{
                id:$('#id').val(),
                role_id:$("#role_id").val(),
                username:$('#username').val(),
                department:$('#depart_id').val(),
                type:$('#type').val(),
                groupid:$('#groupid').val(),
                status:$('#status').val(),
                realname:$('#realname').val(),
                password:$('#password').val(),
                valid_datetime_start: $('#reservation').val(),
                valid_datetime_end: $('#reservation2').val(),
                valid_long: add_long,
                last_change_password:Math.round(new Date().getTime()/1000).toString(),
                email:$('#email').val(),
                local_auth:local_auth,
                finger_auth:finger_auth,
                dynamic_auth:dynamic_auth,
                sms_auth:sms_auth,
                radius_auth:$('#radius_auth')[0].checked?1:0,
                weixin_auth:$('#weixin_auth')[0].checked?1:0,
                ad_auth:$('#ad_auth')[0].checked?1:0,
                ldap_auth:$('#ldap_auth')[0].checked?1:0,
                email_auth:$('#email_auth')[0].checked?1:0,
                mobile:$('#mobile').val(),
                qq:$('#qq').val(),
                wechat:$('#wechat').val(),
                description:$('#description').val(),
				fingerdata:$('#fingerdata').val(),
				fingerdatachange:$('#fingerchange').val()
            },
            success:function(data){
                if(data.success){
                    if ($('#id').val()==""){
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text('新建成功!');
                        $("#modal-success").modal();
                        $("#edituser").modal("hide");
                        $("#modal-primary4").modal("hide");
                    }else {
                        $("#modal-success .modal-title").text('成功');
                        $("#modal-success .modal-body").text('编辑成功!');
                        $("#modal-success").modal();
                        $("#edituser").modal("hide");
                        $("#modal-primary4").modal("hide");
                    }
                    loadAJAX('#userlist');
                }else{
                    if ($('#id').val()==""){
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text('新建失败:'+data.msg);
                        $("#modal-danger").modal();
                    }else {
                        $("#modal-danger .modal-title").text('失败');
                        $("#modal-danger .modal-body").text('编辑失败:'+data.msg);
                        $("#modal-danger").modal();
                    }
                }
            },
            error:function(){}
        })
    }

}
$('#editButton').click(function(){
    editUser();
});
$('#editButton1').click(function(){
    editUser();
    if($('#Vusername').text()!=""||$("#Vrealname").text()!=""||$('#Vpwd').text()!=""||$('#V_pwd').text()!=""||$('#Vemail').text()!=""){
        $("#modal-primary4").modal("hide");
        $("#edituser").modal("show");
    }else if ($("#Vqq").text()!=""||$('#Vwechat').text()!=""){
        return;
    }

});
$('#modal-deluser').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    let i = button.data('row');
    $('#del_id').val($('#userlist').DataTable().row('#' + i).nodes(i).data()[i].id);
});
$('#modal-default2').on('show.bs.modal', function (event){
	var button = $(event.relatedTarget); // Button that triggered the modal
    i = button.data('row');
	$('#fingerpage').attr('src', '../../view/user/finger')
});
$('#delButton').click(function(){
    $.ajax({
        url:"../../user/delUser",
        type:"POST",
        data:{
            ids: new Array($('#del_id').val())
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                $("#modal-deluser").modal('hide');
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }
            loadAJAX('#userlist');
        },
        error:function(){}
    })
});

//清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("");
});
$('#queryPsw').focus(function () {
    $('#PswRep').text("");
});
//下载模版
$('#downTemplate').click(function(){
    $('#downClick')[0].click();

});
$('#depart_name').focus(function () {
    $('#VdepartName').text('');
})
$("#upload").off().on("click", function () {
    var s = $('#btn_file')[0].files[0];
    if(!s){
        $('#Vfile').text('请上传文件');
        return;
    }
    var formData = new FormData();
    formData.append("file_data", s);
    formData.append("type", 0);
    $("#modal-upload .modal-title").text('状态');
    $("#modal-upload .modal-body").text('正在导入...');
    $("#modal-upload").modal();
    $.ajax({
        url: "../../upload/user",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.success){
                $('#modal-default').modal('hide');
                $("#modal-upload").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text(data.msg);
                $("#modal-success").modal();
            }else {
                $("#modal-upload").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('导入失败!');
                $("#modal-danger").modal();
            }
            setTimeout(function () {
                if (data.errorInfo.length !== 0) {
                    $("#modal-uploadInfo").modal();
                    if (data.errorInfo.length !== 0) {
                        $("#modal-uploadInfo").modal();
                        for(let item of data.errorInfo) {
                            $('#uploadError').append(item+"<br/>");
                        }
                        $('#uploadError').append("----详细请看文档和日志");
                    }
                }
            },1500);
            loadAJAX('#userlist');
        },
        error: function () {

        }
    });
});

$('#export').click(function(){
    $.ajax({
        url:"../../export/checkPwd",
        type:"POST",
        data:{
            password: $('#queryPsw').val(),
        },
        success:function(data){
            if(data.success){
                $('#downFile')[0].click();
                $('#modal-default1').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                }else {
                    $("#PswRep").text(data.msg);
                    loadAJAX('#userlist');
                }
            }
        },
        error:function(){

        }
    })
});

$.ajax({
    url:"../../role/findAll",
    type:"POST",
    success:function(data){
        if (data.success){
            for (var i = 0; i <data.data.length; i++){
                if(i==0){
                    $("#role_id").append("<option value='"+data.data[i].id+"' selected >"+data.data[i].name+"</option>");
                }else {
                    $("#role_id").append("<option value='"+data.data[i].id+"'  >"+data.data[i].name+"</option>");
                }
            }
        }
    }
});

$.ajax({
    url:"../../group/listByType",
    type:"POST",
    data:{
        type:0,
    },
    success:function(data){
        if (data.success){
            console.log(data.data);
            for (var i = 0; i < data.data.length; i++){
                if (i==0){
                    $("#groupid").append("<option value='"+data.data[i].id+"' selected >"+data.data[i].name+"</option>");
                }else {
                    $("#groupid").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
                }

            }


        }
    }
})

$.ajax({
    url:"../../user/verify",
    type:"POST",
    success:function(data){
        if(data.verify==1){
            var  html ="";
            html="<div style=\"min-width: 220px;float: left;color: #a4abb5;font-weight: 300;line-height: 1.4;letter-spacing: .6px;font-size: 12px;text-align: left;margin-left: 140px;\" id=\"showRule\"><span>密码规则 密码长度为8-32个字符，<br>需包含大小写字母、数字和特殊字符</span></div>";
            $('#showRule').html(html);
            $('#_showRule').attr("style","margin-top: 55px;");
        }else {
            $('#showRule').html('');
            $('#_showRule').attr("style","margin-top: 0px;");
        }
    }
})

function editstatus(status,id) {
    $.ajax({
        url:"../../user/editstatus",
        type:"POST",
        data:{
            id: id,
            status: status,
        },
        success:function(data){
            if (data.success){
                loadAJAX('#userlist');
            }else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('修改失败!');
                $("#modal-danger").modal();
                loadAJAX('#userlist');
            }
        }
    })
}
$('#delAllButton').click(function(){
    var obj = document.getElementsByName('chk[]');
  /*  console.log(obj);*/
    var ids= new Array();
    for (let i in obj){
        if(obj[i].checked)
            ids.push(obj[i].value);
    }
    if(ids.length==0){
        $("#modal-hint.modal-title").text('失败');
        $("#modal-hint.modal-body").text('请选择要删除的信息');
        $("#modal-hint").modal();
        loadAJAX('#userlist');
        return false;
    }
    $.ajax({
        url:"../../user/delUser",
        type:"POST",
        data:{
            ids:ids
        },
        success:function(data){
            if(data.success){
                $("#delAll").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功！');
                $("#modal-success").modal();
                loadAJAX('#userlist');
                $('#checkAll').prop("checked",false);
            }
            else{
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
                loadAJAX('#userlist');
            }
        },
        error:function(){
            $("#modal-danger .modal-title").text('失败');
            $("#modal-danger .modal-body").text('操作失败!');
            $("#modal-danger").modal();
            loadAJAX('#userlist');
        }
    })
});

let topNode = {id:0,text:''};
//点击空白关闭tree控件
$('#edituser').click(function(event){
    $('#tree').css("display","none");
});
$('#depart_name').click(function (event) {
    stopPropagation(event);
    $('#tree').css("display","block");
});
function stopPropagation(e) {
    e = e || window.event;
    if(e.stopPropagation) { //W3C阻止冒泡方法
        e.stopPropagation();
    } else {
        e.cancelBubble = true; //IE阻止冒泡方法
    }
}
$(function () {
    $.ajax({
        url:"../../department/findAll",
        type:"POST",
        contentType:'application/json',
        async: false,
        data: JSON.stringify({"helpId":"hello"}),//随便往后台传一个值
        success:function(result){
            if(result!=null){
                topNode.id = result.data[0].id;
                topNode.text = result.data[0].text;
                $("#depart_name").click(function() {
                    var options = {
                        levels : 2,
                        data : result.data,
                        nodeIcon:'',
                        collapseIcon:"treegrid-expander treegrid-expander-expanded",
                        expandIcon:'treegrid-expander treegrid-expander-collapsed',
                        onNodeSelected : function(event, data) {
                            $("#depart_name").val(data.text);
                            $("#depart_id").val(data.id);
                            $("#tree").hide();//选中树节点后隐藏树
                        }
                    };
                    $('#tree').treeview(options);
                    $('#tree').click(function (event) {
                        stopPropagation(event);
                    });
                });
            }
        },
    })
});