package cn.itui.webdevelop.service;

import javax.servlet.http.HttpServletRequest;

public interface MajorInfoService {
	public String getMajorInfo(HttpServletRequest request, int majorId) throws Exception;
	public String getRetestInfo(int majorId) throws Exception;
}
