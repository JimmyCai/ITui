package cn.itui.webdevelop.model;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1721098920691192044L;
	private Integer id;
	private Integer userId;
	private Integer majorId;
	private String content;
	private Integer usefulNum;
	private Integer uselessNum;
	private Integer fatherId;
	private Integer replyId;
	private Integer totalNum;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Integer userId, Integer majorId, String content,
			Integer usefulNum, Integer uselessNum, Integer totalNum) {
		this.userId = userId;
		this.majorId = majorId;
		this.content = content;
		this.usefulNum = usefulNum;
		this.uselessNum = uselessNum;
		this.totalNum = totalNum;
	}

	/** full constructor */
	public Comment(Integer userId, Integer majorId, String content,
			Integer usefulNum, Integer uselessNum, Integer fatherId,
			Integer replyId, Integer totalNum) {
		this.userId = userId;
		this.majorId = majorId;
		this.content = content;
		this.usefulNum = usefulNum;
		this.uselessNum = uselessNum;
		this.fatherId = fatherId;
		this.replyId = replyId;
		this.totalNum = totalNum;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUsefulNum() {
		return this.usefulNum;
	}

	public void setUsefulNum(Integer usefulNum) {
		this.usefulNum = usefulNum;
	}

	public Integer getUselessNum() {
		return this.uselessNum;
	}

	public void setUselessNum(Integer uselessNum) {
		this.uselessNum = uselessNum;
	}

	public Integer getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}