package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface StatsDao {
	public void refreshStats(String date);
	public List<HashMap<String, Object>> getTodayStatsInfo(String date);
	public int getUserNum();
	public String getLastRecordDate();
	public void creatNewRecord(String date);
	public int getTotalPV();
}
