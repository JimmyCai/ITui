package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowCollegeDao;

public class FollowCollegeDaoImpl implements FollowCollegeDao{
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> findFollowCollegeByUserCode(String code) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowCollegeDao.findFollowCollegeByUserCode", code);
	}

	public int insertFollowCollegeWithCollegeId(String code, int collegeId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("collegeId", collegeId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithCollegeId", parameter);
	}

	public int insertFollowCollegeWithMajorId(String code, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithMajorId", parameter);
	}

	public int deleteFollowCollegeByCollegeIdAndCode(int collegeId, String code) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("collegeId", collegeId);
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowCollegeDao.deleteFollowCollegeByCollegeIdAndCode", parameter);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
