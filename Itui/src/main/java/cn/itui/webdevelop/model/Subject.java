package cn.itui.webdevelop.model;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5784072318634205377L;
	private Integer id;
	private String code;
	private String name;
	private Integer categoryId;

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** minimal constructor */
	public Subject(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public Subject(String code, String name, Integer categoryId) {
		this.code = code;
		this.name = name;
		this.categoryId = categoryId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}