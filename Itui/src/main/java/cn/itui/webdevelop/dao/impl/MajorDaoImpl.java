package cn.itui.webdevelop.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.model.Major;

public class MajorDaoImpl implements MajorDao {
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 根据major的id返回major对象
	 */
	public Major findMajorById(int id) {
		Major major = sqlSession.selectOne(
				"cn.itui.webdevelop.dao.MajorDao.findMajorById", id);
		return major;
	}

	/**
	 * 根据college id和major code查找相似的major，策略是取出code前两位一样的所有major
	 * hashmap的键有:id,name,school,code,rate
	 */
	public List<HashMap<String, Object>> findCodeLikeMajorByCollegeId(
			String code, int collegeId) {
		String preCode = code.substring(0, 2) + "%";
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("code", preCode);
		parameter.put("collegeId", collegeId);
		List<HashMap<String, Object>> resultList = sqlSession.selectList(
				"cn.itui.webdevelop.dao.MajorDao.findCodeLikeMajorByCollegeId",
				parameter);
		return resultList;
	}

	/**
	 * 根据college id查询所有该学校的major，然后刷选不在candidate的major
	 * hashmap的键有：id,name,school,code,rate
	 */
	public List<HashMap<String, Object>> findMajorByCollegeIdAndNotInMajorIds(
			int collegeId, List<HashMap<String, Object>> candidateMajors) {
		HashSet<Integer> candidateSet = new HashSet<Integer>();
		for (HashMap<String, Object> curMap : candidateMajors) {
			if (curMap.containsKey("id"))
				candidateSet.add((Integer) curMap.get("id"));
		}
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("collegeId", collegeId);
		List<HashMap<String, Object>> queryList = sqlSession
				.selectList(
						"cn.itui.webdevelop.dao.MajorDao.findMajorByCollegeIdAndNotInMajorIds",
						parameter);
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for (HashMap<String, Object> curMap : queryList) {
			if (curMap.containsKey("id")) {
				if (!candidateSet.contains((Integer) curMap.get("id"))) {
					resultList.add(curMap);
				}
			}
		}
		return resultList;
	}

	/**
	 * 根据college id和major code查找同一区域的所有major code一样的major
	 * name,school,college,applyadmitrate
	 */
	public List<HashMap<String, Object>> findAreaSameCodeMajorByCollegeIdAndMajorCode(
			int collegeId, String majorCode) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("collegeId", collegeId);
		parameter.put("majorCode", majorCode);
		List<HashMap<String, Object>> resultList = sqlSession
				.selectList(
						"cn.itui.webdevelop.dao.MajorDao.findAreaSameCodeMajorByCollegeIdAndMajorCode",
						parameter);
		return resultList;
	}

	public List<HashMap<String, Object>> findAllMajors() {
		return sqlSession
				.selectList("cn.itui.webdevelop.dao.MajorDao.findAllMajors");
	}

	/**
	 * 根据condition和分类条件来查询符合条件的专业 hashmap的key有id, name, school, college, logo,
	 * is985, is34, is211, rank, degree
	 */
	public List<HashMap<String, Object>> searchMajors(String condition,
			String category, String subject, String is985, String is211,
			String is34, String type, String area, int from, int limit) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		System.out.println(area);
		parameter.put("condition", condition);
		parameter.put("type", type);
		parameter.put("category", category);
		System.out.println(category);
		parameter.put("subject", subject);
		parameter.put("is985", is985);
		parameter.put("is34", is34);
		parameter.put("is211", is211);
		parameter.put("area", area);
		parameter.put("from", from);
		parameter.put("limit", limit);
//		 RowBounds rowBounds = new RowBounds(from, limit);

		long start = System.currentTimeMillis();
		List<HashMap<String, Object>> list = sqlSession.selectList(
				"cn.itui.webdevelop.dao.MajorDao.searchMajors", parameter);
		System.out.println("sql time:"+(System.currentTimeMillis() - start));
		System.out.println(list.size());
		return list;
	}

	public int count(String condition, String category, String subject,
			String is985, String is211, String is34, String type, String area) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("condition", condition);
		parameter.put("type", type);
		parameter.put("category", category);
		parameter.put("subject", subject);
		parameter.put("is985", is985);
		parameter.put("is34", is34);
		parameter.put("is211", is211);
		parameter.put("area", area);
		return sqlSession.selectOne("cn.itui.webdevelop.dao.MajorDao.count", parameter);
	}
}

class MyResultHandler implements ResultHandler {

	public void handleResult(ResultContext context) {

	}

}
