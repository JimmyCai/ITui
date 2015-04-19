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
	 * 服务于直播课页面 获取所有未结束直播课程信息
	 */
	public String getAllCourse() throws Exception {
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

			HashMap<String, Object> courseInfo = new HashMap<String, Object>();
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
//	    String photoName = "default.png";
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
			} else if(newCourseId == -1){
				resultMap.put("release", "课程信息已存在，请勿重复添加！");
			}else if(newCourseId == -2) {
				resultMap.put("release", "课程信息添加失败！异常！");
			}
		}else if(newTeacherId == -1) {
			resultMap.put("release", "课程信息已存在，请勿重复添加！");
		}else if(newTeacherId == -2){
			resultMap.put("release", "课程信息添加失败！异常！");
		}

		// build json string
		String jsonResult = buildCourseReleaseJson(resultMap);
		return jsonResult;
	}

	// Gson解析Json格式数据
	private Course parseCourseJson(String courseInfo) {
		Gson gson = new Gson();
		Course course = gson.fromJson(courseInfo, new TypeToken<Course>() {
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

}
