package cn.itui.webdevelop.model;

/**
 * Follow entity. @author MyEclipse Persistence Tools
 */

public class Follow implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8123206148785538969L;
	private Integer id;
	private Integer userId;
	private Integer majorId;

	// Constructors

	/** default constructor */
	public Follow() {
	}

	/** full constructor */
	public Follow(Integer userId, Integer majorId) {
		this.userId = userId;
		this.majorId = majorId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

}