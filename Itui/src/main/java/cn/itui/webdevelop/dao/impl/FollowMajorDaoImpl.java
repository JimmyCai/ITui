package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowMajorDao;

public class FollowMajorDaoImpl implements FollowMajorDao{
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> findFollowMajorByUserPassword(String password) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowMajorDao.findFollowMajorByUserPassword", password);
	}

	public int insertFollowMajor(String userPassword, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userPassword", userPassword);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowMajorDao.insertFollowMajor", parameter);
	}

	public int deleteFollowMajorById(int id) {
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowMajorDao.deleteFollowMajorById", id);
	}
	
	public int deleteFollowMajorByCollegeIdAndPassword(String userPassword,
			int collegeId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userPassword", userPassword);
		parameter.put("collegeId", collegeId);
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowMajorDao.deleteFollowMajorByCollegeIdAndPassword", parameter);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
