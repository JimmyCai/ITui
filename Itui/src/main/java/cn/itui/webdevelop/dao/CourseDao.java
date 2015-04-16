package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface CourseDao {

	public List<HashMap<String, Object>> getAllCourseInfo();
	public int insertTeacherInfo(HashMap<String, Object> teacherInfo);
	public int updateTeacherInfo(HashMap<String, Object> teacherInfo);
	public int insertCourseInfo(HashMap<String, Object> courseInfos);
	public int verifyPermissionByCode(String code);
	public int getLastId();
	public int insertTeacherPhoto(String photoName);

}
