package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface FollowMajorDao {
	public List<HashMap<String, Object>> findFollowMajorByUserPassword(String password);
	public int insertFollowMajor(String userPassword, int majorId);
	public int deleteFollowMajorById(int id);
	public int deleteFollowMajorByCollegeIdAndPassword(String userPassword, int collegeId);
}
