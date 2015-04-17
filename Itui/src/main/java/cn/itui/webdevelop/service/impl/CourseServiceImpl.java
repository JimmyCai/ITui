package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.itui.webdevelop.dao.CourseDao;
import cn.itui.webdevelop.dao.StatsDao;
import cn.itui.webdevelop.model.Course;
import cn.itui.webdevelop.model.Stats;
import cn.itui.webdevelop.service.CourseService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.SaveFile;
import cn.itui.webdevelop.utils.ValidateFile;
import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.exception.FileUploadException;

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
	/**
	 * 服务于直播课页面
	 * 获取所有未结束直播课程信息
	 */
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
		// build json string
		String jsonResult = buildCourseJson(resultList);
		return jsonResult;
	}

	private String buildCourseJson(List<HashMap<String, Object>> resultList)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("courseList", resultList);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}
	/**
	 * 服务于发布直播课程页面
	 * 根据登录的code判断是否有发布权限
	 * @param code
	 */
	public int verifyPermissionByCode(String code) {
		return courseDao.verifyPermissionByCode(code);
	}
	/**
	 * 服务于发布新直播课程页面
	 * 根据添加的课程信息，在数据库直播课程表中增加记录
	 * @param courseInfo
	 */
	public String addNewCourse(String courseInfo) throws Exception {
		System.out.println(courseInfo);
		HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
//		teacherInfo.put("photoName", resolveCourseJson(courseInfo).getPhotoName());
		teacherInfo.put("photoName", "789.png");
		System.out.println(resolveCourseJson(courseInfo).getTeacherName());
		teacherInfo.put("teacherName", resolveCourseJson(courseInfo).getTeacherName());
		teacherInfo.put("org", resolveCourseJson(courseInfo).getOrg());
		teacherInfo.put("orgWeb", resolveCourseJson(courseInfo).getOrgWeb());
		//将teacher信息添加到teacher表中
		int teacherId = courseDao.insertTeacherInfo(teacherInfo);
		
		HashMap<String, Object> courseInfos = new HashMap<String, Object>();
		courseInfos.put("summary", resolveCourseJson(courseInfo).getSummary());
		courseInfos.put("price", resolveCourseJson(courseInfo).getPrice());
		courseInfos.put("startDay", resolveCourseJson(courseInfo).getStartDay());
		courseInfos.put("startTime", resolveCourseJson(courseInfo).getStartTime());
		courseInfos.put("endDay", resolveCourseJson(courseInfo).getEndDay());
		courseInfos.put("endTime", resolveCourseJson(courseInfo).getEndTime());
		courseInfos.put("lesson", resolveCourseJson(courseInfo).getLesson());
		courseInfos.put("platform", resolveCourseJson(courseInfo).getPlatform());
		courseInfos.put("platformWeb", resolveCourseJson(courseInfo).getPlatformWeb());
		courseInfos.put("liveSrc", resolveCourseJson(courseInfo).getLiveSrc());
		courseInfos.put("tag", resolveCourseJson(courseInfo).getTag());
//		int courseId = courseDao.insertCourseInfo(courseInfos);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		//不添加老师头像，使用默认头像
		if(teacherId>0){
			courseInfos.put("teacherId", teacherId);
			int courseId = courseDao.insertCourseInfo(courseInfos);
			if(courseId>0){
				System.out.println("NEW TEACHER ID : "+teacherId);
				System.out.println("NEW COURSE ID :"+courseId);
				resultMap.put("release", "课程信息添加成功！");
			}
			else {
				resultMap.put("release", "课程信息添加失败！");
			}
		}
//		//已经添加新课程老师头像 在teacher表中创建了新的记录
//		else if (courseId==lastTeacherId) {
//			teacherInfo.put("teacherId", lastTeacherId);
//			int teacherId = courseDao.updateTeacherInfo(teacherInfo);
//			if(teacherId>0){
//				System.out.println("NEW TEACHER ID : "+teacherId);
//				System.out.println("NEW COURSE ID :"+courseId);
//				resultMap.put("release", "课程信息添加成功！");
//			}
//			else {
//				resultMap.put("release", "教师信息添加失败！");
//			}
//		}
		else {
			resultMap.put("release", "课程信息添加失败！");
		}
		// build json string
		String jsonResult = buildCourseReleaseJson(resultMap);
		return jsonResult;
	}
	
	//Gson解析Json格式数据
	private Course resolveCourseJson(String courseInfo){
		Gson gson = new Gson();
		Course course = gson.fromJson(courseInfo, new TypeToken<Course>(){}.getType());
		return course;
	}
	
	private String buildCourseReleaseJson(HashMap<String, Object> resultMap)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("releaseInfo", resultMap);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}
	/**
	 * 服务于发布新直播课程页面
	 * 根据接收到的头像文件，在数据库中进行存储
	 * @param photo
	 */
	public String uploadPhoto(MultipartFile photo) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		//校验文件格式是否正确
		if (ValidateFile.validateImage(photo)>0) {
//			newCourseId = lastTeacherId+1;
			//
			int newCourseId = courseDao.getLastId();
			String photoName = (10000001 +newCourseId)+".png";
			//存储图片到指定路径
			SaveFile.saveImage(photoName, photo);
			//在teacher表中添加教师头像信息
//			newTeacherId = courseDao.insertTeacherPhoto(photoName);
			//判断图片是否正确插入
			resultMap.put("photoName", photoName);
			resultMap.put("upload", "头像上传成功！");
		}
		else {
			resultMap.put("upload", "头像上传失败！请校验头像图片格式是否为jpg、jpeg、png,图片大小不超过800k！");
		}
		String jsonResult = buildUploadPhotoJson(resultMap);
		return jsonResult;
	}
	
	private String buildUploadPhotoJson(HashMap<String, Object> resultMap)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("uploadInfo", resultMap);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}


}
