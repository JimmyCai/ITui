package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itui.webdevelop.utils.RequestUtil;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.DatabaseException;
import cn.itui.webdevelop.utils.exception.MyNumberFormatException;
import cn.itui.webdevelop.utils.exception.OtherException;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;
import cn.itui.webdevelop.utils.exception.SessionExceedException;

/**
 * AOP类
 * @author jimmycai
 *
 */
public class RequestResponseAdvice implements MethodInterceptor{
	private static Log rRLogger = LogFactory.getLog("requestResponse");

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		//get request and response
		HttpServletRequest request = (HttpServletRequest) methodInvocation.getArguments()[0];
		HttpServletResponse response = (HttpServletResponse) methodInvocation.getArguments()[1];	
		try {
			String result = (String) methodInvocation.proceed();
			ResponseUtil.httpResponse(response, result);
			rRLogger.info(RequestUtil.getUserBaseInfo(request) + "\n" + "RESULT:\n" + result + "\n");
		}catch(Exception e) {
			if(e instanceof DatabaseException || e instanceof MyNumberFormatException || e instanceof ParameterErrorException || e instanceof SessionExceedException) {
				ResponseUtil.httpResponseException(response, e);
				e.printStackTrace();
				rRLogger.error(RequestUtil.getUserBaseInfo(request) + "\n" + "RESULT EXCEPTION MESSAGE:\n" + e.getMessage() + "EXCEPTION CLASS:" + e.getClass() + "\n");
			}
			else {
				ResponseUtil.httpResponseException(response, OtherException.getInstance());
				e.printStackTrace();
				rRLogger.error(RequestUtil.getUserBaseInfo(request) + "\n" + "RESULT EXCEPTION MESSAGE:\n" + e.toString() + "EXCEPTION CLASS:" + e.getMessage() + "\n");
			}
		}	
		return null;
	}

}
