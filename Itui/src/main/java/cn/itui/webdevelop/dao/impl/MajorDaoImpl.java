package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.model.Major;

public class MajorDaoImpl implements MajorDao{
	private SqlSession sqlSession;

	public Major findMajorById(int id) {
		Major major = sqlSession.selectOne("cn.itui.webdevelop.dao.MajorDao.findMajorById", id);
		return major;
	}

	public List<HashMap<String, Object>> findCodeLikeMajorByCollegeId(
			String code, int collegeId) {
		String preCode = code.substring(0, 2) + "%";
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", preCode);
		parameter.put("collegeId", collegeId);
		List<HashMap<String, Object>> resultList = sqlSession.selectList("cn.itui.webdevelop.dao.MajorDao.findCodeLikeMajorByCollegeId", parameter);
		return resultList;
	}

	public List<HashMap<String, Object>> findMajorByCollegeIdAndNotInMajorIds(
			int collegeId, List<HashMap<String, Object>> candidateMajors) {
		StringBuilder notInStringBuilder = new StringBuilder();
		for(HashMap<String, Object> curMap : candidateMajors) {
			notInStringBuilder.append(curMap.get("id") + ",");
		}
		notInStringBuilder.setLength(notInStringBuilder.length() - 1);
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("collegeId", collegeId);
		parameter.put("notInStr", notInStringBuilder.toString());
		List<HashMap<String, Object>> resultList = sqlSession.selectList("cn.itui.webdevelop.dao.MajorDao.findMajorByCollegeIdAndNotInMajorIds", parameter);
		return resultList;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
