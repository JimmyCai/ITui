package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.utils.exception.DatabaseException;

public interface FollowCollegeDao {
	public List<HashMap<String, Object>> findFollowCollegeByUserCode(String code);
	public int insertFollowCollegeWithCollegeId(String code, int collegeId);
	public int insertFollowCollegeWithMajorId(String code, int majorId);
	public int deleteFollowCollegeByCollegeIdAndCode(int collegeId, String code);
	public int isUserFollowCollege(String code, int collegeId) throws DatabaseException;
}
