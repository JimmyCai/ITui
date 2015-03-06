package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.service.MajorService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.WordParticiple;

public class MajorServiceImpl implements MajorService {
	private MajorDao majorDao;
	private MajorInfoDao majorInfoDao;
	private CollegeDao collegeDao;
	private int limit;
	
	/**
	 * 根据条件和分类要求来搜索对应的专业
	 */
	public String searchMajorsList(String condition, String category,
			String subject, String major_type, String college_type, String area, int from) {

		String is985 = "";
		String is34 = "";
		String is211 = "";
		if (!(college_type.equalsIgnoreCase("") || college_type.startsWith("普通"))) {
			if (college_type.startsWith("985")) {
				// 985
				is985 = "1";
			} 
			if (college_type.startsWith("34")) {
				// 34
				is34 = "1";
			} 
			if (college_type.startsWith("211")) {
				// 211
				is211 = "1";
			}
		}else if (college_type.startsWith("普通")){
			is211=is34=is985="0";
			System.out.println("普通");
		}
		is211 = WordParticiple.filterAll(is211);
		is34 = WordParticiple.filterAll(is34);
		is985 = WordParticiple.filterAll(is985);		
		
		String type="";
		if (major_type.startsWith("学")){
			type="1";
		}else if (major_type.startsWith("专")) {
			type="2";
		}else {
			type = WordParticiple.filterAll(major_type);
		}
		condition = WordParticiple.participle(condition);
		// 全部
		category = WordParticiple.filterAll(category);
		subject = WordParticiple.filterAll(subject);
		area = WordParticiple.filterAll(area);
		
		long searchStart = System.currentTimeMillis();
		List<HashMap<String, Object>> majorList = majorDao.searchMajors(condition, category, subject, is985, is211, is34, type, area, from, limit);
		System.out.println("search time:"+(System.currentTimeMillis()-searchStart));
		long countStart = System.currentTimeMillis();
		int total = majorDao.count(condition, category, subject, is985, is211, is34, type, area);
		System.out.println("count Time:" + (System.currentTimeMillis()-countStart));
		
		long rank_degreeStart=System.currentTimeMillis();
		List<Integer> idList = new ArrayList<Integer>();
		for (int i=0; i < majorList.size(); i++){
			idList.add((Integer)majorList.get(i).get("id"));
		}
		
		System.out.println("length:"+idList.size());
		
//		List<HashMap<String, Object>> rankAndDegrees = new ArrayList<HashMap<String,Object>>();
//		if (idList.size() > 0){
//			rankAndDegrees = majorInfoDao.findRankAndDegreeByMajorIds(idList);
//		}
		System.out.println("rank_degree_time:"+(System.currentTimeMillis() - rank_degreeStart));
		for (int i = 0; i < majorList.size(); i++){
			HashMap<String, Object> map = majorList.get(i);
//			System.out.println("size:"+rankAndDegrees.size());
//			for (int j = 0; j < rankAndDegrees.size(); j++){
//				int majorId = (Integer)rankAndDegrees.get(j).get("majorId");
//				int id = (Integer)map.get("id");
//				System.out.println("majorId:"+majorId+" id:"+id);
//				if (majorId == id){
//					System.err.println(true);
					int rank = (Integer)(map.get("rank"));
					if (rank > 1000){ 
						map.put("rank", rank%1000+"+");
					}else {
						map.put("rank", rank);
					}
					if (rank == 2000) {
						map.put("rank", -1);
					}else {
						map.put("rank", rank);
					}
//					map.put("degree", rankAndDegrees.get(j).get("degree"));
//				}
//			}
			map.put("id", EnDeCode.encodePara((Integer)map.get("id")));
			
		}
		String json = buildJson(majorList, total);
		return json;
	}

	/**
	 * 将搜索结果转换成json
	 * @param majorList
	 * @return
	 */
	private String buildJson(List<HashMap<String, Object>> majorList, int total) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("type", "major");
		map.put("list", majorList);
		return ResponseUtil.wrapNormalReturn(map);
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public MajorInfoDao getMajorInfoDao() {
		return majorInfoDao;
	}

	public void setMajorInfoDao(MajorInfoDao majorInfoDao) {
		this.majorInfoDao = majorInfoDao;
	}

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
}
