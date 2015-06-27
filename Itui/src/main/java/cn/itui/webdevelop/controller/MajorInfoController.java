package cn.itui.webdevelop.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.FollowService;
import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.RequestUtil;
import cn.itui.webdevelop.utils.exception.NotLoginException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

/**
 * 管理major info的控制器
 * @author jimmycai
 *
 */
@Controller
public class MajorInfoController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	public static final String CODE = "code";
	private static final String MAJORID = "mid";
	private MajorInfoService majorInfoService;
	private FollowService followService;
	

	
	@RequestMapping(value=URLConstants.GETMAJORINFO)
	public String getMajorInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String majorIdStr = request.getParameter(MAJORID);
		if(majorIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
//		int majorId = EnDeCode.decodePara(majorIdStr);
		System.out.println(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + MAJORID + ":" + majorIdStr;
		rRLogger.info(requestStr);
		String code ="";
		
//20150530 搜索不再需要登录 屏蔽code
//		String code = request.getParameter(CODE);
//		if(code == null)
//			throw NotLoginException.getInstance();
		
		String retJson = majorInfoService.getMajorInfo(code, majorIdStr);
		return retJson;
	}
	
	@RequestMapping(value=URLConstants.GETRETESTINFO)
	public String getRetestInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String majorIdStr = request.getParameter(MAJORID);
		if(majorIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + MAJORID + ":" + majorId;
		rRLogger.info(requestStr);
		String retJson = majorInfoService.getRetestInfo(majorId);
		return retJson;
	}
	
	@RequestMapping(value=URLConstants.FOLLOWMAJOR, method=RequestMethod.POST)
	public String followMajor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		String majorIdStr = request.getParameter(MAJORID);
		if(code == null)
			throw NotLoginException.getInstance();
		if(majorIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + CODE + ":" + code + "\t" + MAJORID + ":" + majorId;
		rRLogger.info(requestStr);
		return followService.followMajor(code, majorId);
	}
	
	@RequestMapping(value=URLConstants.DISFOLLOWMAJOR, method=RequestMethod.POST)
	public String disFollowMajor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		String majorIdStr = request.getParameter(MAJORID);
		if(code == null)
			throw NotLoginException.getInstance();
		if(majorIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + CODE + ":" + code + "\t" + MAJORID + ":" + majorId;
		rRLogger.info(requestStr);
		return followService.deleteFollowMajor(code, majorId);
	}
	
	@RequestMapping(value=URLConstants.GETFOLLOWMAJOR, method=RequestMethod.POST)
	public String getFollowMajors(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		if(code == null)
			throw NotLoginException.getInstance();
		String requestStr = RequestUtil.getUserBaseInfo(request) + CODE + ":" + code;
		rRLogger.info(requestStr);
		return followService.getFollowMajors(code);
	}

	public MajorInfoService getMajorInfoService() {
		return majorInfoService;
	}

	public void setMajorInfoService(MajorInfoService majorInfoService) {
		this.majorInfoService = majorInfoService;
	}

	public FollowService getFollowService() {
		return followService;
	}

	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}
	
	/**
	 * add
	 *
	 */
	@RequestMapping(value=URLConstants.API_RANK_MAJOR)
	public String getMajorRank(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String majorIdStr = request.getParameter(MAJORID);
		System.out.println("first str:"+majorIdStr);
		if(majorIdStr == null)
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
//		String fullname = new String(majorIdStr.getBytes("iso-8859-1"), "utf-8");
		String fullname = URLDecoder.decode(majorIdStr,"utf-8");
		System.out.println("after decode:"+fullname);
//		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + MAJORID + ":" + fullname;
		rRLogger.info(requestStr);
		return majorInfoService.getMajorRank(fullname);
	}

}
