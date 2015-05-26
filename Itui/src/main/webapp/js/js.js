// JavaScript Document
$(function() {

	// 搜索框

	// $('.zhuanye  a ').click(function(event) {
	// 	var neirong = $(this).html();
	// 	var neirong2 = $('.nr_js').html();
	// 	$('.nr_js').html(neirong);
	// 	$('.zhuanye  a ').html(neirong2);
	// });
	// 鼠标移出下拉框1秒后下拉框消失
	// $('.dropdown-menu').mouseout(function(event) {
	// 	setTimeout("$('.dropdown').removeClass('open')", 1000);
	// });

	// 搜索跳转
	function search_jump(major, value) {
		if (major == "专业") {
			major = 1;
			window.open("search.html?t=" + major + "&c=" + encodeURIComponent(value) + "&#.");
			console.log(encodeURIComponent(value));
		} if(major == "学院") {
			major = 2;
			window.open("search_school.html?t=" + major + "&c=" + encodeURIComponent(value)
							+ "&#.");
		}if(major == "达人"){
			console.log('达人跳转');
		}
	}
	
	// 20150515新搜索框
	var major_sou='专业';
	$('.btn-major').click(function(event) {
		event.preventDefault();
		major_sou=$('.btn-major').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#E67917').siblings('.btn-new-tab').css('color', '#fff');
		console.log(major_sou);
	});
	$('.btn-collage').click(function(event) {
		event.preventDefault();
		major_sou=$('.btn-collage').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#0CB9E3').siblings('.btn-new-tab').css('color', '#fff');
		console.log(major_sou);
	});
	$('.btn-people').click(function(event) {
		event.preventDefault();
		major_sou=$('.btn-people').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#73B403').siblings('.btn-new-tab').css('color', '#fff');;
		console.log(major_sou);
	});
	//移入
	$('.btn-new-tab').mousemove(function(event) {
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
	});
	//end20150515新搜索框


	
	// 搜索框ajax提交
	$('#sou').click(function(event) {
		// var major = $('.nr_js').text();
		var value = $('#scbar_txt').val();
		search_jump(major_sou, value);
		console.log(value);
	});

	// 获得焦点时按下回车调用跳转函数至信息页

	$('#scbar_txt').focus(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				// var major = $('.nr_js').text();
				var value = $('#scbar_txt').val();
				search_jump(major_sou, value);
				return false;
			}
		}
	});
	// 失去焦点时按下回车无反应
	$('#scbar_txt').blur(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				var value = $('#scbar_txt').val();
				search_jump(major_sou, value);
				return false;
			}
		}
	});

	// 确定二维码位置
	var win_width = $(window).width();
	$('.qr_code01').mouseenter(function(event) {
		$('.erwei03').css('display', 'block');
		$('.erwei04').css('display', 'none');
	}).mouseleave(function(event) {
		$('.erwei03').css('display', 'none');
	});
	$('.qr_code02').mouseenter(function(event) {
		$('.erwei04').css('display', 'block');
	})
	$('.erwei04').mouseleave(function(event) {
		$('.erwei04').css('display', 'none');
	});
	$('.erwei04').click(function(event) {
		$('.erwei04').css('display', 'none');
	});
	setTimeout(function() {
		$('.erwei04').hide();
	}, 5000);

	// 给页面尾添加点击函数
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

	});

	// 给用户协议添加点击跳转函数
	$('.agree_p').click(function(event) {
		var about_index = "2";
		$.cookie("about_index", about_index, {
			path : "/"
		});
		window.open("about.html");
	});
		
});

// 页面加载函数结束
//判断是否有权限发布课程信息
function ituidata(){
	$.ajax({
		url : 'api/course/verify',
		type : 'get',
		dataType : 'html',
		data : {"code" : $.cookie('user')},
		success : function(msg) {
			console.log(msg);
			data = eval('msg=' + msg);
			if (data == '0') {
				$('.coll01').css('display','block');
				
			} else {
				$('.coll01').css('display','none');
				console.log('no');
			}
		}
	});
	}
function is_load(){
// 判断用户是否登录
if ($.cookie('user') != undefined) {
	console.log('lallala');
	$('.load2 p').text("已登录");
	$('.load1').hide();
	$('.load2').hide();
	$('.logged').show();
	ituidata();
	$('.logged').mouseenter(function(){
		$('.drop_menu').css('display','block');
		
	}).mouseleave(function(){
		$('.drop_menu').css('display','none');
	});
	$('.coll00').click(function(event) {
		window.open("collect.html", "_blank");
	});
	$('.coll01').click(function(event) {
		window.open("form.html", "_blank");
	});  
	
	// 点击退出登录
	$('.coll02').click(function(event) {
		$('.logged').hide();
		$('.load1').show();
		$('.load2').show();
		console.log($.cookie('user'));
		$.removeCookie('user', {
			path : '/'
		});
		$.removeCookie('username', {
			path : '/'
		});
		window.location.reload();
	});
  }
}
is_load();
//登录按钮样式
//$('#load').css('background-color', '#cbcbcb');
//$('#load').attr('disabled', true);
// 注册按钮样式
$('#register').css('background-color', '#cbcbcb');
$('#register').attr('disabled', true);

// 创建注册对象
var indexregs_obj = {
	objemail : null,
	objpasd : null
};
//判断注册对象是不是null
function index_submit_judge() {
	if ((indexregs_obj.objemail!= null&&indexregs_obj.objemail!="") && (indexregs_obj.objpasd!= null&&indexregs_obj.objpasd!="")&&(check_state==1)) {
		$('#register').attr('disabled', false);
		$('#register').css('background-color', '#357EBD');
		// 调用点击函数

	} else {
		// 邮箱和密码若有一个错误择提交按钮可用
		$('#register').attr('disabled', true);
		$('#register').css('background-color', '#cbcbcb');
	}
}
// 注册邮箱验证开始
function regs_zhuce_email(){
	var index_email = $('#input_mail2').val();
	//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	var reg = /^[-_A-Za-z0-9\.]+@([-_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,10}$/;
	if (reg.test(index_email)) {
		$('.zc_email').html('√邮箱合法')
				.css('color', '#6fd415');
		// 将正确的邮箱存进注册对象
		indexregs_obj.objemail = index_email;
		// 判断是否两个输入框都合法
		index_submit_judge();
	} else {

		$('.zc_email').html('×邮箱不合法').css('color', 'red');
		indexregs_obj.objemail = null;
	}
	if (index_email == '') {
		$('.zc_email').html('×邮箱不能为空').css('color', 'red');
		indexregs_obj.objemail = null;
	}
}
regs_zhuce_email();
$('#input_mail2').keyup(function(event) {
		regs_zhuce_email();					

});
// 注册邮箱验证结束


// 注册密码验证开始
function regs_zhuce_pasd(){
	var indexpasd2 = $('#pasd2').val();
	var reg = /^[A-Za-z]\w{5,15}$/;
	if (reg.test(indexpasd2)) {
		$('.pasd4').html('√密码合法').css('color', '#6fd415');
		// 注册确认密码
		function regs_same_pasd(){
			var indexpasd3 = $('#pasd3').val();
			if (indexpasd2 == indexpasd3) {
				$('.ecqu').html('√密码一致').css('color',
						'#6fd415');
				// 将正确的密码存进注册对象
				indexregs_obj.objpasd = indexpasd3;
				// 判断是否两个输入框都合法
				index_submit_judge();

			} else {
				$('.ecqu').html('×两次密码输入必须一致').css('color',
						'red');
				indexregs_obj.objpasd = null;
			}	
		}
		regs_same_pasd();
		$('#pasd3').keyup(function(event) {
			regs_same_pasd();
					
		});

	} else {
		$('.pasd4').html('×输入6-15位以字母开头含有字母数字的密码').css('color',
				'red');
		indexregs_obj.objpasd = null;
	}
	if (indexpasd2 == '') {
		$('.pasd4').html('×密码不能为空').css('color', 'red');
		indexregs_obj.objpasd = null;
	}	
}
regs_zhuce_pasd();
$('#pasd2').keyup(function(event) {
	regs_zhuce_pasd();			
});

// 注册密码验证结束
// 给用户协议添加点击函数
var check_state = 1;
function checkbox_agree() {
	if ($('.agree_input').prop('checked') == true) {
		check_state = 1;
		index_submit_judge();

	} else {
		check_state = 0;
		 $('#register').attr('disabled', true);
		 $('#register').css('background-color', '#cbcbcb');
	}
}

$('.agree_input').click(function(event) {
	checkbox_agree();

});

// 点击提交按钮函数
	$('#register').click(function(event) {
		//$('#register').attr('disabled', true);
		//$('#register').css('background-color', '#cbcbcb');
		// 在用户名和密码都输入正确的情况下调用ajax
		if (check_state == 1) {
			index_register_ajax(indexregs_obj.objemail, indexregs_obj.objpasd);
		}
	});


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

// 首页注册ajax
function index_register_ajax(email, pasd) {
	$.ajax({
		url : 'register.html',
		type : 'post',
		dataType : 'html',
		data : {
			email : email,
			password : pasd
		},
		before:function(){
			$('.bt_p02').text('正在登陆……').css('color', '#6FD415');
		},
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data.status == 0) {
				// 注册失败
				if (data.normalReturn.register == 'failure') {
					$('.bt_p02').text(data.normalReturn.msg)
							.css('color', 'red');
					 $('#register').attr('disabled', true);
					 $('#register').css('background-color', '#cbcbcb');
					indexregs_obj.objemail = null;
					indexregs_obj.objpasd = null;
					regs_zhuce_email();
					regs_zhuce_pasd();
					
					// 重置表单
//					$('#zhuce')[0].reset();
//					$('.zc_email').html('');
//					$('.pasd4').html('');
//					$('.ecqu').html('');
					$('#input_mail2').focus();
				} else {
					//$('.bt_p02').text('先去邮箱查看邮件激活吧!').css('color', 'red');
					$('.modal-footer').html("");
					$('.modal-body').css('height','100px');
					var no_email='<div class="no_email"><p class="no_emP">没有收到邮件</p></div>';
					$('.modal-body').append(no_email);
					$('.no_email').css({
						'width':'100%',
						'margin-left': 'auto',
						'margin-right':'auto',
						'float':'none'
					});
					$('.bt_p02').css({
						'float':'left',
						'font-size':'20px',
						'color':'red'
						});
					$('.bt_p02').text('先去邮箱查看邮件激活吧!');
				}

			} else {
				// 404错误页面
				var err_msg = data.errMessage;
				$.cookie("err_msg", err_msg, {
					path : "/"
				});
				//location.href = "error.html";
				window.open("error.html","_blank");
				console.log(data.status);
			}
		}
		 
	});

}
// 注册邮箱验证
// 
// 登录模态框验证开始

// 创建登录对像
var indexload_obj = {
	objemail : null,
	objpasd : null
};
//判断登录对像是否为空开始
function index_load_judge() {
	if ((indexload_obj.objemail!= null&&indexload_obj.objemail!="" )&& (indexload_obj.objpasd!= null&&indexload_obj.objpasd!="")) {
		$('#load').attr('disabled', false);
		$('#load').css('background-color', '#357EBD');
		index_load_ajax(indexload_obj.objemail, indexload_obj.objpasd);

		// 点击登录按钮
	} else {
		//$('#load').attr('disabled', true);
		//$('#load').css('background-color', '#cbcbcb');
	}
}

// 判断登录对像是否为空结束

// 登录邮箱验证开始
function load_denglu_email(){
	var index_loademail = $('#input_mail').val();
	//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	var reg = /^[-_A-Za-z0-9\.]+@([-_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,10}$/;
	if (reg.test(index_loademail)) {
		$('.maildiv01').html('√邮箱合法').css('color',
				'#6fd415');
		// 将登录邮箱存进登录对象中
		indexload_obj.objemail = index_loademail;
		//index_load_judge();
	} else {
		$('.maildiv01').html('×邮箱不合法');
	}
	if (index_loademail == '') {
		$('.maildiv01').html('×邮箱不能为空');
	}
}

$('#input_mail').keyup(function(event) {
	load_denglu_email();					
});

// 登录邮箱验证结束
// 
// 获取登录密码开始
function load_denglu_pasd(){
	var index_loadpasd1 = $('#pasd').val();
	// 将登录密码存进登录对象
	indexload_obj.objpasd = index_loadpasd1;

}


$('#pasd').keyup(function(event) {
	load_denglu_pasd();	
});

// 获取登录密码结束
// 

//
// 登录点击函数
	$('#load').click(function(event) {
		//$('#load').attr('disabled', true);
		//$('#load').css('background-color', '#cbcbcb');
		load_denglu_email();
		load_denglu_pasd();
		index_load_judge();
		

	});

// 首页登录ajax
function index_load_ajax(email, pasd) {
	$.ajax({
		url : 'login.html',
		// url: 'load.php?msg'+Math.random(),
		type : 'post',
		dataType : 'html',
		data : {
			email : email,
			password : pasd
		},
		before:function(){
		$('.bt_p1 .bt_p01').text('正在登录……');	
		},
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data.status == 0) {
				if (data.normalReturn.login == 'failure') {
					//$('#load').attr('disabled', true);
					//$('#load').css('background-color', '#cbcbcb');
					$('.bt_p01').text(data.normalReturn.msg + '请重新登录！').css(
							'color', 'red');
					$('#input_mail').focus();
					indexload_obj.objemail = null;
					indexload_obj.objpasd = null;
					load_denglu_email();
				    load_denglu_pasd();

				} else {
					//$('#load').attr('disabled', true);
					//$('#load').css('background-color', '#cbcbcb');
					$('.bt_p01').text('恭喜你登录成功了！').css('color', 'red');
					$.cookie('user', data.normalReturn.code, {
						path : '/'
					});
					// 三秒之后将登录框关掉
					setTimeout(function() {
						$('#myModal').hide();
						$('.modal-backdrop').hide();
					}, 1500);
					// 登录成功后改变登录状态显示
//					$('.load2 p').text("已登录");
//					$('.load1').hide();
//					$('.load2').hide();
//					$('.logged').show();
//					$('.logged').click(function(event) {
//						window.open("collect.html", "_blank");
//					});
					is_load();
					$.cookie("username", indexload_obj.objemail, {
						path : "/"
					});
					console.log($.cookie("user"));
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

// 获得meta标签
$('.no_email').click(function(event) {
	//console.log(indexregs_obj.objemail);
	no_email_ajax();
});
function no_email_ajax() {
	$.ajax({
		url : 'resend_email',
		type : 'get',
		dataType : 'html',
		data : {
			"email" : indexregs_obj.objemail
		},
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data.status == 0) {
				$('.no_emP').text(data.normalReturn.msg);
			} else {

			}
		}
	});
}

console.log($.cookie("user"));
function total_Insert(span_str){
	var one_span;
	var two_span;
	two_span= span_str.substring(1, span_str.length); 
	one_span=span_str[0];

	var total_insert='<span class="one">'+one_span+'</span> <span class="two">'+two_span+'</span>';
	return total_insert;
}
//首页搜索总数统计
	$.ajax({
		url : 'api/index',
		type : 'get',
		dataType : 'html',
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data.status == 0) {			  	
				$('.total_ul li').eq(0).find('.total_p01').append(total_Insert(data.normalReturn.statsInfo.total.toString()));
				$('.total_ul li').eq(1).find('.total_p01').append(total_Insert(data.normalReturn.statsInfo.today.toString()));
				$('.total_ul li').eq(2).find('.total_p01').append(total_Insert(data.normalReturn.statsInfo.user_num.toString()));
				console.log(data);
				
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

	
	// 名片瀑布流
	// var w=0;
	// var person_card=new Array();
	// 	for(j=0;j<28;j++)
	// {
	// 	person_card[j]=j;
	// }
	// function li_html(li_num){
	// 	var card_li='<li class="card_li0'+li_num+'"></li>';
	// 	$('.card-ul').append(card_li);
	// }

	// function round_get_li(){

	// 	for(i=w+0;i<w+6;i++)
	// 	{
	// 		if((i)<person_card.length){
	// 			li_html(i);
	// 			$('.card_li0'+(i)).text(i);
	// 			console.log(i+'+'+w+'='+(i+w));
	// 		}else{

	// 			console.log('none'+i+'+'+w+'='+(i+w));
	// 			$('.get-more').text('没有更多');
	// 		}
			
	// 	}
	// 	console.log(i);
	// }
	// function less_get_li(less_num){
	// 	for(i=(0+w);i<w+less_num;i++)
	// 	{
	// 		li_html(i);
	// 		$('.card_li0'+(i)).text(i);
	// 	}
	// }

	
	// if(person_card.length>=6){
	// 	round_get_li();
	// }else{
	// 	var new_num=person_card.length;
	// 	less_get_li(new_num);
	// 	console.log(new_num);
	// }
	// $('.get-more').click(function(event) {
	// 	if(person_card.length>=6){
	// 		w+=6;
	// 		round_get_li();
	// 	}else{
	// 		$('.get-more').text('没有更多');
	// 	}
		
	// });

//导航区域
$('.itui-news-login-regs').mouseenter(function(event) {
	$('.dropdown-list').removeClass('hide');
});
$('.dropdown-list').mouseleave(function(event) {
	$('.dropdown-list').addClass('hide');
});
// 六个名片
var card_name='',card_pic='',card_follow_href='',card_des='';
var arr_cards=new Array(
				{
					cardName:'王一',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http://www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~1'
				},
				{
					cardName:'王二',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http://www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~2'
				},
				{
					cardName:'王三',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http://www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~3'
				},
				{
					cardName:'王四',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http:www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~4'
				},
				{
					cardName:'王五',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http:www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~5'
				},
				{
					cardName:'王六',cardPic:'images/01_avatar_max.jpg.png',
					cardFollowHref:'http:www.baidu.com',
					cardDes:'代表月亮消灭你我爱吃章鱼烧啦啦啦啦~6'
				}
			  );
console.log(arr_cards[5]);
for(i=0;i<arr_cards.length;i++)
{
	$('.li_img0'+i).attr('src', arr_cards[i].cardPic);
	$('.personal_des0'+i).text(arr_cards[i].cardDes);
	$('.card_name0'+i).text(arr_cards[i].cardName);
	$('.card_follow0'+i).attr('href', arr_cards[i].cardFollowHref);
}


//六个新闻
var news_href='',news_src='',news_item='',news_intr='';
var arr_news=new Array(
				{
					news_item:'丽江古城今日看点1',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~1'
				},
				{
					news_item:'丽江古城今日看点2',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~2'
				},
				{
					news_item:'丽江古城今日看点3',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~3'
				},
				{
					news_item:'丽江古城今日看点4',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~4'
				},
				{
					news_item:'丽江古城今日看点5',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~5'
				},
				{
					news_item:'丽江古城今日看点6',news_src:'images/news.jpg',
					news_href:'http://www.baidu.com',
					news_intr:'丽江这么美啊啊巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴~6'
				}
				
			  );
for(i=0;i<arr_news.length;i++)
{
	$('.news_img0'+i).attr('src', arr_news[i].news_src);
	$('.news_intr0'+i).text(arr_news[i].news_intr);
	$('.news_item0'+i).text(arr_news[i].news_item);
	$('.news_a0'+i).attr('href', arr_news[i].news_href);
}
