package cn.itui.webdevelop.model;
/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8855610870483387470L;
	private Integer id;
	private Integer year;
	private Integer majorId;
	private Integer score;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** full constructor */
	public Score(Integer year, Integer majorId, Integer score) {
		this.year = year;
		this.majorId = majorId;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}