package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface FollowMajorDao {
	public List<HashMap<String, Object>> findFollowMajorByUserCode(String code);
	public int isUserFollowMajor(String code, int majorId);
	public int insertFollowMajor(String code, int majorId);
	public int deleteFollowMajorByMajorIdAndCode(String code, int id);
	public int deleteFollowMajorByCollegeIdAndCode(String code, int collegeId);
}
