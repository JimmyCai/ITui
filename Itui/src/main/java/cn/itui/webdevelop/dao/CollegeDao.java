package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.College;

public interface CollegeDao {
	public HashMap<String, Object> findLogoAndRankByMajorId(int id);

	public List<College> findCollegeInRank(int rank, int collegeId);

	public List<HashMap<String, Object>> searchCollegesByName(String condition);

	public List<String> findSchoolsByCollegeId(int id);

	public List<HashMap<String, Object>> findMajorsBySchool(int collegeId,
			String school);

	public HashMap<String, Object> getCollegeInfo(int collegeId);
}
