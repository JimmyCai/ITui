$(function() {
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

		console.log($.cookie("about_index"));
	});
});
// 登录注册模态框默认不显示
$('#modal_load').css('display', 'none');
$('#user_unload').hide();
$('.user2').css('display', 'block');
$('.info_prompt').hide();

// 定义注册对象
var forgetregs_obj = {
	objemail : 'null',
	objpasd : 'null'
};
// 判断注册对象是不是null
// 注册邮箱验证开始
function forget_email() {
	$('.forget_email')
			.blur(
					function(event) {
						var email_forget = $('.forget_email').val();
						var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
						if (reg.test(email_forget)) {
							$('.err_prompt01').html('√邮箱合法').css('color',
									'#6fd415');
							// 将正确的邮箱存进注册对象
							forgetregs_obj.objemail = email_forget;
							// 判断是否两个输入框都合法
							forget_submit_judge(forgetregs_obj.objemail,
									forgetregs_obj.objpasd);
						} else {

							$('.err_prompt01').html('×邮箱不合法').css('color',
									'red');
							forgetregs_obj.objemail = 'null';
						}
						if (email_forget == '') {
							$('.err_prompt01').html('×邮箱不能为空').css('color',
									'red');
							forgetregs_obj.objemail = 'null';
						}
					});
}
// 注册邮箱验证结束
forget_email();

// 注册密码验证
function forgetregs_pasd() {

	$('.forget_pasd').blur(
			function(event) {
				var forgetpasd2 = $('.forget_pasd').val();
				var reg = /^[A-Za-z]\w{5,15}$/;
				if (reg.test(forgetpasd2)) {
					$('.err_prompt02').html('√密码合法').css('color', '#6fd415');
					// 注册确认密码
					$('.sure_spasd').blur(
							function(event) {

								var forgetpasd3 = $('.sure_spasd').val();
								if (forgetpasd2 == forgetpasd3) {
									$('.err_prompt03').html('√密码一致').css(
											'color', '#6fd415');
									// 将正确的密码存进注册对象
									forgetregs_obj.objpasd = forgetpasd3;
									// 判断是否两个输入框都合法
									forget_submit_judge(
											forgetregs_obj.objemail,
											forgetregs_obj.objpasd);

								} else {
									$('.err_prompt03').html('×两次密码输入必须一致').css(
											'color', 'red');
									forgetregs_obj.objpasd = 'null';
								}
							});

				} else {
					$('.err_prompt02').html('×输入6-15位以字母开头含有字母数字的密码').css(
							'color', 'red');
					forgetregs_obj.objpasd = 'null';
				}
				if (forgetpasd2 == '') {
					$('.err_prompt02').html('×密码不能为空').css('color', 'red');
					forgetregs_obj.objpasd = 'null';
				}
			});

}
forgetregs_pasd();

// 判断注册对象是不是null
function forget_submit_judge(email_forget, forgetpasd3) {
	if (email_forget != 'null' && forgetpasd3 != 'null') {
		$('.forget_sub').attr('disabled', false);
		$('.forget_sub').css('background-color', '#FF7F27');
		console.log('true');
		forget_submit_click(email_forget, forgetpasd3);

	} else {
		// 邮箱和密码若有一个错误择提交按钮不可用
		$('.forget_sub').click(function(event) {
			$('.forget_sub').attr('disabled', true);
			$('.forget_sub').css('background-color', '#cbcbcb');
		});
	}
}
// 点击提交按钮函数
function forget_submit_click(email, pasd) {
	$('.forget_sub').click(function(event) {
		// 在用户名和密码都输入正确的情况下调用ajax
		// $('.regspage').css('display', 'none');
		// $('.waitpage').css('display', 'block');
		// $('.waitpage').delay(3000).hide(0);
		forget_register_ajax(email, pasd);
	});

}

// 忘记密码ajax提交
//设置全局的404错误跳转页面
$.ajaxSetup({
    statusCode: {
        404: function () {
        	var err_msg = data.errMessage;
			$.cookie("err_msg", err_msg, {
				path : "/"
			});
			location.href = "error.html";
        }
    }
});
// 注册ajax
function forget_register_ajax(email,pasd)
{
$.ajax
({
		// url: 'http://42.96.190.127:8080/Itui/register?msg'+Math.random(),
		url : 'sendResetEmail',
		type : 'get',
		dataType : 'html',
		data : {
			email : email,
			password : pasd
		},
		success : function(msg) {
			data = eval('msg=' + msg);
			if (status == 0) {
				if (data.normalReturn.reset == 'failure') {
					$('.forget_div h3').text(data.normalReturn.msg + '请重新注册')
							.css('color', 'red');
				} else {
					// 登录成功后提醒用户去邮箱查看邮件
					$('.forget_form').hide();
					$('.info_prompt').show();

					$('.info_prompt p').text(data.normalReturn.msg)
							.css('color', 'red');
					console.log(pasd);
					console.log(email);

				}

			} else {
				// 404错误页面
				var err_msg = data.errMessage;
				$.cookie("err_msg", err_msg, {
					path : "/"
				});
				location.href = "error.html";
			}
		}
	});

}
