// JavaScript Document

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
	$('.cond').hide();

	// 学科门类
	$('.span02').hide();

	$('.subject').mouseover(function(event) {
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
		
	});

	$('.obj_son li').click(function(event) {
		$('.cond1').show();
		$('.cond1 .xk_0').remove();
		var xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond1");
	});
	



	$('.subject').click(function(event) {
		var li_num=$(this).index();
		$(this).find('.span02').hide();
		$(this).find('.span01').show();
		$('.obj_son').eq(li_num-1).css('display', 'none');
	});

	//全部
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

	$('.cengci').click(function(event) {
		$('.cond2').show();
		$('.cond2 .xk_0').remove();
		var xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond2");
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
		var xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond3");
	});

	// 地区------全部
	$('.area0').click(function(event) {
		$(this).addClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').removeClass('hong');
		$(this).siblings('li').find('a').css('color', '#333');
	});
	// 地区------一区，二区
	$('.area01').click(function(event) {
		$(this).addClass('hong');
		$(this).find('a').css('color', '#fff');
		$(this).siblings('li').removeClass('hong');
		$(this).siblings('li').find('a').css('color', '#333');
	});
	$('.area01').click(function(event) {
		$('.cond4').show();
		$('.cond4 .xk_0').remove();
		var xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond4");
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
		var xueke0=$(this).find('a').text();
		$("<span class='xk_0'>"+xueke0+"</span>").prependTo(".cond4");
	});
	

	// 生成筛选条件
	$('.close').click(function(event) {
		$(this).parent('.cond').hide();
	});

	// 二维码
	// var win_width=$(window).width();
	
	// 	$('.erwei').mouseenter(function(event) {
	// 		$('.erwei03').css('display', 'block');
	// 	}).mouseleave(function(event) {
	// 		$('.erwei03').css('display', 'none');
	// 	});
		
	

	


	pageShow(1,500);


})


// 分页

	function pageShow(ThisPage,PageCount) {
	//ThisPage = 当前页
	//PageCount = 总条数
	//获取当前页之后，可通过Ajax进行返值。
	$(function() {
		//每页条数
		var pageText = 4;
		
		//分页总数
		var pageNumber = Math.ceil(PageCount / pageText);
		
		//page分割数量
		var pageFor = 6;
		var pageSlipt = pageFor / 2;
		
		var pageHTML = new Array;
		if (pageNumber > pageFor) {
			if (ThisPage > pageFor) {
				pageHTML += "<a href=\"javascript:pageShow(1,'"+PageCount+"');\">第一页(1)</a>";
			}
			if (ThisPage > (pageFor / 2)) {
				if (ThisPage >= (pageNumber - pageSlipt)) {
					countPage = (((ThisPage - pageSlipt) + pageFor) - pageSlipt + 1);
				}else{
					countPage = ((ThisPage - pageSlipt) + pageFor);
				}
				for (i=(ThisPage - pageSlipt);i<countPage;i++) {
					if (i == ThisPage) {
						pageHTML += "<a href=\"javascript:pageShow("+i+",'"+PageCount+"');\" class=\"this\">" +i+ "</a>";
					}else{
						pageHTML += "<a href=\"javascript:pageShow("+i+",'"+PageCount+"');\">" +i+ "</a>";
					}
				}
			}else{
				for (i=1;i<pageFor;i++) {
					if (i == ThisPage) {
						pageHTML += "<a href=\"javascript:pageShow("+i+",'"+PageCount+"');\" class=\"this\">" +i+ "</a>";
					}else{
						pageHTML += "<a href=\"javascript:pageShow("+i+",'"+PageCount+"');\">" +i+ "</a>";
					}
				}
			}
			if ((ThisPage + pageFor) < pageNumber) {
				pageHTML += "<a href=\"javascript:pageShow("+pageNumber+",'"+PageCount+"');\">最后一页("+pageNumber+")</a>";
			}
			$(".pages").html(pageHTML);
		}
	});
}

	// 分页









	



	
