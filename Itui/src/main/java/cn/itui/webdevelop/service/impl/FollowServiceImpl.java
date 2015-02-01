package cn.itui.webdevelop.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.dao.FollowCollegeDao;
import cn.itui.webdevelop.dao.FollowMajorDao;
import cn.itui.webdevelop.service.FollowService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

public class FollowServiceImpl implements FollowService{
	public static final String FOLLOWCOLLEGE_SUCCESS = "关注学校成功！";
	public static final String FOLLOWMAJOR_SUCCESS = "关注专业成功！";
	public static final String DISFOLLOWCOLLEGE_SUCCESS = "取消关注学校成功！";
	public static final String DISFOLLOWMAJOR_SUCCESS = "取消关注专业成功！";
	private FollowCollegeDao followCollegeDao;
	private FollowMajorDao followMajorDao;
	
	private final static int MD5LENGTH = 32;

	public String followCollege(String userPassword, int collegeId) throws ParameterErrorException, DatabaseException {
		if(userPassword.length() != MD5LENGTH || collegeId <= 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int retInt = followCollegeDao.insertFollowCollegeWithCollegeId(userPassword, collegeId);
		if(retInt != 1)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(FOLLOWCOLLEGE_SUCCESS);
	}

	public String followMajor(String userPassword, int majorId) throws ParameterErrorException, DatabaseException {
		if(userPassword.length() != MD5LENGTH || majorId <= 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int imr = followMajorDao.insertFollowMajor(userPassword, majorId);
		if(imr != 1)
			throw DatabaseException.getInstance();
		followCollegeDao.insertFollowCollegeWithMajorId(userPassword, majorId);		
		return ResponseUtil.wrapNormalReturn(FOLLOWMAJOR_SUCCESS);
	}

	public String getFollowColleges(String userPassword) throws ParameterErrorException {
		if(userPassword.length() != 32)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		List<HashMap<String, Object>> result = followCollegeDao.findFollowCollegeByUserCode(userPassword);
		return ResponseUtil.wrapNormalReturn(result);
	}

	public String getFollowMajors(String userPassword) throws ParameterErrorException {
		if(userPassword.length() != 32)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		List<HashMap<String, Object>> result = followMajorDao.findFollowMajorByUserCode(userPassword);
		return ResponseUtil.wrapNormalReturn(result);
	}

	public String deleteFollowCollege(int id, String userPassword, int collegeId) throws ParameterErrorException, DatabaseException {
		if(id < 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int fretInt = followCollegeDao.deleteFollowCollege(id);
		followMajorDao.deleteFollowMajorByCollegeIdAndCode(userPassword, collegeId);
		if(fretInt != 1)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(DISFOLLOWCOLLEGE_SUCCESS);
	}

	public String deleteFollowMajor(int id) throws ParameterErrorException, DatabaseException {
		if(id < 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int retInt = followMajorDao.deleteFollowMajorById(id);
		if(retInt < 0)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(DISFOLLOWMAJOR_SUCCESS);
	}

	public FollowCollegeDao getFollowCollegeDao() {
		return followCollegeDao;
	}

	public void setFollowCollegeDao(FollowCollegeDao followCollegeDao) {
		this.followCollegeDao = followCollegeDao;
	}

	public FollowMajorDao getFollowMajorDao() {
		return followMajorDao;
	}

	public void setFollowMajorDao(FollowMajorDao followMajorDao) {
		this.followMajorDao = followMajorDao;
	}

}
