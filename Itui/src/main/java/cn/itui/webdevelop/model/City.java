package cn.itui.webdevelop.model;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 191336136317590100L;
	private Integer id;
	private String name;
	private Integer areaId;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(String name) {
		this.name = name;
	}

	/** full constructor */
	public City(String name, Integer areaId) {
		this.name = name;
		this.areaId = areaId;
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

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}