// // 页面加载函数开始
// $(function(){ 
// 	// 搜索专业学校转换
// 	$('.d_down').click(function(event) 
// 	{
// 		$('.dropdown-menu').css('display', 'block');
// 	});
// 	$('.zhuanye  a ').click(function(event) 
// 	{
// 		$('.dropdown-menu').css('display', 'none');
// 		var neirong=$(this).html();
// 		var neirong2=$('.nr_js').html();
// 		$('.nr_js').html(neirong);
// 		$('.zhuanye  a ').html(neirong2);
// 	});

// 	//回顶部事件
// 	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');

// 	$('.back_top').click(function(event)
// 	 {
// 		$body.animate({scrollTop: $('#nav0').offset().top}, 500);
//     					return false;
// 	});

// 		var wind_h=$(window).height();
// 		var wind_w=$(window).width();

// 		var mod_h=$('.tab').height();
// 		var mod_w=$('.tab').width();
// 	// 二维码
// 	$('.ew_ma').hide();
// 	var erwei_right=(wind_w-$('.containa').width())/2-80;
// 	$('.erwei').css({
// 		position: 'fixed',
// 		right: erwei_right+'px'
// 	});
// 	$('.ew_01').mouseenter(function(event) 
// 	{
// 		$('.ew_ma').show();
// 	});
// 	$('.ew_01').mouseleave(function(event) 
// 	{
// 		$('.ew_ma').hide();
// 	});
// // 判断用户是否登录开始
// var user_value=$.cookie("user");

// if(user_value==undefined)
// {	$('.user2').css('display', 'block');
// 	$('.user2').find('img').attr('src', 'images/user2.png');
// 	$('.user').css('display', 'none');
// }else
// {
// $('.user').css('display', 'block');
// $('.user2').css('display', 'none');
// }
// // 判断用户是否登录结束

// // 点击未登录的用户头像出现登录框
// $('.user2').click(function(event) {
// 	// 模态框出现xy轴滚动条消失
// 	$('body').css('overflow-y', 'hidden');
// 	$('body').css('overflow-x', 'hidden');
// 	load_modal(wind_w,wind_h,mod_w,mod_h);
// 	$('#modal_load').css('display', 'block');
// 	resize_modal();

// });

// // 当浏览器窗口大小改变模态框的定位信息跟着改变
// 	function resize_modal()
// {	
// 	$(window).resize(function(event) 
// 	{	
// 		 wind_h=$(window).height();
// 		 wind_w=$(window).width();

// 		 mod_h=$('.tab').height();
// 		 mod_w=$('.tab').width();
// 		load_modal(wind_w,wind_h,mod_w,mod_h);

// 	});
// }
// // 调用用户中心函数
// userCenter();
// // 点击搜索按钮调用跳转函数至信息页
// $('#sou').click(function(event) {
// 	var major = $('.nr_js').text();
// 	var value = $('#scbar_txt').val();
// 	search_jump(major,value);	
// });
// // 按下回车调用跳转函数至信息页
//  document.onkeydown = function(e)
//     {  
//       var ev = document.all ? window.event : e;
//       if(ev.keyCode==13) 
//       {
//           var major = $('.nr_js').text();
// 	var value = $('#scbar_txt').val();
// 	search_jump(major,value);	
//       }
//     }

// });
// 页面加载函数结束
// 
// 搜索框处理自定义函数开始
// function search_jump(major,value)
// {
// 	if(major=="专业")
// 	{
// 		major=1;
// 	}else
// 	{
// 		major=2;
// 	}
// 	window.open("search.html?t="+major+"&c="+value,"_blank");
// }

// // 搜索框处理自定义函数结束
// // 
// // 用户中心自定义函数开始
// function userCenter()
// {
// 	// 用户中心
// $('.user').mouseenter(function(event) 
// {
// 	$('.drop_menu').css('display', 'block');
// 	// 点击退出登录
// 	$('.out_coll').click(function(event) 
// 	{
// 	$('.user').css('display', 'none');
// 	$('.user2').css('display', 'block');
// 	$('.user2').find('img').attr('src', 'images/user2.png');
// 	$('.drop_menu').remove();
// 	});
// }).mouseleave(function(event) 
// {
// 	$('.drop_menu').css('display', 'none');
// });
// $('.collect').mouseenter(function(event) 
// {
// 	$(this).find('.txt').css('color', '#5DB9E3');
// }).mouseleave(function(event) 
// {
// 	$(this).find('.txt').css('color', '#666');
// });
// 	// 点击收藏列表跳转
// 	$('.jump_coll').click(function(event) 
// 	{
// 	});	
// }
// // 用户中心自定义函数结束
// // 
// // 登录注册模态框实现自定义函数开始
// function load_modal(wind_w,wind_h,mod_w,mod_h)
// {
// 	// 定义模态框的绝对位置
// 	$('.tab').css
// 	({
// 		position: 'absolute',
// 		left: (wind_w-mod_w)/2,
// 		top: (wind_h-mod_h)/2
// 	});

// 	// 注册按钮点击事件
// 	$('.regsitem').click(function(event)
// 	 {
// 		$('.regspage').css('display', 'block');
// 		$('.loadpage').css('display', 'none');
// 		$('.regsitem').css('border-bottom', '2px solid #FF7F27');
// 		$('.loaditem').css('border', 'none');
// 	});
// 	// 登录按钮点击事件
// 	$('.loaditem').click(function(event)
// 	 {
// 		$('.regspage').css('display', 'none');
// 		$('.loadpage').css('display', 'block');
// 		$('.regsitem').css('border', 'none');
// 		$('.loaditem').css('border-bottom', '2px solid #428BCA');
// 	});
// 	// 登录邮箱正则验证
// 	$('.mail_inp').blur(function(event) 
// 	{
// 		var email01=$('.mail_inp').val();

// 		var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

// 		  if(reg.test(email01))
// 		  {
// 		  $('.errmsg').html('√邮箱合法').css('color', '#6fd415');
// 		  }
// 	        else{

// 		$('.errmsg').html('×邮箱不合法');
// 		  }
// 		if(email01=='')
// 		{
// 		$('.errmsg').html('×邮箱不能为空');
// 		}
// 	});
// 	// 登录密码正则验证

// 	// 注册邮箱验证
// 	$('.regs_mail').blur(function(event) 
// 	{
// 		var email02=$('.regs_mail').val();
// 		var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
// 		if(reg.test(email02)){

// 				$('.errregs').html('√邮箱合法').css('color', '#6fd415');
// 			}else{

// 				$('.errregs').html('×邮箱不合法').css('color', 'red');
// 				}
// 		if(email02==''){
// 			$('.errregs').html('×邮箱不能为空').css('color', 'red');
// 		}
// 	});

// 	// 注册密码验证
// 	$('.regs_pasd').blur(function(event)
// 	 {
// 	 	var pasd2=$('.regs_pasd').val();
// 		var reg =/^[A-Za-z]\w{5,15}$/;
// 		if(reg.test(pasd2))
// 		{
// 		$('.pasd_p').html('√密码合法').css('color', '#6fd415');
// 		// 注册确认密码
// 			$('.same_pasd').blur(function(event)
// 			 {
// 				var pasd2=$('.regs_pasd').val();
// 				var pasd3=$('.same_pasd').val();
// 				if(pasd2==pasd3)
// 				{
// 					$('.same').html('√密码一致').css('color', '#6fd415');
// 				}else
// 				{
// 					$('.same').html('×两次密码输入必须一致').css('color', 'red');;
// 				}
// 			});

// 		}else
// 		{
// 		$('.pasd_p').html('×输入6-15位以字母开头含有字母数字的密码').css('color', 'red');

// 		}
// 		if(pasd1=='')
// 		{
// 		$('.pasd_p').html('×密码不能为空').css('color', 'red');;
// 		}
// 	});

// 	// 重置注册表单
// 	$('.reset_regs').click(function(event)
// 	 {
// 		$('#regsrorm')[0].reset();
// 		$('.errregs').html('');
// 		$('.pasd_p').html('');
// 		$('.same').html('');
// 	});
// 	// 重置登录表单
// 	$('.reset_load').click(function(event)
// 	 {
// 		$('#loadform')[0].reset();
// 		$('.errmsg').html('');
// 	});

// 	// 关闭登录模态框
// 	$('.close').click(function(event) {
// 		// 模态框出现xy轴滚动条出现
// 		$('body').css('overflow-y', 'visible');
// 		$('body').css('overflow-x', 'visible');
// 		$('#modal_load').css('display', 'none');
// 	});

// }
// 登录注册模态框实现自定义函数结束
// 
// 
$(function(){
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

		console.log($.cookie("about_index"));
	});
});
// 收藏列表学校插入自定义函数开始

var coll_name = "北京大学";
var follow_count = 8;
var major_name = "计算机及应用";

function listsch_insert(id_ul, coll_name, follow_count, collage_id) {

	major_name = "计算机及应用";
	var listsch_htmlcode = '<div class="sch_part sch_part0'
			+ id_ul
			+ '">\
			<div class="item0 item0'
			+ id_ul
			+ '" new_cid="'
			+ collage_id
			+ '">\
				<div class="btn_and btn_and0'
			+ id_ul
			+ '">+</div>\
				<div class="btn_and btn_minus btn_minus0'
			+ id_ul
			+ '">-\
				</div>\
				<p class="schname schname0'
			+ id_ul
			+ '">'
			+ coll_name
			+ '</p>\
				<div class="total">\
				<span>(</span>\
				<span class="total_num total_num0'
			+ id_ul
			+ '">'
			+ follow_count
			+ '</span>\
				<span>)</span>\
				</div>\
				<div class="sch_delete sch_delete0'
			+ id_ul
			+ '" title="删除收藏学校">×</div>\
			</div>\
			<ul class="ul0 ul0'
			+ id_ul + ' ul_height">\
			</ul>\
		     </div>';
	$('#sch_list').append(listsch_htmlcode);

}

// 收藏列表学校插入自定义函数结束
// 
// 专业插入自定义函数开始
function listmajor_insert(ul_id, li_id, major_name, follow_count, major_id) {

	var listmajor_htmlcode = '\
			<li class="li_list lilist_0'
			+ li_id
			+ '" new_mid='
			+ major_id
			+ '>\
		                  <p>'
			+ major_name
//			+ ul_id
//			+ li_id
			+ '</p>\
		                  <div class="major_delete major_delete0'
			+ ul_id + li_id
			+ '" title="删除收藏专业">×</div>\
 		             </li>';

	$('.ul0' + ul_id).append(listmajor_htmlcode);

	delete_major(ul_id, li_id);

}
// 专业插入自定义函数结束
// 
// 自定义点击删除学校函数开始
function sch_confirm(partid) {
	var r = confirm("你确定要删除收藏的学校吗？");
	if (r == true) {
		$('.sch_part0' + partid).remove();
	} else {
		alert("我不删除");
	}
}

function delete_sch(ul_id) {
	$('.sch_delete0' + ul_id).click(function(event) {
		var cid = $('.item0' + ul_id).attr('new_cid');
		// 将用户删除学校的ID发给后台
		post_cid(cid);
		// 删除学校弹框
		sch_confirm(ul_id);

	});
}
// 自定义点击删除学校函数结束
// 
// 自定义点击删除专业函数开始
function major_confirm(ul_id, li_id) {
	var r = confirm("你确定要删除收藏的专业吗？");
	if (r == true) {
		$('.ul0' + ul_id).find('.lilist_0' + li_id).remove();

	} else {

	}

}

// 获得每个专业的ID，并调用ajax将专业ID传输给后台
function get_mid(ul_id, li_id) {
	var mid = $('.ul0' + ul_id).find('.lilist_0' + li_id).attr('new_mid');
//	var num = $('.total_num0'+ul_id).text();
//	console.log(num);
	post_mid(mid, ul_id);

}

// 自定义点击删除专业函数结束
// 学校专业目录自定义函数开始
function schlist_control(i) {
	// 点击加号专业目录出现
	$('.btn_and0' + i).click(function(event) {
		$('.btn_and0' + i).css('display', 'none');
		$('.btn_minus0' + i).css('display', 'block');
		$('.ul0' + i).removeClass('ul_height');

	});
	// 点击减号专业目录收起
	$('.btn_minus0' + i).click(function(event) {
		$('.btn_minus0' + i).css('display', 'none');
		$('.btn_and0' + i).css('display', 'block');
		$('.ul0' + i).addClass('ul_height');
	});

}

function delete_major(ul_id, li_id) {
	$('.major_delete0' + ul_id + li_id).click(function(event) {
		// 删除专业时获得专业ID
		get_mid(ul_id, li_id);
		// 点击删除专业时的弹框
		major_confirm(ul_id, li_id);

	});
}
// 学校专业信息接收
function collect_ajax() {

	$.ajax({
				url : 'getfollowmajor.html',
				// url: 'collect.php?msg'+Math.random(),
				type : 'post',
				data : {
					'code' : $.cookie('user')
				},
				success : function(msg) {
					var data = eval('msg=' + msg);
					if (status == 0) {
						var schlist = data.normalReturn;
						if (schlist.length > 0){
							$('.list_empty').hide();
						}else{
							$('.list_empty').css('display', 'block');
						}
						for (var j = 0; j < schlist.length; j++) {
//							var collage_id = schlist[j].followCollegeId;
							var collage_id = schlist[j].collegeId;
							listsch_insert(j, schlist[j].collegeName,
									schlist[j].followMajorCount, collage_id);

							// 展开收起控制按钮
							schlist_control(j);
							// 删除收藏学校
							delete_sch(j);
							// 循环插入专业
							for (w = 0; w < schlist[j].followMajors.length; w++) {
								var major_name = schlist[j].followMajors[w].majorName;
								var follow_count = schlist[j].followMajors.length;
//								var major_id = schlist[j].followMajors[w].followMajorId;
								var major_id = schlist[j].followMajors[w].majorId;
								listmajor_insert(j, w, major_name,
										follow_count, major_id);

							}

						}

//						console.log(data);
						
						var window_H=$(window).height();
						var nav01_H=$('#nav01').height();
						sch_H=window_H-240;
						$('#sch_list').css('height',sch_H+'px');
						console.log(window_H);
						console.log(nav01_H);
						document.getElementById('sch_list').style.cssText = 'overflow: auto;height: '+sch_H+'px;overflow-x:hidden;'
					}

				}
			});
}

collect_ajax();
// 传送用户删除学校的ID
function post_cid(cid) {
	$.ajax({
		// url: 'http://42.96.190.127:8080/Itui/getfollowmajor.html',
		url : 'disfollowcollege.html',
		type : 'post',
		dataType : 'html',
		data : {
			'cid' : cid,
			'code': $.cookie('user')
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (status == 0) {
				console.log(cid);
			}

		}
	});
}

// 传送用户删除的专业ID
function post_mid(mid, id) {
	$.ajax({
		url : 'disfollowmajor.html',
		// url:
		// 'http://42.96.190.127:8080/Itui/disfollowmajor.html?msg'+Math.random(),
		type : 'post',
		dataType : 'html',
		data : {
			'mid' : mid,
			'code': $.cookie('user')
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (status == 0) {
//				console.log(mid);
				var num = $('.total_num0'+id).text() - 1;
				$('.total_num0'+id).text(num);
			}

		}
	});
}
