package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

@Controller
public class CollegeController {
	private CollegeService collegeService;

	@RequestMapping(URLConstants.COLLEGE)
	public String getCollegeSchools(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (request.getParameter("cid") ==null) throw ParameterErrorException.getInstance("缺少参数");
		int collegeId = 1;
		try{
			collegeId = Integer.parseInt(request.getParameter("cid"));
		}catch (Exception e){
			throw MyNumberFormatException.getInstance();
		}
		return collegeService.findSchoolsById(collegeId);
	}
	
	@RequestMapping(URLConstants.SCHOOL)
	public String getSchoolMajors(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (request.getParameter("cid")==null) {
			throw ParameterErrorException.getInstance("缺少参数");
		}
		int collegeId = 1;
		try {
			Integer.parseInt(request.getParameter("cid"));
		} catch (Exception e) {
			throw MyNumberFormatException.getInstance();
		}
		String school = request.getParameter("s");
		if (school==null) throw ParameterErrorException.getInstance("缺少参数");
		return collegeService.findMajorsBySchool(collegeId, school);
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
}
