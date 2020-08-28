//加载角色
$.ajax({
    url:"../../role/listRole",
    type:"POST",
    data:{

    },
    success:function(data){
        var list = data.data;
        console.log(list)
        if(data.success){
            var html = '';
            var re_div = '';

            for(var i = 0;i<list.length;i++) {
                if (i==0){
                    html+='<li class="active"><a  data-toggle="tab" href="#activity';
                    html+='">'
                    html+=list[0].name;
                    html+='</a></li>';
                }else {
                    html+='<li><a  data-toggle="tab" href="#menu'+list[i].id+'';
                    html+='">'
                    html+=list[i].name;
                    html+='</a></li>';
                }

            }
            html+='<li><a  data-toggle="tab" href="#A';
            html+='">'
            html+='创建角色'
            html+='</a></li>';
            $("#menu").html(html);



            for(var i = 0;i<5;i++) {
                if(i==0){
                    re_div+='<div class="active tab-pane" id="activity" >';
                    re_div+='<div class="post">';
                    re_div+='<hr class="rm-hr1">';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="0" value="0"/><span >全景概览</span>';
                    re_div+='<hr >';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="28" value="28"/><span >部门管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>用户管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="1" value="1"/><span >用户列表</span>';
                    re_div+='<input type="checkbox" class="input"  name="add'+list[i].id+'" id="2" value="2" disabled="disabled" /><span >用户分组</span>';
                    re_div+='<input type="checkbox"  class="input"  name="add'+list[i].id+'" id="3" value="3" disabled="disabled" /><span >角色管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>资产管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="4" value="4" disabled="disabled" /><span >设备列表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="5" value="5" disabled="disabled" /><span >设备分组</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="27" value="27" disabled="disabled" /><span >系统类型</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="6" value="6" disabled="disabled" /><span >应用管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>策略管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="7" value="7" disabled="disabled" /><span >访问控制</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="8" value="8" disabled="disabled" /><span >命令控制</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>自动运维</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="9" value="9" disabled="disabled" /><span >执行任务</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="10" value="10" disabled="disabled" /><span >执行日志</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>审计管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="11" value="11" disabled="disabled" /><span >主机审计</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="12" value="12" disabled="disabled" /><span >应用审计</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="13" value="13" disabled="disabled" /><span >实时会话</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="14" value="14" disabled="disabled" /><span >系统日志</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="15" value="15" disabled="disabled" /><span >日志管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>统计分析</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="16" value="16" disabled="disabled" /><span >登录报表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="17" value="17" disabled="disabled" /><span >运维报表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="18" value="18" disabled="disabled" /><span >资源报表</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>系统管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="19" value="19" disabled="disabled" /><span >系统配置</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="20" value="20" disabled="disabled" /><span >网络配置</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="21" value="21" disabled="disabled" /><span >系统维护</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>操作运维</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'" disabled="disabled"  id="25" value="25"/><span >主机运维</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="26" value="26"/><span >应用运维</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>密码管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'" id="22" value="22" disabled="disabled" /><span >密码管理</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="23" value="23" disabled="disabled" /><span >改密策略</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="24" value="24" disabled="disabled" /><span >改密日志</span>';
                    re_div+='<div class="modal-footer" style="margin-top: 5px">\n</div>';
                    re_div+='</div>';
                    re_div+='</div>';
                }else{
                    re_div+='<div class=" tab-pane" id="menu'+list[i].id +'" >';
                    re_div+='<div class="post">';
                    re_div+='<hr class="rm-hr1">';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="0" value="0"/><span >全景概览</span>';
                    re_div+='<hr >';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="28" value="28"/><span >部门管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>用户管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input"  name="add'+list[i].id+'" id="1" value="1" disabled="disabled" /><span >用户列表</span>';
                    re_div+='<input type="checkbox" class="input"  name="add'+list[i].id+'" id="2" value="2" disabled="disabled" /><span >用户分组</span>';
                    re_div+='<input type="checkbox"  class="input"  name="add'+list[i].id+'" id="3" value="3" disabled="disabled" /><span >角色管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>资产管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="4" value="4" disabled="disabled" /><span >设备列表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="5" value="5" disabled="disabled" /><span >设备分组</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="27" value="27" disabled="disabled" /><span >系统类型</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="6" value="6" disabled="disabled" /><span >应用管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>策略管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="7" value="7" disabled="disabled" /><span >访问控制</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="8" value="8" disabled="disabled" /><span >命令控制</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>自动运维</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="9" value="9" disabled="disabled" /><span >执行任务</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="10" value="10" disabled="disabled" /><span >执行日志</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>审计管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="11" value="11" disabled="disabled" /><span >主机审计</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="12" value="12" disabled="disabled" /><span >应用审计</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="13" value="13" disabled="disabled" /><span >实时会话</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="14" value="14" disabled="disabled" /><span >系统日志</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="15" value="15" disabled="disabled" /><span >日志管理</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>统计分析</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="16" value="16" disabled="disabled" /><span >登录报表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="17" value="17" disabled="disabled" /><span >运维报表</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="18" value="18" disabled="disabled" /><span >资源报表</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>系统管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="19" value="19" disabled="disabled" /><span >系统配置</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="20" value="20" disabled="disabled" /><span >网络配置</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="21" value="21" disabled="disabled" /><span >系统维护</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>操作运维</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'" disabled="disabled"  id="25" value="25"/><span >主机运维</span>';
                    re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="26" value="26"/><span >应用运维</span>';
                    re_div+='<hr >';
                    re_div+='<div class="mch">';
                    re_div+='<p>密码管理</p>';
                    re_div+='</div>';
                    re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'" id="22" value="22" disabled="disabled" /><span >密码管理</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="23" value="23" disabled="disabled" /><span >改密策略</span>';
                    re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="24" value="24" disabled="disabled" /><span >改密日志</span>';
                    re_div+='<div class="modal-footer" style="margin-top: 5px">\n</div>';
                    re_div+='</div>';
                    re_div+='</div>';
                }

            }
            for(var i = 5;i<list.length;i++) {
                re_div+='<div class="tab-pane" id="menu'+list[i].id +'" >';
                re_div+='<div class="post">';
                re_div+='<hr class="rm-hr1">';
                re_div+='<input class="rm-username" type="text" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;输入角色名称" id="name'+list[i].id+'" onclick="clear('+list[i].id+')" onfocus="on('+list[i].id+')" onBlur="Fon('+list[i].id+')"><span id="msg'+list[i].id+'" style="color: red;font-size:12px;"></span>';
                re_div+='<hr >';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input"  name="add'+list[i].id+'" id="0" value="0"/><span >全景概览</span>';
                re_div+='<hr >';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" disabled="disabled" name="add'+list[i].id+'" id="28" value="28"/><span >部门管理</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>用户管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input"  name="add'+list[i].id+'" id="1" value="1"/><span >用户列表</span>';
                re_div+='<input type="checkbox" class="input"  name="add'+list[i].id+'" id="2" value="2"/><span >用户分组</span>';
                re_div+='<input type="checkbox"  class="input"  name="add'+list[i].id+'" id="3" value="3"/><span >角色管理</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>资产管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="4" value="4"/><span >设备列表</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="5" value="5"/><span >设备分组</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="27" value="27"/><span >系统类型</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="6" value="6"/><span >应用管理</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>策略管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="7" value="7"/><span >访问控制</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="8" value="8"/><span >命令控制</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>自动运维</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="9" value="9"/><span >执行任务</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="10" value="10"/><span >执行日志</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>审计管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="11" value="11"/><span >主机审计</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="12" value="12"/><span >应用审计</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="13" value="13"/><span >实时会话</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="14" value="14"/><span >系统日志</span>';
                re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="15" value="15"/><span >日志管理</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>统计分析</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="16" value="16"/><span >登录报表</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="17" value="17"/><span >运维报表</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="18" value="18"/><span >资源报表</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>系统管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="19" value="19"/><span >系统配置</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="20" value="20"/><span >网络配置</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="21" value="21"/><span >系统维护</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>操作运维</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'"   id="25" value="25"/><span >主机运维</span>';
                re_div+='<input type="checkbox" class="input"   name="add'+list[i].id+'" id="26" value="26"/><span >应用运维</span>';
                re_div+='<hr >';
                re_div+='<div class="mch">';
                re_div+='<p>密码管理</p>';
                re_div+='</div>';
                re_div+='<input type="checkbox" style="margin-left: 8px" class="input" name="add'+list[i].id+'" id="22" value="22"/><span >密码管理</span>';
                re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="23" value="23"/><span >改密策略</span>';
                re_div+='<input type="checkbox" class="input" name="add'+list[i].id+'" id="24" value="24"/><span >改密日志</span>';
                re_div+='<div class="modal-footer" style="margin-top: 20px;margin-left: -10px;">\n' +

                    '<input type="hidden" id="'+list[i].id+'">'+
                    '                          <button type="button" class="btn btn-default newaddbtn2" data-toggle="modal" data-target="#delRole" style="height: 30px;font-size: 12px;float: left;" onclick="show('+list[i].id+')">删除</button>\n' +
                    '                          <button type="button" class="btn newaddbtn" style="height: 30px;color: #fff;font-size: 12px;float: left;margin-left: 7px;margin-bottom: 20px;" onclick="editid('+list[i].id+')">修改</button>\n' +
                    '<span id="Error'+list[i].id+'" style="color: red;font-size:12px;float: left;margin-left: 7px;margin-top:6px"></span>\n'+
                    '                      </div>';
                re_div+='</div>';
                re_div+='</div>';


            }

            re_div+='<div class="tab-pane" id="A">\n' +
                '                  <div class="post">\n' +
                '                      <hr class="rm-hr1">\n' +
                '                      <input class="rm-username" type="text" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;输入角色名称" id="name" onclick="clearInput()"><span id="isexistR" style="color: red;font-size:12px;"></span>\n' +
                '                      <hr>\n' +
                '                      <input type="checkbox"  class="input" style="margin-left: 8px"  name="add" value="0"/><span >全景概览</span>\n' +
                '                      <hr >\n' +
                '                      <input type="checkbox"  class="input" style="margin-left: 8px"  name="add" value="28"/><span >部门管理</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>用户管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input" name="add"  value="1"/><span >用户列表</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"  value="2"/><span >用户分组</span>\n' +
                '                      <input type="checkbox"  class="input"   name="add" value="3"/><span >角色管理</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>资产管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"  name="add"  id="4" value="4"/><span >设备列表</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"  value="5"/><span >设备分组</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"  value="27"/><span >系统类型</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"   value="6"/><span >应用管理</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>策略管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"   name="add"  value="7"/><span >访问控制</span>\n' +
                '                      <input type="checkbox" class="input"   name="add"  value="8"/><span >命令控制</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>自动运维</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"  name="add"  value="9"/><span >执行任务</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"  value="10"/><span >执行日志</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>审计管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"  name="add"   value="11"/><span >主机审计</span>\n' +
                '                      <input type="checkbox" class="input"   name="add"  value="12"/><span >应用审计</span>\n' +
                '                      <input type="checkbox" class="input"   name="add"  value="13"/><span >实时会话</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"   value="14"/><span >系统日志</span>\n' +
                '                      <input type="checkbox" class="input"  name="add" value="15"/><span >日志管理</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>统计分析</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"   name="add"  value="16"/><span >登录报表</span>\n' +
                '                      <input type="checkbox" class="input"   name="add"  value="17"/><span >运维报表</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"   value="18"/><span >资源报表</span>\n' +
                '                      <hr >\n' +
                '                      <div class="mch">\n' +
                '                          <p>系统管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input"  name="add" value="19"/><span >系统配置</span>\n' +
                '                      <input type="checkbox" class="input"   name="add"  value="20"/><span >网络配置</span>\n' +
                '                      <input type="checkbox" class="input"  name="add"   value="21"/><span >系统维护</span>\n' +
                '                      <hr >\n' +
                '<div class="mch">\n'+
                '<p>操作运维</p>\n'+
                '</div>\n'+
                '<input type="checkbox" style="margin-left: 8px" class="input" name="add"   id="25" value="25"/><span >主机运维</span>\n'+
                '<input type="checkbox" class="input"   name="add" id="26" value="26"/><span >应用运维</span>\n'+
                '<hr >\n'+
                '                      <div class="mch">\n' +
                '                          <p>密码管理</p>\n' +
                '                      </div>\n' +
                '                      <input type="checkbox" style="margin-left: 8px" class="input" name="add" value="22"/><span >密码管理</span>\n' +
                '                      <input type="checkbox" class="input" name="add" value="23"/><span >改密策略</span>\n' +
                '                      <input type="checkbox" class="input" name="add" value="24"/><span >改密日志</span>\n' +
                '                      <div class="modal-footer" style="margin-top: 20px;margin-left: -10px;">\n' +
                '                          <button type="button" class="btn btn-default newaddbtn2"  style="height: 30px;font-size: 12px;float: left;">取消</button>\n' +
                '                          <button type="button" class="btn newaddbtn" style="height: 30px;color: #fff;font-size: 12px;float: left;margin-left: 7px;margin-bottom: 20px;" onclick="add()">保存</button>\n' +
                '<span id="Error" style="color: red;font-size:12px;float: left;margin-left: 7px; margin-top:6px;"></span>\n'+
                '                      </div>\n' +
                '                  </div>\n' +
                '              </div>';

            $("#re_div").html(re_div);

            // $("input[name='add2'][value=2]").attr("checked",true);
            for(var i=0; i<list.length; i++){
                var res = list[i].menu_id;
                var checkbook = 'add'+list[i].id;
                //回显名称
                if (i>3){
                    var role_name = list[i].name;
                    // var  _name = 'name'+list[i].id;
                    $('#name'+list[i].id+'').val(role_name);
                }
                // console.log(checkboxes.values)
                for(var j=0;j< res.length;j++){

                    $("input[name='"+checkbook+"'][value='"+res[j]+"']").attr("checked",true);
                    // console.log(j)
                }
            }

        }
        else{
        }
    },
})
//新建角色
$('#save').click(function () {
})
//新建角色
function add() {
    var userids = []; //申明数组保存所有被选中checkbox背后的值

    var users = document.getElementsByName("add"); //得到所有的checkbox
    for(var i=0; i<users.length; i++){
        if(users[i].checked){ //如果checkbox被选中
            console.log(users[i].value);
            userids.push(users[i].value); //将被选中checkbox背后的值新建到数组中
        }
    }

    if (userids.length==0){
        $('#Error').text("请选择权限")
        return;
    }
    if($('#isexistR').text()!=""){
        return;
    }
    if ($("#name").val() == ''){
        $('#isexistR').text("请输入角色名")
    }else {
        $.ajax({
            url:"../../role/addRole",
            type:"POST",
            data:{
                list: userids,
                name: $("#name").val(),
            },
            success:function(data){
                if(data.success){
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text('新建成功!');
                    $("#modal-success").modal();
                    setTimeout('window.location.reload()',2000);
                }else {
                    $('#isexistR').text(data.msg)
                }

            },
        })
    }
}

//传递角色id
function show(id) {
    $('#del_id').val(id);
}
//删除角色
function deleteByid() {
    var id= $('#del_id').val();
    $.ajax({
        url:"../../role/delRole",
        type:"POST",
        data:{
            id:id,
        },
        success:function(data){
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('操作成功!');
                $("#modal-success").modal();
                setTimeout('window.location.reload()',2000);

            }else {
                $("#modal-danger .modal-title").text('失败');
                $("#modal-danger .modal-body").text('操作失败!');
                $("#modal-danger").modal();
            }

        },
    })
}

//修改角色
function editid(id){
    var userids = []; //申明数组保存所有被选中checkbox背后的值
    var name = "add"+id;
    var users = document.getElementsByName(name); //得到所有的checkbox
    for(var i=0; i<users.length; i++){
        if(users[i].checked){ //如果checkbox被选中
            console.log(users[i].value);
            userids.push(users[i].value); //将被选中checkbox背后的值新建到数组中
        }
    }
    if ($('#msg'+id+'').text()!=""){
        return;
    }
    if($('#name'+id+'').val()==""){
        $('#Error'+id+'').text("角色名不能为空")
        return;
    }
    if (userids.length==0){
        $('#Error'+id+'').text("请选择权限")
        return;
    }else {
        $('#Error'+id+'').text("")
    }
    var role_name = $('#name'+id+'').val();
    $.ajax({
        url:"../../role/editRole",
        type:"POST",
        data:{
            id:id,
            menuid: userids,
            name: role_name,
        },
        success:function(data){
            console.log(data);
            if(data.success){
                $("#modal-success .modal-title").text('成功');
                $("#modal-success .modal-body").text('编辑成功!');
                $("#modal-success").modal();
                setTimeout('window.location.reload()',2000);
            }else {
                $('#msg'+id+'').text(data.msg)
            }

        },
    })
}
//判断
function Fon(id) {
    var regexp = {
        name:/^([A-Za-z]|[\u4e00-\u9fa5]|\-|\@|\_|[0-9]){0,32}$/,
        length:/^\S{0,32}$/,
    }
    //获取输入的名字
    let username = $('#name'+id+'').val();
    if (username==""){
        $('#msg'+id+'').text('角色名不能为空');
    }
    //判断是否符合规则
    if (!regexp.name.test(username)){
        //不符合
            $('#msg'+id+'').text('角色名格式不正确');
            if (!regexp.length.test(username)){
                $('#msg'+id+'').text("最长32个字符")
            }
        }
}

function on(id) {
    $('#msg'+id+'').text('');
}

function clear(id) {
    console.log("11");
    $('#name'+id+'').focus(function(){
        $('#msg'+id+'').text('');
    });
    $('#name'+id+'').blur(function(){


    });
}
function clearInput() {
    $('#name').focus(function () {
        $('#isexistR').text('');
    })
    $('#name').blur(function () {

        var regexp = {
            name: /^([A-Za-z]|[\u4e00-\u9fa5]|\-|\@|\_|[0-9]){0,32}$/,
            length: /^\S{0,32}$/,
        }
        //获取输入的名字
        let username = $('#name').val();
        if ($('#name').val()=="") {
            $('#isexistR').text('角色名不能为空');
        }
        //判断是否符合规则
        if (!regexp.name.test(username)) {
            //不符合
            $('#isexistR').text('角色名格式不正确');
            if (!regexp.length.test(username)) {
                $('#isexistR').text("最长32个字符")
            }
        }
        $.ajax({
            url:"../../role/checkname",
            type:"POST",
            data:{
                name:username,
            },
            success:function(data){
                if(!data.success){
                    $('#isexistR').text('角色名已存在');
                }
            }
        })
    });
}