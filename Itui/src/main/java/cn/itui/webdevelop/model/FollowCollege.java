package cn.itui.webdevelop.model;

import java.io.Serializable;

public class FollowCollege implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224605774818057434L;
	
	private Integer id;
	private Integer userId;
	private Integer collegeId;
	
	public FollowCollege(){
		
	}
	
	public FollowCollege(Integer id, Integer userId, Integer collegeId) {
		this.id = id;
		this.userId = userId;
		this.collegeId = collegeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}
}
