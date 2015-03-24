package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.dao.FollowMajorDao;
import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.dao.RetestDao;
import cn.itui.webdevelop.dao.ScoreDao;
import cn.itui.webdevelop.model.College;
import cn.itui.webdevelop.model.MajorInfo;
import cn.itui.webdevelop.model.Retest;
import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.recommend.CollegeRecommendFilter;
import cn.itui.webdevelop.utils.recommend.MajorRecommendFilter;
import cn.itui.webdevelop.utils.recommend.MajorRecommendResult;
import cn.itui.webdevelop.utils.recommend.SimilarMajorRecommendFilter;

public class MajorInfoServiceImpl implements MajorInfoService {
	private static final int N = 4;
	private CollegeDao collegeDao;
	private MajorDao majorDao;
	private MajorInfoDao majorInfoDao;
	private ScoreDao scoreDao;
	private RetestDao retestDao;
	private FollowMajorDao followMajorDao;
	private MajorRecommendFilter majorRecommendFilter;// 对数据库查询得到的major数据进行过滤
	private CollegeRecommendFilter collegeRecommendFilter;// 对数据库查询得到的college数据进行过滤

	public String getMajorInfo(String userCode, int majorId) throws Exception {
		// get major main info, base info, college logo and rank info
		HashMap<String, Object> majorAllInfos = majorInfoDao
				.findMajorAllInfoByMajorId(majorId);
		if (majorAllInfos == null)
			throw DatabaseException.getInstance();

		int followId = followMajorDao.isUserFollowMajor(userCode, majorId);

		String code = (String) majorAllInfos.get("code");
		int collegeId = (Integer) majorAllInfos.get("collegeId");

		// get year-score infos
		List<HashMap<String, Object>> yearScores = scoreDao
				.getLastNYearsScoreByMajorId(majorId, N);

		// recommend majors
		List<HashMap<String, Object>> candidateMajors = majorDao
				.findCodeLikeMajorByCollegeId(code, collegeId);
		MajorRecommendResult recommendMajors = majorRecommendFilter
				.recommendMajorFilter(candidateMajors, code, majorId);
		if (recommendMajors.getMajors().size() < SimilarMajorRecommendFilter.SAMECOLLEGE_MAJORCOUNT) {
			recommendMajors = processTransdisciplinary(recommendMajors,
					candidateMajors, collegeId, majorId, code);
		}
		// different college major recommend
		List<HashMap<String, Object>> candidateDiffCollMajors = majorDao
				.findAreaSameCodeMajorByCollegeIdAndMajorCode(collegeId, code);
		List<HashMap<String, Object>> diffCollRecommendMajors = majorRecommendFilter
				.recommendMajorFilter(candidateDiffCollMajors,
						(Double) majorAllInfos.get("degree"));
		// recommend college
		int collegeRank = (Integer) majorAllInfos.get("rank");
		List<College> candidateColleges = collegeDao.findCollegeInRank(
				collegeRank, collegeId);
		List<HashMap<String, Object>> recommendColleges = collegeRecommendFilter
				.recommendCollege(candidateColleges, collegeRank);
		// build json string
		String jsonResult = buildMajorInfoJson(majorAllInfos, followId,
				yearScores, recommendMajors, recommendColleges,
				diffCollRecommendMajors);
		return jsonResult;
	}

	public String getRetestInfo(int majorId) throws Exception {
		try {
			Retest retest = retestDao.findRetestByMajorId(majorId);
			String jsonResult = ResponseUtil.wrapNormalReturn(retest);
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			throw DatabaseException.getInstance();
		}
	}

	private MajorRecommendResult processTransdisciplinary(
			MajorRecommendResult recommendMajors,
			List<HashMap<String, Object>> candidateMajors, int collegeId,
			int majorId, String code) throws Exception {
		int needCount = SimilarMajorRecommendFilter.SAMECOLLEGE_MAJORCOUNT
				- recommendMajors.getMajors().size();
		List<HashMap<String, Object>> allMajors = majorDao
				.findMajorByCollegeIdAndNotInMajorIds(collegeId,
						candidateMajors);
		if (allMajors.size() >= needCount)
			recommendMajors.setTransdisciplinaryCount(needCount);
		recommendMajors = majorRecommendFilter.recommendMajorFilter(
				recommendMajors, allMajors, collegeId, majorId, code);
		// for (int i = 0; i < recommendMajors.getMajors().size(); i++){
		// recommendMajors.getMajors().get(i).get("")
		// }
		return recommendMajors;
	}

	private String buildMajorInfoJson(HashMap<String, Object> majorAllInfos,
			int followId, List<HashMap<String, Object>> yearScores,
			MajorRecommendResult recommendMajors,
			List<HashMap<String, Object>> recommendColleges,
			List<HashMap<String, Object>> diffCollRecommendMajors)
			throws Exception {
		LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		// base info
		LinkedHashMap<String, Object> baseInfoMap = new LinkedHashMap<String, Object>();
		baseInfoMap.put("majorId",
				EnDeCode.encodePara((Integer) majorAllInfos.get("majorId")));
		baseInfoMap.put("logo", majorAllInfos.get("logo"));
		baseInfoMap.put("majorName", majorAllInfos.get("name"));
		baseInfoMap.put("typeInfo", College.getTypeString(
				(Integer) majorAllInfos.get("is211"),
				(Integer) majorAllInfos.get("is985"),
				(Integer) majorAllInfos.get("is34")));
		baseInfoMap.put("school", majorAllInfos.get("school"));
		baseInfoMap.put("collegeId",
				EnDeCode.encodePara((Integer) majorAllInfos.get("collegeId")));
		baseInfoMap.put("followId", followId);
		baseInfoMap.put("college", majorAllInfos.get("college"));

		// grade info
		LinkedHashMap<String, Object> gradeInfoMap = new LinkedHashMap<String, Object>();
		gradeInfoMap.put("grade", majorAllInfos.get("grade"));
		gradeInfoMap.put("rateGrade", majorAllInfos.get("rateGrade"));
		gradeInfoMap.put("scoreGrade", majorAllInfos.get("scoreGrade"));
		gradeInfoMap.put("collegeGrade", majorAllInfos.get("collegeGrade"));
		gradeInfoMap.put("cityGrade", majorAllInfos.get("cityGrade"));
		// rank info
		LinkedHashMap<String, String> rankInfoMap = new LinkedHashMap<String, String>();
		rankInfoMap.put("majorRank",
				MajorInfo.translateRank(majorAllInfos.get("majorRank")));
		rankInfoMap.put("collegeRank",
				MajorInfo.translateRank(majorAllInfos.get("rank")));
		rankInfoMap.put("collegeLocalRank",
				MajorInfo.translateRank(majorAllInfos.get("localRank")));
		// score info
		LinkedHashMap<String, Object> scoreInfoMap = new LinkedHashMap<String, Object>();
		scoreInfoMap.put("yearScoreInfo", yearScores);
		scoreInfoMap.put("scoreAvg", majorAllInfos.get("scoreAvg"));
		scoreInfoMap.put("scoreLowYear", majorAllInfos.get("scoreLowYear"));
		scoreInfoMap.put("scoreLow", majorAllInfos.get("scoreLow"));
		scoreInfoMap.put("scoreHigh", majorAllInfos.get("scoreHigh"));
		scoreInfoMap.put("trend", majorAllInfos.get("trend"));
		// competition info
		LinkedHashMap<String, String> competitionInfoMap = new LinkedHashMap<String, String>();
		competitionInfoMap.put("degree",
				MajorInfo.formatDegree((Double) majorAllInfos.get("degree")));
		competitionInfoMap.put("rateDegree", MajorInfo
				.translateDegree((String) majorAllInfos.get("rateDegree")));
		competitionInfoMap.put("scoreDegree", MajorInfo
				.translateDegree((String) majorAllInfos.get("scoreDegree")));
		competitionInfoMap.put("collegeDegree", MajorInfo
				.translateDegree((String) majorAllInfos.get("collegeDegree")));
		competitionInfoMap.put("cityDegree", MajorInfo
				.translateDegree((String) majorAllInfos.get("cityDegree")));
		competitionInfoMap.put("degreeDescription",
				(String) majorAllInfos.get("degreeDescription"));
		// applyAdmit info
		LinkedHashMap<String, Object> applyAdmitInfoMap = new LinkedHashMap<String, Object>();
		int applyNum = (Integer) majorAllInfos.get("applyNum");
		int admitNum = (Integer) majorAllInfos.get("admitNum");
		applyAdmitInfoMap.put("rate", MajorInfo.translateRate(
				(Double) majorAllInfos.get("rate"), false));
		applyAdmitInfoMap.put("rateDescription",
				majorAllInfos.get("rateDescription"));
		applyAdmitInfoMap.put("applyDescription",
				MajorInfo.translateApplyDescription(applyNum));
		applyAdmitInfoMap.put("admitDescription",
				MajorInfo.translateAdmitDescription(admitNum));
		applyAdmitInfoMap.put("applyCount", applyNum);
		applyAdmitInfoMap.put("admitCount", admitNum);
		applyAdmitInfoMap.put("exemptionCount", majorAllInfos.get("exemption"));
		// major recommend info
		LinkedHashMap<String, Object> majorRecommendMap = new LinkedHashMap<String, Object>();
		majorRecommendMap.put("mainInfo", recommendMajors.getMajors());
		majorRecommendMap.put("similarCount",
				recommendMajors.getSimiliarCount());
		majorRecommendMap.put("nearCount", recommendMajors.getNearCount());
		majorRecommendMap.put("correlateCount",
				recommendMajors.getCorrelateCount());
		majorRecommendMap.put("transdisciplinaryCount",
				recommendMajors.getTransdisciplinaryCount());

		// college recommend info
		LinkedHashMap<String, Object> collegeRecommendMap = new LinkedHashMap<String, Object>();
		for (int i = 0; i < recommendColleges.size(); i++) {
			HashMap<String, Object> tempMap = recommendColleges.get(i);
			tempMap.put("rank", MajorInfo.translateRank(tempMap.get("rank")));
		}
		collegeRecommendMap.put("mainInfo", recommendColleges);

		// different college same major recommend info
		LinkedHashMap<String, Object> diffCollMajorRecommendMap = new LinkedHashMap<String, Object>();
		diffCollMajorRecommendMap.put("mainInfo", diffCollRecommendMajors);

		jsonMap.put("baseInfo", baseInfoMap);
		jsonMap.put("gradeInfo", gradeInfoMap);
		jsonMap.put("rankInfo", rankInfoMap);
		jsonMap.put("scoreInfo", scoreInfoMap);
		jsonMap.put("competitionInfo", competitionInfoMap);
		jsonMap.put("applyAdmitInfo", applyAdmitInfoMap);
		jsonMap.put("majorRecommendInfo", majorRecommendMap);
		jsonMap.put("interestedMajorInfo", diffCollMajorRecommendMap);
		jsonMap.put("interestedCollegeInfo", collegeRecommendMap);

		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		;
		return jsonStr;
	}

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

	public MajorInfoDao getMajorInfoDao() {
		return majorInfoDao;
	}

	public void setMajorInfoDao(MajorInfoDao majorInfoDao) {
		this.majorInfoDao = majorInfoDao;
	}

	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public MajorRecommendFilter getMajorRecommendFilter() {
		return majorRecommendFilter;
	}

	public void setMajorRecommendFilter(
			MajorRecommendFilter majorRecommendFilter) {
		this.majorRecommendFilter = majorRecommendFilter;
	}

	public CollegeRecommendFilter getCollegeRecommendFilter() {
		return collegeRecommendFilter;
	}

	public void setCollegeRecommendFilter(
			CollegeRecommendFilter collegeRecommendFilter) {
		this.collegeRecommendFilter = collegeRecommendFilter;
	}

	public RetestDao getRetestDao() {
		return retestDao;
	}

	public void setRetestDao(RetestDao retestDao) {
		this.retestDao = retestDao;
	}

	public FollowMajorDao getFollowMajorDao() {
		return followMajorDao;
	}

	public void setFollowMajorDao(FollowMajorDao followMajorDao) {
		this.followMajorDao = followMajorDao;
	}

	/*
	 * add
	 */
	public String getMajorRank(int majorId) throws Exception {
		// get major main info, base info, college logo and rank info
		List<HashMap<String, Object>> majorAllInfos = majorInfoDao
				.findMajorAllRankInfoByMajorId(majorId);
		if (majorAllInfos == null)
			throw DatabaseException.getInstance();
		// get subject name
		String subjectName = (String) majorAllInfos.get(1).get("subjectName");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		int j = 0;
		int i = 1;
		while (j < majorAllInfos.size()) {
			if (i == (Integer) majorAllInfos.get(j).get("rank")) {
				HashMap<String, Object> resultItem = new HashMap<String, Object>();
				resultItem.put("college", majorAllInfos.get(j).get("college"));
				resultItem.put(
						"collegeId",
						EnDeCode.encodePara((Integer) majorAllInfos.get(j).get(
								"collegeId")));
				resultItem.put("logo", majorAllInfos.get(j).get("logo"));
				resultItem.put("rank", majorAllInfos.get(j).get("rank"));
				resultItem.put("school", majorAllInfos.get(j).get("school"));
				resultItem.put("degree", majorAllInfos.get(j).get("degree"));
				resultItem.put("typeInfo", College.getRankTypeString(
						(Integer) majorAllInfos.get(j).get("is34"),
						(Integer) majorAllInfos.get(j).get("is985"),
						(Integer) majorAllInfos.get(j).get("is211")));

				List<HashMap<String, Object>> majorInfoMap = new ArrayList<HashMap<String, Object>>();

				while ((Integer) majorAllInfos.get(j).get("rank") == i) {
					HashMap<String, Object> majorItem = new HashMap<String, Object>();
					majorItem.put("majorName",
							majorAllInfos.get(j).get("majorName"));
					majorItem.put("majorId", EnDeCode
							.encodePara((Integer) majorAllInfos.get(j).get(
									"majorId")));
					majorInfoMap.add(majorItem);
					j++;
					if (j >= majorAllInfos.size())
						break;
				}
				resultItem.put("majorList", majorInfoMap);

				resultList.add(resultItem);
			}
			i++;

		}

		// build json string
		String jsonResult = buildMajorRankJson(resultList, subjectName);
		return jsonResult;
	}

	private String buildMajorRankJson(List<HashMap<String, Object>> resultList,
			String subjectName) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rankList", resultList);
		jsonMap.put("subjectName", subjectName);
		String jsonStr = ResponseUtil.wrapNormalReturn(jsonMap);
		return jsonStr;
	}
}
