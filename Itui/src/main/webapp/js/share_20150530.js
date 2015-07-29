//页面加载函数
$(function() {
	

	$('.d_down').click(function(event) {
		$('.dropdown-menu').css('display', 'block');
	});

	$('.zhuanye  a ').click(function(event) {
		$('.dropdown-menu').css('display', 'none');
		var neirong = $(this).html();
		var neirong2 = $('.nr_js').html();
		$('.nr_js').html(neirong);
		$('.zhuanye  a ').html(neirong2);
	});

	$('.back_top').click(function(event) {
		$body.animate({
			scrollTop : $('#nav0').offset().top
		}, 500);
		return false;
	});

	

	$('#sou').click(function(event) {
		var major = $('.nr_js').text();
		var value = $('#scbar_txt').val();
		search_jump(major, value);
	});

	$('#scbar_txt').focus(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				var major = $('.nr_js').text();
				var value = $('#scbar_txt').val();
				search_jump(major, value);
			}
		}
	});

	$('#scbar_txt').blur(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				//console.log('blur');
			}
		}
	});

	
	$('.drop_menu').mouseleave(function(event) {
		$('.drop_menu').css('display', 'none');
	});

	




});
//信息业跳转到dada社区
//$('.dh li').eq(1).hide();
$('.dh li').eq(1).css('cursor','pointer');
$('.dh li').eq(2).css('cursor','pointer');
$('.dh li').eq(2).click(function(event){
	window.open("http://dada.itui.cn/", "_blank");
});
$('.input01').css('border-top-left-radius','6px');
$('.input01 .dropdown').css('border-top-left-radius','6px');
$('.input01 .dropdown #dropdownMenu1').css('border-top-left-radius','6px');

$('.input01').css('border-bottom-left-radius','6px');
$('.input01 .dropdown').css('border-bottom-left-radius','6px');
$('.input01 .dropdown #dropdownMenu1').css('border-bottom-left-radius','6px');

$('#sou').css('border-top-right-radius','6px');
$('#sou').css('border-bottom-right-radius','6px');
//页面加载函数结束
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



function search_jump(major, value) {
	if (major == "专业") {
		major = 1;
		window.open("search.html?t=" + major + "&c=" + encodeURIComponent(value) + "&#.");
	} else {
		major = 2;
		window.open("search_school.html?t=" + major + "&c=" + encodeURIComponent(value) + "&#.");
	}
}



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
	$body.animate({scrollTop: $('#nav0').offset().top}, 500);
					return false;
});

