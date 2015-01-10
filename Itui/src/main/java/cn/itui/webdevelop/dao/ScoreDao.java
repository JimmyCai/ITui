package cn.itui.webdevelop.dao;

import java.util.List;

public interface ScoreDao {
	public List<Object[]> getLastNYearsScoreByMajorId(int id, int n);
}
