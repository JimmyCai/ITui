package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowMajorDao;

public class FollowMajorDaoImpl implements FollowMajorDao{
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> findFollowMajorByUserCode(String code) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowMajorDao.findFollowMajorByUserCode", code);
	}

	public int isUserFollowMajor(String code,
			int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		List<HashMap<String, Object>> result = sqlSession.selectList("cn.itui.webdevelop.dao.FollowMajorDao.isUserFollowMajor", parameter);
		if(result.size() > 0)
			return (Integer)result.get(0).get("id");
		return -1;
	}

	public int insertFollowMajor(String code, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowMajorDao.insertFollowMajor", parameter);
	}

	public int deleteFollowMajorById(int id) {
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowMajorDao.deleteFollowMajorById", id);
	}
	
	public int deleteFollowMajorByCollegeIdAndCode(String code,
			int collegeId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("collegeId", collegeId);
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowMajorDao.deleteFollowMajorByCollegeIdAndCode", parameter);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
