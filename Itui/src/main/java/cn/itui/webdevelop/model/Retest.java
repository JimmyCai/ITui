package cn.itui.webdevelop.model;

/**
 * Retest entity. @author MyEclipse Persistence Tools
 */

public class Retest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2913377928553048280L;
	// Fields

	private Integer id;
	private Integer majorId;
	private String direction;
	private String proportion;
	private String majorTotal;
	private String englishTotal;
	private String subjectiveQuestion;
	private String objectiveQuestion;
	private String questionNum;
	private String timeRestrict;
	private String listening;
	private String reading;
	private String writing;
	private String englishOther;
	private String teamDiscussion;
	private String questionAnswer;
	private String translation;
	private String oralOther;
	private String paper;
	private String collegeFrom;
	private String eduBackground;

	// Constructors

	/** default constructor */
	public Retest() {
	}

	/** minimal constructor */
	public Retest(Integer majorId) {
		this.majorId = majorId;
	}

	/** full constructor */
	public Retest(Integer majorId, String direction, String proportion,
			String majorTotal, String englishTotal, String subjectiveQuestion,
			String objectiveQuestion, String questionNum, String timeRestrict,
			String listening, String reading, String writing,
			String englishOther, String teamDiscussion, String questionAnswer,
			String translation, String oralOther, String paper,
			String collegeFrom, String eduBackground) {
		this.majorId = majorId;
		this.direction = direction;
		this.proportion = proportion;
		this.majorTotal = majorTotal;
		this.englishTotal = englishTotal;
		this.subjectiveQuestion = subjectiveQuestion;
		this.objectiveQuestion = objectiveQuestion;
		this.questionNum = questionNum;
		this.timeRestrict = timeRestrict;
		this.listening = listening;
		this.reading = reading;
		this.writing = writing;
		this.englishOther = englishOther;
		this.teamDiscussion = teamDiscussion;
		this.questionAnswer = questionAnswer;
		this.translation = translation;
		this.oralOther = oralOther;
		this.paper = paper;
		this.collegeFrom = collegeFrom;
		this.eduBackground = eduBackground;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMajorId() {
		return this.majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProportion() {
		return this.proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getMajorTotal() {
		return this.majorTotal;
	}

	public void setMajorTotal(String majorTotal) {
		this.majorTotal = majorTotal;
	}

	public String getEnglishTotal() {
		return this.englishTotal;
	}

	public void setEnglishTotal(String englishTotal) {
		this.englishTotal = englishTotal;
	}

	public String getSubjectiveQuestion() {
		return this.subjectiveQuestion;
	}

	public void setSubjectiveQuestion(String subjectiveQuestion) {
		this.subjectiveQuestion = subjectiveQuestion;
	}

	public String getObjectiveQuestion() {
		return this.objectiveQuestion;
	}

	public void setObjectiveQuestion(String objectiveQuestion) {
		this.objectiveQuestion = objectiveQuestion;
	}

	public String getQuestionNum() {
		return this.questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getTimeRestrict() {
		return this.timeRestrict;
	}

	public void setTimeRestrict(String timeRestrict) {
		this.timeRestrict = timeRestrict;
	}

	public String getListening() {
		return this.listening;
	}

	public void setListening(String listening) {
		this.listening = listening;
	}

	public String getReading() {
		return this.reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getWriting() {
		return this.writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getEnglishOther() {
		return this.englishOther;
	}

	public void setEnglishOther(String englishOther) {
		this.englishOther = englishOther;
	}

	public String getTeamDiscussion() {
		return this.teamDiscussion;
	}

	public void setTeamDiscussion(String teamDiscussion) {
		this.teamDiscussion = teamDiscussion;
	}

	public String getQuestionAnswer() {
		return this.questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getTranslation() {
		return this.translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getOralOther() {
		return this.oralOther;
	}

	public void setOralOther(String oralOther) {
		this.oralOther = oralOther;
	}

	public String getPaper() {
		return this.paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getCollegeFrom() {
		return this.collegeFrom;
	}

	public void setCollegeFrom(String collegeFrom) {
		this.collegeFrom = collegeFrom;
	}

	public String getEduBackground() {
		return this.eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}

}