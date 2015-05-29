package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface CourseDao {

	public List<HashMap<String, Object>> getAllAliveCourseInfo();
	public int insertCourseInfo(HashMap<String, Object> courseInfos);
	public int verifyPermissionByCode(String code);
	public int getLastId();
	public int insertTeacherInfo(String teacherName, String photoName,
			String org, String orgWeb, String code);
	public List<HashMap<String, Object>> getAllCourseInfo();
	public List<HashMap<String, Object>> getAllEndedCourseInfo();
	public int removeCourse(String courseCode);

}
