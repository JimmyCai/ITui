// 页面加载函数开始
$(function(){ 
	// 搜索专业学校转换
	$('.d_down').click(function(event) 
	{
		$('.dropdown-menu').css('display', 'block');
	});
	$('.zhuanye  a ').click(function(event) 
	{
		$('.dropdown-menu').css('display', 'none');
		var neirong=$(this).html();
		var neirong2=$('.nr_js').html();
		$('.nr_js').html(neirong);
		$('.zhuanye  a ').html(neirong2);
	});
	
	//回顶部事件
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');

	$('.back_top').click(function(event)
	 {
		$body.animate({scrollTop: $('#nav0').offset().top}, 500);
    					return false;
	});
	
		var wind_h=$(window).height();
		var wind_w=$(window).width();
		
		var mod_h=$('.tab').height();
		var mod_w=$('.tab').width();
	// 二维码
	$('.ew_ma').hide();
//	var erwei_right=(wind_w-$('.containa').width())/2-80;
//	$('.erwei').css({
//		position: 'fixed',
//		right: erwei_right+'px'
//	});
//	$('.ew_01').mouseenter(function(event) 
//	{
//		$('.ew_ma').show();
//	});
//	$('.ew_01').mouseleave(function(event) 
//	{
//		$('.ew_ma').hide();
//	});
// 判断用户是否登录开始
var user_value=$.cookie("user");
console.log(user_value+"dae");
if(user_value ==undefined)
{	$('.user2').css('display', 'block');
	$('.user2').find('img').attr('src', 'images/user2.png');
	$('.user').css('display', 'none');

//	判断当前页面是否是信息页
	var str_info=window.location.pathname;
	var str_texting=new RegExp('info');
	if(str_texting.test(str_info))
	{
	console.log('Yes');

	}else{
		console.log('no');
		lodal_close();
	}
}else{
	$('.user').css('display', 'block');
	$('.user2').css('display', 'none');
}
// 判断用户是否登录结束


// 点击未登录的用户头像出现登录框
$('.user2').click(function(event) {
	// 模态框出现xy轴滚动条消失
	// $('body').css('overflow-y', 'hidden');
	
	// 当窗口发送变化时模态框的位置信息
	resize_modal();
	// 然后让模态框出现
	// $('#modal_load').css('display', 'block');
	modal_visiblity()
	
});


// 调用用户中心函数
userCenter();
// 点击搜索按钮调用跳转函数至信息页
$('#sou').click(function(event) {
	var major = $('.nr_js').text();
	var value = $('#scbar_txt').val();
	search_jump(major,value);	
});
//获得焦点时按下回车调用跳转函数至信息页

$('#scbar_txt').focus(function(){
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			var major = $('.nr_js').text();
			var value = $('#scbar_txt').val();
			search_jump(major, value);
			console.log('focus');
		}
	}
});
// 失去焦点时按下回车无反应
$('#scbar_txt').blur(function(){
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			console.log('blur');
		}
	}
});

	
});
// 页面加载函数结束
// 
// 搜索框处理自定义函数开始
function search_jump(major, value) {
	console.log(major);
	console.log(value);
	if (major == "专业") {
		major = 1;
		window.open("search.html?t=" + major + "&c=" + value+"&#.");
	} else {
		major = 2;
		window.open("search_school.html?t=" + major + "&c=" + value+"&#.");
	}
	
	console.log("????");
}

// 搜索框处理自定义函数结束
// 
// 用户中心自定义函数开始
function userCenter()
{
	// 用户中心
$('.user').mouseenter(function(event) 
{
	$('.drop_menu').css('display', 'block');
	// 点击退出登录
	$('.out_coll').click(function(event){
		$('.user').css('display', 'none');
		$('.user2').css('display', 'block');
		$('.user2').find('img').attr('src', 'images/user2.png');
		$('.drop_menu').remove();
		console.log($.cookie('user'));
		$.removeCookie('user',{path:'/'});
		window.location.reload();
	});
}).mouseleave(function(event) 
{
	$('.drop_menu').css('display', 'none');
});
$('.collect').mouseenter(function(event) 
{
	$(this).find('.txt').css('color', '#5DB9E3');
}).mouseleave(function(event) 
{
	$(this).find('.txt').css('color', '#666');
});
	// 点击收藏列表跳转
	$('.jump_coll').click(function(event) 
	{
		window.open("collect.html","_blank");
	});	
}
// 用户中心自定义函数结束
// 
// 登录注册模态框实现自定义函数开始
// 定义注册对象
var regs_obj={objemail:'null',objpasd:'null'};
var load_obj={objemail:'null',objpasd:'null'};
// 注册邮箱验证
	function regs_mail()
	{
	$('.regs_mail').blur(function(event) 
	{
		var email02=$('.regs_mail').val();
		var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(reg.test(email02))
		{
		$('.errregs').html('√邮箱合法').css('color', '#6fd415');
		// 将正确的邮箱存进注册对象
		regs_obj.objemail=email02;
		// 判断是否两个输入框都合法
		submit_judge(regs_obj.objemail,regs_obj.objpasd);
		}else
		{
				
		$('.errregs').html('×邮箱不合法').css('color', 'red');
		regs_obj.objemail='null';
		}
		if(email02=='')
		{
		$('.errregs').html('×邮箱不能为空').css('color', 'red');
		regs_obj.objemail='null';
		}
	});
	}
	// 注册密码验证
	function regs_pasd()
	{
	$('.regs_pasd').blur(function(event)
	 {
	 	var pasd2=$('.regs_pasd').val();
		var reg =/^[A-Za-z]\w{5,15}$/;
		if(reg.test(pasd2))
		{
		$('.pasd_p').html('√密码合法').css('color', '#6fd415');
		// 注册确认密码
			$('.same_pasd').blur(function(event)
			 {
				var pasd3=$('.same_pasd').val();
				if(pasd2==pasd3)
				{
				$('.same').html('√密码一致').css('color', '#6fd415');
				// 将正确的密码存进注册对象
				regs_obj.objpasd=pasd3;
				// 判断是否两个输入框都合法
				submit_judge(regs_obj.objemail,regs_obj.objpasd);

				}else
				{
				$('.same').html('×两次密码输入必须一致').css('color', 'red');
				regs_obj.objpasd='null';

				}
			});

	            }else
		{
		$('.pasd_p').html('×输入6-15位以字母开头含有字母数字的密码').css('color', 'red');
		regs_obj.objpasd='null';
				
		}
		if(pasd2=='')
		{
		$('.pasd_p').html('×密码不能为空').css('color', 'red');
		regs_obj.objpasd='null';
		}
	});

	}

//
function load_modal(wind_w,wind_h,mod_w,mod_h)
{
	// 定义模态框的绝对位置
	$('.tab').css
	({
		position: 'absolute',
		left: (wind_w-mod_w)/2,
		top: (wind_h-mod_h)/2
	});
	
	// 注册按钮点击事件
	$('.regsitem').click(function(event)
	 {
		$('.regspage').css('display', 'block');
		$('.loadpage').css('display', 'none');
		$('.regsitem').css('border-bottom', '2px solid #FF7F27');
		$('.loaditem').css('border', 'none');
	});
	// 登录按钮点击事件
	$('.loaditem').click(function(event)
	 {
		$('.regspage').css('display', 'none');
		$('.loadpage').css('display', 'block');
		$('.regsitem').css('border', 'none');
		$('.loaditem').css('border-bottom', '2px solid #428BCA');
	});
	// 登录邮箱正则验证
	$('.mail_inp').blur(function(event) 
	{
		var email01=$('.mail_inp').val();
		
		var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

		if(reg.test(email01)){
			$('.errmsg').html('√邮箱合法').css('color', '#6fd415');
			// 将登录邮箱存进登录对象中
			load_obj.objemail=email01;
			load_judge(load_obj.objemail,load_obj.objpasd);
		}else{
			$('.errmsg').html('×邮箱不合法');
		}
		if(email01=='')
		{
		$('.errmsg').html('×邮箱不能为空');
		}
	});
	// 获得登录密码
	$('.pasd_inp').blur(function(event) 
	{
		var pasd1=$('.pasd_inp').val();
		// 将登录密码存进登录对象
		load_obj.objpasd=pasd1;
		load_judge(load_obj.objemail,load_obj.objpasd);
	});
	
	

	// 注册邮箱验证
	regs_mail();
	
	// 注册密码验证
	regs_pasd();
	

	// 重置注册表单
	$('.reset_regs').click(function(event)
	 {
		$('#regsrorm')[0].reset();
		$('.errregs').html('');
		$('.pasd_p').html('');
		$('.same').html('');
		$('.button_regs').attr('disabled', false);
		$('.button_regs').css('background-color', '#FF7F27');
	});
	// 重置登录表单
	$('.reset_load').click(function(event)
	 {
		$('#loadform')[0].reset();
		$('.errmsg').html('');
	});

	

}


// 判断注册对象是不是null
function submit_judge(email,pasd)
{
	if(email!='null'&&pasd!='null'){
		console.log('qaz');
		$('.button_regs').attr('disabled', false);
		$('.button_regs').css('background-color', '#FF7F27');
		submit_click(email,pasd);
		console.log('wer');
	}else{
		// 邮箱和密码若有一个错误择提交按钮不可用
		$('.button_regs').click(function(event) {
			$('.button_regs').attr('disabled', true);
//			$('.button_regs').css('background-color', '#cbcbcb');
		});

	}
}
// 提交按钮点击函数
function submit_click(email,pasd){
	console.log('qian');
	$('.button_regs').click(function(event){
		// 在用户名和密码都输入正确的情况下调用ajax
		console.log('zhong');
		$('.regspage').css('display', 'none');
		$('.waitpage').css('display', 'block');
		// $('.waitpage').delay(3000).hide(0);
        	$(".waitpage").hide();
        	register_ajax(email,pasd);
        	console.log('hou');
    	
});

}

// 判断登录对象是不是null
function load_judge(email,pasd)
{
	if(email!='null'&&pasd!='null')
	{
//		console.log('true');
//		$('.button_load').attr('disabled', false);
//		$('.button_load').css('background-color', '#428BCA');
		// 点击登录按钮
//		load_submit(email,pasd);
	}else{
		$('.button_load').attr('disabled', true);
//		$('.button_load').css('background-color', '#cbcbcb');
	}
}
// 给登录按钮添加点击函数
//function load_submit(email,pasd)
//{
$('.button_load').click(function(event){
	if (load_obj.objemail != null && load_obj.objpasd != null){
		load_ajax(load_obj.objemail,load_obj.objpasd);
	}else{
		
	}
});	
//}



// 登录注册模态框实现自定义函数结束
// 
// 当浏览器窗口大小改变模态框的定位信息跟着改变
	function resize_modal()
{	
	$(window).resize(function(event) 
	{	
		// 当窗口发生变化时得到动态的窗口宽高
		 wind_h=$(window).height();
		 wind_w=$(window).width();
		
		 mod_h=$('.tab').height();
		 mod_w=$('.tab').width();
		modal_position(wind_h,wind_w,mod_h,mod_w);

	});
}
// 得到窗口宽高
wind_h=$(window).height();
wind_w=$(window).width();
		
mod_h=$('.tab').height();
 mod_w=$('.tab').width();

// 自定义模态框可见函数
	function modal_position(wind_h,wind_w,mod_h,mod_w)
	{
	$('body').css('overflow-x', 'hidden');
	load_modal(wind_w,wind_h,mod_w,mod_h);
	var docu_height=$(document).height();
	$('#modal_load').css('height', docu_height);
	$('#gai_load').css('height', docu_height);
	
	}
 // 若用户没登陆直接显现模态框
 function modal_visiblity()
 {
 modal_position(wind_h,wind_w,mod_h,mod_w);
 resize_modal();
 $('#modal_load').css('display', 'block');
 }
// if ($.cookie('user') == undefined) 
	 modal_visiblity();

// 注册ajax
function register_ajax(email,pasd)
{
	$.ajax({
		url: 'register.html',
		type: 'post',
		dataType: 'html',
		data: {"email": email,"password":pasd},
		success:function(msg){
			data=eval('msg='+msg);
			if(data.status==0){
				if(data.normalReturn.register=='failure'){
					console.log('0');
					$('.regspage').css('display', 'block');
					$('.regsitem').css('border-bottom', '2px solid #FF7F27');
					$('.prompt').text(data.normalReturn.msg+'请重新注册').css('color', 'red');
				}else{
					// 登录成功则向用户显示登录窗口
					$('.loadpage').css('display', 'block');
					$('.loaditem').css('border-bottom', '2px solid #428BCA');	
					$('.regspage').css('display', 'none');
					$('.regsitem').css('border-bottom', 'none');
					$('.prompt').text('先去邮箱查看邮件激活吧!').css('color', 'red');
				}
			}else
			{
//				404错误页面
				var err_msg=data.errMessage;
				if(err_msg!=='请先登录！')
				{
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";
				}else{console.log(err_msg);}
			}
		}
	});	
}

// 登录ajax
function load_ajax(email,pasd)
{
$.ajax({
	url: 'login',
	type: 'post',
	dataType: 'html',
	data: {"email": email,"password":pasd},
	success:function(msg)
	{
		data=eval('msg='+msg);
		if(data.status==0){
			if(data.normalReturn.login=='failure')
			{
				alert("登录失败");
			}else{
				$.cookie("user", data.normalReturn.code,{path:"/"});
				$('#modal_load').css('display', 'none');
				$('.user').css('display', 'block');
				$('.user2').css('display', 'none');
				window.location.reload();
			}	
		}else
		{
//			404错误页面
			var err_msg=data.errMessage;
			if(err_msg!=='请先登录！')
			{
			$.cookie("err_msg",err_msg, {path : "/"});
			location.href="error.html";
			}else{console.log(err_msg);}
		}
	}
	});

}

if ($.cookie('user') != undefined){
	console.log($.cookie('user'));
	$('#modal_load').hide();
}

//关闭登录模态框函数
function lodal_close()
{
	$('.close').click(function(event)
			 {
				// 模态框出现xy轴滚动条出现
				$('body').css('overflow-y', 'visible');
				$('body').css('overflow-x', 'visible');
				$('#modal_load').css('display', 'none');
			});
}

