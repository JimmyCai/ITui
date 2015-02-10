package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.FollowCollegeDao;

public class FollowCollegeDaoImpl implements FollowCollegeDao{
	private SqlSession sqlSession;

	/**
	 * 根据用户code获取所关注的学校
	 */
	public List<HashMap<String, Object>> findFollowCollegeByUserCode(String code) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.FollowCollegeDao.findFollowCollegeByUserCode", code);
	}

	/**
	 * 插入关注学校记录
	 */
	public int insertFollowCollegeWithCollegeId(String code, int collegeId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("collegeId", collegeId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithCollegeId", parameter);
	}

	/**
	 * 插入关注专业时，需要关注该专业所在的学校（如果已经关注过则不会关注）
	 */
	public int insertFollowCollegeWithMajorId(String code, int majorId) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", code);
		parameter.put("majorId", majorId);
		return sqlSession.insert("cn.itui.webdevelop.dao.FollowCollegeDao.insertFollowCollegeWithMajorId", parameter);
	}

	/**
	 * 删除关注学校
	 */
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
