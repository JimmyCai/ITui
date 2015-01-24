package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.RequestUtil;

/**
 * 管理major info的控制器
 * @author jimmycai
 *
 */
@Controller
public class MajorInfoController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	private static final String MAJORID = "majorId";
	private MajorInfoService majorInfoService;
	
	@RequestMapping(value=URLConstants.GETMAJORINFO)
	public String getMajorInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String majorIdStr = request.getParameter(MAJORID);
		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + MAJORID + ":" + majorId;
		rRLogger.info(requestStr);
		String retJson = majorInfoService.getMajorInfo(request, majorId);
		return retJson;
	}
	
	@RequestMapping(value=URLConstants.GETRETESTINFO)
	public String getRetestInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String majorIdStr = request.getParameter(MAJORID);
		int majorId = EnDeCode.decodePara(majorIdStr);
		String requestStr = RequestUtil.getUserBaseInfo(request) + MAJORID + ":" + majorId;
		rRLogger.info(requestStr);
		String retJson = majorInfoService.getRetestInfo(majorId);
		return retJson;
	}

	public MajorInfoService getMajorInfoService() {
		return majorInfoService;
	}

	public void setMajorInfoService(MajorInfoService majorInfoService) {
		this.majorInfoService = majorInfoService;
	}

}
