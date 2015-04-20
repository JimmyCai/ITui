package cn.itui.webdevelop.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.itui.webdevelop.service.CourseService;
import cn.itui.webdevelop.utils.RequestUtil;
import cn.itui.webdevelop.utils.exception.NotLoginException;
import cn.itui.webdevelop.utils.exception.PermissionDeniedException;
import cn.itui.webdevelop.utils.exception.FileUploadException;

@Controller
public class CourseController {
	public static final String CODE = "code";
	public static final String COURSE_INFO ="courseInfo";
	public static final String TEACHER_NAME ="teacherName";
	private CourseService courseService;
	
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value=URLConstants.API_COURSE)
	public String getCourseInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return courseService.getAllCourse();
	}
	
	@RequestMapping(value=URLConstants.API_COURSE_VERIFY)
	public String verifyPermission(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		if(code == null){
			throw NotLoginException.getInstance();
		}
		int adminId = courseService.verifyPermissionByCode(code);
		if(adminId<0){
			throw PermissionDeniedException.getInstance();
		}else if (adminId == 0){
			return "-1";
		}
		return "0";
	}
	
	@RequestMapping(value=URLConstants.API_COURSE_RELEASE,method=RequestMethod.POST)
	public String addCourseInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String courseInfo = RequestUtil.getJsonString(request);
		System.out.println("json is :"+courseInfo);
		return courseService.addNewCourse(courseInfo);
		
	}
	
	
	@RequestMapping(value=URLConstants.API_COURSE_RELEASE_UPLOADPHOTO,method=RequestMethod.POST)
	public String uploadTeacherPhoto(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String result = null;
       //判断request是否有文件上传
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                MultipartFile photo = multiRequest.getFile(iter.next()); 
                if(photo.isEmpty()){
                	throw FileUploadException.getInstance();
                }
                result = courseService.uploadPhoto(photo); 
		}			
	}
		return result;
	}
}
