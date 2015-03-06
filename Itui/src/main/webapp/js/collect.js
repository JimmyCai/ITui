// 页面加载函数开始

$(function(){
	//点击页面尾跳转函数
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

		console.log($.cookie("about_index"));
	});

	
});

//页面加载函数结束
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

//点击获得当前学校的ID
function click_cid(j){
$('.schname0'+j).click(function(event) {
	var c_id=$('.item0'+j).attr('new_cid');
	console.log(c_id);
	window.open("http://localhost:8080/Itui/school.html?name="+c_id,"_blank");
	
});
}
//点击获得当前专业的ID
function click_mid(j,w){
	
	$('.ul0'+j).find('.lilist_0'+w).find('p').click(function(event) {
		var m_id=$('.ul0'+j).find('.lilist_0'+w).attr('new_mid');
		window.open("http://localhost:8080/Itui/info.html?major="+m_id,"_blank");	
		console.log(m_id);
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
							click_cid(j);

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
								click_mid(j,w);	

							}

						}
					
					
						

//						console.log(data);
						
						var window_H=$(window).height();
						var nav01_H=$('#nav01').height();
						sch_H=window_H-240;
						$('#sch_list').css('height',sch_H+'px');
						console.log(window_H);
						console.log(nav01_H);
						document.getElementById('sch_list').style.cssText = 'overflow: auto;height: '+390+'px;overflow-x:hidden;'
					}else
					{
//						404错误页面
						var err_msg=data.errMessage;
						$.cookie("err_msg",err_msg, {path : "/"});
						location.href="error.html";
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
			}else
			{
//				404错误页面
				var err_msg=data.errMessage;
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";
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
			}else
			{
//				404错误页面
				var err_msg=data.errMessage;
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";
			}

		}
	});
}
$('.schname').css("background-color","red");

