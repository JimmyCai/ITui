//判断是否有权限发布课程信息
function ituidata(){
	$.ajax({
		url : 'api/course/verify',
		type : 'get',
		dataType : 'html',
		data : {"code" : $.cookie('user')},
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data == '0') {
				$('.content').css("display","block");
				$('.no_code').css("display","none");
				
			} else {
				$('.content').css("display","none");
				$('.no_code').css("display","block");
				$('.code_p').text('您没有权限查看此页面,三秒后跳转回首页');
				setTimeout(function(){window.location="index.html";},3000);
				
			}
		}
	});
	}
function is_load(){
// 判断用户是否登录
	if ($.cookie('user') != undefined) {
		ituidata();
    }else{
		$('.content').css("display","none");
		$('.no_code').css("display","block");
		setTimeout(function(){window.location="index.html";},3000);
    }
}
is_load();


$(function(){

var photoName=0;
	// 点击提交图片表单
	$('#do').click(function(event){
		  event.preventDefault(); 
		  $('.successtrip').text('正在提交……');
		  
          var teacherPhoto = new FormData();
          teacherPhoto.append("teacherPhoto", $(":file")[0].files[0]);
           $.ajax({
              type:"post",
              url:"api/course/release/uploadphoto",
              data: teacherPhoto,
              cache: false,
              processData: false,
              contentType: false
          }).done(function(res){
              var data = eval('res=' + res);
              if(data.status==0){
              $('.successtrip').text(data.normalReturn.uploadInfo.upload);
              photoName=data.normalReturn.uploadInfo.photoName;}
              else{
            	  $('.successtrip').text('提交失败，请校验格式是否为jpg、png、jpeg并保证图片大小在800k以内！');
              }
            
          }).fail(function(){
        	  $('.successtrip').text('提交失败，请校验格式是否为jpg、png、jpeg并保证图片大小在800k以内！');
          });
         
          return false;
		
	});
$('.reset').click(function(event) {
	$('.zhiboke')[0].reset();
	$('#tech_pic')[0].reset();
});
// 点击提交文字表单
$('.submit').click(function(event) {
	if(photoName==0){
		photoName="default.png";
	}

var flag=false;
// 获得用户输入的内容

	var techname=$('#teacherName').val();
	var price=$('#price').val();
	var startday=$('#startDay').val();
	var starthour=$('#startHour').val();
	var starminute=$('#startminute').val();
	var endday=$('#endDay').val();
	var endhour=$('#endHour').val();
	var endminute=$('#endminute').val();
	var lesson=$('#lesson').val();
	var orgName=$('#orgName').val();
	var orgWeb=$('#orgWeb').val();
	var platform=$('#platform').val();
	var platformWeb=$('#platformWeb').val();
	var liveSrc=$('#liveSrc').val();
	var summary=$('#summary').val();
	var tag1=$('#tag1').val();
	var tag2=$('#tag2').val();
	var tag3=$('#tag3').val();
	var tag4=$('#tag4').val();

	var startday_arr=startday.split('-');
	var endday_arr=endday.split('-');
	var startDay=startday_arr[0]+startday_arr[1]+startday_arr[2];
	var endDay=endday_arr[0]+endday_arr[1]+endday_arr[2];
	var startTime=starthour+starminute;
	var endTime=endhour+endminute;
	var tag=tag1+'/'+tag2+'/'+tag3+'/'+tag4;
	var courseInfo={teacherName:techname,price:price,startDay:startDay,endDay:endDay,startTime:startTime,endTime:endTime,lesson:lesson,orgName:orgName,orgWeb:orgWeb,platform:platform,platformWeb:platformWeb,liveSrc:liveSrc,summary:summary,tag:tag,photo:photoName};

	
	function formdata_ajax(){
		$.ajax({
			url: 'api/course/release',
			type: 'post',
			contentType:"application/json;charset=utf-8",
			dataType: 'html',
			data: JSON.stringify(courseInfo),
			complete:function()
			{
				
				
				$('.trip').text('');
			},
			success:function(msg){
				var data = eval('msg=' + msg);
				if(data.status == 0){
					$('.trip').text('提交成功');
					$('.trip').css({
						display: 'block',
						color: 'green'
					});
				// 提交成功后重置表单
					$('.zhiboke')[0].reset();
					$('#tech_pic')[0].reset();
					$('.successtrip').text('');
					$('.trip').css({display: 'block',color: 'green'});
					$('#startDay').css('border','1px solid #cbcbcb');
					$('#endDay').css('border','1px solid #cbcbcb');
					$('#startHour').css('border','1px solid #cbcbcb');
					$('#startminute').css('border','1px solid #cbcbcb');
					$('#endHour').css('border','1px solid #cbcbcb');
					$('#endminute').css('border','1px solid #cbcbcb');
					alert(data.normalReturn.releaseInfo.release);		
				}else{
					alert('表单格式填写有误请检查！');
				}
			}
		
		});
	}

	function formdata(){
		if(parseInt(endDay)<parseInt(startDay)){
			$('.trip').text('日期错误请从新填写日期');
			$('.trip').css({
				display: 'block',
				color: 'red'
			});
			$('#startDay').css('border','1px solid red');
			$('#endDay').css('border','1px solid red');
		}else{
			if((parseInt(endDay)==parseInt(startDay))&&(parseInt(endTime)<parseInt(startTime)))
			{
				$('.trip').text('时间错误请从新填写时间');
				$('.trip').css({display: 'block',color: 'red'});
				$('#startHour').css('border','1px solid red');
				$('#startminute').css('border','1px solid red');
				$('#endHour').css('border','1px solid red');
				$('#endminute').css('border','1px solid red');
				
			}else{
				formdata_ajax();
			}		
		}
	}
	
	function pass_01(){
		$('.zhiboke').find('.element').each(function() {
			if($(this).val() == "")
			{
				$(this).css('border', '1px solid red');
				flag=true;return;
			}else{
				$(this).css('border', '1px solid #cbcbcb');
			}
		
		});		
	}
	pass_01();
	if(flag==true){
	 	$('.trip').text('红色线框部分不能为空');
		$('.trip').css({display: 'block',color: 'red'});
	}else{
		formdata();
	}

});


});

