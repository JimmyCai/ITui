$(function(){
//	$('.item').mouseenter(function(event){
//		console.log($(this).index());
//	});
	
});




// 页面加载函数结束

//解析url参数,获得id
var code_str=window.location.search;
var code_value=code_str.split("=");
var mid=code_value[1];
console.log(mid);
// 排名页变量定义
var data_array=new Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48);
var title_name='土木工程挖掘机汽修电焊';
var rank_num='345';
var major_name='中央美术学院中央美术学院学院';
var item_title='报考难度：';
var item_value='9.65';
var is_34='34';
var is_211='211';
var is_985='985';
var img_num='defaultlogo.png';
var w=0;
function page_style(back_color){
$('.header').css('background-color', back_color);
$('.nav_rank p').css('background-color', back_color);
$('.rank_area').css('background-color', back_color);
$('.sch_logo').css('border','2px solid '+back_color);
$('.item_l').css('border-right','2px solid '+back_color);
$('.item_r').css('border-left','2px solid '+back_color);
if($(window).width()<384)
{
$('.item_l').css('border-right','none');
$('.item_r').css('border-right','none');
$('.item_l').css('border-left','2px solid '+back_color);
}
$(window).resize(function(event){
if($(window).width()<384)
{
$('.item_l').css('border-right','none');
$('.item_r').css('border-right','none');
$('.item_l').css('border-left','2px solid '+back_color);
}
});

}
var class_num=1;
var major_n='手摇挖掘机专业';
var major_html='<span class="span_name name_0'+class_num+'">'+major_n+'/</span>';
function left_insert(rank_num)
{
var lefthtml_li='\
	              <li class="item item_l item_0'+rank_num+'">\
             <div class="sch_logo">\
                 <div class="logo_tp"><img src="images/'+img_num+'" alt=""></div>\
             </div>\
             <div class="rank_area">\
                <div class="rank_0">'+rank_num+'</div>\
                <div class="rank_wenzi">\
                      <p class="collname">'+major_name+'</p>\
                      <p class="level">\
                      		<span>院校层次：</span>\
                      		<span class="is34">'+is_34+'所/</span>\
                      		<span class="is211">'+is_211+'/</span>\
                      		<span class="is985">'+is_985+'</span>\
                      </p>\
                      <p class="diffcult">\
                           <span>'+item_title+'</span>\
                      </p>\
                </div> \
             </div> \
             <div class="clear"></div>\
     </li>';
     $('.rank_body').append(lefthtml_li);	
   //$('.item_l').find('.diffcult').append(major_html);
}

function right_insert(rank_num){
var righthtml_li='\
	               <li class="item item_r">\
             <div class="sch_logo">\
                 <div class="logo_tp"><img src="images/'+img_num+'" alt=""></div>\
             </div>\
             <div class="rank_area">\
                <div class="rank_0">'+rank_num+'</div>\
                <div class="rank_wenzi">\
                      <p class="collname">'+major_name+'</p>\
                      <p class="diffcult">\
                           <span>'+item_title+'</span>\
                           <span>'+item_value+'</span>\
                      </p>\
                      <p class="level">\
                      	  <span>院校层次：</span>\
                          <span class="is34">'+is_34+'所/</span>\
                          <span class="is211">'+is_211+'/</span>\
                          <span class="is985">'+is_985+'</span>\
                      </p>\
                </div> \
             </div> \
             <div class="clear"></div>\
     </li>';
     $('.rank_body').append(righthtml_li);		
}
//循环插入li函数	   
function loop_insert()
{
for(i=0;i<10;i++)
{
if(data_array[i+w]%2!==0)
{
     if((i+w)<=data_array.length-1){
     left_insert(data_array[i+w]);	
     //每个li下面插入专业
     for(j=1;j<=3;j++)
     {
    	 $('.item_0'+(i+w+1)).find('.diffcult').append(major_html); 
     }
     
     }	
}else{
          if((i+w)<=data_array.length-1){
           right_insert(data_array[i+w]);	
           }
	
       }
}
w+=10;

}


//如果即将到达底部再插入10个
function bottom_insert(){
$(window).scroll(function() {
		var w_height = $(window).height();//获得可视区域高度
		var s_height = $(document).scrollTop();//获得已经滚动上去的高度
		var main_height = $(document).height();//区域总高度
		var bottom_h = main_height- s_height - w_height;
		if(bottom_h<100 ){
		loop_insert();
		page_url();
		}
	});	
}
bottom_insert();



//判断当前页面
var str_major=window.location.pathname;
var str_texting=new RegExp('major');
var str_texting2=new RegExp('college');
function page_url()
{
//专业排名
if(str_texting.test(str_major))
{
	// 先插入10个li
	loop_insert();
var back_color='#FEBC80';
page_style(back_color);

}
//院校全国排名
else if(str_texting2.test(str_major)){
	// 先插入10个li
	loop_insert();
var back_color='#5DB9E3';
page_style(back_color);
}
//院校省内
else{
var back_color='#B2D155';
page_style(back_color);
}	
}
page_url();

$.ajax({
	url: 'api/rank/major',
	type: 'post',
	dataType: 'html',
	data: {'mid': mid},
	success:function(msg)
	{
	var data = eval('msg=' + msg);
	if (data.status == 0)
	   {
		
	   }else
	   {
		 //404错误页面
		var err_msg=data.errMessage;
		if(err_msg!=='请先登录！')
		{
		$.cookie("err_msg",err_msg, {path : "/"});
			location.href="error.html";
		}else{console.log(err_msg);}   
	   }
	}
});

//字体样式
$('.collname,.span_name').mouseenter(function(event){
	$(this).css('text-shadow','#fff 2px 2px 2px');
}).mouseleave(function(event){
	$(this).css('text-shadow','none');
});