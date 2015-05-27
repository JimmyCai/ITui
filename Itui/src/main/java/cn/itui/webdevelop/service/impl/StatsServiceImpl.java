package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.dao.StatsDao;
import cn.itui.webdevelop.model.Stats;
import cn.itui.webdevelop.service.StatsService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;

public class StatsServiceImpl implements StatsService {
	private StatsDao statsDao;
	private static String PERSON_HOMEPAGE;
	private static String NEWS_SQUARE_URL;
	private static String TOPIC_URL;
	private static String tOPIC_IMAGE_URL;
	private static String USER_LOGO_URL;

	public StatsDao getStatsDao() {
		return statsDao;
	}

	public void setStatsDao(StatsDao statsDao) {
		this.statsDao = statsDao;
	}

	public static String getPERSON_HOMEPAGE() {
		return PERSON_HOMEPAGE;
	}

	public static void setPERSON_HOMEPAGE(String pERSON_HOMEPAGE) {
		PERSON_HOMEPAGE = pERSON_HOMEPAGE;
	}

	public static String getNEWS_SQUARE_URL() {
		return NEWS_SQUARE_URL;
	}

	public static void setNEWS_SQUARE_URL(String nEWS_SQUARE_URL) {
		NEWS_SQUARE_URL = nEWS_SQUARE_URL;
	}

	public static String getTOPIC_URL() {
		return TOPIC_URL;
	}

	public static void setTOPIC_URL(String tOPIC_URL) {
		TOPIC_URL = tOPIC_URL;
	}

	public static String gettOPIC_IMAGE_URL() {
		return tOPIC_IMAGE_URL;
	}

	public static void settOPIC_IMAGE_URL(String tOPIC_IMAGE_URL) {
		StatsServiceImpl.tOPIC_IMAGE_URL = tOPIC_IMAGE_URL;
	}

	public static String getUSER_LOGO_URL() {
		return USER_LOGO_URL;
	}

	public static void setUSER_LOGO_URL(String uSER_LOGO_URL) {
		USER_LOGO_URL = uSER_LOGO_URL;
	}

	public String getPVStats() throws Exception {
		// get user num,set the user num basic value is 567
		int user_num = statsDao.getUserNum() + 567;
		// get last record date,if not exists creat a new one record
		if (!(Stats.getDate().equals(statsDao.getLastRecordDate()))) {
			statsDao.creatNewRecord(Stats.getDate());
		}

		statsDao.refreshStats(Stats.getDate(), (int) ((Math.random() * 5) + 1));// 实际增加一次浏览量，生成（1-5）的随机浏览量

		List<HashMap<String, Object>> todayStatsInfo = statsDao
				.getTodayStatsInfo(Stats.getDate());
		if (todayStatsInfo == null) {
			throw DatabaseException.getInstance();
		}
		// get total pv
		int total = statsDao.getTotalPV();
		HashMap<String, Object> resultItem = new HashMap<String, Object>();
		resultItem.put("user_num", user_num);
		resultItem.put("total", total);
		resultItem.put("date", todayStatsInfo.get(0).get("date"));
		resultItem.put("today", todayStatsInfo.get(0).get("today"));
		// build json string
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

	public String getIndexInfo() throws DatabaseException {
		List<HashMap<String, Object>> topicInfo = statsDao.getTopicInfo();
		List<HashMap<String, Object>> personInfo = statsDao.getPersonInfo();
		List<HashMap<String, Object>> newsInfo = statsDao.getNewsInfo();
		if (topicInfo == null || personInfo == null || newsInfo == null) {
			throw DatabaseException.getInstance();
		}
		List<ArrayList<HashMap<String, Object>>> resultItem = new ArrayList<ArrayList<HashMap<String, Object>>>();
		ArrayList<HashMap<String, Object>> topicResultList = new ArrayList<HashMap<String, Object>>();
		int k = 0;
		while (k < topicInfo.size()) {
			HashMap<String, Object> topicItem = new HashMap<String, Object>();
			topicItem.put("topicId", topicInfo.get(k).get("topicId"));
			topicItem.put("topic", topicInfo.get(k).get("topic"));
			topicItem.put("topicPage", TOPIC_URL
					+ topicInfo.get(k).get("topic"));

			topicResultList.add(k, topicItem);
			k++;
		}
		resultItem.add(topicResultList);

		ArrayList<HashMap<String, Object>> personResultList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		while (i < personInfo.size()) {
			HashMap<String, Object> personItem = new HashMap<String, Object>();
			personItem.put("userName", personInfo.get(i).get("userName"));
			personItem.put("userPhoto",
					USER_LOGO_URL + personInfo.get(i).get("userPhoto"));
			personItem.put("sex", personInfo.get(i).get("sex"));
			personItem.put("province", personInfo.get(i).get("province"));
			personItem.put("city", personInfo.get(i).get("city"));
			personItem.put("signature", personInfo.get(i).get("signature"));
			personItem.put("homePage",
					PERSON_HOMEPAGE + personInfo.get(i).get("userName"));

			personResultList.add(i, personItem);
			i++;
		}
		resultItem.add(personResultList);

		ArrayList<HashMap<String, Object>> newsResultList = new ArrayList<HashMap<String, Object>>();
		int j = 0;
		while (j < newsInfo.size()) {
			HashMap<String, Object> newsItem = new HashMap<String, Object>();
			newsItem.put("newsId", newsInfo.get(j).get("newsId"));
			newsItem.put("title", newsInfo.get(j).get("title"));
			newsItem.put(
					"newsPhoto",
					tOPIC_IMAGE_URL
							+ Stats.unixTimeStamp2Date(String.valueOf((newsInfo
									.get(j).get("unixTime"))), "yyyyMMdd/")
							+ newsInfo.get(j).get("newsPhoto"));
			newsItem.put("summary", Stats.stringFilter(
					newsInfo.get(j).get("summary") + "…",
					".attach.*./attach.|.size.*./size."));
			newsItem.put("newsPage",
					NEWS_SQUARE_URL + newsInfo.get(j).get("newsId"));

			newsResultList.add(j, newsItem);
			j++;
		}
		resultItem.add(newsResultList);
		String jsonResult = buildIndexInfoJson(resultItem);
		return jsonResult;
	}

	private String buildIndexInfoJson(
			List<ArrayList<HashMap<String, Object>>> resultItem) {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("indexInfo", resultItem);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}

}
