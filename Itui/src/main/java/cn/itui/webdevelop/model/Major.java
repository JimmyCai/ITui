package cn.itui.webdevelop.model;

/**
 * Major entity. @author MyEclipse Persistence Tools
 */

public class Major implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2784336549604868934L;
	private Integer id;
	private String name;
	private String code;
	private String school;
	private Integer collegeId;
	private Integer subjectId;
	private Integer type;

	// Constructors

	/** default constructor */
	public Major() {
	}

	/** minimal constructor */
	public Major(String name, String code, String school) {
		this.name = name;
		this.code = code;
		this.school = school;
	}

	/** full constructor */
	public Major(String name, String code, String school, Integer collegeId,
			Integer subjectId, Integer type) {
		this.name = name;
		this.code = code;
		this.school = school;
		this.collegeId = collegeId;
		this.subjectId = subjectId;
		this.type = type;
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

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}