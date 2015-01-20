package cn.itui.webdevelop.service.impl;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.service.MajorService;
import cn.itui.webdevelop.utils.WordParticiple;

public class MajorServiceImpl implements MajorService {
	private MajorDao majorDao;
	private int limit;
	
	public String searchMajorsList(String condition, String category,
			String subject, String major_type, String college_type, String area) {

		String is985 = "";
		String is34 = "";
		String is211 = "";
		if (!(college_type.equalsIgnoreCase("") || college_type.startsWith("其他"))) {
			if (college_type.startsWith("9")) {
				// 985
				is985 = "1";
			} else {
				is985 = "0";
			}
			if (college_type.startsWith("3")) {
				// 34
				is34 = "1";
			} else {
				is34 = "0";
			}
			if (college_type.startsWith("2")) {
				// 211
				is211 = "1";
			} else {
				is211 = "0";
			}
		}else if (college_type.startsWith("o")){
			is211=is34=is985="0";
		}else {
			is211=is34=is985=WordParticiple.filterAll(college_type);
		}
		
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
//		major_type = WordParticiple.filterAll(major_type);
//		college_type = WordParticiple.filterAll(college_type);
		area = WordParticiple.filterAll(area);
		
		List<HashMap<String, Object>> majorList = majorDao.searchMajors(condition, category, subject, is985, is211, is34, type, area, limit);
		String json = buildJson(majorList);
		return json;
	}

	private String buildJson(List<HashMap<String, Object>> majorList) {
		// TODO Auto-generated method stub
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(majorList);
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
}
