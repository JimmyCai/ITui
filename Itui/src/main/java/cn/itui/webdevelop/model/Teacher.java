package cn.itui.webdevelop.model;

public class Teacher implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2587970598078245176L;
	private int id;
	private String name;
	private String photo;
	private String orgName;
	private String orgWeb;
	private String code;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** full constructor */
	public Teacher(String name, String photo, String orgName, String orgWeb,
			String code) {
		this.name = name;
		this.photo = photo;
		this.code = code;
		this.orgName = orgName;
		this.orgWeb = orgWeb;
	}

	// Property accessors
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgWeb() {
		return orgWeb;
	}

	public void setOrgWeb(String orgWeb) {
		this.orgWeb = orgWeb;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
