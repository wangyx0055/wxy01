/*$('select').selectpicker();*/
jQuery(function($){
    // 备份jquery的ajax方法  
    var _ajax=$.ajax;
    // 重写ajax方法，先判断登录在执行success函数 
    $.ajax=function(opt){
		var _success = opt && opt.success || function (a, b) { };
        var _opt = $.extend(opt, {
			success: function (xhr, textStatus) {
				try {
					if(xhr.indexOf('用户登录')>0){
						window.location = '/login'
					}
				} catch (e) {
				}
				_success(xhr, textStatus);
			},
			error:function(xhr){
				if(xhr.responseText.indexOf('用户登录')>0){
					window.location = '/login'
				}
			}
        });
        _ajax(_opt);
    };
});
