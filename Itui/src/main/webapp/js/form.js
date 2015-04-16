$(function(){
$('.reset').click(function(event) {
	$('.zhiboke')[0].reset();
	$('#tech_pic')[0].reset();
});
$('.submit').click(function(event) {
var flag=false;
//获得用户输入的内容
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
	var summary=tag1+'/'+tag2+'/'+tag3+'/'+tag4;

	var courseInfo={teacherName:techname,price:price,startDay:startDay,endDay:endDay,startTime:startTime,endTime:endTime,lesson:lesson,orgName:orgName,orgWeb:orgWeb,platform:platform,platformWeb:platformWeb,liveSrc:liveSrc,summary:summary};

	console.log(courseInfo.summary);

function formdata(){
	$.ajax({
		url: 'api/course/release',
		type: 'post',
		dataType: 'JSON',
		data: {courseInfo: courseInfo},
		success:function(msg){
			var data = eval('msg='+msg);
			if(data.status == 0){
				$('.trip').text('提交成功');
				$('.trip').css({
					display: 'block',
					color: 'green'
				});
				//提交成功后重置表单
				$('.zhiboke')[0].reset();
				$('#tech_pic')[0].reset();
			}else{
				
			}
		},
		complete:function(){
			alert("继续添加表单！");
		}
	});
	
	
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
			$('.trip').css({
				display: 'block',
				color: 'red'
			});
		}else{
			
			formdata();
		}
});


});

