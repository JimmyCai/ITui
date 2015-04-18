package cn.itui.webdevelop.model;


/**
 * 获取课程开始时间，并分类
 * */

public class Course implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7979981260171276786L;
	private int id;
	private String photo;
	private String teacherName;
	private String orgName;
	private String orgWeb;
	private String summary;
	private String startDay;
	private String startTime;
	private String endDay;
	private String endTime;
	private String platform;
	private String platformWeb;
	private String liveSrc;
	private String tag;
	private int price;
	private int lesson;

	public static int getDateType(Object object,Object object2){
		//今天 返回0
		if((Integer.parseInt((String) object)<=Integer.parseInt(Stats.getDate()))&&(Integer.parseInt((String) object2)>=Integer.parseInt(Stats.getDate())))
			return 0;
		//明天 返回1
		else if((Integer.parseInt(Stats.getDate())+1)==Integer.parseInt((String) object))
			return 1;
		//明天以后 返回2
		else 
		return 2;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhotoName() {
		return photo;
	}

	public void setPhotoName(String photoName) {
		this.photo = photoName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getOrg() {
		return orgName;
	}

	public void setOrg(String org) {
		this.orgName = org;
	}

	public String getOrgWeb() {
		return orgWeb;
	}

	public void setOrgWeb(String orgWeb) {
		this.orgWeb = orgWeb;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatformWeb() {
		return platformWeb;
	}

	public void setPlatformWeb(String platformWeb) {
		this.platformWeb = platformWeb;
	}

	public String getLiveSrc() {
		return liveSrc;
	}

	public void setLiveSrc(String liveSrc) {
		this.liveSrc = liveSrc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLesson() {
		return lesson;
	}

	public void setLesson(int lesson) {
		this.lesson = lesson;
	}
	

}
