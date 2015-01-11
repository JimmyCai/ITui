package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.Major;

public interface MajorDao {
	public Major findMajorById(int id);
	public List<HashMap<String, Object>> findCodeLikeMajorByCollegeId(String code, int collegeId);
	public List<HashMap<String, Object>> findMajorByCollegeIdAndNotInMajorIds(int collegeId, List<HashMap<String, Object>> candidateMajors);
}
