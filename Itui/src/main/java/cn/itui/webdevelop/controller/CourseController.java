package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.CourseService;
import cn.itui.webdevelop.utils.exception.NotLoginException;

@Controller
public class CourseController {
	public static final String CODE = "code";
	private CourseService courseService;
	
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value=URLConstants.API_COURSE)
	public String getCourseInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		if(code == null)
			throw NotLoginException.getInstance();
		String retJson = courseService.getAllCourseInfo();
		return retJson;
	}

}
