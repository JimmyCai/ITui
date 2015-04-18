package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface CourseDao {

	public List<HashMap<String, Object>> getAllCourseInfo();
	public int insertCourseInfo(HashMap<String, Object> courseInfos);
	public int verifyPermissionByCode(String code);
	public int getLastId();
	public int insertTeacherInfo(String teacherName, String photoName,
			String org, String orgWeb, String code);

}
