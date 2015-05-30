// JavaScript Document
//导航区域
	// 搜索跳转
	function search_jump(major, value) {
		if (major == "专业") {
			major = 1;
			window.open("search.html?t=" + major + "&c=" + encodeURIComponent(value) + "&#.");
			console.log(encodeURIComponent(value));
		} if(major == "院校") {
			major = 2;
			window.open("search_school.html?t=" + major + "&c=" + encodeURIComponent(value)
							+ "&#.");
		}if(major == "榜样"){
			window.open("http://dada.itui.cn/?/search/q-"+btoa(unescape(encodeURIComponent(value)))+"#users");			
		}
	}
	
	// 20150515新搜索框
	var major_sou='专业';
	$('.btn-major').click(function(event) {
		major_sou=$('.btn-major').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#E67917').siblings('.btn-new-tab').css('color', '#fff');
		$('#scbar_txt').css('border','2px solid #E67917');
		console.log(major_sou);
	});
	$('.btn-collage').click(function(event) {
	
		major_sou=$('.btn-collage').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#0CB9E3').siblings('.btn-new-tab').css('color', '#fff');
		$('#scbar_txt').css('border','2px solid #0CB9E3');
		console.log(major_sou);
	});
	$('.btn-people').click(function(event) {
		
		major_sou=$('.btn-people').text();
		$(this).removeClass('btn-border').siblings('.btn-new-tab').addClass('btn-border');
		$(this).css('color', '#73B403').siblings('.btn-new-tab').css('color', '#fff');;
		$('#scbar_txt').css('border','2px solid #73B403');
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
		console.log(major_sou);
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
	
$('.itui-username').addClass('hide');
$('.itui-news-nav-login').removeClass('hide');
$('.itui-news-nav-regs').removeClass('hide');
//12个话题
var topic_li=0,topic_href="www.baidu.com",topic_content="话题1";
var topic_list=new Array(),person_list=new Array(),news_list=new Array();

function topic(topic_li,topic_href,topic_content){
var topic_html='<li role="presentation" class="topic0'+topic_li+'">\
					<a role="menuitem" tabindex="-1" href="'+topic_href+'" target="_blank">'+topic_content+'</a>\
				</li>';
$('.dropdown-menu-news').append(topic_html);
}

//20150526新首页ajax
	function news_ajax(){
	$.ajax({
		url : 'api/newindex',
		type : 'get',
		dataType : 'html',
		success : function(msg) {
			data = eval('msg=' + msg);
//			console.log(data);
			if (data.status == '0') {
//				精选话题
				for(i=0;i<data.normalReturn.indexInfo[0].length;i++){
					topic_list[i]=data.normalReturn.indexInfo[0][i];
					}
				for(i=0;i<topic_list.length;i++){
					topic(i,topic_list[i].topicPage,topic_list[i].topic);
					}
//				达人名片
				for(i=0;i<data.normalReturn.indexInfo[1].length;i++){
					person_list[i]=data.normalReturn.indexInfo[1][i];
					}
				for(i=0;i<person_list.length;i++){
					if(person_list[i].userPhoto==undefined){
						person_list[i].userPhoto='';
					}
					$('.li_img0'+i).attr('src',person_list[i].userPhoto);
					
					if(person_list[i].signature==undefined){
						person_list[i].signature='';
					}
					$('.personal_des0'+i).text(person_list[i].signature);
					
					if(person_list[i].userName==undefined){
						person_list[i].userName='';
					}
					$('.card_name0'+i).text(person_list[i].userName);
					
					if(person_list[i].homePag==undefined){
						person_list[i].homePag='';
					}
					$('.card_follow0'+i).children('a').attr('href', person_list[i].homePage);
					
					if(person_list[i].homePage==undefined){
						person_list[i].homePage='';
					}
					$('.card-ul li').eq(i).children('.card_head').find('a').attr('href', person_list[i].homePage);
					
					if(person_list[i].userSchool==undefined){
						person_list[i].userSchool='';
					}
					$('.card_sch0'+i).children('.sch_span').text(person_list[i].userSchool);
					
					if(person_list[i].degree==undefined){
						person_list[i].degree='';
					}
					$('.card_sch0'+i).children('.deg_span').text(person_list[i].degree);
					
					
					console.log(person_list[i].degree);
					
				}
				for(i=0;i<data.normalReturn.indexInfo[2].length;i++){
					news_list[i]=data.normalReturn.indexInfo[2][i];
				}
				for(i=0;i<news_list.length;i++){
					if(news_list[i].newsPhoto==undefined){
						news_list[i].newsPhoto='';
					}
					$('.news_img0'+i).attr('src', news_list[i].newsPhoto);
					
					if(news_list[i].summary==undefined){
						news_list[i].summary='';
					}
					$('.news_intr0'+i).text(news_list[i].summary);
					
					if(news_list[i].title==undefined){
						news_list[i].title='';
					}
					$('.news_item0'+i).text(news_list[i].title);
					
					if(news_list[i].newsPage==undefined){
						news_list[i].newsPage='';
					}
					$('.news_a0'+i).attr('href', news_list[i].newsPage);
					
					
				}
				
			} else {
				
				console.log('no');
			}
		}
	});
}
	news_ajax();
	
//导航区域
function login_style(){
	$('.itui-username').removeClass('hide');
	$('.itui-news-nav-login').addClass('hide');
	$('.itui-news-nav-regs').addClass('hide');
	$('.person_img').removeClass('hide');
	$('.itui-news-login-regs').mouseenter(function(event) {
		$('.dropdown-list').removeClass('hide');
	});
	$('.dropdown-list').mouseleave(function(event) {
		$('.dropdown-list').addClass('hide');
	});
}
function nonelogin_style(){
	$('.itui-username').addClass('hide');
	$('.itui-news-nav-login').removeClass('hide');
	$('.itui-news-nav-regs').removeClass('hide');
	$('.person_img').addClass('hide');

}
function news_user(){
$.ajax({
	url:'api/getuserinfo',
	type:'get',
	datatype:'html',
	success:function(msg){
		data = eval('msg=' + msg);
		if(data.status==0){
			console.log(data);
			if(data.normalReturn.userInfo.user=="null"){
				nonelogin_style();
			}else{
				login_style();
				$('.person_page img').attr('src', data.normalReturn.userInfo.userPhoto);
				$('.person_page').attr('href', data.normalReturn.userInfo.userPage);
				$('.user_name').text(data.normalReturn.userInfo.userName);
			}
		}else{
			console.log("error");
		}
	}
	
});
}
$(function(){
news_user();
});

//二维码
var erwei_html='\
	<div class="erwei">\
	  <div class="qr_code01">\
	      <div class="ew_0 ew_01">\
	       <img src="images/11.jpg" alt="">\
	      </div>\
	      <div class="ew_0 ew_02">\
			<a href="http://shang.qq.com/wpa/qunwpa?idkey=87bff36de422fbdfb6a0d07119601f12cf5ccb8e51ab2c06efa64b6a1e2d98a3" target="_blank">\
			<img src="images/qq01.png" alt="">\
			</a>\
	      </div>\
		  <div class="ew_0 back_top">\
	        <p>▲</p>\
          </div>\
	  </div>\
	  <div class="qr_code01">\
		<div class="erwei_tp01">\
	       <img src="images/shiliu.png" alt="">\
	    </div>\
		<div class="erwei_tp02">\
			<img src="images/trip.png" alt="">\
        </div>\
	  </div>\
	</div>';

$('body').append(erwei_html);
$('.erwei').css({position:'fixed',
				right:'30px',
				bottom:'80px'});
$('.erwei_tp01').css({position:'absolute',
					right:'40px',
					top:'0px',
					display:'none'});
$('.erwei_tp02').css({position:'absolute',
	right:'40px',
	top:'0px'});

$('.ew_01').mouseenter(function(event) {
	$('.erwei_tp01').css('display', 'block');
	$('.erwei_tp02').css('display', 'none');
}).mouseleave(function(event) {
	$('.erwei_tp01').css('display', 'none');
	//$('.erwei_tp02').css('display', 'block');
});
$('.ew_02').mouseenter(function(event) {
	$('.erwei_tp01').css('display', 'none');
	$('.erwei_tp02').css('display', 'block');
});
$('.erwei_tp02').mouseleave(function(event) {
	$('.erwei_tp02').css('display', 'none');
	
});
setTimeout(function () {
	$('.erwei_tp02').hide();
}, 5000);
$('.erwei_tp02').click(function(event){
	$('.erwei_tp02').hide();
});
//回顶部事件
$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
$('.back_top').click(function(event)
 {
	$body.animate({scrollTop: $('#news_back_top').offset().top}, 500);
					return false;
});