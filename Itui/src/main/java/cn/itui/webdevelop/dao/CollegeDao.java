package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.College;

public interface CollegeDao {
	public HashMap<String, Object> findLogoAndRankByMajorId(int id);

	public List<College> findCollegeInRank(int rank, int collegeId);

	public List<HashMap<String, Object>> searchCollegesByName(String condition, String area, String is211, String is34, String is985, int from, int limit);

	public List<String> findSchoolsByCollegeId(int id);

	public List<HashMap<String, Object>> findMajorsBySchool(int collegeId,
			String school);

	public HashMap<String, Object> getCollegeInfo(int collegeId);

	public int getTotal(String condition, String area, String is211,
			String is34, String is985);
	/*
	 * add
	 * */

	public List<HashMap<String, Object>> getCollegeRankInfos();
	public List<HashMap<String, Object>> getCollegeLocalRankInfos(int collegeId);

	public int getCollegeIdByName(String collegeName);
}
