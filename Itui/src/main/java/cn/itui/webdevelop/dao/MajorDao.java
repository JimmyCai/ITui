package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.Major;

public interface MajorDao {
	public Major findMajorById(int id);

	public List<HashMap<String, Object>> findCodeLikeMajorByCollegeId(
			String code, int collegeId);

	public List<HashMap<String, Object>> findMajorByCollegeIdAndNotInMajorIds(
			int collegeId, List<HashMap<String, Object>> candidateMajors);

	public List<HashMap<String, Object>> findAreaSameCodeMajorByCollegeIdAndMajorCode(
			int collegeId, String majorCode);

	public List<HashMap<String, Object>> findAllMajors();

	public List<HashMap<String, Object>> searchMajors(String condition,
			String category, String subject, String is985, String is211,
			String is34, String type, String area, int from, int limit);

	public int count(String condition, String category, String subject,
			String is985, String is211, String is34, String type, String area);
	
}
