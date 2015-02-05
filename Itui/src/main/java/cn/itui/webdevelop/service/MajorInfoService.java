package cn.itui.webdevelop.service;

public interface MajorInfoService {
	public String getMajorInfo(String userCode, int majorId) throws Exception;
	public String getRetestInfo(int majorId) throws Exception;
}
