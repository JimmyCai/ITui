package cn.itui.webdevelop.service;

public interface CollegeService {
	public String searchCollegeList(String condition);
	public String findSchoolsById(int CollegeId);
	public String findMajorsBySchool(int collegeId, String school);
}
