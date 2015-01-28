$(function(){ 
	

	// 导航移入效果
	$('.dh li').mouseenter(function(event) {
		$(this).css('background-color', '#f6f6f6');
	}).mouseleave(function(event) {
		$(this).css('background-color', '#fff');
	});
	// 点击院系模态框出现
	$('.con_0').click(function(event) {
		$('#modal0').show();
		$(window).scrollTop(0);
		if($('.modal_k').height()+102<$(window).height()){
			$('body').css('overflow-y', 'hidden');
		}

		var yx_name=$(this).text();
		// alert(yx_name);
		$('.col_name').text(yx_name);

	});


	  $('.con_0').each(function(){
	    	var len_course=$(this).text().length;
	    	if(len_course<8){
	    		$(this).css('line-height', '60px');
	    	}
	  });

	 // 专业莫态框

	var hhh=$('.modal_k').height()+64;
			// $('.gai').height(hhh);
	var win_w=$(window).width();
	var win_h=$(window).height();
	var body_h=$('body').height();
	// alert(htm_h);
	$('#modal0').height(body_h);
	

	$(".modal_k").css({ 
	        position: "absolute", 
	        left: (win_w - $(".modal_k").width())/2, 
	        // top: (win_h - $(".modal_k").height())/2 
	    });  


	


	$('.mod_p').each(function(){
	    	var len_course=$(this).find('a').text().length;
	    	if(len_course<13){
	    		$(this).find('a').css('line-height', '40px');
	    	}	
	    	
	  });

	$('.cha').click(function(event) {
		$('#modal0').hide();
		$('body').css('overflow-y', 'visible');
	});

	//复试页面固定定位
	var left0=$(window).width();
	// alert(left0);
	$('#gudiing').css({
		position: 'fixed',
		left: (left0-1024)/2

	});

	



});