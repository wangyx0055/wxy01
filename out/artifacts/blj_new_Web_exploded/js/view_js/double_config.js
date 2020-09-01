          $.ajax({
		    url:"../../config/keepalived",
		    type:"POST",
		    success:function(data){
		        if(data.success){
		        	$('#hastatus').text(data.master==1?'启用':'禁用');
		        	$('#state').text(data.master==1?'主节点':'备节点');
				/*	if(data.master==1){
						$("#state").text('主节点');
						$('#hastatus').text("启用")
						$("#masterinput")[0].checked=true;
					}else if(data.master==0){
						$("#backupinput")[0].checked=true;
						$("#state").text('备节点');
						$("#hastatus").text('禁用');
					}*/
		        	$("#virtual_ipaddressinput1").text(data.virtual_ipaddress);
		        	$("#virtual_ipaddressinput").val(data.virtual_ipaddress);
		        	$("#mcast_src_ipinput1").text(data.mcast_src_ip);
		        	$("#mcast_src_ipinput").val(data.mcast_src_ip);
		        	$("#interfaceinput1").text(data.interface);
		        	$("#interfaceinput").val(data.interface);
		        }
		    },
		    error:function(){

		    }
		});
	//确定
	$("#keepaliveSubmit").click(function(){
		var flag=checkIP();
		if (flag === false) return false;
		if($("#masterinput")[0].checked==true){
			$("#state").text('主节点');
			$('#hastatus').text('启用');
		}else if($("#backupinput")[0].checked==true){
			$("#state").text('备节点');
			$('#hastatus').text('禁用');
		}
		$.ajax({
		    url:"../../config/configKpalive",
		    type:"POST",
		    data:{
		    	master:$('#roleHa input[name="ia"]:selected').val(),
		    	virtual_ipaddress:$('#virtual_ipaddressinput').val(),
		    	mcast_src_ip:$('#mcast_src_ipinput').val(),
		    	interface:$('#interfaceinput').val(),
		    	key:$('#mariadbkey').val()
		    },
		    success:function(data){
		    	if (data.success) {
                    $("#modal-default1").modal("hide");
                    $("#modal-success .modal-title").text('成功');
                    $("#modal-success .modal-body").text("编辑成功!");
                    $("#modal-success").modal();
					$("#state").text($("#masterinput")[0].checked==true?'主节点':'备节点');
					$('#hastatus').text($("#masterinput")[0].checked==true?'启用':'禁用');
					$("#virtual_ipaddressinput1").text($("#virtual_ipaddressinput").val());
					$("#mcast_src_ipinput1").text($("#mcast_src_ipinput").val());
					$("#interfaceinput1").text($("#interfaceinput").val());
                } else {
                    $("#modal-danger .modal-title").text('失败');
                    $("#modal-danger .modal-body").text("编辑失败!");
                    $("#modal-danger").modal();
                }
		    },
		    error:function(){

		    }
		})
	});
$("#double_edit").click(function (){
	$("#Vmariadbkey").text("");
	$("#Vmcast_src_ipinput").text("");
	$("#Vvirtual_ipaddressinput").text("");
	$("#Vinterfaceinput").text("");
   /* $("#virtual_ipaddressinput").val($("#virtual_ipaddressinput1").text());
	$("#mcast_src_ipinput").val($("#mcast_src_ipinput1").text());
	$("#interfaceinput").val($("#interfaceinput1").text());*/
});
function checkIP() {
	var mkey=$("#mariadbkey").val();
	var minput=$("#mcast_src_ipinput").val();
	var vinput=$("#virtual_ipaddressinput").val();
	var iinput=$("#interfaceinput").val();
	var flag=true;
	var p1=/^[0-9A-Za-z]{8,20}$/;
	var p2=/^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/;
	var p3=/^((128|192)|2(24|4[08]|5[245]))(\.(0|(128|192)|2((24)|(4[08])|(5[245])))){3}$/;
	if(mkey==""){
		$("#Vmariadbkey").text("请输入秘钥");
		flag=false;
	}else if(!p1.test(mkey)){
		$("#Vmariadbkey").text("秘钥格式不正确");
		flag=false;
	}
   if(minput==""){
	   $("#Vmcast_src_ipinput").text("请输入节点IP地址");
	   flag=false;
   }else if(!p2.test(minput)){
	   $("#Vmcast_src_ipinput").text("请输入有效的节点IP地址");
	   flag=false;
   }
     if(vinput==""){
	   $("#Vvirtual_ipaddressinput").text("请输入浮动IP地址");
	   flag=false;
   }  else if(!p2.test(vinput)){
	   $("#Vvirtual_ipaddressinput").text("请输入有效的浮动IP地址");
	   flag=false;
   }
   if(iinput==""){
	   $("#Vinterfaceinput").text("请输入浮动IP网口");
	   flag=false;
   }else if(!p3.test(iinput)){
	   $("#Vinterfaceinput").text("请输入有效的浮动IP网口");
	   flag=false;
   }
       return flag;
}

//单个判断
regexp={
  p1:/^[0-9A-Za-z]{8,20}$/,
  p2:/^([1-9]|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])\.(\d|[1-9]\d|1\d{2}|2[0-4][0-9]|25[0-5])$/,
 /* p22:/^([a-zA-Z][-a-zA-Z]{0,62}(\.[a-zA-Z][-a-zA-Z]{0,62})+\.?)$/,*/
  p3:/^((128|192)|2(24|4[08]|5[245]))(\.(0|(128|192)|2((24)|(4[08])|(5[245])))){3}$/,
};
//1
$("#mariadbkey").blur(function () {
	var mkey=$("#mariadbkey").val();
	if(mkey==""){
		$("#Vmariadbkey").text("请输入秘钥");
	}else if(!regexp.p1.test(mkey)){
		$("#Vmariadbkey").text("秘钥格式不正确");
	}
});
$("#mariadbkey").focus(function (){
	$("#Vmariadbkey").text("");
});
//2
$("#mcast_src_ipinput").blur(function () {
	var minput=$("#mcast_src_ipinput").val();
	if(minput==""){
		$("#Vmcast_src_ipinput").text("请输入节点IP地址");
	}else if(!regexp.p2.test(minput)){
		$("#Vmcast_src_ipinput").text("请输入有效的节点IP地址");
	}
})

$("#mcast_src_ipinput").focus(function (){
	$("#Vmcast_src_ipinput").text("");
})
//3
$("#virtual_ipaddressinput").blur(function (){
	var vinput=$("#virtual_ipaddressinput").val();
	if(vinput==""){
		$("#Vvirtual_ipaddressinput").text("请输入浮动IP地址");
	}else if(!regexp.p2.test(vinput)){
		$("#Vvirtual_ipaddressinput").text("请输入有效的浮动IP地址");
	}
})

$("#virtual_ipaddressinput").focus(function (){
	$("#Vvirtual_ipaddressinput").text("");
})
//4
$("#interfaceinput").blur(function () {
	var iinput=$("#interfaceinput").val();
	if(iinput==""){
		$("#Vinterfaceinput").text("请输入浮动IP网口");
	}else if(!regexp.p3.test(iinput)){
		$("#Vinterfaceinput").text("请输入有效的浮动IP网口");
	}
})
$("#interfaceinput").focus(function (){
	$("#Vinterfaceinput").text("");
})