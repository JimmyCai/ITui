package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;
import cn.itui.webdevelop.service.FollowService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.RequestUtil;

@Controller
public class CollegeController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	public static final String USERPASSWORD = "up";
	public static final String COLLEGEID = "cid";
	private CollegeService collegeService;
	private FollowService followService;

	@RequestMapping(URLConstants.COLLEGE)
	public String getCollegeSchools(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (request.getParameter(COLLEGEID) ==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = 1;
		try{
			String collegeIdStr = request.getParameter(COLLEGEID);
			collegeId = EnDeCode.decodePara(collegeIdStr);
			String requestStr = RequestUtil.getUserBaseInfo(request) + COLLEGEID + ":" + collegeId;
			rRLogger.info(requestStr);
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		return collegeService.findSchoolsById(collegeId);
	}
	
	@RequestMapping(URLConstants.SCHOOL)
	public String getSchoolMajors(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (request.getParameter(COLLEGEID)==null) {
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		int collegeId = 1;
		try {
			Integer.parseInt(request.getParameter(COLLEGEID));
		} catch (Exception e) {
			throw MyNumberFormatException.getInstance();
		}
		String school = request.getParameter("s");
		if (school==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		return collegeService.findMajorsBySchool(collegeId, school);
	}
	
	@RequestMapping(URLConstants.FOLLOWCOLLEGE)
	public String followCollege(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userPassword = request.getParameter(USERPASSWORD);
		String collegeIdStr = request.getParameter(COLLEGEID);
		int collegeId = EnDeCode.decodePara(collegeIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + USERPASSWORD + ":" + userPassword + "\t" + COLLEGEID + ":" + collegeId;
		rRLogger.info(requestStr);
		return followService.followCollege(userPassword, collegeId);		
	}
	
	@RequestMapping(URLConstants.DISFOLLOWCOLLEGE)
	public String disFollowCollege(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		String userPassword = request.getParameter(USERPASSWORD);
		String collegeIdStr = request.getParameter(COLLEGEID);
		int collegeId = EnDeCode.decodePara(collegeIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + "ID:" + id + "\t" + USERPASSWORD + ":" + userPassword + "\t" + COLLEGEID + ":" + collegeId;
		rRLogger.info(requestStr);
		return followService.deleteFollowCollege(id, userPassword, collegeId);
	}
	
	@RequestMapping(URLConstants.GETFOLLOWCOLLEGE)
	public String getFollowColleges(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userPassword = request.getParameter(USERPASSWORD);
		String requestStr = RequestUtil.getUserBaseInfo(request) + USERPASSWORD + ":" + userPassword;
		rRLogger.info(requestStr);
		return followService.getFollowColleges(userPassword);
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public FollowService getFollowService() {
		return followService;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}
}
