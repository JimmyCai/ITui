/**
 * this controller is for searching
 */
package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.service.MajorService;
import cn.itui.webdevelop.utils.RequestUtil;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

@Controller
public class SearchController{
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	private MajorService majorService;
	private CollegeService collegeService;	
	
	@RequestMapping(value=URLConstants.SEARCH, method=RequestMethod.POST)
	public String search(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("search");
		String tString= request.getParameter("t");
		if (tString==null) {
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		System.out.println(tString);
		int type=Integer.parseInt(tString);
		String condition = request.getParameter("c");
		if (condition==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		condition = condition.replace(" ", "");
		System.out.println(new String(condition.getBytes("iso-8859-1"), "utf-8"));
		
		String area = request.getParameter("a");

		if (area==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		if (area.equalsIgnoreCase("全部")) area = "";
		String college_type = request.getParameter("ct");
		if (college_type==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		if (college_type.equalsIgnoreCase("全部")) college_type = "";
		if (request.getParameter("l")==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int limit = 0;
		try{
			limit = Integer.parseInt(request.getParameter("l"));
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		
		String result;
		if (type==1){
			//major
			String category = request.getParameter("cg");
			if (category==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
			if (category.equalsIgnoreCase("全部")) category = "";
			String subject = request.getParameter("sj");
			if (subject==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
			if (subject.length() > 4) subject = subject.substring(4);
			String major_type = request.getParameter("mt");
			if (major_type==null) throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
			if (major_type.equalsIgnoreCase("全部")) major_type = "";
			String requestStr = RequestUtil.getUserBaseInfo(request) + "t:" + tString + "\tc:" + condition + "\tcg:" + category + "\tsj:" + subject
					+ "\ta:" + area + "\tct:" + college_type + "\tmt:" + major_type + "\tl:" + limit;
			rRLogger.info(requestStr);

			long serviceStart = System.currentTimeMillis();

			result = majorService.searchMajorsList(condition, category, subject, major_type, college_type, area, limit);
			System.out.println("service time:"+(System.currentTimeMillis() - serviceStart));
		}else if (type==2){
			//college
			String requestStr = RequestUtil.getUserBaseInfo(request) + "t:" + tString + "\tc:" + condition;
			rRLogger.info(requestStr);
			result = collegeService.searchCollegeList(condition, area, college_type, limit);
		}else {
			//error
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		}
		return result;
	}

	public MajorService getMajorService() {
		return majorService;
	}

	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
	
}
