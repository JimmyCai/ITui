package cn.itui.webdevelop.service;

import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
	public String findAllAliveCourse() throws Exception;
	public String addNewCourse(String courseInfo) throws Exception;
	public String uploadPhoto(MultipartFile photo) throws Exception;
	public int verifyPermissionByCode(String code);
	public String findAllCourse() throws Exception;
	public String findAllEndedCourse() throws Exception;
	public String deleteOneCourse(String courseCode);
}
