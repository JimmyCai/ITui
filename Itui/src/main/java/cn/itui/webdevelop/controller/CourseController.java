package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		//不需要登录即可查看课程信息
//		String code = request.getParameter(CODE);
//		if(code == null)
//			throw NotLoginException.getInstance();
		return courseService.getAllCourse();
	}
	
	//验证是否有管理员权限
	@RequestMapping(value=URLConstants.API_COURSE_VERIFY)
	public String verifyPermission(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		if(code == null){
			throw NotLoginException.getInstance();
		}
		int adminId = courseService.verifyPermissionByCode(code);
		if(adminId<=0){
			throw PermissionDeniedException.getInstance();
		}
		return "0";
	}
	
	@RequestMapping(value=URLConstants.API_COURSE_RELEASE,method=RequestMethod.POST)
	public String addCourseInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String code = request.getParameter(CODE);
//		if(code == null)
//			throw NotLoginException.getInstance();
//		if(code != ADMIN_CODE)
//			throw PermissionDeniedException.getInstance();
		String courseInfo = RequestUtil.getJsonString(request);
		System.out.println("json is :"+courseInfo);
		return courseService.addNewCourse(courseInfo);
		
	}
	
	@RequestMapping(value=URLConstants.API_COURSE_RELEASE_UPLOADPHOTO,method=RequestMethod.POST)
	public String uploadTeacherPhoto(@RequestParam(value="teacherPhoto",required=false) MultipartFile photo) throws Exception{
		if (photo.isEmpty()) {
			throw FileUploadException.getInstance();
		}
		return courseService.uploadPhoto(photo);
		}
//	@RequestMapping(value=URLConstants.API_COURSE_RELEASE_UPLOADPHOTO,method=RequestMethod.POST)
//	public String uploadTeacherPhoto(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
	//创建一个通用的多部分解析器
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
	//判断request是否有文件上传
//		if(multipartResolver.isMultipart(request)){
//			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//			Iterator<String> iter = multiRequest.getFileNames();  
//            while(iter.hasNext()){  
//                MultipartFile file = multiRequest.getFile(iter.next());  
//                String fileName = "demoUpload" +file.getOriginalFilename();  
//                String path ="F:/" +fileName;  
//                File localFile = new File(path);  
//                file.transferTo(localFile);  
//		}			
//	}
////		return courseService.uploadPhoto();
//		return "success";
//	}
}
