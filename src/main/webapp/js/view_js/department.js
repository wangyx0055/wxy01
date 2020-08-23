let $table = $('#department');
let topNode = {id:0,text:''};
// 格式化按钮
function operateFormatter(value, row, index) {
    return [
        '<a data-toggle="modal" class="RoleOfedit newcss1" style="cursor:pointer;" >编辑</a> ',
        (row.id==1?'' : '<a data-toggle="modal" class="RoleOfdelete newcss2" style="margin-left: 20px;cursor:pointer;">删除</a>')
    ].join('');

}
//初始化编辑删除按钮的方法
window.operateEvents = {
        'click .RoleOfdelete': function (e, value, row, index) {
            del(row);
        },
        'click .RoleOfedit': function (e, value, row, index) {
            $.ajax({
                    "url":"../../department/findParentName",
                    type:"GET",
                    dataType:"json",
                    data: {
                        parent_id:row.id
                    },
                    success:function(result){
                        $('#departName').val(result.name);
                    }
            });
            update(row);
        }
};
//点击空白关闭tree控件
$('#modal-default2').click(function(event){
    $('#tree').css("display","none");
});
$('#departName').click(function (event) {
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
function update(row) {
    $('#modal-default2 .modal-title').text('编辑部门');
    if (row.id==1){
        $("#first_department").css("display",'none');
        $('#depart_id').val();
    }else {
        $("#first_department").css("display",'block');
    }
    $('#name').val(row.name);
    $('#description').val(row.description);
    $('#edit_id').val(row.id);
    $('#depart_id').val(row.parent_id);
    $('#tree').hide();
    $("#Vname").text('');
    $("#VdepartName").text('');
    $("#Vdescription").text('');
    $("#modal-default2").modal();
}
function del(row) {
    $('#del_id1').val(row.id);
    $('#modal-deldepart').modal();
}
//新建清楚编辑样式
$('#addBtn').click(function(){
    add();
});
function add(){
    $('#departName').removeAttr("disabled");
    $('#modal-default2 .modal-title').text('新建部门');
    $("#first_department").css("display",'block');
    $('#name').val('');
    $('#description').val('');
    $('#edit_id').val('');
    $('#depart_id').val(topNode.id);
    $('#departName').val(topNode.text);
    $('#tree').hide();
    $("#Vname").text('');
    $("#VdepartName").text('');
    $("#Vdescription").text('');
    $("#modal-default2").modal();
}
/**
 * 选中父项时，同时选中子项
 * @param datas 所有的数据
 * @param row 当前数据
 * @param id id 字段名
 * @param pid 父id字段名
 */
function selectChilds(datas,row,id,pid,checked) {
    for(const i in datas){
        if(datas[i][pid] === row[id]){
            datas[i].check=checked;
            selectChilds(datas,datas[i],id,pid,checked);
        };
    }
}

function selectParentChecked(datas,row,id,pid){
    for(const i in datas){
        if(datas[i][id] === row[pid]){
            datas[i].check=true;
            selectParentChecked(datas,datas[i],id,pid);
        };
    }
}
//初始化
let departmentList = function(field,value){
	let data = {start:0, length:10000};
	data[field]=value;
	$table.bootstrapTable('destroy').bootstrapTable({
	    idField: 'id',
	    dataType:'jsonp',
        "serverSide"	: true,
        formatLoadingMessage: function () {
            return '正在加载中,请稍后...'
        },
		 ajax:function(request){
		    $.ajax({
		      "url":"../../department/listDepartment",
		      type:"GET",
		      dataType:"json",
		      data: data,
		      success:function(result){
		        request.success({
		          row : result.data
		        });
		        $table.bootstrapTable('load', result.data);
                $('#department .no-records-found td').html("没有数据");
		      },
		      error:function(error){
		        console.log(error);
		      }
		    })
		 },
	    columns: [
	        { field: 'id', title: '选择',  formatter: function (value, row, index) {
	                return value === 1?'':'<input type="checkbox" name="chk[]" value="'+value+'" />';
	            }
	        },
            { field: 'id',  title: '部门ID' },
	        { field: 'name',  title: '名称' },
	        { field: 'description',  title: '描述' , "render" : function(data, type,row, mata) {
                    return '<div style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:150px;" data-html="true" data-toggle="tooltip" title="'+data+'">'
                        +data
                        + '</div>';
                }},
	        { field: 'count',  title: '用户数' },
	        { field: 'device_count',  title: '设备数' },
	        { field: 'operate', title: '操作', align: 'center', events : operateEvents, formatter: 'operateFormatter' },
	    ],
	    // bootstrap-table-treegrid.js 插件配置 -- start
	    //在哪一列展开树形
	    treeShowField: 'name',
	    //指定父id列
	    parentIdField: 'parent_id',
	    onResetView: function(data) {
	        $table.treegrid({
	            initialState: 'collapsed',// 所有节点都折叠
	            treeColumn: 1,
	            onChange: function() {
	                $table.bootstrapTable('resetWidth');
	            }
	        });
	        $table.treegrid('getRootNodes').treegrid('expand');
            $('#department div').tooltip();
	    },
	    onCheck:function(row){
            const datas = $table.bootstrapTable('getData');
            // 勾选子类
	        selectChilds(datas,row,"id","parent_id",true);
	        // 勾选父类
	        selectParentChecked(datas,row,"id","parent_id");
	        // 刷新数据
	        $table.bootstrapTable('load', datas);
	    },
	    onUncheck:function(row){
            const datas = $table.bootstrapTable('getData');
            selectChilds(datas,row,"id","parent_id",false);
	        $table.bootstrapTable('load', datas);
	    }
	});
};
/* 根据条件搜索 */
$('#search').click(function(){
    departmentList( $('#Distinguish').val(), $('#searchId').val());
});

var reloadDepartment = function(){
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
                $("#departName").click(function() {
                    let options = {
                        levels : 2,
                        data : result.data,
                        nodeIcon:'',
                        collapseIcon:"treegrid-expander treegrid-expander-expanded",
                        expandIcon:'treegrid-expander treegrid-expander-collapsed',
                        onNodeSelected : function(event, data) {
                            $("#departName").val(data.text);
                            $("#depart_id").val(data.id);
                            $("#tree").hide();//选中树节点后隐藏树
                            //ajax判断
                            checkNameAndDepartment();
                        },
                    };
                    $('#tree').treeview(options);
                    $('#tree').click(function (event) {
                        stopPropagation(event);
                    });
                });
            }
        },
    });
    departmentList( 'searchall', '');
};

//正则验证
let regexp = {
    name:/^([A-Za-z]|[\u4e00-\u9fa5]|\.|\-|\@|\_|[0-9]){1,32}$/,
};
$("#description").blur(function () {
    if($("#description").val().length >64){
        $("#Vdescription").text("超过限制长度");
    }
});
$("#name").blur(function () {
    if ($("#name").val() === ""){
        $("#Vname").text("部门名称不能为空");
    }else if(!regexp.name.test($("#name").val()) && $("#name").val().length <33){
        $("#Vname").text("部门名称格式不正确");
    }else if($("#name").val().length>32){
        $("#Vname").text("部门名称不能超过32个字符");
    }else {
        checkNameAndDepartment()
    }
/*    if (regExpUtil.checkName($("#name").val(),32) ==="null") {
        $("#Vname").text("部门名称不能为空");
    }else if (regExpUtil.checkName($("#name").val(),32) ==="false"){
        $("#Vname").text("部门名称格式不正确");
    } else if (regExpUtil.checkName($("#name").val(),32) ==="length"){
        $("#Vname").text("部门名称不能超过32个字符");
    }else {
        $("#Vname").text("");
    }*/
});

$("#departName").blur(function () {
    checkNameAndDepartment()
});
function checkNameAndDepartment() {
    $.ajax({
        url:"../../department/checkDepartmentName",
        type:"POST",
        data:{
            id:$('#edit_id').val(),
            name:$('#name').val(),
            parent_id:$('#depart_id').val(),
            parent_name:$('#departName').val(),
        },
        success:function(data){
            $('#VdepartName').text((typeof(data.info.TopDeptNull) === "undefined" ? '' : data.info.TopDeptNull) + (typeof(data.info.TopNameError) === "undefined" ? '' : data.info.TopNameError));
            $('#Vname').text(typeof(data.info.DuplicateName) === "undefined" ? '': data.info.DuplicateName);
        }
    })
}
//置空信息
$('#name').focus(function(){
    $('#VdepartName').text('');
    $('#Vname').text('');
});
$('#description').focus(function(){
    $('#Vdescription').text('');
});

$('#departName').focus(function(){
    $('#VdepartName').text('');
    $('#Vname').text('');
});
$(function () {
    //新建和编辑
    $('#addButton').click(function () {
        let url = "";
        if ($('#edit_id').val()!=""){
            url = "../../department/editDepartment"
        }else {
            url = "../../department/addDepartment"
        }
        $.ajax({
            url:url,
            type:"POST",
            data:{
                id:$('#edit_id').val(),
                name:$('#name').val(),
                description:$('#description').val(),
                parent_id:$('#depart_id').val(),
                parent_name:$('#departName').val(),
            },
            success:function(data){
                if(data.success === "error") {
                    if (typeof(data.errorMsg) === "undefined") {
                        $('#VdepartName').text((typeof(data.errorMap.TopDeptNull) === "undefined" ? '' : data.errorMap.TopDeptNull) + (typeof(data.errorMap.TopNameError) === "undefined" ? '' : data.errorMap.TopNameError));
                        $('#Vname').text(typeof(data.errorMap.DuplicateName) === "undefined" ? '': data.errorMap.DuplicateName);
                        $('#Vdescription').text('');
                    } else {
                        $('#VdepartName').text((typeof(data.errorMap.TopDeptNull) === "undefined" ? '' : data.errorMap.TopDeptNull) + (typeof(data.errorMap.TopNameError) === "undefined" ? '' : data.errorMap.TopNameError));
                        $('#Vname').text(typeof(data.errorMap.DuplicateName) === "undefined" ? (typeof(data.errorMsg) === "undefined" ? '': data.errorMsg.name): data.errorMap.DuplicateName);
                        $('#Vdescription').text(typeof(data.errorMsg) === "undefined" ? '' : data.errorMsg.description);
                    }
                }else if(data.success === "false") {
                    $("#modal-danger .modal-title").text('失败');
                    if ($('#edit_id').val() === "") {
                        $("#modal-danger .modal-body").text("新建失败!");
                        $("#modal-danger").modal();
                    }else {
                        $("#modal-danger .modal-body").text("编辑失败!");
                        $("#modal-danger").modal();
                    }
                }else {
                    $("#modal-success .modal-title").text('成功');
                    if ($('#edit_id').val() === ""){
                        $("#modal-success .modal-body").text("新建成功!");
                        $("#modal-success").modal();
                        $('#modal-default2').modal('hide');
                        reloadDepartment();
                    }else {
                        $("#modal-success .modal-body").text("编辑成功!");
                        $("#modal-success").modal();
                        $('#modal-default2').modal('hide');
                        reloadDepartment();
                    }

                }
            },
        })
    });
    //删除
    function delDepartment(ids){
        $.ajax({
            url:"../../department/delDepartment",
            type:"POST",
            data:{
                ids: ids
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('操作成功!');
                    $("#modal-success").modal();
                    $("#modal-delAlldepart1").modal('hide');
                    $("#modal-deldepart1").modal('hide');
                    $('#checkAll').prop("checked",false);
                    reloadDepartment();
                }
                else{
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('操作失败!');
                    $("#modal-danger").modal();
                    $('#checkAll').prop("checked",false);
                    reloadDepartment();
                }
            },
            error:function(){}
        })
    }
    //请求后台（单条数据）
    $('#delButton').click(function(){
        $('#modal-deldepart').modal('hide');
        $('#modal-deldepart1').modal();
    });
    $('#delAllButton').click(function(){
        $('#modal-delAlldepart').modal('hide');
        $('#modal-delAlldepart1').modal();
    });
    $('#delButton1').click(function(){
        let ids = new Array($('#del_id1').val());
        delDepartment(ids);
    });
    //请求后台（多条数据）
    $('#delAllButton1').click(function () {
        let obj = document.getElementsByName('chk[]');
        let ids= new Array();
        for (let i in obj){
            if(obj[i].checked)
                ids.push(obj[i].value);
        }
        if(ids.length==0){
            $("#modal-hint .modal-title").text('失败');
            $("#modal-hint .modal-body").text('请选择要删除的信息');
            $("#modal-hint").modal();
            reloadDepartment();
            return false;
        }
        delDepartment(ids);
    });
    reloadDepartment();
});
//导出清空数据
$('#_export').click(function(){
    $('#queryPsw').val("");
    $('#PswRep').text("");
});
$('#queryPsw').focus(function () {
    $('#PswRep').text("");
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
                window.open("../../export/exprotDepartment");
                $('#modal-default1').modal('hide');
            }
            else{
                if (data.msg==""){
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text('导出失败!');
                    $("#modal-danger").modal();
                    reloadDepartment();
                }else {
                    $("#PswRep").text(data.msg)
                }
            }
        },
        error:function(){

        }
    })
});

//下载模版
$('#downTemplate').click(function(){
    $('#downClick')[0].click();
});

$("#upload").off().on("click", function () {
    var s = $('#btn_file')[0].files[0];
    if(!s){
        $('#Vfile').text('请上传文件');
        return;
    }
    var formData = new FormData();
    formData.append("file_data", s);
    $("#modal-upload .modal-title").text('状态');
    $("#modal-upload .modal-body").text('正在导入...');
    $("#modal-upload").modal();
    $.ajax({
        url: "../../upload/department",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.success){
                $("#modal-upload").modal('hide');
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text(data.msg);
                $("#modal-success").modal();
                $('#modal-default').modal('hide');
            }else {
                $("#modal-upload").modal('hide');
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('导入失败!');
                $("#modal-danger").modal();
            }
            reloadDepartment();
            setTimeout(function () {
                if (data.errorInfo.length !== 0) {
                    $("#modal-uploadInfo").modal();
                    $('#uploadError').text(data.errorInfo+"----详细请看文档");
                }
            },1500)
        },
        error: function () {
        }
    });
});

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

$('#updateCount').click(function(){
    $.ajax({
        url:'../../department/update',
        dataType:"json",
        success:function(data){
            if (data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('刷新成功');
                $("#modal-success").modal();
            }else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('刷新失败!');
                $("#modal-danger").modal();
            }
            reloadDepartment();
        }
    })
})