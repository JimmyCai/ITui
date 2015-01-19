package cn.itui.webdevelop.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.dao.ScoreDao;
import cn.itui.webdevelop.model.College;
import cn.itui.webdevelop.model.Major;
import cn.itui.webdevelop.model.MajorInfo;
import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.recommend.CollegeRecommendFilter;
import cn.itui.webdevelop.utils.recommend.MajorRecommendFilter;
import cn.itui.webdevelop.utils.recommend.MajorRecommendResult;
import cn.itui.webdevelop.utils.recommend.SimilarMajorRecommendFilter;

public class MajorInfoServiceImpl implements MajorInfoService{
	private static final int N = 4;
	private CollegeDao collegeDao;
	private MajorDao majorDao;
	private MajorInfoDao majorInfoDao;
	private ScoreDao scoreDao;
	private MajorRecommendFilter majorRecommendFilter;
	private CollegeRecommendFilter collegeRecommendFilter;

	public String getMajorInfo(int id, int random) throws Exception {
		//get major main info
		MajorInfo majorMainInfo = majorInfoDao.getMajorInfoById(id);
		//get college logo and rank info
		HashMap<String, Object> collegeLogoAndRank = collegeDao.findLogoAndRankByMajorId(id);
		//get year-score infos
		List<HashMap<String, Object>> yearScores = scoreDao.getLastNYearsScoreByMajorId(id, N);
		//get major base info
		Major majorBaseInfo = majorDao.findMajorById(id);
		List<HashMap<String, Object>> candidateMajors = majorDao.findCodeLikeMajorByCollegeId(majorBaseInfo.getCode(), majorBaseInfo.getCollegeId());
		MajorRecommendResult recommendMajors = majorRecommendFilter.recommendMajorFilter(candidateMajors, majorBaseInfo.getCode(), id);
		if(recommendMajors.getMajors().size() < SimilarMajorRecommendFilter.SAMECOLLEGE_MAJORCOUNT) {
			recommendMajors = processTransdisciplinary(recommendMajors, candidateMajors, majorBaseInfo.getCollegeId(), id, majorBaseInfo.getCode());
		}
		//different college major recommend
		List<HashMap<String, Object>> candidateDiffCollMajors = majorDao.findAreaSameCodeMajorByCollegeIdAndMajorCode(majorBaseInfo.getCollegeId(), majorBaseInfo.getCode());
		List<HashMap<String, Object>> diffCollRecommendMajors = majorRecommendFilter.recommendMajorFilter(candidateDiffCollMajors, majorMainInfo.getRate());
		//recommend college
		int collegeRank = (Integer)collegeLogoAndRank.get("rank");
		int collegeId = (Integer)collegeLogoAndRank.get("id");
		List<College> candidateColleges = collegeDao.findCollegeInRank(collegeRank, collegeId);
		List<HashMap<String, Object>> recommendColleges = collegeRecommendFilter.recommendCollege(candidateColleges, collegeRank);
		//build json string
		String jsonResult = buildJson(majorMainInfo, collegeLogoAndRank, yearScores,recommendMajors, recommendColleges, diffCollRecommendMajors, random);
		return jsonResult;
	}
	
	private MajorRecommendResult processTransdisciplinary(MajorRecommendResult recommendMajors, List<HashMap<String, Object>> candidateMajors, int collegeId, int majorId, String code) {
		int needCount = SimilarMajorRecommendFilter.SAMECOLLEGE_MAJORCOUNT - recommendMajors.getMajors().size();
		List<HashMap<String, Object>> allMajors = majorDao.findMajorByCollegeIdAndNotInMajorIds(collegeId, candidateMajors);
		if(allMajors.size() >= needCount)
			recommendMajors.setTransdisciplinaryCount(needCount);
		recommendMajors = majorRecommendFilter.recommendMajorFilter(recommendMajors, allMajors, collegeId, majorId, code);
		return recommendMajors;
	}
	
	private String buildJson(MajorInfo majorMainInfo, HashMap<String, Object> logoAndRank, List<HashMap<String, Object>> yearScores, 
			MajorRecommendResult recommendMajors, List<HashMap<String, Object>> recommendColleges, List<HashMap<String, Object>> diffCollRecommendMajors, int random) throws UnsupportedEncodingException {
		LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		//base info
		LinkedHashMap<String, Object> baseInfoMap = new LinkedHashMap<String, Object>();
		baseInfoMap.put("collegeIndexPage", College.COLLEGE_URL + EnDeCode.encodeId((Integer)logoAndRank.get("id"), random));
		//grade info
		LinkedHashMap<String, String> gradeInfoMap = new LinkedHashMap<String, String>();
		gradeInfoMap.put("grade", majorMainInfo.getGrade());
		gradeInfoMap.put("rateGrade", majorMainInfo.getRateGrade());
		gradeInfoMap.put("scoreGrade", majorMainInfo.getScoreGrade());
		gradeInfoMap.put("collegeGrade", majorMainInfo.getCollegeGrade());
		gradeInfoMap.put("cityGrade", majorMainInfo.getCityGrade());
		//rank info
		LinkedHashMap<String, String> rankInfoMap = new LinkedHashMap<String, String>();
		rankInfoMap.put("majorRank", majorMainInfo.translateMajorRank());
		rankInfoMap.put("collegeRank", translaterank(logoAndRank.get("rank")));
		rankInfoMap.put("collegeLocalRank", translaterank(logoAndRank.get("localRank")));
		//score info
		LinkedHashMap<String, Object> scoreInfoMap = new LinkedHashMap<String, Object>();
		scoreInfoMap.put("yearScoreInfo", yearScores);
		scoreInfoMap.put("scoreAvg", majorMainInfo.getScoreAvg());
		scoreInfoMap.put("scoreLowYear", majorMainInfo.getScoreLowYear());
		scoreInfoMap.put("scoreLow", majorMainInfo.getScoreLow());
		scoreInfoMap.put("scoreHigh", majorMainInfo.getScoreHigh());
		scoreInfoMap.put("trend", majorMainInfo.getTrend());
		//competition info
		LinkedHashMap<String, String> competitionInfoMap = new LinkedHashMap<String, String>();
		competitionInfoMap.put("degree", majorMainInfo.formatDegree());
		competitionInfoMap.put("rateDegree", MajorInfo.translateDegree(majorMainInfo.getRateDegree()));
		competitionInfoMap.put("scoreDegree", MajorInfo.translateDegree(majorMainInfo.getScoreDegree()));
		competitionInfoMap.put("collegeDegree", MajorInfo.translateDegree(majorMainInfo.getCollegeDegree()));
		competitionInfoMap.put("cityDegree", MajorInfo.translateDegree(majorMainInfo.getCityDegree()));
		competitionInfoMap.put("degreeDescription", majorMainInfo.getDegreeDescription());
		//applyAdmit info
		LinkedHashMap<String, Object> applyAdmitInfoMap = new LinkedHashMap<String, Object>();
		applyAdmitInfoMap.put("rate", MajorInfo.translateRate(majorMainInfo.getRate()));
		applyAdmitInfoMap.put("applyDescription", majorMainInfo.getApplyDescription());
		applyAdmitInfoMap.put("admitDescription", majorMainInfo.getAdmitDescription());
		applyAdmitInfoMap.put("applyCount", majorMainInfo.getApplyNum()+"");
		applyAdmitInfoMap.put("admitCount", majorMainInfo.getAdmitNum()+"");
		applyAdmitInfoMap.put("exemptionCount", majorMainInfo.getExemption()+"");
		//major recommend info
		LinkedHashMap<String, Object> majorRecommendMap = new LinkedHashMap<String, Object>();
		majorRecommendMap.put("mainInfo", recommendMajors.getMajors());
		majorRecommendMap.put("similarCount", recommendMajors.getSimiliarCount());
		majorRecommendMap.put("nearCount", recommendMajors.getNearCount());
		majorRecommendMap.put("correlateCount", recommendMajors.getCorrelateCount());
		majorRecommendMap.put("transdisciplinaryCount", recommendMajors.getTransdisciplinaryCount());
		
		//college recommend info
		LinkedHashMap<String, Object> collegeRecommendMap = new LinkedHashMap<String, Object>();
		collegeRecommendMap.put("mainInfo", recommendColleges);
		
		//different college same major recommend info
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
		
		LinkedHashMap<String, Object> retJsonMap = new LinkedHashMap<String, Object>();
		retJsonMap.put("status", 0);
		retJsonMap.put("normalReturn", jsonMap);
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonStr = gson.toJson(retJsonMap);
		return jsonStr;
	}
	
	private String translaterank(Object rank) {
		if(rank == null) {
			return "null";
		}
		try {
			int rankI = (Integer)rank;
			if(rankI > 1000)
				return (rankI - 1000) + "";
			else if(rankI == -1)
				return "null";
			else
				return rankI + "";
		} catch (Exception e) {
			return "null";
		}
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

	public void setMajorRecommendFilter(MajorRecommendFilter majorRecommendFilter) {
		this.majorRecommendFilter = majorRecommendFilter;
	}

	public CollegeRecommendFilter getCollegeRecommendFilter() {
		return collegeRecommendFilter;
	}

	public void setCollegeRecommendFilter(
			CollegeRecommendFilter collegeRecommendFilter) {
		this.collegeRecommendFilter = collegeRecommendFilter;
	}

}
