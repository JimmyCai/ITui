// JavaScript Document
//导航区域
$('.itui-news-login-regs').mouseenter(function(event) {
	$('.dropdown-list').removeClass('hide');
});
$('.dropdown-list').mouseleave(function(event) {
	$('.dropdown-list').addClass('hide');
});
//12个话题
var topic_li=0,topic_href="www.baidu.com",topic_content="话题1";
var topic_list=new Array(),person_list=new Array(),news_list=new Array();

function topic(topic_li,topic_href,topic_content){
var topic_html='<li role="presentation" class="topic0'+topic_li+'">\
					<a role="menuitem" tabindex="-1" href="http://'+topic_href+'" target="_blank">'+topic_content+'</a>\
				</li>';
$('.dropdown-menu-news').append(topic_html);
}

//20150526新首页ajax
	function news_ajax(){
	$.ajax({
		url : 'api/index',
		type : 'get',
		dataType : 'html',
		success : function(msg) {
			data = eval('msg=' + msg);
			if (data.status == '0') {
//				精选话题
				for(i=0;i<data.normalReturn.indexInfo[0].length;i++){
					topic_list[i]=data.normalReturn.indexInfo[0][i];
					}
				for(i=0;i<topic_list.length;i++){
					topic(i,topic_list[i].topicPage,topic_list[i].topic);
					}
//				达人名片
				for(i=0;i<data.normalReturn.indexInfo[1].length;i++){
					person_list[i]=data.normalReturn.indexInfo[1][i];
					}
				for(i=0;i<person_list.length;i++){
					$('.li_img0'+i).attr('src','http://'+person_list[i].userPhoto);
					$('.personal_des0'+i).text(person_list[i].signature);
					$('.card_name0'+i).text(person_list[i].userName);
					$('.card_follow0'+i).children('a').attr('href', 'http://'+person_list[i].homePage);
					$('.card-ul li').eq(i).children('.card_head').find('a').attr('href', 'http://'+person_list[i].homePage);
					$('.card_sch0'+i).text(person_list[i].userSchool);
					
				}
				for(i=0;i<data.normalReturn.indexInfo[2].length;i++){
					news_list[i]=data.normalReturn.indexInfo[2][i];
				}
				for(i=0;i<news_list.length;i++){
					$('.news_img0'+i).attr('src', 'http://'+news_list[i].newsPhoto);
					$('.news_intr0'+i).text(news_list[i].summary);
					$('.news_item0'+i).text(news_list[i].title);
					$('.news_a0'+i).attr('href', 'http://'+news_list[i].newsPage);
				}
				
			} else {
				
				console.log('no');
			}
		}
	});
}
	news_ajax();
	
//导航区域
$('.itui-news-login-regs').mouseenter(function(event) {
	$('.dropdown-list').removeClass('hide');
});
$('.dropdown-list').mouseleave(function(event) {
	$('.dropdown-list').addClass('hide');
});

$(function(){

});
