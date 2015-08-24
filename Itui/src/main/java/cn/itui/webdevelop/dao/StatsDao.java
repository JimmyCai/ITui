package cn.itui.webdevelop.dao;

import java.util.HashMap;
import java.util.List;

public interface StatsDao {
	public void refreshStats(String date, int randomNum);
	public List<HashMap<String, Object>> getTodayStatsInfo(String date);
	public int getUserNum();
	public String getLastRecordDate();
	public void creatNewRecord(String date);
	public int getTotalPV();
	public List<HashMap<String, Object>> getPersonInfo();
	public List<HashMap<String, Object>> getNewsInfo();
	public List<HashMap<String, Object>> getTopicInfo();
	public HashMap<String, Object> getUserInfo(String userName, int userId,
			String password);
	public List<HashMap<String, Object>> getPersonEduInfo(
			HashMap<String, Object> personIdMap);
	public List<HashMap<String, Object>> getHeadlineInfo();
}
