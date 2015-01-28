package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowCollegeDao;

public class FollowCollegeDaoImpl implements FollowCollegeDao{
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> findFollowCollegeByUserPassword(String password) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowCollegeDao.findFollowCollegeByUserPassword", password);
	}

	public int insertFollowCollegeWithCollegeId(String userPassword, int collegeId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userPassword", userPassword);
		parameter.put("collegeId", collegeId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithCollegeId", parameter);
	}

	public int insertFollowCollegeWithMajorId(String userPassword, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userPassword", userPassword);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithMajorId", parameter);
	}

	public int deleteFollowCollege(int id) {
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowCollegeDao.deleteFollowCollege", id);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
