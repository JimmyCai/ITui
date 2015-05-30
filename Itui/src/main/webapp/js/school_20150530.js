$('.dh').hide();
$(function() {
	
//	鼠标移出下拉框1秒后下拉框消失
	$('.dropdown-menu').mouseout(function(event){
		setTimeout("$('.dropdown-menu').css('display', 'none')",20);
	});


	// 回顶部事件
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
			: $('body')) : $('html,body');

	$('.back_top').click(function(event) {
		$body.animate({
			scrollTop : $('#nav0').offset().top
		}, 500);
		return false;
	});


	var wind_h = $(window).height();
	var wind_w = $(window).width();

	var mod_h = $('.tab').height();
	var mod_w = $('.tab').width();
	// 二维码
	$('.ew_ma').hide();
	var erwei_right = (wind_w - $('.containa').width()) / 2 - 80;
	$('.erwei').css({
		position : 'fixed',
		right : 100 + 'px'
	});
	$('.ew_01').mouseenter(function(event) {
		$('.ew_ma').show();
	});
	$('.ew_01').mouseleave(function(event) {
		$('.ew_ma').hide();
	});

	// 当浏览器窗口大小改变模态框的定位信息跟着改变
	function resize_modal() {
		$(window).resize(function(event) {
			wind_h = $(window).height();
			wind_w = $(window).width();

			mod_h = $('.tab').height();
			mod_w = $('.tab').width();
			load_modal(wind_w, wind_h, mod_w, mod_h);

		});
	}

	


});
// 页面加载函数结束
// 判断用户是否登录



// 点击关注按钮
// 关注按钮自定义函数开始
// 关注该学校
function atten() {
	$('.image2').css('display', 'block');
	$('.image4').css('display', 'none');
	$('.gz_sch1').text('关注该学校');
}
// 取消关注
function cancel_atten() {
	$('.image4').css('display', 'block');
	$('.image2').css('display', 'none');
	$('.gz_sch1').text('取消关注');
}
// 判断用户是否关注了当前院校
function Attention(follwid) {
	if (follwid == -1) {
		atten();
	} else {
		cancel_atten();
	}
	$('.image2').click(function(event) {
		attention_ajax();
	});
	$('.image4').click(function(event) {
		cancelattention_ajax();
	});
}

// 关注按钮自定义函数结束
function getPar(par) {
	// 获取当前URL
	var local_url = decodeURI(document.location.href);
	// 获取要取得的get参数位置
	var get = local_url.indexOf(par + "=");
	if (get == -1) {
		return false;
	}
	// 截取字符串
	var get_par = local_url.slice(par.length + get + 1);
	// 判断截取后的字符串是否还有其他get参数
	var nextPar = get_par.indexOf("&");
	if (nextPar != -1) {
		get_par = get_par.slice(0, nextPar);
	}
	return get_par;
}

// 2015年0201编辑
var collagename;
var collagelevel;
var collageid = getPar('name');
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

// ajax接收学校数据

function schoolList_ajax() {
	
	$.ajax({
		url : 'college.html',
		type : 'get',
		dataType : 'html',
		data : {
			'cid' : collageid,
			'code' : $.cookie("user")
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			
			if (data.status == 0) {
				
				// 获得学校的logo和所属城市
				var img_src = data.normalReturn.logo;
				var city_coll = data.normalReturn.city;
				$('.sch_name').find('img').attr('src',
						'http://www.itui.cn/itui/images/' + img_src);
				$('.cityp_2').text(city_coll);
				$('.sch_info').find('h3').text(data.normalReturn.college);
				//title标签
				$('title').text(data.normalReturn.college+'研究生院--'+data.normalReturn.college+'研究生招生信息网--爱推院校库')
				//关键字
				$('meta[name="Keywords"]').attr('content',data.normalReturn.college+'研究生院--'+data.normalReturn.college+'bbs--'+data.normalReturn.college+'考研--'+data.normalReturn.college+'研招网');
				//描述标签
				$('meta[name="Description"]').attr('content','爱推-智慧选校为广大学子提供'+data.normalReturn.college+'专业设置、'+data.normalReturn.college+'考研信息、'+data.normalReturn.college+'考研分数线、'+data.normalReturn.college+'历年录取情况、就业情况。');
				
				if (data.normalReturn.is34 == 0 && data.normalReturn.is985 == 0
						&& data.normalReturn.is211 == 0) {
					$('.level01').find('p').text('普通');
					$('.level02').remove();
					$('.level03').remove();
				} else {
					if (data.normalReturn.is34 == 1) {
						$('.level01').find('p').html('34所');
					} else {
						$('.level01').remove();
					}
					if (data.normalReturn.is985 == 1) {
						$('.level02').find('p').text('985');
					} else {
						$('.level02').remove();
					}

					if (data.normalReturn.is211 == 1) {
						$('.level03').find('p').text('211');
					} else {
						$('.level03').remove();
					}
				}

				// 获得关注信息
				var followid = data.normalReturn.followId;
				console.log(followid);
				Attention(followid);

				// 插入学院li
				var school = data.normalReturn.school;
				for (i = 0; i < school.length; i++) {
					collage_liTag(i, school[i]);
				}
				var nav02_H=$('#nav02').height()+30;
				$('#nav02').css('height',nav02_H+'px');
				
				
			}else
			{
			//404错误页面
			var err_msg=data.errMessage;
			if(err_msg!=='缺少参数')
				{
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";
				console.log('参数2');
				}else
				{
					console.log('参数0');
				}
				
			}
		}
	});

}
schoolList_ajax();


var collage_text = "";
// var school_obj={name:''};
// console.log(school_obj.name);
var school_name = '';
var majorname = '';
var major_htmlcode = '';

// 专业标签插入函数
function major_tag(majorname, i, value) {
	major_htmlcode = '\
	<li class="mod_li0">\
		<p class="mod_p">\
			<a href="javascript:;">'
			+ majorname + '\
			</a>\
		</p>\
	</li>';
	$('.mod_ul').append(major_htmlcode);
	$('.mod_li0').eq(i).attr('newid', value);
}

function major_center() {
	// 控制专业名称居中显示
	$('.mod_p').each(function() {
		var len_course = $(this).find('a').text().length;
		if (len_course < 13) {
			$(this).find('a').css('line-height', '40px');
		}
	});
	if ($('.modal_k').height() + 102 < $(window).height()) {
		$('body').css('overflow-y', 'hidden');
	}
	// 专业莫态框
	var hhh = $('.modal_k').height() + 64;
	var win_w = $(window).width();
	var win_h = $(window).height();
	var body_h = $('body').height();
	$('#modal0').height(body_h);
	// 模态框的位置信息
	$(".modal_k").css({
		position : "absolute",
		left : (win_w - $(".modal_k").width()) / 2
	});

}

// 专业点击函数

function collage_liTag(i, collage_text) {
	var li_tagcode = '\
		<li class="coll_li0">\
			<div class="con_0">\
			'
			+ collage_text
			+ '\
			</div>\
			<div class="arr_r"></div>\
		</li>'
	$('.collage_0').append(li_tagcode);

	var course_text = $('.coll_li0').eq(i).find('.con_0').text();
	var course_trim = $.trim(course_text);

	var len_course = course_trim.length;
	if (len_course < 8) {
		$('.coll_li0').eq(i).find('.con_0').css('line-height', '60px');
	} else {
		$('.coll_li0').eq(i).find('.con_0').css('line-height', '20px');
	}
	// 点击专业模态框出现
	modal_click(i, course_trim);

}
// 点击专业模态框出现
function modal_click(i, course_trim) {

	$('.coll_li0').eq(i).find('.con_0').click(function(event) {
		$('#modal0').show();
		$(window).scrollTop(0);

		$('.col_name').text(course_trim);

		// 关闭按钮
		$('.cha').click(function(event) {
			$('#modal0').hide();
			$('body').css('overflow-y', 'visible');
		});
		$('.mod_ul li').remove();
		majorList_ajax(course_trim);

	});

}

// ajax接收专业数据
function majorList_ajax(course_trim) {
	$.ajax({
		url : 'college/school.html',
		type : 'get',
		dataType : 'html',
		data : {
			's' : course_trim,
			'cid' : collageid
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (data.status == 0) {
				var major = data.normalReturn.major;
				var arr_major = new Array();
				for (i = 0; i < major.length; i++) {
					arr_major[i] = major[i].id;

					major_tag(major[i].name, i, major[i].id);
				}
				// 控制专业名居中显示
				major_center();
				$('.mod_li0').click(function(event) {
					var mid = $(this).attr('newid');
					major_jump(mid);
				});
			}else
			{
				//404错误页面
				var err_msg=data.errMessage;
				if(err_msg!=='缺少参数')
					{
					$.cookie("err_msg",err_msg, {path : "/"});
					location.href="error.html";
					console.log('参数2');
					}else
					{
						console.log('参数0');
					}
			}

		}
	});
}

// 点击专业出发ajax
function major_jump(mid) {
	//location.href = "info.html?major=" + mid;
	window.open("info.html?major="+mid,"_blank");
}
// 登录模态框
function load_modal(wind_w, wind_h, mod_w, mod_h) {
	// 定义模态框的绝对位置
	$('.tab').css({
		position : 'absolute',
		left : (wind_w - mod_w) / 2,
		top : (wind_h - mod_h) / 2
	});



	// 关闭登录模态框
	$('.close').click(function(event) {
		// 模态框出现xy轴滚动条出现
		$('body').css('overflow-y', 'visible');
		$('body').css('overflow-x', 'visible');
		$('#modal_load').css('display', 'none');

	});

}


// 如果点击关注将学校id发送给服务器 关注发送
function attention_ajax() {
	$.ajax({
		url : 'followcollege.html',
		type : 'post',
		dataType : 'html',
		data : {
			'cid' : collageid,
			'code' : $.cookie('user')
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (data.status == 0) {
				cancel_atten();
			} else 
			{
				//404错误页面
				var err_msg=data.errMessage;
				if(err_msg!=='缺少参数')
					{
					$.cookie("err_msg",err_msg, {path : "/"});
					location.href="error.html";
					}else
					{
						console.log('参数0');
					}
			}
		}
	});
}

// 如果点击关注将学校id发送给服务器 取消关注发送
function cancelattention_ajax() {
	$.ajax({
		url : 'disfollowcollege.html',
		type : 'post',
		dataType : 'html',
		data : {
			'cid' : collageid,
			'code' : $.cookie("user")
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (data.status == 0) {
				atten();
			} else 
			{
				//404错误页面
				var err_msg=data.errMessage;
				if(err_msg!=='缺少参数')
					{
					$.cookie("err_msg",err_msg, {path : "/"});
					location.href="error.html";
					
					}else
					{
						console.log('参数0');
					}
			}
		}
	});
}

$('#modal_load').hide();
