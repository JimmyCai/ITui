package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowMajorDao;

public class FollowMajorDaoImpl implements FollowMajorDao{
	private SqlSession sqlSession;

	/**
	 * 根据用户code获取关注的专业
	 */
	public List<HashMap<String, Object>> findFollowMajorByUserCode(String code) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowMajorDao.findFollowMajorByUserCode", code);
	}

	/**
	 * code用户是否关注了majorId对应的专业
	 */
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

	/**
	 * 插入关注记录
	 */
	public int insertFollowMajor(String code, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowMajorDao.insertFollowMajor", parameter);
	}

	/**
	 * 删除关注记录
	 */
	public int deleteFollowMajorByMajorIdAndCode(String code, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		return sqlSession.delete("cn.itui.webdevelop.dao.FollowMajorDao.deleteFollowMajorByMajorIdAndCode", parameter);
	}
	
	/**
	 * 删除关注学校时，需要删除该学校的所有关注的专业
	 */
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
