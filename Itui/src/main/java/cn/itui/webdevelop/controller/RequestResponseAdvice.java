package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.UserLoginInfo;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;

public class RequestResponseAdvice implements MethodInterceptor{
	private static Log rRLogger = LogFactory.getLog("requestResponse");

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		//get request and response
		HttpServletRequest request = (HttpServletRequest) methodInvocation.getArguments()[0];
		HttpServletResponse response = (HttpServletResponse) methodInvocation.getArguments()[1];
		//get user login info
//		UserLoginInfo userLoginInfo = UserLoginInfo.extractUserLoginInfoFromSession(request);
		//log request url and user login info
//		rRLogger.info(userLoginInfo.toLoggerString() + request.getRequestURI());
		rRLogger.info(request.getRequestURI());
		
		try {
			String result = (String) methodInvocation.proceed();
			ResponseUtil.httpResponse(response, result);
//			rRLogger.info(userLoginInfo.toLoggerString() + result);
			rRLogger.info(result);
		}catch(MyNumberFormatException e) {
			ResponseUtil.httpResponseException(response, e);
			rRLogger.error(e.getMessage());
//			rRLogger.info(userLoginInfo.toLoggerString() + e.getMessage());
		}catch (Exception e) {
			ResponseUtil.httpResponseException(response, e);
			rRLogger.error(e.getMessage());
//			rRLogger.info(userLoginInfo.toLoggerString() + e.getMessage());
		}
		return null;
	}

}
