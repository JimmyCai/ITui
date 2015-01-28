// JavaScript Document

$(function(){

	// 专业学校转换
		
	$('.d_down').click(function(event) {
		$('.dropdown-menu').css('display', 'block');

	});

	$('.zhuanye  a ').click(function(event) {
		$('.dropdown-menu').css('display', 'none');
		var neirong=$(this).html();
		var neirong2=$('.nr_js').html();
		$('.nr_js').html(neirong);
		$('.zhuanye  a ').html(neirong2);
		
		
	});


	// 折线图
	

	// 图表结束


	//柱状图
	// var height0=$('.baowei').height();
	// alert(height0);
	// $('.baowei0').css('height', height0+'px');

	// var top1=$('.baowei').offset().top;
	// var left1=$('.baowei').offset().left;
	// $('.course1').css({
	// 	left: left1+'px',
	// 	top: top1+'px'
	// });

	// $('.value1').offset().top=top1;
	// $('.value1').offset().left=left1;

	// 获得字符串的长度
	// var www=$('.yuanxi1').text().length;
	// alert(www);
	
	// 柱状图横坐标全称显示
	$('.course_full').hide();
	$('.course').mouseenter(function(event) {
		var len_course=$(this).find('a').text().length;
		var text_course=$(this).find('a').text();
		if(len_course>5){
			$(this).siblings('.course_full').show().text(text_course);
			
		}else{
			$('.course_full').hide();
		}
	});
	$('.course_full').mouseleave(function(event) {
		$(this).hide();
	});


	// $('.yuanxi').mouseover(function(event) {
	// 	var www=$(this).text().length;
	// 	if(www>16){
	// 		alert(www);
	// 	}else{
	// 		return false;
	// 	}
	// });

	// 所属专业和院校部分全称展示
	$('.name_full').hide();
	$('.sch_full').hide();
	// 专业全称
	$('.zy_name').mouseenter(function(event) {
		var leng_name=$(this).text().length;
		var text_name=$(this).html();
		if(leng_name>12){
			$(this).siblings('.name_full').show().html(text_name);
			
		}else{
			$('.name_full').hide();
		}
		
	});
	
	$('.name_full').mouseleave(function(event) {
		$(this).hide();
	});

	// 院校全称
	$('.yuanxi').mouseenter(function(event) {
		var len_sch=$(this).text().length;
		var text_sch=$(this).html();
		if(len_sch>15){
			$(this).siblings('.sch_full').show().html(text_sch);
		}else{
			$('.sch_full').hide();
		}
	});

	$('.sch_full').mouseleave(function(event) {
		$(this).hide();
	});

	// 二维码
	$('.ew_ma').hide();
	$('.ew_01').mouseenter(function(event) {
		$('.ew_ma').show();
	});
	$('.ew_01').mouseleave(function(event) {
		$('.ew_ma').hide();
	});

	//回顶部事件
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');

	$('.back_top').click(function(event) {
				

					$body.animate({scrollTop: $('#nav0').offset().top}, 500);
    					return false;
	
			});

	// 侧面锚点导航
	$('.an_1').mouseenter(function(event) {
		
		$('.ann').eq(0).find('.an_mao1').css('color', '#00a3eb');
		$('.ann').eq(0).find('.yuan').css('background-color', '#00a3eb');
	}).mouseleave(function(event) {
		
		$('.ann').eq(0).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(0).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_2').mouseenter(function(event) {
		$('.ann').eq(1).find('.an_mao1').css('color', '#b5d156');
		$('.ann').eq(1).find('.yuan').css('background-color', '#b5d156');
	}).mouseleave(function(event) {
		$('.ann').eq(1).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(1).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_3').mouseenter(function(event) {
		$('.ann').eq(2).find('.an_mao1').css('color', '#69bde1');
		$('.ann').eq(2).find('.yuan').css('background-color', '#69bde1');
	}).mouseleave(function(event) {
		$('.ann').eq(2).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(2).find('.yuan').css('background-color', '#a8a5a6');
	});


	$('.an_4').mouseenter(function(event) {
		$('.ann').eq(3).find('.an_mao1').css('color', '#feaf6d');
		$('.ann').eq(3).find('.yuan').css('background-color', '#feaf6d');
	}).mouseleave(function(event) {
		$('.ann').eq(3).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(3).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_5').mouseenter(function(event) {
		
		$('.ann').eq(4).find('.an_mao1').css('color', '#98d877');
		$('.ann').eq(4).find('.yuan').css('background-color', '#98d877');
	}).mouseleave(function(event) {
		
		$('.ann').eq(4).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(4).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_6').mouseenter(function(event) {
		
		$('.ann').eq(5).find('.an_mao1').css('color', '#e372cc');
		$('.ann').eq(5).find('.yuan').css('background-color', '#e372cc');
	}).mouseleave(function(event) {
		
		$('.ann').eq(5).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(5).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_1').click(function(event) {
		$body.animate({scrollTop: $('#nav02').offset().top}, 500);
    		return false;
	});

	$('.an_2').click(function(event) {
		$body.animate({scrollTop: $('#nav03').offset().top}, 500);
    		return false;
	});

	$('.an_3').click(function(event) {
		$body.animate({scrollTop: $('#nav04').offset().top}, 500);
    		return false;
	});

	$('.an_4').click(function(event) {
		$body.animate({scrollTop: $('#nav05').offset().top}, 500);
    		return false;
	});

	$('.an_5').click(function(event) {
		$body.animate({scrollTop: $('#nav07').offset().top}, 500);
    		return false;
	});

	$('.an_6').click(function(event) {
		$body.animate({scrollTop: $('#nav08').offset().top}, 500);
    		return false;
	});


	// 院校排名情况
	$('.rank').mouseover(function(event) {
		// $(this).addClass('rank_ac').siblings('.rank').removeClass('rank_ac');
		// alert($(this).index());
	});

	















})











	



	
