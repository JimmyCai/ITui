package cn.itui.webdevelop.model;

import java.sql.Timestamp;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5056645228164003154L;
	private Integer id;
	private Integer gender;
	private Timestamp regesterTime;
	private Integer graduateYear;
	private Integer attendYear;
	private String logo;
	private Integer collegeId;
	private Integer majorId;
	private String englishLevel;
	private Integer englishScre;
	private Integer target1CollegeId;
	private Integer target2CollegeId;
	private Integer target3CollegeId;
	private Integer cityId;
	private String qq;
	private Integer phone;
	private Timestamp lastLoginTime;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(Integer gender, Timestamp regesterTime,
			Integer graduateYear, Integer attendYear, String logo,
			Integer collegeId, Integer majorId, String englishLevel,
			Integer englishScre, Integer target1CollegeId,
			Integer target2CollegeId, Integer target3CollegeId, Integer cityId,
			String qq, Integer phone, Timestamp lastLoginTime) {
		this.gender = gender;
		this.regesterTime = regesterTime;
		this.graduateYear = graduateYear;
		this.attendYear = attendYear;
		this.logo = logo;
		this.collegeId = collegeId;
		this.majorId = majorId;
		this.englishLevel = englishLevel;
		this.englishScre = englishScre;
		this.target1CollegeId = target1CollegeId;
		this.target2CollegeId = target2CollegeId;
		this.target3CollegeId = target3CollegeId;
		this.cityId = cityId;
		this.qq = qq;
		this.phone = phone;
		this.lastLoginTime = lastLoginTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Timestamp getRegesterTime() {
		return this.regesterTime;
	}

	public void setRegesterTime(Timestamp regesterTime) {
		this.regesterTime = regesterTime;
	}

	public Integer getGraduateYear() {
		return this.graduateYear;
	}

	public void setGraduateYear(Integer graduateYear) {
		this.graduateYear = graduateYear;
	}

	public Integer getAttendYear() {
		return this.attendYear;
	}

	public void setAttendYear(Integer attendYear) {
		this.attendYear = attendYear;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public Integer getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getEnglishLevel() {
		return this.englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}

	public Integer getEnglishScre() {
		return this.englishScre;
	}

	public void setEnglishScre(Integer englishScre) {
		this.englishScre = englishScre;
	}

	public Integer getTarget1CollegeId() {
		return this.target1CollegeId;
	}

	public void setTarget1CollegeId(Integer target1CollegeId) {
		this.target1CollegeId = target1CollegeId;
	}

	public Integer getTarget2CollegeId() {
		return this.target2CollegeId;
	}

	public void setTarget2CollegeId(Integer target2CollegeId) {
		this.target2CollegeId = target2CollegeId;
	}

	public Integer getTarget3CollegeId() {
		return this.target3CollegeId;
	}

	public void setTarget3CollegeId(Integer target3CollegeId) {
		this.target3CollegeId = target3CollegeId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getPhone() {
		return this.phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}