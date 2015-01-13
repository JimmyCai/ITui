package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.UserLoginInfo;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;

@Controller
public class MajorInfoController {
	private static final Logger requestLogger = Logger.getLogger("REQUEST");
	private static final Logger responseLogger = Logger.getLogger("RESPONSE");
	private MajorInfoService majorInfoService;
	
	@RequestMapping(value=URLConstants.GETMAJORINFO)
	public void getMajorInfo(HttpServletRequest request, HttpServletResponse response){
//		UserLoginInfo userLoginInfo = UserLoginInfo.extractUserLoginInfoFromSession(request);
		try {
//			requestLogger.info(userLoginInfo.toLoggerString() + request.getRequestURI());
			int majorId = Integer.parseInt(request.getParameter("majorId"));
			String retJson = majorInfoService.getMajorInfo(majorId);
			ResponseUtil.httpResponse(response, retJson);
//			responseLogger.info(userLoginInfo.toLoggerString() + retJson);
		}catch(MyNumberFormatException e) {
			ResponseUtil.httpResponseException(response, e);
//			responseLogger.info(userLoginInfo.toLoggerString() + e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			ResponseUtil.httpResponseException(response, e);
		}
	}

	public MajorInfoService getMajorInfoService() {
		return majorInfoService;
	}

	public void setMajorInfoService(MajorInfoService majorInfoService) {
		this.majorInfoService = majorInfoService;
	}

}
