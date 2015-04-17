$(function(){
//	function getFileName(){ 
//		var fileName=""; 
//		 
//		if(typeof(fileName) != "undefined") 
//		{ 
//		    fileName = $('#file').val(); 
////		    fileName=fileName.substring(0, fileName.lastIndexOf(".")); 
//		} 
//		return fileName; 
//		} 
var photoName=0;
	//点击提交图片表单
	$('#do').click(function(event){
		  event.preventDefault(); 
		  console.log('do');
          var fd = new FormData();
          fd.append("teacherPhoto", $(":file")[0].files[0]);
          $('.successtrip').text('头像上传成功！');
          photoName+=1;
          
           $.ajax({
              type:"post",
              url:"api/course/release/uploadphoto",
              data: fd,
              cache: false,
              processData: false,
              contentType: false
          }).done(function(res){
              console.log(res);
              var data = eval('res=' + res);
              //$('.successtrip').text(data.normalReturn.uploadInfo.upload).css('display','block');
          });
         
          return false;
		
	});
$('.reset').click(function(event) {
	$('.zhiboke')[0].reset();
	$('#tech_pic')[0].reset();
});
//点击提交文字表单
$('.submit').click(function(event) {
	console.log(photoName);

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
	var courseInfo={teacherName:techname,price:price,startDay:startDay,endDay:endDay,startTime:startTime,endTime:endTime,lesson:lesson,orgName:orgName,orgWeb:orgWeb,platform:platform,platformWeb:platformWeb,liveSrc:liveSrc,summary:summary,tag:tag};


	

function formdata(){
	$.ajax({
		url: 'api/course/release',
		type: 'post',
		contentType:"application/json;charset=utf-8",
		dataType: 'json',
		data: JSON.stringify(courseInfo),
		success:function(msg){
			console.log(courseInfo);
			var data = eval('msg=' + msg);
			console.log(data);
			if(data.status == 0){
				console.log(123);
				$('.trip').text('提交成功');
				$('.trip').css({
					display: 'block',
					color: 'green'
				});
				//提交成功后重置表单
				$('.zhiboke')[0].reset();
				$('#tech_pic')[0].reset();
				console.log(courseInfo);
				
			}else{
				
			}
		},
		complete:function(){
//			alert("继续添加表单！");
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

