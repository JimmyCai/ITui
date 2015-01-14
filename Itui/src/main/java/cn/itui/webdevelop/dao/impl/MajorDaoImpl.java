package cn.itui.webdevelop.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		HashSet<Integer> candidateSet = new HashSet<Integer>();
		for(HashMap<String, Object> curMap : candidateMajors) {
			if(curMap.containsKey("id"))
				candidateSet.add((Integer)curMap.get("id"));
		}
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("collegeId", collegeId);
		List<HashMap<String, Object>> queryList = sqlSession.selectList("cn.itui.webdevelop.dao.MajorDao.findMajorByCollegeIdAndNotInMajorIds", parameter);
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		for(HashMap<String, Object> curMap : queryList) {
			if(curMap.containsKey("id")) {
				if(!candidateSet.contains((Integer)curMap.get("id"))){
					resultList.add(curMap);
				}
			}
		}
		return resultList;
	}

	public List<HashMap<String, Object>> findAreaSameCodeMajorByCollegeIdAndMajorCode(
			int collegeId, String majorCode) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("collegeId", collegeId);
		parameter.put("majorCode", majorCode);
		List<HashMap<String, Object>> resultList = sqlSession.selectList("cn.itui.webdevelop.dao.MajorDao.findAreaSameCodeMajorByCollegeIdAndMajorCode", parameter);
		return resultList;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
