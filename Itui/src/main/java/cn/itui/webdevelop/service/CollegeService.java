package cn.itui.webdevelop.service;

import cn.itui.webdevelop.utils.exception.DatabaseException;

public interface CollegeService {
	public String searchCollegeList(String condition, String area, String college_type, int from);
	public String findSchoolsById(String code, String collegeName) throws DatabaseException;
	public String findMajorsBySchool(String collegeName, String school);
	/*
	 * add
	 * */
	public String getCollegeRank(String collegeName) throws Exception;
	public String getCollegeLocalRank(String collegeName) throws Exception;
}
