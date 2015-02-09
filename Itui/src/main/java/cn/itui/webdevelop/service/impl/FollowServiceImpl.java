package cn.itui.webdevelop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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

	public String followCollege(String code, int collegeId) throws ParameterErrorException, DatabaseException {
		if(code.length() != MD5LENGTH || collegeId <= 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int retInt = followCollegeDao.insertFollowCollegeWithCollegeId(code, collegeId);
		if(retInt != 1)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(FOLLOWCOLLEGE_SUCCESS);
	}

	public String followMajor(String code, int majorId) throws ParameterErrorException, DatabaseException {
		if(code.length() != MD5LENGTH || majorId <= 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int imr = followMajorDao.insertFollowMajor(code, majorId);
		if(imr != 1)
			throw DatabaseException.getInstance();
		followCollegeDao.insertFollowCollegeWithMajorId(code, majorId);		
		return ResponseUtil.wrapNormalReturn(FOLLOWMAJOR_SUCCESS);
	}

	public String getFollowColleges(String code) throws ParameterErrorException {
		if(code.length() != MD5LENGTH)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		List<HashMap<String, Object>> result = followCollegeDao.findFollowCollegeByUserCode(code);
		return ResponseUtil.wrapNormalReturn(result);
	}

	public String getFollowMajors(String code) throws ParameterErrorException {
		if(code.length() != MD5LENGTH)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		List<HashMap<String, Object>> followMajors = followMajorDao.findFollowMajorByUserCode(code);
		List<HashMap<String, Object>> followColleges = followCollegeDao.findFollowCollegeByUserCode(code);
		return buildJson(followMajors, followColleges);
	}

	public String deleteFollowCollege(String code, int collegeId) throws ParameterErrorException, DatabaseException {
		if(collegeId < 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int fretInt = followCollegeDao.deleteFollowCollegeByCollegeIdAndCode(collegeId, code);
		followMajorDao.deleteFollowMajorByCollegeIdAndCode(code, collegeId);
		if(fretInt != 1)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(DISFOLLOWCOLLEGE_SUCCESS);
	}

	public String deleteFollowMajor(String code, int majorId) throws ParameterErrorException, DatabaseException {
		if(majorId < 0)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int retInt = followMajorDao.deleteFollowMajorByMajorIdAndCode(code, majorId);
		if(retInt < 0)
			throw DatabaseException.getInstance();
		return ResponseUtil.wrapNormalReturn(DISFOLLOWMAJOR_SUCCESS);
	}
	
	private String buildJson(List<HashMap<String, Object>> followMajors, List<HashMap<String, Object>> followColleges) {
		HashMap<Integer, List<HashMap<String, Object>>> collegeIdMajors = new HashMap<Integer, List<HashMap<String,Object>>>();
		for(HashMap<String, Object> curList : followMajors) {
			int curCollegeId = (Integer)curList.get("collegeId");
			if(!collegeIdMajors.containsKey(curCollegeId)) {
				List<HashMap<String, Object>> tmpList = new ArrayList<HashMap<String,Object>>();
				tmpList.add(curList);
				collegeIdMajors.put(curCollegeId, tmpList);
			}
			else {
				collegeIdMajors.get(curCollegeId).add(curList);
			}
		}
		
		List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String,Object>>();
		Set<Entry<Integer, List<HashMap<String, Object>>>> collegeNameMajorsSet = collegeIdMajors.entrySet();
		for(Entry<Integer, List<HashMap<String, Object>>> curEntry : collegeNameMajorsSet) {
			List<HashMap<String, Object>> curCollegeMajors = curEntry.getValue();
			int collegeId = curEntry.getKey();
			String collegeName = (String)curCollegeMajors.get(0).get("collegeName");
			int followCollegeId = (Integer)curCollegeMajors.get(0).get("followCollegeId");
			List<HashMap<String, Object>> resultMajors = new ArrayList<HashMap<String,Object>>();
			for(HashMap<String, Object> curMajor : curCollegeMajors) {
				HashMap<String, Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("majorId", curMajor.get("majorId"));
				tmpMap.put("followMajorId", curMajor.get("followMajorId"));
				tmpMap.put("majorName", curMajor.get("majorName"));
				resultMajors.add(tmpMap);
			}
			LinkedHashMap<String, Object> curResultMap = new LinkedHashMap<String, Object>();
			curResultMap.put("collegeId", collegeId);
			curResultMap.put("collegeName", collegeName);
			curResultMap.put("followCollegeId", followCollegeId);
			curResultMap.put("followMajorCount", curCollegeMajors.size());
			curResultMap.put("followMajors", resultMajors);
			result.add(curResultMap);
		}
		for(HashMap<String, Object> curMap : followColleges) {
			if(!collegeIdMajors.containsKey((Integer)curMap.get("collegeId"))) {
				LinkedHashMap<String, Object> curResultMap = new LinkedHashMap<String, Object>();
				curResultMap.put("collegeId", curMap.get("collegeId"));
				curResultMap.put("collegeName", curMap.get("collegeName"));
				curResultMap.put("followCollegeId", curMap.get("followCollegeId"));
				curResultMap.put("followMajorCount", 0);
				curResultMap.put("followMajors", new ArrayList<String>());
				result.add(curResultMap);
			}
		}

		return ResponseUtil.wrapNormalReturn(result);
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
