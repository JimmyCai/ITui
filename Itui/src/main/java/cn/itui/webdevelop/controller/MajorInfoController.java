package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.model.MajorInfo;
import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.ResponseUtil;

@Controller
public class MajorInfoController {
	private MajorInfoService majorInfoService;
	
	@RequestMapping(value=URLConstants.GETMAJORINFO)
	public void getMajorInfo(HttpServletRequest request, HttpServletResponse response){
		int majorId = Integer.parseInt(request.getParameter("majorId"));
		String retJson = majorInfoService.getMajorInfo(majorId);
		ResponseUtil.httpResponse(response, retJson);
	}
	
	@RequestMapping(value="/test")
	public void getMajor(HttpServletRequest request, HttpServletResponse response){
		int majorId = Integer.parseInt(request.getParameter("majorId"));
		MajorInfo majorInfo = majorInfoService.test(majorId);
		ResponseUtil.httpResponse(response, "yeeeeessss" + majorInfo.getGrade());
	}

	public MajorInfoService getMajorInfoService() {
		return majorInfoService;
	}

	public void setMajorInfoService(MajorInfoService majorInfoService) {
		this.majorInfoService = majorInfoService;
	}

}
