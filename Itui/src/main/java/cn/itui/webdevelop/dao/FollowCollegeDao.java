package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface FollowCollegeDao {
	public List<HashMap<String, Object>> findFollowCollegeByUserCode(String code);
	public int insertFollowCollegeWithCollegeId(String code, int collegeId);
	public int insertFollowCollegeWithMajorId(String code, int majorId);
	public int deleteFollowCollegeByCollegeIdAndCode(int collegeId, String code);
}
