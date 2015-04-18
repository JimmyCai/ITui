package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DuplicateKeyException;

import cn.itui.webdevelop.dao.CourseDao;
import cn.itui.webdevelop.model.Teacher;

public class CourseDaoImpl implements CourseDao {
	private SqlSession sqlSession;
	private Teacher teacher;
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<HashMap<String, Object>> getAllCourseInfo() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.CourseDao.getAllCourseInfo");
	}

	public int insertCourseInfo(HashMap<String, Object> courseInfos) {
		int courseId = -2;
		try {
			courseId = sqlSession.insert("cn.itui.webdevelop.dao.CourseDao.insertCourseInfo", courseInfos);
		} 
		catch(DuplicateKeyException dke){
			dke.printStackTrace();
			return -1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		return courseId;
		 
	}

	public int verifyPermissionByCode(String code) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CourseDao.verifyPermissionByCode", code);
	}

	public int getLastId() {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CourseDao.getLastId");
	}

	public int insertTeacherInfo(String teacherName, String photoName,
			String org, String orgWeb, String code) {
		System.out.println(teacherName);
		teacher.setName(teacherName);
		teacher.setPhoto(photoName);
		teacher.setOrgName(org);
		teacher.setOrgWeb(orgWeb);
		teacher.setCode(code);
		try {
			sqlSession.insert("cn.itui.webdevelop.dao.CourseDao.insertTeacherInfo", teacher);
		}catch (DuplicateKeyException dke){
			dke.printStackTrace();
			return -1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	    
		return teacher.getId();
	}

}
