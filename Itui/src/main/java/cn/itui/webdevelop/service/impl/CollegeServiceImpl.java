
package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.WordParticiple;

/**
 * college服务类，主要用于college搜索、查看该学校所有院系、以及查看该学校某个院系的搜索专业
 */
public class CollegeServiceImpl implements CollegeService {
	private CollegeDao collegeDao;

	/**
	 * 服务于搜索页
	 * 根据搜索字符串搜索学校
	 * @author warrior
	 * @param condition 用户输入字符串
	 * @return 搜索结果的json
	 */
	public String searchCollegeList(String condition) {
		/*****分词*****/
		condition = WordParticiple.participle(condition);
		System.out.println(condition);
		/***** 分词结束 *****/
		List<HashMap<String, Object>> searchResult = collegeDao.searchCollegesByName(condition);
		for(HashMap<String, Object> curList : searchResult) {
			curList.put("id", EnDeCode.encodePara((Integer)curList.get("id")));
		}
		System.out.println(searchResult.size());
		String json = buildJson(searchResult);
		return json;
	}

	/**
	 * 将搜索结果封装为json
	 * 	这里还处理了排名大于1000的问题
	 * @param searchResult
	 * @return
	 */
	private String buildJson(List<HashMap<String, Object>> searchResult) {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < searchResult.size(); i++) {
			HashMap<String, Object> college = searchResult.get(i);
			
			/*****处理排名*****/
			int rank =   (Integer) college.get("rank");
			if (rank > 1000){
				college.put("rank", rank%1000+"+");
			}
//			else if (rank == -1){
//				college.put("rank", "不参与排名");
//			}
//			int localRank = (Integer)college.get("localRank");
//			if (localRank == -1){
//				college.put("localRank", "不参与排名");
//			}
			
			HashMap<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put(String.valueOf(i), college);
			list.add(itemMap);
		}
		jsonMap.put("list", list);
		jsonMap.put("type", "college");
		jsonMap.put("num", list.size());
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
	 */
	public String findSchoolsById(int collegeId) {
		// TODO Auto-generated method stub
		List<String> schools = collegeDao.findSchoolsByCollegeId(collegeId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("school", schools);
		return ResponseUtil.wrapNormalReturn(map);
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

}
