package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import cn.itui.webdevelop.dao.CollegeDao;
import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.dao.MajorInfoDao;
import cn.itui.webdevelop.dao.ScoreDao;
import cn.itui.webdevelop.model.Major;
import cn.itui.webdevelop.model.MajorInfo;
import cn.itui.webdevelop.service.MajorInfoService;

public class MajorInfoServiceImpl implements MajorInfoService{
	private static final int N = 4;
	private CollegeDao collegeDao;
	private MajorDao majorDao;
	private MajorInfoDao majorInfoDao;
	private ScoreDao scoreDao;
	
	public MajorInfo test(int id) {
		return majorInfoDao.getMajorInfoById(id);
	}

	public String getMajorInfo(int id) {
		//get major main info
		MajorInfo majorMainInfo = majorInfoDao.getMajorInfoById(id);
		//get college logo and rank info
		Object[] collegeLogoAndRank = collegeDao.findLogoAndRankByMajorId(id);
		//get year-score infos
		List<Object[]> yearScores = scoreDao.getLastNYearsScoreByMajorId(id, N);
		
		//build json string
		String jsonResult = buildJson(majorMainInfo, collegeLogoAndRank, yearScores);
		return jsonResult;
	}
	
	private String buildJson(MajorInfo majorMainInfo, Object[] logoAndRank, List<Object[]> yearScores) {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		//base info
		HashMap<String, Object> baseInfoMap = new HashMap<String, Object>();
		baseInfoMap.put("collegeIndexPage", logoAndRank[0]);
		//grade info
		HashMap<String, String> gradeInfoMap = new HashMap<String, String>();
		gradeInfoMap.put("grade", majorMainInfo.getGrade());
		gradeInfoMap.put("rateGrade", majorMainInfo.getRateGrade());
		gradeInfoMap.put("scoreGrade", majorMainInfo.getScoreGrade());
		gradeInfoMap.put("collegeGrade", majorMainInfo.getCollegeGrade());
		gradeInfoMap.put("cityGrade", majorMainInfo.getCityGrade());
		//rank info
		HashMap<String, String> rankInfoMap = new HashMap<String, String>();
		rankInfoMap.put("majorRank", majorMainInfo.translateMajorRank());
		rankInfoMap.put("collegeRank", translaterank(logoAndRank[1].toString()));
		rankInfoMap.put("collegeLocalRank", translaterank(logoAndRank[2].toString()));
		//score info
		HashMap<String, Object> scoreInfoMap = new HashMap<String, Object>();
		ArrayList<HashMap<Object, Object>> yearScoreMap = new ArrayList<HashMap<Object,Object>>();
		for(Object[] curObjects : yearScores) {
			HashMap<Object, Object> curYearScoreMap = new HashMap<Object, Object>();
			curYearScoreMap.put("year", curObjects[0]);
			curYearScoreMap.put("score", curObjects[1]);
			yearScoreMap.add(curYearScoreMap);
		}
		scoreInfoMap.put("yearScoreInfo", yearScoreMap);
		scoreInfoMap.put("scoreAvg", majorMainInfo.getScoreAvg());
		scoreInfoMap.put("scoreLowYear", majorMainInfo.getScoreLowYear());
		scoreInfoMap.put("scoreLow", majorMainInfo.getScoreLow());
		scoreInfoMap.put("trend", majorMainInfo.getTrend());
		//competition info
		HashMap<String, String> competitionInfoMap = new HashMap<String, String>();
		competitionInfoMap.put("degree", majorMainInfo.getDegree().toString());
		competitionInfoMap.put("rateDegree", MajorInfo.translateDegree(majorMainInfo.getRateDegree()));
		competitionInfoMap.put("scoreDegree", MajorInfo.translateDegree(majorMainInfo.getScoreDegree()));
		competitionInfoMap.put("collegeDegree", MajorInfo.translateDegree(majorMainInfo.getCollegeDegree()));
		competitionInfoMap.put("cityDegree", MajorInfo.translateDegree(majorMainInfo.getCityDegree()));
		competitionInfoMap.put("degreeDescription", majorMainInfo.getDegreeDescription());
		//applyAdmit info
		HashMap<String, Object> applyAdmitInfoMap = new HashMap<String, Object>();
		applyAdmitInfoMap.put("rate", majorMainInfo.translateRate());
		applyAdmitInfoMap.put("applyDescription", majorMainInfo.getApplyDescription());
		applyAdmitInfoMap.put("admitDescription", majorMainInfo.getAdmitDescription());
		applyAdmitInfoMap.put("applyCount", majorMainInfo.getApplyNum()+"");
		applyAdmitInfoMap.put("admitCount", majorMainInfo.getAdmitNum()+"");
		applyAdmitInfoMap.put("exemptionCount", majorMainInfo.getExemption()+"");
		
		jsonMap.put("baseInfo", baseInfoMap);
		jsonMap.put("gradeInfo", gradeInfoMap);
		jsonMap.put("rankInfo", rankInfoMap);
		jsonMap.put("scoreInfo", scoreInfoMap);
		jsonMap.put("competitionInfo", competitionInfoMap);
		jsonMap.put("applyAdmitInfo", applyAdmitInfoMap);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(jsonMap);
		return jsonStr;
	}
	
	private String translaterank(Object rank) {
		if(rank == null) {
			return null;
		}
		try {
			int rankI = Integer.parseInt((String)rank);
			if(rankI > 1000)
				return (rankI - 1000) + "";
			else
				return rankI + "";
		} catch (Exception e) {
			return null;
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

}
