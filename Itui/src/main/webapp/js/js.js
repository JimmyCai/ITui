// JavaScript Document

$(function(){
		

// 搜索框

	$('.zhuanye  a ').click(function(event) {
		var neirong=$(this).html();
		var neirong2=$('.nr_js').html();
		$('.nr_js').html(neirong);
		$('.zhuanye  a ').html(neirong2);
		
		
	});
	// 搜索框ajax提交
	$('#sou').click(function(event) {
		var input_val=$('#scbar_txt').val();
		var op_val=$('.nr_js').html();
		if (op_val =="专业"){
			op_val =1;
		}else{
			op_val=2
		}
		$.ajax({
		            		url: '/Itui/search.html',
		            		type: 'post',
		            		dataType:'json',
		            		data: {c:input_val,t:1,cg:'',sj:'',mt:'',ct:'',a:'',l:'0'},
		            		success: function(msg) {
		            			if (msg.status ==0){
		            			
		            			location.href = "/Itui/searchhtml.html";
		            		}else{
		            			alert(msg.errMessage);
		            		}
		           		 }
		       	 });
		
	});


	var win_width=$(window).width();
	
		$('.erwei').mouseenter(function(event) {
			$('.erwei03').css('display', 'block');
		}).mouseleave(function(event) {
			$('.erwei03').css('display', 'none');
		});
		
	// $('#sou').click(function(event) {
	// 		$.ajax({
	// 	            		url: ' search.html',
	// 	            		type: 'post',
	// 	            		dataType:'json',
	// 	            		data: $("#scbar_form").serializeArray(),
	// 	            		success: function(msg) {
	// 	            			alert(123);
	// 	           		 }
	// 	       	 });
	// 	return false;
	// });


// 邮箱正则验证

$('#input_mail').blur(function(event) {
	var email01=$('#input_mail').val();
	
	var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

	  if(reg.test(email01)){
			
			$('.maildiv01').html('√邮箱合法').css('color', '#6fd415');
		}else{
			
			$('.maildiv01').html('×邮箱不合法');
			}
	if(email01==''){
		$('.maildiv01').html('邮箱不能为空');
	}


});

// 邮箱登录密码验证
$('#pasd').blur(function(event) {

	var pasd1=$('#pasd').val();
	var reg =/^[A-Za-z]\w{6,15}$/;
	if(reg.test(pasd1)){
			
			$('.pasdiv01').html('√密码合法').css('color', '#6fd415');
		}else{
			$('.pasdiv01').html('×密码不合法');
			
			}
		if(pasd1==''){
		$('.pasdiv01').html('密码不能为空');
	}
	

	
});

// 登录ajax提交

	$('#load').click(function(event) {

		var email01=$('#input_mail').val();
		var pasd1=$('#pasd').val();

		$.ajax({
		            		url: ' ',
		            		type: 'post',
		            		dataType:'json',
		            		data: {mail:email01,pasd:pasd1},
		            		success: function(msg) {
		            			if(msg.login=="true"){
		            				$('.success01').css('display', 'block');
		            				$('#denglu').hide();
		            			}else{
		            				$('.success01').css('display', 'block').html('用户名或密码错误');
		            			}
		            			
		           		 }
		       	 });
	});


// 注册正则验证

// 注册邮箱验证

$('#input_mail2').blur(function(event) {
	var email02=$('#input_mail2').val();
	var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(reg.test(email02)){
			
			$('.zc_email').html('√邮箱合法').css('color', '#6fd415');
		}else{
			
			$('.zc_email').html('×邮箱不合法');
			}
	if(email02==''){
		$('.zc_email').html('邮箱不能为空');
	}
	
});

// 注册密码验证

$('#pasd2').blur(function(event) {
	var pasd2=$('#pasd2').val();
	var reg =/^[A-Za-z]\w{6,15}$/;
	if(reg.test(pasd2)){
			
			$('.pasd4').html('√密码合法').css('color', '#6fd415');
		}else{
			$('.pasd4').html('×密码不合法').css('color', '#d14b02');
			
			}
		if(pasd2==''){
		$('.pasd4').html('密码不能为空');
	}
});


// 再次输入密码验证

$('#pasd3').blur(function(event) {
	var pasd2=$('#pasd2').val();
	var pasd3=$('#pasd3').val();
	if(pasd2==pasd3){
		$('.ecqu').html('输入正确').css('color', '#6fd415');
	}else{
		$('.ecqu').html('两次密码输入必须一致');
	}
});


// 注册表单ajax提交

$('#register').click(function(event) {
	var email02=$('#input_mail2').val();
	var pasd3=$('#pasd3').val();
	$.ajax({
		url:' ',
		type:'post',
		dataType:'json',
		data: {mail_zc:email02,pasd3:pasd3},
		success: function(msg) {
		            			if(msg=="true"){
		            				$('.success02').css('display', 'block');
		            				$('#denglu').hide();
		            			}else{
		            				$('.success02').css('display', 'block').html('您注册的用户邮箱名已存在');
		            			}
		            			
		           		 }
	});
});










	$('#register').click(function(event) {
		$.ajax({
		            		url: ' ',
		            		type: 'post',
		            		dataType:'json',
		            		data: $("#zhuce").serializeArray(),
		            		success: function(msg) {
		            			$('#zhuce').hide();
		            			$('.success01').css('display', 'block');
		           		 }
		       	 });
	});



	

})







	



	
