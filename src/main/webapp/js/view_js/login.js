var open_times = 0;
var send = 0;
$(function () {
	$('#myModal').on('hidden.bs.modal', function () {
	    open_times++;
	    $('#dynamiccode').focus();
	})
	$('#myModal').on('hide.bs.modal', function () {
	    open_times++;
	    $('#dynamiccode').focus();
	})
	var getFocus = function (){
	    if(send==0){
	        send++;
	    }
	}
	var getLoginType = function (){
		var username = $('#username').val();
	    $.ajax({
	        url:"./userLoginType",
	        type:"POST",
	        data:{
	            username:username
	        },
	        success:function(data){
	            if(data.success){
	                if(data.hasOwnProperty("sms_auth")){
						$('#login_method').val(4)
                		$('#mspassword').focus();
                		$('#smsmsg').text('已绑定手机，后四位:'+data.mobile);
	                }else if(data.hasOwnProperty("dynamic_auth")){
						$('#login_method').val(1)
	                	//if(t==null){
	                		$('#dpassword').focus();
	                	//}
	                }else if(data.hasOwnProperty("finger_auth")){
						$('#login_method').val(3)
						
					}else if(data.hasOwnProperty("local_finger_auth")){
						$('#login_method').val(2)
						
					}else{
						$('#login_method').val(0)
					}
					$("#login_method").change();
	            }else{
            		$('#smsmsg').text(data.msg);
	            }
	        },

	    })
	}
	var qrcodeInfo = function (){
		var username = $('#username').val();
	    var password = $('#dpassword').val();
	    var flag = (username==""||password=="");
	    if (flag){
	        alert('请输入用户名和密码');
	        return false;
	    }
	    $.ajax({
	        url:"./qrcodeInfo",
	        type:"POST",
	        data:{
	            username:username,
	            password:password,
	        },
	        success:function(data){
	            if(data.success){
	                if(data.hasOwnProperty("dynamic_auth")&&data.showQrcode==0){
	                	if(open_times<=0){
		                	open_times++;
		                    $("#dynamic_image").attr('src', './qrcode');
		                    $("#myModal").modal({
		                        keyboard:true//当按下esc键时，modal框消失
		                    })
	                	}
	                }
	            }
	            else{
	                $('#dpassword').focus();
	                send=0;
	                $("#modal-danger .modal-title").text('失败');
	                $("#modal-danger .modal-body").text('输入正确的用户名或密码');
	                $("#modal-danger").modal();
	            }
	        },

	    })
	}
	$('#sendsmsbtn').click(function(){
	    $.ajax({
	        url:"./send/sendSms",
	        type:"POST",
	        data:{
	            username: $('#username').val()
	        },
	        success:function(data){
	            if(data.success){
	                $("#modal-success .modal-title").text('成功');
	                $("#modal-success .modal-body").text('发送成功!');
	                $("#modal-success").modal();
	                $("#modal-dynamicAll").modal('hide');
	            }
	            else{
	                $("#modal-danger .modal-title").text('失败');
	                $("#modal-danger .modal-body").text('发送失败!');
	                $("#modal-danger").modal();
	            }
	        },
	        error:function(){}
	    })
	})
	$('#username').blur(function(){
		open_times=0;
		getLoginType();
	})
	$('#dpassword').blur(function(){
		open_times=0;
	})
	$('#dynamiccode').focus(function(){
		qrcodeInfo();
	});
	
	$("#login_method").change(function(){
		$(".login_sign").text("");
	/* 	$("#login_method").css("border","1px solid #E5E5E5 !important"); */
		console.log("hhhhhhh");
		var login_method = $("#login_method").find("option:selected").val();
		if(login_method==0){
			  $("#password_login").css("display","block");
			  $("#message_login").css("display","none");
			  $("#token_login").css("display","none");
			  $("#finger_login").css("display","none");
		}else if(login_method==1){
			  $("#password_login").css("display","none");
			  $("#message_login").css("display","none");
			  $("#token_login").css("display","block");
			  $("#finger_login").css("display","none");
		}else if(login_method==2){
			  $("#password_login").css("display","none");
			  $("#message_login").css("display","none");
			  $("#token_login").css("display","none");
			  $("#finger_div").css("display","block");
			  $("#finger_login").css("display","block");
	 		  _version();
		}else if(login_method==3){
			  $("#password_login").css("display","none");
			  $("#message_login").css("display","none");
			  $("#token_login").css("display","none");
			  $("#finger_div").css("display","none");
			  $("#finger_login").css("display","block");
	 		  _version();
		}else{
			  $("#password_login").css("display","none");
			  $("#message_login").css("display","block");
			  $("#token_login").css("display","none");
			  $("#finger_login").css("display","none");
			 
		}
	});
	$(".form-control").each(function () {
		$(this).click(function () {
			$(".login_sign").text("");
		})
	})
});