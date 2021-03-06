// JavaScript Document

$(function() {
	// 专业学校转换
	// $('.d_down').click(function(event) {
	// $('.dropdown-menu').css('display', 'block');
	// });
	//
	// $('.zhuanye a ').click(function(event) {
	// $('.dropdown-menu').css('display', 'none');
	// var neirong = $(this).html();
	// var neirong2 = $('.nr_js').html();
	// $('.nr_js').html(neirong);
	// $('.zhuanye a ').html(neirong2);
	// });
	// 鼠标移出下拉框1秒后下拉框消失
	$('.dropdown-menu').mouseout(function(event) {
		setTimeout("$('.dropdown-menu').css('display', 'none')", 1000);
	});

	// 柱状图横坐标全称显示
	$('.course_full').hide();
	$('.course').stop()
			.mouseenter(
					function(event) {
						var len_course = $(this).find('a').text().length;
						var text_course = $(this).find('a').text();
						if (len_course > 5) {
							if (len_course == 6) {
								$(this).siblings('.course_full').css('bottom',
										'-30px');
								// $(this).siblings('.course_full').find('a')css('text-align','right');
							} else {
								$(this).siblings('.course_full').css('bottom',
										'-50px');
							}
							$(this).siblings('.course_full').show().find('a')
									.text(text_course);
							$(this).parent('.baowei0').find('.course').animate(
									{
										display : 'none'
									}, 1);

						} else {
							$('.course_full').animate({
								display : 'none'
							}, 1);
						}
					});
	$('.course_full').stop().mouseleave(function(event) {
		$(this).hide();
		$(this).parent('.baowei0').find('.course').animate({
			display : 'block'
		}, 1);
	});

	// 所属专业和院校部分全称展示
	$('.name_full').hide();
	$('.sch_full').hide();
	// 专业全称
	$('.zy_name').mouseenter(function(event) {
		var leng_name = $(this).text().length;
		var text_name = $(this).html();
		if (leng_name > 12) {
			$(this).siblings('.name_full').show().html(text_name);

		} else {
			$('.name_full').hide();
		}
	});

	$('.name_full').mouseleave(function(event) {
		$(this).hide();
	});

	// 院校全称
	$('.yuanxi').mouseenter(function(event) {
		var len_sch = $(this).text().length;
		var text_sch = $(this).html();
		if (len_sch > 15) {
			$(this).siblings('.sch_full').show().html(text_sch);
		} else {
			$('.sch_full').hide();
		}
	});

	$('.sch_full').mouseleave(function(event) {
		$(this).hide();
	});

	// 二维码
	$('.ew_ma').hide();
	$('.ew_01').mouseenter(function(event) {
		$('.ew_ma').show();
	});
	$('.ew_01').mouseleave(function(event) {
		$('.ew_ma').hide();
	});

	// 回顶部事件
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
			: $('body')) : $('html,body');

	$('.back_top').click(function(event) {
		$body.animate({
			scrollTop : $('#nav0').offset().top
		}, 500);
		return false;
	});

	// 侧面锚点导航
	$('.an_1').mouseenter(function(event) {

		$('.ann').eq(0).find('.an_mao1').css('color', '#00a3eb');
		$('.ann').eq(0).find('.yuan').css('background-color', '#00a3eb');
	}).mouseleave(function(event) {

		$('.ann').eq(0).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(0).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_2').mouseenter(function(event) {
		$('.ann').eq(1).find('.an_mao1').css('color', '#b5d156');
		$('.ann').eq(1).find('.yuan').css('background-color', '#b5d156');
	}).mouseleave(function(event) {
		$('.ann').eq(1).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(1).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_3').mouseenter(function(event) {
		$('.ann').eq(2).find('.an_mao1').css('color', '#69bde1');
		$('.ann').eq(2).find('.yuan').css('background-color', '#69bde1');
	}).mouseleave(function(event) {
		$('.ann').eq(2).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(2).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_4').mouseenter(function(event) {
		$('.ann').eq(3).find('.an_mao1').css('color', '#feaf6d');
		$('.ann').eq(3).find('.yuan').css('background-color', '#feaf6d');
	}).mouseleave(function(event) {
		$('.ann').eq(3).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(3).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_5').mouseenter(function(event) {

		$('.ann').eq(4).find('.an_mao1').css('color', '#98d877');
		$('.ann').eq(4).find('.yuan').css('background-color', '#98d877');
	}).mouseleave(function(event) {

		$('.ann').eq(4).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(4).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_6').mouseenter(function(event) {

		$('.ann').eq(5).find('.an_mao1').css('color', '#e372cc');
		$('.ann').eq(5).find('.yuan').css('background-color', '#e372cc');
	}).mouseleave(function(event) {

		$('.ann').eq(5).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(5).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_7').mouseenter(function(event) {

		$('.ann').eq(6).find('.an_mao1').css('color', '#e66b1a');
		$('.ann').eq(6).find('.yuan').css('background-color', '#e66b1a');
	}).mouseleave(function(event) {

		$('.ann').eq(6).find('.an_mao1').css('color', '#a8a5a6');
		$('.ann').eq(6).find('.yuan').css('background-color', '#a8a5a6');
	});

	$('.an_1').click(function(event) {
		$body.animate({
			scrollTop : $('#nav02').offset().top
		}, 500);
		return false;
	});

	$('.an_2').click(function(event) {
		$body.animate({
			scrollTop : $('#nav03').offset().top
		}, 500);
		return false;
	});

	$('.an_3').click(function(event) {
		$body.animate({
			scrollTop : $('#nav04').offset().top
		}, 500);
		return false;
	});

	$('.an_4').click(function(event) {
		$body.animate({
			scrollTop : $('#nav05').offset().top
		}, 500);
		return false;
	});

	$('.an_5').click(function(event) {
		$body.animate({
			scrollTop : $('#nav07').offset().top
		}, 500);
		return false;
	});

	$('.an_6').click(function(event) {
		$body.animate({
			scrollTop : $('#nav08').offset().top
		}, 500);
		return false;
	});

	$('.an_7').click(function(event) {
		$body.animate({
			scrollTop : $('#nav09').offset().top
		}, 500);
		return false;
	});

	// 院校排名情况
	$('.rank').mouseover(function(event) {
		// $(this).addClass('rank_ac').siblings('.rank').removeClass('rank_ac');
		// alert($(this).index());
	});
	$('.left li').click(function(event) {
		var about_index = $(this).index();
		$.cookie("about_index", about_index, {
			path : "/"
		});

	});

});
// 页面加载函数结束

// 最低分最高分全局变量
var score_min = '';
var score_max = '';

// 解析url参数
function getPar(par) {
	// 获取当前URL
	var local_url = decodeURI(document.location.href);
	// 获取要取得的get参数位置
	var get = local_url.indexOf(par + "=");
	if (get == -1) {
		return false;
	}
	// 截取字符串
	var get_par = local_url.slice(par.length + get + 1);
	// 判断截取后的字符串是否还有其他get参数
	var nextPar = get_par.indexOf("&");
	if (nextPar != -1) {
		get_par = get_par.slice(0, nextPar);
	}
	return get_par;
}
var thisid = getPar('major');
var followid = -1;
var cid = null;
var cid_name = null;
// parameters below are for comparing majors
var major_rank = null;
var college_rank = null;
var degree_description = '';
var apply_admit_rate = null;

// 点击专业排名，跳转
$('.Ranking li').eq(0).click(function(event) {
	if ($('.Ranking li').eq(0).find('.rank_p').text() != '不参与排名') {
		window.open("majorrank.html?major=" + thisid, "_blank");
	}

});
// 点击院校全国排名跳转
$('.Ranking li').eq(1).click(
		function(event) {
			if ($('.Ranking li').eq(1).find('.rank_p').text() != '不参与排名') {
				window.open("collegerank.html?cid=" + cid + "&c_name="
						+ cid_name, "_blank");
			}

		});
// 点击院校省内排名跳转
$('.Ranking li').eq(2).click(
		function(event) {
			if ($('.Ranking li').eq(2).find('.rank_p').text() != '不参与排名') {
				window.open("provincerank.html?cid=" + cid + "&c_name="
						+ cid_name, "_blank");

			}

		});
// 设置全局的404错误跳转页面
$.ajaxSetup({
	statusCode : {
		404 : function() {
			var err_msg = data.errMessage;
			$.cookie("err_msg", err_msg, {
				path : "/"
			});
			location.href = "error.html";
		}
	}
});

// 接收数据
function major_ajax() {

	$
			.ajax({
				url : 'getmajorinfo.html',
				type : 'get',
				dataType : 'html',
				data : {
					'mid' : thisid,
					'code' : $.cookie('user')
				},
				success : function(msg) {
					var data = eval('msg=' + msg);
					if (data.status == 0) {

						data = data.normalReturn;

						// 学校编码
						$('.sch_code').text(data.baseInfo.collegeCode);
						$('.major_code').text(data.baseInfo.majorCode);
						var baseInfo = data.baseInfo;
						$('.coll_1').append(data.baseInfo.college + '/');
						cid_name = data.baseInfo.college;
						// 获得followid
						followid = baseInfo.followId;

						Attention();
						// 改变title标签
						$('title').text(
								baseInfo.majorName + '-' + baseInfo.school
										+ '-' + data.baseInfo.college
										+ '-爱推网-考研大数据');
						// 关键字
						$('meta[name="Keywords"]').attr(
								'content',
								'[' + baseInfo.majorName + ']' + '专业排名：'
										+ data.rankInfo.majorRank + ' ['
										+ data.baseInfo.college + ']' + '学校排名：'
										+ data.rankInfo.collegeLocalRank
										+ '   --' + data.baseInfo.college
										+ '近年分数线平均分：' + data.scoreInfo.scoreAvg
										+ '  --' + data.baseInfo.college
										+ '报录比');
						// 描述标签
						$('meta[name="Description"]').attr(
								'content',
								data.baseInfo.college + '的'
										+ baseInfo.majorName + '专业2016年考研信息，'
										+ '提供' + data.baseInfo.college
										+ '最新大学排名、' + baseInfo.majorName
										+ '最新专业排名、近四年' + data.baseInfo.college
										+ '考研分数线、' + baseInfo.majorName
										+ '考研调剂，独家推出考研竞争分析指数直观反应市场报录情况。');
						// 提取学校的三个类型
						var str_type = baseInfo.typeInfo;
						var arr_type = new Array();
						if (str_type.search("211") >= 0)
							arr_type[0] = 211;
						if (str_type.search("985") >= 0)
							arr_type[1] = 985;
						if (str_type.search("34") >= 0)
							arr_type[2] = 34;
						// 插入学校页面头专业信息
						base_info(baseInfo.logo, baseInfo.majorName,
								baseInfo.school, arr_type[0], arr_type[1],
								arr_type[2], baseInfo.collegeId);
						cid = baseInfo.collegeId;

						// 插入学校综合评级
						grade_charuA(data);
						// 插入四个评级进度条
						four_Grade(data.gradeInfo.rateGrade, tag01);
						four_Grade(data.gradeInfo.scoreGrade, tag02);
						four_Grade(data.gradeInfo.collegeGrade, tag03);
						four_Grade(data.gradeInfo.cityGrade, tag04);
						// 插入三个排名标签

						ranking_charu(data.rankInfo.majorRank, rank_tag1,
								rank_item1);
						major_rank = data.rankInfo.majorRank;

						ranking_charu(data.rankInfo.collegeRank, rank_tag2,
								rank_item2);
						college_rank = data.rankInfo.collegeRank;
						ranking_charu(data.rankInfo.collegeLocalRank,
								rank_tag3, rank_item3);
						// 折线图变量
						for (var i = 0; i < data.scoreInfo.yearScoreInfo.length; i++) {
							year[i] = data.scoreInfo.yearScoreInfo[i].year;
							score[i] = data.scoreInfo.yearScoreInfo[i].score;
						}

						score_min = data.scoreInfo.scoreLow;
						// 调用折线图函数
						line_0();
						max_score_find();
						score_max = max_score_find();
						// 四个分数标签
						score_avg = data.scoreInfo.scoreAvg;
						score_lowyear = data.scoreInfo.scoreLowYear;
						score_trend = data.scoreInfo.trend;
						score_tag();
						// 竞争分析
						score_0 = data.competitionInfo.degree;
						rateDegree = data.competitionInfo.rateDegree;
						scoreDegree = data.competitionInfo.scoreDegree;
						collegeDegree = data.competitionInfo.collegeDegree;
						cityDegree = data.competitionInfo.cityDegree;
						var degreeDescription = data.competitionInfo.degreeDescription;
						degree_description = degreeDescription;
						compet(rateDegree, a5_tag, dengji_tag1);
						compet(scoreDegree, b5_tag, dengji_tag2);
						compet(collegeDegree, c5_tag, dengji_tag3);
						compet(cityDegree, d5_tag, dengji_tag4);
						$('.score0').text(score_0);
						$('.difficult05').text(degreeDescription);
						// 近三年报录比均值
						var ratae_value = parseInt(data.applyAdmitInfo.rate,
								data.applyAdmitInfo.rateDescription);
						apply_admit_rate = data.applyAdmitInfo.rate;
						if (ratae_value == -1) {
							$('div.Interpretation p').text("数据不足，请参考相似专业！");
						}

						if (ratae_value >= 100) {
							$('.exceed').css('display', 'block');
							applyexceed(data.applyAdmitInfo.applyCount,
									data.applyAdmitInfo.admitCount,
									data.applyAdmitInfo.exemptionCount);
						} else if (data.applyAdmitInfo.rate == '-1') {
							$('.exceed').css('display', 'block');
							applyexceed(data.applyAdmitInfo.applyCount,
									data.applyAdmitInfo.admitCount,
									data.applyAdmitInfo.exemptionCount,
									data.applyAdmitInfo.applyDescription,
									data.applyAdmitInfo.admitDescription);
							$('.biao_6').hide();
						} else {
							applyAdmit(data.applyAdmitInfo.rate,
									data.applyAdmitInfo.rateDescription);
							applyexceed(data.applyAdmitInfo.applyCount,
									data.applyAdmitInfo.admitCount,
									data.applyAdmitInfo.exemptionCount,
									data.applyAdmitInfo.applyDescription,
									data.applyAdmitInfo.admitDescription);
						}

						// 柱形图
						maininfo = data.majorRecommendInfo.mainInfo;
						function float_value(x_unknow) {
							if (maininfo[x_unknow].value == '-1') {
								return maininfo[x_unknow].value;
							} else {
								maininfo[x_unknow].value = parseFloat(maininfo[x_unknow].value
										.replace('%', ''));
								return maininfo[x_unknow].value;
							}

						}
						// 8个柱状图显示
						for (var i = 0; i < maininfo.length; i++) {
							// 将每个专业的id存进数组
							major_bar.push(maininfo[i].majorId);
							columnchart(i, float_value(i),
									maininfo[i].majorName, maininfo[i].color,
									maininfo[i].schoolName);
						}
						// 插入柱状图的四个标签
						bar_tag(1, data.majorRecommendInfo.similarCount);
						bar_tag(2, data.majorRecommendInfo.nearCount);
						bar_tag(3, data.majorRecommendInfo.correlateCount);
						bar_tag(4,
								data.majorRecommendInfo.transdisciplinaryCount);
						// 感兴趣的专业
						var maininfo_major = data.interestedMajorInfo.mainInfo;
						for (i = 0; i < maininfo_major.length; i++) {
							insert_major(i, maininfo_major[i].name,
									maininfo_major[i].school,
									maininfo_major[i].degree,
									maininfo_major[i].majorId);
						}

						var maininfo_college = data.interestedCollegeInfo.mainInfo;
						for (i = 0; i < maininfo_college.length; i++) {
							insert_college(i, maininfo_college[i].name,
									maininfo_college[i].level,
									maininfo_college[i].rank,
									maininfo_college[i].collegeId);
						}

						var questionInfo = data.questionInfo;
						for (i = 0; i < questionInfo.length; i++) {
							insert_question(i, questionInfo[i].questionId,
									questionInfo[i].question);
						}

					} else {
						// 404错误页面
						var err_msg = data.errMessage;
						if (err_msg !== '请先登录！') {
							$.cookie("err_msg", err_msg, {
								path : "/"
							});
							location.href = "error.html";
						} else {
							console.log(err_msg);
						}
					}
				}
			});
}
major_ajax();

// nav01页面头信息
function base_info(img_logo, majorname, schoolname, type_211, type_985,
		type_34, collageid) {

	$('.sch_name').find('img').attr('src',
			'http://www.itui.cn/itui/images/' + img_logo);
	$('.sch_info').find('h3').text(majorname);
	$('.coll_2').text(schoolname);
	if (type_211 != 211) {
		// $('.leve01').remove();
	} else {
		$('.leve01 p').text(type_211);
	}

	if (type_985 != 985) {
		$('.leve02').remove();
	} else {
		$('.leve02 p').text(type_985);

	}

	if (type_34 != 34) {
		$('.leve03').remove();
	} else {
		$('.leve03 p').text(type_34);

	}
	if (type_211 != 211 && type_985 != 985 && type_34 != 34) {
		$('.leve01 p').text("普通");
	}
	//	
	// $('.leve02 p').text(type_985);
	// $('.leve03 p').text(type_34);
	$('.dh li').eq(0).find('a').attr('href', 'school.html?name=' + collageid);

}

var collageindex_hre = null;
var code2 = null;
var tag01 = $('.a_A');
var tag02 = $('.b_B');
var tag03 = $('.c_C');
var tag04 = $('.d_D');
// console.log($.cookie("name2"));
// console.log("旧的"+thisid);

function charu_A() {
	var A_html = '<div class="A_A">' + A_A + '</div>\
		<div class="jia">'
			+ jia + '</div>';
	$('.pingji').append(A_html);
}

// 插入学校综合评级
function grade_charuA(data) {
	A_A = data.gradeInfo.grade;
	if (A_A.length > 1)
		jia = "+";
	charu_A();
	for (i = 0; i < arr_grade.length; i++) {
		if (arr_grade[i] == A_A) {

			$('.grade').eq(i).find('span').css('color', '#FFAF68');
		}
	}
}
// 插入标签A+
var A_A = null;
var jia = "";

var four_grade = null;

// 插入四个评级标签
function four_Grade(four_grade, tag_grade) {
	if (four_grade == "D") {
		bar_value1 = '25%';
		tag_grade.css('width', bar_value1);
		tag_grade.find('span').html(four_grade);
	} else if (four_grade == "B") {
		bar_value1 = '75%';
		tag_grade.css('width', bar_value1);
		tag_grade.find('span').html(four_grade);
	} else if (four_grade == "C") {
		bar_value1 = '50%';
		tag_grade.css('width', bar_value1);
		tag_grade.find('span').html(four_grade);
	} else {
		bar_value1 = '100%';
		tag_grade.css('width', bar_value1);
		tag_grade.find('span').html(four_grade);
	}
}

var Prompt_html = '<p>专业评级</p>'
$('.nav02_item').append(Prompt_html);
// 抛物线横坐标值显示
// 插入排名标签
var rank_tag1 = $('.rank_tp1');
var rank_tag2 = $('.rank_tp2');
var rank_tag3 = $('.rank_tp3');
var rank_item1 = '专业排名';
var rank_item2 = '院校全国排名';
var rank_item3 = '院校省内排名';
function ranking_charu(rank_value, rank_tag, rank_item) {
	if (rank_value == -1) {
		$('.rank_tp1 .rank_p').css('font-size', '30px');
		rank_value = '不参与排名';
	}
	var ranking = '<p class="rank_p">' + rank_value + '</p><p class="pai">'
			+ rank_item + '</p>';
	rank_tag.append(ranking);
	var length0 = rank_tag.find('.rank_p').text().length;
	if (length0 > 4) {
		rank_tag.find('.rank_p').css('font-size', '30px');
	} else {
		rank_tag.find('.rank_p').css('font-size', '70px');
	}
}
var strA = null;
// alert(strA.length);
var grade_A = $('.grade').eq(4).find('.grade1').text();
var grade_and = $('.grade').eq(4).find('.and').text();

var grade_B = $('.grade').eq(2).find('.grade3').text();
var gradeB_and = $('.grade').eq(2).find('.and').text();

var grade_Aand_01 = grade_A + grade_and;
var grade_Aand_02 = $('.grade2').text();
var grade_Band_03 = grade_B + gradeB_and;
var grade_Band_04 = $('.grade4').text();
var grade_Cand_05 = $('.grade5').text();

var arr_grade = new Array(grade_Cand_05, grade_Band_04, grade_Band_03,
		grade_Aand_02, grade_Aand_01);
// 得出四年分数中的最大数
function max_score_find() {
	var max_score = 0;
	for (var i = 0; i < score.length; i++) {
		if (max_score < score[i])
			max_score = score[i];
	}
	return max_score;
}

// 折线使用
function line_0() {
	require([ 'echarts', 'echarts/chart/line' ],// 使用折现图就加载line模块，按需加载
	function(echarts) {
		// 折现图
		var myChart = echarts.init(document.getElementById('main2'));
		var option = {
			legend : {
				data : [ '初试平均分' ]
			},
			tooltip : {
				trigger : 'item',
				show : true
			},
			xAxis : [ {
				type : 'category',
				// data:[year01, year02, year03, year04],
				data : year,
				name : '时间'
			}, ],
			yAxis : [ {
				type : 'value',
				name : '分数',
				min : score_min - 10,
				max : score_max + 15
			} ],
			series : [ {
				name : '平均分',
				type : 'line',
				itemStyle : {
					normal : {
						color : function(params) {
							var colorList = [ '#5caede', '#5caede', '#5caede',
									'#5caede' ];
							return colorList[params.dataIndex]
						}

					}
				},
				// data:[score01, score02, score03,score04]
				data : score
			}

			]

		}
		myChart.setOption(option);

	});
}

// 插入平平均分
var score_avg = "", score_lowyear = "", score_trend = "";
var year = new Array();
var score = new Array();

function score_tag() {
	$('.average_h').text(score_avg + "分");
	$('.average_y').text(score_lowyear);
	$('.average_l').text(score_min + "分");
	$('.average_w').text(score_trend);
}

// 竞争力分析
var score_0 = "";
$('.score0').text(score_0);
var width_value = null;

var a5_tag = $('.a_A_5');
var b5_tag = $('.b_B_5');
var c5_tag = $('.c_C_5');
var d5_tag = $('.d_D_5');

var dengji_tag1 = $('.dengji1');
var dengji_tag2 = $('.dengji2');
var dengji_tag3 = $('.dengji3');
var dengji_tag4 = $('.dengji4');

var rateDegree = '';
var scoreDegree = '';
var collegeDegree = '';
var cityDegree = '';
function compet(three_compet, bar_tag, dengji) {
	if (three_compet == "高") {
		width_value = '100%';
		bar_tag.css('width', width_value);
		dengji.text(three_compet);
	} else if (three_compet == "中") {
		width_value = '60%';
		bar_tag.css('width', width_value);
		dengji.text(three_compet);
	} else {
		width_value = '20%';
		bar_tag.css('width', width_value);
		dengji.text(three_compet);
	}
	;
}

// 近三年报录比均值
function applyAdmit(rate_percent, des) {
	var li_num = Math.ceil(rate_percent / 10);
	$('.percent0' + li_num).css('display', 'block');
	$('.percents0' + li_num).text(rate_percent + '%');
	$('.li_desg0' + li_num).css('display', 'block');
	$('.li_desg0' + li_num + ' p').text(des);

}
// 报录比解释标签
function applyexceed(applyCount, admitCount, exemptionCount, applyDescription,
		admitDescription) {
	if (applyCount == '' || applyCount == '-1') {
		$('.baok').text('?');
	} else {
		$('.baok').text(applyCount);
	}

	if (admitCount == '' || admitCount == '-1') {
		$('.luqu').text('?');
	} else {
		$('.luqu').text(admitCount);
	}

	if (exemptionCount == '-1') {
		$('.bao').text('?');
	} else {
		$('.bao').text(exemptionCount);
	}

	if (applyDescription == '') {
		$('.more').text('?');
	} else {
		$('.more').text(applyDescription);
	}

	if (admitDescription == '') {
		$('.Less').text('?');
	} else {
		$('.Less').text(admitDescription);
	}

}

// 柱形图

// var column_height='';
// var bar_index='0';
// var majorname='';
// 定义存储专业id的数组
var major_bar = new Array();
var maininfo = '';

function columnchart(bar_index, column_height, majorname, bar_color, schoolname) {
	if (column_height >= 100) {
		$('.bar_cha' + (bar_index + 1)).css('height', 100 * 1.5 + 'px');
		$('.occup' + (bar_index + 1)).css('height', 100 * 1.5 + 'px');
		$('.value' + (bar_index + 1)).text('≥100%');
		$('.course' + (bar_index + 1)).find('a').text(majorname);
		$('.course_full' + (bar_index + 1)).find('a').text(majorname);
		$('.bar_cha' + (bar_index + 1)).css('background-color', '#cbcbcb');
		$('.value' + (bar_index + 1)).css('color', '#cbcbcb');

	} else if (column_height == '-1') {
		$('.bar_cha' + (bar_index + 1)).css('height', 10 * 1.5 + 'px');
		$('.occup' + (bar_index + 1)).css('height', 10 * 1.5 + 'px');
		$('.value' + (bar_index + 1)).text('未知');
		$('.course' + (bar_index + 1)).find('a').text(majorname);
		$('.course_full' + (bar_index + 1)).find('a').text(majorname);
		$('.bar_cha' + (bar_index + 1)).css('background-color', '#cbcbcb');
		$('.value' + (bar_index + 1)).css('color', '#cbcbcb');
	} else {
		$('.bar_cha' + (bar_index + 1)).css('height',
				column_height * 1.5 + 'px');
		$('.occup' + (bar_index + 1)).css('height', column_height * 1.5 + 'px');
		$('.value' + (bar_index + 1)).text(column_height + '%');
		$('.course' + (bar_index + 1)).find('a').text(majorname);
		$('.course_full' + (bar_index + 1)).find('a').text(majorname);

		if (bar_color == '2') {
			$('.bar_cha' + (bar_index + 1)).css('background-color', '#72D4BD');
			$('.value' + (bar_index + 1)).css('color', '#72D4BD');
		} else if (bar_color == '1') {
			$('.bar_cha' + (bar_index + 1)).css('background-color', '#B4D055');
			$('.value' + (bar_index + 1)).css('color', '#B4D055');
		} else if (bar_color == '0') {
			$('.bar_cha' + (bar_index + 1)).css('background-color', '#FFAF68');
			$('.value' + (bar_index + 1)).css('color', '#FFAF68');
		} else {
			$('.bar_cha' + (bar_index + 1)).css('background-color', '#5DB8E4');
			$('.value' + (bar_index + 1)).css('color', '#5DB8E4');
		}

	}
	// 柱形图专业名点击函数
	$('.course' + (bar_index + 1)).click(function(event) {
		bar_click(bar_index);
		// 每点击一次将专业信息存储进cookie
		cookie_click(bar_index, majorname, schoolname);

	});
	$('.course_full' + (bar_index + 1)).click(function(event) {
		bar_click(bar_index);
		// 每点击一次将专业信息存储进cookie
		cookie_click(bar_index, majorname, schoolname);
	});

}

function bar_tag(index_bartag, bartag_num) {
	$('.sim_num' + index_bartag).text(bartag_num);
}

// 柱形图专业名点击函数

function bar_click(i) {
	window.open("info.html?major=" + major_bar[i], "_blank");
}

// 每点击一次将专业信息存储进cookie
function cookie_click(bar_index, majorname, schoolname) {
	// thisid=major_bar[bar_index];
	// var majorname_para=majorname;
	// var schname_para=schoolname;
	// $.cookie("name2", majorname_para,{path:"/"});
	// $.cookie("range", schname_para,{path:"/"});
	// var href_id=$.cookie("href_id");
	// var href_majnam=$.cookie("majorname");
	// var href_schnam=$.cookie("schoolname");

}

// 感兴趣的专业

function insert_major(major_index, major_name, collage_name, exam_degree,
		maior_id) {
	$('.major0 li').eq(major_index).find('.zy_name' + (major_index + 1)).text(
			major_name);
	$('.major0 li').eq(major_index).find('.yuanxi' + (major_index + 1)).text(
			collage_name);
	$('.major0 li').eq(major_index).find('.nandu' + (major_index + 1)).text(
			exam_degree);
	// 点击专业跳转
	$('.major0 li').eq(major_index).click(function(event) {
		thisid = maior_id;
		// $.cookie("thisid", thisid,{path:"/"});
		window.open("info.html?major=" + thisid, "_blank");
	});
}
// 感兴趣的学校
var collegename = '';
var collegeleve = '';
var collegerank = '';
function insert_college(college_index, college_name, college_leve,
		college_rank, college_id) {
	$('.college0 li').eq(college_index).find('.xx_name' + (college_index + 1))
			.text(college_name);
	if (college_leve == 'null') {
		$('.college0 li').eq(college_index).find('.cci_' + (college_index + 1))
				.text('普通');
	} else {
		$('.college0 li').eq(college_index).find('.cci_' + (college_index + 1))
				.text(college_leve);
	}

	$('.college0 li').eq(college_index).find('.pai_' + (college_index + 1))
			.text(college_rank);
	// 点击学校跳转
	$('.college0 li').eq(college_index).click(function(event) {
		thiscid = college_id;
		collegename = college_name;
		collegeleve = college_leve;
		collegerank = college_rank;
		window.open("school.html?name=" + thiscid, "_blank");

	});
}

//热门问答
function insert_question(question_index, question_id, question_content){
	$('.question li').eq(question_index).find('.question' + (question_index + 1)).text(
			question_content);
	// 点击专业跳转
	$('.question li').eq(question_index).click(function(event) {
		thisId = question_id;
		// $.cookie("thisid", thisid,{path:"/"});
		window.open("http://dada.itui.cn/?/question/" + thisId, "_blank");
	});
}

// 点击关注按钮
// 关注按钮自定义函数开始
// 关注该学校
function atten() {
	$('.image2').css('display', 'block');
	$('.image4').css('display', 'none');
	// $('.gz_sch1').text('关注该专业');
	$('.gz_sch1').text('加入对比');
}
// 取消关注
function cancel_atten() {
	$('.image4').css('display', 'block');
	$('.image2').css('display', 'none');
	// $('.gz_sch1').text('取消关注');
	$('.gz_sch1').text('取消对比');
}
// 判断用户是否关注了当前院校
function Attention() {
	if (followid == -1) {
		atten();

	} else {
		cancel_atten();
	}
	$('.image2').click(
			function(event) {
				// attention_ajax();
				addCompareMajor(thisid, A_A, major_rank, college_rank, score_0,
						apply_admit_rate, degree_description);
			});
	$('.image4').click(function(event) {
		// cancelattention_ajax(followid);
		delCompareMajor(thisid);
	});
}

// 关注按钮自定义函数结束

// 如果点击关注将学校id发送给服务器 关注发送
function attention_ajax() {
	$.ajax({
		url : 'followmajor.html',
		type : 'post',
		dataType : 'html',
		data : {
			'mid' : thisid,
			'code' : $.cookie('user')
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (data.status == 0) {
				cancel_atten();
			} else {
				// 404错误页面
				var err_msg = data.errMessage;
				if (err_msg !== '请先登录！') {
					$.cookie("err_msg", err_msg, {
						path : "/"
					});
					location.href = "error.html";
				} else {
					console.log(err_msg);
				}
			}
		}
	});
}

// 如果点击关注将学校id发送给服务器 取消关注发送
function cancelattention_ajax() {
	$.ajax({
		url : 'disfollowmajor.html',
		type : 'post',
		dataType : 'html',
		data : {
			'mid' : thisid,
			'code' : $.cookie('user')
		},
		success : function(msg) {
			var data = eval('msg=' + msg);
			if (data.status == 0) {
				atten();
			} else {
				// 404错误页面
				var err_msg = data.errMessage;
				if (err_msg !== '请先登录！') {
					$.cookie("err_msg", err_msg, {
						path : "/"
					});
					location.href = "error.html";
				} else {
					console.log(err_msg);
				}
			}
		}
	});
}

// 关注自定义函数结束
// 20150206编辑
// 动态加载css加载
function Fuzzy() {

	$("<link>").attr({
		rel : "stylesheet",
		type : "text/css",
		href : "css/base.css"
	}).appendTo("head");
}

// Fuzzy();

// 20150210王倩编辑
// 竞争分析提示
$('.bar0_5').mouseenter(function(event) {
	var index = $(this).index();
	$('.tip0' + (index + 1)).css('display', 'block');

}).mouseleave(function(event) {
	var index = $(this).index();
	$('.tip0' + (index + 1)).css('display', 'none');
});
// 报录比提示
$('.bar0').mouseenter(function(event) {
	var index = $(this).index();
	$('.tip_p0' + (index)).css('display', 'block');

}).mouseleave(function(event) {
	var index = $(this).index();
	$('.tip_p0' + index).css('display', 'none');

});

// 015/03/30编辑
// $('.li_trip').css('display','none');
$('.Ranking li').mouseenter(function() {
	$(this).children('.li_trip').css('display', 'block');
	$(this).siblings('li').children('.li_trip').css('display', 'none');

}).mouseleave(function() {
	$(this).children('.li_trip').css('display', 'none');
});

/**
 * 以下皆为专业对比相关
 */

// $(document).ready(function(){
// $(window).scroll(function (){
// var offsetTop = 30+ $(window).scrollTop() +"px";
// $("#compare").animate({top : offsetTop },{ duration:600 , queue:false });
// });
// });
// unicode 编码程序
function basketEncodeCookie(inputString) {
	/*
	 * var strRtn=""; for (var i=InputString.length-1;i>=0;i--) {
	 * strRtn+=InputString.charCodeAt(i); if (i) strRtn+="a"; // 用 a 作分隔符 }
	 * return strRtn;
	 */
	return inputString;
}

// unicode 解码程序
function basketDecodeCookie(inputString) {
	/*
	 * var strArr; var strRtn="";
	 * 
	 * strArr=InputString.split("a");
	 * 
	 * for (var i=strArr.length-1;i>=0;i--)
	 * strRtn+=String.fromCharCode(eval(strArr[i]));
	 * 
	 * return strRtn;
	 */
	return inputString;
}

// 读 Cookie
function basketGetCookie(name) {
	var strArg = name + "=";
	var nArgLen = strArg.length;
	var nCookieLen = document.cookie.length;
	var nEnd;
	var i = 0;
	var j;

	while (i < nCookieLen) {
		j = i + nArgLen;
		if (document.cookie.substring(i, j) == strArg) {
			nEnd = document.cookie.indexOf(";", j);
			if (nEnd == -1)
				nEnd = document.cookie.length;
			return basketDecodeCookie(unescape(document.cookie.substring(j,
					nEnd)));
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return null;
}

// 写 Cookie
function basketSetCookie(name, value, expires) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expires * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(basketEncodeCookie(value))
			+ ";expires=" + exp.toGMTString() + ";path=/";
}

// 判断 Cookie 是否存在并写产品对比 Cookie
function basketCheckSetCookieValue(name, value, expires) {
	var nameCookieValue = basketGetCookie(name);
	if ((nameCookieValue == "") || (nameCookieValue == null)) {
		var exp = new Date();
		exp.setTime(exp.getTime() + expires * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(basketEncodeCookie(value))
				+ ";expires=" + exp.toGMTString() + ";path=/";
	} else {
		if (nameCookieValue.indexOf(value) == -1) {
			var arrCookies = nameCookieValue.split("∈");
			var ValueNum = arrCookies.length;
			if (ValueNum > 9) {
				nameCookieValue = "";
				for (loop = 0; loop < 9; loop++) {
					nameCookieValue += arrCookies[loop] + "∈";
				}
				nameCookieValue = nameCookieValue.substring(0,
						nameCookieValue.length - 1);
			}

			var exp = new Date();
			exp.setTime(exp.getTime() + expires * 60 * 60 * 1000);
			document.cookie = name + "="
					+ escape(basketEncodeCookie(value + "∈" + nameCookieValue))
					+ ";expires=" + exp.toGMTString() + ";path=/";
		}
	}
}

function compareHide() {
	obj = document.getElementById("compare");
	obj.style.display = 'none';
}

function compareShow() {
	obj = document.getElementById("compare");
	obj.style.display = 'block';
}

function empty() {
	basketSetCookie("POP_SubCategory", "", 4);
	basketSetCookie("POP_CompareMajors", "", 4);
	redraw();
	compareHide();
	atten();
}

function addCompareMajor(majorId, majorLevel, major_rank, college_rank,
		difficulty, apply_admit_rate, degree_description) {
	var compareMajors = basketGetCookie("POP_CompareMajors");
	if ((compareMajors != "") && (compareMajors != null)) {
		var arrCookies = compareMajors.split("∈");
		if (arrCookies.length < 3) {
			if (compareMajors.indexOf(majorId) != -1) {
				alert("（" + majorId + "）已经被选择了！");
			} else {
				basketCheckSetCookieValue("POP_CompareMajors", majorId + "∑"
						+ majorLevel + "∑" + major_rank + "∑" + college_rank
						+ "∑" + difficulty + "∑" + apply_admit_rate + "∑"
						+ degree_description, 4);
			}
		} else {
			var YESORNO = confirm("对不起，您只能选择三个专业进行对比，是否清除所有已选专业？");
			if (YESORNO) {
				empty();
			}
		}
	} else {
		basketCheckSetCookieValue("POP_CompareMajors", majorId + "∑"
				+ majorLevel + "∑" + major_rank + "∑" + college_rank + "∑"
				+ difficulty + "∑" + apply_admit_rate + "∑"
				+ degree_description, 4);
	}
	redraw();
	showLayer();
	cancel_atten();
}

function delCompareMajor(majorId) {
	var nameCookieValue = basketGetCookie("POP_CompareMajors");
	if ((nameCookieValue != null) && (nameCookieValue != "")) {
		if (nameCookieValue.indexOf(majorId) > -1) {
			var arrCookies = nameCookieValue.split("∈");
			var valueNum = arrCookies.length;
			nameCookieValue = "";
			for (i = 0; i < valueNum; i++) {
				if (arrCookies[i].indexOf(majorId) == -1) {
					nameCookieValue += arrCookies[i] + "∈";
				}
			}
			nameCookieValue = nameCookieValue.substring(0,
					nameCookieValue.length - 1);
			basketSetCookie("POP_CompareMajors", nameCookieValue, 4);
		}
		redraw();
		atten();
	}
}

function redraw() {
	// FixPosition();
	var compareMajors = basketGetCookie("POP_CompareMajors");
	if (compareMajors == null) {
		compareMajors = "";
	}
	var arrCookies = compareMajors.split("∈");
	var mylength = 0;
	if (arrCookies.length > 0 && (arrCookies[0] != null)
			&& (arrCookies[0] != "")) {
		mylength = arrCookies.length;
	}
	var compareTable = "";
	compareTable = "<div class=db>"
			+ "<div class=db1>"
			+ "<div class=db11>["
			+ mylength
			+ "/3]专业对比栏</div>"
			+ "<div class=db12><a onClick='empty()' style='color:blue;cursor:pointer'>清空</a></div>"
			+ "<div class=db13><a onClick='hideLayer()' style='color:blue;cursor:pointer'>隐藏</a></div></div>"
			+ "<div class=db2><dl><dt class=majortitles>专业名称</dt><dd class=titles>专业评级</dd>"
			+ "<dd class=titles>专业排名</dd><dd class=titles>院校全国排名</dd>"
			+ "<dd class=titles>报考难度</dd><dd class=titles>近三年录取率均值</dd><dd class=deltitle></dd>";
	for (i = mylength - 1; i >= 0; i--) {
		if ((arrCookies[i] != "") && (arrCookies[i] != null)) {
			var majorInfo = arrCookies[i].split("∑");
			if (majorInfo.length == 7) {
				var majorId = majorInfo[0];
				var majorLevel = majorInfo[1];
				var majorRank = majorInfo[2];
				if (majorRank == -1) {
					majorRank = "不参与排名";
				}
				console.log(majorInfo[2] + "...");
				var collegeRank = majorInfo[3];
				if (collegeRank == -1) {
					college = "不参与排名";
				}
				var difficulty = majorInfo[4];
				var applyAdmitRate = majorInfo[5];
				if (applyAdmitRate == -1) {
					applyAdmitRate = "暂无数据";
				} else {
					applyAdmitRate = applyAdmitRate + "%";
				}
				var degree_description = majorInfo[6];
				var majorURL = "http://www.itui.cn/info.html?major=" + majorId;
				compareTable += "<dt class=comparemajorname><a target=_blank href='"
						+ majorURL
						+ "'title='"
						+ majorId
						+ "'>"
						+ cutStr(majorId, 32)
						+ "</a></dt><dd class=comparemajor>"
						+ majorLevel
						+ "</dd><dd class=comparemajor>"
						+ majorRank
						+ "</dd><dd class=comparemajor>"
						+ collegeRank
						+ "</dd><dd class=comparemajor>"
						+ difficulty
						+ "/"
						+ degree_description
						+ "</dd><dd class=comparemajor>"
						+ applyAdmitRate
						+ "</dd><dd class=delcomparemajor><a style='color:blue;cursor:pointer' onClick=\"delCompareMajor('"
						+ majorId + "')\">删除</a></dd>";
			}
		}
	}
	// compareTable += "</dl><div class=db4><input type=button value=进行对比
	// onClick=window.open('http://www.163css.com') /></div><div class=db5><a
	// style='color:blue;cursor:pointer'
	// onClick='empty()'>清空对比栏</a></div></div>";
	// compareTable += "</dl><div class=db3><input type=button class=btn-empty
	// style='font-size:14px' value=清空对比栏 onClick='empty()'></div>";
	// compareTable += "</dl><div class=db3><button type=button class=btn-empty
	// onClick='empty()'>清空对比栏</button></div>";
	compareTable += "</dl></div>"
	jQuery('#compare').html(compareTable);
	// $(".db .db2").mouseover(function() {
	// this.className = 'db2r';
	// $(this).children().children('.db33').compareShow();
	// });
	// $(".db .db2").mouseout(function() {
	// this.className = 'db2';
	// $(this).children().children('.db33').compareHide();
	// });
}

function isNumber(inputVal) {
	var inputStr = inputVal.toString();
	var i = 0;
	for (i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.charAt(i)
		if (oneChar < "0" || oneChar > "9") {
			return false;
		}
	}
	return true;
}

function compareCheck() {
	var URL = "/";
	var compareProductsValue = basketGetCookie("POP_CompareMajors");
	var b = "00283";
	if ((compareProductsValue != "") && (compareProductsValue != null)) {
		var arrValues = compareProductsValue.split("∈");
		var valuesNum = arrValues.length;
		if (valuesNum < 2) {
			alert('请至少选择两款同类别产品进行对比！');
			return;
		} else {
			var isTwo = false;
			// if (ValuesNum == 2)//2款产品比较跳转至PK页{//isTwo = true;}
			var subCategorySN = basketGetCookie("POP_SubCategory");
			if ((subCategorySN != "") && (subCategorySN != null)) {
				if (isNumber(subCategorySN)) {
					var queryString = "";
					if (isTwo) {
						queryString = "";
					}
					for (i = 0; i < valuesNum; i++) {
						var majorInfo = arrValues[valuesNum - 1 - i].split("Σ");
						if (isNumber(majorInfo[0])) {
							queryString += majorInfo[0] + "_";

						}
						if (i == valuesNum - 1) {
							if (arrValues[i].split("Σ")[3] != "undefined")
								b = arrValues[i].split("Σ")[3];
						}
					}
					queryString = queryString.substring(0,
							queryString.length - 1);
					window.open(URL + queryString.replace("{brandsn}", b)
							+ "/Detail.html");

				}
			}
		}
	}
	empty();
}

redraw();

function hideLayer() {
	basketSetCookie("POP_HiddenCompare", "Hide", 4);
	compareHide();
}

function showLayer() {
	basketSetCookie("POP_HiddenCompare", "Show", 4);
	compareShow();
}

if ((basketGetCookie("POP_HiddenCompare") == "Hide")
		|| (basketGetCookie("POP_CompareMajors") == "")
		|| (basketGetCookie("POP_CompareMajors") == null)) {
	hideLayer();
} else {
	showLayer();
}

function cutStr(str, length) {
	var a = str.replace(/([\u0391-\uffe5])/ig, '$1a');
	var b = a.substring(0, length);
	var c = b.replace(/([\u0391-\uffe5])a/ig, '$1');
	return c;
}
