package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.CourseDao;

public class CourseDaoImpl implements CourseDao {
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<HashMap<String, Object>> getAllCourseInfo() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.CourseDao.getAllCourseInfo");
	}

	public int insertTeacherInfo(HashMap<String, Object> teacherInfo) {
		return sqlSession.insert("cn.itui.webdevelop.dao.CourseDao.insertTeacherInfo", teacherInfo);
		
	}

	public int insertCourseInfo(HashMap<String, Object> courseInfos) {
		return sqlSession.insert("cn.itui.webdevelop.dao.CourseDao.insertCourseInfo", courseInfos);
	}

	public int verifyPermissionByCode(String code) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CourseDao.verifyPermissionByCode", code);
	}

	public int getLastId() {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CourseDao.getLastId");
	}

	public int insertTeacherPhoto(String photoName) {
		return sqlSession.insert("cn.itui.webdevelop.dao.CourseDao.insertTeacherPhoto",photoName);	
	}

	public int updateTeacherInfo(HashMap<String, Object> teacherInfo) {
		return sqlSession.update("cn.itui.webdevelop.dao.CourseDao.updateTeacherInfo", teacherInfo);
	}

}
