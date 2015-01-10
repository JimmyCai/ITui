package cn.itui.webdevelop.model;
/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3680547843709130081L;
	private Integer id;
	private Integer code;
	private String name;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}