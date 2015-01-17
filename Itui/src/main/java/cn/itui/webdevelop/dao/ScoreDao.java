package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface ScoreDao {
	public List<HashMap<String, Object>> getLastNYearsScoreByMajorId(int majorId, int count);
}
