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
var title_name='';
var rank_num='';
var coll_name='';
var item_title='城市：';
area_city='';
var item_value='';
var is_34='';
var is_211='';
var is_985='';
var img_num='defaultlogo.png';
var college_id='';
var w=0;
var class_num=1;
var major_n=new Array('','');
var level_type=new Array();

function major_nInsert(j){
	console.log('123qwe');
	var major_html='<span class="span_name name_0'+class_num+'">'+major_n[0].majorName+'/</span>';
}

//专业排名ajax
function major_ajax(){
	$.ajax({
		url: 'api/rank/major',
		type: 'get',
		dataType: 'html',
		data: {'mid': mid},
		success:function(msg)
		{
		var data = eval('msg=' + msg);
		if (data.status == 0)
		   {
			title_name=data.normalReturn.subjectName+'(前'+data.normalReturn.rankList.length+'名)';
			var subname=data.normalReturn.subjectName;
			//title标签，keyword，discription
			$('title').text(subname+'专业考研--爱推网--考研大数据');
			$('meta[name="Keywords"]').attr('content',subname+'考研--'+subname+'专业大学排名--'+subname+'专业排行榜--'+subname+'专业排名--'+subname+'研究生教育排行榜--'+subname+'大学本科教育排行榜');
			$('meta[name="Description"]').attr('content',subname+'专业排行榜综合考虑2012年教育部学位中心学科评估，武书连2014中国大学学科专业等的排名，拟合高校就业情况、社会声誉、生源质量生成爱推专业排行榜。');
			//科目名称
			$('.itemname').text(title_name);
			//把接收到的数据存进自定义数组
			for(var i=0;i<data.normalReturn.rankList.length;i++)
			{
				data_array[i]=data.normalReturn.rankList[i];
			}
			loop_insert();
			
			console.log(data.normalReturn.rankList);
			var back_color='#FEBC80';
			page_style(back_color);
			if($(window).width>600){
				$('.rank_0').css('margin-top','5%');
			}else{
				$('.rank_0').css('margin-top','9%');
			}
			
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
			title_name='院校全国排名'+'('+data.normalReturn.collegeRankList.length+'所)';
			//科目名称
			$('.itemname').text(title_name);
			//title标签，keyword，discription
			$('title').text('中国大学排行榜--爱推网--考研大数据');
			$('meta[name="Keywords"]').attr('content','中国考研--中国大学排行榜--中国大学排名--爱推|考研大数据。');
			$('meta[name="Description"]').attr('content','中国大学排行榜综合考虑2014中国校友会网高校排行榜，武书连2014中国大学排行榜，拟合高校就业情况、社会声誉、生源质量生成爱推专业排行榜。');
			//把接收到的数据存进自定义数组
			for(var i=0;i<data.normalReturn.collegeRankList.length;i++)
			{
			data_array[i]=data.normalReturn.collegeRankList[i];
			}
			loop_insert();
			console.log(data.normalReturn.collegeRankList);
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
		type: 'get',
		dataType: 'html',
		data: {'cid': cid},
		success:function(msg)
		{
		var data = eval('msg=' + msg);
		if (data.status == 0)
		   {
			title_name=data.normalReturn.area+'院校排名'+'('+data.normalReturn.collegeLocalRankList.length+'所)';
			areaname=data.normalReturn.area;
			//科目名称
			$('.itemname').text(title_name);
			//把接收到的数据存进自定义数组
			//title标签，keyword，discription
			$('title').text(areaname+'大学排行榜--爱推网--考研大数据');
			$('meta[name="Keywords"]').attr('content',areaname+'考研--'+areaname+'大学排行榜--'+areaname+'大学排名--爱推|考研大数据。');
			$('meta[name="Description"]').attr('content',areaname+'大学排行榜综合考虑2014中国校友会网高校排行榜，武书连2014中国大学排行榜，拟合高校就业情况、社会声誉、生源质量生成爱推专业排行榜。');
			for(var i=0;i<data.normalReturn.collegeLocalRankList.length;i++)
			{
				data_array[i]=data.normalReturn.collegeLocalRankList[i];
			}
			loop_insert();
			console.log(data.normalReturn.collegeLocalRankList);
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
//    每个li的院校层次
    level_type=data_array[i+w].typeInfo;    
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
      
           major_n=data_array[i+w].majorList;
           right_insert((i+w+1));
           if(area_city== undefined){$('.diffcult').remove();}
           if(is_34< 0){
          	 $('.item_0'+(i+w+1)).find('.is34').remove();
        	}if(is_211< 0){
        		$('.item_0'+(i+w+1)).find('.is211').remove();
        	}if(is_985< 0){
        		$('.item_0'+(i+w+1)).find('.is985').remove();
        	}if(is_normal<0){
        		$('.item_0'+(i+w+1)).find('.isnormal').remove();
        	}

           
           }
	
       }
}

}


//如果即将到达底部再插入10个
function bottom_insert(back_color){
$(window).scroll(function() {
		var w_height = $(window).height();//获得可视区域高度
		var s_height = $(document).scrollTop();//获得已经滚动上去的高度
		var main_height = $(document).height();//区域总高度
		var bottom_h = main_height- s_height - w_height;
		if(bottom_h<100&&w<data_array.length){
			w+=10;
		loop_insert();
		console.log(w);
		page_style(back_color);
		}
	});	
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
	
	var back_color='#FEBC80'; 
	bottom_insert(back_color);

	
}
//院校全国排名
else if(str_texting2.test(str_major)){
	college_ajax();
	var back_color='#5DB9E3';
	bottom_insert(back_color);
	
}
//院校省内
else{
	collegelocal_ajax();
	var back_color='#B2D155';
	bottom_insert(back_color);
	}	
}
page_url();



//字体样式
$('.collname,.span_name').mouseenter(function(event){
	$(this).css('text-shadow','#fff 2px 2px 2px');
}).mouseleave(function(event){
	$(this).css('text-shadow','none');
});