package cn.itui.webdevelop.service;

import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

public interface FollowService {
	public String followCollege(String userPassword, int collegeId) throws ParameterErrorException, DatabaseException;
	public String followMajor(String userPassword, int majorId) throws ParameterErrorException, DatabaseException;
	public String getFollowColleges(String userPassword) throws ParameterErrorException;
	public String getFollowMajors(String userPassword) throws ParameterErrorException;
	public String deleteFollowCollege(int id, String userPassword, int collegeId) throws ParameterErrorException, DatabaseException;
	public String deleteFollowMajor(int id) throws ParameterErrorException, DatabaseException;
}
