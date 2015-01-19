/**
 * this controller is for searching
 */
package cn.itui.webdevelop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.service.MajorService;

@Controller
public class SearchController{
	
	private MajorService majorService;
	private CollegeService collegeService;
	
	@RequestMapping(value=URLConstants.SEARCH, method=RequestMethod.POST)
	public String search(HttpServletRequest request, HttpServletResponse response) throws Exception{

//		System.out.println(request.get);
		int type=Integer.parseInt(request.getParameter("t"));
		String condition = request.getParameter("c");
		
		Map<String, String[]> postMap = request.getParameterMap();
		String category = postMap.get("cg")[0];
		String subject = postMap.get("sj")[0];
		String college_type = postMap.get("ct")[0];
		String major_type = postMap.get("mt")[0];
		String area = postMap.get("a")[0];

		String result;
		if (type==1){
			result = majorService.searchMajorsList(condition, category, subject, major_type, college_type, area);
		}else if (type==2){
			result = collegeService.searchCollegeList(condition);
		}else {
			//error
			result="";
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
