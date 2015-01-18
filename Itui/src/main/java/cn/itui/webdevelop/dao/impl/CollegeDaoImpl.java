package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.model.College;

public class CollegeDaoImpl implements CollegeDao{
	private SqlSession sqlSession;

	public HashMap<String, Object> findLogoAndRankByMajorId(int id) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.CollegeDao.findLogoAndRankByMajorId", id);
	}

	/**
	 * 查找排名在某个范围内的College
	 * rank 为排名，取rank-4 ~ rank+4之间的
	 */
	public List<College> findCollegeInRank(int rank, int collegeId) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("curRank", rank);
		parameterMap.put("collegeId", collegeId);
		return sqlSession.selectList("cn.itui.webdevelop.dao.CollegeDao.findCollegeInRank", parameterMap);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
