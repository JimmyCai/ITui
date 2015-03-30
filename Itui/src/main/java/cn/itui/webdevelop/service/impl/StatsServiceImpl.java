package cn.itui.webdevelop.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.dao.StatsDao;
import cn.itui.webdevelop.model.Stats;
import cn.itui.webdevelop.service.StatsService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;

public class StatsServiceImpl implements StatsService {
	private StatsDao statsDao;
	
	public StatsDao getStatsDao() {
		return statsDao;
	}

	public void setStatsDao(StatsDao statsDao) {
		this.statsDao = statsDao;
	}

	public String getPVStats() throws Exception {
		//get user num,set the user num basic value is 567
		int user_num = statsDao.getUserNum()+567;
		//get last record date,if not exists creat a new one record
		if(!(Stats.getDate().equals(statsDao.getLastRecordDate()))){
			statsDao.creatNewRecord(Stats.getDate());
		}
		
		statsDao.refreshStats(Stats.getDate(), (int)((Math.random()*5)+1));//实际增加一次浏览量，生成（1-5）的随机浏览量
		
		List<HashMap<String, Object>> todayStatsInfo = statsDao.getTodayStatsInfo(Stats.getDate());
		if (todayStatsInfo == null)
			throw DatabaseException.getInstance();
		
		//get total pv
		int total = statsDao.getTotalPV();
		HashMap<String, Object> resultItem = new HashMap<String, Object>();
		resultItem.put("user_num", user_num);
		resultItem.put("total", total);
		resultItem.put("date", todayStatsInfo.get(0).get("date"));
		resultItem.put("today", todayStatsInfo.get(0).get("today"));
		//build json string
		String jsonResult = buildStatsJson(resultItem);
		return jsonResult;
	}
	
	private String buildStatsJson(HashMap<String, Object> resultItem)
			throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("statsInfo", resultItem);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}

}
