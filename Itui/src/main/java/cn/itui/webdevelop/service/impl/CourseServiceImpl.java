package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.DigestUtils;
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

public class CourseServiceImpl implements CourseService {
	private CourseDao courseDao;
	private StatsDao statsDao;
	private Gson gson;
	private Course course;

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

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 服务于直播课页面 获取所有未结束直播课程信息
	 */
	public String findAllAliveCourse() throws Exception {
		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量

		List<HashMap<String, Object>> allAliveCourseInfo = courseDao
				.getAllAliveCourseInfo();
		if (allAliveCourseInfo == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while (i < allAliveCourseInfo.size()) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
			teacherInfo.put("teacherId", EnDeCode
					.encodePara((Integer) allAliveCourseInfo.get(i).get(
							"teacherId")));
			teacherInfo.put("teacherName",
					allAliveCourseInfo.get(i).get("teacherName"));
			teacherInfo.put("photo", allAliveCourseInfo.get(i).get("photo"));
			teacherInfo
					.put("orgName", allAliveCourseInfo.get(i).get("orgName"));
			teacherInfo.put("orgWeb", allAliveCourseInfo.get(i).get("orgWeb"));
			teacherInfo.put("teacherCode", allAliveCourseInfo.get(i).get("teacherCode"));

			HashMap<String, Object> courseInfo = new HashMap<String, Object>();
			courseInfo.put("courseId", allAliveCourseInfo.get(i).get("courseId"));
			courseInfo.put("dateType", Course.getDateType(allAliveCourseInfo
					.get(i).get("startDay"),
					allAliveCourseInfo.get(i).get("endDay")));
			courseInfo.put("summary", allAliveCourseInfo.get(i).get("summary"));
			courseInfo.put("startDay", allAliveCourseInfo.get(i)
					.get("startDay"));
			courseInfo.put("startTime",
					allAliveCourseInfo.get(i).get("startTime"));
			courseInfo.put("endDay", allAliveCourseInfo.get(i).get("endDay"));
			courseInfo.put("endTime", allAliveCourseInfo.get(i).get("endTime"));
			courseInfo.put("lesson", allAliveCourseInfo.get(i).get("lesson"));
			courseInfo.put("platform", allAliveCourseInfo.get(i)
					.get("platform"));
			courseInfo.put("platformWeb",
					allAliveCourseInfo.get(i).get("platformWeb"));
			courseInfo.put("liveSrc", allAliveCourseInfo.get(i).get("liveSrc"));
			courseInfo.put("price", allAliveCourseInfo.get(i).get("price"));
			courseInfo.put("tag", allAliveCourseInfo.get(i).get("tag"));
			courseInfo.put("courseCode", allAliveCourseInfo.get(i).get("courseCode"));

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
	 * 服务于发布直播课程页面 根据登录的code判断是否有发布权限
	 * 
	 * @param code
	 */
	public int verifyPermissionByCode(String code) {
		return courseDao.verifyPermissionByCode(code);
	}

	/**
	 * 服务于发布新直播课程页面 根据添加的课程信息，在数据库直播课程表中增加记录
	 * 
	 * @param courseInfo
	 */
	public String addNewCourse(String courseInfo) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String teacherName = parseCourseJson(courseInfo).getTeacherName();
		String photoName = parseCourseJson(courseInfo).getPhotoName();
		// String photoName = "default.png";
		String org = parseCourseJson(courseInfo).getOrg();
		String orgWeb = parseCourseJson(courseInfo).getOrgWeb();
		// 生成teacher code 保证教师信息唯一性
		String teacherCode = DigestUtils
				.md5DigestAsHex((teacherName + org + orgWeb).getBytes());

		// HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
		// teacherInfo.put("photoName", "default.png");
		// teacherInfo.put("photoName", photoName);
		// teacherInfo.put("teacherName", teacherName);
		// teacherInfo.put("org", org);
		// teacherInfo.put("orgWeb", orgWeb);

		String summary = parseCourseJson(courseInfo).getSummary();
		int price = parseCourseJson(courseInfo).getPrice();
		String startDay = parseCourseJson(courseInfo).getStartDay();
		String startTime = parseCourseJson(courseInfo).getStartTime();
		String endDay = parseCourseJson(courseInfo).getEndDay();
		String endTime = parseCourseJson(courseInfo).getEndTime();
		int lesson = parseCourseJson(courseInfo).getLesson();
		String platform = parseCourseJson(courseInfo).getPlatform();
		String platformWeb = parseCourseJson(courseInfo).getPlatformWeb();
		String liveSrc = parseCourseJson(courseInfo).getLiveSrc();
		String tag = parseCourseJson(courseInfo).getTag();
		// 生成course code 保证course信息唯一性
		String courseCode = DigestUtils.md5DigestAsHex((summary + price
				+ startDay + startTime + endDay + endTime + lesson + platform
				+ platformWeb + liveSrc + tag + teacherCode).getBytes());
		HashMap<String, Object> courseInfos = new HashMap<String, Object>();
		courseInfos.put("summary", summary);
		courseInfos.put("price", price);
		courseInfos.put("startDay", startDay);
		courseInfos.put("startTime", startTime);
		courseInfos.put("endDay", endDay);
		courseInfos.put("endTime", endTime);
		courseInfos.put("lesson", lesson);
		courseInfos.put("platform", platform);
		courseInfos.put("platformWeb", platformWeb);
		courseInfos.put("liveSrc", liveSrc);
		courseInfos.put("tag", tag);
		courseInfos.put("code", courseCode);

		// 通过code寻找teacher表中是否存在该老师信息，存在则返回teacher id

		// 将teacher信息添加到teacher表中
		int newTeacherId = courseDao.insertTeacherInfo(teacherName, photoName,
				org, orgWeb, teacherCode);
		System.out.println(newTeacherId);
		if (newTeacherId > 0) {
			courseInfos.put("teacherId", newTeacherId);

			int newCourseId = courseDao.insertCourseInfo(courseInfos);
			if (newCourseId > 0) {
				resultMap.put("release", "课程信息添加成功！");
			} else if (newCourseId == -1) {
				resultMap.put("release", "课程信息已存在，请勿重复添加！");
			} else if (newCourseId == -2) {
				resultMap.put("release", "课程信息添加失败！异常！");
			}
		} else if (newTeacherId == -1) {
			resultMap.put("release", "课程信息已存在，请勿重复添加！");
		} else if (newTeacherId == -2) {
			resultMap.put("release", "课程信息添加失败！异常！");
		}

		// build json string
		String jsonResult = buildCourseReleaseJson(resultMap);
		return jsonResult;
	}

	// Gson解析Json格式数据
	private Course parseCourseJson(String courseInfo) {
		// Gson gson = new Gson();
		course = gson.fromJson(courseInfo, new TypeToken<Course>() {
		}.getType());
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
	 * 服务于发布新直播课程页面 根据接收到的头像文件，在数据库中进行存储
	 * 
	 * @param photo
	 */
	public String uploadPhoto(MultipartFile photo) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 校验文件格式是否正确
		if (ValidateFile.validateImage(photo) > 0) {
			// newCourseId = lastTeacherId+1;
			//
			int newCourseId = courseDao.getLastId();
			String photoName = (10000001 + newCourseId) + ".png";
			// 存储图片到指定路径
			SaveFile.saveImage(photoName, photo);
			// 在teacher表中添加教师头像信息
			// newTeacherId = courseDao.insertTeacherPhoto(photoName);
			// 判断图片是否正确插入
			resultMap.put("photoName", photoName);
			resultMap.put("upload", "头像上传成功！");
		} else {
			resultMap.put("upload",
					"头像上传失败！请校验头像图片格式是否为jpg、jpeg、png,图片大小不超过800k！");
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

	/**
	 * 服务于直播课程管理页面 显示全部课程信息
	 */
	public String findAllCourse() throws Exception {
		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量

		List<HashMap<String, Object>> allCourseInfo = courseDao
				.getAllCourseInfo();
		if (allCourseInfo == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while (i < allCourseInfo.size()) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
			teacherInfo.put(
					"teacherId",
					EnDeCode.encodePara((Integer) allCourseInfo.get(i).get(
							"teacherId")));
			teacherInfo.put("teacherName",
					allCourseInfo.get(i).get("teacherName"));
			teacherInfo.put("photo", allCourseInfo.get(i).get("photo"));
			teacherInfo.put("orgName", allCourseInfo.get(i).get("orgName"));
			teacherInfo.put("orgWeb", allCourseInfo.get(i).get("orgWeb"));
			teacherInfo.put("teacherCode",
					allCourseInfo.get(i).get("teacherCode"));

			HashMap<String, Object> courseInfo = new HashMap<String, Object>();
			courseInfo.put(
					"courseId",
					EnDeCode.encodePara((Integer) allCourseInfo.get(i).get(
							"courseId")));
			courseInfo.put("dateType", Course.getDateType(allCourseInfo.get(i)
					.get("startDay"), allCourseInfo.get(i).get("endDay")));
			courseInfo.put("summary", allCourseInfo.get(i).get("summary"));
			courseInfo.put("startDay", allCourseInfo.get(i).get("startDay"));
			courseInfo.put("startTime", allCourseInfo.get(i).get("startTime"));
			courseInfo.put("endDay", allCourseInfo.get(i).get("endDay"));
			courseInfo.put("endTime", allCourseInfo.get(i).get("endTime"));
			courseInfo.put("lesson", allCourseInfo.get(i).get("lesson"));
			courseInfo.put("platform", allCourseInfo.get(i).get("platform"));
			courseInfo.put("platformWeb",
					allCourseInfo.get(i).get("platformWeb"));
			courseInfo.put("liveSrc", allCourseInfo.get(i).get("liveSrc"));
			courseInfo.put("price", allCourseInfo.get(i).get("price"));
			courseInfo.put("tag", allCourseInfo.get(i).get("tag"));
			courseInfo
					.put("courseCode", allCourseInfo.get(i).get("courseCode"));

			resultMap.put("teacherInfo", teacherInfo);
			resultMap.put("courseInfo", courseInfo);

			resultList.add(resultMap);
			i++;
		}
		// build json string
		String jsonResult = buildCourseJson(resultList);
		return jsonResult;
	}

	public String findAllEndedCourse() throws Exception {
		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量

		List<HashMap<String, Object>> allEndedCourseInfo = courseDao
				.getAllEndedCourseInfo();
		if (allEndedCourseInfo == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while (i < allEndedCourseInfo.size()) {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			HashMap<String, Object> teacherInfo = new HashMap<String, Object>();
			teacherInfo.put(
					"teacherId",
					EnDeCode.encodePara((Integer) allEndedCourseInfo.get(i).get(
							"teacherId")));
			teacherInfo.put("teacherName",
					allEndedCourseInfo.get(i).get("teacherName"));
			teacherInfo.put("photo", allEndedCourseInfo.get(i).get("photo"));
			teacherInfo.put("orgName", allEndedCourseInfo.get(i).get("orgName"));
			teacherInfo.put("orgWeb", allEndedCourseInfo.get(i).get("orgWeb"));
			teacherInfo.put("teacherCode",
					allEndedCourseInfo.get(i).get("teacherCode"));

			HashMap<String, Object> courseInfo = new HashMap<String, Object>();
			courseInfo.put(
					"courseId",
					EnDeCode.encodePara((Integer) allEndedCourseInfo.get(i).get(
							"courseId")));
			courseInfo.put("dateType", Course.getDateType(allEndedCourseInfo.get(i)
					.get("startDay"), allEndedCourseInfo.get(i).get("endDay")));
			courseInfo.put("summary", allEndedCourseInfo.get(i).get("summary"));
			courseInfo.put("startDay", allEndedCourseInfo.get(i).get("startDay"));
			courseInfo.put("startTime", allEndedCourseInfo.get(i).get("startTime"));
			courseInfo.put("endDay", allEndedCourseInfo.get(i).get("endDay"));
			courseInfo.put("endTime", allEndedCourseInfo.get(i).get("endTime"));
			courseInfo.put("lesson", allEndedCourseInfo.get(i).get("lesson"));
			courseInfo.put("platform", allEndedCourseInfo.get(i).get("platform"));
			courseInfo.put("platformWeb",
					allEndedCourseInfo.get(i).get("platformWeb"));
			courseInfo.put("liveSrc", allEndedCourseInfo.get(i).get("liveSrc"));
			courseInfo.put("price", allEndedCourseInfo.get(i).get("price"));
			courseInfo.put("tag", allEndedCourseInfo.get(i).get("tag"));
			courseInfo
					.put("courseCode", allEndedCourseInfo.get(i).get("courseCode"));

			resultMap.put("teacherInfo", teacherInfo);
			resultMap.put("courseInfo", courseInfo);

			resultList.add(resultMap);
			i++;
		}
		// build json string
		String jsonResult = buildCourseJson(resultList);
		return jsonResult;
	}

	public String deleteOneCourse(String courseCode) {
		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if(courseDao.removeCourse(courseCode)>0){
			resultMap.put("result", "课程信息删除成功！");
		}
		else{
			resultMap.put("result", "异常，课程信息删除失败！");
		} 
			
		//build json string
		String jsonResult = buildResultJson(resultMap);
		return jsonResult;
	}
	
	private String buildResultJson(HashMap<String, Object>resultMap){
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("actionInfo", resultMap);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}

}
