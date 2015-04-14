// JavaScript Document

$(function(){
	card_ajax();





});
//页面加载函数结束

var cards=new Array();
var j=0;
//名片变量定义
var s_year,s_month,s_date,s_hour,s_minute;
var e_year,e_month,e_date,e_hour,e_minute;
var lesson,today,tomorrow,datetype;
var summary,tags1,tags2,tags3,tags4;
var orgname,techername,platname,livesrc,techerlogo,price;
var orgsrc,platsrc;


function round_insert(){
   for(i=0;i<3;i++)
	{
	if((i+j)<cards.length){
		li_insert(i+j);
	  }
	}
}

//如果即将到达底部再插入3个
function bottom_insert(){
$(window).scroll(function() {
		var w_height = $(window).height();//获得可视区域高度
		var s_height = $(document).scrollTop();//获得已经滚动上去的高度
		var main_height = $(document).height();//区域总高度
		var bottom_h = main_height- s_height - w_height;
			if(bottom_h<20&&j<(cards.length)-3){
				j+=3;
				round_insert();
				console.log(j);
			}
	});	
}

function li_insert(index){
	
//变量赋值

	techerlogo=cards[index].teacherInfo.photo;
	techername=cards[index].teacherInfo.teacherName;
	price=cards[index].courseInfo.price;
	summary=cards[index].courseInfo.summary;
	
	s_year=cards[index].courseInfo.startDay.substring(0,4);
	s_month=cards[index].courseInfo.startDay.substring(4,6);
	s_date=cards[index].courseInfo.startDay.substring(6,8);
	s_hour=cards[index].courseInfo.startTime.substring(0,2);
	s_minute=cards[index].courseInfo.startTime.substring(2,4);

	e_year=cards[index].courseInfo.endDay.substring(0,4);
	e_month=cards[index].courseInfo.endDay.substring(4,6);
	e_date=cards[index].courseInfo.endDay.substring(6,8);
	e_hour=cards[index].courseInfo.endTime.substring(0,2);
	e_minute=cards[index].courseInfo.endTime.substring(2,4);
	
	lesson=cards[index].courseInfo.lesson;
	orgname=cards[index].teacherInfo.orgName;
	platname=cards[index].courseInfo.platform;
	orgsrc=cards[index].teacherInfo.orgWeb;
	platsrc=cards[index].courseInfo.platformWeb;
	livesrc=cards[index].courseInfo.liveSrc;

	if(cards[index].courseInfo.dateType==0){
		datetype='今天';
	}
	else if(cards[index].courseInfo.dateType==1){
		datetype='明天';
	}else{
		datetype=s_year+'年'+s_month+'月'+s_date+'日';
	}
	
	var index1 = (cards[index].courseInfo.tag).indexOf("/");
	if((cards[index].courseInfo.tag).length=0){
		tags1="标签缺省";
		tags2="";
		tags3="";
		tags4="";
	}else if(index1==-1){
		tags1=cards[index].courseInfo.tag;
		tags2="";
		tags3="";
		tags4="";
	}else{
		var tag=(cards[index].courseInfo.tag).split("/");
		console.log(tag.length);

	if(tag[2]==undefined){
			tag[2]="";
		}
    if(tag[3]==undefined){
			console.log("kong");
			tag[3]="";
		}
		tags1=tag[0];
		tags2=tag[1];
		tags3=tag[2];
		tags4=tag[3];
		

	}
	

var li_html='\
	    <li class="card-li card0'+index+'">\
	    <div class="card-div">\
						<p class="div-item">\
							'+datetype+'\
						</p>\
						<div class="course-01">\
							<div class="tech">\
								<div class="techer-logo">\
									<img src="images/'+techerlogo+'" alt="" class="img-responsive">\
								</div>\
								<p>'+techername+'</p>\
								<p class="price-p">\
									<span>￥</span><span class="price-s">'+price+'</span>\
								</p>\
							</div>\
						</div>\
						<div class="course-01">\
							<p class="message">'+summary+'</p>\
						</div>\
						<div class="course-01">\
							<div class="course_s start">\
								<img src="images/c-3.png" alt="">\
								<span class="span-p">课程时间:</span>\
								<span class="start-s statr_date">'+s_year+'-'+s_month+'-'+s_date+'</span>\
								<span class="start-s statr_time">'+s_hour+':'+s_minute+'</span>\
							</div>\
							<div class="course_s stop">\
								<span class="start-s stop_date">'+e_year+'-'+e_month+'-'+e_minute+'</span>\
								<span class="start-s stop_time">'+e_hour+':'+e_minute+'</span>\
								<img src="images/c-6.png" alt="">\
								<span class="span-p">'+lesson+' 课时</span>\
							</div>\
						</div>\
						<div class="course-01">\
							<div class="sch-left">\
								<div class="sch">\
									<img src="images/c-4.png" alt="">\
									<span class="sch-s">开课机构：</span>\
									<span class="sch-name"><a href="'+orgsrc+'" target="_blank">'+orgname+'</a></span>\
								</div>\
								<div class="sch sch-plat">\
									<img src="images/c-5.png" alt="">\
									<span class="sch-p">直播平台：</span>\
									<span class="sch-p-name"><a href="'+platsrc+'" target="_blank">'+platname+'</a></span>\
								</div>\
							</div>\
							<div class="gouon">\
								<div class="go-btn">\
									<div id="trianle"></div>\
									<span class="go-on">\
										<a target="_blank" href="'+livesrc+'">前往观看</a>\
									</span>\
								</div>\
							</div>\
						</div>\
						<div class="course-01">\
							<p class="p-tag">\
								<span class="span-tag">'+tags1+'</span>\
								<span class="span-tag">'+tags2+'</span>\
								<span class="span-tag">'+tags3+'</span>\
								<span class="span-tag">'+tags4+'</span>\
							</p>\
						</div>\
					</div>\
     	</li>';
     	$('.card').append(li_html);
}



function card_ajax(){
	$.ajax({
		url: '02.php',
		type: 'post',
		dataType: 'html',
		success:function(msg){
		var data = eval('msg='+msg);
		if (data.status == 0){
			var cardslength=data.normalReturn.courseList.length;
			// 将接收数据赋值给自定义数组
			for(w=0;w<cardslength;w++)
			{
				cards[w]=data.normalReturn.courseList[w];
			}
			console.log(cards[3]);
			//根据数组长度插入名片
				if(cards.length<=3)
				{
					for(i=0;i<cards.length;i++)
					{
						li_insert(i+j);
					}
				}else{
						round_insert();
						bottom_insert();
					}

			}else{}
		}
	});
	
	
}
