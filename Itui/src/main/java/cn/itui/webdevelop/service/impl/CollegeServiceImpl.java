package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.WordParticiple;

public class CollegeServiceImpl implements CollegeService {
	private CollegeDao collegeDao;

	/**
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
		System.out.println(searchResult.size());
		String json = buildJson(searchResult);
		return json;
	}

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
		jsonMap.put("num", list.size());
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(jsonMap);
		return json;
	}

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

}
