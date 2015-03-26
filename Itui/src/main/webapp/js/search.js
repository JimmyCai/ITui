// JavaScript Document
var xueke0="",xueke1="",xueke2="",xueke3="",xueke4="",xueke5="",num_0=0;
$(function(){

//	鼠标移出下拉框1秒后下拉框消失
	$('.dropdown-menu').mouseout(function(event){
		setTimeout("$('.dropdown-menu').css('display', 'none')",20);
	});

	

	// 学科门类
	$('.span02').hide();

	$('.subject').mouseenter(function(event) {
		var li_num=$(this).index();
//		$(this).addClass('hong').siblings('.subject').removeClass('hong');
//		$(this).siblings('li').removeClass('hong');
//		$(this).find('a').css('color', '#fff');
//		$(this).siblings('li').find('a').css('color', '#333');
//		$(this).siblings('.subject').find('a').css('color', '#333');
//		$(this).siblings('.subject').removeClass('hong');
		$(this).find('.span01').hide();
		$(this).siblings('.subject').find('.span01').show();
		$(this).find('.span02').show();
		$(this).siblings('.subject').find('.span02').hide();
		$('.obj_son').eq(li_num-1).css('display', 'block').siblings('.obj_son').css('display', 'none');
		$(this).addClass('redd').siblings('.subject').removeClass('redd');
		
	});
	// 学科全部点击事件
	$('.sub_all').click(function(event) {
		$('.cond0').hide();
		$('.cond0 .xk_0').remove();
		xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		$('.cond1').hide();
		xueke0="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
		all_style('.sub_all');
	});
	
	//一级学科点击事件
	$('.obj_son li').click(function(event) {
		$('.redd').addClass('hong').siblings('.subject').removeClass('hong');
		$('.redd').siblings('li').removeClass('hong');
		$('.redd').find('a').css('color', '#fff');
		$('.redd').siblings('li').find('a').css('color', '#333');
		$('.cond1').show();
		$('.cond1 .xk_0').remove();
		 xueke1=$(this).find('a').text();
		 xueke0=$('.redd').find('a').text();
		$("<span class='xk_0'>"+xueke1+"</span>").prependTo(".cond1");
		$('.cond0').show();
		$('.cond0 .xk_0').remove();
		xueke0=$('.redd').find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);	
	});

	$('.obj_son').mouseleave(function(event) {
			$('.obj_son').css('display', 'none');
			$('.subject').find('.span02').hide();
			$('.subject').find('.span01').show();
	});

	$('.subject').click(function(event) {
		$(this).addClass('hong').siblings('.subject').removeClass('hong');
		$(this).siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
		var li_num=$(this).index();
		$(this).find('.span02').hide();
		$(this).find('.span01').show();
		$('.obj_son').eq(li_num-1).css('display', 'none');
		$('.cond0').show();
		$('.cond0 .xk_0').remove();
		xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		$('.cond1').hide();
		
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);	
	});


	//全部标签样式
	function all_style(li_class){
		console.log(li_class);
		$(li_class).addClass('hong').siblings('li').removeClass('hong');
		$(li_class).find('a').css('color', '#fff');
		$(li_class).siblings('li').find('a').css('color', '#333');
		$(li_class).siblings('li').find('.span01').show();
		$(li_class).siblings('li').find('.span02').hide();
		$('.obj_son').css('display', 'none');
	}



//	四个全部点击样式
	$('.all_cengci').click(function(event) {
		all_style('.all_cengci');		
	});
	$('.sub_all').click(function(event) {
		all_style('.sub_all');		
	});
	$('.all_leixing').click(function(event) {
		all_style('.all_leixing');		
	});
	$('.area01').click(function(event) {
		all_style('.area01');		
	});


	// 院校层次点击事件
	$('.cengci').click(function(event) {
		$(this).addClass('hong').siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
		$('.cond2').show();
		$('.cond2 .xk_0').remove();
		xueke2=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke2+"</span>").prependTo(".cond2");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	//全部 院校层次点击事件
	$('.all_cengci').click(function(event){
		$('.cond2').hide();
		$('.cond2 .xk_0').remove();
		xueke2=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke2+"</span>").prependTo(".cond2");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
		all_style('.all_cengci');
	});
	// 学术类型

	
	$('.leixing').click(function(event) {
		$(this).addClass('hong').siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
		$('.cond3').show();
		$('.cond3 .xk_0').remove();
		 xueke3=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke3+"</span>").prependTo(".cond3");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);

	});
//	类型全部点击函数
	$('.all_leixing').click(function(event){
		$('.cond3').hide();
		$('.cond3 .xk_0').remove();
		xueke3=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke3+"</span>").prependTo(".cond3");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
		all_style('.all_leixing');
	});

	// 地区------全部
	$('.area0').click(function(event) {
		$(this).addClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').removeClass('hong');
		$(this).siblings('li').find('a').css('color', '#333');
		
	});
	
	
	$('.area01').click(function(event) {

		$('.cond4').hide();
		$('.cond4 .xk_0').remove();
		xueke4=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke4+"</span>").prependTo(".cond4");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
		all_style('.area01');
	});
	
	$('.ite044').click(function(event) {
		$(this).addClass('hong');
		$(this).siblings('.ite04').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('.ite04').find('a').css('color', '#333');
		$('.cond4').show();
		$('.cond4 .xk_0').remove();
		xueke4=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke4+"</span>").prependTo(".cond4");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);	
		//设置当前页的url
		window.location.hash="#."+xueke4;
		console.log(decodeURI(window.location.hash));
	});
	

	// 生成筛选条件
	$('.close').click(function(event) {
		$(this).parent('.cond').hide();
	});

	// 关闭标签
	$('.cond0 .close').click(function(event) {
		xueke0="";
		all_style('.sub_all');
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);		
	});
	$('.cond1 .close').click(function(event) {
		xueke1="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	$('.cond2 .close').click(function(event) {
		xueke2="";
		all_style('.all_cengci');
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	$('.cond3 .close').click(function(event) {
		xueke3="";
		all_style('.all_leixing');
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	$('.cond4 .close').click(function(event) {
		xueke4="";
		//设置当前页的url
		window.location.hash="#."+xueke4;
		all_style('.area01');
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	$('.cond5 .close').click(function(event) {
		xueke5="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
	});
	

// 筛选条件ajax提交
function ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0){
//		console.log(t1);
		$.ajax({
		 	url: 'back/search.html',
			 type: 'post',
			 data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,c:xueke5,t:t1,l:num_0},
			 beforeSend:function()
			 {
				 $('.load_wait').css('display','block');
			 },
			 complete:function()
			 {
				 $('.load_wait').css('display','none');
			 },
			 success: function(msg) {
			 var data=eval("msg="+msg);			 
			  if(data.status=='0'){
				  $(".main_ul").html('');
			   	ajax_01 (data);
			  }else
			  {
//					404错误页面
					var err_msg=data.errMessage;
					$.cookie("err_msg",err_msg, {path : "/"});
					location.href="error.html";
			  }    			
			 }
		});
	}

function page_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0){
	console.log(t1);
	$.ajax({
	 	url: 'back/search.html',
		 type: 'post',
		 data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,c:xueke5,t:t1,l:num_0},
		 beforeSend:function()
		 {
			 $('.load_wait').css('display','block');
		 },
		 complete:function()
		 {
			 $('.load_wait').css('display','none');
		 },
		 success: function(msg) {
		location.href="search.html?t="+t1+"&c="+xueke5+"&#.";
		 var data=eval("msg="+msg);			 
		  if(data.status=='0'){
			  $(".main_ul").html('');
		   	ajax_01 (data);
		  }else
		  {
//				404错误页面
				var err_msg=data.errMessage;
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";  
		  }    			
		 }
	});
}

//搜索框事件
// 搜索框ajax提交
	$('#sou').click(function(event) {
		xueke5=$('#scbar_txt').val();
		t1=$('.nr_js').html();

		if(t1=="专业"){
			t1=1;
		}else{
			t1=2;
		}
			
		 cond5_charu();

		xueke0="",xueke1="",xueke2="",xueke3="",xueke4="";
		$('.cond0').hide();
		$('.cond1').hide();
		$('.cond2').hide();
		$('.cond3').hide();
		$('.cond4').hide();
		page_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0);
		
	});
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

		console.log($.cookie("about_index"));
	});	
});
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

function getPar(par){
    //获取当前URL
    var local_url =decodeURI(document.location.href) ; 
    //获取要取得的get参数位置
    var get = local_url.indexOf(par +"=");
    if(get == -1){
        return false;   
    }   
    //截取字符串
    var get_par = local_url.slice(par.length + get + 1);    
    //判断截取后的字符串是否还有其他get参数
    var nextPar = get_par.indexOf("&");
    if(nextPar != -1){
        get_par = get_par.slice(0, nextPar);
    }
    return get_par;
}
var t1 = getPar('t');
var c1 = getPar('c');
if (!c1) {
	c1 = '';
};
 xueke5=c1;
 
 if (t1=='1'){
	 $('.nr_js').html('专业');
	 $('.zhuanye a').html('学校');
 }else{
	 $('.nr_js').html('学校');
	 $('.zhuanye a').html('专业');
 }
 
function cond_hide(){
		$('.cond0').hide();
		$('.cond1').hide();
		$('.cond2').hide();
		$('.cond3').hide();
		$('.cond4').hide();
		$('.cond5').hide();
}
cond_hide();


// 插入首页搜索条件
 function cond5_charu(){ 
 	$('.cond5 .xk_0').remove();
// 	$("<span class='xk_0'>"+xueke5+"</span>").after(".keyword");
 	$('.keyword').after("<span class='xk_0'>"+xueke5+"</span>");
 }
 
if(xueke5!="")
{
	$('.cond5').show();
//	console.log(xueke5+"sg");
	cond5_charu();
}
 




var list=null;
var obj_json=null;
var page_num=0;
var type0=null;
var l=num_0;

$(function(){
//从信息页跳转过来第一次ajax
	$.ajax({
	 	url: 'back/search.html?t='+t1+'&c='+xueke5,
		 type: 'post',
		 data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,l:num_0},
		 beforeSend:function()
		 {
			 $('.load_wait').css('display','block');
		 },
		 complete:function()
		 {
			 $('.load_wait').css('display','none');
		 },
		 success: function(data1) {
			 var data = eval('data1='+data1);		 
		  if(data.status=='0'){
		   	ajax_01 (data);
		  }else
		  {
//				404错误页面
				var err_msg=data.errMessage;
				$.cookie("err_msg",err_msg, {path : "/"});
				location.href="error.html";
		  }    			
		 }
	});


});

function ajax_01 (data){
	// 将接收到的数据存储进自定义对象内
	$('.item_total').text(data.normalReturn.total);
	if(data.normalReturn.total==0)
		{
		$('.total_0').css('display','block');
		}else{
			$('.total_0').css('display','none');	
		}
	type0=data.normalReturn.type;
	list=data.normalReturn.list;
	obj_json={array:list};
	// 封装插入标签函数
	//处理搜索类型为学校
	if (type0=='college') {
		page_num=data.normalReturn.total;
		round_1();//第一次for循环结束
		collage_hide();
		// 定义总条数
		if(page_num>15){
			pageShow(1,page_num);// 分页
			console.log(page_num);
		}else{
			$('.page_0').html('');
		}
		
	}else{
		//处理搜索类型为专业
		page_num=data.normalReturn.total;
		round_1();//第一次for循环结束
		console.log('zhuanye');
		if(page_num>15){
			pageShow(1,page_num);// 分页
		}else{
			$('.page_0').html('');
		}
	}
}


function city_hide(i){
	if (type0=='college'){
		console.log(obj_json.array[i].city);
		return obj_json.array[i].city;
	}else{
		return obj_json.array[i].school;	
	}
	
}
function degree_hide(i){
	if (type0=='college'){
		return obj_json.array[i].localRank;
	}else{
		return obj_json.array[i].degree;	
	}
	
}


function round_1(){
		var length = page_num;
		if (length > 15) length = 15;
		
		for (var i=0;i<length;i++)
		{	
			charu(i);
			// 条目点击函数
		}
		jump_info();
		
		
}//循环插入函数

function jump_info(){
		$('.jquery_0').click(function(event){

			var thisid_para=$(this).find('.major').children('span').attr('major_href');
			if(type0 == 'major'){
				//location.href="info.html?major="+thisid_para;	
				window.open("info.html?major="+thisid_para,"_blank");
			}else {
				window.open("school.html?name="+thisid_para,"_blank");
			}
				
		});	
}

function charu(i){ 
	var name=city_hide(i);
	var value_show=degree_hide(i);
	var htmlcode01 = '\
		<li class="jquery_0">\
			<div class="pic">\
				<img src="http://www.itui.cn/itui/images/' +obj_json.array[i].logo + '" alt="">\
			</div>\
			<div class="info">\
				<p class="major">\
					<span class="major_a1" major_href="' + obj_json.array[i].id + '">' + obj_json.array[i].name + '</span>\
					<span class="major_a2" major_href="'+obj_json.array[i].id+'">' + obj_json.array[i].college + '</span>\
				</p>\
				<p class="sch_name">';
		if (obj_json.array[i].is211=='1') {
			htmlcode01 += '<span>211</span>';
		};
		if (obj_json.array[i].is34=='1') {
			htmlcode01 += '<span>34所</span>';
		};
		if (obj_json.array[i].is985=='1') {
			htmlcode01 += '<span>985</span>';
		};
		
		htmlcode01 +='</p>\
				<p class="range"><span class="range_2">所属院系：</span>\
					<span>' + name+ '</span>\
				</p>\
			</div>\
			<div class="group0">\
				<div class="sort0">\
					<div class="group1 sort1">\
						<p class="shuzi1">' + obj_json.array[i].rank + '</p>\
					</div>\
					<div class="group2 sort2">\
						<p>专业排名</p>\
					</div>\
				</div>\
				<div class="difficult0">\
					<div class="group1 difficult1">\
						<p class="shuzi2">' + value_show + '</p>\
					</div>\
					<div class="group2 difficult2">\
						<p>报考难度</p>\
					</div>\
				</div>\
			</div>\
		</li>';
						
	$('.main_ul').append(htmlcode01);
}//封装插入标签函数结束

var pageText = 15;
function pageShow(ThisPage,PageCount){
					//ThisPage = 当前页
					//PageCount = 总条数
					//获取当前页之后，可通过Ajax进行返值。
					$(function() {
						//每页条数					
						
						//分页总数
						var pageNumber = Math.ceil(PageCount / pageText);
						
						
						//page分割数量
						var pageFor = 4;
						var pageSlipt = pageFor / 2;
						
						var pageHTML = new Array;
						if (pageNumber > pageFor)
						 {
							
							if (ThisPage > pageFor) {
								pageHTML += "<div class='page_b'><a href=\"javascript:pageShow(1,'"+PageCount+"');\">第一页(1)</a></div>";
								

							}
							if (ThisPage > (pageFor / 2)) {
								if (ThisPage >= (pageNumber - pageSlipt)) {
									countPage = (((ThisPage - pageSlipt) + pageFor) - pageSlipt + 1);

								}else{
									countPage = ((ThisPage - pageSlipt) + pageFor);
								}
								for (i=(ThisPage - pageSlipt);i<countPage;i++) {
									if (i == ThisPage) {
										pageHTML += "<div class=\"page_b\" ><a href=\"javascript:pageShow("+i+",'"+PageCount+"');\" class=\"this\">" +i+ "</a></div>";
									}else{
										pageHTML += "<div class=\"page_b\"><a href=\"javascript:pageShow("+i+",'"+PageCount+"');\">" +i+ "</a></div>";
									}
								}
							}else{
								for (i=1;i<pageFor;i++) {
									if (i == ThisPage) {
										pageHTML += "<div class=\"page_b\"><a href=\"javascript:pageShow("+i+",'"+PageCount+"');\" class=\"this\">" +i+ "</a></div>";
									}else{
										pageHTML += "<div class=\"page_b\"><a href=\"javascript:pageShow("+i+",'"+PageCount+"');\">" +i+ "</a></div>";
									}
								}
							}
							if ((ThisPage + pageFor) < pageNumber) {
								pageHTML += "<div class=\"page_b\"><a href=\"javascript:pageShow("+pageNumber+",'"+PageCount+"');\">最后一页("+pageNumber+")</a></div>";
							}
							$(".page_0").html(pageHTML);
							

							
							
						}else{
							$('.page_0').html('');
							for(w=1;w<=pageNumber;w++)
								{
//								pageHTML += "<div class=\"page_b\"><a href=\"javascript:pageShow("+w+",'"+PageCount+"');\">" +w+ "</a></div>";
								pageHTML02="<div class=\"page_b\"><a href=\"javascript:pageShow("+w+",'"+PageCount+"');\">" +w+ "</a></div>";
								$('.page_0').append(pageHTML02);
								console.log(pageHTML);
								}
							
						}
						click_charu();
					});
				}

//类型为college时用于控制页面显示
function collage_hide(){
	$('.sort2 p').text('全国排名');
	$('.difficult2 p').text('本地排名');
	$('.range_2').text('所属城市:');
	$('.major_a2').hide();	
}

function click_charu(){
	$('.pages .page_b').click(function(event){     	
		var this_pageNum_strr=$(this).text();
		//当点击页码的时候就回到顶部
		 $(window).scrollTop(450);
		var this_num=parseInt(this_pageNum_strr.match(/[0-9]+/));
		console.log("this_num:"+this_num);
		$('.main_ul').html('');
		pageShow(this_num,page_num);
		var length = (this_num-1)*15+15;
		if (length > page_num) length = page_num;
		var l_0=(Math.floor((this_num-1)/20))*300;
		if (l_0 == l){
			for(var i=(this_num-1)*15-l_0;i<length-l_0;i++)
			{
				charu(i);
				if (type0 == "college") collage_hide();
				
				
			}//点击插入标签结束
			jump_info();
		}


			if(l_0!=l)
			{
				l=l_0;

              $.ajax({
					url: 'back/search.html',
					type: 'POST',
					data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,c:xueke5,t:t1,l:l_0},
					beforeSend:function()
					 {
						 $('.load_wait').css('display','block');
					 },
					 complete:function()
					 {
						 $('.load_wait').css('display','none');
					 },
						success:function(msg)
						{ 
							var data3=eval("msg="+msg);
							console.log("msg:");
							console.log(data3);
							if(data3.status==0)
							{
								
						// 将接收到的数据存储进自定义对象内
								console.log(data3.normalReturn.total);
								$('.item_total').text(data3.normalReturn.total);
								type0=data3.normalReturn.type;
								console.log("type:"+type0);
								list=data3.normalReturn.list;
								obj_json={array:list};
								var length = (this_num-1)*15+15;
								if (length > page_num) length = page_num;
			   			for(i=(this_num-1)*15-l_0;i<length - l_0;i++)
			   				{
//			   					console.log(i);
			   					charu(i);
			   					if (type0 == "college") collage_hide();
			   					
			   				}
			   			jump_info();
						 }else
						 {
//								404错误页面
								var err_msg=data.errMessage;
								$.cookie("err_msg",err_msg, {path : "/"});
								location.href="error.html";
						 }

						}
					});//点击是aja请求
					
								//超过20页的请求
			}//如果不等于向后台请求数据
								
		});	
	}//点击函数结束

$('#modal_load').hide();



var code_str=window.location.search;
var code_value=code_str.split("=");
var code_123=code_str.substring(3,(code_str.length+1));

//猛戳联系我们跳转
$('.total_trip a').click(function(event){
	$.cookie("about_index", "5", {
		path : "/"
	});
	console.log($.cookie("about_index"));
	window.open("about.html");
});

	
