package cn.itui.webdevelop.model;

/**
 * College entity. @author MyEclipse Persistence Tools
 */

public class College implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8012716579009969857L;
	private Integer id;
	private String name;
	private String code;
	private String logo;
	private Integer is211;
	private Integer is985;
	private Integer is34;
	private Integer cityId;
	private Integer rank;
	private Integer localRank;
	private Integer followNum;
	
	public static final String COLLEGE_URL = "http://www.itui.cn/college?cid=";

	// Constructors

	/** default constructor */
	public College() {
	}

	/** minimal constructor */
	public College(String name) {
		this.name = name;
	}

	/** full constructor */
	public College(String name, String code, String logo, Integer is211,
			Integer is985, Integer is34, Integer cityId, Integer rank,
			Integer localRank, Integer followNum) {
		this.name = name;
		this.code = code;
		this.logo = logo;
		this.is211 = is211;
		this.is985 = is985;
		this.is34 = is34;
		this.cityId = cityId;
		this.rank = rank;
		this.localRank = localRank;
		this.followNum = followNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getIs211() {
		return this.is211;
	}

	public void setIs211(Integer is211) {
		this.is211 = is211;
	}

	public Integer getIs985() {
		return this.is985;
	}

	public void setIs985(Integer is985) {
		this.is985 = is985;
	}

	public Integer getIs34() {
		return this.is34;
	}

	public void setIs34(Integer is34) {
		this.is34 = is34;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getLocalRank() {
		return this.localRank;
	}

	public void setLocalRank(Integer localRank) {
		this.localRank = localRank;
	}

	public Integer getFollowNum() {
		return this.followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

}