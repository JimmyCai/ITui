
package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.dao.FollowCollegeDao;
import cn.itui.webdevelop.model.College;
import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.WordParticiple;
import cn.itui.webdevelop.utils.exception.DatabaseException;

/**
 * college服务类，主要用于college搜索、查看该学校所有院系、以及查看该学校某个院系的搜索专业
 */
public class CollegeServiceImpl implements CollegeService {
	private CollegeDao collegeDao;
	private FollowCollegeDao followCollegeDao;
	private int limit;

	/**
	 * 服务于搜索页
	 * 根据搜索字符串搜索学校
	 * @author warrior
	 * @param condition 用户输入字符串
	 * @return 搜索结果的json
	 */
	public String searchCollegeList(String condition, String area, String collegeType, int from) {
		/*****分词*****/
		condition = WordParticiple.participle(condition);
		System.out.println(condition);
		/***** 分词结束 *****/
		String is985 = "";
		String is34 = "";
		String is211 = "";
		if (!(collegeType.equalsIgnoreCase("") || collegeType.startsWith("普通"))) {
			if (collegeType.startsWith("985")) {
				// 985
				is985 = "1";
			} 
			if (collegeType.startsWith("34")) {
				// 34
				is34 = "1";
			} 
			if (collegeType.startsWith("211")) {
				// 211
				is211 = "1";
			}
		}else if (collegeType.startsWith("普通")){
			is211=is34=is985="0";
			System.out.println("普通");
		}
		is211 = WordParticiple.filterAll(is211);
		is34 = WordParticiple.filterAll(is34);
		is985 = WordParticiple.filterAll(is985);		

		List<HashMap<String, Object>> searchResult = collegeDao.searchCollegesByName(condition,area, is211,is34,is985, from, limit);
		int total = collegeDao.getTotal(condition,area, is211,is34,is985);
		for(HashMap<String, Object> curList : searchResult) {
			curList.put("id", EnDeCode.encodePara((Integer)curList.get("id")));
		}
		System.out.println(searchResult.size());
		String json = buildJson(searchResult, total);
		return json;
	}

	/**
	 * 将搜索结果封装为json
	 * 	这里还处理了排名大于1000的问题
	 * @param searchResult
	 * @param total 
	 * @return
	 */
	private String buildJson(List<HashMap<String, Object>> searchResult, int total) {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < searchResult.size(); i++) {
			HashMap<String, Object> college = searchResult.get(i);
			
			/*****处理排名*****/
			int rank =   (Integer) college.get("rank");
			if (rank > 1000){
				college.put("rank", rank%1000+"+");
			}
			if (rank == 2000){
				college.put("rank", "-1");
			}
			list.add(college);
		}
		jsonMap.put("list", list);
		jsonMap.put("type", "college");
		jsonMap.put("total", total);
		return ResponseUtil.wrapNormalReturn(jsonMap);
	}

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	/**
	 * 服务于学校页面
	 * 根据college的id找到其school列表
	 * @param collegeId
	 * @author warrior
	 * @throws DatabaseException 
	 */
	public String findSchoolsById(String code, int collegeId) throws DatabaseException {
		HashMap<String, Object> result = collegeDao.getCollegeInfo(collegeId);
		List<String> schools=null;
		try {
			schools = collegeDao.findSchoolsByCollegeId(collegeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.put("school", schools);
		int followId = followCollegeDao.isUserFollowCollege(code, collegeId);
		result.put("followId", followId);
		return ResponseUtil.wrapNormalReturn(result);
	}

	/**
	 * 服务于学校页点击院系名称
	 * 根据学校id和学院名搜索对应的专业
	 * @param collegeId
	 * @param school
	 * @author warrior
	 */
	public String findMajorsBySchool(int collegeId, String school) {
		List<HashMap<String, Object>> majors = collegeDao.findMajorsBySchool(collegeId,school);
		for(HashMap<String, Object> curList : majors) {
			curList.put("id", EnDeCode.encodePara((Integer)curList.get("id")));
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("major", majors);
		return ResponseUtil.wrapNormalReturn(map);
	}

	public FollowCollegeDao getFollowCollegeDao() {
		return followCollegeDao;
	}

	public void setFollowCollegeDao(FollowCollegeDao followCollegeDao) {
		this.followCollegeDao = followCollegeDao;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/*
	 * add
	 * */

	public String getCollegeRank(int collegeId) throws Exception {
		List<HashMap<String, Object>> collegesInfos = collegeDao.getCollegeRankInfos();
		if (collegesInfos == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while(i<(collegesInfos.size()/2)){
			HashMap<String, Object> resultItem = new HashMap<String, Object>();
			resultItem.put("college", collegesInfos.get(i).get("college"));
			resultItem.put("collegeId", EnDeCode.encodePara((Integer)collegesInfos.get(i).get("collegeId")));
			resultItem.put("logo", collegesInfos.get(i).get("logo"));
			resultItem.put("rank", College.getRankValue((Integer)collegesInfos.get(i).get("rank")));
			resultItem.put("city", collegesInfos.get(i).get("city"));
			resultItem.put("typeInfo", College.getRankTypeString(
					(Integer) collegesInfos.get(i).get("is34"),
					(Integer) collegesInfos.get(i).get("is985"),
					(Integer) collegesInfos.get(i).get("is211")));
			
			resultList.add(resultItem);
			i++;
		}
		// build json string
				String jsonResult = buildCollegeRankJson(resultList);
				return jsonResult;
	}
	private String buildCollegeRankJson(List<HashMap<String, Object>> resultList)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("collegeRankList", resultList);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}

	public String getCollegeLocalRank(int collegeId) throws Exception {
		
		List<HashMap<String, Object>> collegesInfos = collegeDao.getCollegeLocalRankInfos(collegeId);
		if (collegesInfos == null)
			throw DatabaseException.getInstance();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while(i<collegesInfos.size()){
			HashMap<String, Object> resultItem = new HashMap<String, Object>();
			resultItem.put("college", collegesInfos.get(i).get("college"));
			resultItem.put("collegeId", EnDeCode.encodePara((Integer)collegesInfos.get(i).get("collegeId")));
			resultItem.put("logo", collegesInfos.get(i).get("logo"));
			resultItem.put("localRank", College.getRankValue((Integer)collegesInfos.get(i).get("localRank")));
			resultItem.put("city", collegesInfos.get(i).get("city"));
			resultItem.put("typeInfo", College.getRankTypeString(
					(Integer) collegesInfos.get(i).get("is34"),
					(Integer) collegesInfos.get(i).get("is985"),
					(Integer) collegesInfos.get(i).get("is211")));
			
			resultList.add(resultItem);
			i++;
		}
		// build json string
				String jsonResult = buildCollegeLocalRankJson(resultList);
				return jsonResult;
	}
	private String buildCollegeLocalRankJson(List<HashMap<String, Object>> resultList)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("collegeLocalRankList", resultList);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}

}
