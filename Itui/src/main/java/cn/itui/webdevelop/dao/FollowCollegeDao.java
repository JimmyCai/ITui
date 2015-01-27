package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface FollowCollegeDao {
	public List<HashMap<String, Object>> findFollowCollegeByUserPassword(String password);
	public int insertFollowCollegeWithCollegeId(String userPassword, int collegeId);
	public int insertFollowCollegeWithMajorId(String userPassword, int majorId);
	public int deleteFollowCollege(int id);
}
