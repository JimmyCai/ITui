package cn.itui.webdevelop.model;

import java.text.DecimalFormat;

/**
 * MajorInfo entity. @author MyEclipse Persistence Tools
 */

public class MajorInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5546559089860474174L;
	private Integer id;
	private Integer majorId;
	private String grade;
	private String rateGrade;
	private String scoreGrade;
	private String collegeGrade;
	private String cityGrade;
	private Integer majorRank;
	private String rateDegree;
	private String scoreDegree;
	private String collegeDegree;
	private String cityDegree;
	private Double degree;
	private String degreeDescription;
	private Double rate;
	private Integer scoreLow;
	private Integer scoreAvg;
	private Integer scoreHigh;
	private Integer scoreLowYear;
	private String trend;
	private Integer applyNum;
	private Integer admitNum;
	private Integer exemption;
	private Integer followNum;
	private String rateDescription;
	private Integer language;
	private Integer math;
	
	private static final int APPLYLARGENUM = 20;
	private static final int ADMITLARGENUM = 10;
	private static final String LARGESTR = "较多";
	private static final String NORMALSTR = "一般";

	// Constructors

	/** default constructor */
	public MajorInfo() {
	}

	/** minimal constructor */
	public MajorInfo(Integer majorId) {
		this.majorId = majorId;
	}

	/** full constructor */
	public MajorInfo(Integer majorId, String grade, String rateGrade,
			String scoreGrade, String collegeGrade, String cityGrade,
			Integer majorRank, String rateDegree, String scoreDegree,
			String collegeDegree, String cityDegree, Double degree,
			String degreeDescription, Double rate, Integer scoreLow,
			Integer scoreAvg, Integer scoreHigh, Integer scoreLowYear,
			String trend, Integer applyNum, Integer admitNum,
			Integer exemption, Integer followNum, String rateDescription,
			Integer language, Integer math) {
		this.majorId = majorId;
		this.grade = grade;
		this.rateGrade = rateGrade;
		this.scoreGrade = scoreGrade;
		this.collegeGrade = collegeGrade;
		this.cityGrade = cityGrade;
		this.majorRank = majorRank;
		this.rateDegree = rateDegree;
		this.scoreDegree = scoreDegree;
		this.collegeDegree = collegeDegree;
		this.cityDegree = cityDegree;
		this.degree = degree;
		this.degreeDescription = degreeDescription;
		this.rate = rate;
		this.scoreLow = scoreLow;
		this.scoreAvg = scoreAvg;
		this.scoreHigh = scoreHigh;
		this.scoreLowYear = scoreLowYear;
		this.trend = trend;
		this.applyNum = applyNum;
		this.admitNum = admitNum;
		this.exemption = exemption;
		this.followNum = followNum;
		this.rateDescription = rateDescription;
		this.language = language;
		this.math = math;
	}

	// Property accessors
	
	public static String translateRank(Object rank) {
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
	
	public static String translateDegree(String degree) {
		if(degree.equals("A"))
			return "高";
		else if(degree.equals("B") || degree.equals("C"))
			return "中";
		else
			return "低";
	}
	
	public static String formatDegree(double degree) {
		DecimalFormat dFormat = new DecimalFormat("#0.00");
		return dFormat.format(degree);
	}
	
	public static String translateRate(double rate, boolean needPrecent) {
		if(rate == 0) 
			return "-1";
		else {
			double tmp = 1/rate;
			long round = Math.round(tmp*1000);
			if(needPrecent)
				return round/10.0 + "%";
			else
				return round/10.0 + "";
		}
	}
	
	public static String translateApplyDescription(int applyNum) {
		if(applyNum >= APPLYLARGENUM)
			return LARGESTR;
		return NORMALSTR;
	}
	
	public static String translateAdmitDescription(int admitNum) {
		if(admitNum >= ADMITLARGENUM)
			return LARGESTR;
		return NORMALSTR;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRateGrade() {
		return this.rateGrade;
	}

	public void setRateGrade(String rateGrade) {
		this.rateGrade = rateGrade;
	}

	public String getScoreGrade() {
		return this.scoreGrade;
	}

	public void setScoreGrade(String scoreGrade) {
		this.scoreGrade = scoreGrade;
	}

	public String getCollegeGrade() {
		return this.collegeGrade;
	}

	public void setCollegeGrade(String collegeGrade) {
		this.collegeGrade = collegeGrade;
	}

	public String getCityGrade() {
		return this.cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	public Integer getMajorRank() {
		return this.majorRank;
	}

	public void setMajorRank(Integer majorRank) {
		this.majorRank = majorRank;
	}

	public String getRateDegree() {
		return this.rateDegree;
	}

	public void setRateDegree(String rateDegree) {
		this.rateDegree = rateDegree;
	}

	public String getScoreDegree() {
		return this.scoreDegree;
	}

	public void setScoreDegree(String scoreDegree) {
		this.scoreDegree = scoreDegree;
	}

	public String getCollegeDegree() {
		return this.collegeDegree;
	}

	public void setCollegeDegree(String collegeDegree) {
		this.collegeDegree = collegeDegree;
	}

	public String getCityDegree() {
		return this.cityDegree;
	}

	public void setCityDegree(String cityDegree) {
		this.cityDegree = cityDegree;
	}

	public Double getDegree() {
		return this.degree;
	}

	public void setDegree(Double degree) {
		this.degree = degree;
	}

	public String getDegreeDescription() {
		return this.degreeDescription;
	}

	public void setDegreeDescription(String degreeDescription) {
		this.degreeDescription = degreeDescription;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getScoreLow() {
		return this.scoreLow;
	}

	public void setScoreLow(Integer scoreLow) {
		this.scoreLow = scoreLow;
	}

	public Integer getScoreAvg() {
		return this.scoreAvg;
	}

	public void setScoreAvg(Integer scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	public Integer getScoreHigh() {
		return this.scoreHigh;
	}

	public void setScoreHigh(Integer scoreHigh) {
		this.scoreHigh = scoreHigh;
	}

	public Integer getScoreLowYear() {
		return this.scoreLowYear;
	}

	public void setScoreLowYear(Integer scoreLowYear) {
		this.scoreLowYear = scoreLowYear;
	}

	public String getTrend() {
		return this.trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public Integer getApplyNum() {
		return this.applyNum;
	}

	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}

	public Integer getAdmitNum() {
		return this.admitNum;
	}

	public void setAdmitNum(Integer admitNum) {
		this.admitNum = admitNum;
	}

	public Integer getExemption() {
		return this.exemption;
	}

	public void setExemption(Integer exemption) {
		this.exemption = exemption;
	}

	public Integer getFollowNum() {
		return this.followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public String getRateDescription() {
		return this.rateDescription;
	}

	public void setRateDescription(String rateDescription) {
		this.rateDescription = rateDescription;
	}

	public Integer getLanguage() {
		return this.language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Integer getMath() {
		return this.math;
	}

	public void setMath(Integer math) {
		this.math = math;
	}

}