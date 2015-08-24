package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.MajorInfo;

public interface MajorInfoDao {
	public MajorInfo getMajorInfoById(int id);
	public HashMap<String, Object> findMajorAllInfoByMajorId(int majorId);
	public List<HashMap<String, Object>> findRankAndDegreeByMajorIds(List<Integer> list);
	/*
	 * add
	 */
	public List<HashMap<String, Object>> findMajorAllRankInfoByMajorId(int majorId);
	public int getMajorIdByFullname(String majorIdStr);
	public List<HashMap<String, Object>> getHotQuestions(int day);

}
