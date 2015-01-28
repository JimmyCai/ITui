/**
 * this controller is for searching
 */
package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.CollegeService;
import cn.itui.webdevelop.service.MajorService;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;

@Controller
public class SearchController{
	
	private MajorService majorService;
	private CollegeService collegeService;
	
	@RequestMapping(value=URLConstants.SEARCH, method=RequestMethod.POST)
	public String search(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String tString= request.getParameter("t");
		if (tString==null) {
			throw ParameterErrorException.getInstance("缺少参数");
		}
		int type=Integer.parseInt(tString);
		String condition = request.getParameter("c");
		if (condition==null) throw ParameterErrorException.getInstance("缺少参数");
			
		String result;
		if (type==1){
			//major
			String category = request.getParameter("cg");
			if (category==null) throw ParameterErrorException.getInstance("缺少参数");
			String subject = request.getParameter("sj");
			if (subject==null) throw ParameterErrorException.getInstance("缺少参数");
			String area = request.getParameter("a");
			if (area==null) throw ParameterErrorException.getInstance("缺少参数");
			String college_type = request.getParameter("ct");
			if (college_type==null) throw ParameterErrorException.getInstance("缺少参数");
			String major_type = request.getParameter("mt");
			if (major_type==null) throw ParameterErrorException.getInstance("缺少参数");
			if (request.getParameter("l")==null) throw ParameterErrorException.getInstance("缺少参数");
			int limit = 0;
			try{
				limit = Integer.parseInt(request.getParameter("l"));
			}catch (Exception e){
				throw MyNumberFormatException.getInstance();
			}
			
			result = majorService.searchMajorsList(condition, category, subject, major_type, college_type, area, limit);
		}else if (type==2){
			//college
			result = collegeService.searchCollegeList(condition);
		}else {
			//error
			throw ParameterErrorException.getInstance("参数错误");
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
