$(function(){

	
});


// 页面加载函数结束
//当前页面样式
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
//解析url参数,获得id
var code_str=window.location.search;
var code_value=code_str.split("=");
var mid=code_value[1];
var cid_str=code_value[1].split("&");
var cid=cid_str[0];
var cid_name=decodeURI(code_value[2]);
console.log(cid_name);
console.log(cid);
// 排名页变量定义
var data_array=new Array();
var title_name='土木工程挖掘机汽修电焊';
var rank_num='345';
var coll_name='中央美术学院中央美术学院学院';
var item_title='城市：';
area_city='北京';
var item_value='9.65';
var is_34='';
var is_211='';
var is_985='';
var img_num='defaultlogo.png';
var college_id='';
var w=0;
var class_num=1;
var major_n=new Array('手摇挖掘机专业1','手摇挖掘机专业2');
var level_type=new Array();

function major_nInsert(j){
	console.log('123qwe');
	var major_html='<span class="span_name name_0'+class_num+'">'+major_n[0].majorName+'/</span>';
}

//专业排名ajax
function major_ajax(){
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
			title_name=data.normalReturn.subjectName;
			//科目名称
			$('.itemname').text(title_name);
			//把接收到的数据存进自定义数组
			for(var i=0;i<data.normalReturn.rankList.length;i++)
			{
			data_array[i]=data.normalReturn.rankList[i];
			}
			
			loop_insert();
			bottom_insert();
			console.log(data.normalReturn.rankList);
			var back_color='#FEBC80';
			page_style(back_color);
				
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
}

//全国排名ajax
function college_ajax(){
	$.ajax({
		url: 'api/rank/college',
		type: 'get',
		dataType: 'html',
		data: {'cid': cid},
		success:function(msg)
		{
		var data = eval('msg=' + msg);
		if (data.status == 0)
		   {
			title_name=cid_name;
			//科目名称
			$('.itemname').text(title_name);
			//把接收到的数据存进自定义数组
			for(var i=0;i<data.normalReturn.collegeRankList.length;i++)
			{
			data_array[i]=data.normalReturn.collegeRankList[i];
			}
			loop_insert();
			bottom_insert();
			//console.log(data.normalReturn.rankList);
			var back_color='#5DB9E3';
			page_style(back_color);
			$('.is34').css('border','1px solid #E3835D');
				
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
}

//省内排名ajax
function collegelocal_ajax(){
	$.ajax({
		url: 'api/rank/localrank/college',
		type: 'post',
		dataType: 'html',
		data: {'cid': cid},
		success:function(msg)
		{
		var data = eval('msg=' + msg);
		if (data.status == 0)
		   {
			title_name=cid_name;
			//科目名称
			$('.itemname').text(title_name);
			//把接收到的数据存进自定义数组
			for(var i=0;i<data.normalReturn.collegeLocalRankList.length;i++)
			{
			data_array[i]=data.normalReturn.collegeLocalRankList[i];
			}
			loop_insert();
			bottom_insert();
			//console.log(data.normalReturn.rankList);
			var back_color='#B2D155';
			page_style(back_color);
			$('.is985').css('border','1px solid #E3835D');
				
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
}

function left_insert(li_num)
{  
	
var lefthtml_li='\
	    <li class="item item_l item_0'+li_num+'">\
             <div class="sch_logo" nameid="'+college_id+'">\
                 <div class="logo_tp"><img src="http://www.itui.cn/itui/images/'+img_num+'" alt=""></div>\
             </div>\
             <div class="rank_area">\
                <div class="rank_0">'+rank_num+'</div>\
                <div class="rank_wenzi">\
                      <p class="collname" nameid="'+college_id+'">'+coll_name+'</p>\
                      <p class="level">\
                      <span class="is34">34所</span>\
              		  <span class="is211">211</span>\
              		  <span class="is985">985</span>\
              		  <span class="isnormal">普通</span>\
                      </p>\
                      <p class="diffcult">\
                           <span>'+item_title+'</span>\
                           <span class="area">'+area_city+'</span>\
                      </p>\
                </div> \
             </div> \
             <div class="clear"></div>\
     </li>';
     $('.rank_body').append(lefthtml_li);
     //点击学校跳转
     $('.item_0'+li_num).find('.collname').click(function(event){
		 var college_id=$('.item_0'+li_num).find('.collname').attr('nameid');
		 window.open("school.html?name=" + college_id, "_blank");
		 console.log(college_id);
		   
	   });
     $('.item_0'+li_num).find('.sch_logo').click(function(event){
		 var college_id=$('.item_0'+li_num).find('.sch_logo').attr('nameid');
		 window.open("school.html?name=" + college_id, "_blank");
		 console.log(college_id);
		   
	   });
   
  
}

function right_insert(li_num){

var righthtml_li='\
	   <li class="item item_r item_0'+li_num+'">\
	   <div class="sch_logo" nameid="'+college_id+'">\
       <div class="logo_tp"><img src="http://www.itui.cn/itui/images/'+img_num+'" alt=""></div>\
   </div>\
   <div class="rank_area">\
      <div class="rank_0">'+rank_num+'</div>\
      <div class="rank_wenzi">\
            <p class="collname" nameid="'+college_id+'">'+coll_name+'</p>\
            <p class="level">\
            		<span class="is34">34所</span>\
            		<span class="is211">211</span>\
            		<span class="is985">985</span>\
            		<span class="isnormal">普通</span>\
            </p>\
            <p class="diffcult">\
                 <span>'+item_title+'</span>\
                 <span class="area">'+area_city+'</span>\
            </p>\
      </div> \
   </div> \
   <div class="clear"></div>\
     </li>';
     $('.rank_body').append(righthtml_li);	
     //点击学校跳转
     $('.item_0'+li_num).find('.collname').click(function(event){
		 var college_id=$('.item_0'+li_num).find('.collname').attr('nameid');
		 window.open("school.html?name=" + college_id, "_blank");
		 console.log(college_id);
		   
	   });
     $('.item_0'+li_num).find('.sch_logo').click(function(event){
		 var college_id=$('.item_0'+li_num).find('.sch_logo').attr('nameid');
		 window.open("school.html?name=" + college_id, "_blank");
		 console.log(college_id);
		   
	   });

    
}
//循环插入li函数	   
function loop_insert()
{
for(i=0;i<10;i++)
{
if((i+w+1)%2!==0)
{
     if((i+w+1)<=data_array.length){
    	 //每个li的学校名
     coll_name=data_array[i+w].college;
     //每个li的校徽
    img_num=data_array[i+w].logo;
    //每个学校的id
    college_id=data_array[i+w].collegeId;
    //所属城市
    area_city=data_array[i+w].city;
    rank_num=data_array[i+w].rank;
    console.log(rank_num);
//    每个li的院校层次
    level_type=data_array[i+w].typeInfo;

    var is_211=level_type.indexOf("211");
    var is_34=level_type.indexOf("34");
    var is_985=level_type.indexOf("985");
    var is_normal=level_type.indexOf("普通");
    console.log(is_normal);
   
//     major_n=data_array[i+w].majorList;
//    插入左边li
     left_insert((i+w+1));
     if(is_34< 0){
      	 $('.item_0'+(i+w+1)).find('.is34').remove();
    	}if(is_211< 0){
    		$('.item_0'+(i+w+1)).find('.is211').remove();
    	}if(is_985< 0){
    		$('.item_0'+(i+w+1)).find('.is985').remove();
    	}if(is_normal<0){
    		$('.item_0'+(i+w+1)).find('.isnormal').remove();
    	}
    if( area_city== undefined){
    	$('.diffcult').remove();
    }
     
    var is_34=level_type.indexOf('34');
    var is_211=level_type.indexOf('211');
    var is_985=level_type.indexOf('985');
    var is_normal=level_type.indexOf('普通');
    //level_type=level_type.split("/");
//     major_n=data_array[i+w].majorList;
//    插入左边li
     left_insert((i+w+1));
     if(area_city== undefined){$('.diffcult').remove();}
     if(is_34<0){
    	 $('.item_0'+(i+w+1)).find('.is34').remove();
      }if(is_211<0){
    	  $('.item_0'+(i+w+1)).find('.is211').remove(); 
      }if(is_985<0){
    	  $('.item_0'+(i+w+1)).find('.is985').remove();
      }if(is_normal<0){
    	  $('.item_0'+(i+w+1)).find('.isnormal').remove();
      }


     }	
}else{
          if((i+w)<=data_array.length-1){
        	coll_name=data_array[i+w].college;
           img_num=data_array[i+w].logo;
         //每个学校的id
           college_id=data_array[i+w].collegeId;
           area_city=data_array[i+w].city;
           rank_num=data_array[i+w].rank;
//         每个li的院校层次
         level_type=data_array[i+w].typeInfo;
         var is_211=level_type.indexOf("211");
         var is_34=level_type.indexOf("34");
         var is_985=level_type.indexOf("985");
         var is_normal=level_type.indexOf("普通");
      
         //level_type=level_type.split("/");
           major_n=data_array[i+w].majorList;
           right_insert((i+w+1));

           if(is_34< 0){
          	 $('.item_0'+(i+w+1)).find('.is34').remove();
        	}if(is_211< 0){
        		$('.item_0'+(i+w+1)).find('.is211').remove();
        	}if(is_985< 0){
        		$('.item_0'+(i+w+1)).find('.is985').remove();
        	}if(is_normal<0){
        		$('.item_0'+(i+w+1)).find('.isnormal').remove();
        	}

           if(is_34<0){
          	 $('.item_0'+(i+w+1)).find('.is34').remove();
            }if(is_211<0){
          	  $('.item_0'+(i+w+1)).find('.is211').remove(); 
            }if(is_985<0){
          	  $('.item_0'+(i+w+1)).find('.is985').remove();
            }if(is_normal<0){
          	  $('.item_0'+(i+w+1)).find('.isnormal').remove();
            }

           
           }
	
       }
}

}


//如果即将到达底部再插入10个
function bottom_insert(){
$(window).scroll(function() {
		var w_height = $(window).height();//获得可视区域高度
		var s_height = $(document).scrollTop();//获得已经滚动上去的高度
		var main_height = $(document).height();//区域总高度
		var bottom_h = main_height- s_height - w_height;
		if(bottom_h<100 ){
			w+=10;
		loop_insert();
		page_url();
		console.log(123);
		}
	});	
console.log(w);
}




//判断当前页面
var str_major=window.location.pathname;
var str_texting=new RegExp('major');
var str_texting2=new RegExp('college');
function page_url()
{
//专业排名

if(str_texting.test(str_major))
 {
	major_ajax();

if(str_texting.test(str_major)){

	major_ajax();



}
//院校全国排名
else if(str_texting2.test(str_major)){
	
	college_ajax();

}
//院校省内
else{
	collegelocal_ajax();
	}	
 }
}
page_url();



//字体样式
$('.collname,.span_name').mouseenter(function(event){
	$(this).css('text-shadow','#fff 2px 2px 2px');
}).mouseleave(function(event){
	$(this).css('text-shadow','none');
});