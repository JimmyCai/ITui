package cn.itui.webdevelop.service;

import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

public interface FollowService {
	public String followCollege(String code, int collegeId) throws ParameterErrorException, DatabaseException;
	public String followMajor(String code, int majorId) throws ParameterErrorException, DatabaseException;
	public String getFollowColleges(String code) throws ParameterErrorException;
	public String getFollowMajors(String code) throws ParameterErrorException;
	public String deleteFollowCollege(int id, String code, int collegeId) throws ParameterErrorException, DatabaseException;
	public String deleteFollowMajor(int id) throws ParameterErrorException, DatabaseException;
}
