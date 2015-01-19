package cn.itui.webdevelop.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static String getUserBaseInfo(HttpServletRequest request) {
		StringBuffer userInfoBuffer = new StringBuffer();
		userInfoBuffer.append("USER IP:" + request.getRemoteAddr() + "\t");
		userInfoBuffer.append("HOST:" + request.getRemoteHost() + "\t");
		userInfoBuffer.append("PORT:" + request.getRemotePort() + "\t");
		userInfoBuffer.append("REQUEST URL:" + request.getRequestURL() + "\t");
		userInfoBuffer.append("QUERY STRING:" + request.getQueryString() + "\t");
		userInfoBuffer.append("METHOD:" + request.getMethod() + "\t");
		return userInfoBuffer.toString();
	}

}
