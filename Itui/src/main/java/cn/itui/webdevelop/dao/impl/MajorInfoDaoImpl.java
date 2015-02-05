package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.model.MajorInfo;

public class MajorInfoDaoImpl implements MajorInfoDao {
	private SqlSession sqlSession;
	
	public MajorInfo getMajorInfoById(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cn.itui.webdevelop.dao.MajorInfoDao.getMajorInfoById", id);
	}

	public HashMap<String, Object> findMajorAllInfoByMajorId(int majorId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cn.itui.webdevelop.dao.MajorInfoDao.findMajorAllInfoByMajorId", majorId);
	}

	public List<HashMap<String, Object>> findRankAndDegreeByMajorIds(
			List<Integer> list) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cn.itui.webdevelop.dao.MajorInfoDao.finaRankAndDegreeByMajorIds", list);

	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
