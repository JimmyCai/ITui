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

}
