package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.CollegeService;

@Controller
public class CollegeController {
	private CollegeService collegeService;

	@RequestMapping(URLConstants.COLLEGE)
	public String getCollegeSchools(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int collegeId = Integer.parseInt(request.getParameter("cid"));
		return collegeService.findSchoolsById(collegeId);
	}
	
	@RequestMapping(URLConstants.SCHOOL)
	public String getSchoolMajors(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int collegeId = Integer.parseInt(request.getParameter("cid"));
		String school = request.getParameter("s");
		return collegeService.findMajorsBySchool(collegeId, school);
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}
}
