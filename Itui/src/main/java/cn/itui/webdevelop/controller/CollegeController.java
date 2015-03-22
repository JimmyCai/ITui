package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.NotLoginException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;
import cn.itui.webdevelop.service.FollowService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.RequestUtil;

@Controller
public class CollegeController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	public static final String CODE = "code";
	public static final String COLLEGEID = "cid";
	private CollegeService collegeService;
	private FollowService followService;

	@RequestMapping(URLConstants.COLLEGE)
	public String getCollegeSchools(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		if (request.getParameter(CODE) == null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE); 
		if (request.getParameter(COLLEGEID) ==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = 1;
		String code="";
		try{
			String collegeIdStr = request.getParameter(COLLEGEID);
			code = request.getParameter(CODE);
			collegeId = EnDeCode.decodePara(collegeIdStr);
			String requestStr = RequestUtil.getUserBaseInfo(request) + COLLEGEID + ":" + collegeId+", "+CODE+":"+code;
			rRLogger.info(requestStr);
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		return collegeService.findSchoolsById(code, collegeId);
	}
	
	@RequestMapping(URLConstants.SCHOOL)
	public String getSchoolMajors(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (request.getParameter(COLLEGEID)==null) {
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		int collegeId = 1;
		try {
			String collegeIdStr = request.getParameter(COLLEGEID);
			collegeId = EnDeCode.decodePara(collegeIdStr);
		} catch (Exception e) {
			throw MyNumberFormatException.getInstance();
		}
		String school = request.getParameter("s");
		String requestStr = RequestUtil.getUserBaseInfo(request) + COLLEGEID + ":" + collegeId + "\ts:" + school;
		rRLogger.info(requestStr);
		if (school==null) 
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		return collegeService.findMajorsBySchool(collegeId, school);
	}
	
	@RequestMapping(value=URLConstants.FOLLOWCOLLEGE, method=RequestMethod.POST)
	public String followCollege(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		String collegeIdStr = request.getParameter(COLLEGEID);
		if(code == null)
			throw NotLoginException.getInstance();
		if(collegeIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = EnDeCode.decodePara(collegeIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + CODE + ":" + code + "\t" + COLLEGEID + ":" + collegeId;
		rRLogger.info(requestStr);
		return followService.followCollege(code, collegeId);		
	}
	
	@RequestMapping(value=URLConstants.DISFOLLOWCOLLEGE, method=RequestMethod.POST)
	public String disFollowCollege(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		String collegeIdStr = request.getParameter(COLLEGEID);
		System.out.println(collegeIdStr+"test");
		if(code == null)
			throw NotLoginException.getInstance();
		if(collegeIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = EnDeCode.decodePara(collegeIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + "\t" + CODE + ":" + code + "\t" + COLLEGEID + ":" + collegeId;
		rRLogger.info(requestStr);
		return followService.deleteFollowCollege(code, collegeId);
	}
	
	@RequestMapping(value=URLConstants.GETFOLLOWCOLLEGE, method=RequestMethod.POST)
	public String getFollowColleges(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		String requestStr = RequestUtil.getUserBaseInfo(request) + CODE + ":" + code;
		rRLogger.info(requestStr);
		if(code == null)
			throw NotLoginException.getInstance();
		return followService.getFollowColleges(code);
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
	
	/*
	 *  add
	 * */
	@RequestMapping(URLConstants.API_RANK_COLLEGE)
	public String getCollegeRank(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		if (request.getParameter(CODE) == null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE); 
		if (request.getParameter(COLLEGEID) ==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = 1;
		String code="";
		try{
			String collegeIdStr = request.getParameter(COLLEGEID);
			code = request.getParameter(CODE);
			collegeId = EnDeCode.decodePara(collegeIdStr);
			String requestStr = RequestUtil.getUserBaseInfo(request) + COLLEGEID + ":" + collegeId+", "+CODE+":"+code;
			rRLogger.info(requestStr);
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		return collegeService.getCollegeRank(collegeId);
	}
	
	@RequestMapping(URLConstants.API_RANK_LOCALRANK_COLLEGE)
	public String getCollegeLocalRank(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		if (request.getParameter(CODE) == null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE); 
		if (request.getParameter(COLLEGEID) ==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int collegeId = 1;
		String code="";
		try{
			String collegeIdStr = request.getParameter(COLLEGEID);
			code = request.getParameter(CODE);
			collegeId = EnDeCode.decodePara(collegeIdStr);
			String requestStr = RequestUtil.getUserBaseInfo(request) + COLLEGEID + ":" + collegeId+", "+CODE+":"+code;
			rRLogger.info(requestStr);
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		return collegeService.getCollegeLocalRank(collegeId);
	}
}
