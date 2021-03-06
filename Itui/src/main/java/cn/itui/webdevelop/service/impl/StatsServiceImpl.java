package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;

import com.sun.mail.util.BASE64DecoderStream;

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
//	public static final String REGULAR_EXPRESSION = ".attach.*./attach.|.size.*./size.|.quote.|./quote.|.list.|./list.|.code.*./code.|\\[[a-zA-Z0-9\\*]\\]|\\[/[a-zA-Z0-9\\*]\\]|\\[/url\\]";

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
//		long start = System.nanoTime();
//		System.out.println(start);
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
		HashMap<String, Object> personIdMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> personEduResult = new ArrayList<HashMap<String, Object>>();
		// long start = System.nanoTime();
		// System.out.println(start);
		int i = 0;
		while (i < personInfo.size()) {
			HashMap<String, Object> personItem = new HashMap<String, Object>();
			long userId = (Long) personInfo.get(i).get("userId");
			// cast userId from long to int
			int uid = new Long(userId).intValue();
			personIdMap.put("userId" + i, uid);
			personItem.put("userId", uid);
			personItem.put("userSchool", "");
			personItem.put("degree", "");
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
		personEduResult = statsDao.getPersonEduInfo(personIdMap);
		for (int m = 0; m < personResultList.size(); m++) {
			for (int n = 0; n < personEduResult.size(); n++) {
				if (personResultList.get(m).get("userId") == (personEduResult
						.get(n).get("userId"))) {
					personResultList.get(m).put("userSchool",
							personEduResult.get(n).get("userSchool"));
					personResultList.get(m).put("degree",
							personEduResult.get(n).get("degree"));
					personEduResult.remove(n);
					// 匹配后将跳出当前循环
					break;
				}
			}
		}
		resultItem.add(personResultList);
		// long end = System.nanoTime();
		// System.out.println(end);
		// System.out.println("cost time:"+(end-start));

		ArrayList<HashMap<String, Object>> newsResultList = new ArrayList<HashMap<String, Object>>();
		int j = 0;
		while (j < newsInfo.size()) {
			HashMap<String, Object> newsItem = new HashMap<String, Object>();
			newsItem.put("newsId", newsInfo.get(j).get("newsId"));
			newsItem.put("title", newsInfo.get(j).get("title"));
			// 取出的时间减去8小时，转化为格林威治时间
			newsItem.put(
					"newsPhoto",
					tOPIC_IMAGE_URL
							+ Stats.unixTimeStamp2Date(String
									.valueOf((Integer) (newsInfo.get(j)
											.get("unixTime")) - 28800),
									"yyyyMMdd/")
							+ newsInfo.get(j).get("newsPhoto"));
			String message = Stats
					.bbcode2Text((String) newsInfo.get(j).get("message"));
			newsItem.put("summary", message.substring(0, 75) + "…");
			newsItem.put("newsPage",
					NEWS_SQUARE_URL + newsInfo.get(j).get("newsId"));

			newsResultList.add(j, newsItem);
			j++;
		}
		resultItem.add(newsResultList);
		String jsonResult = buildIndexInfoJson(resultItem);
//		 long end = System.nanoTime();
//		 System.out.println(end);
//		 System.out.println("cost time:"+(end-start));
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
				// 获取对应cookie，进行解码
				if (cookies[i].getName().equalsIgnoreCase(COOKIE_NAME)) {
					hashString = cookies[i].getValue().toString();
					// hashString =
					// "2cyVlJWNkoJvXaepltSV0pqlmsKVXWVqa2ZsanFkcaXV1F-Zp8-CnGGgk6mk2aXWnZaSj2uUaW1lmGpvmpacnJucl2hvmMLFmWKUmWaYmp1vaW6U";
					System.out.println("fhwe::" + hashString);
					String tHashString = hashString.replace("-", "+");
					tHashString = tHashString.replace("_", "/");
					tHashString = tHashString.replace(".", "=");
					System.out.println(tHashString);
					String decodeHashString = new String(
							BASE64DecoderStream
									.decode((tHashString.getBytes())),
							"iso8859-1");
					System.out.println(decodeHashString);
					System.out.println(decodeHashString.length());
					StringBuffer sBuffer = new StringBuffer();
					for (int t = 1; t <= decodeHashString.length(); t++) {
						String string = decodeHashString.substring(t - 1, t);
						System.out.println("char:" + string);
						String ahkString = authHashKey + authHashKey;
						int beginIndex = (t % authHashKey.length()) - 2;
						if (beginIndex < 0)
							beginIndex += authHashKey.length();
						String keyString = ahkString.substring(beginIndex,
								beginIndex + 1);
						System.out.println("keychar:" + keyString);
						char tmpString = (char) string.compareTo(keyString);
						System.out.println(tmpString);
						sBuffer.append(tmpString);
					}
					System.out.println(sBuffer);
					String[] strArray = sBuffer.toString().split("!;-");
					HashMap<String, Object> hashData = new HashMap<String, Object>();
					for (int j = 0; j < strArray.length; j++) {
						System.out.println(strArray[j]);
						String[] subStrArray = strArray[j].split("\\^\\]\\+");
						System.out.println(subStrArray[0]);
						if (subStrArray[0] != null) {
							hashData.put(subStrArray[0], subStrArray[1]);
						}
					}
					String userName = (String) hashData.get("user_name");
					int userId = Integer.valueOf((String) hashData.get("uid"));
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
						userInfoResult.put("userPhoto",
								USER_LOGO_URL
										+ userInfo.get("userPhoto").toString()
												.replace("min", "mid"));
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
