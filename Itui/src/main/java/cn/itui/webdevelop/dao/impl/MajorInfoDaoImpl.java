package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.model.MajorInfo;

public class MajorInfoDaoImpl implements MajorInfoDao {
	private SqlSession sqlSession;
	
	public MajorInfo getMajorInfoById(int id) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.MajorInfoDao.getMajorInfoById", id);
	}

	public HashMap<String, Object> findMajorAllInfoByMajorId(int majorId) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.MajorInfoDao.findMajorAllInfoByMajorId", majorId);
	}

	public List<HashMap<String, Object>> findRankAndDegreeByMajorIds(
			List<Integer> list) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.MajorInfoDao.finaRankAndDegreeByMajorIds", list);

	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	/*
	 * add
	 */
	public List<HashMap<String, Object>> findMajorAllRankInfoByMajorId(int majorId) {
		List<HashMap<String, Object>> resultList = sqlSession.selectList(
				"cn.itui.webdevelop.dao.MajorInfoDao.findMajorAllRankInfoByMajorId", majorId);
		return resultList;
	}

	

}
