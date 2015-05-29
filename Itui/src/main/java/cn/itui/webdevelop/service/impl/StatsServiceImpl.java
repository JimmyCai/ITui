package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.binary.Base64;

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
	public static final String COOKIE_NAME = "tzg__user_login";

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
		String returnJsonName = "statsInfo";
		String jsonResult = buildStatsJson(resultItem, returnJsonName);
		return jsonResult;
	}

	private String buildStatsJson(HashMap<String, Object> resultItem,
			String returnName) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(returnName, resultItem);
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
			HashMap<String, Object> personEduInfo = new HashMap<String, Object>();
			long userId = (Long) personInfo.get(i).get("userId");
			System.out.println("fdakjfe:"+userId);
			personEduInfo = statsDao.getUserEduInfo(userId);
			// String userSchool;
			if (personEduInfo == null)
			{
				personItem.put("userSchool", "");
				personItem.put("degree", "");
			}else
			{
				personItem.put("userSchool", personEduInfo.get("userSchool"));
				personItem.put("degree", personEduInfo.get("degree"));
			}
			personItem.put("userName", personInfo.get(i).get("userName"));
			personItem.put(
					"userPhoto",
					USER_LOGO_URL
							+ ((String) personInfo.get(i).get("userPhoto"))
									.replace("min", "mid"));
			
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
			newsItem.put(
					"summary",
					Stats.stringFilter(
							newsInfo.get(j).get("summary") + "…",
							".attach.*./attach.|.size.*./size.|.quote.|./quote.|.list.|./list.|.b.|.i.|./b.|./i.|.code.*./code.|..\\*.|.\\*."));
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

	public String getUserInfo(Cookie[] cookies, String authHashKey)
			throws Exception {
		HashMap<String, Object> userInfoResult = new HashMap<String, Object>();
		String hashString = "";
		String returnJsonName = "userInfo";
		// cookies为空的话则返回
		if (cookies == null) {
			userInfoResult.put("user", "null");
			return buildStatsJson(userInfoResult, returnJsonName);
		} else {
			for (int i = 0; i < cookies.length; i++) {
				//获取对应cookie，进行解码
				if (cookies[i].getName().equalsIgnoreCase(COOKIE_NAME)) {
					hashString = cookies[i].getValue().toString();
					String tHashString = hashString.replace("-", "+");
					tHashString = tHashString.replace("_", "/");
					tHashString = tHashString.replace(".", "=");
					String decodeHashString = new String(
							Base64.decodeBase64(tHashString.getBytes()));
					StringBuffer sBuffer = new StringBuffer();
					for (int t = 1; t <= decodeHashString.length(); t++) {
						String string = decodeHashString.substring(t - 1, t);
						String keyString = authHashKey.substring(
								(t % authHashKey.length()) - 2,
								(t % authHashKey.length()) - 1);
						char tmpString = (char) string.compareTo(keyString);
						sBuffer.append(tmpString);
					}
					String[] strArray = sBuffer.toString().split("!;-");
					HashMap<String, Object> hashData = new HashMap<String, Object>();
					for (int j = 0; j < strArray.length; j++) {
						String[] subStrArray = strArray[j].split("^]+");
						if (subStrArray[0] != null) {
							hashData.put(subStrArray[0], subStrArray[1]);
						}
					}
					String userName = (String) hashData.get("user_name");
					int userId = (Integer) hashData.get("uid");
					String password = (String) hashData.get("password");
					HashMap<String, Object> userInfo = new HashMap<String, Object>();

					userInfo = statsDao.getUserInfo(userName, userId, password);
					// 数据库中没有匹配的用户则返回
					if (userInfo == null) {
						userInfoResult.put("user", "null");
						return buildStatsJson(userInfoResult, returnJsonName);
					} else {
						userInfoResult
								.put("userName", userInfo.get("userName"));
						userInfoResult.put("userPhoto", USER_LOGO_URL
								+ userInfo.get("userPhoto"));
						userInfoResult.put("userPage", PERSON_HOMEPAGE
								+ userInfo.get("userName"));
						return buildStatsJson(userInfoResult, returnJsonName);
					}
				}
			}
			// cookies中不存在对应的cookie
			userInfoResult.put("user", "null");
			return buildStatsJson(userInfoResult, returnJsonName);
		}
	}

}
