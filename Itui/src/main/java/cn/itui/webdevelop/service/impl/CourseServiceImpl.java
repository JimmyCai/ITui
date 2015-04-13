package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.dao.CourseDao;
import cn.itui.webdevelop.dao.StatsDao;
import cn.itui.webdevelop.model.Course;
import cn.itui.webdevelop.model.Stats;
import cn.itui.webdevelop.service.CourseService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;

public class CourseServiceImpl implements CourseService {
	private CourseDao courseDao;
	private StatsDao statsDao;
	
	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public StatsDao getStatsDao() {
		return statsDao;
	}

	public void setStatsDao(StatsDao statsDao) {
		this.statsDao = statsDao;
	}

	public String getAllCourse() throws Exception {
		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量

		List<HashMap<String, Object>> allCourseInfo = courseDao
				.getAllCourseInfo();
		if (allCourseInfo == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		int i =0;
		while (i<allCourseInfo.size()) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
			teacherInfo.put("teacherId", EnDeCode.encodePara((Integer) allCourseInfo.get(i).get("teacherId")));
			teacherInfo.put("teacherName", allCourseInfo.get(i).get("teacherName"));
			teacherInfo.put("photo", allCourseInfo.get(i).get("photo"));
			teacherInfo.put("orgName", allCourseInfo.get(i).get("orgName"));
			teacherInfo.put("orgWeb", allCourseInfo.get(i).get("orgWeb"));
			
			HashMap<String, Object> courseInfo = new HashMap<String, Object>();
			courseInfo.put("dateType", Course.getDateType(allCourseInfo.get(i).get("startDay"), allCourseInfo.get(i).get("endDay")));
			courseInfo.put("summary", allCourseInfo.get(i).get("summary"));
			courseInfo.put("startDay", allCourseInfo.get(i).get("startDay"));
			courseInfo.put("startTime", allCourseInfo.get(i).get("startTime"));
			courseInfo.put("endDay", allCourseInfo.get(i).get("endDay"));
			courseInfo.put("endTime", allCourseInfo.get(i).get("endTime"));
			courseInfo.put("lesson", allCourseInfo.get(i).get("lesson"));
			courseInfo.put("platform", allCourseInfo.get(i).get("platform"));
			courseInfo.put("platformWeb", allCourseInfo.get(i).get("platformWeb"));
			courseInfo.put("liveSrc", allCourseInfo.get(i).get("liveSrc"));
			courseInfo.put("price", allCourseInfo.get(i).get("price"));
			courseInfo.put("tag", allCourseInfo.get(i).get("tag"));
			
			resultMap.put("teacherInfo", teacherInfo);
			resultMap.put("courseInfo", courseInfo);
			
			resultList.add(resultMap);
			i++;
		}
		System.out.println(resultList);
		// build json string
		String jsonResult = buildCourseJson(resultList);
		return jsonResult;
	}

	private String buildCourseJson(List<HashMap<String, Object>> resultList)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("courseList", resultList);
		System.out.println(jsonMap);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		System.out.println(jsonStr);
		return jsonStr;
	}

}
