// JavaScript Document
var xueke0="",xueke1="",xueke2="",xueke3="",xueke4="",xueke5="",num_0=0;
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
	
	function cond_hide(){
		$('.cond0').hide();
		$('.cond1').hide();
		$('.cond2').hide();
		$('.cond3').hide();
		$('.cond4').hide();
	}
	cond_hide();
	$('.cond5').show();

	// 学科门类
	$('.span02').hide();

	$('.subject').mouseenter(function(event) {
		var li_num=$(this).index();
		$(this).addClass('hong').siblings('.subject').removeClass('hong');
		$(this).siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
		$(this).siblings('.subject').find('a').css('color', '#333');
		$(this).find('.span01').hide();
		$(this).siblings('.subject').find('.span01').show();
		$(this).find('.span02').show();
		$(this).siblings('.subject').find('.span02').hide();
		$('.obj_son').eq(li_num-1).css('display', 'block').siblings('.obj_son').css('display', 'none');
		$(this).addClass('redd').siblings('.subject').removeClass('redd');
		
	});
	// 学科全部点击事件
	
	$('.sub_all').click(function(event) {
		$('.cond0').show();
		$('.cond0 .xk_0').remove();
		xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		$('.cond1').hide();
		xueke0="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)




	});
	
	$('.obj_son li').click(function(event) {
		$('.cond1').show();
		$('.cond1 .xk_0').remove();
		 xueke1=$(this).find('a').text();
		 xueke0=$('.redd').find('a').text();

		$("<span class='xk_0'>"+xueke1+"</span>").prependTo(".cond1");

		$('.cond0').show();
		$('.cond0 .xk_0').remove();
		xueke0=$('.redd').find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	
	});

	
	// 鼠标移出1秒钟后div消失
	$('.obj_son').mouseleave(function(event) {
			$('.obj_son').css('display', 'none');
			$('.subject').find('.span02').hide();
			$('.subject').find('.span01').show();
	});

	$('.subject').click(function(event) {
		var li_num=$(this).index();
		$(this).find('.span02').hide();
		$(this).find('.span01').show();
		$('.obj_son').eq(li_num-1).css('display', 'none');
		$('.cond0').show();
		$('.cond0 .xk_0').remove();
		xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond0");
		$('.cond1').hide();
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
		
		
		
	});


	//全部标签样式
	$('.li1').click(function(event) {
		
		$(this).addClass('hong').siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
		$(this).siblings('li').find('.span01').show();
		$(this).siblings('li').find('.span02').hide();
		$('.obj_son').css('display', 'none');
	});

	// 院校层次
	$('.cengci').mouseover(function(event) {
		$(this).addClass('hong').siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
	});
	// 院校你层次点击事件
	$('.cengci').click(function(event) {
		$('.cond2').show();
		$('.cond2 .xk_0').remove();
		xueke2=$(this).find('a').text();
		console.log(xueke2);
		$("<span class='xk_0'>"+xueke2+"</span>").prependTo(".cond2");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)

	});

	// 学术类型

	$('.leixing').mouseover(function(event) {
		$(this).addClass('hong').siblings('li').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').find('a').css('color', '#333');
	});
	
	$('.leixing').click(function(event) {
		$('.cond3').show();
		$('.cond3 .xk_0').remove();
		 xueke3=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke3+"</span>").prependTo(".cond3");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)

	});

	// 地区------全部
	$('.area0').click(function(event) {
		$(this).addClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').removeClass('hong');
		$(this).siblings('li').find('a').css('color', '#333');
	});
	
	
	$('.area01').click(function(event) {
		$('.cond4').show();
		$('.cond4 .xk_0').remove();
		xueke4=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke4+"</span>").prependTo(".cond4");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	
	// 地区------省市
	$('.ite044').mouseover(function(event) {
		$(this).addClass('hong');
		$(this).siblings('.ite04').removeClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('.ite04').find('a').css('color', '#333');
	});
	$('.ite044').click(function(event) {
		$('.cond4').show();
		$('.cond4 .xk_0').remove();
		xueke4=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke4+"</span>").prependTo(".cond4");
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
		
	});
	

	// 生成筛选条件
	$('.close').click(function(event) {
		$(this).parent('.cond').hide();
	});

	// 关闭标签
	$('.cond0 .close').click(function(event) {
		xueke0="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	$('.cond1 .close').click(function(event) {
		xueke1="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	$('.cond2 .close').click(function(event) {
		xueke2="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	$('.cond3 .close').click(function(event) {
		xueke3="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	$('.cond4 .close').click(function(event) {
		xueke4="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});
	$('.cond5 .close').click(function(event) {
		xueke5="";
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
	});

	


// 筛选条件ajax提交

function ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0){

		console.log(t1);
		$.ajax({
		 	url: 'back/search.html',
			 type: 'post',
			 data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,c:xueke5,t:t1,l:num_0},
			 success: function(msg) {
			 var data=eval("msg="+msg);
			 
			  if(data.status=='0'){
				  $(".main_ul").html('');
			   	ajax_01 (data);

			  }else{}
			            			
			            			
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
		ajax_search(xueke0,xueke1,xueke2,xueke3,xueke4,xueke5,t1,num_0)
		
		
	});





});


// 分页



	
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

// 插入首页搜索条件
 function cond5_charu(){
 	
 	 $('.cond5 .xk_0').remove();
 	$("<span class='xk_0'>"+xueke5+"</span>").prependTo(".cond5");
 }
 cond5_charu();




var list=null;
var obj_json=null;
var page_num=null;
var type0=null;
var l=num_0;

$(function(){


	$.post('back/search.html?t='+t1+'&c='+xueke5, {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,l:num_0}, function(data1) {
		var data = eval('data1='+data1);
//		console.log("post success");
		if (data.status==0) {
			ajax_01 (data);
		}
	}); //post结束



});

function ajax_01 (data)
			{
				// 将接收到的数据存储进自定义对象内
				type0=data.normalReturn.type;
				console.log("type:"+type0);
				 list=data.normalReturn.list;
				obj_json={array:list};
				// 封装插入标签函数
				//处理搜索类型为学校
				if (type0=='college') {
//					console.log("college begin");
					page_num=data.normalReturn.total;
					round_1();//第一次for循环结束
					collage_hide();

				// 定义总条数

				 pageShow(1,page_num);// 分页
				
				}else{
				//处理搜索类型为专业

				// 将接收到的数据存储进自定义对象内
//				type0=data.normalReturn.type;
//				 list=data.normalReturn.list;
//				obj_json={array:list};
				page_num=data.normalReturn.total;
				console.log("total:"+page_num);
				round_1();//第一次for循环结束
//				collage_hide();
				// 定义总条数

				 pageShow(1,page_num);// 分页
				
				}

			}


function city_hide(i){
	if (type0=='college'){
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
		if (length > 14) length = 14;
		console.log(length);
		for (var i=0;i<=length;i++)
		{	
			charu(i);
			// 条目点击函数
		}
		jump_info();
		
		
}//循环插入函数

function jump_info()
		{
		$('.jquery_0').click(function(event) {
			// var ttt=$(this).index();
			var pic_para=$(this).find('img').attr('src');
			// var name1_para=$(this).find('.major_a1').text();
			var name2_para=$(this).find('.major_a2').text();
			var leve01_para=$(this).find('.sch_name span:eq(0)').text();
			var leve02_para=$(this).find('.sch_name span:eq(1)').text();
			var leve03_para=$(this).find('.sch_name span:eq(2)').text();
			var range_para=$(this).find('.range').text();
			var thisid_para=$(this).find('.major').children('a').attr('href');
			// alert(this_id);
			
			$.cookie("pic", pic_para,{path:"/"});
			$.cookie("name2", name2_para,{path:"/"});
			$.cookie("leve01", leve01_para,{path:"/"});
			$.cookie("leve02", leve02_para,{path:"/"});
			$.cookie("leve03", leve03_para,{path:"/"});
			$.cookie("range", range_para,{path:"/"});
			$.cookie("thisid", thisid_para,{path:"/"});
			
			
			location.href="info.html";
			
			
		});	
		}

function charu(i){ 
	var name=city_hide(i);
	var value_show=degree_hide(i);
	var htmlcode01 = '\
		<li class="jquery_0">\
			<div class="pic">\
				<img src="http://42.96.190.127:8080/itui/images/' +obj_json.array[i].logo + '" alt="">\
			</div>\
			<div class="info">\
				<p class="major">\
					<a class="major_a1" href="' + obj_json.array[i].id + '"target="_blank">' + obj_json.array[i].name + '</a>\
					<a class="major_a2" href="'+obj_json.array[i].id+'"  target="_blank">' + obj_json.array[i].college + '</a>\
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
						<p>学校排名</p>\
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


function pageShow(ThisPage,PageCount){
					//ThisPage = 当前页
					//PageCount = 总条数
					//获取当前页之后，可通过Ajax进行返值。
					$(function() {
						//每页条数
						var pageText = 15;
						
						
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
							

							
							
						}
						click_charu();
					});
				}

//类型为college时用于控制页面显示
function collage_hide(){
	$('.sort2 p').text('本地排名');
	$('.difficult2 p').text('全国排名');
	$('.range_2').text('所属城市:');
	$('.major_a2').hide();	
}

function click_charu(){
	$('.pages .page_b').click(function(event){     	
		var this_strr=$(this).text();
		var this_num=parseInt(this_strr.match(/[1-9]+/));
		console.log("this_num:"+this_num);
		$('.main_ul').html('');
		pageShow(this_num,page_num);
		var length = (this_num-1)*15+14;
		if (length > page_num) length = page_num;
		console.log("length:"+length);
		var l_0=(Math.floor((this_num-1)/20))*300;
//		console.log(l);
		if (l_0 == l){
			for(var i=(this_num-1)*15-l_0;i<=length-l_0;i++)
			{
				console.log(i);
				charu(i);
				if (type0 == "college") collage_hide();
				jump_info();
				
			}//点击插入标签结束
		}


			console.log("l_0:"+l_0);
			console.log("l:"+l);
			if(l_0!=l)
			{
				l=l_0;
									
				
				$.ajax({
					url: 'back/search.html',
					type: 'POST',
					data: {cg:xueke0,sj:xueke1,mt:xueke3,ct:xueke2,a:xueke4,c:xueke5,t:t1,l:l_0},
						success:function(msg)
						{ 
							var data3=eval("msg="+msg);
							console.log("msg:");
							console.log(data3);
							if(data3.status==0)
							{
								
						// 将接收到的数据存储进自定义对象内
								type0=data3.normalReturn.type;
								console.log("type:"+type0);
								list=data3.normalReturn.list;
								obj_json={array:list};
								var length = (this_num-1)*15+14;
								if (length > page_num) length = page_num;
			   			for(i=(this_num-1)*15-l_0;i<length - l_0;i++)
			   				{
			   					console.log(i);
			   					charu(i);
			   					if (type0 == "college") collage_hide();
			   					jump_info();
			   				}

						 }else{}

						}
					});//点击是aja请求

				

//				click_ajax();
					
				
				
				

					
								//超过20页的请求
			}//如果不等于向后台请求数据
								
		});	
	}//点击函数结束







	
